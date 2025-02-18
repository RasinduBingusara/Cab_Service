
<div class="sidebar">
  <div class="logo">Mega City Cab</div>
  <ul class="nav-links">

    <form action="bookings" method="get">
      <input type="hidden" name="action" value="view">
      <input type="submit" value="Manage Bookings">
    </form>

    <form action="vehicles" method="get">
      <input type="hidden" name="action" value="view">
      <input type="submit" value="Manage Vehicles">
    </form>

    <form action="drivers" method="get">
      <input type="hidden" name="action" value="all">
      <input type="submit" value="Manage Drivers">
    </form>

    <form action="login" method="get">
      <input type="submit" value="Logout">
    </form>
  </ul>
</div>

<style>
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: Arial, sans-serif;
  }

  .sidebar {
    width: 250px;
    height: 100vh;
    background-color: #ffcc00;
    position: fixed;
    left: 0;
    top: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 20px;
    box-shadow: 4px 0px 6px rgba(0, 0, 0, 0.1);
  }

  .logo {
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 20px;
    color: #333;
  }

  .nav-links {
    list-style: none;
    width: 100%;
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .nav-links form {
    width: 100%;
    display: flex;
    justify-content: center;
    margin: 10px 0;
  }

  .nav-links input[type="submit"] {
    width: 80%;
    padding: 12px;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    background-color: #333;
    color: #ffcc00;
    transition: background 0.3s, color 0.3s;
  }

  .nav-links input[type="submit"]:hover {
    background-color: #ffcc00;
    color: #333;
    border: 1px solid #333;
  }

  .content {
    margin-left: 270px;
    padding: 20px;
  }
</style>

