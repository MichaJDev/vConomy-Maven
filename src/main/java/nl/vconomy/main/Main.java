package nl.vconomy.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import fr.mrmicky.fastinv.FastInvManager;
import nl.vconomy.main.Bussines.Listeners.Listeners;
import nl.vconomy.main.Surface.Commands.CommandHandler;
import nl.vconomy.main.data.Configuration.Config;
import nl.vconomy.main.data.Models.Bank;
import nl.vconomy.main.data.Models.Wallet;
import nl.vconomy.main.data.Models.enums.LogType;

public class Main extends JavaPlugin {

	private Config cfg;
	private List<Bank> banks;
	private List<Wallet> wallets;
	private Listeners list;
	private CommandHandler cmd;

	public void onEnable() {
		FastInvManager.register(this);
		banks = new ArrayList<Bank>();
		wallets = new ArrayList<Wallet>();
		cfg = new Config(this);
		list = new Listeners(this);
		cmd = new CommandHandler(this);
		if (!cfg.getDir().exists()) {
			getLogger().warning("vConomy Configuration and Files not setup");
			Setup();
		}
		GetCommands();
		GetListeners();

	}

	@Override
	public void onLoad() {
		getLogger().info("Added class loader to HeadsPluginApi springClassLoaders");
	}

	private void Setup() {
		log(LogType.INFO, "Setting up Writer");
		cfg.getWriter().Setup();
		log(LogType.INFO, "Setting up Configurations files");
		cfg.Setup();
		log(LogType.INFO, "Setting up Material Database");
		cfg.getMatDB().Setup();
		log(LogType.INFO, "Setting up Wallet Database");
		cfg.getWalletDB().Setup();
		log(LogType.INFO, "Setting up Bank Database");
		cfg.getBankDB().Setup();
	}

	public void GetCommands() {
		getCommand("wallet").setExecutor(cmd.getWalletCommand());
	}

	public void GetListeners() {
		getServer().getPluginManager().registerEvents(list.getLogin(), this);
	}

	public List<Bank> GetBanks() {
		return banks;
	}

	public List<Wallet> GetWallets() {
		return wallets;
	}

	public void log(LogType log, String msg) {
		switch (log) {
		case INFO:
			getLogger().info(msg);
			break;
		case WARNING:
			getLogger().warning(msg);
			break;
		case SEVERE:
			getLogger().warning(msg);
			break;

		}
	}
}
