package domini;

import java.util.Vector;

public class ConjuntAula {
	private static ConjuntAula A;
	private static Vector<Aula> Aules;
	private static Vector<Boolean> Ocupat;
	
	//Creator & Instances
	private ConjuntAula(){
		Aules = new Vector<Aula>();
		Ocupat = new Vector<Boolean>();
	}
	
	public static ConjuntAula getInstance() {
		if (A == null) A = new ConjuntAula();
		return A;
	}
	
	//IDs & Objects
	public  Integer NewId(Aula a) {
	    Integer i= GetId(a);
	    if (i != -1) return -1;
		Aules.add(a);
		Ocupat.add(true);
		return Aules.size() - 1;
	}
	public Integer GetId(Aula a){
		for(int i = 0; i < Aules.size(); ++i) {
			if (Ocupat.get(i) == true && this.getId(i).equals(a.getId())) return i;
		}
		return -1;
	}
	public  Boolean IsObject(Integer idA) {
		if (idA < Ocupat.size() && idA >= 0){
			return Ocupat.get(idA);
		}
		return false;
	}
	public Aula GetObject(Integer idA){
		if (idA < Ocupat.size() && idA >= 0 && Ocupat.get(idA)){
			return Aules.get(idA);
		}
		else return null;
	}
	public void DeleteObject(Integer idA) {
		if (idA < Ocupat.size() && idA >= 0){
			Ocupat.set(idA,false);
		}
	}
	
	//Getters
	public String getId(Integer idA) {
		return Aules.get(idA).getId();
	}
	public Integer getCapacitat(Integer idA) {
		return Aules.get(idA).getCapacitat();
	}
	public Integer getTipus(Integer idA) {
		return Aules.get(idA).getTipus();
	}
	public Vector<Integer> getSessions(int idA){
		return Aules.get(idA).getSessions();
	}
	
	//Setters
	public void setId(Integer idA,String id) {
		Aules.get(idA).setId(id);
	}
	public void setCapacitat(Integer idA,Integer capacitat) {
		Aules.get(idA).setCapacitat(capacitat);
	}
	public void setTipus(Integer idA,Integer tipus) {
		Aules.get(idA).setTipus(tipus);
	}
	public void setSessions(Integer idA,Vector<Integer> S) {
		Aules.get(idA).setSessions(S);
	}
	
	//Adds & Deletes
	public void AddSessio(Integer idA, Integer idS){
		Aules.get(idA).AddSessio(idS);
	}
	public Boolean DeleteSessio(Integer idA, Integer idS){
		return Aules.get(idA).DeleteSessio(idS);
	}
	
	//Operations
	public static Vector<Integer> findTipusAula(Integer idTA){
		Vector<Integer> aules = new Vector<Integer>();
		for(int i = 0; i < Aules.size(); ++i) {
			if(Aules.get(i).getTipus() == idTA && Ocupat.get(i)) aules.add(i);
		}
		return aules;
	}
	public static Vector<Integer> findCapacitat(Integer cap){
		Vector<Integer> aules = new Vector<Integer>();
		for (int i = 0; i < Aules.size(); ++i) {
			if(Aules.get(i).getCapacitat() >= cap && Ocupat.get(i)) aules.add(i);
		}
		return aules;
	}
	public Integer size() {
		return Aules.size();
	}
	
	public void reiniciarConjunt() {
		Aules = new Vector<Aula>();
		Ocupat = new Vector<Boolean>();
	}
	
}


