<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/12
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
    <form action="servlet1" method="post">
        <p>name<input type="text" name="name" value="name"></p>
        <p>username<input type="text" name="username" value="username"></p>
        <p>password<input type="password" name="password" value="password"></p>

        <p><input type="submit" value="submit"></p>
        <p><input type="reset" value="reset"></p>
    </form>
</body>
</html>
