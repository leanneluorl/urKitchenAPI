package com.mycompany.UrKitchen.service;

import com.mycompany.UrKitchen.model.DatabaseConnection;
import com.mycompany.UrKitchen.model.FoodType;
import com.mycompany.UrKitchen.model.Ingredient;
import com.mycompany.UrKitchen.model.Instruction;
import com.mycompany.UrKitchen.model.Recipe;
import com.mycompany.UrKitchen.model.SearchRCPbyIGD_selections;
import com.mycompany.UrKitchen.model.item;
import com.mycompany.UrKitchen.model.recipeIngredient;
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
public class RecipeService {
    
    
    public <T> List<T>getRCPbyCatalog(String table, String itemID){
        Recipe u1;
        List<T> U = new ArrayList();
        String query = "";
        String tableID =table+"ID";
        
        query=  "select recipeID, RecipeTitle,RecipeInfo, MainImage, viewtimes from recipe\n" +
                "where "+tableID+" = "+itemID+" \n"+
                "order by viewtimes desc;";   
    System.out.println("\ngetRecipebyCatalog: \n"+query);
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query);  

            while (rs.next()) 
            { 
                int A = rs.getInt("RecipeID");  
                String B = rs.getString("RecipeTitle"); 
                String C = rs.getString("RecipeInfo");
                String D = rs.getString("MainImage");
              /*Timestamp E = rs.getTimestamp("CreateDate"); 
                String F = rs.getString("Author");
                String G = rs.getString("AdminID");
                String H = rs.getString("UserID");
                    if(G==null){G="-";};
                    if(H==null){H="-";};*/
                int I = rs.getInt("ViewTimes");
              /*String J = rs.getString("DietType");
                String K = rs.getString("Cuisine");
                int L = rs.getInt("PerServeing_For");
                int M = rs.getInt("CookingTime");*/
                u1 = new Recipe(A,B,C,D,null,null,null,null,I,null,null,0,0);
                U.add((T) u1);
            }    

            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
    
    
    public <T> List<T> getRecipe(String keyword, String order, String sort){
        Recipe u1;
        List<T> U = new ArrayList();
        String query = "";
        
        
        if(keyword.equalsIgnoreCase("all")){
            query=  "select RecipeID, RecipeTitle, RecipeInfo, MainImage, r.CreateDate, Author, AdminID , USerID, ViewTimes, DietType, Cuisine, PerServeing_For, CookingTime from Recipe r\n" +
                    "LEFT JOIN admin a ON a.AccID = r.Author\n" +
                    "LEFT JOIN User u ON u.AccID = r.Author\n" +
                    "LEFT JOIN dietType d ON d.dietTypeid = r.dietTypeid\n" +
                    "LEFT JOIN Cuisine c ON c.cuisineid = r.cuisineid \n"  +  
                    "Order by "+order+" "+sort;
        }
        else{
            query=  "select r.RecipeID, RecipeTitle, RecipeInfo, MainImage, r.CreateDate, Author, AdminID , USerID, ViewTimes, DietType, Cuisine, i.Ingredient, PerServeing_For, CookingTime from Recipe r\n" +
                    "LEFT JOIN admin a ON a.AccID = r.Author\n" +
                    "LEFT JOIN User u ON u.AccID = r.Author\n" +
                    "LEFT JOIN dietType d ON d.dietTypeid = r.dietTypeid \n" +
                    "LEFT JOIN Cuisine c ON c.cuisineid = r.cuisineid \n"  + 
                    "LEFT JOIN recipeingredient ri ON ri.recipeid = r.recipeid \n" +
                    "LEFT JOIN ingredient i ON ri.ingredientid = i.ingredientid \n" +
                    "where r.recipeID ='"+keyword+"' or \n"+
                    "RecipeTitle like'%"+keyword+"%' or \n"+
                    "Author like'%"+keyword+"%' or \n"+
                    "UserID like'%"+keyword+"%' or \n"+
                    "AdminID like'%"+keyword+"%' or \n"+
                    "Email like'%"+keyword+"%' or \n"+
                    "Cuisine like'%"+keyword+"%' or \n"+
                    "dietType like'%"+keyword+"%' or \n"+
                    "ingredient like '%"+keyword+"%'" +
                    "group by r.recipeID \n" +
                    "order by "+order+" "+sort;    
        }
        //System.out.println("\ngetRecipe: \n"+query);
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query);  

