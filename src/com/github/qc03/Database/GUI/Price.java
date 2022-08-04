package com.github.qc03.Database.GUI;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.qc03.Database.DisplayName;
import com.github.qc03.Database.Line;
import com.github.qc03.Database.PriceData;

public class Price {

	private int line = 4;
	private Inventory inv;
	
	public Inventory getGUI(int Id, int slot)
	{
		
		line = Line.getLine(Id);
		inv = Bukkit.createInventory(null, ((this.line) *9), "[상점가격설정] " + DisplayName.getDisplayName(Id) + " [" + Id + "]" + " [" + slot + "]");
		
		
		PriceData itemPriceClass = new PriceData();
		
		PriceData.Money itemMoneyPrice = itemPriceClass.new Money();
		PriceData.Cash itemCashPrice = itemPriceClass.new Cash();

		// Buy Bar
		int ItemSlot = 10;
		inv.setItem(ItemSlot, createGuiItem(Material.SIGN, ChatColor.GREEN + "돈", "현재 구매가격 : " + itemMoneyPrice.getPrice(Id, slot, "Buy")));
		
		ItemSlot = ItemSlot + 3;
		inv.setItem(ItemSlot, createGuiItem(Material.SIGN, ChatColor.GREEN + "캐시", "현재 구매가격 : " + itemCashPrice.getPrice(Id, slot, "Buy")));

		ItemSlot = ItemSlot + 3;
		inv.setItem(ItemSlot, createGuiItem(Material.SIGN, ChatColor.GREEN + "아이템", "구매가격 확인 (클릭)"));

		
		// Sell Bar
		ItemSlot = ItemSlot + 3;
		inv.setItem(ItemSlot, createGuiItem(Material.SIGN, ChatColor.GREEN + "돈", "현재 판매가격 : " + itemMoneyPrice.getPrice(Id, slot, "Sell")));
		
		ItemSlot = ItemSlot + 3;
		inv.setItem(ItemSlot, createGuiItem(Material.SIGN, ChatColor.GREEN + "캐시", "현재 판매가격 : " + itemCashPrice.getPrice(Id, slot, "Sell")));

		ItemSlot = ItemSlot + 3;
		inv.setItem(ItemSlot, createGuiItem(Material.SIGN, ChatColor.GREEN + "아이템", "판매가격 확인 (클릭)"));
		
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
