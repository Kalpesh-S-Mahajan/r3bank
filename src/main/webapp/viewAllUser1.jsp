<%@ page import="java.sql.*" %>
<%@ page import="com.r3bank.DbConnection" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Bank Users</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 30px auto;
            font-family: Arial, sans-serif;
        }

        th, td {
            padding: 10px 15px;
            border: 1px solid #333;
            text-align: left;
        }

        th {
            background-color: #003366;
            color: white;
        }

        h2 {
            text-align: center;
            font-family: 'Segoe UI', sans-serif;
            margin-top: 40px;
        }
    </style>
</head>
<body>

<h2>My Bank Users List</h2>

<%
    try (
        Connection conn = DbConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM userdata");
        ResultSet rs = pstmt.executeQuery();
    ) {
%>

<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Password</th>
        <th>Balance</th>
    </tr>
    <%
        while(rs.next()) {
    %>
    <tr>
        <td><%= rs.getInt("id") %></td>
        <td><%= rs.getString("name") %></td>
        <td><%= rs.getString("email") %></td>
        <td><%= rs.getString("password") %></td> 
        <td><%= rs.getInt("balance") %></td>      
    </tr>
    <%
        }
    %>
</table>

<%
    } catch (Exception e) {
        out.println("<p style='color:red; text-align:center;'>Error fetching user data: " + e.getMessage() + "</p>");
        e.printStackTrace();
    }
%>

</body>
</html>
