package com.github.qc03.QCShop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.qc03.Command.ShopCmd;
import com.github.qc03.Database.DBConnection.DBConnection;
import com.github.qc03.Events.NpcClick;

public class Main extends JavaPlugin implements Listener {
	
	ConsoleCommandSender console = Bukkit.getConsoleSender();
	
	@Override
	public void onEnable() {
		
		console.sendMessage( ChatColor.AQUA + "[QC Shop] Enable Plugin.");
		
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new NpcClick(this), this);
		
		getCommand("상점").setExecutor(new ShopCmd());
		
		DBConnection.createDatabase();
		
	}
	
	@Override
	public void onDisable() {
		
		console.sendMessage( ChatColor.AQUA + "[QC Shop] Disable Plugin.");
		
	}
}
	