package negocio.cliente;

public class TransferClienteEstandar extends TransferCliente {
	protected boolean recibePublicidad;

	/**Constructora por defecto de los transfers*/
	public TransferClienteEstandar() {
		super();
	}

	/**Crea un transfer con id de un cliente
	 * @param id*/
	public TransferClienteEstandar(int id) {
		super(id);
	}

	
	public TransferClienteEstandar(String nombre, String apellido,
			String direccion, String correo) {
		super(nombre, apellido, direccion, correo);
		this.recibePublicidad = true;
	}
	
	public TransferClienteEstandar(String nombre, String apellido,
			String direccion, String correo, boolean recibePublicidad) {
		super(nombre, apellido, direccion, correo);
		this.recibePublicidad = recibePublicidad;

	}

	public TransferClienteEstandar(int id, String nombre, String apellido,
			String direccion, String correo) {
		super(id, nombre, apellido, direccion, correo);
		this.recibePublicidad = true;
	}

	public TransferClienteEstandar(int id, boolean activo, String nombre,
			String apellido, String direccion, String correo, boolean publicidad) {
		super(id, activo, nombre, apellido, direccion, correo);
		this.recibePublicidad = publicidad;
	}

	public TransferClienteEstandar(int id, String nombre, String apellido,
			String direccion, String correo, boolean publicidad) {
		super(id, nombre, apellido, direccion, correo);
		this.recibePublicidad = publicidad;
	}

	@Override
	public String getTipo() {
		return "Estandar";
	}

	public Boolean getRecibePublicidad() {
		return recibePublicidad;
	}

	public void setRecibePublicidad(boolean nuevoRecibePublicidad) {
		recibePublicidad = nuevoRecibePublicidad;
	}

	@Override
	public String toString() {
		return "Id: " + id + "\n    Cliente " + getTipo() + "\n    Nombre: "
				+ nombre + " \n    Apellido: " + apellidos
				+ "\n    Direccion: " + direccion + "\n    Correo: " + correo
				+ "\n    Recibe Publicidad: "
				+ (recibePublicidad ? "Si" : "No") + "\n    Activo: "
				+ (activo ? "Si" : "No");
	}
}
