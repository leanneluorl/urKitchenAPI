package com.mycompany.UrKitchen.service;

import com.mycompany.UrKitchen.model.Admin;
import com.mycompany.UrKitchen.model.DatabaseConnection;
import com.mycompany.UrKitchen.model.Ingredient;
import com.mycompany.UrKitchen.model.Recipe;
import com.mycompany.UrKitchen.model.Urtable;
import com.mycompany.UrKitchen.model.User;
import com.mycompany.UrKitchen.model.feedback;
import com.mycompany.UrKitchen.model.stockdetail;
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
public class UserService {
    RecipeService rs = new RecipeService();
    public static List<Admin> listA = new ArrayList<>();
    public static List<User> listU = new ArrayList<>();
    
    
    public User getUser(String keyword){
        User u1=new User();
        
        String query = "";
        query=  "select * from User\n" + 
                "where email ='"+keyword+"' or \n"+
                    "UserID ='"+keyword+"';";
        
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
                
            }    


            con.close();  

        } 
        catch (Exception e) { 
                e.printStackTrace(); 
        } 
        return u1;
    }

    
    public <T> List<T> updateUserAccount(String ID, User i){
        List<T> U = new ArrayList();
        
        String query = "";
        
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
               
            query = "update User \n"+ 
                    "set FirstName=?, \n"+
                    " LastName= ?,  \n"+
                    " MobileNo=? \n"+
                    "where userid ='"+ID+"';";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, i.getFirstName());
            preparedStmt.setString(2, i.getLastName());
            preparedStmt.setString(3, i.getMobileNo());
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

    
    public feedback addUser(String table, User i){
        User u1=new User();
        //List<T> U = new ArrayList();
        String query = "";
        String tableID = table+"ID";
        feedback f = new feedback();
        try {                 
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
                    .prepareStatement("insert into User values(?,?,?,?,null,null,now(),null,?)"); 

            st.setString(1,i.getEmail());
            st.setString(2,i.getUserID());
            st.setString(3,i.getUserPWD());
            st.setString(4,i.getFirstName());
            st.setString(5,AID);
            
            st.executeUpdate(); 

            // Close all the connections 
            st.close(); 
            con.close(); 
            f.setMsg("Account has been created, please log in!");
            
        } 
        catch (Exception e) { 
                e.printStackTrace(); 
        }
        return f;
    }
    
    
    public <T> List<T> getStock_IGD(String UID){
        
        stockdetail u1;
        List<T> U = new ArrayList();
        String query = "";
        
        query=  "select s.* ,l.information as 'LocationImage', i.UnitAprox as 'IGDimage', i.ingredient, l.location,f.foodtype, f.foodtypeID from stockdetail s \n" +
                "left join ingredient i on i.ingredientID = s.IngredientID \n"+
                "left join location l on l.locationID = s.locationID \n"+
                "left join foodtype f on f.foodtypeID = i.foodtypeID \n"+
                "Where UserID = '"+UID+"' order by  Location;";    
        
    System.out.println("\ngetStock_IGD: \n"+query);
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query);  

            while (rs.next()) 
            { 
                String A = rs.getString("SDID");  
                String B = rs.getString("Ingredient"); 
                int C = rs.getInt("Amount"); 
                String D = rs.getString("foodtype");
                String E = rs.getString("Location");
                String F = rs.getString("IGDimage");
                String G = rs.getString("LocationImage");
                String H = rs.getString("foodtypeID");
                u1 = new stockdetail(A,B,C,D,E,F,G,H);
                U.add((T) u1);
            }    

            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
    
    
     public <T> List<T> updatestockdetail(String ID, stockdetail i){
        List<T> U = new ArrayList();
        String query = "";
        String LocationID = rs.findID("location", i.getLocationID());
        
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
               
            query = "update stockdetail \n"+ 
                    "set Amount= ?,  \n"+ 
                    " LocationID=? \n"+
                    "where SDID ='"+ID+"';";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, i.getAmount());
            preparedStmt.setString(2, LocationID);
            
        System.out.println("\nupdate stockdetail: \n"+preparedStmt.toString());
            
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            
            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
    
     
     public feedback deleteStock_IGD(String table, String ItemID){
        String query;
        
        String RID="";
        feedback f = new feedback();
       
        query="delete from "+table+" \n" + 
              "where SDID ='"+ItemID+"'; \n";
              
        System.out.println("\n deleteStock_IGD:"+"\n"+query+"\n");
        
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
                      
            PreparedStatement ACC2 = con.prepareStatement(query); 
            ACC2.execute();
            
            con.close();  
            f.setMsg("Deleted.");
            
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return f;
    }
     
    
    public feedback addStockdetail(String UserID ,stockdetail i){
        feedback f = new feedback();
        String query="";
        String IID = rs.findID("ingredient",i.getIngredientID());
        String LID = rs.findID("location",i.getLocationID());
       
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            
            query = "Insert into stockdetail \n"+ 
                    "values \n"+
                    "(null,?,?,'gram',?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            
            preparedStmt.setString(1, IID);
            preparedStmt.setInt(2, i.getAmount());
            preparedStmt.setString(3, LID); 
            preparedStmt.setString(4, UserID);
    System.out.println("addStockdetail: \n"+preparedStmt.toString());
            preparedStmt.executeUpdate();
                                   
            con.close(); 
            f.setMsg("Created.");
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return f;
    }
   
    
    
    public feedback addUrtable(Urtable i){
        feedback f = new feedback();
        String query="";
               
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            
            query = "Insert into Urtable \n"+ 
                    "values \n"+
                    "(null,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            
            preparedStmt.setString(1, i.getUserID());
            preparedStmt.setString(2, i.getToday());
            preparedStmt.setString(3, i.getRecipeID()); 
            
    System.out.println("addUrtable: \n"+preparedStmt.toString());
            preparedStmt.executeUpdate();
                                   
            con.close(); 
            f.setMsg("Created.");
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return f;
    }
    
    
    
    public <T> List<T> getUrtable_RCP(String keyword, String today){
        Urtable u1=new Urtable();
        List<T> U = new ArrayList();
        String query = "";
        query=  "select * from urtable \n" +
                "where today ='"+today+"' and \n"+
                    "UserID ='"+keyword+"';";
        
        try { 

            // Initialize the database 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet Result = stmt.executeQuery(query);  

            while (Result.next()) 
            {   
                Recipe r= rs.getRecipeByID_ricipe(Result.getString("RecipeID"));
                
                String A = Result.getString("UrtableID");  
                String D = Result.getString("RecipeID"); 
                String E = r.getRecipeTitle(); 
                String F = r.getMainImage();
                u1=new Urtable(A,null,null,D,E,F);
                U.add((T) u1);
            }    


            con.close();  

        } 
        catch (Exception e) { 
                e.printStackTrace(); 
        } 
        return U;
    }
    
    
    
    public feedback deleteUrtableRow(String UrtableID){
        String query;
        
        String RID="";
        feedback f = new feedback();
       
        query="delete from Urtable \n" + 
              "where UrtableID ='"+UrtableID+"'; \n";
              
        System.out.println("\n deleteUrtableRow:"+"\n"+query+"\n");
        
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
                      
            PreparedStatement ACC2 = con.prepareStatement(query); 
            ACC2.execute();
            
            con.close();  
            f.setMsg("Deleted.");
            
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return f;
    }
    
}