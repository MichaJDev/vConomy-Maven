package nl.vconomy.main.data.Writers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import nl.vconomy.main.Main;
import nl.vconomy.main.data.Configuration.Config;
import nl.vconomy.main.data.Configuration.Databases.BankDB;

public class Writer {

	private Main main;
	private Config cfg;
	private BankDB bank;

	public Writer(Main main) {
		this.main = main;
		cfg = new Config(main);
		bank = cfg.getBankDB();
	}

	public void Setup() {
		CreateDir();
		CreateLog();
		CreateCreationLog();
		CreateBankLogDir();
	}

	private void CreateDir() {
		File file = new File(main.getDataFolder(), "\\Logs\\");
		file.mkdirs();
	}

	private File GetDir() {
		return new File(main.getDataFolder(), "\\Logs\\");
	}

	private void CreateLog() {
		File file = new File(GetDir(), "errorlog.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException io) {
				main.getLogger().severe(io.getMessage());
			}
		}
	}

	private File GetLogFile() {
		return new File(GetDir(), "errorlog.yml");
	}

	public void writeErrorLog(String msg) {
		FileConfiguration log = YamlConfiguration.loadConfiguration(GetLogFile());
		log.addDefault("[" + LocalDate.now().toString() + "]-[" + LocalTime.now().toString() + "]: ", msg);
		log.options().copyDefaults(true);
		try {
			log.save(GetLogFile());
		} catch (IOException io) {
			main.getLogger().severe(io.getMessage());
		}
	}

	private void CreateBankLogDir() {
		File file = new File(bank.GetDir(), "\\BankLogs\\");
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	private File GetBankLogDir() {
		return new File(bank.GetDir(), "\\BankLogs\\");
	}

	public void createBankFile(Player p) {
		File file = new File(GetBankLogDir(), p.getUniqueId() + ".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException io) {
				main.getLogger().severe(io.getMessage());
				writeErrorLog(io.getMessage());
			}
		}
	}

	private File GetBankFile(UUID uuid) {
		return new File(GetDir(), uuid + ".yml");
	}

	public void writeBankLog(UUID uuid, String msg) {
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(GetBankFile(uuid));
		cfg.addDefault("[" + LocalDate.now().toString() + "]-[" + LocalTime.now().toString() + "]", msg);
	}

	private void CreateCreationLog() {
		File file = new File(GetDir(), "accountCreationLog.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException io) {
				main.getLogger().severe(io.getMessage());
				writeErrorLog(io.getMessage());
			}
		}
	}

	private File GetCreationFile() {
		return new File(GetDir(), "accountCreationLog.yml");
	}

	public void writeCreationLog(Player p) {
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(GetCreationFile());
		cfg.addDefault("[" + LocalDate.now().toString() + "]-[" + LocalTime.now().toString() + "]",
				"Created new Account for " + p.getName() + ":" + p.getUniqueId().toString());
	}
}
