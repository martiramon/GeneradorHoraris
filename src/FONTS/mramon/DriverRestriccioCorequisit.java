package FONTS.mramon;

import java.lang.Exception;
import java.util.Vector;

import FONTS.esaus.inout;
import FONTS.esaus.ConjuntAssignatura;
import FONTS.esaus.ConjuntAula;
import FONTS.esaus.ConjuntGrup;
import FONTS.esaus.ConjuntGrupAssig;
import FONTS.esaus.ConjuntTipusAules;
import FONTS.esaus.ConjuntTipusSessio;
import FONTS.esaus.Aula;
import FONTS.agallofre.CtrlDomini;
import FONTS.esaus.Assignatura;
import FONTS.esaus.Grup;
import FONTS.esaus.GrupAssig;
import FONTS.esaus.Sessio;
import FONTS.esaus.TipusAula;
import FONTS.esaus.TipusSessio;

class DriverRestriccioCorequisit {
	private static inout io;
	private static RestriccioCorequisit r;
	
	public static void testConstructor() throws Exception {
		io.write("Introdueix Id (String): \n");
		String id = io.readname();
		io.write("Introdueix Tipus (unaria, binaria o global): \n");
		io.write("Per a comprovar la restriccio s'ha d'assignar tipus binaria \n");
		TipusRes tipus = io.readTipusRes();
		io.write("Introdueix Activat (Boolean): \n");
		Boolean activat = io.readboolean();
		io.write("Creant Restriccio amb Id " + id + ", TipusRestriccio " + String.valueOf(tipus) + " i Activat " + activat + "\n");
		r = new RestriccioCorequisit(id,tipus,activat);
		io.write("La Restriccio creada te: \n Id = " + r.getId() + "\n TipusRestriccio = " + (r.getTipus().name()) + "\n Activat = " + String.valueOf(r.getActivat()));
	}
	
	public static void testConstructor2() throws Exception {
		io.write("Introdueix Id (String): \n");
		String id = io.readname();
		io.write("Introdueix Tipus (unaria, binaria o global): \n");
		io.write("Per a comprovar la restriccio s'ha d'assignar tipus binaria \n");
		TipusRes tipus = io.readTipusRes();
		io.write("Introdueix Activat (Boolean): \n");
		r = new RestriccioCorequisit(id,tipus);
		io.write("La Restriccio creada te: \n Id = " + r.getId() + "\n TipusRestriccio = " + (r.getTipus().name()) + "\n");
	}
	public static void testgetId() throws Exception {
		io.write("ID = " + r.getId());
	}
	public static void testgetTipus() throws Exception {
		io.write("ID = " + (r.getTipus()).name());		
	}
	public static void testgetActivat() throws Exception {
		if (r.getActivat() == null) io.write("La restriccio no té el camp activat definit");
		else io.write("Activat = " + String.valueOf(r.getActivat()));
	}
	public static void testsetId() throws Exception {
		io.write("Introdueix Id (String): \n");
		String id = io.readname();
		r.setId(id);
	}
	public static void testsetTipus() throws Exception {
		io.write("Introdueix Tipus (String: unaria, binaria, global): \n");
		TipusRes tipus = io.readTipusRes();
		r.setTipus(tipus);
	}
	public static void testsetActivat() throws Exception {
		io.write("Introdueix Activat (Boolean): \n");
		Boolean activat = io.readboolean();
		r.setActivat(activat);
	}	
	public static void testActivatON() throws Exception {
		io.write("S'activa la restricció: \n");
		r.activatON();
	}	
	public static void testActivatOFF() throws Exception {
		io.write("Es desactiva la restricció: \n");
		r.activatOFF();
	}
	
