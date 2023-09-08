package com.tokio.prevencion.ponderadosprevencion73.commands.resource;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.tokio.prevencion.ponderadosprevencion73.constants.PonderadosPrevencionPortlet73PortletKeys;
import com.tokio.prevencion.prevencionservicebuilder.service.ResultadoPonderadoLocalService;

import java.io.PrintWriter;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name="+ PonderadosPrevencionPortlet73PortletKeys.PONDERADOSPREVENCIONPORTLET73,
"mvc.command.name=/prevencion/getIdRespuesta" }, service = MVCResourceCommand.class)

public class GetIdRespuestaResourceCommand extends BaseMVCResourceCommand {
	
	@Reference
	ResultadoPonderadoLocalService _ResultadoPonderadoLocalService;
	
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		try{
			int idSolicitud = ParamUtil.getInteger(resourceRequest, "idSolicitud");
			int ponderado = ParamUtil.getInteger(resourceRequest, "ponderado");
			
			int res = _ResultadoPonderadoLocalService.getIdRespuestaBySolicitud(idSolicitud, ponderado);
			
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(String.valueOf(res));
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
