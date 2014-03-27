package com.magnabyte.cancela.nomina.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.magnabyte.cancela.nomina.dao.CancelaNominaDao;
import com.magnabyte.cancela.nomina.model.Nomina;

@Repository
public class CancelaNominaDaoImpl implements CancelaNominaDao {

	private static final Logger logger = Logger.getLogger(CancelaNominaDaoImpl.class);
	
	@Override
	public void recuperarRegistrosACancelar(Nomina nomina) {
		logger.debug("Cancela Nomina DAO: " + nomina);
	}

}
