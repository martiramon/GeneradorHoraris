package FONTS.esaus;

import java.util.Vector;

public class GrupAssig {
	Integer grup;
	Integer assignatura;
	Boolean mati;
	Vector<Integer> sessions;
	

	//Creators & Operations
	public GrupAssig() {
		this.grup = null;
		this.assignatura = null;
		this.mati = null;
		this.sessions = new Vector<Integer>();
	}
	public GrupAssig(Integer grup, Integer assignatura, Boolean mati) {
		this.grup = grup;
		this.assignatura = assignatura;
		this.mati = mati;
		this.sessions = new Vector<Integer>();
	}
	public Boolean equals(GrupAssig ga){
		return (this.grup.equals(ga.getGrup()) && this.assignatura.equals(ga.getAssignatura())
				&& this.sessions.equals(ga.getSessions()) && this.mati.equals(ga.getMati()));
	}
	
	
	//Getters
	public Integer getGrup() {
		return this.grup;
	}
	public Integer getAssignatura() {
		return this.assignatura;
	}
	public Vector<Integer> getSessions() {
		return this.sessions;
	}
	public Boolean getMati() {
		return this.mati;
	}
	
	//Setters
	public void setGrup(Integer grup) {
		this.grup = grup;
	}
	public void setAssignatura(Integer assignatura) {
		this.assignatura = assignatura;
	}
	public void setSessions(Vector<Integer> sessions) {
		this.sessions = sessions;
	}
	public void setMati(Boolean mati) {
		this.mati = mati;
	}
	
	//Adds & Deletes
	public void AddSessio(Integer idS){
		if (this.sessions.indexOf(idS) == -1) {
			this.sessions.add(idS);
		}
	}
	public Boolean DeleteSessio(Integer idS){
		return this.sessions.removeElement(idS);
	}
}
