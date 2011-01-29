package manager;

/**
 * Parametres du programme
 * @author Sylvain Mauduit <sylvain@mauduit.fr>
 */
public class Params {
	/**
	 * Rayon de la ville par default (10 000 metres)
	 */
	public static final int RAYON_DEFAULT = 10000;
	/**
	 * Vitesse des taxis par default (833.33 metres/minutes)
	 */
	public static final double VITESSE_DEFAULT = 833.33;
	/**
	 * Nombre d'iterations par default (10 000)
	 */
	public static final int ITERRATIONS_DEFAULT = 10000;

	/**
	 * Rayon de la ville (en metres)
	 */
	private int rayon;
	/**
	 * Vitesse des taxis (en metres/minute)
	 */
	private double vitesse;
	/**
	 * Nombre d'iterations
	 */
	private int iterations;

	/**
	 * Constructeur par default de Params
	 */
	public Params() {
		rayon = Params.RAYON_DEFAULT;
		vitesse = Params.VITESSE_DEFAULT;
		iterations = Params.ITERRATIONS_DEFAULT;
	}

	/**
	 * Getter pour le nombre d'iterations
	 * @return
	 * Le nombre d'iterations
	 */
	public int getIterations() {
		return iterations;
	}
	/**
	 * Setter pour le nombre d'iterations
	 * @param iterations
	 * Le nombre d'iterations
	 */
	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
	/**
	 * Getter pour la taille du rayon
	 * @return
	 * La taille du rayon
	 */
	public int getRayon() {
		return rayon;
	}
	/**
	 * Setter pour la taille du rayon
	 * @param rayon
	 * La taille du rayon
	 */
	public void setRayon(int rayon) {
		this.rayon = rayon;
	}
	/**
	 * Getter pour la vitesse des taxis
	 * @return
	 * La itesse des taxis
	 */
	public double getVitesse() {
		return vitesse;
	}
	/**
	 * Setter pour la vitesse des taxis
	 * @param vitesse
	 * La vitesse des taxis
	 */
	public void setVitesse(double vitesse) {
		this.vitesse = vitesse;
	}
}