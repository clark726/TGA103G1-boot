package TGA103G1boot.chat.model;

import java.util.Set;

public class State {
	private String type;
	// the user changing the state
	private String user;
	// total users
	private Set<String> users;
	private Set<String> otherNames;

	public State(String type, String user, Set<String> users) {
		super();
		this.type = type;
		this.user = user;
		this.users = users;
	}
	
	public State(String type, String user, Set<String> users, Set<String> otherNames) {
		super();
		this.type = type;
		this.user = user;
		this.users = users;
		this.otherNames = otherNames;
	}




	public Set<String> getOtherNames() {
		return otherNames;
	}


	public void setOtherNames(Set<String> otherNames) {
		this.otherNames = otherNames;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Set<String> getUsers() {
		return users;
	}

	public void setUsers(Set<String> users) {
		this.users = users;
	}

}
