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

import com.mycompany.UrKitchen.service.AdminService;
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
 
   

@Path("/UrKitchen/admin")
public class AdminResources extends HttpServlet { 
        AdminService as = new AdminService();
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
    //curl -vi -X GET -G "http://127.0.0.1:49000/api/UrKitchen/admin/leanneluorl"
        @GET
        @Path("/{param}")   
        @Produces(MediaType.APPLICATION_JSON)
        public Response Login(@PathParam("param") String id) {
            
            Admin a1=as.getAdmin(id);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(a1)).build();
        } 
    
    
    //Admin manage User accounts
    //curl -vi -X GET -G "http://127.0.0.1:49000/api/UrKitchen/admin/User/{param}"
        @GET
        @Path("/User/{param}")   
        @Produces(MediaType.APPLICATION_JSON)
        public Response findUser(@PathParam("param") String keyword) {
            List<User> U = as.getUser(keyword);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(U)).build();
        } 
        
        
    

    //Admin manage table
    //curl -vi -X GET -G "http://127.0.0.1:49000/api/UrKitchen/admin/{table}/{param}"
        @GET
        @Path("/{table}/{param}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response getRow(@PathParam("table") String table, @PathParam("param") String keyword) {
           List<item> F;
            if(table.equalsIgnoreCase("ingredient"))
                {F = as.getIngreByID(table,keyword);}
            else
                {F = as.getItem(table,keyword);}
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        } 
           
    //curl -vi -X GET -G "http://127.0.0.1:49000/api/UrKitchen/admin/{table}/{param}/{order}"
        @GET
        @Path("/Ingredient/{param}/{order}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response getIGDRow(   
                                    @PathParam("param") String keyword,
                                    @PathParam("order") String order) {
            
            List<item> F = as.getIngre("Ingredient",keyword,order);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
        
    //curl -vi -X PUT -G "http://127.0.0.1:49000/api/UrKitchen/admin/{table}/{ID}/"
        @PUT
        @Path("/{table}/{ID}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response UpdateRow(  @PathParam("table") String table, 
                                    String body) {
            item i = gson.fromJson(body, item.class);
            as.updateItem(table, i);
            List<item> F = as.getItem(table,Integer.toString(i.getItemID()));
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
        
    //curl -vi -X PUT -G "http://127.0.0.1:49000/api/UrKitchen/admin/Ingredient/{IGDID}/{foodtype}"
        @PUT
        @Path("/Ingredient/{IGDID}/{foodtype}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response UpdateIGDRow(   @PathParam("IGDID") String ID,
                                        @PathParam("foodtype") String foodtype,
                                        String body) {
            Ingredient i = gson.fromJson(body, Ingredient.class);
            as.updateIGDItem(ID, i, foodtype);
            List<Ingredient> F = as.getIngreByID("Ingredient",ID);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
        
    //curl -vi -X POST -G "http://127.0.0.1:49000/api/UrKitchen/admin/{Ingredient}/{FoodTypeID}/{Amount}/{Unit}/{UnitAprox}/{CaloriesPerServing}/{Unique_Name}"
        @POST
        @Path("/Ingredient/Create/{foodtype}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response addIGDRow(  @PathParam("IGDID") String ID,
                                    @PathParam("foodtype") String foodtype,
                                    String body) {
            Ingredient i = gson.fromJson(body, Ingredient.class);
            as.addIGDItem(ID, i, foodtype);
            List<Ingredient> F = as.getIngreByID("Ingredient","all");
            
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
        
    //curl -vi -X POST -G "http://127.0.0.1:49000/api/UrKitchen/admin/{table}/{item}/{info}"
        @POST
        @Path("{table}/Create") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response addRow( @PathParam("table") String table, 
                                String body) {
            item i = gson.fromJson(body, item.class);
            as.addItem(table, i);
            List<Ingredient> F = as.getIngreByID("Ingredient","all");
            
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
        
    //curl -vi -X GET -G "http://127.0.0.1:49000/api/UrKitchen/admin/{table}/{param}"
        @DELETE
        @Path("/{table}/{param}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response deleteLastRow(@PathParam("table") String table, @PathParam("param") int ItemID) {
            as.deleteItem(table,ItemID);
            List<item> F = as.getItem(table,"all");
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        } 
        
}        
