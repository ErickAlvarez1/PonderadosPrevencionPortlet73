package com.tokio.prevencion.ponderadosprevencion73.commands.resource;

import com.google.gson.JsonObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.tokio.prevencion.ponderadosprevencion73.constants.PonderadosPrevencionPortlet73PortletKeys;
import com.tokio.prevencion.prevencionservicebuilder.model.VinculoPonderado;
import com.tokio.prevencion.prevencionservicebuilder.service.VinculoPonderadoLocalService;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + PonderadosPrevencionPortlet73PortletKeys.PONDERADOSPREVENCIONPORTLET73,
		"mvc.command.name=/prevencion/activaC"
	},
	service = MVCResourceCommand.class
)

public class ActivaVersionMVCResourceCommand extends BaseMVCResourceCommand {
	
	@Reference
	VinculoPonderadoLocalService _VinculoPonderadoLocalService;
	
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		int idVinculo = ParamUtil.getInteger(resourceRequest, "idRespuesta");
		int confianza = ParamUtil.getInteger(resourceRequest, "confianza");
		
		JsonObject response = new JsonObject();
		
		try {
			VinculoPonderado vinculo = _VinculoPonderadoLocalService.fetchVinculoPonderado(idVinculo);
			VinculoPonderado vinculoAnt = _VinculoPonderadoLocalService.getByIdTransportistaVigente(vinculo.getIdProveedor(), true);
			if(Validator.isNotNull(vinculoAnt)){
				System.out.println("dar de baja version activa");
				vinculoAnt.setVigente(false);
				_VinculoPonderadoLocalService.updateVinculoPonderado(vinculoAnt);
			}else{
				System.out.println("No hay version activa");
			}
			
			
			vinculo.setVigente(true);
			
			if(vinculo.getVCuestionario() == vinculo.getVCuestionarioAnt()){
				vinculo.setVersion( vinculo.getVersionAnt() +1 );
			}else{
				vinculo.setVersion(0);
			}
			
			vinculo.setConfianza(confianza);
			vinculo.setEstatus(1);//evaluado
			
			_VinculoPonderadoLocalService.updateVinculoPonderado(vinculo);

			response.addProperty("codigo", 0);
			response.addProperty("msg", "Exito al generar versión");
			response.addProperty("vinculo", vinculo.getVersion());
			
			resourceResponse.getWriter().write( response.toString() );
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.addProperty("codigo", 1);
			response.addProperty("msg", "Error al generar versión");
			resourceResponse.getWriter().write(response.toString());
		}
	}

}
