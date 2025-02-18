<%@ page import="org.megacity.cab_service.model.VehicleModel" %>
<%@ page import="java.util.List" %>
<%@ page import="org.megacity.cab_service.model.UserAccount" %><%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 14/02/2025
  Time: 01:53 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
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
        <h1>Add Vehicle</h1>

        <form action="vehicles" method="post" class="vehicle-form">

            <label for="vehicleModel">Vehicle Model:</label>
            <select id="vehicleModel" name="vehicleModel" required>
                <option value="" disabled selected>Select Model</option>
                <%
                    if(models != null){
                    for (VehicleModel model : models){
                %>
                <option value='<%= model.getModelId()%>'><%= model.getModelName()%></option>
                <%
                    }}
                %>
            </select>

            <label for="color">Color:</label>
            <input type="text" id="color" name="color" placeholder="Enter color..." required>

            <label for="plateNumber">Plate Number:</label>
            <input type="text" id="plateNumber" name="plateNumber" placeholder="Enter plate number..." required>

            <label for="seatCount">Seat Count:</label>
            <input type="number" id="seatCount" name="seatCount" placeholder="Enter seat count..." required min="1">

            <label for="availability">Available:</label>
            <input type="checkbox" id="availability" name="availability">

            <label for="pricePerKm">Price Per Km (LKR):</label>
            <input type="number" id="pricePerKm" name="pricePerKm" placeholder="Enter price per km..." required min="0" step="0.01">

            <label for="litersPerKm">Liters Per Km:</label>
            <input type="number" id="litersPerKm" name="litersPerKm" placeholder="Enter liters per km..." required min="0" step="0.1">

            <div class="driver-selection">
                <label for="searchDriver">Search Driver:</label>
                <input type="text" id="searchDriver" placeholder="Search by name..." onkeyup="filterTable()">

                <table id="driverTable">
                    <thead>
                    <tr>
                        <th>Select</th>
                        <th>Driver Name</th>
                        <th>License No.</th>
                        <th>NIC</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        if(drivers != null){
                        for (UserAccount driver : drivers){
                    %>
                    <tr>
                        <td><input type="radio" name="driver" value='<%= driver.getId()%>'></td>
                        <td><%= driver.getFirstname() + " " + driver.getLastname()%></td>
                        <td><%= driver.getDriverLicense()%></td>
                        <td><%= driver.getNic()%></td>
                    </tr>
                    <%
                        }}
                    %>

                    </tbody>
                </table>
            </div>

            <button type="submit" class="submit-btn">Add Vehicle</button>
        </form>
    </div>

    <script>
        function filterTable() {
            let input = document.getElementById("searchDriver").value.toLowerCase();
            let rows = document.querySelectorAll("#driverTable tbody tr");

            rows.forEach(row => {
                let driverName = row.cells[1].textContent.toLowerCase();
                row.style.display = driverName.includes(input) ? "" : "none";
            });
        }
    </script>
</body>

<style>
    h1 {
        font-size: 26px;
        margin-bottom: 20px;
        color: #333;
        text-align: center;
    }

    /* Form Styling */
    .vehicle-form {
        display: flex;
        flex-direction: column;
        gap: 12px;
    }

    .vehicle-form label {
        font-weight: bold;
        margin-bottom: 5px;
        color: #333;
    }

    .vehicle-form input,
    .vehicle-form select {
        width: 100%;
        padding: 10px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    /* Checkbox Alignment */
    .vehicle-form input[type="checkbox"] {
        width: auto;
        margin-left: 10px;
    }
    #searchDriver {
        width: 100%;
        padding: 10px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 5px;
        margin-bottom: 10px;
    }

    .driver-selection {
        margin-top: 20px;
        padding: 15px;
        background: #f8f9fa;
        border-radius: 8px;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    }
    table {
        width: 100%;
        border-collapse: collapse;
        background: #fff;
        border-radius: 5px;
        overflow: hidden;
    }

    th, td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
        background: #ffcc00;
        color: #333;
        font-weight: bold;
    }

    td input[type="radio"] {
        transform: scale(1.2);
    }

    tr:hover {
        background: #f1f1f1;
    }

    /* Responsive Design */
    @media (max-width: 768px) {
        th, td {
            padding: 8px;
        }
    }

    /* Submit Button */
    .submit-btn {
        background-color: #ffcc00;
        color: #333;
        padding: 12px;
        font-size: 18px;
        font-weight: bold;
        border: none;
        cursor: pointer;
        border-radius: 5px;
        margin-top: 15px;
        transition: 0.3s;
    }

    .submit-btn:hover {
        background-color: #333;
        color: #ffcc00;
    }

    /* Responsive Design */
    @media (max-width: 768px) {
        .content {
            margin-left: 0;
            width: 90%;
            margin: auto;
        }
    }
</style>

</html>
