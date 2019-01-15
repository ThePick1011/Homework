<%@ page import="domain.Student" %><%--
  Created by IntelliJ IDEA.
  User: Trash
  Date: 2019/1/15
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>UpdateStudent</title>
</head>
<body>

    <form action="/updateStudent" method="post">
        <p>
            学号<input type="text" disabled  value="${student.sid}">
            <input type="text"  hidden name="sid" value="${student.sid}">

        </p>
        <p>
            姓名<input type="text" name="name" value="${student.sname}">
        </p>
        <p>
            出生日期<input type="date" name="birthday" id="birthday" value="${student.birthday}">
        </p>
        <p>
            性别<input type="radio" value="男" name="sex" checked>男<input type="radio" value="女" name="sex">女
        </p>
        <p><input type="submit" value="submit"></p>
    </form>
</body>
</html>
