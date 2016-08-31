package fr.pizzeria.ihm;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collection;

import fr.pizzeria.model.ExportInterface;

public class ExportAction<T extends ExportInterface> extends Action {
	
	private Class<T> entityClass;
	
	public ExportAction(Class<T> maClasse,IhmHelper helper) {
		super("Exporter info "+maClasse.getSimpleName(), helper);
		this.entityClass = maClasse;
	}
	
	
	@Override
	public void execute() {
		String open = "<"+entityClass.getSimpleName()+">\n";
		String close = "</"+entityClass.getSimpleName()+">\n";
		Collection<T> objectsT = this.helper.getStockage(entityClass).findAll();
		try {
			Files.deleteIfExists(Paths.get("data",entityClass.getSimpleName()+ ".txt"));
			Path newFile =Files.createFile(Paths.get("data",entityClass.getSimpleName()+ ".txt"));
			objectsT.stream().forEach(t -> {
					try {
						Files.write(newFile, Arrays.asList(open+t.toStringXml()+close), StandardOpenOption.APPEND);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur : "+e.getMessage());
					}
				});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur : "+e.getMessage());
		}
		
		
		
		
		
			
			
			
	}
	
	
	
}
