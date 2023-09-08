function objResumen(nombre, porcen, califMax, calif, confianza){
	this.nombre = nombre;
	this.porcen = porcen;
	this.califMax = califMax;
	this.calif = calif;
	this.confianza = confianza;
}

function objRespuesta (idPregunta, respuesta, evidencia, estatusDoc, comentrio){
	this.idPregunta = idPregunta;
	this.respuesta = respuesta;
	this.evidencia = evidencia;
	this.estatusDoc = estatusDoc;
	this.comentrio = comentrio;
}