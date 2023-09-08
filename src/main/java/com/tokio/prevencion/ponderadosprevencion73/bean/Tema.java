package com.tokio.prevencion.ponderadosprevencion73.bean;

import java.util.List;

public class Tema {
	private int idTema;
	private String descripcion;
	private int orden;
	private int idEvaluacion;
	private int porcentaje;
	private List<Subtema> listSubtema;
	
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
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public int getIdEvaluacion() {
		return idEvaluacion;
	}
	public void setIdEvaluacion(int idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}
	public int getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}
	public List<Subtema> getListSubtema() {
		return listSubtema;
	}
	public void setListSubtema(List<Subtema> listSubtema) {
		this.listSubtema = listSubtema;
	}
	
	@Override
	public String toString() {
		return "Tema [idTema=" + idTema + ", descripcion=" + descripcion + ", orden=" + orden + ", idEvaluacion="
				+ idEvaluacion + ", porcentaje=" + porcentaje + ", listSubtema=" + listSubtema + "]";
	}
	
}
