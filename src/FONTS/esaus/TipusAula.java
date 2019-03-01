package FONTS.esaus;

import java.util.Vector;

public class TipusAula {
	String id;
	Vector<Integer> aules;
	//Vector<TipusSessio> tipussessions;
	
	
	//Creators & Operations
	public TipusAula() {
		this.id = null;
		this.aules = new Vector<Integer>();
	}
	public TipusAula(String id) {
		this.id = id;
		this.aules = new Vector<Integer>();
	}
	
	public Boolean equals(TipusAula ta) {
		return (this.id.equals(ta.getId()) && this.aules.equals(ta.getAules()));
	}

	//Getters
	public String getId() {
		return this.id;
	}
	public Vector<Integer> getAules() {
		return this.aules;
	}
	
	//Setters
	public void setId(String id) {
		this.id = id;
	}
	public void setAules(Vector<Integer> aules) {
		this.aules = aules;
	}
	
	//Adds & Deletes	
	
	
	public void AddAula(Integer idA){
		if (this.aules.indexOf(idA) == -1){
			this.aules.add(idA);
		}
	}
	public Boolean DeleteAula(Integer idA){
		return this.aules.removeElement(idA);
	}
}
