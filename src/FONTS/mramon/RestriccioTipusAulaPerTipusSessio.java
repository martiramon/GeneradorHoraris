package FONTS.mramon;

import FONTS.esaus.ConjuntAula;
import FONTS.esaus.ConjuntTipusSessio;
import FONTS.esaus.Sessio;

public class RestriccioTipusAulaPerTipusSessio extends RestriccioUnaria{

	public RestriccioTipusAulaPerTipusSessio(String id, TipusRes tipus) {
		super(id, tipus);
	}
	
	public RestriccioTipusAulaPerTipusSessio(String id, TipusRes tipus, Boolean activat) {
		super(id, tipus, activat);		
	}

	@Override
	public Boolean comprovar(Sessio s, Integer dia, Integer hora) {		
		Integer idTS = s.getTipusSessio();
		ConjuntTipusSessio cjTS = ConjuntTipusSessio.getInstance();
		Integer idTA = cjTS.getTipus(idTS);		
		Integer idA = s.getAula();		
		ConjuntAula cjA = ConjuntAula.getInstance();
		Integer idcjA = cjA.getTipus(idA);
		if (idcjA == idTA) return true;
		else return false;		
	}
}