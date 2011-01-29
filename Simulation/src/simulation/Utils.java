package simulation;

import random.MTRandom;

/**
 * Utilitaires mathematiques pour le bon fonctionnement du programme et de ses algorithmes
 * @author Sylvain Mauduit <sylvain@mauduit.fr>
 */
public class Utils {
	/**
	 * Generateur de nombre aleatoire
	 * Utilise la methode Mersenne Twister : une des meilleures
	 * Sans argument il s'initialise en fonction de l'heure
	 */
	public static MTRandom MTRand = new MTRandom();

	/**
	 * Calcul le factoriel de n
	 * @param n
	 * Nombre a prendre en compte
	 * @return
	 * Factoriel de n
	 */
	static int fact(int n) {
		if (n <= 1)
			return 1;
		else
			return n * fact(n - 1);
	}

	/**
	 * Genere un nombre decimal
	 * @return
	 * Un nombre decimal aleatoire
	 */
	public static double getRandDouble() {
		return MTRand.nextDouble();
	}
}
