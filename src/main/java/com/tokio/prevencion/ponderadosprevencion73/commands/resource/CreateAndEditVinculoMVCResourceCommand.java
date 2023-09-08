package com.tokio.prevencion.ponderadosprevencion73.commands.resource;

import com.google.gson.JsonObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.tokio.prevencion.ponderadosprevencion73.constants.PonderadosPrevencionPortlet73PortletKeys;
import com.tokio.prevencion.prevencionservicebuilder.model.VinculoPonderado;
import com.tokio.prevencion.prevencionservicebuilder.service.VinculoPonderadoLocalService;

import java.io.PrintWriter;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + PonderadosPrevencionPortlet73PortletKeys
			.PONDERADOSPREVENCIONPORTLET73,
		"mvc.command.name=/prevencion/createEditVinculo"
	},
	service = MVCResourceCommand.class
)

public class CreateAndEditVinculoMVCResourceCommand extends BaseMVCResourceCommand {
	private static final Log _log = LogFactoryUtil.getLog(CreateAndEditVinculoMVCResourceCommand.class);
	@Reference
	VinculoPonderadoLocalService _VinculoPonderadoLocalService;
	
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
//		Gson gson = new Gson();
		JsonObject response = new JsonObject();
		int code = -1;
		String msj = "";
		
		int modo = ParamUtil.getInteger(resourceRequest, "modo");
		String idProveedor = ParamUtil.getString(resourceRequest, "idProveedor");
		int tipo = ParamUtil.getInteger(resourceRequest, "tipo");
		int vCuestio = ParamUtil.getInteger(resourceRequest, "vCuestio");
		long idVinculoAnt = ParamUtil.getLong(resourceRequest, "idVinculo");
		System.out.println("MODO VINCULO: " + modo);

		User user = (User) resourceRequest.getAttribute(WebKeys.USER);
//		Date fechaCreacion = new Date();
//		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City"));
//		Date fechaCreacion = calendar.getTime();
		
		switch (modo) {

		case 0:/*Modo Crear*/
			System.out.println("Entré modo crear vinculo");
			System.out.println("idVinculoAnt: " + idVinculoAnt);
			System.out.println("vCuestio: " + vCuestio);
			
			if(idVinculoAnt == 0){
				//long idVinculo = CounterLocalServiceUtil.increment(VinculoPonderado.class.getName());
				long idVinculo = Long.parseLong(String.valueOf(_VinculoPonderadoLocalService.findLastVinculoPonderadoId()));
				VinculoPonderado curVinculo = _VinculoPonderadoLocalService.createVinculoPonderado(idVinculo + 1);

				curVinculo.setIdUsuario(user.getUserId());
				curVinculo.setTipoPonderado(tipo);
				curVinculo.setIdProveedor(idProveedor);
				curVinculo.setVCuestionario(vCuestio);
				curVinculo.setVersion(-1);
				System.err.println(curVinculo);
				
				curVinculo = _VinculoPonderadoLocalService.addVinculoPonderado(curVinculo);
				System.err.println(curVinculo);
				response.addProperty("idVinculo",curVinculo.getId());
			}else{
				VinculoPonderado vinculoAnt = _VinculoPonderadoLocalService.fetchVinculoPonderado(idVinculoAnt);
				System.out.println(vinculoAnt);

				//long idVinculo = CounterLocalServiceUtil.increment(VinculoPonderado.class.getName());
				long idVinculo = Long.parseLong(String.valueOf(_VinculoPonderadoLocalService.findLastVinculoPonderadoId()));
				VinculoPonderado curVinculo = _VinculoPonderadoLocalService.createVinculoPonderado(idVinculo + 1);

				curVinculo.setIdUsuario(user.getUserId());
				curVinculo.setTipoPonderado(tipo);
				curVinculo.setIdProveedor(idProveedor);
				curVinculo.setVCuestionario(vCuestio);
				curVinculo.setVCuestionarioAnt(vinculoAnt.getVCuestionario());
				curVinculo.setVersionAnt(vinculoAnt.getVersion());
				curVinculo.setVersion(-1);
				System.out.println(curVinculo);
				curVinculo = _VinculoPonderadoLocalService.addVinculoPonderado(curVinculo);
				
				response.addProperty("idVinculo", curVinculo.getId());
			}
			
			
			code = 0;
			msj = "Éxito al crear";
			break;

		case 1:/*Modo editar*/
			System.out.println("Entré modo editar vinculo");
			response.addProperty("idVinculo", idVinculoAnt);
			
			break;

		default:
			msj = "Modo indefinido";
			System.err.println("ERROR MODO VINCULO");
			break;
		}
		
		response.addProperty("code", code);
		response.addProperty("msj", msj);
		
		PrintWriter writer = resourceResponse.getWriter();
		writer.write(response.toString());
	}
}
