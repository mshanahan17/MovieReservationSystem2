<%--
  Created by IntelliJ IDEA.
  User: Matt
  Date: 1/20/2018
  Time: 10:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
<header style="width: 100%"></header>
<div class="main">
    <div id="regForm">
        <fieldset style="width: 50vw">
            <legend>User Registration:</legend>
            <form action="Registration" method="post">
                <b>First Name:</b><br>
                <input type="text" name="fName"><br>
                <b>Last Name:</b><br>
                <input type="text" name="lName"><br>
                <b>E-mail:</b><br>
                <input type="email" name="email"><br>
                <b>Re-Enter Email:</b><br>
                <input type="email" name="email"><span style="color: red">${emailError}</span><br>
                <b>Password:</b><br>
                <input type="password" name="password"><br>
                <b>Re-Enter Password:</b><br>
                <input type="password" name="password"><span style="color: red">${pwError}</span><br>
                <input type="submit">
            </form>
        </fieldset>
    </div>
</div>
</body>
</html>
