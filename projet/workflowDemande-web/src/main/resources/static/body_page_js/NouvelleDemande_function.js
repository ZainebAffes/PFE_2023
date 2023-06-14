function drawBtnLesDemandes() {
    DessinerButton('30', '#listetid_Demandes');
    ActionBoutton();
}
var Demande = [];
var Champs = [];
let demandeId;
let url = window.location.search;
if (url !== '') {
    var e = [];
    e = url.split('?');
    var t = [];
    t = e[1].split('&');
    demandeId = t[0].split('=')[1];
}


function ActionBoutton() {
    $('#btn_Ajouter').unbind('click');
    $('#btn_Ajouter').bind('click', function (e) {
        AfficheModalAddLesDemandes();
        sessionStorage.setItem("Demande", 'ajout');
        $("#typeMode").val("add");
    });


    $('#btn_Consulter').unbind('click');
    $('#btn_Consulter').bind('click', function (e) {
        sessionStorage.setItem("Demande", 'consult');
        var rowDde = $('#tableNouvelleDemandes').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir une demande ", 'error', 3000);
        else {
            var numeroDemande = $('.selectionnee').find('td').eq(0).text();
            findChamps(numeroDemande);
            majNVDemande(numeroDemande, "consult");
        }
    });


    $('#btn_Annuler').unbind('click');
    $('#btn_Annuler').bind('click', function (e) {
        var rowDde = $('#tableNouvelleDemandes').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir une demande ", 'error', 3000);
        else
        {
            var numeroDemande = $('.selectionnee').find('td').eq(0).text();
            findChamps(numeroDemande);
            majNVDemande(numeroDemande, "delete");
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
        var type = "Excel";
        var url = `${url_base}/demandes/print?actifs=${varActif}&user=` + window.localStorage.getItem('username') + `&type=` + type;
        exporterList(url, "demandes");
    });
    $('#btn_Valider').unbind('click');
    $('#btn_Valider').bind('click', function (e) {
        var liste = [];
        $("#tableNouvelleDemandes > tbody > tr").each(function () {
            if ($(this).find(".checkBoxClass").is(":checked")) {
                liste.push($(this).find('td').eq(0).text());
            }
        });
        if (liste.length === 0)
        {
            showNotification('Avertissement', "Veuillez choisir une demande", 'error', 3000);
        } else
        {
            validation(liste, false);
        }
    });
    $('#btn_Modifier').unbind('click');
    $('#btn_Modifier').bind('click', function (e) {
        sessionStorage.setItem("Demande", 'update');
        var rowDde = $('#tableNouvelleDemandes').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', "Veuillez choisir une demande ", 'error', 3000);
        else {
            var numeroDemande = $('.selectionnee').find('td').eq(0).text();
            findChamps(numeroDemande);
            majNVDemande(numeroDemande, "update");
        }
    });
    $('#btn_Refuser').unbind('click');
    $('#btn_Refuser').bind('click', function (e) {
        var liste = [];
        $("#tableNouvelleDemandes > tbody > tr").each(function () {
            if ($(this).find(".checkBoxClassRef").is(":checked")) {
                liste.push($(this).find('td').eq(0).text());
            }
        });
        if (liste.length === 0)
        {
            showNotification('Avertissement', "Veuillez choisir une demande", 'error', 3000);
        } else
        {
            validation(liste, true);
        }
    });
}

function majNVDemande(code, action) {
    $('#modalAdd').modal('show');
    if (action === "update") {
        $('#labelTitre').text("Modification d'une " + Demande.designation);
        sessionStorage.setItem("Demande", 'modif');
        $("#btnMAJDemandes").show();
    }
    if (action === "delete") {
        $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-trash"></i>');
        $('#labelTitre').text("Suppression d'une " + Demande.designation);
        $('#code').prop("disabled", true);
        sessionStorage.setItem("Demande", 'delete');
        $("#btnMAJDemandes").show();
    }
    if (action === "consult") {
        $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-list"></i>');
        $('#labelTitre').text("Détail d'une " + Demande.designation);
        sessionStorage.setItem("Demande", 'consult');
        $('#code').prop("disabled", true);
        $('#selectEtat').prop("disabled", "disabled");
        $("#btnMAJDemandes").hide();
    }
    DessinerListeDesChamps(code, action);
}


function DessinerListeDesChamps(code, action) {
    var html = "";
    for (let i = 0; i < Champs.length; i++) {
        var etiquette = Champs[i];
//        success: function (etiquette) {
        var inputType = "";
        switch (etiquette.typeEtiquette.type) {
            case "text":
                inputType += "<input id='" + etiquette.nomChamp.replace(/\s/g, '') + "s' type='text' value='" + etiquette.valeur + "'>";
                break;
            case "number":
                inputType = "<input id='" + etiquette.nomChamp.replace(/\s/g, '') + "s' value='" + etiquette.valeur + "'type='number'/>";
                break;
            case "date":
                inputType = "<input id='" + etiquette.nomChamp.replace(/\s/g, '') + "s' value='" + etiquette.valeur + "'type='date'>";
                break;
            case "time":
                inputType = "<input id='" + etiquette.nomChamp.replace(/\s/g, '') + "s' value='" + etiquette.valeur + "'type='time'/>";
                break;
            default:
                inputType = "<input id='" + etiquette.nomChamp.replace(/\s/g, '') + "s' value='" + etiquette.valeur + "'type='text'/>";
                break;
        }
// ajouter le code HTML pour afficher l'étiquette et l'input
        html += "<div class='col-md-12'><div style='padding:8px ;margin: 8px;'><div class='col-md-4 control-drag'>" + etiquette.nomChamp
                + ":</div> " + " <div class='col-md-4 input-group'>" + inputType + "</div>"

                + "</div>";
        document.getElementById("parametrage-demande").innerHTML = html;


// },

    }

}
function validation(list, refus) {
    $.ajax({
        url: url_base + '/demandes/validation?user=' + window.localStorage.getItem('username') + '&validation=' + list + '&refus=' + refus,
        type: 'PUT',
        contentType: "application/json; charset=utf-8",
        async: false,
        success: function (data)
        {
            showNotification('Succès', "Validation effectuée avec succés", 'success', 5000);
            DrawLesNouvellesDemandes();
        }
    });
}


function DrawTableLesDemandes() {
    window.parent.$.loader.open();
    setTimeout(function () {
        DrawLesNouvellesDemandes("tableNouvelleDemandes", "_grid_NouvelleDemandes");
        window.parent.$.loader.close();
    }, 100);
}
function DrawLesNouvellesDemandes() {

    var data = [];
    let id;
    let url = window.location.search;
    if (url !== '') {
        var e = [];
        e = url.split('?');
        var t = [];
        t = e[1].split('&');
        id = t[0].split('=')[1];
    }
    data = findAllDemandeByCodeParametrage(id);
    document.getElementById("_grid_NouvelleDemandes").innerHTML = '';
    var table_list = "<table id='tableNouvelleDemandes' class='display projects-table table table-striped table-bordered table-hover' cellspacing='0'  width='100%'>";
    table_list += "</table>";
    $("#_grid_NouvelleDemandes").html(table_list);
    var columns = [
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
            data: 'idEtat',
            title: 'valider',
            sortable: false,
            render: function (data, type, row, meta) {
                var check = data === "0 " ? "unchecked" : "checked";
                return '<form><label style="display: flex;justify-content: center;align-items: center; "><input name="renouv" type="checkbox" class="editor-active checkBoxClass checkbox" ' + '><span></span></label></form>';
            }
        },
        {
            data: 'idEtat',
            title: 'Refuser',
            sortable: false,
            render: function (data, type, row, meta) {
                var check = data === "0 " ? "unchecked" : "checked";
                return '<form><label style="display: flex;justify-content: center;align-items: center; "><input name="renouv" type="checkbox" class="editor-active checkBoxClass checkBoxClassRef checkbox" ' + '><span></span></label></form>';
            }
        }


    ];
    if (window.localStorage.getItem('username').includes('Employe')) {
        columns.splice(6, 2);
    }
    var pageLength = parseInt(($(document).height() - 220) / 34);
    table = $('#tableNouvelleDemandes').on('page.dt', function () {}).DataTable({
        "dom": 'frtip',
        "searching": true,
        destroy: false,
        bPaginate: true,
        "pageLength": pageLength,
        data: data,
        "order": [],
        language: dataTablesLang,
        columns: columns,
        "aoColumnDefs": [{
                'bSortable': false
            }]

    });

    $('#tableNouvelleDemandes  tbody').delegate('tr', 'click', function (e) {
        var highlightColor = '#d9edf7';
        var css = $(this).attr('style');
        if (css !== 'border-color: rgb(217, 237, 247); background-color: rgb(217, 237, 247)') {
            $('#tableNouvelleDemandes > tbody > tr').removeAttr('style');
            $('#tableNouvelleDemandes > tbody > tr').removeClass('selectionnee');
            $(this).addClass('selectionnee');
            $(this).css('background-color', highlightColor);
            $(this).css('border-color', highlightColor);
        } else {
            $(this).removeAttr('style');
        }
        $('#tableNouvelleDemandes > tbody > tr').focus();
    });
    $("#search").on("keyup search input paste cut", function () {
        table.search(this.value).draw();
    });
    $('#tableNouvelleDemandes > tbody').on('dblclick', function (e) {
        var rowDde = $('#tableNouvelleDemandes').find('tr.selectionnee');
        if (rowDde.length === 0)
            showNotification('Attention', 'veuillez Sélectionner une Demande', 'error', 2000);
        else {
            var numeroDemande = $('.selectionnee').find('td').eq(0).text();
            majNVDemande(numeroDemande, 'consult');
        }
    });
    $('#tableNouvelleDemandes_info').css("padding", '0');
    $('#tableNouvelleDemandes_filter').hide();
}
function  AfficheModalAddLesDemandes() {
    var i = 0;
    $('#modalIconDemande').replaceWith('<i id="modalIconDemande" class="glyphicon glyphicon-plus"></i>');
    $('#modalAdd').modal('show');
    ///
    // récupérer l'ID de la demande à partir de l'URL ou d'un clic sur un bouton

    let demandeId;
    let url = window.location.search;
    if (url !== '') {
        var e = [];
        e = url.split('?');
        var t = [];
        t = e[1].split('&');
        demandeId = t[0].split('=')[1];
    }
// faire une requête AJAX pour récupérer les données de la demande
    $.ajax({
        url: url_base + '/parametragedemandes/' + demandeId,
        method: "GET",
        success: function (demandeData) {
// créer le code HTML dynamique pour afficher le paramétrage de la demande
            $('#labelTitre').text("Ajout d'une " + demandeData.designation);
            $('#modal_ajout_Demandes_title h2').val("Ajout d'une " + demandeData.designation);
            var html = "<ul>";

            html += "<div class='username'><i class='fa fas fa-duotone fa-user-tie'></i>" + "      " + window.localStorage.getItem('username') + "</div>";
            html += "<div class='separator'></div>";
            for (i = 0; i < demandeData.etiquetteparametragedemandeDTOs.length; i++) {
                var etiquette = demandeData.etiquetteparametragedemandeDTOs[i];
// faire une requête AJAX pour récupérer les données de l'étiquette
                $.ajax({
                    url: url_base + '/etiquetteparametragedemandes/' + etiquette.code,
                    method: "GET",
                    success: function (etiquetteData) {


                        var inputType = "";

                        switch (etiquetteData.typeEtiquetteDTO.type) {

                            case "text":
                                inputType = "<input id='" + etiquetteData.description.replace(/\s/g, '') + "s' type='text'/>";
                                break;
                            case "number":
                                inputType = "<input id='" + etiquetteData.description.replace(/\s/g, '') + "s' type='number'/>";
                                break;
                            case "date":
                                var today = new Date();
                                var minDate = formatDate(today);
                                var maxDate = formatDate(addMonths(today, 1));
                                inputType = "<input id='" + etiquetteData.description.replace(/\s/g, '') + "s' type='date' min='" + minDate + "' max='" + maxDate + "'/>";
                                break;
                            case "time":
                                inputType = "<input id='" + etiquetteData.description.replace(/\s/g, '') + "s' type='time'/>";
                                break;
                            default:
                                inputType = "<input id='" + etiquetteData.description.replace(/\s/g, '') + "s' type='text'/>";
                                break;
                        }
                        let x = etiquetteData.description;

// ajouter le code HTML pour afficher l'étiquette et l'input
                        html += "<div class='col-md-12'><div style='padding:8px ;margin: 8px;'><div class='col-md-4 control-drag'>" + x
                                + ":</div> " + " <div class='col-md-4 input-group'>" + inputType + "</div>"
                                + etiquetteData.defultValue
                                + "</div>";
// mettre à jour le code HTML affiché sur la page
                        document.getElementById("parametrage-demande").innerHTML = html;
                    },
                    error: function (xhr, status, error) {
                        console.error("Erreur lors de la récupération des données de l'étiquette : " + error);
                    }
                });
            }
            html += "</ul>";
        },
        error: function (xhr, status, error) {
            console.error("Erreur lors de la récupération des données de la demande : " + error);
        }
    });
    $("#btnMAJDemandes").show();
    sessionStorage.setItem("Demande", 'ajout');
}
// Fonction utilitaire pour formater une date au format "YYYY-MM-DD"
function formatDate(date) {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

// Fonction utilitaire pour ajouter un nombre de mois à une date
function addMonths(date, months) {
    const newDate = new Date(date);
    newDate.setMonth(date.getMonth() + months);
    return newDate;
}
function submitMAJLesDemandes() {
    var rowDde = $('#tableNouvelleDemandes').find('tr.selectionnee');

    var numeroDemande = $('.selectionnee').find('td').eq(0).text();


    if (sessionStorage.getItem("Demande") === 'delete') {
        deleteDemandes(numeroDemande);
    } else {


        var payload = payloadLesDemandes();
        if (sessionStorage.getItem("Demande") === 'ajout') {
            addDemande(payload);
        } else {
            if (sessionStorage.getItem("Demande") === 'modif') {
                updateDemandes(payload);
            }
        }

    }
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
            showNotification('succès', 'Ajout effectuée avec succès', 'success', 3000);
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

function findAllDemandeByCodeParametrage(code) {

    var url = url_base + '/demandes/filter';
    if (code !== undefined) {
        url = url + '?codeParametrage=' + code;
    }
    if (window.localStorage.getItem('username') !== undefined && window.localStorage.getItem('username').toLowerCase().includes('employe') ) {
        url = url + '&nomEmploye=' + window.localStorage.getItem('username').trim();
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
        url: `${url_base}/demandes/update?user=` + window.localStorage.getItem('username'),
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
            showNotification('Succès', "Suppression effectuée avec succès", 'success', 5000);
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
function payloadLesDemandes() {
    var etiquettes = [];
    var numeroDemande = $('.selectionnee').find('td').eq(0).text();
    for (var i = 0; i < Demande.etiquetteparametragedemandeDTOs.length; i++) {
        var etiquette = {};
        var etiqu = Demande.etiquetteparametragedemandeDTOs[i];
        etiquette["nomChamp"] = etiqu.description;
        etiquette["valeur"] = $('#' + etiqu.description.replace(/\s/g, '') + 's').val();
        etiquette["codeTypeEtiquette"] = etiqu.typeEtiquetteDTO.code;
        etiquette["Codedemande"] = numeroDemande;
        etiquettes.push(etiquette);
    }

    var payload = {
        "nomEmploye": window.localStorage.getItem('username'),
        "idEmployes": window.localStorage.getItem('username'),
        "designation": Demande.designation,
        "code": numeroDemande,
        "typeDemande": Demande.codeTypeDemande,
        "codeParametrage": demandeId,
        "etat": Demande.etats,
        "champsDTOs": etiquettes
    };
    return payload;
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

function findDemande() {
    var response = "";
    $.ajax({
        url: url_base + '/parametragedemandes/' + demandeId,
        contentType: "text/html; charset=utf-8",
        type: 'GET',
        dataType: "json",
        async: false,
        success: function (data)
        {
            Demande = data;
        }
    });
    return response;
}
function findChamps(num) {
    var response = "";
    $.ajax({
        url: url_base + '/listedeschampss/filter?codeParametrage=' + num,
        contentType: "text/html; charset=utf-8",
        type: 'GET',
        dataType: "json",
        async: false,
        success: function (data)
        {
            Champs = data;
        }
    });
    return response;
}
function addDemande(list) {
    $.ajax({
        url: url_base + '/demandes?user=' + window.localStorage.getItem('username'),
        type: 'POST',
        data: JSON.stringify(list),
        contentType: "application/json; charset=utf-8",
        async: false,
        success: function () {
            showNotification('Succès', 'Ajout effectuée', 'success', 3000);
            $('#modalAdd').modal('hide');
            showLoadingNotification();
            setTimeout(function () {
                DrawLesNouvellesDemandes("tableNouvelleDemandes", "_grid_NouvelleDemandes");
                hideLoadingNotification();
            }, 50);
        }
        ,
        complete: function (jqXHR, textStatus) {
        }
    });
}
