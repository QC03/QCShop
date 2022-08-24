package com.github.qc03.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.github.qc03.Database.DBConnection.DBConnection;

public class DisplayName {

	public static String getDisplayName(int Id)
	{
		
		String displayName = "존재하지 않는 상점";
		
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String selectSQL = "SELECT displayName FROM " + DBConnection.tableName + " WHERE Id = " + Id;
			ResultSet rs = stmt.executeQuery(selectSQL);
			
			while (rs.next()) {
				
				displayName = rs.getString("displayName");
				break;

			}
			
			con.close();
			stmt.close();
			
			return displayName;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return displayName;
	}

	public static void setDisplayName(int Id, String shopName) {
		
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String updateSQL = "UPDATE " + DBConnection.tableName + " SET displayName = '" + shopName + "' WHERE Id = " + Id;
			stmt.executeUpdate(updateSQL);
			
			con.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
