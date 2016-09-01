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
		String open = "\t<"+entityClass.getSimpleName()+">\n";
		String close = "\t</"+entityClass.getSimpleName()+">\r";
		String declaration = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r" ;
		Collection<T> objectsT = this.helper.getStockage(entityClass).findAll();
		try {
			Files.deleteIfExists(Paths.get("data",entityClass.getSimpleName()+ ".mxl"));
			Path newFile =Files.createFile(Paths.get("data",entityClass.getSimpleName()+ ".xml"));
			Files.write(newFile, Arrays.asList(declaration+"<Pizzeria>\r"), StandardOpenOption.APPEND);
			objectsT.stream().forEach(t -> {
					try {
						Files.write(newFile, Arrays.asList(open+t.toStringXml()+close), StandardOpenOption.APPEND);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur : "+e.getMessage());
					}
				});
			Files.write(newFile, Arrays.asList("</Pizzeria>"), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur : "+e.getMessage());
		}
		
		
		
		
		
			
			
			
	}
	
	
	
}
