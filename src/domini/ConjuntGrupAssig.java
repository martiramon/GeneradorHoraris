package domini;

import java.util.Vector;

public class ConjuntGrupAssig {
	private static ConjuntGrupAssig GA;
	private Vector<GrupAssig> GrupAssigs;
	private Vector<Boolean> Ocupat;
	
	//Creators & Instance
	private ConjuntGrupAssig() {
		GrupAssigs = new Vector<GrupAssig>();
		Ocupat = new Vector<Boolean>();
	}
	
	public void reiniciarConjunt() {
		GrupAssigs = new Vector<GrupAssig>();
		Ocupat = new Vector<Boolean>();
	}
	
	public static ConjuntGrupAssig getInstance() {
		if (GA == null) GA = new ConjuntGrupAssig();
		return GA;
	}
	
	//IDs & Objects
	public Integer NewId(GrupAssig ga){
		Integer i= GetId(ga);
		if (i != -1) return -1;
		GrupAssigs.add(ga);
		Ocupat.add(true);
		return GrupAssigs.size() - 1;
	}
	
	public Boolean IsObject(Integer idGA) {
		if (idGA < Ocupat.size() && idGA >= 0){
			return Ocupat.get(idGA);
		}
		return false;
	}
	public Integer GetId(GrupAssig a){
		for(int i = 0; i < GrupAssigs.size(); ++i) {
			if (Ocupat.get(i) == true && this.getGrup(i).equals(a.getGrup()) && this.getAssignatura(i).equals(a.getAssignatura())) return i;
		}
		return -1;
	}
	public GrupAssig GetObject(Integer idGA){
		if (idGA < Ocupat.size() && idGA >= 0 && Ocupat.get(idGA)){
			return GrupAssigs.get(idGA);
		}
		return null;
	}
	public void DeleteObject(Integer idGA) {
		if (idGA < Ocupat.size() && idGA >= 0){
			Ocupat.set(idGA,false);
		}
	}
	
	//Getters
	public Integer getGrup(Integer idGA) {
		return GrupAssigs.get(idGA).getGrup();
	}
	public Integer getAssignatura(Integer idGA) {
		return GrupAssigs.get(idGA).getAssignatura();
	}
	public Vector<Integer> getSessions(Integer idGA) {
		return GrupAssigs.get(idGA).getSessions();
	}
	public Boolean getMati(Integer idGA) {
		return GrupAssigs.get(idGA).getMati();
	}
	
	//Setters
	public void setGrup(Integer idGA,Integer grup) {
		GrupAssigs.get(idGA).setGrup(grup);
	}
	public void setAssignatura(Integer idGA,Integer assignatura) {
		GrupAssigs.get(idGA).setAssignatura(assignatura);
	}
	public void setSessions(Integer idGA,Vector<Integer> sessions) {
		GrupAssigs.get(idGA).setSessions(sessions);
	}
	public void setMati(Integer idGA, Boolean mati) {
		GrupAssigs.get(idGA).setMati(mati);
	}
	
	//Adds & Deletes
	public void AddSessio(Integer idGA, Integer idS){
		GrupAssigs.get(idGA).AddSessio(idS);
	}
	public Boolean DeleteSessio(Integer idGA, Integer idS){
		return GrupAssigs.get(idGA).DeleteSessio(idS);
	}
	
	// Size 
	
	public Integer size() {
		return GrupAssigs.size();
	}
}

