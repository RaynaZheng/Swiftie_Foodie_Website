package com.mie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mie.dao.RecipeDao;

public class RetrieveFavouritesController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static String fav_list = "/fav_list.jsp"; // jsp needed here
	private RecipeDao dao;

	/**
	 * Constructor for this class.
	 */
	public RetrieveFavouritesController() {
		super();
		dao = new RecipeDao();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/**
		 * This method handles the retrieval of the search keyword entered by
		 * the user.
		 */
		
		
		RequestDispatcher view = request.getRequestDispatcher(fav_list);
		HttpSession session = request.getSession(false);
		
		String person_username = request.getParameter("usrname");
		
		// check whether this user has already logged in or not
		if (session == null || session.getAttribute("loggedInUser") == null) {//jsp class!!!!!!
			response.sendRedirect("userLogged.jsp");
		}else{
			request.setAttribute("Itshouldcorrespondtojsp", dao.getFavorites(person_username));
				//return boolean
			}
		
		/**
		 * Redirect to the search results page after the list of students
		 * matching the keywords has been retrieved.
		 */

		view.forward(request, response);
	}
}
