<%@ include file="/init.jsp" %>

<portlet:resourceURL id="/prevencion/createPonderado" var="postCreatePonderado" />
<portlet:resourceURL id="/prevencion/savePonderado" var="postSavePonderado" />
<portlet:resourceURL id="/prevencion/createEditVinculo" var="postVinculo" />

<div class="container" id="containCuestionario">
<!-- 	<div class="row"> -->
<!-- 		<div class="col-12 text-center"> -->
<!-- 			<p class="font-weight-bold h1-responsive">PONDERADOSPREVENCIONPORTLET73 - Prevenci&oacute;n</p> -->
<!-- 		</div> -->
<!-- 	</div> -->
	
	<section id="formatoEvalucaion">
	
		<div class="container">
			<div class="row">
				<div class="col-12 mt-4 text-center">
					<c:set var="versionC" value="${vinculo.version >= 0 ? vinculo.version : '-'}" />
					<p class="font-weight-bold h1-responsive">Formato de evaluaci&oacute;n ${tipoVigente.descripcion} V${tipoVigente.VCuestionario}.${versionC}</p>
				</div>
				<div class="col-12 mt-4 text-center">
					<p class="font-weight-bold h1-responsive">${ nombreTransporte }</p>
				</div>
			</div>
			
			<div style="position: relative;">
				<liferay-ui:success key="msjExito" message="${exitoMsg}" />
				<liferay-ui:error key="errorConocido" message="${errorMsg}" />
			</div>
			
			<div class="row" hidden="true">
				<div class="col-md-6">
					<div class="md-form form-group">
						<select name="selectHidden" id="selectHidden" class="mdb-select form-control-sel">
							<option value="0">Seleccione una opci&oacute;n</option>
							<option value="1">Documentos vigentes</option>
						</select>
						<label for="selectHidden">Estatus de Documentaci&oacute;n</label>
					</div>
				</div>
	        </div>
		</div>
		
		<div class="container">
		    <!-- Horizontal Steppers -->
		    <div id="rowSteppers" class="row">
		        <div class="col-md-12">
		            <!-- Stepers Wrapper -->
		            <ul class="stepper stepper-horizontal">
						<!-- CONTENIDO DINAMICO LLENADO DESDE JS (ya no)-->
						<c:forEach items="${listTemas}" var="opc" varStatus="loop">