            while (rs.next()) 
            { 
                int A = rs.getInt("RecipeID");  
                String B = rs.getString("RecipeTitle"); 
                String C = rs.getString("RecipeInfo");
                String D = rs.getString("MainImage");
                Timestamp E = rs.getTimestamp("CreateDate"); 
                String F = rs.getString("Author");
                String G = rs.getString("AdminID");
                String H = rs.getString("UserID");
                    if(G==null){G="-";};
                    if(H==null){H="-";};
                int I = rs.getInt("ViewTimes");
                String J = rs.getString("DietType");
                String K = rs.getString("Cuisine");
                int L = rs.getInt("PerServeing_For");
                int M = rs.getInt("CookingTime");
                u1 = new Recipe(A,B,C,D,E,F,G,H,I,J,K,L,M);
                U.add((T) u1);
            }    

            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
    
    
    public <T> List<T> getRecipeByID(String keyword){
        Recipe u1;
        List<T> U = new ArrayList();
        String query = "";
        
        query=  "select RecipeID, RecipeTitle, RecipeInfo, MainImage, r.CreateDate, Author, AdminID , UserID, ViewTimes, DietType, Cuisine, PerServeing_For, CookingTime from Recipe r\n" +
                "LEFT JOIN admin a ON a.AccID = r.Author\n" +
                "LEFT JOIN User u ON u.AccID = r.Author\n" +
                "LEFT JOIN dietType d ON d.dietTypeid = r.dietTypeid\n" +
                "LEFT JOIN Cuisine c ON c.cuisineid = r.cuisineid \n"  +                  
                "where recipeID ='"+keyword+"'";    
        
       //System.out.println("\ngetRecipe: \n"+query);
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query);  

            while (rs.next()) 
            { 
                int A = rs.getInt("RecipeID");  
                String B = rs.getString("RecipeTitle"); 
                String C = rs.getString("RecipeInfo");
                String D = rs.getString("MainImage");
                Timestamp E = rs.getTimestamp("CreateDate"); 
                String F = rs.getString("Author");
                String G = rs.getString("AdminID");
                String H = rs.getString("UserID");
                    if(G==null){G="-";};
                    if(H==null){H="-";};
                int I = rs.getInt("ViewTimes");
                String J = rs.getString("DietType");
                String K = rs.getString("Cuisine");
                int L = rs.getInt("PerServeing_For");
                int M = rs.getInt("CookingTime");
                u1 = new Recipe(A,B,C,D,E,F,G,H,I,J,K,L,M);
                U.add((T) u1);
            }    

            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
    
    
    public <T> List<T> getRecipeByID_instru(String keyword){
        Instruction u1;
        List<T> U = new ArrayList();
        String query = "";
        
        query=  "select * from Instruction r\n" +
                "where recipeID ='"+keyword+"' order by StepNo \n";    
        
       //System.out.println("\ngetRecipe: \n"+query);
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query);  

            while (rs.next()) 
            { 
                int A = rs.getInt("InstructionID");  
                int B = rs.getInt("StepNo"); 
                String C = rs.getString("Instruction");
                String D = rs.getString("instructionPic");
                
                u1 = new Instruction(A,B,C,D);
                U.add((T) u1);
            }    

            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
   
