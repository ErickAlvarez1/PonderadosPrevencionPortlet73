package com.tokio.prevencion.ponderadosprevencion73.commands.resource;

import com.google.gson.Gson;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.tokio.prevencion.ponderadosprevencion73.constants.PonderadosPrevencionPortlet73PortletKeys;
import com.tokio.prevencion.prevencionservicebuilder.model.CatEstatusDocs;
import com.tokio.prevencion.prevencionservicebuilder.service.CatEstatusDocsLocalService;

import java.io.PrintWriter;
import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "javax.portlet.name="+ PonderadosPrevencionPortlet73PortletKeys.PONDERADOSPREVENCIONPORTLET73,
"mvc.command.name=/prevencion/getCatEstDosc" }, service = MVCResourceCommand.class)

public class GetCatEstatusDocsResourceCommand extends BaseMVCResourceCommand {
	
	@Reference
	CatEstatusDocsLocalService _CatEstatusDocsLocalService;
	
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
			
		Gson gson = new Gson();
		PrintWriter writer = resourceResponse.getWriter();
		try {
			System.out.println("Entré getCatEstDocs!!!");
			
			List<CatEstatusDocs> ab = _CatEstatusDocsLocalService.getCatEstatusDocses(-1, -1);
            String jsonString = gson.toJson(ab);
            writer.write(jsonString);
		}
		catch (Exception e) {
			// TODO: handle exception
			String jsonString = "{}";
			writer.write(jsonString);
		}
	}

}
