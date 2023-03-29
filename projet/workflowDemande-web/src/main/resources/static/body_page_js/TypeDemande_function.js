function drawBtnTypeDemande() {
    DessinerButton('20', '#listetid_TypeDemande');
    ActionBoutton();
}
function ActionBoutton() {
    $('#btn_Ajouter').unbind('click');
    $('#btn_Ajouter').bind('click', function (e) {
        AfficheModalAddTypeDemande();
    });

    $('#btn_Modifier').unbind('click');
    $('#btn_Modifier').bind('click', function (e) {
        var rowDde = $('#tableListTypeDemande').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir un typeDemande ", 'error', 3000);
        else {
            var codTypeDemande = $('.selectionnee').find('td').eq(0).text();
            majTypeDemande(codTypeDemande, "update");

          
        }
    });

    $('#btn_Consulter').unbind('click');
    $('#btn_Consulter').bind('click', function (e) {
        var rowDde = $('#tableListTypeDemande').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir un typeDemande ", 'error', 3000);
        else {
            var codTypeDemande = $('.selectionnee').find('td').eq(0).text();
            majTypeDemande(codTypeDemande, "consult");
        }
    });

    $('#btn_Annuler').unbind('click');
    $('#btn_Annuler').bind('click', function (e) {
        var rowDde = $('#tableListTypeDemande').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir un typeDemande ", 'error', 3000);
        else
        {
            var codTypeDemande = $('.selectionnee').find('td').eq(0).text();
            majTypeDemande(codTypeDemande, "delete");
        }
    });

    $('#btn_Imprimer').unbind('click');
    $('#btn_Imprimer').bind('click', function (e) {
        $('#search').val("");
        var varActif;
        var etatActif = $('.filtreActif').find('.fa-check-circle').parent().find('span').eq(0).text();
        if (etatActif === "Actif") {
            varActif = "true";
        } else if (etatActif === "Non actif") {
            varActif = "false";
        } else if (etatActif === "Tous") {
            varActif = "true,false";
        }
        var type = "PDF";
        var url = `${url_base}/parametrage_typeDemande/print?actifs=${varActif}&user=` + window.localStorage.getItem('username') + `&type=` + type;
        impressionListe(url);
    });

    $("#btn_Exporter").unbind("click");
    $("#btn_Exporter").bind("click", function (e) {

        var varActif;
        var etatActif = $('.filtreActif').find('.fa-check-circle').parent().find('span').eq(0).text();
        if (etatActif === "Actif") {
            varActif = "true";
        } else if (etatActif === "Non actif") {
            varActif = "false";
        } else if (etatActif === "Tous") {
            varActif = "true,false";
        }
        var type = "Excel";
        var url = `${url_base}/parametrage_typeDemande/print?actifs=${varActif}&user=` + window.localStorage.getItem('username') + `&type=` + type;
        exporterList(url, "typeDemande");
    });

}
function majTypeDemande(codTypeDemande, action) {
    var TypeDemande = findTypeDemandeById(codTypeDemande);
    $('#modalAdd').modal('show');
    $('#codTypeDemande').val(codTypeDemande);
    $('#desTypeDemande').val(TypeDemande.description);
    $('#codTypeDemande').prop("disabled", "disabled");
    if (action === "update") {
        $('#desTypeDemande').prop("disabled", false);
        $('#codTypeDemandeTypeTypeDemande').prop("disabled", false);
        $('#checkboxActif').prop("disabled", false);
        $('#modalIconTypeDemande').replaceWith('<i id="modalIconTypeDemande" class="glyphicon glyphicon-edit"></i>');
        $('#labelTitre').text("Modification d'un typeDemande");
        sessionStorage.setItem("TypeDemande", 'modif');
        $("#btnMAJTypeDemande").show();
    }
    if (action === "delete") {
        $('#modalIconTypeDemande').replaceWith('<i id="modalIconTypeDemande" class="glyphicon glyphicon-trash"></i>');
        $('#labelTitre').text("Annulation d'un typeDemande");
        $('#desTypeDemande').prop("disabled", "disabled");
        $('#codTypeDemandeTypeTypeDemande').prop("disabled", true);
        $('#checkboxActif').prop("disabled", "disabled");
        sessionStorage.setItem("TypeDemande", 'delete');
        $("#btnMAJTypeDemande").show();
    }
    if (action === "consult") {
        $('#modalIconTypeDemande').replaceWith('<i id="modalIconTypeDemande" class="glyphicon glyphicon-list"></i>');
        $('#labelTitre').text("Détail d'un typeDemande");
        $('#desTypeDemande').prop("disabled", "disabled");
        $('#codTypeDemandeTypeTypeDemande').prop("disabled", true);
        $('#checkboxActif').prop("disabled", "disabled");
        $("#btnMAJTypeDemande").hide();
    }

}

