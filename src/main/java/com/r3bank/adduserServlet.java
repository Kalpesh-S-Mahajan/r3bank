package com.r3bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class adduserServlet
 */


@WebServlet("/adduserServlet")
public class adduserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public adduserServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		 response.setContentType("text/html");
	     PrintWriter out = response.getWriter();
	     
	        int accno = 0;
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String address = request.getParameter("address");
	        int balance = Integer.parseInt(request.getParameter("balance"));
	        
	        Connection conn=DbConnection.connect();
	        
	        try {
				PreparedStatement pstmt=conn.prepareStatement("insert into bank values(?,?,?,?,?)");
				pstmt.setInt(1, accno);
				pstmt.setString(2, name);
				pstmt.setString(3, email);
				pstmt.setString(4, address);
				pstmt.setInt(5, balance);
				
				int i=pstmt.executeUpdate();
				if(i>0) {
					System.out.println("data inserted successfully...");
					out.println("<h2 style='color: green;'>Add User Successful....</h2>");
					out.println("<a href='dashboard.html'>🔙 Back to Dashboard</a>");
					
					
				}else {
					System.out.println("Not Inserted the data...");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
		
	}

}
