package taxis;

import java.util.ArrayList;

public class Taxi {
	
	//Position actuelle
	private double x;
	private double y;
	
	private double xDest;
	private double yDest;
	
	public ArrayList<Client> clientDansTaxi;
	
	public Taxi(){
		/**
		 * Les taxi commence en 0:0, au centre;
		 */
		this.x=0;
		this.y=0;
		
		this.clientDansTaxi = new ArrayList<Client>();
		
	}
	
	public void prendreClient(Client c){
		if(this.clientDansTaxi.size() =
	}

}