    public <T> List<T> getRecipeByID_IGD(String keyword){
        
        recipeIngredient u1;
        List<T> U = new ArrayList();
        String query = "";
        
        query=  "Select RecipeIngredientID, Ingredient, r.IngredientID,f.foodtypeID, foodtype, r.Amount, r.Unit from RecipeIngredient r\n" +
                "left join ingredient i on r.ingredientID = i.IngredientID\n" +
                "left join foodtype f on f.FoodTypeID = i.FoodTypeID\n" +
                "Where recipeID = "+keyword+" order by  foodtype;";    
        
       //System.out.println("\ngetRecipe: \n"+query);
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query);  

            while (rs.next()) 
            { 
                String A = rs.getString("RecipeIngredientID");  
                String B = rs.getString("Ingredient"); 
                String C = rs.getString("FoodType");
                int D = rs.getInt("Amount"); 
                String E = rs.getString("Unit");
                String F = rs.getString("FoodTypeID");
                u1 = new recipeIngredient(A,B,C,D,E,F);
                U.add((T) u1);
            }    

            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
  /*  public List<FoodType> getRecipeByID_IGD(String keyword){
        List<FoodType> f = new ArrayList();
        List<recipeIngredient> ftype =new ArrayList();
        FoodType Meat =  new FoodType("Meat",ftype);
        FoodType Vegetable =  new FoodType("Vegetable",ftype);
        FoodType Seafood =  new FoodType("Seafood",ftype);
        FoodType Sauce =  new FoodType("Sauce",ftype);
        FoodType Spice =  new FoodType("Spice",ftype);
        f.add(Meat); f.add(Vegetable); f.add(Seafood); f.add(Sauce); f.add(Spice);
        
        recipeIngredient u1;
        List<recipeIngredient> U = new ArrayList();
        String query = "";
        
        query=  "Select RecipeIngredientID, Ingredient, f.unique_Name, r.Amount, r.Unit from RecipeIngredient r\n" +
                "left join ingredient i on r.ingredientID = i.IngredientID\n" +
                "left join foodtype f on f.FoodTypeID = i.FoodTypeID\n" +
                "Where recipeID = "+keyword+" order by  foodtype, Ingredient;";    
        List<recipeIngredient> f1type=new ArrayList();
       //System.out.println("\ngetRecipe: \n"+query);
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query);  

            while (rs.next()) 
            {   
                String A = rs.getString("RecipeIngredientID");  
                String B = rs.getString("Ingredient"); 
                String C = rs.getString("unique_Name");
                int D = rs.getInt("Amount"); 
                String E = rs.getString("Unit");
                
                u1 = new recipeIngredient(A,B,C,D,E);
                U.add( u1);
                
                String FT = rs.getString("unique_Name");
                System.out.print(FT);
                for(FoodType f1:f){
                    if(f1.getFT()== "Meat" ){
                        f1type = f1.getListRI();
                        f1type.add(u1);
                    }
                    System.out.print(f1.getFT()+","+FT+"\n");
                }
                
            }    

            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return f;
    }
  */
    
    
    public <T> List<T> deleteRecipe(String table, String ItemID){
        item u1;
        List<T> U = new ArrayList();
        String query1, query2, query3;
        String ID = table+"ID";
        
        query1="delete from recipeingredient \n" + 
              "where "+ID+" ='"+ItemID+"'; \n";
        query2="delete from instruction \n" + 
              "where "+ID+" ='"+ItemID+"'; \n";
                               
        query3="delete from "+table+"\n" + 
              "where "+ID+" ='"+ItemID+"'; \n";
              
        System.out.println("\n deleteRecipe: \n"+query1+"\n"+query2+"\n"+query3);
        
        
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            PreparedStatement ACC = con.prepareStatement(query1); 
            ACC.execute();
            PreparedStatement ACC2 = con.prepareStatement(query2); 
            ACC2.execute();
            PreparedStatement ACC3 = con.prepareStatement(query3); 
            ACC3.execute();
             
            
               

            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
    
    
    public String deleteRecipe_instru(String table, String ItemID){
        
        String query1, query2, query3;
        String ID = table+"ID";
        String RID="";
        
        query1="Select recipeID from instruction \n" + 
              "where "+ID+" ='"+ItemID+"'; \n";
        query2="delete from instruction \n" + 
              "where "+ID+" ='"+ItemID+"'; \n";
              
        System.out.println("\n deleteRecipe_instru: \n"+query1+"\n"+query2+"\n");
        
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query1);  

            while (rs.next()) 
            { 
                RID = rs.getString("RecipeID");  
            }
           
            PreparedStatement ACC2 = con.prepareStatement(query2); 
            ACC2.execute();
            
            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return RID;
    }
    

