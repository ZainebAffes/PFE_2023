function drawBtnWorkflow() {
    DessinerButton('40', '#listetid_Workflow');
    ActionBoutton();
}
function ActionBoutton() {
    $('#btn_Ajouter').unbind('click');
    $('#btn_Ajouter').bind('click', function (e) {
        AfficheModalAddWorkflow();
    });

    $('#btn_Modifier').unbind('click');
    $('#btn_Modifier').bind('click', function (e) {
        var rowDde = $('#tableListWorkflow').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir une état ", 'error', 3000);
        else {
            var codWorkflow = $('.selectionnee').find('td').eq(0).text();
            majWorkflow(codWorkflow, "update");
        }
    });
     
    $('#btn_Annuler').unbind('click');
    $('#btn_Annuler').bind('click', function (e) {
        var rowDde = $('#tableListWorkflow').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir une état ", 'error', 3000);
        else
        {
            var codWorkflow = $('.selectionnee').find('td').eq(0).text();
            majWorkflow(codWorkflow, "delete");
        }
    });

}
function majWorkflow(codWorkflow, action) {
    var Workflow = findWorkflowById(codWorkflow);
    $('#modalAdd').modal('show');
    $('#codWorkflow').val(codWorkflow);
    $('#desWorkflow').val(Workflow.designation);
    $('#logoWorkflow').val(Workflow.logo);
    $('#codWorkflow').prop("disabled", "disabled");
   
    if (action === "update") {
        $('#desWorkflow').prop("disabled", false);
        $('#codWorkflowTypeWorkflow').prop("disabled", false);
         $('#logoWorkflow').prop("disabled", false);       
        $('#modalIconWorkflow').replaceWith('<i id="modalIconWorkflow" class="glyphicon glyphicon-edit"></i>');
        $('#labelTitre').text("Modification d'un Workflow");
        sessionStorage.setItem("Workflow", 'modif');
        $("#btnMAJWorkflow").show();
    }
    if (action === "delete") {
        $('#modalIconWorkflow').replaceWith('<i id="modalIconWorkflow" class="glyphicon glyphicon-trash"></i>');
        $('#labelTitre').text("Suppression d'un Workflow");
        $('#desWorkflow').prop("disabled", "disabled");
         $('#logoWorkflow').prop("disabled", "disabled");
        $('#codWorkflow').prop("disabled", true);
       
        sessionStorage.setItem("Etat", 'delete');
        $("#btnMAJWorkflow").show();
    }   
}

