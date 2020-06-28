package nl.vconomy.main.data.Models;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import nl.vconomy.main.Main;
import nl.vconomy.main.data.Configuration.Config;
import nl.vconomy.main.data.Configuration.Databases.MatDB;

public class Product {

	private Main main;
	private String name;
	private int cost;
	private Material mat;
	MatDB MatDb;

	public Product(Main main, int cost, Material mat) {
		this.main = main;
		this.cost = cost;
		this.mat = mat;
		this.MatDb = new Config(main).getMatDB();
		setName();
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	private void setName() {
		ConfigurationSection materials = MatDb.getMats().getConfigurationSection("Materials");
		for (String key : materials.getKeys(false)) {
			if (key.contains(mat.name().toString())) {
				name = key.replaceAll(mat.name().toString(), "").replace('"', ' ').replace(':', ' ');
			}
		}
	}
}
