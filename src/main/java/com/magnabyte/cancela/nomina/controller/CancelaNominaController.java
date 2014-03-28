package com.magnabyte.cancela.nomina.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.magnabyte.cancela.nomina.model.Nomina;
import com.magnabyte.cancela.nomina.service.CancelaNominaService;
import com.magnabyte.cancela.nomina.utils.LetterVerifier;
import com.magnabyte.cancela.nomina.utils.NumberVerifier;

@Controller
public class CancelaNominaController extends JFrame implements ActionListener {

	private static final long serialVersionUID = -886941888411579261L;

	@Autowired
	private CancelaNominaService cancelaNominaService;
	
	public static final int BORDER_PANEL10 = 20;
	public static final int BORDER_PANEL20 = 20;
	
	JPanel mainPanel, formPanel, progressPanel,bottomPanel;
	JLabel lblClaveEmpleado, lblTipoNomina, lblPeriodo, lblAnio;
	JTextField txtfClaveEmpleado, txtfTipoNomina, txtfPeriodo, txtfAnio;
	JButton btnCancel, btnExit;
	JProgressBar progressBar;
	NumberVerifier numberVerifierClaveEmpleado = new NumberVerifier(false);
	LetterVerifier letterVerifierTipo = new LetterVerifier(1, 1);
	NumberVerifier numberVerifierPeriodo = new NumberVerifier(3, 1);
	NumberVerifier numberVerifierAnio = new NumberVerifier(4, 4);
	
	public CancelaNominaController() {
		initUI();
	}

	private void initUI() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_PANEL20, BORDER_PANEL20, BORDER_PANEL20, BORDER_PANEL20));
		
		formPanel = new JPanel(new GridLayout(0, 2));
		formPanel.setPreferredSize(new Dimension(300, 150));
		
		progressPanel = new JPanel();
		progressPanel.setBorder(BorderFactory.createEmptyBorder(0, BORDER_PANEL10, 5, BORDER_PANEL10));
		
		bottomPanel = new JPanel();
		
		lblClaveEmpleado = new JLabel("Clave Empleado: ", JLabel.TRAILING);
		lblTipoNomina = new JLabel("Tipo Nómina: ", JLabel.TRAILING);
		lblPeriodo = new JLabel("Periodo: ", JLabel.TRAILING);
		lblAnio = new JLabel("Año: ", JLabel.TRAILING);

		txtfClaveEmpleado = new JTextField();
		txtfClaveEmpleado.setInputVerifier(numberVerifierClaveEmpleado);
		lblClaveEmpleado.setLabelFor(txtfClaveEmpleado);

		txtfTipoNomina = new JTextField();
		txtfTipoNomina.setInputVerifier(letterVerifierTipo);
		lblTipoNomina.setLabelFor(txtfTipoNomina);
		
		txtfPeriodo = new JTextField();
		txtfPeriodo.setInputVerifier(numberVerifierPeriodo);
		lblPeriodo.setLabelFor(txtfPeriodo);
		
		txtfAnio = new JTextField();
		txtfAnio.setInputVerifier(numberVerifierAnio);
		lblClaveEmpleado.setLabelFor(txtfAnio);
		
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		
		btnCancel = new JButton("Cancelar Nómina");
		btnCancel.setPreferredSize(new Dimension(160, 30));
		btnCancel.addActionListener(this);
		
		btnExit = new JButton("Salir");
		btnExit.setPreferredSize(new Dimension(120, 30));
		btnExit.addActionListener(this);

		formPanel.add(lblClaveEmpleado);
		formPanel.add(txtfClaveEmpleado);
		formPanel.add(lblTipoNomina);
		formPanel.add(txtfTipoNomina);
		formPanel.add(lblPeriodo);
		formPanel.add(txtfPeriodo);
		formPanel.add(lblAnio);
		formPanel.add(txtfAnio);
		formPanel.add(new JLabel());
		
		progressPanel.add(progressBar);
		
		bottomPanel.add(btnCancel);
		bottomPanel.add(btnExit);
		
		formPanel.setOpaque(true);
		mainPanel.add(formPanel, BorderLayout.NORTH);
		mainPanel.add(progressPanel);
		mainPanel.add(new JSeparator());
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		add(mainPanel);
		pack();
		
		setTitle("Cancelación de Nómina");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			if (validateForm() && confirmCancel()) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						btnCancel.setEnabled(false);
						btnExit.setEnabled(false);
						cancelaNominaService.recuperarRegistrosACancelar(getNominaACancelar());
						JOptionPane.showMessageDialog(mainPanel, 
								"El proceso ha terminado",
								"Proceso terminado", 
								JOptionPane.INFORMATION_MESSAGE);
						progressBar.setValue(0);
						btnCancel.setEnabled(true);
						btnExit.setEnabled(true);
					}
				}).start();
			} 
		} else if (e.getSource() == btnExit) {
			System.exit(0);
		}
	}

	private boolean validateForm() {
		boolean validForm = numberVerifierClaveEmpleado.verify(txtfClaveEmpleado) 
				& letterVerifierTipo.verify(txtfTipoNomina)
				& numberVerifierPeriodo.verify(txtfPeriodo)
				& numberVerifierAnio.verify(txtfAnio);
		
		if (!validForm) {
			JOptionPane.showMessageDialog(this, 
					"Los parámetros no son válidos, verifique por favor.",
					"Error", 
					JOptionPane.ERROR_MESSAGE);
		}
		return validForm;
	}

	private Nomina getNominaACancelar() {
		int claveEmpleado = 0;
		if (!txtfClaveEmpleado.getText().trim().isEmpty()) {
			claveEmpleado = Integer.parseInt(txtfClaveEmpleado.getText());
		}
		String tipoNomina = txtfTipoNomina.getText();
		int periodo = Integer.parseInt(txtfPeriodo.getText());
		int anio = Integer.parseInt(txtfAnio.getText());
		Nomina nomina = new Nomina();
		nomina.setClaveEmpleado(claveEmpleado);
		nomina.setTipoNomina(tipoNomina);
		nomina.setPeriodo(periodo);
		nomina.setAnio(anio);
		nomina.setProgress(progressBar);
		return nomina;
	}

	private boolean confirmCancel() {
		boolean result = JOptionPane.showConfirmDialog(this, 
				"Confirme que desea cancelar la nómina con los parámetros seleccionados", 
				"Confimación", 
				JOptionPane.OK_CANCEL_OPTION) == 0;
		return result;
	}
}
