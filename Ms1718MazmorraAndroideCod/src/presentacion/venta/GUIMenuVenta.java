package presentacion.venta;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import integracion.query.DatosQuery;
import negocio.venta.LineaVenta;
import negocio.venta.TransferVenta;

import presentacion.GUI;
import presentacion.controlador.Contexto;
import presentacion.controlador.ControladorAplicacion;
import presentacion.eventos.Evento;

public class GUIMenuVenta extends JFrame implements GUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Action Performed Menu Botones (Redirección entre vistas)

	private void abrirVentaButtonActionPerformed() {
		Calendar c1 = GregorianCalendar.getInstance();
		int day = c1.get(Calendar.DAY_OF_MONTH);
		int month = c1.get(Calendar.MONTH);
		int year = c1.get(Calendar.YEAR);
		venta = new TransferVenta(day, month, year);
		Contexto contexto = new Contexto(Evento.abrirVenta, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void devolverButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirDevolverProducto, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void mostrarUnoButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirMostrarUnaVenta, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void mostrarTodosButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.mostrarTodasVentas, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void mostrarVentasConProductosClienteTipoButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirMostrarVentasConProductosAClientesTipo, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void menuPrincipalButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirMenuPrincipal, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
		this.dispose();
	}

	// Action Performed AbrirVenta

	private void cerrarVentaButtonActionPerfomed() {
		try {
			Integer idCli = Integer.parseInt(textIdCliente.getText());

			if (idCli < 0)
				throw new Exception();

			venta.setIdCliente(idCli);

			Contexto contexto = new Contexto(Evento.cerrarVenta, venta);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
			textIdCliente.setText("");
			textIdProducto.setText("");
			textCantidad.setText("");

		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
		}
	}

	private void anadirProductoButtonActionPerformed() {
		try {
			Integer idProducto = Integer.parseInt(textIdProducto.getText());
			Integer cantidadPro = Integer.parseInt(textCantidad.getText());

			if (idProducto < 0 || cantidadPro < 0)
				throw new Exception();

			int errCod = venta.anadirProducto(idProducto, cantidadPro);
			switch (errCod) {
			case 0:
				textAreaVenta.append("->Producto insertado\n\n");
				break;
			case 1:
				textAreaVenta.append("->Cantidad aumentada\n\n");
				break;
			case 2:
				textAreaVenta.append("->Error al insertar o aumentar el producto\n\n");
				break;
			}
			textIdProducto.setText("");
			textCantidad.setText("");

		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
		}
	}

	private void eliminarProductoButtonActionPerformed() {
		try {
			Integer idPro = Integer.parseInt(textIdProducto.getText());
			Integer cantidadPro = Integer.parseInt(textCantidad.getText());

			if (idPro < 0 || cantidadPro < 0)
				throw new Exception();

			int errCod = venta.eliminarProducto(idPro, cantidadPro);
			switch (errCod) {
			case 0:
				textAreaVenta.append("->Producto eliminado\n\n");
				break;
			case 1:
				textAreaVenta.append("->Cantidad reducida\n\n");
				break;
			case 2:
				textAreaVenta.append("->Error al eliminar o reducir el producto\n\n");
				break;
			}
			textIdProducto.setText("");
			textCantidad.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
		}
	}

	private void mostrarCarritoButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.mostrarCarrito, venta.getProductos());
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void volverDeMostrarCarritoAAbrirVentaButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVenta, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void cancelarAbrirVentaButtonActionPerformed() {
		textIdCliente.setText("");
		textIdProducto.setText("");
		textCantidad.setText("");
		CardLayout cl = (CardLayout) (panelContenidoVentas.getLayout());
		cl.first(panelContenidoVentas);
	}

	// Action Performed DevolverVenta

	private void aceptarDevolverVentaButtonActionPerformed() {
		try {
			Integer idVenta = Integer.parseInt(textIdVentaDevolver.getText());
			Integer idProducto = Integer.parseInt(textIdProductoDevolver.getText());
			Integer cantidad = Integer.parseInt(textCantidadDevolver.getText());

			if (idVenta < 0 || idProducto < 0 || cantidad < 0)
				throw new Exception();

			TransferVenta tVen = new TransferVenta(idVenta);
			tVen.anadirProducto(idProducto, cantidad);

			Contexto contexto = new Contexto(Evento.devolverProducto, tVen);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
			textIdVentaDevolver.setText("");
			textIdProductoDevolver.setText("");
			textCantidadDevolver.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarDevolverVentaButtonActionPerformed() {
		textIdVentaDevolver.setText("");
		textIdProductoDevolver.setText("");
		textCantidadDevolver.setText("");
		CardLayout cl = (CardLayout) (panelContenidoVentas.getLayout());
		cl.first(panelContenidoVentas);
	}

	// Action Performed MostrarUna

	private void aceptarMostrarUnaButtonActionPerformed() {
		try {
			Integer id = Integer.parseInt(textIdProductoMostrarUna.getText());

			if (id < 0)
				throw new Exception();

			TransferVenta tVen = new TransferVenta(id);

			Contexto contexto = new Contexto(Evento.mostrarUnaVenta, tVen);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
			textIdProductoMostrarUna.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarMostrarUnaButtonActionPerformed() {
		textIdProductoMostrarUna.setText("");
		CardLayout cl = (CardLayout) (panelContenidoVentas.getLayout());
		cl.first(panelContenidoVentas);
	}

	private void volverDeMostrarCarritoAMostrarUnaButtonActionPerformed() {
		CardLayout cl = (CardLayout) (panelContenidoVentas.getLayout());
		cl.first(panelContenidoVentas);
	}

	// Action Performed MostrarVentasProductosClientesTipo

	private void aceptarMostrarVentasProductosClientesTipoButtonActionPerformed() {
		try {
			Integer idProducto = Integer.parseInt(textIdProductoMostrarVPC.getText());

			if (idProducto < 0)
				throw new Exception();

			String tipoCliente = (String) comboClienteMostrarVPC.getSelectedItem();

			DatosQuery datos = new DatosQuery(idProducto, tipoCliente);
			Contexto contexto = new Contexto(Evento.mostrarVentasConProductosAClientesTipo, datos);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
			textIdProductoMostrarVPC.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarMostrarVentasProductosClientesTipoButtonActionPerformed() {
		textIdProductoMostrarVPC.setText("");
		CardLayout cl = (CardLayout) (panelContenidoVentas.getLayout());
		cl.first(panelContenidoVentas);
	}

	@Override
	/**
	 * Muestra los mensajes de confirmacion o error de cada funcion de clasificacion
	 */
	public void actualizar(Contexto contexto) {
		switch (contexto.getEvento()) {

		case abrirVenta:
			CardLayout cl = (CardLayout) (panelContenidoVentas.getLayout());
			cl.show(panelContenidoVentas, "Abrir");
			break;

		case abrirDevolverProducto:
			CardLayout cl1 = (CardLayout) (panelContenidoVentas.getLayout());
			cl1.show(panelContenidoVentas, "Devolver");
			break;

		case abrirMostrarUnaVenta:
			CardLayout cl2 = (CardLayout) (panelContenidoVentas.getLayout());
			cl2.show(panelContenidoVentas, "Mostrar");
			break;

		case abrirMostrarVentasConProductosAClientesTipo:
			CardLayout cl3 = (CardLayout) (panelContenidoVentas.getLayout());
			cl3.show(panelContenidoVentas, "MostrarVentasProductosClientesTipo");
			break;

		case cerrarVenta:
			textAreaVenta.append("->Venta realizada correctamente con id "
					+ ((TransferVenta) contexto.getDatos()).getIdVenta() + "\n\n");
			rellenarTablaConUnaVenta((TransferVenta) contexto.getDatos());
			rellenarTablaLineasVenta(((TransferVenta) contexto.getDatos()).getProductos());
			CardLayout cl5 = (CardLayout) (panelContenidoVentas.getLayout());
			cl5.show(panelContenidoVentas, "LineasVenta");
			((TransferVenta) contexto.getDatos()).getProductos().clear();
			break;

		case devolverProducto:
			textAreaVenta.append("->Devolucion realizada correctamente.\n\n");
			setVisible(true);
			break;

		case errorArgumentos:
			textAreaVenta.append("->Error al introducir argumentos\n\n");
			break;

		case errorConexionBBDD:
			textAreaVenta.append("->Error en la conexion a la Base de Datos\n\n");
			break;

		case errorDevolverProductoVentaInexistente:
			textAreaVenta.append("->La venta indicada no existe\n\n");
			break;

		case errorDevolverProducto:
			textAreaVenta.append("->El producto no existe o se encuentra inactivo\n\n");
			break;

		case errorDevolverProductoInexistente:
			textAreaVenta.append("->La venta indicada no contiene ninguna unidad del producto con id " + ((int) contexto.getDatos()) + "\n\n");
			break;

		case errorDevolverProductoCantidadExcesiva:
			textAreaVenta.append("->La venta contiene menos " + "de " + ((int) contexto.getDatos())
					+ " unidades del producto, se " + "han devuelto todas las unidades del producto en la venta.\n\n");
			break;

		case errorDevolverProductoClienteInactivo:
			textAreaVenta.append(
					"->El cliente que realizó la compra está inactivo, no se ha podido realizar la devolución.\n\n");
			break;

		case errorDevolverProductoCantidadExcesivaYReactivacion:
			textAreaVenta.append("->La venta contiene menos de " + ((int) contexto.getDatos())
					+ " unidades del producto y el producto estaba inactivo, se han devuelto "
					+ "todas las unidades del producto en la venta y se ha reactivado el producto.\n\n");
			break;

		case errorDevolverProductoReactivacionProducto:
			textAreaVenta.append("->La devolucion ha sido realizada correctamente y se ha reactivado el producto.\n\n");
			break;

		case errorCerrarVentaCliente:
			textAreaVenta.append("->Error al cerrar la venta: no existe en el sistema un cliente con" + " id "
					+ ((TransferVenta) contexto.getDatos()).getIdCliente() + ".\n\n");
			((TransferVenta) contexto.getDatos()).getProductos().clear();
			break;

		case errorCerrarVenta:
			textAreaVenta.append("->La venta se ha creado con id " + ((TransferVenta) contexto.getDatos()).getIdVenta()
					+ " pero algunos productos no se han podido añadir debido a falta "
					+ "de stock o a que no existen en el sistema. \n\n");
			rellenarTablaConUnaVenta((TransferVenta) contexto.getDatos());
			rellenarTablaLineasVenta(((TransferVenta) contexto.getDatos()).getProductos());
			CardLayout cl7 = (CardLayout) (panelContenidoVentas.getLayout());
			cl7.show(panelContenidoVentas, "LineasVenta");
			((TransferVenta) contexto.getDatos()).getProductos().clear();
			break;

		case errorCerrarVentaCarrito:
			textAreaVenta.append("->La venta no ha podido cerrarse debido a que ningun producto insertado "
					+ "en el carrito existe en el sistema o no hay stock de ningun producto.\n\n");
			((TransferVenta) contexto.getDatos()).getProductos().clear();
			break;

		case errorMostrarTodasVentas:
			vaciarTablaVentas();
			textAreaVenta.append("->No existe ninguna venta en el sistema\n\n");
			break;

		case errorMostrarVentasConProductosAClientesTipo:
			textAreaVenta.append(
					"->No existen ventas a clientes de tipo " + ((DatosQuery) contexto.getDatos()).getTipoCliente()
							+ " del producto con id " + ((DatosQuery) contexto.getDatos()).getIdProducto() + "\n\n");
			vaciarTablaVentas();
			break;

		case errorMostrarVentasConProductosAClientesTipoProductoNoExistente:
			
			textAreaVenta.append("->El producto con id " + ((DatosQuery) contexto.getDatos()).getIdProducto()
					+ " no existe en el sistema.\n\n");
			vaciarTablaVentas();
			break;

		case errorMostrarUnaVenta:
			textAreaVenta.append("->No existe ninguna venta con id "
					+ ((TransferVenta) contexto.getDatos()).getIdVenta() + " en el sistema\n\n");
			vaciarTablaVentas();
			break;

		case mostrarCarrito:
			rellenarCarrito((HashMap<Integer, LineaVenta>) contexto.getDatos());
			CardLayout cl4 = (CardLayout) (panelContenidoVentas.getLayout());
			cl4.show(panelContenidoVentas, "Carrito");
			break;

		case mostrarCarritoVacio:
			textAreaVenta.append("->El carrito no contiene ningun producto\n\n");
			break;

		case mostrarVentasConProductosAClientesTipo:
		case mostrarTodasVentas:
			rellenarTablaConVentas((List<TransferVenta>) contexto.getDatos());
			break;

		case mostrarUnaVenta:
			rellenarTablaConUnaVenta((TransferVenta) contexto.getDatos());
			rellenarTablaLineasVenta(((TransferVenta) contexto.getDatos()).getProductos());
			CardLayout cl6 = (CardLayout) (panelContenidoVentas.getLayout());
			cl6.show(panelContenidoVentas, "LineasVenta");
			break;

		default:
			break;
		}
	}

	// ---------------------------------

	private void rellenarTablaConUnaVenta(TransferVenta venta) {
		datosTabla = new Object[1][4];

		datosTabla[0][0] = venta.getIdVenta();
		datosTabla[0][1] = venta.getIdCliente();
		datosTabla[0][2] = venta.getPrecio();
		datosTabla[0][3] = venta.getFecha();

		setTablaVentasConfiguration();
	}

	private void rellenarCarrito(HashMap<Integer, LineaVenta> productos) {
		datosTabla = new Object[productos.size()][2];
		int i = 0;
		for (LineaVenta producto : productos.values()) {
			datosTabla[i][0] = producto.getId();
			datosTabla[i][1] = producto.getCantidad();
			++i;
		}

		tablaCarrito.setModel(new DefaultTableModel(datosTabla, new String[] { "ID Producto", "Cantidad" }) {

			private static final long serialVersionUID = -1254170937977362022L;

			Class[] types = new Class[] { Integer.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaCarrito.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tablaCarrito.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	}

	private void rellenarTablaLineasVenta(HashMap<Integer, LineaVenta> productos) {
		datosTabla = new Object[productos.size()][3];
		int i = 0;
		for (LineaVenta producto : productos.values()) {
			datosTabla[i][0] = producto.getId();
			datosTabla[i][1] = producto.getCantidad();
			datosTabla[i][2] = producto.getPrecio();
			++i;
		}

		lineasVentaTabla
				.setModel(new DefaultTableModel(datosTabla, new String[] { "ID Producto", "Cantidad", "Precio Unitario" }) {

					private static final long serialVersionUID = 7482486286317006320L;

					Class[] types = new Class[] { Integer.class, Integer.class, Double.class };

					public Class getColumnClass(int columnIndex) {
						return types[columnIndex];
					}

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return false;
					}
				});

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		lineasVentaTabla.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		lineasVentaTabla.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

		DefaultTableCellRenderer doubleRenderer = new DefaultTableCellRenderer() {
			private final DecimalFormat formatter = new DecimalFormat("0.00");

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				// First format the cell value as required
				if (value instanceof Double) {
					value = formatter.format((Number) value);
				}

				// And pass it on to parent class

				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		};

		doubleRenderer.setHorizontalAlignment(JLabel.CENTER);
		lineasVentaTabla.getColumnModel().getColumn(2).setCellRenderer(doubleRenderer);

	}

	private void rellenarTablaConVentas(List<TransferVenta> ventas) {
		datosTabla = new Object[ventas.size()][4];
		int i = 0;
		for (TransferVenta venta : ventas) {
			datosTabla[i][0] = venta.getIdVenta();
			datosTabla[i][1] = venta.getIdCliente();
			datosTabla[i][2] = venta.getPrecio();
			datosTabla[i][3] = venta.getFecha();
			++i;
		}

		setTablaVentasConfiguration();
	}

	public void setTablaVentasConfiguration() {
		tablaVenta.setModel(new DefaultTableModel(datosTabla, new String[] { "Id", "Cliente", "Precio", "Fecha" }) {

			private static final long serialVersionUID = -4826319740640786334L;

			Class[] types = new Class[] { Integer.class, Integer.class, Double.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaVenta.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tablaVenta.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		DefaultTableCellRenderer doubleRenderer = new DefaultTableCellRenderer() {

			private final DecimalFormat formatter = new DecimalFormat("0.00");

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				// First format the cell value as required
				if (value instanceof Double) {
					value = formatter.format((Number) value);
				}

				// And pass it on to parent class

				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		};

		doubleRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaVenta.getColumnModel().getColumn(2).setCellRenderer(doubleRenderer);
		tablaVenta.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	}

	private void vaciarTablaVentas() {
		DefaultTableModel model = (DefaultTableModel) tablaVenta.getModel();
		model.setRowCount(0);
	}

	/**
	 * Creates new form GUIVentaPrueba
	 */
	public GUIMenuVenta() {
		initGUI();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings({ "unchecked", "serial" })
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initGUI() {
		GridBagConstraints gridBagConstraints;

		jScrollPane1 = new JScrollPane();
		tablaVenta = new JTable();
		jScrollPane2 = new JScrollPane();
		textAreaVenta = new JTextArea();
		panelBotonesVenta = new JPanel();
		botonAbrirVenta = new JButton();
		botonDevolverProducto = new JButton();
		botonMostrarUnaVenta = new JButton();
		botonMostrarTodasVentas = new JButton();
		botonMostrarVentasProductosAClientesTipo = new JButton();
		botonVolver = new JButton();
		panelContenidoVentas = new JPanel();

		abrirVentaPanel = new JPanel();
		abrirVentaPanel.setBorder(BorderFactory.createTitledBorder("Abrir nueva venta"));
		inicializarComponentesPanelAbrirVenta();

		devolverProductoPanel = new JPanel();
		devolverProductoPanel.setBorder(BorderFactory.createTitledBorder("Devolver un producto"));
		inicializarComponentesPanelDevolver();

		mostrarUnaPanel = new JPanel();
		mostrarUnaPanel.setBorder(BorderFactory.createTitledBorder("Mostrar una venta"));
		inicializarComponentesPanelMostrarUno();

		mostrarVentasProductosClientesTipoPanel = new JPanel();
		mostrarVentasProductosClientesTipoPanel
				.setBorder(BorderFactory.createTitledBorder("Mostrar ventas de productos a clientes de un tipo"));
		inicializarComponentesPanelMostrarVentasProductosClientesTipo();

		carritoPanel = new JPanel();
		carritoPanel.setBorder(BorderFactory.createTitledBorder("Carrito"));
		inicializarComponentesPanelCarrito();

		lineasVentaPanel = new JPanel();
		lineasVentaPanel.setBorder(BorderFactory.createTitledBorder("Lineas de venta"));
		inicializarComponentesPanelLineasVenta();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMaximumSize(new Dimension(868, 480));
		setMinimumSize(new Dimension(868, 480));
		setResizable(false);

		tablaVenta.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Cliente", "Precio", "Fecha" }) {
					@SuppressWarnings("rawtypes")
					Class[] types = new Class[] { Integer.class, Integer.class, Double.class, String.class };
					boolean[] canEdit = new boolean[] { false, false, false, false };

					@SuppressWarnings("rawtypes")
					public Class getColumnClass(int columnIndex) {
						return types[columnIndex];
					}

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return canEdit[columnIndex];
					}
				});
		tablaVenta.setMaximumSize(new Dimension(300, 64));
		tablaVenta.setMinimumSize(new Dimension(300, 64));
		tablaVenta.setOpaque(false);
		tablaVenta.getTableHeader().setReorderingAllowed(false);
		jScrollPane1.setViewportView(tablaVenta);
		if (tablaVenta.getColumnModel().getColumnCount() > 0) {
			tablaVenta.getColumnModel().getColumn(0).setResizable(false);
			tablaVenta.getColumnModel().getColumn(1).setResizable(false);
			tablaVenta.getColumnModel().getColumn(2).setResizable(false);
			tablaVenta.getColumnModel().getColumn(3).setResizable(false);
		}

		textAreaVenta.setColumns(20);
		textAreaVenta.setRows(5);
		textAreaVenta.setMaximumSize(new Dimension(164, 94));
		textAreaVenta.setMinimumSize(new Dimension(164, 94));
		jScrollPane2.setViewportView(textAreaVenta);

		panelBotonesVenta.setMaximumSize(new Dimension(411, 242));
		panelBotonesVenta.setMinimumSize(new Dimension(411, 242));
		panelBotonesVenta.setOpaque(false);
		panelBotonesVenta.setPreferredSize(new Dimension(411, 242));
		panelBotonesVenta.setLayout(new GridBagLayout());

		botonAbrirVenta.setText("Abrir");
		botonAbrirVenta.setPreferredSize(new Dimension(150, 45));
		botonAbrirVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				abrirVentaButtonActionPerformed();
			}
		});
		panelBotonesVenta.add(botonAbrirVenta, new GridBagConstraints());

		botonDevolverProducto.setText("Devolver");
		botonDevolverProducto.setPreferredSize(new Dimension(150, 45));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		botonDevolverProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				devolverButtonActionPerformed();
			}
		});
		panelBotonesVenta.add(botonDevolverProducto, gridBagConstraints);

		botonMostrarUnaVenta.setText("Mostrar Una");
		botonMostrarUnaVenta.setPreferredSize(new Dimension(150, 45));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		botonMostrarUnaVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mostrarUnoButtonActionPerformed();
			}
		});
		panelBotonesVenta.add(botonMostrarUnaVenta, gridBagConstraints);

		botonMostrarTodasVentas.setText("Mostrar Todas");
		botonMostrarTodasVentas.setPreferredSize(new Dimension(150, 45));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		botonMostrarTodasVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mostrarTodosButtonActionPerformed();
			}
		});
		panelBotonesVenta.add(botonMostrarTodasVentas, gridBagConstraints);

		botonMostrarVentasProductosAClientesTipo.setText("Mostrar ventas con productos a cliente tipo");
		botonMostrarVentasProductosAClientesTipo.setToolTipText("Mostrar Ventas con Productos a Clientes Tipo");
		botonMostrarVentasProductosAClientesTipo.setPreferredSize(new Dimension(300, 45));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		botonMostrarVentasProductosAClientesTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mostrarVentasConProductosClienteTipoButtonActionPerformed();
			}
		});
		panelBotonesVenta.add(botonMostrarVentasProductosAClientesTipo, gridBagConstraints);

		botonVolver.setText("Volver");
		botonVolver.setPreferredSize(new Dimension(150, 45));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuPrincipalButtonActionPerformed();
			}
		});
		panelBotonesVenta.add(botonVolver, gridBagConstraints);

		panelContenidoVentas.setMaximumSize(new Dimension(411, 242));
		panelContenidoVentas.setMinimumSize(new Dimension(411, 242));
		panelContenidoVentas.setPreferredSize(new Dimension(411, 242));
		panelContenidoVentas.setLayout(new CardLayout());

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addComponent(jScrollPane1).addGap(18, 18, 18)
										.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 279,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(panelBotonesVenta, GroupLayout.PREFERRED_SIZE, 415,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
										.addComponent(panelContenidoVentas, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(panelBotonesVenta, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE))
								.addComponent(panelContenidoVentas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap()));

		textAreaVenta.setLineWrap(true);
		textAreaVenta.setWrapStyleWord(true);
		panelContenidoVentas.add(new JPanel());
		panelContenidoVentas.add(abrirVentaPanel, "Abrir");
		panelContenidoVentas.add(devolverProductoPanel, "Devolver");
		panelContenidoVentas.add(mostrarUnaPanel, "Mostrar");
		panelContenidoVentas.add(mostrarVentasProductosClientesTipoPanel, "MostrarVentasProductosClientesTipo");
		panelContenidoVentas.add(carritoPanel, "Carrito");
		panelContenidoVentas.add(lineasVentaPanel, "LineasVenta");
		pack();
	}// </editor-fold>

	private void inicializarComponentesPanelAbrirVenta() {
		GridBagConstraints gridBagConstraints;

		labelIdCliente = new JLabel();
		textIdProducto = new JTextField();
		labelIdProducto = new JLabel();
		textCantidad = new JTextField();
		labelCantidad = new JLabel();
		textIdCliente = new JTextField();
		botonAnadir = new JButton();
		botonEliminar = new JButton();
		botonMostrar = new JButton();
		botonCerrarVenta = new JButton();
		botonCancelar = new JButton();

		abrirVentaPanel.setMaximumSize(new Dimension(411, 242));
		abrirVentaPanel.setMinimumSize(new Dimension(411, 242));
		abrirVentaPanel.setPreferredSize(new Dimension(411, 242));
		abrirVentaPanel.setLayout(new GridBagLayout());

		labelIdCliente.setText("ID Cliente");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(16, 74, 0, 0);
		abrirVentaPanel.add(labelIdCliente, gridBagConstraints);

		textIdProducto.setMinimumSize(new Dimension(50, 20));
		textIdProducto.setPreferredSize(new Dimension(50, 20));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(16, 1, 0, 59);
		abrirVentaPanel.add(textIdProducto, gridBagConstraints);

		labelIdProducto.setText("ID Producto");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(16, 74, 0, 0);
		abrirVentaPanel.add(labelIdProducto, gridBagConstraints);

		textCantidad.setMinimumSize(new Dimension(50, 20));
		textCantidad.setPreferredSize(new Dimension(50, 20));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(16, 1, 0, 59);
		abrirVentaPanel.add(textCantidad, gridBagConstraints);

		labelCantidad.setText("Cantidad");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(16, 74, 0, 0);
		abrirVentaPanel.add(labelCantidad, gridBagConstraints);

		textIdCliente.setMinimumSize(new Dimension(50, 20));
		textIdCliente.setPreferredSize(new Dimension(50, 20));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(16, 1, 0, 59);
		abrirVentaPanel.add(textIdCliente, gridBagConstraints);

		botonAnadir.setText("Anadir Producto");
		botonAnadir.setMaximumSize(new Dimension(135, 35));
		botonAnadir.setMinimumSize(new Dimension(135, 35));
		botonAnadir.setPreferredSize(new Dimension(135, 35));
		botonAnadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				anadirProductoButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		abrirVentaPanel.add(botonAnadir, gridBagConstraints);

		botonEliminar.setText("Eliminar Producto");
		botonEliminar.setMaximumSize(new Dimension(135, 35));
		botonEliminar.setMinimumSize(new Dimension(135, 35));
		botonEliminar.setPreferredSize(new Dimension(135, 35));
		botonEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eliminarProductoButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		abrirVentaPanel.add(botonEliminar, gridBagConstraints);

		botonMostrar.setText("Mostrar Carrito");
		botonMostrar.setMaximumSize(new Dimension(135, 35));
		botonMostrar.setMinimumSize(new Dimension(135, 35));
		botonMostrar.setPreferredSize(new Dimension(135, 35));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarCarritoButtonActionPerformed();
			}
		});
		abrirVentaPanel.add(botonMostrar, gridBagConstraints);

		botonCerrarVenta.setText("Cerrar Venta");
		botonCerrarVenta.setMaximumSize(new Dimension(135, 35));
		botonCerrarVenta.setMinimumSize(new Dimension(135, 35));
		botonCerrarVenta.setPreferredSize(new Dimension(135, 35));
		botonCerrarVenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cerrarVentaButtonActionPerfomed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		abrirVentaPanel.add(botonCerrarVenta, gridBagConstraints);

		botonCancelar.setText("Cancelar");
		botonCancelar.setMaximumSize(new Dimension(135, 35));
		botonCancelar.setMinimumSize(new Dimension(135, 35));
		botonCancelar.setPreferredSize(new Dimension(135, 35));
		botonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarAbrirVentaButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		abrirVentaPanel.add(botonCancelar, gridBagConstraints);
	}

	private void inicializarComponentesPanelDevolver() {
		GridBagConstraints gridBagConstraints;

		labelIdVentaDevolver = new JLabel();
		textIdVentaDevolver = new JTextField();
		labelIdClienteDevolver = new JLabel();
		textIdProductoDevolver = new JTextField();
		labelCantidadDevolver = new JLabel();
		textCantidadDevolver = new JTextField();
		botonCancelarDevolver = new JButton();
		botonAceptarDevolver = new JButton();

		devolverProductoPanel.setMaximumSize(new Dimension(411, 242));
		devolverProductoPanel.setMinimumSize(new Dimension(411, 242));
		devolverProductoPanel.setPreferredSize(new Dimension(411, 242));
		devolverProductoPanel.setLayout(new GridBagLayout());

		labelIdVentaDevolver.setText("ID Venta");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 17;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(0, 128, 0, 0);
		devolverProductoPanel.add(labelIdVentaDevolver, gridBagConstraints);

		textIdVentaDevolver.setPreferredSize(new Dimension(50, 20));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 13;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(0, 6, 0, 91);
		devolverProductoPanel.add(textIdVentaDevolver, gridBagConstraints);

		labelIdClienteDevolver.setText("ID Producto");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.ipadx = 17;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(0, 128, 0, 0);
		devolverProductoPanel.add(labelIdClienteDevolver, gridBagConstraints);

		textIdProductoDevolver.setPreferredSize(new Dimension(50, 20));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.ipadx = 13;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(0, 10, 0, 95);
		devolverProductoPanel.add(textIdProductoDevolver, gridBagConstraints);

		labelCantidadDevolver.setText("Cantidad");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 17;
		gridBagConstraints.insets = new Insets(0, 130, 0, 0);
		devolverProductoPanel.add(labelCantidadDevolver, gridBagConstraints);

		textCantidadDevolver.setPreferredSize(new Dimension(50, 20));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 13;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(0, 10, 0, 95);
		devolverProductoPanel.add(textCantidadDevolver, gridBagConstraints);

		botonCancelarDevolver.setText("Cancelar");
		botonCancelarDevolver.setPreferredSize(new Dimension(150, 45));
		botonCancelarDevolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarDevolverVentaButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.ipadx = 8;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(0, 10, 0, 10);
		devolverProductoPanel.add(botonCancelarDevolver, gridBagConstraints);

		botonAceptarDevolver.setText("Aceptar");
		botonAceptarDevolver.setPreferredSize(new Dimension(150, 45));
		botonAceptarDevolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aceptarDevolverVentaButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.ipadx = 13;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(0, 10, 0, 10);
		devolverProductoPanel.add(botonAceptarDevolver, gridBagConstraints);

	}

	private void inicializarComponentesPanelMostrarUno() {
		GridBagConstraints gridBagConstraints;

		labelIdProductoMostrarUna = new JLabel();
		textIdProductoMostrarUna = new JTextField();
		botonAceptarMostrarUna = new JButton();
		botonCancelarMostrarUna = new JButton();

		mostrarUnaPanel.setMaximumSize(new Dimension(411, 242));
		mostrarUnaPanel.setMinimumSize(new Dimension(411, 242));
		mostrarUnaPanel.setPreferredSize(new Dimension(411, 242));
		mostrarUnaPanel.setLayout(new GridBagLayout());

		labelIdProductoMostrarUna.setText("ID Venta");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 85, 0, 0);
		mostrarUnaPanel.add(labelIdProductoMostrarUna, gridBagConstraints);

		textIdProductoMostrarUna.setMaximumSize(new Dimension(50, 20));
		textIdProductoMostrarUna.setMinimumSize(new Dimension(50, 20));
		textIdProductoMostrarUna.setPreferredSize(new Dimension(50, 20));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 0, 0, 80);
		mostrarUnaPanel.add(textIdProductoMostrarUna, gridBagConstraints);

		botonAceptarMostrarUna.setText("Aceptar");
		botonAceptarMostrarUna.setMaximumSize(new Dimension(150, 45));
		botonAceptarMostrarUna.setMinimumSize(new Dimension(150, 45));
		botonAceptarMostrarUna.setPreferredSize(new Dimension(150, 45));
		botonAceptarMostrarUna.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aceptarMostrarUnaButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(69, 0, 0, 42);
		mostrarUnaPanel.add(botonAceptarMostrarUna, gridBagConstraints);

		botonCancelarMostrarUna.setText("Cancelar");
		botonCancelarMostrarUna.setMaximumSize(new Dimension(150, 45));
		botonCancelarMostrarUna.setMinimumSize(new Dimension(150, 45));
		botonCancelarMostrarUna.setPreferredSize(new Dimension(150, 45));
		botonCancelarMostrarUna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelarMostrarUnaButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(69, 0, 0, 0);
		mostrarUnaPanel.add(botonCancelarMostrarUna, gridBagConstraints);
	}

	private void inicializarComponentesPanelMostrarVentasProductosClientesTipo() {
		GridBagConstraints gridBagConstraints;

		labelIdProductoMostrarVPC = new JLabel();
		textIdProductoMostrarVPC = new JTextField();
		labelClienteMostrarVPC = new JLabel();
		comboClienteMostrarVPC = new JComboBox<>();
		botonAceptarMostrarVPC = new JButton();
		botonCancelarMostrarVPC = new JButton();

		mostrarVentasProductosClientesTipoPanel.setMaximumSize(new Dimension(411, 242));
		mostrarVentasProductosClientesTipoPanel.setMinimumSize(new Dimension(411, 242));
		mostrarVentasProductosClientesTipoPanel.setPreferredSize(new Dimension(411, 242));
		mostrarVentasProductosClientesTipoPanel.setLayout(new GridBagLayout());

		labelIdProductoMostrarVPC.setText("ID Producto");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 85, 0, 0);
		mostrarVentasProductosClientesTipoPanel.add(labelIdProductoMostrarVPC, gridBagConstraints);

		textIdProductoMostrarVPC.setMaximumSize(new Dimension(50, 20));
		textIdProductoMostrarVPC.setMinimumSize(new Dimension(50, 20));
		textIdProductoMostrarVPC.setPreferredSize(new Dimension(50, 20));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 0, 0, 80);
		mostrarVentasProductosClientesTipoPanel.add(textIdProductoMostrarVPC, gridBagConstraints);

		labelClienteMostrarVPC.setText("Tipo de Cliente");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(8, 71, 0, 0);
		mostrarVentasProductosClientesTipoPanel.add(labelClienteMostrarVPC, gridBagConstraints);

		comboClienteMostrarVPC.setModel(new DefaultComboBoxModel<>(new String[] { "Estandar", "VIP" }));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(8, 0, 0, 75);
		mostrarVentasProductosClientesTipoPanel.add(comboClienteMostrarVPC, gridBagConstraints);

		botonAceptarMostrarVPC.setText("Aceptar");
		botonAceptarMostrarVPC.setMaximumSize(new Dimension(150, 45));
		botonAceptarMostrarVPC.setMinimumSize(new Dimension(150, 45));
		botonAceptarMostrarVPC.setPreferredSize(new Dimension(150, 45));
		botonAceptarMostrarVPC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aceptarMostrarVentasProductosClientesTipoButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(69, 0, 0, 42);
		mostrarVentasProductosClientesTipoPanel.add(botonAceptarMostrarVPC, gridBagConstraints);

		botonCancelarMostrarVPC.setText("Cancelar");
		botonCancelarMostrarVPC.setMaximumSize(new Dimension(150, 45));
		botonCancelarMostrarVPC.setMinimumSize(new Dimension(150, 45));
		botonCancelarMostrarVPC.setPreferredSize(new Dimension(150, 45));
		botonCancelarMostrarVPC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelarMostrarVentasProductosClientesTipoButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(69, 0, 0, 0);
		mostrarVentasProductosClientesTipoPanel.add(botonCancelarMostrarVPC, gridBagConstraints);
	}

	private void inicializarComponentesPanelCarrito() {
		GridBagConstraints gridBagConstraints;

		jScrollPane1Carrito = new JScrollPane();
		tablaCarrito = new JTable();
		volverCarrito = new JButton();

		carritoPanel.setMaximumSize(new Dimension(411, 242));
		carritoPanel.setMinimumSize(new Dimension(411, 242));
		carritoPanel.setLayout(new GridBagLayout());

		tablaCarrito.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID Producto", "Cantidad" }) {

			private static final long serialVersionUID = -4345004164632422941L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});
		tablaCarrito.getTableHeader().setReorderingAllowed(false);
		jScrollPane1Carrito.setViewportView(tablaCarrito);
		if (tablaCarrito.getColumnModel().getColumnCount() > 0) {
			tablaCarrito.getColumnModel().getColumn(0).setResizable(false);
			tablaCarrito.getColumnModel().getColumn(1).setResizable(false);
		}

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 363;
		gridBagConstraints.ipady = 109;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 0.8;
		gridBagConstraints.insets = new Insets(14, 10, 0, 15);
		carritoPanel.add(jScrollPane1Carrito, gridBagConstraints);

		volverCarrito.setText("Volver");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(13, 0, 21, 0);
		volverCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				volverDeMostrarCarritoAAbrirVentaButtonActionPerformed();
			}
		});
		carritoPanel.add(volverCarrito, gridBagConstraints);
	}

	private void inicializarComponentesPanelLineasVenta() {
		GridBagConstraints gridBagConstraints;

		jScrollPane1LineasVenta = new JScrollPane();
		lineasVentaTabla = new JTable();
		cerrarLineasVentaButton = new JButton();

		lineasVentaPanel.setMaximumSize(new Dimension(411, 242));
		lineasVentaPanel.setMinimumSize(new Dimension(411, 242));
		lineasVentaPanel.setLayout(new GridBagLayout());

		lineasVentaTabla.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID Producto", "Cantidad", "Precio" }) {

					private static final long serialVersionUID = -5191374889542656332L;

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return false;
					}
				});
		lineasVentaTabla.getTableHeader().setReorderingAllowed(false);
		jScrollPane1LineasVenta.setViewportView(lineasVentaTabla);
		if (lineasVentaTabla.getColumnModel().getColumnCount() > 0) {
			lineasVentaTabla.getColumnModel().getColumn(0).setResizable(false);
			lineasVentaTabla.getColumnModel().getColumn(1).setResizable(false);
			lineasVentaTabla.getColumnModel().getColumn(2).setResizable(false);
		}

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 363;
		gridBagConstraints.ipady = 109;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 0.8;
		gridBagConstraints.insets = new Insets(14, 10, 0, 15);
		lineasVentaPanel.add(jScrollPane1LineasVenta, gridBagConstraints);

		cerrarLineasVentaButton.setText("Cerrar");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(13, 0, 21, 0);
		cerrarLineasVentaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				volverDeMostrarCarritoAMostrarUnaButtonActionPerformed();
			}
		});
		lineasVentaPanel.add(cerrarLineasVentaButton, gridBagConstraints);
	}

	// Variables declaration - do not modify
	private JButton botonAbrirVenta;
	private JButton botonDevolverProducto;
	private JButton botonMostrarTodasVentas;
	private JButton botonMostrarUnaVenta;
	private JButton botonMostrarVentasProductosAClientesTipo;
	private JButton botonVolver;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JPanel panelBotonesVenta;
	private JPanel panelContenidoVentas;
	private JTable tablaVenta;
	private JTextArea textAreaVenta;

	// Abrir Venta
	private JPanel abrirVentaPanel;
	private JButton botonAnadir;
	private JButton botonCancelar;
	private JButton botonCerrarVenta;
	private JButton botonEliminar;
	private JButton botonMostrar;
	private JLabel labelCantidad;
	private JLabel labelIdCliente;
	private JLabel labelIdProducto;
	private JTextField textCantidad;
	private JTextField textIdCliente;
	private JTextField textIdProducto;

	// Devolver Producto
	private JPanel devolverProductoPanel;
	private JButton botonAceptarDevolver;
	private JButton botonCancelarDevolver;
	private JLabel labelCantidadDevolver;
	private JLabel labelIdClienteDevolver;
	private JLabel labelIdVentaDevolver;
	private JTextField textCantidadDevolver;
	private JTextField textIdProductoDevolver;
	private JTextField textIdVentaDevolver;

	// Mostrar una Venta
	private JPanel mostrarUnaPanel;
	private JButton botonAceptarMostrarUna;
	private JButton botonCancelarMostrarUna;
	private JLabel labelIdProductoMostrarUna;
	private JTextField textIdProductoMostrarUna;

	// Mostrar Ventas con Productos A Cliente Tipo
	private JPanel mostrarVentasProductosClientesTipoPanel;
	private JButton botonAceptarMostrarVPC;
	private JButton botonCancelarMostrarVPC;
	private JComboBox<String> comboClienteMostrarVPC;
	private JLabel labelClienteMostrarVPC;
	private JLabel labelIdProductoMostrarVPC;
	private JTextField textIdProductoMostrarVPC;

	// Carrito
	private JPanel carritoPanel;
	private JScrollPane jScrollPane1Carrito;
	private JTable tablaCarrito;
	private JButton volverCarrito;

	// LineasVenta
	private JPanel lineasVentaPanel;
	private JScrollPane jScrollPane1LineasVenta;
	private JTable lineasVentaTabla;
	private JButton cerrarLineasVentaButton;

	private Object[][] datosTabla;
	private TransferVenta venta;
	// End of variables declaration

}
