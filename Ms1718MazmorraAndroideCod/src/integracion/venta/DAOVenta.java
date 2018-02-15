package integracion.venta;

import java.util.List;

import negocio.venta.TransferVenta;

public interface DAOVenta {
	
	public TransferVenta read(int id) throws Exception;

	public List<TransferVenta> readAll() throws Exception;
	
	public int write(TransferVenta tVen) throws Exception;

	public int update(TransferVenta tVen) throws Exception;

}
