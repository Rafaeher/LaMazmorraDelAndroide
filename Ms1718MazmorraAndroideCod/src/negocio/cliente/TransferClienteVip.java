package negocio.cliente;

public class TransferClienteVip extends TransferCliente {
	/**Descuento aplicable del cliente vip*/
	private int descuento;
	/**Puntos acumulados del cliente vip*/
	private int puntos;

	/**Constructora por defecto de los transfers*/
	public TransferClienteVip() {
		super();
	}

	/**Crea un transfer con id de un cliente
	 * @param id*/
	public TransferClienteVip(int id) {
		super(id);
	}

	/**Crea un transfer con el nombre, la direccion, el correo y el descuento del cliente
	 * @param nombre
	 * @param direccion
	 * @param correo
	 * @param descuento*/
	public TransferClienteVip(String nombre, String apellido, String direccion,
			String correo, int descuento) {
		super(nombre, apellido, direccion, correo);
		this.descuento = descuento;
		this.puntos = 0;
	}

	public TransferClienteVip(int id, String nombre, String apellido,
			String direccion, String correo, int descuento) {
		super(id, nombre, apellido, direccion, correo);
		this.descuento = descuento;
		this.puntos = 0;
	}

	public TransferClienteVip(int id, String nombre, String apellido,
			String direccion, String correo) {
		super(id, nombre, apellido, direccion, correo);
		this.puntos = 0;
	}

	public TransferClienteVip(int id, boolean activo, String nombre,
			String apellido, String direccion, String correo, int descuento,
			int puntos) {
		super(id, activo, nombre, apellido, direccion, correo);
		this.descuento = descuento;
		this.puntos = puntos;
	}

	/**Edita el campo del descuento
	 * @param descuento nuevo*/
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	/**@return puntos acumulados del cliente vip*/
	public int getPuntos() {
		return puntos;
	}

	/**Edita el campo de los puntos
	 * @param puntos nuevos*/
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	@Override
	public String getTipo() {
		return "Vip";
	}

	public int getDescuento() {
		return descuento;
	}


	@Override
	public String toString() {
		return "Id: " + id + "\n    Cliente " + getTipo() + "\n    Nombre: "
				+ nombre + "\n    Direccion: " + direccion + "\n    Correo: "
				+ correo + "\n    Descuento: " + descuento + "%\n    Puntos: "
				+ puntos + "\n    Activo: " + (activo ? "Si" : "No");
	}
}
