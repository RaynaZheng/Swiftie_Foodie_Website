package com.mie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mie.dao.RecipeDao;

public class GetRecipeController extends HttpServlet{
	private RecipeDao dao;

	/**
	 * Constructor for this class.
	 */
	public GetRecipeController() {
		
		super();
		dao = new RecipeDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("??????????????????????");
		
		String recipe_id = request.getParameter("idname");
		int actual = Integer.parseInt(recipe_id.substring(12, recipe_id.length()-3));
		request.setAttribute("detailed_recipe", dao.getRecipeById(actual)); 
		
	
		/**
		 * Redirect to the search results page after the list of students
		 * matching the keywords has been retrieved.
		 */
		
		RequestDispatcher view = request.getRequestDispatcher("/detail.jsp");
		view.forward(request, response);
	}
}
