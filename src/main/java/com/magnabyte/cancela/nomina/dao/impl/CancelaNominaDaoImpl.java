package com.magnabyte.cancela.nomina.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.magnabyte.cancela.nomina.dao.CancelaNominaDao;
import com.magnabyte.cancela.nomina.dao.GenericJdbcDao;
import com.magnabyte.cancela.nomina.dao.sql.CancelaNominaSql;
import com.magnabyte.cancela.nomina.model.Nomina;

@Repository
public class CancelaNominaDaoImpl extends GenericJdbcDao implements CancelaNominaDao {

	private static final Logger logger = Logger.getLogger(CancelaNominaDaoImpl.class);
	
	@Override
	public List<String> recuperarRegistrosACancelarPorEmpleado(Nomina nomina) {
		logger.debug("recuperarRegistrosACancelarEmpleado: " + nomina);
		return getJdbcTemplate().query(CancelaNominaSql.READ_BY_EMPLEADO, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(CancelaNominaSql.UUID);
			}
		}, nomina.getClaveEmpleado(), nomina.getTipoNomina(), nomina.getPeriodo(), nomina.getAnio());
	}
	
	@Override
	public List<String> recuperarRegistrosACancelar(Nomina nomina) {
		logger.debug("recuperarRegistrosACancelar: " + nomina);
		return getJdbcTemplate().query(CancelaNominaSql.READ_ALL, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(CancelaNominaSql.UUID);
			}
		}, nomina.getTipoNomina(), nomina.getPeriodo(), nomina.getAnio());
	}

}
