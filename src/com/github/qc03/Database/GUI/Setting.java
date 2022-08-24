package com.github.qc03.Database.GUI;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.qc03.Database.DisplayName;
import com.github.qc03.Database.Line;
import com.github.qc03.Database.ShopItems;

public class Setting {

	private Inventory inv;
	private int line;
	private ItemStack air = new ItemStack(Material.AIR, 1);
	
	public Inventory getGUI(int Id)
	{
		
		line = Line.getLine(Id);
		inv = Bukkit.createInventory(null, ((this.line) *9), "[상점설정] " + DisplayName.getDisplayName(Id) + " [" + Id + "]");
		
		try {
			
			List<ItemStack> slotItems = ShopItems.getItems(Id);
			if (slotItems.size() <= 0) { return inv; }
				
			int slot = 0;
			for (ItemStack item : slotItems)
			{
				if (item == null)
				{
					inv.setItem(slot, air);
				} else {
					inv.setItem(slot, item);
				}

				slot++;
			}
			
		} catch (IllegalArgumentException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		return inv;
	}

	
	protected ItemStack createGuiItem(final Material material, final String name, final String... lore)
	{
		final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
	}
	
	
}
