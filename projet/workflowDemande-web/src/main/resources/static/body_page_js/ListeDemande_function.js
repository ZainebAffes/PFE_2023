var listTypesDemandes = [];
var listEtats = [];
function drawBtnDemande() {
    DessinerButton('10', '#listetid_Demande');
    ActionBoutton();
}
function ActionBoutton() {
    $('#btn_Ajouter').unbind('click');
    $('#btn_Ajouter').bind('click', function (e) {
        $('#dropzones').empty();
        AfficheModalAddDemande();
        $("#typeMode").val("add");
    });
    $('#btn_Modifier').unbind('click');
    $('#btn_Modifier').bind('click', function (e) {
        var rowDde = $('#tableListDemande').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir un paramétrage de type de demande ", 'error', 3000);
        else {
            $('#dropzones').empty();
            var code = $('.selectionnee').find('td').eq(0).text();
            majDemande(code, "update");
        }
    });
    $('#btn_Consulter').unbind('click');
    $('#btn_Consulter').bind('click', function (e) {
        var rowDde = $('#tableListDemande').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir un paramétrage de type de demande", 'error', 3000);
        else {
            $('#dropzones').empty();
            var code = $('.selectionnee').find('td').eq(0).text();
            majDemande(code, "consult");
        }
    });
    $('#btn_Annuler').unbind('click');
    $('#btn_Annuler').bind('click', function (e) {
        var rowDde = $('#tableListDemande').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir un paramétrage de type de demande ", 'error', 3000);
        else
        {
            $('#dropzones').empty();
            var code = $('.selectionnee').find('td').eq(0).text();
            majDemande(code, "delete");
        }
    });
    $('#btn_Imprimer').unbind('click');
    $('#btn_Imprimer').bind('click', function (e) {
        var url = url_base + '/pdf/parametragedemandes';
        if ($('#search').val() !== "undefined") {
            url = url + '?designation=' + $('#search').val();
        }
        impressionListe(url);
    });
    $("#btn_Exporter").unbind("click");
    $("#btn_Exporter").bind("click", function (e) {
        const headers = [
            'Code',
            'Désignation',
            '	Type Demande '

        ];
        console.log(window);
        const workbook = new window.ExcelJS.Workbook();
        const downloadAsExcel = () => {
            workbook.xlsx.writeBuffer().then((data) => {
                const blob = new Blob([data], {
                    type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
                });
                saveAs(blob, `parametragedemandes.xlsx`);
            });
        };
        var url = url_base + '/parametragedemandes/';
        if ($('#search').val() !== "undefined") {
            url = url + '?designation=' + $('#search').val();
        }
        fetch(url, {
            method: "GET"
        }).then(async(response) => {
            const json = await response.json();
            console.log(json);
            const title = 'parametragedemandes';
            const worksheet = workbook.addWorksheet(
                    `parametragedemandes`
                    );
            worksheet.addRow(headers);
            json.forEach((parametragedemandes) => {
                const newRow = worksheet.addRow([]);
                newRow.getCell(1).value = parametragedemandes.code;
                newRow.getCell(2).value = parametragedemandes.designation;
                newRow.getCell(3).value = parametragedemandes.descriptionTypeDemande;
            });
            downloadAsExcel();
            worksheet.destroy();
        });
    });
}

