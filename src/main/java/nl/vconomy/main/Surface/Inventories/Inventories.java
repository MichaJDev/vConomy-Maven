package nl.vconomy.main.Surface.Inventories;

import nl.vconomy.main.Main;
import nl.vconomy.main.Surface.Inventories.Bank.BankInv;
import nl.vconomy.main.Surface.Inventories.Wallet.WalletInv;
import nl.vconomy.main.data.Configuration.Config;
import nl.vconomy.main.data.Writers.Writer;

@SuppressWarnings("unused")
public class Inventories {

	private Main main;
	private Config cfg;
	private Writer writer;

	public Inventories(Main main) {
		this.main = main;
		cfg = new Config(main);
		writer = cfg.getWriter();
	}

	public WalletInv getWalletInv() {
		return new WalletInv(main);
	}

	public BankInv getBankInv() {
		return new BankInv(main);
	}

}
