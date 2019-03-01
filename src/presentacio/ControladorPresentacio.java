package presentacio;

import java.awt.EventQueue;
import java.util.Vector;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import FONTS.agallofre.CtrlDomini;
import FONTS.agallofre.HorariExceptions;
import javafx.util.Pair;

public class ControladorPresentacio {

	private static CtrlDomini ctrl = new CtrlDomini();
	
	ControladorPresentacio(){
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ctrl.carregarDades();
					Principal window = Principal.getInstance();
					window.setVisible(true);
					GestioAssigs wga = GestioAssigs.getInstance();
					wga.setVisible(false);
					GestioAules wgau = GestioAules.getInstance();
					wga.setVisible(false);
					GestioGrups wgg = GestioGrups.getInstance();
					wgg.setVisible(false);
					GestioTipusAules wgtau = GestioTipusAules.getInstance();
					wgtau.setVisible(false);
					GestioTipusSessions wgtus = GestioTipusSessions.getInstance();
					wgtus.setVisible(false);
					GestioHoresBloquejades wghb = GestioHoresBloquejades.getInstance();
					wghb.setVisible(false);
					Assignatura wa = Assignatura.getInstance();
					wa.setVisible(false);
					Aula wau = Aula.getInstance();
					wau.setVisible(false);
					Horari wh = Horari.getInstance();
					wh.setVisible(false);
					Grup wg = Grup.getInstance();
					wg.setVisible(false);
					TipusAula wtau = TipusAula.getInstance();
					wtau.setVisible(false);
					TipusSessio wts = TipusSessio.getInstance();
					wts.setVisible(false);
					GrupsAssig wgas = GrupsAssig.getInstance();
					wgas.setVisible(false);
					SessionsAssig wsa = SessionsAssig.getInstance();
					wsa.setVisible(false);
					Corequisit wc = Corequisit.getInstance();
					wc.setVisible(false);
					MatiTarda mt = MatiTarda.getInstance();
					mt.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/* Operacions per assignatures */
	
	String getAssigByName (String name) {
		String result = null;
		result = ctrl.getAssigByName(name);
		return result;
	}
	
	String getTipusAulaByName (String name) {
		String result = null;
		result = ctrl.getTipusAulaByName(name);
		return result;
	}
	
	String getAulaByName (String name ) {
		String result = null;
		result = ctrl.getAulaStringByName(name);
		return result;
	}
	
	String getTipusSessioByName (String name) {
		String result = null;
		result = ctrl.getTipusSessioByName(name);
		return result;
	}
	
	String getNomTipusAula(String taula) {
		return ctrl.getNomTipusAula(Integer.valueOf(taula));
	}
	
	Vector<String> getAllAssigName() {
		return ctrl.getAllAssigNames();
	}
	
	Vector<String> getAllTipusSessions() {
		return ctrl.getAllTipusSessions();
	}
	
	Vector<String> getAllSessions(String assig) {
		return ctrl.getAllTipusSessions(assig);
	}
	
	Vector<String> getAllTipusAules() {
		return ctrl.getAllTipusAules();
	}
	
	Vector<String> getAllAulesIds() {
		return ctrl.getAllAulesIds();
	}
	
	Boolean modificarHorari (String oday, String ohour, String oaula, String dday, String dhour, String daula) throws NumberFormatException, HorariExceptions {
		return ctrl.modificarHorari(Integer.valueOf(oday), Integer.valueOf(ohour), oaula, Integer.valueOf(dday), Integer.valueOf(dhour), daula);
	}
	
	public Boolean crearAssignatura (String acronim, String nom, Integer nivell, Vector<Pair<String,String>> grups, Vector<String> coreq, Vector<String> tsessions) {
		Integer assig = ctrl.crearAssignatura(acronim, nom, nivell, tsessions, grups, coreq);
		return assig != -1;
	}
	
	public Boolean crearAula (String nom, String capacitat, String taula) {
		Integer cap = Integer.valueOf(capacitat);
		Integer assig = ctrl.crearAula(nom, cap, taula);
		return assig != -1;
	}
	
	public Boolean crearGrup (String num, String capacitat) {
		Integer n = Integer.valueOf(num);
		Integer c = Integer.valueOf(capacitat);
		
		Integer grup = ctrl.crearGrup(n, c);
		return grup != -1;
	}
	
	public Boolean crearTipusSessio (String id, String tipus, String hores) {
		Integer h = Integer.valueOf(hores);
		Integer grup = ctrl.crearTipusSessio(id, tipus, h);
		return grup != -1;
	}
	
	protected Vector<Integer> getAllTsessions (String name){
		Vector<Integer> result = new Vector<Integer>();
		String json = ctrl.getAssigByName(name);
		
		JsonObject assignatura = new JsonParser().parse(json).getAsJsonObject();
		
		if (assignatura.has("sessions")) {
			JsonElement sessions = assignatura.get("sessions");
			for (JsonElement value : sessions.getAsJsonArray()) {
				result.add(value.getAsInt());
			}
		}
		
		return result;
	}
	
	protected Vector<String> getAllGrups (String name){
		return ctrl.getGroupIdByAssig(name);
	}
	
	protected Vector<String> getAllGrupsAssig (String name){
		return ctrl.getGroupAssigIdByAssig(name);
	}
	
	protected Boolean crearGrupAssig(String grup, String assig, Boolean mati) {
		return ctrl.crearGrupAssignatura(grup, assig, mati);
	}
	
	protected Boolean modificarGrupAssig(String grup, String assig, Boolean mati) {
		return ctrl.modificarGrupAssignatura(grup, assig, mati);
	}
	
	protected Vector<String> getAllCoreq (String name){
		return ctrl.getAllCoreq(name);		
	}
	
	protected Boolean modificarAssignatura (String nom, String acronim, Integer nivell, Vector<Pair<String,String>> grups, 
			Vector<String> tsessions, Vector<Integer> sessions, Vector<String> corequisits) {
		return ctrl.modificarAssignatura(nom, acronim, nivell, grups, tsessions, sessions, corequisits);
	}
	
	public Boolean modificarAula(String id, Integer capacitat, String tipusA) {
		Vector<Integer> sessions = null;
		return ctrl.modificarAula(id, capacitat, tipusA, sessions);
	}	
	
	public Boolean modificarGrup (String num, String cap) {
		return ctrl.modificarGrup (Integer.valueOf(num), Integer.valueOf(cap));
	}
	
	public Boolean modificarTipusSessio (String id, String tipus) {
		return ctrl.modificarTsessio (id, Integer.valueOf(tipus));
	}
	
	public Boolean eliminarAssignatura (String nom) {
		return ctrl.eliminarAssignatura(nom);
	}
	
	public Boolean eliminarAula (String id) {
		return ctrl.eliminarAula(id);
	}
	
	public Boolean eliminarGrup (String id) {
		return ctrl.eliminarGrup(id);
	}
	
	public Boolean eliminarTipusAula (String id) {
		return ctrl.eliminarTaula(id);
	}
	
	public Boolean eliminarTipusSessio (String id) {
		return ctrl.eliminarTipusSessio(id);
	}
	
	public Boolean afegirCorequisits (String assig, Vector<Pair<String,Boolean>> coreq) {
		return afegirCorequisits(assig, coreq);
	}
	
	public void carregarDades() throws Exception {
		ctrl.carregarDades();
	}
	
	public void guardarDades() throws Exception {
		ctrl.guardarDades();
	}
	
	public void guardarHorari() throws Exception {
		ctrl.guardarHorari();
	}
	
	public Boolean generarHorari() throws HorariExceptions {
		return ctrl.generarHorari();
	}
	
	public Pair<String[][],Integer> getInfoHorariAula (String aula) throws HorariExceptions{
		return ctrl.getInfoHorariAula(aula);
	}
	
	public Pair<String[][],Integer> getInfoHorariAssig (String assignatura) throws HorariExceptions{
		return ctrl.getInfoHorariAssig(assignatura);
	}
	public Pair<String[][],Integer> getInfoHorariAssigAula (String assignatura, String aula) throws HorariExceptions{
		return ctrl.getInfoHorariAssigAula(assignatura, aula);
	}
	
	public Boolean toogleRestriccio(String nom, Boolean estat) {
		return ctrl.toogleRestriccio(nom, estat);
	}
	
	public Boolean getInfoRestriccio(String nom) {
		return ctrl.getInfoRestriccio(nom);
	}
	
	/* Operacions per grups */
	
	String getGroupById (Integer id) {
		String result = null;
		result = ctrl.getGroupById(id);
		return result;
	}
	
	Vector<String> getAllGroupIds() {
		return ctrl.getAllGroupIds();
	}
	
	/* Operacions grup assig */ 

	Vector<String> getGroupAssigById (Integer id) {
		Vector<String> result = null;
		result = ctrl.getGroupAssigByAssig(id);
		return result;
	}
	
	Vector<String> getAllGroupAssigIds () {
		return ctrl.getAllGroupAssigIds();
	}

	public Boolean crearTipusAula(String text) {
		Integer i = ctrl.crearTipusAula(text);
		return i != -1;
	}

	public boolean getMati(Pair<String, String> grupAssig) {
		return ctrl.getMati(grupAssig);
	}
	
}
