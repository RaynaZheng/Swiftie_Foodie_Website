package com.mie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mie.model.*;
import com.mie.dao.*;

/**
 * Servlet implementation for LoginController.
 * 
 * This class handles the login servlet and assigns session attributes for users
 * who succesfully log into the system.
 */
public class LoginController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		/**
		 * Retrieve the entered username and password from the login.jsp form.
		 */
		Person user = new Person();
		user.setUsername(request.getParameter("un"));
		user.setPassword(request.getParameter("pw"));

		try {
			/**
			 * Try to see if the user can log in.
			 */
			user = PersonDao.login(user);

			/**
			 * If the isValid value is true, assign session attributes to the
			 * current user.
			 */
			if (user.isValid()) {

				HttpSession session = request.getSession(true);
				
				session.setAttribute("loggedInUser", user); //should correspond to jsp file
				session.setAttribute("username", user.getUsername());
				session.setAttribute("firstname", user.getFirstName());
				session.setAttribute("lastname", user.getLastName());
			
				/**
				 * Redirect to the users-only home page.
				 */
				response.sendRedirect("main.jsp"); //naming should correspond

			}

			else {
				/**
				 * Otherwise, redirect the user to the invalid login page and
				 * ask them to log in again with the proper credentials.
				 */
				response.sendRedirect("main.jsp#login"); //for testing purpose
			}
		}

		catch (Throwable theException) {
			/**
			 * Print out any errors.
			 */
			System.out.println(theException);
		}
	}
}

