package com.mycompany.UrKitchen.model;

import com.mycompany.UrKitchen.model.DatabaseConnection;
import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.*;
import java.util.Date;

import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

// Import Database Connection Class file 

// Servlet Name 
@WebServlet("/CreateUser") 
public class CreateUser extends HttpServlet { 
	private static final long serialVersionUID = 1L; 
        
        
	protected void doPost(HttpServletRequest request, 
HttpServletResponse response) 
		throws ServletException, IOException 
	{ 
		try { 

			// Initialize the database 
			Connection con = DatabaseConnection.initializeDatabase(); 
                        
                        Statement stmt = con.createStatement();  
                        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM User;");  
                        rs.next();
                        int c =rs.getInt(1)+1; 
                        String formatted = String.format("%08d", c);
                        String AID ="U"+formatted; 
                        System.out.print(AID);
                        PreparedStatement ACC = con 
				.prepareStatement("insert into account values(?,?,?)"); 
                        ACC.setString(1, AID); 
                        ACC.setString(2, null); 
                        ACC.setString(3, "User"); 
                        ACC.executeUpdate();
			// Create a SQL query to insert data into User table 
			// User table consists of 8 columns, so eight '?' is used 
			PreparedStatement st = con 
				.prepareStatement("insert into User values(?,?,?,?,?,?,?,?)"); 

			// For the first parameter, 
			// get the data using request object 
			// sets the data to st pointer 
			st.setString(1, request.getParameter("id")); 
                        st.setString(2, request.getParameter("pwd")); 
                        st.setString(3, request.getParameter("Fname")); 
                        st.setString(4, request.getParameter("Lname")); 
                        st.setString(5, null); 
                        Timestamp date = new Timestamp(new Date().getTime());
                        st.setTimestamp(6, date);
                        
                        st.setString(7, null); 
                        st.setString(8, AID);
                        
			// Execute the insert command using executeUpdate() 
			// to make changes in database 
			st.executeUpdate(); 

			// Close all the connections 
			st.close(); 
			con.close(); 

			// Get a writer pointer 
			// to display the successful result 
			PrintWriter out = response.getWriter(); 
			out.println("<html><body><b>Successfully Inserted"
						+ "</b></body></html>"); 
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	} 
} 
