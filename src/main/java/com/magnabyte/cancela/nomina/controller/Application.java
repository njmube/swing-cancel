package com.magnabyte.cancela.nomina.controller;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.magnabyte.cancela.nomina.config.AppConfig;

public class Application {
	
	private static final Logger logger = Logger.getLogger(Application.class);

	public static void main(String[] args) {
		obtenerContexto();
	}

	private static void obtenerContexto() {
		logger.debug("en obtenerContexto");
		final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				CancelaNominaController cancelaNominaController = context.getBean(CancelaNominaController.class);
				cancelaNominaController.setVisible(true);
			}
		});
	}

}
