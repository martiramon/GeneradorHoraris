package FONTS.mramon;

import FONTS.esaus.Sessio;

public abstract class RestriccioBinaria extends Restriccio {	
	
	//Constructores
	
	public RestriccioBinaria(String id, TipusRes tipus) {
		super(id, tipus);		
	}
	
	public RestriccioBinaria(String id, TipusRes tipus, Boolean activat) {
		super(id, tipus, activat);		
	}
	
	//Mètodes
	
	public abstract Boolean comprovar(Sessio s, Sessio[] sessions, Integer dia, Integer hora);
}
