package com.mycompany.UrKitchen.Demo;

import com.mycompany.UrKitchen.model.DatabaseConnection;
import com.mycompany.UrKitchen.service.AdminService;
import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.*;

import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

// Import Database Connection Class file 

// Servlet Name 
@WebServlet("/display") 
public class display extends HttpServlet { 
	private static final long serialVersionUID = 1L; 
        AdminService as = new AdminService();
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException 
	{   
            
            PrintWriter out = res.getWriter();  
            res.setContentType("text/html");  
            out.println("<html><body>");  
            String id="3";
		try { 

                    // Initialize the database 
                    Connection con = DatabaseConnection.initializeDatabase(); 
                    Statement stmt = con.createStatement();  
                    ResultSet rs = stmt.executeQuery("select * from foodtype");  
                    out.println("<table border=1 width=50% height=50%>");  
                    out.println("<tr><th>EmpId</th><th>EmpName</th><th>Salary</th><tr>");  
                    while (rs.next()) 
                    {  
                        String n = rs.getString("FoodTypeID");  
                        String nm = rs.getString("FoodType");  
                        String s = rs.getString("Information");   
                        out.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>" + s + "</td></tr>");   
                    }  
                    out.println("</table>");  
                    out.println("</html></body>");  
                    con.close();  
			
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	} 
} 
