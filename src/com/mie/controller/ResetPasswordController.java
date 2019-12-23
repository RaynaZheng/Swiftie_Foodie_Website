package com.mie.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mie.dao.PersonDao;
import com.mie.dao.RecipeDao;
import com.mie.model.Person;

//do we need to connect to jsp files
public class ResetPasswordController extends HttpServlet {
	private PersonDao dao;
	public ResetPasswordController() {
		super();
		dao = new PersonDao();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		String pw = request.getParameter("pw");
		String username = request.getParameter("username"); //do we require the user to re-enter his email

		
		HttpSession session = request.getSession(true);
		session.setAttribute("password", pw);
		dao.updatePassword(pw, username);
	}
}
