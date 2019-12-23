package com.mie.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.RecipeDao;
import com.mie.model.Recipe;

public class RecommendDailyRecipe extends HttpServlet{
	
	
	public String MealType(){
		
		TimeZone timeZone = TimeZone.getTimeZone("GMT-5");
        Calendar rightNow = Calendar.getInstance(timeZone);
        int hr = rightNow.get(Calendar.HOUR_OF_DAY);
        String type = new String();
        
        if (hr < 12 && hr >= 0) {
            type = "Breakfast";
        } else if (hr >= 12 && hr < 16) {
            type = "Lunch";
        } else if (hr >= 16 && hr < 24) {
            type = "Dinner";
        }
        return type;
	}
	
	private static final long serialVersionUID = 1L;
	private static String Recommend_Recipe = "/main.jsp"; // jsp needed here
	private RecipeDao dao;

	/**
	 * Constructor for this class.
	 */
	public RecommendDailyRecipe() {
		
		super();
		dao = new RecipeDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("recommendrecipes", dao.getRecommendedRecipe(MealType())); 
		
	
		/**
		 * Redirect to the search results page after the list of students
		 * matching the keywords has been retrieved.
		 */
		
		response.sendRedirect("main.jsp");
	}
	
	// see RecipeDao, we think we should directly call this method in controller
/*
	public ArrayList<Recipe> getRecommendation(){
		
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from Recipe where Recipe_Type = '" + MealType() + "'");
			ResultSet rs = preparedStatement.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData(); 
			int columnCount = rsmd.getColumnCount();
			ArrayList<Recipe> recommendation_list = new ArrayList<Recipe>(columnCount);
			
			while (rs.next()) {              
			   int i = 1;
			   while(i <= columnCount) {
			        recommendation_list.add(rs.getString(i++));
			   }
			
			Random rand = new Random(); 
			
			ArrayList<Recipe> picked_recommendation = new ArrayList<Recipe>();
			
			for (int j = 0; j < 4; j++){
				 int randomIndex = rand.nextInt(recommendation_list.size()); 
				  
		            // add element in temporary list 
		         picked_recommendation.add(recommendation_list.get(randomIndex)); 
			}
			return picked_recommendation;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
*/
	
	
	}