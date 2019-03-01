package domini;

import java.lang.Exception;
import java.util.Vector;

class DriverRestriccioNivell {
	private static inout io;
	private static RestriccioNivell r;
	
	public static void testConstructor() throws Exception {
		io.write("Introdueix Id (String): \n");
		String id = io.readname();
		io.write("Introdueix Tipus (unaria, binaria o global): \n");
		io.write("Per a comprovar la restriccio s'ha d'assignar tipus binaria \n");
		TipusRes tipus = io.readTipusRes();
		io.write("Introdueix Activat (Boolean): \n");
		Boolean activat = io.readboolean();
		io.write("Creant Restriccio amb Id " + id + ", TipusRestriccio " + String.valueOf(tipus) + " i Activat " + activat + "\n");
		r = new RestriccioNivell(id,tipus,activat);
		io.write("La Restriccio creada te: \n Id = " + r.getId() + "\n TipusRestriccio = " + (r.getTipus().name()) + "\n Activat = " + String.valueOf(r.getActivat()));
	}
	
	public static void testConstructor2() throws Exception {
		io.write("Introdueix Id (String): \n");
		String id = io.readname();
		io.write("Introdueix Tipus (unaria, binaria o global): \n");
		io.write("Per a comprovar la restriccio s'ha d'assignar tipus binaria \n");
		TipusRes tipus = io.readTipusRes();
		io.write("Introdueix Activat (Boolean): \n");
		r = new RestriccioNivell(id,tipus);
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
	
	public static void testComprovar1(Sessio s, Sessio[] sessions) throws Exception {
		if (r.getTipus() == TipusRes.binaria) {
			io.write("Tenim una assignatura FM creada de nivell 1...\n");
			io.write("Tenim un grup 10 creat, de capacitat 30 persones, i assignat a FM...\n" );
			io.write("Tenim una aula A01 creada, de capacitat 30 persones, amb tipus aula TEO...\n");
			io.write("Tenim una sessio de TEO creada, per dilluns de 8h a 9h, pel grup 10 de FM a l'aula A01...\n");
			io.write("Tenim una altre aula creada A02, de capacitat 30 persones, amb tipus aula TEO...\n");
			io.write("Tenim una altre assignatura creada, IC, que es de nivell 1... \n");
			io.write("Creem sessio pel grup 10 d'IC, de 8h a 9h de dilluns a l'aula A02...\n");
		    io.write("Comprovant restricci�...\n");		
			Boolean correcte = r.comprovar(s, sessions, 0, 0);
			if (correcte) io.write("Sessions correctes \n");
			else io.write("Sessions amb solapament incorretes: assignatures mateix nivell \n");
		}
		else io.write("La restriccio ha de ser binaria. Per provar-la canvia el tipus a traves d'un set\n ");
	}
	
	public static void testComprovar2(Sessio s, Sessio[] sessions) throws Exception {
		if (r.getTipus() == TipusRes.binaria) {
			io.write("Tenim una assignatura FM creada de nivell 1...\n");
			io.write("Tenim un grup 10 creat, de capacitat 30 persones, i assignat a FM...\n" );
			io.write("Tenim una aula A01 creada, de capacitat 30 persones, amb tipus aula TEO...\n");
			io.write("Tenim una sessio de TEO creada, per dilluns de 8h a 9h, pel grup 10 de FM a l'aula A01...\n");
			io.write("Tenim una altre aula creada A02, de capacitat 30 persones, amb tipus aula TEO...\n");
			io.write("Tenim una altre assignatura creada, M1, que es de nivell 2... \n");
			io.write("Creem sessio pel grup 10 de M1, de 8h a 9h de dilluns a l'aula A02...\n");
		    io.write("Comprovant restricci�...\n");
			Boolean correcte = r.comprovar(s, sessions, 0, 0);
			if (correcte) io.write("Sessions correctes \n");
			else io.write("Sessions amb solapament incorretes: assignatures amb mateix nivell \n");
		}
		else io.write("La restriccio ha de ser binaria. Per provar-la canvia el tipus a traves d'un set\n ");
	}
	public static void main(String[] args) throws Exception {
		io = new inout();
		int s = -1;
		Aula au = new Aula ("A01", 30); //nova aula au
		ConjuntAula cjAU = ConjuntAula.getInstance();
		Integer idcjAU = cjAU.NewId(au); //afegeixo aula al conjunt
		Vector<Integer> aules = new Vector<Integer>(); //vector aules
		aules.add(idcjAU); //afeigeixo a aules l'aula creada
		Aula au2 = new Aula ("A02", 30); //nova aula au2
		Integer idcjAU2 = cjAU.NewId(au2); //afeigeixo aula2 al conjunt
		aules.add(idcjAU); //afeigeixo a aules l'aula creada 2
		TipusAula ta = new TipusAula("TEO"); //nou tipus aula teo
		ta.setAules(aules); //afegeixo aules a tipus aula teo
		ConjuntTipusAules cjTA = ConjuntTipusAules.getInstance();
		Integer idcjTA= cjTA.NewId(ta); //afegeixo tipus aula ta al conjunt tipus aula
		au.setTipus(idcjTA); //asigno a au el tipus d'aula
		au2.setTipus(idcjTA);//assigno a au2 el tipus d'aula
		TipusSessio ts = new TipusSessio("TEO", idcjTA, 2); //tipus sessio
		ConjuntTipusSessio cjTS = ConjuntTipusSessio.getInstance();
		Integer idcjTS = cjTS.NewId(ts); //afeigeixo tipus sessio al conjunt tipus sessio
		Vector<Integer> tsessions = new Vector<Integer>();  //vector de tipus sessions
		tsessions.add(idcjTS); //afegeixo a tssesions el tipus sessio creat
		Assignatura a = new Assignatura ("FM", "Fonaments Matematics", 1, tsessions); //nova assignatura a amb tsessions
		ConjuntAssignatura cjA = ConjuntAssignatura.getInstance();
		Integer idcjA = cjA.NewId(a); //afegeixo a al conjunt assignatures
		Assignatura a2 = new Assignatura ("M1", "Matematiques1", 2, tsessions); //nova assignatura a2 amb tsessions
		Integer idcjA2 = cjA.NewId(a2);	//afegeixo a2 al conjunt assignatures
		Assignatura a3 = new Assignatura ("IC", "Introduccio als Computadors", 1, tsessions); //nova assignatura a3 amb tsessions
		Integer idcjA3 = cjA.NewId(a3); //afegeixo a3 al conjunt assignatures
		Grup g = new Grup (10,30); //nou grup
		ConjuntGrup cjG = ConjuntGrup.getInstance();
		Integer idcjG = cjG.NewId(g); //afegeixo grup al conjunt grups
		GrupAssig ga = new GrupAssig (idcjG, idcjA, true); //nou grup assignatura amb a, g
		ConjuntGrupAssig cjGA = ConjuntGrupAssig.getInstance();
		Integer idcjGA = cjGA.NewId(ga);	//afegeixo ga al conjunt grup assignatura
		GrupAssig ga2 = new GrupAssig (idcjG, idcjA2, true); //nou grup assiggnatura amb a2, g
		Integer idcjGA2 = cjGA.NewId(ga2); //afegeixo ga2 al conjunt grup assignatura
		GrupAssig ga3 = new GrupAssig(idcjG, idcjA3, true); //nou grup assignatura amb a3, g
		Integer idcjGA3 = cjGA.NewId(ga3); //afegeixo ga3 al conjunt grup assignatura 
		Sessio ss = new Sessio("sessio1", idcjAU, idcjGA, idcjTS); //nova sessio amb au, ga, ts
		Vector<Sessio> vSessions = new Vector<Sessio>(); //vector de sessions
		vSessions.add(ss);		    //afegeixo sessio1 a sessions
		Sessio s2 = new Sessio("sessio2", idcjAU2, idcjGA2, idcjTS); //nova sessio amb au2, ga2, ts
		vSessions.add(s2); //afegeixo sessio2 a sessions
		Sessio s3 = new Sessio("sessio3", idcjAU2, idcjGA3, idcjTS); //nova sessio amb au2, ga3, ts
		vSessions.add(s3); //afegeixo sessio3 a sessions
		Sessio[] sessions = vSessions.toArray(new Sessio[vSessions.size()]); //converteixo vector sessiosn a array
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
					if (s == 1) testComprovar1(s3, sessions);
					if (s == 2) testComprovar2(s2, sessions);
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