package pl.essay.itemMaker.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users", uniqueConstraints = {
		@UniqueConstraint(columnNames = "name")})
@NamedQueries(
		@NamedQuery(
				name = "getUserByName",
				query = "select u from User u where name = :name"
				)
		)
public class User implements UserDetails{

	@Id @GeneratedValue
	@Column(name="id")
	private int id;

	@Column(name="name")
	@NotNull(message="Name must not be empty")
	@Size(min=8, message="Name must be at least 8 characters long")
	private String username = "";

	@Column(name="password")
	@NotNull(message="Password must not be empty")
	@Size(min=6, message="Password must be at least 6 characters long")
	private String password = "";

	@Column(columnDefinition="boolean", name="enabled") 
	private boolean enabled = false;

	@Column(name="roles",nullable = false) 
	private String roles = ""; //roles serialized with ; as separator

	public User(){}

	public User(String name, String pass, String r, boolean e){
		this.username = name;
		this.password = pass;
		this.roles = r;
		this.enabled = e;
	}

	//setters & getters
	public void setId(int id){
		this.id = id;
	}
	public void setUsername(String name){
		this.username = name;
	}
	public void setPassword(String pass){
		this.password = pass;
	}
	public int getId(){
		return this.id;
	}
	public String getUsername(){
		return this.username;
	}
	public String getPassword(){
		return this.password;
	}
	public String getRoles(){
		return this.roles;
	}
	public void setRoles(String r){
		this.roles = r;
	}
	public void addRole(String r){
		this.roles += ";"+r; 
	}

	public String toString(){
		return this.getId() + ":: name : "+this.getUsername();
	}

	public Collection<GrantedAuthority> getAuthorities() {
		Roles roles = new Roles(this.roles);
		return roles.getGrantedAutority();
	}

	public List<String> getRolesList() {
		Roles roles = new Roles(this.roles);
		return roles.getRolesList();
	}


	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public void setEnabled(boolean e) {
		this.enabled = e;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	private class Roles{

		Collection<GrantedAuthority> grantedAuthority;
		List<String> rolesList;

		public Roles(String roesSerialized){
			grantedAuthority = new ArrayList<GrantedAuthority>();
			rolesList = new ArrayList<String>();

			if (!"".equals(roesSerialized)){
				String[] roles = roesSerialized.split(";");
				for (String role: roles)
					if (!"".equals(role)) {
						grantedAuthority.add(new SimpleGrantedAuthority(role));
						rolesList.add(role);
					}
			}
		}

		public Collection<GrantedAuthority> getGrantedAutority(){
			return grantedAuthority;
		}

		public List<String> getRolesList(){
			return rolesList;
		}

		private class SimpleGrantedAuthority implements GrantedAuthority{

			String role;
			public SimpleGrantedAuthority(String r){
				this.role = r;
			}

			public String getAuthority() {
				return this.role;
			}

		}

	}

}
