package pl.essay.itemMaker.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.essay.itemMaker.model.ItemComponent;

@Repository
@Transactional
public class ItemComponentDaoImpl extends AbstractDaoHbn<ItemComponent> implements ItemComponentDao{

	@Override
	public ItemComponent load(Serializable id) {
		ItemComponent ic = super.load(id);
		ic.getParent();
		ic.getComponent();
		return ic;
	}
	
}
