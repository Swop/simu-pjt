package taxis;

import java.util.ArrayList;

public class Comportement {
	
	
	/**
	 * Ici on defini les paramètre de la loi de 
	 * poisson pour connaitre les temps d'aparition
	 * des clients Ex:
	 * Unité de temps 1h
	 * 4 client par heure :
	 * Lambda = 4*1h = 4
	 * 
	 */
	
	/**
	 * Les clients sur la map
	 */
	private ArrayList<Client> clients;
	
	private int lambda;
	
	public Comportement(int lambda){
		this.lambda = lambda;
		
		
	}

}
