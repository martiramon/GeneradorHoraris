
package FONTS.mramon;

import FONTS.esaus.ConjuntAssignatura;
import FONTS.esaus.ConjuntGrupAssig;
import FONTS.esaus.ConjuntTipusSessio;
import FONTS.esaus.Sessio;
import java.util.Vector;



public class RestriccioLaboratoriPostTeoria extends RestriccioGlobal{
	
	public RestriccioLaboratoriPostTeoria(String id, TipusRes tipus) {
		super(id, tipus);
	}
	
	public RestriccioLaboratoriPostTeoria(String id, TipusRes tipus, Boolean activat) {
		super(id, tipus, activat);		
	}

	@Override
	public Boolean comprovar(Sessio s, Sessio[][][] horari, Integer dia, Integer hora) {
		Integer tsessio = s.getTipusSessio();
		ConjuntTipusSessio cjTS = ConjuntTipusSessio.getInstance();
		Boolean correct = true;
		String tipusS = cjTS.getId(tsessio);
		
		if ((tipusS.toUpperCase().equals("TEO")) || (tipusS.toUpperCase().equals("T")) || (tipusS.toUpperCase().equals("TEORIA"))) return true;
		else {
			ConjuntGrupAssig cjGA = ConjuntGrupAssig.getInstance();
			Integer idA = cjGA.getAssignatura(s.getGrup());
			
			ConjuntAssignatura cjA = ConjuntAssignatura.getInstance();
			Vector<Integer> vtsess = cjA.getTipusSessio(idA);
			
			
			for (Integer i : vtsess) {
				if (cjTS.getId(i).toUpperCase().equals("TEO") || cjTS.getId(i).toUpperCase().equals("T") || cjTS.getId(i).toUpperCase().equals("TEORIA")) correct = false; 
			}			
			if (!correct) {
				for (int i=0; i < dia+1 ; i++ ) {
					for (int j=0; dia == i &&  j < hora+1 || i != dia && j < horari[i].length; j++) {
						for (int k=0; k < horari[i][j].length; k++) {
							if (horari[i][j][k] != null) {
								if (i != dia || (i == dia && j <= hora)) {
									Sessio aux = horari[i][j][k];
									if (aux.getGrup() == s.getGrup()) {
										if (cjTS.getId(aux.getTipusSessio()).equals("TEO") || cjTS.getId(aux.getTipusSessio()).equals("TEORIA") || cjTS.getId(aux.getTipusSessio()).equals("T")) correct = true;
									}
								}
							}
						}
					}
				}
			}
		}
		return correct;
	}
	
}
