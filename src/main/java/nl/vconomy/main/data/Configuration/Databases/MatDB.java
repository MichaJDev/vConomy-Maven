package nl.vconomy.main.data.Configuration.Databases;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import nl.vconomy.main.Main;
import nl.vconomy.main.data.Configuration.Config;
import nl.vconomy.main.data.Writers.Writer;

public class MatDB {

	private Main main;
	private Config cfg;
	private Writer writer;

	public MatDB(Main main) {
		this.main = main;
		cfg = new Config(main);
		writer = cfg.getWriter();
	}

	public void Setup() {
		Create();
	}

	private void CreateDir() {
		File file = new File(cfg.getDir(), "\\Materials\\");
		file.mkdirs();
	}

	public File GetDir() {
		File file = new File(cfg.getDir(), "\\Materials\\");
		if (!file.exists()) {
			return null;
		}
		return file;
	}

	private void Create() {
		CreateDir();
		CreateFile();
	}

	private void CreateFile() {
		File file = new File(GetDir(), "Materials.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException io) {
				main.getLogger().severe(io.getMessage());
				writer.writeErrorLog(io.getMessage());
			}
		}
	}

	public File GetFile() {
		return new File(GetDir(), "Materials.yml");
	}

	public FileConfiguration getMats() {
		return YamlConfiguration.loadConfiguration(GetFile());
	}
}
