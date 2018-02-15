package integracion.cliente;

import java.util.List;

import negocio.cliente.TransferCliente;

public interface DAOCliente {

	public TransferCliente readByEmail(String email) throws Exception;
	
	public TransferCliente read(int id) throws Exception;

	public List<TransferCliente> readAll() throws Exception;
	
	public int write(TransferCliente tCli) throws Exception;

	public int update(TransferCliente tCli) throws Exception;

}
