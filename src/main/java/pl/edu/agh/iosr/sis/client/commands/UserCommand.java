package pl.edu.agh.iosr.sis.client.commands;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserCommand {

	@Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric with no spaces")
	private String login;

	@Size(min = 6, max = 20, message = "The password must be at least 6 characters long.")
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