	public static void testComprovar1() throws Exception {
		if (r.getTipus() == TipusRes.binaria) {
			CtrlDomini ctr = new CtrlDomini();
			ctr.reiniciarConjunts();
			io.write("Tenim una assignatura FM creada...\n");
			io.write("Tenim un grup 10 creat, de capacitat 30 persones, i assignat a FM...\n" );
			io.write("Tenim una aula A01 creada, de capacitat 30 persones, amb tipus aula TEO...\n");
			io.write("Tenim una sessio de TEO creada, per dilluns de 8h a 9h, pel grup 10 de FM a l'aula A01...\n");
			io.write("Tenim una altre aula creada A02, de capacitat 30 persones, amb tipus aula TEO...\n");
			io.write("Tenim una altre assignatura creada, M1, que es corequisit de FM... \n");
			Aula au = new Aula ("A01", 30);
			ConjuntAula cjAU = ConjuntAula.getInstance();
			Integer idcjAU = cjAU.NewId(au);
			Vector<Integer> aules = new Vector<Integer>();
			aules.add(idcjAU);
			Aula au2 = new Aula ("A02", 30);
			Integer idcjAU2 = cjAU.NewId(au2);
			aules.add(idcjAU);
			TipusAula ta = new TipusAula("TEO");
			ta.setAules(aules);
			ConjuntTipusAules cjTA = ConjuntTipusAules.getInstance();
			Integer idcjTA= cjTA.NewId(ta);
			au.setTipus(idcjTA);			
			TipusSessio ts = new TipusSessio("TEO", idcjTA, 2);
			ConjuntTipusSessio cjTS = ConjuntTipusSessio.getInstance();
			Integer idcjTS = cjTS.NewId(ts);
			Vector<Integer> tsessions = new Vector<Integer>(); 
			tsessions.add(idcjTS);
			Assignatura a = new Assignatura ("FM", "Fonaments Matematics", 1, tsessions);
			ConjuntAssignatura cjA = ConjuntAssignatura.getInstance();
			Integer idcjA = cjA.NewId(a);
			Assignatura a2 = new Assignatura ("M1", "Matematiques1", 2, tsessions);
			Integer idcjA2 = cjA.NewId(a2);
			Vector<Integer> corequisitsa1 = new Vector<Integer>();
			corequisitsa1.add(idcjA2);
			Vector<Integer> corequisitsa2 = new Vector<Integer>();
			corequisitsa2.add(idcjA);
			a.setCorequisit(corequisitsa1);
			a2.setCorequisit(corequisitsa2);
			Grup g = new Grup (10,30);
			ConjuntGrup cjG = ConjuntGrup.getInstance();
			Integer idcjG = cjG.NewId(g);
			GrupAssig ga = new GrupAssig (idcjG, idcjA, true);
			ConjuntGrupAssig cjGA = ConjuntGrupAssig.getInstance();
			Integer idcjGA = cjGA.NewId(ga);
			GrupAssig ga2 = new GrupAssig (idcjG, idcjA2, true);
			Integer idcjGA2 = cjGA.NewId(ga2);
			Sessio s = new Sessio("sessio1", idcjAU, idcjGA, idcjTS);
			Vector<Sessio> vSessions = new Vector<Sessio>();
			vSessions.add(s);
			io.write("Creem sessio pel grup 10 de M1, de 8h a 9h de dilluns a l'aula A02...\n");
		    io.write("Comprovant restricció...\n");		    
			Sessio s2 = new Sessio("sessio2", idcjAU2, idcjGA2, idcjTS);
			vSessions.add(s2);
			Sessio[] sessions = vSessions.toArray(new Sessio[vSessions.size()]);
			Boolean correcte = r.comprovar(s2, sessions, 0, 12);
			if (correcte) io.write("Sessions correctes \n");
			else io.write("Sessions amb solapament incorretes: assignatures amb corequisits \n");
		}
		else io.write("La restriccio ha de ser binaria. Per provar-la canvia el tipus a traves d'un set\n ");
	}
	
