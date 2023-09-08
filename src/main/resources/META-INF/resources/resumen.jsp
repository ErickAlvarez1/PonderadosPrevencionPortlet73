<%@ include file="/init.jsp"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<portlet:resourceURL id="/prevencion/activaC" var="postActivaC" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css?v=${version}"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-12 text-center">
            <p class="font-weight-bold h1-responsive">RESUMEN DE PONDERADO</p>
        </div>
    </div>
    <div class="row">
        <div class="col-12 mt-4 text-center">
            <p class="font-weight-bold h1-responsive">${ tipoVigente.descripcion }</p>
        </div>
    </div>
    <div class="row">
        <div class="col-12 mt-4 text-center">
            <p class="font-weight-bold h1-responsive">${ nombreTransporte }</p>
        </div>
    </div>

    <!-- 	<div class="container"> -->
    <div class="row mt-5" style="padding: 2%;">
        <div class="col-md-6" style="display: flex;align-items: center;">
            <div class="row">
                <div class="col-md-12" style="background-color: #4285f4cf !important;padding: 10px;border: solid 1px;">
                    <p class="text-center" style="color: white !important;margin: 0;font-weight: bold;">" ESCALA DE RIESGOS POR TMX "</p>
                </div>
                <div class="col-md-10 text-justify" style="border: solid 1px;">
                    <p>Empresa con nulos controles de seguridad y alta factibilidad de siniestralidad.</p>
                </div>
                <div class="col-md-2 text-center" style="border: solid 1px;">
                    <p>0 - 20%</p>
                </div>
                <div class="col-md-10 text-justify" style="border: solid 1px;">
                    <p>Empresa con bajos controles de seguridad y probables incidencias de siniestros.</p>
                </div>
                <div class="col-md-2 text-center" style="border: solid 1px;">
                    <p>21 - 40%</p>
                </div>
                <div class="col-md-10 text-justify" style="border: solid 1px;">
                    <p>Empresa con medianos controles de seguridad con riesgos controlables, quie el cliente puede desarrollar.</p>
                </div>
                <div class="col-md-2 text-center" style="border: solid 1px;">
                    <p>41 - 60%</p>
                </div>
                <div class="col-md-10 text-justify" style="border: solid 1px;">
                    <p>Empresa con altos controles de seguridad, baja incidencia de siniestralidad.</p>
                </div>
                <div class="col-md-2 text-center" style="border: solid 1px;">
                    <p>61 - 80%</p>
                </div>
                <div class="col-md-10 text-justify" style="border: solid 1px;">
                    <p>Empresa con controles de seguridad por encima del mercado y casi nula siniestralidad.</p>
                </div>
                <div class="col-md-2 text-center" style="border: solid 1px;">
                    <p>81 - 99%</p>
                </div>
            </div>
        </div>

        <div class="col-md-6 radar-container">
            <div class="chart-container">
                <canvas id="radarChart"></canvas>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="row mt-5">
                <div class="col-md-12" style="background-color: #4285f4cf !important;padding: 10px;border: solid 1px;">
                    <p class="text-center" style="color: white !important;margin: 0;font-weight: bold;"> MATRIZ DE EVALUACI&Oacute;N </p>
                </div>

                <div class="col-md-4" style="background-color: #72a5fbcf !important;padding: 10px;border: solid 1px;">
                    <p class="text-center" style="color: white !important;margin: 0;font-weight: bold;">ELEMENTOS EVALUADOS EN LA OPERACI&Oacute;N</p>
                </div>
                <div class="col-md-2" style="background-color: #72a5fbcf !important;padding: 10px;border: solid 1px;">
                    <p class="text-center" style="color: white !important;margin: 0;font-weight: bold;">% DE IMPORTANCIA</p>
                </div>
                <div class="col-md-2" style="background-color: #72a5fbcf !important;padding: 10px;border: solid 1px;">
                    <p class="text-center" style="color: white !important;margin: 0;font-weight: bold;">M&Aacute;XIMA CALIFICACI&Oacute;N</p>
                </div>
                <div class="col-md-2" style="background-color: #72a5fbcf !important;padding: 10px;border: solid 1px;">
                    <p class="text-center" style="color: white !important;margin: 0;font-weight: bold;">CALIFICACI&Oacute;N ALCANZADA</p>
                </div>
                <div class="col-md-2" style="background-color: #72a5fbcf !important;padding: 10px;border: solid 1px;">
                    <p class="text-center" style="color: white !important;margin: 0;font-weight: bold;">% DE CONFIANZA</p>
                </div>

            </div>

            <div id="contenResumen">
                <!-- Contenido llenado desde JS -->
            </div>

            <div class="row .califFinal">
                <div class="col-md-4">
                    <p></p>
                </div>
                <div class="col-md-2 text-center">
                    <p>VALORES TOTALES</p>
                </div>
                <div class="col-md-2 text-center">
                    <!-- 						<p>9701</p> -->
                    <p id="pMax"></p>
                </div>
                <div class="col-md-2 text-center" style="border: solid 1px;">
                    <!-- 						<p>7709</p> -->
                    <p id="pObt"></p>
                </div>
                <div class="col-md-2 text-center" style="background-color: #72a5fbcf !important; border: solid 1px;">
                    <!-- 						<p>79%</p> -->
                    <p id="pTot"></p>
                </div>
            </div>

        </div>

        <div class="col-md-6 divCanvas" style="display: flex;align-items: center;">
            <canvas id="horizontalBar" class="divCanvasBar mt-5"></canvas>
        </div>

    </div>
    <!-- 	</div> -->

    <!--pantalla resumen-->
    <div class="row">
        <div id="resumenEvaluacion" class="table-responsive text-nowrap mt-5">
            <table class="table table-striped fixed_headers" id="tablaEvaluacion">
                <thead style="background-color: #01579b; color: white; font-weight: bold;">
                <tr>
                    <th>%Confianza</th>
                    <th>ITEM</th>
                    <th>%</th>
                    <th class="widthTh">Criterios de evaluaci&oacute;n</th>
                    <th>Evaluaci&oacute;n inicial</th>
                    <th>No cumple<br>0%
                    </th>
                    <th>Deficiente<br>25%
                    </th>
                    <th>Requiere mejoras<br>50%
                    </th>
                    <th>Aceptable<br>75%
                    </th>
                    <th>Confiable<br>99%
                    </th>
                    <th>Ponderaci&oacute;n</th>
                    <th>Puntaje m&aacute;ximo</th>
                    <th>Estatus de documentaci&oacute;n</th>
                    <th>Comentario auditor</th>
                    <th>Comentarios Equipo 20-05-20199</th>
                </tr>
                </thead>

                <!--Table body-->
                <tbody>

                <c:forEach items="${cuestionario}" var="opc" varStatus="loop">
                    <tr class="back-blue-tmx a-${loop}">
                        <th></th>
                        <th>${loop.index + 1}</th>
                        <th id="tema-porcen-${loop.index + 1}"><span class="porcTema">${opc.porcentaje}</span>%<span></span></th>
                        <th id="table-tema-${loop.index + 1}" class="titTema widthTh txtTema">${opc.descripcion}</th>
                        <th>Docs Control</th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th><a style="font-size: 25px;" class="text-center acord" acord="${loop.index}"><i class="fas fa-chevron-down" style="min-width: 100%; min-height: 100%;"></i></a></th>
                    </tr>

                    <c:set var="txtSub" value="${''}" />
                    <c:set var="total" value="${0}"/>
                    <c:set var="count" value="${0}"/>

                    <c:forEach items="${opc.listSubtema}" var="subOpc" varStatus="subLoop">

                        <c:set var="txtCurSub" value="${subOpc.descripcion}" />
                        <c:set var="valid" value="${txtSub == subOpc.descripcion ? 'hide' : ''}" />

                        <c:forEach items="${subOpc.listRespuesta}" var="pregOpc" varStatus="pregLoop">
                            <c:set var="count" value="${count + 1}"/>
                            <c:set var="txtSub" value="${txtSub == txtCurSub ? txtSub : txtCurSub}" />

                            <tr class="d-none acordConten" acord="${loop.index}">
                                <th></th>
                                <th></th>
                                <th></th>
                                <th id="subTableTxt-${loop.index + 1}-${subLoop.index + 1}-${pregLoop.index + 1}" class="widthTh"><span class="${valid}">${txtCurSub}</span></th>
                                <th>${pregOpc.descripcion}</th>
                                <c:set var="aux1" value="${pregOpc.respuesta == 1 ? 'X' : ''}" />
                                <th class="text-center group-${loop.index + 1}-${subLoop.index + 1} opc-${loop.index + 1}-${pregOpc.idPregunta}-1">${aux1}</th>
                                <c:set var="aux2" value="${pregOpc.respuesta == 2 ? 'X' : ''}" />
                                <th class="text-center group-${loop.index + 1}-${subLoop.index + 1} opc-${loop.index + 1}-${pregOpc.idPregunta}-2">${aux2}</th>
                                <c:set var="aux3" value="${pregOpc.respuesta == 3 ? 'X' : ''}" />
                                <th class="text-center group-${loop.index + 1}-${subLoop.index + 1} opc-${loop.index + 1}-${pregOpc.idPregunta}-3">${aux3}</th>
                                <c:set var="aux4" value="${pregOpc.respuesta == 4 ? 'X' : ''}" />
                                <th class="text-center group-${loop.index + 1}-${subLoop.index + 1} opc-${loop.index + 1}-${pregOpc.idPregunta}-4">${aux4}</th>
                                <c:set var="aux5" value="${pregOpc.respuesta == 5 ? 'X' : ''}" />
                                <th class="text-center group-${loop.index + 1}-${subLoop.index + 1} opc-${loop.index + 1}-${pregOpc.idPregunta}-5">${aux5}</th>
                                <c:choose>
                                    <c:when test="${pregOpc.respuesta == 1}">
                                        <c:set var="ponde" value="${0}" />
                                    </c:when>
                                    <c:when test="${pregOpc.respuesta == 2}">
                                        <c:set var="ponde" value="${25}" />
                                    </c:when>
                                    <c:when test="${pregOpc.respuesta == 3}">
                                        <c:set var="ponde" value="${50}" />
                                    </c:when>
                                    <c:when test="${pregOpc.respuesta == 4}">
                                        <c:set var="ponde" value="${75}" />
                                    </c:when>
                                    <c:when test="${pregOpc.respuesta == 5}">
                                        <c:set var="ponde" value="${99}" />
                                    </c:when>
                                </c:choose>
                                <c:set var="total" value="${total + ponde}" />
                                <th class=" text-center pondeRes-group-${loop.index + 1}-`+auxNoPregunta+` tot-${loop.index + 1}">${ponde}</th>
                                <th></th>
                                <c:choose>
                                    <c:when test="${pregOpc.estatusDoc == 1}">
                                        <c:set var="estatus" value="${'Documentos vigentes'}" />
                                    </c:when>
                                    <c:when test="${pregOpc.estatusDoc == 2}">
                                        <c:set var="estatus" value="${'Documentos en elaboración'}" />
                                    </c:when>
                                    <c:when test="${pregOpc.estatusDoc == 3}">
                                        <c:set var="estatus" value="${'Documentos en revisión'}" />
                                    </c:when>
                                    <c:when test="${pregOpc.estatusDoc == 4}">
                                        <c:set var="estatus" value="${'Procedimiento en elaboración'}" />
                                    </c:when>
                                    <c:when test="${pregOpc.estatusDoc == 5}">
                                        <c:set var="estatus" value="${'Procedimiento en revisión'}" />
                                    </c:when>
                                    <c:when test="${pregOpc.estatusDoc == 6}">
                                        <c:set var="estatus" value="${'Procedimiento vigente'}" />
                                    </c:when>
                                    <c:when test="${pregOpc.estatusDoc == -1}">
                                        <c:set var="estatus" value="${''}" />
                                    </c:when>
                                </c:choose>
                                <th class="text-center est_doc-${loop.index + 1}-${pregLoop.index + 1}`">${estatus}</th>
                                <th class="text-center txt_audi-${loop.index + 1}-${pregLoop.index + 1}">${pregOpc.comentrio}</th>
                                <th></th>
                            </tr>
                        </c:forEach>

                        <c:set var="totalMax" value="${99 * count}" />

                    </c:forEach>

                    <c:set var="confianza" value="${(total / totalMax) * 100}" />

                    <tr style="background-color: rgb(255, 116, 140); color: white; font-weight: bold; height: 38px;" id="resTr-`+auxTema+`-`+auxNoPregunta+`">
                        <th class="text-center"><span class="resTit"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${confianza}" /></span><span>%</span></th>
                        <th></th>
                        <th></th>
                        <th class="widthTh"></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th id="tot-${loop.index + 1}" class="divs-tot text-center resTema">${total}</th>
                        <th id="tot-max-${loop.index + 1}" class="text-center maxTema">${totalMax}</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>

                </c:forEach>


                </tbody>
                <!--/Table body-->

            </table>

        </div>
    </div>
    <div class="row justify-content-center ${hideBtn1}">
        <div class="col-md-2">
            <button type="button"
                    class="btn btn-blue waves-effect waves-light mt-5"
                    onclick="regresaPantalla(2)">Editar</button>
        </div>
        <div class="col-md-2">
            <button id="backSol" type="button"
                    class="btn btn-pink waves-effect waves-light mt-5"
                    onclick="refreshPage()">Finalizar</button>
        </div>
    </div>
    <div class="row justify-content-center ${hideBtn2}">
        <div class="col-md-2">
            <button type="button"
                    class="btn btn-blue waves-effect waves-light mt-5"
                    onclick="regresaPantalla(0)">Regresar</button>
        </div>
        <div class="col-md-2">
            <button id="backSol" type="button"
                    class="btn btn-pink waves-effect waves-light mt-5"
                    onclick="submitBtn()">Actualizar</button>
        </div>
    </div>
    <div class="row justify-content-center ${hideBtn3}">
        <div class="col-md-2">
            <button type="button"
                    class="btn btn-blue waves-effect waves-light mt-5"
                    onclick="regresaPantalla(0)">Regresar</button>
        </div>
    </div>
