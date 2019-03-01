package FONTS.esaus;

import java.util.Vector;

public class ConjuntTipusSessio {
	private static ConjuntTipusSessio CTS;
	private Vector<TipusSessio> TSessions;
	private Vector<Boolean> Ocupat;
	
	//Creators & Instance
	private ConjuntTipusSessio(){
		TSessions = new Vector<TipusSessio>();
		Ocupat = new Vector<Boolean>();
	}
	public static ConjuntTipusSessio getInstance() {
		if (CTS == null) CTS = new ConjuntTipusSessio();
		return CTS;
	}
	
	public void reiniciarConjunt() {
		TSessions = new Vector<TipusSessio>();
		Ocupat = new Vector<Boolean>();
	}
	
	//IDs & Objects
	public Integer NewId(TipusSessio ts){
		Integer i= TSessions.indexOf(ts);
		if (i != -1) return -1;
		TSessions.add(ts);
		Ocupat.add(true);
		return TSessions.size() - 1;
	}
	public Integer GetId(TipusSessio a){
		for(int i = 0; i < TSessions.size(); ++i) {
			if (Ocupat.get(i) == true && this.getId(i).equals(a.getId())) return i;
		}
		return -1;
	}
	public Boolean IsObject(Integer idTS) {
		if (idTS < Ocupat.size() && idTS >= 0){
			return Ocupat.get(idTS);
		}
		return false;
	}
	public TipusSessio GetObject(Integer idTS){
		if (idTS < Ocupat.size() && idTS >= 0 && Ocupat.get(idTS)){
			return TSessions.get(idTS);
		}
		return null;
	}
	public void DeleteObject(Integer idTS) {
		if (idTS < Ocupat.size() && idTS >= 0){
			Ocupat.set(idTS,false);
		}
	}
	
	//Getters
	public String getId(Integer idTS) {
		return TSessions.get(idTS).getId();
	}
	public Integer getTipus(Integer idTS) {
		return TSessions.get(idTS).getTipus();
	}
	public Integer getHores(Integer idTS) {
		return TSessions.get(idTS).getTipus();
	}
	public Vector<Integer> getAssignatures(Integer idTS) {
		return TSessions.get(idTS).getAssignatures();
	}
	
	//Setters
	public void setId(Integer idTS,String id) {
		TSessions.get(idTS).setId(id);
	}
	public void setTipus(Integer idTS,Integer tipus) {
		TSessions.get(idTS).setTipus(tipus);
	}
	public void setHores(Integer idTS, Integer hores) {
		TSessions.get(idTS).setHores(hores);
	}
	public void setAssignatures(Integer idTS, Vector<Integer> assignatures) {
		TSessions.get(idTS).setAssignatures(assignatures);
	}
	
	//Adds & Deletes
	public void AddAssignatura(Integer idTS, Integer idA) {
		TSessions.get(idTS).AddAssignatura(idA);
	}
	public Boolean DeleteAssignatura(Integer idTS, Integer idA) {
		return TSessions.get(idTS).DeleteAssignatura(idA);
	}
	public Integer size() {
		return TSessions.size();
	}
}
