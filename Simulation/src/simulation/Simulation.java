package simulation;

import java.util.ArrayList;
import java.util.List;
import manager.Model;
import taxis.Client;
import taxis.ClientStatus;
import taxis.Taxi;
import taxis.TaxiStatus;
import view.LogView;
import view.MainWindow;

/**
 * Classe gerant le deroulement de la simulation
 * @author Sylvain Mauduit <sylvain@mauduit.fr>
 */
public class Simulation extends Thread {
	/**
	 * Pas de temps dans le calcul d'une journee
	 */
	public static final int PAS_DE_TEMPS = 1;

	/**
	 * Nombre de pas de temps dans la journee, correspond a 1440 minute
	 */
	public static final int NB_PAS = 1440;

	/**
	 * Nombre de taxi necessaire pour atteindre la satisfaction client
	 */
	private int nombreTaxisNecessaires;

	/**
	 * Satisfaction client atteinte pendant la simulation
	 */
	private double moyenneSatisfaction;

	/**
	 * Represente l'etat de la simulation
	 */
	private boolean running = false;
	
	/**
	 * Represente la liste des clients de la simulation
	 */
	private List<Client> clients = new ArrayList<Client>();

	/**
	 * Represente la liste des clients qui ont ete supprimer
	 * pendant le pas d'action (satisfaits ou non), car on ne peut pas
	 * retirer un element d'une liste pendant qu'elle est parcourue
	 */
	private List<Client> clientsAVirer = new ArrayList<Client>();
	
	/**
	 * Constructeur par default de la simulation
	 */
	public Simulation() {
		
	}

	/**
	 * Lancement du Thread de simulation
	 */
	@Override
	public void run() {
		running = true;
		
		nombreTaxisNecessaires = 0;
		moyenneSatisfaction = 0;

		LogView.log("---- LANCEMENT DE LA SIMULATION ----");
		LogView.log("Iterations : "+Model.getParams().getIterations());
		LogView.log("Satisfaction client minimale : "+Model.getParams().getSatisfactionClient());
		LogView.log("Vitesse des taxis : "+Model.getParams().getVitesse()+" metres/min");
		LogView.log("Rayon de la ville : "+Model.getParams().getRayon()+" metres");
		LogView.log("Nombre moyen de nouveau client par heure : "+Model.getParams().getLambdaPoisson());

		LogView.log("");

		while(moyenneSatisfaction < Model.getParams().getSatisfactionClient() && running) {

			nombreTaxisNecessaires++;
			LogView.log("TAXIS : "+nombreTaxisNecessaires);

			List<Double> satisfactionsJournees = new ArrayList<Double>();

			for(int i=0; i<Model.getParams().getIterations() && running; i++) {
				satisfactionsJournees.add(calculerStaisfactionJournee(nombreTaxisNecessaires));
			}
			if(!running)
				break;
			moyenneSatisfaction = Utils.moyenne(satisfactionsJournees);
		}
		if(!running) {
			nombreTaxisNecessaires--;
			LogView.log("-- Simulation stoppée par l'utilisateur --");
		}
		
		LogView.log("---- RESULTATS ----");
		LogView.log("Satisfaction client : "+moyenneSatisfaction);
		LogView.log("Taxi nécessaires : "+nombreTaxisNecessaires);

		running = false;
		MainWindow.getInstance().activerCommandes(true);
	}

