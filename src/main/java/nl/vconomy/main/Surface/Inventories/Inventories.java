package nl.vconomy.main.Surface.Inventories;

import nl.vconomy.main.Main;
import nl.vconomy.main.Surface.Inventories.Wallet.WalletInv;
import nl.vconomy.main.data.Configuration.Config;
import nl.vconomy.main.data.Writers.Writer;

public class Inventories {

	private Main main;
	private Config cfg;
	private Writer writer;
	private WalletInv wInv;
	public Inventories(Main main) {
		this.main = main;
		cfg = new Config(main);
		writer = cfg.getWriter();
	}
	
	public WalletInv getWalletInv() {
		return new WalletInv(main);
	}

}
