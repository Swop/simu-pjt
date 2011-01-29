package manager;

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

}
