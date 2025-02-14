<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 14/02/2025
  Time: 03:04 pm
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
        <h1>Add Vehicle Model</h1>

        <form action="add-model" method="post" class="model-form">
            <!-- Vehicle Type -->
            <label for="vehicleType">Vehicle Type:</label>
            <select id="vehicleType" name="vehicleType" required>
                <option value="" disabled selected>Select Type</option>
                <option value="Car">Car</option>
                <option value="Van">Van</option>
                <option value="Bus">Bus</option>
            </select>

            <!-- Model Name -->
            <label for="modelName">Model Name:</label>
            <input type="text" id="modelName" name="modelName" placeholder="Enter model name..." required>

            <!-- Manufacturer -->
            <label for="manufacturer">Manufacturer:</label>
            <input type="text" id="manufacturer" name="manufacturer" placeholder="Enter manufacturer name..." required>

            <!-- Year -->
            <label for="year">Year:</label>
            <input type="number" id="year" name="year" placeholder="Enter year..." required min="1900" max="2099">

            <!-- Fuel Type -->
            <label for="fuelType">Fuel Type:</label>
            <select id="fuelType" name="fuelType" required>
                <option value="" disabled selected>Select Fuel Type</option>
                <option value="Petrol">Petrol</option>
                <option value="Diesel">Diesel</option>
                <option value="Electric">Electric</option>
                <option value="Hybrid">Hybrid</option>
            </select>

            <!-- Transmission -->
            <label for="transmission">Transmission:</label>
            <select id="transmission" name="transmission" required>
                <option value="" disabled selected>Select Transmission</option>
                <option value="Manual">Manual</option>
                <option value="Automatic">Automatic</option>
            </select>
            <p><%= request.getParameter("response")%></p>
            <!-- Submit Button -->
            <button type="submit" class="submit-btn">Add Model</button>
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
    .model-form {
        display: flex;
        flex-direction: column;
        gap: 12px;
    }

    .model-form label {
        font-weight: bold;
        margin-bottom: 5px;
        color: #333;
    }

    .model-form input,
    .model-form select {
        width: 100%;
        padding: 10px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 5px;
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
