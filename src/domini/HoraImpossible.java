package domini;

import java.util.Arrays;
import java.util.Vector;

import utils.Pair;

public class HoraImpossible {

	private static Boolean[][] horesImp;
	private static HoraImpossible myHoraImpossible;
	
	
	public HoraImpossible() {
		horesImp = new Boolean[5][12];
		for (Boolean[] row: horesImp)
		    Arrays.fill(row, Boolean.FALSE);
	}
	
	public static HoraImpossible getHoraImpossible() {
		if (myHoraImpossible == null) {
			myHoraImpossible = new HoraImpossible();
		}
		return myHoraImpossible;
	}
	
	public void setHoraImpossible(Integer dia, Integer hora, Boolean state) {
		horesImp[dia][hora] = state;
	}
	
	public Vector<Pair<Integer>> getHoresImpossibles() {
	
		Vector<Pair<Integer>> result = new Vector<Pair<Integer>>(); 
		
		for (int i = 0; i < horesImp.length; ++i) { 
			for (int j = 0; j < horesImp[0].length; ++j) {
				if (horesImp[i][j]) {
					Pair<Integer> pair = new Pair<Integer>(i,j);
					result.add(pair);
				}
			}
		}
		return result;
	}
	
	
	public Boolean esHoraImpossible(Integer dia, Integer hora) {
		return horesImp[dia][hora];
	}
}
