package taxis;

import java.util.ArrayList;

public class Taxi {
	
	//Position actuelle
	private double x;
	private double y;
	
	/**
	 * Liste de tous les clients dans le taxi
	 */
	private ArrayList<Client> clientDansTaxi;
	
	/**
	 * Permet de savoir qui est le chef des clients
	 * le taxi se deplacera toujours en fction de lui
	 * soit il va le chercher, soit il va le deposer
	 * si un autre client est sur la route par exemple
	 * ben il devient le main client
	 */
	private Client mainClient;
	
	private TaxiStatus status;
	
	public Taxi(){
		/**
		 * Les taxi commence en 0:0, au centre;
		 */
		this.x=0;
		this.y=0;
		
		this.clientDansTaxi = new ArrayList<Client>();
		this.status = TaxiStatus.waiting;
		
	}
	/**
	 * Bouge dans la direction du main client
	 */
	public void move(){
		if (this.mainClient != null){			
		}
		else{
			if (this.status == TaxiStatus.wayToClient){
				/**
				 * TODO
				 * on bouge dans la direction mainClient.position
				 */
			}
			else if(this.status == TaxiStatus.wayToClientDestination){
				/**
				 * TODO
				 * on bouge dans la direction de mainClient.Destination
				 */
			}
			
		}
	}
	
	public void prendreClient(Client c){
		if(this.clientDansTaxi.size() == 0){
		}
	}
	public boolean isLibre(){		
		return true;
	}

}
