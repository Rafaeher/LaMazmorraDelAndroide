package presentacion.despachadorVista;

import presentacion.GUI;
import presentacion.controlador.Contexto;
import presentacion.factoriaPresentacion.FactoriaPresentacion;

public class DespachadorVistaImp extends DespachadorVista {
		private GUI vista;

		@Override
		public void crearVista(Contexto contexto) {

			switch(contexto.getEvento()) {			
								
			case abrirMenuCliente:
				vista = FactoriaPresentacion.obtenerInstancia().crearGUI("menuCliente");
				vista.setVisible(true);
				break;

			case abrirMenuPrincipal:
				vista = FactoriaPresentacion.obtenerInstancia().crearGUI("menuPrincipal");
				vista.setVisible(true);
				break;

			case abrirMenuProducto:
				vista = FactoriaPresentacion.obtenerInstancia().crearGUI("menuProducto");
				vista.setVisible(true);
				break;
			
			case abrirMenuVenta:
				vista = FactoriaPresentacion.obtenerInstancia().crearGUI("menuVenta");
				vista.setVisible(true);
				break;
				
			default:
				vista.actualizar(contexto);
				break;
				
			}
		}
}
