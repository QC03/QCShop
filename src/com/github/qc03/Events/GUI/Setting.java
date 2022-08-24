package com.github.qc03.Events.GUI;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.github.qc03.Command.ShopCmd;
import com.github.qc03.Database.Line;
import com.github.qc03.Database.ShopItems;
import com.github.qc03.Database.GUI.Price;
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
		
		int clickedSlot = event.getRawSlot();
		
		// under the design bar
		int underSlot = (((shopLine)+1)*9);
		if (clickedSlot >= underSlot)
		{
			event.setCancelled(true);
			
			// Set Name
			if (clickedSlot == ((underSlot)+1))
			{
				// set Name meta
				return;
			}
			
			// Set NPC
			if (clickedSlot == ((underSlot)+2))
			{
				// set NPC meta
				return;
			}
			return;
		}
		
		// above the design bar
		// open price setting gui
		if (event.getClick() == ClickType.SHIFT_LEFT)
		{
			event.setCancelled(true);
			event.getWhoClicked().openInventory(new Price().getGUI(shopId, clickedSlot));
		}
		
	}
	
	@EventHandler
	public void onSettingInvClick(InventoryCloseEvent event) {
		
		Inventory closedInv = event.getInventory();
		String[] splitedInvName = closedInv.getName().split(" ");
		
		if (!(splitedInvName[0].equals("[상점설정]"))) { return; }
		
		int shopId = Integer.parseInt(splitedInvName[2].replace("[", "").replace("]", ""));
		
		List<ItemStack> items = Arrays.asList(closedInv.getContents());
		
		try {
			ShopItems.setItems(shopId, items);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		event.getPlayer().sendMessage(ShopCmd.cmdPrefix + "설정값 저장완료");
	}
	
	
}
