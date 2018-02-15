package integracion.factoriaIntegracion;

import integracion.cliente.DAOCliente;
import integracion.producto.DAOProducto;
import integracion.venta.DAOVenta;

public abstract class FactoriaIntegracion {
	private static FactoriaIntegracion instancia;

	public synchronized static FactoriaIntegracion obtenerInstancia() {
		if (instancia == null)
			instancia = new FactoriaIntegracionImp();
		return instancia;
	}

	public abstract DAOCliente generaDAOCliente();
	public abstract DAOProducto generaDAOProducto();
	public abstract DAOVenta generaDAOVenta();
}
