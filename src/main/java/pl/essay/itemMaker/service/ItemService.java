package pl.essay.itemMaker.service;

import java.util.List;

import pl.essay.itemMaker.model.Item;
import pl.essay.itemMaker.model.ItemComponent;

public interface ItemService {

	    public int addItem(Item i);
		public void updateItem(Item i);
		public List<Item> listItems();
		public Item getItemById(int id);
		public void removeItem(int id);
		public void removeItemComponent(int component);
		public int addItemComponent(ItemComponent component);
		public ItemComponent getItemComponent(int id);
		public List<ItemComponent> listItemComponent(int itemid);
}
