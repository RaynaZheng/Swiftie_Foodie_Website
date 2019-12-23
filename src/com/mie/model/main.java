package com.mie.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mie.dao.PersonDao;
import com.mie.dao.RecipeDao;

public class main {

	public static void main(String[] args){
		String s = "Discard 4: I hate it";
		String w = s.substring(8, s.length()-7);
		System.out.println(w);
	}
}

