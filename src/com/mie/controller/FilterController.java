package com.mie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.RecipeDao;

public class FilterController {//need a step to trigger the search function
	/**
	 * This class only handles the SEARCH feature of the web app.
	 * 
	 * These are variables that lead to the appropriate JSP pages.
	 * 
	 * SEARCH_USER leads to the search results page.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String SEARCH_USER = "/searchRecipeResult.jsp";// where is this jsp??
	private RecipeDao dao; //connection to database
	private SearchController searchController;//call search 

	/**
	 * Constructor for this class.
	 */
	public FilterController() {
		super();
		dao = new RecipeDao();
		searchController = new SearchController();//initialize search controller
	}

	
}
