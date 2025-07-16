package com.r3bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class depositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public depositServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String accnoStr = request.getParameter("accno");
		String amountStr = request.getParameter("amount");

		try {
			int accno = Integer.parseInt(accnoStr);
			int amount = Integer.parseInt(amountStr);

			Connection conn = DbConnection.connect(); // Use your connection method

			String sql = "UPDATE bank SET balance = balance + ? WHERE accno = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, accno);

			int rowsUpdated = pstmt.executeUpdate();

			if (rowsUpdated > 0) {
				out.println("<h2 style='color: green;'>Deposit successful!</h2>");
				out.println("<a href='dashboard.html'>🔙 Back to Dashboard</a>");
				
			} else {
				out.println("<h2 style='color: red;'>Account not found. Deposit failed.</h2>");
				out.println("<a href='dashboard.html'>🔙 Back to Dashboard</a>");
			}

		} catch (NumberFormatException e) {
			out.println("<h2 style='color: red;'>Invalid input. Please enter numeric values.</h2>");
			e.printStackTrace();
		} catch (SQLException e) {
			out.println("<h2 style='color: red;'>Database error: " + e.getMessage() + "</h2>");
			e.printStackTrace();
		}
	}
}
