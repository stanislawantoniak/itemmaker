package pl.essay.itemMaker.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Item.class)
public abstract class Item_ {

	public static volatile SingularAttribute<Item, Boolean> isComposed;
	public static volatile SetAttribute<Item, ItemComponent> components;
	public static volatile SingularAttribute<Item, String> name;
	public static volatile SetAttribute<Item, ItemComponent> usedIn;
	public static volatile SingularAttribute<Item, Integer> id;

}

