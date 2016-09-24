package pl.essay.itemMaker.model;

import java.util.ArrayList;
import java.util.Collection;

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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="user", uniqueConstraints = {
		@UniqueConstraint(columnNames = "name")})
@NamedQueries(
	    @NamedQuery(
	        name = "get_user_by_name",
	        query = "select u from User u where name = :name"
	    )
	)
public class User implements UserDetails{

	@Id @GeneratedValue
	@Column(name="id")
	private long id;
	
	@Column(name="name")
	@NotNull(message="Name must not be empty")
    @Size(min=8, message="Name must be at least 8 characters long")
	private String username;
	
	@Column(name="password")
	@NotNull(message="Password must not be empty")
    @Size(min=6, message="Password must be at least 6 characters long")
	private String password;
		
	public User(){
	}
	
	public User(String name, String pass){
		this.username = name;
		this.password = pass;
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
	public long getId(){
		return this.id;
	}
	public String getUsername(){
		return this.username;
	}
	public String getPassword(){
		return this.password;
	}
	
	public String toString(){
		return this.getId() + ":: name : "+this.getUsername();
	}

	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> a = new ArrayList<GrantedAuthority>();
		a.add(new GrantedAuthority(){
			public String getAuthority(){
				return "ADMIN";
			}
		});
		return a;
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

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
