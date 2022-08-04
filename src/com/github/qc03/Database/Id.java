package com.github.qc03.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.github.qc03.Database.DBConnection.DBConnection;

public class Id {

	public static int getShopNextId()
	{
		
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String selectSQL = "SELECT 'Id' FROM " + DBConnection.tableName;
			ResultSet rs = stmt.executeQuery(selectSQL);
			
			List<Integer> Ids = new ArrayList<Integer>();
			while (rs.next()) {
				Ids.add(rs.getInt("Id"));
			}
			
			Ids.sort(Comparator.naturalOrder());
			
			con.close();
			stmt.close();
			
			int Id = 0;
			if (Ids.size() > 0) { Id = (int) Collections.max(Ids); }

			return  Id;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -999;
	}
	
	public static boolean isShopId(int Id)
	{
		boolean found = false;
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String selectSQL = "SELECT 'Id' FROM " + DBConnection.tableName;
			ResultSet rs = stmt.executeQuery(selectSQL);
			
			while (rs.next()) {
				
				found = true;
				break;

			}
			
			con.close();
			stmt.close();
			
			return found;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return found;
	}
}
