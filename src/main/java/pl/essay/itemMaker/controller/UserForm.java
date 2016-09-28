package pl.essay.itemMaker.controller;

import java.util.ArrayList;
import java.util.List;

import pl.essay.itemMaker.model.User;

public class UserForm {
	
	public static final String roleAdmin = "ROLE_ADMIN";
	public static final String roleUser = "ROLE_USER";
	public List<String> getAllRoles(){
		List<String> list = new ArrayList<String>();
		list.add(UserForm.roleAdmin);
		list.add(UserForm.roleUser);
		return list;
	}
	
	private int id;
	private String username, password;
	private List<String> rolesSelected;
	
	private boolean enabled;
	
	public UserForm(){}
	
	public UserForm(User u){
		this.id = u.getId();
		this.username = u.getUsername();
		this.enabled = u.isEnabled();
		this.rolesSelected = u.getRolesList();
	}
	
	public void setUsername(String s){
		this.username = s;
	}
	public void setPassword(String s){
		this.password = s;
	}
	public void setRolesSelected(List<String> s){
		this.rolesSelected = s;
	}
	public void setEnabled(boolean s){
		this.enabled = s;
	}
	
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	public List<String> getRolesSelected(){
		return rolesSelected;
	}
	public boolean getEnabled(){
		return enabled;
	}
	public void setId(int i){
		this.id = i;
	}
	public int getId(){
		return this.id;
	}
		
}
