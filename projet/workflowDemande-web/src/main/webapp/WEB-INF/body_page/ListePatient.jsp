<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String date = dateFormat.format(new Date());
%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>


<spring:eval var="url_base" expression="@environment.url_base"/>
<spring:eval var="idModule" expression="@environment.idModule"/>
<spring:eval var="url_base_access" expression="@environment.url_base_access"/>
<spring:eval var="currencyScale" expression="@environment.currencyScale"/>
<spring:eval var="url_base_fichier_base" expression="@environment.url_base_fichier_base"/>
<spring:eval var="numRecept" expression="@environment.numRecept"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="css_declare.jsp"/>   
        <title><spring:message code="fb.Acceuil.Patient"/></title>
        <style>
            .myDropDoxnStyle_L .select2-results .select2-result-label{
                padding:0;
            }
            #sessionExpirationConfirm{
                z-index:999999999;
            }
            .longtd,input{
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
                font-size:12px;
            }
            label{
                font-size: 12px;
                font-weight: 300;
            }
            .nav-tabs.bordered {
                background: #3276b1;
            }
            .smart-form fieldset {
                padding: 0px 14px 5px;
            }
            .well{
                padding-top: 0;
            }
            [class^="select2"],select{
                font-size: 12px;
            }
            .form-group {
                margin-bottom: 5px;
            }

            legend {
                margin: 0px 0 10px;
            }

            .input-xs {
                height: 25px;
                font-size: 12px;
            }
            .btn-circle {
                width: 25px;
                height: 25px;
                padding: 4px 0px;
                font-size: 12px;
            }
            .smart-form .inline-group .radio {
                float: left;
                margin-right: 5px;
            }
            @media screen{
                #LabelMotif{
                    display: block;
                    color: #0A0A0A;
                    text-align: left;
                    font-size:100%;
                }
                .rowSelected{
                    background-color:rgb(217, 237, 247);
                }
            }
            .form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control {
                background-color: #fff;
            }
            .control-label{
                top: 5px;
            }
            .selectionne{
                background-color: #BAD2E4;
            }
            #select2-drop.myDropDoxnStyle_L{
                width: 40% !important;
            }
            .btn-group,  .btn-group-vertical {
                position: relative;
                display: block;
                vertical-align: middle;
            }
            .champOblig{
                color: red;
            }

            select2-choice ,.form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control , [disabled] {
                background-color: #fff;
            }

            .select2-container {
                width:100%;
            }
            .select2-container a.select2-choice {
                font-size: 11px;
                height: 25px;
                padding: 4px 12px;
                line-height: 1.42857;
            }
            .select2-container .select2-choice .select2-arrow {
                padding-top:6px;
                width: 25px;
            }
            .input-group-addon {
                padding: 4px 6px;
            }

            td {
                vertical-align: middle !important;
            }
            #_grid_ListPatient  thead > tr th:nth-child(1),
            #_grid_ListPatient  tbody > tr td:nth-child(1){
                width: 5%!important;
            }
            #_grid_ListPatient  thead > tr th:nth-child(2),
            #_grid_ListPatient  tbody > tr td:nth-child(2){
                width: 10%!important;

            }
            #_grid_ListPatient  thead > tr th:nth-child(3),
            #_grid_ListPatient  tbody > tr td:nth-child(3){
                width: 8%!important;
            }
            #_grid_ListPatient  thead > tr th:nth-child(4),
            #_grid_ListPatient  tbody > tr td:nth-child(4){
                width: 8%!important;
            }

            #_grid_ListPatient  thead > tr th:nth-child(5),
            #_grid_ListPatient  tbody > tr td:nth-child(5){
                width: 8%!important;
            }
            #_grid_ListPatient  thead > tr th:nth-child(6),
            #_grid_ListPatient  tbody > tr td:nth-child(6){
                width: 15%!important;
            }
            #_grid_ListPatient  thead > tr th:nth-child(7),
            #_grid_ListPatient  tbody > tr td:nth-child(7){
                width: 8%!important;
            }
            #_grid_ListPatient  thead > tr th:nth-child(8),
            #_grid_ListPatient  tbody > tr td:nth-child(8){
                width: 6%!important;
            }
            #_grid_ListPatient  thead > tr th:nth-child(9),
            #_grid_ListPatient  tbody > tr td:nth-child(9){
                width: 10%!important;
            }

            #_grid_ListPatient  thead > tr th:nth-child(10),
            #_grid_ListPatient  tbody > tr td:nth-child(10){
                width: 8%!important;
            }
            #_grid_ListPatient  thead > tr th:nth-child(11),
            #_grid_ListPatient  tbody > tr td:nth-child(11){
                width: 8%!important;
            }
            #_grid_ListPatient  thead > tr th:nth-child(12),
            #_grid_ListPatient  tbody > tr td:nth-child(12){
                width: 8%!important;
            }

            #_grid_ListPatient  tbody {
                flex: 1 1 auto;
                width: 100%;
                display: block;
                overflow-y: scroll;
                overflow-x: hidden;
                min-height: 20px;
                max-height: calc(100vh - 222px);
                height:auto !important;
            }
            #_grid_ListPatient {
                display: flex;
                flex-flow: column;
                height: 100%;
                width: 100%;
            }
            #_grid_ListPatient thead, #_grid_ListPatient tbody tr {
                display: table;
                table-layout: fixed;
            }
            #_grid_ListPatient thead {
                width: calc(100% -  6px) !important;
                flex: 0 0 auto;
            }
            #_grid_ListPatient tbody tr {
                width: 100%;
            }

            #_grid_ListPatient  tbody > tr > td,
            #_grid_ListPatient  tbody > tr > th,
            #_grid_ListPatient  tfoot > tr > td,
            #_grid_ListPatient  tfoot > tr > th,
            #_grid_ListPatient  thead > tr > td,
            #_grid_ListPatient  thead > tr > th {
                padding: 3px 5px!important;
                font-size: 12px;
            }

            .btn.filtreActif{
                background-color: white;
                color: black;
                border-left-width: 5px;
                border-bottom-color: #0000003b;
                border-top-color: #0000003b;
                border-right-color: #0000003b;
                margin: 0px 2px!important;
                border-radius: 1px;
                box-sizing: border-box;
                width: calc(33.33% - 19px);
            }
            .btn.etatCli{
                background-color: white;
                color: black;
                border-left-width: 5px;
                border-bottom-color: #0000003b!important;
                border-top-color: #0000003b!important;
                border-right-color: #0000003b!important;
                margin: 0px 2px!important;
                border-radius: 1px;
                box-sizing: border-box;
                width: calc(17.73% - 19px);
            }

            .widget-body{
                z-index: 0;
            }

            #date_du{
                z-index: 0 !important;
            }
            #date_au{
                z-index: 0 !important;
            }
            #dateNaissRecherchePatient{
                z-index: 0 !important;
            }
        </style>
    </head>
    <body class="styleCsys" > 
        <section id="widget-grid" >

            <!-- row -->
            <div class="row hidden-print screen">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-right: 2px;">
                    <div class="jarviswidget jarviswidget-color-redLight"  data-widget-editbutton="false" data-widget-deletebutton="false">

                        <header class='screen' >
                            <a class="btn btn-default  pull-left" id="rafresh" > 
                                <span class="widget-icon"><i class="glyphicon glyphicon-refresh"></i></span>  
                            </a>
                            <h2><strong id="etat-header" >Gestion des patients </strong></h2>
                            <div style="float: right;width: 86%;"> 
                                <a class="btn btn-default accessCtrl pull-right" id="btnFermer"> 
                                    <span class="widget-icon"><i class="glyphicon btn-danger glyphicon-log-out"></i> <spring:message code="fb.global.fermer"/></span>
                                </a>
                                <a class="btn btn-default accessCtrl pull-right" id="btn_Exporter"> 
                                    <span class="widget-icon"><i class="fa fa-list"></i>Excel</span>
                                </a>
                                <div id='bntContainer'>
                                </div>
                            </div>
                        </header>
                        <!-- widget div-->
                        <div style="min-height: calc(100vh - 55px);">
                            <div class="widget-body screen col-md-12">
                                <div class="row" >
                                    <div class="col-md-6 form-group" style="float: right;">

                                        <label class="btn btn-default active etatCli" name="btnTousActif" style="border-color:#1ac165; font-size:12px;">
                                            <i class="fa fa-check-circle" style="/*font-size: 1.5em;*/"></i>
                                            <span>Hospitalisé</span>
                                        </label>
                                        <label class="btn etatCli" name="btnTousActif" style="border-color:#2149df;font-size:12px;">
                                            <i class="fa fa-search" style="/*font-size: 1.5em;*/"></i>
                                            <span>Autorisé</span>
                                        </label>
                                        <label class="btn etatCli" name="btnTousActif" style="border-color: #ec8e8e;font-size:12px; padding: 7px 0px 5px 0px;">
                                            <i class="fa fa-search" style="/*font-size: 1.5em;*/"></i>
                                            <span>Audité</span>
                                        </label>
                                        <label class="btn etatCli" name="btnTousActif" style="border-color:#de0f0f; font-size:12px;">
                                            <i class="fa fa-search" style="/*font-size: 1.5em;*/"></i>
                                            <span>Facturé</span>
                                        </label>
                                        <label class="btn etatCli" name="btnTousActif" style="border-color: #efbb50;font-size:12px;">
                                            <i class="fa fa-search" style="/*font-size: 1.5em;*/"></i>
                                            <span>en Instance</span>
                                        </label>
                                        <label class="btn etatCli" name="btnTousActif" style="border-color: #5e6561;font-size:12px;padding: 7px 0px 5px 0px;">
                                            <i class="fa fa-search" style="/*font-size: 1.5em;*/"></i>
                                            <span>Tous</span>
                                        </label>
                                    </div>
                                    <div class="col-md-5" style="float: right;">
                                        <div class="form-group col-md-4">
                                            <label class="col-md-2 control-label">Du </label>
                                            <div class="col-md-8 input-group" id="_date_debut_Parent">
                                                <input id="date_du" type="text" name="datedebut" placeholder="Date Début" class="form-control datepicker input-xs" data-mask="00/00/0000" data-mask-clearifnotmatch="true">
                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                            </div>
                                        </div>
                                        <div class="col-md-4 form-group" >
                                            <label class="col-md-2 control-label">Au </label>

                                            <div id="_date_fin_Parent" class="input-group  col-md-8" >
                                                <input  id="date_au" type="text" name="datefin" placeholder="Date Fin" class="form-control datepicker input-xs" data-mask="00/00/0000" data-mask-clearifnotmatch="true">
                                                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                            </div>
                                        </div>
                                        <div class="col-md-4  form-group ">
                                            <button id="btnact" class="btn btn-outline-primary" type="button"><span><i
                                                        class="fal fa-search mr-1"></i>
                                                </span></button>
                                            <button id="init_btn" class="btn btn-outline-dark" type="button"><span><i
                                                        class="fal fa-sync-alt mr-1"></i></span></button>
                                        </div>
                                    </div>
                                    <div class="col-md-1">
                                        <a class="btn  collapsed waves-effect waves-themed" data-toggle="collapse"
                                           href="#collapseExample" role="button" aria-expanded="false"
                                           aria-controls="collapseExample" title="Recherche avancée">
                                            <span><i class="fal fa-search-plus mr-1 fa-2x"></i></span>
                                        </a>
                                    </div>
                                    <div class="collapse row col-md-12 form-group" id="collapseExample">
                                        <label class="input">
                                            <div class="icon-addon addon-md">
                                                <input class="form-control" id="Identifiant" type="text"
                                                       placeholder="Identifiant">
                                                <label class="glyphicon glyphicon-search" rel="tooltip" title="" style="padding: 10px 0;"></label>
                                            </div>
                                        </label>
                                        <label class="input">
                                            <div class="icon-addon addon-md">
                                                <input class="form-control" id="cin" type="text"
                                                       placeholder="N°Pièce d'identité">
                                                <label class="glyphicon glyphicon-search" rel="tooltip" title="" style="padding: 10px 0;"></label>
                                            </div>
                                        </label><!-- comment -->
                                        <label class="input">
                                            <div class="icon-addon addon-md">
                                                <input class="form-control" id="numfac" type="text"
                                                       placeholder="N°Facture">
                                                <label class="glyphicon glyphicon-search" rel="tooltip" title="" style="padding: 10px 0;"></label>
                                            </div>
                                        </label>
                                        <label class="input">
                                            <div class="icon-addon addon-md">
                                                <input class="form-control" id="NumCha" type="text"
                                                       placeholder="N°Chambre">
                                                <label class="glyphicon glyphicon-search" rel="tooltip" title="" style="padding: 10px 0;"></label>
                                            </div>
                                        </label>

                                        <label class="input">
                                            <div class="icon-addon addon-md">
                                                <input class="form-control" id="numDoss" type="text"
                                                       placeholder="Numéro dossier">
                                                <label class="glyphicon glyphicon-search" rel="tooltip" title="" style="padding: 10px 0;"></label>
                                            </div>
                                        </label>
                                        <label class="input">
                                            <div class="icon-addon addon-md">
                                                <input class="form-control" id="prenom" type="text"
                                                       placeholder="Prénom">
                                                <label class="glyphicon glyphicon-search" rel="tooltip" title="" style="padding: 10px 0;"></label>
                                            </div>
                                        </label>
                                        <label class="input">
                                            <div class="icon-addon addon-md">
                                                <input class="form-control" id="Nom" type="text"
                                                       placeholder="Nom">
                                                <label class="glyphicon glyphicon-search" rel="tooltip" title="" style="padding: 10px 0;"></label>
                                            </div>
                                        </label>
                                        <div class="col-md-4  form-group ">
                                            <div class="col-md-4">
                                                <div class="input-group"> 
                                                    <input id="dateNaissRecherchePatient" type="text"
                                                           class="form-control _date_deb_fin_filtre" data-mask="00/00/0000"
                                                           placeholder="Date de naissance">
                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>  
                                </div>
                                <div class="row">
                                    <div  id="_grid_ListPatient">
                                    </div>
                                </div>

                            </div>
                        </div>
                </article>
            </div>
        </section>   

        <div class="modal fade " id="parametrage" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog centre_screen" style="width: 850px;">
                <div class="modal-content">
                    <div class="modal-header"   style="color: #1293b8;">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i></button>
                        <h4  class="modal-title"><i style="font-size: 20px;" class="glyphicon glyphicon-log-out"></i> <span>Paramétrage impression</span></h4>
                    </div>

                    <div id="parametragebody" class="modal-body"  style="overflow-x: hidden;overflow-y: auto;max-height: 200px;">
                    </div>

                    <div class="modal-footer" >

                        <button id="btnCloseModalImpr" type="" onClick="$('#parametrage').modal('hide');" class="btn btn-default btnCloseImprCh" style="float:right"><i class="fa fa-times"></i> Fermer</button>
                        <div onclick="validerparametrage()" >
                            <a href="javascript:void(0);" class="btn btn-default"   style="margin-right:10px;float:right"> 
                                <span class="widget-icon"><i class="fa fa-check"></i></span>&nbsp;&nbsp;Valider
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>           

        <div class="modal fade " id="sessionExpirationConfirm" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog centre_screen" style="width: 530px;">
                <div class="modal-content">
                    <div class="modal-header bg-color-redLight txt-color-white"   >
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i></button>
                        <h4  class="modal-title"><i style="font-size: 20px;" class="glyphicon glyphicon-log-out"></i> <label>Expiration de session</label></h4>
                    </div>

                    <div id="popup_sessionExpiration" class="modal-body">
                        <label>Votre session a expiré! Pour des raisons de sécurité, vous serez automatiquement déconnecté après <span id="SecondsUntilExpire"></span> secondes</label>
                    </div>

                    <div  class="modal-footer" style="padding: 1px 20px 4px;">
                        <div class="row"><br>
                            <div style="padding: 1px 20px 4px;" align="right">
                                <button id="submitSessionExpiration" type="button" class="btn btn-success"><i class="fa fa-sign-in"></i>&nbsp; Rester connecté</button>
                                <button id="cancelSessionExpiration" type="button" class="btn btn-danger" ><i class="fa fa-sign-out"></i>&nbsp; Se déconnecter</button>
                            </div>
                        </div>
                    </div> 
                </div>
            </div>
        </div> 

        <style>
            [id^="drag"]{
                width: 6%;
                height: 40px;
                margin: 0.5%;
                display: inline-block;
                color: white;
                border-radius: 0.75em;
                padding: 1.3%;
                font-weight: bolder;
                -webkit-transform: translate(0px, 0px);
                transform: translate(0px, 0px);
            }
            ul.tabs-left{
                overflow-y: scroll;
                max-height: 450px;
                overflow-x: hidden;
            }

            #drag-me::before {
                content: "#" attr(id);
                font-weight: bold;
            }

            .select2-container .select2-choice>.select2-chosen{
                color: black;
            }
        </style>

        <jsp:include page="EditionModal.jsp"/>      
        <jsp:include page="js_declare.jsp"/>
        <jsp:include page="EditionSessionExpire.jsp"/>
        <jsp:include page="remise_facture.jsp"/>   
        <script>
            var url_base = "${url_base}";
            var idModule = "${idModule}";
            var url_base_access = "${url_base_access}";
            var currencyScale = ${currencyScale};
            var url_base_fichier_base = "${url_base_fichier_base}";
            var numRecept = "${numRecept}";
        </script>        
        <script src="js/backbone_backgrid/underscore-min.js"></script>        
        <script src="body_page_js/otherfunction.js?version=<%=date%>"></script>
        <script src="body_page_js/BackGridEditor.js?version=<%=date%>"></script> 
        <script src="body_page_js/ListePatient.js?version=<%=date%>"></script> 
        <script src="body_page_js/ListePatient_function.js?version=<%=date%>"></script> 
        <script src="js/plugin/other-plugin/summernote.min.js?version=<%=date%>"></script> 
    </body>
</html>
