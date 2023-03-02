var pageableGridList;
function drawBtnPatient() {
    DessinerButton("10", '#bntContainer', true);
    ActionBouttonPatient();
}
function ActionBouttonPatient() {


    $('#btn_Remise').unbind('click');
    $('#btn_Remise').bind('click', function (e) {
        var rowDde = $('#tableListPatient').find('tr.selectionnee');
        if (rowDde.length === 0)
        {
            showNotification('Avertissement', "Veuillez choisir un patient", 'error', 3000);
        } else
        {
            var code = rowDde.find('td').eq(1).text().replace('&amp;', '%26');
            sessionStorage.setItem("typeParametragePatient", 'accorder_remise');
            onclickBtnOpenModalPatient(code, "remise");
        }
    });

    $('#btn_Imprimer').unbind('click');
    $('#btn_Imprimer').bind('click', function (e) {
        var listParam = listcolumnedition("Patient");
        var index = 0;
        index = _.where(listParam, {visible: true}).length;
        if (index > 10) {
            showNotification('Avertissement', "Veuillez choisir au maximum 10 colonnes visibles dans l'impression", 'error', 3000);
        } else {
            consultImprPatient();
        }
    });
    $("#btn_Exporter").unbind("click");
    $("#btn_Exporter").bind("click", function (e) {
        var varActif;
        var etatActif = $('.filtreActif').find('.fa-check-circle').parent().find('span').text();
        if (etatActif === "Actif") {
            varActif = "true";
        } else if (etatActif === "Non actif") {
            varActif = "false";
        } else if (etatActif === "Tous") {
            varActif = "true,false";
        }
        var numSer = $("#listSelectServ").val() !== null ? $("#listSelectServ").val() : "";
        var varCouleur;
        var etatCouleur = $('.etatCli').find('.fa-check-circle').parent().find('span').eq(0).text();
        if (etatCouleur === "Réservée") {
            varCouleur = "1";
        } else if (etatCouleur === "Occupée") {
            varCouleur = "2";
        } else if (etatCouleur === "Prête") {
            varCouleur = "5";
        } else if (etatCouleur === "Réparation") {
            varCouleur = "4";
        } else if (etatCouleur === "Libre") {
            varCouleur = "3";
        } else if (etatCouleur === "Tous") {
            varCouleur = "1,2,3,4,5";
        }
        var url = url_base + 'clients/print?actifs= ' + varActif + '&externe=0&listEtat=' + varCouleur + '&user=' + window.localStorage.getItem('usernameFichBase');
        if (numSer !== "") {
            url = url + '&numSer=' + numSer;
        }
        exporterList(url, "CHAMBRES");
    });
}
function consultImprPatient() {
    var varCouleur;
    var etatCouleur = $('.etatCli').find('.fa-check-circle').parent().find('span').eq(0).text();
    if (etatCouleur === "Réservée") {
        varCouleur = "1";
    } else if (etatCouleur === "Occupée") {
        varCouleur = "2";
    } else if (etatCouleur === "Prête") {
        varCouleur = "5";
    } else if (etatCouleur === "Réparation") {
        varCouleur = "4";
    } else if (etatCouleur === "Libre") {
        varCouleur = "3";
    } else if (etatCouleur === "Tous") {
        varCouleur = "1,2,3,4,5";
    }
    var varActif;
    var etatActif = $('.filtreActif').find('.fa-check-circle').parent().find('span').eq(0).text();
    if (etatActif === "Actif") {
        varActif = "true";
    } else if (etatActif === "Non actif") {
        varActif = "false";
    } else if (etatActif === "Tous") {
        varActif = "true,false";
    }
    var numSer = $("#listSelectServ").val() !== null ? $("#listSelectServ").val() : "";
    var list = listcolumnedition("Patient");
    if (list.length === 0) {
        addColonneEdition("Patient");
    }
    var url = url_base + `clients/printchambres`;
    if (numSer === "") {
        url = url + '?actifs=' + varActif + '&listEtat=' + varCouleur + '&externe=0';
    } else {
        url = url + '?actifs=' + varActif + '&listEtat=' + varCouleur + '&externe=0&numSer=' + numSer;
    }
    impressionListe(url);
}
function DrawTablePatient(idTable, idContainer) {
    showLoadingNotification();
    var List = [];
    var dateDu = $('#date_du').val();
    var dateAu = $('#date_au').val();
    var dateNais = $('#dateNaissRecherchePatient').val();
    var etatCli = $('.etatCli').find('.fa-check-circle').parent().find('span').eq(0).text();
    var dateDu = formatCalendarWithTime(dateDu, "dd-mm-yyyy");
    var dateAu = formatCalendarWithTime(dateAu, "dd-mm-yyyy");
    var numCha = $("#NumCha").val();
    var numFac = $("#numfac").val();
    var medTrait = $("#selectMedecin").val();
    var numDoss = $("#numDoss").val();
    var prenom = $("#prenom").val();
    var nomCli = $("#Nom").val();
    var cin = $("#Identifiant").val();

    var dateN = formatCalendarWithTime(dateNais, "dd-mm-yyyy");
    List = findListePatient(etatCli, dateDu, dateAu, numCha, numFac, medTrait, numDoss, nomCli, prenom, cin, dateN);
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
                data: 'desEtatCli',
                title: 'État',
                render: function (data, type, row, meta) {
                    var couleur;
                    switch (data) {
                        case  "Hospitalisé":
                            couleur = "#1ac165";
                            break;
                        case "Autorisé":
                            couleur = "#2149df";
                            break;
                        case "Audité":
                            couleur = "#ec8e8e";
                            break;
                        case "Facturé":
                            couleur = "#de0f0f";
                            break;
                        case "En Instance":
                            couleur = "#efbb50";
                            break;
                        default:
                            couleur = "";
                    }
                    return  '<span data-toggle="tooltip" class="btn-default" data-toggle="tooltip" title="' + data + '" style="background-color:' + couleur + ';height: 24px !important;display: inline-block;"> &nbsp;&nbsp;</span>';
                }
            },
            {
                data: 'numDoss',
                title: 'N° Dossier',
                filterable: true,
                render: function (data, type, row, meta) {
                    if (data !== null) {
                        return "<div title='" + data + "' class='longtd'>" + data + "</div>";
                    } else {
                        return "";
                    }
                }
            }
            ,
            {
                data: 'numCha',
                title: 'N° Patient',
                class: 'numCha'
            },
            {
                data: 'nomCli',
                title: 'Patient',
                filterable: true,
                render: function (data, type, row, meta) {
                    if (data !== null) {
                        return "<div title='" + data + "' class='longtd'>" + data + "</div>";
                    } else {
                        return "";
                    }
                }
            }
            ,
            {
                data: 'libNat',
                title: 'Nationalité',
                filterable: true,
                render: function (data, type, row, meta) {
                    if (data !== null) {
                        return "<div title='" + data + "' class='longtd'>" + data + "</div>";
                    } else {
                        return "";
                    }
                }
            },
            {
                data: 'nomMed',
                title: 'Médecin',
                filterable: true,
                render: function (data, type, row, meta) {
                    if (data !== null) {
                        return "<div title='" + data + "' class='longtd'>" + data + "</div>";
                    } else {
                        return "";
                    }

                }
            },
            {
                data: 'datArr',
                title: "Date d'admission",
                filterable: true,
                render: function (data) {
                    if (data === null || data === undefined)
                        return '';
                    else
                        return formatCalendarWithTime(data, 'dd/mm/yyyy');
                }
            },
            {
                data: 'societeDTO',
                title: 'Organisme',
                filterable: true,
                render: function (data, type, row) {
                    if (data !== null) {
                        return "<div title='" + data.desSoc + "' class='longtd'>" + data.desSoc + "</div>";
                    } else {
                        return "";
                    }
                }},
            {
                title: "Date d'autorisation",
                data: 'datAutoris',
                width: "100px",
                filterable: true,
                render: function (data) {
                    if (data === null || data === undefined)
                        return '';
                    else
                        return formatCalendarWithTime(data, 'dd/mm/yyyy');
                }
            },
            {
                title: "Motif d'admission",
                data: 'desMotif',
                width: "120px",
                filterable: true
            },
            {
                title: "Durée d'attente",
                data: 'dureeAttente',
                width: "100px",
                filterable: true,
                render: function (data) {
                    if (data !== null)
                        return `<div>${data}</div>`;
                    else
                        return `<div></div>`;
                }
            },
            {
                title: "Autorisé par",
                data: 'autoris',
                width: "80px",
                filterable: true
            }
        ],
        "aoColumnDefs": [{
                'bSortable': false,
                'aTargets': colDef
            }],
        "order": [[0, "asc"]]
    });
    $('#tableListPatient  tbody').delegate('tr', 'click', function (e) {
        var highlightColor = '#d9edf7';
        var css = $(this).attr('style');
        if (css !== 'border-color: rgb(217, 237, 247); background-color: rgb(217, 237, 247)') {
            $('#' + idTable + ' > tbody > tr').removeAttr('style');
            $('#' + idTable + ' > tbody > tr').removeClass('selectionnee');
            $(this).addClass('selectionnee');
            $(this).css('background-color', highlightColor);
            $(this).css('border-color', highlightColor);
        } else {
            $(this).removeAttr('style');
        }
        $('#' + idTable + ' tbody > tr').focus();
    });
    $("#search").on("keyup search input paste cut", function () {
        table.search(this.value).draw();
    });
    $('#tableListPatient_info').css("padding", '0');
    $('#tableListPatient_filter').hide();
    hideLoadingNotification();
}
function findListePatient(etatCli, dateDu, dateAu, numCha, numFac, medTrait, numDoss, nomCli, prenom, cin, dateN) {
    var url = url_base + '/clients/find';
    if (etatCli === "Hospitalisé") {
        url += `?etatCli=0`;
        url += `&audit=false`;
    } else if (etatCli === "Facturé") {
        url += `?etatCli=1`;
    } else if (etatCli === "en Instance") {
        url += `?etatCli=0`;
        url += `&autoris=''`;
        url += `&audit=false`;
    } else if (etatCli === "Audité") {
        url += `?etatCli=0`;
        url += `&audit=true`;
    } else if (etatCli === "Autorisé") {
        url += `?etatCli=0`;
        url += `&audit=false`;
        url += `&notAutoris=''`;
    }
    if (numDoss !== "" && numDoss !== undefined) {
        url += `&numDoss=${numDoss}`;
    }
    if (numFac !== "" && numFac !== undefined) {
        url += `&numFac=${numFac}`;
    }
    if (nomCli !== "" && nomCli !== undefined) {
        url += `&nomCli=${nomCli}`;
    }
    if (prenom !== "" && prenom !== undefined) {
        url += `&prenom=${prenom}`;
    }
    if (medTrait !== "" && medTrait !== undefined) {
        url += `&medTrait=${medTrait}`;
    }
    if (numCha !== "" && numCha !== undefined) {
        url += `&numCha=${numCha}`;
    }
    if (cin !== "" && cin !== undefined) {
        url += `&cin=${cin}`;
    }
    if (dateN !== "--" && dateN !== undefined) {
        url += `&datNai=${dateN}`;
    }
    if (etatCli === "facture" && dateDu !== "--" && dateDu !== undefined && dateAu !== "--" && dateAu !== undefined) {

        url += `&dateDu=${dateDu}`;
        url += `&dateAu=${dateAu}`;
    }
    url += `&numRecept=${numRecept}&lazy=false`;
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
function refreshDate() {
    var date_du = $("#date_du").val();
    var date_au = $("#date_au").val();
    if (verifValidDate(date_du) && (verifValidDate(date_au))) {
        var dif = dateDiff(date_du, date_au);
        if (dif < 0) {
            showNotification('Attention!', 'Période non valide', 'error', 5000);
            $('#date_du').addClass('has-error');
            $('#date_au').addClass('has-error');
        } else {
            if (date_du !== '') {
                if (date_au === "") {
                    date_au = getDateNow();
                    $('#_date_finInstance').val(date_au);
                }
            }
            $('#date_du').removeClass('has-error');
            $('#date_au').removeClass('has-error');
            setTimeout(function () {
                DrawListJourDate(date_du, date_au);
                window.parent.$.loader.close();
            }, 100);
        }
    } else {
        $('#date_du').removeClass('has-error');
        $('#date_au').removeClass('has-error');
        if ((verifValidDate(date_du) === false) && (verifValidDate(date_au) === false)) {
            $('#date_du').addClass('has-error');
            $('#date_au').addClass('has-error');
            showNotification('Attention!', 'Date non valide', 'error', 5000);
        } else if (verifValidDate(date_du) === false) {
            $('#date_du').addClass('has-error');
            showNotification('Attention!', 'Date non valide', 'error', 5000);
        } else if (verifValidDate(date_au) === false) {
            $('#date_au').addClass('has-error');
            showNotification('Attention!', 'Date non valide', 'error', 5000);
        }
    }
}

function refresh() {
    $('#date_du').val("");
    $('#date_au').val("");
    $('#dateNaissRecherchePatient').val("");
    $("#NumCha").val("");
    $("#numfac").val("");
    $("#selectMedecin").val("");
    $("#numDoss").val("");
    $("#prenom").val("");
    $("#Nom").val("");
    $("#NumCIN").val("");


}