function DrawTableTypeDemande() {
    window.parent.$.loader.open();
    setTimeout(function () {
        DrawListTypeDemande("tableListTypeDemande", '_grid_ListTypeDemande');
        window.parent.$.loader.close();
    }, 100);
}
function DrawListTypeDemande(idTable, idContainer) {
    showLoadingNotification();
    var List = [];

    List = findTypeDemande(undefined);
    document.getElementById(idContainer).innerHTML = '';
    var table_list = "<table id='" + idTable + "' class='display dataTable projects-table table table-striped table-bordered table-hover' cellspacing='0'  width='100%' align='center'>";
    table_list += "</table>";
    $("#" + idContainer).html(table_list);
    var colDef = [0];
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
                data: 'codeTypeDemande',
                render: function (data, type, row, meta) {
                    if (data !== null)
                        return data;
                    else
                        "";
                }
            },
            {
                title: "désignation",
                data: 'description',
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
    $('#tableListTypeDemande  tbody').delegate('tr', 'click', function (e) {
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
    $('#tableListTypeDemande > tbody').on('dblclick', function (e) {
        var rowDde = $('#tableListTypeDemande').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', 'veuillez Sélectionner un TypeDemande', 'error', 2000);
        else {
            var codTypeDemande = $('.selectionnee').find('td').eq(0).text();
            majTypeDemande(codTypeDemande, 'consult');
        }
    });
    $('#tableListTypeDemande_info').css("padding", '0');
    $('#tableListTypeDemande_filter').hide();
    hideLoadingNotification();
}
function AfficheModalAddTypeDemande() {
    $('#labelTitre').text("Ajout d'un type de demande");
    $('#modal_ajout_TypeDemande_title h2').val("Ajout d'un type de Demande");
    $('#modalIconTypeDemande').replaceWith('<i id="modalIconTypeDemande" class="glyphicon glyphicon-plus"></i>');
    $('#modalAdd').modal('show');
    $('#codTypeDemande').val('');
    $('#desTypeDemande').val('');
    $('#desTypeDemande').prop("disabled", false);
    $('#codTypeDemande').prop("disabled", false);
    $('#checkboxActif').prop("disabled", false);
    $("#btnMAJTypeDemande").show();
    sessionStorage.setItem("TypeDemande", 'ajout');
}
function submitMAJTypeDemande() {
    if (sessionStorage.getItem("TypeDemande") === 'delete') {
        deleteTypeDemande($('#codTypeDemande').val());
    } else {
        if (($('#codTypeDemande').val() === '')) {
            $('#codTypeDemande').addClass('css-error');
            $('#codTypeDemande').attr('style', 'background-color: #fff0f0;border-color: #A90329;');
        } else {
            $('#codTypeDemande').removeClass('css-error');
            $('#codTypeDemande').attr('style', '');
        }
        if (($('#desTypeDemande').val() === '')) {
            $('#desTypeDemande').addClass('css-error');
            $('#desTypeDemande').attr('style', 'border-width: 1px;background-color: #fff0f0;border-color: #A90329;');
        } else {
            $('#desTypeDemande').removeClass('css-error');
            $('#desTypeDemande').attr('style', '');
        }
        if ($('.css-error').length > 0) {
            showNotification('Avertissement', "Veuillez vérifier le(s) champ(s) saisi(s) ! ", 'error', 3000);
        } else {
            var payload = payloadTypeDemande();
            if (sessionStorage.getItem("TypeDemande") === 'ajout') {
                var Type = findTypeDemande(undefined, $('#desTypeDemande').val());
                if (Type.length > 0) {
                    DrawListRassemblant('tableListRassemblant', '_grid_ListRassemblant', Type);
                    $('#add_msg').html("<h6>des Types de demande(s) ressemblante(s): </h6>");
                    $('#add_msg_confirm').html("<span>Voulez vous confirmer l'ajout ? </span>");
                    $('#addConfirm').modal('show');
                    $('#modalAdd').modal('hide');
                    $("#submitAdd").unbind('click');
                    $("#submitAdd").click(function () {
                        $('#addConfirm').modal('hide');
                        addTypeDemande(payload);
                    });
                } else {
                    addTypeDemande(payload);
                }
            } else {
                if (sessionStorage.getItem("TypeDemande") === 'modif') {
                    updateTypeDemande(payload);
                }
            }
        }
    }
}
function addTypeDemande(list) {
    $.ajax({
        url: `${url_base}/typedemandes`,
//          url: `${url_base}//typedemandes?user=` + window.localStorage.getItem('username'),
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
                DrawTableTypeDemande();
                hideLoadingNotification();
            }, 50);
        }
        ,
        complete: function (jqXHR, textStatus) {

        }
    });

}

function findTypeDemandeById(id) {

    var response = "";
    $.ajax({
        url: `${url_base}/typedemandes/${id}`,
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
function updateTypeDemande(object) {
    $.ajax({
        url: `${url_base}/typedemandes?user=` + window.localStorage.getItem('username'),
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
                DrawTableTypeDemande();
                hideLoadingNotification();
            }, 50);
        }
        ,
        complete: function (jqXHR, textStatus) {
        }
    });
}
function deleteTypeDemande(codTypeDemande) {
    var response = "";
    $.ajax({
         url: `${url_base}/typedemandes/${codTypeDemande}`,
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
                DrawTableTypeDemande();
                hideLoadingNotification();
            }, 50);

        }
    });
    return response;
}
function payloadTypeDemande() {
    var payload = {
        "codeTypeDemande": $('#codTypeDemande').val(),
        "description": $('#desTypeDemande').val()
    };
    return payload;
}
function DrawListRassemblant(idTable, idContainer, list) {
    document.getElementById(idContainer).innerHTML = '';
    var table_list = "<table id='" + idTable + "' class='display dataTable projects-table table table-striped table-bordered table-hover' cellspacing='0'  width='100%' align='center'>";
    table_list += "</table>";
    $("#" + idContainer).html(table_list);
    var colDef = [1];
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
                data: 'codeTypeDemande',
                render: function (data, type, row, meta) {
                    if (data !== null)
                        return data;
                    else
                        "";
                }
            },
            {
                title: "Désignation",
                data: 'description',
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

function findTypeDemande(desTypeDemande) {

    var url = url_base + '/typedemandes/filter';
    if (desTypeDemande !== undefined) {
        url = url + '?designation=' + desTypeDemande;
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

