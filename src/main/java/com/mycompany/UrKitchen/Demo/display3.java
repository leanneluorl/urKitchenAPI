package com.mycompany.UrKitchen.Demo;

import com.mycompany.UrKitchen.model.User;
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
@WebServlet("/display3") 
public class display3 extends HttpServlet { 
	private static final long serialVersionUID = 1L; 

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException 
	{ 
            PrintWriter out = res.getWriter();  
            res.setContentType("text/html");  
            out.println("<html><body>");  
            String id="test2";
		try { 

                    // Initialize the database 
                    Connection con = DatabaseConnection.initializeDatabase(); 
                    Statement stmt = con.createStatement();  
                    ResultSet rs = stmt.executeQuery("select * from User\n" +
                                                     "where Email='"+id+"';");  
                    out.println("<table border=1 width=50% height=50%>");  
                    out.println("<tr><th>Email</th><th>UserPWD</th><th>Date of Birth</th><tr>");  
                     
                    while (rs.next()) 
                    { 
                        String n = rs.getString("Email");  
                        String nm = rs.getString("UserPWD");  
                        Date s = rs.getDate("DateOfBirth"); 
                        out.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>" + s + "</td></tr>");   
                    } 
                    /*
                    User a1=new User();
                    while (rs.next()) 
                    { 
                        String A = rs.getString("UserID");  
                        String B = rs.getString("UserPWD");  
                        Date C = rs.getDate("DateOfBirth");
                        String E = rs.getString("AccID"); 
                        //List<User> a = new ArrayList();
                        a1 = new User(A,B,C,E);
                    } 
                    System.out.println(a1.toString());*/
                    out.println("</table>");  
                    out.println("</html></body>");  
                    con.close();  
			
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	} 
} 
