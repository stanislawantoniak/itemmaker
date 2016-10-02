package pl.essay.itemMaker.dao;

import pl.essay.itemMaker.model.Item;
import pl.essay.itemMaker.model.ItemComponent;

public interface ItemDao extends Dao<Item> {

	public void addComponent(ItemComponent ic);
	
}