function majDemande(code, action) {
    var Demande = findDemandeById(code);
    $('#modalAdd').modal('show');

    $('#designation').val(Demande.designation);
    $('#code').val(code);
    createSelectEtats();
    $('#listSelectEtat').select2("val", Demande.etats);
    createSelectTypesDemandes();
    $('#listSelectType').select2("val", Demande.codeTypeDemande);
    $('#codeTypeDemande').val(Demande.codeTypeDemande);
    $('#selectEtat').val(Demande.idEtat);

    if (action === "update") {
        $('#designation').prop("disabled", false);
        $('#codeTypeDemande').prop("disabled", false);
        $('#selectEtat').prop("disabled", false);
        //  $('#dropzones').empty();
        $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-edit"></i>');
        $('#labelTitre').text("Modification d'un paramétrage de type de demande");

        sessionStorage.setItem("Demande", 'modif');
        $("#btnMAJDemande").show();

    }
    if (action === "delete") {
        $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-trash"></i>');
        $('#labelTitre').text("Suppression d'un paramétrage de type de demande");
        $('#designation').prop("disabled", "disabled");
        $('#codeTypeDemande').prop("disabled", true);
        sessionStorage.setItem("Demande", 'delete');
        $("#btnMAJDemande").show();

    }
    if (action === "consult") {
        $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-list"></i>');
        $('#labelTitre').text("Détail d'un paramétrage de type de demande");
        $('#designation').prop("disabled", "disabled");
        $('#codeTypeDemande').prop("disabled", true);
        $('#selectEtat').prop("disabled", "disabled");
        $("#btnMAJDemande").hide();

    }

    DessinerEtiquette($('#code').val(), action);

}
function DessinerEtiquette(code) {
    var Demande = findDemandeById(code);
    for (let i = 0; i < Demande.etiquetteparametragedemandeDTOs.length; i++) {
        var etiquette = Demande.etiquetteparametragedemandeDTOs[i];
        $.ajax({
            url: url_base + '/etiquetteparametragedemandes/' + etiquette.code,
            method: "GET",
            success: function (etiquetteData) {
                const dropzones = document.getElementById("dropzones");

                const draggedTag = document.createElement('div');
                draggedTag.classList.add('dragging');
                draggedTag.setAttribute('draggable', 'true');
                draggedTag.setAttribute('data-type', etiquetteData.typeEtiquetteDTO.type);
                draggedTag.innerHTML = `<span class="glyphicon glyphicon-${getGlyphicon(etiquetteData.typeEtiquetteDTO.type)}"><i> ${etiquetteData.typeEtiquetteDTO.type}</i></span>`;

                const newInput = document.createElement('input');
                newInput.setAttribute('type', 'text');
                newInput.setAttribute('placeholder', 'Saisir le nom du champ');
                newInput.classList.add('dropped-nom');
                newInput.classList.add('nom');
                newInput.setAttribute('value', etiquetteData.description);

                draggedTag.appendChild(newInput);
                dropzones.appendChild(draggedTag);

                const newInput2 = document.createElement('input');
                newInput2.classList.add('dropped-tag');
                newInput2.setAttribute('id', 'text');
                newInput2.setAttribute('placeholder', etiquetteData.defultValue);
                newInput2.setAttribute('for', 'input');
                newInput2.setAttribute('type', getInputType(etiquetteData.typeEtiquetteDTO.type));

                const requiredLabel22 = document.createElement('label');
                requiredLabel22.innerHTML = 'Valeur par défaut';
                requiredLabel22.setAttribute('for', 'input2');
                newInput2.setAttribute('id', 'valeur');

                const deleteButton = document.createElement('button');
                deleteButton.innerHTML = 'Supprimer';
                deleteButton.classList.add('delete-tag');

                const requiredCheckbox = document.createElement('input');
                requiredCheckbox.setAttribute('type', 'checkbox');
                requiredCheckbox.setAttribute('id', 'requiredCheckbox');
                $('#requiredCheckbox').prop("checked", etiquetteData.isRequired);

                const requiredLabel = document.createElement('label');
                requiredLabel.innerHTML = 'Champ obligatoire';
                requiredLabel.setAttribute('for', 'requiredCheckbox');

                draggedTag.appendChild(requiredLabel22);
                draggedTag.appendChild(newInput2);
                draggedTag.appendChild(deleteButton);
                draggedTag.appendChild(requiredCheckbox);
                draggedTag.appendChild(requiredLabel);

                deleteButton.addEventListener('click', (e) => {
                    const parentTag = e.target.parentElement;
                    const grandParentTag = parentTag.parentElement;
                    grandParentTag.removeChild(parentTag);
                });
            }
        });
    }
}

function getGlyphicon(type) {
    switch (type) {
        case "text":
            return "text-width";
        case "number":
            return "option-horizontal";
        case "date":
            return "calendar";
        case "time":
            return "time";
        default:
            return "question-sign";
    }
}

