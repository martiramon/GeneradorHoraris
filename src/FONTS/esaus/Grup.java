package FONTS.esaus;

import java.util.Vector;

public class Grup {
	Integer num;
	Integer capacitat;
	Vector<Integer> grupsassig;
	
	//Creators & Operations
	public Grup() {
		this.num = null;
		this.capacitat = null;
		this.grupsassig = new Vector<Integer>();
	}
	public Grup(Integer num, Integer capacitat) {
		this.num = num;
		this.capacitat = capacitat;
		this.grupsassig = new Vector<Integer>();
	}
	public Boolean equals(Grup g) {
		return (this.num.equals(g.getNum())  && this.capacitat.equals(g.getCapacitat()) &&
				this.grupsassig.equals(g.getGrupAssigs()));
	}
	
	
	//Getters
	public Integer getNum() {
		return this.num;
	}
	public Integer getCapacitat() {
		return this.capacitat;
	}
	public Vector<Integer> getGrupAssigs() {
		return this.grupsassig;
	}
	
	//Setters
	public void setNum(Integer num) {
		this.num = num;
	}
	public void setCapacitat(Integer capacitat) {
		this.capacitat = capacitat;
	}
	public void setGrupAssigs(Vector<Integer> grupsassig){
		this.grupsassig = grupsassig;
	}
	
	//Adds & Deletes
	public void AddGrupAssig(Integer idGA) {
		if (this.grupsassig.indexOf(idGA) == -1){
			this.grupsassig.add(idGA);
		}
	}
	public Boolean DeleteGrupAssig(Integer idGA){
		return this.grupsassig.removeElement(idGA);
	}
	

	
}
