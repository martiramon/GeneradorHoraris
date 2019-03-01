package domini;

import java.util.Vector;

public class ConjuntAssignatura {
	private static ConjuntAssignatura CA;
	private  Vector<Assignatura> Assignatures;
	private  Vector<Boolean> Ocupat;
	
	
	//Creator & Instances
	
	private ConjuntAssignatura(){
		Assignatures = new Vector<Assignatura>();
		Ocupat = new Vector<Boolean>();
	}
	public static ConjuntAssignatura getInstance() {
		if (CA == null) CA = new ConjuntAssignatura();
		return CA;
	}
	
	public void reiniciarConjunt() {
		Assignatures = new Vector<Assignatura>();
		Ocupat = new Vector<Boolean>();
	}
	
	
	//IDs & Objects
	
	public Integer NewId(Assignatura a){
		Integer i= GetId(a);
		if (i != -1) return i;
		Assignatures.add(a);
		Ocupat.add(true);
		return Assignatures.size() - 1;
	}
	public Integer GetId(Assignatura a){
		for(int i = 0; i < Assignatures.size(); ++i) {
			if (Ocupat.get(i) == true && this.getNom(i).equals(a.getNom())) return i;
		}
		return -1;
	}
	public Boolean IsObject(Integer idA) {
		if (idA < Ocupat.size() && idA >= 0){
			return Ocupat.get(idA);
		}
		return false;
	}
	public Assignatura GetObject(Integer idA){
		if (idA < Ocupat.size() && idA >= 0 && Ocupat.get(idA)){
			return Assignatures.get(idA);
		}
		return null;
	}
	public void DeleteObject(Integer idA) {
		if (idA < Ocupat.size() && idA >= 0){
			Ocupat.set(idA,false);
		}
	}
	
	//Getters
	
	public String getAcronim(Integer idA) {
		return Assignatures.get(idA).getAcronim();
	}
	public String getNom(Integer idA) {
		return Assignatures.get(idA).getNom();
	}
	public Integer getNivell(Integer idA) {
		return Assignatures.get(idA).getNivell();
	}
	public Vector<Integer> getSessions(Integer idA){
		return Assignatures.get(idA).getSessions();
	}
	public Vector<Integer> getGrups(Integer idA) {
		return Assignatures.get(idA).getGrups();
	}
	public Vector<Integer> getTipusSessio(Integer idA){
		return Assignatures.get(idA).getTipusSessions();
	}
	public Vector<Integer> getCorequisit(Integer idA) {
		return Assignatures.get(idA).getCorequisit();
	}
	//Setters
	public void setAcronim(Integer idA,String acronim) {
		Assignatures.get(idA).setAcronim(acronim);
	}
	public void setNivell(Integer idA, Integer nivell) {
		Assignatures.get(idA).setNivell(nivell);
	}
	public void setNom(Integer idA,String nom) {
		Assignatures.get(idA).setNom(nom);
	}
	public void setSessions(Integer idA,Vector<Integer> sessions) {
		Assignatures.get(idA).setSessions(sessions);
	}
	public void setGrups(Integer idA,Vector<Integer> grups) {
		Assignatures.get(idA).setGrups(grups);
	}
	public void setTipusSessio(Integer idA, Vector<Integer> tipussesions) {
		Assignatures.get(idA).setTipusSessions(tipussesions);
	}
	public void setCorequisit(Integer idA, Vector<Integer> corequisit) {
		Assignatures.get(idA).setTipusSessions(corequisit);
	}
	
	//Adds & Deletes
	public void AddSessio(Integer idA, Integer idS){
		Assignatures.get(idA).AddSessio(idS);
	}
	public void AddGrups(Integer idA, Integer idG) {
		Assignatures.get(idA).AddGrup(idG);
	}
	public void AddTipusSessio(Integer idA, Integer idTS) {
		Assignatures.get(idA).AddTipusSessio(idTS);
	}
	public void AddCorequisit(Integer idA, Integer idA2) {
		Assignatures.get(idA).AddCorequisit(idA2);
	}
	public Boolean DeleteSessio(Integer idA, Integer idS){
		return Assignatures.get(idA).DeleteSessio(idS);
	}
	public Boolean DeleteGrup(Integer idA, Integer idG){
		return Assignatures.get(idA).DeleteSessio(idG);
	}
	public Boolean DeleteTipusSessio(Integer idA, Integer idTS) {
		return Assignatures.get(idA).DeleteTipusSessio(idTS);
	}
	public Boolean DeleteCorequsit(Integer idA, Integer idA2) {
		return Assignatures.get(idA).DeleteCorequisit(idA2);
	}
	
	// Size
	
	public Integer size() {
		return Assignatures.size();
	}
	
}