<%-- 							<c:if test="${!loop.last}">,</c:if> --%>
							<c:set var="auxLast" value="${loop.last ? 'fin' : opc.orden}" />
							<li class="step step-${opc.orden} active">
								<a onclick="showForm('${auxLast}')" dataNum="${opc.orden}" data-toggle="tooltip" data-placement="bottom" title="${opc.descripcion}">
								<span class="circle">${opc.orden}</span>
								</a>
							</li>
						</c:forEach>
		            </ul>
		            <!-- /.Stepers Wrapper -->
		
		        </div>
		    </div>
		    <!-- /.Horizontal Steppers -->
		</div>
		
		<div id="divForms"class="container">
			<!--pantalla 1-->
			
			<c:forEach items="${cuestionario}" var="opc" varStatus="loop">
				<form id="form${opc.orden}" onSubmit="JavaScript:return false">
					<div class="row">
						<div class="col-md-12 text-center">
							<p id="tema-${opc.orden}" class="font-weight-bold h5-responsive text-center ponde-titulo">${opc.descripcion}</p>
						</div>
					</div>
					
					<c:forEach items="${opc.listSubtema}" var="subOpc" varStatus="subLoop">
						<div class="row">
							<div class="col-md-12 text-center mt-5">
								<p id="sub-${opc.orden}-${subOpc.orden}"class="font-weight-bold h5-responsive text-center ponde-subtema">${subOpc.descripcion}</p>
							</div>
						</div>
						
						<c:forEach items="${subOpc.listPregunta}" var="pregOpc" varStatus="pregLoop">
							
							<div class="row">
								<div class="col-md-12 mt-3">
									<p class="ponde-pregunta">${pregOpc.orden}.- ${pregOpc.descripcion}</p>
								</div>
							</div>
							
							<div class="divPregunta row d-flex justify-content-around group-${opc.orden}-${subOpc.orden}-${pregOpc.orden}" data="${pregOpc.idPregunta}" aux="${opc.orden}-${subOpc.orden}-${pregOpc.orden}">
								<div class="col-md-2 text-center">
									<div class="custom-control custom-radio" style="text-align: center;">
										<input type="radio" class="form-check-input opcCheck" id="opc-${opc.orden}-${subOpc.orden}-${pregOpc.orden}-1" name="group-${opc.orden}-${subOpc.orden}-${pregOpc.orden}" value="1" data="0">
										<label class="custom-control-label" for="opc-${opc.orden}-${subOpc.orden}-${pregOpc.orden}-1">No cumple 0%</label>
									</div>
								</div>
								<div class="col-md-2 text-center">
									<div class="custom-control custom-radio ">
										<input type="radio" class="form-check-input opcCheck" id="opc-${opc.orden}-${subOpc.orden}-${pregOpc.orden}-2" name="group-${opc.orden}-${subOpc.orden}-${pregOpc.orden}" value="2" data="25">
										<label class="custom-control-label" for="opc-${opc.orden}-${subOpc.orden}-${pregOpc.orden}-2">Deficiente 25%</label>
									</div>
								</div>
								<div class="col-md-2 text-center">
									<div class="custom-control custom-radio">
										<input type="radio" class="form-check-input opcCheck" id="opc-${opc.orden}-${subOpc.orden}-${pregOpc.orden}-3" name="group-${opc.orden}-${subOpc.orden}-${pregOpc.orden}" value="3" data="50">
										<label class="custom-control-label" for="opc-${opc.orden}-${subOpc.orden}-${pregOpc.orden}-3">Requiere mejoras 50%</label>
									</div>
								</div>
								<div class="col-md-2 text-center">
									<div class="custom-control custom-radio">
										<input type="radio" class="form-check-input opcCheck" id="opc-${opc.orden}-${subOpc.orden}-${pregOpc.orden}-4" name="group-${opc.orden}-${subOpc.orden}-${pregOpc.orden}" value="4" data="75">
										<label class="custom-control-label" for="opc-${opc.orden}-${subOpc.orden}-${pregOpc.orden}-4">Aceptable 75%</label>
									</div>
								</div>
								<div class="col-md-2 text-center">
									<div class="custom-control custom-radio">
										<input type="radio" class="form-check-input opcCheck" id="opc-${opc.orden}-${subOpc.orden}-${pregOpc.orden}-5" name="group-${opc.orden}-${subOpc.orden}-${pregOpc.orden}" value="5" data="99">
										<label class="custom-control-label" for="opc-${opc.orden}-${subOpc.orden}-${pregOpc.orden}-5">Confiable 99%</label>
									</div>
								</div>
								<div class="col-md-2 text-center">
<!-- 									<div class="form-check"> -->
<%-- 										<input type="checkbox" class="form-check-input eviCheck" id="eviden-${opc.orden}-${subOpc.orden}-${pregOpc.orden}"> --%>
<%-- 										<label class="form-check-label" for="eviden-${opc.orden}-${subOpc.orden}-${pregOpc.orden}">&iquest;Mostr&oacute; evidencia?</label> --%>
<!-- 									</div> -->
									<p>&iquest;Mostr&oacute; evidencia?</p>
									<div class="switch">
										<label>
											No
											<input class="eviCheck" id="eviden-${opc.orden}-${subOpc.orden}-${pregOpc.orden}" type="checkbox">
											<span class="lever"></span>
											Si
										</label>
									</div>
								</div>
							</div>
							<div id="selPregunta-${pregOpc.idPregunta}" class="row rowPregunta" data="${pregOpc.idPregunta}">
								<div class="col-md-6 mt-5">
									<div class="md-form form-group mt-4">
										<select name="est_doc-${opc.orden}-${subOpc.orden}-${pregOpc.orden}" id="est_doc-${opc.orden}-${subOpc.orden}-${pregOpc.orden}" class="mdb-select form-control-sel">
											<option value="-1">Seleccione una opci&oacute;n</option>
											<c:forEach items="${catEstatusDocs}" var="selOpc">
												<option value="${selOpc.id}">${selOpc.descripcion}</option>
											</c:forEach>
										</select>
										<label for="est_doc-${opc.orden}-${subOpc.orden}-${pregOpc.orden}">Estatus de Documentaci&oacute;n</label>
									</div>
								</div>
								<div class="col-md-6">
									<div class="md-form">
<%-- 										<input type="text" id="txt_audi-${opc.orden}-${subOpc.orden}-${pregOpc.orden}" name="txt_audi-${opc.orden}-${subOpc.orden}-${pregOpc.orden}" class="form-control input-txt"> --%>
										<textarea id="txt_audi-${opc.orden}-${subOpc.orden}-${pregOpc.orden}" name="txt_audi-${opc.orden}-${subOpc.orden}-${pregOpc.orden}" class="md-textarea form-control input-txt" rows="3" style="resize: both"></textarea>
										<label for="txt_audi-${opc.orden}-${subOpc.orden}-${pregOpc.orden}">Comentario Auditor:</label>
									</div>
								</div>
							</div>
							
						</c:forEach>
						
					</c:forEach>
					
					<c:if test="${loop.index == 0}">
						<div class="col-sm-12 text-right mt-5">
