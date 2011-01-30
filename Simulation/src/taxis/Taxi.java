package taxis;

import java.util.ArrayList;

import manager.Model;

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
	 * @return
	 * 0 si le client est arrive 1 sinon
	 */
	public int move(){
		if (this.mainClient == null) {
		}
		else{
			if (this.status == TaxiStatus.wayToClient){
				/**
				 * on bouge dans la direction mainClient.position
				 */
				
				// calcul de l'angle separant le taxi du client (radian)
				double angle = Math.atan2((mainClient.getY() - y), mainClient.getX() - x);
				// on calcule alors le x parcouru et le y parcouru
				double newX = Model.getParams().getVitesse()*Math.cos(angle);
				double newY = Model.getParams().getVitesse()*Math.sin(angle);
				
				// on regarde si on a atteint le client
				if((x < mainClient.getX() && x+newX >= mainClient.getX())
					|| (x > mainClient.getX() && x+newX <= mainClient.getX())) {
					// client atteint on met a jour
					x = mainClient.getX();
					y = mainClient.getY();
					status = TaxiStatus.wayToClientDestination;
					mainClient.setStatus(ClientStatus.inTheTaxi);
					clientDansTaxi.add(mainClient);
					// recalcul du mainClient en fonction de la distance la plus proche
					mainClient = getPlusProche();
				} else {
					x += newX;
					y += newY;
				}
			}
			else if(this.status == TaxiStatus.wayToClientDestination){
				/**
				 * on bouge dans la direction de mainClient.Destination
				 */
				// calcul de l'angle separant le taxi de sa destination (radian)
				double angle = Math.atan2((mainClient.getYDest() - y), mainClient.getXDest() - x);
				// on calcule alors le x parcouru et le y parcouru
				double newX = Model.getParams().getVitesse()*Math.cos(angle);
				double newY = Model.getParams().getVitesse()*Math.sin(angle);
				
				// on regarde si on a atteint la destination client
				if((x < mainClient.getXDest() && x+newX >= mainClient.getXDest())
					|| (x > mainClient.getXDest() && x+newX <= mainClient.getXDest())) {
					// destination du client atteinte on met a jour
					x = mainClient.getXDest();
					y = mainClient.getYDest();
					status = TaxiStatus.waiting;
					// il faut supprimer le client
					// TODO : signaler au modele que le client a disparu ??
					clientDansTaxi.remove(mainClient);
					// recalcul du mainClient en fonction de la distance la plus proche
					mainClient = getPlusProche();
					
					// on retourne 1 pour dire que le client est bien arrive
					return 1;
				} else {
					x += newX;
					y += newY;
				}
			}
		}
		return 0;
	}
	
	/**
	 * Un getter pour connaitre la coordonnees x du taxi
	 * @return
	 * La coordonnee x du taxi
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Un getter pour connaitre la coordonnees y du taxi
	 * @return
	 * La coordonnee y du taxi
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * 
	 * Methode pour prendre aller chercher un nouveau client
	 * 
	 * @param c
	 * Le nouveau client a prendre en consideration
	 */
	public void changeClientPrority(Client c) {
		if(status == TaxiStatus.wayToClient) {
			// il faut echanger le client que le taxi va chercher
			// mainClient ne peut etre null a cause de ce status
			mainClient.setStatus(ClientStatus.waiting);
			// le client devient la nouvelle priorite
			mainClient = c;
		} else if(status == TaxiStatus.wayToClientDestination) {
			// on regarde lequel devient la plus grande priorite
			double distance = Math.sqrt((c.getX() - x)*(c.getX() - x)
					+ (c.getY() - y)*(c.getY() - y));
			double mainDistance = Math.sqrt((mainClient.getXDest() - x)*(mainClient.getXDest() - x)
					+(mainClient.getYDest() - y)*(mainClient.getYDest() - y));
			if(distance <= mainDistance) {
				status = TaxiStatus.wayToClient;
				mainClient = c;
			}
		} else { // status est a waiting
			status = TaxiStatus.wayToClient;
			mainClient = c;
		}
		// on met a jour le status du client
		c.setStatus(ClientStatus.taxiComing);
	}
	
	/**
	 * Un getter pour connaitre le status du taxi
	 * @return
	 * le status du taxi
	 */
	public TaxiStatus getStatus() {
		return status;
	}
	
	public double getDistanceObjectif() {
		if(status == TaxiStatus.wayToClient) {
			return Math.sqrt((mainClient.getX() - x)*(mainClient.getX() - x)
					+ (mainClient.getY() - y)*(mainClient.getY() - y));
		} else if( status == TaxiStatus.wayToClientDestination) {
			return Math.sqrt((mainClient.getXDest() - x)*(mainClient.getXDest() - x)
					+ (mainClient.getYDest() - y)*(mainClient.getYDest() - y));
		} else {
			return -1;
		}
	}
	
	public void prendreClient(Client c){
		if(this.clientDansTaxi.size() == 0){
		}
	}
	
	public boolean isFull(){		
		if (this.clientDansTaxi.size() >= 2) return true;
		else return false;
	}

	/**
	 * Methode pour obtenir le client dont la destination est
	 * la plus proche de la position actuelle du taxi
	 * @return
	 * le client dont la destination est la plus proche du taxi
	 */
	private Client getPlusProche() {
		Client plusProche = null; // peut etre a null
		for(Client c : clientDansTaxi) {
			if(plusProche == null) {
				plusProche = c;
			} else {
				double distance = Math.sqrt((c.getXDest() - x)*(c.getXDest() - x)
						+ (c.getYDest() - y)*(c.getYDest() - y));
				double mainDistance = Math.sqrt((plusProche.getXDest() - x)
						*(plusProche.getXDest() - x)
						+(plusProche.getYDest() - y)
						*(plusProche.getYDest() - y));
				if(distance < mainDistance) {
					plusProche = c;
				}
			}
		}
		return plusProche;
	}
}
