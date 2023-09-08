package com.tokio.prevencion.ponderadosprevencion73.bean;

import java.util.Date;

public class Resultado {
	private int id;
	private int idRespuesta;
	private int idPonderado;
	private int idPregunta;	
	private int respuesta;
	private int estatusDoc;
	private String comentrio;
	private int idSolicitud;
	private int idTransportista;
	private int vResultado;
	private boolean activo;
	private Date fechaCreacion;
	private int vCuestionario;
	private boolean evidencia;
	private String descripcion;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdRespuesta() {
		return idRespuesta;
	}
	public void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}
	public int getIdPonderado() {
		return idPonderado;
	}
	public void setIdPonderado(int idPonderado) {
		this.idPonderado = idPonderado;
	}
	public int getIdPregunta() {
		return idPregunta;
	}
	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}
	public int getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}
	public int getEstatusDoc() {
		return estatusDoc;
	}
	public void setEstatusDoc(int estatusDoc) {
		this.estatusDoc = estatusDoc;
	}
	public String getComentrio() {
		return comentrio;
	}
	public void setComentrio(String comentrio) {
		this.comentrio = comentrio;
	}
	public int getIdSolicitud() {
		return idSolicitud;
	}
	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	public int getIdTransportista() {
		return idTransportista;
	}
	public void setIdTransportista(int idTransportista) {
		this.idTransportista = idTransportista;
	}
	public int getvResultado() {
		return vResultado;
	}
	public void setvResultado(int vResultado) {
		this.vResultado = vResultado;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public int getvCuestionario() {
		return vCuestionario;
	}
	public void setvCuestionario(int vCuestionario) {
		this.vCuestionario = vCuestionario;
	}
	public boolean isEvidencia() {
		return evidencia;
	}
	public void setEvidencia(boolean evidencia) {
		this.evidencia = evidencia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "Resultado [id=" + id + ", idRespuesta=" + idRespuesta + ", idPonderado=" + idPonderado + ", idPregunta="
				+ idPregunta + ", respuesta=" + respuesta + ", estatusDoc=" + estatusDoc + ", comentrio=" + comentrio
				+ ", idSolicitud=" + idSolicitud + ", idTransportista=" + idTransportista + ", vResultado=" + vResultado
				+ ", activo=" + activo + ", fechaCreacion=" + fechaCreacion + ", vCuestionario=" + vCuestionario
				+ ", evidencia=" + evidencia + ", descripcion=" + descripcion + "]";
	}
		
}
