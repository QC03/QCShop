package com.github.qc03.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.github.qc03.Database.DBConnection.DBConnection;

public class NPC {

	public static void setNpcId(int shopId, int npcId)
	{
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String updateSQL = "UPDATE " + DBConnection.tableName + " SET 'NPC' = " + npcId + " WHERE 'Id' = " + shopId;
			stmt.executeUpdate(updateSQL);
			
			con.close();
			stmt.close();
			
		} catch (SQLException e) {
			
		}
	}
	
	public static int getNpcId(int shopId)
	{
		int npcId = -1;
		
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String selectSQL = "SELECT 'NPC' FROM " + DBConnection.tableName + " WHERE 'Id' = " + shopId;
			ResultSet rs = stmt.executeQuery(selectSQL);
			
			while (rs.next()) {
				npcId = rs.getInt("NPC");
				break;
			}
			
			con.close();
			stmt.close();

			return npcId;
			
		} catch (SQLException e) {
			
		}
		
		return npcId;
	}
	
	public static boolean isShopNpcId(int npcId)
	{
		boolean found = false;
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String selectSQL = "SELECT 'NPC' FROM " + DBConnection.tableName;
			ResultSet rs = stmt.executeQuery(selectSQL);
			
			while (rs.next()) {
				found = true;
				break;
			}
			
			con.close();
			stmt.close();

			return found;
			
		} catch (SQLException e) {
			
		}
		
		return found;
	}
	
	public static int getShopId(int npcId)
	{
		int shopId = -1;
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String selectSQL = "SELECT 'Id' FROM " + DBConnection.tableName + " WHERE 'NPC' = " + npcId;
			ResultSet rs = stmt.executeQuery(selectSQL);
			
			while (rs.next()) {
				shopId = rs.getInt("Id");
				break;
			}
			
			con.close();
			stmt.close();

			return shopId;
			
		} catch (SQLException e) {
			
		}
		
		return shopId;
	}
}
