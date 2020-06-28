package nl.vconomy.main.data.Configuration.Databases;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import nl.vconomy.main.Main;
import nl.vconomy.main.data.Configuration.Config;
import nl.vconomy.main.data.Models.Wallet;
import nl.vconomy.main.data.Models.enums.LogType;
import nl.vconomy.main.data.Writers.Writer;

public class WalletDB {
	private Main main;
	private Config cfg;
	private Writer writer;

	public WalletDB(Main main) {
		this.main = main;
		cfg = new Config(main);
		writer = cfg.getWriter();
	}

	public void Setup() {
		CreateDir();
		CreateSettings();
	}

	private void CreateDir() {
		File file = new File(main.getDataFolder(), "\\Wallets\\");
		file.mkdirs();
	}

	public File GetDir() {
		return new File(main.getDataFolder(), "\\Wallets\\");
	}

	private void CreateSettings() {
		File file = new File(GetDir(), "settings.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException io) {
				main.getLogger().severe(io.getMessage());
				writer.writeErrorLog(io.getMessage());
			}
			NestSettings();
		}
	}

	private File GetSettingsFile() {
		return new File(GetDir(), "settings.yml");
	}

	private FileConfiguration GetSettings() {
		return YamlConfiguration.loadConfiguration(GetSettingsFile());
	}

	private void NestSettings() {
		FileConfiguration cfg = GetSettings();
		cfg.addDefault("MaxAmount", 250000);
		cfg.options().copyDefaults(true);
		try {
			cfg.save(GetSettingsFile());
		} catch (IOException io) {
			main.getLogger().severe(io.getMessage());
			writer.writeErrorLog(io.getMessage());
		}
	}

	private File GetFile(Player p) {
		File file = new File(GetDir(), p.getUniqueId().toString() + ".yml");
		if (!file.exists()) {
			return null;
		}
		return file;
	}

	public Wallet GetWallet(Player p) {
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(GetFile(p));
		Wallet wallet = new Wallet(p, cfg.getInt("Amount"), cfg.getInt("MaxAmount"));

		return wallet;
	}

	public void CreateWallet(Player p) {
		File file = new File(GetDir(), p.getUniqueId().toString() + ".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException io) {
				main.getLogger().severe(io.getMessage());
				writer.writeErrorLog(io.getMessage());
			}
			NestWalletStats(p);
		}
	}

	public void SaveWallet(Player p, Wallet wallet) {
		FileConfiguration cfgWallet = YamlConfiguration.loadConfiguration(GetFile(p));
		cfgWallet.set("Amount", wallet.getAmount());
		cfgWallet.set("MaxAmount", wallet.getMaxAmount());
		cfgWallet.options().copyDefaults();
		try {
			cfgWallet.save(GetFile(p));
		} catch (IOException io) {
			main.log(LogType.SEVERE, io.getMessage());
			writer.writeErrorLog(io.getMessage());
		}
		;
	}

	private void NestWalletStats(Player p) {
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(GetFile(p));
		cfg.addDefault("Amount", 0);
		cfg.options().copyDefaults(true);
		try {
			cfg.save(GetFile(p));
		} catch (IOException io) {
			main.getLogger().severe(io.getMessage());
			writer.writeErrorLog(io.getMessage());
		}

	}

}
