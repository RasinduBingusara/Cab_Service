<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 18/02/2025
  Time: 01:20 pm
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
        <h2>View Bookings</h2>

        <input type="text" id="bookingSearch" onkeyup="searchTable('bookingTable', 'bookingSearch')" placeholder="Search bookings...">

        <table id="bookingTable">
            <thead>
            <tr>
                <th>Booking ID</th>
                <th>Customer</th>
                <th>Pickup</th>
                <th>Destination</th>
                <th>Vehicle</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>B001</td>
                <td>John Doe</td>
                <td>Main Street</td>
                <td>Airport</td>
                <td>Sedan - ABC-1234</td>
                <td><span class="status completed">Completed</span></td>
            </tr>
            <tr>
                <td>B002</td>
                <td>Jane Smith</td>
                <td>City Center</td>
                <td>Hotel Grand</td>
                <td>SUV - XYZ-5678</td>
                <td><span class="status pending">Pending</span></td>
            </tr>
            <tr>
                <td>B003</td>
                <td>Michael Johnson</td>
                <td>Train Station</td>
                <td>Business District</td>
                <td>Hatchback - LMN-9876</td>
                <td><span class="status in-progress">In Progress</span></td>
            </tr>
            </tbody>
        </table>
    </div>
    <script>
        function searchTable(tableId, searchInputId) {
            let input = document.getElementById(searchInputId).value.toLowerCase();
            let table = document.getElementById(tableId);
            let rows = table.getElementsByTagName("tr");

            for (let i = 1; i < rows.length; i++) {
                let cells = rows[i].getElementsByTagName("td");
                let match = false;

                for (let j = 0; j < cells.length; j++) {
                    if (cells[j].innerText.toLowerCase().includes(input)) {
                        match = true;
                        break;
                    }
                }

                rows[i].style.display = match ? "" : "none";
            }
        }
    </script>
</body>

<style>
    h2 {
        text-align: center;
        color: #333;
    }

    input[type="text"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 16px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        background: white;
    }

    table th, table td {
        border: 1px solid #ddd;
        padding: 12px;
        text-align: left;
    }

    table th {
        background: #007bff;
        color: white;
    }

    table tbody tr:hover {
        background: #f1f1f1;
    }

    .status {
        padding: 5px 10px;
        border-radius: 5px;
        font-weight: bold;
        text-align: center;
        display: inline-block;
    }

    .status.completed {
        background: #28a745;
        color: white;
    }

    .status.pending {
        background: #ffc107;
        color: black;
    }

    .status.in-progress {
        background: #17a2b8;
        color: white;
    }
</style>
</html>