</div>

<form id=formBtn1 method="POST" class="hidden">
    <input type="hidden" id="modoVista" name="modoVista" value="2"/>
    <input type="hidden" id="proveedorId" name="proveedorId" value="${proveedorId}">
    <input type="hidden" id="idTipo" name="idTipo" value="${tipoVigente.idTipo}"/>
    <input type="hidden" id="retornoResumen" name="retornoResumen" value="false"/>
    <button id="btnActualizar" type="submit"></button>
</form>

<!-- MODAL CONFIRMAR -->
<div class="modal" id="modalVersion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <!--Content-->
        <div class="modal-content">
            <!--Header-->
            <div class="modal-header blue-gradient text-white">
                <h3 class="heading lead"> VERSI&Oacute;N GENERADA </h3>

                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true" class="white-text">&times;</span>
                </button>
            </div>

            <!--Body-->
            <div class="modal-body">
                <div class="row rowEditTemas">
                    <div class="col-md-12 text-center text-primary font-weight-bold">
                        <h3>Se guardo la versi&oacute;n</h3>
                        <h4 id="versionM"></h4>
                    </div>

                </div>

            </div><!--  -->
            <!--Footer-->
            <div class="modal-footer justify-content-center blue-gradient">
                <button type="button" class="btn btn-pink" style="display: none;">Cancelar</button>
                <!-- 				<button onclick="history.go(-1);" type="button" class="btn btn-pink" data-dismiss="modal">Cancelar</button> -->
                <button onclick="" type="button" data-dismiss="modal" class="btn btn-blue">Aceptar</button>
            </div>
        </div>
        <!--/.Content-->
    </div>
