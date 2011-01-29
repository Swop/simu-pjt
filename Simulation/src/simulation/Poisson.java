package simulation;

import manager.Model;
import view.LogView;

/**
 * Loi de Poisson
 */
public class Poisson {

	public static double poisson(double lambda, int k) {

		//Pour un lambda = 4, au dela de 12 on considere la proba nulle
		//car factorielle de 13 rentre pas dans un int;

		// int k = MTRand.nextInt(12);
		return Math.exp(-lambda) * Math.pow(lambda, k) / Utils.fact(k);
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
