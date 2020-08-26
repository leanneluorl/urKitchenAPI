package com.mycompany.UrKitchen.service;

import com.mycompany.UrKitchen.model.Admin;
import com.mycompany.UrKitchen.model.DatabaseConnection;
import com.mycompany.UrKitchen.model.Ingredient;
import com.mycompany.UrKitchen.model.User;
import com.mycompany.UrKitchen.model.item;
import com.mycompany.UrKitchen.model.itemIGD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leanne
 */
public class AdminService {
    public static List<Admin> listA = new ArrayList<>();
    public static List<User> listU = new ArrayList<>();
    
    public Admin getAdmin(String id){
        Admin a1=new Admin();
        try { 
                // Initialize the database 
                Connection con = DatabaseConnection.initializeDatabase(); 
                Statement stmt = con.createStatement();  
                ResultSet rs = stmt.executeQuery("select * from Admin\n" +
                                                 "where AdminID='"+id+"';");  
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

                con.close();  
            } 
            catch (Exception e) { 
                    e.printStackTrace(); 
            } 
        return a1;
    }
    
    
    public <T> List<T> getUser(String keyword){
        User u1=new User();
        List<T> U = new ArrayList();
        String query = "";
        if(keyword.equalsIgnoreCase("all")){
            query="select * from User \n"+
                  "order by AccID";
        }
        else{
            query="select * from User\n" + 
                  "where email like'%"+keyword+"%' or \n"+
                    "UserID like'%"+keyword+"%' or\n"+
                    "FirstName like'%"+keyword+"%' or\n"+
                    "LastName like'%"+keyword+"%' or\n"+
                    "MobileNo like'%"+keyword+"%' or\n"+
                    "AccID like'%"+keyword+"%';";
        }
        try { 

            // Initialize the database 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query);  

            while (rs.next()) 
            { 
                String A = rs.getString("Email");  
                String B = rs.getString("UserPWD"); 
                String C = rs.getString("FirstName");
                String D = rs.getString("LastName");
                Date E = rs.getDate("DateOfBirth");
                Timestamp F = rs.getTimestamp("RegisterDate");
                String G = rs.getString("MobileNo");
                String H = rs.getString("AccID");
                String I = rs.getString("UserID"); 

                u1 = new User(A,B,C,D,E,F,G,H,I);
                U.add((T) u1);
            }    


            con.close();  

        } 
        catch (Exception e) { 
                e.printStackTrace(); 
        } 
        return U;
    }
    
    public <T> List<T> getItem(String table, String keyword){
        item u1=new item();
        List<T> U = new ArrayList();
        String query = "";
        String ID = table+"ID";
        
        if(keyword.equalsIgnoreCase("all")){
            query="select * from "+table+"\n"+
                  " order by "+ID+";";
        }
        else{
            query="select * from "+table+"\n" + 
                  "where "+ID+" ='"+keyword+"' or \n"+
                    table+" like'%"+keyword+"%' or \n"+
                    "unique_name like'%"+keyword+"%' or\n"+
                    "information like'%"+keyword+"%';";
        }
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query);  

            while (rs.next()) 
            { 
                int A = rs.getInt(ID);  
                String B = rs.getString(table); 
                String C = rs.getString("Information");
                String D = rs.getString("Unique_Name");
                u1 = new item(A,B,C,D);
                U.add((T) u1);
            }    

            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
    
    public <T> List<T> getIngre(String table, String keyword, String order){
        itemIGD u1=new itemIGD();
        List<T> U = new ArrayList();
        String query = "";
        String ID = table+"ID";
        
        if(keyword.equalsIgnoreCase("all")){
            query="select IngredientID, Ingredient, FoodType, i.Unique_Name, f.foodtypeID, f.information from ingredient i \n" +
                  "LEFT JOIN FoodType f ON f.FoodTypeid = i.FoodTypeid \n"+
                  "Order by "+order;
        }
        else{
            query="select IngredientID, Ingredient, FoodType, i.Unique_Name from ingredient  \n" +
                  "LEFT JOIN FoodType f ON f.FoodTypeid = i.FoodTypeid \n"+
                  "where IngredientID ='"+keyword+"' or \n"+
                  "Ingredient like'%"+keyword+"%' or \n"+
                  "i.unique_name like'%"+keyword+"%' or \n"+
                  "FoodType like'%"+keyword+"%' \n"+
                  "order by "+order;
            
        }
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query);  

            while (rs.next()) 
            { 
                int A = rs.getInt("IngredientID");  
                String B = rs.getString("Ingredient"); 
                String C = rs.getString("FoodType");
                String D = rs.getString("Unique_Name");
                String E = rs.getString("FoodTypeID");
                String F = rs.getString("information");
                u1 = new itemIGD(A,B,C,D,E,F);
                U.add((T) u1);
            }    

            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
    
