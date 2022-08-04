package com.github.qc03.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.github.qc03.Command.ShopCmd;
import com.github.qc03.Database.DBConnection.DBConnection;

public class ShopList {

	public static String getPriceTableName(int Id)
	{
		return "PriceTable_" + Id;
	}
	
	public static void addShop(String displayName, int shopId)
	{
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String insertSQL = "INSERT INTO " + DBConnection.tableName +
								" (Id, displayName, Line) VALUES (" +
								shopId + ", '" +
								displayName + "', " +
								 "6)";
			stmt.executeUpdate(insertSQL);
			
			
			String createTableSQL = "CREATE TABLE " + getPriceTableName(shopId) +
	                   " (slot INT(255) not NULL, " +
	                   " Buy_Money INT, " + 
	                   " Buy_Cash INT, " +
	                   " Buy_Item INT, " +
	                   " Sell_Money INT, " +
	                   " Sell_Cash INT, " +
	                   " Sell_Item INT, " +
	                   " PRIMARY KEY ( slot ))";
			
			stmt.executeUpdate(createTableSQL);
			
			con.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void removeShop(int shopId)
	{
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String deleteSQL = "DELETE FROM " + DBConnection.tableName + " " +
								"WHERE Id = " +
								shopId;
			stmt.executeUpdate(deleteSQL);
			
			String dropSQL = "DROP TABLE" + getPriceTableName(shopId);
			stmt.executeUpdate(dropSQL);
			
			con.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Object> getShopList(String returnType, int page)
	{
		
		List<Object> shopList = new ArrayList<Object>();
		shopList.add(" ");
		
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String selectSQL = "SELECT displayName, Id FROM " + DBConnection.tableName;
			ResultSet rs = stmt.executeQuery(selectSQL);
			
			String displayName;
			int Id;
			int count = 0;
			while (rs.next()) {
				
				
				if (page != -1)
				{
					count++;
					if (count < (((page)-1)*10)) { continue; }
					if (count >= ((page)*10)) { break; }
				}
				
				displayName = rs.getString("displayName");
				Id = rs.getInt("Id");
				
				if (returnType == null) {
					shopList.add(ShopCmd.cmdPrefix + " 번호 : " + Id + " 상점이름 : " + displayName);
					
				} else if (returnType.equals("Name")) {
					shopList.add(displayName);
					
				} else if (returnType.equals("Id")) {
					shopList.add(Id);
					
				}
			}
			
			con.close();
			stmt.close();
			
			shopList.add(" ");
			return shopList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		shopList.add(" ");
		return shopList;
	}
}
