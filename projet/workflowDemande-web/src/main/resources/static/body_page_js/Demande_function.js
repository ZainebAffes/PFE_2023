function drawBtnLesDemandes() {
    DessinerButton('30', '#listetid_Demandes');
    ActionBoutton();

}

function drawBtn() {
    // Récupérer la div pour les boutons par ID
    const btnContainer = document.getElementById("btnContainer");
    // Envoyer une requête AJAX à l'API pour récupérer les données de la base de données
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:9011/workflowDemande-core/api/parametragedemandes");
    xhr.setRequestHeader('Content-Type', 'application/json'); // Ajouter une en-tête pour spécifier le format de la réponse
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Convertir la réponse JSON en objet JavaScript
            const data = JSON.parse(xhr.responseText);
            // Parcourir les données et créer des boutons pour la div
            for (let i = 0; i < data.length; i++) {
                const btn = document.createElement("button");
                btn.innerHTML = `<div class="menuS"> <i class="fa ${data[i].logo} icon"></i>` + ` <p class="designation" id=${data[i].code}>${data[i].designation}</p></div>`;
                btn.value = data[i].code;
                btn.setAttribute("id", "btnContainer");
                btn.setAttribute("class", "btnSousMenu");
                btn.setAttribute("id", "btnContainer");
                btnContainer.appendChild(btn);
            }
            AfficheModalListDemandes();
        }
    };
    xhr.send();
}
function  AfficheModalListDemandes() {
  $(".btnSousMenu").on('click', function() {
        var value = ($(this).val());
        ouvrirOnglet("Nouvelle Demande", "NouvelleDemande", false, 'fils', '?op=' + value, 'NouvelleDemande');
    });


}




function ActionBoutton() {
    $('#btn_Ajouter').unbind('click');
    $('#btn_Ajouter').bind('click', function (e) {
        AfficheModalAddLesDemandes();
        $("#typeMode").val("add");
    });

    $('#btn_Consulter').unbind('click');
    $('#btn_Consulter').bind('click', function (e) {
        var rowDde = $('#tableDemandes').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir une demande ", 'error', 3000);
        else {
            var numeroDemande = $('.selectionnee').find('td').eq(0).text();
            majDemandes(numeroDemande, "consult");
        }
    });

    $('#btn_Annuler').unbind('click');
    $('#btn_Annuler').bind('click', function (e) {
        var rowDde = $('#tableDemandes').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir une demande ", 'error', 3000);
        else
        {
            var numeroDemande = $('.selectionnee').find('td').eq(0).text();
            majDemandes(numeroDemande, "delete");
        }
    });

    $('#btn_Imprimer').unbind('click');
    $('#btn_Imprimer').bind('click', function (e) {
        $('#search').val("");
        var type = "PDF";
        var url = `${url_base}/demandes/print?user=` + window.localStorage.getItem('username') + `&type=` + type;
        impressionListe(url);
    });

    $("#btn_Exporter").unbind("click");
    $("#btn_Exporter").bind("click", function (e) {
        const headers = [
            'Numéro demande',
            'Désignation du demande',
            '	Employé',
            'Type de demande',
            'Date du demande'
        ];
        console.log(window);
        const workbook = new window.ExcelJS.Workbook();

        const downloadAsExcel = () => {
            workbook.xlsx.writeBuffer().then((data) => {
                const blob = new Blob([data], {
                    type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
                });
                saveAs(blob, `demandes.xlsx`);

            });
        };
        var url = url_base + '/typedemandes/filter';
        if ($('#search').val() !== "undefined") {
            url = url + '?designation=' + $('#search').val();
        }
        fetch(url, {
            method: "GET"
        }).then(async(response) => {
            const json = await response.json();

            console.log(json);
            const title = 'demandes';

            const worksheet = workbook.addWorksheet(
                    `demandes`
                    );

            worksheet.addRow(headers);
            json.forEach((demandes) => {
                const newRow = worksheet.addRow([]);
                newRow.getCell(1).value = demandes.numeroDemande;
                newRow.getCell(2).value = demandes.designation;
                newRow.getCell(3).value = demandes.nomEmploye;
                newRow.getCell(4).value = demandes.typeDemande;
                newRow.getCell(5).value = demandes.dateCreation;
            });
            downloadAsExcel();

            worksheet.destroy();

        });

    });
}
function validation(list) {
    $.ajax({
        url: url_base + '/demandes/validation?user=' + window.localStorage.getItem('username') + '&validation=' + list,
        type: 'PUT',
        contentType: "application/json; charset=utf-8",
        async: false,
        success: function (data)
        {
            showNotification('Succès', "Validation effectuée avec succés", 'success', 5000);
            DrawTableLesDemandes();
        }
    });
}
function refuser(list) {
    $.ajax({
        url: url_base + '/demandes/refuser?user=' + window.localStorage.getItem('username') + '&validation=' + list,
        type: 'PUT',
        contentType: "application/json; charset=utf-8",
        async: false,
        success: function (data)
        {
            showNotification('Succès',"La refu a été effectuée avec succès", 'success', 5000);
            DrawTableLesDemandes();
        }
    });
}

