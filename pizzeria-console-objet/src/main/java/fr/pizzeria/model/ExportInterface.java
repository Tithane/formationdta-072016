package fr.pizzeria.model;

import java.io.File;

public interface ExportInterface<T>{
	public String toStringXml();
	
	public T importXML(File myFile);
}
