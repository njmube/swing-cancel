package com.magnabyte.cancela.nomina.service.impl;

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
		cancelaNominaDao.recuperarRegistrosACancelar(nomina);
	}
}
