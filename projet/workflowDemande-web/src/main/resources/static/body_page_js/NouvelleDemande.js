$(function () {
    drawBtnLesDemandes();
    DrawTableLesDemandes();
    $('#rafresh').unbind('click');
    $('#rafresh').bind('click', function (e) {
        showLoadingNotification();
        setTimeout(function () {
            DrawTableLesDemandes();
            hideLoadingNotification();
        }, 50);
    });
    $('#btnMAJDemande').unbind('click');
    $('#btnMAJDemande').bind('click', function () {
        window.parent.$.loader.open();
        setTimeout(function () {
            submitMAJLesDemandes();
            window.parent.$.loader.close();
        }, 100);
    });

    $('#btnFermer').unbind('click');
    $('#btnFermer').bind('click', function (e) {
        window.parent.$('ul.ui-tabs-nav .active .closeRad').click();
    });


    $('#rafresh').unbind('click');
    $('#rafresh').bind('click', function (e) {
        showLoadingNotification();
        setTimeout(function () {
            DrawTableLesDemandes();
            hideLoadingNotification();
        }, 50);
    });

    ///
    // récupérer l'ID de la demande à partir de l'URL ou d'un clic sur un bouton
    var demandeId = "11";

// faire une requête AJAX pour récupérer les données de la demande
    $.ajax({
        url: url_base +'/parametragedemandes/' + demandeId,
        method: "GET",
        success: function (demandeData) {
// créer le code HTML dynamique pour afficher le paramétrage de la demande
            var html = "<h2 style='font-size: 1.2em ;margin: 0px; background-color: #1293b8;color: white;'>"+"<i class='fa fas fa-pen';></i>"
                    + demandeData.designation 
                    + "</h2>";
            html += "<ul>";
            for (var i = 0; i < demandeData.etiquetteparametragedemandeDTOs.length; i++) {
                var etiquette = demandeData.etiquetteparametragedemandeDTOs[i];
// faire une requête AJAX pour récupérer les données de l'étiquette
                $.ajax({
                    url: url_base +'/etiquetteparametragedemandes/' + etiquette.code,
                    method: "GET",
                    success: function (etiquetteData) {

                        var inputType = "";
                        switch (etiquetteData.typeEtiquetteDTO.type) {
                            case "text":
                                inputType = "<input type='text'/>";
                                break;
                            case "number":
                                inputType = "<input type='number'/>";
                                break;
                                 case "date":
                                inputType = "<input type='date'>";
                                break;
                                 case "time":
                                inputType = "<input type='time'/>";
                                break;

                            default:
                                inputType = "<input type='text'/>";
                                break;
                        }
// ajouter le code HTML pour afficher l'étiquette et l'input
                        html += "<div class='col-md-12'><div style='padding:8px ;margin: 8px;'><div class='col-md-4 control-drag'>" + etiquetteData.description 
                                + ":</div> " + " <div class='col-md-4 input-group'>" + inputType + "</div>" 
                                + etiquetteData.defultValue
                                +"</div>";
                        
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

});

