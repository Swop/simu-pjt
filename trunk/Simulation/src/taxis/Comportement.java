package taxis;

public class Comportement {
	
	
	/**
	 * Ici on defini les param�tre de la loi de 
	 * poisson pour connaitre les temps d'aparition
	 * des clients Ex:
	 * Unit� de temps 1h
	 * 4 client par heure :
	 * Lambda = 4*1h = 4
	 * 
	 */
	
	private int lambda;
	
	public Comportement(int lambda){
		this.lambda = lambda;
		
		
	}

}