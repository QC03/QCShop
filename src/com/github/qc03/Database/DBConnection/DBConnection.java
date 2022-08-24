package com.github.qc03.Database.DBConnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	private static Connection con;
	public static String dbName = "shop_database";
	public static String tableName = "shop";
	
	public static Connection getConnection()
	{
		String url = "jdbc:mysql://localhost/?characterEncoding=UTF-8&serverTimezone=UTC&autoReconnect=true";
		String user = "root";
		String password = "zxcvv1324!";
		
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("DB연결을 실패하거나, SQL문이 틀렸습니다.");
			System.out.print("사유 : " + e.getMessage());
		}
		
		return con;
	}
	
	public static void createDatabase()
	{
		Connection con = DBConnection.getConnection();
		Statement stmt;
		try {
			
			stmt = con.createStatement();
			
			stmt.executeUpdate("use " + dbName);
			
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, tableName, null);
			if (!(tables.next()))
			{ 
				con.close();
				stmt.close();
				return;
			}
			
			String createTableSQL = "CREATE TABLE " + tableName + "(" +
	                   "Id 				INT 		not NULL, " +
	                   "displayName 	CHAR(36) 	not NULL, " + 
	                   "Line 			TINYINT(9) 	not NULL, " +
	                   "NPC 			INT, " +
	                   "Items 			TEXT, " +
	                   "PRIMARY 		KEY(Id))";
			
			stmt.executeUpdate(createTableSQL);
			
			con.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
