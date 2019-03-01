package FONTS.agallofre;

import java.util.Properties;
import java.util.Vector;
import FONTS.esaus.Assignatura;
import FONTS.esaus.Aula;
import FONTS.esaus.Grup;
import FONTS.esaus.GrupAssig;
import FONTS.esaus.PlaEstudis;
import FONTS.esaus.Sessio;
import FONTS.esaus.TipusAula;
import FONTS.esaus.TipusSessio;
import FONTS.mramon.CjtRestriccions;
import javafx.util.Pair;
import persistencia.ControladorPersistencia;
import FONTS.esaus.ConjuntAssignatura;
import FONTS.esaus.ConjuntAula;
import FONTS.esaus.ConjuntGrup;
import FONTS.esaus.ConjuntGrupAssig;
import FONTS.esaus.ConjuntPlaEstudis;
import FONTS.esaus.ConjuntSessio;
import FONTS.esaus.ConjuntTipusAules;
import FONTS.esaus.ConjuntTipusSessio;
import com.google.gson.*;

public class CtrlDomini {

	/* Funcions de creaci� */
	
	private Horari horari = Horari.getInstance();
	private Gson gson = new Gson();
	private ControladorPersistencia persistencia = new ControladorPersistencia();
	
	public CtrlDomini() {
	}
	
	public Integer crearAssignatura (String acronim, String nom, Integer nivell, Vector<Integer> tsessions) {
		Assignatura assig = new Assignatura(acronim, nom, nivell, tsessions);
		ConjuntAssignatura cassig = ConjuntAssignatura.getInstance();
		if (cassig.GetId(assig) == -1) return cassig.NewId(assig);
		else return -1;
	}
	
	public Integer crearAssignatura (String acronim, String nom, Integer nivell, Vector<String> tsess, Vector<Pair<String,String>> grups, Vector<String> coreq) {
		Vector<Integer> c = null;
		Vector<Integer> ts = null;
		Vector<Integer> g = null;
		
		if (coreq != null && coreq.size() > 0) c = getVectorCoreq(coreq);
		if (tsess != null && tsess.size() > 0) ts = getVectorTsessions(tsess);
		if (grups != null && grups.size() > 0) g = getVectorGrups(grups);
		
		Assignatura assig = new Assignatura(acronim, nom, nivell, ts, null, g, c);

		ConjuntAssignatura cassig = ConjuntAssignatura.getInstance();
		if (cassig.GetId(assig) == -1) {
			Integer id = cassig.NewId(assig);
			if (c != null) { 
				for (Integer aux : c) { 
					afegirCorequisits(id, aux); 
				} 
			}
			return id;
		}
		else return -1;
	}
	
	public Integer crearAula (String id, Integer capacitat) {		
		Aula aula = new Aula(id, capacitat);
		ConjuntAula caula = ConjuntAula.getInstance();
		
		if (caula.GetId(aula) == -1) return caula.NewId(aula);
		else return -1;
	}
	
	public Integer crearAula (String id, Integer capacitat, String tipusA) {
		Integer tipusAId = getIdTaulaByName(tipusA); 
		Aula aula = new Aula(id, capacitat, tipusAId);
		ConjuntAula caula = ConjuntAula.getInstance();
		
		if (caula.GetId(aula) == -1) return caula.NewId(aula);
		else return -1;
	}
	
	public Integer crearGrup (Integer num, Integer capacitat) {		
		Grup grup = new Grup(num, capacitat);
		ConjuntGrup cgrup = ConjuntGrup.getInstance();
		
		if (cgrup.GetId(grup) == -1) return cgrup.NewId(grup);
		else return -1;

	}
		
	public Integer crearGrupAssignatura (Integer grup, Integer assignatura, Boolean mati) {		
		GrupAssig gassig = new GrupAssig(grup, assignatura, mati);
		ConjuntGrupAssig cgrupassig = ConjuntGrupAssig.getInstance();
		
		if (cgrupassig.GetId(gassig) == -1) return cgrupassig.NewId(gassig);
		else return -1;		
	}
	
	public Integer crearPlaEstudis (String nom, Vector<Integer> assignatures) {		
		PlaEstudis plaestudis = new PlaEstudis(nom, assignatures);
		ConjuntPlaEstudis cplaestudis = ConjuntPlaEstudis.getInstance();
		
		if (cplaestudis.GetId(plaestudis) == -1) return cplaestudis.NewId(plaestudis);
		else return -1;		
	}
	
	public Integer crearSessio (String id, Integer aula, Integer grup, Integer tsessio) {		
		Sessio sessio = new Sessio(id, aula, grup, tsessio);
		ConjuntSessio csessio = ConjuntSessio.getInstance();
		
		if (csessio.GetId(sessio) == -1) return csessio.NewId(sessio);
		else return -1;
	}
	
	public Integer crearTipusAula (String id) {		
		TipusAula taula = new TipusAula(id);
		ConjuntTipusAules ctaula = ConjuntTipusAules.getInstance();
		
		if (ctaula.GetId(taula) == -1) return ctaula.NewId(taula);
		else return -1;
	}
	
	public Integer crearTipusSessio (String id, Integer tipus, Integer hores) {
		TipusSessio tsessio = new TipusSessio(id, tipus, hores);
		ConjuntTipusSessio ctsessio = ConjuntTipusSessio.getInstance();
		
		if (ctsessio.GetId(tsessio) == -1) return ctsessio.NewId(tsessio);
		else return -1;
	}
	
	public Integer crearTipusSessio (String id, String tipus, Integer hores) {
		Integer t = getTipusAulaIdByName(tipus);
		TipusSessio tsessio = new TipusSessio(id, t, hores);
		ConjuntTipusSessio ctsessio = ConjuntTipusSessio.getInstance();
		
		if (ctsessio.GetId(tsessio) == -1) return ctsessio.NewId(tsessio);
		else return -1;
	}
	
	/* Funcions d'eliminar */ 

	public void eliminarAssignatura (Integer idAssig) {
		ConjuntAssignatura cassig = ConjuntAssignatura.getInstance();
		cassig.DeleteObject(idAssig);
	}
	
	public Boolean eliminarAssignatura(String nom) {
		Boolean result = false;
		Integer a = getAssigIdByName(nom);
		
		if (a != -1) {
			eliminarAssignatura(a);
			result = true;
		}
		
		return result;
	}
	
