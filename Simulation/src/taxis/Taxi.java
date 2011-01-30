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
				int newX = (int)(Model.getParams().getVitesse()*Math.cos(angle));
				int newY = (int)(Model.getParams().getVitesse()*Math.sin(angle));
				
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
					for(Client c : clientDansTaxi) {
						double distance = Math.sqrt(c.getXDest()*c.getXDest()
												+c.getYDest()*c.getYDest());
						double mainDistance = Math.sqrt(mainClient.getXDest()*mainClient.getXDest()
													+mainClient.getYDest()*mainClient.getYDest());
						if(distance < mainDistance) {
							mainClient = c;
						}
					}
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
				int newX = (int)(Model.getParams().getVitesse()*Math.cos(angle));
				int newY = (int)(Model.getParams().getVitesse()*Math.sin(angle));
				
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
					mainClient = null;
					for(Client c : clientDansTaxi) {
						if(mainClient == null) {
							mainClient = c;
						} else {
							double distance = Math.sqrt(c.getXDest()*c.getXDest()
									+c.getYDest()*c.getYDest());
							double mainDistance = Math.sqrt(mainClient.getXDest()*mainClient.getXDest()
									+mainClient.getYDest()*mainClient.getYDest());
							if(distance < mainDistance) {
								mainClient = c;
							}
						}
					}
					
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
	
	public void prendreClient(Client c){
		if(this.clientDansTaxi.size() == 0){
		}
	}
	
	public boolean isFull(){		
		if (this.clientDansTaxi.size() >= 2) return true;
		else return false;
	}

}
