<%@ include file="/init.jsp" %>

<portlet:resourceURL id="/prevencion/addProveedor" var="postAddProveedor" />

<div class="row">
	<div class="col-12 text-center">
		<p class="font-weight-bold h1-responsive">Evaluaci&oacute;n de Proveedor</p>
		<p class="font-weight-bold h2-responsive">${ tipoVigente.descripcion }</p>
	</div>
</div>

<div class="container mt-5" id="contenProveedor">
	<div class="row mt-5">
		<div class="col-md-4 text-center mt-3">
			<p class="font-weight-bold h5-responsive">Proveedor a evaluar</p>
		</div>
		<div class="col-md-4">
			<div class="md-form form-group">
				<select id="selProveedor" name="selProveedor" class="mdb-select md-form" searchable="Buscar">
					<option value="-1" selected disabled>Seleccionar opci&oacute;n</option>
					<c:forEach items="${listProveedor}" var="opc" varStatus="loop">
						<option value="${opc.proveedorId}">${opc.nombre}</option>
					</c:forEach>
					<c:forEach items="${listTransportistas}" var="opc" varStatus="loop">
						<option value="${opc.codigo}">${opc.nombre}</option>
					</c:forEach>
				</select>
				<label for="selProveedor" class="mdb-main-label">Seleccione proveedor</label>
			</div>
		</div>
		<div class="col-md-4 text-center">
			<button onclick="$('#modalAddProveedor').modal('show');" class="btn btn-blue" id="provNew" >Nuevo Proveedor</button>
		</div>
	</div>
	
	<div class="row mt-5">
		<div class="col-sm-12 text-right mt-5">
			<button onclick="regresaPantalla(0)" class="btn btn-pink" id="ProvCancel" >Cancelar</button>
			<button onclick="submitBtn()" class="btn btn-blue" id="provDone" disabled>Aceptar</button>
		</div>
	</div>
</div>

<form id=formBtn1 method="POST" class="hidden">
	<input type="hidden" id="modoVista" name="modoVista" value="2"/>
	<input type="hidden" id="proveedorId" name="proveedorId"/>
	<input type="hidden" id="idTipo" name="idTipo" value="${tipoVigente.idTipo}"/>
	<button id="btnDataProv" type="submit"></button>
</form>

<div id="etiquetas" hidden="true">
	<input type="hidden" id="postAddProveedor" value="${postAddProveedor}">
</div>

<script src="<%=request.getContextPath()%>/js/proveedor.js?v=${version}"></script>