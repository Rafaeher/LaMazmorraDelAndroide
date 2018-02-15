package negocio.venta;

import java.util.List;

import integracion.query.DatosQuery;

public interface SAVenta {
	int devolverProducto(TransferVenta transferVenta, int idProducto, int cantidadADevolver);
	int cerrarVenta(TransferVenta tVen);
	TransferVenta mostrarUnaVenta(TransferVenta tVen) throws Exception;
	List<TransferVenta> mostrarTodasVentas() throws Exception;
	List<TransferVenta> mostrarVentasConProductosAClientesTipo(DatosQuery datos) throws Exception;

}
