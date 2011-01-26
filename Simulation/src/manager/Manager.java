package manager;

import random.MTRandom;

public class Manager {

	
	//Generateur de nombre aleatoire
	//Utilise la méthode Mersenne Twister : une des meilleures
	//Sans argument il s'initialise en fonction de l'heure
	public static MTRandom MTRand = new MTRandom();
	
	//Taille du cercle
	public static int RAYON = 10;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
				
		for (int i =0 ;i< 100;i++)
			{
				double p;
				double d =MTRand.nextDouble();
				if (d < poisson(4,0)){
					System.out.println("0");
				}
				else if (d < (poisson(4,0)+ poisson(4,1) )){
					System.out.println("1");
				}
				else if (d < (poisson(4,0)+ poisson(4,1)+ poisson(4,2) )){
					System.out.println("2");
				}
				else if (d < (poisson(4,0)+ poisson(4,1)+ poisson(4,2)+ poisson(4,3) )){
					System.out.println("3");
				}
				else System.out.println("+++");
			
			
			}
	}

	public static double poisson(double lambda,int k) 
	{
		//Pour un lambda = 4, au dela de 12 on considere la proba nulle
		//car factorielle de 13 rentre pas dans un int;
	   // int k = MTRand.nextInt(12);
	    return Math.exp (-lambda)* Math.pow(lambda, k) / fact(k);
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
