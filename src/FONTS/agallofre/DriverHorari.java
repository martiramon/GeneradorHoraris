package FONTS.agallofre;

import java.util.Vector;

import FONTS.esaus.ConjuntAssignatura;
import FONTS.esaus.ConjuntAula;
import FONTS.esaus.ConjuntGrup;
import FONTS.esaus.ConjuntGrupAssig;
import FONTS.esaus.ConjuntTipusAules;
import FONTS.esaus.ConjuntTipusSessio;
import FONTS.esaus.Sessio;
import FONTS.esaus.inout;

public class DriverHorari {
	
	private static inout io;
	
	public static void TestHorariBad() throws Exception {
		io = new inout();
		
		CtrlDomini ctr = new CtrlDomini();
		ctr.reiniciarConjunts();

		Integer aula1 = ctr.crearAula("A5101", 25);
		
		Vector<Integer> aules = new Vector<Integer>();
		
		aules.add(aula1);
		Integer taula = ctr.crearTipusAula("TEO");
		ConjuntTipusAules cjttaula = ConjuntTipusAules.getInstance();
		cjttaula.GetObject(taula).setAules(aules);
		
		ConjuntAula cjaula = ConjuntAula.getInstance();
		cjaula.GetObject(aula1).setTipus(taula);
		
		Integer tsessio = ctr.crearTipusSessio("TEO", taula, 3);
		Vector<Integer> tsessions = new Vector<Integer>();
		tsessions.add(tsessio);

		
		//Integer assig = ctr.crearAssignatura("PROP", "Projecte de programació", 1, tsessions);
		
		Integer assig1 = ctr.crearAssignatura("FM", "Fonaments Matematics", 1, tsessions);
		
		Integer grup = ctr.crearGrup(10, 30);
		
		
		ctr.crearGrupAssignatura(grup, assig1, true);
		
		Horari h = new Horari();
		h.generarHorari();
		if (!h.getb()) io.write("No es pot generar un horari \n");
		else printhorari(h.getHorari());
	}
	
