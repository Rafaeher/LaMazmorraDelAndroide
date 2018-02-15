package presentacion.cliente;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import presentacion.GUI;
import presentacion.controlador.Contexto;
import presentacion.controlador.ControladorAplicacion;
import presentacion.eventos.Evento;

import negocio.cliente.TransferCliente;
import negocio.cliente.TransferClienteVip;
import negocio.cliente.TransferClienteEstandar;

public class GUIMenuCliente extends JFrame implements GUI {

	private static final long serialVersionUID = 6684609487367296176L;

	// Action Performed Menu Botones

	private void actualizarButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirActualizarCliente, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void altaButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirAltaCliente, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void bajaButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirBajaCliente, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void mostrarUnoButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirMostrarUnCliente, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void mostrarTodosButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.mostrarTodosClientes, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
	}

	private void menuPrincipalButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirMenuPrincipal, null);
		ControladorAplicacion.obtenerInstancia().accion(contexto);
		this.dispose();
	}

	// Action Performed ModificarCliente

	private void modificarClienteVipButtonActionPerformed() {
		try {
			int idupdate = Integer.parseInt(idModificacionVipTextField.getText());
			String nombre = nombreModificacionVipTextField.getText();
			String apellido = apellidoModificacionVipTextField.getText();
			String direccion = direccionModificacionVipTextField.getText();
			String correo = correoModificacionVipTextField.getText();

			if (nombre.equals("") || apellido.equals("") || direccion.equals("") || correo.equals("") || idupdate <= 0)
				throw new Exception("Error: argumentos invalidos");

			TransferCliente transferCliente = new TransferClienteVip(idupdate, nombre, apellido, direccion, correo);

			Contexto contexto = new Contexto(Evento.actualizarCliente, transferCliente);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
			
			idModificacionVipTextField.setText("");
			nombreModificacionVipTextField.setText("");
			apellidoModificacionVipTextField.setText("");
			direccionModificacionVipTextField.setText("");
			correoModificacionVipTextField.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarModificarClienteVipButtonActionPerformed() {
		idModificacionVipTextField.setText("");
		nombreModificacionVipTextField.setText("");
		apellidoModificacionVipTextField.setText("");
		direccionModificacionVipTextField.setText("");
		correoModificacionVipTextField.setText("");

		CardLayout cl = (CardLayout) (insercionDatosPanel.getLayout());
		cl.first(insercionDatosPanel);
	}

	private void modificarClienteEstandarButtonActionPerformed() {
		try {
			int idupdate = Integer.parseInt(idModificacionEstandarTextField.getText());
			String nombre = nombreModificacionEstandarTextField.getText();
			String apellido = apellidoModificacionEstandarTextField.getText();
			String direccion = direccionModificacionEstandarTextField.getText();
			String correo = correoModificacionEstandarTextField.getText();
			boolean recibePublicidad = recibePublicidadModificarEstandarComboBox.getSelectedItem().equals("Si");

			if (nombre.equals("") || apellido.equals("") || direccion.equals("") || correo.equals("") || idupdate <= 0)
				throw new Exception("Error: argumentos invalidos");

			TransferCliente transferCliente = new TransferClienteEstandar(idupdate, nombre, apellido, direccion, correo,
					recibePublicidad);

			Contexto contexto = new Contexto(Evento.actualizarCliente, transferCliente);
			ControladorAplicacion.obtenerInstancia().accion(contexto);

			idModificacionEstandarTextField.setText("");
			nombreModificacionEstandarTextField.setText("");
			apellidoModificacionEstandarTextField.setText("");
			direccionModificacionEstandarTextField.setText("");
			correoModificacionEstandarTextField.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarModificarClienteEstandarButtonActionPerformed() {
		idModificacionEstandarTextField.setText("");
		nombreModificacionEstandarTextField.setText("");
		apellidoModificacionEstandarTextField.setText("");
		direccionModificacionEstandarTextField.setText("");
		correoModificacionEstandarTextField.setText("");

		CardLayout cl = (CardLayout) (insercionDatosPanel.getLayout());
		cl.first(insercionDatosPanel);
	}

	// Action Performed AltaCliente

	private void altaClienteVipButtonActionPerformed() {
		try {
			String nombre = nombreAltaVipTextField.getText();
			String apellido = apellidoAltaVipTextField.getText();
			String direccion = direccionAltaVipTextField.getText();
			String correo = correoAltaVipTextField.getText();
			Integer descuento = Integer.parseInt(descuentoAltaVipTextField.getText());

			if (nombre.equals("") || apellido.equals("") || direccion.equals("") || correo.equals("") || descuento < 0
					|| descuento > 100)
				throw new IllegalArgumentException("Los argumentos no son validos");

			TransferCliente transferCliente = new TransferClienteVip(nombre, apellido, direccion, correo, descuento);

			Contexto contexto = new Contexto(Evento.altaCliente, transferCliente);
			ControladorAplicacion.obtenerInstancia().accion(contexto);

			nombreAltaVipTextField.setText("");
			apellidoAltaVipTextField.setText("");
			direccionAltaVipTextField.setText("");
			correoAltaVipTextField.setText("");
			descuentoAltaVipTextField.setText("");

		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);

		}
	}

	private void cancelarAltaClienteVipButtonActionPerformed() {
		CardLayout cl = (CardLayout) (insercionDatosPanel.getLayout());
		cl.first(insercionDatosPanel);

		nombreAltaVipTextField.setText("");
		apellidoAltaVipTextField.setText("");
		direccionAltaVipTextField.setText("");
		correoAltaVipTextField.setText("");
		descuentoAltaVipTextField.setText("");
	}

	private void altaClienteEstandarButtonActionPerformed() {
		try {
			String nombre = nombreAltaEstandarTextField.getText();
			String apellido = apellidoAltaEstandarTextField.getText();
			String direccion = direccionAltaEstandarTextField.getText();
			String correo = correoAltaEstandarTextField.getText();
			boolean recibePublicidad = recibePublicidadAltaEstandarComboBox.getSelectedItem().equals("Si");

			if (nombre.equals("") || apellido.equals("") || direccion.equals("") || correo.equals(""))
				throw new IllegalArgumentException("Los argumentos no son validos");

			TransferCliente transferCliente = new TransferClienteEstandar(nombre, apellido, direccion, correo,
					recibePublicidad);

			Contexto contexto = new Contexto(Evento.altaCliente, transferCliente);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
			
			nombreAltaEstandarTextField.setText("");
			apellidoAltaEstandarTextField.setText("");
			direccionAltaEstandarTextField.setText("");
			correoAltaEstandarTextField.setText("");
			
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarAltaClienteEstandarButtonActionPerformed() {
		CardLayout cl = (CardLayout) (insercionDatosPanel.getLayout());
		cl.first(insercionDatosPanel);
		
		nombreAltaEstandarTextField.setText("");
		apellidoAltaEstandarTextField.setText("");
		direccionAltaEstandarTextField.setText("");
		correoAltaEstandarTextField.setText("");
	}

	// Action Performed BajaCliente

	private void bajaClienteButtonActionPerformed() {
		try {
			int id = Integer.parseInt(idBajaTextField.getText());

			if (id < 0)
				throw new Exception();

			TransferCliente transferCliente = new TransferCliente(id);

			Contexto contexto = new Contexto(Evento.bajaCliente, transferCliente);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
			idBajaTextField.setText("");

		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarBajaClienteButtonActionPerformed() {
		idBajaTextField.setText("");
		CardLayout cl = (CardLayout) (insercionDatosPanel.getLayout());
		cl.first(insercionDatosPanel);
	}

	// Action Performed MostrarUnoCliente

	private void mostrarUnoClienteButtonActionPerformed() {
		try {
			int id = Integer.parseInt(idMostrarUnoTextField.getText());

			if (id < 0)
				throw new Exception();

			TransferCliente tCli = new TransferCliente(id);
			Contexto contexto = new Contexto(Evento.mostrarUnCliente, tCli);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
			idMostrarUnoTextField.setText("");

		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacion.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarMostrarUnoClienteButtonActionPerformed() {
		idMostrarUnoTextField.setText("");
		CardLayout cl = (CardLayout) (insercionDatosPanel.getLayout());
		cl.first(insercionDatosPanel);
	}

	// ---------------------------------

	public GUIMenuCliente() {
		initGUI();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actualizar(Contexto contexto) {
		switch (contexto.getEvento()) {

		case abrirActualizarCliente:
			CardLayout cl = (CardLayout) (insercionDatosPanel.getLayout());
			cl.show(insercionDatosPanel, "SeleccionTipoModificar");
			break;

		case abrirActualizarClienteEstandar:
			CardLayout cl1 = (CardLayout) (insercionDatosPanel.getLayout());
			cl1.show(insercionDatosPanel, "ModificarEstandar");
			break;

		case abrirActualizarClienteVip:
			CardLayout cl2 = (CardLayout) (insercionDatosPanel.getLayout());
			cl2.show(insercionDatosPanel, "ModificarVip");
			break;

		case abrirAltaCliente:
			CardLayout cl3 = (CardLayout) (insercionDatosPanel.getLayout());
			cl3.show(insercionDatosPanel, "SeleccionTipoAlta");
			break;

		case abrirAltaClienteEstandar:
			CardLayout cl4 = (CardLayout) (insercionDatosPanel.getLayout());
			cl4.show(insercionDatosPanel, "AltaEstandar");
			break;

		case abrirAltaClienteVip:
			CardLayout cl5 = (CardLayout) (insercionDatosPanel.getLayout());
			cl5.show(insercionDatosPanel, "AltaVip");
			break;

		case abrirBajaCliente:
			CardLayout cl6 = (CardLayout) (insercionDatosPanel.getLayout());
			cl6.show(insercionDatosPanel, "Baja");
			break;

		case abrirMostrarUnCliente:
			CardLayout cl7 = (CardLayout) (insercionDatosPanel.getLayout());
			cl7.show(insercionDatosPanel, "Mostrar");
			break;

		case actualizarCliente:
			this.resultadosTextArea.append("->Actualizacion realizada correctamente\n\n");
			break;

		case altaCliente:
			this.resultadosTextArea.append("->Alta realizada correctamente con id "
					+ ((TransferCliente) contexto.getDatos()).getId() + "\n\n");
			break;

		case errorAltaClienteDuplicado:
			this.resultadosTextArea.append("->Este cliente ya estaba en el sistema, se ha activado\n\n");
			break;

		case bajaCliente:
			this.resultadosTextArea.append("->Baja realizada correctamente\n\n");
			break;

		case errorAltaCliente:
			this.resultadosTextArea.append("->Error en alta: ya existe un cliente con email "
					+ ((TransferCliente) contexto.getDatos()).getCorreo() + " en el sistema.\n\n");
			break;

		case errorAltaClienteDistintoDescuento:
			this.resultadosTextArea.append("->El cliente con id " + ((TransferCliente) contexto.getDatos()).getId()
					+ " estaba inactivo, se ha activado y se han actualizado sus datos, salvo el descuento (que no es modificable).\n\n");
			break;

		case errorActualizarCliente:
			this.resultadosTextArea.append("->Id del cliente inexistente\n\n");
			break;

		case errorActualizarClienteReactivacion:

			this.resultadosTextArea.append("->El cliente con id " + ((TransferCliente) contexto.getDatos()).getId()
					+ " " + "estaba inactivo, se ha activado y se han actuliazado sus datos.\n\n");
			break;

		case errorActualizarClienteTiposNoCoinciden:
			this.resultadosTextArea.append(
					"->No existe en el sistema un cliente de tipo " + ((TransferCliente) contexto.getDatos()).getTipo()
							+ " con id " + ((TransferCliente) contexto.getDatos()).getId() + "\n\n");
			break;

		case errorActualizarClienteConMismoEmail:
			this.resultadosTextArea.append("->No se ha podido actualizar al cliente, ya existe un cliente"
					+ " con el email " + ((TransferCliente) contexto.getDatos()).getCorreo() + " en el sistema.\n\n");
			break;

		case errorBajaCliente:
			this.resultadosTextArea.append("->No existe en el sistema un cliente con id "
					+ ((TransferCliente) contexto.getDatos()).getId() + "\n\n");
			break;

		case errorBajaClienteDuplicado:
			this.resultadosTextArea.append("->El cliente ya estaba dado de baja\n\n");
			break;

		case errorArgumentos:
			this.resultadosTextArea.append("->Error al introducir argumentos\n\n");
			break;

		case errorCambioTipoCliente:
			this.resultadosTextArea.append("->Error: no se puede cambiar el tipo de un cliente\n\n");
			break;

		case errorConexionBBDD:
			this.resultadosTextArea.append("->Error en la conexion a la Base de Datos\n\n");
			break;

		case errorMostrarUnCliente:
			vaciarTablaClientes();
			this.resultadosTextArea.append("->Id del cliente inexistente\n\n");
			break;

		case errorMostrarTodosClientes:
			vaciarTablaClientes();
			this.resultadosTextArea.append("->No existen clientes en el sistema\n\n");
			break;

		case mostrarTodosClientes:
			rellenarTablaConClientes((List<TransferCliente>) contexto.getDatos());
			break;

		case mostrarUnCliente:
			rellenarTablaConUnCliente((TransferCliente) contexto.getDatos());
			break;

		default:
			break;
		}
	}

	private void rellenarTablaConUnCliente(TransferCliente cliente) {
		datosTabla = new Object[1][10];

		datosTabla[0][0] = cliente.getId();
		datosTabla[0][1] = cliente.getTipo();
		datosTabla[0][2] = cliente.getNombre();
		datosTabla[0][3] = cliente.getApellido();
		datosTabla[0][4] = cliente.getCorreo();
		datosTabla[0][5] = cliente.getDireccion();
		datosTabla[0][6] = cliente.getActivo();

		if (cliente.getTipo().equalsIgnoreCase("Vip")) {
			datosTabla[0][7] = ((TransferClienteVip) cliente).getDescuento();
			datosTabla[0][8] = ((TransferClienteVip) cliente).getPuntos();
			datosTabla[0][9] = null;
		} else {
			datosTabla[0][7] = null;
			datosTabla[0][8] = null;
			datosTabla[0][9] = ((TransferClienteEstandar) cliente).getRecibePublicidad();
		}

		setTableConfiguration();

	}

	private void rellenarTablaConClientes(List<TransferCliente> clientes) {
		datosTabla = new Object[clientes.size()][10];
		int i = 0;
		for (TransferCliente cliente : clientes) {
			datosTabla[i][0] = cliente.getId();
			datosTabla[i][1] = cliente.getTipo();
			datosTabla[i][2] = cliente.getNombre();
			datosTabla[i][3] = cliente.getApellido();
			datosTabla[i][4] = cliente.getCorreo();
			datosTabla[i][5] = cliente.getDireccion();
			datosTabla[i][6] = cliente.getActivo();

			if (cliente.getTipo().equalsIgnoreCase("Vip")) {
				datosTabla[i][7] = ((TransferClienteVip) cliente).getDescuento();
				datosTabla[i][8] = ((TransferClienteVip) cliente).getPuntos();
				datosTabla[i][9] = null;
			} else {
				datosTabla[i][7] = null;
				datosTabla[i][8] = null;
				datosTabla[i][9] = ((TransferClienteEstandar) cliente).getRecibePublicidad();
			}
			++i;
		}

		setTableConfiguration();
	}

	private void vaciarTablaClientes() {
		DefaultTableModel model = (DefaultTableModel) clientesTable.getModel();
		model.setRowCount(0);
	}

	private void setTableConfiguration() {
		clientesTable.setModel(new DefaultTableModel(datosTabla, new String[] { "Id", "Tipo", "Nombre", "Apellido",
				"Correo", "Direccion", "Activo", "Descuento (%)", "Puntos", "Recibe publicidad" }) {

			private static final long serialVersionUID = 968785611059193713L;

			Class<?>[] types = new Class[] { Integer.class, String.class, String.class, String.class, String.class,
					String.class, Boolean.class, Integer.class, Integer.class, Boolean.class };

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		clientesTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		clientesTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		clientesTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		clientesTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		clientesTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		clientesTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		clientesTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
		clientesTable.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

		clientesTable.setShowGrid(true);
		jScrollPane5.setViewportView(clientesTable);
	}

	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initGUI() {

		GridBagConstraints gridBagConstraints;

		panelTabla = new JPanel();
		jScrollPane5 = new JScrollPane();
		clientesTable = new JTable();
		panelResultados = new JPanel();
		jScrollPane6 = new JScrollPane();
		resultadosTextArea = new JTextArea();
		panelCasosDeUso = new JPanel();
		altaButton = new JButton();
		actualizarButton = new JButton();
		bajaButton = new JButton();
		mostrarUnoButton = new JButton();
		mostrarTodosButton = new JButton();
		insercionDatosPanel = new JPanel();
		menuPrincipalButton = new JButton();

		// Panel mostrar un producto
		mostrarUnoPanel = new JPanel();
		mostrarUnoPanel.setBorder(BorderFactory.createTitledBorder("Mostrar un cliente"));
		inicializarComponentesPanelMostrarUno();

		// Panel baja
		bajaPanel = new JPanel();
		bajaPanel.setBorder(BorderFactory.createTitledBorder("Baja de un cliente"));
		inicializarComponentesPanelBaja();

		// Panel alta estandar
		altaEstandarPanel = new JPanel();
		altaEstandarPanel.setBorder(BorderFactory.createTitledBorder("Alta de un nuevo cliente estandar"));
		inicializarComponentesPanelAltaEstandar();

		// Panel alta vip
		altaVipPanel = new JPanel();
		altaVipPanel.setBorder(BorderFactory.createTitledBorder("Alta de un nuevo cliente VIP"));
		inicializarComponentesPanelAltaVip();

		// Panel de seleccion de tipo de cliente en el alta
		seleccionTipoClienteEnAltaPanel = new JPanel();
		inicializarComponentesPanelSeleccionTipoClienteAlta();

		// Panel modificar vip
		modificarVipPanel = new JPanel();
		modificarVipPanel.setBorder(BorderFactory.createTitledBorder("Modificar un cliente VIP"));
		inicializarComponentesPanelModificarVip();

		// Panel modificar estandar
		modificarEstandarPanel = new JPanel();
		modificarEstandarPanel.setBorder(BorderFactory.createTitledBorder("Modificar un cliente estandar"));
		inicializarComponentesPanelModificarEstandar();

		// Panel de seleccion de tipo de cliente en la modificacion
		seleccionTipoClienteEnModificarPanel = new JPanel();
		inicializarComponentesPanelSeleccionTipoClienteModificacion();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		setTableConfiguration();

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

		altaButton.setText("Alta cliente");
		altaButton.setPreferredSize(new Dimension(150, 45));
		altaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				altaButtonActionPerformed();
			}
		});

		panelCasosDeUso.add(altaButton, new GridBagConstraints());

		actualizarButton.setText("Modificar cliente");
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

		bajaButton.setText("Baja cliente");
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

		mostrarUnoButton.setText("Mostrar un cliente");
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

		mostrarTodosButton.setText("Mostrar todos los clientes");
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

		menuPrincipalButton.setText("Volver al menu principal");
		menuPrincipalButton.setPreferredSize(new Dimension(200, 45));
		menuPrincipalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuPrincipalButtonActionPerformed();
			}
		});

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		panelCasosDeUso.add(menuPrincipalButton, gridBagConstraints);

		GroupLayout insercionDatosPanelLayout = new GroupLayout(insercionDatosPanel);
		insercionDatosPanel.setLayout(insercionDatosPanelLayout);
		insercionDatosPanelLayout.setHorizontalGroup(insercionDatosPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));
		insercionDatosPanelLayout.setVerticalGroup(insercionDatosPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 269, Short.MAX_VALUE));

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
		this.insercionDatosPanel.setLayout(new CardLayout());
		this.insercionDatosPanel.add(new JPanel());
		this.insercionDatosPanel.add(modificarVipPanel, "ModificarVip");
		this.insercionDatosPanel.add(modificarEstandarPanel, "ModificarEstandar");
		this.insercionDatosPanel.add(altaEstandarPanel, "AltaEstandar");
		this.insercionDatosPanel.add(altaVipPanel, "AltaVip");
		this.insercionDatosPanel.add(bajaPanel, "Baja");
		this.insercionDatosPanel.add(mostrarUnoPanel, "Mostrar");
		this.insercionDatosPanel.add(seleccionTipoClienteEnAltaPanel, "SeleccionTipoAlta");
		this.insercionDatosPanel.add(seleccionTipoClienteEnModificarPanel, "SeleccionTipoModificar");

		pack();
	}

	private void inicializarComponentesPanelModificarVip() {

		idModificacionVipLabel = new JLabel();
		idModificacionVipTextField = new JTextField();
		nombreModificacionVipLabel = new JLabel();
		nombreModificacionVipTextField = new JTextField();
		apellidoModificacionVipLabel = new JLabel();
		apellidoModificacionVipTextField = new JTextField();
		direccionModificacionVipLabel = new JLabel();
		direccionModificacionVipTextField = new JTextField();
		correoModificacionVipLabel = new JLabel();
		correoModificacionVipTextField = new JTextField();
		aceptarModificarVipButton = new JButton();
		cancelarModificarVipButton = new JButton();

		idModificacionVipLabel.setText("Id:");
		nombreModificacionVipLabel.setText("Nombre:");
		apellidoModificacionVipLabel.setText("Apellido:");
		direccionModificacionVipLabel.setText("Direccion:");
		correoModificacionVipLabel.setText("Correo:");

		aceptarModificarVipButton.setText("Modificar");
		aceptarModificarVipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modificarClienteVipButtonActionPerformed();
			}
		});

		cancelarModificarVipButton.setText("Cancelar");
		cancelarModificarVipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarModificarClienteVipButtonActionPerformed();
			}
		});

		GroupLayout modificarVipPanelLayout = new GroupLayout(modificarVipPanel);
		modificarVipPanel.setLayout(modificarVipPanelLayout);
		modificarVipPanelLayout
				.setHorizontalGroup(modificarVipPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING,
								modificarVipPanelLayout.createSequentialGroup()
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(aceptarModificarVipButton).addGap(18, 18, 18)
										.addComponent(cancelarModificarVipButton).addGap(83, 83, 83))
						.addGroup(GroupLayout.Alignment.TRAILING, modificarVipPanelLayout.createSequentialGroup()
								.addGap(0, 196, Short.MAX_VALUE)
								.addGroup(modificarVipPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(GroupLayout.Alignment.TRAILING, modificarVipPanelLayout
												.createSequentialGroup().addComponent(idModificacionVipLabel)
												.addGap(18, 18, 18).addComponent(idModificacionVipTextField,
														GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
										.addGroup(GroupLayout.Alignment.TRAILING, modificarVipPanelLayout
												.createSequentialGroup().addComponent(nombreModificacionVipLabel)
												.addGap(18, 18, 18).addComponent(nombreModificacionVipTextField,
														GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
										.addGroup(GroupLayout.Alignment.TRAILING, modificarVipPanelLayout
												.createSequentialGroup().addComponent(apellidoModificacionVipLabel)
												.addGap(18, 18, 18).addComponent(apellidoModificacionVipTextField,
														GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
										.addGroup(GroupLayout.Alignment.TRAILING, modificarVipPanelLayout
												.createSequentialGroup().addComponent(direccionModificacionVipLabel)
												.addGap(18, 18, 18).addComponent(direccionModificacionVipTextField,
														GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
										.addGroup(GroupLayout.Alignment.TRAILING,
												modificarVipPanelLayout.createSequentialGroup()
														.addComponent(correoModificacionVipLabel).addGap(18, 18, 18)
														.addComponent(correoModificacionVipTextField,
																GroupLayout.PREFERRED_SIZE, 317,
																GroupLayout.PREFERRED_SIZE)))));
		modificarVipPanelLayout.setVerticalGroup(modificarVipPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(modificarVipPanelLayout.createSequentialGroup().addContainerGap()
						.addGroup(modificarVipPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(idModificacionVipTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(idModificacionVipLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(modificarVipPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(nombreModificacionVipTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(nombreModificacionVipLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(modificarVipPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(apellidoModificacionVipTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(apellidoModificacionVipLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(modificarVipPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(direccionModificacionVipTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(direccionModificacionVipLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(modificarVipPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(correoModificacionVipTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(correoModificacionVipLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(modificarVipPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(aceptarModificarVipButton).addComponent(cancelarModificarVipButton))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

	}

	private void inicializarComponentesPanelModificarEstandar() {

		idModificacionEstandarLabel = new JLabel();
		idModificacionEstandarTextField = new JTextField();
		nombreModificacionEstandarLabel = new JLabel();
		nombreModificacionEstandarTextField = new JTextField();
		apellidoModificacionEstandarLabel = new JLabel();
		apellidoModificacionEstandarTextField = new JTextField();
		direccionModificacionEstandarLabel = new JLabel();
		direccionModificacionEstandarTextField = new JTextField();
		correoModificacionEstandarLabel = new JLabel();
		correoModificacionEstandarTextField = new JTextField();
		aceptarModificarEstandarButton = new JButton();
		cancelarModificarEstandarButton = new JButton();
		recibePublicidadModificarEstandarComboBox = new JComboBox<>();
		recibePublicidadModificarLabel = new JLabel();

		recibePublicidadModificarEstandarComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Si", "No" }));

		idModificacionEstandarLabel.setText("Id:");
		nombreModificacionEstandarLabel.setText("Nombre:");
		apellidoModificacionEstandarLabel.setText("Apellido:");
		direccionModificacionEstandarLabel.setText("Direccion:");
		correoModificacionEstandarLabel.setText("Correo:");
		recibePublicidadModificarLabel.setText("Recibe Publicidad: ");
		aceptarModificarEstandarButton.setText("Modificar");
		cancelarModificarEstandarButton.setText("Cancelar");

		aceptarModificarEstandarButton.setText("Modificar");
		aceptarModificarEstandarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modificarClienteEstandarButtonActionPerformed();
			}
		});

		cancelarModificarEstandarButton.setText("Cancelar");
		cancelarModificarEstandarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarModificarClienteEstandarButtonActionPerformed();
			}
		});

		GroupLayout modificarEstandarPanelLayout = new GroupLayout(modificarEstandarPanel);
		modificarEstandarPanel.setLayout(modificarEstandarPanelLayout);
		modificarEstandarPanelLayout
				.setHorizontalGroup(modificarEstandarPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING,
								modificarEstandarPanelLayout.createSequentialGroup()
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(aceptarModificarEstandarButton).addGap(18, 18, 18)
										.addComponent(cancelarModificarEstandarButton).addGap(83, 83, 83))
						.addGroup(GroupLayout.Alignment.TRAILING, modificarEstandarPanelLayout.createSequentialGroup()
								.addGap(0, 199, Short.MAX_VALUE)
								.addGroup(modificarEstandarPanelLayout
										.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(GroupLayout.Alignment.TRAILING, modificarEstandarPanelLayout
												.createSequentialGroup().addComponent(idModificacionEstandarLabel)
												.addGap(18, 18, 18).addComponent(idModificacionEstandarTextField,
														GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
										.addGroup(GroupLayout.Alignment.TRAILING, modificarEstandarPanelLayout
												.createSequentialGroup().addComponent(nombreModificacionEstandarLabel)
												.addGap(18, 18, 18).addComponent(nombreModificacionEstandarTextField,
														GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
										.addGroup(GroupLayout.Alignment.TRAILING, modificarEstandarPanelLayout
												.createSequentialGroup().addComponent(apellidoModificacionEstandarLabel)
												.addGap(18, 18, 18).addComponent(apellidoModificacionEstandarTextField,
														GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
										.addGroup(GroupLayout.Alignment.TRAILING, modificarEstandarPanelLayout
												.createSequentialGroup()
												.addComponent(direccionModificacionEstandarLabel).addGap(18, 18, 18)
												.addComponent(direccionModificacionEstandarTextField,
														GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
										.addGroup(GroupLayout.Alignment.TRAILING, modificarEstandarPanelLayout
												.createSequentialGroup().addComponent(correoModificacionEstandarLabel)
												.addGap(18, 18, 18).addComponent(correoModificacionEstandarTextField,
														GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
										.addGroup(GroupLayout.Alignment.TRAILING,
												modificarEstandarPanelLayout.createSequentialGroup()
														.addComponent(recibePublicidadModificarLabel).addGap(18, 18, 18)
														.addComponent(recibePublicidadModificarEstandarComboBox,
																GroupLayout.PREFERRED_SIZE, 317,
																GroupLayout.PREFERRED_SIZE)))));
		modificarEstandarPanelLayout.setVerticalGroup(modificarEstandarPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(modificarEstandarPanelLayout.createSequentialGroup().addContainerGap()
						.addGroup(modificarEstandarPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(idModificacionEstandarTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(idModificacionEstandarLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(modificarEstandarPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(nombreModificacionEstandarTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(nombreModificacionEstandarLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(modificarEstandarPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(apellidoModificacionEstandarTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(apellidoModificacionEstandarLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(modificarEstandarPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(direccionModificacionEstandarTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(direccionModificacionEstandarLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(modificarEstandarPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(correoModificacionEstandarTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(correoModificacionEstandarLabel))
						.addGroup(modificarEstandarPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(recibePublicidadModificarEstandarComboBox, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(recibePublicidadModificarLabel))
						.addGap(56, 56, 56)
						.addGroup(modificarEstandarPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(aceptarModificarEstandarButton)
								.addComponent(cancelarModificarEstandarButton))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
	}

	private void inicializarComponentesPanelAltaVip() {
		nombreAltaVipLabel = new JLabel();
		nombreAltaVipLabel = new JLabel();
		nombreAltaVipTextField = new JTextField();
		apellidoAltaVipLabel = new JLabel();
		apellidoAltaVipTextField = new JTextField();
		direccionAltaVipLabel = new JLabel();
		direccionAltaVipTextField = new JTextField();
		correoAltaVipLabel = new JLabel();
		correoAltaVipTextField = new JTextField();
		aceptarAltaVipButton = new JButton();
		cancelarAltaVipButton = new JButton();
		descuentoAltaVipLabel = new JLabel();
		descuentoAltaVipTextField = new JTextField();

		nombreAltaVipLabel.setText("Nombre:");
		apellidoAltaVipLabel.setText("Apellido:");
		direccionAltaVipLabel.setText("Direccion:");
		correoAltaVipLabel.setText("Correo:");
		descuentoAltaVipLabel.setText("Descuento (%):");
		aceptarAltaVipButton.setText("Dar de alta");
		aceptarAltaVipButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				altaClienteVipButtonActionPerformed();
			}
		});

		cancelarAltaVipButton.setText("Cancelar");
		cancelarAltaVipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarAltaClienteVipButtonActionPerformed();
			}
		});

		GroupLayout altaVipPanelLayout = new GroupLayout(altaVipPanel);
		altaVipPanel.setLayout(altaVipPanelLayout);
		altaVipPanelLayout.setHorizontalGroup(altaVipPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, altaVipPanelLayout.createSequentialGroup()
						.addGap(0, 205, Short.MAX_VALUE)
						.addGroup(altaVipPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
								GroupLayout.Alignment.TRAILING,
								altaVipPanelLayout.createSequentialGroup().addComponent(nombreAltaVipLabel)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(nombreAltaVipTextField, GroupLayout.PREFERRED_SIZE, 317,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(GroupLayout.Alignment.TRAILING, altaVipPanelLayout.createSequentialGroup()
										.addGroup(altaVipPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
												.addComponent(direccionAltaVipLabel).addComponent(apellidoAltaVipLabel))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(altaVipPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(apellidoAltaVipTextField, GroupLayout.PREFERRED_SIZE, 317,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(direccionAltaVipTextField, GroupLayout.Alignment.TRAILING,
														GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap())
				.addGroup(GroupLayout.Alignment.TRAILING,
						altaVipPanelLayout.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(altaVipPanelLayout
										.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
												GroupLayout.Alignment.TRAILING, altaVipPanelLayout
														.createSequentialGroup().addComponent(aceptarAltaVipButton)
														.addGap(18, 18,
																18)
														.addComponent(cancelarAltaVipButton).addGap(83, 83, 83))
										.addGroup(GroupLayout.Alignment.TRAILING,
												altaVipPanelLayout.createSequentialGroup()
														.addGroup(altaVipPanelLayout
																.createParallelGroup(GroupLayout.Alignment.TRAILING)
																.addComponent(descuentoAltaVipLabel)
																.addComponent(correoAltaVipLabel))
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
														.addGroup(altaVipPanelLayout
																.createParallelGroup(GroupLayout.Alignment.LEADING,
																		false)
																.addComponent(correoAltaVipTextField,
																		GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
																.addComponent(descuentoAltaVipTextField))
														.addContainerGap()))));
		altaVipPanelLayout.setVerticalGroup(altaVipPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(altaVipPanelLayout.createSequentialGroup()
						.addGroup(altaVipPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(nombreAltaVipTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(nombreAltaVipLabel))
						.addGap(2, 2, 2)
						.addGroup(altaVipPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(apellidoAltaVipTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(apellidoAltaVipLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(altaVipPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(direccionAltaVipLabel).addComponent(direccionAltaVipTextField,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(altaVipPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(correoAltaVipTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(correoAltaVipLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(altaVipPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(descuentoAltaVipTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(descuentoAltaVipLabel))
						.addGap(55, 55, 55)
						.addGroup(altaVipPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(aceptarAltaVipButton).addComponent(cancelarAltaVipButton))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

	}

	private void inicializarComponentesPanelAltaEstandar() {
		nombreAltaEstandarLabel = new JLabel();
		nombreAltaEstandarTextField = new JTextField();
		apellidoAltaEstandarLabel = new JLabel();
		apellidoAltaEstandarTextField = new JTextField();
		direccionAltaEstandarLabel = new JLabel();
		direccionAltaEstandarTextField = new JTextField();
		correoAltaEstandarLabel = new JLabel();
		correoAltaEstandarTextField = new JTextField();
		aceptarAltaEstandarButton = new JButton();
		cancelarAltaEstandarButton = new JButton();
		recibePublicidadAltaEstandarComboBox = new JComboBox<>();
		recibePublicidadAltaEstandarLabel = new JLabel();

		nombreAltaEstandarLabel.setText("Nombre:");
		apellidoAltaEstandarLabel.setText("Apellido:");
		direccionAltaEstandarLabel.setText("Direccion:");
		correoAltaEstandarLabel.setText("Correo:");
		recibePublicidadAltaEstandarLabel.setText("Recibe publicidad: ");

		recibePublicidadAltaEstandarComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Si", "No" }));

		aceptarAltaEstandarButton.setText("Dar de alta");
		aceptarAltaEstandarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				altaClienteEstandarButtonActionPerformed();
			}
		});

		cancelarAltaEstandarButton.setText("Cancelar");
		cancelarAltaEstandarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarAltaClienteEstandarButtonActionPerformed();
			}
		});

		GroupLayout altaEstandarPanelLayout = new GroupLayout(altaEstandarPanel);
		altaEstandarPanel.setLayout(altaEstandarPanelLayout);
		altaEstandarPanelLayout.setHorizontalGroup(altaEstandarPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, altaEstandarPanelLayout.createSequentialGroup()
						.addGap(0, 205, Short.MAX_VALUE)
						.addGroup(altaEstandarPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
								GroupLayout.Alignment.TRAILING,
								altaEstandarPanelLayout.createSequentialGroup().addComponent(nombreAltaEstandarLabel)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(nombreAltaEstandarTextField, GroupLayout.PREFERRED_SIZE, 317,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(GroupLayout.Alignment.TRAILING, altaEstandarPanelLayout
										.createSequentialGroup()
										.addGroup(altaEstandarPanelLayout
												.createParallelGroup(GroupLayout.Alignment.TRAILING)
												.addComponent(direccionAltaEstandarLabel)
												.addComponent(apellidoAltaEstandarLabel))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(altaEstandarPanelLayout
												.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(apellidoAltaEstandarTextField, GroupLayout.PREFERRED_SIZE,
														317, GroupLayout.PREFERRED_SIZE)
												.addComponent(direccionAltaEstandarTextField,
														GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 317,
														GroupLayout.PREFERRED_SIZE))))
						.addContainerGap())
				.addGroup(GroupLayout.Alignment.TRAILING, altaEstandarPanelLayout.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(altaEstandarPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
								GroupLayout.Alignment.TRAILING,
								altaEstandarPanelLayout.createSequentialGroup().addComponent(aceptarAltaEstandarButton)
										.addGap(18, 18, 18).addComponent(cancelarAltaEstandarButton).addGap(83, 83, 83))
								.addGroup(GroupLayout.Alignment.TRAILING,
										altaEstandarPanelLayout.createSequentialGroup()
												.addComponent(correoAltaEstandarLabel)
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(correoAltaEstandarTextField, GroupLayout.PREFERRED_SIZE,
														317, GroupLayout.PREFERRED_SIZE)
												.addContainerGap())
								.addGroup(GroupLayout.Alignment.TRAILING,
										altaEstandarPanelLayout.createSequentialGroup()
												.addComponent(recibePublicidadAltaEstandarLabel)
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(recibePublicidadAltaEstandarComboBox,
														GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
												.addContainerGap()))));
		altaEstandarPanelLayout.setVerticalGroup(altaEstandarPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(altaEstandarPanelLayout.createSequentialGroup()
						.addGroup(altaEstandarPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(nombreAltaEstandarTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(nombreAltaEstandarLabel))
						.addGap(2, 2, 2)
						.addGroup(altaEstandarPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(apellidoAltaEstandarTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(apellidoAltaEstandarLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(altaEstandarPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(direccionAltaEstandarLabel)
								.addComponent(direccionAltaEstandarTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(altaEstandarPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(correoAltaEstandarTextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(correoAltaEstandarLabel))
						.addGroup(altaEstandarPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(recibePublicidadAltaEstandarComboBox, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(recibePublicidadAltaEstandarLabel))
						.addGap(83, 83, 83)
						.addGroup(altaEstandarPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(aceptarAltaEstandarButton).addComponent(cancelarAltaEstandarButton))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

	}

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
				bajaClienteButtonActionPerformed();
			}
		});

		cancelarBajaButton.setText("Cancelar");
		cancelarBajaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarBajaClienteButtonActionPerformed();
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
				mostrarUnoClienteButtonActionPerformed();
			}
		});

		cancelarMostrarUnoButton.setText("Cancelar");
		cancelarMostrarUnoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarMostrarUnoClienteButtonActionPerformed();
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

	private void inicializarComponentesPanelSeleccionTipoClienteAlta() {

		eleccionTipoClienteEnAltaLabel = new JLabel();
		clienteEstandarAltaButton = new JButton();
		clienteVipAltaButton = new JButton();
		cancelarSeleccionTipoDeClienteEnAltaButton = new JButton();

		eleccionTipoClienteEnAltaLabel.setText("Seleccione el tipo de cliente: ");

		clienteEstandarAltaButton.setText("Estandar");
		clienteEstandarAltaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Contexto contexto = new Contexto(Evento.abrirAltaClienteEstandar, null);
				ControladorAplicacion.obtenerInstancia().accion(contexto);

			}

		});

		clienteVipAltaButton.setText("VIP");
		clienteVipAltaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Contexto contexto = new Contexto(Evento.abrirAltaClienteVip, null);
				ControladorAplicacion.obtenerInstancia().accion(contexto);

			}

		});

		cancelarSeleccionTipoDeClienteEnAltaButton.setText("Cancelar");
		cancelarSeleccionTipoDeClienteEnAltaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (insercionDatosPanel.getLayout());
				cl.first(insercionDatosPanel);
			}

		});

		GroupLayout panelSeleccionTipoClienteEnAltaLayout = new GroupLayout(seleccionTipoClienteEnAltaPanel);
		seleccionTipoClienteEnAltaPanel.setLayout(panelSeleccionTipoClienteEnAltaLayout);
		panelSeleccionTipoClienteEnAltaLayout.setHorizontalGroup(panelSeleccionTipoClienteEnAltaLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelSeleccionTipoClienteEnAltaLayout.createSequentialGroup().addGap(245, 245, 245)
						.addComponent(eleccionTipoClienteEnAltaLabel).addContainerGap(245, Short.MAX_VALUE))
				.addGroup(GroupLayout.Alignment.TRAILING, panelSeleccionTipoClienteEnAltaLayout.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(clienteVipAltaButton, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
						.addGap(61, 61, 61)
						.addComponent(clienteEstandarAltaButton, GroupLayout.PREFERRED_SIZE, 136,
								GroupLayout.PREFERRED_SIZE)
						.addGap(61, 61, 61)
						.addComponent(cancelarSeleccionTipoDeClienteEnAltaButton, GroupLayout.PREFERRED_SIZE, 136,
								GroupLayout.PREFERRED_SIZE)
						.addGap(65, 65, 65))
				.addGroup(panelSeleccionTipoClienteEnAltaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(panelSeleccionTipoClienteEnAltaLayout.createSequentialGroup().addGap(59, 59, 59)

								.addContainerGap(478, Short.MAX_VALUE))));
		panelSeleccionTipoClienteEnAltaLayout.setVerticalGroup(panelSeleccionTipoClienteEnAltaLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelSeleccionTipoClienteEnAltaLayout.createSequentialGroup().addGap(41, 41, 41)
						.addComponent(eleccionTipoClienteEnAltaLabel).addGap(44, 44, 44)
						.addGroup(panelSeleccionTipoClienteEnAltaLayout
								.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(clienteVipAltaButton, GroupLayout.PREFERRED_SIZE, 81,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(clienteEstandarAltaButton, GroupLayout.PREFERRED_SIZE, 81,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(cancelarSeleccionTipoDeClienteEnAltaButton, GroupLayout.PREFERRED_SIZE,
										81, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(51, Short.MAX_VALUE))
				.addGroup(panelSeleccionTipoClienteEnAltaLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING,
								panelSeleccionTipoClienteEnAltaLayout.createSequentialGroup()
										.addContainerGap(101, Short.MAX_VALUE)

										.addGap(51, 51, 51))));
	}

	private void inicializarComponentesPanelSeleccionTipoClienteModificacion() {

		eleccionTipoClienteEnModificarLabel = new JLabel();
		clienteEstandarEnModificarButton = new JButton();
		clienteVipEnModificarButton = new JButton();
		cancelarSeleccionTipoDeClienteEnModificarButton = new JButton();

		eleccionTipoClienteEnModificarLabel.setText("Seleccione el tipo de cliente: ");

		clienteEstandarEnModificarButton.setText("Estandar");
		clienteEstandarEnModificarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Contexto contexto = new Contexto(Evento.abrirActualizarClienteEstandar, null);
				ControladorAplicacion.obtenerInstancia().accion(contexto);
			}

		});

		clienteVipEnModificarButton.setText("VIP");
		clienteVipEnModificarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Contexto contexto = new Contexto(Evento.abrirActualizarClienteVip, null);
				ControladorAplicacion.obtenerInstancia().accion(contexto);

			}

		});

		cancelarSeleccionTipoDeClienteEnModificarButton.setText("Cancelar");
		cancelarSeleccionTipoDeClienteEnModificarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (insercionDatosPanel.getLayout());
				cl.first(insercionDatosPanel);
			}

		});

		GroupLayout panelSeleccionTipoClienteEnModificarLayout = new GroupLayout(seleccionTipoClienteEnModificarPanel);
		seleccionTipoClienteEnModificarPanel.setLayout(panelSeleccionTipoClienteEnModificarLayout);
		panelSeleccionTipoClienteEnModificarLayout.setHorizontalGroup(panelSeleccionTipoClienteEnModificarLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelSeleccionTipoClienteEnModificarLayout.createSequentialGroup().addGap(245, 245, 245)
						.addComponent(eleccionTipoClienteEnModificarLabel).addContainerGap(245, Short.MAX_VALUE))
				.addGroup(GroupLayout.Alignment.TRAILING,
						panelSeleccionTipoClienteEnModificarLayout.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(clienteVipEnModificarButton, GroupLayout.PREFERRED_SIZE, 136,
										GroupLayout.PREFERRED_SIZE)
								.addGap(61, 61, 61)
								.addComponent(clienteEstandarEnModificarButton, GroupLayout.PREFERRED_SIZE, 136,
										GroupLayout.PREFERRED_SIZE)
								.addGap(61, 61, 61)
								.addComponent(cancelarSeleccionTipoDeClienteEnModificarButton,
										GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
								.addGap(65, 65, 65))
				.addGroup(panelSeleccionTipoClienteEnModificarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(panelSeleccionTipoClienteEnModificarLayout.createSequentialGroup().addGap(59, 59, 59)

								.addContainerGap(478, Short.MAX_VALUE))));
		panelSeleccionTipoClienteEnModificarLayout.setVerticalGroup(
				panelSeleccionTipoClienteEnModificarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(panelSeleccionTipoClienteEnModificarLayout.createSequentialGroup().addGap(41, 41, 41)
								.addComponent(eleccionTipoClienteEnModificarLabel).addGap(44, 44, 44)
								.addGroup(panelSeleccionTipoClienteEnModificarLayout
										.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(clienteVipEnModificarButton, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(clienteEstandarEnModificarButton, GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(cancelarSeleccionTipoDeClienteEnModificarButton,
												GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(51, Short.MAX_VALUE))
						.addGroup(panelSeleccionTipoClienteEnModificarLayout
								.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(GroupLayout.Alignment.TRAILING,
										panelSeleccionTipoClienteEnModificarLayout.createSequentialGroup()
												.addContainerGap(101, Short.MAX_VALUE)

												.addGap(51, 51, 51))));
	}

	private JButton actualizarButton;
	private JButton altaButton;
	private JButton bajaButton;
	private JPanel insercionDatosPanel;
	private JScrollPane jScrollPane5;
	private JScrollPane jScrollPane6;
	private JTable clientesTable;
	private JButton mostrarTodosButton;
	private JButton mostrarUnoButton;
	private JPanel panelCasosDeUso;
	private JPanel panelResultados;
	private JPanel panelTabla;
	private JTextArea resultadosTextArea;
	private Object[][] datosTabla;
	private JButton menuPrincipalButton;

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

	// Variables del panel alta estandar
	private JPanel altaEstandarPanel;
	private JButton aceptarAltaEstandarButton;
	private JLabel apellidoAltaEstandarLabel;
	private JTextField apellidoAltaEstandarTextField;
	private JButton cancelarAltaEstandarButton;
	private JLabel correoAltaEstandarLabel;
	private JTextField correoAltaEstandarTextField;
	private JLabel direccionAltaEstandarLabel;
	private JTextField direccionAltaEstandarTextField;
	private JLabel nombreAltaEstandarLabel;
	private JTextField nombreAltaEstandarTextField;
	private JComboBox<String> recibePublicidadAltaEstandarComboBox;
	private JLabel recibePublicidadAltaEstandarLabel;

	// Variables del panel alta vip
	private JPanel altaVipPanel;
	private JButton aceptarAltaVipButton;
	private JLabel apellidoAltaVipLabel;
	private JTextField apellidoAltaVipTextField;
	private JButton cancelarAltaVipButton;
	private JLabel correoAltaVipLabel;
	private JTextField correoAltaVipTextField;
	private JLabel descuentoAltaVipLabel;
	private JTextField descuentoAltaVipTextField;
	private JLabel direccionAltaVipLabel;
	private JTextField direccionAltaVipTextField;
	private JLabel nombreAltaVipLabel;
	private JTextField nombreAltaVipTextField;

	// Variables del panel de seleccion de tipo de cliente en el alta
	private JPanel seleccionTipoClienteEnAltaPanel;
	private JButton cancelarSeleccionTipoDeClienteEnAltaButton;
	private JButton clienteEstandarAltaButton;
	private JButton clienteVipAltaButton;
	private JLabel eleccionTipoClienteEnAltaLabel;

	// Variables del panel modificarVip
	private JPanel modificarVipPanel;
	private JButton aceptarModificarVipButton;
	private JLabel apellidoModificacionVipLabel;
	private JTextField apellidoModificacionVipTextField;
	private JButton cancelarModificarVipButton;
	private JLabel correoModificacionVipLabel;
	private JTextField correoModificacionVipTextField;

	private JLabel direccionModificacionVipLabel;
	private JTextField direccionModificacionVipTextField;
	private JLabel idModificacionVipLabel;
	private JTextField idModificacionVipTextField;
	private JLabel nombreModificacionVipLabel;
	private JTextField nombreModificacionVipTextField;

	// Variables del panel modificarEstandar
	private JPanel modificarEstandarPanel;
	private JButton aceptarModificarEstandarButton;
	private JLabel apellidoModificacionEstandarLabel;
	private JTextField apellidoModificacionEstandarTextField;
	private JButton cancelarModificarEstandarButton;
	private JLabel correoModificacionEstandarLabel;
	private JTextField correoModificacionEstandarTextField;
	private JLabel direccionModificacionEstandarLabel;
	private JTextField direccionModificacionEstandarTextField;
	private JLabel idModificacionEstandarLabel;
	private JTextField idModificacionEstandarTextField;
	private JLabel nombreModificacionEstandarLabel;
	private JTextField nombreModificacionEstandarTextField;
	private JComboBox<String> recibePublicidadModificarEstandarComboBox;
	private JLabel recibePublicidadModificarLabel;

	// Variables del panel de seleccion de tipo de cliente en la modificacion
	private JPanel seleccionTipoClienteEnModificarPanel;
	private JButton cancelarSeleccionTipoDeClienteEnModificarButton;
	private JButton clienteEstandarEnModificarButton;
	private JButton clienteVipEnModificarButton;
	private JLabel eleccionTipoClienteEnModificarLabel;

}
