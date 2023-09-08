package com.tokio.prevencion.ponderadosprevencion73.commands.resource;

import com.google.gson.Gson;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.tokio.prevencion.ponderadosprevencion73.constants.PonderadosPrevencionPortlet73PortletKeys;
import com.tokio.prevencion.prevencionservicebuilder.model.PonderadoImpresoBase;
import com.tokio.prevencion.prevencionservicebuilder.service.PonderadoImpresoBaseLocalService;
import com.tokio.prevencion.prevencionservicebuilder.service.PonderadoImpresoBaseLocalServiceUtil;

import java.io.PrintWriter;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name="+ PonderadosPrevencionPortlet73PortletKeys.PONDERADOSPREVENCIONPORTLET73,
"mvc.command.name=/prevencion/getPonderadoBase" }, service = MVCResourceCommand.class)

public class GetPonderadoImpresoBaseResourceCommand extends BaseMVCResourceCommand{
	@Reference
	private PonderadoImpresoBaseLocalService _PonderadoImpresoBaseLocalService;
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		try{
			int idResultado = ParamUtil.getInteger(resourceRequest, "idResultado");
			
			PonderadoImpresoBase res = _PonderadoImpresoBaseLocalService.findLastByIdResultado(idResultado);
			
			System.out.println("ImpresoBase: " + res);
			
			Gson gson = new Gson();
			String jsonString = "null";
			PrintWriter writer = resourceResponse.getWriter();
			
			if(res != null){
				jsonString = gson.toJson(res);
			}
			
			writer.write(jsonString);
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
