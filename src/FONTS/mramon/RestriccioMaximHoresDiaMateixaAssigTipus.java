package FONTS.mramon; 


import FONTS.esaus.Sessio;

public class RestriccioMaximHoresDiaMateixaAssigTipus extends RestriccioGlobal{
	
	public RestriccioMaximHoresDiaMateixaAssigTipus(String id, TipusRes tipus) {
		super(id, tipus);
	}
	
	public RestriccioMaximHoresDiaMateixaAssigTipus(String id, TipusRes tipus, Boolean activat) {
		super(id, tipus, activat);		
	}

	@Override
	public Boolean comprovar(Sessio s, Sessio[][][] horari, Integer dia, Integer hora) {
		Integer idGA = s.getGrup();
		Integer idTS = s.getTipusSessio();
		Integer numTS = 0;
		for (int j=0; j < hora+1; j++) {
			for (int k=0; k < horari[dia][j].length; k++) {
				if (horari[dia][j][k] != null) {
					if (j <= hora) {
						Sessio aux = horari[dia][j][k];
						if (aux.getGrup() == idGA && aux.getTipusSessio() == idTS) numTS++;
					}
				}
			}
		}
		if (numTS > 1) return false;
		else return true;
	}
}
