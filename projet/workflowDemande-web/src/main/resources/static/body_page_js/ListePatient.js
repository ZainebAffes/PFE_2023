$(function () {
    drawBtnPatient();
    DrawTablePatient("tableListPatient", '_grid_ListPatient');
    applyDatePresentation("date_du");
    applyDatePresentation("date_au");
    applyDatePresentation("dateNaissRecherchePatient");
    afficheDate();
    $('#dateNaissRecherchePatient').datepicker({
        orientation: 'auto top'
    });
    $('#date_du').datepicker({
        orientation: 'auto top'
    });
    $('#date_au').datepicker({
        orientation: 'auto top'
    });


    $("#date_du").bind("change", function ()
    {
        var debut = $("#date_du").val();
//      var fin = $('#date_au').val();
        var deb = new Date(debut.split("/")[2], debut.split("/")[1] - 1, debut.split("/")[0]);
        var date = new Date(debut.split("/")[2], debut.split("/")[1] - 1, debut.split("/")[0]);
        date.setDate(1);
        var nbj = new Date(date.getFullYear(), date.getMonth() + 1, -1).getDate() + 1;
        var afterMonth = getDayAfterDays(new Date(deb.getFullYear(), deb.getMonth(), deb.getDate()), (nbj - 1));

        var dayafterMonth = (afterMonth.getDate().toString().length === 2) ? afterMonth.getDate() : "0" + afterMonth.getDate();
        var monthafterMonth = ((afterMonth.getMonth() + 1).toString().length === 2) ? afterMonth.getMonth() + 1 : "0" + (afterMonth.getMonth() + 1);
        var yearafterMonth = afterMonth.getFullYear();
        $("#date_au").val(dayafterMonth + "/" + monthafterMonth + "/" + yearafterMonth);

        if ((debut === "") || (!verifValidDate(debut)))
        {
            $("#date_au").val("");
        }

        $('#apercu').show();
        $('#_grid_ListDate table tbody').html("<tr><td colspan='9'>Pas de résultat</td></tr>");
    });

    $('#apercu').unbind('click');
    $('#apercu').bind('click', function (e) {
        refreshDate();
    });



    $('#rafresh').unbind('click');
    $('#rafresh').bind('click', function (e) {
        showLoadingNotification();
        setTimeout(function () {
            refresh();
            refreshDate();
            DrawTablePatient("tableListPatient", '_grid_ListPatient');
            hideLoadingNotification();
        }, 50);
    });
    $('.etatCli').unbind('click');
    $('.etatCli').bind('click', function (e) {
        $('.etatCli').find('i').removeClass('fa-check-circle');
        $('.etatCli').find('i').removeClass('fa-search');
        $('.etatCli').find('i').addClass('fa-search');
        $(this).find('i').eq(0).removeClass('fa-search');
        $(this).find('i').eq(0).addClass('fa-check-circle');
        DrawTablePatient("tableListPatient", '_grid_ListPatient');
    });
    $('#btnact').unbind('click');
    $('#btnact').bind('click', function (e) {

        DrawTablePatient("tableListPatient", '_grid_ListPatient');
    });

    $('#btnFermer').unbind('click');
    $('#btnFermer').bind('click', function (e) {
        window.parent.$('ul.ui-tabs-nav .active .closeRad').click();
    });

});