package nl.vconomy.main.Bussines.Listeners;

import nl.vconomy.main.Main;
import nl.vconomy.main.Bussines.Listeners.Auth.LoginListener;

@SuppressWarnings("unused")
public class Listeners {

	private Main main;
	private LoginListener login;

	public Listeners(Main main) {
		this.main = main;
		this.login = new LoginListener(main);
	}

	public LoginListener getLogin() {
		return login;
	}
}
