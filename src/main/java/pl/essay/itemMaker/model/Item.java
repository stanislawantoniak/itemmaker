package pl.essay.itemMaker.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Type;  

@Entity
@Table(name="item", uniqueConstraints = {
		@UniqueConstraint(columnNames = "name")})
public class Item {
	@Id @GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="is_composed") @Type(type="yes_no")
	private Boolean isComposed = false;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parentItem")
	private List<ItemComponent> itemComponents = new ArrayList<ItemComponent>();
	
	public Item(){
	}
	
	public Item(String name, Boolean composed_TF){
		this.setName(name);
		this.setIsComposed(composed_TF);
	}
	
	//setters & getters
	public void setId(int id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setIsComposed(Boolean composed_TF){
		this.isComposed = composed_TF;
	}
	
	public int getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public Boolean getIsComposed(){
		return this.isComposed;
	}
	public String getIsComposedString(){
		return (this.isComposed ? "Yes" : "No") ;
	}
	
	public List<ItemComponent> getItemComponents() {
		return this.itemComponents;
	}
	public void setItemComponents(List<ItemComponent> ic) {
		this.itemComponents = ic;
	}
	
	public String toString(){
		return this.getId() + ":: name : "+this.getName()+":: is composed : "+this.getIsComposedString();
	}
}
