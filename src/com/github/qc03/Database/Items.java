package com.github.qc03.Database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.github.qc03.Database.DBConnection.DBConnection;

import net.minecraft.server.v1_12_R1.ItemStack;

public class Items {

	public static byte[] serialize(List<ItemStack> items) throws IOException
	{
		byte[] serializedItems;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				
			    oos.writeObject(items);
			    serializedItems = baos.toByteArray();
			    
			    baos.close();
			    oos.close();
			}
		}
		
		return serializedItems;
	}
	
	@SuppressWarnings("unchecked")
	public static List<ItemStack> deserialize(byte[] serializedItems) throws IOException, ClassNotFoundException
	{
		List<ItemStack> items = new ArrayList<ItemStack>();
		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedItems)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				
				items = (List<ItemStack>) ois.readObject();
				
				bais.close();
				ois.close();
			}
		}
		
		return items;
	}
	
	public static void setItems(int Id, List<ItemStack> items) throws IOException
	{
		byte[] serializedItems = serialize(items);
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String updateSQL = "UPDATE " + DBConnection.tableName + " SET 'Items' = " + serializedItems.toString() + " WHERE 'Id' = " + Id;
			stmt.executeUpdate(updateSQL);
			
			con.close();
			stmt.close();
			
		} catch (SQLException e) {
			
		}
	}
	
	public static List<ItemStack> getItems(int Id) throws ClassNotFoundException, IOException
	{
		
		List<ItemStack> deserializedItems = new ArrayList<ItemStack>();
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use " + DBConnection.dbName);
			
			String selectSQL = "SELECT 'Items' FROM " + DBConnection.tableName + " WHERE 'Id' = " + Id;
			ResultSet rs = stmt.executeQuery(selectSQL);
			
			byte[] serializedItems = null;
			while (rs.next()) {
				serializedItems = rs.getString("Items").getBytes();
				break;
			}
			
			con.close();
			stmt.close();

			deserializedItems = deserialize(serializedItems);
			return deserializedItems;
			
		} catch (SQLException e) {
			
		}
		
		return deserializedItems;
	}

}
