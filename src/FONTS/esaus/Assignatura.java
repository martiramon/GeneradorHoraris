package FONTS.esaus;

import java.util.Vector;

public class Assignatura {	
	String acronim;
	String nom;
	Integer nivell;
	Vector<Integer> sessions;
	Vector<Integer> grups;
	Vector<Integer> tsessions;
	Vector<Integer> corequisit;
	
	//Creators & Operations
	public Assignatura() {
		this.acronim = null;
		this.nom = null;
		this.nivell  = null;
		this.tsessions = new Vector<Integer>();
		this.sessions = new Vector<Integer>();
		this.grups = new Vector<Integer>();
		this.corequisit = new Vector<Integer>();
	}
	public Assignatura(String acronim, String nom, Integer nivell, Vector<Integer> tsessions) {
		this.acronim = acronim;
		this.nom = nom;
		this.nivell  = nivell;
		this.tsessions = tsessions;
		this.sessions = new Vector<Integer>();
		this.grups = new Vector<Integer>();
		this.corequisit = new Vector<Integer>();
	}
	
	public Assignatura(String acronim, String nom, Integer nivell, Vector<Integer> tsessions, Vector<Integer> sessions) {
		this.acronim = acronim;
		this.nom = nom;
		this.nivell  = nivell;
		this.sessions = sessions;
		this.tsessions = tsessions;
		this.grups = new Vector<Integer>();
		this.corequisit = new Vector<Integer>();
	}
	
	public Assignatura(String acronim, String nom, Integer nivell, Vector<Integer> tsessions, Vector<Integer> sessions, Vector<Integer> grups) {
		this.acronim = acronim;
		this.nom = nom;
		this.nivell  = nivell;
		this.sessions = sessions;
		this.tsessions = tsessions;
		this.grups = grups;
		this.corequisit = new Vector<Integer>();
	}	
	
	public Assignatura(String acronim, String nom, Integer nivell, Vector<Integer> tsessions, Vector<Integer> sessions, Vector<Integer> grups, Vector<Integer> coreq) {
		this.acronim = acronim;
		this.nom = nom;
		this.nivell  = nivell;
		this.sessions = sessions;
		this.tsessions = tsessions;
		this.grups = grups;
		this.corequisit = coreq;
	}	
	
	public Boolean equals(Assignatura a){
		return (this.acronim.equals(a.getAcronim()) && this.nom.equals(a.getNom()) && this.nivell.equals(a.getNivell())
				&& this.getSessions().equals(a.getSessions()) && this.getGrups().equals(a.getGrups())  && this.getTipusSessions().equals(a.getTipusSessions()) 
				&& this.getCorequisit().equals(a.getCorequisit()));
	}
	
	//Getters
	public String getAcronim() {
		return this.acronim;
	}
	public String getNom() {
		return this.nom;
	}
	public Integer getNivell() {
		return this.nivell;
	}
	public Vector<Integer> getSessions() {
		return this.sessions;
	}
	public Vector<Integer> getGrups() {
		return this.grups;
	}
	public Vector<Integer> getTipusSessions(){
		return this.tsessions;
	}
	public Vector<Integer> getCorequisit() {
		return this.corequisit;
	}
	
	//Setters
	public void setAcronim(String acronim) {
		this.acronim = acronim;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setNivell(Integer nivell) {
		this.nivell = nivell;
	}
	public void setSessions(Vector<Integer> sessions) {
		this.sessions = sessions;
	}
	public void setGrups(Vector<Integer> grups) {
		this.grups = grups;
	}
	public void setTipusSessions(Vector<Integer> tsessions) {
		this.tsessions = tsessions;
	}
	public void setCorequisit(Vector<Integer> corequisit) {
		 this.corequisit = corequisit;
	}
	
	//Adds & Deletes
	public void AddGrup(Integer idG) {
		if(this.grups.indexOf(idG) == -1)
		this.grups.add(idG);
	}
	public void AddSessio(Integer idS) {
		if (this.sessions.indexOf(idS) == -1)
			this.sessions.add(idS);
	}
	public void AddTipusSessio(Integer idTS) {
		if (this.sessions.indexOf(idTS) == -1)
			this.sessions.add(idTS);
	}
	public void AddCorequisit(Integer idA) {
		if (this.corequisit.indexOf(idA) == -1)
			this.corequisit.add(idA);
	}
	public Boolean DeleteSessio(Integer idS){
		return this.sessions.removeElement(idS);
	}
	public Boolean DeleteGrup(Integer idG){
		return this.grups.removeElement(idG);
	}
	public Boolean DeleteTipusSessio(Integer idTS) {
		return this.tsessions.removeElement(idTS);
	}
	public Boolean DeleteCorequisit(Integer idA) {
		return this.corequisit.removeElement(idA);
	}
}