    public <T> List<T> getIngreByID(String table, String ID){
        Ingredient u1=new Ingredient();
        List<T> U = new ArrayList();
        String query = "";
        
        query="select * from Ingredient \n"+
              "where IngredientID ='"+ID+"';";
                  
        
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query);  

            while (rs.next()) 
            { 
                int A = rs.getInt("IngredientID");  
                String B = rs.getString("Ingredient"); 
                int C = rs.getInt("FoodTypeID");
                int D = rs.getInt("Amount");
                String E = rs.getString("Unit"); 
                String F = rs.getString("UnitAprox");
                int G = rs.getInt("CaloriesPerServing");
                String H = rs.getString("Unique_Name");
                u1 = new Ingredient(A,B,C,D,E,F,G,H);
                U.add((T) u1);
            }    

            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
    
    public <T> List<T> updateItem(String table, item i){
        item u1=new item();
        List<T> U = new ArrayList();
        String query = "";
        String tableID = table+"ID";
        
        
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            //Statement stmt = con.createStatement();  
            //ResultSet rs = stmt.executeQuery(query);  
            query = 
                    "update "+table+ "\n"+ 
                    "set "+table+"= ?, information=? \n"+
                    " where "+tableID+ " = ? ;";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            
            preparedStmt.setString(1, i.getItem());
            preparedStmt.setString(2, i.getInformation() );
            preparedStmt.setInt(3, i.getItemID());
            System.out.println("\n"+preparedStmt.toString());
            
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            
            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
    
    public <T> List<T> updateIGDItem(String ID, Ingredient i, String FoodType){
        List<T> U = new ArrayList();
        
        String query1 = "Select FoodtypeID from foodtype where foodtype like '%"+FoodType+"%' or \n"+
                        "foodtypeID='"+FoodType+"';";
        String query = "";
        int foodtypeID=0;
        
        System.out.println(query1);
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query1);  
            
            while (rs.next()) 
            { 
                foodtypeID = rs.getInt("FoodtypeID");  
            }    
            query = "update Ingredient \n"+ 
                    "set Ingredient=?, \n"+
                    " FoodTypeID= ?, Amount=?, \n"+
                    " UnitAprox=?, \n"+
                    " CaloriesPerServing=? \n"+
                    "where ingredientid ="+ID+";";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, i.getIngredient());
            preparedStmt.setInt(2, foodtypeID);
            preparedStmt.setInt(3, i.getAmount());
            preparedStmt.setString(4, i.getUnitAprox());
            preparedStmt.setInt(5, i.getCaloriesPerServing());
            System.out.println("\n"+preparedStmt.toString());
            
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            
            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
        
    
    public <T> List<T> addItem(String table, item i){
        item u1=new item();
        List<T> U = new ArrayList();
        String query = "";
        String tableID = table+"ID";
        
        
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            //Statement stmt = con.createStatement();  
            //ResultSet rs = stmt.executeQuery(query);  
            query = 
                    "Insert into "+table+ "\n"+ 
                    "values \n"+
                    "(null,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            
            preparedStmt.setString(1, i.getItem());
            preparedStmt.setString(2, i.getInformation());
            preparedStmt.setString(3, i.getItem());
            
            System.out.println("\n"+preparedStmt.toString());
            
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            
            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
   
    public <T> List<T> addIGDItem(String ID, Ingredient i, String FoodType){
        item u1=new item();
        List<T> U = new ArrayList();
        String query = "";
        String query1 = "Select FoodtypeID from foodtype where foodtype  like '%"+FoodType+"%' or \n"+
                        "foodtypeID='"+FoodType+"';";
        int foodtypeID=0;
        
        System.out.println(query1);
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query1);  
            while (rs.next()) 
            { 
                foodtypeID = rs.getInt("FoodtypeID");  
            } 
            query = "Insert into ingredient \n"+ 
                    "values \n"+
                    "(null,?,?,?,?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, i.getIngredient());
            preparedStmt.setInt(2, foodtypeID);
            preparedStmt.setInt(3, i.getAmount());
            preparedStmt.setString(4, "gram");
            preparedStmt.setString(5, i.getUnitAprox());
            preparedStmt.setInt(6, i.getCaloriesPerServing());
            preparedStmt.setString(7, i.getUnique_Name());
            System.out.println("\n"+preparedStmt.toString());
            
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            
            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
   
    
    public <T> List<T> deleteItem(String table, int ItemID){
        item u1;
        List<T> U = new ArrayList();
        String query, query2;
        String ID = table+"ID";
        
                               
        query="delete from "+table+"\n" + 
              "where "+ID+" ='"+ItemID+"'; \n";
              
        
        query2="ALTER TABLE "+table+" AUTO_INCREMENT = "+ItemID+";";
        
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            PreparedStatement ACC = con.prepareStatement(query); 
            ACC.execute();
             
            stmt.executeUpdate(query2);
               

            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
    
}
