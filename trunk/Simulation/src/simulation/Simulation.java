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
	 * Nombre de pas de temps dans la journee
	 */
	public static final int NB_PAS = 24;

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
	 * Satisfaction lcient obtenue
	 */
	private double calculerStaisfactionJournee(int nbTaxis) {
		int nbTotalClients = 0;
		int nbClientSatisfaits = 0;

		List<Client> clients = new ArrayList<Client>();
		List<Taxi> taxis = new ArrayList<Taxi>();

		for(int i=0; i< nbTaxis; i++)
			taxis.add(new Taxi(clients));


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
			int nbNouveauxClients = Poisson.nombreNouveauxClients(Model.getParams().getLambdaPoisson());
			nbTotalClients += nbNouveauxClients;

			for(int j=0; j<nbNouveauxClients; j++)
				clients.add(new Client());

			for(Taxi t : taxis) {
				nbClientSatisfaits += t.move();
			}

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

						// Attention, si on place le client en mode "taxiComing", et si le taxi en question
						// est redirriger autre part, il faut penser a remettre le client en "isWaiting"
						// pour qu'un autre taxi lui soit envoye la fois d'apres ...

						//TODO : client.tempsAttente += 1;
					} else {
						//TODO Le client se barre
					}
				} else if(c.getStatus().equals(ClientStatus.taxiComing)) {
					if(c.getTpsAttente() >= Client.MAX_TEMPS_ATTENTE) {
						//TODO le client se barre.
						// Penser a prevenir le taxi qui se dirrigeai vers lui que c'est plus la peine de venir ...
					} else {
						//TODO : client.tempsAttente += 1;
					}
				}
			}
		}

		return (double)nbClientSatisfaits/(double)nbTotalClients;
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
