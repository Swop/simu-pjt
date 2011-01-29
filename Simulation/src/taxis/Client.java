package taxis;

import manager.Manager;
import manager.Model;
import simulation.Utils;

public class Client {
	
	//Position Actuelle
	private double x;
	private double y;
	
	//Destination
	private double xDest;
	private double yDest;
	
	//Temps attente entre 0,2 et 1h
	private double tpsAttente;
	
	
	//Cr�e un client avec une position et une destination aleatoire
	public Client(){
		
		
		double teta = Utils.getRandDouble()* 2 * Math.PI;
		double r = Utils.getRandDouble() * Model.getParams().getRayon() ;
		this.x = r * Math.cos(teta);
		this.y = r * Math.sin(teta);

		
		teta = Utils.getRandDouble()* 2 * Math.PI;
		r = Utils.getRandDouble() * Model.getParams().getRayon();
		this.xDest = r * Math.cos(teta);
		this.yDest = r * Math.sin(teta);
		
		this.tpsAttente = 0.8 * Utils.getRandDouble() +  0.2;
		
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