	public void eliminarAula (Integer idAula) {
		ConjuntAula caula = ConjuntAula.getInstance();
		caula.DeleteObject(idAula);
	}
	
	public Boolean eliminarAula(String id) {
		Boolean result = false;
		Integer a = getAulaIdByName(id);
		
		if (a != -1) {
			eliminarAula(a);
			result = true;
		}
		return result;
	}
	
	public Boolean eliminarTaula(String id) {
		Boolean result = false;
		Integer a = getIdTaulaByName(id);
		
		if (a != -1) {
			eliminarTipusAula(a);
			result = true;
		}
		return result;
	}
	
	public void eliminarGrup (Integer idGrup) {
		ConjuntGrup cgrup = ConjuntGrup.getInstance();
		cgrup.DeleteObject(idGrup);
	}
	
	public Boolean eliminarGrup(String id) {
		Boolean result = false;
		Integer a = getGrupIdById(id);
		
		if (a != -1) {
			eliminarGrup(a);
			result = true;
		}
		return result;
	}

	public void eliminarGrupAssignatura (Integer idGrupAssig) {
		ConjuntGrupAssig cgrupassig = ConjuntGrupAssig.getInstance();
		cgrupassig.DeleteObject(idGrupAssig);
	}
	
	public void eliminarPlaEstudis (Integer idPlaEstudis) {
		ConjuntPlaEstudis cpla = ConjuntPlaEstudis.getInstance();
		cpla.DeleteObject(idPlaEstudis);
	}
	
	public void eliminarSessio (Integer idSessio) {
		ConjuntSessio csessio = ConjuntSessio.getInstance();
		csessio.DeleteObject(idSessio);
	}
	
	public void eliminarTipusAula (Integer idTaula) {
		ConjuntTipusAules ctaula = ConjuntTipusAules.getInstance();
		ctaula.DeleteObject(idTaula);
	}
	
	public void eliminarTipusSessio (Integer idTsessio) {
		ConjuntTipusSessio ctsessio = ConjuntTipusSessio.getInstance();
		ctsessio.DeleteObject(idTsessio);
	}
	
	public Boolean eliminarTipusSessio(String id) {
		Boolean result = false;
		Integer a = getTSessioIdByName(id);
		
		if (a != -1) {
			eliminarTipusSessio(a);
			result = true;
		}
		return result;
	}
	
	/* Operacions amb restriccions */ 
	
	public void activarRestriccio (Integer id) {
		CjtRestriccions cjtRestriccions = CjtRestriccions.getCjtRestriccions();
		cjtRestriccions.activatON(id);
	}
	
	public void desactivarRestriccio (Integer id) {
		CjtRestriccions cjtRestriccions = CjtRestriccions.getCjtRestriccions();
		cjtRestriccions.activatOFF(id);
	}
	
	/* Operacions consultores */ 
	
	public String consultarAssignatura (Integer id) {
		String assingatura = null; 
		
		Assignatura a = ConjuntAssignatura.getInstance().GetObject(id);
		assingatura = gson.toJson(a);
		
		return assingatura;
	}
		
	public String consultarAula (Integer id) {
		String aula = null;
		
		Aula a = ConjuntAula.getInstance().GetObject(id);
		aula = gson.toJson(a);
		
		return aula;
	}
	
	public String consultarGrup (Integer id) { 
		String grup = null;
		
		Grup g = ConjuntGrup.getInstance().GetObject(id);
		grup = gson.toJson(g);
		
		return grup;
	}
	
	public String consultarSessio (Integer id) {
		String sessio = null;
		
		Sessio s = ConjuntSessio.getInstance().GetObject(id);
		sessio = gson.toJson(s);
		
		return sessio;
	}
	
	public String consultarTipusAula (Integer id) {
		String tipusAula = null;
		
		TipusAula ta = ConjuntTipusAules.getInstance().GetObject(id);
		tipusAula = gson.toJson(ta);
		
		return tipusAula;
	}
	
	public String consultarTipusSessio (Integer id) {
		String tipusSessio = null;
		
		TipusSessio ts = ConjuntTipusSessio.getInstance().GetObject(id);
		tipusSessio = gson.toJson(ts);
		
		return tipusSessio;
	}
	
	public String consultarGrupAssig (Integer id) {
		String grupAssig = null;
		
		GrupAssig ga = ConjuntGrupAssig.getInstance().GetObject(id);
		grupAssig = gson.toJson(ga);
		
		return grupAssig;
	}
	
	/* Consultores de conjunts */ 
	
	public String ConsultarConjuntAula() {
		String conjuntAula = null;
		conjuntAula = gson.toJson(ConjuntAula.getInstance());
		return conjuntAula;
	}
	
	public String ConsultarConjuntAssignatura() {
		String conjuntAssignatura = null;
		conjuntAssignatura = gson.toJson(ConjuntAssignatura.getInstance());
		return conjuntAssignatura;
	}
	
	public String ConsultarConjuntGrup() {
		String conjuntGrup = null;
		conjuntGrup = gson.toJson(ConjuntGrup.getInstance());
		return conjuntGrup;
	}
	
	public String ConsultarConjuntSessio() {
		String conjuntSessio = null;
		conjuntSessio = gson.toJson(ConjuntSessio.getInstance());
		return conjuntSessio;
	}
	
	public String ConsultarConjuntTipusAula() {
		String conjuntTipusAula = null;
		conjuntTipusAula = gson.toJson(ConjuntTipusAules.getInstance());
		return conjuntTipusAula;
	}
	
	public String ConsultarConjuntTipusSessio() {
		String conjuntTipusSessio = null;
		conjuntTipusSessio = gson.toJson(ConjuntTipusSessio.getInstance());
		return conjuntTipusSessio;
	}
	
	public String ConsultarGrupAssig() {
		String conjuntGrupAssig = null;
		conjuntGrupAssig = gson.toJson(ConjuntGrupAssig.getInstance());
		return conjuntGrupAssig;
	}
	
	/* Operacions horari */ 
	
	public Boolean generarHorari() throws HorariExceptions { 
		horari = Horari.getInstance();
		return horari.generarHorari();
	}
	
