<%response.setContentType("text/html;charset=UTF-8"); %>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="db.ManageUser"%>
<%if (request.getParameter("logout") != null) {
   session.invalidate();
   response.sendRedirect("index.jsp");
   return;
}
ManageUser data = new ManageUser(request.getUserPrincipal().getName());%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="http://code.ionicframework.com/ionicons/2.0.0/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <link href="../dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <link href="../dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
		.content-wrapper {margin-left:0px;}
		.main-header>.navbar {margin-left: 0;}
		.navbar-nav>.user-menu>.dropdown-menu {width: auto;}
    </style>
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="skin-blue">
    <div class="wrapper">
      <header class="main-header">
        <nav class="navbar navbar-static-top" role="navigation">
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <img src="../dist/img/user2-160x160.jpg" class="user-image" alt="User Image"/>
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
      <div class="content-wrapper">
        <section class="content-header">
          <h1>
            Dashboard
            <small> &rsaquo; User data</small>
          </h1>
        </section>

        <section class="content">
        
        
        <div class="col-md-6 col-xs-offset-3">
              <div class="small-box bg-yellow">
                <div class="inner">
                  <h3>User profile</h3><br/>
                  <p>Name: <%=data.getUserData().getFirstName() %></p>
                  <p>Surname: <%=data.getUserData().getLastName() %></p>
                  <p>Email: <%=data.getUserData().getUser() %></p>
                  <p>Phone: <%=data.getUserData().getPhone()!=null?data.getUserData().getPhone():"" %></p>
                  <p>Company: <%=data.getUserData().getCompany()!=null?data.getUserData().getCompany():"" %></p>
                </div>
                <div class="icon">
                  <i class="ion ion-person"></i>
                </div>
                <a href="#" class="small-box-footer">
                  Edit personal data <i class="fa fa-arrow-circle-right"></i>
                </a>
              </div>
            </div>
        </section>
      </div>
  </div>

    <script src="../plugins/jQuery/jQuery-2.1.3.min.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <script src='../plugins/fastclick/fastclick.min.js'></script>
    <script src="../dist/js/app.min.js" type="text/javascript"></script>
  </body>
</html>