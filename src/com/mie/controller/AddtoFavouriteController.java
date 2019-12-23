package com.mie.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mie.dao.FavouriteDao;
import com.mie.dao.RecipeDao;
import com.mie.model.Person;

public class AddtoFavouriteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static String fav = "/main.jsp"; // jsp needed here
	private FavouriteDao dao;

	/**
	 * Constructor for this class.
	 */
	public AddtoFavouriteController() {
		
		super();

		dao = new FavouriteDao();
	
	}

//	public String getName(HttpServletRequest request){
//		Principal user = request.getUserPrincipal();  
//		String p_Id = user.getName();
//		return p_Id;
//	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/**
		 * This method handles the retrieval of the search keyword entered by
		 * the user.
		 */
		String forward = "";
		HttpSession session = request.getSession();
		System.out.println(session);

		if(session.getAttribute("username")!=null&&session.getAttribute("username")!=""){
			
			System.out.println("here");
			Person ppl = (Person)session.getAttribute("loggedInUser");
			String fn = (String)session.getAttribute("firstname");
			String ln = (String)session.getAttribute("lastname");
			String un = (String)session.getAttribute("username");
			String recipe_id = request.getParameter("recipe");
			
			int actual = Integer.parseInt(recipe_id.substring(5, recipe_id.length()-10));
			if(dao.checkLiked(un, actual)){
				System.out.println(1111);
				
				dao.addFavourite(true, true, actual, un); //unliked
				forward = "/main.jsp";
			}else{
				
				dao.addFavourite(true, false, actual, un);
				System.out.println(1111111111);
				forward = "/main.jsp";
			}
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
			
			
		
		} else{ 
			
			String msg = "Please login/signup first to save your recipes.;)";
			
			response.sendRedirect("main.jsp#login");
			
		
	}
	}
}




		
		/**
		RequestDispatcher view = request.getRequestDispatcher(fav);
		HttpSession session = request.getSession(true);
		String person_username = request.getParameter("usrname");
		int recipe_id = Integer.parseInt(request.getParameter("r_id"));
		
		// check whether this user has already logged in or not
		if (session == null || session.getAttribute("loggedInUser") == null) {//jsp class!!!!!!
			response.sendRedirect("userLogged.jsp");
		}else{
			// check whether this recipe is liked or not, then operate the "add" function
			if(dao.checkLiked(person_username, recipe_id)){
				request.setAttribute("Itshouldcorrespondtojsp", dao.addFavourite(true, true, recipe_id, person_username)); //unliked
			}else{
				request.setAttribute("Itshouldcorrespondtojsp", dao.addFavourite(true, false, recipe_id, person_username)); //add to favourites
				//return boolean
			}
		}*/
		
		/**
		 * Redirect to the search results page after the list of students
		 * matching the keywords has been retrieved.
		 */
