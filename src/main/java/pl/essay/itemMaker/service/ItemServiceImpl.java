package pl.essay.itemMaker.service;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.essay.itemMaker.dao.ItemComponentDao;
import pl.essay.itemMaker.dao.ItemDao;
import pl.essay.itemMaker.model.Item;
import pl.essay.itemMaker.model.ItemComponent;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{

	@Inject private ItemDao itemDao;
	@Inject private ItemComponentDao itemComponentDao;

	public void updateItem(Item i){
		this.itemDao.update(i);
	}
	public int addItem(Item i){
		this.itemDao.create(i);
		return i.getId();
	}
	public List<Item> listItems(){
		return this.itemDao.getAll();
	}
	public Item getItemById(int id){
		Item item = this.itemDao.get(id);
		return item;
	}
	public void removeItem(int id){
		Item item = this.itemDao.load(id);
		this.itemDao.delete(item);
	}

	public void removeItemComponent(int componentId){
		ItemComponent ic = this.itemComponentDao.load(componentId);
		Item item = ic.getParent();
		item.removeComponent(componentId);
		this.itemDao.update(item);
	}

	public ItemComponent getItemComponent(int id){
		return this.itemComponentDao.load(id);
	}

	public int addItemComponent(ItemComponent component){

		if (component.getId() == 0){
			int itemId = component.getParent().getId();
			Item item = this.itemDao.load(itemId);
			item.addComponent(component);
			this.itemDao.update(item);
		} else {
			this.itemComponentDao.update(component);
		}

		return component.getId();
	}
	
	public Set<ItemComponent> getItemComponentsByParent(int id) {
		return this.itemComponentDao.getItemComponentsByParent(id);
	}

}
