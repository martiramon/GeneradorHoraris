package domini;

import java.util.Vector;

public class PlaEstudis {
	String nom;
	Vector<Integer> assignatures;
	
	
	//Creators & Operations
	public PlaEstudis() {
		this.nom = null;
		this.assignatures = null;
	}
	public PlaEstudis(String nom, Vector<Integer> assignatures) {
		this.nom = nom;
		this.assignatures = assignatures;
	}
	public Boolean equal(PlaEstudis pe) {
		return (this.nom.equals(pe.getNom()) && this.assignatures.equals(pe.getAssignatures()));
	}
	
	//Getters
	public String getNom() {
		return this.nom;
	}
	public Vector<Integer> getAssignatures() {
		return this.assignatures;
	}
	
	//Setters
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setAssignatures(Vector<Integer> assignatures) {
		this.assignatures = assignatures;
	}
	
	//Adds & Deletes
	public void addAssignatura(Integer idA){
		if (this.assignatures.indexOf(idA) == -1)
			assignatures.add(idA);
	}
	public Boolean deleteAssignatura(Integer idA){
		return this.assignatures.removeElement(idA);
	}
}
