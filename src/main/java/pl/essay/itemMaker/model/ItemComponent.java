package pl.essay.itemMaker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.validation.constraints.*;

@Entity
@Table
//@SequenceGenerator(name="itemcomponent_seq", initialValue=1, allocationSize=100)
public class ItemComponent {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)//, generator="itemcomponent_seq")
	@Column
	private int id;
	
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Item parent;
	
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Item component;
	
	@Column
	@NotNull
    @DecimalMin("1")
	private int quantity;
	
	@Column
	private String remarks;
	
	
	public ItemComponent(){
	}
	
	public ItemComponent(Item parent){
		this.parent = parent;
	}
	
	//setters & getters
	public Item getParent(){
		return this.parent;
	}
	public void setParent(Item i){
		this.parent = i;
	}	
	public void setId(int i){
		this.id = i;
	}
	public int getId(){
		return this.id;
	}
	
	public void setQuantity(int q){
		this.quantity = q;
	}
	public int getQuantity(){
		return this.quantity;
	}
	public void setRemarks(String r){
		this.remarks = r;
	}
	public String getRemarks(){
		return this.remarks;
	}
	public void setComponent(Item q){
		this.component = q;
	}
	public Item getComponent(){
		return this.component;
	}
	public String getComponentName(){
		return (this.component != null ? this.component.getName() : "");
	}
	public int getComponentId(){
		return (this.component != null ? this.component.getId() : 0 );
	}
	public String toString(){
		return "component id: "+this.getId()+" parent :: "+this.getParent().getId()+ ":: item: #"+this.getComponentId()+" "+this.getComponentName()+":: qty: "+this.getQuantity();
	}
}
