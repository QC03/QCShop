package com.github.qc03.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.github.qc03.Database.NPC;
import com.github.qc03.Database.GUI.General;
import com.github.qc03.QCShop.Main;

import net.citizensnpcs.api.event.NPCRightClickEvent;

public class NpcClick implements Listener {
	
	public NpcClick(Main plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onNpcClick(NPCRightClickEvent event) {
	  
		int npcId = event.getNPC().getId();
		if (!(NPC.isShopNpcId(npcId))) { return; }
		
		int shopId = NPC.getShopId(npcId);
		
		General shopGui = new General();
		event.getClicker().openInventory(shopGui.getGUI(shopId));
	}
}
