package FONTS.mramon;

import FONTS.esaus.Sessio;

public abstract class RestriccioGlobal extends Restriccio {
	
	//Constructores
	
	public RestriccioGlobal(String id, TipusRes tipus) {
		super(id,tipus);		
	}
	
	public RestriccioGlobal(String id, TipusRes tipus, Boolean activat) {
		super(id, tipus, activat);		
	}
	
	//Mètodes
	
	public abstract Boolean comprovar(Sessio s, Sessio[][][] horari, Integer dia, Integer hora);
}
