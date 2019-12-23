package com.mie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.PersonDao;
import com.mie.dao.RecipeDao;

import java.util.Random ;

public class ForgotPWController {
	private static final long serialVersionUID = 1L;
	private static String ForgotPassword = "/ForgotPassword.jsp";//jsp name to be changed
	private PersonDao dao; //connection to database

	/**
	 * Constructor for this class.
	 */
	public ForgotPWController() {
		super();
		dao = new PersonDao();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/**
		 * This method handles the retrieval of the search keyword entered by
		 * the user.
		 */
		//String email = request.getParameter("email");// get email from the user
		String answer = request.getParameter("answer"); 
		String username = request.getParameter("username");
		
		Random random = new Random();
		Integer qNum = random.nextInt(2)+1;
		
		RequestDispatcher view = request.getRequestDispatcher(ForgotPassword);
		
		request.setAttribute("question", dao.getQuestion(username,qNum));//return list of recipe
		/**
		 * Redirect to the search results page after the list of recipes
		 * matching the keywords has been retrieved.
		 */
		if(answer == dao.getAnswer(username, qNum)){
			response.sendRedirect("resetPassword.jsp"); //naming should correspond
		}//does not print any error messages if the answer is incorrect
		
		view.forward(request, response);
	}
}
