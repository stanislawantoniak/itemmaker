package pl.essay.itemMaker.service;

import java.util.List;

import pl.essay.itemMaker.dao.ItemDao;
import pl.essay.itemMaker.model.Item;
import pl.essay.itemMaker.model.ItemComponent;

public class ItemServiceImpl implements ItemService{

	private ItemDao itemDAO;
	public void setItemDAO(ItemDao i){
		this.itemDAO = i;
	}
	
	public void updateItem(Item i){
		this.itemDAO.updateItem(i);
	}
	public int addItem(Item i){
		return this.itemDAO.addItem(i);
	}
		public List<Item> listItems(){
		return this.itemDAO.listItems();
	}
	public Item getItemById(int id){
		return this.itemDAO.getItemById(id);
	}
	public void removeItem(int id){
		this.itemDAO.removeItem(id);
	}
	
	public void removeItemComponent(int component){
		this.itemDAO.removeItemComponent(component);
	}
	
	public ItemComponent getItemComponent(int id){
		return this.itemDAO.getItemComponentById(id);
	}
	public int addItemComponent(ItemComponent component){
		return this.itemDAO.addItemComponent(component);
	}
	public List<ItemComponent> listItemComponent(int itemid){
		Item item = this.getItemById(itemid);
		return item.getItemComponents();
		//this.itemDAO.listItemComponents(item);
	}
}
