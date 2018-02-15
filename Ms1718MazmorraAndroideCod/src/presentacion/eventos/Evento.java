package presentacion.eventos;

/** Enumerados con las acciones que se pueden realizar */
public enum Evento {
	
	// Mantened el orden alfabetico

	
	/*			EVENTOS PARTE JPA				*/
	
	
			/*	ACTIVIDAD */
	abrirMenuActividad,
	
	abrirVistaActualizarActividad,
	
	abrirVistaAltaActividad,
	
	abrirVistaBajaActividad,
	
	abrirVistaMostrarUnaActividad,
	
	actualizarActividad,
	
	actualizarActividadOK,
	
	altaActividad,
	
	altaActividadOK,
	
	bajaActividad,
	
	bajaActividadOK,
	
	errorActualizarActividadIdNoExistente,
	
	errorActualizarActividadNombreYaExistente,
	
	errorActualizarActividadReactivacion,

	errorActualizarActividadOptimisticLockException,
	
	errorActualizarActividadAsignacionConMayorDuracion,
	
	errorAltaActividadNombreYaExistente,
	
	errorAltaActividadOptimisticLockException, 
	
	errorAltaActividadReactivacion,
	
	errorBajaActividadEmpleadosActivos,
	
	errorBajaActividadIdNoExistente,
	
	errorBajaActividadOptimisticLockException,
	
	errorBajaActividadYaDadoDeBaja,
	
	errorMostrarTodasActividadesNoExisteNinguna,
	
	errorMostrarUnaActividadIdNoExistente,
	
	mostrarTodasActividades,
	
	mostrarTodasActividadesOK,
	
	mostrarUnaActividad,
	
	mostrarUnaActividadOK,
	
			/* DEPARTAMENTO */
	
	
	
	abrirMenuDepartamento,
	
	abrirVistaActualizarDepartamento,
	
	abrirVistaAltaDepartamento,
	
	abrirVistaBajaDepartamento,
	
	abrirVistaMostrarUnDepartamento,
	
	abrirVistaCalcularSueldoNetoDepartamento,
	
	actualizarDepartamento,
	
	actualizarDepartamentoOK,
		
	altaDepartamento,
	
	altaDepartamentoOK,
	
	bajaDepartamento,
	
	bajaDepartamentoOK,
	
	calcularSueldoNetoDepartamento,
	
	calcularSueldoNetoDepartamentoOK,
	
	mostrarUnDepartamento,
	
	mostrarUnDepartamentoOK,
	
	mostrarTodosDepartamentos,
	
	mostrarTodosDepartamentosOK,
	
	errorActualizarDepartamentoOptimisticLockException,
	
	errorActualizarDepartamentoReactivacion,
	
	errorActualizarDepartamentoNombreYaExistente,
	
	errorActualizarDepartamentoIdNoExistente,
	
	errorAltaDepartamentoOptimisticLockException,
	
	errorAltaDepartamentoReactivacion,
	
	errorAltaDepartamentoNombreYaExistente,
	
	errorBajaDepartamentoIdNoExistente,
	
	errorBajaDepartamentoYaDadoDeBaja,
	
	errorBajaDepartamentoEmpleadosActivos,
	
	errorBajaDepartamentoOptimisticLockException,
	
	errorCalcularSueldoNetoDepartamentoOptimisticLockException,
	
	errorCalcularSueldoNetoDepartamentoIdNoExistente,
	
	errorCalcularSueldoNetoDepartamentoDepartamentoInactivo,
	
	errorMostrarUnDepartamentoIdNoExistente,
	
	errorMostrarTodosDepartamentosNoExisteNinguno,
	 
			/*	EMPLEADO */
	
	abrirMenuEmpleado,
	
	abrirVistaAltaEmpleadoTemporal,

	abrirVistaAltaEmpleadoFijo,
	
	abrirVistaActualizarEmpleadoTemporal,
	
	abrirVistaActualizarEmpleadoFijo,
	
	abrirVistaAsignarActividad,
	
	abrirVistaBajaEmpleado,
	
	abrirVistaDesasignarActividad,
	
	abrirVistaMostrarUnEmpleado,
	
	actualizarEmpleado,
	
	actualizarEmpleadoOK,
	
	actualizarEmpleadoOKReactivado,
	
	altaEmpleado, 
	
	altaEmpleadoOK,
	
	asignarActividad,
	
	asignarActividadOK,
	
	asignarActividadOKAsignacionModificada,
	
	bajaEmpleado,
	
	bajaEmpleadoOK,
	
	desasignarActividad,
	
	desasignarActividadOK,
	
	errorActualizarEmpleado_DepartamentoInactivo,
	
	errorActualizarEmpleado_DepartamentoInexistente,
	
	errorActualizarEmpleadoDNIRepetido,
	
	errorActualizarEmpleado_EmpleadoInexistente,
	
	errorActualizarEmpleadoTiposDistintos,
	
	errorActualizarEmpleadoOptimisticLockException,
	
	errorAltaEmpleadoDepartamentoNoExistente,
	
	errorAltaEmpleadoDepartamentoInactivo,
	
	errorAltaEmpleadoDNIYaExistente,
	
	errorAltaEmpleadoOptimisticLockException,
	
	errorAltaEmpleadoReactivacion,
	
