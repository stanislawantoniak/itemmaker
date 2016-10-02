package pl.essay.itemMaker.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.essay.itemMaker.model.Item;
import pl.essay.itemMaker.model.ItemComponent;

@Repository
@Transactional
public class ItemDaoTransactional extends AbstractDaoHbn<Item> implements ItemDao {

	@Override
	public Item load(Serializable id) {
		Item item = super.load(id);
		item.getComponents(); 
		return item;
	}
	
	public void addComponent(ItemComponent ic){
		Item item = super.load( ic.getParent().getId() );
		item.addComponent( ic );
		this.update(item);
	}

}
