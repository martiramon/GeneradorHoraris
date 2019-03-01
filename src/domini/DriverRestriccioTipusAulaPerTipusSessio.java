package domini;

import java.lang.Exception;
import java.util.Vector;

class DriverRestriccioTipusAulaPerTipusSessio {
	private static inout io;
	private static RestriccioTipusAulaPerTipusSessio r;
	
	public static void testConstructor() throws Exception {
		io.write("Introdueix Id (String): \n");
		String id = io.readname();
		io.write("Introdueix Tipus (unaria, binaria o global): \n");
		io.write("Per a comprovar la restriccio s'ha d'assignar tipus unaria \n");
		TipusRes tipus = io.readTipusRes();
		io.write("Introdueix Activat (Boolean): \n");
		Boolean activat = io.readboolean();
		io.write("Creant Restriccio amb Id " + id + ", TipusRestriccio " + String.valueOf(tipus) + " i Activat " + activat + "\n");
		r = new RestriccioTipusAulaPerTipusSessio(id,tipus,activat);
		io.write("La Restriccio creada te: \n Id = " + r.getId() + "\n TipusRestriccio = " + (r.getTipus().name()) + "\n Activat = " + String.valueOf(r.getActivat()));
	}
	
	public static void testConstructor2() throws Exception {
		io.write("Introdueix Id (String): \n");
		String id = io.readname();
		io.write("Introdueix Tipus (unaria, binaria o global): \n");
		io.write("Per a comprovar la restriccio s'ha d'assignar tipus unaria \n");
		TipusRes tipus = io.readTipusRes();
		io.write("Introdueix Activat (Boolean): \n");
		r = new RestriccioTipusAulaPerTipusSessio(id,tipus);
		io.write("La Restriccio creada te: \n Id = " + r.getId() + "\n TipusRestriccio = " + (r.getTipus().name()) + "\n");
	}
	public static void testgetId() throws Exception {
		io.write("ID = " + r.getId());
	}
	public static void testgetTipus() throws Exception {
		io.write("ID = " + (r.getTipus()).name());		
	}
	public static void testgetActivat() throws Exception {
		if (r.getActivat() == null) io.write("La restriccio no t� el camp activat definit");
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
		io.write("S'activa la restricci�: \n");
		r.activatON();
	}	
	public static void testActivatOFF() throws Exception {
		io.write("Es desactiva la restricci�: \n");
		r.activatOFF();
	}
	
	public static void testComprovar1() throws Exception {
		if (r.getTipus() == TipusRes.unaria) {
			io.write("Creem una assignatura FM...\n");
			io.write("Creem un grup 10, de capacitat 30 persones, i l'assignem a FM...\n" );
			io.write("Creem una aula A01, de capacitat 30 persones, amb tipus aula LAB...\n");
			io.write("Creem una sessio de TEO per dilluns de 8h a 9h, pel grup 10 de FM a l'aula A01...\n");			
			Aula au = new Aula ("A01", 30);
			ConjuntAula cjAU = ConjuntAula.getInstance();
			Integer idcjAU = cjAU.NewId(au);
			Vector<Integer> aules = new Vector<Integer>();
			aules.add(idcjAU);
			TipusAula ta = new TipusAula("LAB");
			ta.setAules(aules);
			ConjuntTipusAules cjTA = ConjuntTipusAules.getInstance();
			Integer idcjTA= cjTA.NewId(ta);
			au.setTipus(idcjTA);
			TipusAula ta2 = new TipusAula("TEO");
			Integer idcjTA2 = cjTA.NewId(ta2);
			TipusSessio ts = new TipusSessio("TEO", idcjTA2, 2);
			ConjuntTipusSessio cjTS = ConjuntTipusSessio.getInstance();
			Integer idcjTS = cjTS.NewId(ts);
			Vector<Integer> tsessions = new Vector<Integer>(); 
			tsessions.add(idcjTS);
			Assignatura a = new Assignatura ("FM", "Fonaments Matematics", 1, tsessions);
			ConjuntAssignatura cjA = ConjuntAssignatura.getInstance();
			Integer idcjA = cjA.NewId(a);
			Grup g = new Grup (10,30);
			ConjuntGrup cjG = ConjuntGrup.getInstance();
			Integer idcjG = cjG.NewId(g);
			GrupAssig ga = new GrupAssig (idcjG, idcjA, true);
			ConjuntGrupAssig cjGA = ConjuntGrupAssig.getInstance();
			Integer idcjGA = cjGA.NewId(ga);					
			Sessio s = new Sessio("sessio1", idcjAU, idcjGA, idcjTS);
		    io.write("Comprovant restricci�...\n");
			Boolean correcte = r.comprovar(s, 0, 12);
			if (correcte) io.write("Tipus d'aula concorda amb tipus sessio \n");
			else io.write("Tipus d'aula NO concorda amb tipus sessio \n");
		}
		else io.write("La restriccio ha de ser unaria. Per provar-la canvia el tipus a traves d'un set\n ");
	}
	
