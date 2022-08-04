package com.github.qc03.Database.GUI;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.github.qc03.Database.DisplayName;
import com.github.qc03.Database.ShopItems;
import com.github.qc03.Database.GUI.LoreSet.GeneralLore;
import com.github.qc03.Database.Line;

public class General {

	private String getPrefix = "[상점] ";
	
	public Inventory getGUI(int Id)
	{
		Inventory inv = Bukkit.createInventory(null, ((Line.getLine(Id)) *9), this.getPrefix + DisplayName.getDisplayName(Id) + " [ " + Id + " ]");

		try {
			
			int slot = 0;
			for (Object slotItem : ShopItems.getItems(Id).toArray().clone())
			{
				inv.setItem(slot, GeneralLore.loreSetItem((ItemStack) slotItem, Id, slot));
				slot++;
			}
			
		} catch (IllegalArgumentException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		return inv;
	}
}
