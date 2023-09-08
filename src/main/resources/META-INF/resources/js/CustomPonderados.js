$(document).ready(function() {
	showLoader();
    window.scrollTo(0, 0);
    hideLoader();
});


function llenaSelectData(idVar, dataSelect, enabled){
	$('#' + idVar + '  option').remove();
	$('#' + idVar).append("<option value=\"-1\" selected>Seleccione una opción</option>");
	$.each(dataSelect, function (key, registro) {
		$('#' + idVar).append("<option value=\"" + registro._id + "\">" + registro._descripcion + "</option>");
	});
	selectDestroy($('#' + idVar), enabled);
}
function selectDestroy(objeto, enabled) {
	$(objeto).prop("disabled", enabled);
	$(objeto).materialSelect('destroy');
	$(objeto).materialSelect();
}



$(document).ready(function() {
	/*
    $("#tablaEvaluacion").DataTable({
        "ordering": false,
        scrollY: "80vh",
        scrollX: true,
        scrollCollapse: true,
        paging: false,
        fixedColumns: {
            leftColumns: 5
        },
        fixedHeader: {
            header: true
        }
    });

	$(function() {
		$('[data-toggle="tooltip"]').tooltip();
	})
    */


    $(".custom-control.custom-radio").change(function() {
        var auxInput = this.firstElementChild.getAttribute('name');
        /*console.log("change radio");
        console.log(auxInput); /*group-2-1*/
        $('.justify-content-around.' + auxInput).removeClass('incomplete');
    });

    hideAll();
    /************/
    window.scrollTo(0, 0);
});

function hideAll() {
    $('form').hide();
    $('form').hide();
    $('#form1').show();
    $('.step').removeClass('active');
    $('.step-1').removeClass('completed');
    $('.step-1').removeClass('warning');
    $('.step-1').addClass('active');
}

function backForm(){
	$('#form1').show();
    $('#rowSteppers').show();
    $('#resumenEvaluacion').hide();
    window.scrollTo(0, 0);
}

function calculaTotales(){
	$('.divs-tot').each(function (key, val) {
		console.log("VAL: " + val);
		var auxSuma = $(val).attr('id');
		var sumaTotal = 0;
		var auxDivs = $('.'+auxSuma);
		
		auxDivs.each(function (key, val) {
			var auxStr = $(val).text();
			var iNum = parseInt(auxStr);
			sumaTotal= sumaTotal + iNum;
		});
		$('#tot-max-'+(key+1)).text(auxDivs.length*99);
		$(val).text(sumaTotal);
	});
}

/*
function chkRdodtsContr(rdoBtons) {
    var res = null;
    $.each(rdoBtons, function(index, value) {
        if ($(value).is(':checked')) {
            res = $(value).val();
            return false;
        }
    });
    return res;
}
*/

function getIdRespuesta(idSolicitud, ponderado){
	$('#auxPonderado').val(ponderado);
	$.post($("#postGetIdRespuesta").val(), {
		idSolicitud: idSolicitud,
		ponderado: ponderado
	}).done(function (idRespuesta) {
		$('#idRespuesta').val(idRespuesta);
		console.log("DONE: " + idRespuesta);
		if(idRespuesta > 0){
			
			$("#centralModalSuccess").modal("show");
			/*
			$.post($("#postGetPonderadoBase").val(), {
				idRespuesta: idRespuesta
			}).done(function (data) {
				console.log("DATA: " + data);
				window.location.href = "./pdf-ponderado?idSolicitud="+idSolicitud+"&idResultado="+idRespuesta;
			});
			*/
		}
		else{
			showEvaluacion(ponderado);
			/*agregaAlertError("No hay PDF guardado.");*/
		}
	});
}


/*
function goToPdf(idSolicitud){
	var idRespuesta = $('#idRespuesta').val();
	$.post($("#postGetPonderadoBase").val(), {
		idRespuesta: idRespuesta
	}).done(function (data) {
		console.log("DATA: " + data);
		window.location.href = "./pdf-ponderado?idSolicitud="+idSolicitud+"&idResultado="+idRespuesta;
	});
}
*/
function btnEditar(){
	var ponderado = parseInt($('#auxPonderado').val());
	showEvaluacion(ponderado);
}

/*****************Funciones mensajes******************/
var idAlert = 0;
function agregaAlertSuccess(mensaje) {
	showMessageSuccess(".navbar", mensaje, idAlert);
	idAlert++;
}
function agregaAlertError(mensaje) {
	showMessageError(".navbar", mensaje, idAlert);
	idAlert++;
}
function eliminaAlerttext() {
	idAlert--;
	if (idAlert < 0) {
		idAlert = 0;
	}
	$(".alert-danger").remove();
	$(".invalid").removeClass('invalid');
}

function chkRdodtsContr(rdoBtons) {
    var res = null;
    $.each(rdoBtons, function(index, value) {
        if ($(value).is(':checked')) {
            res = $(value).val();
            return false;
        }
    });
    return res;
}

function chkSelContr(selects) {
    var res = null;
    $.each(selects, function(index, value) {
        if ($(value).val() != "-1") {
            res = $(value).val();
            return false;
        }
    });
    return res;
}

$('#banner .navbar').on("click", ".close", function () {
	$(this).parent().parent().remove();
	idAlert--;
	$('#banner .navbar .container-fluid-1280.lfr-alert-wrapper.in').each(function (k, v) {
		var top = 75 + (k * 64);
		$(this).attr("id", "customAlert" + k);
		$(this).attr("style", "height: 54px;margin-bottom:10px;position:  absolute;top: " + top + "px;z-index: 10;width:  90%;left: 5%;");
	});
});

$("#nombreProv").keyup(function(e) {
	var txt = $('#nombreProv').val();
	if (txt == ""){
		$('#saveProv').attr('disabled' , true);
	}else{
		$('#saveProv').attr('disabled' , false);		
	}
});

function regresaPantalla(val){
	showLoader();
	$("#modoVista").val(val);
	switch (modoVista){
		case '0':
			break;
		case '1':
			$("#btnDataProv").trigger("click");
			break
		case '2':
			$("#btnDataCuest").trigger("click");
			break;
		case '3':
			$("#retornoResumen").val("true");
			$("#btnActualizar").trigger("click");
			break;
	}
}
/*****************Funciones mensajes******************/