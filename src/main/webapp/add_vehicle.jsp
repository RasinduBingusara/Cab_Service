<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 14/02/2025
  Time: 01:53 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="dashboard_navigator.jsp"%>

    <div class="content">
        <h1>Add Vehicle</h1>

        <form action="addVehicle" method="post" class="vehicle-form">

            <!-- Vehicle Model -->
            <label for="vehicleModel">Vehicle Model:</label>
            <select id="vehicleModel" name="vehicleModel" required>
                <option value="" disabled selected>Select Model</option>
                <option value="Toyota Prius">Toyota Prius</option>
                <option value="Honda Civic">Honda Civic</option>
                <option value="Ford Explorer">Ford Explorer</option>
            </select>

            <!-- Color -->
            <label for="color">Color:</label>
            <input type="text" id="color" name="color" placeholder="Enter color..." required>

            <!-- Plate Number -->
            <label for="plateNumber">Plate Number:</label>
            <input type="text" id="plateNumber" name="plateNumber" placeholder="Enter plate number..." required>

            <!-- Seat Count -->
            <label for="seatCount">Seat Count:</label>
            <input type="number" id="seatCount" name="seatCount" placeholder="Enter seat count..." required min="1">

            <!-- Availability -->
            <label for="availability">Available:</label>
            <input type="checkbox" id="availability" name="availability">

            <!-- Price Per Km -->
            <label for="pricePerKm">Price Per Km (LKR):</label>
            <input type="number" id="pricePerKm" name="pricePerKm" placeholder="Enter price per km..." required min="0" step="0.01">

            <!-- Liters Per Km -->
            <label for="litersPerKm">Liters Per Km:</label>
            <input type="number" id="litersPerKm" name="litersPerKm" placeholder="Enter liters per km..." required min="0" step="0.1">

            <!-- Driver -->
            <label for="driver">Driver:</label>
            <select id="driver" name="driver" required>
                <option value="" disabled selected>Select Driver</option>
                <option value="John Doe">John Doe</option>
                <option value="Jane Smith">Jane Smith</option>
            </select>

            <!-- Owner -->
            <label for="owner">Owner:</label>
            <select id="owner" name="owner" required>
                <option value="" disabled selected>Select Owner</option>
                <option value="Company A">Company A</option>
                <option value="Company B">Company B</option>
            </select>

            <!-- Submit Button -->
            <button type="submit" class="submit-btn">Add Vehicle</button>
        </form>
    </div>

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
