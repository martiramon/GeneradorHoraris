package FONTS.mramon;

import FONTS.agallofre.HoraImpossible;
import FONTS.esaus.Sessio;

public class RestriccioHoresImpossibles extends RestriccioUnaria{

	public RestriccioHoresImpossibles(String id, TipusRes tipus) {
		super(id, tipus);
	}
	
	public RestriccioHoresImpossibles(String id, TipusRes tipus, Boolean activat) {
		super(id, tipus, activat);		
	}

	@Override
	public Boolean comprovar(Sessio s, Integer dia, Integer hora) {
		HoraImpossible hi = HoraImpossible.getHoraImpossible();
		Boolean esHoraImpossible = hi.esHoraImpossible(dia, hora);
		return !esHoraImpossible;
	}
}
