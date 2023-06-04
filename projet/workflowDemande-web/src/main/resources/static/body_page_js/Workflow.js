$(function () {
    drawBtnWorkflow();
    DrawTableWorkflow();
    $('#rafresh').unbind('click');
    $('#rafresh').bind('click', function (e) {
        showLoadingNotification();
        setTimeout(function () {
            DrawTableWorkflow();
            hideLoadingNotification();
        }, 50);
    });
    $('#btnMAJWorkflow').unbind('click');
    $('#btnMAJWorkflow').bind('click', function () {
        window.parent.$.loader.open();
        setTimeout(function () {
            submitMAJWorkflow();
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
            DrawTableWorkflow();
            hideLoadingNotification();
        }, 50);
    });
      });
