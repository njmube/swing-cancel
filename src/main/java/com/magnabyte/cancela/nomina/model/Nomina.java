package com.magnabyte.cancela.nomina.model;

import javax.swing.JProgressBar;

public class Nomina {
	private Integer claveEmpleado;
	private String tipoNomina;
	private Integer periodo;
	private Integer anio;
	private JProgressBar progress;

	public Integer getClaveEmpleado() {
		return claveEmpleado;
	}

	public void setClaveEmpleado(Integer claveEmpleado) {
		this.claveEmpleado = claveEmpleado;
	}

	public String getTipoNomina() {
		return tipoNomina;
	}

	public void setTipoNomina(String tipoNomina) {
		this.tipoNomina = tipoNomina;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	
	public JProgressBar getProgress() {
		return progress;
	}
	
	public void setProgress(JProgressBar progress) {
		this.progress = progress;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Nomina [claveEmpleado=");
		builder.append(claveEmpleado);
		builder.append(", tipoNomina=");
		builder.append(tipoNomina);
		builder.append(", periodo=");
		builder.append(periodo);
		builder.append(", anio=");
		builder.append(anio);
		builder.append("]");
		return builder.toString();
	}
	
}
