package negocio.producto;

public class TransferProducto {
	private String name;
	private int id;
	private double precio;
	private int stock;
	private int so;
	private boolean activo;

	
	public TransferProducto(double precio, int stock, int so, String name) {
		this.precio = precio;
		this.stock = stock;
		this.so = so;
		this.name = name;
		this.activo = true;
	}

	public TransferProducto(int id, boolean activo, double precio, int stock,
			int so, String name) {
		this.id = id;
		this.precio = precio;
		this.stock = stock;
		this.so = so;
		this.name = name;
		this.activo = activo;
	}

	public TransferProducto(int id) {
		this.id = id;
	}

	public TransferProducto(int id, double precio, int stock, int so,
			String name) {
		this.id = id;
		this.precio = precio;
		this.stock = stock;
		this.so = so;
		this.name = name;
		this.activo = true;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getSo() {
		return so;
	}

	public void setSo(int so) {
		this.so = so;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public boolean equals(Object o) {
		if (!(o instanceof TransferProducto)) {
			return false;
		} else {
			return this.name.equalsIgnoreCase(((TransferProducto) o).name);
		}
	}
}