	public static void testHorariCorrecte() throws Exception {

		
		CtrlDomini ctr = new CtrlDomini();
		ctr.reiniciarConjunts();
		
		Integer aula1 = ctr.crearAula("A5101", 25);
		Integer aula2 = ctr.crearAula("A5102", 25);
		Integer aula3 = ctr.crearAula("A5103", 25);
		Integer aula4 = ctr.crearAula("A5104", 25);
		Integer aula5 = ctr.crearAula("A5105", 25);
		Integer aula6 = ctr.crearAula("A5106", 20);
		
		Integer aula7 = ctr.crearAula("A6101", 20);
		Integer aula8 = ctr.crearAula("A6102", 20);
		Integer aula9 = ctr.crearAula("A6103", 20);
		Integer aula10 = ctr.crearAula("A6104", 20);
		Integer aula11 = ctr.crearAula("A6105", 20);
		Integer aula12 = ctr.crearAula("A6106", 20);
		
		Integer aulal1 = ctr.crearAula("A5S101", 25);
		Integer aulal2 = ctr.crearAula("A5S102", 25);
		Integer aulal3 = ctr.crearAula("A5S103", 25);
		Integer aulal4 = ctr.crearAula("A5S104", 25);
		Integer aulal5 = ctr.crearAula("A5S105", 25);
		Integer aulal6 = ctr.crearAula("A5S106", 25);
		
		Integer aulal7 = ctr.crearAula("A6S101", 25);
		Integer aulal8 = ctr.crearAula("A6S102", 25);
		Integer aulal9 = ctr.crearAula("A6S103", 25);
		Integer aulal10 = ctr.crearAula("A6S104", 25);
		Integer aulal11 = ctr.crearAula("A6S105", 25);
		Integer aulal12 = ctr.crearAula("A6S106", 25);
		
		Vector<Integer> aules = new Vector<Integer>();
		Vector<Integer> aules2 = new Vector<Integer>();
		
		aules.add(aula1);
		aules.add(aula2);
		aules.add(aula3);
		aules.add(aula4);
		aules.add(aula5);
		aules.add(aula6);
		aules.add(aula7);
		aules.add(aula8);
		aules.add(aula9);
		aules.add(aula10);
		aules.add(aula11);
		aules.add(aula12);
		
		aules2.add(aulal1);
		aules2.add(aulal2);
		aules2.add(aulal3);
		aules2.add(aulal4);
		aules2.add(aulal5);
		aules2.add(aulal6);
		aules2.add(aulal7);
		aules2.add(aulal8);
		aules2.add(aulal9);
		aules2.add(aulal10);
		aules2.add(aulal11);
		aules2.add(aulal12);
		
		Integer taula = ctr.crearTipusAula("TEO");
		Integer taula2 = ctr.crearTipusAula("LAB");
		ConjuntTipusAules cjttaula = ConjuntTipusAules.getInstance();
		cjttaula.GetObject(taula).setAules(aules);
		cjttaula.GetObject(taula2).setAules(aules2);
		
		
		ConjuntAula cjaula = ConjuntAula.getInstance();
		cjaula.GetObject(aula1).setTipus(taula);
		cjaula.GetObject(aula2).setTipus(taula);
		cjaula.GetObject(aula3).setTipus(taula);
		cjaula.GetObject(aula4).setTipus(taula);
		cjaula.GetObject(aula5).setTipus(taula);
		cjaula.GetObject(aula6).setTipus(taula);
		cjaula.GetObject(aula7).setTipus(taula);
		cjaula.GetObject(aula8).setTipus(taula);
		cjaula.GetObject(aula9).setTipus(taula);
		cjaula.GetObject(aula10).setTipus(taula);
		cjaula.GetObject(aula11).setTipus(taula);
		cjaula.GetObject(aula12).setTipus(taula);
		
		cjaula.GetObject(aulal1).setTipus(taula2);
		cjaula.GetObject(aulal2).setTipus(taula2);
		cjaula.GetObject(aulal3).setTipus(taula2);
		cjaula.GetObject(aulal4).setTipus(taula2);
		cjaula.GetObject(aulal5).setTipus(taula2);
		cjaula.GetObject(aulal6).setTipus(taula2);
		cjaula.GetObject(aulal7).setTipus(taula2);
		cjaula.GetObject(aulal8).setTipus(taula2);
		cjaula.GetObject(aulal9).setTipus(taula2);
		cjaula.GetObject(aulal10).setTipus(taula2);
		cjaula.GetObject(aulal11).setTipus(taula2);
		cjaula.GetObject(aulal12).setTipus(taula2);
		
		
		//cjttaula.GetObject(taula).AddAula(aula);
	
	
		Integer tsessio2 = ctr.crearTipusSessio("LAB", taula2, 2);
		Integer tsessio = ctr.crearTipusSessio("TEO", taula, 3);
	
	
		Vector<Integer> tsessions = new Vector<Integer>();
		Vector<Integer> tsessions2 = new Vector<Integer>();
	
		tsessions.add(tsessio);
		tsessions.add(tsessio2);
		
		tsessions2.add(tsessio);
		
		//Integer assig = ctr.crearAssignatura("PROP", "Projecte de programació", 1, tsessions);
		
		Integer assig1 = ctr.crearAssignatura("FM", "Fonaments Matematics", 1, tsessions);
		Integer assig2 = ctr.crearAssignatura("IC", "Introduccio als computadors", 1, tsessions);
		Integer assig3 = ctr.crearAssignatura("F", "Física", 1, tsessions);
		Integer assig4 = ctr.crearAssignatura("PRO1", "Programació 1", 1, tsessions);
		
		Integer assig5 = ctr.crearAssignatura("PR02", "Fonaments Matematics", 2, tsessions);
		Integer assig6 = ctr.crearAssignatura("EC", "Introduccio als computadors", 2, tsessions);
		Integer assig7 = ctr.crearAssignatura("M1", "Física", 2, tsessions);
		Integer assig8 = ctr.crearAssignatura("M2", "Programació 1", 2, tsessions);
		
		Integer grup = ctr.crearGrup(10, 25);
		Integer grup2 = ctr.crearGrup(20, 25);
		Integer grup3 = ctr.crearGrup(30, 25);
		Integer grup4 = ctr.crearGrup(40, 25);
		Integer grup5 = ctr.crearGrup(50, 25);
		Integer grup6 = ctr.crearGrup(60, 25);
		Integer grup7 = ctr.crearGrup(70, 25);
		
		ctr.crearGrupAssignatura(grup, assig1, true);
		ctr.crearGrupAssignatura(grup, assig2, true);
		ctr.crearGrupAssignatura(grup, assig3, true);
		ctr.crearGrupAssignatura(grup, assig4, true);
		
		ctr.crearGrupAssignatura(grup, assig5, true);
		ctr.crearGrupAssignatura(grup, assig6, true);
		ctr.crearGrupAssignatura(grup, assig7, true);
		ctr.crearGrupAssignatura(grup, assig8, true);
		
		ctr.crearGrupAssignatura(grup2, assig1, true);
		ctr.crearGrupAssignatura(grup2, assig2, true);
		ctr.crearGrupAssignatura(grup2, assig3, true);
		ctr.crearGrupAssignatura(grup2, assig4, true);
		
		ctr.crearGrupAssignatura(grup2, assig5, true);
		ctr.crearGrupAssignatura(grup2, assig6, true);
		ctr.crearGrupAssignatura(grup2, assig7, true);
		ctr.crearGrupAssignatura(grup2, assig8, true);
		
		ctr.crearGrupAssignatura(grup3, assig1, true);
		ctr.crearGrupAssignatura(grup3, assig2, true);
		ctr.crearGrupAssignatura(grup3, assig3, true);
		ctr.crearGrupAssignatura(grup3, assig4, true);
		
		ctr.crearGrupAssignatura(grup3, assig5, true);
		ctr.crearGrupAssignatura(grup3, assig6, true);
		ctr.crearGrupAssignatura(grup3, assig7, true);
		ctr.crearGrupAssignatura(grup3, assig8, true);
		
		ctr.crearGrupAssignatura(grup4, assig1, true);
		ctr.crearGrupAssignatura(grup4, assig2, true);
		ctr.crearGrupAssignatura(grup4, assig3, true);
		ctr.crearGrupAssignatura(grup4, assig4, true);
		
		ctr.crearGrupAssignatura(grup4, assig5, true);
		ctr.crearGrupAssignatura(grup4, assig6, true);
		ctr.crearGrupAssignatura(grup4, assig7, true);
		ctr.crearGrupAssignatura(grup4, assig8, true);
		
		ctr.crearGrupAssignatura(grup5, assig1, true);
		ctr.crearGrupAssignatura(grup5, assig2, true);
		ctr.crearGrupAssignatura(grup5, assig3, true);
		ctr.crearGrupAssignatura(grup5, assig4, true);
		
		ctr.crearGrupAssignatura(grup5, assig5, true);
		ctr.crearGrupAssignatura(grup5, assig6, true);
		ctr.crearGrupAssignatura(grup5, assig7, true);
		ctr.crearGrupAssignatura(grup5, assig8, true);
		
		ctr.crearGrupAssignatura(grup6, assig1, false);
		ctr.crearGrupAssignatura(grup6, assig2, false);
		ctr.crearGrupAssignatura(grup6, assig3, false);
		ctr.crearGrupAssignatura(grup6, assig4, false);
		
		ctr.crearGrupAssignatura(grup6, assig5, false);
		ctr.crearGrupAssignatura(grup6, assig6, false);
		ctr.crearGrupAssignatura(grup6, assig7, false);
		ctr.crearGrupAssignatura(grup6, assig8, false);
		
		ctr.crearGrupAssignatura(grup7, assig1, false);
		ctr.crearGrupAssignatura(grup7, assig2, false);
		ctr.crearGrupAssignatura(grup7, assig3, false);
		ctr.crearGrupAssignatura(grup7, assig4, false);
		
		ctr.crearGrupAssignatura(grup7, assig5, false);
		ctr.crearGrupAssignatura(grup7, assig6, false);
		ctr.crearGrupAssignatura(grup7, assig7, false);
		ctr.crearGrupAssignatura(grup7, assig8, false);
		//ctr.crear
		
		Horari h = new Horari();
		h.generarHorari();
		printhorari(h.getHorari());
		
	}
	
	
	
