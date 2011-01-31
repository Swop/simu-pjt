package taxis;

import manager.Model;
import simulation.Utils;

public class Client {
	
	public static final int MAX_TEMPS_ATTENTE = 40;

	//Position Actuelle
	private double x;
	private double y;
	
	//Destination
	private double xDest;
	private double yDest;
	
	//Temps attente entre 0,2 et 1h
	private double tpsAttente;

	private ClientStatus status;
	
	// Le taxi dans lequel se trouve le client
	private Taxi taxi;
	
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
		
		//this.tpsAttente = 0.8 * Utils.getRandDouble() +  0.2

		status = ClientStatus.waiting;
		
		taxi = null;
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

	public ClientStatus getStatus() {
		return status;
	}

	public double getTpsAttente() {
		return tpsAttente;
	}
	
	public void setStatus(ClientStatus status) {
		this.status = status;
	}
	
	/**
	 * Methode pour incrementer le tps d'attente du client
	 * en fonction d'un pas
	 * @param pas
	 * le pas qui permet de calculer de combien augment le tps
	 * d'attente du client
	 */
	public void incrementeTpsAttente(double pas) {
		tpsAttente += pas;
	}
	
	/**
	 * Setter qui permet de notifier au client dans quel taxi il se trouve
	 * @param taxi
	 * le taxi dans lequel le client est
	 */
	public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}
	
	/**
	 * Getter pour connaitre le taxi dans lequel se trouve le client
	 * @return
	 * le taxi dans lequel se situe le client,
	 * null s'il n'en a pas
	 */
	public Taxi getTaxi() {
		return taxi;
	}
}
