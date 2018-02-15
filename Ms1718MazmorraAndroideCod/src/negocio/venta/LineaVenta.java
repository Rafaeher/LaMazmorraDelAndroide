package negocio.venta;

public class LineaVenta {
	private int id;
	private int cantidad;
	private double precio;

	public LineaVenta(int id, int cantidad) {
		this.id = id;
		this.cantidad = cantidad;
	}

	public LineaVenta(int id, int cantidad, double precio) {
		this.id = id;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
