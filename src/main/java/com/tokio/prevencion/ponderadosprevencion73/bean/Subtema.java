package com.tokio.prevencion.ponderadosprevencion73.bean;

import java.util.List;

public class Subtema {
	private int idSubtema;
	private int orden;
	private int idTema;
	private String descripcion;
	private List<Pregunta> listPregunta;
	private List<Resultado> listRespuesta;
	
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
	public int getIdTema() {
		return idTema;
	}
	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Pregunta> getListPregunta() {
		return listPregunta;
	}
	public void setListPregunta(List<Pregunta> listPregunta) {
		this.listPregunta = listPregunta;
	}
	public List<Resultado> getListRespuesta() {
		return listRespuesta;
	}
	public void setListRespuesta(List<Resultado> listRespuesta) {
		this.listRespuesta = listRespuesta;
	}
	
	@Override
	public String toString() {
		return "Subtema [idSubtema=" + idSubtema + ", orden=" + orden + ", idTema=" + idTema + ", descripcion="
				+ descripcion + ", listPregunta=" + listPregunta + ", listRespuesta=" + listRespuesta + "]";
	}
	
}
