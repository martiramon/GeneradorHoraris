package FONTS.agallofre;

import java.util.Arrays;
import java.util.Vector;

import FONTS.esaus.Assignatura;
import FONTS.esaus.Aula;
import FONTS.esaus.ConjuntAssignatura;
import FONTS.esaus.ConjuntAula;
import FONTS.esaus.ConjuntGrup;
import FONTS.esaus.ConjuntGrupAssig;
import FONTS.esaus.ConjuntTipusAules;
import FONTS.esaus.ConjuntTipusSessio;
import FONTS.mramon.CjtRestriccions;
import FONTS.mramon.Restriccio;
import FONTS.mramon.RestriccioUnaria;
import FONTS.mramon.RestriccioBinaria;
import FONTS.mramon.RestriccioGlobal;
import FONTS.mramon.TipusRes;
import FONTS.esaus.GrupAssig;
import FONTS.esaus.Sessio;
import FONTS.esaus.TipusSessio;

public class Horari {

	/* Atributes of the class */
	
	public static Horari horaris;
	
    private ConjuntAula aules = null;
    private CjtRestriccions restriccions = null;
    private ConjuntGrupAssig grups = null;
    private ConjuntTipusSessio tipussesio = null;
    private ConjuntAssignatura assignatures = null;
    private Boolean b = false;
    private Boolean fw = true;
    
    private Sessio[][][] horari;
    
    private Integer HoresDia = 12;
	private Integer DiesSetmana = 5;
    
    
    /* Getters & Setters */
    
    public Boolean getb() {
    	return b;
    }

	public Sessio[][][] getHorari() {
		return horari;
	}


	public void setHorari(Sessio[][][] horari) {
		this.horari = horari;
	}
    
    public Integer getHoresDia() {
		return HoresDia;
	}


	public void setHoresDia(Integer horesDia) {
		HoresDia = horesDia;
	}


	public Integer getDiesSetmana() {
		return DiesSetmana;
	}


	public void setDiesSetmana(Integer diesSetmana) {
		DiesSetmana = diesSetmana;
	}

	public static Horari getInstance() {
		if (horaris == null) horaris = new Horari();
		return horaris;
	}
	
    /**
     *  Instanciate the class Horari in an object that could be used
     *  @return return an instance of Horari
     */
    public Horari(Integer HoresDia, Integer DiesSetmana) {
        aules = ConjuntAula.getInstance();
        grups = ConjuntGrupAssig.getInstance();
        tipussesio = ConjuntTipusSessio.getInstance();
        assignatures = ConjuntAssignatura.getInstance();
        setHorari(new Sessio[DiesSetmana][HoresDia][aules.size()]);
        restriccions = CjtRestriccions.getCjtRestriccions();
        
        this.HoresDia = HoresDia;
        this.DiesSetmana = DiesSetmana;
    }
    
    
    public Horari() {
        aules = ConjuntAula.getInstance();
        grups = ConjuntGrupAssig.getInstance();
        tipussesio = ConjuntTipusSessio.getInstance();
        assignatures = ConjuntAssignatura.getInstance();
        setHorari(new Sessio[DiesSetmana][HoresDia][aules.size()]);
        restriccions = CjtRestriccions.getCjtRestriccions();
    }
    
    public Boolean fordwardChecking (Sessio sessio, Integer dia, Integer hora) {
        /* Check the easy restrictions to know if we should iterate that branch
         * and return either if we should or not
         */
    	
    	Restriccio res = restriccions.GetObject(0);
    	if(res.getActivat()) {
    		return ((RestriccioUnaria)res).comprovar(sessio, dia, hora);
    	}
    	
        return true;
    };
    
    private Boolean mateixGrup (Sessio sessio, Sessio[] horari) {
    	
    	Integer k = sessio.getGrup();
    	
    	for (Sessio s : horari) {
    		if ((s != null) && s.getGrup() == k) return false;
    	}
    	
    	return true;
    }
    
