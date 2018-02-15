package integracion.query;

public class DatosQuery {
	private Integer idProducto;
	private String tipoCliente;
	
	public DatosQuery(Integer idProducto, String tipoCliente) {
		this.idProducto = idProducto;
		this.tipoCliente = tipoCliente;
	}
	
	public Integer getIdProducto() {
		return this.idProducto;
	}
	
	public String getTipoCliente() {
		return this.tipoCliente;
	}

}
