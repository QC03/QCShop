package com.github.qc03.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.QCUtilLib.Message.ErrorMessage;
import com.github.qc03.Database.DisplayName;
import com.github.qc03.Database.Id;
import com.github.qc03.Database.NPC;
import com.github.qc03.Database.ShopList;
import com.github.qc03.Database.GUI.General;
import com.github.qc03.Database.GUI.Setting;

public class ShopCmd implements CommandExecutor {

	public static String cmdPrefix = ChatColor.YELLOW + "[Shop] " + ChatColor.RESET;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		
		if (!(sender instanceof Player)) { return false; }
		
		Player player = (Player) sender;
		
		if (args.length < 1)
		{
			
			for ( String help : CmdList.getCmds())
			{
				player.sendMessage(help);
			}
			return true;
		}
		
		if (args[0].equals("목록"))
		{
			
			int page = 1;
			
			// has input page value
			if (args.length >= 2) { page = Integer.parseInt(args[1]); }
			
			for ( Object list : ShopList.getShopList(null, page))
			{
				player.sendMessage((String) list);
			}
			
			return true;
		}
		
		if (args.length < 2)
		{
			ErrorMessage.sendError(player, "모든 값을 입력해주세요.");
			return false;
		}
		
		if (args[0].equals("생성"))
		{
			String displayName = args[1];
			ShopList.addShop(displayName, Id.getShopNextId());
			
			player.sendMessage(cmdPrefix + " 상점을 성공적으로 생성하였습니다.");
			return true;
		}
		
		
		int shopId = Integer.parseInt(args[1]);
		if (!(Id.isShopId(shopId)))
		{
			ErrorMessage.sendError(player, "존재하지 않는 상점입니다.");
			return false;
		}
		
		if (args[0].equals("열기"))
		{
			
			General shopGui = new General();
			player.openInventory(shopGui.getGUI(shopId));
			return true;
		}
		
		if (args[0].equals("설정"))
		{
			Setting settingGui = new Setting();
			player.openInventory(settingGui.getGUI(shopId));
			return true;
		}
		
		
		
		if (args.length < 3)
		{
			ErrorMessage.sendError(player, "모든 값을 입력해주세요.");
			return false;
		}
		
		
		if (args[0].equals("NPC"))
		{
			int npcId = Integer.parseInt(args[2]);
			NPC.setNpcId(shopId, npcId);
			player.sendMessage(cmdPrefix + " 상점NPC를 성공적으로 변경하였습니다.");
		}
		
		
		if (args[0].equals("이름"))
		{
			String shopName = args[2];
			DisplayName.setDisplayName(shopId, shopName);
			player.sendMessage(cmdPrefix + " 상점이름을 성공적으로 변경하였습니다.");
		}
		
		return false;
	}
}
