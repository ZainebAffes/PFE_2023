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
        
       <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/amcharts/3.21.15/plugins/export/libs/FileSaver.js/FileSaver.min.js"></script>
             <script type="text/javascript" src="https://unpkg.com/exceljs@4.3.0/dist/exceljs.min.js"></script>
              <script type="text/javascript" >
                  console.log('loadet');
              </script>

        <style>
            .jarvismetro-tile .iconbox span {
                display: block;
            }
            .select_depot .select2-results .select2-result-label{
                padding:0;
            }
            label{
                font-size: 12px;
                font-weight: 300;
            }
            #btnContainer {
                padding: 0;
                border: none;
                display: flex;
                flex-wrap: wrap;
                align-items: center;
                justify-content: center;
                align-content: center;
                font-size: 20px;
                margin: 5px;
                margin-top: 60px;
            }
            .btnSousMenu{
                background-color:white;
                width: 245px;
                height: 90px;

            }
            .btnSousMenu:hover{
                color: gray;
            }

            .btnSousMenu .fa{
                font-size: 25px;
                line-height: 40px;
                width: 45px;
                padding-left: 15px;
                padding-right: 15px;
                color: #1293b8;

            }
            #btnContainer .menuS{
                display: flex;
                height: 100%;
                align-items: center;
            }

            #btnContainer .designation{
                font-family: Cairo !important;
                margin-left: 5px;
                font-size: 16px;
                text-align: initial;
                padding: 10px;
                padding-right: 24px;

            }
            .module {
                padding: 50px 100px 0px 100px;
                display: flex;
                position: fixed;
                color: #8888888c;
                z-index: 99999;
            }
            .module a p {
                font-size: 22px;
            }

            .module a p:hover {
                text-decoration: underline;
            }
            .module>a {
                display: flex;
                height: 100%;
                color: #999;
            }
            .module>a+a:before {
                content: "/\00a0";
                padding: 0 5px;
                color: #8888888c;
                font-size: 22px;
            }
            /*            font-family: Cairo !important;*/

        </style>
    </head> 


    <body class="styleCsys"  id="my_body" >
        <section id="widget-grid" class="">
            <div class="row">
                <article>
                    <div class="module">
                        <a parent="principal"><i class="far fa-folder-open fa-2x"></i><p><spring:message code="Liste des demandes"/></p></a>

                    </div>

                    <div id="btnContainer" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <ul>


                        </ul>
                    </div>      
                </article>
            </div>
            <!-- end widget div -->




        </section> 


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
        <script src="body_page_js/Demande.js?version=<%=date%>"></script>
        <script src="body_page_js/Demande_function.js?version=<%=date%>"></script>
    </body>
</html>
