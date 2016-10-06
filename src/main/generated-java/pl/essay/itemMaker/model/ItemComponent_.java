package pl.essay.itemMaker.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItemComponent.class)
public abstract class ItemComponent_ {

	public static volatile SingularAttribute<ItemComponent, Item> parent;
	public static volatile SingularAttribute<ItemComponent, Item> component;
	public static volatile SingularAttribute<ItemComponent, Integer> quantity;
	public static volatile SingularAttribute<ItemComponent, Integer> id;
	public static volatile SingularAttribute<ItemComponent, String> remarks;

}

