<%@ page import="java.util.List" %>
<%@ page import="org.megacity.cab_service.model.UserAccount" %><%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 16/02/2025
  Time: 06:26 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  List<UserAccount> drivers = (List<UserAccount>) request.getAttribute("drivers");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="dashboard_navigator.jsp"%>
  <div class="content">
    <h2>Driver Management</h2>

    <a href="drivers?action=add">
      <button class="add-driver-btn">+ Add Driver</button>
    </a>

    <input type="text" id="searchDriver" placeholder="Search by Name, NIC or License..." onkeyup="filterTable()">

    <table id="driverTable">
      <thead>
      <tr>
        <th>Driver Name</th>
        <th>NIC No.</th>
        <th>License No.</th>
        <th>Phone Number</th>
        <th>Created At</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <%
        if(drivers == null){
      %>
      <tr>There is no Drivers Available</tr>
      <%
        }else{
          for (UserAccount driver: drivers){
      %>
      <tr>
        <td><%= driver.getFirstname() + " " + driver.getLastname()%></td>
        <td><%= driver.getDriverLicense()%></td>
        <td><%= driver.getNic()%></td>
        <td><%= driver.getContactNumber()%></td>
        <td><%= driver.getCreatedAt()%></td>
        <td>
          <button class="edit-btn">Edit</button>
          <button class="delete-btn">Delete</button>
        </td>
      </tr>
      <%
        }}
      %>
      </tbody>
    </table>
  </div>

  <script>
    function filterTable() {
      let input = document.getElementById("searchDriver").value.toLowerCase();
      let rows = document.querySelectorAll("#driverTable tbody tr");

      rows.forEach(row => {
        let name = row.cells[0].textContent.toLowerCase();
        let nic = row.cells[1].textContent.toLowerCase();
        let license = row.cells[2].textContent.toLowerCase();
        row.style.display = name.includes(input) || license.includes(input) || nic.includes(input) ? "" : "none";
      });
    }
  </script>
</body>

<style>

  /* Heading */
  h2 {
    color: #333;
    font-size: 28px;
    margin-bottom: 20px;
  }

  .add-driver-btn {
    background-color: #ffcc00;
    color: #333;
    padding: 10px 15px;
    font-size: 16px;
    border: none;
    cursor: pointer;
    border-radius: 5px;
    transition: 0.3s;
  }

  .add-driver-btn:hover {
    background-color: #333;
    color: #ffcc00;
  }

  /* Search Bar */
  #searchDriver {
    width: 100%;
    padding: 10px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-bottom: 10px;
    margin-top: 20px;
  }

  /* Table */
  table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 10px;
  }

  th, td {
    padding: 12px;
    text-align: left;
    border-bottom: 1px solid #ddd;
  }

  th {
    background: #ffcc00;
    color: #333;
    font-weight: bold;
  }

  /* Buttons */
  button {
    padding: 6px 12px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 14px;
    margin-right: 5px;
  }

  .edit-btn {
    background: #28a745;
    color: white;
  }

  .delete-btn {
    background: #dc3545;
    color: white;
  }

  button:hover {
    opacity: 0.8;
  }

  /* Hover Effect */
  tr:hover {
    background: #f1f1f1;
  }

  /* Responsive Design */
  @media (max-width: 768px) {
    th, td {
      padding: 8px;
    }

    button {
      padding: 5px 10px;
      font-size: 12px;
    }
  }
</style>
</html>