	/**
	 * Calcule la satifaction client obtenue au bout d'une journee
	 * @param nbTaxis
	 * Nombre de taxis utilise
	 * @return
	 * Satisfaction client obtenue
	 */
	private double calculerStaisfactionJournee(int nbTaxis) {
		clients.clear(); // on reinitialise
		int nbTotalClients = 0;
		int nbClientSatisfaits = 0;

		List<Taxi> taxis = new ArrayList<Taxi>();
		// on ajoute les taxis
		for(int i = 0; i < nbTaxis; ++i) {
			taxis.add(new Taxi());
		}
		
		int attenteAvantAparition = 0;
		
		
		for(int i = 0; i<Simulation.NB_PAS*Simulation.PAS_DE_TEMPS; i+=Simulation.PAS_DE_TEMPS) {
			
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
			
			clientsAVirer.clear(); // reinit les clients a virer
			int nbNouveauxClients =0;
			if (attenteAvantAparition == 0){
				
				attenteAvantAparition = Exponentielle.getTpsApparitionClient(Model.getParams().getLambdaPoisson());
				//System.out.println(attenteAvantAparition);
				nbNouveauxClients = 1;
			}
			else attenteAvantAparition--;
			
			/*int nbNouveauxClients = Poisson.nombreNouveauxClients(Model.getParams().getLambdaPoisson());*/
			nbTotalClients += nbNouveauxClients;

			for(int j=0; j<nbNouveauxClients; j++)
				clients.add(new Client());

			for(Taxi t : taxis) {
				nbClientSatisfaits += t.move();
			}
			
			// on retire les clients qui ont ete satisfaits
			for(Client c : clientsAVirer) {
				clients.remove(c);
			}
			
			clientsAVirer.clear();

			for(Client c : clients) {
				
				if(c.getStatus().equals(ClientStatus.waiting)) {
					if(c.getTpsAttente() < Client.MAX_TEMPS_ATTENTE) {
						// Parcours taxi, selectioner le plus proche qui est pas pris
						Taxi plusProche = null;
						double distPlusProche = 0;
						for(Taxi t : taxis) {
							if(!t.isFull())  {
								if(plusProche == null) {
									// on verifie que le taxi n'est pas en train de
									// recuperer un client plus proche
									double dist = Math.sqrt((c.getX() - t.getX())
											*(c.getX() - t.getX())
											+(c.getY() - t.getY())
											*(c.getY() - t.getY()));
									if(t.getStatus() != TaxiStatus.wayToClient
											|| dist < t.getDistanceObjectif()) {
										// le taxi peut y aller
										distPlusProche = dist;
										plusProche = t;
									}
								} else {
									// on calcul la distance
									double distance = Math.sqrt((c.getX() - t.getX())
											*(c.getX() - t.getX())
											+(c.getY() - t.getY())
											*(c.getY() - t.getY()));
									if(distance < distPlusProche) {
										// on verifie que le taxi n'est pas en train de
										// recuperer un client plus proche
										if(t.getStatus() != TaxiStatus.wayToClient
												|| distance < t.getDistanceObjectif()) {
											// le taxi peut y aller
											distPlusProche = distance;
											plusProche = t;
										}
									}
								}
							}
						}
						
						// on regarde si on a reussi a obtenir un taxi
						if(plusProche != null) {
							// dans ce cas on met a jour les status
							plusProche.changeClientPrority(c);
						}

						// on incremente le tps d'attente du client en fct du pas
						c.incrementeTpsAttente(PAS_DE_TEMPS);
					} else {
						// Le client se barre
						// on le retire de la liste
						destroyClient(c);
					}
				} else if(c.getStatus().equals(ClientStatus.taxiComing)) {
					if(c.getTpsAttente() >= Client.MAX_TEMPS_ATTENTE) {
						// Le client se barre
						
						
						// on notifie le taxi
						c.getTaxi().forgetClient(c);
						
						// on le retire de la liste
						destroyClient(c);
					} else {
						// on incremente le tps d'attente du client en fct du pas
						c.incrementeTpsAttente(PAS_DE_TEMPS);
					}
				}
			}
			
			// on retire les clients qui ont sont partis
			for(Client c : clientsAVirer) {
				clients.remove(c);
			}
		}

		return (double)nbClientSatisfaits/(double)nbTotalClients;
	}
	
	/**
	 * Methode permettant d'eliminer un client de la liste
	 * car arrive a destination
	 * @param client
	 * Le client a retirer de la liste
	 */
	public void destroyClient(Client client) {
		clientsAVirer.add(client);
	}

	/**
	 * Getter pour le nombre de taxi requis pour couvrir la satisfaction client
	 * @return
	 * Nombre de taxis requis
	 */
	public int getNombreTaxisNecessaires() {
		return nombreTaxisNecessaires;
	}

	/**
	 * Getter pour la satisfaction client obtenue pendant la simulation
	 * @return
	 * La satisfaction lcient obtenue
	 */
	public double getMoyenneSatisfaction() {
		return moyenneSatisfaction;
	}

	/**
	 * Renseigne sur l'etat de la simulation
	 * @return
	 * True si la simulation est en cours d'exection, False sinon
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Stoppe la simulation
	 */
	public void stopThread() {
		running = false;
	}
}
