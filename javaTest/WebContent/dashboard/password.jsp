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
<html><!-- InstanceBegin template="/Templates/template.dwt.jsp" codeOutsideHTMLIsLocked="false" -->
  <head>
    <meta charset="UTF-8">
    <!-- InstanceBeginEditable name="doctitle" -->
    <title>Dashboard</title>
    <!-- InstanceEndEditable -->
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
  <!-- InstanceBeginEditable name="head" -->
  <script src="../plugins/jQuery/jquery.validate.min.js" type="text/javascript"></script>
  <script src="../plugins/jQuery/additional-methods.min.js" type="text/javascript"></script>
  <script src="../js/pass.js" type="text/javascript"></script>
  <script src="../js/extra-validate-methods.js" type="text/javascript"></script>
  <!-- InstanceEndEditable -->
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
                      <a href="index.jsp?logout=true" class="btn btn-default btn-flat">Sign out</a>
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
            <!-- InstanceBeginEditable name="menu" -->
            <li><a href="index.jsp"><span>Profile</span></a></li>
            <li class="active"><a href="password.jsp"><span>Change password</span></a></li>
            <li><a href="photo.jsp"><span>Photo</span></a></li>
            <!-- InstanceEndEditable -->
          </ul>
        </section>
      </aside>
      <div class="content-wrapper">
        <section class="content-header">
          <h1>
            Dashboard
            <small> &rsaquo; <!-- InstanceBeginEditable name="path" -->Change password<!-- InstanceEndEditable --></small>
          </h1>
        </section>
        <section class="content">
        <!-- InstanceBeginEditable name="mainContent" -->
        <div class="col-md-6 col-md-offset-3">
              <div class="small-box bg-yellow">
                <div class="inner">
                  <h3>User password</h3><br/>
                  <div id="formID">
                  <form id="form" role="form" method="post" action="">
                  <div class="row form-group">
                  		Password*:<br/>
	                	<input class="form-control" name="pass" type="password" value="<% %>">
	                	<label id="" class="" for="pass"></label>
                  </div>
                  <div class="row form-group space">
                      New password*:<br/>
                        <input class="form-control" id="newPass" name="newPass" type="password" value="<% %>">
                        <label id="" class="" for="newPass"></label>
                  </div>
                  <div class="row form-group space">
                      Reenter new password*:<br/>
                        <input class="form-control" name="reenteredNewPass" type="password" value="<% %>">
                        <label id="" class="" for="reenteredNewPass"></label>
                  </div>
                  
                  <div class="row space">
                  <div class="col-md-9 col-md-offset-3">
                  <button class="btn btn-block btn-warning btn-md">Submit</button>
                  </div>
                  </div>
                  </form>
                  <div id="form-alert" class="alert-warning" style="text-align:center;"></div>
                </div>
               
                </div>
                <div class="icon">
                  <i class="ion ion-person"></i>
                </div>
              </div>
            </div>
        <!-- InstanceEndEditable -->
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
<!-- InstanceEnd --></html>