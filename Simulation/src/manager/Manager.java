package manager;

import random.MTRandom;

public class Manager {

	
	//Generateur de nombre aleatoire
	//Utilise la méthode Mersenne Twister : une des meilleures
	//Sans argument il s'initialise en fonction de l'heure
	public static MTRandom MTRand = new MTRandom();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
				
		for (int i =0 ;i< 5;i++) System.out.println(MTRand.nextInt());
	}

	
	
	
	//public static double distance
}