	/**
	 * 
	 * @param dia test
	 * @param hora
	 * @return Returns an string that contains the set of sessions that belongs to an specific day and time  
	 */
	public Vector<String> getInfoDiaHora(Integer dia, Integer hora) {
		Vector<String> infoDiaHora = new Vector<String>();
		horari = Horari.getInstance();
		Sessio[][][] horariGenerat = horari.getHorari(); 
		
		for (Sessio s : horariGenerat[dia][hora]) {
			infoDiaHora.add(gson.toJson(s));
		}
		
		return infoDiaHora;
	}
	
	
	/**
	 * 
	 * @param dia
	 * @return A 2d matrix containing the information of all sessions
	 */
	public Vector<Vector<String>> getInfoDia(Integer dia) {
		
		Vector<Vector<String>> infoDia = new Vector<Vector<String>>();
		horari = Horari.getInstance();
		Sessio[][][] horariGenerat = horari.getHorari(); 
		
		for (Sessio[] SessionsDia : horariGenerat[dia]) {
			Vector<String> infoDiaHora = new Vector<String>();
			for (Sessio s : SessionsDia) {
				infoDiaHora.add(gson.toJson(s));
			}
			infoDia.add(infoDiaHora);
		}
		
		return infoDia;
	}
	
	/**
	 * 
	 * @return a 3d matrix of strings that contains all info in the schedule
	 */
	public Vector<Vector<Vector<String>>> getInfoHorari() {
		
		Vector<Vector<Vector<String>>> infoHorari = new Vector<Vector<Vector<String>>>();
		
		Sessio[][][] horariGenerat = horari.getHorari(); 
		
		for (Sessio[][] SessionsTotals : horariGenerat) {
			Vector<Vector<String>> infoDia = new Vector<Vector<String>>();
			for (Sessio[] SessionsDia : SessionsTotals) {
				Vector<String> infoDiaHora = new Vector<String>();
				for (Sessio s : SessionsDia) {
					infoDiaHora.add(gson.toJson(s));
				}
				infoDia.add(infoDiaHora);
			}
			infoHorari.add(infoDia);
		}
		
		return infoHorari;
	}
	
	/* Buscadores */
	
	/**
	 * 
	 * @param name
	 * @return returns string with a JSON of the subject
	 */
	public String getAssigByName (String name) {
		ConjuntAssignatura cjassig = ConjuntAssignatura.getInstance();
		String result = null;
		Integer size = cjassig.size();
		for (int i = 0; i<size; ++i ) {
			if (cjassig.IsObject(i)) {
				if (cjassig.GetObject(i).getNom().equals(name)) {
					result = consultarAssignatura(i);
				}
			}
		}
		return result;
	}
	
	public String getTipusAulaByName (String name) {
		ConjuntTipusAules cjtaula = ConjuntTipusAules.getInstance();
		String result = null;
		Integer size = cjtaula.size();
		for (int i = 0; i<size; ++i ) {
			if (cjtaula.IsObject(i)) {
				if (cjtaula.GetObject(i).getId().equals(name)) {
					result = consultarTipusAula(i);
				}
			}
		}
		return result;
	}
	
	public String getTipusSessioByName (String name) {
		ConjuntTipusSessio cjsessio = ConjuntTipusSessio.getInstance();
		String result = null;
		Integer size = cjsessio.size();
		for (int i = 0; i<size; ++i ) {
			if (cjsessio.IsObject(i)) {
				if (cjsessio.GetObject(i).getId().equals(name)) {
					result = consultarTipusSessio(i);
				}
			}
		}
		return result;
	}
	
	public Integer getTipusAulaIdByName (String name) {
		ConjuntTipusAules cjtaula = ConjuntTipusAules.getInstance();
		Integer size = cjtaula.size();
		for (int i = 0; i<size; ++i ) {
			if (cjtaula.IsObject(i)) {
				if (cjtaula.GetObject(i).getId().equals(name)) {
					return i;
				}
			}
		}
		return null;
	}
	
	/* Buscar una assignatura al conjunt */
	
	/**
	 * Funcio per obtenir una assignatura del conjunt a partir d'un nom
	 * @param name
	 * @return
	 */
	protected Assignatura getAssigObjectByName (String name) {
		ConjuntAssignatura cjassig = ConjuntAssignatura.getInstance();
		Assignatura result = null;
		Integer size = cjassig.size();
		for (int i = 0; i < size; ++i ) {
			if (cjassig.IsObject(i)) {
				if (cjassig.GetObject(i).getNom().equals(name)) {
					result = cjassig.GetObject(i);
				}
			}
		}
		return result;
	}
	
	protected Integer getAssigIdByName (String name) {
		ConjuntAssignatura cjassig = ConjuntAssignatura.getInstance();
		Integer result = -1;
		Integer size = cjassig.size();
		for (int i = 0; i < size; ++i ) {
			if (cjassig.IsObject(i)) {
				if (cjassig.GetObject(i).getNom().equals(name)) {
					return i;
				}
			}
		}
		return result;
	}
	
