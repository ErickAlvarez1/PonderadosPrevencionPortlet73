package com.tokio.prevencion.ponderadosprevencion73.portlet;

import com.google.gson.Gson;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.tokio.prevencion.ponderadosprevencion73.bean.ListTema;
import com.tokio.prevencion.ponderadosprevencion73.bean.Pregunta;
import com.tokio.prevencion.ponderadosprevencion73.bean.Resultado;
import com.tokio.prevencion.ponderadosprevencion73.bean.Subtema;
import com.tokio.prevencion.ponderadosprevencion73.bean.Tema;
import com.tokio.prevencion.ponderadosprevencion73.constants.PonderadosPrevencionPortlet73PortletKeys;
import com.tokio.prevencion.prevencionservicebuilder.model.CatEstatusDocs;
import com.tokio.prevencion.prevencionservicebuilder.model.CatPregunta;
import com.tokio.prevencion.prevencionservicebuilder.model.CatSubtemas;
import com.tokio.prevencion.prevencionservicebuilder.model.CatTemas;
import com.tokio.prevencion.prevencionservicebuilder.model.Proveedor;
import com.tokio.prevencion.prevencionservicebuilder.model.ResultadoPonderado;
import com.tokio.prevencion.prevencionservicebuilder.model.TipoPonderado;
import com.tokio.prevencion.prevencionservicebuilder.model.VinculoPonderado;
import com.tokio.prevencion.prevencionservicebuilder.service.CatEstatusDocsLocalService;
import com.tokio.prevencion.prevencionservicebuilder.service.CatPreguntaLocalService;
import com.tokio.prevencion.prevencionservicebuilder.service.CatSubtemasLocalService;
import com.tokio.prevencion.prevencionservicebuilder.service.CatTemasLocalService;
import com.tokio.prevencion.prevencionservicebuilder.service.ProveedorLocalService;
import com.tokio.prevencion.prevencionservicebuilder.service.ResultadoPonderadoLocalService;
import com.tokio.prevencion.prevencionservicebuilder.service.TipoPonderadoLocalService;
import com.tokio.prevencion.prevencionservicebuilder.service.VinculoPonderadoLocalService;
import com.tokio.prevencion.prevencionservices.PrevencionServices73;
import com.tokio.prevencion.prevencionservices.bean.CatTransportistas;
import com.tokio.prevencion.prevencionservices.bean.Transportista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author urielfloresvaldovinos
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=PonderadosPrevencionPortlet73",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PonderadosPrevencionPortlet73PortletKeys.PONDERADOSPREVENCIONPORTLET73,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.private-request-attributes=false"
	},
	service = Portlet.class
)
public class PonderadosPrevencionPortlet73Portlet extends MVCPortlet {
	private static final Log _log = LogFactoryUtil.getLog(PonderadosPrevencionPortlet73Portlet.class);
	@Reference
	PrevencionServices73 _PrevencionServices;
	
	@Reference
	VinculoPonderadoLocalService _VinculoPonderadoLocalService;
	
	@Reference
	TipoPonderadoLocalService _TipoPonderadoLocalService;
	
	@Reference
	CatTemasLocalService _CatTemasLocalService;
	
	@Reference
	ProveedorLocalService _ProveedorLocalService;
	
	@Reference
	CatSubtemasLocalService _CatSubtemasLocalService;
	
	@Reference
	CatEstatusDocsLocalService _CatEstatusDocsLocalService;
	
	@Reference
	ResultadoPonderadoLocalService _ResultadoPonderadoLocalService;
	
	@Reference
	CatPreguntaLocalService _CatPreguntaLocalService;
	
	long modoVista = 0;
	long tipoPonderado = 0;
	TipoPonderado tipoVigente;
	List<CatTemas> listTemas;
	long vCuestionario = -1;
	int proveedorId = 0;
	
	String strProveedorId = "";
	
	VinculoPonderado vinculo;
	
	User usuario;
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException, IOException {
		
		getModoVista(renderRequest);
		getUser(renderRequest);
			
		switch ((int)modoVista) {
			case 0: /*Menu*/
				break;
		
			case 1:	/*Lista Proveedores*/
				getTipoPonderado(renderRequest);
				findProveedorList(renderRequest);
				break;

			case 2:	/*Cuestionario*/
				getTipoPonderado(renderRequest);
				getProveedorId(renderRequest);
				getTemas(renderRequest);
				generaCuestionario(renderRequest);
				getCatEstatusDocs(renderRequest);
				getVinculo(renderRequest);
				break;
				
			case 3: /*Resumen*/
				getTipoPonderado(renderRequest);
				getProveedorId(renderRequest);
				getRespuesta(renderRequest);
				generaTablaRespuesta(renderRequest);
				fConsulta(renderRequest);
				break;
				
			default:
				break;
		}
			

		super.render(renderRequest, renderResponse);
	}

