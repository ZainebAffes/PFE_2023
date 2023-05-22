$(function () {
     drawBtn();
      $('#btnContainer').bind('click', function () {
           window.parent.$.loader.open();
           setTimeout(function () {       
            window.parent.$.loader.close();
        }, 100);
    
        });  
   
  
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
    
      });