	public static void printhorari(Sessio[][][] horari) throws Exception {
		
		io = new inout();
		
		ConjuntAula aules = ConjuntAula.getInstance();
        ConjuntGrupAssig grups = ConjuntGrupAssig.getInstance();
        ConjuntTipusSessio tipussesio = ConjuntTipusSessio.getInstance();
        ConjuntAssignatura assignatures = ConjuntAssignatura.getInstance();
        ConjuntGrup grupsnormals = ConjuntGrup.getInstance();
		
    	for (int i = 0; i < horari.length; ++i) {
    		switch (i) {
			case 0:
				io.write("Dilluns: \n");
				break;
			case 1:
				io.write("Dimarts: \n");
				break;
			case 2:
				io.write("Dimecres: \n");
				break;
			case 3:
				io.write("Dijous:\n");
				break;
			case 4:
				io.write("Divendres:\n");
				break;
			default:
				break;
			}
	   		for (int j = 0; j < horari[0].length; ++j) {
	   			switch (j) {
				case 0:
					io.write("	08:00 - 09:00\n");
					break;
				case 1:
					io.write("	09:00 - 10:00\n");
					break;
				case 2:
					io.write("	10:00 - 11:00\n");
					break;
				case 3:
					io.write("	11:00 - 12:00\n");
					break;
				case 4:
					io.write("	12:00 - 13:00\n");
					break;
				case 5:
					io.write("	13:00 - 14:00\n");
					break;
				case 6:
					io.write("	14:00 - 15:00\n");
					break;
				case 7:
					io.write("	15:00 - 16:00\n");
					break;
				case 8:
					io.write("	16:00 - 17:00\n");
					break;
				case 9:
					io.write("	17:00 - 18:00\n");
					break;
				case 10:
					io.write("	18:00 - 19:00\n");
					break;
				case 11:
					io.write("	19:00 - 20:00\n");
					break;
				default:
					break;
				}
	   			for (int k = 0; k < horari[0][0].length; ++k) {
	   				if (horari[i][j][k] != null) {
	   				io.write("		Aula: " + aules.GetObject(horari[i][j][k].getAula()).getId() 
	   									+ " Assig: " 
	   							+ assignatures.GetObject(grups.GetObject(horari[i][j][k].getGrup()).getAssignatura()).getAcronim() 
	   							+ " Grup: "
	   							+ grupsnormals.getNum(grups.GetObject(horari[i][j][k].getGrup()).getGrup())
	   							+ " Tipus: " + tipussesio.GetObject(horari[i][j][k].getTipusSessio()).getId() + "\n");
	   				}
	   			}
	   		}
    	}
    }
	
	public static void main(String[] args) throws Exception {
		io = new inout();
		int s = -1;
		io.write("Opcions: \n 1.Generar Horari Correcte \n 2.Generar Horari Incorrecte \n 3.Print horari buit \n 4.Exit \n");
		while (s != 6) {
			if (s == 1) {
				testHorariCorrecte();
				io.write("\n");
				io.write("Opcions: \n 1.Generar Horari Correcte \n 2.Generar Horari Incorrecte \n 3.Print horari buit \n 4.Exit \n");
				}
			else if (s == 2) {
				TestHorariBad();
				io.write("\n");
				io.write("Opcions: \n 1.Generar Horari Correcte \n 2.Generar Horari Incorrecte \n 3.Print horari buit \n 4.Exit \n");
			}
			else if (s == 3) {
				Horari h = new Horari();
				printhorari(h.getHorari());
				io.write("\n");
				io.write("Opcions: \n 1.Generar Horari Correcte \n 2.Generar Horari Incorrecte \n 3.Print horari buit \n 4.Exit \n");
			}
			else if (s == 4) {
				System.exit(0);
			}
			s = io.readint();
		}
		io.write("Exit Drive \n");
	}

	
}
