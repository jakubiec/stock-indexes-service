package pl.edu.agh.iosr.sis.client.commands;

import java.util.List;

public class UserCommand {

	private String login;

	private String password;

	private List<String> indexes;

	private Boolean isAdmin;

	private Boolean isNew;

	public UserCommand() {
	}

	public UserCommand(boolean isNew) {
		this.isNew = isNew;
	}

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

	public List<String> getIndexes() {
		return indexes;
	}

	public void setIndexes(List<String> indexes) {
		this.indexes = indexes;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

}
