package domini;

import java.util.Vector;

public class Aula {
	String id;
	Integer capacitat;
	Integer tipusA;
	Vector<Integer> sessions;
	
	public Aula(){
		this.id = null;
		this.capacitat =null;
		this.tipusA = null;
		sessions = new Vector<Integer>();
	}
	//Creators & Operations
	public Aula(String id, Integer capacitat) {
		this.id = id;
		this.capacitat = capacitat;
		this.tipusA = null;
		this.sessions = new Vector<Integer>();
	}
	public Aula(String id, Integer capacitat, Integer tipusA) {
		this.id = id;
		this.capacitat = capacitat;
		this.tipusA = tipusA;
		sessions = new Vector<Integer>();
	} 
	public Boolean equals(Aula a){
		return (this.id.equals(a.getId()) && this.capacitat.equals(a.getCapacitat())  && 
			this.tipusA.equals(a.getTipus()) && this.sessions.equals(a.getSessions()));
	}
	
	//Getters
	public String getId() {
		return id;
	} 
	public Integer getCapacitat() {
		return capacitat;
	}
	public Integer getTipus() {
		return tipusA;
	}
	public Vector<Integer> getSessions() {
		return this.sessions;
	}
	
	
	//Setters
	public void setId(String id) {
		this.id = id;
	}
	public void setCapacitat(Integer capacitat) {
		this.capacitat = capacitat;
	}
	public void setTipus(Integer tipusA) {
		this.tipusA = tipusA;
	}
	public void setSessions(Vector<Integer> S) {
		this.sessions = S;
	}
	
	//Adds & Deletes
	public void AddSessio(Integer idS){
		if (this.sessions.indexOf(idS) == -1)
		this.sessions.add(idS);
	}
	public Boolean DeleteSessio(Integer idS){
		return this.sessions.removeElement(idS);
	}
	
}


