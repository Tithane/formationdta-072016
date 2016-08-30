package fr.pizzeria.ihm;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import fr.pizzeria.model.Pizza;

public class Lister<T> extends Action {
	private Class<T> entityClass;
	
	
	
	public Lister(Class<T> maClasse,IhmHelper helper) {
		super("Lister les "+maClasse.getSimpleName()+"s", helper);
		//this.entityClass =  (Class<T>)getClass().getGenericSuperclass().getClass();
		//this.setLibelle("Lister les "+entityClass.getSimpleName()+"s");
		this.entityClass = maClasse;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		System.out.println("**** Liste de "+entityClass.getSimpleName()+"s ****");
		
		Collection<T> objectsT = this.helper.getStockage(entityClass).findAll();
		
		for (T objectT : objectsT) {
			System.out.println(objectT);
		}
		System.out.println("\n");
	}
	
	

}
