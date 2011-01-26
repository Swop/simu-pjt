package taxis;

import manager.Manager;

public class Client {
	
	//Position Actuelle
	private double x;
	private double y;
	
	//Destination
	private double xDest;
	private double yDest;
	
	//Temps attente entre 0,2 et 1h
	private double tpsAttente;
	
	
	//Crée un client avec une position et une destination aleatoire
	public Client(){
		
		
		double teta = Manager.MTRand.nextDouble()* 2 * Math.PI;
		double r = Manager.MTRand.nextDouble() * Manager.RAYON ;
		this.x = r * Math.cos(teta);
		this.y = r * Math.sin(teta);

		
		teta = Manager.MTRand.nextDouble()* 2 * Math.PI;
		r = Manager.MTRand.nextDouble() * Manager.RAYON;	
		this.xDest = r * Math.cos(teta);
		this.yDest = r * Math.sin(teta);
		
		this.tpsAttente = 0.8 * Manager.MTRand.nextDouble() +  0.2;
		
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getXDest() {
		return xDest;
	}

	public double getYDest() {
		return yDest;
	}
	
	

}
