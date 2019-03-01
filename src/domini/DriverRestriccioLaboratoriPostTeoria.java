package domini;

import java.lang.Exception;
import java.util.Vector;

class DriverRestriccioLaboratoriPostTeoria {
	private static inout io;
	private static RestriccioLaboratoriPostTeoria r;
	
	public static void testConstructor() throws Exception {
		io.write("Introdueix Id (String): \n");
		String id = io.readname();
		io.write("Introdueix Tipus (unaria, binaria o global): \n");
		io.write("Per a comprovar la restriccio s'ha d'assignar tipus global \n");
		TipusRes tipus = io.readTipusRes();
		io.write("Introdueix Activat (Boolean): \n");
		Boolean activat = io.readboolean();
		io.write("Creant Restriccio amb Id " + id + ", TipusRestriccio " + String.valueOf(tipus) + " i Activat " + activat + "\n");
		r = new RestriccioLaboratoriPostTeoria(id,tipus,activat);
		io.write("La Restriccio creada te: \n Id = " + r.getId() + "\n TipusRestriccio = " + (r.getTipus().name()) + "\n Activat = " + String.valueOf(r.getActivat()));
	}
	
	public static void testConstructor2() throws Exception {
		io.write("Introdueix Id (String): \n");
		String id = io.readname();
		io.write("Introdueix Tipus (unaria, binaria o global): \n");
		io.write("Per a comprovar la restriccio s'ha d'assignar tipus global \n");
		TipusRes tipus = io.readTipusRes();
		io.write("Introdueix Activat (Boolean): \n");
		r = new RestriccioLaboratoriPostTeoria(id,tipus);
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
	
	public static void testComprovar1(Sessio s, Sessio[][][] horari) throws Exception {
		if (r.getTipus() == TipusRes.global) {
			io.write("Tenim una assignatura FM creada de nivell 1 amb session de TEO i de LAB...\n");
			io.write("Tenim tipus de sessio creada LAB i tipus de sessio creada TEO \n");
			io.write("Tenim un grup 10 creat, de capacitat 30 persones, i assignat a FM...\n" );
			io.write("Tenim una aula A01 creada, de capacitat 30 persones, amb tipus aula LAB...\n");
			io.write("Creem sessio de LAB pel grup 10 de FM, de 8h a 9h de dilluns a l'aula A01...\n");
		    io.write("Comprovant restricci�...\n");	
			Boolean correcte = r.comprovar(s, horari, 0, 0);
			if (correcte) io.write("Sessions correctes \n");
			else io.write("Sessions incorrectes: sessi� de LAB es realitza abans de sessio de TEO \n");
		}
		else io.write("La restriccio ha de ser global. Per provar-la canvia el tipus a traves d'un set\n ");
	}
	
	public static void testComprovar2(Sessio s, Sessio[][][] horari) throws Exception {
		if (r.getTipus() == TipusRes.global) {
			io.write("Tenim una assignatura FM creada de nivell 1 amb sessions de TEO i de LAB...\n");
			io.write("Tenim tipus de sessio creada LAB i tipus de sessio creada TEO \n");
			io.write("Tenim un grup 10 creat, de capacitat 30 persones, i assignat a FM...\n" );
			io.write("Tenim una aula A01 creada, de capacitat 30 persones, amb tipus aula LAB...\n");
			io.write("Tenim una aula A02 creada, de capacitat 30 persones, amb tipus aula TEO...\n");
			io.write("Tenim una sessio de TEO pel grup 10 de FM, de 8h a 9h de dilluns a l'aula A02...\n");
			io.write("Creem una sessio de LAB pel grup 10 de FM, de 9h a 10h de dilluns a l'aula A01...\n");
		    io.write("Comprovant restricci�...\n");	
			Boolean correcte = r.comprovar(s, horari, 0, 1);
			if (correcte) io.write("Sessions correctes \n");
			else io.write("Sessions incorrectes: sessi� de LAB es realitza abans de sessio de TEO \n");
		}
		else io.write("La restriccio ha de ser global. Per provar-la canvia el tipus a traves d'un set\n ");
	}
	
	public static void testComprovar3(Sessio s, Sessio[][][] horari) throws Exception {
		if (r.getTipus() == TipusRes.global) {
			io.write("Tenim una assignatura IC creada de nivell 1 amb sessions de LAB...\n");
			io.write("Tenim tipus de sessio creada LAB i tipus de sessio creada TEO \n");
			io.write("Tenim un grup 10 creat, de capacitat 30 persones, i assignat a IC...\n" );
			io.write("Tenim una aula A01 creada, de capacitat 30 persones, amb tipus aula LAB...\n");
			io.write("Creem sessio de LAB pel grup 10 de IC, de 8h a 9h de dilluns a l'aula A01...\n");
		    io.write("Comprovant restricci�...\n");	
			Boolean correcte = r.comprovar(s, horari, 0, 0);
			if (correcte) io.write("Sessions correctes \n");
			else io.write("Sessions incorrectes: sessi� de LAB es realitza abans de sessio de TEO \n");
		}
		else io.write("La restriccio ha de ser global. Per provar-la canvia el tipus a traves d'un set\n ");
	}
	public static void main(String[] args) throws Exception {
		io = new inout();
		int s = -1;
		Aula au = new Aula ("A01", 30); 
		ConjuntAula cjAU = ConjuntAula.getInstance();
		Integer idcjAU = cjAU.NewId(au); 
		Vector<Integer> aulesLAB = new Vector<Integer>(); 
		aulesLAB.add(idcjAU);
		Aula au2 = new Aula ("A02", 30); 
		Integer idcjAU2 = cjAU.NewId(au2);
		Vector<Integer> aulesTEO = new Vector<Integer>(); 
		aulesTEO.add(idcjAU2); 	
		TipusAula taT = new TipusAula("TEO"); 
		taT.setAules(aulesTEO);
		ConjuntTipusAules cjTA = ConjuntTipusAules.getInstance();
		Integer idcjTAT= cjTA.NewId(taT); 
		au2.setTipus(idcjTAT);
		TipusAula taL = new TipusAula("LAB"); 
		taL.setAules(aulesLAB); 
		Integer idcjTAL= cjTA.NewId(taL); 
		au.setTipus(idcjTAL); 
		TipusSessio tsT = new TipusSessio("TEO", idcjTAT, 1); 
		ConjuntTipusSessio cjTS = ConjuntTipusSessio.getInstance();
		Integer idcjTST = cjTS.NewId(tsT); 
		Vector<Integer> tsessions = new Vector<Integer>();  
		tsessions.add(idcjTST);
		TipusSessio tsL = new TipusSessio("LAB", idcjTAL, 1); 
		Integer idcjTSL = cjTS.NewId(tsL); 
		tsessions.add(idcjTSL); 
		Vector<Integer> tsessions2 = new Vector<Integer>();
		tsessions2.add(idcjTSL);
		Assignatura a = new Assignatura ("FM", "Fonaments Matematics", 1, tsessions); 
		ConjuntAssignatura cjA = ConjuntAssignatura.getInstance();
		Integer idcjA = cjA.NewId(a);
		Assignatura a2 = new Assignatura ("IC", "Introduccio als Computadors", 1, tsessions2); 
		Integer idcjA2 = cjA.NewId(a2); 
		Grup g = new Grup (10,30); 
		ConjuntGrup cjG = ConjuntGrup.getInstance();
		Integer idcjG = cjG.NewId(g); 
		GrupAssig ga = new GrupAssig (idcjG, idcjA, true); 
		ConjuntGrupAssig cjGA = ConjuntGrupAssig.getInstance();
		Integer idcjGA = cjGA.NewId(ga);
		GrupAssig ga2 = new GrupAssig (idcjG, idcjA2, true); 
		Integer idcjGA2 = cjGA.NewId(ga2);
		Sessio ss = new Sessio("sessio1T", idcjAU, idcjGA, idcjTST); 
		Vector<Sessio> vSessions = new Vector<Sessio>(); 
		vSessions.add(ss);		    
		Sessio s2 = new Sessio("sessio1L", idcjAU, idcjGA, idcjTSL); 
		vSessions.add(s2); 
		Sessio s3 = new Sessio("sessio2L", idcjAU, idcjGA2, idcjTSL);
		Vector<Sessio> vSessions2 = new Vector<Sessio>(); 
		vSessions2.add(s3);
		Sessio[][][] horari1 = new Sessio[5][12][vSessions.size()];
		Sessio[][][] horari2 = new Sessio[5][12][vSessions.size()];
		Sessio[] sessions = vSessions.toArray(new Sessio[vSessions.size()]);
		horari2[0][0][0] = sessions[1];
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
					io.write("Opcions Setters: \n 1.Comprovar1 (Incorrecte) \n 2.Comprovar2 (Correcte) \n 3.Comprovar3 (correcte) \n");
					s = io.readint();
					if (s == 1) testComprovar1(s2, horari1);
					if (s == 2) testComprovar2(ss, horari2);
					if (s == 3) testComprovar3(s3, horari1);
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