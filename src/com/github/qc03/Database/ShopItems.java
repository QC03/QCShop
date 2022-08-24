package com.github.qc03.Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import com.github.qc03.Database.DBConnection.DBConnection;

public class ShopItems {
	
	public static void setItems(int Id, List<ItemStack> items) throws IOException
	{
		ItemSerializer serializer = new ItemSerializer();
		String serializedItems = serializer.serialize(items);
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String updateSQL = "UPDATE " + DBConnection.tableName + " SET Items = '" + serializedItems + "' WHERE Id = " + Id;
			stmt.executeUpdate(updateSQL);
			
			con.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<ItemStack> getItems(int Id) throws ClassNotFoundException, IOException
	{
		
		List<ItemStack> deserializedItems = new ArrayList<ItemStack>();
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String selectSQL = "SELECT Items FROM " + DBConnection.tableName + " WHERE Id = " + Id;
			ResultSet rs = stmt.executeQuery(selectSQL);
			
			String serializedItems = null;
			while (rs.next()) {
				serializedItems = rs.getString("Items");
				break;
			}
			
			con.close();
			stmt.close();
			
			if (serializedItems.isEmpty()) { return deserializedItems; }
			
			ItemSerializer serializer = new ItemSerializer();
			deserializedItems = serializer.deserializeList(serializedItems);
			return deserializedItems;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return deserializedItems;
	}

}
