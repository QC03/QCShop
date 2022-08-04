package com.github.qc03.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.github.qc03.Database.DBConnection.DBConnection;

public class Line {

	public static int getLine(int Id)
	{
		int line = 9;
		
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String selectSQL = "SELECT Line FROM " + DBConnection.tableName + " WHERE Id = " + Id;
			ResultSet rs = stmt.executeQuery(selectSQL);
			
			while (rs.next()) {
				line = rs.getInt("Line");
				break;
			}
			
			con.close();
			stmt.close();

			return line;
			
		} catch (SQLException e) {
			
		}
		
		return line;
	}
	
	
	public static void setLine(int Id, int line)
	{
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String updateSQL = "UPDATE " + DBConnection.tableName + " SET Line = " + line + " WHERE Id = " + Id;
			stmt.executeUpdate(updateSQL);
			
			con.close();
			stmt.close();
			
		} catch (SQLException e) {
			
		}
	}
}
