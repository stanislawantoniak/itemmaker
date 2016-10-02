package pl.essay.itemMaker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="item", uniqueConstraints = {
		@UniqueConstraint(columnNames = "name")}
)
//@SequenceGenerator(name="item_seq", initialValue=1, allocationSize=100)
public class Item {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)//, generator="item_seq")
	@Column(name="id")
	private int id;

	@Column(name="name")
	@NotNull(message="Name must not be empty")
	@Size(min=3, message="Name must be at least 3 characters long")
	private String name;

	@Column(name="is_composed") @Type(type="yes_no")
	private Boolean isComposed = false;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parent", cascade=CascadeType.ALL)
	private Set<ItemComponent> components = new HashSet<ItemComponent>();
	//private Set itemComponents = new ArrayList<ItemComponent>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "component")
	private Set<ItemComponent> usedIn = new HashSet<ItemComponent>();
	
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

	public Set<ItemComponent> getComponents() {
		return this.components;
	}

	public void addComponent(ItemComponent ic){
		this.removeComponent(ic.getId());			
		this.components.add(ic);
		this.isComposed = true;
		ic.setParent(this);
	}
	
	public void removeComponent(int icId){
		System.out.println("components size (1): "+components.size());
		ItemComponent icToRemove = null;
		for (ItemComponent ic : components){
			if (icId == ic.getId()){
				icToRemove = ic;
				System.out.println("ic: "+icId+" current comp:"+ic);
				break;
			}
		}
		System.out.println("to remove "+icToRemove);
		components.remove(icToRemove);
		System.out.println("components size (2): "+components.size());
		if (components.size() == 0)
			this.isComposed = false;
	}

	public void setComponents(Set<ItemComponent> ic) {
		this.components = ic;
	}
	
	public Set<ItemComponent> getUsedIn() {
		return this.usedIn;
	}

	public void setUsedIn(Set<ItemComponent> ic) {
		this.usedIn = ic;
	}

	public String toString(){
		return this.getId() + ":: name : "+this.getName()+":: is composed : "+this.getIsComposedString();
	}
}
