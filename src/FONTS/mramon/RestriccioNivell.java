package FONTS.mramon;

import FONTS.esaus.ConjuntAssignatura;
import FONTS.esaus.ConjuntGrupAssig;
import FONTS.esaus.Sessio;

public class RestriccioNivell extends RestriccioBinaria{

	public RestriccioNivell(String id, TipusRes tipus) {
		super(id, tipus);
	}
	
	public RestriccioNivell(String id, TipusRes tipus, Boolean activat) {
		super(id, tipus, activat);		
	}

	@Override
	public Boolean comprovar(Sessio s, Sessio[] sessions, Integer dia, Integer hora) {
		Boolean correct = true;
		Integer idGA = s.getGrup();
		ConjuntGrupAssig cjGA = ConjuntGrupAssig.getInstance();
		Integer idA = cjGA.getAssignatura(idGA);
		Integer idG = cjGA.getGrup(idGA);
		ConjuntAssignatura cjA = ConjuntAssignatura.getInstance();
		Integer NivA = cjA.getNivell(idA);
		for (int i=0; i< (sessions.length); i++ ) {
			if (sessions[i] != null) {
				Integer idGA2 = sessions[i].getGrup();
				Integer idA2 = cjGA.getAssignatura(idGA2);
				Integer idG2 = cjGA.getGrup(idGA2);
				Integer NivA2 = cjA.getNivell(idA2);
				if (NivA == NivA2 && idG == idG2 && idA2 != idA) correct = false;			
			}
		}
		return correct;		
	}
}
