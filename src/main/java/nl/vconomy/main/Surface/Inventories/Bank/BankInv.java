package nl.vconomy.main.Surface.Inventories.Bank;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import net.md_5.bungee.api.ChatColor;
import nl.vconomy.main.Main;
import nl.vconomy.main.data.Configuration.Config;
import nl.vconomy.main.data.Configuration.Databases.WalletDB;
import nl.vconomy.main.data.Writers.Writer;

public class BankInv extends FastInv {

	private Main main;
	private Config cfg;
	private Writer writer;

	private boolean preventClose = false;

	public BankInv(Main main) {
		super(45, ChatColor.GOLD + "Bank");

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

}
