package com.magnabyte.cancela.nomina.dao;

import java.util.List;

import com.magnabyte.cancela.nomina.model.Nomina;

public interface CancelaNominaDao {

	List<String> recuperarRegistrosACancelarPorEmpleado(Nomina nomina);

	List<String> recuperarRegistrosACancelar(Nomina nomina);

}
