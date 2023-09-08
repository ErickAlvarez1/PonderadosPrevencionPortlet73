function submitBtn(){
	console.log('SAVE BTN');
	$('#btnDataProv').click();
}

$("#selProveedor").change(function() {
	var auxVal = $(this).val();
	if(auxVal != -1){
		$('#proveedorId').val(auxVal);
		$('#provDone').attr('disabled', false);		
	}
	else{
		$('#proveedorId').val(0);
		$('#provDone').attr('disabled', true);
	}
});

function saveProveedor(){
	var dataF = new FormData();
	
	dataF.append( "nombre", $('#nombreProv').val() );
	dataF.append( "tipo", $('#idTipo').val() );
	
	$.ajax( {
		url : $("#postAddProveedor").val(), 
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
					showMessageSuccess('.navbar', response.msg, 0);
					location. reload();
				}else{
					showMessageError('.navbar', response.msg, 0);
				}
			}
			
		}
	} );
}