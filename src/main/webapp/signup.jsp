<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 03/02/2025
  Time: 10:06 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
    <div>
        <form action="signup" method="post">
           <div>
               <div>
                   <label for="firstname">First Name</label>
                   <input type="text" id="firstname" name="firstname" placeholder="First Name..."
                          value='<%= request.getParameter("firstname") != null ? request.getParameter("firstname"):""%>' required>
               </div>
               <div>
                   <label for="lastname">Last Name</label>
                   <input type="text" id="lastname" name="lastname" placeholder="Last Name..."
                          value='<%= request.getParameter("lastname") != null ? request.getParameter("lastname"):""%>' required>
               </div>
           </div>
            <div>
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Email..."
                       value='<%= request.getParameter("email") != null ? request.getParameter("email"):""%>' required>
            </div>
            <div>
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Password..." required>
            </div>
            <div>
                <label for="conPassword">Confirm Password</label>
                <input type="password" id="conPassword" name="confirmpassword" placeholder="Confirm Password..." required>
            </div>
            <div>
                <label for="contactnumber">Contact Number</label>
                <input type="number" id="contactnumber" name="contactnumber" placeholder="Contact Number..."
                       value='<%= request.getParameter("contactnumber") != null ? request.getParameter("contactnumber"):""%>' required>
            </div>
            <label>
                <%=
                request.getParameter("error") != null? request.getParameter("error"):""
                %>
            </label>
            <p>already have an account?<a href="login.jsp">login</a></p>
            <input type="submit" value="Sign Up">
        </form>
    </div>
</body>
</html>
