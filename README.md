# Java Test

This is a test web application (back end: Java 8, Tomcat 9, MySQL 5.7) that implements a basic mechanism for identifying / managing user accounts. Front end implemented using bootstrap framework ([AdminLTE-2.0.3 template](https://adminlte.io/themes/AdminLTE/index.html)), jQuery (form validation, ajax), CSS3 and HTML5.

## Features

1. Register page (**name**, **surname**, **email**, phone, company, **password**, **reenter password**, **google reCaptcha**)
2. Profile page (display/edit profile data)
3. Upload photo
4. Change password
5. Login page (email, password)

## Database management

- Hibernate framework for execution of queries (see [hibernate.cfg.xml](https://github.com/jnkefala/javatest/blob/master/javaTest/src/hibernate.cfg.xml) and [mapping classes](https://github.com/jnkefala/javatest/tree/master/javaTest/src/db/mapping))

- Connection pooling set in Tomcat server.xml file (MySQL connector jar is placed in Tomcat lib folder):

  `<Resource auth="Container" description="Test Database" driverClassName="com.mysql.jdbc.Driver" maxActive="14" maxIdle="7" minEvictableIdleTimeMillis="14400000" name="jdbc/user_db" numTestsPerEviction="-1" password="dbpass" timeBetweenEvictionRunsMillis="4800000" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/user_db?autoReconnect=true&amp;characterEncoding=utf8" username="dbusr"/>`

- Form authentication set in [web.xml](https://github.com/jnkefala/javatest/blob/master/javaTest/WebContent/WEB-INF/web.xml) and [content.xml](https://github.com/jnkefala/javatest/blob/master/javaTest/WebContent/META-INF/context.xml) according to database tables and columns (see [sql script](https://github.com/jnkefala/javatest/blob/master/javaTest/WebContent/javaTest.sql))

## Demo

A demo video file is presented in this [link](https://github.com/jnkefala/javatest/blob/master/javaTest/WebContent/demo.wmv)
