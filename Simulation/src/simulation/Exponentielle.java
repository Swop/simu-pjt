package simulation;

public class Exponentielle {
	
	
	/**
	 * Prend en parametre le temps ecoule depuis la derniere apparaition d'un client
	 * @param tpsEcoule
	 * @param lambda parametre de la loi exponentielle
	 * lambda represente le temps moyen d'attente entre 2 client
	 * donc imaginons un tps moyen de 4 min, lambda 4
	 * Retourne un entier correspodant au temps d'attente
	 * @return
	 */
	//NOTE : FONCTION TESTEE SUR PLUSIEURS MILLIERS D'ITERATIONS : CA FONCTIONNE
	public static int getTpsApparitionClient(double lambda){
		//On tire aleatoirement une espérance de la loi exponentielle
		//d'espérance lambda
		
		//p(X=k)= exp(-1/lambda  * k)
		//d'ou   log ( p(X=k) )  = -1/lambda   * k
		//d'ou  -lambda *  log ( p(X=k) ) = k
		//en tirant aleatoirement p(X=k) (dans [0,1])		
		double d =(-lambda * Math.log(Utils.getRandDouble()));
		
		//Permet un vrai arrondi
		d = Math.floor(d+0.5);	
		return (int)d;	
	}

}
