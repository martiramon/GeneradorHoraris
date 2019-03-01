package FONTS.esaus;

import java.util.Vector;

public class ConjuntPlaEstudis {
	private static ConjuntPlaEstudis CPE;
	private Vector<PlaEstudis> PlansE;
	private Vector<Boolean> Ocupat;
	
	//Creators & Instance
	private ConjuntPlaEstudis(){
		PlansE = new Vector<PlaEstudis>();
		Ocupat = new Vector<Boolean>();
	}
	
	public void reiniciarConjunt() {
		PlansE = new Vector<PlaEstudis>();
		Ocupat = new Vector<Boolean>();
	}
	
	public static ConjuntPlaEstudis getInstance() {
		if (CPE == null) CPE = new ConjuntPlaEstudis();
		return CPE;
	}
	
	//IDs & Objects
	public Integer NewId(PlaEstudis pe){
		Integer i= PlansE.indexOf(pe);
		if (i != -1) return -1;
		PlansE.add(pe);
		Ocupat.add(true);
		return PlansE.size() - 1;
	}
	public Integer GetId(PlaEstudis a){
		for(int i = 0; i < PlansE.size(); ++i) {
			if (Ocupat.get(i) == true && this.getNom(i).equals(a.getNom())) return i;
		}
		return -1;
	}
	public Boolean IsObject(Integer idPE) {
		if (idPE < Ocupat.size() && idPE >= 0){
			return Ocupat.get(idPE);
		}
		return false;
	}
	public PlaEstudis GetObject(Integer idPE){
		if (idPE < Ocupat.size() && idPE >= 0 && Ocupat.get(idPE)){
			return PlansE.get(idPE);
		}
		return null;
	}
	public void DeleteObject(Integer idPE) {
		if (idPE < Ocupat.size() && idPE >= 0){
			Ocupat.set(idPE,false);
		}
	}
	
	//Getters
	public String getNom(Integer idPE) {
		return PlansE.get(idPE).getNom();
	}
	public Vector<Integer> getAssignatures(Integer idPE) {
		return PlansE.get(idPE).getAssignatures();
	}
	
	//Setters
	public void setNom(Integer idPE, String nom) {
		PlansE.get(idPE).setNom(nom);
	}
	public void setAssignatures(Integer idPE,Vector<Integer> assignatures) {
		PlansE.get(idPE).setAssignatures(assignatures);
	}
	
	//Adds & Deletes
	public void addAssignatura(Integer idPE, Integer idA){
		PlansE.get(idPE).addAssignatura(idA);
	}
	public Boolean deleteAssignatura(Integer idPE, Integer idA){
		return PlansE.get(idPE).deleteAssignatura(idA);
	}

	public int size() {
		return PlansE.size();
	}
}