function DrawTableWorkflow() {
    window.parent.$.loader.open();
    setTimeout(function () {
        DrawListWorkflow("tableListWorkflow", '_grid_ListWorkflow');
        window.parent.$.loader.close();
    }, 100);
}
function DrawListWorkflow(idTable, idContainer) {
    showLoadingNotification();
    var List = [];

    List = findWorkflow(undefined);
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
                data: 'code',
                render: function (data, type, row, meta) {
                    if (data !== null)
                        return data;
                    else
                        "";
                }
            },
            {
                title: "Responsable de validation",
                data: 'designation',
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
    $('#tableListWorkflow  tbody').delegate('tr', 'click', function (e) {
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
    $('#tableListWorkflow > tbody').on('dblclick', function (e) {
        var rowDde = $('#tableListWorkflow').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', 'veuillez Sélectionner un Workflow', 'error', 2000);
        else {
            var codWorkflow = $('.selectionnee').find('td').eq(0).text();
            majWorkflow(codWorkflow, 'consult');
        }
    });
    $('#tableListWorkflow_info').css("padding", '0');
    $('#tableListWorkflow_filter').hide();
    hideLoadingNotification();
}
function AfficheModalAddWorkflow() {
    $('#labelTitre').text("Ajout d'un Workflow");
    $('#modal_ajout_Workflow_title h2').val("Ajout d'un Workflow");
    $('#modalIconWorkflow').replaceWith('<i id="modalIconWorkflow" class="glyphicon glyphicon-plus"></i>');
    $('#modalAdd').modal('show');
    $('#codWorkflow').val('');
    $('#desWorkflow').val('');
   $('#logoWorkflow').val('');
    $('#desWorkflow').prop("disabled", false);
    $('#codWorkflow').prop("disabled", false);   
    $('#logoWorkflow').prop("disabled", false);
    $("#btnMAJWorkflow").show();
    sessionStorage.setItem("Etat", 'ajout');
}
function submitMAJWorkflow() {
    if (sessionStorage.getItem("Etat") === 'delete') {
        deleteWorkflow($('#codWorkflow').val());
    } else {
        if (($('#codWorkflow').val() === '')) {
            $('#codWorkflow').addClass('css-error');
            $('#codWorkflow').attr('style', 'background-color: #fff0f0;border-color: #A90329;');
        } else {
            $('#codWorkflow').removeClass('css-error');
            $('#codWorkflow').attr('style', '');
        }
        if (($('#desWorkflow').val() === '')) {
            $('#desWorkflow').addClass('css-error');
            $('#desWorkflow').attr('style', 'border-width: 1px;background-color: #fff0f0;border-color: #A90329;');
        } else {
            $('#desWorkflow').removeClass('css-error');
            $('#desWorkflow').attr('style', '');
        }
        if (($('#logoWorkflow').val() === '')) {
            $('#logoWorkflow').addClass('css-error');
            $('#logoWorkflow').attr('style', 'border-width: 1px;background-color: #fff0f0;border-color: #A90329;');
        } else {
            $('#logoWorkflow').removeClass('css-error');
            $('#logoWorkflow').attr('style', '');
        }
        if ($('.css-error').length > 0) {
            showNotification('Avertissement', "Veuillez vérifier le(s) champ(s) saisi(s) ! ", 'error', 3000);
        } else {
            var payload = payloadWorkflow();
            if (sessionStorage.getItem("Workflow") === 'ajout') {
                var Type = findWorkflow($('#desWorkflow').val());
                if (Type.length > 0) {
                    DrawListRassemblant('tableListRassemblant', '_grid_ListRassemblant', Type);
                    $('#add_msg').html("<h6>des Types de demande(s) ressemblante(s): </h6>");
                    $('#add_msg_confirm').html("<span>Voulez vous confirmer l'ajout ? </span>");
                    $('#addConfirm').modal('show');
                    $('#modalAdd').modal('hide');
                    $("#submitAdd").unbind('click');
                    $("#submitAdd").click(function () {
                        $('#addConfirm').modal('hide');
                        addWorkflow(payload);
                    });
                } else {
                    addWorkflow(payload);
                }
            } else {
                if (sessionStorage.getItem("Workflow") === 'modif') {
                    updateWorkflow(payload);
                }
            }
        }
    }
}
function addWorkflow(list) {
    $.ajax({
        url: `${url_base}/etats`,
      //    url: `${url_base}//etats?user=` + window.localStorage.getItem('username'),
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
            showNotification('succès','Ajout effectuée avec succès', 'success', 3000);
            $('#modalAdd').modal('hide');
            showLoadingNotification();
            setTimeout(function () {
                DrawTableWorkflow();
                hideLoadingNotification();
            }, 50);
        }
        ,
        complete: function (jqXHR, textStatus) {

        }
    });

}

function findWorkflowById(id) {

    var response = "";
    $.ajax({
        url: `${url_base}/etats/${id}`,
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
function updateWorkflow(object) {
    $.ajax({
        url: `${url_base}/etats`,
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

            showNotification('succès','Modification effectuée avec succès', 'success', 3000);
            $('#addConfirm').modal('hide');
            $('#modalAdd').modal('hide');
            showLoadingNotification();
            setTimeout(function () {
                DrawTableWorkflow();
                hideLoadingNotification();
            }, 50);
        }
        ,
        complete: function (jqXHR, textStatus) {
        }
    });
}
function deleteWorkflow(code) {
    var response = "";
    $.ajax({
        url: `${url_base}/etats/${code}`,
        contentType: "text/html; charset=utf-8",
        type: 'DELETE',
        async: false,
        success: function (data)
        {
            response = data;
            showNotification('succès',"Suppression effectuée avec succès", 'success', 5000);
            $('#addConfirm').modal('hide');
            $('#modalAdd').modal('hide');
            showLoadingNotification();
            setTimeout(function () {
                DrawTableWorkflow();
                hideLoadingNotification();
            }, 50);

        }
    });
    return response;
}
function payloadWorkflow() {
    var payload = {
        "code": $('#codWorkflow').val(),
        "designation": $('#desWorkflow').val(),
        "logo": $('#logoWorkflow').val()
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
                data: 'code',
                render: function (data, type, row, meta) {
                    if (data !== null)
                        return data;
                    else
                        "";
                }
            },
            {
                title: "Responsable de validation",
                data: 'designation',
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

function findWorkflow(desWorkflow) {

    var url = url_base + '/etats';
    if (desWorkflow !== undefined) {
        url = url + '?designation=' + desWorkflow;
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