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

import com.mycompany.UrKitchen.service.RecipeService;
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
 
   

@Path("/UrKitchen/Recipe")
public class RecipeResources extends HttpServlet { 
        RecipeService rs = new RecipeService();
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
        
    
    //Retrieve Recipe data 
    
    //Get Recipes by Catalog
    //curl -vi -X GET -G "http://127.0.0.1:49000/api/UrKitchen/Recipe/getRCPbyCatalog/{table}/{tableID}"
        @GET
        @Path("getRCPbyCatalog/{table}/{tableID}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response searchRCPbyCatalog(@PathParam("table") String table, @PathParam("tableID") String tableID) {
           List<Recipe> F =rs.getRCPbyCatalog(table, tableID);
            
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        } 
         
    //curl -vi -X GET -G "http://127.0.0.1:49000/api/UrKitchen/Recipe/{keyword}/{order/{sort}}"
        @GET
        @Path("/{keyword}/{order}/{sort}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response findRecipe( @PathParam("keyword") String keyword,
                                    @PathParam("order") String order,
                                    @PathParam("sort") String sort) {
            
            List<Recipe> F = rs.getRecipe(keyword,order, sort);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
    
    
    //curl -vi -X GET -G "http://127.0.0.1:49000/api/UrKitchen/Recipe/{ID}"
        @GET
        @Path("/{ID}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response findRecipeByID( @PathParam("ID") String ID) {
            
            List<Recipe> F = rs.getRecipeByID(ID);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
      
        
    //curl -vi -X GET -G "http://127.0.0.1:49000/api/UrKitchen/Recipe/{ID}/Instru"
        @GET
        @Path("/{ID}/Instru") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response findRecipeByID_instru( @PathParam("ID") String ID) {
            
            List<Instruction> F = rs.getRecipeByID_instru(ID);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
        
        
    //curl -vi -X GET -G "http://127.0.0.1:49000/api/UrKitchen/Recipe/{ID}/IGD"
        @GET
        @Path("/{RID}/IGD") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response findRecipeByID_IGD( @PathParam("RID") String RID) {
            
            List<recipeIngredient> F = rs.getRecipeByID_IGD(RID);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
        
        
    //curl -vi -X DELETE -G "http://127.0.0.1:49000/api/UrKitchen/Recipe/{ID}"
        @DELETE
        @Path("/{ID}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response deleteRow(@PathParam("ID") String ItemID) {
            rs.deleteRecipe("recipe",ItemID);
            List<Recipe> F = rs.getRecipe(ItemID,"recipeID","asc");
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        } 
        
        
    //curl -vi -X DELETE -G "http://127.0.0.1:49000/api/UrKitchen/Recipe/Instru/{ID}"
        @DELETE
        @Path("/Instru/{instruID}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response deleteRow_instru(@PathParam("instruID") String instruID) {
            String ID=rs.deleteRecipe_instru("instruction",instruID);
            List<Instruction> F = rs.getRecipeByID_instru(ID);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        } 
        
        
    //curl -vi -X DELETE -G "http://127.0.0.1:49000/api/UrKitchen/Recipe/IGD/{ID}"
        @DELETE
        @Path("/IGD/{IGDID}") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response deleteRow_IGD(@PathParam("IGDID") String IGDID) {
            String ID=rs.deleteRecipe_IGD("recipeingredient",IGDID);
            List<Instruction> F = rs.getRecipeByID_instru(ID);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
        
        
    //curl -vi -X PUT -G "http://127.0.0.1:49000/api/UrKitchen/Recipe/{ID}"
        @PUT
        @Path("/{ID}") 
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response UpdateRecipe(@PathParam("ID") String ID, String body) {
            
            Gson gson = new Gson();
            Recipe r = gson.fromJson(body, Recipe.class);
            rs.updateRecipeItem(ID, r);
            
            
            return Response.status(Response.Status.CREATED).entity(gson.toJson(r)).build();
        }
        
    
    //curl -vi -X PUT -G "http://127.0.0.1:49000/api/UrKitchen/Recipe/Instru/{instruID}"
        @PUT
        @Path("/Instru/{instruID}") 
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response UpdateRecipe_instru(@PathParam("instruID") String ID, String body) {
            
            Gson gson = new Gson();
            Instruction i = gson.fromJson(body, Instruction.class);
            rs.updateRecipeItem_instru(ID, i);
            
            
            return Response.status(Response.Status.CREATED).entity(gson.toJson(i)).build();
        }    
    
       
    //curl -vi -X PUT -G "http://127.0.0.1:49000/api/UrKitchen/Recipe/IGD/{IGDID}"
        @PUT
        @Path("/IGD/{IGDID}") 
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response UpdateRecipe_IGD(@PathParam("IGDID") String ID, String body) {
            
            Gson gson = new Gson();
            recipeIngredient i = gson.fromJson(body, recipeIngredient.class);
            rs.updateRecipeItem_IGD(ID, i);
            
            
            return Response.status(Response.Status.CREATED).entity(gson.toJson(i)).build();
        }  
      
    
    //curl -vi -X POSt -G "http://127.0.0.1:49000/api/UrKitchen/Recipe/"
        @POST
        @Path("/Create") 
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response createRecipe(String body) {
            
            Gson gson = new Gson();
            Recipe r = gson.fromJson(body, Recipe.class);
            String ID = rs.addRecipeItem(r);
            List<Recipe> F = rs.getRecipeByID(ID);
            
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        } 
        
        
    //curl -vi -X POSt -G "http://127.0.0.1:49000/api/UrKitchen/Recipe/Instru/Create/{RID}"
        @POST
        @Path("/Instru/Create/{RID}") 
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response createRecipe_instru(@PathParam("RID") int RID,String body) {
            
            Gson gson = new Gson();
            Instruction r = gson.fromJson(body, Instruction.class);
            String ID = rs.addRecipeItem_instru(RID,r);
            List<Recipe> F = rs.getRecipeByID_instru(ID);
            
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        } 
        
    //curl -vi -X POSt -G "http://127.0.0.1:49000/api/UrKitchen/Recipe/IGD/Create/{RID}"
        @POST
        @Path("/IGD/Create/{RID}") 
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response createRecipe_IGD(@PathParam("RID") int RID,String body) {
            
            Gson gson = new Gson();
            recipeIngredient r = gson.fromJson(body, recipeIngredient.class);
            String ID = rs.addRecipeItem_IGD(RID,r);
            List<Recipe> F = rs.getRecipeByID_instru(ID);
            
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        } 
     
    
        
    //*****Search Recipe by ingredients
    //curl -vi -X GET -G "http://127.0.0.1:49000/api/UrKitchen/Recipe/SearchRCPbyIGD"
        @POST
        @Path("/SearchRCPbyIGD") 
        @Produces(MediaType.APPLICATION_JSON)
        public Response SearchRCPbyIGD(String body) {
            
            ArrayList IGDs = new ArrayList();
           
            
            Gson gson = new Gson();
            SearchRCPbyIGD_selections ss = gson.fromJson(body, SearchRCPbyIGD_selections.class);
            
            List<Recipe> F = rs.getRCPbyIGD_selections(ss);
            
            return Response.status(Response.Status.CREATED).entity(gson.toJson(F)).build();
        }
        
   
        
}        
