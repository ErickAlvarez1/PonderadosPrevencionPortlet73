package com.tokio.prevencion.ponderadosprevencion73.commands.resource;

import com.google.gson.Gson;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.tokio.prevencion.ponderadosprevencion73.constants.PonderadosPrevencionPortlet73PortletKeys;
import com.tokio.prevencion.prevencionservicebuilder.model.CatTemas;
import com.tokio.prevencion.prevencionservicebuilder.service.CatTemasLocalService;

import java.io.PrintWriter;
import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name="+ PonderadosPrevencionPortlet73PortletKeys.PONDERADOSPREVENCIONPORTLET73,
"mvc.command.name=/prevencion/getListTemas" }, service = MVCResourceCommand.class)

public class GetListaTemasResourceCommand extends BaseMVCResourceCommand {
	
	@Reference
	CatTemasLocalService _CatTemasLocalService;
	
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		Gson gson = new Gson();
		PrintWriter writer = resourceResponse.getWriter();
		String jsonString = "";
		int idPonderado = ParamUtil.getInteger(resourceRequest, "idPonderado");
		try{
			List<CatTemas> ab = _CatTemasLocalService.getTemas(idPonderado);
			jsonString = gson.toJson(ab);
			writer.write(jsonString);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonString = "{}";
			writer.write(jsonString);
		}
		
	}

}
