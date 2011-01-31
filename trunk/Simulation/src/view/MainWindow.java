package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import manager.Model;
import simulation.Simulation;

/**
 * Fenetre principale du programme
 * @author Sylvain Mauduit <sylvain@mauduit.fr>
 */
public class MainWindow extends JFrame implements ActionListener {
	
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
	 * Boutton pour lancer la simulation
	 */
	private JButton runBtn;

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

		JPanel northPane = new JPanel(new BorderLayout());

		paramsPanel = new ParamsPanel();
		paramsPanel.init();
		northPane.add(paramsPanel, BorderLayout.PAGE_START);

		runBtn = new JButton("LANCER LA SIMULATION");
		runBtn.addActionListener(this);
		northPane.add(runBtn, BorderLayout.CENTER);

		mainPane.add(northPane, BorderLayout.PAGE_START);

		logView = LogView.getInstance();
		JScrollPane scroll = new JScrollPane(logView);
		scroll.setPreferredSize(new Dimension(800, 600));

		mainPane.add(scroll, BorderLayout.CENTER);



		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(new Dimension(1024, 700));
		this.setTitle("Modelisation & Simulation : Gestion de taxis");
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

	/**
	 * Desactive le panneau de configuration des parametres et le bouton de lancement avant le calcul
	 */
	public void activerCommandes(boolean val) {
		paramsPanel.setEnabled(val);
		if(val)
			runBtn.setText("LANCER LA SIMULATION");
		else
			runBtn.setText("STOPPER LA SIMULATION");
	}

	/**
	 * S'occupe du traitement du a l'activation du boutton de lancement/d'arret de simulation
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(runBtn)) {
			
			Simulation simu = Model.getInstance().getSimulation();

			if(simu != null && simu.isRunning()) {
				simu.stopThread();
				activerCommandes(true);
			} else {
				activerCommandes(false);
				simu = new Simulation();
				Model.getInstance().setSimulation(simu);
				simu.start();
			}
		}
	}
}
