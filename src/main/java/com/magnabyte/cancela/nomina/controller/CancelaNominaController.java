package com.magnabyte.cancela.nomina.controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.text.MaskFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.magnabyte.cancela.nomina.model.Nomina;
import com.magnabyte.cancela.nomina.service.CancelaNominaService;

@Controller
public class CancelaNominaController extends JFrame implements ActionListener {

	private static final long serialVersionUID = -886941888411579261L;

	@Autowired
	private CancelaNominaService cancelaNominaService;
	
	public static final int BORDER_PANEL = 20;
	
	JLabel lblClaveEmpleado, lblTipoNomina, lblPeriodo, lblAnio;
	JFormattedTextField txtfClaveEmpleado, txtfTipoNomina, txtfPeriodo, txtfAnio;
	JButton btnCancel, btnExit;
	
	public CancelaNominaController() {
		initUI();
	}

	private void initUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_PANEL, BORDER_PANEL, BORDER_PANEL, BORDER_PANEL));
		
		JPanel formPanel = new JPanel(new GridLayout(0, 2));
		formPanel.setPreferredSize(new Dimension(300, 150));
		
		JPanel bottomPanel = new JPanel();
		
		lblClaveEmpleado = new JLabel("Clave Empleado: ", JLabel.TRAILING);
		lblTipoNomina = new JLabel("Tipo Nómina: ", JLabel.TRAILING);
		lblPeriodo = new JLabel("Periodo: ", JLabel.TRAILING);
		lblAnio = new JLabel("Año: ", JLabel.TRAILING);

		txtfClaveEmpleado = new JFormattedTextField(getNumberFormat(6, 1));
		lblClaveEmpleado.setLabelFor(txtfClaveEmpleado);

		txtfTipoNomina = new JFormattedTextField(createFormatter("U"));
		lblTipoNomina.setLabelFor(txtfTipoNomina);
		
		txtfPeriodo = new JFormattedTextField(getNumberFormat(3, 1));
		lblPeriodo.setLabelFor(txtfPeriodo);
		
		txtfAnio = new JFormattedTextField(getNumberFormat(4, 4));
		lblClaveEmpleado.setLabelFor(txtfAnio);
		
		btnCancel = new JButton("Cancelar Nómina");
		btnCancel.setPreferredSize(new Dimension(140, 30));
		btnCancel.addActionListener(this);
		
		btnExit = new JButton("Salir");
		btnExit.setPreferredSize(new Dimension(140, 30));
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
		
		bottomPanel.add(btnCancel);
		bottomPanel.add(btnExit);
		
		formPanel.setOpaque(true);
		mainPanel.add(formPanel, BorderLayout.NORTH);
		mainPanel.add(new JSeparator());
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		add(mainPanel);
		pack();
		
		setTitle("Cancelación de Nómina");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	private MaskFormatter createFormatter(String mask) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(mask);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formatter;
	}

	private NumberFormat getNumberFormat(Integer maxDigits, Integer minDigits) {
		DecimalFormat numberFormat = (DecimalFormat) DecimalFormat.getInstance();
		numberFormat.setGroupingUsed(false);
		if (maxDigits != null) {
			numberFormat.setMaximumIntegerDigits(maxDigits);
		}
		if (minDigits != null) {
			numberFormat.setMinimumIntegerDigits(minDigits);
		}
		return numberFormat;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			if (confirmCancel()) {
				cancelaNominaService.recuperarRegistrosACancelar(getNominaACancelar());
				JOptionPane.showMessageDialog(this, 
						"La nómina se ha cancelado con éxito",
						"Cancelación existosa", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (e.getSource() == btnExit) {
			System.exit(0);
		}
	}

	private Nomina getNominaACancelar() {
		Nomina nomina = new Nomina();
		nomina.setClaveEmpleado(((Number) txtfClaveEmpleado.getValue()).intValue());
		nomina.setTipoNomina((String) txtfTipoNomina.getValue());
		nomina.setPeriodo(((Number) txtfPeriodo.getValue()).intValue());
		nomina.setAnio(((Number) txtfAnio.getValue()).intValue());
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
