/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.11
 * Generated at: 2023-02-08 09:19:24 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.body_005fpage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class js_005fdeclare_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<script src=\"js/jquery/jquery.min.js\"></script>\n");
      out.write("<script src=\"js/libs/jquery-2.1.1.min.js\"></script>\n");
      out.write("<script src=\"js/libs/jquery-ui-1.10.3.min.js\"></script>\n");
      out.write("<script src=\"js/jquery/jquery-ui.min.js\"></script>\n");
      out.write("<script src=\"js/royal_tab.min.js\"></script>\n");
      out.write("<script src=\"js/api_Royal_Tab.js\" type=\"text/javascript\"></script>\n");
      out.write("<!--<script src=\"js/javascript.js\"></script>-->\n");
      out.write("<!--<script src=\"js/jquery.treetable.js\"></script>-->\n");
      out.write("<script type=\"text/javascript\" src=\"js/jqxcore.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/jqxdata.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/jqxbuttons.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/jqxscrollbar.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/jqxdatatable.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/jqxtreegrid.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/jqxlistbox.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/jqxdropdownlist.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/jqxcheckbox.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/jqxinput.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/jqxslider.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/demos.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/generatedata.js\"></script>\n");
      out.write("<!-- Demo purpose only --> \n");
      out.write("<!--backbone and friends -->\n");
      out.write("<script src=\"js/backbone_backgrid/backbone_setting.js\"></script>\n");
      out.write("<script src=\"js/backbone_backgrid/underscore-min.js\"></script>\n");
      out.write("<script src=\"js/backbone_backgrid/backbone-min.js\"></script>\n");
      out.write("<script src=\"js/backbone_backgrid/backbone-pageable.js\"></script>\n");
      out.write("<script src=\"js/backbone_backgrid/backgrid.js\"></script>\n");
      out.write("<script src=\"js/backbone_backgrid/backgrid-paginator.js\"></script>\n");
      out.write("<script src=\"js/backbone_backgrid/backgrid-filter.js\"></script>\n");
      out.write("<script src=\"js/backbone_backgrid/backgrid-select2-cell.js\"></script>\n");
      out.write("<script src=\"js/backbone_backgrid/backgrid-grouped-columns.js\"></script>\n");
      out.write("<script src=\"js/backbone_backgrid/backgrid-selecttree-cell.js\"></script> \n");
      out.write("<script src=\"master_page/index.js\"></script>\n");
      out.write("<script src=\"js/helper_functions.js\"></script> \n");
      out.write("<!-- JS TOUCH : include this plugin for mobile drag / drop touch events-->\n");
      out.write("<script src=\"js/plugin/jquery-plugin/jquery.ui.touch-punch.min.js\"></script>   \n");
      out.write("<!-- BOOTSTRAP JS -->\n");
      out.write("<script src=\"js/bootstrap/bootstrap.min.js\"></script> \n");
      out.write("<!-- BOOTSTRAP PLUGIN JS -->\n");
      out.write("<script src=\"js/plugin/bootstrap-plugin/bootstrap-timepicker.min.js\"></script> \n");
      out.write("\n");
      out.write("<script src=\"js/plugin/bootstrap-plugin/bootstrap-tagsinput.min.js\"></script> \n");
      out.write("<!-- CUSTOM NOTIFICATION -->\n");
      out.write("<script src=\"js/notification/SmartNotification.min.js\"></script> \n");
      out.write("<!-- JARVIS WIDGETS -->\n");
      out.write("<script src=\"js/plugin/other-plugin/jarvis.widget.min.js\"></script> \n");
      out.write("<!-- EASY PIE CHARTS -->\n");
      out.write("<script src=\"js/plugin/jquery-plugin/jquery.easy-pie-chart.min.js\"></script> \n");
      out.write("<!-- SPARKLINES -->\n");
      out.write("<script src=\"js/plugin/jquery-plugin/jquery.sparkline.min.js\"></script> \n");
      out.write("<!-- JQUERY VALIDATE -->\n");
      out.write("<script src=\"js/plugin/jquery-plugin/jquery.validate.min.js\"></script> \n");
      out.write("<!-- JQUERY MASKED INPUT -->\n");
      out.write("<script src=\"js/plugin/jquery-plugin/jquery.maskedinput.min.js\"></script> \n");
      out.write("<!-- JQUERY MASKED INPUT -->\n");
      out.write("<script src=\"js/plugin/jquery-plugin/jquery-effect.js\"></script>\n");
      out.write("<!-- JQUERY SELECT2 INPUT -->\n");
      out.write("<script src=\"js/plugin/other-plugin/select2.js\"></script> \n");
      out.write("<!-- JQUERY UI + Bootstrap Slider \n");
      out.write("<script src=\"js/plugin/bootstrap-plugin/bootstrap-slider.min.js\"></script> --> \n");
      out.write("<!-- browser msie issue fix -->\n");
      out.write("<script src=\"js/plugin/jquery-plugin/jquery.mb.browser.min.js\"></script> \n");
      out.write("<!-- FastClick: For mobile devices: you can disable this in app.js -->\n");
      out.write("<script src=\"js/plugin/other-plugin/fastclick.min.js\"></script>  \n");
      out.write("<script src=\"js/demo.min.js\"></script> \n");
      out.write("<script src=\"js/app.config.js\"></script>\n");
      out.write("<script src=\"js/idle-timer.min.js\"></script> \n");
      out.write("<script src=\"js/app.min.js\"></script>\n");
      out.write("<script src=\"js/plugin/other-plugin/highlight.effect.js\"></script>\n");
      out.write("<script src=\"js/plugin/other-plugin/voicecommand.min.js\"></script>\n");
      out.write("<script src=\"js/plugin/bootstrap-plugin/bootstrap-contextmenu.js\"></script>\n");
      out.write("<script src=\"js/jquery.mask.min.js\"></script>\n");
      out.write("<script src=\"js/jquery-loader.js\"></script>\n");
      out.write("<script src=\"js/datepickerI18n/datepicker-ar.js\"></script>\n");
      out.write("<script src=\"js/datepickerI18n/datepicker-en.js\"></script>\n");
      out.write("<script src=\"js/datepickerI18n/datepicker-fr.js\"></script>\n");
      out.write("<script src=\"js/bootstrap-multiselect.js\"></script>\n");
      out.write("<script src=\"js/excelexportjs.js\"></script>\n");
      out.write("<script src=\"js/bootstrap/bootstrap-treeview.min.js\" type=\"text/javascript\"></script>\n");
      out.write("<script src=\"js/plugin/datatables/jquery.dataTables.min.js\"></script>\n");
      out.write("<script src=\"js/plugin/datatables/dataTables.colVis.min.js\"></script>\n");
      out.write("<script src=\"js/plugin/datatables/dataTables.tableTools.min.js\"></script>\n");
      out.write("<script src=\"js/plugin/datatables/dataTables.bootstrap.min.js\"></script>\n");
      out.write("<script src=\"js/plugin/datatable-responsive/datatables.responsive.min.js\"></script>  ");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
