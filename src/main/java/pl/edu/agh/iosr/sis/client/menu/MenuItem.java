package pl.edu.agh.iosr.sis.client.menu;

import java.util.List;

import pl.edu.agh.iosr.sis.client.authentication.SecurityHelper;

public class MenuItem {

	private String url;
	private String label;
	private boolean isActive = false;
	private List<MenuItem> itemsSublist = null;
	private List<String> requiredRoles;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<MenuItem> getItemsSublist() {
		return itemsSublist;
	}

	public void setItemsSublist(List<MenuItem> itemsSublist) {
		this.itemsSublist = itemsSublist;
	}

	public void setRequiredRoles(List<String> requiredRoles) {
		this.requiredRoles = requiredRoles;
	}

	public boolean isAccessible() {
		for ( String role : requiredRoles ) {
			if ( !SecurityHelper.hasUserRole(role) ) {
				return false;
			}
		}
		return true;
	}

}
