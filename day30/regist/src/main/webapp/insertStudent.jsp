<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/13
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>InsertStudent</title>

</head>
<body>
    <form action="/insertStudent" method="post">
        <p>
            name<input type="text" name="name">
        </p>
        <p>
            birthday<input type="date" name="birthday" id="birthday">
        </p>
        <p>
            sex<input type="radio" value="男" name="sex" checked>男<input type="radio" value="女" name="sex">女
        </p>
        <p>
            <input type="submit" value="submit">
        </p>
    </form>
</body>
</html>