    public String deleteRecipe_IGD(String table, String ItemID){
        
        String query1, query2, query3;
        String ID = table+"ID";
        String RID="";
        
        query1="Select RecipeID from recipeIngredient \n" + 
              "where "+ID+" ='"+ItemID+"'; \n";
        query2="delete from recipeIngredient \n" + 
              "where "+ID+" ='"+ItemID+"'; \n";
              
        System.out.println("\n deleteRecipe_IGD: \n"+query1+"\n"+query2+"\n");
        
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query1);  

            while (rs.next()) 
            { 
                RID = rs.getString("RecipeID");  
            }
           
            PreparedStatement ACC2 = con.prepareStatement(query2); 
            ACC2.execute();
            
            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return RID;
    }
    
    
    public <T> List<T> updateRecipeItem(String ID, Recipe r){
        List<T> U = new ArrayList();
        
        String DietTypeID = r.getDietTypeID();
        String CuisineID =r.getCuisineID();
        
        String query1 = "Select diettypeID from diettype where diettype like'%"+DietTypeID+"%' or \n"+
                        "diettypeID='"+DietTypeID+"';";
        String query2 = "Select cuisineID from cuisine where cuisine like'%"+CuisineID+"%' or \n"+
                        "cuisineID='"+CuisineID+"';";
        //System.out.println("\nquery:\n"+query1+"\n"+query2);
        int DTID=0;
        int CsID=0;
        
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query1);  
            while (rs.next()) 
            { 
               DTID = rs.getInt("diettypeID");  
            } 
            Statement stmt2 = con.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query2);  
            while (rs2.next()) 
            { 
               CsID = rs2.getInt("cuisineID");  
            } 
           
            String query3 = "Update recipe \n" +
                            "set RecipeTitle = ?,\n" +
                            "    RecipeInfo = ?,\n" +
                            "    MainImage= ?,\n" +
                            "    ViewTimes=?,\n" +
                            "    DietTypeID=?,\n" +
                            "    cuisineID=?, \n" +
                            "    PerServeing_For=?,\n" +
                            "    CookingTime=? \n" +
                            "where recipeID ="+ID+";";
            
            PreparedStatement preparedStmt = con.prepareStatement(query3);
            preparedStmt.setString(1, r.getRecipeTitle());
            preparedStmt.setString(2, r.getRecipeInfo());
            preparedStmt.setString(3, r.getMainImage());
            preparedStmt.setInt(4, r.getViewTimes());
            preparedStmt.setInt(5, DTID);
            preparedStmt.setInt(6, CsID);
            preparedStmt.setInt(7, r.getPerServeing_For());
            preparedStmt.setInt(8, r.getCookingTime());
            System.out.println("\nUpdate Recipe:\n"+preparedStmt.toString());
            
           
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            
            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
    
    
    public String updateRecipeItem_instru(String ID, Instruction i){
                
        String RID = "";
        String query1="Select recipeID from instruction \n" + 
              "where instructionID ='"+ID+"'; \n";
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
                       
            String query  = "Update Instruction \n" +
                            "set StepNo = ?, \n" +
                            "    Instruction = ?, \n" +
                            "    instructionPic = ? \n" +
                            "where InstructionID ="+ID+";";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, i.getStepNo());
            preparedStmt.setString(2, i.getInstruction());
            preparedStmt.setString(3, i.getInstructionPic());
            System.out.println("\nUpdate Recipe:\n"+preparedStmt.toString());
            preparedStmt.executeUpdate();
            
            
            
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query1);
            while (rs.next()) 
            { 
                RID = rs.getString("RecipeID");  
            }
            
            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return RID;
    }
   
    
    public String updateRecipeItem_IGD(String ID, recipeIngredient i){
                
        String RID = "";
        String query1="Select recipeID from recipeIngredient \n" + 
              "where recipeIngredientID ='"+ID+"'; \n";
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
                       
            String query  = "Update recipeIngredient \n" +
                            "set Amount = ?, \n" +
                            "    Unit = ? \n" +
                            "where recipeIngredientID ="+ID+";";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, i.getAmount());
            preparedStmt.setString(2, i.getUnit());
            System.out.println("\nUpdate Recipe:\n"+preparedStmt.toString());
            preparedStmt.executeUpdate();
            
            
            
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query1);
            while (rs.next()) 
            { 
                RID = rs.getString("RecipeID");  
            }
            
            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return RID;
    }
   
    
    public String addRecipeItem(Recipe r){
        String recipeID="";
        String query="";
        String AccID="";
        int DTID=Integer.parseInt(findID("DietType",r.getDietTypeID()));
        int CsID=Integer.parseInt(findID("Cuisine",r.getCuisineID()));
        if(r.getAdminID()!=null){
            AccID=findID("Admin",r.getAdminID());
        }else{
            AccID=findID("User",r.getAdminID());
        }
        
        
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            
            query = "Insert into Recipe \n"+ 
                    "values \n"+
                    "(null,?,?,null,now(),?,0,?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, r.getRecipeTitle());
            preparedStmt.setString(2, r.getRecipeInfo());
            preparedStmt.setString(3, AccID);
            preparedStmt.setInt(4, DTID);
            preparedStmt.setInt(5, CsID);
            preparedStmt.setInt(6, r.getPerServeing_For());
            preparedStmt.setInt(7, r.getCookingTime());
            
                System.out.println("\n"+preparedStmt.toString());
            preparedStmt.executeUpdate();
            
            String getID="Select recipeID from recipe order by RecipeID desc limit 1;";
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(getID);  
                while (rs.next()) 
                { 
                    recipeID = rs.getString("recipeID");  
                } 
            
            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return recipeID;
    }
    
    
    public String addRecipeItem_instru(int RID, Instruction i){
        String recipeID=Integer.toString(RID);
        String query="";
        
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            
            query = "Insert into Instruction \n"+ 
                    "values \n"+
                    "(null,?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, i.getStepNo());
            preparedStmt.setString(2, i.getInstruction());
            preparedStmt.setInt(3, RID);
            preparedStmt.setString(4, i.getInstructionPic());
                System.out.println("\n"+preparedStmt.toString());
            preparedStmt.executeUpdate();
                                   
            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return recipeID;
    }
   
    
    public String addRecipeItem_IGD(int RID, recipeIngredient i){
        String recipeID=Integer.toString(RID);
        String query="";
        String IID = findID("ingredient",i.getIngredientID());
        String queryCheck="select IngredientID from recipeingredient\n" +
                          "where recipeID = "+RID+";";
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            
            query = "Insert into recipeIngredient \n"+ 
                    "values \n"+
                    "(null,?,?,?,?)";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            
            preparedStmt.setString(1, IID);
            preparedStmt.setInt(2, i.getAmount());
            preparedStmt.setString(3, i.getUnit());
            preparedStmt.setInt(4, RID);
                System.out.println("\n"+preparedStmt.toString());
            preparedStmt.executeUpdate();
                                   
            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return recipeID;
    }
   
    
    ///////////////////
    public String findID(String table, String itemName){
        String query="";
        String tableID = table+"ID";
        if(table=="User"||table=="Admin"){
            query = "Select AccID from "+table+ " where "+tableID+ " = '"+itemName+"';"; 
        
        }
        else{    
            query = "Select "+tableID+" from "+table+ " where "+table+ " like '%"+itemName+"%' or \n"+
                        tableID+"='"+itemName+"';";
        }
        System.out.println(query);
        String ID="";
        
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query);  
            if(table=="Admin"||table=="User"){
                while (rs.next()) 
                { 
                    ID = rs.getString("AccID"); 
                } 
            }
            else{
                while (rs.next()) 
                { 
                    ID = rs.getString(tableID);   
                }
            }
         System.out.println(ID);    
            
            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return ID;
    
    }
    ///////////////////////////////////
    
    public <T> List<T> getRCPbyIGD_selections( SearchRCPbyIGD_selections ss){
        Recipe u1;
        List<T> U = new ArrayList();
        String query = "";
        String s1 = ss.getSelection1();
        String s2 = ss.getSelection2();
        String s3 = ss.getSelection3();
        
        if(ss.getSelection2().length()==0){
            System.out.println("s2 is null test");
            U = getRecipe(ss.getSelection1(), "viewtimes", "desc");
        }else if(ss.getSelection2().length()>0 && ss.getSelection3().length()==0){
            String S1ID = findID("ingredient", s1);
            String S2ID = findID("ingredient", s2);
            query="SELECT recipeID\n" +
                    "FROM recipeingredient AS a\n" +
                    "  JOIN recipeingredient AS b\n" +
                    "  USING (recipeID)\n" +
                    "WHERE a.IngredientID = "+S1ID+"\n" +
                    "  AND b.IngredientID = "+S2ID+" \n" +
                    "  group by recipeID;";
        
        
        } 
        
        //System.out.println("\ngetRecipe: \n"+query);
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query);  

            while (rs.next()) 
            { 
                u1 =getRecipeByID_ricipe(Integer.toString(rs.getInt("RecipeID")));
                U.add((T) u1);
            }    

            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return U;
    }
    
       public Recipe getRecipeByID_ricipe(String keyword){
        Recipe u1 = new Recipe();
        //List<T> U = new ArrayList();
        String query = "";
        
        query=  "select RecipeID, RecipeTitle, RecipeInfo, MainImage, r.CreateDate, Author, AdminID , UserID, ViewTimes, DietType, Cuisine, PerServeing_For, CookingTime from Recipe r\n" +
                "LEFT JOIN admin a ON a.AccID = r.Author\n" +
                "LEFT JOIN User u ON u.AccID = r.Author\n" +
                "LEFT JOIN dietType d ON d.dietTypeid = r.dietTypeid\n" +
                "LEFT JOIN Cuisine c ON c.cuisineid = r.cuisineid \n"  +                  
                "where recipeID ='"+keyword+"'";    
        
       //System.out.println("\ngetRecipe: \n"+query);
        try { 
            Connection con = DatabaseConnection.initializeDatabase(); 
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery(query);  

            while (rs.next()) 
            { 
                int A = rs.getInt("RecipeID");  
                String B = rs.getString("RecipeTitle"); 
                String C = rs.getString("RecipeInfo");
                String D = rs.getString("MainImage");
                Timestamp E = rs.getTimestamp("CreateDate"); 
                String F = rs.getString("Author");
                String G = rs.getString("AdminID");
                String H = rs.getString("UserID");
                    if(G==null){G="-";};
                    if(H==null){H="-";};
                int I = rs.getInt("ViewTimes");
                String J = rs.getString("DietType");
                String K = rs.getString("Cuisine");
                int L = rs.getInt("PerServeing_For");
                int M = rs.getInt("CookingTime");
                u1 = new Recipe(A,B,C,D,E,F,G,H,I,J,K,L,M);
                
            }    

            con.close();  
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return u1;
    }
    
}
