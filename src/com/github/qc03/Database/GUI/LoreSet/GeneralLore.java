package com.github.qc03.Database.GUI.LoreSet;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.qc03.Database.PriceData;
import com.github.qc03.Database.PriceData.Cash;
import com.github.qc03.Database.PriceData.Items;
import com.github.qc03.Database.PriceData.Money;

public class GeneralLore {

	private final static ItemStack air = new ItemStack(Material.AIR, 1);
	
	public static ItemStack loreSetItem(ItemStack item, int Id, int slot)
	{
		
		if (item == null) { return air.clone(); }
		if (item.getData() == air.getData()) { return air.clone(); }
		
		PriceData priceData = new PriceData();
		
		Money moneyData = priceData.new Money();
		Cash cashData = priceData.new Cash();
		Items itemsData = priceData.new Items();
		
		ItemMeta meta = item.getItemMeta();
		
		boolean total_Buyable = false;
		boolean total_Sellable = false;
		List<String> lore = new ArrayList<String>();
		
		if (meta.getLore() != null)
		{
			lore = meta.getLore();
		}
		
		// Lore Set
		
		// Money Lore
		if (moneyData.isTradeable(Id, slot))
		{
			lore.add(ChatColor.WHITE + "[ 돈 ]");
			
			if (moneyData.isBuyable(Id, slot))
			{
				lore.add(" ");
				lore.add(ChatColor.WHITE + "구매가격 : " + ChatColor.GREEN + moneyData.getPrice(Id, slot, "Buy") + ChatColor.WHITE + "원 ");
				total_Buyable = true;
			}
			
			if (moneyData.isSellable(Id, slot))
			{
				lore.add(" ");
				lore.add(ChatColor.WHITE + "판매가격 : " + ChatColor.GREEN + moneyData.getPrice(Id, slot, "Sell") + ChatColor.WHITE + "원 ");
				total_Sellable = true;
			}
		}
		
		
		// Cash Lore
		if (cashData.isTradeable(Id, slot))
		{
			lore.add(ChatColor.WHITE + "[ 캐시 ]");
			if (cashData.isBuyable(Id, slot))
			{
				lore.add(" ");
				lore.add(ChatColor.WHITE + "구매가격 : " + ChatColor.GREEN + cashData.getPrice(Id, slot, "Buy") + ChatColor.WHITE + "원 ");
				total_Buyable = true;
			}
			
			if (cashData.isSellable(Id, slot))
			{
				lore.add(" ");
				lore.add(ChatColor.WHITE + "판매가격 : " + ChatColor.GREEN + cashData.getPrice(Id, slot, "Sell") + ChatColor.WHITE + "원 ");
				total_Sellable = true;
			}
		}
		
		
		// Item Lore
		if (cashData.isTradeable(Id, slot))
		{
			lore.add(ChatColor.WHITE + "[ 아이템 ]");
			if (itemsData.isBuyable(Id, slot))
			{
				lore.add(" ");
				lore.add(ChatColor.WHITE + "구매가격 : " + ChatColor.YELLOW + "클릭시 확인 가능합니다. ");
				total_Buyable = true;
			}
			
			if (itemsData.isSellable(Id, slot))
			{
				lore.add(" ");
				lore.add(ChatColor.WHITE + "판매가격 : " + ChatColor.YELLOW + "클릭시 확인 가능합니다. ");
				total_Sellable = true;
			}
		}
		
		
		if (!(total_Buyable))
		{
			lore.add(" ");
			lore.add(ChatColor.RED + "구매불가 ");
		}
		
		if (!(total_Sellable))
		{
			lore.add(" ");
			lore.add(ChatColor.RED + "판매불가 ");
		}
		
		
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
		
	}
}
