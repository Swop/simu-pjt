package view;

import javax.swing.JTextArea;

/**
 * Fenetre de log
 * @author Sylvain Mauduit <sylvain@mauduit.fr>
 */
public class LogView extends JTextArea {
	/**
	 * Instance (singleton) de la fenetre de log
	 */
	private static LogView _instance = null;

	/**
	 * Constructeur de la fenetre de log
	 */
	private LogView() {
		super();
		setEditable(false);
	}

	/**
	 * Accesseur pour le singleton
	 * @return
	 * L'instance (unique) de la fenetre de log
	 */
	public static LogView getInstance() {
		if(_instance == null)
			_instance = new LogView();
		return _instance;
	}

	/**
	 * Log un message dans la fenetre de log
	 * @param msg
	 * Le message a loguer
	 */
	public static void log(String msg) {
		LogView.getInstance().logMsg(msg+"\n");
	}

	/**
	 * Log un message dans la fenetre de log
	 * @param msg
	 *	Le message a afficher
	 */
	private void logMsg(String msg) {
		this.append(msg);
	}
}
