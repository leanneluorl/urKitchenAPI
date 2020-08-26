package com.mycompany.UrKitchen.resources;

/**
 *
 * @author Leanne
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.mycompany.UrKitchen.model.*;
import com.mycompany.UrKitchen.model.DatabaseConnection;


import com.mycompany.UrKitchen.service.UserService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
 
   

@Path("/UrKitchen/user")
public class UserResources extends HttpServlet { 
        UserService us = new UserService();
        Gson gson = new Gson();
        
	private static final long serialVersionUID = 1L; 

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException 
	{  fixHeaders(resp);}
        
        
        protected void doPut(HttpServletRequest req, HttpServletResponse resp) 
	throws ServletException, IOException 
	{  fixHeaders(resp);}
    
    
         //@Override
         protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                                                                  throws ServletException, IOException {
             fixHeaders(resp);
         }

         //@Override
         protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
                                                                  throws ServletException, IOException {
             fixHeaders(resp);
         }

         private void fixHeaders(HttpServletResponse response) {

             response.setContentType("text/html");
             response.setHeader("Cache-control", "no-cache, no-store");
             response.setHeader("Pragma", "no-cache");
             response.setHeader("Expires", "-1");

             response.addHeader("Access-Control-Allow-Origin", "*"); // 授權的網址，星號代表接受所有
             response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE"); // 接受的方式
             response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Content-Length");
             response.addHeader("Access-Control-Max-Age", "86400");
         }
        
         
    //Admin login   
    //curl -vi -X GET -G "http://127.0.0.1:49000/api/UrKitchen/user/{UserID}"
        @GET
        @Path("/{UserID}")   
        @Produces(MediaType.APPLICATION_JSON)
        public Response Login(@PathParam("UserID") String id) {
            
            User a1=us.getUser(id);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(a1)).build();
    } 
       
        
    //curl -vi -X PUT -G "http://127.0.0.1:49000/api/UrKitchen/user/{UserID}"
        @PUT
        @Path("/{UserID}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response UpdateUseRow(   @PathParam("UserID") String ID,
                                        String body) {
            User i = gson.fromJson(body, User.class);
            us.updateUserAccount(ID, i);
            User F = us.getUser(ID);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
        
    
    //curl -vi -X POST -G "http://127.0.0.1:49000/api/UrKitchen/user/Create"
        @POST
        @Path("/Create") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response createUser( String body) {
            User i = gson.fromJson(body, User.class);
            feedback F = us.addUser("user", i);
            
            
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
        
    //curl -vi -X GET -G "http://127.0.0.1:49000/api/UrKitchen/user/{UserID}/StockIGD"
        @GET
        @Path("/{UserID}/StockIGD") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response findStock_IGD( @PathParam("UserID") String UID) {
            
            List<stockdetail> F = us.getStock_IGD(UID);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }    
    
    //curl -vi -X PUT -G "http://127.0.0.1:49000/api/UrKitchen/user/{UserID}"
        @PUT
        @Path("/SDID/{SDID}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response UpdateStockIGDRow(  @PathParam("SDID") String ID,
                                            String body) {
            stockdetail i = gson.fromJson(body, stockdetail.class);
            us.updatestockdetail(ID, i);
            User F = us.getUser(ID);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
    
    //curl -vi -X DELETE -G "http://127.0.0.1:49000/api/UrKitchen/user/stockIGD/{ID}"
        @DELETE
        @Path("/stockIGD/{IGDID}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response deleteRow_StockIGD(@PathParam("IGDID") String IGDID) {
            
            feedback F=us.deleteStock_IGD("stockdetail",IGDID);
            
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
        
    //curl -vi -X POST -G "http://127.0.0.1:49000/api/UrKitchen/user/StockIGD/Create/{UID}"
        @POST
        @Path("StockIGD/Create/{UID}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response createUser(@PathParam("UID") String UID, String body) {
            stockdetail i = gson.fromJson(body, stockdetail.class);
            feedback F = us.addStockdetail(UID, i);
            
            
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
    
        
       
    //curl -vi -X POST -G "http://127.0.0.1:49000/api/UrKitchen/user/addToUrTable"
        @POST
        @Path("/addToUrTable") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response createUrtableRow(String body) {
            Urtable i = gson.fromJson(body, Urtable.class);
            feedback F = us.addUrtable(i);
            
            
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }   
    
      @GET
        @Path("/{UserID}/UrTable/{today}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response findUrtable_RCP( @PathParam("UserID") String UID,
                                         @PathParam("today") String today) {
            
            List<Recipe> F = us.getUrtable_RCP(UID, today);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        } 
      
    
     //curl -vi -X DELETE -G "http://127.0.0.1:49000/api/UrKitchen/user/stockIGD/{ID}"
        @DELETE
        @Path("/Urtable/{UrtableID}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response deleteRow_Urtable(@PathParam("UrtableID") String UrtableID) {
            
            feedback F=us.deleteUrtableRow(UrtableID);
            
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
}