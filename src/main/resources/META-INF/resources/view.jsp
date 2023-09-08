<%@ include file="/init.jsp" %>
<jsp:include page="modales.jsp" />

<!-- <portlet:resourceURL id="/prevencion/getCatEstDosc" var="postCatEstatusDocs" /> -->
<!-- <portlet:resourceURL id="/prevencion/getFormulario" var="postGetFormulario" /> -->
<!-- <portlet:resourceURL id="/prevencion/getListTemas" var="postGetTemas" /> -->
<!-- <portlet:resourceURL id="/prevencion/getListSubtemas" var="postGetSubtemas" /> -->
<!-- <portlet:resourceURL id="/prevencion/getIdRespuesta" var="postGetIdRespuesta" /> -->
<!-- <portlet:resourceURL id="/prevencion/getPonderadoBase" var="postGetPonderadoBase" /> -->


<c:if test="${modoVista == 0}">
	<%@ include file="./menu.jsp" %>
</c:if> 

<c:if test="${modoVista == 1}">
	<%@ include file="./proveedor.jsp" %>
</c:if> 

<c:if test="${modoVista == 2}">
	<%@ include file="./cuestionario.jsp" %>
</c:if>

<c:if test="${modoVista == 3}">
	<%@ include file="./resumen.jsp" %>
</c:if>



<div id="etiquetas" hidden="true">
<%-- 	<input type="hidden" id="postCatEstatusDocs" value="${postCatEstatusDocs}"> --%>
<%-- 	<input type="hidden" id="postGetFormulario" value="${postGetFormulario}"> --%>
<%-- 	<input type="hidden" id="postGetTemas" value="${postGetTemas}"> --%>
<%-- 	<input type="hidden" id="postGetSubtemas" value="${postGetSubtemas}"> --%>
<%-- 	<input type="hidden" id="postGetIdRespuesta" value="${postGetIdRespuesta}"> --%>
<%-- 	<input type="hidden" id="postGetPonderadoBase" value="${postGetPonderadoBase}"> --%>
	<input type="hidden" id="httpSelect" value="${httpContent}">
	<input type="hidden" id="auxIdSolicitud" value="${idSolicitud}">
	<input type="hidden" id="auxPonderado" value="${auxPonderado}">
	<input type="hidden" id="idRespuesta" value="${idRespuesta}">
</div>

<script src="<%=request.getContextPath()%>/js/CustomPonderados.js?v=${version}"></script>




<script type="text/javascript">
	const modoVista = '${modoVista}';
    $(document).ready(function() {
        $('.owl-carousel.owl-carousel-custom-2').owlCarousel({
            loop: false,
            margin: 10,
            nav: false,
            dots: false,
            responsive: {
                0: {
                    items: 1
                },
                600: {
                    items: 1
                },
                1000: {
                    items: 1
                },
                1500: {
                    items: 1
                }
            }
        });

        $('.owl-carousel-unique .owl-nav').hide();

    });
    
</script>
<style>
	.site-wrapper .item-custom {
	    overflow: hidden;
	    padding: 0;
	    max-height: 210px;
	}
</style>