package domini;

import java.util.Vector;

public class ConjuntTipusAules {
	public static ConjuntTipusAules CTA;
	private Vector<TipusAula> TAules;
	private Vector<Boolean> Ocupat;
	
	//Creators & Instance
	private ConjuntTipusAules() {
		TAules = new Vector<TipusAula>();
		Ocupat = new Vector<Boolean>();
	}
	public static ConjuntTipusAules getInstance() {
		if (CTA == null) CTA = new ConjuntTipusAules();
		return CTA;
	}
	
	
	public void reiniciarConjunt() {
		TAules = new Vector<TipusAula>();
		Ocupat = new Vector<Boolean>();
	}
	
	
	//IDs & Objects
	public Integer NewId(TipusAula ta) {
	    Integer i= TAules.indexOf(ta);
	    if (i != -1) return -1;
		TAules.add(ta);
		Ocupat.add(true);
		return TAules.size() - 1;
	}
	public Integer GetId(TipusAula a){
		for(int i = 0; i < TAules.size(); ++i) {
			if (Ocupat.get(i) == true && this.getId(i).equals(a.getId())) return i;
		}
		return -1;
	}
	public Boolean IsObject(Integer idTA) {
		if (idTA < Ocupat.size() && idTA >= 0){
			return Ocupat.get(idTA);
		}
		return false;
	}
	public TipusAula GetObject(Integer idTA){
		if (idTA < Ocupat.size() && idTA >= 0 && Ocupat.get(idTA)){
			return TAules.get(idTA);
		}
		return null;
	}
	public void DeleteObject(Integer idTA) {
		if (idTA < Ocupat.size() && idTA >= 0){
			Ocupat.set(idTA,false);
		}
	}
	
	
	//Getters
	public String getId(Integer idTA){
		return TAules.get(idTA).getId();
	}
	public void setId(Integer idTA, String id){
		TAules.get(idTA).setId(id);
	}
	public Integer size() {
		return TAules.size();
	}
}
