package domini;

public class RestriccioMatiTarda extends RestriccioUnaria{

	public RestriccioMatiTarda(String id, TipusRes tipus) {
		super(id, tipus);
	}
	
	public RestriccioMatiTarda(String id, TipusRes tipus, Boolean activat) {
		super(id, tipus, activat);		
	}

	@Override
	public Boolean comprovar(Sessio s, Integer dia, Integer hora) {
		Integer idG = s.getGrup();
		ConjuntGrupAssig cjGA = ConjuntGrupAssig.getInstance();		
		Boolean grupmati = cjGA.getMati(idG);
		if (grupmati == true && hora >= 0 && hora <= 5) return true;
		if (grupmati == false && hora >= 6 && hora <= 11) return true;
		else return false;
	}
}