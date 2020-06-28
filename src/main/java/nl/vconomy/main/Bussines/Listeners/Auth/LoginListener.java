package nl.vconomy.main.Bussines.Listeners.Auth;

import java.io.File;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import nl.vconomy.main.Main;
import nl.vconomy.main.data.Configuration.Config;
import nl.vconomy.main.data.Models.Bank;
import nl.vconomy.main.data.Models.Wallet;
import nl.vconomy.main.data.Models.enums.LogType;
import nl.vconomy.main.data.Writers.Writer;

public class LoginListener implements Listener {
	private Main main;
	private Config cfg;
	private Writer writer;

	public LoginListener(Main main) {
		this.main = main;
		cfg = new Config(main);
		writer = cfg.getWriter();
	}

	@EventHandler
	public void OnLogin(PlayerJoinEvent e) {
		for (File f : cfg.getBankDB().GetDir().listFiles()) {
			if (!f.getName().contains(e.getPlayer().getUniqueId().toString())) {
				main.getLogger().info("Player logged in for the first time, creating bankaccount!");
				cfg.getBankDB().CreateBank(e.getPlayer());
				writer.writeCreationLog(e.getPlayer());
			} else {
				main.GetBanks().add(cfg.getBankDB().GetBank(e.getPlayer()));
			}
		}
		for (File f : cfg.getWalletDB().GetDir().listFiles()) {
			if (!f.getName().contains(e.getPlayer().getUniqueId().toString())) {
				main.getLogger().info("Player logged in for the first time, creating bankaccount!");
				cfg.getWalletDB().CreateWallet(e.getPlayer());
				writer.writeCreationLog(e.getPlayer());
			} else {
				main.GetWallets().add(cfg.getWalletDB().GetWallet(e.getPlayer()));
			}
		}
	}

	public void OnLogout(PlayerQuitEvent e) {
		main.log(LogType.INFO,
				e.getPlayer().getName() + ":" + e.getPlayer().getUniqueId().toString() + " has logged out");
		main.log(LogType.INFO, "Saving bank and Wallet");
		Bank bank = cfg.getBankDB().GetBank(e.getPlayer());
		cfg.getBankDB().SaveBank(e.getPlayer(), bank);
		main.log(LogType.INFO, "Bank saved, saving wallet");
		Wallet wallet = cfg.getWalletDB().GetWallet(e.getPlayer());
		main.log(LogType.INFO, "Wallet saved, saving complete!");
		cfg.getWalletDB().SaveWallet(e.getPlayer(), wallet);
		writer.writeBankLog(e.getPlayer().getUniqueId(), "Logout | Bank Account Saved");

	}
}
