package nl.vconomy.main.Surface.Commands.Bank;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.vconomy.main.Main;
import nl.vconomy.main.Surface.Inventories.Inventories;
import nl.vconomy.main.data.Configuration.Config;
import nl.vconomy.main.data.Configuration.Databases.BankDB;
import nl.vconomy.main.data.Configuration.Databases.SkullDB;
import nl.vconomy.main.data.Configuration.Databases.WalletDB;
import nl.vconomy.main.data.Models.Bank;
import nl.vconomy.main.data.Writers.SkullCreator;
import nl.vconomy.main.data.Writers.Writer;

@SuppressWarnings("unused")
public class BankCmd implements CommandExecutor {
	private Main main;
	private Config cfg;

	private Writer writer;
	private BankDB bank;
	private WalletDB wallet;

	private Inventories inv;

	public BankCmd(Main main) {
		this.main = main;
		cfg = new Config(main);
		writer = cfg.getWriter();
		bank = cfg.getBankDB();
		wallet = cfg.getWalletDB();

	}

	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if (c.getName().equalsIgnoreCase("bank")) {
			if (s.hasPermission("vconomo.bank")) {
				if (s instanceof Player) {
					Player p = (Player) s;
					Bank pBank = bank.GetBank(p);
					if (args.length == 0) {
						String amount = String.valueOf(pBank.getAmount());
						char[] digits = amount.toCharArray();
						for (char Char : digits) {
							inv.getBankInv().addItem(SkullCreator.itemFromBase64(SkullDB.getSkull(Char)));
						}
						inv.getBankInv().open(p);
					}
				}
			}
		}
		return false;
	}

}
