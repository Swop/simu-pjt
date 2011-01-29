package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Fenetre principale du programme
 * @author Sylvain Mauduit <sylvain@mauduit.fr>
 */
public class MainWindow extends JFrame {
	
	/**
	 * Fenetre de log
	 */
	private LogView logView;
	/**
	 * Panneau de parametrage
	 */
	private ParamsPanel paramsPanel;
	/**
	 * Instance de la fenetre
	 */
	private static MainWindow _instance = null;

	/**
	 * Constructeur de la fenetre
	 */
	private MainWindow() {
		super();
	}

	/**
	 * Accesseur pour le ingleton de la fenetre
	 * @return
	 * L'instance de la fenetre
	 */
	public static MainWindow getInstance() {
		if(_instance == null)
			_instance = new MainWindow();
		return _instance;
	}

	/**
	 * Initialise la fenetre
	 */
	public void init() {
		Container mainPane = this.getContentPane();
		mainPane.setLayout(new BorderLayout());

		paramsPanel = new ParamsPanel();
		paramsPanel.init();
		mainPane.add(paramsPanel, BorderLayout.NORTH);

		logView = LogView.getInstance();
		JScrollPane scroll = new JScrollPane(logView);
		scroll.setPreferredSize(new Dimension(800, 600));

		mainPane.add(scroll, BorderLayout.CENTER);



		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(new Dimension(1024, 700));
		this.setTitle("Modélisation & Simulation : Gestion de taxis");
		this.setLocationRelativeTo(null);

		this.pack();

		this.setVisible(true);
	}
	/**
	 * Getter pour la fenetre de log
	 * @return
	 * La fenetre de log
	 */
	public LogView getLogView() {
		return logView;
	}
	/**
	 * Getter pour le panneau de configuration des parametres
	 * @return
	 * Le panneau de configuration des parametres
	 */
	public ParamsPanel getParamsPanel() {
		return paramsPanel;
	}
}
