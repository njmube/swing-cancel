package com.magnabyte.cancela.nomina.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magnabyte.cancela.nomina.dao.CancelaNominaDao;
import com.magnabyte.cancela.nomina.model.Nomina;
import com.magnabyte.cancela.nomina.service.CancelaNominaService;

@Service
public class CancelaNominaServiceImpl implements CancelaNominaService {
	
	private static final Logger logger = Logger.getLogger(CancelaNominaServiceImpl.class);
	
	@Autowired
	private CancelaNominaDao cancelaNominaDao;
	
	@Override
	public void recuperarRegistrosACancelar(Nomina nomina) {
		logger.debug("Cancela Nomina Service: " + nomina);
		List<String> uuidListaACancelar = new ArrayList<String>();
		if (nomina.getClaveEmpleado() > 0) {
			uuidListaACancelar = cancelaNominaDao.recuperarRegistrosACancelarPorEmpleado(nomina);
		} else {
			uuidListaACancelar = cancelaNominaDao.recuperarRegistrosACancelar(nomina);
		}
		logger.debug(uuidListaACancelar.size());
		float progress = 0;
		float increment = Float.valueOf(100) / Float.valueOf(uuidListaACancelar.size());
		Process process;
		for (String uuid : uuidListaACancelar) {
			try {
				process = Runtime.getRuntime().exec(createCommandToExecute(uuid));
				process.waitFor();
				progress += increment;
				nomina.getProgress().setValue((int) progress);
			} catch (InterruptedException e) {
				logger.error("Ocurrió un error al ejecutar el comando para cancelar el uuid: " + uuid);
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error("Ocurrió un error al ejecutar el comando para cancelar el uuid: " + uuid);
				logger.error(e.getMessage());
			}
		}
		nomina.getProgress().setValue(100);
	}

	private String createCommandToExecute(String uuid) {
		StringBuilder builder = new StringBuilder();
		builder.append("sh run.java.cancel.nomina.sh ");
		builder.append(uuid);
		logger.debug(builder.toString());
		return builder.toString();
	}
}