function getInputType(type) {
    switch (type) {
        case "text":
            return "text";
        case "number":
            return "text";
        case "date":
            return "date";
        case "time":
            return "time";
        default:
            return "text";
    }
}


function DrawTableDemande() {
    window.parent.$.loader.open();
    setTimeout(function () {
        DrawListDemande("tableListDemande", '_grid_ListDemande');
        window.parent.$.loader.close();
    }, 100);
}
function DrawListDemande(idTable, idContainer) {
    showLoadingNotification();
    var List = [];
    List = findDemande(undefined);
    document.getElementById(idContainer).innerHTML = '';
    var table_list = "<table id='" + idTable + "' class='display dataTable projects-table table table-striped table-bordered table-hover' cellspacing='0'  width='100%' align='center'>";
    table_list += "</table>";
    $("#" + idContainer).html(table_list);
    var colDef = [2];
    var pageLength = parseInt(($(document).height() - 220) / 34);
    table = $('#' + idTable).on('page.dt', function () {}).DataTable({
        "dom": 'frtip',
        "searching": true,
        destroy: false,
        bPaginate: true,
        sort: false,
        data: List,
        language: dataTablesLang,
        "pageLength": pageLength,
        columns: [
            {
                title: "Code",
                data: 'code',
                render: function (data, type, row, meta) {
                    if (data !== null)
                        return data;
                    else
                        "";
                }
            },
            {
                title: "désignation",
                data: 'designation',
                render: function (data) {
                    if (data === undefined)
                        return '';
                    else
                        return "<span title='" + data + "'>" + data + "</span>";
                }
            },
            {
                title: "Type Demande",
                data: 'descriptionTypeDemande',
                render: function (data) {
                    if (data === undefined)
                        return '';
                    else
                        return "<span title='" + data + "'>" + data + "</span>";
                }
            }
        ],
        "aoColumnDefs": [{
                'bSortable': false,
                'aTargets': colDef
            }],
        "order": [[0, "asc"]]
    });
    $('#tableListDemande  tbody').delegate('tr', 'click', function (e) {
        var highlightColor = '#d9edf7';
        var css = $(this).attr('style');
        if ($(this).find('.dataTables_empty').length === 0) {
            if (css !== 'border-color: rgb(217, 237, 247); background-color: rgb(217, 237, 247)') {
                $('#' + idTable + ' > tbody > tr').removeAttr('style');
                $('#' + idTable + ' > tbody > tr').removeClass('selectionnee');
                $(this).addClass('selectionnee');
                $(this).css('background-color', highlightColor);
                $(this).css('border-color', highlightColor);
            } else {
                $(this).removeAttr('style');
            }
        }
        $('#' + idTable + ' tbody > tr').focus();
    });
    $("#search").on("keyup search input paste cut", function () {
        table.search(this.value).draw();
    });
    $('#tableListDemande > tbody').on('dblclick', function (e) {
        var rowDde = $('#tableListDemande').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', 'veuillez Sélectionner un Demande', 'error', 2000);
        else {
            var code = $('.selectionnee').find('td').eq(0).text();
            majDemande(code, 'consult');
        }
    });
    $('#tableListDemande_info').css("padding", '0');
    $('#tableListDemande_filter').hide();
    hideLoadingNotification();
}
function AfficheModalAddDemande() {
    $('#labelTitre').text("Ajout d'un paramétrage de type de demande");
    $('#modal_ajout_Demande_title h2').val("Ajout d'un paramétrage de type de demande");
    $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-plus"></i>');
    $('#modalAdd').modal('show');
    $('#code').val('');
    $('#designation').val('');
    $('#codeTypeDemande').val('');
    $('#code').prop("disabled", false);
    $('#designation').prop("disabled", false);
    $('#codeTypeDemande').prop("disabled", false);
    $('#designation').val('');
    $('#dropzones').empty();
    createSelectTypesDemandes();
    createSelectEtats();
    $("#btnMAJDemande").show();
    sessionStorage.setItem("Demande", 'ajout');
}
function submitMAJDemande() {
    if (sessionStorage.getItem("Demande") === 'delete') {
        deleteDemande($('#code').val());
    } else {
        if (($('#designation').val() === '')) {
            $('#designation').addClass('css-error');
            $('#designation').attr('style', 'border-width: 1px;background-color: #fff0f0;border-color: #A90329;');
        } else {
            $('#designation').removeClass('css-error');
            $('#designation').attr('style', '');
        }
        if (($('#codeTypeDemande').val() === '')) {
            $('#codeTypeDemande').addClass('css-error');
            $('#codeTypeDemande').attr('style', 'border-width: 1px;background-color: #fff0f0;border-color: #A90329;');
        } else {
            $('#codeTypeDemande').removeClass('css-error');
            $('#codeTypeDemande').attr('style', '');
        }
        if (($('#selectEtat').val() === '')) {
            $('#selectEtat').addClass('css-error');
            $('#selectEtat').attr('style', 'border-width: 1px;background-color: #fff0f0;border-color: #A90329;');
        } else {
            $('#selectEtat').removeClass('css-error');
            $('#selectEtat').attr('style', '');
        }

        if ($('.css-error').length > 0) {
            showNotification('Avertissement', "Veuillez vérifier le(s) champ(s) saisi(s) ! ", 'error', 3000);
        } else {

            var payload = payloadDemande();
            if (sessionStorage.getItem("Demande") === 'ajout') {
                var spec = findDemande($('#designation').val());
                if (spec.length > 0) {
                    DrawListRassemblant('tableListRassemblant', '_grid_ListRassemblant', spec);
                    $('#add_msg').html("<h6>designation(s)de règlement(s) ressemblante(s): </h6>");
                    $('#add_msg_confirm').html("<span>Voulez vous confirmer l'ajout ? </span>");
                    $('#addConfirm').modal('show');
                    $('#modalAdd').modal('hide');
                    $("#submitAdd").unbind('click');
                    $("#submitAdd").click(function () {
                        $('#addConfirm').modal('hide');
                        addDemande(payload);
                    });
                } else {
                    addDemande(payload);
                }
            } else {
                if (sessionStorage.getItem("Demande") === 'modif') {
                    updateDemande(payload);
                }
            }
        }
    }
}
function addDemande(list) {
    $.ajax({
        url: `${url_base}/parametragedemandes`,
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(list),
        async: false,
        dataType: 'text',
        headers: {
            'x-auth-token': localStorage.getItem("x-auth-token"),
            'Accept-Language': localStorage.getItem("langue")
        },
        success: function (data, textStatus, jqXHR) {
            showNotification('succès', 'Ajout effectuée avec Succès', 'success', 3000);
            $('#modalAdd').modal('hide');
            showLoadingNotification();
            setTimeout(function () {
                DrawTableDemande();
                hideLoadingNotification();
            }, 50);
        }
        ,
        complete: function (jqXHR, textStatus) {

        }
    });
}

