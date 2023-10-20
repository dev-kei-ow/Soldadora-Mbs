$(document).ready(function() {
	$('#datatable').DataTable({
		lengthMenu: [5, 10, 20, 100, 200, 500],
		columnDefs: [
			{ targets: '_all', className: 'dt-center dt-head-center' },
			/*{ targets: [1], width: '650px', className: 'dt-left dt-head-left' },*/
			/*{ orderable: false, targets: [4, 5] },*/
			/*{ searchable: true, targets: [1] }*/
		],
		language: {
			lengthMenu: 'Mostrar _MENU_ registros',
			zeroRecords: 'Ningun registro encontrado',
			info: 'Mostrando _START_ a _END_ de _TOTAL_ registros',
			infoEmpty: 'Ningun registro encontrado',
			infoFiltered: '(filtrados desde _MAX_ registros totales)',
			search: 'Buscar:',
			loadingRecords: 'Cargando...',
			paginate: {
				first: 'Primero',
				last: 'Último',
				next: 'Siguiente',
				previous: 'Anterior'
			}
		}
	});
});