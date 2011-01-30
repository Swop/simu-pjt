package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import manager.Model;
import manager.Params;

/**
 * Panneau de configuration du programme
 * @author Sylvain Mauduit <sylvain@mauduit.fr>
 */
public class ParamsPanel extends JPanel implements ChangeListener {
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
	 * Spinner pour la satisfaction minimale des clients
	 */
	private JSpinner staisfactionClientSpinner;

	/**
	 * Spinner pour le lambda de la loi de Poisson
	 */
	private JSpinner lambdaPoissonSpinner;

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
		nbIterrationsSpinner.addChangeListener(this);
		nbIterrationsSpinnerEditor.getModel().setValue(Params.ITERRATIONS_DEFAULT);
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
		tailleVilleSpinnerEditor.getModel().setValue(Params.RAYON_DEFAULT);
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
		vitesseTaxisSpinnerEditor.getModel().setValue(Params.VITESSE_DEFAULT);
		vitesseTaxisSpinnerEditor.getModel().setMinimum(1);
		vitesseTaxisSpinnerEditor.getFormat().applyPattern("###,##0.0#");
		optionPane.add(vitesseTaxisSpinner, BorderLayout.CENTER);
		optionPane.add(label, BorderLayout.WEST);
		this.add(optionPane);

		optionPane = new JPanel(new BorderLayout());
		label = new JLabel("Satisfaction client : ");
		this.staisfactionClientSpinner = new JSpinner();
		staisfactionClientSpinner = new JSpinner();
		JSpinner.NumberEditor staisfactionClientSpinnerEditor = new JSpinner.NumberEditor(staisfactionClientSpinner);
		staisfactionClientSpinner.setEditor(staisfactionClientSpinnerEditor);
		staisfactionClientSpinnerEditor.getModel().setValue(Params.SATISFACTION_CLIENT_DEFAULT);
		staisfactionClientSpinnerEditor.getModel().setMinimum(0.01);
		staisfactionClientSpinnerEditor.getModel().setMaximum(1);
		staisfactionClientSpinnerEditor.getFormat().applyPattern("0.0#");
		optionPane.add(staisfactionClientSpinner, BorderLayout.CENTER);
		optionPane.add(label, BorderLayout.WEST);
		this.add(optionPane);

		optionPane = new JPanel(new BorderLayout());
		label = new JLabel("Lambda Poisson (Moy clients/heure) : ");
		this.lambdaPoissonSpinner = new JSpinner();
		lambdaPoissonSpinner = new JSpinner();
		JSpinner.NumberEditor lambdaPoissonSpinnerEditor = new JSpinner.NumberEditor(lambdaPoissonSpinner);
		lambdaPoissonSpinner.setEditor(lambdaPoissonSpinnerEditor);
		lambdaPoissonSpinnerEditor.getModel().setValue(Params.LAMBDA_POISSON_DEFAULT);
		lambdaPoissonSpinnerEditor.getModel().setMinimum(0.01);
		lambdaPoissonSpinnerEditor.getFormat().applyPattern("0.0#");
		optionPane.add(lambdaPoissonSpinner, BorderLayout.CENTER);
		optionPane.add(label, BorderLayout.WEST);
		this.add(optionPane);

	}

	@Override
	public void setEnabled(boolean val) {
		nbIterrationsSpinner.setEnabled(val);
		tailleVilleSpinner.setEnabled(val);
		vitesseTaxisSpinner.setEnabled(val);
		staisfactionClientSpinner.setEnabled(val);
		lambdaPoissonSpinner.setEnabled(val);
	}

	public void stateChanged(ChangeEvent e) {
		if(e.getSource().equals(nbIterrationsSpinner)) {
			Model.getParams().setIterations((Integer)nbIterrationsSpinner.getModel().getValue());
		} else if(e.getSource().equals(tailleVilleSpinner)) {
			Model.getParams().setRayon((Integer)tailleVilleSpinner.getModel().getValue());
		} else if(e.getSource().equals(vitesseTaxisSpinner)) {
			Model.getParams().setVitesse((Double)vitesseTaxisSpinner.getModel().getValue());
		} else if(e.getSource().equals(staisfactionClientSpinner)) {
			Model.getParams().setSatisfactionClient((Double)staisfactionClientSpinner.getModel().getValue());
		} else if(e.getSource().equals(lambdaPoissonSpinner)) {
			Model.getParams().setLambdaPoisson((Double)lambdaPoissonSpinner.getModel().getValue());
		}
	}
}