	protected Integer getTsessionsIdByName (String name) {
		ConjuntTipusSessio ctsessio = ConjuntTipusSessio.getInstance();
		Integer size = ctsessio.size();
		for (int i = 0; i < size; ++i ) {
			if (ctsessio.IsObject(i)) {
				if (ctsessio.GetObject(i).getId().equals(name)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	protected Integer getAulaIdByName (String id) {
		ConjuntAula cjaula = ConjuntAula.getInstance();
		Integer result = -1;
		Integer size = cjaula.size();
		for (int i = 0; i < size; ++i ) {
			if (cjaula.IsObject(i)) {
				if (cjaula.GetObject(i).getId().equals(id)) {
					result = i;
				}
			}
		}
		return result;
	}
	
	public String getAulaStringByName (String id) {
		ConjuntAula cjaula = ConjuntAula.getInstance();
		String result = null;
		Integer size = cjaula.size();
		for (int i = 0; i < size; ++i ) {
			if (cjaula.IsObject(i)) {
				if (cjaula.GetObject(i).getId().equals(id)) {
					result = gson.toJson(cjaula.GetObject(i));
				}
			}
		}
		return result;
	}
	
	protected Integer getTSessioIdByName (String id) {
		ConjuntTipusSessio cjtses = ConjuntTipusSessio.getInstance();
		Integer result = -1;
		Integer size = cjtses.size();
		for (int i = 0; i < size; ++i ) {
			if (cjtses.IsObject(i)) {
				if (cjtses.GetObject(i).getId().equals(id)) {
					result = i;
				}
			}
		}
		return result;
	}
	
	private Integer getGrupIdById(String id) {
		ConjuntGrup cjgrup = ConjuntGrup.getInstance();
		Integer result = -1;
		Integer size = cjgrup.size();
		for (int i = 0; i < size; ++i ) {
			if (cjgrup.IsObject(i)) {
				if (cjgrup.GetObject(i).getNum() == Integer.valueOf(id)) {
					result = i;
				}
			}
		}
		return result;
	}
	
	protected Integer getIdTaulaByName (String id) {
		ConjuntTipusAules cjtaula = ConjuntTipusAules.getInstance();
		Integer size = cjtaula.size();
		
		for (int i = 0; i < size; ++i) {
			if (cjtaula.IsObject(i)) {
				if (cjtaula.GetObject(i).getId().equals(id)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	protected Grup getIdGroupByNum (Integer num) {
		ConjuntGrup cjgrup = ConjuntGrup.getInstance();
		Integer size = cjgrup.size();
		
		for (int i = 0; i < size; ++i) {
			if (cjgrup.IsObject(i)) {
				if (cjgrup.GetObject(i).getNum() == num) {
					return cjgrup.GetObject(i);
				}
			}
		}
		return null;
	}
	
	protected TipusSessio getIdTsessioByString (String id) {
		ConjuntTipusSessio cjtsessio = ConjuntTipusSessio.getInstance();
		Integer size = cjtsessio.size();
		
		for (int i = 0; i < size; ++i) {
			if (cjtsessio.IsObject(i)) {
				if (cjtsessio.GetObject(i).getId().equals(id)) {
					return cjtsessio.GetObject(i);
				}
			}
		}
		return null;
	}
	
	protected Aula getAulaObjectByid (String id) {
		ConjuntAula cjaula = ConjuntAula.getInstance();
		Aula result = null;
		Integer size = cjaula.size();
		for (int i = 0; i < size; ++i ) {
			if (cjaula.IsObject(i)) {
				if (cjaula.GetObject(i).getId().equals(id)) {
					result = cjaula.GetObject(i);
				}
			}
		}
		return result;
	}
	
	public Vector<Integer> getVectorCoreq (Vector<String> coreq){
		Vector<Integer> vi = new Vector<Integer>();
		
		for (String s : coreq) {
			Integer a = getAssigIdByName(s);
			vi.add(a);		
		}
		
		return vi;
	}
	
	private Vector<Integer> getVectorTsessions (Vector<String> tsessions) {
		Vector<Integer> vi = new Vector<Integer>();
		
		for (String s : tsessions) {
			Integer ts = getTsessionsIdByName(s);
			vi.add(ts);		
		}
		return vi;
	}
	
	private Integer getGrupIdbyGrup(String grup) {
		ConjuntGrup conjuntGrup = ConjuntGrup.getInstance();
		
		for (int i = 0; i < conjuntGrup.size(); ++i) {
			if (conjuntGrup.IsObject(i)) {
				if (conjuntGrup.GetObject(i).getNum() == Integer.valueOf(grup)) {
					return i;
				}
			}
		}
		return -1;
	}
	
	private Vector<Integer> getVectorGrups (Vector<Pair<String,String>> grups) {
		Vector<Integer> vi = new Vector<Integer>();
			
		for (Pair<String,String> p : grups) {
			Integer a = getAssigIdByName(p.getKey());
			Integer g = getGrupIdbyGrup(p.getValue());
			Integer ts = getGroupAssigIdByAssig(a,g);
			vi.add(ts);		
		}
		return vi;
	}
	
	public Boolean modificarAssignatura (String nom, String acronim, Integer nivell, Vector<Pair<String,String>> grups, 
			Vector<String> tsessions, Vector<Integer> sessions, Vector<String> corequisits) {
		Boolean modificat = false;
		ConjuntAssignatura cjassig = ConjuntAssignatura.getInstance();
		
		Vector<Integer> cr = getVectorCoreq(corequisits);
		Vector<Integer> ts = getVectorTsessions(tsessions);
		Vector<Integer> g = getVectorGrups(grups);
		
		Assignatura assig = getAssigObjectByName(nom);
		if (assig != null) {
			eliminarAssignatura(cjassig.GetId(assig));
			assig.setAcronim(acronim);
			assig.setNivell(nivell);
			assig.setGrups(g);
			assig.setTipusSessions(ts);
			assig.setSessions(sessions);
			assig.setCorequisit(cr);
			cjassig.NewId(assig);
			modificat = true;
		}
		return modificat;
	}
	
	public Boolean modificarAula (String id, Integer capacitat, String tipusA, Vector<Integer> sessions) {
		Boolean modificat = false;
		ConjuntAula cjaula = ConjuntAula.getInstance();
		Aula a = getAulaObjectByid(id);
		
		Integer taula = getIdTaulaByName(tipusA);
		
		if (a != null) {
			eliminarAula(cjaula.GetId(a));
			a.setCapacitat(capacitat);
			a.setTipus(taula);
			a.setSessions(sessions);
			cjaula.NewId(a);
			modificat = true;
		}
		return modificat;
	}
	
	public Boolean modificarGrup(Integer num, Integer cap) {
		Boolean modificat = false;
		ConjuntGrup cjgrup = ConjuntGrup.getInstance();
		Grup g = getIdGroupByNum(num);
		
		if (g != null) {
			eliminarGrup(cjgrup.GetId(g));
			g.setCapacitat(cap);
			cjgrup.NewId(g);
			modificat = true;
		}
		
		return modificat;
	}
	
	public Boolean modificarTsessio(String id, Integer tipus) {
		Boolean modificat = false;
		ConjuntTipusSessio cjtsessio = ConjuntTipusSessio.getInstance();
		TipusSessio ts = getIdTsessioByString(id);
		
		if (ts != null) {
			eliminarGrup(cjtsessio.GetId(ts));
			ts.setTipus(tipus);
			cjtsessio.NewId(ts);
			modificat = true;
		}
		
		return modificat;
	}
	
	public Boolean afegirCorequisits(String assig, Vector<Pair<String,Boolean>> coreqs) {
		
		Assignatura a = getAssigObjectByName(assig);
		Vector<Integer> co = new Vector<Integer>();
		
		if (a != null) {
			Integer ida = ConjuntAssignatura.getInstance().GetId(a);
			
			for (Pair<String,Boolean> s : coreqs) {
				Integer naid = getAssigIdByName(s.getKey());
				Assignatura na = ConjuntAssignatura.getInstance().GetObject(naid);	
				Vector<Integer> aux = na.getCorequisit();
				if (s.getValue()) {
					co.add(naid);
					if (!aux.contains(ida)) aux.add(ida);
				}
				else {
					if (aux.contains(ida)) aux.remove(ida);
				}
				
				/* Set the main assig as coreq of one in the vector */
				
				ConjuntAssignatura.getInstance().GetObject(naid).setCorequisit(aux);
			}
			ConjuntAssignatura.getInstance().GetObject(ida).setCorequisit(co);	
			return true;
		}
		return false;
	}
	
	public void afegirCorequisits(Integer assig, Integer assig2) {
		Vector<Integer> cor = ConjuntAssignatura.getInstance().GetObject(assig2).getCorequisit();
		if (cor == null) {
			cor = new Vector<Integer>();
			cor.add(assig);
		}
		else {
			if (!cor.contains(assig)) {
				cor.add(assig);
			}
		}
		
		ConjuntAssignatura.getInstance().GetObject(assig2).setCorequisit(cor);
	}
	
	/**
	 * 
	 * @return return a vector of strings containing all subject names
	 */
	public Vector<String> getAllAssigNames (){
		
		ConjuntAssignatura cjassig = ConjuntAssignatura.getInstance();
		Vector<String> result = new Vector<String>();
		Integer size = cjassig.size();
		
		for (int i = 0; i<size; ++i ) {
			if (cjassig.IsObject(i)) {
				result.add(cjassig.getNom(i));
			}
		}
		
		return result;
	}
	
	public Vector<String> getAllTipusAules (){
		ConjuntTipusAules cjtaula = ConjuntTipusAules.getInstance();
		Vector<String> result = new Vector<String>();
		Integer size = cjtaula.size();
		
		for (int i = 0; i<size; ++i ) {
			if (cjtaula.IsObject(i)) {
				result.add(cjtaula.getId(i));
			}
		}
		
		return result;
	}
	
	public Vector<String> getAllTipusSessions (){
		ConjuntTipusSessio cjtsessio = ConjuntTipusSessio.getInstance();
		Vector<String> result = new Vector<String>();
		Integer size = cjtsessio.size();
		
		for (int i = 0; i<size; ++i ) {
			if (cjtsessio.IsObject(i)) {
				result.add(cjtsessio.getId(i));
			}
		}
		
		return result;
	}
	
	public Vector<String> getAllTipusSessions (String assig){
		ConjuntTipusSessio cjtsessio = ConjuntTipusSessio.getInstance();
		Vector<String> result = new Vector<String>();
		
		Assignatura a = getAssigObjectByName(assig);
		
		if (a != null) {
			Vector<Integer> tsa = a.getGrups();
			
			Integer size = cjtsessio.size();
			
			for (int i = 0; i<size; ++i ) {
				if (cjtsessio.IsObject(i)) {
					if(tsa.contains(i)) {
						result.add(cjtsessio.GetObject(i).getId());
					}
				}
			}
		}
		
		return result;
	}
	
	public Vector<String> getAllAulesIds() {
		ConjuntAula cjaula = ConjuntAula.getInstance();
		Vector<String> result = new Vector<String>();
		Integer size = cjaula.size();
		
		for (int i = 0; i<size; ++i ) {
			if (cjaula.IsObject(i)) {
				result.add(cjaula.getId(i));
			}
		}
		
		return result;
	}
	
	/* ---------------------------------------- */ 
	
	/** 
	 * 
	 * @param num
	 * @return returns a JSON with the group that matches num
	 */
	public String getGroupById (Integer num) {
		ConjuntGrup cjgrup = ConjuntGrup.getInstance();
		String result = null;
		Integer size = cjgrup.size();
		for (int i = 0; i<size; ++i ) {
			if (cjgrup.IsObject(i)) {
				if (cjgrup.GetObject(i).getNum() == num) {
					result = consultarGrup(i);
				}
			}
		}
		
		return result;
	}
		
	/**
	 * 
	 * @return get ids of all groups in the system
	 */
	public Vector<String> getAllGroupIds (){
		ConjuntGrup cjgrup = ConjuntGrup.getInstance();
		Vector<String> result = new Vector<String>();
		Integer size = cjgrup.size();
		
		for (int i = 0; i<size; ++i ) {
			if (cjgrup.IsObject(i)) {
				result.add(cjgrup.getNum(i).toString());
			}
		}
		
		return result;
	}
	
	/* ---------------------------------------- */ 
	
	public Vector<String> getGroupAssigByAssig (Integer assignatura) {
		
		ConjuntGrupAssig cjgrupassig = ConjuntGrupAssig.getInstance();
		
		Vector<String> result = new Vector<String>();
		Integer size = cjgrupassig.size();
		
		for (int i = 0; i<size; ++i ) {
			if (cjgrupassig.IsObject(i)) {
				if (cjgrupassig.GetObject(i).getAssignatura() == assignatura) {
					result.addElement(consultarGrupAssig(i));
				}
			}
		}
		
		return result;
	}
	
	public Vector<String> getGroupIdByAssig(String assig) {
		Vector<String> vs = new Vector<String>();
		Assignatura a = ConjuntAssignatura.getInstance().GetObject(getAssigIdByName(assig));
		if(a != null) {
			for (Integer g : a.getGrups()) {
				vs.add(ConjuntGrup.getInstance().GetObject(((ConjuntGrupAssig.getInstance().getGrup(g)))).getNum().toString());
			}
		}
		return vs;
	}
	
	public Vector<String> getGroupAssigIdByAssig(String assig){
		Vector<String> vs = new Vector<String>();
		ConjuntGrupAssig cjassig = ConjuntGrupAssig.getInstance();
		
		for (int i = 0; i < cjassig.size(); ++i) {
			if (cjassig.IsObject(i)) {
				vs.add(cjassig.GetObject(i).getGrup().toString());
			}
		}
		return vs;
	}
	
	public Integer getGroupAssigIdByAssig(Integer assig, Integer grup){
		ConjuntGrupAssig cjassig = ConjuntGrupAssig.getInstance();
		
		for (int i = 0; i < cjassig.size(); ++i) {
			if (cjassig.IsObject(i)) {
				if (cjassig.GetObject(i).getAssignatura() == assig && cjassig.GetObject(i).getGrup() == grup) {
				return i;
				}
			}
		}
		return null;
	}
	
	public Vector<String> getAllGroupAssigIds (){
		ConjuntGrupAssig cjgrupassig = ConjuntGrupAssig.getInstance();
		Vector<String> result = new Vector<String>();
		Integer size = cjgrupassig.size();
		
		for (int i = 0; i<size; ++i ) {
			if (cjgrupassig.IsObject(i)) {
				result.add(cjgrupassig.getGrup(i).toString());
			}
		}
		return result;
	}
		
	public Boolean crearGrupAssignatura (String grup, String assignatura, Boolean mati) {	
		Integer g = getGrupIdById(grup);
		Integer a = ConjuntAssignatura.getInstance().size();
		
		crearGrupAssignatura(g, a, mati);
		return true;
	}
	
	public Boolean modificarGrupAssignatura (String grup, String assignatura, Boolean mati) {	
		Integer g = getGrupIdById(grup);
		Integer a = getAssigIdByName(assignatura);
		
		GrupAssig ga = getGrupAssig(g, a);
		if (ga != null) {
			eliminarGrupAssignatura(ConjuntGrupAssig.getInstance().GetId(ga));
			ga.setMati(mati);
			ConjuntGrupAssig.getInstance().NewId(ga);
			return true;
		}
		crearGrupAssignatura(g, a, mati);
		return false;
	}
	
	protected GrupAssig getGrupAssig(Integer g, Integer a) {
		ConjuntGrupAssig cgassig =  ConjuntGrupAssig.getInstance();
		for (int i = 0; i < cgassig.size(); ++i) {
			if (cgassig.IsObject(i)) {
				if (cgassig.getAssignatura(i) == a && cgassig.getGrup(i) == g) {
					return cgassig.GetObject(i);
				}
			}
		}
		return null;
	}
	
	/* Persist�ncia */
	
	/* Guardar dades */ 
	
	public void guardarAules () throws Exception {
		ConjuntAula caula = ConjuntAula.getInstance();
		String[] aules = new String[caula.size()];
		for (int i = 0; i < caula.size(); ++i) {
			if (caula.IsObject(i)) {
				if (caula.GetObject(i) != null) aules[i] = gson.toJson(caula.GetObject(i));
			}
		}
		
		persistencia.EscriureAula(aules);
	}
	
	public void guardarGrups () throws Exception {
		ConjuntGrup cgrup = ConjuntGrup.getInstance();
		String[] grup = new String[cgrup.size()];
		for (int i = 0; i < cgrup.size(); ++i) {
			if (cgrup.IsObject(i)) {
				grup[i] = gson.toJson(cgrup.GetObject(i));
			}
		}
		persistencia.EscriureGrup(grup);
	}
	
	public void guardarAssignatura () throws Exception {
		ConjuntAssignatura cassig = ConjuntAssignatura.getInstance();
		String[] assig = new String[cassig.size()];
		
		for (int i = 0; i < cassig.size(); ++i) {
			if (cassig.IsObject(i)) {
				if (cassig.GetObject(i) != null)  assig[i] = gson.toJson(cassig.GetObject(i));
			}
		}
		persistencia.EscriureAssignatures(assig);
	}	
	
	public void guardarGrupAssignatura () throws Exception {
		ConjuntGrupAssig cgassig = ConjuntGrupAssig.getInstance();
		String[] grupassig = new String[cgassig.size()];
		
		for (int i = 0; i < cgassig.size(); ++i) {
			if (cgassig.IsObject(i)) {
				grupassig[i] = gson.toJson(cgassig.GetObject(i));
			}
		}
		persistencia.EscriureGrupAssignatura(grupassig);
	}	
	
	public void guardarTipusAula () throws Exception {
		ConjuntTipusAules ctaula = ConjuntTipusAules.getInstance();
		String[] taula = new String[ctaula.size()];
		
		for (int i = 0; i < ctaula.size(); ++i) {
			if (ctaula.IsObject(i)) {
				taula[i] = gson.toJson(ctaula.GetObject(i));
			}
		}
		
		persistencia.EscriureTipusAula(taula);
	}	
	
	public void guardarTipusSessio () throws Exception {
		ConjuntTipusSessio ctsessio = ConjuntTipusSessio.getInstance();
		String[] tses = new String[ctsessio.size()];
		
		for (int i = 0; i < ctsessio.size(); ++i) {
			if (ctsessio.IsObject(i)) {
				tses[i] = gson.toJson(ctsessio.GetObject(i));
			}
		}
		persistencia.EscriureTipusSessio(tses);
	}
	
	public void guardarSessio () throws Exception {
		ConjuntSessio csessio = ConjuntSessio.getInstance();
		String[] ses = new String[csessio.size()];
		
		for (int i = 0; i < csessio.size(); ++i) {
			if (csessio.IsObject(i)) {
				ses[i] = gson.toJson(csessio.GetObject(i));
			}
		}
		persistencia.EscriureSessio(ses);
	}
	
	public void guardarPlaEstudis () throws Exception {
		ConjuntPlaEstudis cplaEstudis = ConjuntPlaEstudis.getInstance();
		String[] pla = new String[cplaEstudis.size()];
		
		for (int i = 0; i < cplaEstudis.size(); ++i) {
			if (cplaEstudis.IsObject(i)) {
				pla[i] = gson.toJson(cplaEstudis.GetObject(i));
			}
		}
		persistencia.EscriurePlaEstudis(pla);
	}
	

	public Vector<String> getAllCoreq(String name) {
		Vector<String> coreqs = new Vector<String>();
		
		Assignatura a = getAssigObjectByName(name);
		if (a != null) {
			Vector<Integer> c = a.getCorequisit();
			if (c != null) {
				for(Integer i : c) {
					String assig = ConjuntAssignatura.getInstance().GetObject(i).getNom();
					coreqs.add(assig);
				}
			}
		}

		return coreqs;
	}
	
	public void guardarHorari() throws Exception {
		String s = gson.toJson(getInfoHorari2().getKey());
		persistencia.EscriureHorari(s);
	}
	
	public void guardarDades () throws Exception {
		guardarAules();
		guardarGrups();
		guardarSessio();
		guardarTipusAula();
		guardarPlaEstudis();
		guardarTipusSessio();
		guardarAssignatura();
		guardarGrupAssignatura();
	}
	
	/* Importar dades */
	
	public void carregarAules() throws Exception {
		Vector<String> aules = persistencia.LlegirAules();
		ConjuntAula caula = ConjuntAula.getInstance();
		
		for (String s : aules) {
			if (!s.equals("null")) {
				Aula a = gson.fromJson(s, Aula.class);
				caula.NewId(a);
			}
		}
	}
	
	public void carregarAssignatures() throws Exception {
		Vector<String> assignatures = persistencia.LlegirAssignatures();
		ConjuntAssignatura cassig = ConjuntAssignatura.getInstance();
		
		for (String s : assignatures) {
			if (!s.equals("null")) {
			Assignatura a = gson.fromJson(s, Assignatura.class);
			cassig.NewId(a);
			}
		}
	}
	
	public void carregarGrups() throws Exception {
		Vector<String> grups = persistencia.LlegirGrups();
		ConjuntGrup cgrup = ConjuntGrup.getInstance();
		
		for (String s : grups) {
			if (!s.equals("null")) {
				Grup a = gson.fromJson(s, Grup.class);
				cgrup.NewId(a);
			}
		}
	}
	
	public void carregarSessio() throws Exception {
		Vector<String> sessions = persistencia.LlegirSessions();
		ConjuntSessio csessio = ConjuntSessio.getInstance();
		
		for (String s : sessions) {
			if (!s.equals("null")) {
				Sessio a = gson.fromJson(s, Sessio.class);
				csessio.NewId(a);
			}
		}
	}
	
	public void carregarGrupAssignatura() throws Exception {
		Vector<String> grupsAssignatures = persistencia.LlegirGrupsAssignatura();
		ConjuntGrupAssig cgrupassig = ConjuntGrupAssig.getInstance();
		
		for (String s : grupsAssignatures) {
			if (!s.equals("null")) {
				GrupAssig a = gson.fromJson(s, GrupAssig.class);
				cgrupassig.NewId(a);
			}
		}
	}
	
	public void carregarPlaEstudis() throws Exception {
		Vector<String> plansestudi = persistencia.LlegirPlansEstudi();
		ConjuntPlaEstudis cplaestudis = ConjuntPlaEstudis.getInstance();
		
		for (String s : plansestudi) {
			if (!s.equals("null")) {
				PlaEstudis a = gson.fromJson(s, PlaEstudis.class);
				cplaestudis.NewId(a);
			}
		}
	}
	
	public void carregarTipusSessio() throws Exception {
		Vector<String> tipussessions = persistencia.LlegirTipusSessio();
		ConjuntTipusSessio ctsessions = ConjuntTipusSessio.getInstance();
		
		for (String s : tipussessions) {
			if (!s.equals("null")) {
				TipusSessio a = gson.fromJson(s, TipusSessio.class);
				ctsessions.NewId(a);
			}
		}
	}
	
	public void carregarTipusAula() throws Exception {
		Vector<String> tipusaules = persistencia.LlegirTipusAula();
		ConjuntTipusAules ctaules = ConjuntTipusAules.getInstance();
		
		for (String s : tipusaules) {
			if (!s.equals("null")) {
				TipusAula a = gson.fromJson(s, TipusAula.class);
				ctaules.NewId(a);
			}
		}
	}
	
	
	public void carregarDades () throws Exception {
		carregarAules();
		carregarAssignatures();
		carregarGrups();
		carregarGrupAssignatura();
		carregarPlaEstudis();
		carregarSessio();
		carregarTipusAula();
		carregarTipusSessio();
	}
	
	/* Operacions comuns dels conjunts */
	
	public void reiniciarConjunts() { 
		ConjuntAula caula = ConjuntAula.getInstance();
		caula.reiniciarConjunt();
		
		ConjuntAssignatura cassig = ConjuntAssignatura.getInstance();
		cassig.reiniciarConjunt();
		
		ConjuntGrup cgrup = ConjuntGrup.getInstance();
		cgrup.reiniciarConjunt();
		
		ConjuntGrupAssig cgrupassig = ConjuntGrupAssig.getInstance();
		cgrupassig.reiniciarConjunt();
		
		ConjuntPlaEstudis cpla = ConjuntPlaEstudis.getInstance();
		cpla.reiniciarConjunt();
		
		ConjuntSessio csessio = ConjuntSessio.getInstance();
		csessio.reiniciarConjunt();
		
		ConjuntTipusAules ctaula = ConjuntTipusAules.getInstance();
		ctaula.reiniciarConjunt();
		
		ConjuntTipusSessio ctsessio = ConjuntTipusSessio.getInstance();
		ctsessio.reiniciarConjunt();
	}

	public Boolean getMati(Pair<String, String> grupAssig) {
		Integer assig = getAssigIdByName(grupAssig.getKey());
		Integer grup = getGrupIdbyGrup(grupAssig.getValue());
		
		Integer grupassig = null;
		
		if (assig != null && grup != null) grupassig = getGroupAssigIdByAssig(assig, grup);
		
		if (grupassig != null) {
			return ConjuntGrupAssig.getInstance().GetObject(grupassig).getMati();
		}
		
		return null;
	}

	public Boolean toogleRestriccio(String nom, Boolean estat) {
		CjtRestriccions cjres = CjtRestriccions.getCjtRestriccions();
		
		for (int i = 0; i < cjres.size(); ++i) {
			if (cjres.GetObject(i).getId().equals(nom)) {
				cjres.GetObject(i).setActivat(estat);
				return true;
			}
		}
		
		return false;
	}
	
	public Boolean getInfoRestriccio(String nom) {
		CjtRestriccions cjres = CjtRestriccions.getCjtRestriccions();
		
		for (int i = 0; i < cjres.size(); ++i) {
			if (cjres.GetObject(i).getId().equals(nom)) {
				return cjres.GetObject(i).getActivat();
			}
		}
		
		return null;
	}
	
	public Pair<String[][],Integer> getInfoHorari2() throws HorariExceptions{
		String[][] matriu = new String[12][5];
		
		horari = Horari.getInstance();
		Sessio[][][] h = horari.getHorari();
		
		for (int i = 0; i < h.length; ++i) {
			for (int j = 0; j < h[i].length; ++j) {
				String casella = "<html><body>";
				for (int k = 0; k < h[i][j].length; ++k) {
					Sessio s = h[i][j][k];
					if (s != null) {
						Integer g = ConjuntGrup.getInstance().GetObject(ConjuntGrupAssig.getInstance().GetObject(s.getGrup()).getGrup()).getNum();
						String assig = ConjuntAssignatura.getInstance().GetObject(ConjuntGrupAssig.getInstance().GetObject(s.getGrup()).getAssignatura()).getAcronim();
						casella += ConjuntAula.getInstance().GetObject(k).getId() + " " + assig + " " + g.toString() + " " + ConjuntTipusAules.getInstance().GetObject(ConjuntAula.getInstance().GetObject(k).getTipus()).getId() + "<br>";
					}
				}
				matriu[j][i] = casella;
			}
		}
		Pair<String[][], Integer> p = new Pair<String[][], Integer>(matriu, 1);
		return p;
	}
	
	public Pair<String[][],Integer> getInfoHorariAula(String aula) throws HorariExceptions{
		String[][] matriu = new String[12][5];
		
		horari = Horari.getInstance();
		Sessio[][][] h = horari.getHorari();
		
		for (int i = 0; i < h.length; ++i) {
			for (int j = 0; j < h[i].length; ++j) {
				String casella = "<html><body>";
				for (int k = 0; k < h[i][j].length; ++k) {
					Sessio s = h[i][j][k];
					if (s != null) {
						Integer g = ConjuntGrup.getInstance().GetObject(ConjuntGrupAssig.getInstance().GetObject(s.getGrup()).getGrup()).getNum();
						String assig = ConjuntAssignatura.getInstance().GetObject(ConjuntGrupAssig.getInstance().GetObject(s.getGrup()).getAssignatura()).getAcronim();
						
						if (ConjuntAula.getInstance().GetObject(k).getId().equals(aula)) {
							casella += aula + " " + assig + " " + g.toString() + " " + ConjuntTipusAules.getInstance().GetObject(ConjuntAula.getInstance().GetObject(k).getTipus()).getId() + "<br>";
						}
					}
				}
				matriu[j][i] = casella;
			}
		}
		Pair<String[][], Integer> p = new Pair<String[][], Integer>(matriu, 1);
		return p;
	}

	
	public Pair<String[][],Integer> getInfoHorariAssig(String assignatura) throws HorariExceptions{
		String[][] matriu = new String[12][5];
		
		Integer a = getAssigIdByName(assignatura);
		Integer maxSaltos = 1;
		horari = Horari.getInstance();
		Sessio[][][] h = horari.getHorari();
		
		
		for (int i = 0; i < h.length; ++i) {
			for (int j = 0; j < h[i].length; ++j) {
				String casella = "<html><body>";
				Integer auxSaltos = 0;
				for (int k = 0; k < h[i][j].length; ++k) {
					Sessio s = h[i][j][k];
					
					if (s != null) {
						Integer g = ConjuntGrup.getInstance().GetObject(ConjuntGrupAssig.getInstance().GetObject(s.getGrup()).getGrup()).getNum();
						String assig = ConjuntAssignatura.getInstance().GetObject(ConjuntGrupAssig.getInstance().GetObject(s.getGrup()).getAssignatura()).getAcronim();
						String aula = ConjuntAula.getInstance().GetObject(k).getId();
						if (ConjuntAssignatura.getInstance().GetObject(ConjuntGrupAssig.getInstance().GetObject(s.getGrup()).getAssignatura()).getNom().equals(assignatura)) {
							casella += aula + " " + assig + " " + g.toString() + " " + ConjuntTipusAules.getInstance().GetObject(ConjuntAula.getInstance().GetObject(k).getTipus()).getId() + "<br>";
							++auxSaltos;
						}
					}
				}
				if (auxSaltos > maxSaltos) maxSaltos = auxSaltos;
				matriu[j][i] = casella;
			}
		}
		
		Pair<String[][], Integer> p = new Pair<String[][], Integer>(matriu, maxSaltos);
		return p;
	}
	
	public Pair<String[][],Integer> getInfoHorariAssigAula(String assignatura, String aula) throws HorariExceptions{
		String[][] matriu = new String[12][5];

		horari = Horari.getInstance();
		Sessio[][][] h = horari.getHorari();
		
		for (int i = 0; i < h.length; ++i) {
			for (int j = 0; j < h[i].length; ++j) {
				String casella = "<html><body>";
				for (int k = 0; k < h[i][j].length; ++k) {
					Sessio s = h[i][j][k];
					if (s != null) {
						Integer g = ConjuntGrup.getInstance().GetObject(ConjuntGrupAssig.getInstance().GetObject(s.getGrup()).getGrup()).getNum();
						String assig = ConjuntAssignatura.getInstance().GetObject(ConjuntGrupAssig.getInstance().GetObject(s.getGrup()).getAssignatura()).getAcronim();
						String aula2 = ConjuntAula.getInstance().GetObject(k).getId();
						if (ConjuntAssignatura.getInstance().GetObject(ConjuntGrupAssig.getInstance().GetObject(s.getGrup()).getAssignatura()).getNom().equals(assignatura) 
								&& aula.equals(aula2)) {
							casella += aula + " " + assig + " " + g.toString() + " " + ConjuntTipusAules.getInstance().GetObject(ConjuntAula.getInstance().GetObject(k).getTipus()).getId()  + "<br>";
						}
					}
				}
				matriu[j][i] = casella;
			}
		}
		Pair<String[][], Integer> p = new Pair<String[][], Integer>(matriu, 1);
		return p;
	}

	public String getNomTipusAula(Integer taula) {
		return ConjuntTipusAules.getInstance().GetObject(taula).getId();
	}
	
	public Boolean modificarHorari (Integer oday, Integer ohour, String oaula, Integer dday, Integer dhour, String daula) throws HorariExceptions {
		horari = Horari.getInstance();
		return horari.modificarHorari(oday, ohour, getAulaIdByName(oaula), dday, dhour, getAulaIdByName(daula));
	}
	
	


}
