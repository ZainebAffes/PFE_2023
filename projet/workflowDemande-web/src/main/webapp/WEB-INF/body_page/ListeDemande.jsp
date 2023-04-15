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
                width: 30%!important;
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
            drag{
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
            .control-drag{
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



            .container {

                display: flex;
                justify-content: space-between;
                align-items: center;
                height: 546px;
                flex-direction: row-reverse;
                justify-content: flex-end;

            }



            .tags-list {
                display: flex;
                flex-direction: column;
                padding-left: 15%;
                list-style: none;
                width: 50%;
                height: 90%;

            }

            .tag {
                /*              display: inline-block;*/
                border: hidden;

                position: relative;

                padding: 10px;
                margin-bottom: 10px;
                box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
                transition: 0.3s;
                width:100%;
                border-radius: 5px;

            }


            .dropzones {
                width: 80%;
                height: 90%;
                border-style: solid;
            }
            .dropped-tag {
                background-color: transparent;
                border: 1px;
                box-sizing: border-box;
                position: relative;
                font-size: 14px;
                min-width: 0px;
                outline: none;
                width: 100%;
                border-style: solid;
                line-height: 1.42857;
                font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen, Ubuntu, "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
            }
            .dropped-tag:hover{
                color: blue;
            }

            .delete-tag{
                padding: 5px 15px;
                border: none;
                border-radius: 4px;
                color: black;
                cursor: pointer;
                text-align: center;
                position: relative;
                background-color: whitesmoke;
                margin-top: 15px;
                margin-right: 15px;
                margin-left: 70%;
            }

            .delete-tag:hover{
                opacity: 0.7;
                background-color: #cccccc;
            }



            .tag:hover {
                box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
            }

            .etiquettes {
                display: inline-block;
                width: 30%;
                margin-right: 3%;
                margin-bottom: 10px;
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
                            <h2><strong id="etat-header">Gestion des demandes</strong></h2> 
                        </header>
                        <!-- widget div-->
                        <div style="min-height: calc(100vh - 55px);">
                            <div class="col-md-2">
                                <div class="input">
                                    <div class="icon-addon addon-md">
                                        <input class="form-control" id="search" type="text" placeholder="Rechercher...">
                                        <div class="glyphicon glyphicon-search" rel="tooltip" title="" style="padding: 10px 0;"></div>
                                    </div>
                                </div>
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
                        <h4  class="modal-title"><i style="font-size: 20px;" id="modalIcon1" class="glyphicon glyphicon-check"></i> <span id="addlabelTitre">Confirmation d'ajout</span><div id="addLabelFich" style="display: none"> </div></h4>
                    </div>
                    <div id="add_popup_div" class="modal-body">
                        <div id="add_msg"></div>
                        <div class="row">
                            <div  id="_grid_ListRassemblant" style=""> 
                            </div>
                        </div>
                        <div id="add_msg_confirm"></div>
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
        <div class="modal fade screen" id="modalAdd" tabindex="-1" role="dialog" aria-dragledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
            <div class="modal-dialog centre_screen" style="width: 95%;">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 style="font-size: 20px;color: #3276b1;" class="modal-title">
                            <i id="modalIconDemande" class="glyphicon glyphicon-plus"></i> 
                            <span id="labelTitre"></span>
                        </h4>
                    </div>      
                    <!-- widget div-->
                    <div class="modal-body" style="padding-bottom: 7px;height: 600px;">
                        <div class="row" >
                            <div class="col-md-12">

                                <fieldset>
                                    <div class="row">
                                        <div class="form-group col-md-3">
                                            <div class="col-md-4 control-drag">Code<span class="champOblig">*</span></div>
                                            <div class="col-md-6 input-group">
                                                <input maxlength="6" id="code" type="text"  class=" form-control datepicker input-xs"  data-mask-clearifnotmatch="true" >
                                            </div>
                                        </div>

                                        <div class="form-group col-md-3" >
                                            <div class="col-md-4 control-drag">Désignation <span class="champOblig">*</span></div>
                                            <div class="col-md-6 input-group">
                                                <input maxlength="100" id="designation" type="text"  class=" form-control datepicker input-xs"  data-mask-clearifnotmatch="true" >
                                            </div>
                                        </div>

                                        <div class="form-group col-md-6" >
                                            <div class="col-md-4 control-drag">Type de demande <span class="champOblig">*</span></div>
                                            <div class="col-md-6 input-group">
                                                <select id="codeTypeDemande"class=" form-control datepicker input-xs"  data-mask-clearifnotmatch="true">

                                                </select>
                                            </div>
                                        </div></div>




                                </fieldset>
                            </div>
                            <div class="widget-body screen col-md-7">
                                <fieldset>

                                    <div class="container">
                                        <div class="dropzones" id="dropzones">
                                            <div id="zone">

                                            </div>
                                        </div>

                                    </div>
                                </fieldset>

                            </div>
                            <div class="widget-body screen col-md-5">
                                <fieldset>
                                    <div>

                                        <div class="container">
                                            <div class="tags-list">
                                                <h4>Champs</h4>
                                                <div id="divLegendServ"  >
                                                    <legend style="font-size: 0.8em ;">
                                                        <p class="p1">CRÉER UN CHAMP</p>
                                                        <p>
                                                            Faites glisser un type de champ vers l'une des sections de gauche
                                                            afin de créer un champ personnalisé pour ce type de ticket.</p>
                                                    </legend>
                                                </div>  

                                                <div class="etiquettes">
                                                    <div class="tag" id="texte" draggable="true"data-type="text">                                                        
                                                        <span class="glyphicon glyphicon-text-width" ><i> Description courte : </i></span>
                                                       
                                                    </div>
                                                    <div class="tag" id="temps"  draggable="true"data-type="temps">
                                                        <span class="glyphicon glyphicon-time"><i> Temps :</i></span>
                                                    </div>
                                                    <div class="tag" id="date" draggable="true"data-type="date">
                                                        <span class="fa fa-calendar"><i> Date : </i></span>                                                        
                                                    </div>
                                                    <div class="tag" id="nombre"draggable="true"data-type="nombre">
                                                        <i> Nombre :</i>
                                                    </div>
                                                    
                                                    <div class="tag" draggable="true"data-type="caseCocher">
                                                        <span class="glyphicon glyphicon-time"><i> Case à cocher :</i></span>
                                                    
                                                    </div>
                                                    
                                                </div>  
                                                <!--                                                <span class="glyphicon glyphicon-text-width"><i> Description courte : </i></span>
                                                                                                <div class="tag"><input draggable="true" type="text"class=" form-control datepicker input-xs"  data-mask-clearifnotmatch="true" placeholder="text"></div>
                                                
                                                                                                <span class="glyphicon glyphicon-time"><i> Temps :</i></span>
                                                                                                <div class="tag"><input draggable="true" type="time"class=" form-control datepicker input-xs"  data-mask-clearifnotmatch="true"></div>
                                                
                                                                                                <span class="fa fa-calendar"><i> Date : </i></span>                                                        
                                                                                                <div class="tag"><input draggable="true" type="date"class=" form-control datepicker input-xs"  data-mask-clearifnotmatch="true"></div>
                                                
                                                                                                <i> Nombre :</i>
                                                                                                <div class="tag" id="nombre"><input draggable="true" type="number"class=" form-control datepicker input-xs"  data-mask-clearifnotmatch="true" placeholder="nombre" >
                                                                                                   
                                                                                                </div>
                                                
                                                                                                <span class="glyphicon glyphicon-align-left"> <i> Paragraphe :</i></span>
                                                
                                                                                                <div class="tag"> <textarea  draggable="true" placeholder="Paragraphe.." class=" form-control datepicker input-xs"  data-mask-clearifnotmatch="true"></textarea></div>
                                                -->
                                            </div>

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
                                <button id="btnMAJDemande" type="button" class="btn btn-default"><i class="fa fa-check"></i>&nbsp; Valider</button>
                                <button id='btnCloseModalDemande' type="button" class="btn btn-default" onClick="$('#modalAdd').modal('hide');"><i class="fa fa-times"></i>&nbsp; Fermer</button>
                            </div>
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
                            <div id="LabelFich" style="display: none"> </div>
                        </h4>
                    </div>
                    <div id="popup_sessionExpiration" class="modal-body">
                        <div>Votre session a expiré! Pour des raisons de sécurité, vous serez automatiquement déconnecté après <span id="SecondsUntilExpire"></span> secondes</div>
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
