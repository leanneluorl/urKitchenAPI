package com.mycompany.UrKitchen.Demo;

import com.mycompany.UrKitchen.model.Admin;
import com.mycompany.UrKitchen.model.DatabaseConnection;
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
@WebServlet("/display2") 
public class display2 extends HttpServlet { 
	private static final long serialVersionUID = 1L; 

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException 
	{ 
            PrintWriter out = res.getWriter();  
            res.setContentType("text/html");  
            out.println("<html><body>");  
            String id="test";
		try { 

                    // Initialize the database 
                    Connection con = DatabaseConnection.initializeDatabase(); 
                    Statement stmt = con.createStatement();  
                    ResultSet rs = stmt.executeQuery("select * from Admin\n" +
                                                     "where AdminID='"+id+"';");  
                    out.println("<table border=1 width=50% height=50%>");  
                    out.println("<tr><th>AdminID</th><th>AdminName</th><th>AdminPWD</th><tr>");  
                     
                    while (rs.next()) 
                    { 
                        String n = rs.getString("AdminID");  
                        String nm = rs.getString("AdminName");  
                         String s = rs.getString("CreateDate"); 
                        out.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>" + s + "</td></tr>");   
                    }  
                    Admin a1=new Admin();
                    while (rs.next()) 
                    { 
                        String A = rs.getString("AdminID");  
                        String B = rs.getString("AdminName"); 
                        String C = rs.getString("AdminPWD");  
                        Timestamp D = rs.getTimestamp("CreateDate");
                        String E = rs.getString("AccID"); 
                        //List<Admin> a = new ArrayList();
                        a1 = new Admin(A,B,C,D,E);
                    } 
                    System.out.println(a1.toString());
                    out.println("</table>");  
                    out.println("</html></body>");  
                    con.close();  
			
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	} 
} 
