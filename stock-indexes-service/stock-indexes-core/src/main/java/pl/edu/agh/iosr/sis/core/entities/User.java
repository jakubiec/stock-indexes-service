package pl.edu.agh.iosr.sis.core.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String login;

	private String password;

	private Boolean isAdmin;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "available_indexes", 
		joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, 
		inverseJoinColumns = { @JoinColumn(name = "index_symbol", referencedColumnName = "symbol") })
	private Set<Index> availableIndexes;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public void addAvailableIndex(Index index){
		this.availableIndexes.add(index);
	}
	
	public void removeAvailableIndex(Index index){
		if(availableIndexes.contains(index))
			availableIndexes.remove(index);
	}

}
