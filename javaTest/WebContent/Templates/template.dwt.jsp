<%response.setContentType("text/html;charset=UTF-8"); %>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="db.ManageUser"%>
<%@ page import="java.util.Base64"%>
<%if (request.getParameter("logout") != null) {
   session.invalidate();
   response.sendRedirect("index.jsp");
   return;
}
ManageUser data = new ManageUser(request.getUserPrincipal().getName());%>
<%String imgEncode = null;
if(data.getUserData().getPhoto()!=null){
	imgEncode = Base64.getEncoder().encodeToString(data.getUserData().getPhoto());
}%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <!-- TemplateBeginEditable name="doctitle" -->
    <title>Dashboard</title>
    <!-- TemplateEndEditable -->
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="http://code.ionicframework.com/ionicons/2.0.0/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <link href="../dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <link href="../dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
    <script src="../plugins/jQuery/jQuery-2.1.3.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../dist/js/app.min.js" type="text/javascript"></script>
    <style type="text/css">
		.navbar-nav>.user-menu>.dropdown-menu {width: auto;}
		.small-box{padding:40px;}
		.space{margin-top: 5px;}
		.btn-warning {background-color: #ce840f;}
		.alert-success{padding:40px;}
    </style>
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  <!-- TemplateBeginEditable name="head" -->
  <!-- TemplateEndEditable -->
  </head>
  <body class="skin-blue">
    <div class="wrapper">
      <header class="main-header">
      <a href="#" class="logo"><b>Dashboard</b></a>
        <nav class="navbar navbar-static-top" role="navigation">
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <img src="<%=imgEncode!=null?"data:image/jpeg;base64," + imgEncode:"../imgs/user.png"%>" class="user-image" alt="User Image"/>
                  <span class="hidden-xs"><%=data.getUserData().getFirstName()%> <%=data.getUserData().getLastName()%></span>
                </a>
                <ul class="dropdown-menu">
                  <li class="user-footer">
                    <div align="center">
                      <a href="../dashboard/index.jsp?logout=true" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </nav>
      </header>
      <aside class="main-sidebar">
        <section class="sidebar">
          <ul class="sidebar-menu">
            <li class="header"></li>
            <!-- TemplateBeginEditable name="menu" -->
            <li class="active"><a href="../dashboard/index.jsp"><span>Profile</span></a></li>
            <li><a href="../dashboard/password.jsp"><span>Change password</span></a></li>
            <li><a href="../dashboard/photo.jsp"><span>Photo</span></a></li>
            <!-- TemplateEndEditable -->
          </ul>
        </section>
      </aside>
      <div class="content-wrapper">
        <section class="content-header">
          <h1>
            Dashboard
            <small> &rsaquo; <!-- TemplateBeginEditable name="path" -->User data<!-- TemplateEndEditable --></small>
          </h1>
        </section>
        <section class="content">
        <!-- TemplateBeginEditable name="mainContent" -->
        
        <!-- TemplateEndEditable -->
        </section>
      </div>
      <footer class="main-footer">
        <div class="pull-right hidden-xs">
          Anything you want
        </div>
        <strong>Copyright &copy; 2015 <a href="#">Company</a>.</strong> All rights reserved.
      </footer>
  </div>
  </body>
</html>