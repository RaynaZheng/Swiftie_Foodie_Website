package com.mie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mie.dao.RecipeDao;

public class GetFavouriteController extends HttpServlet{
	//private static final long serialVersionUID = 1L;
	//private static String favourite = "/main.jsp"; // jsp needed here
	private RecipeDao dao;

	/**
	 * Constructor for this class.
	 */
	public GetFavouriteController() {
		
		super();
		dao = new RecipeDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("??????????????????????");
		HttpSession session = request.getSession();
		String un = (String)session.getAttribute("username");
		request.setAttribute("fav", dao.getFavorites(un)); 
		
	
		/**
		 * Redirect to the search results page after the list of students
		 * matching the keywords has been retrieved.
		 */
		
		response.sendRedirect("main.jsp");
	}
	
}