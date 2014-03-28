package com.magnabyte.cancela.nomina.utils;

import java.awt.Color;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class NumberVerifier extends InputVerifier {
	
	private int maxDigits, minDigits;
	private boolean required;
	
	public NumberVerifier() {
	}
	
	public NumberVerifier(boolean required) {
		this.required = required;
	}
	
	public NumberVerifier(int maxDigits, int minDigits) {
		this(maxDigits, minDigits, true);
	}
	
	public NumberVerifier(int maxDigits, int minDigits, boolean required) {
		this.maxDigits = maxDigits;
		this.minDigits = minDigits;
		this.required = required;
	}
	
	@Override
	public boolean verify(JComponent input) {
		JTextField field = (JTextField) input;
		
		if (!required && field.getText().trim().isEmpty()) {
			input.setBackground(UIManager.getColor("TextField.background"));
			return true;
		}
		
		try {
			Integer.parseInt(field.getText());
		} catch (NumberFormatException ex) {
			input.setBackground(Color.pink);
			return false;
		}
		
		if (maxDigits > 0 || minDigits > 0) {
			if (maxDigits > 0) {
				if (field.getText().length() > maxDigits) {
					input.setBackground(Color.pink);
					return false;
				}
			}
			
			if (minDigits > 0) {
				if (field.getText().length() < minDigits) {
					input.setBackground(Color.pink);
					return false;
				}
			}
			
		}
		input.setBackground(UIManager.getColor("TextField.background"));
		return true;
	}
}
