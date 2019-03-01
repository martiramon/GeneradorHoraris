package FONTS.mramon;

import FONTS.esaus.Sessio;

public abstract class RestriccioUnaria extends Restriccio {
	
	//Constructores
	
	public RestriccioUnaria(String id, TipusRes tipus) {
		super(id, tipus);		
	}
	
	public RestriccioUnaria(String id, TipusRes tipus, Boolean activat) {
		super(id, tipus, activat);		
	}
	
	//Mètodes
	
	public abstract Boolean comprovar(Sessio s, Integer dia, Integer hora);
}