function majDemande(numeroDemande, action) {
    var Demande = findDemandeById(numeroDemande);
    $('#modalAdd').modal('show');
    $('#numeroDemande').val(numeroDemande);
    $('#designation').val(Demande.designation);
    $('#dateCreation').val(Demande.dateCreation);
    $('#numeroDemande').prop("disabled", "disabled");

    if (action === "update") {
        $('#designation').prop("disabled", false);
        $('#dateCreation').prop("disabled", false);
        $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-edit"></i>');
        $('#labelTitre').text("Modification d'une demande");
        sessionStorage.setItem("Demande", 'modif');
        $("#btnMAJDemandes").show();
    }
    if (action === "delete") {
        $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-trash"></i>');
        $('#labelTitre').text("Suppression d'une demande");
        $('#designation').prop("disabled", "disabled");
        $('#dateCreation').prop("disabled", true);
        $('#checkboxActif').prop("disabled", "disabled");
        sessionStorage.setItem("Demande", 'delete');
        $("#btnMAJDemandes").show();
    }
    if (action === "consult") {
        $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-list"></i>');
        $('#labelTitre').text("Détail d'une demande");
        $('#designation').prop("disabled", "disabled");
        $('#dateCreation').prop("disabled", true);
        $("#btnMAJDemandes").hide();
    }

}

function DrawTableLesDemandes() {
    window.parent.$.loader.open();
    setTimeout(function () {
        DrawLesDemandes("tableDemandes", '_grid_Demandes');
        window.parent.$.loader.close();
    }, 100);
}
function  DrawLesDemandes(idTable, idContainer) {
    showLoadingNotification();
    var List = [];
     let id;
    let url = window.location.search;
    if (url !== '') {
        var e = [];
        e = url.split('?');
        var t = [];
        t = e[1].split('&');
        id = t[0].split('=')[1];
    }
    List = findDemande(id);
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
                title: "Numéro demande",
                data: 'code',
                render: function (data, type, row, meta) {
                    if (data !== null)
                        return data;
                    else
                        "";
                }
            },
            {
                title: "Désignation du demande",
                data: 'desParametrageDemande',
                render: function (data) {
                    if (data === undefined)
                        return '';
                    else
                        return "<span title='" + data + "'>" + data + "</span>";
                }
            }, {
                title: "Employé",
                data: 'nomEmploye',
                render: function (data) {
                    if (data === undefined)
                        return '';
                    else
                        return "<span title='" + data + "'>" + data + "</span>";
                }
            },
            {
                title: "Type de demande",
                data: 'typeDemande',
                render: function (data) {
                    if (data === undefined)
                        return '';
                    else
                        return "<span title='" + data + "'>" + data + "</span>";
                }
            },

            {
                data: 'dateCreation',
                title: 'Date du demande ',
                render: function (data) {
                    if (data === null || data === undefined)
                        return '';
                    else
                        return formatCalendarWithTime(data, 'dd/mm/yyyy');
                }
            },
            {
                title: "Etat",
                data: 'logoEtat',
                render: function (data) {
                    if (data === undefined)
                        return '';
                    else
                        return "<i class='" + data + "'></i>";
                }
            },
            {
                title: "Etat",
                data: 'idEtat',
                render: function (data) {
                    if (data === undefined)
                        return '';
                    else
                        return "<i class='" + data + "'>" + data + "</i>";
                }
            },
            {
                data: 'idEtat',
                title: 'valider',
                sortable: false,
                render: function (data, type, row, meta) {
                    var check = data === "0 " ? "unchecked" : "checked";
                    return '<form><label style="display: flex;justify-content: center;align-items: center; "><input name="renouv" type="checkbox" class="editor-active checkBoxClass checkbox" ' + check + '><span></span></label></form>';
                }
            }
        ],
        "aoColumnDefs": [{
                'bSortable': false,
                'aTargets': colDef
            }],
        "order": [[0, "asc"]]
    });
    $('#tableDemandes  tbody').delegate('tr', 'click', function (e) {
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
    $('#tableDemandes > tbody').on('dblclick', function (e) {
        var rowDde = $('#tableDemandes').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', 'veuillez Sélectionner une Demande', 'error', 2000);
        else {
            var numeroDemande = $('.selectionnee').find('td').eq(0).text();
            majDemandes(numeroDemande, 'consult');
        }
    });
    $('#tableDemandes_info').css("padding", '0');
    $('#tableDemandes_filter').hide();
    hideLoadingNotification();
}
function  AfficheModalAddLesDemandes() {
    $('#labelTitre').text("Ajout d'une demande");
    $('#modal_ajout_Demandes_title h2').val("Ajout d'une demande");
    $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-plus"></i>');
    $('#modalAdd').modal('show');
    $('#numeroDemande').val('');
    $('#designation').val('');
    $('#dateCreation').val('');

    $('#designation').prop("disabled", false);
    $('#checkboxActif').prop("disabled", false);
    $('#dateCreation').prop("disabled", false);

    $('#designation').val('');
    $("#btnMAJDemandes").show();
    sessionStorage.setItem("Demande", 'ajout');
}

function addLesDemandes(list) {
    $.ajax({
        url: `${url_base}/demandes`,
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
                DrawTableLesDemandes();
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
        url: `${url_base}/demandes/${id}`,
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
function updateDemandes(object) {
    $.ajax({
        url: `${url_base}/demandes?user=` + window.localStorage.getItem('username'),
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

            showNotification('Modification effectuée avec succès', 'success', 3000);
            $('#addConfirm').modal('hide');
            $('#modalAdd').modal('hide');
            showLoadingNotification();
            setTimeout(function () {
                DrawTableLesDemandes();
                hideLoadingNotification();
            }, 50);
        }
        ,
        complete: function (jqXHR, textStatus) {
        }
    });
}
function deleteDemandes(numeroDemande) {
    var response = "";
    $.ajax({
        url: `${url_base}/demandes/${numeroDemande}`,
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
                DrawTableLesDemandes();
                hideLoadingNotification();
            }, 50);

        }
    });
    return response;
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
                title: "Numéro demande",
                data: 'numeroDemande',
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
                title: "Date création",
                data: 'dateCreation',
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

    var url = url_base + '/demandes/filter';
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
function findDemandePDF(designation) {

    var url = url_base + '/pdf/demandes';
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
function getDemande(designation) {

    var url = url_base + '/export/demandes';
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
