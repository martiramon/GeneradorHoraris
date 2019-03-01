package FONTS.mramon;

import java.util.Vector;

import FONTS.esaus.ConjuntAssignatura;
import FONTS.esaus.ConjuntGrupAssig;
import FONTS.esaus.Sessio;

public class RestriccioCorequisit extends RestriccioBinaria{

	public RestriccioCorequisit(String id, TipusRes tipus) {
		super(id, tipus);
	}
	
	public RestriccioCorequisit(String id, TipusRes tipus, Boolean activat) {
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
		Vector<Integer> AsCorequisit = cjA.getCorequisit(idA);
		for (int i=0; i< (sessions.length); i++ ) {
			if (sessions[i] != null) {
				Integer idGA2 = sessions[i].getGrup();
				Integer idA2 = cjGA.getAssignatura(idGA2);
				Integer idG2 = cjGA.getGrup(idGA2);
				if (AsCorequisit != null) {
					for (int j=0; j< AsCorequisit.size(); j++) {
						if (idG == idG2 && AsCorequisit.get(j) == idA2) correct = false;
					}
				}
			}
		}
		return correct;		
	}
}