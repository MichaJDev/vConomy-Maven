package nl.vconomy.main.Surface.Inventories.Wallet;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import net.md_5.bungee.api.ChatColor;
import nl.vconomy.main.Main;
import nl.vconomy.main.data.Configuration.Config;
import nl.vconomy.main.data.Writers.Writer;

public class WalletInv extends FastInv {

	private Main main;
	private Config cfg;
	private Writer writer;

	private boolean preventClose = false;

	public WalletInv(Main main) {
		super(45, ChatColor.GREEN + "Wallet");

		setItem(22, new ItemStack(Material.IRON_SWORD), e -> e.getWhoClicked().sendMessage("You clicked on the sword"));

		setItems(getBorders(), new ItemBuilder(Material.LAPIS_BLOCK).name(" ").build());

		setItem(34, new ItemBuilder(Material.BARRIER).name(ChatColor.RED + "Prevent close").build(), e -> {
			preventClose = !preventClose;
		});

		setCloseFilter(p -> preventClose);

		this.main = main;
		cfg = new Config(main);
		writer = cfg.getWriter();
	}

	@Override
	public void onOpen(InventoryOpenEvent e) {
		e.getPlayer().sendMessage(ChatColor.GOLD + "Opened your inventory");
	}

	@Override
	public void onClose(InventoryCloseEvent event) {
		event.getPlayer().sendMessage(ChatColor.GOLD + "You closed the inventory");
	}

	@Override
	public void onClick(InventoryClickEvent event) {
		// do something
	}
	/// give @p minecraft:player_head{display:{Name:"{\"text\":\"Diamonds
	/// Dollar\"}"},SkullOwner:{Id:[I;1981450220,-1940504086,-1791506151,459273445],Properties:{textures:[{Value:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmYxYWM3MTgyZjkxZWZjYzI3NGQ2ZGQzODdlNzVkMjEyMjdmMWFkMGEwNmIwNzI1M2Q0M2NjYzlkZWEyOWZmIn19fQ=="}]}}}
	/// 1

}
