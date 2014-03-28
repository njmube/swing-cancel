package com.magnabyte.cancela.nomina.dao.sql;

public class CancelaNominaSql {
	public static final String EOL = "\n";
	public static final String UUID = "uuid";
	public static final String READ_BY_EMPLEADO;
	public static final String READ_ALL;
	
	static {
		StringBuilder qryBuilder = new StringBuilder();
		
		qryBuilder.append("select uuid from nomina_cfdi").append(EOL);
		qryBuilder.append("where num_empleado = ?").append(EOL);
		qryBuilder.append("and tipo_nomina = UPPER(?)").append(EOL);
		qryBuilder.append("and periodo = ?").append(EOL);
		qryBuilder.append("and anio = ?").append(EOL);
		
		READ_BY_EMPLEADO = qryBuilder.toString();
		
		qryBuilder = new StringBuilder();
		
		qryBuilder.append("select uuid from nomina_cfdi").append(EOL);
		qryBuilder.append("where tipo_nomina = UPPER(?)").append(EOL);
		qryBuilder.append("and periodo = ?").append(EOL);
		qryBuilder.append("and anio = ?").append(EOL);
		
		READ_ALL = qryBuilder.toString();
		
	}
}