    public Boolean compleixRestriccio (Restriccio restriccio, Sessio sessio, Integer dia, Integer hora, Sessio[][][] horari2) throws HorariExceptions {
        /* Check if the time slot with the given subject can be assigned
         * having in count the given restriction
         */

    	if (restriccio.getTipus() == TipusRes.unaria) {
    		Boolean x = ((RestriccioUnaria)restriccio).comprovar(sessio, dia, hora);
    		//if(!x) System.out.println(restriccio.getId());
    		return x;
    	}
		else if (restriccio.getTipus() == TipusRes.binaria) {
			Boolean x = ((RestriccioBinaria)restriccio).comprovar(sessio, horari2[dia][hora], dia, hora);
			//if(!x) System.out.println(restriccio.getId());
			return x;
		}
		else if (restriccio.getTipus() == TipusRes.global) {
			Boolean x = ((RestriccioGlobal)restriccio).comprovar(sessio, horari2, dia, hora);
			//if(!x) System.out.println(restriccio.getId());
			return x;
		}
		else return true;
    	
    };
    
    public Boolean potSerAssignat(Sessio sessio, Integer dia, Integer hora, Sessio[][][] horari2) throws HorariExceptions {
        /* Check if the given subject can be assigned in the given slot
         * by checking all the selected restrictions, set by the user
         */
    	
    	if (sessio == null) return true;
    	
    	Boolean result = true;
    	
    	//if (sessio.getId().equals("0")) return false;
    	Integer numRestriccions = restriccions.size();
    	
    	for (Integer i = 1; i < numRestriccions; ++i) {
    		
    		Restriccio res = restriccions.GetObject(i);
    		
    		if (res.getActivat() && result) {
    		 	result = compleixRestriccio(res, sessio, dia, hora, horari2);
    		}
    	}
    	
    	if (result) {
			result = mateixGrup(sessio, horari2[dia][hora]);
		}
    	
        return result;
    };
    
    private Sessio[][][] copyHorari(Sessio[][][] horarix){
	   	Sessio[][][] aux = new Sessio[DiesSetmana][HoresDia][aules.size()];
	   	
	   	for (int i = 0; i < horarix.length; ++i)
	   		for (int j = 0; j < horarix[0].length; ++j)
	   			for (int k = 0; k < horarix[0][0].length; ++k)
	   				if (horarix[i][j][k] != null) {
	   					aux[i][j][k] = horarix[i][j][k].copy();
	   				}
	   	
	   	return aux;
    }
    
    private void backtraking2(Sessio[][][] horarix, Vector<Sessio> sessions, Integer s, Integer[] posats, Integer quants) throws HorariExceptions {
        
		if (posats[0].intValue() == quants) {
		   	b = true;
		   	Sessio[][][] aux = copyHorari(horarix);
		   	setHorari(aux);
		}
		
        Integer id = 0;
        if (!b && posats[0] != quants) {
        	for(int l = 0; l < sessions.size(); ++l) {
        		if (posats[0].intValue() != quants) {
	        		Sessio aux2 = sessions.get(l);
	        		
	        		Integer[] horespossibles = generatePossibleHours(aux2);
	        		
			        for(int i = 0; i < DiesSetmana; ++i) {
			        	for (int j = horespossibles[0]; j < horespossibles[1]; ++j) {
			        		for (int k = 0;  k < aules.size(); k++) {
			        			if(sessions.size() > 0 && aules.IsObject(k)) {
				    				Sessio aux = new Sessio(id.toString(), k, sessions.get(l).getGrup(), sessions.get(l).getTipusSessio()); //Crear sessi� per afegir amb les dades que facin falt
				        				        				
				        				if (!fordwardChecking(aux, i, j)) {
				        					fw = false;
				        					b = false;
				        					return;
				        				}
									
					        			if(!b && fw) {
					        				if (horarix[i][j][k] == null && potSerAssignat(aux, i, j, horarix)) {
					        					
					        					horarix[i][j][k] = aux;
					        					posats[0]++;
					        					sessions.remove(aux2);
					        					
					        					backtraking2(horarix, sessions, s, posats, quants);
					        					
					        					if (!b) {
							        				sessions.add(aux2);
							        				posats[0]--;
							        				horarix[i][j][k] = null;
					        					}
					        					
				        				}
					        		}
			        			}
		        			}
		        		}
		        	}
		        }
        	}
		}
        
        return;
    }
    
