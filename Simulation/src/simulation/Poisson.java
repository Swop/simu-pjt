package simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import manager.Model;
import view.LogView;

/**
 * Loi de Poisson
 */
public class Poisson {

	public static final double SEUIL_ACCEPTATION = 0.02;

	public static double poisson(double lambda, int k) {

		//Pour un lambda = 4, au dela de 12 on considere la proba nulle
		//car factorielle de 13 rentre pas dans un int;

		// int k = MTRand.nextInt(12);
		return Math.exp(-lambda) * Math.pow(lambda, k) / Utils.fact(k);
	}
	
	public static int nombreNouveauxClients(double lambda) {
		HashMap lst = new HashMap();
		boolean firstIntersectionPassed = false;
		double proba = 0;

		int k = 0;

		while(proba >= Poisson.SEUIL_ACCEPTATION || !firstIntersectionPassed) {
			proba = Poisson.poisson(lambda, k);
			if(!firstIntersectionPassed && proba >= Poisson.SEUIL_ACCEPTATION)
				firstIntersectionPassed = true;

			if(firstIntersectionPassed && proba >= Poisson.SEUIL_ACCEPTATION)
				lst.put(k, proba);
			k++;
		}

		double sum = 0;

		for(Integer i : (Set<Integer>)lst.keySet())
			sum += (Double)lst.get(i);

		double alea = Utils.getRandDouble() * sum;

		sum = 0;

		for(Integer i : (Set<Integer>)lst.keySet()) {
			if(sum+(Double)lst.get(i) >= alea)
				return i;
			sum += (Double)lst.get(i);
		}
		return 0;
	}

	public static double superPoisson() {
		double p = 0;
		double d = Utils.getRandDouble();
		int j = 0;
		while (d > p) {

			p += poisson(4, j);
			j++;
		}
		return j - 1;
	}

	public static void testPoisson() {
		double cpt = 0;
		for (int i = 0; i < Model.getParams().getIterations(); i++) {
			LogView.log(""+Poisson.superPoisson());
		}
		LogView.log(""+Poisson.poisson(4, 4));
		LogView.log("" + cpt / 10000);
	}
}