	private void getModoVista(RenderRequest renderRequest){
		HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
		String strModoVista = PortalUtil.getOriginalServletRequest(request).getParameter("modoVista");
		
		if(Validator.isNotNull(strModoVista)){
			modoVista = Long.parseLong(strModoVista);
		}else{
			modoVista = 0;
		}
		
		renderRequest.setAttribute("modoVista", modoVista);
	}
	
	private void getUser(RenderRequest renderRequest){
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			usuario = themeDisplay.getUser();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Error Usuario");
			usuario = null;
		}
	}
	
	private void getTipoPonderado(RenderRequest renderRequest){
		HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
		String strIdTipo = PortalUtil.getOriginalServletRequest(request).getParameter("idTipo");
		
		if(Validator.isNotNull(strIdTipo)){
			tipoPonderado = Long.parseLong(strIdTipo);
		}else{
			tipoPonderado = 0;
		}
		
		tipoVigente = _TipoPonderadoLocalService.findByTipoAndActive(tipoPonderado);
		System.out.println("tipoPonderado: " + tipoPonderado);
		System.out.println("tipoVigente: " + tipoVigente);
		
		renderRequest.setAttribute("tipoPonderado", tipoPonderado);
		renderRequest.setAttribute("tipoVigente", tipoVigente);

	}
	
	private void findProveedorList(RenderRequest renderRequest) {
		
		try {
			
			if(tipoVigente.getIdTipo() == 10) {
				
				CatTransportistas transoirtistas = _PrevencionServices
						.GetCatTransportista(usuario.getScreenName(),
						PonderadosPrevencionPortlet73PortletKeys.PONDERADOSPREVENCIONPORTLET73);
				if(Validator.isNotNull(transoirtistas)){
					List<Transportista> listTranspor = transoirtistas.getLista();
					renderRequest.setAttribute("listTransportistas", listTranspor);
				}
			}
			else {
				
				List<Proveedor> listProveedor = _ProveedorLocalService
						.findByTipo((int)tipoVigente.getIdTipo());
				
				if(Validator.isNotNull(listProveedor)) {
					
					renderRequest.setAttribute("listProveedor", listProveedor);
				}			
			}			
		}
		catch (Exception e) {
		}
		
	}

	private void getProveedorId(RenderRequest renderRequest){
		HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
		strProveedorId = PortalUtil.getOriginalServletRequest(request).getParameter("proveedorId");
		String nombreTransporte = "";
		
		if(Validator.isNotNull(strProveedorId)){
			if(tipoVigente.getIdTipo() == 10){
				try{
					CatTransportistas ListT = _PrevencionServices.GetTransportistaByCodigo(usuario.getScreenName(), PonderadosPrevencionPortlet73PortletKeys.PONDERADOSPREVENCIONPORTLET73, strProveedorId);
					for (Transportista curTran : ListT.getLista()) {
						nombreTransporte = curTran.getNombre();
						break;
					}
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					System.out.println("Error al recuperar transportista");
				}				
			}else{
				try {
					Proveedor curTran = _ProveedorLocalService.getProveedor(Long.parseLong(strProveedorId));
					nombreTransporte = curTran.getNombre();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			
			renderRequest.setAttribute("nombreTransporte", nombreTransporte);
			renderRequest.setAttribute("proveedorId", strProveedorId);
		}
		
	}
	
	private void getCatEstatusDocs(RenderRequest renderRequest) {
		List<CatEstatusDocs> catEstatusDocs = _CatEstatusDocsLocalService
				.getCatEstatusDocses(-1, -1);
		renderRequest.setAttribute("catEstatusDocs", catEstatusDocs);
	}
	
	private void getVinculo(RenderRequest renderRequest) {
		HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
		Calendar sameDate = Calendar.getInstance();
		vinculo = _VinculoPonderadoLocalService.findLastVinculoByIdTransportista(strProveedorId);
		_log.debug(vinculo);
		String retornoResumen = PortalUtil.getOriginalServletRequest(request).getParameter("retornoResumen");
		renderRequest.setAttribute("retornoResumen", retornoResumen);
		if(Validator.isNotNull(vinculo)){
			List<ResultadoPonderado> listResultado = _ResultadoPonderadoLocalService
					.findByIdRespuesta((int)vinculo.getId());
			Gson gson = new Gson();
			String strList = gson.toJson(listResultado);
			renderRequest.setAttribute("listResultado", strList);
			renderRequest.setAttribute("vinculo", vinculo);
			renderRequest.setAttribute("flagSave", vinculo.isVigente());
			
			sameDate.setTime(vinculo.getFechaCreacion());
			_log.debug(retornoResumen);
			if(!"true".equals(retornoResumen)){
				SessionMessages.add(renderRequest, "msjExito");
				renderRequest.setAttribute("exitoMsg", "El proveedor seleccionado ya cuenta con una evaluación vigente registrada el día: " + sameDate.get(Calendar.DAY_OF_MONTH) + "-" + (sameDate.get(Calendar.MONTH) + 1) + "-" + sameDate.get(Calendar.YEAR));

				User auxUser = UserLocalServiceUtil.fetchUser(vinculo.getIdUsuario());
				if(Validator.isNotNull(auxUser)){
					renderRequest.setAttribute("exitoMsg2", "Creado por: " + auxUser.getScreenName());
				}
				SessionMessages.add(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			}
		}else{		
			SessionErrors.add(renderRequest, "errorConocido");
			renderRequest.setAttribute("errorMsg", "Ninguna versión encontrada");
			SessionMessages.add(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
			renderRequest.setAttribute("vinculo", vinculo);
			renderRequest.setAttribute("flagSave", true);
		}
	}
	
	private void getTemas(RenderRequest renderRequest) {
		
		if(Validator.isNotNull(tipoVigente)) {
			
			listTemas = _CatTemasLocalService.getTemas((int)tipoVigente.getIdEvaluacion());
			renderRequest.setAttribute("listTemas", listTemas);
		}
	}
	
	private void generaCuestionario(RenderRequest renderRequest){
		ListTema cuestionario = new ListTema();
		if(Validator.isNotNull(listTemas)){
			List<Tema> curListTema = new ArrayList<>();
			for (CatTemas tema : listTemas) {
				Tema curTema = new Tema();
				curTema.setIdTema(tema.getIdTema());
				curTema.setDescripcion(tema.getDescripcion());
				curTema.setOrden(tema.getOrden());
				curTema.setIdEvaluacion(tema.getIdEvaluacion());
				curTema.setPorcentaje(tema.getPorcentaje());
				
				List<CatSubtemas> listSubtema = _CatSubtemasLocalService.findSubtemasByIdTema(tema.getIdTema());
				List<Subtema> curListSubtema = new ArrayList<>();
				for (CatSubtemas subtema : listSubtema) {
					Subtema curSub = new Subtema();
					curSub.setIdSubtema(subtema.getIdSubtema());
					curSub.setDescripcion(subtema.getDescripcion());
					curSub.setOrden(subtema.getOrden());
					curSub.setIdTema(subtema.getIdTema());
					
					List<CatPregunta> listPregunta = _CatPreguntaLocalService
							.findByIdsubtema(subtema.getIdSubtema());
					List<Pregunta> curListPreg = new ArrayList<>();
					for (CatPregunta pregunta : listPregunta) {
						Pregunta curPreg = new Pregunta();
						curPreg.setIdPregunta(pregunta.getId());
						curPreg.setIdSubtema(pregunta.getIdSubtema());
						curPreg.setDescripcion(pregunta.getDescripcion());
						curPreg.setOrden(pregunta.getOrden());
						
						curListPreg.add(curPreg);
					}
					curSub.setListPregunta(curListPreg);
					
					curListSubtema.add(curSub);
				}
				curTema.setListSubtema(curListSubtema);

				curListTema.add(curTema);
			}
			cuestionario.setListTema(curListTema);
			
			renderRequest.setAttribute("cuestionario", cuestionario.getListTema());
		}
	}

	private void generaTablaRespuesta(RenderRequest renderRequest){
		ListTema cuestionario = new ListTema();
		
		HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
		String idRespuestaStr = PortalUtil.getOriginalServletRequest(request).getParameter("vCuestionarioId");
		
		int idRespuesta = Integer.parseInt(idRespuestaStr);
		
		if(Validator.isNotNull(listTemas)){
			List<Tema> curListTema = new ArrayList<>();
			for (CatTemas tema : listTemas) {
				Tema curTema = new Tema();
				curTema.setIdTema(tema.getIdTema());
				curTema.setDescripcion(tema.getDescripcion());
				curTema.setOrden(tema.getOrden());
				curTema.setIdEvaluacion(tema.getIdEvaluacion());
				curTema.setPorcentaje(tema.getPorcentaje());
				
				List<CatSubtemas> listSubtema = _CatSubtemasLocalService.findSubtemasByIdTema(tema.getIdTema());
				List<Subtema> curListSubtema = new ArrayList<>();
				for (CatSubtemas subtema : listSubtema) {
					Subtema curSub = new Subtema();
					curSub.setIdSubtema(subtema.getIdSubtema());
					curSub.setDescripcion(subtema.getDescripcion());
					curSub.setOrden(subtema.getOrden());
					curSub.setIdTema(subtema.getIdTema());
					
					List<CatPregunta> listPregunta = _CatPreguntaLocalService.findByIdsubtema(subtema.getIdSubtema());
//					List<Pregunta> curListPreg = new ArrayList<>();
					List<Resultado> curListRes = new ArrayList<>();
					for (CatPregunta pregunta : listPregunta) {
						Resultado curRes = new Resultado();
						//ResultadoPonderado resultado = _ResultadoPonderadoLocalService.findByIdPregunta(pregunta.getId());
						ResultadoPonderado resultado = _ResultadoPonderadoLocalService.findResByIdPreguntaAndIdRespuesta(pregunta.getId(), idRespuesta);
						curRes.setId(resultado.getId());
						curRes.setIdRespuesta(resultado.getIdRespuesta());
						curRes.setIdPonderado(resultado.getIdPonderado());
						curRes.setIdPregunta(resultado.getIdPregunta());
						curRes.setRespuesta(resultado.getRespuesta());
						curRes.setEstatusDoc(resultado.getEstatusDoc());
						curRes.setComentrio(resultado.getComentrio());
						curRes.setIdSolicitud(resultado.getIdSolicitud());
						curRes.setIdTransportista((int)resultado.getIdTransportista());
						curRes.setvResultado((int)resultado.getVResultado());
						curRes.setActivo((resultado.getActivo() == 1 ? true : false));
						curRes.setFechaCreacion(resultado.getFechaCreacion());
						curRes.setvCuestionario((int)resultado.getVCuestionario());
						curRes.setEvidencia(resultado.getEvidencia());
						curRes.setDescripcion(pregunta.getDescripcion());
						
						
//						Pregunta curPreg = new Pregunta();
//						curPreg.setIdPregunta(pregunta.getId());
//						curPreg.setIdSubtema(pregunta.getIdSubtema());
//						curPreg.setDescripcion(pregunta.getDescripcion());
//						curPreg.setOrden(pregunta.getOrden());
						
//						curListPreg.add(curPreg);
						curListRes.add(curRes);
					}
//					curSub.setListPregunta(curListPreg);
					curSub.setListRespuesta(curListRes);
					
					curListSubtema.add(curSub);
				}
				curTema.setListSubtema(curListSubtema);
				
				curListTema.add(curTema);
			}
			cuestionario.setListTema(curListTema);
			
			renderRequest.setAttribute("cuestionario", cuestionario.getListTema());
		}
	}
	
	private void getRespuesta(RenderRequest renderRequest){
		if(Validator.isNotNull(tipoVigente)){
			HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
			String idRespuestaStr = PortalUtil.getOriginalServletRequest(request).getParameter("vCuestionarioId");
			
			int idRespuesta;
			if(Validator.isNotNull(idRespuestaStr)){
				idRespuesta = Integer.parseInt(idRespuestaStr);

				List<ResultadoPonderado> listRespuesta = _ResultadoPonderadoLocalService.findByIdRespuesta(idRespuesta);
				for (ResultadoPonderado res : listRespuesta) {
					vCuestionario = res.getVCuestionario();
					break;
				}
				
				listTemas = _CatTemasLocalService.getTemas((int)vCuestionario);
				
				renderRequest.setAttribute("idRespuesta", idRespuesta);
				renderRequest.setAttribute("listRespuesta", listRespuesta);
			}
			
		}
	}
	
	private void fConsulta(RenderRequest renderRequest){
		HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
		String modoConsulta = PortalUtil.getOriginalServletRequest(request).getParameter("modoConsulta");
		
		switch (modoConsulta) {
		case "0":
			renderRequest.setAttribute("hideBtn1", "");
			renderRequest.setAttribute("hideBtn2", "d-none");
			renderRequest.setAttribute("hideBtn3", "d-none");
			
			break;

		case "1":
			renderRequest.setAttribute("hideBtn1", "d-none");
			renderRequest.setAttribute("hideBtn2", "");
			renderRequest.setAttribute("hideBtn3", "d-none");
			
			break;
		case "3":
			renderRequest.setAttribute("hideBtn1", "d-none");
			renderRequest.setAttribute("hideBtn2", "d-none");
			renderRequest.setAttribute("hideBtn3", "");
			
			break;

		default:
			renderRequest.setAttribute("hideBtn1", "d-none");
			renderRequest.setAttribute("hideBtn2", "d-none");
			renderRequest.setAttribute("hideBtn3", "d-none");
			break;
		}
	}
}