<%@ page import="java.util.List" %>
<%@ page import="org.megacity.cab_service.model.Vehicle" %>
<%@ page import="org.megacity.cab_service.model.UserAccount" %>
<%@ page import="org.megacity.cab_service.model.VehicleModel" %><%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 14/02/2025
  Time: 01:46 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Vehicle> vehicleList = (List<Vehicle>) request.getAttribute("VehicleList");
    List<VehicleModel> models = (List<VehicleModel>) request.getAttribute("vehicleModels");
    List<UserAccount> drivers = (List<UserAccount>) request.getAttribute("drivers");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="dashboard_navigator.jsp"%>

<div class="content">
    <h1>Manage Vehicles</h1>

    <a href="vehicles?action=add">
        <button class="add-vehicle-btn">+ Add Vehicle</button>
    </a>
    <a href="add_model.jsp">
        <button class="add-vehicle-btn">+ Add Model</button>
    </a>

    <table class="vehicle-table">
        <thead>
        <tr>
            <th>Model</th>
            <th>License Plate</th>
            <th>Driver</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            if(vehicleList == null){

        %>

        <tr>
            <td>No Vehicle Available</td>
        </tr>
        <%
        }
        else{
            for (Vehicle vehicle: vehicleList){

        %>
        <tr>
            <td><%
                for (VehicleModel model : models){
                    if(model.getModelId() == vehicle.getModel().getModelId()){
                        out.println(model.getModelName());
                        break;
                    }
                }
            %></td>
            <td><%= vehicle.getPlate_no()%></td>
            <td><%
                for (UserAccount driver : drivers){
                    if(driver.getId() == vehicle.getDriver().getId()){
                        out.println(driver.getFirstname() + " " + driver.getLastname());
                        break;
                    }
                }
            %></td>
            <td class="status active"><%= vehicle.isAvailability()? "Active":"Inactive"%></td>
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
</body>
<style>
    h1 {
        font-size: 28px;
        margin-bottom: 20px;
    }

    .add-vehicle-btn {
        background-color: #ffcc00;
        color: #333;
        padding: 10px 15px;
        font-size: 16px;
        border: none;
        cursor: pointer;
        border-radius: 5px;
        transition: 0.3s;
    }

    .add-vehicle-btn:hover {
        background-color: #333;
        color: #ffcc00;
    }

    .vehicle-table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    .vehicle-table thead {
        background-color: #333;
        color: white;
    }

    .vehicle-table th,
    .vehicle-table td {
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    .status {
        font-weight: bold;
    }

    .status.active {
        color: green;
    }

    .status.inactive {
        color: red;
    }

    .edit-btn, .delete-btn {
        padding: 6px 12px;
        font-size: 14px;
        border: none;
        cursor: pointer;
        border-radius: 5px;
        transition: 0.3s;
    }

    .edit-btn {
        background-color: #007bff;
        color: white;
    }

    .delete-btn {
        background-color: #dc3545;
        color: white;
    }

    .edit-btn:hover {
        background-color: #0056b3;
    }

    .delete-btn:hover {
        background-color: #a71d2a;
    }
</style>
</html>