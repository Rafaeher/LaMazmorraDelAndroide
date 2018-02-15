package presentacion.producto;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
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

import negocio.producto.TransferProducto;

import presentacion.GUI;
import presentacion.controlador.Contexto;
import presentacion.controlador.ControladorAplicacion;
import presentacion.eventos.Evento;

public class GUIMenuProducto extends JFrame implements GUI {

	// Action Performed Menu Botones (Redirección entre vistas)
	private void actualizarButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirActualizarProducto, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void altaButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirAltaProducto, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void bajaButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirBajaProducto, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void mostrarUnoButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirMostrarUnProducto, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void mostrarTodosButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.mostrarTodosProductos, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void mostrarEnVentasButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirMostrarProductosEnVentasSuperioresAUnPrecio, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void menuPrincipalButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirMenuPrincipal, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
		this.dispose();
	}

	// Action Performed Vista Alta Producto

	private void altaProductoButtonActionPerformed() {
		try {
			double precio = Double.parseDouble(precioAltaTextField.getText());
			int stock = Integer.parseInt(stockAltaTextField.getText());
			int so = Integer.parseInt(sistemaOperativoAltaTextField.getText());

			if (precio < 0 || stock < 0 || so < 0)
				throw new Exception();

			String name = nombreAltaTextField.getText();

			TransferProducto tPro = new TransferProducto(precio, stock, so, name);
			Contexto contexto = new Contexto(Evento.altaProducto, tPro);
			ControladorAplicacion.obtenerInstancia().accion(contexto);

			precioAltaTextField.setText("");
			sistemaOperativoAltaTextField.setText("");
			stockAltaTextField.setText("");
			nombreAltaTextField.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarAltaProductoButtonActionPerformed() {
		precioAltaTextField.setText("");
		sistemaOperativoAltaTextField.setText("");
		stockAltaTextField.setText("");
		nombreAltaTextField.setText("");
		CardLayout cl = (CardLayout) (insercionDatosPanel.getLayout());
		cl.first(insercionDatosPanel);
	}

	// Action Performed Vista Baja Producto

	private void bajaProductoButtonActionPerformed() {
		try {
			int id = Integer.parseInt(idBajaTextField.getText());

			if (id < 0) {
				throw new Exception();
			}
			TransferProducto tPro = new TransferProducto(id);
			Contexto contexto = new Contexto(Evento.bajaProducto, tPro);
			ControladorAplicacion.obtenerInstancia().accion(contexto);

			idBajaTextField.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarBajaProductoButtonActionPerformed() {
		idBajaTextField.setText("");

		CardLayout cl = (CardLayout) (insercionDatosPanel.getLayout());
		cl.first(insercionDatosPanel);
	}

	// Action Performed Vista Mostrar un producto

	private void mostrarUnProductoButtonActionPerformed() {
		try {
			int id = Integer.parseInt(idMostrarUnoTextField.getText());

			if (id < 0) {
				throw new Exception();
			}
			TransferProducto tPro = new TransferProducto(id);
			Contexto contexto = new Contexto(Evento.mostrarUnProducto, tPro);
			ControladorAplicacion.obtenerInstancia().accion(contexto);

			idMostrarUnoTextField.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarMostrarUnProductoButtonActionPerformed() {
		idMostrarUnoTextField.setText("");

		CardLayout cl = (CardLayout) (insercionDatosPanel.getLayout());
		cl.first(insercionDatosPanel);
	}

	// Action Performed Vista Mostrar productos en ventas

	private void mostrarProductosEnVentasButtonActionPerformed() {
		try {
			double cantidad = Double.valueOf(precioMostrarProductosEnVentasTextField.getText());
			Contexto contexto = new Contexto(Evento.mostrarProductosEnVentasSuperioresAUnPrecio, cantidad);
			ControladorAplicacion.obtenerInstancia().accion(contexto);

			precioMostrarProductosEnVentasTextField.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarmostrarProductosEnVentasProductoButtonActionPerformed() {
		precioMostrarProductosEnVentasTextField.setText("");

		CardLayout cl = (CardLayout) (insercionDatosPanel.getLayout());
		cl.first(insercionDatosPanel);
	}

	// Action Performed Vista Actualizar Producto

	private void actualizarProductoButtonActionPerformed() {
		try {
			double precio = Double.parseDouble(precioModificacionTextField.getText());
			int stock = Integer.parseInt(stockModificacionTextField.getText());
			int so = Integer.parseInt(sistemaOperativoModificacionTextField.getText());

			if (precio < 0 || stock < 0 || so < 0)
				throw new Exception();

			String name = nombreModificacionTextField.getText();
			int id = Integer.parseInt(idModificacionTextField.getText());

			if (id < 0)
				throw new Exception();

			TransferProducto tPro = new TransferProducto(id, precio, stock, so, name);
			Contexto contexto = new Contexto(Evento.actualizarProducto, tPro);
			ControladorAplicacion.obtenerInstancia().accion(contexto);

			sistemaOperativoModificacionTextField.setText("");
			precioModificacionTextField.setText("");
			stockModificacionTextField.setText("");
			nombreModificacionTextField.setText("");
			idModificacionTextField.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarActualizarProductoButtonActionPerformed() {
		sistemaOperativoModificacionTextField.setText("");
		precioModificacionTextField.setText("");
		stockModificacionTextField.setText("");
		nombreModificacionTextField.setText("");
		idModificacionTextField.setText("");

		CardLayout cl = (CardLayout) (insercionDatosPanel.getLayout());
		cl.first(insercionDatosPanel);
	}

	@Override
	public void actualizar(Contexto contexto) {
		switch (contexto.getEvento()) {

		case abrirAltaProducto:
			CardLayout cl = (CardLayout) (insercionDatosPanel.getLayout());
			cl.show(insercionDatosPanel, "Alta");
			break;

		case abrirActualizarProducto:
			CardLayout cl2 = (CardLayout) (insercionDatosPanel.getLayout());
			cl2.show(insercionDatosPanel, "Modificar");
			break;

		case abrirBajaProducto:
			CardLayout cl1 = (CardLayout) (insercionDatosPanel.getLayout());
			cl1.show(insercionDatosPanel, "Baja");
			break;

		case abrirMostrarUnProducto:
			CardLayout cl4 = (CardLayout) (insercionDatosPanel.getLayout());
			cl4.show(insercionDatosPanel, "Mostrar");
			break;

		case abrirMostrarProductosEnVentasSuperioresAUnPrecio:
			CardLayout cl3 = (CardLayout) (insercionDatosPanel.getLayout());
			cl3.show(insercionDatosPanel, "MostrarProductosEnVentas");
			break;

		case actualizarProducto:
			this.resultadosTextArea.append("->Actualizacion correcta del producto con id "
					+ ((TransferProducto) contexto.getDatos()).getId() + "\n\n");
			break;

		case altaProducto:
			this.resultadosTextArea.append(
					"->Alta correcta del producto con id " + ((TransferProducto) contexto.getDatos()).getId() + "\n\n");
			break;

		case bajaProducto:
			this.resultadosTextArea.append("->El producto con id " + ((TransferProducto) contexto.getDatos()).getId()
					+ " ha sido dado de baja\n\n");
			break;

		case errorActualizarProducto:
			this.resultadosTextArea.append("->Id del producto inexistente\n\n");
			break;

		case errorActualizarProductoInactivo:
			this.resultadosTextArea
					.append("->El producto estaba inactivo, se ha activado y se han actualizado sus datos.\n\n");
			break;

		case errorActualizarProductoConMismoNombre:
			this.resultadosTextArea
					.append("->Ya existe un producto con nombre " + ((TransferProducto) contexto.getDatos()).getName()
							+ " en el sistema, no" + " se ha podido actualizar el producto.\n\n");
			break;

		case errorAltaProducto:
			this.resultadosTextArea.append("->Error en el proceso de dar de alta a un producto\n\n");
			break;

		case errorAltaProductoDuplicadoActivo:
			this.resultadosTextArea
					.append("->No se ha podido dar de alta el producto, ya existe un producto con nombre "
							+ ((TransferProducto) contexto.getDatos()).getName() + " en el sistema.\n\n");
			break;

		case errorAltaProductoDuplicadoInactivo:
			this.resultadosTextArea.append(
					"->Este producto ya estaba en el sistema, se ha activado y se han actualizado sus datos.\n\n");
			break;

		case errorArgumentos:
			this.resultadosTextArea.append("->Error al introducir argumentos\n\n");
			break;

		case errorBajaProducto:
			this.resultadosTextArea.append("->Id del producto no existente\n\n");
			break;

		case errorBajaProductoDuplicado:
			this.resultadosTextArea.append("->El producto ya estaba dado de baja\n\n");
			break;

		case errorConexionBBDD:
			this.resultadosTextArea.append("->Error en la conexion a la Base de Datos\n\n");
			break;

		case errorMostrarTodosProductos:
			vaciarTablaProductos();
			this.resultadosTextArea.append("->No existen productos en el sistema\n\n");
			break;

		case errorMostrarProductosEnVentasSuperioresAUnPrecio:
			vaciarTablaProductos();
			this.resultadosTextArea.append("->No existe ningun producto en ventas superiores a "
					+ (double) contexto.getDatos() + " euros\n\n");
			break;

		case errorMostrarUnProducto:
			vaciarTablaProductos();
			this.resultadosTextArea.append("->Id del producto inexistente\n\n");
			break;

		case mostrarProductosEnVentasSuperioresAUnPrecio:
		case mostrarTodosProductos:
			rellenarTablaConProductos((List<TransferProducto>) contexto.getDatos());
			break;

		case mostrarUnProducto:
			rellenarTablaConUnProducto((TransferProducto) contexto.getDatos());
			break;

		default:
			break;
		}

	}

	private void rellenarTablaConUnProducto(TransferProducto producto) {
		datosTabla = new Object[1][6];

		datosTabla[0][0] = producto.getId();
		datosTabla[0][1] = producto.getName();
		datosTabla[0][2] = producto.getSo();
		datosTabla[0][3] = producto.getStock();
		datosTabla[0][4] = producto.getPrecio();
		datosTabla[0][5] = producto.getActivo();

		setTableConfiguration();
	}

	private void rellenarTablaConProductos(List<TransferProducto> productos) {
		datosTabla = new Object[productos.size()][6];
		int i = 0;
		for (TransferProducto producto : productos) {
			datosTabla[i][0] = producto.getId();
			datosTabla[i][1] = producto.getName();
			datosTabla[i][2] = producto.getSo();
			datosTabla[i][3] = producto.getStock();
			datosTabla[i][4] = producto.getPrecio();
			datosTabla[i][5] = producto.getActivo();
			++i;
		}

		setTableConfiguration();

	}

	public void setTableConfiguration() {
		productosTable.setModel(new DefaultTableModel(datosTabla,
				new String[] { "Id", "Nombre", "Sistema operativo", "Stock", "Precio", "Activo" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 7887877216184614686L;

			Class[] types = new Class[] { Integer.class, String.class, Integer.class, Integer.class, Double.class,
					Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		productosTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		productosTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		productosTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		productosTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		productosTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

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
		productosTable.getColumnModel().getColumn(4).setCellRenderer(doubleRenderer);
	}

	private void vaciarTablaProductos() {
		DefaultTableModel model = (DefaultTableModel) productosTable.getModel();
		model.setRowCount(0);
	}

	/**
	 * Creates new form FrameConEditor
	 */
	public GUIMenuProducto() {
		initGU();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initGU() {

		GridBagConstraints gridBagConstraints;

		panelTabla = new JPanel();
		jScrollPane5 = new JScrollPane();
		productosTable = new JTable();
		panelResultados = new JPanel();
		jScrollPane6 = new JScrollPane();
		resultadosTextArea = new JTextArea();
		panelCasosDeUso = new JPanel();
		altaButton = new JButton();
		actualizarButton = new JButton();
		bajaButton = new JButton();
		mostrarUnoButton = new JButton();
		mostrarTodosButton = new JButton();
		mostrarEnVentasButton = new JButton();
		insercionDatosPanel = new JPanel();
		menuPrincipalButton = new JButton();

		// Panel mostrar productos en ventas
		mostrarProductosEnVentasPanel = new JPanel();
		mostrarProductosEnVentasPanel
				.setBorder(BorderFactory.createTitledBorder("Mostrar productos en ventas superiores a un precio"));
		inicializarComponentesPanelMostrarProductosEnVentas();

		// Panel mostrar un producto
		mostrarUnoPanel = new JPanel();
		mostrarUnoPanel.setBorder(BorderFactory.createTitledBorder("Mostrar un producto"));
		inicializarComponentesPanelMostrarUno();

		// Panel baja
		bajaPanel = new JPanel();
		bajaPanel.setBorder(BorderFactory.createTitledBorder("Baja de un producto"));
		inicializarComponentesPanelBaja();

		// Panel alta
		altaPanel = new JPanel();
		altaPanel.setBorder(BorderFactory.createTitledBorder("Alta de un nuevo producto"));
		inicializarComponentesPanelAlta();

		// Panel modificar
		modificarPanel = new JPanel();
		modificarPanel.setBorder(BorderFactory.createTitledBorder("Modificar un producto"));
		inicializarComponentesPanelModificar();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		productosTable.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "Id", "Nombre", "Sistema operativo", "Stock", "Precio", "Activo" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -700867400030283608L;
			Class[] types = new Class[] { Integer.class, String.class, Integer.class, Integer.class, Double.class,
					Boolean.class };
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		productosTable.setShowGrid(true);
		jScrollPane5.setViewportView(productosTable);
		if (productosTable.getColumnModel().getColumnCount() > 0) {
			productosTable.getColumnModel().getColumn(0).setResizable(false);
			productosTable.getColumnModel().getColumn(0).setHeaderValue("Id");
			productosTable.getColumnModel().getColumn(1).setResizable(false);
			productosTable.getColumnModel().getColumn(1).setHeaderValue("Nombre");
			productosTable.getColumnModel().getColumn(2).setResizable(false);
			productosTable.getColumnModel().getColumn(2).setHeaderValue("Sistema operativo");
			productosTable.getColumnModel().getColumn(3).setResizable(false);
			productosTable.getColumnModel().getColumn(3).setHeaderValue("Stock");
			productosTable.getColumnModel().getColumn(4).setResizable(false);
			productosTable.getColumnModel().getColumn(4).setHeaderValue("Precio");
			productosTable.getColumnModel().getColumn(5).setResizable(false);
			productosTable.getColumnModel().getColumn(5).setHeaderValue("Activo");
		}

		GroupLayout panelTablaLayout = new GroupLayout(panelTabla);
		panelTabla.setLayout(panelTablaLayout);
		panelTablaLayout.setHorizontalGroup(panelTablaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelTablaLayout.createSequentialGroup().addContainerGap().addComponent(jScrollPane5,
						GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)));
		panelTablaLayout.setVerticalGroup(panelTablaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane5, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE));

		jScrollPane6.setViewportBorder(BorderFactory.createTitledBorder(""));

		resultadosTextArea.setEditable(false);
		resultadosTextArea.setColumns(20);
		resultadosTextArea.setRows(5);
		jScrollPane6.setViewportView(resultadosTextArea);

		GroupLayout panelResultadosLayout = new GroupLayout(panelResultados);
		panelResultados.setLayout(panelResultadosLayout);
		panelResultadosLayout
				.setHorizontalGroup(panelResultadosLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(panelResultadosLayout.createSequentialGroup().addContainerGap()
								.addComponent(jScrollPane6, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
								.addContainerGap()));
		panelResultadosLayout.setVerticalGroup(
				panelResultadosLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScrollPane6,
						GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE));

		panelCasosDeUso.setMaximumSize(new Dimension(411, 242));
		panelCasosDeUso.setMinimumSize(new Dimension(411, 242));
		panelCasosDeUso.setOpaque(false);
		panelCasosDeUso.setPreferredSize(new Dimension(411, 242));
		panelCasosDeUso.setLayout(new GridBagLayout());

		altaButton.setText("Alta producto");
		altaButton.setPreferredSize(new Dimension(150, 45));
		altaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				altaButtonActionPerformed();
			}
		});

		panelCasosDeUso.add(altaButton, new GridBagConstraints());

		actualizarButton.setText("Modificar producto");
		actualizarButton.setPreferredSize(new Dimension(150, 45));
		actualizarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				actualizarButtonActionPerformed();
			}

		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		panelCasosDeUso.add(actualizarButton, gridBagConstraints);

		bajaButton.setText("Baja producto");
		bajaButton.setPreferredSize(new Dimension(150, 45));
		bajaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bajaButtonActionPerformed();
			}
		});

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		panelCasosDeUso.add(bajaButton, gridBagConstraints);

		mostrarUnoButton.setText("Mostrar un producto");
		mostrarUnoButton.setPreferredSize(new Dimension(150, 45));
		mostrarUnoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mostrarUnoButtonActionPerformed();
			}
		});

		mostrarUnoButton.setPreferredSize(new Dimension(150, 45));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		panelCasosDeUso.add(mostrarUnoButton, gridBagConstraints);

		mostrarTodosButton.setText("Mostrar todos los productos");
		mostrarTodosButton.setPreferredSize(new Dimension(200, 45));
		mostrarTodosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mostrarTodosButtonActionPerformed();
			}
		});

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		panelCasosDeUso.add(mostrarTodosButton, gridBagConstraints);

		mostrarEnVentasButton.setText("Mostrar productos en ventas superiores a un precio");
		mostrarEnVentasButton.setPreferredSize(new Dimension(400, 45));
		mostrarEnVentasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mostrarEnVentasButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		panelCasosDeUso.add(mostrarEnVentasButton, gridBagConstraints);

		menuPrincipalButton.setText("Volver al menu principal");
		menuPrincipalButton.setPreferredSize(new Dimension(200, 45));
		menuPrincipalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuPrincipalButtonActionPerformed();
			}
		});

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		panelCasosDeUso.add(menuPrincipalButton, gridBagConstraints);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup().addContainerGap()
								.addComponent(panelTabla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(panelResultados,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup().addGap(12, 12, 12)
								.addComponent(panelCasosDeUso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(insercionDatosPanel, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
						.addComponent(panelResultados, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(panelTabla, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(panelCasosDeUso, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(insercionDatosPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addContainerGap()));

		this.resultadosTextArea.setLineWrap(true);
		this.resultadosTextArea.setWrapStyleWord(true);

		insercionDatosPanel.setMaximumSize(new Dimension(411, 242));
		insercionDatosPanel.setMinimumSize(new Dimension(411, 242));
		insercionDatosPanel.setPreferredSize(new Dimension(411, 242));
		insercionDatosPanel.setLayout(new CardLayout());

		this.insercionDatosPanel.add(new JPanel());
		this.insercionDatosPanel.add(modificarPanel, "Modificar");
		this.insercionDatosPanel.add(altaPanel, "Alta");
		this.insercionDatosPanel.add(bajaPanel, "Baja");
		this.insercionDatosPanel.add(mostrarUnoPanel, "Mostrar");
		this.insercionDatosPanel.add(mostrarProductosEnVentasPanel, "MostrarProductosEnVentas");
		pack();
	}

	private void inicializarComponentesPanelModificar() {

		idModificacionLabel = new JLabel();
		idModificacionTextField = new JTextField();
		nombreModificacionTextField = new JTextField();
		nombreModificacionLabel = new JLabel();
		sistemaOperativoModificacionTextField = new JTextField();
		stockModificacionTextField = new JTextField();
		precioModificacionTextField = new JTextField();
		sistemaOperativoModificacionLabel = new JLabel();
		stockModificacionLabel = new JLabel();
		precioModificacionLabel = new JLabel();
		aceptarModificacionButton = new JButton();
		cancelarModificacionButton = new JButton();

		idModificacionLabel.setText("Id:");

		nombreModificacionLabel.setText("Nombre del videojuego:");

		sistemaOperativoModificacionLabel.setText("Version del sistema operativo:");

		stockModificacionLabel.setText("Stock:");

		precioModificacionLabel.setText("Precio:");

		aceptarModificacionButton.setText("Modificar");
		aceptarModificacionButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarProductoButtonActionPerformed();
			}
		});

		cancelarModificacionButton.setText("Cancelar");
		cancelarModificacionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarActualizarProductoButtonActionPerformed();

			}
		});

		GroupLayout layout = new GroupLayout(modificarPanel);
		modificarPanel.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
						GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
										.addComponent(stockModificacionLabel).addComponent(nombreModificacionLabel)
										.addComponent(sistemaOperativoModificacionLabel)
										.addComponent(precioModificacionLabel))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
										.addComponent(nombreModificacionTextField, GroupLayout.DEFAULT_SIZE, 347,
												Short.MAX_VALUE)
										.addComponent(sistemaOperativoModificacionTextField)
										.addComponent(stockModificacionTextField)
										.addComponent(precioModificacionTextField)))
						.addGroup(GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addContainerGap(198, Short.MAX_VALUE)
										.addComponent(idModificacionLabel).addGap(18, 18, 18)
										.addComponent(idModificacionTextField, GroupLayout.PREFERRED_SIZE, 347,
												GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(203, 203, 203)
								.addComponent(aceptarModificacionButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cancelarModificacionButton)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(idModificacionLabel)
						.addComponent(idModificacionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(nombreModificacionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(nombreModificacionLabel))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(sistemaOperativoModificacionTextField, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(sistemaOperativoModificacionLabel))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(stockModificacionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(stockModificacionLabel))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(precioModificacionLabel).addComponent(precioModificacionTextField,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(aceptarModificacionButton).addComponent(cancelarModificacionButton))
				.addContainerGap()));
	}

	private void inicializarComponentesPanelAlta() {

		nombreAltaTextField = new JTextField();
		nombreAltaLabel = new JLabel();
		sistemaOperativoAltaTextField = new JTextField();
		stockAltaTextField = new JTextField();
		precioAltaTextField = new JTextField();
		sistemaOperativoAltaLabel = new JLabel();
		stockAltaLabel = new JLabel();
		precioAltaLabel = new JLabel();
		aceptarAltaButton = new JButton();
		cancelarAltaButton = new JButton();

		nombreAltaLabel.setText("Nombre del videojuego:");

		sistemaOperativoAltaLabel.setText("Version del sistema operativo:");

		stockAltaLabel.setText("Stock:");

		precioAltaLabel.setText("Precio:");

		aceptarAltaButton.setText("Dar de alta");
		aceptarAltaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				altaProductoButtonActionPerformed();
			}
		});

		cancelarAltaButton.setText("Cancelar");
		cancelarAltaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarAltaProductoButtonActionPerformed();

			}
		});

		cancelarAltaButton.setText("Cancelar");

		GroupLayout layout = new GroupLayout(altaPanel);
		altaPanel.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addGap(0, 30, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
												.addComponent(stockAltaLabel).addComponent(nombreAltaLabel)
												.addComponent(sistemaOperativoAltaLabel).addComponent(precioAltaLabel))
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
												.addComponent(nombreAltaTextField, GroupLayout.DEFAULT_SIZE, 347,
														Short.MAX_VALUE)
												.addComponent(sistemaOperativoAltaTextField)
												.addComponent(stockAltaTextField).addComponent(precioAltaTextField)))
						.addGroup(layout.createSequentialGroup().addGap(203, 203, 203).addComponent(aceptarAltaButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cancelarAltaButton)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(38, 38, 38)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(nombreAltaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(nombreAltaLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(sistemaOperativoAltaTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(sistemaOperativoAltaLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(stockAltaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(stockAltaLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(precioAltaLabel)
										.addComponent(precioAltaTextField, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(aceptarAltaButton).addComponent(cancelarAltaButton))
						.addContainerGap()));
	}// </editor-fold>

	private void inicializarComponentesPanelBaja() {

		idBajaLabel = new JLabel();
		idBajaTextField = new JTextField();
		aceptarBajaButton = new JButton();
		cancelarBajaButton = new JButton();

		idBajaLabel.setText("Id:");
		aceptarBajaButton.setText("Baja");
		aceptarBajaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bajaProductoButtonActionPerformed();
			}
		});

		cancelarBajaButton.setText("Cancelar");
		cancelarBajaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarBajaProductoButtonActionPerformed();
			}
		});

		GroupLayout layout = new GroupLayout(bajaPanel);
		bajaPanel.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(203, 203, 203).addComponent(aceptarBajaButton)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(cancelarBajaButton)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addGap(0, 118, Short.MAX_VALUE).addComponent(idBajaLabel)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(idBajaTextField,
										GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)
								.addGap(98, 98, 98)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(93, 93, 93)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(idBajaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(idBajaLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(aceptarBajaButton).addComponent(cancelarBajaButton))
						.addContainerGap()));
	}// </editor-fold>

	private void inicializarComponentesPanelMostrarUno() {

		idMostrarUnoLabel = new JLabel();
		idMostrarUnoTextField = new JTextField();
		aceptarMostrarUnoButton = new JButton();
		cancelarMostrarUnoButton = new JButton();

		idMostrarUnoLabel.setText("Id:");
		aceptarMostrarUnoButton.setText("Mostrar");
		aceptarMostrarUnoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarUnProductoButtonActionPerformed();
			}
		});

		cancelarMostrarUnoButton.setText("Cancelar");
		cancelarMostrarUnoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarMostrarUnProductoButtonActionPerformed();

			}
		});

		GroupLayout layout = new GroupLayout(mostrarUnoPanel);
		mostrarUnoPanel.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(203, 203, 203).addComponent(aceptarMostrarUnoButton)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(cancelarMostrarUnoButton)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addGap(0, 118, Short.MAX_VALUE).addComponent(idMostrarUnoLabel)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(idMostrarUnoTextField, GroupLayout.PREFERRED_SIZE, 347,
										GroupLayout.PREFERRED_SIZE)
								.addGap(98, 98, 98)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(93, 93, 93)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(idMostrarUnoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(idMostrarUnoLabel))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(aceptarMostrarUnoButton).addComponent(cancelarMostrarUnoButton))
				.addContainerGap()));
	}

	private void inicializarComponentesPanelMostrarProductosEnVentas() {
		GridBagConstraints gridBagConstraints;

		precioMostrarProductosEnVentasLabel = new JLabel();
		precioMostrarProductosEnVentasTextField = new JTextField();
		aceptarMostrarProductosEnVentasButton = new JButton();
		cancelarMostrarProductosEnVentasButton = new JButton();

		precioMostrarProductosEnVentasLabel.setText("Precio:");
		aceptarMostrarProductosEnVentasButton.setText("Mostrar");
		aceptarMostrarProductosEnVentasButton.setMaximumSize(new Dimension(150, 45));
		aceptarMostrarProductosEnVentasButton.setMinimumSize(new Dimension(150, 45));
		aceptarMostrarProductosEnVentasButton.setPreferredSize(new Dimension(150, 45));
		aceptarMostrarProductosEnVentasButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarProductosEnVentasButtonActionPerformed();
			}
		});

		cancelarMostrarProductosEnVentasButton.setText("Cancelar");
		cancelarMostrarProductosEnVentasButton.setMaximumSize(new Dimension(150, 45));
		cancelarMostrarProductosEnVentasButton.setMinimumSize(new Dimension(150, 45));
		cancelarMostrarProductosEnVentasButton.setPreferredSize(new Dimension(150, 45));
		cancelarMostrarProductosEnVentasButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarmostrarProductosEnVentasProductoButtonActionPerformed();

			}
		});

		mostrarProductosEnVentasPanel.setMaximumSize(new Dimension(411, 242));
		mostrarProductosEnVentasPanel.setMinimumSize(new Dimension(411, 242));
		mostrarProductosEnVentasPanel.setPreferredSize(new Dimension(411, 242));
		mostrarProductosEnVentasPanel.setLayout(new GridBagLayout());

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(69, 0, 0, 42);
		mostrarProductosEnVentasPanel.add(precioMostrarProductosEnVentasLabel, gridBagConstraints);

		precioMostrarProductosEnVentasTextField.setMaximumSize(new Dimension(300, 20));
		precioMostrarProductosEnVentasTextField.setMinimumSize(new Dimension(300, 20));
		precioMostrarProductosEnVentasTextField.setPreferredSize(new Dimension(300, 20));

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(69, -80, 0, 42);
		mostrarProductosEnVentasPanel.add(precioMostrarProductosEnVentasTextField, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(69, 0, 0, 42);
		mostrarProductosEnVentasPanel.add(aceptarMostrarProductosEnVentasButton, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(69, 0, 0, 42);
		mostrarProductosEnVentasPanel.add(cancelarMostrarProductosEnVentasButton, gridBagConstraints);
	}

	private static final long serialVersionUID = -849695982820362597L;
	// Variables declaration - do not modify
	private JButton actualizarButton;
	private JButton altaButton;
	private JButton bajaButton;
	private JPanel insercionDatosPanel;
	private JScrollPane jScrollPane5;
	private JScrollPane jScrollPane6;
	private JTable productosTable;
	private JButton mostrarEnVentasButton;
	private JButton mostrarTodosButton;
	private JButton mostrarUnoButton;
	private JPanel panelCasosDeUso;
	private JPanel panelResultados;
	private JPanel panelTabla;
	private JTextArea resultadosTextArea;
	private Object[][] datosTabla;
	private JButton menuPrincipalButton;
	// End of variables declaration

	// Variables del panel mostrarProductosEnVentas
	private JPanel mostrarProductosEnVentasPanel;
	private JButton aceptarMostrarProductosEnVentasButton;
	private JButton cancelarMostrarProductosEnVentasButton;
	private JLabel precioMostrarProductosEnVentasLabel;
	private JTextField precioMostrarProductosEnVentasTextField;
	// End of variables declaration

	// Variables del panel mostrar un producto
	private JPanel mostrarUnoPanel;
	private JButton aceptarMostrarUnoButton;
	private JButton cancelarMostrarUnoButton;
	private JLabel idMostrarUnoLabel;
	private JTextField idMostrarUnoTextField;

	// Variables del panel baja
	private JPanel bajaPanel;
	private JButton aceptarBajaButton;
	private JButton cancelarBajaButton;
	private JLabel idBajaLabel;
	private JTextField idBajaTextField;

	// Variables del panel alta
	private JPanel altaPanel;
	private JButton cancelarAltaButton;
	private JButton aceptarAltaButton;
	private JLabel nombreAltaLabel;
	private JTextField nombreAltaTextField;
	private JLabel precioAltaLabel;
	private JTextField precioAltaTextField;
	private JLabel sistemaOperativoAltaLabel;
	private JTextField sistemaOperativoAltaTextField;
	private JLabel stockAltaLabel;
	private JTextField stockAltaTextField;

	// Variables del panel modificar
	private JPanel modificarPanel;
	private JButton cancelarModificacionButton;
	private JLabel idModificacionLabel;
	private JTextField idModificacionTextField;
	private JButton aceptarModificacionButton;
	private JLabel nombreModificacionLabel;
	private JTextField nombreModificacionTextField;
	private JLabel precioModificacionLabel;
	private JTextField precioModificacionTextField;
	private JLabel sistemaOperativoModificacionLabel;
	private JTextField sistemaOperativoModificacionTextField;
	private JLabel stockModificacionLabel;
	private JTextField stockModificacionTextField;

}