package domini;
	import java.lang.Exception;
	import java.util.Vector;

	class DriverRestriccioMaximHoresDiaMateixaAssigTipus {
		
		private static inout io;
		private static RestriccioMaximHoresDiaMateixaAssigTipus r;
		
		public static void testConstructor() throws Exception {
			io.write("Introdueix Id (String): \n");
			String id = io.readname();
			io.write("Introdueix Tipus (unaria, binaria o global): \n");
			io.write("Per a comprovar la restriccio s'ha d'assignar tipus global \n");
			TipusRes tipus = io.readTipusRes();
			io.write("Introdueix Activat (Boolean): \n");
			Boolean activat = io.readboolean();
			io.write("Creant Restriccio amb Id " + id + ", TipusRestriccio " + String.valueOf(tipus) + " i Activat " + activat + "\n");
			r = new RestriccioMaximHoresDiaMateixaAssigTipus(id,tipus,activat);
			io.write("La Restriccio creada te: \n Id = " + r.getId() + "\n TipusRestriccio = " + (r.getTipus().name()) + "\n Activat = " + String.valueOf(r.getActivat()));
		}
		
		public static void testConstructor2() throws Exception {
			io.write("Introdueix Id (String): \n");
			String id = io.readname();
			io.write("Introdueix Tipus (unaria, binaria o global): \n");
			io.write("Per a comprovar la restriccio s'ha d'assignar tipus global \n");
			TipusRes tipus = io.readTipusRes();
			io.write("Introdueix Activat (Boolean): \n");
			r = new RestriccioMaximHoresDiaMateixaAssigTipus(id,tipus);
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
				io.write("Tenim una assignatura FM creada de nivell 1...\n");
				io.write("Tenim un grup 10 creat, de capacitat 30 persones, i assignat a FM...\n" );
				io.write("Tenim una aula A01 creada, de capacitat 30 persones, amb tipus aula TEO...\n");
				io.write("Tenim unes sessions de TEO creades, per dilluns de 8h a 9h i de 9h a 10h, pel grup 10 de FM a l'aula A01...\n");
				io.write("Creem sessio de TEO pel grup 10 de FM, de 11h a 12h de dilluns a l'aula A01...\n");
			    io.write("Comprovant restricci�...\n");	
				Boolean correcte = r.comprovar(s, horari, 0, 3);
				if (correcte) io.write("Sessions correctes \n");
				else io.write("Ja hi ha 2 sessions del mateix tipus per aquest grup i assignatura en aquest dia \n");
			}
			else io.write("La restriccio ha de ser global. Per provar-la canvia el tipus a traves d'un set\n ");
		}
		
		public static void testComprovar2(Sessio s, Sessio[][][] horari) throws Exception {
			if (r.getTipus() == TipusRes.global) {
				io.write("Tenim una assignatura FM creada de nivell 1...\n");
				io.write("Tenim un grup 10 creat, de capacitat 30 persones, i assignat a FM...\n" );
				io.write("Tenim una aula A01 creada, de capacitat 30 persones, amb tipus aula TEO...\n");
				io.write("Tenim una aula A02 creada, de capacitat 30 persones, amb tipus aula LAB...\n");
				io.write("Tenim unes sessions de TEO creades, per dilluns de 8h a 9h i de 9h a 10h, pel grup 10 de FM a l'aula A01...\n");
				io.write("Creem sessio de LAB pel grup 10 de FM, de 11h a 12h de dilluns a l'aula A02...\n");
			    io.write("Comprovant restricci�...\n");	
				Boolean correcte = r.comprovar(s, horari, 0, 3);
				if (correcte) io.write("Sessions correctes \n");
				else io.write("Ja hi ha 2 sessions del mateix tipus per aquest grup i assignatura en aquest dia \n");
			}
			else io.write("La restriccio ha de ser global. Per provar-la canvia el tipus a traves d'un set\n ");
		}
		public static void main(String[] args) throws Exception {
			io = new inout();
			int s = -1;
			Aula au = new Aula ("A01", 30); 
			ConjuntAula cjAU = ConjuntAula.getInstance();
			Integer idcjAU = cjAU.NewId(au); 
			Vector<Integer> aules = new Vector<Integer>(); 
			aules.add(idcjAU); 		
			TipusAula ta = new TipusAula("TEO"); 
			ta.setAules(aules); 
			ConjuntTipusAules cjTA = ConjuntTipusAules.getInstance();
			Integer idcjTA= cjTA.NewId(ta); 
			au.setTipus(idcjTA);
			Aula au2 = new Aula ("A02", 30); 
			Integer idcjAU2 = cjAU.NewId(au2); 
			Vector<Integer> aules2 = new Vector<Integer>(); 
			aules2.add(idcjAU2); 
			TipusAula ta2 = new TipusAula("LAB"); 
			ta2.setAules(aules2); 
			Integer idcjTA2= cjTA.NewId(ta2); 
			au.setTipus(idcjTA);
			TipusSessio ts = new TipusSessio("TEO", idcjTA, 4); 
			ConjuntTipusSessio cjTS = ConjuntTipusSessio.getInstance();
			Integer idcjTS = cjTS.NewId(ts); 
			Vector<Integer> tsessions = new Vector<Integer>();  
			tsessions.add(idcjTS);
			TipusSessio ts2 = new TipusSessio("LAB", idcjTA2, 4); 	
			Integer idcjTS2 = cjTS.NewId(ts2); 
			tsessions.add(idcjTS2); 
			Assignatura a = new Assignatura ("FM", "Fonaments Matematics", 1, tsessions); 
			ConjuntAssignatura cjA = ConjuntAssignatura.getInstance();
			Integer idcjA = cjA.NewId(a); 	
			Grup g = new Grup (10,30); 
			ConjuntGrup cjG = ConjuntGrup.getInstance();
			Integer idcjG = cjG.NewId(g); 
			GrupAssig ga = new GrupAssig (idcjG, idcjA, true); 
			ConjuntGrupAssig cjGA = ConjuntGrupAssig.getInstance();
			Integer idcjGA = cjGA.NewId(ga);		
			Sessio ss = new Sessio("sessio1", idcjAU, idcjGA, idcjTS); 
			Vector<Sessio> vSessions = new Vector<Sessio>(); 
			vSessions.add(ss);		    
			Sessio s2 = new Sessio("sessio2", idcjAU, idcjGA, idcjTS); 
			vSessions.add(s2); 
			Sessio s3 = new Sessio("sessio3", idcjAU, idcjGA, idcjTS); 

			Sessio s4 = new Sessio("sessio4", idcjAU2, idcjGA, idcjTS2);
			
			Sessio[][][] horari = new Sessio[5][12][vSessions.size()];
			Sessio[] sessions = vSessions.toArray(new Sessio[vSessions.size()]);
			horari[0][0][0] = sessions[0];
			horari[0][1][0] = sessions[1];
			
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
						if (s == 1) testComprovar1(s3, horari);
						if (s == 2) testComprovar2(s4, horari);
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