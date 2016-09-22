package pl.essay.itemMaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.essay.itemMaker.dao.ItemDao;
import pl.essay.itemMaker.model.Item;
import pl.essay.itemMaker.model.ItemComponent;

@Service
public class ItemServiceImpl implements ItemService{

	private ItemDao itemDao;
	
	@Autowired
	public void setItemDAO(ItemDao i){
		this.itemDao = i;
	}
	
	public void updateItem(Item i){
		this.itemDao.updateItem(i);
	}
	public int addItem(Item i){
		return this.itemDao.addItem(i);
	}
		public List<Item> listItems(){
		return this.itemDao.listItems();
	}
	public Item getItemById(int id){
		return this.itemDao.getItemById(id);
	}
	public void removeItem(int id){
		this.itemDao.removeItem(id);
	}
	
	public void removeItemComponent(int component){
		this.itemDao.removeItemComponent(component);
	}
	
	public ItemComponent getItemComponent(int id){
		return this.itemDao.getItemComponentById(id);
	}
	public int addItemComponent(ItemComponent component){
		return this.itemDao.addItemComponent(component);
	}
	public List<ItemComponent> listItemComponent(int itemid){
		Item item = this.getItemById(itemid);
		return item.getItemComponents();
		//this.itemDAO.listItemComponents(item);
	}
}
