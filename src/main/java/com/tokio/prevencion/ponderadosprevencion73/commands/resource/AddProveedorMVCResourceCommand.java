package com.tokio.prevencion.ponderadosprevencion73.commands.resource;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.tokio.prevencion.ponderadosprevencion73.constants.PonderadosPrevencionPortlet73PortletKeys;
import com.tokio.prevencion.prevencionservicebuilder.model.Proveedor;
import com.tokio.prevencion.prevencionservicebuilder.service.ProveedorLocalService;
import com.tokio.prevencion.prevencionservices.PrevencionServices73;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + PonderadosPrevencionPortlet73PortletKeys
			.PONDERADOSPREVENCIONPORTLET73,
		"mvc.command.name=/prevencion/addProveedor"
	},
	service = MVCResourceCommand.class
)

public class AddProveedorMVCResourceCommand extends BaseMVCResourceCommand{
	private static final Log _log = LogFactoryUtil.getLog(AddProveedorMVCResourceCommand.class);

	@Reference
	PrevencionServices73 _PrevencionServices;
	
	@Reference
	ProveedorLocalService _ProveedorLocalService;
	
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		
		String nombre = ParamUtil.getString(resourceRequest, "nombre");
		int tipo = ParamUtil.getInteger(resourceRequest, "tipo");
		
		try {
			
			if(tipo == 10){
				ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
				User usuario = themeDisplay.getUser();
				
				_PrevencionServices.SaveCatTransportista(nombre, usuario.getScreenName(), PonderadosPrevencionPortlet73PortletKeys.PONDERADOSPREVENCIONPORTLET73);
				
			}
			else{
				//long idNew = CounterLocalServiceUtil.increment(Proveedor.class.getName());
				long idNew = Long.parseLong(String.valueOf(_ProveedorLocalService.findLastProveedorId()));
				Proveedor newProveedor = _ProveedorLocalService.createProveedor(idNew + 1);
				newProveedor.setNombre(nombre);
				newProveedor.setTipo(tipo);
				_ProveedorLocalService.addProveedor(newProveedor);
			}
			
			resourceResponse.getWriter().write( "{ \"codigo\":0, \"msg\":\"Exito al guardar proveedor\"}" );
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resourceResponse.getWriter().write( "{ \"codigo\":1, \"msg\":\"Error al guardar proveedor\"}" );
		}
		
	}
}
