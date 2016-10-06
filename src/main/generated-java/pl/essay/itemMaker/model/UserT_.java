package pl.essay.itemMaker.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserT.class)
public abstract class UserT_ {

	public static volatile SingularAttribute<UserT, String> password;
	public static volatile SingularAttribute<UserT, String> roles;
	public static volatile SingularAttribute<UserT, Integer> id;
	public static volatile SingularAttribute<UserT, Boolean> enabled;
	public static volatile SingularAttribute<UserT, String> username;

}