<%-- 							<a class="btn btn-pink waves-effect waves-light" id="form1_next" onclick="showForm(${loop.index + 2})">Continuar</a> --%>
							<a class="btn btn-pink waves-effect waves-light" id="form1_next" onclick="clickA(${loop.index + 2})">Continuar</a>
						</div>
					</c:if>
					<c:if test="${(loop.index != 0) && (!loop.last)}">
						<div class="col-sm-12 text-right mt-5">
							<a class="btn btn-blue waves-effect waves-light" id="form2_back" onclick="clickA(${loop.index})">Regresar</a>
							<a class="btn btn-pink waves-effect waves-light" id="form2_next" onclick="clickA(${loop.index + 2})">Continuar</a>
						</div>
					</c:if>
					<c:if test="${loop.last}">
						<div class="col-sm-12 text-right mt-5">
							<a class="btn btn-blue waves-effect waves-light" id="form9_back" onclick="clickA(${loop.index})">Regresar</a>
							<button class="btn btn-pink waves-effect waves-light btnFin" id="formLast_next" onclick="f_finalizar(${tipoVigente.idTipo})" disabled="disabled">Generar Resumen</button>
						</div>
					</c:if>
					
				</form>
			
			</c:forEach>
			
			
			<!--/pantalla 1-->
		</div>
		
	</section>
	
</div>

<!-- MODAL CONFIRMAR -->
<div class="modal" id="modalConfirma" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg modal-dialog-centered" role="document">
	<!--Content-->
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header blue-gradient text-white">
				<h3 class="heading lead"> ATENCI&Oacute;N </h3>
				
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true" class="white-text">&times;</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body">
				<div class="row rowEditTemas">
					<div class="col-md-12 text-center text-primary font-weight-bold">
						<h3>${exitoMsg}</h3>
						<h4>${exitoMsg2}</h4>
						<p class="mt5">&iquest;Desea actualizar la informaci&oacute;n?</p>
					</div>

				</div>

			</div><!--  -->
			<!--Footer-->
			<div class="modal-footer justify-content-center blue-gradient">
				<button type="button" class="btn btn-pink" style="display: none;">Cancelar</button>
				<button onclick="regresaPantalla(1)" type="button" class="btn btn-pink" data-dismiss="modal">Cancelar</button>
				<button onclick="llenaRespuestas();" type="button" class="btn btn-blue">Aceptar</button>
			</div>
		</div>
	<!--/.Content-->
	</div>
</div>
<!-- MODAL CONFIRMAR -->

<form id=formBtn1 method="POST" class="hidden">
	<input type="hidden" id="modoVista" name="modoVista" value="3"/>
	<input type="hidden" id="modoConsulta" name="modoConsulta" value="0"/>
	<input type="hidden" id="proveedorId" name="proveedorId" value="${proveedorId}"/>
	<input type="hidden" id="idTipo" name="idTipo" value="${tipoVigente.idTipo}"/>
	<input type="hidden" id="idTipoVigente" name="idTipo" value="${tipoVigente.idEvaluacion}"/>
	<input type="hidden" id="vCuestionarioId" name="vCuestionarioId"/>
	<button id="btnDataCuest" type="submit"></button>
</form>

<div id="etiquetas" hidden="true">
	<input type="hidden" id="idVinculo" value="${vinculo.id}">
	<input type="hidden" id="vCuestio" value="${tipoVigente.VCuestionario}">
	<input type="hidden" id="flagSave" value="${flagSave}">
	<input type="hidden" id="postCreatePonderado" value="${postCreatePonderado}">
	<input type="hidden" id="postSavePonderado" value="${postSavePonderado}">
	<input type="hidden" id="postVinculo" value="${postVinculo}">
</div>

<script>
	const retornoResumen = '${retornoResumen}';
	var listRespuesta = '${listResultado}';
</script>

<script src="<%=request.getContextPath()%>/js/cuestionario.js?v=${version}"></script>
<script src="<%=request.getContextPath()%>/js/objetos.js?v=${version}"></script>

<style type="text/css">
    .site-wrapper #rowSteppers .stepper li.completed a .circle {
        background-color: #388e3c !important;
    }
    
    .site-wrapper .incomplete {
        color: red;
    }
</style>