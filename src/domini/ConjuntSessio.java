package domini;

import java.util.Vector;

public class ConjuntSessio {
	private static ConjuntSessio CS;
	private Vector<Sessio> Sessions;
	private Vector<Boolean> Ocupat;
	
	//Creators & Instance
	private ConjuntSessio(){
		Sessions = new Vector<Sessio>();
		Ocupat = new Vector<Boolean>();
	}
	
	public void reiniciarConjunt() {
		Sessions = new Vector<Sessio>();
		Ocupat = new Vector<Boolean>();
	}
	
	public static ConjuntSessio getInstance() {
		if (CS == null) CS = new ConjuntSessio();
		return CS;
	}
	
	//IDs & Objects
	public Integer NewId(Sessio s){
		Integer i= Sessions.indexOf(s);
		if (i != -1) return -1;
		Sessions.add(s);
		Ocupat.add(true);
		return Sessions.size() - 1;
	}
	public Integer GetId(Sessio a){
		for(int i = 0; i < Sessions.size(); ++i) {
			if (Ocupat.get(i) == true && this.getId(i).equals(a.getId())) return i;
		}
		return -1;
	}
	public Boolean IsObject(Integer idS) {
		if (idS < Ocupat.size() && idS >= 0){
			return Ocupat.get(idS);
		}
		return false;
	}
	public Sessio GetObject(Integer idS){
		if (idS < Ocupat.size() && idS >= 0 && Ocupat.get(idS)){
			return Sessions.get(idS);
		}
		return null;
	}
	public void DeleteObject(Integer idS) {
		if (idS < Ocupat.size() && idS >= 0){
			Ocupat.set(idS,false);
		}
	}
	
	//Getters
	public String getId(Integer idS) {
		return Sessions.get(idS).getId();
	}
	public Integer getAula(Integer idS) {
		return Sessions.get(idS).getAula();
	}
	public Integer getGrup(Integer idS) {
		return Sessions.get(idS).getGrup();
	}
	public Integer getTipusSessio(Integer idS) {
		return Sessions.get(idS).getTipusSessio();
	}
	

	
	//Setters
	public void setId(Integer idS, String id) {
		Sessions.get(idS).setId(id);
	}
	public void setAula(Integer idS, Integer aula) {
		Sessions.get(idS).setAula(aula);
	}
	public void setGrup(Integer idS, Integer grup) {
		Sessions.get(idS).setGrup(grup);;
	}
	public void setTipusSessio(Integer idS, Integer tsessio) {
		Sessions.get(idS).setTipusSessio(tsessio);
	}

	public int size() {
		return Sessions.size();
	}
	
	
}
