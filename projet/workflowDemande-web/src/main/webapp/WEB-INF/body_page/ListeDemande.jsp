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

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../body_page/css_declare.jsp"/>   
        <title>Demandes</title>
        <style>

            tbody > tr td{
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
            }
            .jarviswidget .widget-body {
                min-height: 0px;
            }
            .widget-body{
                z-index: 0;
            }
            #_grid_ListDemande thead > tr th:nth-child(1),
            #_grid_ListDemande tbody > tr td:nth-child(1){
                width: 20%!important;
            }
            #_grid_ListDemande thead > tr th:nth-child(2),
            #_grid_ListDemande tbody > tr td:nth-child(2){
                width: 20%!important;
            }
            #_grid_ListDemande thead > tr th:nth-child(3),
            #_grid_ListDemande tbody > tr td:nth-child(3){
                width: 20%!important;

            }
            #_grid_ListDemande thead > tr th:nth-child(4),
            #_grid_ListDemande tbody > tr td:nth-child(4){
                width: 20%!important;
            }

            #_grid_ListDemande thead > tr th:nth-child(5),
            #_grid_ListDemande tbody > tr td:nth-child(5){
                width: 10%!important;
            }
            #_grid_ListDemande thead > tr th:nth-child(6),
            #_grid_ListDemande tbody > tr td:nth-child(6){
                width: 10%!important;
            }
            #_grid_ListDemande  tbody {
                flex: 1 1 auto;
                width: 100%;
                display: block;
                overflow-y: scroll;
                overflow-x: hidden;
                min-height: 20px;
                max-height: calc(100vh - 222px);
                height:auto !important;
            }
            #_grid_ListDemande {
                display: flex;
                flex-flow: column;
                height: 100%;
                width: 100%;
            }
            #_grid_ListDemande thead, #_grid_ListDemande tbody tr {
                display: table;
                table-layout: fixed;
            }
            #_grid_ListDemande thead {
                width: calc(100% -  6px) !important;
                flex: 0 0 auto;
            }
            #_grid_ListDemande tbody tr {
                width: 100%;
            }

            #_grid_ListDemande tbody > tr > td,
            #_grid_ListDemande  tbody > tr > th,
            #_grid_ListDemande  tfoot > tr > td,
            #_grid_ListDemande  tfoot > tr > th,
            #_grid_ListDemande  thead > tr > td,
            #_grid_ListDemande  thead > tr > th {
                padding: 3px 5px!important;
                font-size: 12px;
            }


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
                font-size: 11px;
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

            .form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control {
                background-color: #fff;
            }
            .control-label{
                top: 5px;
            }
            .hover {
                background: #eee;
            }
            .selectionne{
                background-color: #BAD2E4;
            }
            .btn-group,  .btn-group-vertical {
                position: relative;
                display: block;
                vertical-align: middle;
            }
            #myTab1 li:not(.active) a{
                color :#000 !important;
            }
            .champOblig{
                color: red;
            }
            /*             #myTab1 li:not(.active) a {
                            color :#000;
                        }*/

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


            #filter_Demande{
                z-index: 0 !important;
            }
            td {
                vertical-align: middle !important;
            }
            .bootstrap-tagsinput input {
                width: 28em;
            }
            #_grid_ListDemande_filter{
                margin-left: -1132px;

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
                width: calc(33.33% - 8px);
            }
            #tableListRassemblant  thead > tr th:nth-child(1),
            #tableListRassemblant  tbody > tr td:nth-child(1){
                width: 30%!important;
            }
            #tableListRassemblant  thead > tr th:nth-child(2),
            #tableListRassemblant  tbody > tr td:nth-child(2){
                width: 70%!important;
            }
            #tableListRassemblant  tbody {
                flex: 1 1 auto;
                width: 100%;
                display: block;
                overflow-y: scroll;
                overflow-x: hidden;
                min-height: 20px;
                max-height: 108px;
                height:auto !important;
            }
            #tableListRassemblant {
                /* display: flex;*/
                flex-flow: column;
                height: 100%;
                width: 100%;
            }
            #tableListRassemblant  thead, #tableListRassemblant  tbody tr {
                display: table;
                table-layout: fixed;
            }
            #tableListRassemblant  thead {
                /* head takes the height it requires, 
                and it's not scaled when table is resized */
                width: calc(100% - 6px ) !important;
                flex: 0 0 auto;
            }
            #tableListRassemblant  tbody tr {
                width: 100%;
            }

            #tableListRassemblant > tbody > tr > td,
            #tableListRassemblant > tbody > tr > th,
            #tableListRassemblant > tfoot > tr > td,
            #tableListRassemblant > tfoot > tr > th,
            #tableListRassemblant > thead > tr > td,
            #tableListRassemblant > thead > tr > th {
                padding: 5px;
                font-size: 12px;
            }
        </style>
    </head>
    <body id="my_body" class="styleCsys">
        <section id="widget-grid" class="" >
            <!-- row -->
            <div class="row hidden-print screen">
                <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="padding-right: 2px;">
                    <div class="jarviswidget jarviswidget-color-redLight"  data-widget-editbutton="false" data-widget-deletebutton="false">

                        <header class='screen' id="listetid_Demande">
                            <a class="btn btn-default accessCtrl pull-right" id="btnFermer"> 
                                <span class="widget-icon"><i class="glyphicon btn-danger glyphicon-log-out"></i> <spring:message code="fb.global.fermer"/></span>
                            </a>
                            <a class="btn btn-default  pull-left" id="rafresh" > 
                                <span class="widget-icon"><i class="glyphicon glyphicon-refresh"></i></span>  
                            </a>
                            <h2><strong id="etat-header">Gestion des Demandes</strong></h2> 
                        </header>
                        <!-- widget div-->
                        <div style="min-height: calc(100vh - 55px);">
                            <div class="col-md-9">
                                <label class="input">
                                    <div class="icon-addon addon-md">
                                        <input class="form-control" id="search" type="text" placeholder="Rechercher...">
                                        <label class="glyphicon glyphicon-search" rel="tooltip" title="" style="padding: 10px 0;"></label>
                                    </div>
                                </label>
                            </div>
                            <div class="col-md-3 " style="">
                                <label class="btn btn-default active filtreActif" style="">
                                    <i class="fa fa-search" style="/*font-size: 1.5em;*/"></i>
                                    <span>Tous</span>
                                </label>
                                <label class="btn btn-success filtreActif" style="font-size:12px;">
                                    <i class="fa fa-check-circle" style="/*font-size: 1.5em;*/"></i>
                                    <span>Actif</span>
                                </label>
                                <label class="btn btn-info filtreActif" style="font-size:12px;">
                                    <i class="fa fa-search" style="/*font-size: 1.5em;*/"></i>
                                    <span>Non actif</span>
                                </label>
                            </div>
                            <div class="widget-body screen col-md-12">

                                <div class="row">
                                    <div  id="_grid_ListDemande" >
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </article>
            </div>
        </section>     
        <div class="modal fade screen" id="addConfirm" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog centre_screen" style="width: 40%;">
                <div class="modal-content">
                    <div class="modal-header" style="color: #1293b8;" >
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i></button>
                        <h4  class="modal-title"><i style="font-size: 20px;" id="modalIcon1" class="glyphicon glyphicon-check"></i> <span id="addlabelTitre">Confirmation d'ajout</span><label id="addLabelFich" style="display: none"> </label></h4>
                    </div>
                    <div id="add_popup_div" class="modal-body">
                        <label id="add_msg"></label>
                        <div class="row">
                            <div  id="_grid_ListRassemblant" style=""> 
                            </div>
                        </div>
                        <label id="add_msg_confirm"></label>
                    </div>
                    <div  class="modal-footer" style="padding: 1px 20px 4px;">
                        <div class="row"><br>
                            <div style="padding: 1px 20px 4px;" align="right">
                                <button id="submitAdd" type="button" class="btn btn-default"><i class="fa fa-check"></i>&nbsp; Valider</button>
                                <button type="button" class="btn btn-default" onClick="$('#addConfirm').modal('hide');$('#modalAddSousFamExamen').modal('show');"><i class="fa fa-times"></i>&nbsp; Fermer</button>
                            </div>
                        </div>
                    </div> 
                </div>
            </div>
        </div>
        <div class="modal fade screen" id="modalAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
            <div class="modal-dialog centre_screen" style="width: 60%;">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 style="font-size: 20px;color: #3276b1;" class="modal-title">
                            <i id="modalIconDemande" class="glyphicon glyphicon-plus"></i> 
                            <span id="labelTitre"></span>
                        </h4>
                    </div>      
                    <!-- widget div-->
                    <div id="popup_div" class="modal-body">
                        <div class="row">
                            <div class="widget-body screen col-md-12">
                                <fieldset >
                                    <div class="row">
                                        <div class="form-group col-md-3">
                                            <label class="col-md-4 control-label">Code<span class="champOblig">*</span></label>
                                            <div class="col-md-6 input-group">
                                                <input maxlength="6" id="codDemande" type="text"  class=" form-control datepicker input-xs"  data-mask-clearifnotmatch="true" >
                                            </div>
                                        </div>

                                        <div class="form-group col-md-3" >
                                            <label class="col-md-3 control-label">Désignation<span class="champOblig">*</span></label>
                                            <div class="col-md-8 input-group">
                                                <input draggable = "true"  maxlength="100" id="desDemande" type="text"  class=" form-control datepicker input-xs"  data-mask-clearifnotmatch="true" >
                                            </div>
                                        </div>
                                        <div id="div1" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
                                        <img id="drag1" src="img_logo.gif" draggable="true" ondragstart="drag(event)" width="336" height="69">
                                        <div class="drop-targets">
                                            <div class="box">
                                                <div class="item" id="item" draggable="true">
                                                </div>
                                                <div class="box"></div>
                                                <div class="box"></div>
                                            </div>

                                            <div class="form-group col-md-2" >
                                                <label id="chActif" class="col-md-5 control-label">
                                                    <input id="checkboxActif" type="checkbox" class="checkbox" checked>
                                                    <span style=" font-size: 12px;">Actif</span>
                                                </label>
                                            </div>
                                        </div>
                                </fieldset>
                            </div>
                        </div>

                    </div>
                    <!-- end widget div -->
                    <div  class="modal-footer" style="padding: 1px 20px 4px;">
                        <div class="row">
                            <div align="right">
                                <button id="btnMAJFam" type="button" class="btn btn-default"><i class="fa fa-check"></i>&nbsp; Valider</button>
                                <button id='btnCloseModalFam' type="button" class="btn btn-default" onClick="$('#modalAdd').modal('hide');"><i class="fa fa-times"></i>&nbsp; Fermer</button>
                            </div>
                        </div>
                    </div> 
                </div>
            </div>
        </div>


        <div class="modal fade " id="parametrage" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog centre_screen" style="width: 850px;">
                <div class="modal-content">
                    <div class="modal-header"   >
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i></button>
                        <h4 class="modal-title" style="font-size: 20px;color: #3276b1;">
                            <i id="modalIcon" class="glyphicon glyphicon-trash"></i> 
                            <span id="labelTitre">Paramétrage impression</span>
                            <label id="LabelFich" style="display: none"> </label>
                        </h4>
                    </div>

                    <div id="parametragebody" class="modal-body" style="  overflow-x: auto;overflow-y: auto;max-height: 200px;">
                    </div>

                    <div class="modal-footer" >

                        <button id="btnCloseModalImpr" type="" onClick="$('#parametrage').modal('hide');" class="btn btn-default" style="float:right"><i class="fa fa-times"></i> Fermer</button>
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

                    <div class="modal-header"   >
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i></button>
                        <h4 class="modal-title" style="font-size: 20px;color: #3276b1;">
                            <i id="modalIcon" class="glyphicon glyphicon-trash"></i> 
                            <span id="labelTitre">Expiration de session</span>
                            <label id="LabelFich" style="display: none"> </label>
                        </h4>
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
        <jsp:include page="EditionModal.jsp"/>       
        <jsp:include page="js_declare.jsp"/>
        <jsp:include page="EditionSessionExpire.jsp"/>
        <script>
            var url_base = "${url_base}";
            var idModule = "${idModule}";
            var url_base_access = "${url_base_access}";
            var currencyScale = ${currencyScale};
        </script>        
        <script src="js/backbone_backgrid/underscore-min.js"></script>        
        <script src="body_page_js/otherfunction.js?version=<%=date%>"></script>
        <script src="body_page_js/BackGridEditor.js?version=<%=date%>"></script> 
        <script src="js/plugin/other-plugin/summernote.min.js"></script>    
        <script src="body_page_js/ListeDemande.js?version=<%=date%>"></script>
        <script src="body_page_js/ListeDemande_function.js?version=<%=date%>"></script>
    </body>
</html>
