package domini;

import java.util.Vector;

public class RestriccioTipusAulaDisponible extends RestriccioUnaria{

	public RestriccioTipusAulaDisponible(String id, TipusRes tipus) {
		super(id, tipus);
	}
	
	public RestriccioTipusAulaDisponible(String id, TipusRes tipus, Boolean activat) {
		super(id, tipus, activat);		
	}

	@Override
	public Boolean comprovar(Sessio s, Integer dia, Integer hora) {
		Integer idG = s.getGrup();
		ConjuntGrupAssig cjGA = ConjuntGrupAssig.getInstance();		
		Integer idgrup = cjGA.getGrup(idG);
		ConjuntGrup cjG = ConjuntGrup.getInstance();		
		Integer tam = cjG.getCapacitat(idgrup);
		
		Integer idTS = s.getTipusSessio();
		ConjuntTipusSessio cjTS = ConjuntTipusSessio.getInstance();
		Integer idTA = cjTS.getTipus(idTS);
		
		Vector<Integer> AulesDeTipus = new Vector<Integer>();
		AulesDeTipus = ConjuntAula.findTipusAula(idTA);
		Vector<Integer> AulesDeCapacitat = new Vector<Integer>(); 
		AulesDeCapacitat = ConjuntAula.findCapacitat(tam);
		Integer sizeAuTip = AulesDeTipus.size(); 
		if (sizeAuTip == 0) return false;
		Integer sizeAuCap = AulesDeCapacitat.size();
		if (sizeAuCap == 0) return false;
		for (int i=0; i< sizeAuTip; i++) {
			for (int j=0; j <sizeAuCap; j++) {
				if (AulesDeTipus.get(i) == AulesDeCapacitat.get(j)) return true;
			}
		}
		return false;
	}
}
