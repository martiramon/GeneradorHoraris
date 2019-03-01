package domini;

import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestControlador {

	CtrlDomini ctrl = new CtrlDomini();
	
	@Test
	public void testCrearAssignatura() {
		
		ctrl.reiniciarConjunts();
		
		Integer object = ctrl.crearAssignatura("PROP", "Projecte de programacio", 1, null);
		ConjuntAssignatura conj = ConjuntAssignatura.getInstance();
		Assignatura comprovacio = new Assignatura("PROP", "Projecte de programacio", 1, null);

		assertEquals(comprovacio.getAcronim(), conj.GetObject(object).getAcronim());
	}
	
	@Test
	public void testCrearSessio() {
		
		ctrl.reiniciarConjunts();
		
		Integer object = ctrl.crearSessio("Test", 0, 0, 0);
		ConjuntSessio conj = ConjuntSessio.getInstance();
		Sessio comprovacio = new Sessio("Test", 0,0,0);

		assertEquals(comprovacio.getId(), conj.GetObject(object).getId());
	}
	
	@Test
	public void testCrearAula() {
		
		ctrl.reiniciarConjunts();
		
		Integer aula = ctrl.crearAula("A5102", 20);
		ConjuntAula caula = ConjuntAula.getInstance();
		Aula comprovacio = new Aula("A5102", 20);

		assertEquals(comprovacio.getId(), caula.GetObject(aula).getId());
	}
	
	@Test
	public void testCrearGrup() {
		
		ctrl.reiniciarConjunts();
		
		Integer object = ctrl.crearGrup(10, 20);
		ConjuntGrup conj = ConjuntGrup.getInstance();
		Grup comprovacio = new Grup(10, 20);

		assertEquals(comprovacio.getNum(), conj.GetObject(object).getNum());
	}
	

	
	@Test
	public void testCrearGrupAssignatura() {
		
		ctrl.reiniciarConjunts();
		
		Integer grup = ctrl.crearGrup(10, 20);
		Integer assig = ctrl.crearAssignatura("PROP", "Projecte de programacio", 1, null);
		Integer object = ctrl.crearGrupAssignatura(grup, assig, true);
		
		ConjuntGrupAssig conj = ConjuntGrupAssig.getInstance();
		
		GrupAssig comprovacio = new GrupAssig(grup, assig, true);

		assertEquals(comprovacio.getGrup(), conj.GetObject(object).getGrup());
	}
	
	@Test
	public void testCrearPla() {
		
		ctrl.reiniciarConjunts();
		
		Vector<Integer> assignatures = new Vector<Integer>();
		
		Integer assig = ctrl.crearAssignatura("PROP", "Projecte de programacio", 1, null);
		assignatures.add(assig);
		
		Integer object = ctrl.crearPlaEstudis("FIB", assignatures);
		ConjuntPlaEstudis conj = ConjuntPlaEstudis.getInstance();
		PlaEstudis comprovacio = new PlaEstudis("FIB", assignatures);
				
		assertEquals(comprovacio.getNom(), conj.GetObject(object).getNom());
	}
	
	@Test
	public void testCrearTaula() {
		ctrl.reiniciarConjunts();
		
		Integer object = ctrl.crearTipusAula("Test");
		ConjuntTipusAules conj = ConjuntTipusAules.getInstance();
		
		TipusAula comprovacio = new TipusAula("Test");
		assertEquals(comprovacio.getId(), conj.GetObject(object).getId());		
	}
	
	@Test
	public void testCrearTsessio() {
		ctrl.reiniciarConjunts();
		
		Integer object = ctrl.crearTipusSessio("Test", 1, 2);
		ConjuntTipusSessio conj = ConjuntTipusSessio.getInstance();
		
		TipusSessio comprovacio = new TipusSessio("Test", 1, 2);
		assertEquals(comprovacio.getId(), conj.GetObject(object).getId());		
	}
	
	/* eliminadores */
	
	@Test
	public void testEliminarAssignatura() {
		
		ctrl.reiniciarConjunts();
		
		Integer object = ctrl.crearAssignatura("PROP", "Projecte de programacio", 1, null);
		ConjuntAssignatura conj = ConjuntAssignatura.getInstance();
		ctrl.eliminarAssignatura(object);

		assertEquals(false, conj.IsObject(object));
	}
	
	@Test
	public void testEliminarAula() {
		
		ctrl.reiniciarConjunts();
		
		Integer aula = ctrl.crearAula("A5102", 20);
		ConjuntAula caula = ConjuntAula.getInstance();
		ctrl.eliminarAula(aula);

		assertEquals(false, caula.IsObject(aula));
	}
	
	@Test
	public void testEliminarGrup() {
		
		ctrl.reiniciarConjunts();
		
		Integer object = ctrl.crearGrup(10, 20);
		ConjuntGrup conj = ConjuntGrup.getInstance();
		ctrl.eliminarGrup(object);

		assertEquals(false, conj.IsObject(object));
	}
	

	
	@Test
	public void testEliminarGAssignatura() {
		
		ctrl.reiniciarConjunts();
		
		Integer grup = ctrl.crearGrup(10, 20);
		Integer assig = ctrl.crearAssignatura("PROP", "Projecte de programacio", 1, null);
		Integer object = ctrl.crearGrupAssignatura(grup, assig, true);
		
		ConjuntGrupAssig conj = ConjuntGrupAssig.getInstance();
		
		ctrl.eliminarGrupAssignatura(object);

		assertEquals(false, conj.IsObject(object));
	}
	
	@Test
	public void testEliminarPla() {
		
		ctrl.reiniciarConjunts();
		
		Vector<Integer> assignatures = new Vector<Integer>();
		
		Integer assig = ctrl.crearAssignatura("PROP", "Projecte de programacio", 1, null);
		assignatures.add(assig);
		
		Integer object = ctrl.crearPlaEstudis("FIB", assignatures);
		ConjuntPlaEstudis conj = ConjuntPlaEstudis.getInstance();
		
		ctrl.eliminarPlaEstudis(object);
				
		assertEquals(false, conj.IsObject(object));
	}
	
	@Test
	public void testEliminarTaula() {
		ctrl.reiniciarConjunts();
		
		Integer object = ctrl.crearTipusAula("Test");
		ConjuntTipusAules conj = ConjuntTipusAules.getInstance();
		ctrl.eliminarTipusAula(object);
		
		assertEquals(false, conj.IsObject(object));		
	}
	
	@Test
	public void testEliminarTsessio() {
		ctrl.reiniciarConjunts();
		
		Integer object = ctrl.crearTipusSessio("Test", 1, 2);
		ConjuntTipusSessio conj = ConjuntTipusSessio.getInstance();
		ctrl.eliminarTipusSessio(object);
		
		assertEquals(false, conj.IsObject(object));		
	}
	
	@Test
	public void testEliminarSessio() {
		
		ctrl.reiniciarConjunts();
		
		Integer object = ctrl.crearSessio("Test", 0, 0, 0);
		ConjuntSessio conj = ConjuntSessio.getInstance();
		ctrl.eliminarSessio(object);
		assertEquals(false, conj.IsObject(object));
	}
	
	@Test
	public void TestActivarRestriccio () {
		CjtRestriccions cjtRestriccions = CjtRestriccions.getCjtRestriccions();
		ctrl.activarRestriccio(0);
		
		assertEquals(true, cjtRestriccions.getActivat(0));
	}
	
	@Test
	public void TestDesactivarRestriccio () {
		CjtRestriccions cjtRestriccions = CjtRestriccions.getCjtRestriccions();
		ctrl.desactivarRestriccio(0);
		
		assertEquals(false, cjtRestriccions.getActivat(0));
	}
	
	@Test
	public void TestHorari() throws HorariExceptions {

		ctrl.reiniciarConjunts();
		
		Boolean test = ctrl.generarHorari();
		
		assertEquals(true, test.booleanValue());
		
	}
	
	@Test
	public void TestConsultarAssignatura() {
		Integer assig = ctrl.crearAssignatura("PROP", "Projecte de programacio", 1, null);
		String resultat = ctrl.consultarAssignatura(assig);
		
		assertEquals(resultat, "{\"acronim\":\"PROP\",\"nom\":\"Projecte de programacio\",\"nivell\":1,\"sessions\":[],\"grups\":[],\"corequisit\":[]}");
	}
	
	@Test
	public void TestJsonAssignatura() {
		Integer assig = ctrl.crearAssignatura("PROP", "Projecte de programacio", 1, null);
		String resultat = ctrl.consultarAssignatura(assig);
		
		JsonObject jelement = new JsonParser().parse(resultat).getAsJsonObject();
		String result = jelement.get("nom").getAsString();
		
		System.out.println(result);
		
		//assertEquals(resultat, "{\"acronim\":\"PROP\",\"nom\":\"Projecte de programacio\",\"nivell\":1,\"sessions\":[],\"grups\":[],\"corequisit\":[]}");
	}

		
}