</div>
<!-- MODAL CONFIRMAR -->

<div id="etiquetas" hidden="true">
    <input type="hidden" id="postActivaC" value="${postActivaC}">
    <input type="hidden" id="vCuestionario" value="${tipoVigente.VCuestionario}">
    <input type="hidden" id="intConfianta">
</div>

<script src="<%=request.getContextPath()%>/js/objetos.js?v=${version}"></script>
<script src="<%=request.getContextPath()%>/js/resumen.js?v=${version}"></script>

<script>
    var idRespuesta = '${idRespuesta}';
    /*var postActivaC = '${postActivaC}'';*/
    var listRespuesta = '${listRespuesta}';
</script>


<style type="text/css">
    th,
    td {
        white-space: normal;
    }

    div.dataTables_wrapper {
        width: 100%;
        margin: 0 auto;
        text-align: center;
    }

    td.docs {
        text-align: left;
        border-right: groove;
    }

    table.dataTable.fixed-datatable {
        margin-top: 0px!important;
        margin-bottom: 0px!important;
    }

    .site-wrapper .container-fluid {
        width: 100%;
        padding-right: 0px;
        padding-left: 0px;
        margin-right: auto;
        margin-left: auto;
    }

    .site-wrapper #rowSteppers .stepper li.completed a .circle {
        background-color: #388e3c !important;
    }

    .site-wrapper .incomplete {
        color: red;
    }

    #tablaEvaluacion .back-blue-tmx {
        background-color: rgb(42, 133, 242);
    }

    #resumenEvaluacion{
        /**make table can scroll**/
        max-height: 800px;
        overflow: auto;
    }
</style>