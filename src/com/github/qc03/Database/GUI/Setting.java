package com.github.qc03.Database.GUI;

import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.qc03.Database.DisplayName;
import com.github.qc03.Database.Items;
import com.github.qc03.Database.Line;
import com.github.qc03.Database.NPC;

public class Setting {

	private String getPrefix = "[상점설정] ";
	private Inventory inv;
	private int line;
	
	public Inventory getGUI(int Id)
	{
		
		line = Line.getLine(Id);
		
		inv = Bukkit.createInventory(null, (this.line)+3, this.getPrefix + DisplayName.getDisplayName(Id));
		
		try {
			inv.addItem((ItemStack[]) Items.getItems(Id).toArray().clone());
			
		} catch (IllegalArgumentException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		initializeSettingItems(Id);
		return inv;
	}
	
	private ItemStack guiBar = new ItemStack(Material.STAINED_GLASS_PANE, 1);
	private void initializeSettingItems(int Id)
	{
		
		int slot = ((this.line) - 3);
		// set Gui Bar Design
		for ( int barSlot = slot; barSlot <= ((slot)+9); barSlot++)
		{
			inv.setItem(barSlot, this.guiBar);
			slot++;
		}
		
		slot++;
		inv.setItem(slot, createGuiItem(Material.SIGN, "이름설정", "현재 이름 : " + DisplayName.getDisplayName(Id)));
		slot++;
		inv.setItem(slot, createGuiItem(Material.SIGN, "NPC설정", "현재 NPC : " + NPC.getNpcId(Id)));
		
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
