package nl.vconomy.main.data.Configuration;

import java.io.File;

import nl.vconomy.main.Main;
import nl.vconomy.main.data.Configuration.Databases.BankDB;
import nl.vconomy.main.data.Configuration.Databases.MatDB;
import nl.vconomy.main.data.Configuration.Databases.WalletDB;
import nl.vconomy.main.data.Writers.Writer;

public class Config {
	private MatDB mats;
	private Main main;
	private Writer writer;
	private BankDB bank;
	private WalletDB wallet;

	public Config(Main main) {
		this.main = main;
		mats = new MatDB(main);
		writer = new Writer(main);
		bank = new BankDB(main);
		wallet = new WalletDB(main);
	}

	public void Setup() {
		CreateDir();
	}

	public MatDB getMatDB() {
		return mats;
	}

	public BankDB getBankDB() {
		return bank;
	}

	public Writer getWriter() {
		return writer;
	}

	public WalletDB getWalletDB() {
		return wallet;
	}

	private void CreateDir() {
		File file = new File(main.getDataFolder(), "Config");
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public File getDir() {
		File file = new File(main.getDataFolder(), "\\Config\\");
		if (!file.exists()) {
			return null;
		}
		return file;
	}

}
