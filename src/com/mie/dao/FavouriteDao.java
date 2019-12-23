package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mie.util.DbUtil;

public class FavouriteDao {
	/**
	 * This class handles the user object like certain receipe
	 */
	static Connection currentCon = null;
	private Connection connection;
	
	public boolean checkLiked(String person_username, int recipe_id){
		boolean liked = false;
		try{
			System.out.println("It's this ufnction ");
			connection = DbUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select person_username, Recipe_ID FROM Favourites WHERE person_username = '" + person_username + "'and recipe_id = "+recipe_id+";");
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
		
				liked = true;
			}else{
	
				liked = false;
			}
		}catch(SQLException e){
				e.printStackTrace();
			}

		return liked;
		}
	
	
	/**
	 * 
	 * @param Pstatus---logged in==True, not logged in==False
	 * @param Rstuatus -- liked == true, not liked == false
	 * @param recipe_id
	 * @param person_username
	 */
	public boolean addFavourite(boolean Pstatus, boolean Rstatus, int recipe_id, String person_username){
	
		boolean added = false;
		connection = DbUtil.getConnection();
		if (Pstatus == true && Rstatus == false){// logged in and not liked

			try {
				
				PreparedStatement preparedStatement1 = connection
						.prepareStatement("select max(Favourite_ID) from favourites;"); 
				ResultSet favid1 = preparedStatement1.executeQuery();
				favid1.next();
		
				PreparedStatement preparedStatement2 = connection
						.prepareStatement("insert into favourites(Favourite_ID,Recipe_ID,Person_UserName) values (?, ?, ?)");
				
				
				preparedStatement2.setInt(1, favid1.getInt(1)+1);
				preparedStatement2.setInt(2,recipe_id);
				preparedStatement2.setString(3, person_username);
				preparedStatement2.executeUpdate();
			
			}catch(SQLException e) {
				e.printStackTrace();
			}

			added = true;
	} else if (Pstatus == true && Rstatus == true){// logged in and liked
		try {
			PreparedStatement preparedStatement3 = connection
					.prepareStatement("delete from favourites where Recipe_ID = '" + recipe_id + "' and Person_UserName = '" + person_username + "'"  );
			preparedStatement3.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		added = false;
	}
		
		return added;
	}
	
	/*
	public boolean deleteFavourite(int favourite_ID){
		boolean deleted = false;
		try {
			PreparedStatement preparedStatement1 = connection
					.prepareStatement("select Favourite_ID from favourites");
			ResultSet favid2 = preparedStatement1.executeQuery();
			while(favid2.next()){
				try {
					if(favourite_ID == favid2.getInt(1)){
						PreparedStatement preparedStatement2 = connection
								.prepareStatement("DELETE FROM favourites where Favourite_ID = '"+ favourite_ID+ ";");
						preparedStatement2.executeUpdate();
						deleted = true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return deleted;
		
}
*/	

}
