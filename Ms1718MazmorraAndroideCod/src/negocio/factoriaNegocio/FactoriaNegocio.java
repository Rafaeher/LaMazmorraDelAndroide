package negocio.factoriaNegocio;

import negocio.cliente.SACliente;
import negocio.producto.SAProducto;
import negocio.venta.SAVenta;

public abstract class FactoriaNegocio {
	private static FactoriaNegocio instancia;

	public synchronized static FactoriaNegocio obtenerInstancia() {
		if (instancia == null) {
			instancia = new FactoriaNegocioImp();
		}

		return instancia;
	}

	public abstract SACliente generarSACliente();
	public abstract SAProducto generarSAProducto();
	public abstract SAVenta generarSAVenta();
}
