package negocio.producto;

import java.util.List;

public interface SAProducto {

	public int altaProducto(TransferProducto tPro);
	public int bajaProducto(TransferProducto tPro);
	public int actualizarProducto(TransferProducto tPro);
	public TransferProducto mostrarUnProducto(TransferProducto tPro) throws Exception;
	public List<TransferProducto> mostrarTodosProductos() throws Exception;
	public List<TransferProducto> mostrarProductosEnVentasSuperioresA(double cantidad) throws Exception;
}