function findDemandeById(id) {
    var response = "";
    $.ajax({
        url: `${url_base}/parametragedemandes/${id}`,
        type: 'GET',
        async: false,
        dataType: 'json',
        headers: {
            'x-auth-token': localStorage.getItem("x-auth-token"),
            'Accept-Language': localStorage.getItem("langue")
        },
        success: function (data)
        {
            response = data;
        }
    });
    return response;
}
function updateDemande(object) {
    $.ajax({
        url: `${url_base}/parametragedemandes`,
        type: 'PUT',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(object),
        async: false,
        dataType: 'text',
        headers: {
            'x-auth-token': localStorage.getItem("x-auth-token"),
            'Accept-Language': localStorage.getItem("langue")
        },
        success: function (data, textStatus, jqXHR) {
            showNotification('succès', 'Modification effectuée avec succès', 'success', 3000);
            $('#addConfirm').modal('hide');
            $('#modalAdd').modal('hide');
            showLoadingNotification();
            setTimeout(function () {
                DrawTableDemande();
                hideLoadingNotification();
            }, 50);
        }
        ,
        complete: function (jqXHR, textStatus) {

        }
    });
}

function deleteDemande(code) {
    var response = "";
    $.ajax({
        url: `${url_base}/parametragedemandes/${code}`,
        contentType: "text/html; charset=utf-8",
        type: 'DELETE',
        async: false,
        success: function (data)
        {
            response = data;
            showNotification('succès', "Suppression effectuée avec succès", 'success', 5000);
            $('#addConfirm').modal('hide');
            $('#modalAdd').modal('hide');
            showLoadingNotification();
            setTimeout(function () {
                DrawTableDemande();
                hideLoadingNotification();
            }, 50);
        }
    });
    return response;
}
function payloadDemande() {

    var oElements = document.querySelectorAll(".dropped-tag");
    var noms = document.querySelectorAll(".nom");
    var param = $('#code').val();
    if (oElements !== null) {
        var etiquettes = [];
        var typeEtiquetteDTOs = [];
        typeEtiquetteDTOs = findTypeEtiquette();

        for (var j = 0; j < noms.length; j++) {
            var etiquette = {};
            var selectedOptions = [];
            var checkedOptions = [];
            etiquette["description"] = noms[j].value;
            etiquette["min"] = oElements[j].getAttribute('min');
            etiquette["max"] = oElements[j].getAttribute('max');
            etiquette["isRequired"] = oElements[j].parentElement.querySelector('#requiredCheckbox').checked;
            etiquette["position"] = j;
            etiquette["defultValue"] = oElements[j].parentElement.querySelector('#valeur').value;
            etiquette["visible"] = oElements[j].style.display !== 'none';
            etiquette["multiple"] = oElements[j].getAttribute('multiple') !== null;
            etiquette["codeParametrageEtiquette"] = param;
            for (var x = 0; x < typeEtiquetteDTOs.length; x++) {
                if (oElements[j].getAttribute('type') === typeEtiquetteDTOs[x].type) {
                    etiquette["codeTypeEtiquette"] = typeEtiquetteDTOs[x].code;
                }
            }
            if (oElements[j].getAttribute('type') === 'checkbox') {
                const optionsContainer = oElements[j].parentElement.querySelector('.options-container');
                const options = optionsContainer.querySelectorAll('input[type=checkbox]');
                for (let i = 0; i < options.length; i++) {
                    if (options[i].checked) {
                        checkedOptions.push(options[i].parentNode.querySelector('input[type=text]').value);
                    }
                }
            } else if (oElements[j].getAttribute('type') === 'listeDeroulante') {
                const options = oElements[j].querySelectorAll('optionL');
                for (let i = 0; i < options.length; i++) {

                    selectedOptions.push(options[i].value);
                }

            }
            etiquette["optionEtiquetteDTOs"] = selectedOptions;
            etiquette["optionEtiquetteDTOs"] = checkedOptions;
            etiquettes.push(etiquette);
        }
    }
    var payload = {
        "code": $('#code').val(),
        "designation": $('#designation').val(),
        "codeTypeDemande": $('#codeTypeDemande').val(),
        "idEtat": $('#selectEtat').val(),
        "etiquetteparametragedemandeDTOs": etiquettes

    };
    return payload;
}
function DrawListRassemblant(idTable, idContainer, list) {
    document.getElementById(idContainer).innerHTML = '';
    var table_list = "<table id='" + idTable + "' class='display dataTable projects-table table table-striped table-bordered table-hover' cellspacing='0'  width='100%' align='center'>";
    table_list += "</table>";
    $("#" + idContainer).html(table_list);
    var colDef = [2];
    table = $('#' + idTable).on('page.dt', function () {}).DataTable({
        "dom": 'frtip',
        "searching": true,
        destroy: false,
        bPaginate: true,
        sort: false,
        data: list,
        language: dataTablesLang,
        "pageLength": 15,
        columns: [
            {
                title: "Code",
                data: 'code',
                render: function (data, type, row, meta) {
                    if (data !== null)
                        return data;
                    else
                        "";
                }
            },
            {
                title: "Désignation",
                data: 'designation',
                render: function (data) {
                    if (data === undefined)
                        return '';
                    else
                        return "<span title='" + data + "'>" + data + "</span>";
                }
            },
            {
                title: "Type demande",
                data: 'descriptionTypeDemande',
                render: function (data) {
                    if (data === undefined)
                        return '';
                    else
                        return "<span title='" + data + "'>" + data + "</span>";
                }
            }

        ],
        "aoColumnDefs": [{
                'bSortable': false,
                'aTargets': colDef
            }],
        "order": [[0, "asc"]]
    });
    $('#tableListRassemblant  tbody').delegate('tr', 'click', function (e) {
        var highlightColor = '#d9edf7';
        var css = $(this).attr('style');
        if ($(this).find('.dataTables_empty').length === 0) {
            if (css !== 'border-color: rgb(217, 237, 247); background-color: rgb(217, 237, 247)') {
                $('#' + idTable + ' > tbody > tr').removeAttr('style');
                $('#' + idTable + ' > tbody > tr').removeClass('selectionnee');
                $(this).addClass('selectionnee');
                $(this).css('background-color', highlightColor);
                $(this).css('border-color', highlightColor);
            } else {
                $(this).removeAttr('style');
            }
        }
        $('#' + idTable + ' tbody > tr').focus();
    });
    $("#search").on("keyup search input paste cut", function () {
        table.search(this.value).draw();
    });
    $('#tableListRassemblant_info').css("padding", '0');
    $('#tableListRassemblant_filter').hide();
    hideLoadingNotification();
}

