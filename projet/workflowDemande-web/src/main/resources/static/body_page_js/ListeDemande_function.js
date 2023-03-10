function drawBtnDemande() {
    DessinerButton('10', '#listetid_Demande');
    ActionBoutton();
}
function ActionBoutton() {
    $("#jours").on('keyup', function (event)
    {
        verifInteger(this);
    });
    $('#btn_Ajouter').unbind('click');
    $('#btn_Ajouter').bind('click', function (e) {
        AfficheModalAddFam();
        $("#typeMode").val("add");
    });

    $('#btn_Modifier').unbind('click');
    $('#btn_Modifier').bind('click', function (e) {
        var rowDde = $('#tableListDemande').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir un demande ", 'error', 3000);
        else {
            var code = $('.selectionnee').find('td').eq(0).text();
            majDemande(code, "update");

            parametrageChampHarmoniser("1008");
        }
    });

    $('#btn_Consulter').unbind('click');
    $('#btn_Consulter').bind('click', function (e) {
        var rowDde = $('#tableListDemande').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir un demande ", 'error', 3000);
        else {
            var code = $('.selectionnee').find('td').eq(0).text();
            majDemande(code, "consult");
        }
    });

    $('#btn_Annuler').unbind('click');
    $('#btn_Annuler').bind('click', function (e) {
        var rowDde = $('#tableListDemande').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir un demande ", 'error', 3000);
        else
        {
            var codDemande = $('.selectionnee').find('td').eq(0).text();
            majDemande(codDemande, "delete");
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
        var url = `${url_base}/delairegfrs/print?actifs=${varActif}&user=` + window.localStorage.getItem('username') + `&type=` + type;
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
        var url = `${url_base}/delairegfrs/print?actifs=${varActif}&user=` + window.localStorage.getItem('username') + `&type=` + type;
        exporterList(url, "demande");
    });

}
function majDemande(code, action) {
    var Demande = findDemandeByCodFam(code);
    $('#modalAdd').modal('show');
    $('#codDemande').val(code);
    $('#desDemande').val(Demande.desDemande);
    $('#jours').val(Demande.jours);
    $('#checkboxActif').prop("checked", Demande.actif);
    $('#codDemande').prop("disabled", "disabled");
    if (action === "update") {
        $('#desDemande').prop("disabled", false);
        $('#jours').prop("disabled", false);
        $('#checkboxActif').prop("disabled", false);
        $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-edit"></i>');
        $('#labelTitre').text("Modification d'un demande");
        sessionStorage.setItem("Demande", 'modif');
        $("#btnMAJFam").show();
    }
    if (action === "delete") {
        $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-trash"></i>');
        $('#labelTitre').text("Annulation d'un demande");
        $('#desDemande').prop("disabled", "disabled");
        $('#jours').prop("disabled", true);
        $('#checkboxActif').prop("disabled", "disabled");
        sessionStorage.setItem("Demande", 'delete');
        $("#btnMAJFam").show();
    }
    if (action === "consult") {
        $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-list"></i>');
        $('#labelTitre').text("Détail d'un demande");
        $('#desDemande').prop("disabled", "disabled");
        $('#jours').prop("disabled", true);
        $('#checkboxActif').prop("disabled", "disabled");
        $("#btnMAJFam").hide();
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
    var varActif;
    var etatActif = $('.filtreActif').find('.fa-check-circle').parent().find('span').eq(0).text();
    if (etatActif === "Actif") {
        varActif = "true";
    } else if (etatActif === "Non actif") {
        varActif = "false";
    } else if (etatActif === "Tous") {
        varActif = "true,false";
    }
    List = findDemandeByActif(varActif, undefined);
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
                data: 'codDemande',
                render: function (data, type, row, meta) {
                    if (data !== null)
                        return data;
                    else
                        "";
                }
            },
            {
                title: "Délai",
                data: 'desDemande',
                render: function (data) {
                    if (data === undefined)
                        return '';
                    else
                        return "<span title='" + data + "'>" + data + "</span>";
                }
            },
            {
                title: "Nombre de jours",
                data: 'jours',
                render: function (data) {
                    if (data === undefined)
                        return '';
                    else
                        return "<span title='" + data + "'>" + data + "</span>";
                }
            },
            {
                title: "Créé par",
                data: 'userCreation',
                render: function (data) {
                    if (data === undefined || data === null || data === 'null')
                        return '';
                    else
                        return "<span title='" + data + "'>" + data + "</span>";
                }
            }, {
                data: 'dateCreation',
                title: 'Date de création',
                render: function (data) {
                    if (data === null || data === undefined)
                        return '';
                    else
                        return formatCalendarWithTime(data, 'dd/mm/yyyy');
                }
            },
            {
                data: 'actif',
                title: "Actif",
                sortable: false,
                render: function (data, type, row, meta) {
                    var check = data === true ? "checked" : "unchecked";
                    return '<form><label style="display: flex;justify-content: center;align-items: center; "><input type="checkbox" class="checkbox" disabled="" ' + check + '><span></span></label></form>';
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
            showNotification('Attention', dictionnaire_local['parametrage.Demande.veuillezSelectionnerUnDemande'], 'error', 2000);
        else {
            var code = $('.selectionnee').find('td').eq(0).text();
            majDemande(code, 'consult');
        }
    });
    $('#tableListDemande_info').css("padding", '0');
    $('#tableListDemande_filter').hide();
    hideLoadingNotification();
}
function AfficheModalAddFam() {
    $('#labelTitre').text("Ajout d'un demande");
    $('#modal_ajout_Demande_title h2').val("Ajout d'un demande");
    $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-plus"></i>');
    $('#modalAdd').modal('show');
    $('#codDemande').val('');
    $('#desDemande').val('');
    $('#jours').val('');
    $('#actif').prop("checked", true);
    $('#desDemande').prop("disabled", false);
    $('#checkboxActif').prop("disabled", false);
    $('#jours').prop("disabled", false);
    var genCodeAuto = generateCodeAutoByparam("DelaiRegFrs", '');
    var codeAuto = genCodeAuto !== null ? genCodeAuto.code : '';
    var auto = genCodeAuto !== null ? genCodeAuto.auto : 'M';
    var maxLength = genCodeAuto.maxLength !== null && genCodeAuto.maxLength !== 0 ? genCodeAuto.maxLength : 10;
    var disabled = auto === 'A' ? "disabled='disabled'" : "";
    if (auto === 'A' || auto === 'F') {
        $('#codDemande').prop('disabled', disabled);
    } else {
        $('#codDemande').removeAttr('disabled');
    }
    $('#codDemande').prop('maxLength', maxLength);

    if (auto === 'A') {
        $('#codDemande').val(codeAuto);
    }
    $('#desDemande').val('');
    $("#btnMAJFam").show();
    sessionStorage.setItem("Demande", 'ajout');
}
function submitMAJDemande() {
    if (sessionStorage.getItem("Demande") === 'delete') {
        deleteDemande($('#codDemande').val());
    } else {
        if (($('#codDemande').val() === '')) {
            $('#codDemande').addClass('css-error');
            $('#codDemande').attr('style', 'background-color: #fff0f0;border-color: #A90329;');
        } else {
            $('#codDemande').removeClass('css-error');
            $('#codDemande').attr('style', '');
        }
        if (($('#desDemande').val() === '')) {
            $('#desDemande').addClass('css-error');
            $('#desDemande').attr('style', 'border-width: 1px;background-color: #fff0f0;border-color: #A90329;');
        } else {
            $('#desDemande').removeClass('css-error');
            $('#desDemande').attr('style', '');
        }
        if (($('#jours').val() === '')) {
            $('#jours').addClass('css-error');
            $('#jours').attr('style', 'border-width: 1px;background-color: #fff0f0;border-color: #A90329;');
        } else {
            $('#jours').removeClass('css-error');
            $('#jours').attr('style', '');
        }
        if ($('.css-error').length > 0) {
            showNotification('Avertissement', "Veuillez vérifier le(s) champ(s) saisi(s) ! ", 'error', 3000);
        } else {
            var ifActif = $('#checkboxActif').is(':checked');
            var actif;
            if (ifActif === true) {
                actif = "true";
            } else if (ifActif === false) {
                actif = "false";
            }
            var payload = payloadDemande();
            if (sessionStorage.getItem("Demande") === 'ajout') {
                var spec = findDemandeByActif(undefined, $('#jours').val());
                if (spec.length > 0) {
                    DrawListRassemblant('tableListRassemblant', '_grid_ListRassemblant', spec);
                    $('#add_msg').html("<h6>Délai(s)de règlement(s) ressemblante(s): </h6>");
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
        url: `${url_base}/delairegfrs?user=` + window.localStorage.getItem('username'),
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

function findDelaiRegFrsByActif(actif, designation) {
    var url = url_base + '/delairegfrs/filter';
    if (actif !== undefined && designation !== undefined) {
        url = url + '?actifs=' + actif + '&designation=' + designation;
    } else if (actif !== undefined) {
        url = url + '?actifs=' + actif;
    } else if (designation !== undefined) {
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
function findDemandeByCodFam(codDemande) {

    var response = "";
    $.ajax({
        url: `${url_base}/delairegfrs?codDemande=${codDemande}`,
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
        url: `${url_base}/delairegfrs?user=` + window.localStorage.getItem('username'),
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
function deleteDemande(codDemande) {
    var response = "";
    $.ajax({
        url: `${url_base}/delairegfrs?delairegfrs=${codDemande}&user=` + window.localStorage.getItem('username'),
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
    var actif = $('#checkboxActif').is(':checked') === true ? "true" : "false";
    var payload = {
        "codDemande": $('#codDemande').val(),
        "desDemande": $('#desDemande').val(),
        "jours": $('#jours').val(),
        "actif": actif
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
                data: 'codDemande',
                render: function (data, type, row, meta) {
                    if (data !== null)
                        return data;
                    else
                        "";
                }
            },
            {
                title: "Désignation",
                data: 'desDemande',
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

function findDemandeByActif(actif, designation) {
    var url = url_base + '/delairegfrs/filter';
    if (designation !== undefined && (actif === undefined)) {
        url = url + '?designation=' + designation;
    }
    if (actif !== undefined && designation === undefined) {
        url = url + '?actifs=' + actif;
    }
    if (actif !== undefined && designation !== undefined) {
        url = url + '?actifs=' + actif + '&designation=' + designation;
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
function allowDrop(ev) {
  ev.preventDefault();
}

function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
  ev.preventDefault();
  var data = ev.dataTransfer.getData("text");
  ev.target.appendChild(document.getElementById(data));
}