package integracion.producto;

import java.util.List;

import negocio.producto.TransferProducto;

public interface DAOProducto {
	
	public TransferProducto read(int id) throws Exception;
	public TransferProducto readByName(String name) throws Exception;
	public List<TransferProducto> readAll() throws Exception;
	public int write(TransferProducto tPro) throws Exception;
	public int update(TransferProducto tPro) throws Exception;
}
