package domini;

import java.util.Vector;

public class TipusSessio {
	private String id;
	private Integer tipusA;
	private Integer hores;
	private Vector<Integer> assignatures;
	
	//Creators & Operations
	public TipusSessio() {
		this.id = null;
		this.tipusA = null;
		this.hores = null;
		this.assignatures = new Vector<Integer>();
	}
	public TipusSessio(String id, Integer tipus, Integer hores) {
		this.id = id;
		this.tipusA = tipus;
		this.hores = hores;
		this.assignatures = new Vector<Integer>();
	}
	public Boolean equals(TipusSessio ts){
		return (this.id.equals(ts.getId()) && this.tipusA.equals(ts.getTipus()) && this.hores.equals(ts.getHores())
				&& this.assignatures.equals(ts.getAssignatures()));
	}
	
	//Getters
	public String getId() {
		return this.id;
	}
	public Integer getTipus() {
		return this.tipusA;
	}
	public Integer getHores() {
		return this.hores;
	}
	public Vector<Integer> getAssignatures(){
		return this.assignatures;
	}
	
	//Setters
	public void setId(String id) {
		this.id = id;
	}
	public void setTipus(Integer tipus) {
		this.tipusA = tipus;
	}
	public void setHores(Integer hores) {
		this.hores = hores;
	}
	public void setAssignatures(Vector<Integer> assignatures) {
		this.assignatures = assignatures;
	}
	
	//Adds & Deletes
	public void AddAssignatura(int idA) {
		if (this.assignatures.indexOf(idA) == -1)
			this.assignatures.add(idA);
	}
	public Boolean DeleteAssignatura(int idA) {
		return this.assignatures.removeElement(idA);
	}
}
