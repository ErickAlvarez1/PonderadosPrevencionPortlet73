package com.tokio.prevencion.ponderadosprevencion73.bean;

public class Pregunta {
	private int idPregunta;
	private int idSubtema;
	private int orden;
	private String descripcion;
	
	public int getIdPregunta() {
		return idPregunta;
	}
	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}
	public int getIdSubtema() {
		return idSubtema;
	}
	public void setIdSubtema(int idSubtema) {
		this.idSubtema = idSubtema;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "Pregunta [idPregunta=" + idPregunta + ", idSubtema=" + idSubtema + ", orden=" + orden + ", descripcion="
				+ descripcion + "]";
	}
	
}
