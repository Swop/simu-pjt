package taxis;

import manager.Manager;

public class Client {
	
	//Position du client en coord polaire
	private double x;
	private double y;
	
	//Destination du client en coord polaire
	private double xDest;
	private double yDest;
	
	public Client(){
		//TODO Verifier que r < rayon du cercle
		//TODO Verifier que teta compris entre 0 et 2*PI
		
		double teta = Manager.MTRand.nextDouble()* 2 * Math.PI;
		double r = Manager.MTRand.nextDouble();
		this.x = r * Math.cos(teta);
		this.y = r * Math.sin(teta);
		
		
		teta = Manager.MTRand.nextDouble()* 2 * Math.PI;
		r = Manager.MTRand.nextDouble();	
		this.xDest = r * Math.cos(teta);
		this.yDest = r * Math.sin(teta);
		
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
