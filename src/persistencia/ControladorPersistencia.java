package persistencia;

import java.util.Vector;
import java.io.*;
import java.time.Instant;

public class ControladorPersistencia {
	
	private ControladorDades da, dassig, dg, dga, dpe, ds, dta, dts,dh,dhi;
	
	public ControladorPersistencia() {
		File d = new File("./dades/Horaris");
		if (!d.exists()) d.mkdirs();
		da = new ControladorDades("./dades/Aules.txt");
		dassig = new ControladorDades("./dades/Assignatures.txt");
		dg = new ControladorDades("./dades/Grups.txt");
		dga = new ControladorDades("./dades/GrupsAssignatura.txt");
		dpe = new ControladorDades("./dades/PlansEstudi.txt");
		ds = new ControladorDades("./dades/Sessions.txt");
		dta = new ControladorDades("./dades/TipusAula.txt");
		dts = new ControladorDades("./dades/TipusSessio.txt");
		dhi = new ControladorDades("./dades/HoresImpossibles.txt");
		dh = new ControladorDades("./dades/Horaris/horari.txt");
	}
	
	public void CarregarHorari(String s) {
		String p = "./dades/Horaris/" + s;
		dh = new ControladorDades(p);
	}
	
	//Escritures (String)
	public void EscriureAula(String s) throws Exception{
		da.ObrirArxiu();
		da.Escriure(s);
		da.TancarArxiu();
	}
	public void EscriureAssignatura(String s) throws Exception{
		dassig.ObrirArxiu();
		dassig.Escriure(s);
		dassig.TancarArxiu();
	}
	public void EscriureGrup(String s) throws Exception{
		dg.ObrirArxiu();
		dg.Escriure(s);
		dg.TancarArxiu();
	}
	public void EscriureGrupAssignatura(String s) throws Exception{
		dga.ObrirArxiu();
		dga.Escriure(s);
		dga.TancarArxiu();
	}
	public void EscriurePlaEstudis(String s) throws Exception{
		dpe.ObrirArxiu();
		dpe.Escriure(s);
		dpe.TancarArxiu();
	}	
	public void EscriureSessio(String s) throws Exception{
		ds.ObrirArxiu();
		ds.Escriure(s);
		ds.TancarArxiu();
	}	
	public void EscriureTipusAula(String s) throws Exception{
		dta.ObrirArxiu();
		dta.Escriure(s);
		dta.TancarArxiu();
	}	
	public void EscriureTipusSessio(String s) throws Exception{
		dts.ObrirArxiu();
		dts.Escriure(s);
		dts.TancarArxiu();
	}
	public void EscriureHorari(String s) throws Exception{
		long test = Instant.now().toEpochMilli();
		dh = new ControladorDades("./dades/Horaris/horari" + Long.toString(test) + ".txt");
		dh.ObrirArxiu();
		dh.Escriure(s);
		dh.TancarArxiu();
	}
	public void EscriureHoresImpossibles(String[][] s) throws Exception{
		dhi.ObrirArxiu();
		dhi.Escriure(s);
		dhi.TancarArxiu();
	}
	
	
	//Escritures (Arrays)
	public void EscriureAula(String[] s) throws Exception{
		da.EsborrarContingut();
		da.ObrirArxiu();
		for (int i = 0; i < s.length; ++i) {
			da.Escriure(s[i]);
		}
		da.TancarArxiu();
	}
	public void EscriureAssignatures(String[] s) throws Exception{
		dassig.EsborrarContingut();
		dassig.ObrirArxiu();
		for (int i = 0; i < s.length; ++i) {
			dassig.Escriure(s[i]);
		}
		dassig.TancarArxiu();
	}
	public void EscriureGrup(String[] s) throws Exception{
		dg.EsborrarContingut();
		dg.ObrirArxiu();
		for (int i = 0; i < s.length; ++i) {
			dg.Escriure(s[i]);
		}
		dg.TancarArxiu();
	}
	public void EscriureGrupAssignatura(String[] s) throws Exception{
		dga.EsborrarContingut();
		dga.ObrirArxiu();
		for (int i = 0; i < s.length; ++i) {
			dg.Escriure(s[i]);
		}
		dga.TancarArxiu();
	}
	public void EscriurePlaEstudis(String[] s) throws Exception{
		dpe.EsborrarContingut();
		dpe.ObrirArxiu();
		for (int i = 0; i < s.length; ++i) {
			dpe.Escriure(s[i]);
		}
		dpe.TancarArxiu();
	}
	public void EscriureSessio(String[] s) throws Exception{
		ds.EsborrarContingut();
		ds.ObrirArxiu();
		for (int i = 0; i < s.length; ++i) {
			ds.Escriure(s[i]);
		}
		ds.TancarArxiu();
	}
	public void EscriureTipusAula(String[] s) throws Exception{
		dta.EsborrarContingut();
		dta.ObrirArxiu();
		for (int i = 0; i < s.length; ++i) {
			dta.Escriure(s[i]);
		}
		dta.TancarArxiu();
	}
	public void EscriureTipusSessio(String[] s) throws Exception{
		dts.EsborrarContingut();
		dts.ObrirArxiu();
		for (int i = 0; i < s.length; ++i) {
			dts.Escriure(s[i]);
		}
		dts.TancarArxiu();
	}
	
	
	//Lectures
	public Vector<String> LlegirAules() throws Exception{
		da.ObrirArxiu();
		Vector<String> aux = da.Llegir();
		da.TancarArxiu();
		return aux;
	}
	public Vector<String> LlegirAssignatures() throws Exception{
		dassig.ObrirArxiu();
		Vector<String> aux = dassig.Llegir();
		dassig.TancarArxiu();
		return aux;
	}
	public Vector<String> LlegirGrups() throws Exception{
		dg.ObrirArxiu();
		Vector<String> aux = dg.Llegir();
		dg.TancarArxiu();
		return aux;
	}
	public Vector<String> LlegirGrupsAssignatura() throws Exception{
		dga.ObrirArxiu();
		Vector<String> aux = dga.Llegir();
		dga.TancarArxiu();
		return aux;
	}
	public Vector<String> LlegirPlansEstudi() throws Exception{
		dpe.ObrirArxiu();
		Vector<String> aux = dpe.Llegir();
		dpe.TancarArxiu();
		return aux;
	}
	public Vector<String> LlegirSessions() throws Exception{
		ds.ObrirArxiu();
		Vector<String> aux = ds.Llegir();
		ds.TancarArxiu();
		return aux;
	}
	public Vector<String> LlegirTipusAula() throws Exception{
		dta.ObrirArxiu();
		Vector<String> aux = dta.Llegir();
		dta.TancarArxiu();
		return aux;
	}
	public Vector<String> LlegirTipusSessio() throws Exception{
		dts.ObrirArxiu();
		Vector<String> aux = dts.Llegir();
		dts.TancarArxiu();
		return aux;
	}
	public String[][][] LlegirHorari() throws Exception{
		dh.ObrirArxiu();
		String[][][] aux = dh.LlegirMat();
		dh.TancarArxiu();
		return aux;
	}
	public String[][][] LlegirHoresImpossibles() throws Exception{
		dhi.ObrirArxiu();
		String[][][] aux = dhi.LlegirMat();
		dhi.TancarArxiu();
		return aux;
	}
}
