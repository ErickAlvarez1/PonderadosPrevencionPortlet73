package com.tokio.prevencion.ponderadosprevencion73.bean;

public class Respuesta {
	private int idPregunta;
	private int respuesta;
	private int estatusDoc;
	private boolean evidencia;
	private String comentrio;
	
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
	public boolean isEvidencia() {
		return evidencia;
	}
	public void setEvidencia(boolean evidencia) {
		this.evidencia = evidencia;
	}
	public String getComentrio() {
		return comentrio;
	}
	public void setComentrio(String comentrio) {
		this.comentrio = comentrio;
	}
	
	@Override
	public String toString() {
		return "Respuesta [idPregunta=" + idPregunta + ", respuesta=" + respuesta + ", estatusDoc=" + estatusDoc
				+ ", evidencia=" + evidencia + ", comentrio=" + comentrio + "]";
	}
	
}