	public static void testComprovar2() throws Exception {
		if (r.getTipus() == TipusRes.binaria) {
			CtrlDomini ctr = new CtrlDomini();
			ctr.reiniciarConjunts();
			io.write("Tenim una assignatura FM creada...\n");
			io.write("Tenim un grup 10 creat, de capacitat 30 persones, i assignat a FM...\n" );
			io.write("Tenim una aula A01 creada, de capacitat 30 persones, amb tipus aula TEO...\n");
			io.write("Tenim una sessio de TEO creada, per dilluns de 8h a 9h, pel grup 10 de FM a l'aula A01...\n");
			io.write("Tenim una altre aula creada A02, de capacitat 30 persones, amb tipus aula TEO...\n");
			io.write("Tenim una altre assignatura creada, M1, que es NO corequisit de FM... \n");
			Aula au = new Aula ("A01", 30);
			ConjuntAula cjAU = ConjuntAula.getInstance();
			Integer idcjAU = cjAU.NewId(au);
			Vector<Integer> aules = new Vector<Integer>();
			aules.add(idcjAU);
			Aula au2 = new Aula ("A02", 30);
			Integer idcjAU2 = cjAU.NewId(au2);
			aules.add(idcjAU);
			TipusAula ta = new TipusAula("TEO");
			ta.setAules(aules);
			ConjuntTipusAules cjTA = ConjuntTipusAules.getInstance();
			Integer idcjTA= cjTA.NewId(ta);
			au.setTipus(idcjTA);			
			TipusSessio ts = new TipusSessio("TEO", idcjTA, 2);
			ConjuntTipusSessio cjTS = ConjuntTipusSessio.getInstance();
			Integer idcjTS = cjTS.NewId(ts);
			Vector<Integer> tsessions = new Vector<Integer>(); 
			tsessions.add(idcjTS);
			Assignatura a = new Assignatura ("FM", "Fonaments Matematics", 1, tsessions);
			ConjuntAssignatura cjA = ConjuntAssignatura.getInstance();
			Integer idcjA = cjA.NewId(a);
			Assignatura a2 = new Assignatura ("M1", "Matematiques1", 2, tsessions);
			Integer idcjA2 = cjA.NewId(a2);	
			Grup g = new Grup (10,30);
			ConjuntGrup cjG = ConjuntGrup.getInstance();
			Integer idcjG = cjG.NewId(g);
			GrupAssig ga = new GrupAssig (idcjG, idcjA, true);
			ConjuntGrupAssig cjGA = ConjuntGrupAssig.getInstance();
			Integer idcjGA = cjGA.NewId(ga);	
			GrupAssig ga2 = new GrupAssig (idcjG, idcjA2, true);
			Integer idcjGA2 = cjGA.NewId(ga2);					
			Sessio s = new Sessio("sessio1", idcjAU, idcjGA, idcjTS);
			Vector<Sessio> vSessions = new Vector<Sessio>();
			vSessions.add(s);
			io.write("Creem sessio pel grup 10 de M1, de 8h a 9h de dilluns a l'aula A02...\n");
		    io.write("Comprovant restricció...\n");		    
			Sessio s2 = new Sessio("sessio2", idcjAU2, idcjGA2, idcjTS);
			vSessions.add(s2);
			Sessio[] sessions = vSessions.toArray(new Sessio[vSessions.size()]);
			Boolean correcte = r.comprovar(s2, sessions, 0, 12);
			if (correcte) io.write("Sessions correctes \n");
			else io.write("Sessions amb solapament incorretes: assignatures amb corequisits \n");
		}
		else io.write("La restriccio ha de ser binaria. Per provar-la canvia el tipus a traves d'un set\n ");
	}
	public static void main(String[] args) throws Exception {
		io = new inout();
		int s = -1;
		io.write("Opcions: \n 1.Constructor \n 2.Getters \n 3.Setters \n 4.Metode \n 5.Exit \n");
		while (s != 6) {
			if (s == 1) {
				io.write("Opcions: \n 1.Constructora 1 \n 2.Constructora 2 \n 3.Endarrera \n");
				s = io.readint();
				if (s == 1) testConstructor();
				if (s == 2) testConstructor2();
				io.write("\n");
				io.write("Opcions: \n 1.Constructor \n 2.Getters \n 3.Setters \n 4.Metode \n 5.Exit \n");
			}
			else if (s == 2) {
				if (r == null) io.write("Restriccio no creada");
				else {
					io.write("Opcions Getters: \n 1.Id \n 2.Tipus \n 3.Activat \n");
					s = io.readint();
					if (s == 1) testgetId();
					if (s == 2) testgetTipus();
					if (s == 3) testgetActivat();
				}
				io.write("\n");
				io.write("Opcions: \n 1.Constructor \n 2.Getters \n 3.Setters \n 4.Metode \n 5.Exit \n");
			}
			else if (s == 3) {
				if (r == null) io.write("S'ha de crear una restriccio abans de donar-li valor"); 
				else {
					io.write("Opcions Setters: \n 1.Id \n 2.Tipus \n 3.Activat \n 4.ActivatON \n 5.ActivatOFF \n 6.Endarrera \n");
					s = io.readint();
					if (s == 1) testsetId();
					if (s == 2) testsetTipus();
					if (s == 3) testsetActivat();
					if (s == 4) testActivatON();
					if (s == 5) testActivatOFF();
				}
				io.write("\n");
				io.write("Opcions: \n 1.Constructor \n 2.Getters \n 3.Setters \n 4.Metode \n 5.Exit \n");
			}
			else if (s == 4) {
				if (r == null) io.write("S'ha de crear una restriccio abans de comprovar"); 
				else {
					io.write("Opcions Setters: \n 1.Comprovar1 (Incorrecte) \n 2.Comprovar2 (Correcte) \n");
					s = io.readint();
					if (s == 1) testComprovar1();
					if (s == 2) testComprovar2();
				}
				io.write("\n");
				io.write("Opcions: \n 1.Constructor \n 2.Getters \n 3.Setters \n 4.Metode \n 5.Exit \n");
			}
			else if (s == 5) {
				System.exit(0);
			}
			s = io.readint();
		}
		io.write("Exit Drive \n");
	}
}


