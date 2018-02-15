package negocio.factoriaNegocio;

import negocio.cliente.SACliente;
import negocio.cliente.SAClienteImp;
import negocio.producto.SAProducto;
import negocio.producto.SAProductoImp;
import negocio.venta.SAVenta;
import negocio.venta.SAVentaImp;

public class FactoriaNegocioImp extends FactoriaNegocio {

	@Override
	public SACliente generarSACliente() {
		return new SAClienteImp();
	}

	@Override
	public SAProducto generarSAProducto() {
		return new SAProductoImp();
	}

	@Override
	public SAVenta generarSAVenta() {
		return new SAVentaImp();
	}
}
