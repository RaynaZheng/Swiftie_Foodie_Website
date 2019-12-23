package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.mie.model.Recipe;
import com.mie.util.DbUtil;

public class RecipeDao {
	/**
	 * This class handles all of the Student-related methods
	 * (add/update/delete/get).
	 */

	private Connection connection;

	public RecipeDao() {
		/**
		 * Get the database connection.
		 */
		connection = DbUtil.getConnection();
	}

	/**
	 * did not change add, delete or update method
	 * @param recipe
	 */
	
	public void addRecipe(Recipe recipe) {
		/**
		 * This method adds a new recipe to the database.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into recipe(Recipe_Name,Recipe_Description,Recipe_Type,Recipe_Time,Recipe_Restriction,Recipe_Instruction) values (?, ?, ?, ?, ?, ? )");
			// Parameters start with 1
			preparedStatement.setString(1, recipe.getRecipeName());
			preparedStatement.setString(2, recipe.getRecipeDescrip());
			preparedStatement.setString(3, recipe.getRecipeType());
			preparedStatement.setInt(4, recipe.getRecipeTime());
			preparedStatement.setString(5, recipe.getRecipeRestrict());
			preparedStatement.setString(6, recipe.getRecipeInstruction());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteRecipe(int recipeid) {
		/**
		 * This method deletes a recipe from the database.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from Recipe where Recipe_ID=?");
			// Parameters start with 1
			preparedStatement.setInt(1, recipeid);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateRecipe(Recipe recipe) {
		/**
		 * This method updates a recipe's information into the database.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update Recipe set Recipe_Name=?, Recipe_Description=?, Recipe_Type=?, Recipe_Time=?, Recipe_Restriction=?, Recipe_Instruction=?"
							+ " where Recipe_ID=?");
			// Parameters start with 1
			preparedStatement.setString(1, recipe.getRecipeName());
			preparedStatement.setString(2, recipe.getRecipeDescrip());
			preparedStatement.setString(3, recipe.getRecipeType());
			preparedStatement.setInt(4, recipe.getRecipeTime());
			preparedStatement.setString(5, recipe.getRecipeRestrict());
			preparedStatement.setString(6, recipe.getRecipeInstruction());
			preparedStatement.setInt(7, recipe.getRecipeid());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Recipe> getAllRecipes() {
		/**
		 * This method returns the list of all students in the form of a List
		 * object.
		 */
		List<Recipe> listof_recipe = new ArrayList<Recipe>();
		try {
			Statement statement = connection.createStatement();
			// System.out.println("getting students from table");
			ResultSet rs = statement.executeQuery("SELECT * FROM Recipe R INNER JOIN Recipe_Link L ON R.Recipe_ID=L.Recipe_ID");
			
			
			while (rs.next()) {
				Recipe recipe = new Recipe();
				recipe.setRecipeid(rs.getInt("R.Recipe_ID"));
				recipe.setRecipeName(rs.getString("Recipe_Name"));
				recipe.setRecipeDescrip(rs.getString("Recipe_Description"));
				recipe.setRecipeType(rs.getString("Recipe_Type"));
				recipe.setRecipeTime(rs.getInt("Recipe_Time"));
				recipe.setRecipeRestrict(rs.getString("Recipe_Restrictions"));
				recipe.setRecipeInstruction(rs.getString("Recipe_Instruction"));
				recipe.setLink(rs.getString("Recipe_Picture"));
				
				int id = rs.getInt("R.Recipe_ID");
				ResultSet rs1 = statement.executeQuery("SELECT Raw_Material, Raw_Material_Measurement, Material_Unit FROM Cook WHERE Recipe_ID = "+id+";");
				
				String ingredients = "";
				while(rs1.next()){
					ingredients=rs.getDouble("Raw_Material_Measurement")+rs.getString("Material_Unit")+rs.getString("Raw_Material")+"\n";
				}
				recipe.setIngredients(ingredients);
				listof_recipe.add(recipe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listof_recipe;
	}

	public Recipe getRecipeById(int recipeid) {
		/**
		 * This method retrieves a student by their Recipe_ID number.
		 * 
		 * Currently not used in the sample web app, but code is left here for
		 * your review.
		 */
		Recipe recipe = new Recipe();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from (Recipe R INNER JOIN Cook C ON R.Recipe_ID = C.Recipe_ID) INNER JOIN Recipe_Link L ON R.Recipe_ID=L.Recipe_ID where C.Recipe_ID=?");
			preparedStatement.setInt(1, recipeid);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				recipe.setRecipeid(rs.getInt("Recipe_ID"));
				recipe.setRecipeName(rs.getString("Recipe_Name"));
				recipe.setRecipeDescrip(rs.getString("Recipe_Description"));
				recipe.setRecipeType(rs.getString("Recipe_Type"));
				recipe.setRecipeTime(rs.getInt("Recipe_Time"));
				recipe.setRecipeRestrict(rs.getString("Recipe_Restrictions"));
				recipe.setRecipeInstruction(rs.getString("Recipe_Instruction"));
				recipe.setLink(rs.getString("Recipe_Picture").substring(1, rs.getString("Recipe_Picture").length()-1));
				
				int id = rs.getInt("Recipe_ID");
				System.out.println("ID is: "+id);
				Statement statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery("SELECT Raw_Material, Raw_Material_Measurement, Material_Unit FROM Cook WHERE Recipe_ID = "+id+";");
				
				String ingredients = "";
				while(rs1.next()){
					
					ingredients+=rs1.getDouble("Raw_Material_Measurement")+ " " + rs1.getString("Material_Unit")+ " " + rs1.getString("Raw_Material")+"\n";
				}
				recipe.setIngredients(ingredients);
				System.out.println("sha bi");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("da sha bi");
		return recipe;
	}

	public List<Recipe> getRecipeByKeyword(String keyword, String preptime, String Restrict) {
		/**
		 * This method retrieves a list of recipes that matches the keyword
		 * entered by the user.
		 */
		List<Recipe> matched_recipes = new ArrayList<Recipe>();
		try {
			System.out.println("keyword is "+keyword);
			System.out.println("pretime is "+preptime);
			System.out.println("restriction is "+Restrict);
			
			//PreparedStatement preparedStatement = connection
					//.prepareStatement("select * from (Recipe R INNER JOIN Cook C ON R.Recipe_ID = C.Recipe_ID) INNER JOIN Recipe_Link L ON R.Recipe_ID=L.Recipe_ID where R.Recipe_Name LIKE ? OR R.Recipe_Description LIKE ? OR R.Recipe_Type LIKE ? OR R.Recipe_TimeRange LIKE ? OR R.Recipe_Restrictions LIKE ? OR R.Recipe_Instruction LIKE ? OR C.Raw_Material LIKE ?");
			PreparedStatement preparedStatement = null;

//			if(keyword.equals("") && preptime.equals("showall") && Restrict.equals("showall")){
//				preparedStatement = connection.prepareStatement("select * from (Recipe R INNER JOIN Cook C ON R.Recipe_ID = C.Recipe_ID) INNER JOIN Recipe_Link L ON R.Recipe_ID=L.Recipe_ID");	
//			}
			if(preptime.equals("showall") && Restrict.equals("showall")){
				preparedStatement = connection.prepareStatement("select * from (Recipe R INNER JOIN Cook C ON R.Recipe_ID = C.Recipe_ID) INNER JOIN Recipe_Link L ON R.Recipe_ID=L.Recipe_ID WHERE C.Raw_Material LIKE ?");
				preparedStatement.setString(1, "%"+keyword+"%");
				System.out.println(1111);

			}else if(keyword.equals("") && preptime.equals("showall")){
				preparedStatement = connection.prepareStatement("select * from (Recipe R INNER JOIN Cook C ON R.Recipe_ID = C.Recipe_ID) INNER JOIN Recipe_Link L ON R.Recipe_ID=L.Recipe_ID WHERE R.Recipe_Restrictions = ?");
				preparedStatement.setString(1, Restrict);
				System.out.println(2222);
				
			}else if(keyword.equals("") && Restrict.equals("showall")){
				preparedStatement = connection.prepareStatement("select * from (Recipe R INNER JOIN Cook C ON R.Recipe_ID = C.Recipe_ID) INNER JOIN Recipe_Link L ON R.Recipe_ID=L.Recipe_ID WHERE R.Recipe_TimeRange = ?");
				preparedStatement.setString(1, preptime);
				System.out.println(3333); 

			}else if(preptime.equals("showall")){
				preparedStatement = connection.prepareStatement("select * from (Recipe R INNER JOIN Cook C ON R.Recipe_ID = C.Recipe_ID) INNER JOIN Recipe_Link L ON R.Recipe_ID=L.Recipe_ID WHERE C.Raw_Material LIKE ? AND R.Recipe_Restrictions = ?");
				preparedStatement.setString(1, "%"+keyword+"%");
				preparedStatement.setString(2, Restrict);
				System.out.println(4444);

			}else if(Restrict.equals("showall")){
				preparedStatement = connection.prepareStatement("select * from (Recipe R INNER JOIN Cook C ON R.Recipe_ID = C.Recipe_ID) INNER JOIN Recipe_Link L ON R.Recipe_ID=L.Recipe_ID WHERE C.Raw_Material LIKE ? AND R.Recipe_TimeRange = ?");
				preparedStatement.setString(1, "%"+keyword+"%");
				preparedStatement.setString(2, preptime);
				System.out.println(5555);

			}else if(keyword.equals("")){
				preparedStatement = connection.prepareStatement("select * from (Recipe R INNER JOIN Cook C ON R.Recipe_ID = C.Recipe_ID) INNER JOIN Recipe_Link L ON R.Recipe_ID=L.Recipe_ID WHERE R.Recipe_Restrictions = ? AND R.Recipe_TimeRange = ?");
				preparedStatement.setString(1, Restrict);
				preparedStatement.setString(2, preptime);
				System.out.println(6666);

			}else{
				preparedStatement = connection.prepareStatement("select * from (Recipe R INNER JOIN Cook C ON R.Recipe_ID = C.Recipe_ID) INNER JOIN Recipe_Link L ON R.Recipe_ID=L.Recipe_ID WHERE C.Raw_Material LIKE ? AND R.Recipe_Restrictions = ? AND R.Recipe_TimeRange = ?");
				preparedStatement.setString(1, "%"+keyword+"%");
				preparedStatement.setString(2, Restrict);
				preparedStatement.setString(3, preptime);
				System.out.println(7777);

			}
			
			
			
			ResultSet rs = preparedStatement.executeQuery();
			
			System.out.println(rs.next());
			while (rs.next()) {
				
				Recipe recipe = new Recipe();
				recipe.setRecipeid(rs.getInt("Recipe_ID"));
				recipe.setRecipeName(rs.getString("Recipe_Name"));
				recipe.setRecipeDescrip(rs.getString("Recipe_Description"));
				recipe.setRecipeType(rs.getString("Recipe_Type"));
				recipe.setRecipeTime(rs.getInt("Recipe_Time"));
				recipe.setRecipeRestrict(rs.getString("Recipe_Restrictions"));
				recipe.setRecipeInstruction(rs.getString("Recipe_Instruction"));
				recipe.setLink(rs.getString("Recipe_Picture").substring(1, rs.getString("Recipe_Picture").length()-1));
				

				int id = rs.getInt("Recipe_ID");
				System.out.println("ID is: "+id);
				Statement statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery("SELECT Raw_Material, Raw_Material_Measurement, Material_Unit FROM Cook WHERE Recipe_ID = "+id+";");
				
				String ingredients = "";
				while(rs1.next()){
					
					ingredients+=rs1.getDouble("Raw_Material_Measurement")+ " " + rs1.getString("Material_Unit")+ " " + rs1.getString("Raw_Material")+"\n";
				}
				recipe.setIngredients(ingredients);
				
				matched_recipes.add(recipe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("There are "+matched_recipes.size());
//		for (int i =0;i< matched_recipes.size();i++){
//			System.out.println(matched_recipes.get(i).getLink());
//		}

		return matched_recipes;
		
	}


	
	public List<Recipe> getRecommendedRecipe(String type){
		
		List<Recipe> recommendation_list = new ArrayList<Recipe>();
		List<Recipe> picked_recommendation = new ArrayList<Recipe>();
		
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from Recipe R INNER JOIN Recipe_Link L ON R.Recipe_ID=L.Recipe_ID where R.Recipe_Type = '"+type+"'");
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Recipe recipe = new Recipe();
				recipe.setRecipeid(rs.getInt("Recipe_ID"));
				recipe.setRecipeName(rs.getString("Recipe_Name"));
				recipe.setRecipeDescrip(rs.getString("Recipe_Description"));
				recipe.setRecipeType(rs.getString("Recipe_Type"));
				recipe.setRecipeTime(rs.getInt("Recipe_Time"));
				recipe.setRecipeRestrict(rs.getString("Recipe_Restrictions"));
				recipe.setRecipeInstruction(rs.getString("Recipe_Instruction"));
				recipe.setLink(rs.getString("Recipe_Picture").substring(1, rs.getString("Recipe_Picture").length()-1));
				
				int id = rs.getInt("Recipe_ID");
				Statement statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery("SELECT Raw_Material, Raw_Material_Measurement, Material_Unit FROM Cook WHERE Recipe_ID = "+id+";");
				
				String ingredients = "";
				while(rs1.next()){
					ingredients+=rs1.getDouble("Raw_Material_Measurement")+rs1.getString("Material_Unit")+rs1.getString("Raw_Material")+"\n";
				}
				recipe.setIngredients(ingredients);
				
//				for (int i =0;i< recommendation_list.size();i++){
//					System.out.println(recommendation_list.get(i).getRecipeName());
//				}
				
				recommendation_list.add(recipe);
			}		
			
			Random rand = new Random(); 
			HashSet hs = new HashSet();
			
			//System.out.println("recommendation list size is: "+recommendation_list.size());
			
			while(hs.size()<4){
				int randomIndex = rand.nextInt(recommendation_list.size()); 
				hs.add(randomIndex);  
		            // add element in temporary list
				//System.out.println(randomIndex);
			}
			Iterator iter = hs.iterator();
			
			while(iter.hasNext()){
				//System.out.println(iter.next()); 
				picked_recommendation.add(recommendation_list.get((Integer)iter.next()));
			}
		
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return picked_recommendation;
	}
	
	public List<Recipe> getFavorites(String usrname){
		List<Recipe> favorite_list = new ArrayList<Recipe>();
		
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from ((Recipe R INNER JOIN Recipe_Link L ON R.Recipe_ID=L.Recipe_ID) INNER JOIN Favourites F ON R.Recipe_ID = F.Recipe_ID) INNER JOIN Person P ON P.Person_UserName = F.Person_UserName WHERE P.Person_UserName = '" + usrname + "';");
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Recipe recipe = new Recipe();
				recipe.setRecipeid(rs.getInt("Recipe_ID"));
				recipe.setRecipeName(rs.getString("Recipe_Name"));
				recipe.setRecipeDescrip(rs.getString("Recipe_Description"));
				recipe.setRecipeType(rs.getString("Recipe_Type"));
				recipe.setRecipeTime(rs.getInt("Recipe_Time"));
				recipe.setRecipeRestrict(rs.getString("Recipe_Restrictions"));
				recipe.setRecipeInstruction(rs.getString("Recipe_Instruction"));
				recipe.setLink(rs.getString("Recipe_Picture"));
				
				int id = rs.getInt("Recipe_ID");
				Statement statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery("SELECT Raw_Material, Raw_Material_Measurement, Material_Unit FROM Cook WHERE Recipe_ID = "+id+";");
				
				String ingredients = "";
				while(rs1.next()){
					ingredients+=rs1.getDouble("Raw_Material_Measurement")+rs1.getString("Material_Unit")+rs1.getString("Raw_Material")+"\n";
				}
				recipe.setIngredients(ingredients);
				
				favorite_list.add(recipe);}
			} catch(SQLException e){
				e.printStackTrace();
			}
			return favorite_list;
	}
	
}