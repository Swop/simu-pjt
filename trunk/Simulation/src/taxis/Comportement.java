/**
 *		*******************************************************************
 *
 *		CLASSE A ZAPPER JE PENSE ...
 *			Y'A DEJA LE DEROULEMENT DE LA SIMULATION DANS LA CLASSE Simulation
 *
 *		PAR CONTRE, LES COMPORTEMENTS DANS TAXI (ET CLIENT) PEUVENT ETRE INTERRESSANT A COMPLETER
 *
 *
 *		*******************************************************************
 *
 *
 */

package taxis;

import java.util.ArrayList;

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
	
	/**
	 * Les clients sur la map
	 */
	private ArrayList<Client> clients;
	private ArrayList<Taxi> taxis;
	
	private int lambda;
	
	public Comportement(int lambda){
		this.lambda = lambda;
		
		this.clients = new ArrayList<Client>();
		this.taxis = new ArrayList<Taxi>();
		
		
	}

	/**
	 * Algo du comportement
	 * 
	 * Tant que condition de fin de prog faire
	 * 		On fait apparaitre (ou non les clients)
	 * 		On fait avancer les taxis qui ont une destination
	 * 			(on peut ainsi les mettre � "libre" si ils viennet de deposer qqun)
	 * 
	 * 
	 * 		Parcours de la liste des clients
	 * 			si un client est un attente et tps attente n'est pas atteind
	 * 				parcours de la liste des taxis pour lui trouver un taxi
	 * 			sinon si temps attente max atteind, on vire le client;
	 * 			sinon tempsattente++;
	 * 		 			
	 */
}
