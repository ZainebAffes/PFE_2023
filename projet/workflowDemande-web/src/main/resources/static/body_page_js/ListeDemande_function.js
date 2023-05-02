function drawBtnDemande() {
    DessinerButton('10', '#listetid_Demande');
    ActionBoutton();
}
function ActionBoutton() {
    $('#btn_Ajouter').unbind('click');
    $('#btn_Ajouter').bind('click', function (e) {
        AfficheModalAddDemande();
        $("#typeMode").val("add");
    });

    $('#btn_Modifier').unbind('click');
    $('#btn_Modifier').bind('click', function (e) {
        var rowDde = $('#tableListDemande').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir une demande ", 'error', 3000);
        else {
            var code = $('.selectionnee').find('td').eq(0).text();
            majDemande(code, "update");
        }
    });

    $('#btn_Consulter').unbind('click');
    $('#btn_Consulter').bind('click', function (e) {
        var rowDde = $('#tableListDemande').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir une demande ", 'error', 3000);
        else {
            var code = $('.selectionnee').find('td').eq(0).text();
            majDemande(code, "consult");
        }
    });

    $('#btn_Annuler').unbind('click');
    $('#btn_Annuler').bind('click', function (e) {
        var rowDde = $('#tableListDemande').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir une demande ", 'error', 3000);
        else
        {
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
    $('#code').val(code);
    $('#designation').val(Demande.designation);
    $('#codeTypeDemande').val(Demande.codeTypeDemande);
    $('#code').prop("disabled", "disabled");

    if (action === "update") {
        $('#designation').prop("disabled", false);
        $('#codeTypeDemande').prop("disabled", false);
        $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-edit"></i>');
        $('#labelTitre').text("Modification d'une demande");
        sessionStorage.setItem("Demande", 'modif');
        $("#btnMAJDemande").show();
    }
    if (action === "delete") {
        $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-trash"></i>');
        $('#labelTitre').text("Annulation d'une demande");
        $('#designation').prop("disabled", "disabled");
        $('#codeTypeDemande').prop("disabled", true);
        $('#checkboxActif').prop("disabled", "disabled");
        sessionStorage.setItem("Demande", 'delete');
        $("#btnMAJDemande").show();
    }
    if (action === "consult") {
        $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-list"></i>');
        $('#labelTitre').text("Détail d'une demande");
        $('#designation').prop("disabled", "disabled");
        $('#codeTypeDemande').prop("disabled", true);
        $('#checkboxActif').prop("disabled", "disabled");
        $("#btnMAJDemande").hide();
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
//            ,
//            {
//                title: "Créé par",
//                data: 'userCreation',
//                render: function (data) {
//                    if (data === undefined || data === null || data === 'null')
//                        return '';
//                    else
//                        return "<span title='" + data + "'>" + data + "</span>";
//                }
//            }, {
//                data: 'dateCreation',
//                title: 'Date de création',
//                render: function (data) {
//                    if (data === null || data === undefined)
//                        return '';
//                    else
//                        return formatCalendarWithTime(data, 'dd/mm/yyyy');
//                }
//            }
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
    $('#labelTitre').text("Ajout d'une demande");
    $('#modal_ajout_Demande_title h2').val("Ajout d'une demande");
    $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-plus"></i>');
    $('#modalAdd').modal('show');
    $('#code').val('');
    $('#designation').val('');
    $('#codeTypeDemande').val('');
 $('#code').prop("disabled", false);
    $('#designation').prop("disabled", false);
    $('#checkboxActif').prop("disabled", false);
    $('#codeTypeDemande').prop("disabled", false);

    $('#designation').val('');
    $("#btnMAJDemande").show();
    sessionStorage.setItem("Demande", 'ajout');
}
function submitMAJDemande() {
    if (sessionStorage.getItem("Demande") === 'delete') {
        deleteDemande($('#code').val());
    } else {
        if (($('#code').val() === '')) {
            $('#code').addClass('css-error');
            $('#code').attr('style', 'background-color: #fff0f0;border-color: #A90329;');
        } else {
            $('#code').removeClass('css-error');
            $('#code').attr('style', '');
        }
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
            showNotification('Succès', 'Ajout effectuée', 'success', 3000);
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
        url: `${url_base}/parametragedemandes?user=` + window.localStorage.getItem('username'),
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

            showNotification('Succès', 'Modification effectuée', 'success', 3000);
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
            showNotification('succès', "Annulation effectuée", 'success', 5000);
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



