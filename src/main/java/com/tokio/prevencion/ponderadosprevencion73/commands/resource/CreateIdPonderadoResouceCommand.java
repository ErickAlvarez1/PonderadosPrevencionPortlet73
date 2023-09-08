package com.tokio.prevencion.ponderadosprevencion73.commands.resource;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.tokio.prevencion.ponderadosprevencion73.constants.PonderadosPrevencionPortlet73PortletKeys;
import com.tokio.prevencion.prevencionservicebuilder.model.IdRelacion;
import com.tokio.prevencion.prevencionservicebuilder.service.IdRelacionLocalService;
import com.tokio.prevencion.prevencionservicebuilder.service.IdRelacionLocalServiceUtil;

import java.io.PrintWriter;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name="+ PonderadosPrevencionPortlet73PortletKeys.PONDERADOSPREVENCIONPORTLET73,
"mvc.command.name=/prevencion/createPonderado" }, service = MVCResourceCommand.class)

public class CreateIdPonderadoResouceCommand extends BaseMVCResourceCommand{

	@Reference
	private IdRelacionLocalService _IdRelacionLocalService;
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		PrintWriter writer = resourceResponse.getWriter();
		try{
//			int idRef = (int)IdRelacionLocalServiceUtil.agregaRealcion();
			//long idRelacion = CounterLocalServiceUtil.increment(IdRelacion.class.getName() );
			long idRelacion = Long.parseLong(String.valueOf(_IdRelacionLocalService.findLastIdRelacionId()));
			IdRelacion auxRelacion = _IdRelacionLocalService.createIdRelacion(idRelacion + 1);

			auxRelacion = _IdRelacionLocalService.addIdRelacion(auxRelacion);
//			String idRef = String.valueOf(IdRelacionLocalServiceUtil.agregaRealcion());
			String idRef = String.valueOf(auxRelacion.getId());
//			System.out.println("IDRELACION:" + idRef);
			
			System.out.println("idRelacion: " + idRef);
			
			writer.write(idRef);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			writer.write("null");
		}
	}
}
