package FONTS.mramon; 


import FONTS.esaus.Sessio;

public class RestriccioMaximSessionsAssigDia extends RestriccioGlobal{
	
	public RestriccioMaximSessionsAssigDia(String id, TipusRes tipus) {
		super(id, tipus);
	}
	
	public RestriccioMaximSessionsAssigDia(String id, TipusRes tipus, Boolean activat) {
		super(id, tipus, activat);		
	}

	@Override
	public Boolean comprovar(Sessio s, Sessio[][][] horari, Integer dia, Integer hora) {
		Integer idGA = s.getGrup();
		Integer numGA = 0;
		for (int j=0; j < hora+1; j++) {
			for (int k=0; k < horari[dia][j].length; k++) {
				if (horari[dia][j][k] != null) {
					if (j <= hora) {
						Sessio aux = horari[dia][j][k];
						if (aux.getGrup() == idGA ) numGA++;
					}
				}
			}
		}
		if (numGA > 3) return false;
		else return true;
	}
}
