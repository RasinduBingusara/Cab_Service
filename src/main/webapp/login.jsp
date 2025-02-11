<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 03/02/2025
  Time: 10:05 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <div>
        <form action="login" method="post">
            <div>
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Email..." required>
            </div>
            <div>
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Password..." required>
            </div>
            <label>
                <%=
                    request.getParameter("error") != null? request.getParameter("error"):""
                %>
            </label>
            <p>Don't have an account?<a href="signup.jsp">Create Account</a></p>
            <input type="submit" value="Sign In">
        </form>
    </div>
</body>
</html>
