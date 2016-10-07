package pl.essay.itemMaker.dao;

import java.util.Set;

import pl.essay.itemMaker.model.ItemComponent;

public interface ItemComponentDao extends Dao<ItemComponent>{

	public Set<ItemComponent> getItemComponentsByParent(int id);
	
}
