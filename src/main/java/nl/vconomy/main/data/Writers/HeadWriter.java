package nl.vconomy.main.data.Writers;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadWriter {

	public static ItemStack getHead(Player player) {
		int lifePlayer = (int) player.getHealth();
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
		SkullMeta skull = (SkullMeta) item.getItemMeta();
		skull.setDisplayName(player.getName());
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Custom head");
		skull.setLore(lore);
		skull.setOwner(player.getName());
		item.setItemMeta(skull);
		return item;
	}

}
