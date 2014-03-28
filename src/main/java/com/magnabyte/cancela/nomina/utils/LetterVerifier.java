package com.magnabyte.cancela.nomina.utils;

import java.awt.Color;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class LetterVerifier extends InputVerifier {

	private int maxLenght, minLenght;
	
	public LetterVerifier() {
	}
	
	public LetterVerifier(int maxLenght, int minLenght) {
		this.maxLenght = maxLenght;
		this.minLenght = minLenght;
	}

	@Override
	public boolean verify(JComponent input) {
		JTextField field = (JTextField) input;
		if (maxLenght > 0 || minLenght > 0) {
			if (field.getText().matches("[0-9]+")) {
				input.setBackground(Color.pink);
				return false;
			}
			
			if (maxLenght > 0) {
				if (field.getText().length() > maxLenght) {
					input.setBackground(Color.pink);
					return false;
				}
			}
			
			if (minLenght > 0) {
				if (field.getText().length() < minLenght) {
					input.setBackground(Color.pink);
					return false;
				}
			}
			
		}
		input.setBackground(UIManager.getColor("TextField.background"));
		return true;
	}

}
