package presentacion.actividad;


import java.awt.CardLayout;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import negocio.actividad.Actividad;
import negocio.empleado.AsignacionActividad;
import presentacion.GUI;
import presentacion.controlador.Contexto;
import presentacion.controlador.ControladorAplicacionJPA;
import presentacion.eventos.Evento;

public class GUIMenuActividad extends JFrame implements GUI {

	private static final long serialVersionUID = 1L;

	// Action Performed Menu Botones (Eventos de Redireccion entre vistas)
	
	private void menuPrincipalButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirMenuPrincipal, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		this.dispose();
	}
	
	private void altaButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaAltaActividad, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}
	
	private void actualizarButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaActualizarActividad, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}
	
	private void bajaButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaBajaActividad, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}

	private void mostrarUnoButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaMostrarUnaActividad, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}
	
	// Actualizar actividad
	
	private void aceptarActualizarButtonActionPerformed() {
		try {
			Integer id = Integer.parseInt(textIdActualizar.getText());
			String nombre = textNombreActualizar.getText();
			Integer duracion = Integer.parseInt(textDuracionActualizar.getText());
			String lugar = textLugarActualizar.getText();
			
			if (nombre.isEmpty() || lugar.isEmpty()) {
				throw new Exception();
			}
			
			Actividad actividad = new Actividad(id, nombre, lugar, duracion);
			Contexto contexto = new Contexto(Evento.actualizarActividad, actividad);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
			
			textNombreActualizar.setText("");
			textDuracionActualizar.setText("");
			textIdActualizar.setText("");
			textLugarActualizar.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}
	
	private void cancelarActualizarButtonActionPerformed() {
		textNombreActualizar.setText("");
		textDuracionActualizar.setText("");
		textIdActualizar.setText("");
		textLugarActualizar.setText("");
		
		CardLayout cl = (CardLayout) (panelContenidoActividades.getLayout());
		cl.first(panelContenidoActividades);
	}

	// Alta actividad

	private void aceptarAltaButtonActionPerformed() {
		try {
			String nombre = textNombreAlta.getText();
			Integer duracion = Integer.parseInt(textDuracionAlta.getText());
			String lugar = textLugarAlta.getText();
			
			if (nombre.isEmpty() || lugar.isEmpty()) {
				throw new Exception();
			}
			
			Actividad actividad = new Actividad(nombre, duracion, lugar);
			Contexto contexto = new Contexto(Evento.altaActividad, actividad);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
			
			textNombreAlta.setText("");
			textDuracionAlta.setText("");
			textLugarAlta.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarAltaButtonActionPerformed() {
		textNombreAlta.setText("");
		textDuracionAlta.setText("");
		textLugarAlta.setText("");
		CardLayout cl = (CardLayout) (panelContenidoActividades.getLayout());
		cl.first(panelContenidoActividades);
	}
	
	//Baja actividad
	
	
	private void aceptarBajaButtonActionPerformed() {
		try {
			Integer id = Integer.parseInt(textIdBaja.getText());

			if (id < 0)
				throw new Exception();

			Contexto contexto = new Contexto(Evento.bajaActividad, id);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
			textIdBaja.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}
	
	private void cancelarBajaButtonActionPerformed() {
		textIdBaja.setText("");
		CardLayout cl = (CardLayout) (panelContenidoActividades.getLayout());
		cl.first(panelContenidoActividades);
	}

	//Mostrar una actividad

	private void aceptarMostrarUnoButtonActionPerformed() {
		try {
			Integer id = Integer.parseInt(textIdMostrarUno.getText());

			if (id < 0)
				throw new Exception();

			Contexto contexto = new Contexto(Evento.mostrarUnaActividad, id);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
			textIdMostrarUno.setText("");
		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarMostrarUnoButtonActionPerformed() {
		textIdMostrarUno.setText("");
		CardLayout cl = (CardLayout) (panelContenidoActividades.getLayout());
		cl.first(panelContenidoActividades);
	}

	private void volverDeTablaEmpleadosAMostrarUnoButtonActionPerformed() {
		CardLayout cl = (CardLayout) (panelContenidoActividades.getLayout());
		cl.show(panelContenidoActividades, "Mostrar");
	}
	
	//Mostrar todos
	
	private void mostrarTodasButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.mostrarTodasActividades, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}

	
	@Override
	public void actualizar(Contexto contexto) {
		switch (contexto.getEvento()) {
		
		case abrirVistaActualizarActividad:
			CardLayout cl1 = (CardLayout) (panelContenidoActividades.getLayout());
			cl1.show(panelContenidoActividades, "Actualizar");
			break;
			
		case abrirVistaAltaActividad:
			CardLayout cl2 = (CardLayout) (panelContenidoActividades.getLayout());
			cl2.show(panelContenidoActividades, "Alta");
			break;
			
		case abrirVistaBajaActividad:
			CardLayout cl3 = (CardLayout) (panelContenidoActividades.getLayout());
			cl3.show(panelContenidoActividades, "Baja");
			break;
			
		case abrirVistaMostrarUnaActividad:
			CardLayout cl4 = (CardLayout) (panelContenidoActividades.getLayout());
			cl4.show(panelContenidoActividades, "Mostrar");
			break;

		case actualizarActividadOK:
			this.textAreaActividad.append("->Actualizacion correcta de la actividad con id " + ((Actividad) contexto.getDatos()).getId() + "\n\n");
			break;
			
		case altaActividadOK:
			this.textAreaActividad.append("->Alta correcta de la nueva actividad con id " + ((Actividad) contexto.getDatos()).getId() + "\n\n");
			break;
			
		case bajaActividadOK:
			this.textAreaActividad.append("->Baja correcta de la actividad con id " + ((Integer) contexto.getDatos()) + "\n\n");
		break;

		case errorActualizarActividadIdNoExistente:
			this.textAreaActividad.append("->ERROR: no existe ninguna actividad con id " + ((Actividad) contexto.getDatos()).getId() + " en el sistema.\n\n");
			break;	
			
		case errorActualizarActividadNombreYaExistente:
			this.textAreaActividad.append("->ERROR: no se ha podido actualizar la actividad debido a que " +
					"ya existe una actividad con nombre " + ((Actividad) contexto.getDatos()).getNombre() + " en el sistema.\n\n");
			break;
			
		case errorActualizarActividadReactivacion:
			this.textAreaActividad.append("->La actividad con id " + ((Actividad) contexto.getDatos()).getId() +
					" estaba inactiva, se ha reactivado y se han actualizado sus datos.\n\n");
			break;
			
		case errorActualizarActividadOptimisticLockException:
			this.textAreaActividad.append("->ERROR: la actividad existe en el sistema pero no se ha podido completar la " +
					"operacion debido a que otro usuario estaba modificando la misma actividad.\n\n");
			break;
			
		case errorActualizarActividadAsignacionConMayorDuracion:
			this.textAreaActividad.append("->ERROR: no se ha podido actualizar la actividad debido a que un empleado la tiene " +
										  "asignada con mas de " + ((Actividad) contexto.getDatos()).getDuracion() + " horas.\n\n");
			break;
			
		case errorAltaActividadNombreYaExistente:
			this.textAreaActividad.append("->ERROR: no se ha podido dar de alta la actividad porque ya existe en" +
					" el sistema una actividad con nombre " + ((Actividad) contexto.getDatos()).getNombre() + "\n\n");
			break;
			
		case errorAltaActividadReactivacion:
			this.textAreaActividad.append("->La actividad ya existe en el sistema pero estaba inactiva, se ha reactivado y se han actualizado sus datos.\n\n");
			break;
			
		case errorAltaActividadOptimisticLockException:
			this.textAreaActividad.append("->ERROR: la actividad ya existe en el sistema pero no se ha podido completar la " +
					"operacion debido a que otro usuario estaba modificando la misma actividad.\n\n");
			break;
			
		case errorArgumentos:
			this.textAreaActividad.append("->Error al introducir argumentos\n\n");
			break;
			
		case errorBajaActividadEmpleadosActivos:
			this.textAreaActividad.append("->ERROR: la baja no ha podido realizarse debido a que hay empleados asignados a esta actividad.\n\n");
			break;
		
		case errorBajaActividadIdNoExistente:
			this.textAreaActividad.append("->ERROR: no existe ninguna actividad con id " + ((Integer) contexto.getDatos()) + " en el sistema.\n\n");
			break;
		
		case errorBajaActividadOptimisticLockException:
			this.textAreaActividad.append("->ERROR: la actividad ya existe en el sistema pero no se ha podido completar la" + 
										" operacion debido a que otro usuario estaba modificando la misma actividad.\n\n");
			break;
		
		case errorBajaActividadYaDadoDeBaja:
			this.textAreaActividad.append("->ERROR: la actividad ya estaba dada de baja.\n\n");
			break;
			
		case errorConexionBBDD:
			this.textAreaActividad.append("->ERROR: no ha sido posible conectarse a la BBDD.\n\n");
			break;
			
		case errorMostrarTodasActividadesNoExisteNinguna:
			vaciarTablaActividades();
			this.textAreaActividad.append("->No existe ninguna actividad en el sistema.\n\n");
			break;
			
		case errorMostrarUnaActividadIdNoExistente:
			vaciarTablaActividades();
			this.textAreaActividad.append("->No existe ninguna actividad con id " + ((Integer) contexto.getDatos()) + " en el sistema.\n\n");
			break;

		case mostrarTodasActividadesOK:
			rellenarTablaConActividades((List<Actividad>) contexto.getDatos());
			break;
			
		case mostrarUnaActividadOK:
			rellenarTablaConUnaActividad((Actividad) contexto.getDatos());
			rellenarTablaAsignacionesActividad(((Actividad) contexto.getDatos()).getAsignaciones());
			CardLayout cl6 = (CardLayout) (panelContenidoActividades.getLayout());
			cl6.show(panelContenidoActividades, "Asignaciones");
			break;
		
		default:
			break;
		}
	}

	// ---------------------------------

	private void rellenarTablaConUnaActividad(Actividad actividad) {
		datosTabla = new Object[1][5];
		datosTabla[0][0] = actividad.getId();
		datosTabla[0][1] = actividad.getNombre();
		datosTabla[0][2] = actividad.getLugar();
		datosTabla[0][3] = actividad.getDuracion();
		datosTabla[0][4] = actividad.getActivo();

		setTablaActividadesConfiguration();
	}

	private void rellenarTablaAsignacionesActividad(List<AsignacionActividad> asignacionesActividad) {
		datosTabla = new Object[asignacionesActividad.size()][5];
		int i = 0;
		
		for (AsignacionActividad asignacionActividad : asignacionesActividad) {
			datosTabla[i][0] = asignacionActividad.getEmpleado().getId();
			datosTabla[i][1] = asignacionActividad.getEmpleado().getDNI();
			datosTabla[i][2] = asignacionActividad.getEmpleado().getNombre();
			datosTabla[i][3] = asignacionActividad.getEmpleado().getTelefono();
			datosTabla[i][4] = asignacionActividad.getHoras();
			++i;
		}

		tablaEmpleados
				.setModel(new DefaultTableModel(datosTabla, new String[] { "ID", "DNI", "Nombre", "Telefono", "Horas asignadas" }) {

					private static final long serialVersionUID = 7482486286317006320L;

					Class[] types = new Class[] { Integer.class, String.class, String.class, String.class, Integer.class };

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
		tablaEmpleados.getColumnModel().getColumn(0).setMaxWidth(35);
		tablaEmpleados.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tablaEmpleados.getColumnModel().getColumn(1).setMaxWidth(85);
		tablaEmpleados.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tablaEmpleados.getColumnModel().getColumn(2).setMaxWidth(75);
		tablaEmpleados.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tablaEmpleados.getColumnModel().getColumn(3).setMaxWidth(85);
		tablaEmpleados.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
	}

	private void rellenarTablaConActividades(List<Actividad> actividades) {
		datosTabla = new Object[actividades.size()][5];
		int i = 0;
		
		for (Actividad actividad : actividades) {
			datosTabla[i][0] = actividad.getId();
			datosTabla[i][1] = actividad.getNombre();
			datosTabla[i][2] = actividad.getLugar();
			datosTabla[i][3] = actividad.getDuracion();
			datosTabla[i][4] = actividad.getActivo();
			++i;
		}

		setTablaActividadesConfiguration();
	}

	public void setTablaActividadesConfiguration() {
		tablaActividades.setModel(new DefaultTableModel(datosTabla, new String[] { "Id", "Nombre", "Lugar", "Duracion (horas)", "Activo" }) {

			private static final long serialVersionUID = -4826319740640786334L;

			Class[] types = new Class[] { Integer.class, String.class, String.class, Integer.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaActividades.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tablaActividades.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tablaActividades.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tablaActividades.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tablaActividades.setMaximumSize(new Dimension(300, 64));
		tablaActividades.setMinimumSize(new Dimension(300, 64));
		tablaActividades.setOpaque(false);
		tablaActividades.getTableHeader().setReorderingAllowed(false);
		jScrollPane1.setViewportView(tablaActividades);
	}

	private void vaciarTablaActividades() {
		DefaultTableModel model = (DefaultTableModel) tablaActividades.getModel();
		model.setRowCount(0);
	}


	public GUIMenuActividad() {
		initGUI();
	}

	
	@SuppressWarnings({ "unchecked", "serial" })
	private void initGUI() {
		GridBagConstraints gridBagConstraints;

		jScrollPane1 = new JScrollPane();
		tablaActividades = new JTable();
		tablaEmpleados = new JTable();
		
		jScrollPane2 = new JScrollPane();
		
		textAreaActividad = new JTextArea();
		panelBotonesActividad = new JPanel();
		botonAltaActividad = new JButton();
		botonBajaActividad = new JButton();
		botonActualizarActividad = new JButton();
		botonMostrarUnaActividad = new JButton();
		botonMostrarTodasActividades = new JButton();
		botonVolver = new JButton();
		panelContenidoActividades = new JPanel();

		altaPanel = new JPanel();
		altaPanel.setBorder(BorderFactory.createTitledBorder("Alta de una nueva actividad"));
		inicializarComponentesPanelAlta();

		actualizarPanel = new JPanel();
		actualizarPanel.setBorder(BorderFactory.createTitledBorder("Actualizar una actividad"));
		inicializarComponentesPanelActualizar();
		
		bajaPanel = new JPanel();
		bajaPanel.setBorder(BorderFactory.createTitledBorder("Dar de baja una actividad"));
		inicializarComponentesPanelBaja();

		mostrarUnoPanel = new JPanel();
		mostrarUnoPanel.setBorder(BorderFactory.createTitledBorder("Mostrar una actividad"));
		inicializarComponentesPanelMostrarUno();

		setTablaActividadesConfiguration();
		
		tablaAsignacionesPanel = new JPanel();
		tablaAsignacionesPanel.setBorder(BorderFactory.createTitledBorder("Asignaciones"));
		inicializarComponentesPanelAsignaciones();

		textAreaActividad.setColumns(20);
		textAreaActividad.setRows(5);
		textAreaActividad.setMaximumSize(new Dimension(164, 94));
		textAreaActividad.setMinimumSize(new Dimension(164, 94));
		jScrollPane2.setViewportView(textAreaActividad);

		panelBotonesActividad.setMaximumSize(new Dimension(411, 242));
		panelBotonesActividad.setMinimumSize(new Dimension(411, 242));
		panelBotonesActividad.setOpaque(false);
		panelBotonesActividad.setPreferredSize(new Dimension(411, 242));
		panelBotonesActividad.setLayout(new GridBagLayout());

		botonAltaActividad.setText("Alta");
		botonAltaActividad.setPreferredSize(new Dimension(150, 45));
		botonAltaActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				altaButtonActionPerformed();
			}
		});
		panelBotonesActividad.add(botonAltaActividad, new GridBagConstraints());

		
		botonBajaActividad.setText("Baja");
		botonBajaActividad.setPreferredSize(new Dimension(150, 45));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		botonBajaActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bajaButtonActionPerformed();
			}
		});
		
		panelBotonesActividad.add(botonBajaActividad, gridBagConstraints);
		
		botonActualizarActividad.setText("Actualizar");
		botonActualizarActividad.setPreferredSize(new Dimension(150, 45));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		botonActualizarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				actualizarButtonActionPerformed();
			}
		});
		panelBotonesActividad.add(botonActualizarActividad, gridBagConstraints);

		botonMostrarUnaActividad.setText("Mostrar uno");
		botonMostrarUnaActividad.setPreferredSize(new Dimension(150, 45));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		botonMostrarUnaActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mostrarUnoButtonActionPerformed();
			}
		});
		panelBotonesActividad.add(botonMostrarUnaActividad, gridBagConstraints);
		botonMostrarTodasActividades.setText("Mostrar Todos");
		botonMostrarTodasActividades.setPreferredSize(new Dimension(150, 45));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.1;
		botonMostrarTodasActividades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mostrarTodasButtonActionPerformed();
			}
		});
		panelBotonesActividad.add(botonMostrarTodasActividades, gridBagConstraints);

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
		panelBotonesActividad.add(botonVolver, gridBagConstraints);

		panelContenidoActividades.setMaximumSize(new Dimension(411, 242));
		panelContenidoActividades.setMinimumSize(new Dimension(411, 242));
		panelContenidoActividades.setPreferredSize(new Dimension(411, 242));
		panelContenidoActividades.setLayout(new CardLayout());

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addComponent(jScrollPane1).addGap(18, 18, 18)
										.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 279,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(panelBotonesActividad, GroupLayout.PREFERRED_SIZE, 415,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
										.addComponent(panelContenidoActividades, GroupLayout.PREFERRED_SIZE,
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
										.addComponent(panelBotonesActividad, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE))
								.addComponent(panelContenidoActividades, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap()));

		textAreaActividad.setLineWrap(true);
		textAreaActividad.setWrapStyleWord(true);
		panelContenidoActividades.add(new JPanel());
		panelContenidoActividades.add(altaPanel, "Alta");
		panelContenidoActividades.add(actualizarPanel, "Actualizar");
		panelContenidoActividades.add(bajaPanel, "Baja");
		panelContenidoActividades.add(mostrarUnoPanel, "Mostrar");
		panelContenidoActividades.add(tablaAsignacionesPanel, "Asignaciones");
		pack();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// </editor-fold>

	private void inicializarComponentesPanelAlta() {
		java.awt.GridBagConstraints gridBagConstraints;

        labelNombreAlta = new javax.swing.JLabel();
        textNombreAlta = new javax.swing.JTextField();
        labelDuracionAlta = new javax.swing.JLabel();
        textDuracionAlta = new javax.swing.JTextField();
        labelLugarAlta = new javax.swing.JLabel();
        textLugarAlta = new javax.swing.JTextField();
        panelBotonesAlta = new javax.swing.JPanel();
        botonAceptarAlta = new javax.swing.JButton();
        botonCancelarAlta = new javax.swing.JButton();

        altaPanel.setMaximumSize(new java.awt.Dimension(411, 242));
        altaPanel.setMinimumSize(new java.awt.Dimension(411, 252));
        altaPanel.setLayout(new java.awt.GridBagLayout());

        labelNombreAlta.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(9, 88, 9, 6);
        altaPanel.add(labelNombreAlta, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 155;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 13, 9, 62);
        altaPanel.add(textNombreAlta, gridBagConstraints);

        labelDuracionAlta.setText("Duracion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 88, 9, 5);
        altaPanel.add(labelDuracionAlta, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(9, 13, 9, 2);
        altaPanel.add(textDuracionAlta, gridBagConstraints);

        labelLugarAlta.setText("Lugar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(9, 88, 9, 11);
        altaPanel.add(labelLugarAlta, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 153;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 13, 0, 0);
        altaPanel.add(textLugarAlta, gridBagConstraints);

        panelBotonesAlta.setLayout(new java.awt.GridBagLayout());

        botonAceptarAlta.setText("Aceptar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 9, 0);
        botonAceptarAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				aceptarAltaButtonActionPerformed();
			}
		});
        panelBotonesAlta.add(botonAceptarAlta, gridBagConstraints);

        botonCancelarAlta.setText("Cancelar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 9, 0);
        botonCancelarAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelarAltaButtonActionPerformed();
			}
		});
        panelBotonesAlta.add(botonCancelarAlta, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 309;
        altaPanel.add(panelBotonesAlta, gridBagConstraints);
	}
	
	private void inicializarComponentesPanelActualizar() {
		java.awt.GridBagConstraints gridBagConstraints;

        labelIDActualizar = new JLabel();
        textIdActualizar = new JTextField();
        labelNombreActualizar = new JLabel();
        textNombreActualizar = new JTextField();
        labelDuracionActualizar = new JLabel();
        textDuracionActualizar = new JTextField();
        labelLugarActualizar = new JLabel();
        textLugarActualizar = new JTextField();
        
        actualizarBotonesPanel = new JPanel();
        botonAceptarActualizar = new JButton();
        botonCancelarActualizar = new JButton();

        actualizarPanel.setMaximumSize(new Dimension(455, 242));
        actualizarPanel.setMinimumSize(new Dimension(455, 242));
        actualizarPanel.setPreferredSize(new Dimension(455, 242));
        actualizarPanel.setLayout(new java.awt.GridBagLayout());

        labelIDActualizar.setText("ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 88, 9, 11);
        actualizarPanel.add(labelIDActualizar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 13, 9, 0);
        actualizarPanel.add(textIdActualizar, gridBagConstraints);

        labelNombreActualizar.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(9, 88, 9, 6);
        actualizarPanel.add(labelNombreActualizar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 155;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 13, 9, 62);
        actualizarPanel.add(textNombreActualizar, gridBagConstraints);

        labelDuracionActualizar.setText("Duracion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 88, 9, 5);
        actualizarPanel.add(labelDuracionActualizar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(9, 13, 9, 2);
        actualizarPanel.add(textDuracionActualizar, gridBagConstraints);

        labelLugarActualizar.setText("Lugar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(9, 88, 9, 11);
        actualizarPanel.add(labelLugarActualizar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 153;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 13, 0, 0);
        actualizarPanel.add(textLugarActualizar, gridBagConstraints);

        actualizarBotonesPanel.setLayout(new java.awt.GridBagLayout());

        botonAceptarActualizar.setText("Aceptar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 9, 0);
        botonAceptarActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aceptarActualizarButtonActionPerformed();
			}
		});
        actualizarBotonesPanel.add(botonAceptarActualizar, gridBagConstraints);

        botonCancelarActualizar.setText("Cancelar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 9, 0);
        botonCancelarActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarActualizarButtonActionPerformed();
			}
		});
        actualizarBotonesPanel.add(botonCancelarActualizar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 309;
        actualizarPanel.add(actualizarBotonesPanel, gridBagConstraints);
	}
	
	private void inicializarComponentesPanelMostrarUno() {
		java.awt.GridBagConstraints gridBagConstraints;

        labelIDMostrarUno = new javax.swing.JLabel();
        textIdMostrarUno = new javax.swing.JTextField();
        panelBotonesMostrarUno = new javax.swing.JPanel();
        botonAceptarMostrarUno = new javax.swing.JButton();
        botonCancelarMostrarUno = new javax.swing.JButton();

        mostrarUnoPanel.setMaximumSize(new java.awt.Dimension(411, 242));
        mostrarUnoPanel.setMinimumSize(new java.awt.Dimension(411, 252));
        mostrarUnoPanel.setLayout(new java.awt.GridBagLayout());

        labelIDMostrarUno.setText("ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 67, 44, 5);
        mostrarUnoPanel.add(labelIDMostrarUno, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 32;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 7, 44, 64);
        mostrarUnoPanel.add(textIdMostrarUno, gridBagConstraints);

        panelBotonesMostrarUno.setLayout(new java.awt.GridBagLayout());

        botonAceptarMostrarUno.setText("Aceptar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 9, 0);
        botonAceptarMostrarUno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				aceptarMostrarUnoButtonActionPerformed();
			}
		});
        panelBotonesMostrarUno.add(botonAceptarMostrarUno, gridBagConstraints);

        botonCancelarMostrarUno.setText("Cancelar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 9, 0);
        botonCancelarMostrarUno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelarMostrarUnoButtonActionPerformed();
			}
		});
        panelBotonesMostrarUno.add(botonCancelarMostrarUno, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 309;
        mostrarUnoPanel.add(panelBotonesMostrarUno, gridBagConstraints);
	}

	private void inicializarComponentesPanelBaja() {
		java.awt.GridBagConstraints gridBagConstraints;

        labelIDBaja = new javax.swing.JLabel();
        textIdBaja = new javax.swing.JTextField();
        panelBotonesBaja = new javax.swing.JPanel();
        botonAceptarBaja = new javax.swing.JButton();
        botonCancelarBaja = new javax.swing.JButton();

        bajaPanel.setMaximumSize(new java.awt.Dimension(411, 242));
        bajaPanel.setMinimumSize(new java.awt.Dimension(411, 252));
        bajaPanel.setLayout(new java.awt.GridBagLayout());

        labelIDBaja.setText("ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 67, 44, 5);
        bajaPanel.add(labelIDBaja, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 32;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 7, 44, 64);
        bajaPanel.add(textIdBaja, gridBagConstraints);

        panelBotonesBaja.setLayout(new java.awt.GridBagLayout());

        botonAceptarBaja.setText("Aceptar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 9, 0);
        botonAceptarBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				aceptarBajaButtonActionPerformed();
			}
		});
        panelBotonesBaja.add(botonAceptarBaja, gridBagConstraints);

        botonCancelarBaja.setText("Cancelar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 0, 9, 0);
        botonCancelarBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelarBajaButtonActionPerformed();
			}
		});
        panelBotonesBaja.add(botonCancelarBaja, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 309;
        bajaPanel.add(panelBotonesBaja, gridBagConstraints);
	}

	private void inicializarComponentesPanelAsignaciones() {
		GridBagConstraints gridBagConstraints;

		jScrollPane1Empleados = new JScrollPane();
		tablaEmpleados = new JTable();
		cerrarTablaEmpleadosButton = new JButton();

		tablaAsignacionesPanel.setMaximumSize(new Dimension(411, 242));
		tablaAsignacionesPanel.setMinimumSize(new Dimension(411, 242));
		tablaAsignacionesPanel.setLayout(new GridBagLayout());

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
		tablaAsignacionesPanel.add(jScrollPane1Empleados, gridBagConstraints);

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
		tablaAsignacionesPanel.add(cerrarTablaEmpleadosButton, gridBagConstraints);
	}

	private JButton botonAltaActividad;
	private JButton botonBajaActividad;
	private JButton botonActualizarActividad;
	private JButton botonMostrarTodasActividades;
	private JButton botonMostrarUnaActividad;
	private JButton botonVolver;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JPanel panelBotonesActividad;
	private JPanel panelContenidoActividades;
	private JTable tablaActividades;
	private JTextArea textAreaActividad;
	
	// Alta Departamento
	private JPanel altaPanel;
	private JButton botonAceptarAlta;
    private JButton botonCancelarAlta;
    private JLabel labelDuracionAlta;
    private JLabel labelLugarAlta;
    private JLabel labelNombreAlta;
    private JPanel panelBotonesAlta;
    private JTextField textDuracionAlta;
    private JTextField textLugarAlta;
    private JTextField textNombreAlta;

	// Actualizar Actividad
	private JPanel actualizarPanel;
	private JPanel actualizarBotonesPanel;
	private JButton botonCancelarActualizar;
    private JButton botonAceptarActualizar;
    private JLabel labelDuracionActualizar;
    private JLabel labelIDActualizar;
    private JLabel labelLugarActualizar;
    private JLabel labelNombreActualizar;
    private JTextField textDuracionActualizar;
    private JTextField textIdActualizar;
    private JTextField textLugarActualizar;
    private JTextField textNombreActualizar;

	// Baja departamento
	private JPanel bajaPanel;
	private JButton botonCancelarBaja;
    private JButton botonAceptarBaja;
    private JLabel labelIDBaja;
    private JPanel panelBotonesBaja;
    private JTextField textIdBaja;

	// Mostrar un departamento
	private JPanel mostrarUnoPanel;
	private JButton botonCancelarMostrarUno;
    private JButton botonAceptarMostrarUno;
    private JLabel labelIDMostrarUno;
    private JPanel panelBotonesMostrarUno;
    private JTextField textIdMostrarUno;

	// Tabla empleados
	private JPanel tablaAsignacionesPanel;
	private JScrollPane jScrollPane1Empleados;
	private JTable tablaEmpleados;
	private JButton cerrarTablaEmpleadosButton;

	private Object[][] datosTabla;
}
