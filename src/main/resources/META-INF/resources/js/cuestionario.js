$(document).ready(function() {
	showLoader();
    window.scrollTo(0, 0);
    
    if(retornoResumen === '' && !valIsNullOrEmpty(listRespuesta) ){
    	$("#modalConfirma").modal("show");
    }else if(retornoResumen === 'true' ){
		console.log("Viene de Resumen");
		llenaRespuestas();
	}
    hideLoader();
});

function showForm(auxForm) {
    var continuar = verificaCompleted();
	if(continuar){
	    if(auxForm == 'fin'){
	    	console.log('Verifica BTN');
	    	auxForm = $('li.step:last span').text();
	    	var auxBandera = false;
	    	var auxLi = $('#rowSteppers .stepper li.step');
			$.each(auxLi, function (k, valPestania) {
				auxBandera = ( $(this).hasClass('warning') ? true : false );
				if (auxBandera){
					return false;
				}
			});
			if(!auxBandera){
				$('#formLast_next').attr("disabled", false);
			}
			console.log("auxBandera: " + auxBandera);
	    }
	    $('form').hide();
	    $('#form' + auxForm).show();
	    $('.step').removeClass('active');
	    $('.step-' + auxForm).removeClass('completed');
	    $('.step-' + auxForm).removeClass('warning');
	    $('.step-' + auxForm).addClass('active');
	
	    window.scrollTo(0, 0);
	}
	else{
		//showMessageError(".navbar", "Faltan datos por llenar", 0);
	}
}

function clickA(auxForm){
	var continuar = verificaCompleted();
	if(continuar){
		$("li.step a[datanum='"+auxForm+"']").click();
	}
	else{
		//showMessageError(".navbar", "Faltan datos por llenar", 0);
	}
}

function verificaCompleted() {
	
	var buttons = true;
	var selects = true;
	
    var auxVerifi = $('#rowSteppers .stepper .step.active span').text();
    
    $('#form' + auxVerifi + ' .row.d-flex.justify-content-around').each(function(index) {
    	var auxForm = $(this).attr('aux');
        /*var auxGrupo = chkRdodtsContr($('#form' + (auxVerifi) + ' .group-' + (auxVerifi) + '-' + (index + 1) + ' .custom-radio .form-check-input'));*/
        var auxGrupo = chkRdodtsContr($('#form' + (auxVerifi) + ' .group-' + auxForm + ' .custom-radio .form-check-input'));
        $('#rowSteppers .stepper .step-' + auxVerifi).addClass('completed');
        if (auxGrupo == null || auxGrupo == "") {
            $('#rowSteppers .stepper .step-' + auxVerifi).removeClass('completed');
            $('#rowSteppers .stepper .step-' + auxVerifi).addClass('warning');
			buttons = false;
            verificaCampos();
            return false;
        }
    });

	$('#form' + auxVerifi + ' .rowPregunta .form-control-sel .initialized').each(function(index){
		
		$(this).siblings('.select-dropdown').addClass('invalid');
		
		if($(this).val() != "-1"){
			$(this).siblings('.select-dropdown').removeClass('invalid');
		}
		else{
			selects = false;
			$('#rowSteppers .stepper .step-' + auxVerifi).removeClass('completed');
			$('#rowSteppers .stepper .step-' + auxVerifi).addClass('warning');
		}
	});
	
	if(buttons && selects){
		$('#rowSteppers .stepper .step-' + auxVerifi).removeClass('warning');	
	}
	
	return (buttons && selects);
}

function verificaCampos() {
    var auxVerifi = $('#rowSteppers .stepper .step.active span').text();
    
    $('#form' + auxVerifi + ' .row.d-flex.justify-content-around').each(function(index) {
    	var auxForm = $(this).attr('aux');
        var auxGrupo = chkRdodtsContr($('#form' + (auxVerifi) + ' .group-' + auxForm + ' .custom-radio .form-check-input'));
        if (auxGrupo == null || auxGrupo == "") {
            $(this).addClass('incomplete');
        }
    });
}

function f_finalizar(auxPonde) {
	showLoader();
	var continuar = verificaCompleted();
	if(continuar){
		console.log("Finalizar " + auxPonde)
		console.log('CLICK BTN FINALIZAR');
			
		var idRelacion = generaIdRelacion();
		guardaRespuestas(idRelacion);
		
		window.scrollTo(0, 0);
	}
	else{
		
	}
}

