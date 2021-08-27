package Model;

import Helper.DBConnection;

public class User {
	private int id;
	String idno, password, name, type;
	DBConnection conn = new DBConnection();
	
	public User(int id, String idno, String password, String name, String type) {
		this.id = id;
		this.idno = idno;
		this.password = password;
		this.name = name;
		this.type = type;
	}
	
	public User() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
