package com.github.qc03.Database;


import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class ItemSerializer {

	private final String splitStr = "~";
	
	public String serialize(ItemStack item) {

		String encodedItem = "";
		try {
			
			ByteArrayOutputStream io = new ByteArrayOutputStream();
			BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);
			
			os.writeObject(item);
			os.flush();
			
			byte[] serializedItem = io.toByteArray();
			encodedItem = Base64.getEncoder().encodeToString(serializedItem);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return encodedItem;
	}
	

	public ItemStack deserialize(String encodedItem) {

		ItemStack deserializedItem = null;
		try {
			
			byte[] serializedItem = Base64.getDecoder().decode(encodedItem);
			
			ByteArrayInputStream in = new ByteArrayInputStream(serializedItem);
			BukkitObjectInputStream is = new BukkitObjectInputStream(in);
			
			deserializedItem = (ItemStack) is.readObject();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return deserializedItem;
	}

	
	public String serialize(List<ItemStack> items) {
		
		String serializedItemList = "";
		
		for (ItemStack item : items)
		{
			if (serializedItemList.isEmpty()){
				serializedItemList = serialize(item);
				
			} else {
				serializedItemList = serializedItemList + splitStr + serialize(item);
			}
		}
		
		return serializedItemList;
	}
	
	public List<ItemStack> deserializeList(String encodedItemList) {
		
		List<ItemStack> deserializedItemList = new ArrayList<ItemStack>();
		
		for (String serializedItem : encodedItemList.split(splitStr))
		{
			deserializedItemList.add(deserialize(serializedItem));
		}
		
		return deserializedItemList;
	}
}