	errorAltaEmpleadoTiposEmpleadosNoCoinciden,
	
	errorAsignarActividad_ActividadInactiva,
	
	errorAsignarActividad_ActividadInexistente,
	
	errorAsignarActividad_EmpleadoInactivo,
	
	errorAsignarActividad_EmpleadoInexistente,
	
	errorAsignarActividadOptimisticLockException,
	
	errorAsignarActividadHorasMayorQueDuracionActividad,
	
	errorBajaEmpleado_ActividadesAsignadas,
	
	errorBajaEmpleado_EmpleadoInactivo,
	
	errorBajaEmpleado_EmpleadoInexistente,
	
	errorBajaEmpleadoOptimisticLockException,
	
	errorDesasignarActividad_ActividadInactiva,
	
	errorDesasignarActividad_ActividadInexistente,
	
	errorDesasignarActividad_AsignacionInexistente,
	 
	errorDesasignarActividad_EmpleadoInactivo,
	
	errorDesasignarActividad_EmpleadoInexistente,
	
	errorDesasignarActividadOptimisticLockException,
	
	errorMostrarUnEmpleadoNoExiste,
	
	errorMostrarTodosEmpleadosNoExisteNinguno,
	
	mostrarUnEmpleado,
	
	mostrarUnEmpleadoOK,
	
	mostrarTodosEmpleados,
	
	mostrarTodosEmpleadosOK,
	
	
	
	
	
	
	/*				EVENTOS PARTE DAO			*/
	
	
	abrirActualizarCliente,
	
	abrirActualizarClienteEstandar,
	
	abrirActualizarClienteVip,
	
	abrirActualizarProducto,
	
	abrirActualizarVenta,
	
	abrirAltaCliente,
	
	abrirAltaClienteVip,
	
	abrirAltaClienteEstandar,
	
	abrirAltaProducto,
	
	abrirBajaCliente,
	
	abrirBajaProducto,
	
	abrirDevolverProducto,
	
	abrirCerrarVenta,
	
	abrirMenuCliente,
	
	abrirMenuPrincipal,
	
	abrirMenuProducto, 
	
	abrirMenuVenta,
	
	abrirMostrarProductosEnVentasSuperioresAUnPrecio,
	
	abrirMostrarVentasConProductosAClientesTipo, 
	
	abrirMostrarUnaVenta, 
	
	abrirMostrarUnCliente,
	
	abrirMostrarUnProducto,
	
	abrirVenta,
	
	actualizarCliente,
	
	actualizarProducto,
	
	altaCliente,
	
	altaProducto,
	
	bajaCliente,
	
	bajaProducto,
	
	cerrarVenta,
	
	devolverProducto,
	
	errorAltaClienteDuplicado, 
	
	errorAltaProductoDuplicado,
	
	errorActualizarCliente,
	
	errorActualizarProducto, 
	
	errorActualizarProductoInactivo, 
	
	errorActualizarProductoConMismoNombre,
	
	errorAltaCliente,
	
	errorAltaClienteDistintoDescuento,
	
	errorActualizarClienteConMismoEmail, 
	
	errorActualizarClienteReactivacion,
	
	errorActualizarClienteTiposNoCoinciden,
	
	errorAltaProducto,
	
	errorAltaProductoDuplicadoActivo,
	
	errorAltaProductoDuplicadoInactivo,
	
	errorAltaVenta,
	
	errorAnadirProductoAlCarrito,
	
	errorArgumentos,
	
	errorBajaCliente,
	
	errorBajaClienteDuplicado,
	
	errorBajaProducto,
	
	errorBajaVenta,
	
	errorBajaProductoDuplicado,
	
	errorCambioTipoCliente,
	
	errorCerrarVenta,
	
	errorCerrarVentaCliente, 
	
	errorCerrarVentaCarrito,
	
	errorConexionBBDD,
	
	errorDevolverProducto,
	
	errorDevolverProductoDuplicado,
	
	errorDevolverProductoInexistente,
	
	errorDevolverProductoVentaInexistente,
	
	errorDevolverProductoCantidadExcesiva,
	
	errorDevolverProductoClienteInactivo,
	
	errorDevolverProductoReactivacionProducto,
	
	errorDevolverProductoCantidadExcesivaYReactivacion,
	
	errorFueraStock,
	
	errorMostrarCarrito,
	
	errorMostrarTodosClientes,
	
	errorMostrarTodosProductos,
	
	errorMostrarProductosEnVentasSuperioresAUnPrecio,
	
	errorMostrarVentasConProductosAClientesTipo,
	
	errorMostrarVentasConProductosAClientesTipoProductoNoExistente,
	
	errorMostrarTodasVentas,
	
	errorQuitarProductoDelCarrito,
	
	errorMostrarUnCliente,
	
	errorMostrarUnProducto,
	
	errorMostrarUnaVenta,
	
	errorNoExisteCarrito, 

	errorProductosAsignados,
	
	mostrarCarrito,
	
	mostrarCarritoVacio,
	
	mostrarTodasVentas,
	
	mostrarTodosClientes,
	
	mostrarTodosProductos,
	
	mostrarProductosEnVentasSuperioresAUnPrecio,

	mostrarVentasConProductosAClientesTipo,
	
	mostrarUnaVenta,
	
	mostrarUnCliente,
	
	mostrarUnProducto,
}
