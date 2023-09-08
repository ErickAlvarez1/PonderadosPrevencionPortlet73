<!-- MODAL TEMAS EDITAR -->
<div class="modal" id="modalAddProveedor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg modal-dialog-centered" role="document">
	<!--Content-->
		<div class="modal-content">
			<!--Header-->
			<div class="modal-header blue-gradient text-white">
				<h3 class="heading lead">Nuevo Proveedor</h3>
				
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true" class="white-text">&times;</span>
				</button>
			</div>

			<!--Body-->
			<div class="modal-body">
				<div class="row infoProveedor">
					<div class="col-md-12">
						<div class="md-form">
							<input type="text" id="nombreProv" class="form-control">
							<label for="nombreProv">Nombre Proveedor</label>
						</div>
					</div>
				</div>

			</div><!--  -->
			<!--Footer-->
			<div class="modal-footer justify-content-center blue-gradient">
				<button type="button" class="btn btn-pink" style="display: none;">Cancelar</button>
				<button onclick="" type="button" class="btn btn-pink" data-dismiss="modal">Cancelar</button>
				<button id="saveProv" onclick="saveProveedor();" type="button" class="btn btn-blue" disabled>Guardar</button>
<!-- 				<button id="saveTemas" onclick="saveEditPregunta();" type="button" class="btn btn-blue" disabled>Guardar</button> -->
			</div>
		</div>
	<!--/.Content-->
	</div>
</div>
<!-- MODAL TEMAS EDITAR -->