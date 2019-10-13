<%--
  Created by IntelliJ IDEA.
  User: srima_000
  Date: 10/11/2019
  Time: 6:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  $END$
  </body>
<script>
  xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function () {
    if(this.status === 401){
      window.location = "/LogInPage.html";
    }
    if(this.status === 200 && this.readyState ===4 ){
      window.location = "/Welcome";
    }
  }
  xhr.open("GET","http://localhost:8010/LogInServlet");
  xhr.send();
</script>
</html>
