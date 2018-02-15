package presentacion.departamento;


import java.awt.CardLayout;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


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

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import negocio.departamento.Departamento;
import negocio.empleado.Empleado;


import presentacion.GUI;
import presentacion.controlador.Contexto;
import presentacion.controlador.ControladorAplicacionJPA;
import presentacion.eventos.Evento;

public class GUIMenuDepartamento extends JFrame implements GUI {

	private static final long serialVersionUID = 1L;

	// Action Performed Menu Botones (Eventos de Redireccion entre vistas)

	private void altaButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaAltaDepartamento, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}

	private void actualizarButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaActualizarDepartamento, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}
	
	private void bajaButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaBajaDepartamento, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}

	private void mostrarUnoButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaMostrarUnDepartamento, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}
	
	private void calcularSueldoNetoButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaCalcularSueldoNetoDepartamento, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}
	
	private void menuPrincipalButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirMenuPrincipal, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		this.dispose();
	}
	
	
	// Actualizar departamento
	
	private void aceptarActualizarButtonActionPerformed() {
		try {
			Integer id = Integer.parseInt(idActualizarJTextField.getText());
			String nombre = JTextFieldNombreActualizar.getText();
			String abreviatura = JTextFieldAbreviaturaActualizar.getText();

			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaCreacion = dateFormatter.parse(fechaActualizarJTextField.getText()); 
			
			
			if (nombre.isEmpty() || abreviatura.isEmpty()) {
				throw new Exception();
			}
			
			Departamento departamento = new Departamento(id, nombre, abreviatura, fechaCreacion);
			Contexto contexto = new Contexto(Evento.actualizarDepartamento, departamento);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
			
			JTextFieldNombreActualizar.setText("");
			JTextFieldAbreviaturaActualizar.setText("");
			idActualizarJTextField.setText("");
			fechaActualizarJTextField.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}
	
	private void cancelarActualizarButtonActionPerformed() {
		JTextFieldNombreActualizar.setText("");
		JTextFieldAbreviaturaActualizar.setText("");
		idActualizarJTextField.setText("");
		fechaActualizarJTextField.setText("");
		
		CardLayout cl = (CardLayout) (panelContenidoDepartamentos.getLayout());
		cl.first(panelContenidoDepartamentos);
	}

	
	// Alta Departamento

	private void aceptarAltaButtonActionPerformed() {
		try {
			String nombre = JTextFieldNombreAlta.getText();
			String abreviatura = JTextFieldAbreviaturaAlta.getText();
			
			if (nombre.isEmpty() || abreviatura.isEmpty()) {
				throw new Exception();
			}
			
			Departamento departamento = new Departamento(nombre, abreviatura);
			Contexto contexto = new Contexto(Evento.altaDepartamento, departamento);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
			JTextFieldNombreAlta.setText("");
			JTextFieldAbreviaturaAlta.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarAltaButtonActionPerformed() {
		JTextFieldNombreAlta.setText("");
		JTextFieldAbreviaturaAlta.setText("");
		CardLayout cl = (CardLayout) (panelContenidoDepartamentos.getLayout());
		cl.first(panelContenidoDepartamentos);
	}
	
	//Baja departamento
	
	
	private void aceptarBajaButtonActionPerformed() {
		try {
			Integer id = Integer.parseInt(textIdDepartamentoBaja.getText());

			if (id < 0)
				throw new Exception();

			Contexto contexto = new Contexto(Evento.bajaDepartamento, id);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
			textIdDepartamentoBaja.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}
	
	private void cancelarBajaButtonActionPerformed() {
		textIdDepartamentoBaja.setText("");
		CardLayout cl = (CardLayout) (panelContenidoDepartamentos.getLayout());
		cl.first(panelContenidoDepartamentos);
	}
	
	//Calcular sueldo neto
	
	private void aceptarCalcularSueldoButtonActionPerformed() {
		try {
			Integer id = Integer.parseInt(textIdDepartamentoCalcularSueldo.getText());

			if (id < 0)
				throw new Exception();

			Contexto contexto = new Contexto(Evento.calcularSueldoNetoDepartamento, id);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
			textIdDepartamentoCalcularSueldo.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}
	
	private void cancelarCalcularSueldoButtonActionPerformed() {
		textIdDepartamentoBaja.setText("");
		CardLayout cl = (CardLayout) (panelContenidoDepartamentos.getLayout());
		cl.first(panelContenidoDepartamentos);
	}

	//Mostrar un departamento

	private void aceptarMostrarUnoButtonActionPerformed() {
		try {
			Integer id = Integer.parseInt(textIdDepartamentoMostrarUno.getText());

			if (id < 0)
				throw new Exception();

			Contexto contexto = new Contexto(Evento.mostrarUnDepartamento, id);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
			textIdDepartamentoMostrarUno.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarMostrarUnaButtonActionPerformed() {
		textIdDepartamentoMostrarUno.setText("");
		CardLayout cl = (CardLayout) (panelContenidoDepartamentos.getLayout());
		cl.first(panelContenidoDepartamentos);
	}

	private void volverDeTablaEmpleadosAMostrarUnoButtonActionPerformed() {
		CardLayout cl = (CardLayout) (panelContenidoDepartamentos.getLayout());
		cl.show(panelContenidoDepartamentos, "Mostrar");
	}
	
	
	//Mostrar todos
	
	private void mostrarTodosButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.mostrarTodosDepartamentos, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}
	
	@Override
	public void actualizar(Contexto contexto) {
		switch (contexto.getEvento()) {
		
		case abrirVistaAltaDepartamento:
			CardLayout cl = (CardLayout) (panelContenidoDepartamentos.getLayout());
			cl.show(panelContenidoDepartamentos, "Alta");
			break;
			
		case abrirVistaActualizarDepartamento:
			CardLayout cl1 = (CardLayout) (panelContenidoDepartamentos.getLayout());
			cl1.show(panelContenidoDepartamentos, "Actualizar");
			break;
			
		case abrirVistaBajaDepartamento:
			CardLayout cl3 = (CardLayout) (panelContenidoDepartamentos.getLayout());
			cl3.show(panelContenidoDepartamentos, "Baja");
			break;
			
		case abrirVistaCalcularSueldoNetoDepartamento:
			CardLayout cl6 = (CardLayout) (panelContenidoDepartamentos.getLayout());
			cl6.show(panelContenidoDepartamentos, "CalcularSueldo");
			break;
			
		case abrirVistaMostrarUnDepartamento:
			CardLayout cl2 = (CardLayout) (panelContenidoDepartamentos.getLayout());
			cl2.show(panelContenidoDepartamentos, "Mostrar");
			break;
			
			
			
		case actualizarDepartamentoOK:
			this.textAreaDepartamento.append("->Actualizacion correcta del departamento con id " + ((Departamento) contexto.getDatos()).getId() + "\n\n");
			break;
			
		case altaDepartamentoOK:
			this.textAreaDepartamento.append("->Alta correcta del nuevo departamento con id " + ((Departamento) contexto.getDatos()).getId() + "\n\n");
			break;
			
		case bajaDepartamentoOK:
			this.textAreaDepartamento.append("->El departamento con id " + ((Integer) contexto.getDatos()) + " se ha dado de baja.\n\n");
			break;
			
		case calcularSueldoNetoDepartamentoOK:
			DecimalFormat formatter = new DecimalFormat("0.00");
			Double sueldoNeto = ((Double) contexto.getDatos());
			this.textAreaDepartamento.append("->El sueldo neto del departamento es " + formatter.format((Number) sueldoNeto) + " euros.\n\n");
			break;
			
		case errorActualizarDepartamentoIdNoExistente:
			this.textAreaDepartamento.append("->ERROR: no existe ningun departamento con id " + ((Departamento) contexto.getDatos()).getId() + " en el sistema.\n\n");
			break;
			
			
		case errorActualizarDepartamentoNombreYaExistente:
			this.textAreaDepartamento.append("->ERROR: no se ha podido actualizar el departamento debido a que " +
					"ya existe un departamento con nombre " + ((Departamento) contexto.getDatos()).getNombre() + " en el sistema.\n\n");
			break;
			
		case errorActualizarDepartamentoReactivacion:
			this.textAreaDepartamento.append("->El departamento con id " + ((Departamento) contexto.getDatos()).getId() +
					" estaba inactivo, se ha reactivado y se han actualizado sus datos.\n\n");
			break;
			
		case errorAltaDepartamentoNombreYaExistente:
			this.textAreaDepartamento.append("->ERROR: no se ha podido dar de alta el departamento porque ya existe en" +
					" el sistema un departamento con nombre " + ((Departamento) contexto.getDatos()).getNombre() + "\n\n");
			break;
			
		case errorActualizarDepartamentoOptimisticLockException:
			this.textAreaDepartamento.append("->ERROR: el departamento existe en el sistema pero no se ha podido completar la " +
					"operacion debido a que otro usuario estaba modificando el mismo departamento.\n\n");
			break;
			
		case errorAltaDepartamentoReactivacion:
			this.textAreaDepartamento.append("->El departamento ya existe en el sistema pero estaba inactivo, se ha reactivado y se han actualizado sus datos.\n\n");
			break;
			
		case errorAltaDepartamentoOptimisticLockException:
			this.textAreaDepartamento.append("->ERROR: el departamento ya existe en el sistema pero no se ha podido completar la " +
					"operacion debido a que otro usuario estaba modificando el mismo departamento.\n\n");
			break;
			
		case errorArgumentos:
			textAreaDepartamento.append("->Error al introducir argumentos\n\n");
			break;
			
		case errorBajaDepartamentoIdNoExistente:
			this.textAreaDepartamento.append("->ERROR: no existe ningun departamento con id " + ((Integer) contexto.getDatos()) + " en el sistema.\n\n");
			break;
			
		case errorBajaDepartamentoEmpleadosActivos:
			this.textAreaDepartamento.append("->ERROR: no se ha podido dar de baja el departamento porque tiene empleados activos.\n\n");
			break;
			
		case errorBajaDepartamentoYaDadoDeBaja:
			this.textAreaDepartamento.append("->ERROR: el departamento con id " + ((Integer) contexto.getDatos()) + " ya estaba dado de baja.\n\n");
			break;
			
		case errorBajaDepartamentoOptimisticLockException:
			this.textAreaDepartamento.append("->ERROR: el departamento existe en el sistema pero no se ha podido completar la " +
					"operacion debido a que otro usuario estaba modificando el mismo departamento.\n\n");
			break;

		case errorCalcularSueldoNetoDepartamentoIdNoExistente:
			this.textAreaDepartamento.append("->ERROR: no existe ningun departamento con id " + ((Integer) contexto.getDatos()) + " en el sistema.\n\n");
			break;
			
		case errorCalcularSueldoNetoDepartamentoOptimisticLockException:
			this.textAreaDepartamento.append("->ERROR: el departamento existe en el sistema pero no se ha podido completar la " +
					"operacion debido a que otro usuario estaba modificando el mismo departamento o a alguno de sus empleados.\n\n");
			break;
			
		case errorCalcularSueldoNetoDepartamentoDepartamentoInactivo:
			this.textAreaDepartamento.append("->ERROR: no se ha podido calcular el sueldo neto del "
					+ "departamento porque está inactivo.\n\n");
			break;
			
		case errorConexionBBDD:
			this.textAreaDepartamento.append("->ERROR: no ha sido posible conectarse a la BBDD.\n\n");
			break;
			
		case errorMostrarTodosDepartamentosNoExisteNinguno:
			vaciarTablaDepartamentos();
			this.textAreaDepartamento.append("->No existe ningun departamento en el sistema.\n\n");
			break;
			
		case errorMostrarUnDepartamentoIdNoExistente:
			vaciarTablaDepartamentos();
			this.textAreaDepartamento.append("->No existe ningun departamento con id " + ((Integer) contexto.getDatos()) + " en el sistema.\n\n");
			break;
			
		case mostrarTodosDepartamentosOK:
			rellenarTablaConDepartamentos((List<Departamento>) contexto.getDatos());
			break;
			
		case mostrarUnDepartamentoOK:
			rellenarTablaConUnDepartamento((Departamento) contexto.getDatos());
			rellenarTablaEmpleados(((Departamento) contexto.getDatos()).getEmpleados());
			CardLayout cl9 = (CardLayout) (panelContenidoDepartamentos.getLayout());
			cl9.show(panelContenidoDepartamentos, "Empleados");
			break;

		default:
			break;
		}
	}

	// ---------------------------------

	private void rellenarTablaConUnDepartamento(Departamento departamento) {
		datosTabla = new Object[1][5];
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

		datosTabla[0][0] = departamento.getId();
		datosTabla[0][1] = departamento.getNombre();
		datosTabla[0][2] = departamento.getAbreviatura();
		datosTabla[0][3] = dateFormatter.format(departamento.getFechaCreacion());
		datosTabla[0][4] = departamento.getActivo();

		setTablaDepartamentosConfiguration();
	}

	private void rellenarTablaEmpleados(List<Empleado> empleados) {
		
		Collections.sort(empleados, new Comparator<Empleado>() {
			
			@Override
			public int compare(Empleado empleado1, Empleado empleado2) {
				if (empleado1.getId() < empleado2.getId()) {
					return -1;
				} else if (empleado1.getId() == empleado2.getId()) {
					return 0;
				} else {
					return 1;
				}
			}
		});
		
		datosTabla = new Object[empleados.size()][5];
		int i = 0;
		
		for (Empleado empleado : empleados) {
			datosTabla[i][0] = empleado.getId();
			datosTabla[i][1] = empleado.getDNI();
			datosTabla[i][2] = empleado.getNombre();
			datosTabla[i][3] = empleado.getTelefono();
			datosTabla[i][4] = empleado.getActivo();
			++i;
		}

		tablaEmpleados
				.setModel(new DefaultTableModel(datosTabla, new String[] { "ID", "DNI", "Nombre", "Telefono", "Activo" }) {

					private static final long serialVersionUID = 7482486286317006320L;

					Class[] types = new Class[] { Integer.class, String.class, String.class, String.class, Boolean.class };

					public Class getColumnClass(int columnIndex) {
						return types[columnIndex];
					}

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return false;
					}
				});

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaEmpleados.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tablaEmpleados.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tablaEmpleados.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tablaEmpleados.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	}

	private void rellenarTablaConDepartamentos(List<Departamento> departamentos) {
		datosTabla = new Object[departamentos.size()][5];
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		int i = 0;
		
		for (Departamento departamento : departamentos) {
			datosTabla[i][0] = departamento.getId();
			datosTabla[i][1] = departamento.getNombre();
			datosTabla[i][2] = departamento.getAbreviatura();
			datosTabla[i][3] = dateFormatter.format(departamento.getFechaCreacion());
			datosTabla[i][4] = departamento.getActivo();
			++i;
		}

		setTablaDepartamentosConfiguration();
	}

	public void setTablaDepartamentosConfiguration() {
		tablaDepartamentos.setModel(new DefaultTableModel(datosTabla, new String[] { "Id", "Nombre", "Abreviatura", "Fecha de creacion", "Activo" }) {

			private static final long serialVersionUID = -4826319740640786334L;

			Class[] types = new Class[] { Integer.class, String.class, String.class, Date.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaDepartamentos.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tablaDepartamentos.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tablaDepartamentos.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tablaDepartamentos.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tablaDepartamentos.setMaximumSize(new Dimension(300, 64));
		tablaDepartamentos.setMinimumSize(new Dimension(300, 64));
		tablaDepartamentos.setOpaque(false);
		tablaDepartamentos.getTableHeader().setReorderingAllowed(false);
		jScrollPane1.setViewportView(tablaDepartamentos);
	}

	private void vaciarTablaDepartamentos() {
		DefaultTableModel model = (DefaultTableModel) tablaDepartamentos.getModel();
		model.setRowCount(0);
	}

	public GUIMenuDepartamento() {
		initGUI();
	}
	
	@SuppressWarnings({ "unchecked", "serial" })
	private void initGUI() {
		GridBagConstraints gridBagConstraints;

		jScrollPane1 = new JScrollPane();
		tablaDepartamentos = new JTable();
		tablaEmpleados = new JTable();
		
		jScrollPane2 = new JScrollPane();
		textAreaDepartamento = new JTextArea();
		panelBotonesDepartamento = new JPanel();
		botonAltaDepartamento = new JButton();
		botonBajaDepartamento = new JButton();
		botonActualizarDepartamento = new JButton();
		botonMostrarUnDepartamento = new JButton();
		botonMostrarTodosDepartamentos = new JButton();
		botonCalcularSueldoNeto = new JButton();
		botonVolver = new JButton();
		panelContenidoDepartamentos = new JPanel();

		altaPanel = new JPanel();
		altaPanel.setBorder(BorderFactory.createTitledBorder("Alta de un nuevo departamento"));
		inicializarComponentesPanelAlta();
		
		actualizarPanel = new JPanel();
		actualizarPanel.setBorder(BorderFactory.createTitledBorder("Actualizar un departamento"));
		inicializarComponentesPanelActualizar();
		
		bajaPanel = new JPanel();
		bajaPanel.setBorder(BorderFactory.createTitledBorder("Dar de baja un departamento"));
		inicializarComponentesPanelBaja();

		mostrarUnoPanel = new JPanel();
		mostrarUnoPanel.setBorder(BorderFactory.createTitledBorder("Mostrar un departamento"));
		inicializarComponentesPanelMostrarUno();
		
		calcularSueldoPanel = new JPanel();
		calcularSueldoPanel.setBorder(BorderFactory.createTitledBorder("Caclular el sueldo neto de un departamento"));
		inicializarComponentesPanelBCalcularSueldo();



		setTablaDepartamentosConfiguration();
		
		tablaEmpleadosPanel = new JPanel();
		tablaEmpleadosPanel.setBorder(BorderFactory.createTitledBorder("Empleados"));
		inicializarComponentesPanelEmpleados();

		textAreaDepartamento.setColumns(20);
		textAreaDepartamento.setRows(5);
		textAreaDepartamento.setMaximumSize(new Dimension(164, 94));
		textAreaDepartamento.setMinimumSize(new Dimension(164, 94));
		jScrollPane2.setViewportView(textAreaDepartamento);

		panelBotonesDepartamento.setMaximumSize(new Dimension(411, 242));
		panelBotonesDepartamento.setMinimumSize(new Dimension(411, 242));
		panelBotonesDepartamento.setOpaque(false);
		panelBotonesDepartamento.setPreferredSize(new Dimension(411, 242));
		panelBotonesDepartamento.setLayout(new GridBagLayout());

		botonAltaDepartamento.setText("Alta");
		botonAltaDepartamento.setPreferredSize(new Dimension(150, 45));
		botonAltaDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				altaButtonActionPerformed();
			}
		});
		panelBotonesDepartamento.add(botonAltaDepartamento, new GridBagConstraints());

		
		botonBajaDepartamento.setText("Baja");
		botonBajaDepartamento.setPreferredSize(new Dimension(150, 45));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		botonBajaDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bajaButtonActionPerformed();
			}
		});
		
		panelBotonesDepartamento.add(botonBajaDepartamento, gridBagConstraints);
		
		botonActualizarDepartamento.setText("Actualizar");
		botonActualizarDepartamento.setPreferredSize(new Dimension(150, 45));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		botonActualizarDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				actualizarButtonActionPerformed();
			}
		});
		panelBotonesDepartamento.add(botonActualizarDepartamento, gridBagConstraints);

		botonMostrarUnDepartamento.setText("Mostrar uno");
		botonMostrarUnDepartamento.setPreferredSize(new Dimension(150, 45));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		botonMostrarUnDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mostrarUnoButtonActionPerformed();
			}
		});
		panelBotonesDepartamento.add(botonMostrarUnDepartamento, gridBagConstraints);

		botonMostrarTodosDepartamentos.setText("Mostrar Todos");
		botonMostrarTodosDepartamentos.setPreferredSize(new Dimension(150, 45));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		botonMostrarTodosDepartamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mostrarTodosButtonActionPerformed();
			}
		});
		panelBotonesDepartamento.add(botonMostrarTodosDepartamentos, gridBagConstraints);
		
		botonCalcularSueldoNeto.setText("Calcular sueldo neto");
		botonCalcularSueldoNeto.setPreferredSize(new Dimension(150, 45));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		botonCalcularSueldoNeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				calcularSueldoNetoButtonActionPerformed();
			}
		});
		panelBotonesDepartamento.add(botonCalcularSueldoNeto, gridBagConstraints);

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
		panelBotonesDepartamento.add(botonVolver, gridBagConstraints);

		panelContenidoDepartamentos.setMaximumSize(new Dimension(411, 242));
		panelContenidoDepartamentos.setMinimumSize(new Dimension(411, 242));
		panelContenidoDepartamentos.setPreferredSize(new Dimension(411, 242));
		panelContenidoDepartamentos.setLayout(new CardLayout());

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addComponent(jScrollPane1).addGap(18, 18, 18)
										.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 279,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(panelBotonesDepartamento, GroupLayout.PREFERRED_SIZE, 415,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
										.addComponent(panelContenidoDepartamentos, GroupLayout.PREFERRED_SIZE,
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
										.addComponent(panelBotonesDepartamento, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE))
								.addComponent(panelContenidoDepartamentos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap()));

		textAreaDepartamento.setLineWrap(true);
		textAreaDepartamento.setWrapStyleWord(true);
		panelContenidoDepartamentos.add(new JPanel());
		panelContenidoDepartamentos.add(altaPanel, "Alta");
		panelContenidoDepartamentos.add(actualizarPanel, "Actualizar");
		panelContenidoDepartamentos.add(bajaPanel, "Baja");
		panelContenidoDepartamentos.add(mostrarUnoPanel, "Mostrar");
		panelContenidoDepartamentos.add(calcularSueldoPanel, "CalcularSueldo");
		panelContenidoDepartamentos.add(tablaEmpleadosPanel, "Empleados");
		pack();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// </editor-fold>
	
	private void inicializarComponentesPanelAlta() {
		GridBagConstraints gridBagConstraints;

	
		JTextFieldNombreAlta = new JTextField();
		labelNombreAlta = new JLabel();
		JTextFieldAbreviaturaAlta = new JTextField();
		labelAbreviaturaAlta = new JLabel();
		
		botonCancelarAlta = new JButton();
		botonAceptarAlta = new JButton();

		altaPanel.setMaximumSize(new Dimension(411, 242));
		altaPanel.setMinimumSize(new Dimension(411, 242));
		altaPanel.setPreferredSize(new Dimension(411, 242));
		//altaPanel.setLayout(new GridBagLayout());
		altaPanel.setLayout(new GridLayout(3, 2));
		
		
		labelNombreAlta.setText("Nombre: ");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 17;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(0, 128, 0, 0);
		//altaPanel.add(labelNombreAlta, gridBagConstraints);
		altaPanel.add(labelNombreAlta);
		

		JTextFieldNombreAlta.setPreferredSize(new Dimension(50, 20));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 13;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(0, 6, 0, 91);
		//altaPanel.add(JTextFieldNombreAlta, gridBagConstraints);
		altaPanel.add(JTextFieldNombreAlta);
		
		labelAbreviaturaAlta.setText("Abreviatura: ");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.ipadx = 17;
		gridBagConstraints.insets = new Insets(0, 130, 0, 0);
		//altaPanel.add(labelAbreviaturaAlta, gridBagConstraints);
		altaPanel.add(labelAbreviaturaAlta);
		

		JTextFieldAbreviaturaAlta.setPreferredSize(new Dimension(50, 20));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.ipadx = 13;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(0, 10, 0, 95);
		//altaPanel.add(JTextFieldAbreviaturaAlta, gridBagConstraints);
		altaPanel.add(JTextFieldAbreviaturaAlta);
		

		botonAceptarAlta.setText("Aceptar");
		botonAceptarAlta.setPreferredSize(new Dimension(150, 45));
		botonAceptarAlta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aceptarAltaButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.ipadx = 13;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(0, 10, 0, 10);
		//altaPanel.add(botonAceptarAlta, gridBagConstraints);
		altaPanel.add(botonAceptarAlta);
		
		
		botonCancelarAlta.setText("Cancelar");
		botonCancelarAlta.setPreferredSize(new Dimension(150, 45));
		botonCancelarAlta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarAltaButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.ipadx = 8;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(0, 10, 0, 10);
		//altaPanel.add(botonCancelarAlta, gridBagConstraints);
		altaPanel.add(botonCancelarAlta);
	}

	private void inicializarComponentesPanelActualizar() {
		JTextFieldNombreActualizar = new JTextField();
		labelNombreActualizar = new JLabel();
		JTextFieldAbreviaturaActualizar = new JTextField();
		labelAbreviaturaActualizar = new JLabel();
		idActualizarJLabel = new JLabel();
		idActualizarJTextField = new JTextField();
		fechaActualizarJLabel = new JLabel();
		fechaActualizarJTextField = new JTextField();
		
		botonCancelarActualizar = new JButton();
		botonAceptarActualizar = new JButton();

		actualizarPanel.setMaximumSize(new Dimension(411, 242));
		actualizarPanel.setMinimumSize(new Dimension(411, 242));
		actualizarPanel.setPreferredSize(new Dimension(411, 242));
		actualizarPanel.setLayout(new GridLayout(5, 2));
		
		idActualizarJLabel.setText("Id: ");
		actualizarPanel.add(idActualizarJLabel);
		
		idActualizarJTextField.setPreferredSize(new Dimension(50, 20));
		actualizarPanel.add(idActualizarJTextField);

		labelNombreActualizar.setText("Nombre: ");
		actualizarPanel.add(labelNombreActualizar);
		
		JTextFieldNombreActualizar.setPreferredSize(new Dimension(50, 20));
		actualizarPanel.add(JTextFieldNombreActualizar);
		
		labelAbreviaturaActualizar.setText("Abreviatura: ");
		actualizarPanel.add(labelAbreviaturaActualizar);
		
		JTextFieldAbreviaturaActualizar.setPreferredSize(new Dimension(50, 20));
		actualizarPanel.add(JTextFieldAbreviaturaActualizar);
		
		fechaActualizarJLabel.setText("Fecha creacion (dd/mm/yyyy): ");
		actualizarPanel.add(fechaActualizarJLabel);
		
		fechaActualizarJTextField.setPreferredSize(new Dimension(50, 20));
		actualizarPanel.add(fechaActualizarJTextField);
		
		botonAceptarActualizar.setText("Aceptar");
		botonAceptarActualizar.setPreferredSize(new Dimension(150, 45));
		botonAceptarActualizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aceptarActualizarButtonActionPerformed();
			}
		});
		
		actualizarPanel.add(botonAceptarActualizar);
	
		botonCancelarActualizar.setText("Cancelar");
		botonCancelarActualizar.setPreferredSize(new Dimension(150, 45));
		botonCancelarActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarActualizarButtonActionPerformed();
			}
		});

		actualizarPanel.add(botonCancelarActualizar);	
	}
	
	private void inicializarComponentesPanelMostrarUno() {
		GridBagConstraints gridBagConstraints;

		labelIdProductoMostrarUno = new JLabel();
		textIdDepartamentoMostrarUno = new JTextField();
		botonAceptarMostrarUno = new JButton();
		botonCancelarMostrarUno = new JButton();

		mostrarUnoPanel.setMaximumSize(new Dimension(411, 242));
		mostrarUnoPanel.setMinimumSize(new Dimension(411, 242));
		mostrarUnoPanel.setPreferredSize(new Dimension(411, 242));
		mostrarUnoPanel.setLayout(new GridBagLayout());

		labelIdProductoMostrarUno.setText("ID Departamento");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 85, 0, 0);
		mostrarUnoPanel.add(labelIdProductoMostrarUno, gridBagConstraints);

		textIdDepartamentoMostrarUno.setMaximumSize(new Dimension(50, 20));
		textIdDepartamentoMostrarUno.setMinimumSize(new Dimension(50, 20));
		textIdDepartamentoMostrarUno.setPreferredSize(new Dimension(50, 20));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 0, 0, 80);
		mostrarUnoPanel.add(textIdDepartamentoMostrarUno, gridBagConstraints);

		botonAceptarMostrarUno.setText("Aceptar");
		botonAceptarMostrarUno.setMaximumSize(new Dimension(150, 45));
		botonAceptarMostrarUno.setMinimumSize(new Dimension(150, 45));
		botonAceptarMostrarUno.setPreferredSize(new Dimension(150, 45));
		botonAceptarMostrarUno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aceptarMostrarUnoButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(69, 0, 0, 42);
		mostrarUnoPanel.add(botonAceptarMostrarUno, gridBagConstraints);

		botonCancelarMostrarUno.setText("Cancelar");
		botonCancelarMostrarUno.setMaximumSize(new Dimension(150, 45));
		botonCancelarMostrarUno.setMinimumSize(new Dimension(150, 45));
		botonCancelarMostrarUno.setPreferredSize(new Dimension(150, 45));
		botonCancelarMostrarUno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelarMostrarUnaButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(69, 0, 0, 0);
		mostrarUnoPanel.add(botonCancelarMostrarUno, gridBagConstraints);
	}

	private void inicializarComponentesPanelBaja() {
		GridBagConstraints gridBagConstraints;

		labelIdBaja = new JLabel();
		textIdDepartamentoBaja = new JTextField();
		botonAceptarBaja = new JButton();
		botonCancelarBaja = new JButton();

		bajaPanel.setMaximumSize(new Dimension(411, 242));
		bajaPanel.setMinimumSize(new Dimension(411, 242));
		bajaPanel.setPreferredSize(new Dimension(411, 242));
		bajaPanel.setLayout(new GridBagLayout());

		labelIdBaja.setText("ID Departamento");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 85, 0, 0);
		bajaPanel.add(labelIdBaja, gridBagConstraints);

		textIdDepartamentoBaja.setMaximumSize(new Dimension(50, 20));
		textIdDepartamentoBaja.setMinimumSize(new Dimension(50, 20));
		textIdDepartamentoBaja.setPreferredSize(new Dimension(50, 20));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 0, 0, 80);
		bajaPanel.add(textIdDepartamentoBaja, gridBagConstraints);

		botonAceptarBaja.setText("Aceptar");
		botonAceptarBaja.setMaximumSize(new Dimension(150, 45));
		botonAceptarBaja.setMinimumSize(new Dimension(150, 45));
		botonAceptarBaja.setPreferredSize(new Dimension(150, 45));
		botonAceptarBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aceptarBajaButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(69, 0, 0, 42);
		bajaPanel.add(botonAceptarBaja, gridBagConstraints);

		botonCancelarBaja.setText("Cancelar");
		botonCancelarBaja.setMaximumSize(new Dimension(150, 45));
		botonCancelarBaja.setMinimumSize(new Dimension(150, 45));
		botonCancelarBaja.setPreferredSize(new Dimension(150, 45));
		botonCancelarBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelarBajaButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(69, 0, 0, 0);
		bajaPanel.add(botonCancelarBaja, gridBagConstraints);
	}
	
	private void inicializarComponentesPanelBCalcularSueldo() {
		GridBagConstraints gridBagConstraints;

		labelIdCalcularSueldo = new JLabel();
		textIdDepartamentoCalcularSueldo = new JTextField();
		botonAceptarCalcularSueldo = new JButton();
		botonCancelarCalcularSueldo = new JButton();

		calcularSueldoPanel.setMaximumSize(new Dimension(411, 242));
		calcularSueldoPanel.setMinimumSize(new Dimension(411, 242));
		calcularSueldoPanel.setPreferredSize(new Dimension(411, 242));
		calcularSueldoPanel.setLayout(new GridBagLayout());

		labelIdCalcularSueldo.setText("ID Departamento");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 85, 0, 0);
		calcularSueldoPanel.add(labelIdCalcularSueldo, gridBagConstraints);

		textIdDepartamentoCalcularSueldo.setMaximumSize(new Dimension(50, 20));
		textIdDepartamentoCalcularSueldo.setMinimumSize(new Dimension(50, 20));
		textIdDepartamentoCalcularSueldo.setPreferredSize(new Dimension(50, 20));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 0, 0, 80);
		calcularSueldoPanel.add(textIdDepartamentoCalcularSueldo, gridBagConstraints);

		botonAceptarCalcularSueldo.setText("Aceptar");
		botonAceptarCalcularSueldo.setMaximumSize(new Dimension(150, 45));
		botonAceptarCalcularSueldo.setMinimumSize(new Dimension(150, 45));
		botonAceptarCalcularSueldo.setPreferredSize(new Dimension(150, 45));
		botonAceptarCalcularSueldo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aceptarCalcularSueldoButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(69, 0, 0, 42);
		calcularSueldoPanel.add(botonAceptarCalcularSueldo, gridBagConstraints);

		botonCancelarCalcularSueldo.setText("Cancelar");
		botonCancelarCalcularSueldo.setMaximumSize(new Dimension(150, 45));
		botonCancelarCalcularSueldo.setMinimumSize(new Dimension(150, 45));
		botonCancelarCalcularSueldo.setPreferredSize(new Dimension(150, 45));
		botonCancelarCalcularSueldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelarCalcularSueldoButtonActionPerformed();
			}
		});
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(69, 0, 0, 0);
		calcularSueldoPanel.add(botonCancelarCalcularSueldo, gridBagConstraints);
		
	}

	private void inicializarComponentesPanelEmpleados() {
		GridBagConstraints gridBagConstraints;

		jScrollPane1Empleados = new JScrollPane();
		tablaEmpleados = new JTable();
		cerrarTablaEmpleadosButton = new JButton();

		tablaEmpleadosPanel.setMaximumSize(new Dimension(411, 242));
		tablaEmpleadosPanel.setMinimumSize(new Dimension(411, 242));
		tablaEmpleadosPanel.setLayout(new GridBagLayout());

		tablaEmpleados.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "DNI", "Nombre", "Telefono", "Activo" }) {

					private static final long serialVersionUID = -5191374889542656332L;

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return false;
					}
				});
		tablaEmpleados.getTableHeader().setReorderingAllowed(false);
		jScrollPane1Empleados.setViewportView(tablaEmpleados);
		if (tablaEmpleados.getColumnModel().getColumnCount() > 0) {
			tablaEmpleados.getColumnModel().getColumn(0).setResizable(false);
			tablaEmpleados.getColumnModel().getColumn(1).setResizable(false);
			tablaEmpleados.getColumnModel().getColumn(2).setResizable(false);
			tablaEmpleados.getColumnModel().getColumn(3).setResizable(false);
			tablaEmpleados.getColumnModel().getColumn(4).setResizable(false);
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
		tablaEmpleadosPanel.add(jScrollPane1Empleados, gridBagConstraints);

		cerrarTablaEmpleadosButton.setText("Cerrar");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(13, 0, 21, 0);
		cerrarTablaEmpleadosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				volverDeTablaEmpleadosAMostrarUnoButtonActionPerformed();
			}
		});
		tablaEmpleadosPanel.add(cerrarTablaEmpleadosButton, gridBagConstraints);
	}


	private JButton botonAltaDepartamento;
	private JButton botonBajaDepartamento;
	private JButton botonActualizarDepartamento;
	private JButton botonMostrarTodosDepartamentos;
	private JButton botonMostrarUnDepartamento;
	private JButton botonCalcularSueldoNeto;
	private JButton botonVolver;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JPanel panelBotonesDepartamento;
	private JPanel panelContenidoDepartamentos;
	private JTable tablaDepartamentos;
	private JTextArea textAreaDepartamento;

	
	// Alta Departamento
	private JPanel altaPanel;
	private JButton botonAceptarAlta;
	private JButton botonCancelarAlta;
	private JLabel labelNombreAlta;
	private JLabel labelAbreviaturaAlta;
	private JTextField JTextFieldAbreviaturaAlta;
	private JTextField JTextFieldNombreAlta;
	
	// Actualizar Departamento
	private JPanel actualizarPanel;
	private JButton botonAceptarActualizar;
	private JButton botonCancelarActualizar;
	private JLabel idActualizarJLabel;
	private JLabel labelNombreActualizar;
	private JLabel labelAbreviaturaActualizar;
	private JLabel fechaActualizarJLabel;
	private JTextField idActualizarJTextField;
	private JTextField JTextFieldAbreviaturaActualizar;
	private JTextField JTextFieldNombreActualizar;
	private JTextField fechaActualizarJTextField;
	
	// Baja departamento
	private JPanel bajaPanel;
	private JButton botonAceptarBaja;
	private JButton botonCancelarBaja;
	private JLabel labelIdBaja;
	private JTextField textIdDepartamentoBaja;

	
	// Calcular sueldo neto
	private JPanel calcularSueldoPanel;
	private JButton botonAceptarCalcularSueldo;
	private JButton botonCancelarCalcularSueldo;
	private JLabel labelIdCalcularSueldo;
	private JTextField textIdDepartamentoCalcularSueldo;

	// Mostrar un departamento
	private JPanel mostrarUnoPanel;
	private JButton botonAceptarMostrarUno;
	private JButton botonCancelarMostrarUno;
	private JLabel labelIdProductoMostrarUno;
	private JTextField textIdDepartamentoMostrarUno;

	// Tabla empleados
	private JPanel tablaEmpleadosPanel;
	private JScrollPane jScrollPane1Empleados;
	private JTable tablaEmpleados;
	private JButton cerrarTablaEmpleadosButton;

	private Object[][] datosTabla;
}
