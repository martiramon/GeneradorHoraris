package presentacio;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.google.gson.Gson;

import FONTS.agallofre.CtrlDomini;

public class TestControladorPresentacio {

	ControladorPresentacio ctrl = new ControladorPresentacio();
	CtrlDomini ctrldom = new CtrlDomini();
	Gson gson = new Gson();
	
	@Test
	public void testJsonToArrays () {
		String[][] sm = new String[2][2];
		
		sm[0][0] = "test";
		sm[1][1] = "otrotest";
		
		String s = gson.toJson(sm);
		int x = 1;
		
	}
	
	
}
