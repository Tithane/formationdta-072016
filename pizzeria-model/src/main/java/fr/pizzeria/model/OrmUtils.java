package fr.pizzeria.model;
/*
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

public class OrmUtils {
	public static <T> T initializeAndUnproxy(T entity) {
		if (entity == null) {
			return null;
		}
		if (entity instanceof HibernateProxy) {
			Hibernate.initialize(entity);
			entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
		}
		return entity;
	}
}
*/