function generaIdRelacion(){
	
	var dataF = new FormData();
	var modo = $('#flagSave').val() == "true" ? 0 : 1;
	
	dataF.append( "modo", modo );
	dataF.append( "idVinculo", $('#idVinculo').val() );
	dataF.append( "vCuestio", $('#vCuestio').val() );
	dataF.append( "idProveedor", $('#proveedorId').val() );
	dataF.append( "tipo", $('#idTipo').val() );

	var idRelacion = 0;
	
	$.ajax( {
		url : $("#postVinculo").val(), 
		data : dataF,
		processData : false, 
		contentType : false, 
		type : 'POST',
		async: false,
		success : function(data) {
			if(!valIsNullOrEmpty(data)){
				var jsonResponse = JSON.parse(data);
				idRelacion = jsonResponse.idVinculo;
				console.log('jsonResponse: ' + jsonResponse);
			}
		}
	} );
	
	$('#vCuestionarioId').val(idRelacion);
	return idRelacion;
}

function guardaRespuestas(idRelacion){
	showLoader();
	var proveedorId = $('#proveedorId').val();
	var idTipo = $('#idTipo').val();
	var vCuestionario = $('#idTipoVigente').val();
	var flagSave = $('#flagSave').val();

	var arrRespuesta = [];
	
	$.each( $('.divPregunta') , function (index, data){
		
		var idPregunta = $(data).attr('data');
		var respuesta = chkRdodtsContr($(data).find('input.opcCheck'));
		var evidencia = $(data).find('input.eviCheck').is(":checked");
		var estatusDoc = $('#selPregunta-'+idPregunta +' select').val();
		var comentrio = $('#selPregunta-'+idPregunta +' textarea.form-control').val();

		var auxResp = new objRespuesta (idPregunta, respuesta, evidencia, estatusDoc, comentrio) ;
		arrRespuesta.push(auxResp);
		
		
	});
	
	var dataF = new FormData();
	dataF.append( "idRelacion", idRelacion );
	dataF.append( "proveedorId", proveedorId );
	dataF.append( "idTipo", idTipo );
	dataF.append( "vCuestionario", vCuestionario );
	dataF.append( "flagSave", flagSave );
	
	dataF.append( "arrFinal", JSON.stringify ( arrRespuesta ) );
	
	$.ajax( {
		url : $("#postSavePonderado").val(), 
		data : dataF,
		processData : false, 
		contentType : false, 
		type : 'POST',
		async: false,
		success : function(data) {
			/*submitBtn();*/
			if(!valIsNullOrEmpty(data)){
				var respJson = JSON.parse(data);
				if(respJson.code == 0 ){
					submitBtn();
				}else{
					showMessageError( '.navbar', respJson.msg, 0 );
					hideLoader();
				}
			}
		}
	} );
	
	
	/*hideLoader();*/
}

function submitBtn(){
	console.log('SAVE BTN');
	$('#btnDataCuest').click();
}

function selectDestroy(objeto, enabled) {
	$(objeto).prop("disabled", enabled);
	$(objeto).materialSelect('destroy');
	$(objeto).materialSelect();
}

function llenaRespuestas(){
	window.scrollTo(0, 0);
	$("#modalConfirma").modal("hide");
	showLoader();
	var respJson = JSON.parse(listRespuesta);
	$.each( respJson, function( key, value ) {
		var curIdPreg = value._idPregunta;
		var curResp = value._respuesta;
		var curComentario = value._comentrio;
		var curEstatusDoc = value._estatusDoc;
		var curEvidencia = value._evidencia;
		
		$('.divPregunta[data="'+curIdPreg+'"] .opcCheck[value="'+curResp+'"]').attr('checked', true);
		$('.divPregunta[data="'+curIdPreg+'"] .eviCheck').attr('checked', curEvidencia);
		$('.rowPregunta[data="'+curIdPreg+'"] textarea').val(curComentario).siblings('label').addClass('active');
		$('.rowPregunta[data="'+curIdPreg+'"] select[value="1"]').val(curComentario);
		
		$('.rowPregunta[data="'+curIdPreg+'"] select option[value="'+curEstatusDoc+'"]').attr("selected","selected");
		/*if(reinicio)
			selectDestroy($('.rowPregunta[data="'+curIdPreg+'"] select') , false);*/
	});
	showForm('fin');
	$('li.step a').click();
	hideLoader();
}