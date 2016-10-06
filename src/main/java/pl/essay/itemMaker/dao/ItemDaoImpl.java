package pl.essay.itemMaker.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.essay.itemMaker.model.Item;
import pl.essay.itemMaker.model.ItemComponent;
import pl.essay.itemMaker.model.UserT;

@Repository
public class ItemDaoImpl extends AbstractDaoHbn<Item> implements ItemDao {

	public void addComponent(ItemComponent ic){
		Item item = super.load( ic.getParent().getId() );
		item.addComponent( ic );
		this.update(item);
	}
	
}