function findDemande(designation) {
    var url = url_base + '/parametragedemandes';
    if (designation !== undefined) {
        url = url + '?designation=' + designation;
    }
    var response = "";
    $.ajax({
        url: url,
        contentType: "text/html; charset=utf-8",
        type: 'GET',
        dataType: "json",
        async: false,
        success: function (data)
        {
            response = data;
        }
    });
    return response;
}

function findTypeEtiquette() {
    var url = url_base + '/typeetiquettes';
    var response = "";
    $.ajax({
        url: url,
        contentType: "text/html; charset=utf-8",
        type: 'GET',
        dataType: "json",
        async: false,
        success: function (data)
        {
            response = data;
        }
    });
    return response;
}



function findEtats() {

    $.ajax({
        url: url_base + '/etats',
        contentType: "text/html; charset=utf-8",
        type: 'GET',
        dataType: "json",
        async: false,
        success: function (data)
        {
            listEtats = data;
        }
    });
}
function findTypesDemandes() {

    $.ajax({
        url: url_base + '/typedemandes/filter',
        contentType: "text/html; charset=utf-8",
        type: 'GET',
        dataType: "json",
        async: false,
        success: function (data)
        {
            listTypesDemandes = data;
        }
    });
}
function createSelectTypesDemandes() {
    var listType = listTypesDemandes;
    var select_html1 = "<select id='listSelectFamFourniss' class='select2' style='' >";
    select_html1 += "<option  selected='selected' value='' >" + " Choisir un type de demande " + "</option>";
    $.each(listType, function (i, item) {
        var code = listType[i].codeTypeDemande;
        var libelle = listType[i].description;
        select_html1 += "<option value='" + code + "'>" + libelle + "</option>";
    });
    select_html1 += "</select>";
    $('#codeTypeDemande').html(select_html1).trigger('create');
    $("#listSelectType").select2({
        placeholder: "Choisir Type",
        width: '100%',
        allowClear: true
    });
}

function createSelectEtats() {
    var listEtat = listEtats;
    var select_html1 = "<select id='listSelectEtat' class='select2' style='' >";
    select_html1 += "<option  selected='selected' value='' >" + " Choisir une état " + "</option>";
    $.each(listEtat, function (i, item) {
        var code = listEtat[i].code;
        var libelle = listEtat[i].designation;
        select_html1 += "<option value='" + code + "'>" + libelle + "</option>";
    });
    select_html1 += "</select>";
    $('#selectEtat').html(select_html1).trigger('create');
    $('#listSelectEtat').empty();
    $("#listSelectEtat").select2({
        placeholder: "Choisir une état",
        width: '100%',
        allowClear: true
    });
}