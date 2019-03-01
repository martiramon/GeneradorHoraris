package FONTS.mramon;

import java.util.Vector;

import FONTS.mramon.Restriccio;

public class CjtRestriccions {
	
	private static  Vector<Restriccio> Restriccions;
	private static CjtRestriccions myCjtRestriccions;
	
	private CjtRestriccions() {
		Restriccions = new Vector<Restriccio>();
		RestriccioTipusAulaDisponible r1 = new RestriccioTipusAulaDisponible("RestriccioTipusAulaDisponible", TipusRes.unaria, true);
		Restriccions.add(r1);
		RestriccioCapacitat r2 = new RestriccioCapacitat("RestriccioCapacitat", TipusRes.unaria , true);
		Restriccions.add(r2);
		RestriccioHorariLectiu r3 = new RestriccioHorariLectiu("RestriccioHorariLectiu", TipusRes.unaria , true);
		Restriccions.add(r3);
		RestriccioMatiTarda r4 = new RestriccioMatiTarda("RestriccioMatiTarda", TipusRes.unaria , true);
		Restriccions.add(r4);
		RestriccioHoresImpossibles r5 = new RestriccioHoresImpossibles("RestriccioHoresImpossibles", TipusRes.unaria, true);
		Restriccions.add(r5);
		RestriccioTipusAulaPerTipusSessio r6 = new RestriccioTipusAulaPerTipusSessio("RestriccioTipusAulaPerTipusSessio", TipusRes.unaria , true);
		Restriccions.add(r6);
		RestriccioAulaBuida r7 = new RestriccioAulaBuida("RestriccioAulaBuida", TipusRes.binaria , true);
		Restriccions.add(r7);
		RestriccioCorequisit r8 = new RestriccioCorequisit("RestriccioCorequisit", TipusRes.binaria , true);
		Restriccions.add(r8);
		RestriccioNivell r9 = new RestriccioNivell("RestriccioNivell", TipusRes.binaria , true);
		Restriccions.add(r9);
		RestriccioMaximSessionsAssigSeguides r10 = new RestriccioMaximSessionsAssigSeguides("RestriccioMaximSessionsAssigSeguides", TipusRes.global , true);
		Restriccions.add(r10);
		RestriccioLaboratoriPostTeoria r11 = new RestriccioLaboratoriPostTeoria("RestriccioLaboratoriPostTeoria", TipusRes.global, true);
		Restriccions.add(r11);
		RestriccioMaximHoresDiaMateixaAssigTipus r12 = new RestriccioMaximHoresDiaMateixaAssigTipus("RestriccioMaximHoresDiaMateixAssigTipus", TipusRes.global, true);
		Restriccions.add(r12);
		RestriccioMaximSessionsAssigDia r13 = new RestriccioMaximSessionsAssigDia("RestriccioMaximSessionsAssigDia", TipusRes.global, true);
		Restriccions.add(r13);
	}

	public static CjtRestriccions getCjtRestriccions() {
		if (myCjtRestriccions == null) {
			myCjtRestriccions = new CjtRestriccions();
		}
		return myCjtRestriccions;
	}
	
	public Integer NewId(Restriccio r){
		int i= Restriccions.indexOf(r);
		if (i != -1) return -1;
		Restriccions.add(r);
		return Restriccions.size() - 1;
	}

	public Restriccio GetObject(Integer idR){
		return Restriccions.get(idR);
	}

	public TipusRes getTipus(Integer idR) {
		return Restriccions.get(idR).getTipus();
	}
	public void setTipus(Integer idR,TipusRes tipus) {
		Restriccions.get(idR).setTipus(tipus);
	}
	public Boolean getActivat(Integer idR) {
		return Restriccions.get(idR).getActivat();
	}
	public void setActivat(Integer idR, Boolean activat) {
		Restriccions.get(idR).setActivat(activat);
	}
	public void activatON(Integer idR) {
		Restriccions.get(idR).activatON();
	}	
	public void activatOFF(Integer idR) {
		Restriccions.get(idR).activatOFF();
	}
	public Vector<Restriccio> getRestriccions() {
		return Restriccions;
	}
	public void setRestriccions(Vector<Restriccio> restriccions) {
		Restriccions = restriccions;
	}
	public Integer size() {
		return Restriccions.size();
	}

}
