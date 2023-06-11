$(function () {
    drawBtnLesDemandes();
    DrawTableLesDemandes();
    findDemande();
    $('#rafresh').unbind('click');
    $('#rafresh').bind('click', function (e) {
        showLoadingNotification();
        setTimeout(function () {
            DrawLesNouvellesDemandes();
            hideLoadingNotification();
        }, 50);
    });
    $('#btnMAJDemandes').unbind('click');
    $('#btnMAJDemandes').bind('click', function () {
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


});

