package com.magnabyte.cancela.nomina.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Controller;

@Controller
public class CancelaNominaController extends JFrame {
	
	public CancelaNominaController() {
		initUI();
	}

	private void initUI() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel panelTop = new JPanel();
		
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));
		panelTop.setBackground(Color.white);
		panelTop.setPreferredSize(new Dimension(250, 150));
		
		JLabel labelEmpleado = new JLabel("Clave Empleado");
		JLabel labelEmpleados = new JLabel("Clave Empleado");
		
		
		panelTop.add(labelEmpleado);
		panelTop.add(Box.createHorizontalGlue());
		panelTop.add(labelEmpleados);
		panel.add(panelTop);
		
		panel.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));

		add(panel);
		pack();
		
		setTitle("Cancelación de Nómina");
		setSize(350, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
