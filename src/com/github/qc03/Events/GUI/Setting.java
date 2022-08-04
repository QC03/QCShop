package com.github.qc03.Events.GUI;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.github.qc03.Database.Line;
import com.github.qc03.QCShop.Main;

public class Setting implements Listener {

	public Setting(Main plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onSettingInvClick(InventoryClickEvent event) {
	  
		Inventory clickedInv = event.getClickedInventory();
		if (clickedInv == null) { return; }
		
		String[] splitedInvName = clickedInv.getName().split(" ");
		
		if (!(splitedInvName[0].equals("[상점설정]"))) { return; }
		
		int shopId = Integer.parseInt(splitedInvName[2].replace("[", "").replace("]", ""));
		int shopLine = Line.getLine(shopId);
		
		// under the design bar
		if (event.getRawSlot() >= ((shopLine)+1)*9)
		{
			
			return;
		}
		
		// above the design bar
		// open price setting gui
		if (event.getClick() == ClickType.SHIFT_LEFT)
		{
			
		}
		
	}
}
