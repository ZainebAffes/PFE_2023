/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.11
 * Generated at: 2023-02-03 14:58:32 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.body_005fpage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class EditionSessionExpire_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!DOCTYPE html>\n");
      out.write("<div class=\"modal fade\" id=\"sessionExpirationConfirm\" tabindex=\"-1\" role=\"dialog\" aria-hidden=\"true\" data-backdrop=\"static\" data-keyboard=\"false\" style=\"display: none; margin-top: 10%;\">\n");
      out.write("    <div class=\"modal-dialog centre_screen\" style=\"width: 700px;\">\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("            <div class=\"modal-header\" style=\"color: #1293b8;\">\n");
      out.write("                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\"><i class=\"fa fa-times\"></i></button>\n");
      out.write("                <h4 style=\"\" class=\"modal-title\">\n");
      out.write("                    <i class=\"glyphicon glyphicon-log-out\"></i> \n");
      out.write("                    <span id=\"\">Expiration de session</span>\n");
      out.write("                </h4>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"popup_sessionExpiration\" class=\"modal-body\">\n");
      out.write("                <label>Votre session a expiré! Pour des raisons de sécurité, vous serez automatiquement déconnecté après 120 minutes</label>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-footer\" style=\"padding: 1px 20px 4px;\">\n");
      out.write("                <div class=\"row\"><br>\n");
      out.write("                    <div style=\"padding: 1px 20px 4px;\" align=\"right\">\n");
      out.write("                        <button id=\"submitSessionExpiration\" type=\"button\" class=\"btn btn-default\"><i class=\"fas fa-sign-in-alt\"></i>&nbsp; Rester connecté</button>\n");
      out.write("                        <button id=\"cancelSessionExpiration\" type=\"button\" class=\"btn btn-default\"><i class=\"fas fa-sign-out-alt\"></i>&nbsp; Se déconnecter</button>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div> \n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("<div class=\"modal fade\" id=\"authentification\" tabindex=\"-1\" role=\"dialog\" aria-hidden=\"true\" data-backdrop=\"static\" data-keyboard=\"false\" style=\"display: none; margin-top: 10%;\">\n");
      out.write("    <div class=\"modal-dialog centre_screen\" style=\"width: 700px;\">\n");
      out.write("        <div class=\"modal-content\">\n");
      out.write("            <div class=\"modal-header\" style=\"color: #1293b8;\">\n");
      out.write("                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\"><i class=\"fa fa-times\"></i></button>\n");
      out.write("                <h4 style=\"\" class=\"modal-title\">\n");
      out.write("                    <span id=\"\">Connexion</span>\n");
      out.write("                </h4>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-body\">\n");
      out.write("                <div class=\"row   form-group\" >\n");
      out.write("                    <div class=\"form-group col-md-6\">\n");
      out.write("                        <label class=\"col-md-4 control-label\"> Nom d'utilisateur</label>\n");
      out.write("                        <div class=\"col-md-6 input-group\">\n");
      out.write("                            <input id=\"username\"  type=\"text\"\n");
      out.write("                                   name=\"email\">\n");
      out.write("                        </div>\n");
      out.write("                    </div> \n");
      out.write("                    <div class=\"form-group col-md-6\" >\n");
      out.write("                        <label class=\"col-md-4 control-label\" >Mot de passe</label>\n");
      out.write("                        <div class=\"col-md-6 input-group\">\n");
      out.write("                            <input id=\"password\"  type=\"password\" name=\"password\">\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"modal-footer\" style=\"padding: 1px 20px 4px;\">\n");
      out.write("                <div class=\"row\"><br>\n");
      out.write("                    <div style=\"padding: 1px 20px 4px;\" align=\"right\">\n");
      out.write("                        <button id=\"validerAuthentification\" type=\"button\" class=\"btn btn-default\"><i class=\"fas fa-sign-in-alt\"></i>   <spring:message code=\"button.submit\"/></button>\n");
      out.write("                        <button id=\"cancelAuthentification\" type=\"button\" class=\"btn btn-default\"><i class=\"fas fa-sign-out-alt\"></i>Fermer</button>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div> \n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
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
