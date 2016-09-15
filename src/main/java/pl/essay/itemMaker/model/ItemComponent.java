package pl.essay.itemMaker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="item_component")
public class ItemComponent {

	@Id @GeneratedValue
	@Column(name="id")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = false)
	private Item parentItem;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="component_item", referencedColumnName = "id")
	private Item componentItem;
	
	@Column(name="quantity")
	private int quantity;
	
	public ItemComponent(){
	}
	
	public ItemComponent(Item parent){
		this.parentItem = parent;
	}
	
	//setters & getters
	public Item getParentItem(){
		return this.parentItem;
	}
	public void setParentItem(Item i){
		this.parentItem = i;
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
	public void setComponentItem(Item q){
		this.componentItem = q;
	}
	public Item getComponentItem(){
		return this.componentItem;
	}
	public String getComponentName(){
		return (this.componentItem != null ? this.componentItem.getName() : "");
	}
	public int getComponentId(){
		return (this.componentItem != null ? this.componentItem.getId() : 0 );
	}
	public String toString(){
		return "component id: "+this.getId()+" parent :: "+this.getParentItem().getId()+ ":: item: #"+this.getComponentId()+" "+this.getComponentName()+":: qty: "+this.getQuantity();
	}
}
