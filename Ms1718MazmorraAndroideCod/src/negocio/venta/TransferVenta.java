package negocio.venta;

import java.util.HashMap;

public class TransferVenta {
	private int idVenta;
	private int idCliente;
	private String fecha;
	private double precio;

	private HashMap<Integer, LineaVenta> productos;

	public TransferVenta() {
		this.productos = new HashMap<>();
	}

	public TransferVenta(int id) {
		this.idVenta = id;
		this.productos = new HashMap<>();
	}

	public TransferVenta(HashMap<Integer, LineaVenta> productos) {
		this.productos = productos;
	}

	public TransferVenta(int id, int cliente, String fecha, double precio,
			HashMap<Integer, LineaVenta> mapProductos) {
		this.idVenta = id;
		this.idCliente = cliente;
		this.fecha = fecha;
		this.precio = precio;
		this.productos = mapProductos;

	}

	public TransferVenta(int day, int month, int year) {
		this.precio = 0;
		this.fecha = String.valueOf(day) + '/' + String.valueOf(month) + '/'
				+ String.valueOf(year);
		this.productos = new HashMap<Integer, LineaVenta>();
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int id) {
		this.idVenta = id;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int cliente) {
		this.idCliente = cliente;
	}

	public HashMap<Integer, LineaVenta> getProductos() {
		return this.productos;
	}

	public int anadirProducto(int idProducto, int cantidad) {
		int respuesta = 0;
		LineaVenta producto = null;

		if (this.productos.containsKey(idProducto)) {
			producto = productos.get(idProducto);
			cantidad += producto.getCantidad();
			producto.setCantidad(cantidad);
			productos.put(idProducto, producto);
			respuesta = 1;
		} else {
			producto = new LineaVenta(idProducto, cantidad);
			productos.put(idProducto, producto);
			respuesta = 0;
		}

		return respuesta;
	}

	public int eliminarProducto(int idProducto, int cantidad) {
		int respuesta = 0;

		LineaVenta producto = productos.get(idProducto);
		if (producto != null) {
			if (producto.getCantidad() <= cantidad) {
				producto.setCantidad(0);
				respuesta = 0;
			} else if (producto.getCantidad() > cantidad && cantidad > 0) {
				producto.setCantidad(producto.getCantidad() - cantidad);
				respuesta = 1;
			}

			if (producto.getCantidad() == 0) {
				productos.remove(idProducto);
			}

		} else {
			respuesta = 2;
		}

		return respuesta;
	}
}
