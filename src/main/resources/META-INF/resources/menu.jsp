<%@ include file="/init.jsp" %>

<div class="container" id="menuPrincipal">
	<div class="row">
		<div class="col-12 text-center">
			<p class="font-weight-bold h1-responsive">Ponderados - Prevenci&oacute;n</p>
		</div>
	</div>

    <div class="row w-100 mx-auto wow fadeIn mt-5" data-ride="carousel" data-wow-delay="0.2s">
        <div class="owl-carousel owl-theme">
            <div class="item">
                <a onclick="goAdmin(10)">
                    <img src="<%=request.getContextPath()%>/img/Transporte.png" class="z-depth-1" alt="Sample avatar" />
                    <div class="btn-blue btn-agentes">Transporte</div>
                </a>
            </div>
            <div class="item">
                <a onclick="goAdmin(3)">
                    <img src="<%=request.getContextPath()%>/img/Custodia.png" class="z-depth-1" alt="Sample avatar" />
                    <div class="btn-blue btn-agentes">Custodia</div>
                </a>
            </div>
            <div class="item">
                <a onclick="goAdmin(1)">
                    <img src="<%=request.getContextPath()%>/img/AgenteAduanal.png" class="z-depth-1" alt="Sample avatar" />
                    <div class="btn-blue btn-agentes">Agente Aduanal</div>
                </a>
            </div>
        </div>
    </div>

    <div class="row w-100 mx-auto wow fadeIn mt-5" data-ride="carousel" data-wow-delay="0.2s">
        <div class="owl-carousel owl-theme">
            <div class="item">
                <a onclick="goAdmin(4)">
                    <img src="<%=request.getContextPath()%>/img/Distribuidor.png" class="z-depth-1" alt="Sample avatar" />
                    <div class="btn-blue btn-agentes">Distribuidores</div>
                </a>
            </div>
            <div class="item">
                <a onclick="goAdmin(5)">
                    <img src="<%=request.getContextPath()%>/img/Monitoreo.png" class="z-depth-1" alt="Sample avatar" />
                    <div class="btn-blue btn-agentes">Monitoreo</div>
                </a>
            </div>
            <div class="item">
                <a onclick="goAdmin(6)">
                    <img src="<%=request.getContextPath()%>/img/Acero.png" class="z-depth-1" alt="Sample avatar" />
                    <div class="btn-blue btn-agentes">Operaci&oacute;n Acero</div>
                </a>
            </div>
        </div>
    </div>

    <div class="row w-100 mx-auto wow fadeIn mt-5 justify-content-center" data-wow-delay="0.2s">
        <div class="col-md-4 item-custom">
            <a onclick="goAdmin(2)">
                <img src="<%=request.getContextPath()%>/img/OperadorLogistico.png" class="z-depth-1" alt="Sample avatar" />
                <div class="btn-blue btn-agentes">Operador Log&iacute;stico</div>
            </a>
        </div>
        <div class="col-md-1 item-custom">

        </div>
        <div class="col-md-4 item-custom">
            <a onclick="goAdmin(7)">
                <img src="<%=request.getContextPath()%>/img/PatioAutomotriz.png" class="z-depth-1" alt="Sample avatar" />
                <div class="btn-blue btn-agentes">Patio Automotriz</div>
            </a>
        </div>
    </div>
    
    <div class="row">
		<div class="col-12 text-center mt-5">
			<a type="button" href="consulta-ponderados" class="btn btn-success" style="width: 100%;">RESUMEN DE PONDERADOS</a>
		</div>
	</div>
</div>

<form id=formBtn1 method="POST" class="d-none">
	<input type="hidden" id="modoVista" name="modoVista" value="1"/>
	<input type="hidden" id="idTipo" name="idTipo"/>
	<button id="btnMenu" type="submit"><span> Oportunidades</span></button>
</form>

<script src="<%=request.getContextPath()%>/js/menu.js?v=${version}"></script>

<style>
	.site-wrapper .item-custom {
	    overflow: hidden;
	    padding: 0;
	    max-height: 210px;
	}
</style>