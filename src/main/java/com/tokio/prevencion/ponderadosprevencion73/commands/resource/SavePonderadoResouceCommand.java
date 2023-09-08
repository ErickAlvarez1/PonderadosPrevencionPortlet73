package com.tokio.prevencion.ponderadosprevencion73.commands.resource;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.tokio.prevencion.ponderadosprevencion73.bean.ListRespuesta;
import com.tokio.prevencion.ponderadosprevencion73.bean.Respuesta;
import com.tokio.prevencion.ponderadosprevencion73.constants.PonderadosPrevencionPortlet73PortletKeys;
import com.tokio.prevencion.prevencionservicebuilder.model.ResultadoPonderado;
import com.tokio.prevencion.prevencionservicebuilder.service.ResultadoPonderadoLocalService;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + PonderadosPrevencionPortlet73PortletKeys
			.PONDERADOSPREVENCIONPORTLET73,
		"mvc.command.name=/prevencion/savePonderado"
	},
	service = MVCResourceCommand.class
)

public class SavePonderadoResouceCommand extends BaseMVCResourceCommand {
	
	Gson gson = new Gson();
	ListRespuesta listRespuesta;
	
	@Reference
	ResultadoPonderadoLocalService _ResultadoPonderadoLocalService;
	
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		JsonObject response = new JsonObject();
		int code;
		String msg;
		
		boolean estatus = true;
		int idRelacion = ParamUtil.getInteger(resourceRequest, "idRelacion");
		int idTipo = ParamUtil.getInteger(resourceRequest, "idTipo");
		int proveedorId = ParamUtil.getInteger(resourceRequest, "proveedorId");
		int vCuestionario = ParamUtil.getInteger(resourceRequest, "vCuestionario");
//		boolean flagSave = ParamUtil.getBoolean(resourceRequest, "flagSave");

		fRecuperaListaResp(resourceRequest);
		
		if(Validator.isNotNull(listRespuesta)){
			for (Respuesta res : listRespuesta.getListRespuesta()) {
				
				estatus = saveRespuesta(idRelacion, idTipo, res.getIdPregunta(), res.getRespuesta(), res.getEstatusDoc(), res.getComentrio(), res.isEvidencia(), proveedorId, vCuestionario);					
				
				if (!estatus){
					System.err.println("entré break");
					break;
				}
			}
			
			if (estatus){
				code = 0;
				msg = "Exito al guardar";
			}else{
				code = 1;
				msg = "Error al guardar";
			}
		}else{
			code = 2;
			msg = "Sin lista";
		}
		response.addProperty("code", code);
		response.addProperty("msg", msg);
		resourceResponse.getWriter().write(response.toString());
	}

	private boolean saveRespuesta (int idRelacion, int idTipo, int idPregunta, int respuesta, int estatusDoc, String comentario, boolean evidencia, int proveedorId, int vCuestionario){
		
		try{			
			//long idCur = CounterLocalServiceUtil.increment(ResultadoPonderado.class.getName() );
			int idCur = _ResultadoPonderadoLocalService.findLastResultadoPonderadoId();

			ResultadoPonderado res = _ResultadoPonderadoLocalService.createResultadoPonderado(idCur + 1);
			
			res.setIdRespuesta(idRelacion);
			res.setIdPonderado(idTipo);
			res.setIdPregunta(idPregunta);
			res.setRespuesta(respuesta);
			res.setEstatusDoc(estatusDoc);
			res.setComentrio(comentario);
			res.setIdSolicitud(0);
			res.setIdTransportista(proveedorId);
			res.setVResultado(idRelacion);
			res.setActivo(0);
			res.setVCuestionario(vCuestionario);
			res.setEvidencia(evidencia);
			
			
			_ResultadoPonderadoLocalService.addResultadoPonderado(res);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
	private void fRecuperaListaResp(ResourceRequest resourceRequest){
		try {
			String strListResp = "{\"listRespuesta\":" + ParamUtil.getString(resourceRequest, "arrFinal") + "}";
			listRespuesta = gson.fromJson(strListResp, ListRespuesta.class);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error al recuperar info");
			listRespuesta = null;
			e.printStackTrace();
		}
	}
	
}
