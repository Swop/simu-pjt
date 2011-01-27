package manager;

import random.MTRandom;

public class Manager {

	
	//Generateur de nombre aleatoire
	//Utilise la méthode Mersenne Twister : une des meilleures
	//Sans argument il s'initialise en fonction de l'heure
	public static MTRandom MTRand = new MTRandom();
	
	/**
	 * Taille du cercle
	 * Correspond à 10 000 mètres
	 */
	public static int RAYON = 10000;
	
	/**
	 * Le temps commence à 0;
	 * Un pas de temps correspond à 1 min
	 */
	public static int TPS = 0;
	
	/**
	 * Vitesse du taxi en ville 50km/h
	 * en metre par minute : 50 000/60 m/min =  833,33
	 */
	public static double VITESSE = 833.333;
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
				double cpt =0;
		for (int i =0 ;i< 10000;i++)
			{
				System.out.println(superPoisson());
			}
		System.out.println(poisson(4,4));
		System.out.println(""+cpt/10000);
	}	

	public static double poisson(double lambda,int k) 
	{
		//Pour un lambda = 4, au dela de 12 on considere la proba nulle
		//car factorielle de 13 rentre pas dans un int;
	   // int k = MTRand.nextInt(12);
	    return Math.exp (-lambda)* Math.pow(lambda, k) / fact(k);
	}
	
	public static double superPoisson(){
		double p =0;
		double d =MTRand.nextDouble();
		int j=0;
		while (d > p){
			
			p += poisson(4,j);
			j++;
		}
		return j-1;
	}
	
	
	 static int fact(int n) {
			
			// Base Case: 
			//    If n <= 1 then n! = 1.
			if (n <= 1) {
			    return 1;
			}
			// Recursive Case:  
			//    If n > 1 then n! = n * (n-1)!
			else {
			    return n * fact(n-1);
			}
	 }
	
	
	//public static double distance
}
