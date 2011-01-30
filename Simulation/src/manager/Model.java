package manager;

import simulation.Simulation;

/**
 * Modele de donnee du programme
 * @author Sylvain Mauduit <sylvain@mauduit.fr>
 */
public class Model {
	/**
	 * Parametres du programme
	 */
	private Params parametres;

	/**
	 * Simulation en cours
	 */
	private Simulation simulation = null;
	
	/**
	 * Instance du modele
	 */
	private static Model _instance = null;

	/**
	 * Constructeur par default du modele
	 */
	private Model() {
		parametres = new Params();
	}

	/**
	 * Accesseur pour l'unique instance du modele
	 * @return
	 * Le modele
	 */
	public static Model getInstance() {
		if(_instance == null)
			_instance = new Model();
		return _instance;
	}

	/**
	 * Initialisation du modele
	 */
	public void init() {

	}

	/**
	 * Getter pour les parametres du programme
	 * @return
	 * Les parametres du programme
	 */
	public static Params getParams() {
		return getInstance().parametres;
	}

	/**
	 * Getter pour la simulation en cours
	 * @return
	 * La simulation en cours ou Null si aucune simulation n'est en cours
	 */
	public Simulation getSimulation() {
		return simulation;
	}

	/**
	 * Setter pour la simulation en cours
	 * @param simulation
	 * La nouvelle simulation
	 */
	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

}
