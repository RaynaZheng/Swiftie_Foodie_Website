/*
package com.mie.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//is this class necessary????????
public class Favourites {
	private int favourite_ID;
	private int recipe_ID;
	private String person_username;
	private boolean login_status;
	
	static Connection currentCon = null;
	static ResultSet favid = null;
	private Connection connection;
	
	
	public boolean deleteFavourite(int favourite_ID){
		boolean success = false;
		try {
			PreparedStatement preparedStatement1 = connection
					.prepareStatement("select Favourite_ID from favourites");
			ResultSet favid = preparedStatement1.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			while(favid.next()){
				try {
					if(favourite_ID == favid.getInt(1)){
						PreparedStatement preparedStatement2 = connection
								.prepareStatement("DELETE FROM favourites where Favourite_ID = '"+ favourite_ID+ ";");
						preparedStatement2.executeUpdate();
						success = true;
}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
		
}

}

*/
