package fr.pizzeria.model;

public enum CategoriePizza {

		VIANDE ("Viande"),
		POISSON ("Poisson"),
		SANS_VIANDE ("Sans Viande");
	
		private String name;

		CategoriePizza(String name) {
			this.name = name ;
		}

		@Override
		public String toString() {
			return name;
		}
		
	
	
}
