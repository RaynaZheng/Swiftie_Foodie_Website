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
public class SignupController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		/**
		 * Retrieve the entered username and password from the login.jsp form.
		 */

		try {

			/**
			 * If the isValid value is true, assign session attributes to the
			 * current user.
			 */
			if (PersonDao.signup(request.getParameter("un"), request.getParameter("pw"), request.getParameter("ln"), request.getParameter("fn"),
					request.getParameter("email"), request.getParameter("q1"), request.getParameter("a1"), request.getParameter("q2"), request.getParameter("a2"))) {
				

				response.sendRedirect("main.jsp"); //naming should correspond

			}

			else {
				/**
				 * Otherwise, redirect the user to the invalid login page and
				 * ask them to log in again with the proper credentials.
				 */
				response.sendRedirect("invalidLogin.jsp"); //naming should correspond
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