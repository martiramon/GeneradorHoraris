package FONTS.esaus;

import java.util.Vector;

public class ConjuntGrup {
	private static ConjuntGrup CG;
	private Vector<Grup> Grups;
	private Vector<Boolean> Ocupat;
	
	//Creators & Instances
	private ConjuntGrup(){
		Grups = new Vector<Grup>();
		Ocupat = new Vector<Boolean>();
	}
	public static ConjuntGrup getInstance() {
		if (CG == null) CG = new ConjuntGrup();
		return CG;
	}
	
	//IDs & Objects
	public  Integer NewId(Grup g){
		Integer i= GetId(g);
		if (i != -1) return -1;
		Grups.add(g);
		Ocupat.add(true);
		return Grups.size() - 1;
	}
	public Integer GetId(Grup g) {
		for(int i = 0; i < Grups.size(); ++i) {
			if (Ocupat.get(i) == true && this.getNum(i).equals(g.getNum())) return i;
		}
		return -1;
	}
	public Boolean IsObject(Integer idG) {
		if (idG < Ocupat.size() && idG >= 0){
			return Ocupat.get(idG);
		}
		return false;
	}
	public Grup GetObject(Integer idG){
		if (idG < Ocupat.size() && idG >= 0 && Ocupat.get(idG)){
			return Grups.get(idG);
		}
		else return null;
	}
	public void DeleteObject(Integer idG) {
		if (idG < Ocupat.size() && idG >= 0){
			Ocupat.set(idG,false);
		}
	}
	
	//Getters
	public Integer getNum(Integer idG) {
		return Grups.get(idG).getNum();
	}
	public Integer getCapacitat(Integer idG){
		return Grups.get(idG).getCapacitat();
	}
	public Vector<Integer> getGrupAssigs(Integer idG) {
		return Grups.get(idG).getGrupAssigs();
	}
	
	//Setters
	public void setNum(Integer idG, Integer num) {
		Grups.get(idG).setNum(num);
	}
	
	public void setCapacitat(Integer idG, Integer capacitat){
		Grups.get(idG).setCapacitat(capacitat);
	}
	public void setGrupsAssigs(Integer idG, Vector<Integer> grupsassig) {
		Grups.get(idG).setGrupAssigs(grupsassig);
	}
	
	//Adds & Deletes
	public void AddGrupAssig(Integer idG, Integer idGA) {
		Grups.get(idG).AddGrupAssig(idGA);
	}
	public Boolean DeleteGrupAssig(Integer idG, Integer idGA){
		return Grups.get(idG).DeleteGrupAssig(idGA);
	}
	
	public void reiniciarConjunt() {
		Grups = new Vector<Grup>();
		Ocupat = new Vector<Boolean>();
	}
	
	public Integer size() {
		return Grups.size();
	}
}
