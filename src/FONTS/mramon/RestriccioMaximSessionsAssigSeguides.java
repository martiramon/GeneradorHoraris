package FONTS.mramon;

import FONTS.esaus.Sessio;

public class RestriccioMaximSessionsAssigSeguides extends RestriccioGlobal{
	
	public RestriccioMaximSessionsAssigSeguides(String id, TipusRes tipus) {
		super(id, tipus);
	}
	
	public RestriccioMaximSessionsAssigSeguides(String id, TipusRes tipus, Boolean activat) {
		super(id, tipus, activat);		
	}

	@Override
	public Boolean comprovar(Sessio s, Sessio[][][] horari, Integer dia, Integer hora) {
		Boolean correct = true;
		Boolean AsHoraAbans = false;
		Boolean As2HoraAbans = false;
		Integer idGA = s.getGrup();
		if (hora>=2) {
			for (int i=0; i< (horari[dia][hora-1].length); i++ ) {
				if (null != horari[dia][hora-1][i]) {
					if (horari[dia][hora-1][i].getGrup() == idGA) AsHoraAbans = true;
				}
			}
			for (int i=0; i< (horari[dia][hora-2].length); i++ ) {
				if (null != horari[dia][hora-2][i]) { 
					if (horari[dia][hora-2][i].getGrup() == idGA) As2HoraAbans = true;
				}
			}
			if (AsHoraAbans && As2HoraAbans) correct = false;
		}
		return correct;		
	}
}
