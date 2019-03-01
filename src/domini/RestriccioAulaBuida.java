package domini;

public class RestriccioAulaBuida extends RestriccioBinaria{

	public RestriccioAulaBuida(String id, TipusRes tipus) {
		super(id, tipus);
	}
	
	public RestriccioAulaBuida(String id, TipusRes tipus, Boolean activat) {
		super(id, tipus, activat);		
	}

	@Override
	public Boolean comprovar(Sessio s, Sessio[] sessions, Integer dia, Integer hora) {
		Integer idA = s.getAula();
		Integer idGA = s.getGrup();
			for (int i=0; i< (sessions.length); i++ ) {
				if (sessions[i] != null) {
					Integer idAA = sessions[i].getAula();
					Integer idGAA = sessions[i].getGrup();
					if (idA == idAA && idGA != idGAA) return false;
				}
		}
		return true;
	}
}