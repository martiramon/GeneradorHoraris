package persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;


public class ControladorDades {
	protected static File a;
	protected static FileReader fr;
	protected static FileWriter fw;
	protected String path;
    
    public ControladorDades(String p) {
    	a = null;
    	fr = null;
    	fw = null;
    	path = p;
    }
    
    public void ObrirArxiu() throws IOException {
    	a = new File(path);
    	if (!a.exists()){
    		a.createNewFile();
    	}
    }
    public  void TancarArxiu() throws IOException {
    	a = null;
    	if (fr != null) fr.close();
    	if (fw != null) fw.close();
    }
    public void EsborrarContingut() throws Exception {
    	ObrirArxiu();
    	fw = new FileWriter(a);
    	BufferedWriter bw = new BufferedWriter(fw);
    	bw.write("");
    	TancarArxiu();
    }
    public void Escriure(String s) throws Exception {
    	if (a == null) throw new Exception("Arxiu no obert");
    	
    	fw = new FileWriter(a, true);
    	BufferedWriter bw = new BufferedWriter(fw);
    	PrintWriter out = new PrintWriter(bw);
    	out.println(s);
    	out.close();
    }
    
    public void Escriure(String[][] hor) throws Exception {
		if (a == null) throw new Exception("Arxiu no obert");
    	fw = new FileWriter(a, true);
    	BufferedWriter bw = new BufferedWriter(fw);
    	PrintWriter out = new PrintWriter(bw);
    	for (int d = 0; d < hor.length; ++d) { //Days
    		for (int h = 0; h < hor[d].length; ++h) { //Hours
    			out.print(hor[d][h]);
    		}
    		out.println("-----");
    	}
    	out.close();
	}
    
    public Vector<String> Llegir() throws Exception {
    	if (a == null) throw new Exception("Arxiu no obert");
    	fr = new FileReader(a);
    	BufferedReader in = new BufferedReader(fr);
    	Vector<String> lineas = new Vector<String>();
    	String linea;
    	while ((linea = in.readLine()) != null) {
    		lineas.add(linea);
    	}
    	in.close();
    	return lineas;
    } 
    
    public String[][][] LlegirMat() throws Exception {
		if (a == null) throw new Exception("Arxiu no obert");
    	fr = new FileReader(a);
    	BufferedReader in = new BufferedReader(fr);
    	String[][][] hor = new String [5][12][];
		for (int d = 0; d < hor.length; ++d) { //Days
			ArrayList<String[]> sessions = new ArrayList<String[]>();
			String s;
			s = in.readLine();
			while (!(s.equals("-----"))) {
				sessions.add(s.split(";"));
				s = in.readLine();
			}
			hor[d] = sessions.toArray(new String[sessions.size()][]);
		}
		return hor;
	}
}
