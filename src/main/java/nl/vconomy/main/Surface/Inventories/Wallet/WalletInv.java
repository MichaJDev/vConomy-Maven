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
import nl.vconomy.main.data.Configuration.Databases.SkullDB;
import nl.vconomy.main.data.Configuration.Databases.WalletDB;
import nl.vconomy.main.data.Writers.SkullCreator;
import nl.vconomy.main.data.Writers.Writer;

@SuppressWarnings("unused")
public class WalletInv extends FastInv {

	private Main main;
	private Config cfg;
	private Writer writer;

	private WalletDB wallet = cfg.getWalletDB();
	private boolean preventClose = false;

	public WalletInv(Main main) {
		super(45, ChatColor.GREEN + "Wallet");

		setItem(22, new ItemStack(Material.IRON_SWORD), e -> e.getWhoClicked().sendMessage("You clicked on the sword"));

		setItems(getBorders(), new ItemBuilder(Material.LAPIS_BLOCK).name(" ").build());

		setItem(34, new ItemBuilder(Material.BARRIER).name(ChatColor.RED + "Prevent close").build(), e -> {
			preventClose = !preventClose;
		});
		ItemStack skull = SkullCreator.itemFromBase64(SkullDB.getSkull(0));

		setCloseFilter(p -> preventClose);
		this.addItem(skull);
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
