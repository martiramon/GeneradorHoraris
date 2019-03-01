package domini;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import utils.Pair;

public class TestHoraImpossible {
	
	@Test
	public void testConstructor() {
		HoraImpossible test = new HoraImpossible();
		assertSame(test.esHoraImpossible(0,0), Boolean.FALSE);
	}
	
	@Test
	public void testSetter() {
		HoraImpossible test = new HoraImpossible();
		test.setHoraImpossible(0, 0, true);
		assertEquals(test.esHoraImpossible(0,0), Boolean.TRUE);
	}
	
	@Test
	public void testGetterVector() {
		HoraImpossible test = new HoraImpossible();
		test.setHoraImpossible(1, 1, true);
		
		Vector<Pair<Integer>> result = new Vector<Pair<Integer>>(); 
		Pair<Integer> pair = new Pair<Integer>(1,1);
		
		result.add(pair);
		Vector<Pair<Integer>> result2 = test.getHoresImpossibles();
		
		assertEquals(result.get(0).first(), result2.get(0).first());	
	}
	
	 @Test
	 public void singleton_withoutInstance() {
		HoraImpossible test = HoraImpossible.getHoraImpossible();
		
		Vector<Pair<Integer>> test2 = test.getHoresImpossibles();
		Vector<Pair<Integer>> result = new Vector<Pair<Integer>>();
		
		assertEquals(test2, result);
	 }
	 
}
