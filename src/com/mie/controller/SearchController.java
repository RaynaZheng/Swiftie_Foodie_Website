package com.mie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.RecipeDao;
import com.mie.model.Recipe;


public class SearchController extends HttpServlet{
	
	/**
	 * This class only handles the SEARCH feature of the web app.
	 * 
	 * These are variables that lead to the appropriate JSP pages.
	 * 
	 * SEARCH_USER leads to the search results page.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String SEARCH_USER = "/result.jsp";// where is this jsp??
	private RecipeDao dao; //connection to database

	/**
	 * Constructor for this class.
	 */
	public SearchController() {
		super();
		dao = new RecipeDao();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String forward = "";
		String keyword = request.getParameter("keyword");// get keyword from the user
		String pretime = request.getParameter("preptime");//get option outcome from the user
		String vege = request.getParameter("vegetarianfood"); // get the check value from the user
		
		
		request.setAttribute("keyword", keyword);
		request.setAttribute("resultrecipes", dao.getRecipeByKeyword(keyword, pretime, vege));//return list of recipe
		
		forward = SEARCH_USER;
		

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	
		
	//	response.sendRedirect("result.jsp"); //naming should correspond

		
		
		
		//view.forward(request, response);
		
		
	}
}

