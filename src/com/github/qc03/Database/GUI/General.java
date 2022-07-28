package com.github.qc03.Database.GUI;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.github.qc03.Database.DisplayName;
import com.github.qc03.Database.Items;
import com.github.qc03.Database.Line;

public class General {

	private String getPrefix = "[상점] ";
	
	public Inventory getGUI(int Id)
	{
		Inventory inv = Bukkit.createInventory(null, Line.getLine(Id), this.getPrefix + DisplayName.getDisplayName(Id));

		try {
			inv.addItem((ItemStack[]) Items.getItems(Id).toArray().clone());
			
		} catch (IllegalArgumentException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return inv;
	}
}
