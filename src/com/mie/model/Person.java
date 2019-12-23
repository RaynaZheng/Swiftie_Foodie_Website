package com.mie.model;

import java.util.Date;

public class Person {
	/**
	 * This class contains all of the relevant information, and getter/setter
	 * methods for the Person object.
	 */
	private int personid;
	private String lastName;
	private String firstName;
	private String email;
	private String username;
	private String password;
	private String Q1;
	private String A1;
	private String Q2;
	private String A2;
	
	private boolean valid; // Dao will set valid or not

	public int getPersonid() {
		return personid;
	}

	public void setPersonid(int id) {
		this.personid = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setQ1(String Q1){
		this.Q1 = Q1;
	}
	
	public String getQ1(){
		return Q1;
	}
	
	public void setQ2(String Q2){
		this.Q2 = Q2;
	}
	
	public String getQ2(){
		return Q2;
	}
	
	public void setA1(String A1){
		this.A1 = A1;
	}
	
	public String getA1(){
		return A1;
	}
	
	public void setA2(String A2){
		this.A2 = A2;
	}
	
	public String getA2(){
		return A2;
	}
	
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean newValid) {
		valid = newValid;
	}

	@Override
	public String toString() {
		return "{Person [userid=" + personid + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", security_question1=" + Q1 + ", security_answer1=" + A1 + ", security_question2=" + Q2 + ", security_answer2=" + A2 + "]";
	}
}
