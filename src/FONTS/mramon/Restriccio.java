package FONTS.mramon;

public abstract class Restriccio {
	private String id;
	private TipusRes tipus;
	private Boolean activat;
	
	//Constructores

    public Restriccio(String id, TipusRes tipus, Boolean activat) {
        this.id = id;
        this.tipus = tipus;
        this.activat = activat;
    }
    
    public Restriccio(String id, TipusRes tipus) {
        this.id = id;
        this.tipus = tipus;
        this.activat = null;
    }
	
	//Getters    

    public String getId(){
        return this.id;
    }

    public Boolean getActivat() {
    	return this.activat;
    }
    
    public TipusRes getTipus() {
    	return this.tipus;
    }
    //Setters
	
    public void setId(String id) {
        this.id = id;
    }
       
    public void setTipus(TipusRes tipus) {
    	this.tipus = tipus;
    }

    public void setActivat(Boolean activat) {
        this.activat = activat;
    }
    
    public void activatON() {
    	this.activat = true;    	
    }
    
    public void activatOFF() {
    	this.activat = false;
    }
    
    //Mètodes
}
