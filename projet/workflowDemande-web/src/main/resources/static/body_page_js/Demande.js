$(function () {
    drawBtnTypeDemande();
    DrawTableTypeDemande();
    $('#rafresh').unbind('click');
    $('#rafresh').bind('click', function (e) {
        showLoadingNotification();
        setTimeout(function () {
            DrawTableTypeDemande();
            hideLoadingNotification();
        }, 50);
    });
    $('#btnMAJDemande').unbind('click');
    $('#btnMAJDemande').bind('click', function () {
        window.parent.$.loader.open();
        setTimeout(function () {
            submitMAJTypeDemande();
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
            DrawTableTypeDemande();
            hideLoadingNotification();
        }, 50);
    });
      });
