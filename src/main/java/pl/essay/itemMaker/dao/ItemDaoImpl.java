package pl.essay.itemMaker.dao;

import org.springframework.stereotype.Repository;
import pl.essay.itemMaker.model.Item;
import pl.essay.itemMaker.model.ItemComponent;

@Repository
public class ItemDaoImpl extends AbstractDaoHbn<Item> implements ItemDao {

	public void addComponent(ItemComponent ic){
		Item item = super.load( ic.getParent().getId() );
		item.addComponent( ic );
		this.update(item);
	}

}
