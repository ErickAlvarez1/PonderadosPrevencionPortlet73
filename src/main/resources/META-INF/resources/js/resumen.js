var myRadarChart;
$(document).ready(function() {
	
	var arrTitulos = [ ];
	$.each( $('.txtTema'), function( key, value ) {
		arrTitulos.push( $(this).text() );
	});

	var arrRes = [ ];
	$.each( $('.resTit'), function( key, value ) {
		arrRes.push( $(this).text() );
		arrTitulos[key] = arrTitulos[key] + " " + $(this).text() + "%";
	});
	
	var punTot = 0;
	var arrPun = [ ];
	$.each( $('.resTema'), function( key, value ) {
		arrPun.push( $(this).text() );
		punTot = punTot + parseInt($(this).text());
	});

	var punMaxTot  = 0;
	var arrPunMax = [ ];
	$.each( $('.maxTema'), function( key, value ) {
		arrPunMax.push( $(this).text() );
		punMaxTot = punMaxTot + parseInt($(this).text());
	});
	var arrPorcen = [ ];
	$.each( $('.porcTema'), function( key, value ) {
		arrPorcen.push( $(this).text() );
	});
	
	var porcTot = Math.round((punTot / punMaxTot) * 100);
	
	$.each( arrTitulos, function( key, value ) {
		/*var auxPreg = new objResumen(arrTitulos[key], 0, 0, 0, arrRes[key]);*/
		$('#contenResumen').append(`
				<div class="row .califTema">
					<div class="col-md-4 text-justify" style="border: solid 1px;">
						<p>`+arrTitulos[key]+`</p>
					</div>
					<div class="col-md-2 text-center" style="border: solid 1px;">
						<p>`+arrPorcen[key]+`%</p>
					</div>
					<div class="col-md-2 text-center" style="border: solid 1px;">
						<p>`+arrPunMax[key]+`</p>
					</div>
					<div class="col-md-2 text-center" style="border: solid 1px;">
						<p>`+arrPun[key]+`</p>
					</div>
					<div class="col-md-2 text-center" style="border: solid 1px;">
						<p>`+arrRes[key]+`%</p>
					</div>
				</div>
		`);
	});
	
	$('#pObt').text(punTot);
	$('#pMax').text(punMaxTot);
	$('#pTot').text(porcTot+'%');
	$('#intConfianta').val(porcTot);
	
	/***radar***/
    var ctxR = document.getElementById("radarChart").getContext('2d');
    myRadarChart = new Chart(ctxR, {
    	type: 'radar',
    	data: {
		    /*labels: ["1.-Constitución Legal de la Organización", "2.-Contexto de la organización", "3.-Gestión de Recursos Humanos", "4.-Gestión de Mantenimiento de Flota vehicular", "5.-Rastreo y monitoreo de flota vehicular", "6.-Capacitación de Personal", "7.-Tráfico y Logistica", "8.-Gestión de riesgos", "Seguridad en instalaciones"],*/
		    labels: arrTitulos,
		    datasets: [{
			    label: "Resumen",
			    /*data: [75.76, 97, 74, 50, 74, 75, 48, 83, 95],*/
			    data: arrRes,
			    backgroundColor: [
			    	'rgba(105, 0, 132, .2)',
			    ],
			    borderColor: [
			    	'rgba(200, 99, 132, .7)',
			    ],
			    borderWidth: 2
		    }]
    	},
    	options: {
    		responsive: true,
			maintainAspectRatio: false,
    		scale: {
    		    ticks: {
    		        beginAtZero: true,
    		        max: 100,
    		        min: 0,
    		        stepSize: 25
    		    },
    		    pointLabels:{
        			fontSize : 14
        		}
    		}
    	}
    });
    /***radar***/
    
    var arrValHorizontal = [0, porcTot, 0];
    /**Horizontal**/
    new Chart(document.getElementById("horizontalBar"), {
    	"type": "horizontalBar",
    	"data": {
    		"labels": [" ", " ", " "],
    		"datasets": [{
    			"label": "PORCENTAJE DE CONFIANZA",
    			/*"data": [porcTot],*/
    			"data": arrValHorizontal,
    			"height":300,
    			"fill": false,
    			/*"backgroundColor": ["rgba(255, 99, 132, 0.2)"],*/
    			"backgroundColor": ["rgba(140, 182, 252, 0.8)", "rgba(140, 182, 252, 0.8)", "rgba(140, 182, 252, 0.8)"],
    			/*"borderColor": ["rgb(255, 99, 132)"],*/
    			"borderColor": ["rgb(102, 156, 247)", "rgb(102, 156, 247)", "rgb(102, 156, 247)"],
    	"borderWidth": 1
    	}]
    	},
    	"options": {
    		"scales": {
    			"xAxes": [{
    				"ticks": {
    					"beginAtZero": true,
    					"steps": 25,
    					"stepSize": 25,
    					"max" : 100
    				},
    				"scaleLabel": {
    					"labelString": '---- DEFICIENTE ----------------- REQUIERE MEJORAS ----------------------- ACEPTABLE ------------------------------- CONFIABLE ---------------',
    					"display": true,
    				}
    			}]
    		}
    	}
    });
    /**Horizontal**/ 
});

window.onload = function(){
  var tableCont = document.querySelector('#resumenEvaluacion')
  /**
   * scroll handle
   * @param {event} e -- scroll event
   */
  function scrollHandle (e){
    var scrollTop = this.scrollTop;
    this.querySelector('thead').style.transform = 'translateY(' + scrollTop + 'px)';
  }
  
  tableCont.addEventListener('scroll',scrollHandle)
}

function refreshPage(){
	showLoader();
	
	var dataF = new FormData();
	
	dataF.append( "idRespuesta", $('#idRespuesta').val() );
	dataF.append( "confianza", $('#intConfianta').val() );
	
	$.ajax( {
		url : $("#postActivaC").val(), 
		data : dataF,
		processData : false, 
		contentType : false, 
		type : 'POST',
		async: false,
		success : function(data) {
			if (valIsNullOrEmpty(data)) {
				showMessageError('.navbar', "Error", 0);
			} else {
				var response = jQuery.parseJSON(data);
				if( response.codigo ==0 ){
					/*showMessageSuccess('.navbar', response.msg, 0);*/
					hideLoader();
					var auxTxt = 'V'+ $("#vCuestionario").val() + '.' + response.vinculo;
					$('#versionM').text(auxTxt);;
					$("#modalVersion").modal("show");
					
					/*location. reload();*/
				}else{
					showMessageError('.navbar', response.msg, 0);
					hideLoader();
				}
			}
		}
	} );
	window.scrollTo(0, 0);	
}

$( ".acord" ).click(function() {
	var auxAcord = $(this).attr('acord');
	$('tr[acord="'+auxAcord+'"]').each(function( index ) {
		console.log('index: ' + index );
		if( $(this).hasClass('d-none') ){
			$(this).removeClass('d-none');
		}else{
			$(this).addClass('d-none');
		}
	});
});

$("#modalVersion").on("hidden.bs.modal", function () {
    // Aquí va el código a disparar en el evento
	var url      = window.location.href;
	$(location).attr('href',url);
});

function submitBtn(){
	$('#btnActualizar').click();
}