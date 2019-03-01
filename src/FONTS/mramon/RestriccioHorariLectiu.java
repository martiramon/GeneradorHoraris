package FONTS.mramon;

import FONTS.esaus.Sessio;

public class RestriccioHorariLectiu extends RestriccioUnaria{

	public RestriccioHorariLectiu(String id, TipusRes tipus) {
		super(id, tipus);
	}
	
	public RestriccioHorariLectiu(String id, TipusRes tipus, Boolean activat) {
		super(id, tipus, activat);		
	}

	@Override
	public Boolean comprovar(Sessio s, Integer dia, Integer hora) {
		if ((dia >= 0 && dia <=4) && (hora >=0 && hora <=11)) return true;
		else return false;
	}
}
