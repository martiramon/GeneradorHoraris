package FONTS.esaus;

//import java.util.Vector;

public class Sessio {
	String id;
	Integer grup;
	Integer aula;
	Integer tsessio;
	//Vector<Retriccio> restriccions;
	
	
	//Creators & Operations
	public Sessio() {
		this.id = null;
		this.aula = null;
		this.grup = null;
		this.tsessio = null;
	}
	public Sessio(String id, Integer aula, Integer grup, Integer tsessio) {
		this.id = id;
		this.aula = aula;
		this.grup = grup;
		this.tsessio = tsessio;
	}
	public Boolean equals(Sessio s) {
		return (this.id.equals(s.getId()) && this.grup.equals(s.getGrup()) && this.aula.equals(s.getAula()) && 
				this.tsessio.equals(s.getTipusSessio()));
	}
	public Sessio copy() {
		Sessio copy = new Sessio(this.id, this.aula, this.grup, this.tsessio);
		return copy;
	}

	
	//Getters
	public String getId() {
		return this.id;
	}
	public Integer getAula() {
		return this.aula;
	}
	public Integer getGrup() {
		return this.grup;
	}
	public Integer getTipusSessio() {
		return this.tsessio;
	}
	
	//Setters
	public void setId(String id) {
		this.id = id;
	}
	public void setAula(Integer aula) {
		this.aula = aula;
	}
	public void setGrup(Integer grup) {
		this.grup = grup;
	}
	public void setTipusSessio(Integer tsessio) {
		this.tsessio = tsessio;
	}
	
	// Clone
	
	
}
