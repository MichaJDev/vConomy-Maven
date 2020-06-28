package nl.vconomy.main.Surface.Commands.Wallet;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.vconomy.main.Main;
import nl.vconomy.main.Bussines.Checks.AmountChecker;
import nl.vconomy.main.Surface.Inventories.Inventories;
import nl.vconomy.main.data.Configuration.Config;
import nl.vconomy.main.data.Configuration.Databases.WalletDB;
import nl.vconomy.main.data.Models.Wallet;
import nl.vconomy.main.data.Models.enums.LogType;
import nl.vconomy.main.data.Writers.Writer;

@SuppressWarnings("unused")
public class WalletCmd implements CommandExecutor {
	private Main main;
	private Config cfg;

	private Writer writer;
	private WalletDB wallet;

	private Inventories inv;

	public WalletCmd(Main main) {
		this.main = main;
		cfg = new Config(main);
		writer = cfg.getWriter();
		wallet = cfg.getWalletDB();
		inv = new Inventories(main);

	}

	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {

		if (c.getName().equalsIgnoreCase("wallet")) {
			if (s instanceof Player) {
				Player p = (Player) s;
				Wallet wall = wallet.GetWallet(p);
				if (args.length == 0) {
					if (s.hasPermission("vConomy.wallet")) {

						inv.getWalletInv().open(p);
						// inv.getWalletInv().addItem(ItemOne);
					}
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("pay")) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&cCannot use pay like that: /wallet pay <player> <amount>"));
					}
				} else if (args.length > 1) {
					if (args[0].equalsIgnoreCase("pay")) {
						if (!args[1].isEmpty() || AmountChecker.isInteger(args[1])) {
							Player tp = main.getServer().getPlayer(args[1]);
							Wallet tpWal = wallet.GetWallet(tp);
							Wallet myWal = wallet.GetWallet(p);
							if (AmountChecker.isInteger(args[2])) {
								if (!(myWal.getAmount() - Integer.parseInt(args[2]) < 0)) {
									tpWal.setAmount(tpWal.getAmount() + Integer.parseInt(args[2]));
									myWal.setAmount(myWal.getAmount() - Integer.parseInt(args[2]));
								} else {
									p.sendMessage(ChatColor.translateAlternateColorCodes('&',
											"&cCannot use more than wallet balance to pay."));
								}
							} else {
								p.sendMessage(ChatColor.translateAlternateColorCodes('&',
										"&cExpected integer(number): /wallet pay <player> <amount>"));
							}
						}
					} else {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&cPlayer slot cannot be empty: /wallet pay <player> <amount>"));
					}
				}
			} else {
				main.log(LogType.INFO, "Console is not allowed to use this command.");

			}
		}

		return false;
	}
}
