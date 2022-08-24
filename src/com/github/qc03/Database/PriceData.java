package com.github.qc03.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import com.github.qc03.Database.DBConnection.DBConnection;

public class PriceData {

	private Connection con = DBConnection.getConnection();

	
	public class Money
	{
		
		public int getPrice(int Id, int slot, String type)
		{
			
			int price = -1;
			
			try {
				
				Statement stmt = con.createStatement();
				stmt.executeUpdate("use " + DBConnection.dbName);
				
				String coulum = type + "_Money";
				
				String selectSQL = "SELECT " + coulum + " FROM " + ShopList.getPriceTableName(Id) + " WHERE slot = " + slot;
				ResultSet rs = stmt.executeQuery(selectSQL);
				
				while (rs.next())
				{
					price = rs.getInt(coulum);
					break;
				}
				
				stmt.close();
				
				return price;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return price;
		}
		
		public void setPrice(int Id, int slot, String type, int price)
		{
			
			try {
				
				Statement stmt = con.createStatement();
				stmt.executeUpdate("use " + DBConnection.dbName);
				
				String coulum = type + "_Money";
				
				String updateSQL = "UPDATE " + ShopList.getPriceTableName(Id) + " SET '" + coulum + "' = " + price + " WHERE 'slot' = " + slot;
				stmt.executeUpdate(updateSQL);
				stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public boolean isBuyable(int Id, int slot)
		{
			
			if (getPrice(Id, slot, "Buy") == -1)
			{
				return false;
			} else {
				return true;
			}
		}
		
		public boolean isSellable(int Id, int slot)
		{
			
			if (getPrice(Id, slot, "Sell") == -1)
			{
				return false;
			} else {
				return true;
			}
		}
		
		public boolean isTradeable(int Id, int slot)
		{
			if (isBuyable(Id, slot) || isSellable(Id, slot) )
			{
				return true;
				
			} else {
				return false;
			}
		}
	}
	
	public class Cash
	{
		public int getPrice(int Id, int slot, String type)
		{
			
			int price = -1;
			
			try {
				
				Statement stmt = con.createStatement();
				stmt.executeUpdate("use " + DBConnection.dbName);
				
				String coulum = type + "_Cash";
				
				String selectSQL = "SELECT " + coulum + " FROM " + ShopList.getPriceTableName(Id) + " WHERE slot = " + slot;
				ResultSet rs = stmt.executeQuery(selectSQL);
				
				while (rs.next())
				{
					price = rs.getInt(coulum);
					break;
				}
				stmt.close();
				
				return price;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return price;
		}
		
		public void setPrice(int Id, int slot, String type, int price)
		{
			
			try {
				
				Statement stmt = con.createStatement();
				stmt.executeUpdate("use " + DBConnection.dbName);
				
				String coulum = type + "_Cash";
				
				String updateSQL = "UPDATE " + ShopList.getPriceTableName(Id) + " SET '" + coulum + "' = " + price + " WHERE 'slot' = " + slot;
				stmt.executeUpdate(updateSQL);
				stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public boolean isBuyable(int Id, int slot)
		{
			
			if (getPrice(Id, slot, "Buy") == -1)
			{
				return false;
			} else {
				return true;
			}
		}
		
		public boolean isSellable(int Id, int slot)
		{
			
			if (getPrice(Id, slot, "Sell") == -1)
			{
				return false;
			} else {
				return true;
			}
		}
		
		public boolean isTradeable(int Id, int slot)
		{
			if (isBuyable(Id, slot) || isSellable(Id, slot) )
			{
				return true;
				
			} else {
				return false;
			}
		}
	}
	
	public class Items
	{
		public List<ItemStack> getPrice(int Id, int slot, String type)
		{
			
			List<ItemStack> deserializedItems = new ArrayList<ItemStack>();
			
			try {
				
				Statement stmt = con.createStatement();
				stmt.executeUpdate("use " + DBConnection.dbName);
				
				String coulum = type + "_Items";
				
				String selectSQL = "SELECT " + coulum + " FROM " + ShopList.getPriceTableName(Id) + " WHERE slot = " + slot;
				ResultSet rs = stmt.executeQuery(selectSQL);
				
				String serializedItems = null;
				while (rs.next())
				{
					serializedItems = rs.getString(coulum);
					break;
				}
				stmt.close();
				
				ItemSerializer serializer = new ItemSerializer();
				deserializedItems = serializer.deserializeList(serializedItems);
				return deserializedItems;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return deserializedItems;
		}
		
		public void setPrice(int Id, int slot, String type, List<ItemStack> items)
		{
			
			try {
				
				Statement stmt = con.createStatement();
				stmt.executeUpdate("use " + DBConnection.dbName);
				
				ItemSerializer serializer = new ItemSerializer();
				String coulum = type + "_Items";
				String updateSQL = "UPDATE " + ShopList.getPriceTableName(Id) + " SET '" + coulum + "' = " + serializer.serialize(items) + " WHERE 'slot' = " + slot;
				stmt.executeUpdate(updateSQL);
				stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public boolean isBuyable(int Id, int slot)
		{
			
			if (getPrice(Id, slot, "Buy").size() == 0)
			{
				return false;
			} else {
				return true;
			}
		}
		
		public boolean isSellable(int Id, int slot)
		{
			
			if (getPrice(Id, slot, "Sell").size() == 0)
			{
				return false;
			} else {
				return true;
			}
		}
		
		public boolean isTradeable(int Id, int slot)
		{
			if (isBuyable(Id, slot) || isSellable(Id, slot) )
			{
				return true;
				
			} else {
				return false;
			}
		}
	}
}
