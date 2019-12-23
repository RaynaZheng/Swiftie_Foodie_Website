package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mie.util.DbUtil;
import com.mie.model.*;
import com.mie.controller.*;
import com.mie.util.*;

public class PersonDao {

	/**
	 * This class handles the Member objects and the login component of the web
	 * app.
	 */
	
	static Connection currentCon = null;
	static Connection connection = null;
	static ResultSet rs = null;

	
	public static Person login(Person user) {

		/**
		 * This method attempts to find the member that is trying to log in by
		 * first retrieving the username and password entered by the user.
		 */
		Statement stmt = null;

		String username = user.getUsername();
		String password = user.getPassword();

		/**
		 * Prepare a query that searches the members table in the database
		 * with the given username and password.
		 */
		String searchQuery = "select * from Person where Person_UserName='"
				+ username + "' AND Person_Password='" + password + "'";

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			/**
			 * If there are no results from the query, set the member to false.
			 * The person attempting to log in will be redirected to the home
			 * page when isValid is false.
			 */
			
			if (!more) {
				user.setValid(false);
			}

			/**
			 * If the query results in an database entry that matches the
			 * username and password, assign the appropriate information to
			 * the Person object.
			 */
			else if (more) {
				String firstName = rs.getString("Person_FirstName");
				String lastName = rs.getString("Person_LastName");

				user.setFirstName(firstName);
				user.setLastName(lastName);
				// Do we need to set email here?
				user.setValid(true);
			}
		}

		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
		}
		/**
		 * Return the Member object.
		 */
		return user;

	}
	
	public static boolean signup(String username, String password, String lastName, String firstName, String email, String Q1, String A1, String Q2, String A2) {
		/**
		 * This method attempts to find the member that is trying to log in by
		 * first retrieving the username and password entered by the user.
		 */
		boolean signup = false;
		Person user = new Person();
		try {
			connection = DbUtil.getConnection();
			Statement statement = connection.createStatement();
			
			/*
			System.out.println("getting students from table");
			ResultSet rs1 = statement.executeQuery("select * from Person where Person_Email= '" + email +"';");
			ResultSet rs2 = statement.executeQuery("select * from Person where Person_Email= '" + email +"';");
			System.out.println("rs1 ="+rs1);
			System.out.println("rs2 ="+rs2);
			*/

				try {
					PreparedStatement preparedStatement1 = connection
							.prepareStatement("select max(Person_ID) from person");
					ResultSet max_personid = preparedStatement1.executeQuery();
					max_personid.next();
					PreparedStatement preparedStatement2 = connection
							.prepareStatement("insert into person(person_id,person_username,person_lastname,person_firstname,person_email,person_password,person_questiona, person_questionb,person_answera,person_answerb) values (?, ?, ?,?,?,?,?,?,?,?)");
					
					preparedStatement2.setInt(1, max_personid.getInt(1)+1);
					preparedStatement2.setString(2,username);
					preparedStatement2.setString(3, lastName);
					preparedStatement2.setString(4, firstName);
					preparedStatement2.setString(5, email);
					preparedStatement2.setString(6, password);
					preparedStatement2.setString(7, Q1);
					preparedStatement2.setString(8, Q2);
					preparedStatement2.setString(9, A1);
					preparedStatement2.setString(10, A2);
					preparedStatement2.executeUpdate();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
				signup = true;
		

		}catch (Exception e) {
			System.out.println("Sign up failed: An Exception has occurred! "+ e);
				}
		return signup;
			}
	
	public String getQuestion(String username,int qNum){
		ResultSet rs1=null;
		ResultSet rs2=null;
		String rs_str = null;
		try {
			connection = DbUtil.getConnection();
			Statement statement = connection.createStatement();
			// System.out.println("getting students from table");
			if (qNum == 1){
				rs1 = statement.executeQuery("select Person_QuestionA from Person where Person_Email= '" + username +";");
				if(rs1.next()){
					rs_str = rs1.getString(1);
				}
			} else if (qNum == 2){
				rs2 = statement.executeQuery("select Person_QuestionB from Person where Person_Email= '" + username +";");
				if(rs2.next()){
					rs_str = rs2.getString(1);
				}
			} 
		} catch(SQLException e){
			e.printStackTrace();
		}
		return rs_str;
	}
	
	public String getAnswer(String username,int aNum){
		ResultSet rs1=null;
		ResultSet rs2=null;
		String rs_str = null;
		try {
			connection = null;
			connection = DbUtil.getConnection();
			System.out.println("Are we here1");
			Statement statement = connection.createStatement();
			// System.out.println("getting students from table");
			if (aNum == 1){
				rs1 = statement.executeQuery("select Person_AnswerA from Person where Person_Email= '" + username +";");
				if(rs1.next()){
					rs_str = rs1.getString(1);
				}
			} else if (aNum == 2){
				rs2 = statement.executeQuery("select Person_AnswerB from Person where Person_Email= '" + username +";");
				if(rs2.next()){
					rs_str = rs2.getString(1);
				}
			} 
		} catch(SQLException e){
			System.out.println("Are we here");
			e.printStackTrace();
		}
		return rs_str;
	}
	
	public void updatePerson(Person person) {
		/**
		 * This method updates a recipe's information into the database.
		 */
		try {
			connection = DbUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("update Person set person_username=?, person_lastname=?, person_firstname=?, person_email=?, person_password=?, person_questiona=?, person_questionb=?, person_answera=?, person_answerb=?"
							+ " where person_id=?");
			// Parameters start with 1
			preparedStatement.setString(1, person.getUsername());
			preparedStatement.setString(2, person.getLastName());
			preparedStatement.setString(3, person.getFirstName());
			preparedStatement.setString(4, person.getEmail());
			preparedStatement.setString(5, person.getPassword());
			preparedStatement.setString(6, person.getQ1());
			preparedStatement.setString(7, person.getQ2());
			preparedStatement.setString(8, person.getA1());
			preparedStatement.setString(9, person.getA2());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePassword(String pw, String username) {
		try {
			connection = DbUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("update Person set Person_password =?"
							+ " where Person_UserName=?");
			preparedStatement.setString(1, pw);
			preparedStatement.setString(2, username);
			preparedStatement.executeUpdate();
			
	}catch (SQLException e) {
		e.printStackTrace();
	}
	}

}


