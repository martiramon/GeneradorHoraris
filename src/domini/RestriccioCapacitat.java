package domini;

public class RestriccioCapacitat extends RestriccioUnaria{

	public RestriccioCapacitat(String id, TipusRes tipus) {
		super(id, tipus);
	}
	
	public RestriccioCapacitat(String id, TipusRes tipus, Boolean activat) {
		super(id, tipus, activat);		
	}

	@Override
	public Boolean comprovar(Sessio s, Integer dia, Integer hora) {
		Integer idA = s.getAula();
		Integer idG = s.getGrup();
		ConjuntAula cjA = ConjuntAula.getInstance();
		Integer capac = cjA.getCapacitat(idA);
		ConjuntGrupAssig cjGA = ConjuntGrupAssig.getInstance();		
		Integer idgrup = cjGA.getGrup(idG);
		ConjuntGrup cjG = ConjuntGrup.getInstance();		
		Integer tam = cjG.getCapacitat(idgrup);
		if (capac >= tam) return true;
		else return false;
	}
}