	public static void testComprovar2() throws Exception {
		if (r.getTipus() == TipusRes.unaria) {
			io.write("Creem una assignatura FM...\n");
			io.write("Creem un grup 10, de capacitat 30 persones, i l'assignem a FM...\n" );
			io.write("Creem una aula A01, de capacitat 30 persones, amb tipus aula TEO...\n");
			io.write("Creem una sessio de TEO per dilluns de 8h a 9h, pel grup 10 de FM a l'aula A01...\n");			
			Aula au = new Aula ("A01", 30); //creo aula
			ConjuntAula cjAU = ConjuntAula.getInstance(); //creo cjAules
			Integer idcjAU = cjAU.NewId(au); //obtinc id de l'aula dins del cjAUles
			Vector<Integer> aules = new Vector<Integer>(); //creo vector aules
			aules.add(idcjAU); //afegeixo idcjAU al vector aules
			TipusAula ta = new TipusAula("TEO"); //creo tipusAula TEO
			ta.setAules(aules); //afegeixo aules a tipus Aula TEO
			ConjuntTipusAules cjTA = ConjuntTipusAules.getInstance(); //creo conjunt tipusAules
			Integer idcjTA= cjTA.NewId(ta); //obtinc id del tipusAula dins del cjTAula
			au.setTipus(idcjTA); //assigno tipus idcjTA a tipus de l'aula
			TipusSessio ts = new TipusSessio("TEO", idcjTA, 2); //creo tipus sessio de TEO amb tipus Aula idcjTA
			ConjuntTipusSessio cjTS = ConjuntTipusSessio.getInstance(); //creo conjunt tipus sessio
			Integer idcjTS = cjTS.NewId(ts); //obtinc id del tipus sessio TEO dins del cjTS
			Vector<Integer> tsessions = new Vector<Integer>();  //creo tsessions
			tsessions.add(idcjTS); //afegeixo id del tipus sessio Teoria del cjTS a tsessions
			Assignatura a = new Assignatura ("FM", "Fonaments Matematics", 1, tsessions); //creo assignatura amb sessions tsessions
			ConjuntAssignatura cjA = ConjuntAssignatura.getInstance(); //creo conjunt assig
			Integer idcjA = cjA.NewId(a); //obtinc id assig dins del cjA
			Grup g = new Grup (10,30); //creo grup
			ConjuntGrup cjG = ConjuntGrup.getInstance(); //creo conjunt grups
			Integer idcjG = cjG.NewId(g); //obtinc id grup dins del conjunt grups
			GrupAssig ga = new GrupAssig (idcjG, idcjA, true); //creo Grup assig per assig idA, grup idcjg
			ConjuntGrupAssig cjGA = ConjuntGrupAssig.getInstance(); //creo conjunt grup assig
			Integer idcjGA = cjGA.NewId(ga); //obtinc id del grupassig dins del conjunt cjGA
			Sessio s = new Sessio("sessio1", idcjAU, idcjGA, idcjTS); //creo sessio per l'aula idcjAU, pel grupassig idcjGA, pel tipus de sessio idcjTS
		    io.write("Comprovant restricci�...\n");
			Boolean correcte = r.comprovar(s, 0, 12);
			if (correcte) io.write("Tipus d'aula concorda amb tipus sessio \n");
			else io.write("Tipus d'aula NO concorda amb tipus sessio \n");
		}
		else io.write("La restriccio ha de ser unaria. Per provar-la canvia el tipus a traves d'un set\n ");
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