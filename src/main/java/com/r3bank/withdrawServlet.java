package com.r3bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class withdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		int accno = Integer.parseInt(request.getParameter("accno"));
		int amount = Integer.parseInt(request.getParameter("amount"));

		Connection conn = DbConnection.connect();

		try {
			// Step 1: Check current balance
			PreparedStatement checkStmt = conn.prepareStatement("SELECT balance FROM bank WHERE accno = ?");
			checkStmt.setInt(1, accno);
			ResultSet rs = checkStmt.executeQuery();

			if (rs.next()) {
				int currentBalance = rs.getInt("balance");

				if (amount <= currentBalance) {
					
					int newBalance = currentBalance - amount;
					PreparedStatement updateStmt = conn.prepareStatement("UPDATE bank SET balance = ? WHERE accno = ?");
					updateStmt.setInt(1, newBalance);
					updateStmt.setInt(2, accno);
					int rows = updateStmt.executeUpdate();

					if (rows > 0) {
						out.println("<h2 style='color:green;'>Withdrawal successful!</h2>");
						out.println("<p>New Balance: ₹" + newBalance + "</p>");
						out.println("<a href='dashboard.html'>🔙 Back to Dashboard</a>");
					} else {
						out.println("<h2 style='color:red;'>Failed to update balance.</h2>");
						out.println("<a href='dashboard.html'>🔙 Back to Dashboard</a>");
					}
				} else {
					out.println("<h2 style='color:red;'>Insufficient balance!</h2>");
					out.println("<a href='dashboard.html'>🔙 Back to Dashboard</a>");
				}
			} else {
				out.println("<h2 style='color:red;'>Account not found.</h2>");
				out.println("<a href='dashboard.html'>🔙 Back to Dashboard</a>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("<h2 style='color:red;'>Database error occurred.</h2>");
			out.println("<a href='dashboard.html'>🔙 Back to Dashboard</a>");
		}
	}
}
