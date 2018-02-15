package negocio.cliente;

import java.util.List;


public interface SACliente {
	
	public int altaCliente(TransferCliente transferCliente);
	public int bajaCliente(TransferCliente transferCliente);
	public int actualizarCliente(TransferCliente transferCliente);
	public TransferCliente mostrarUnCliente(TransferCliente transferCliente) throws Exception;
	public List<TransferCliente> mostrarTodosClientes() throws Exception;
}
