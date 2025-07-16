package com.r3bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class transferServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int fromAcc = Integer.parseInt(request.getParameter("fromAcc"));
        int toAcc = Integer.parseInt(request.getParameter("toAcc"));
        int amount = Integer.parseInt(request.getParameter("amount"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection con = DbConnection.connect()) {

         
            con.setAutoCommit(false);

          
            PreparedStatement checkSender = con.prepareStatement("SELECT balance FROM bank WHERE accno = ?");
            checkSender.setInt(1, fromAcc);
            ResultSet rsSender = checkSender.executeQuery();

            PreparedStatement checkReceiver = con.prepareStatement("SELECT balance FROM bank WHERE accno = ?");
            checkReceiver.setInt(1, toAcc);
            ResultSet rsReceiver = checkReceiver.executeQuery();

            if (!rsSender.next()) {
                out.println("<h3 style='color:red;'>Sender account doesn't exist!</h3>");
                out.println("<a href='dashboard.html'>🔙 Back to Dashboard</a>");
                return;
            }

            if (!rsReceiver.next()) {
                out.println("<h3 style='color:red;'>Receiver account doesn't exist!</h3>");
                out.println("<a href='dashboard.html'>🔙 Back to Dashboard</a>");
                return;
            }

            int senderBalance = rsSender.getInt("balance");

            if (senderBalance < amount) {
                out.println("<h3 style='color:red;'>Insufficient balance!</h3>");
                out.println("<a href='dashboard.html'>🔙 Back to Dashboard</a>");
                return;
            }

     
            PreparedStatement deduct = con.prepareStatement("UPDATE bank SET balance = balance - ? WHERE accno = ?");
            deduct.setInt(1, amount);
            deduct.setInt(2, fromAcc);
            deduct.executeUpdate();

         
            PreparedStatement add = con.prepareStatement("UPDATE bank SET balance = balance + ? WHERE accno = ?");
            add.setInt(1, amount);
            add.setInt(2, toAcc);
            add.executeUpdate();

          
            con.commit();

            out.println("<h3 style='color:green;'>₹" + amount + " transferred successfully from " + fromAcc + " to " + toAcc + "</h3>");
            out.println("<a href='dashboard.html'>🔙 Back to Dashboard</a>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h3 style='color:red;'>Transfer failed due to an error.</h3>");
            out.println("<a href='dashboard.html'>🔙 Back to Dashboard</a>");
        }
    }
}
