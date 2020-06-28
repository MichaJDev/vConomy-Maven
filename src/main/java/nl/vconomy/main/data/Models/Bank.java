package nl.vconomy.main.data.Models;

import java.util.UUID;

import org.bukkit.entity.Player;

public class Bank {
	private Player player;
	private UUID bankUUID;
	private int amount;
	private int maxAmount;

	public Bank(Player p, int maxAmount) {
		this.player = p;
		this.bankUUID = p.getUniqueId();
	}

	public Bank(Player p, int amount, int maxAmount) {

		this.player = p;
		this.bankUUID = p.getUniqueId();
		this.amount = amount;
		this.maxAmount = maxAmount;
	}

	public Player getPlayer() {
		return player;
	}

	public UUID getBankUUID() {
		return bankUUID;
	}

	public int getAmount() {
		return amount;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}

	public void Withdraw(int amount) {
		this.amount = (this.amount - amount);
	}

	public void Deposit(int amount) {
		this.amount = (this.amount + amount);
	}

}
