package nl.vconomy.main.Surface.Commands;

import nl.vconomy.main.Main;
import nl.vconomy.main.Surface.Commands.Wallet.WalletCmd;
import nl.vconomy.main.data.Configuration.Config;
import nl.vconomy.main.data.Models.enums.LogType;
import nl.vconomy.main.data.Writers.Writer;

public class CommandHandler {

	private Main main;
	private Config cfg;
	private Writer writer;
	private WalletCmd wCmd;

	public CommandHandler(Main main) {
		this.main = main;
		cfg = new Config(main);
		writer = cfg.getWriter();
		wCmd = new WalletCmd(main);
		writer.writeErrorLog("CommandHandler Setup");
		main.log(LogType.INFO, "Setup CommandHandler Succesfully!");
	}

	public WalletCmd getWalletCommand() {
		return wCmd;
	}
}