    /**
     * The function allows the user change the schedule adding the modifications that he wants
     * 
     * @param oday  dia origen de la sessio que es vol modificar
     * @param ohour hora origen de la sessio que es vol modificar
     * @param oaula Aula origen de la sessio que es vol modificar
     * @param dday  Dia dest� de la sessio que es vol modificar
     * @param dhour hora dest� de la sessio que es vol modificar
     * @param daula Aula dest� de la sessio que es vol modificar
     * @return Returns true if the schedule can be modified and copy 
     * to the atribute of the class the new version of the schedule, else return false and keep the original schedule as it was
     * @throws HorariExceptions
     */
    public Boolean modificarHorari (Integer oday, Integer ohour, Integer oaula, Integer dday, Integer dhour, Integer daula) throws HorariExceptions {
    	Sessio[][][] newHorari = copyHorari(this.horari);
    	
    	// Copiem el valor de la sessio de desti per tal de no perdrel
    	Sessio aux = newHorari[oday][ohour][oaula];
    	Sessio aux2 = newHorari[dday][dhour][daula];
    	
    	newHorari[oday][ohour][oaula] = null;
    	newHorari[dday][dhour][daula] = null;
    	
    	if (aux != null) aux.setAula(daula);
    	if (aux2 != null) aux2.setAula(oaula);
    	
    	if (potSerAssignat(aux, dday, dhour, newHorari)) {
        	newHorari[dday][dhour][daula] = aux;
        	
    		if(potSerAssignat(aux2, oday, ohour, newHorari)) {
    			newHorari[oday][ohour][oaula] = aux2;
    			this.horari = copyHorari(newHorari);
        		return true;
    		}
    		
    	}
    	
    	return false;
    }
    
    private Integer[] generatePossibleHours(Sessio sessio) {
		// TODO Auto-generated method stub
    	Integer [] res = new Integer[2]; 
    	
    	boolean esmati = grups.GetObject(sessio.getGrup()).getMati();
    	
    	if (esmati) {
    		res[0] = 0;
    		res[1] = 5;
    	}
    	else {
    		res[0] = 6;
    		res[1] = 11;
    	}
    	
		return res;
	}

    public Boolean generarHorari() throws HorariExceptions {
        /* Generate the schedule by assigning all of the sessions to a time
         * slot and a classroom, if this isn't possible return false, and 
         * return the partial solution to allow the user make some changes
         */
        //Generar un vector amb cada sessi� separada per una hora
    	Vector<Sessio> allSessions = generarThings ();
        
    	Sessio[][][] hor = new Sessio[DiesSetmana][HoresDia][aules.size()];
    	Integer [] posats = new Integer[1];
    	posats[0] = 0;
    	//backtraking(hor, allSessions, 0, 0, 0, posats, allSessions.size());
    	backtraking2(hor, allSessions, 0, posats, allSessions.size());
    	return b;
        
    };
    
    public Vector<Sessio> generarThings(){
    	Vector<GrupAssig> aux = getAllGrupAssig();
    	return generarConjuntSessions(aux); 
    }
    
    /* Funcions auxiliars */
    
    private Vector<Sessio> generarConjuntSessions (Vector<GrupAssig> sessions){
    	
    	Vector<Sessio> result = new Vector<Sessio>();
    	Integer id = 0; 
    	Integer iterator = grups.size();    	
    	//for (GrupAssig g : grups) {
    	for (int f = 0; f < iterator; ++f) {
    		GrupAssig g = grups.GetObject(f);
    		for (Integer i : assignatures.GetObject(g.getAssignatura()).getTipusSessions()) {
    			TipusSessio ts = tipussesio.GetObject(i);
    			for (int k = 0; k < ts.getHores(); ++k) {
                	Sessio aux = new Sessio(id.toString(), null , f,  ts.getTipus());
                	result.add(aux);
                	++id;
    			}
    		}
    	}
    	
    	return result;
    }
    
    private Vector<GrupAssig> getAllGrupAssig() {

    	Integer iter = grups.size();
    	Vector<GrupAssig> aux = new Vector<GrupAssig>();
    	for (int i = 0; i < iter; ++i) {
    		if (grups.IsObject(i)) { 
    			GrupAssig gassig = grups.GetObject(i);
    			aux.add(gassig);
    		}
    	}
    	
    	return aux;
    }

}