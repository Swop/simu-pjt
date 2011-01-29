package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

/**
 * Panneau de configuration du programme
 * @author Sylvain Mauduit <sylvain@mauduit.fr>
 */
public class ParamsPanel extends Container {
	/**
	 * Spinner pour le nombre d'iterrations
	 */
	private JSpinner nbIterrationsSpinner;
	/**
	 * Spinner pour la taille de la ville
	 */
	private JSpinner tailleVilleSpinner;
	/**
	 * Spinner pour la vitesse des taxis
	 */
	private JSpinner vitesseTaxisSpinner;

	/**
	 * Constructeur du panneau de parametres
	 */
	public ParamsPanel() {
		super();
	}

	/**
	 * Initialise le panneau de parametres
	 */
	public void init() {
		this.setLayout(new FlowLayout());
		
		JLabel label;
		JPanel optionPane;
		
		optionPane = new JPanel(new BorderLayout());
		label = new JLabel("Nb itérations : ");
		this.nbIterrationsSpinner = new JSpinner();
		nbIterrationsSpinner = new JSpinner();
		JSpinner.NumberEditor nbIterrationsSpinnerEditor = new JSpinner.NumberEditor(nbIterrationsSpinner);
		nbIterrationsSpinner.setEditor(nbIterrationsSpinnerEditor);
		//TODO Default value from model
		nbIterrationsSpinnerEditor.getModel().setValue(10000);
		nbIterrationsSpinnerEditor.getModel().setMinimum(1);
		nbIterrationsSpinnerEditor.getFormat().applyPattern("###,##0");
		optionPane.add(nbIterrationsSpinner, BorderLayout.CENTER);
		optionPane.add(label, BorderLayout.WEST);
		this.add(optionPane);

		optionPane = new JPanel(new BorderLayout());
		label = new JLabel("Taille ville (m) : ");
		this.tailleVilleSpinner = new JSpinner();
		tailleVilleSpinner = new JSpinner();
		JSpinner.NumberEditor tailleVilleSpinnerEditor = new JSpinner.NumberEditor(tailleVilleSpinner);
		tailleVilleSpinner.setEditor(tailleVilleSpinnerEditor);
		//TODO Default value from model
		tailleVilleSpinnerEditor.getModel().setValue(10000);
		tailleVilleSpinnerEditor.getModel().setMinimum(1);
		tailleVilleSpinnerEditor.getFormat().applyPattern("###,##0");
		optionPane.add(tailleVilleSpinner, BorderLayout.CENTER);
		optionPane.add(label, BorderLayout.WEST);
		this.add(optionPane);

		optionPane = new JPanel(new BorderLayout());
		label = new JLabel("Vitesse taxis (m/min) : ");
		this.vitesseTaxisSpinner = new JSpinner();
		vitesseTaxisSpinner = new JSpinner();
		JSpinner.NumberEditor vitesseTaxisSpinnerEditor = new JSpinner.NumberEditor(vitesseTaxisSpinner);
		vitesseTaxisSpinner.setEditor(vitesseTaxisSpinnerEditor);
		//TODO Default value from model
		vitesseTaxisSpinnerEditor.getModel().setValue(833.33);
		vitesseTaxisSpinnerEditor.getModel().setMinimum(1);
		vitesseTaxisSpinnerEditor.getFormat().applyPattern("###,##0.0#");
		optionPane.add(vitesseTaxisSpinner, BorderLayout.CENTER);
		optionPane.add(label, BorderLayout.WEST);
		this.add(optionPane);

	}
}
