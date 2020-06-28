package nl.vconomy.main.data.Models;

import java.util.UUID;

import org.bukkit.entity.Player;

public class Wallet {
	private Player player;
	private UUID uuid;
	private int amount;
	private int maxAmount;

	public Wallet(Player p, int maxAmount) {
		this.player = p;
		this.uuid = p.getUniqueId();
		this.maxAmount = maxAmount;
	}

	public Wallet(Player p, int amount, int maxAmount) {
		this.player = p;
		this.uuid = p.getUniqueId();
		this.amount = amount;
		this.maxAmount = maxAmount;
	}

	public Player getPlayer() {
		return player;
	}

	public UUID getUuid() {
		return uuid;
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
}
