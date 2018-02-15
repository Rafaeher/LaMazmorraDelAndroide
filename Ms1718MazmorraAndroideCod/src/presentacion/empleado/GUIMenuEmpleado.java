package presentacion.empleado;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
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
import negocio.departamento.Departamento;
import negocio.empleado.AsignacionActividad;
import negocio.empleado.Empleado;
import negocio.empleado.EmpleadoFijo;
import negocio.empleado.EmpleadoTemporal;
import presentacion.GUI;
import presentacion.controlador.Contexto;
import presentacion.controlador.ControladorAplicacionJPA;
import presentacion.eventos.Evento;

public class GUIMenuEmpleado extends JFrame implements GUI {

	private static final long serialVersionUID = 1L;

	// Action Performed Menu Botones (Eventos de Redireccion entre vistas)

	private void menuPrincipalButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirMenuPrincipal, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		this.dispose();
	}

	private void altaFijoButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaAltaEmpleadoFijo, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}

	private void altaTemporalButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaAltaEmpleadoTemporal, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}

	private void actualizarFijoButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaActualizarEmpleadoFijo, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}

	private void actualizarTemporalButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaActualizarEmpleadoTemporal, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}

	private void asignarActivivdadButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaAsignarActividad, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}

	private void bajaButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaBajaEmpleado, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}

	private void desasignarActivivdadButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaDesasignarActividad, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}

	private void mostrarUnoButtonActionPerformed() {
		Contexto contexto = new Contexto(Evento.abrirVistaMostrarUnEmpleado, null);
		ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
	}

	// Actualizar empleado fijo

	private void aceptarActualizarEmpleadoFijoButtonActionPerformed()
	{
		try
		{
			String nombre = textNombreActualizarFijo.getText();
			String telefono = textTelefonoActualizarFijo.getText();
			Integer idEmpleado = Integer.parseInt(textIdActualizarFijo.getText());
			String dni = textDNIActualizarFijo.getText();
			Integer idDepartamento = Integer.parseInt(textDepartamentoActualizarFijo.getText());
			Double sueldoMensual = Double.valueOf(textSueldoMensualActualizarFijo.getText());
			Double impuestos = Double.valueOf(textImpuestosActualizarFijo.getText());
			
			if (nombre.equals("") || telefono.equals("") || idEmpleado <= 0 || dni.equals("") ||
					idDepartamento <= 0 || sueldoMensual <= 0 || impuestos < 0.0 || impuestos > 100.0)
				throw new Exception("Argumentos incorrectos");
			
			Departamento departamento = new Departamento(idDepartamento);
			Empleado empleado = new EmpleadoFijo(idEmpleado, dni, nombre, telefono, departamento, sueldoMensual, impuestos / 100.0);
			
			Contexto contexto = new Contexto(Evento.actualizarEmpleado, empleado);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
			
			textNombreActualizarFijo.setText("");
			textTelefonoActualizarFijo.setText("");
			textIdActualizarFijo.setText("");
			textDNIActualizarFijo.setText("");
			textDepartamentoActualizarFijo.setText("");
			textSueldoMensualActualizarFijo.setText("");
			textImpuestosActualizarFijo.setText("");
		}
		catch(Exception e)
		{
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarActualizarEmpleadoFijoButtonActionPerformed() {
		textNombreActualizarFijo.setText("");
		textTelefonoActualizarFijo.setText("");
		textIdActualizarFijo.setText("");
		textDNIActualizarFijo.setText("");
		textDepartamentoActualizarFijo.setText("");
		textSueldoMensualActualizarFijo.setText("");
		textImpuestosActualizarFijo.setText("");

		CardLayout cl = (CardLayout) (panelBotonesCasosDeUso.getLayout());
		cl.first(panelBotonesCasosDeUso);
	}
	
	//Actualizar empleado temporal

	private void aceptarActualizarEmpleadoTemporalButtonActionPerformed()
	{
		try
		{
			String nombre = textNombreActualizarTemporal.getText();
			String telefono = textTelefonoActualizarTemporal.getText();
			Integer idEmpleado = Integer.parseInt(textIdActualizarTemporal.getText());
			String dni = textDNIActualizarTemporal.getText();
			Integer idDepartamento = Integer.parseInt(textDepartamentoActualizarTemporal.getText());
			Integer horasMes = Integer.parseInt(textHorasMesActualizarTemporal.getText());
			Double salarioHora = Double.valueOf(textSalarioPorHoraActualizarTemporal.getText());
			
			if (nombre.equals("") || telefono.equals("") || idEmpleado <= 0 || dni.equals("") ||
					idDepartamento <= 0 || horasMes <= 0 || salarioHora <= 0)
				throw new Exception("Argumentos incorrectos");
			
			Departamento departamento = new Departamento(idDepartamento);
			Empleado empleado = new EmpleadoTemporal(idEmpleado, dni, nombre, telefono, departamento, horasMes, salarioHora);
			
			Contexto contexto = new Contexto(Evento.actualizarEmpleado, empleado);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
			
			textNombreActualizarTemporal.setText("");
			textTelefonoActualizarTemporal.setText("");
			textIdActualizarTemporal.setText("");
			textDNIActualizarTemporal.setText("");
			textDepartamentoActualizarTemporal.setText("");
			textHorasMesActualizarTemporal.setText("");
			textSalarioPorHoraActualizarTemporal.setText("");
		}
		catch(Exception e)
		{
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarActualizarEmpleadoTemporalButtonActionPerformed() {
		textNombreActualizarTemporal.setText("");
		textTelefonoActualizarTemporal.setText("");
		textIdActualizarTemporal.setText("");
		textDNIActualizarTemporal.setText("");
		textDepartamentoActualizarTemporal.setText("");
		textHorasMesActualizarTemporal.setText("");
		textSalarioPorHoraActualizarTemporal.setText("");
		

		CardLayout cl = (CardLayout) (panelBotonesCasosDeUso.getLayout());
		cl.first(panelBotonesCasosDeUso);
	}
	// Seleccion del tipo de empleado en actualizar

	private void cancelarSeleccionTipoActualizarActionPerformed() {
		CardLayout cl = (CardLayout) (panelBotonesCasosDeUso.getLayout());
		cl.first(panelBotonesCasosDeUso);
	}

	// Alta Empleado Fijo

	private void aceptarAltaEmpleadoFijoButtonActionPerformed() {
		try {
			String nombre = textNombreAltaFijo.getText();
			String dni = textDNIAltaFijo.getText();
			String telefono = textTelefonoAltaFijo.getText();
			Double sueldoMensual = Double.valueOf(textSueldoMensualAltaFijo.getText());
			Double impuestos = Double.valueOf(textImpuestosAltaFijo.getText());
			Integer idDepartamento = Integer.valueOf(textDepartamentoAltaFijo.getText());

			if (nombre.isEmpty() || dni.isEmpty() || telefono.isEmpty() || impuestos < 0.0 || impuestos > 100.0
					|| sueldoMensual < 0.0 || idDepartamento < 0) {
				throw new Exception();
			}

			Departamento departamento = new Departamento(idDepartamento);
			Empleado empleado = new EmpleadoFijo(dni, nombre, telefono, departamento, sueldoMensual, impuestos / 100.0);
			Contexto contexto = new Contexto(Evento.altaEmpleado, empleado);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);

			textNombreAltaFijo.setText("");
			textDNIAltaFijo.setText("");
			textTelefonoAltaFijo.setText("");
			textSueldoMensualAltaFijo.setText("");
			textImpuestosAltaFijo.setText("");
			textDepartamentoAltaFijo.setText("");

		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarAltaEmpleadoFijoButtonActionPerformed() {
		textNombreAltaFijo.setText("");
		textDNIAltaFijo.setText("");
		textTelefonoAltaFijo.setText("");
		textSueldoMensualAltaFijo.setText("");
		textImpuestosAltaFijo.setText("");
		textDepartamentoAltaFijo.setText("");

		CardLayout cl = (CardLayout) (panelBotonesCasosDeUso.getLayout());
		cl.first(panelBotonesCasosDeUso);
	}

	// Alta empleado temporal

	private void aceptarAltaEmpleadoTemporalButtonActionPerformed() {
		try {
			String nombre = textNombreAltaTemporal.getText();
			String dni = textDNIAltaTemporal.getText();
			String telefono = textTelefonoAltaTemporal.getText();
			Double salarioPorHora = Double.valueOf(textSalarioPorHoraAltaTemporal.getText());
			Integer horasMes = Integer.valueOf(textHorasMesAltaTemporal.getText());
			Integer idDepartamento = Integer.valueOf(textDepartamentoAltaTemporal.getText());

			if (nombre.isEmpty() || dni.isEmpty() || telefono.isEmpty() || horasMes < 0 || salarioPorHora < 0.0
					|| idDepartamento < 0) {
				throw new Exception();
			}

			Departamento departamento = new Departamento(idDepartamento);
			Empleado empleado = new EmpleadoTemporal(dni, nombre, telefono, departamento, horasMes, salarioPorHora);
			Contexto contexto = new Contexto(Evento.altaEmpleado, empleado);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);

			textNombreAltaTemporal.setText("");
			textDNIAltaTemporal.setText("");
			textTelefonoAltaTemporal.setText("");
			textSalarioPorHoraAltaTemporal.setText("");
			textHorasMesAltaTemporal.setText("");
			textDepartamentoAltaTemporal.setText("");

		} catch (Exception ex) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarAltaEmpleadoTemporalButtonActionPerformed() {
		textNombreAltaTemporal.setText("");
		textDNIAltaTemporal.setText("");
		textTelefonoAltaTemporal.setText("");
		textSalarioPorHoraAltaTemporal.setText("");
		textHorasMesAltaTemporal.setText("");
		textDepartamentoAltaTemporal.setText("");

		CardLayout cl = (CardLayout) (panelBotonesCasosDeUso.getLayout());
		cl.first(panelBotonesCasosDeUso);
	}

	// Seleccion del tipo de empleado en el alta

	private void cancelarSeleccionTipoAltaActionPerformed() {
		CardLayout cl = (CardLayout) (panelBotonesCasosDeUso.getLayout());
		cl.first(panelBotonesCasosDeUso);
	}

	// Baja empleado

	private void aceptarBajaButtonActionPerformed()
	{
		try
		{
			Integer id = Integer.parseInt(textIdBaja.getText());
			
			if (id <= 0)
				throw new Exception();
			
			Contexto contexto = new Contexto(Evento.bajaEmpleado, id);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
			
			textIdBaja.setText("");
		}
		catch (Exception e)
		{
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarBajaButtonActionPerformed() {
		textIdBaja.setText("");
		CardLayout cl = (CardLayout) (panelBotonesCasosDeUso.getLayout());
		cl.first(panelBotonesCasosDeUso);
	}

	// Mostrar un empleado

	private void aceptarMostrarUnoButtonActionPerformed() {
		try {
			Integer id = Integer.valueOf(textIdMostrarUno.getText());
			
			if (id <= 0) {
				throw new Exception();
			}
			Contexto contexto = new Contexto(Evento.mostrarUnEmpleado, id);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
			
			textIdMostrarUno.setText("");
			
		} catch(Exception e) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}

	private void cancelarMostrarUnoButtonActionPerformed() {
		textIdMostrarUno.setText("");
		CardLayout cl = (CardLayout) (panelBotonesCasosDeUso.getLayout());
		cl.first(panelBotonesCasosDeUso);
	}

	private void volverDeTablaEmpleadosAMostrarUnoButtonActionPerformed() {
		CardLayout cl = (CardLayout) (panelBotonesCasosDeUso.getLayout());
		cl.show(panelBotonesCasosDeUso, "Mostrar");
	}
	
	//Asignar actividad
	
	private void aceptarAsignaractividadButtonActionPerformed() {
		try {
			Integer idEmpleado = Integer.parseInt(textIdEmpleadoAsignarActividad.getText());
			Integer idActividad = Integer.parseInt(textIdActividadAsignarActividad.getText());
			Integer horas = Integer.parseInt(textHorasAsignarActividad.getText());
			
			if (idEmpleado <= 0 || idActividad <= 0 || horas <= 0)
				throw new Exception("Error en argumentos");
			
			AsignacionActividad asignacion = new AsignacionActividad(new EmpleadoFijo(idEmpleado, null, null, null, null, null, null),
					new Actividad(idActividad, null, null, null), horas);
			
			Contexto contexto = new Contexto(Evento.asignarActividad, asignacion);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
			
			textIdEmpleadoAsignarActividad.setText("");
			textIdActividadAsignarActividad.setText("");
			textHorasAsignarActividad.setText("");
			
		} catch (Exception e) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}
	
	private void cancelarAsignaractividadButtonActionPerformed() {
		textIdEmpleadoAsignarActividad.setText("");
		textIdActividadAsignarActividad.setText("");
		textHorasAsignarActividad.setText("");
		
		CardLayout cl = (CardLayout) (panelBotonesCasosDeUso.getLayout());
		cl.first(panelBotonesCasosDeUso);
	}

	//Desasignar actividad
	
	private void aceptarDesasignarActividadButtonActionPerformed() {
		try {
			Integer idEmpleado = Integer.parseInt(textIdEmpleadoDesasignarActividad.getText());
			Integer idActividad = Integer.parseInt(textIdActividadDesasignarActividad.getText());
			
			if (idEmpleado <= 0 || idActividad <= 0)
				throw new Exception("Error en argumentos");
			
			AsignacionActividad asignacion = new AsignacionActividad(new EmpleadoFijo(idEmpleado, null, null, null, null, null, null),
					new Actividad(idActividad, null, null, null));
			
			Contexto contexto = new Contexto(Evento.desasignarActividad, asignacion);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
			
			textIdEmpleadoDesasignarActividad.setText("");
			textIdActividadDesasignarActividad.setText("");
			
		} catch (Exception e) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}
	
	private void cancelarDesasignarActividadButtonActionPerformed() {
		textIdEmpleadoDesasignarActividad.setText("");
		textIdActividadDesasignarActividad.setText("");
		
		
		CardLayout cl = (CardLayout) (panelBotonesCasosDeUso.getLayout());
		cl.first(panelBotonesCasosDeUso);
	}
	
	// Mostrar todos

	private void mostrarTodasButtonActionPerformed() {
		try {
			Contexto contexto = new Contexto(Evento.mostrarTodosEmpleados, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
			
			CardLayout cl = (CardLayout) (panelBotonesCasosDeUso.getLayout());
			cl.first(panelBotonesCasosDeUso);
			
		} catch(Exception e) {
			Contexto contexto = new Contexto(Evento.errorArgumentos, null);
			ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
		}
	}

	@Override
	public void actualizar(Contexto contexto) {
		switch (contexto.getEvento()) {

		case abrirVistaActualizarEmpleadoFijo:
			CardLayout cl1 = (CardLayout) (panelBotonesCasosDeUso.getLayout());
			cl1.show(panelBotonesCasosDeUso, "ActualizarEmpleadoFijo");
			break;
			
		case abrirVistaActualizarEmpleadoTemporal:
			CardLayout cl11 = (CardLayout) (panelBotonesCasosDeUso.getLayout());
			cl11.show(panelBotonesCasosDeUso, "ActualizarEmpleadoTemporal");
			break;
			
		case abrirVistaAltaEmpleadoFijo:
			CardLayout cl2 = (CardLayout) (panelBotonesCasosDeUso.getLayout());
			cl2.show(panelBotonesCasosDeUso, "AltaEmpleadoFijo");
			break;

		case abrirVistaAltaEmpleadoTemporal:
			CardLayout cl22 = (CardLayout) (panelBotonesCasosDeUso.getLayout());
			cl22.show(panelBotonesCasosDeUso, "AltaEmpleadoTemporal");
			break;
			
		case abrirVistaAsignarActividad:
			CardLayout cl111 = (CardLayout) (panelBotonesCasosDeUso.getLayout());
			cl111.show(panelBotonesCasosDeUso, "AsignarActividad");
			break;
			
		case abrirVistaBajaEmpleado:
			CardLayout cl113 = (CardLayout) (panelBotonesCasosDeUso.getLayout());
			cl113.show(panelBotonesCasosDeUso, "Baja");
			break;
			
		case abrirVistaDesasignarActividad:
			CardLayout cl112 = (CardLayout) (panelBotonesCasosDeUso.getLayout());
			cl112.show(panelBotonesCasosDeUso, "DesasignarActividad");
			break;
			
		case abrirVistaMostrarUnEmpleado:
			CardLayout cl122 = (CardLayout) (panelBotonesCasosDeUso.getLayout());
			cl122.show(panelBotonesCasosDeUso, "Mostrar");
			break;

		case actualizarEmpleadoOK:
			this.textAreaEmpleado.append("->Empleado con id "
					+ ((Empleado) contexto.getDatos()).getId()
					+ " actualizado correctamente.\n\n");
			break;
			
		case actualizarEmpleadoOKReactivado:
			this.textAreaEmpleado.append("->Empleado con id "
					+ ((Empleado) contexto.getDatos()).getId()
					+ " actualizado correctamente. El empleado estaba dado de baja y se ha reactivado\n\n");
			break;
			
		case altaEmpleadoOK:
			this.textAreaEmpleado.append(
					"->Alta correcta del nuevo empleado con id " + ((Empleado) contexto.getDatos()).getId() + "\n\n");
			break;
			
		case asignarActividadOK:
			this.textAreaEmpleado.append("->Actividad con id "
					+ ((AsignacionActividad)contexto.getDatos()).getActividad().getId()
					+ " asignada al empleado con id "
					+ ((AsignacionActividad)contexto.getDatos()).getEmpleado().getId()
					+ ".\n\n");
			break;
			
		case asignarActividadOKAsignacionModificada:
			this.textAreaEmpleado.append("->Se han actualizado las horas asignadas al empleado con id "
					+ ((AsignacionActividad)contexto.getDatos()).getEmpleado().getId()
					+ " a la actividad con id "
					+ ((AsignacionActividad)contexto.getDatos()).getActividad().getId()
					+ " a un total de "
					+ ((AsignacionActividad)contexto.getDatos()).getHoras()
					+" horas.\n\n");
			break;
		
		case bajaEmpleadoOK:
			this.textAreaEmpleado.append("->Baja correcta del empleado con id " + (Integer) contexto.getDatos() + ".\n\n");
			break;
			
		case errorActualizarEmpleado_DepartamentoInactivo:
			this.textAreaEmpleado.append("->Error al intentar actualizar el empleado con id "
					+ ((Empleado) contexto.getDatos()).getId()
					+ ". El departamento con id "
					+ ((Empleado) contexto.getDatos()).getDepartamento().getId()
					+ " no estaba activo.\n\n");
			break;
			
		case errorActualizarEmpleado_DepartamentoInexistente:
			this.textAreaEmpleado.append("->Error al intentar actualizar el empleado con id "
					+ ((Empleado) contexto.getDatos()).getId()
					+ ". El departamento con id "
					+ ((Empleado) contexto.getDatos()).getDepartamento().getId()
					+ " no existe.\n\n");
			break;
			
		case errorActualizarEmpleadoDNIRepetido:
			this.textAreaEmpleado.append("->ERROR: no se ha actualizado el empleado con id "
					+ ((Empleado) contexto.getDatos()).getId()
					+ ". El DNI ya esta en uso por otro empleado.\n\n");
			break;
			
		case errorActualizarEmpleado_EmpleadoInexistente:
			this.textAreaEmpleado.append("->ERROR: no se ha actualizado el empleado con id "
					+ ((Empleado) contexto.getDatos()).getId()
					+ ". El empleado no existe en la base de datos.\n\n");
			break;
			
		case errorActualizarEmpleadoTiposDistintos:
			this.textAreaEmpleado.append("->ERROR: no se ha actualizado el empleado con id "
					+ ((Empleado) contexto.getDatos()).getId()
					+ ". Se ha intentado cambiar el tipo del empleado.\n\n");
			break;
			
		case errorActualizarEmpleadoOptimisticLockException:
			this.textAreaEmpleado.append("->ERROR: el empleado existe en el sistema pero no se ha podido completar la "
					+ "operacion debido a que otro usuario estaba modificando el mismo empleado.\n\n");
			break;
			
		case errorAltaEmpleadoDepartamentoNoExistente:
			this.textAreaEmpleado.append("->ERROR: no se ha podido dar de alta el empleado porque el departamento"
					+ " con id " + ((Empleado) contexto.getDatos()).getDepartamento().getId()
					+ " no existe en el sistema.\n\n");
			break;

		case errorAltaEmpleadoDepartamentoInactivo:
			this.textAreaEmpleado.append("->ERROR: no se ha podido dar de alta el empleado porque el departamento"
					+ " con id " + ((Empleado) contexto.getDatos()).getDepartamento().getId() + " está inactivo.\n\n");
			break;

		case errorAltaEmpleadoTiposEmpleadosNoCoinciden:
			Boolean empleadoTipoFijo = ((Empleado) contexto.getDatos()).getClass().equals(EmpleadoFijo.class);
			this.textAreaEmpleado.append("->ERROR: ya existe un empleado con "
					+ "DNI " + ((Empleado) contexto.getDatos()).getDNI() + " "
					+ " en el sistema de tipo " + (empleadoTipoFijo ? "temporal" : "fijo")
					+ ", no es posible modificar el tipo " + "de un empleado.\n\n");
			break;
			
		case errorAltaEmpleadoDNIYaExistente:
			this.textAreaEmpleado.append("->ERROR: no se ha podido dar de "
					+ "alta al empleado debido a que " + "ya existe un empleado con DNI "
							+ ((Empleado) contexto.getDatos()).getDNI() + " en el sistema.\n\n");
			break;

		case errorAltaEmpleadoOptimisticLockException:
			this.textAreaEmpleado.append("->ERROR: el empleado existe en el sistema pero no se ha podido completar la "
					+ "operacion debido a que otro usuario estaba modificando el mismo empleado.\n\n");
			break;

		case errorAltaEmpleadoReactivacion:
			this.textAreaEmpleado.append("->El empleado ya existe en el sistema pero "
					+ "estaba inactivo, se ha reactivado y se han actualizado sus datos.\n\n");
			break;
			
		case errorAsignarActividad_ActividadInactiva:
			this.textAreaEmpleado.append("->ERROR: no se ha podido asignar la actividad con id "
					+ ((AsignacionActividad) contexto.getDatos()).getActividad().getId()
					+ " al empleado con id "
					+ ((AsignacionActividad) contexto.getDatos()).getEmpleado().getId()
					+ " porque la actividad estaba inactiva.\n\n");
			break;
			
		case errorAsignarActividad_ActividadInexistente:
			this.textAreaEmpleado.append("->ERROR: no se ha podido asignar la actividad con id "
					+ ((AsignacionActividad) contexto.getDatos()).getActividad().getId()
					+ " al empleado con id "
					+ ((AsignacionActividad) contexto.getDatos()).getEmpleado().getId()
					+ " porque la actividad no existe en el sistema.\n\n");
			break;
			
		case errorAsignarActividad_EmpleadoInactivo:
			this.textAreaEmpleado.append("->ERROR: no se ha podido asignar la actividad con id "
					+ ((AsignacionActividad) contexto.getDatos()).getActividad().getId()
					+ " al empleado con id "
					+ ((AsignacionActividad) contexto.getDatos()).getEmpleado().getId()
					+ " porque el empleado estaba inactivo.\n\n");
			break;
			
		case errorAsignarActividad_EmpleadoInexistente:
			this.textAreaEmpleado.append("->ERROR: no se ha podido asignar la actividad con id "
					+ ((AsignacionActividad) contexto.getDatos()).getActividad().getId()
					+ " al empleado con id "
					+ ((AsignacionActividad) contexto.getDatos()).getEmpleado().getId()
					+ " porque el empleado no existe en el sistema.\n\n");
			break;
			
		case errorAsignarActividadOptimisticLockException:
			this.textAreaEmpleado.append("->ERROR: no se ha podido asignar la actividad con id "
					+ ((AsignacionActividad) contexto.getDatos()).getActividad().getId()
					+ " al empleado con id "
					+ ((AsignacionActividad) contexto.getDatos()).getEmpleado().getId()
					+ " debido a que otro usuario estaba modificando la actividad o el empleado.\n\n");
			break;
			
		case errorAsignarActividadHorasMayorQueDuracionActividad:
			this.textAreaEmpleado.append("ERROR: no se ha podido asignar la actividad debido a que "
					+ "tiene una duracion menor "
					+ "de " + ((Integer) contexto.getDatos()) + " horas.\n\n");
			break;
			
		case errorArgumentos:
			this.textAreaEmpleado.append("->Error al introducir argumentos\n\n");
			break;
			
		case errorBajaEmpleado_ActividadesAsignadas:
			this.textAreaEmpleado.append(
					"->ERROR: el empleado con id " + (Integer) contexto.getDatos() + " tiene actividades asignadas.\n\n");
			break;
			
		case errorBajaEmpleado_EmpleadoInactivo:
			this.textAreaEmpleado.append(
					"->ERROR: el empleado con id " + (Integer) contexto.getDatos() + " ya esta dado de baja.\n\n");
			break;
			
		case errorBajaEmpleado_EmpleadoInexistente:
			this.textAreaEmpleado.append(
					"->ERROR: el empleado con id " + (Integer) contexto.getDatos() + " no existe en el sistema.\n\n");
			break;
			
		case errorBajaEmpleadoOptimisticLockException:
			this.textAreaEmpleado.append("->ERROR: el empleado existe en el sistema pero no se ha podido completar la "
					+ "operacion debido a que otro usuario estaba modificando el mismo empleado.\n\n");
			break;
			
		case errorConexionBBDD:
			this.textAreaEmpleado.append("->ERROR: no ha sido posible conectarse a la BBDD.\n\n");
			break;
			
		case errorDesasignarActividad_ActividadInactiva:
			this.textAreaEmpleado.append("->ERROR: no se ha podido desasignar la actividad con id "
					+ ((AsignacionActividad) contexto.getDatos()).getActividad().getId()
					+ " al empleado con id "
					+ ((AsignacionActividad) contexto.getDatos()).getEmpleado().getId()
					+ " porque la actividad estaba inactiva.\n\n");
			break;
			
		case errorDesasignarActividad_ActividadInexistente:
			this.textAreaEmpleado.append("->ERROR: no se ha podido desasignar la actividad con id "
					+ ((AsignacionActividad) contexto.getDatos()).getActividad().getId()
					+ " al empleado con id "
					+ ((AsignacionActividad) contexto.getDatos()).getEmpleado().getId()
					+ " porque la actividad no existe en el sistema.\n\n");
			break;
			
		case errorDesasignarActividad_AsignacionInexistente:
			this.textAreaEmpleado.append("->ERROR: no se ha podido desasignar la actividad con id "
					+ ((AsignacionActividad) contexto.getDatos()).getActividad().getId()
					+ " al empleado con id "
					+ ((AsignacionActividad) contexto.getDatos()).getEmpleado().getId()
					+ " porque la asignacion no existe en el sistema.\n\n");
			break;
			 
		case errorDesasignarActividad_EmpleadoInactivo:
			this.textAreaEmpleado.append("->ERROR: no se ha podido desasignar la actividad con id "
					+ ((AsignacionActividad) contexto.getDatos()).getActividad().getId()
					+ " al empleado con id "
					+ ((AsignacionActividad) contexto.getDatos()).getEmpleado().getId()
					+ " porque el empleado estaba inactivo.\n\n");
			break;
			
		case errorDesasignarActividad_EmpleadoInexistente:
			this.textAreaEmpleado.append("->ERROR: no se ha podido desasignar la actividad con id "
					+ ((AsignacionActividad) contexto.getDatos()).getActividad().getId()
					+ " al empleado con id "
					+ ((AsignacionActividad) contexto.getDatos()).getEmpleado().getId()
					+ " porque el empleado no existe en el sistema.\n\n");
			break;
			
		case errorDesasignarActividadOptimisticLockException:
			this.textAreaEmpleado.append("->ERROR: no se ha podido desasignar la actividad con id "
					+ ((AsignacionActividad) contexto.getDatos()).getActividad().getId()
					+ " al empleado con id "
					+ ((AsignacionActividad) contexto.getDatos()).getEmpleado().getId()
					+ " debido a que otro usuario estaba modificando la actividad o el empleado.\n\n");
			break;
			
		case errorMostrarUnEmpleadoNoExiste:
			vaciarTablaEmpleados();
			this.textAreaEmpleado.append("->No existe ningun empleado con id " + (Integer) (contexto.getDatos()) + " en el sistema.\n\n");
			break;
			
		case errorMostrarTodosEmpleadosNoExisteNinguno:
			vaciarTablaEmpleados();
			this.textAreaEmpleado.append("->No existe ningun empleado en el sistema.\n\n");
			break;
			
		case desasignarActividadOK:
			this.textAreaEmpleado.append("->Actividad con id "
					+ ((AsignacionActividad)contexto.getDatos()).getActividad().getId()
					+ " desasignada al empleado con id "
					+ ((AsignacionActividad)contexto.getDatos()).getEmpleado().getId()
					+ ".\n\n");
			break;
			
		case mostrarUnEmpleadoOK:
			rellenarTablaConUnEmpleado((Empleado) contexto.getDatos());
			rellenarTablaAsignacionesActividad(((Empleado) contexto.getDatos()).getAsignaciones());
			CardLayout cl6 = (CardLayout) (panelBotonesCasosDeUso.getLayout());
			cl6.show(panelBotonesCasosDeUso, "Asignaciones");
			break;

		case mostrarTodosEmpleadosOK:
			rellenarTablaConEmpleados((List<Empleado>) contexto.getDatos());
			break;
			
		default:
			break;
		}
	}

	// ---------------------------------

	private void rellenarTablaConUnEmpleado(Empleado empleado) {
		datosTabla = new Object[1][10];
		datosTabla[0][0] = empleado.getId();
		datosTabla[0][1] = empleado.getNombre();
		datosTabla[0][2] = empleado.getDNI();
		datosTabla[0][3] = empleado.getDepartamento().getId();
		datosTabla[0][4] = empleado.getTelefono();
		datosTabla[0][5] = empleado.getActivo();
		if (empleado instanceof EmpleadoFijo) {
			datosTabla[0][6] = ((EmpleadoFijo) empleado).getSueldoMensual();
			datosTabla[0][7] = 100.0 * ((EmpleadoFijo) empleado).getImpuestos();
			datosTabla[0][8] = null;
			datosTabla[0][9] = null;
		} else if (empleado instanceof EmpleadoTemporal) {
			datosTabla[0][6] = null;
			datosTabla[0][7] = null;
			datosTabla[0][8] = ((EmpleadoTemporal) empleado).getSalarioPorHora();
			datosTabla[0][9] = ((EmpleadoTemporal) empleado).getHorasMes();
		}

		setTablaEmpleadosConfiguration();
	}

	private void rellenarTablaAsignacionesActividad(List<AsignacionActividad> asignacionesActividad) {
		datosTabla = new Object[asignacionesActividad.size()][4];
		int i = 0;

		for (AsignacionActividad asignacionActividad : asignacionesActividad) {
			datosTabla[i][0] = asignacionActividad.getActividad().getId();
			datosTabla[i][1] = asignacionActividad.getActividad().getNombre();
			datosTabla[i][2] = asignacionActividad.getActividad().getLugar();
			datosTabla[i][3] = asignacionActividad.getHoras();
			++i;
		}

		tablaAsignaciones.setModel(
				new DefaultTableModel(datosTabla, new String[] { "ID", "Nombre", "Lugar", "Horas asignadas" }) {

					private static final long serialVersionUID = 7482486286317006320L;

					Class[] types = new Class[] { Integer.class, String.class, String.class, Integer.class };

					public Class getColumnClass(int columnIndex) {
						return types[columnIndex];
					}

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return false;
					}
				});

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaAsignaciones.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tablaAsignaciones.getColumnModel().getColumn(0).setMaxWidth(35);
		tablaAsignaciones.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tablaAsignaciones.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tablaAsignaciones.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	}

	private void rellenarTablaConEmpleados(List<Empleado> empleados) {
		
		
		
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
		
		datosTabla = new Object[empleados.size()][10];
		int i = 0;

		for (Empleado empleado : empleados) {
			datosTabla[i][0] = empleado.getId();
			datosTabla[i][1] = empleado.getNombre();
			datosTabla[i][2] = empleado.getDNI();
			datosTabla[i][3] = empleado.getDepartamento().getId();
			datosTabla[i][4] = empleado.getTelefono();
			datosTabla[i][5] = empleado.getActivo();
			if (empleado instanceof EmpleadoFijo) {
				datosTabla[i][6] = ((EmpleadoFijo) empleado).getSueldoMensual();
				datosTabla[i][7] = 100 * ((EmpleadoFijo) empleado).getImpuestos();
				datosTabla[i][8] = "------";
				datosTabla[i][9] = "------";
			} else if (empleado instanceof EmpleadoTemporal) {
				datosTabla[i][6] = "------";
				datosTabla[i][7] = "------";
				datosTabla[i][8] = ((EmpleadoTemporal) empleado).getSalarioPorHora();
				datosTabla[i][9] = ((EmpleadoTemporal) empleado).getHorasMes();
			}

			++i;
		}

		setTablaEmpleadosConfiguration();
	}

	public void setTablaEmpleadosConfiguration() {
		tablaEmpleados.setModel(new DefaultTableModel(datosTabla, new String[] { "Id", "Nombre", "DNI", "Departamento",
				"Telefono", "Activo", "Sueldo Mensual", "Impuestos (%)", "Salario por hora", "Horas mensuales" }) {

			private static final long serialVersionUID = -4826319740640786334L;

			Class[] types = new Class[] { Integer.class, String.class, String.class, Integer.class, Integer.class,
					Boolean.class, Double.class, Double.class, Double.class, Integer.class };

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
		tablaEmpleados.getColumnModel().getColumn(1).setMaxWidth(50);
		tablaEmpleados.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tablaEmpleados.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		tablaEmpleados.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		tablaEmpleados.getColumnModel().getColumn(5).setMaxWidth(45);
		tablaEmpleados.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
		
		
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
		
		tablaEmpleados.getColumnModel().getColumn(7).setCellRenderer(doubleRenderer);
		tablaEmpleados.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
		tablaEmpleados.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
		tablaEmpleados.setMaximumSize(new Dimension(300, 104));
		tablaEmpleados.setMinimumSize(new Dimension(300, 104));
		tablaEmpleados.setOpaque(false);
		tablaEmpleados.getTableHeader().setReorderingAllowed(false);
		jScrollPane1.setViewportView(tablaEmpleados);
	}

	private void vaciarTablaEmpleados() {
		DefaultTableModel model = (DefaultTableModel) tablaEmpleados.getModel();
		model.setRowCount(0);
	}

	public GUIMenuEmpleado() {
		initGUI();
	}
	
	

	@SuppressWarnings({ "unchecked", "serial" })
	private void initGUI() {

		jScrollPane1 = new JScrollPane();
		tablaEmpleados = new JTable();
		tablaAsignaciones = new JTable();

		jScrollPane2 = new JScrollPane();

		textAreaEmpleado = new JTextArea();
		panelBotones = new JPanel();
		panelBotonesCasosDeUso = new JPanel();
		botonAltaEmpleado = new JButton();
		botonBajaEmpleado = new JButton();
		botonActualizarEmpleado = new JButton();
		botonMostrarUnEmpleado = new JButton();
		botonAsignarActividad = new JButton();
		botonDesasignarActividad = new JButton();
		botonMostrarTodosEmpleados = new JButton();
		botonVolver = new JButton();
		panelBotonesCasosDeUso = new JPanel();

		altaFijoPanel = new JPanel();
		altaFijoPanel.setBorder(BorderFactory.createTitledBorder("Alta de un nuevo empleado fijo"));
		inicializarComponentesPanelAltaEmpleadoFijo();

		altaTemporalPanel = new JPanel();
		altaTemporalPanel.setBorder(BorderFactory.createTitledBorder("Alta de un nuevo empleado temporal"));
		inicializarComponentesPanelAltaEmpleadoTemporal();

		seleccionTipoAltaPanel = new JPanel();
		seleccionTipoAltaPanel.setBorder(BorderFactory.createTitledBorder("Seleccione el tipo de empleado"));
		inicializarComponentesPanelSeleccionTipoEmpleadoAlta();

		actualizarFijoPanel = new JPanel();
		actualizarFijoPanel.setBorder(BorderFactory.createTitledBorder("Actualizar un empleado fijo"));
		inicializarComponentesPanelActualizarEmpleadoFijo();
		
		actualizarTemporalPanel = new JPanel();
		actualizarTemporalPanel.setBorder(BorderFactory.createTitledBorder("Actualizar un empleado temporal"));
		inicializarComponentesPanelActualizarEmpleadoTemporal();

		seleccionTipoActualizarPanel = new JPanel();
		seleccionTipoActualizarPanel.setBorder(BorderFactory.createTitledBorder("Seleccione el tipo de empleado"));
		inicializarComponentesPanelSeleccionTipoEmpleadoActualizar();

		bajaPanel = new JPanel();
		bajaPanel.setBorder(BorderFactory.createTitledBorder("Dar de baja un empleado"));
		inicializarComponentesPanelBaja();

		mostrarUnoPanel = new JPanel();
		mostrarUnoPanel.setBorder(BorderFactory.createTitledBorder("Mostrar un empleado"));
		inicializarComponentesPanelMostrarUno();
		
		asignarActividadPanel = new JPanel();
		asignarActividadPanel.setBorder(BorderFactory.createTitledBorder("Asignar una actividad"));
		inicializarComponentesPanelAsignarActividad();
		
		desasignarActividadPanel = new JPanel();
		desasignarActividadPanel.setBorder(BorderFactory.createTitledBorder("Desasignar una actividad"));
		inicializarComponentesPanelDesasignarActividad();
		
		
		setTablaEmpleadosConfiguration();

		tablaAsignacionesPanel = new JPanel();
		tablaAsignacionesPanel.setBorder(BorderFactory.createTitledBorder("Asignaciones"));
		inicializarComponentesPanelAsignaciones();

		textAreaEmpleado.setColumns(30);
		textAreaEmpleado.setRows(5);
		textAreaEmpleado.setMaximumSize(new Dimension(164, 114));
		textAreaEmpleado.setMinimumSize(new Dimension(164, 114));
		jScrollPane2.setViewportView(textAreaEmpleado);

		panelBotones.setMaximumSize(new Dimension(411, 242));
		panelBotones.setMinimumSize(new Dimension(411, 242));
		panelBotones.setOpaque(false);
		panelBotones.setPreferredSize(new Dimension(411, 242));
		panelBotones.setLayout(new GridLayout(5, 2));

		botonAltaEmpleado.setText("Alta");
		botonAltaEmpleado.setPreferredSize(new Dimension(150, 45));
		botonAltaEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				CardLayout cl3 = (CardLayout) (panelBotonesCasosDeUso.getLayout());
				cl3.show(panelBotonesCasosDeUso, "SeleccionTipoAlta");
			}
		});
		panelBotones.add(botonAltaEmpleado);

		botonBajaEmpleado.setText("Baja");
		botonBajaEmpleado.setPreferredSize(new Dimension(150, 45));
		botonBajaEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bajaButtonActionPerformed();
			}
		});

		panelBotones.add(botonBajaEmpleado);

		botonActualizarEmpleado.setText("Actualizar");
		botonActualizarEmpleado.setPreferredSize(new Dimension(150, 45));
		botonActualizarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				CardLayout cl3 = (CardLayout) (panelBotonesCasosDeUso.getLayout());
				cl3.show(panelBotonesCasosDeUso, "SeleccionTipoActualizar");
			}
		});
		panelBotones.add(botonActualizarEmpleado);

		botonMostrarUnEmpleado.setText("Mostrar uno");
		botonMostrarUnEmpleado.setPreferredSize(new Dimension(150, 45));
		botonMostrarUnEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mostrarUnoButtonActionPerformed();
			}
		});
		panelBotones.add(botonMostrarUnEmpleado);

		botonAsignarActividad.setText("Asignar actividad");
		botonAsignarActividad.setPreferredSize(new Dimension(150, 45));
		botonAsignarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				asignarActivivdadButtonActionPerformed();
			}
		});
		panelBotones.add(botonAsignarActividad);

		botonDesasignarActividad.setText("Desasignar actividad");
		botonDesasignarActividad.setPreferredSize(new Dimension(150, 45));
		botonDesasignarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				desasignarActivivdadButtonActionPerformed();
			}
		});
		panelBotones.add(botonDesasignarActividad);

		botonMostrarTodosEmpleados.setText("Mostrar Todos");
		botonMostrarTodosEmpleados.setPreferredSize(new Dimension(150, 45));
		botonMostrarTodosEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mostrarTodasButtonActionPerformed();
			}
		});
		panelBotones.add(botonMostrarTodosEmpleados);

		botonVolver.setText("Volver");
		botonVolver.setPreferredSize(new Dimension(150, 45));

		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				menuPrincipalButtonActionPerformed();
			}
		});
		panelBotones.add(botonVolver);

		panelBotonesCasosDeUso.setMaximumSize(new Dimension(411, 242));
		panelBotonesCasosDeUso.setMinimumSize(new Dimension(411, 242));
		panelBotonesCasosDeUso.setPreferredSize(new Dimension(411, 242));
		panelBotonesCasosDeUso.setLayout(new CardLayout());

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addComponent(jScrollPane1).addGap(18, 18, 18)
										.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 279,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(panelBotones, GroupLayout.PREFERRED_SIZE, 415,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
										.addComponent(panelBotonesCasosDeUso, GroupLayout.PREFERRED_SIZE,
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
										.addComponent(panelBotones, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE))
								.addComponent(panelBotonesCasosDeUso, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));

		textAreaEmpleado.setLineWrap(true);
		textAreaEmpleado.setWrapStyleWord(true);
		panelBotonesCasosDeUso.add(new JPanel());
		panelBotonesCasosDeUso.add(altaTemporalPanel, "AltaEmpleadoTemporal");
		panelBotonesCasosDeUso.add(altaFijoPanel, "AltaEmpleadoFijo");
		panelBotonesCasosDeUso.add(actualizarTemporalPanel, "ActualizarEmpleadoTemporal");
		panelBotonesCasosDeUso.add(actualizarFijoPanel, "ActualizarEmpleadoFijo");
		panelBotonesCasosDeUso.add(seleccionTipoAltaPanel, "SeleccionTipoAlta");
		panelBotonesCasosDeUso.add(seleccionTipoActualizarPanel, "SeleccionTipoActualizar");
		panelBotonesCasosDeUso.add(asignarActividadPanel, "AsignarActividad");
		panelBotonesCasosDeUso.add(desasignarActividadPanel, "DesasignarActividad");
		panelBotonesCasosDeUso.add(bajaPanel, "Baja");
		panelBotonesCasosDeUso.add(mostrarUnoPanel, "Mostrar");
		panelBotonesCasosDeUso.add(tablaAsignacionesPanel, "Asignaciones");
		this.setSize(new Dimension(1130, 520));
		// pack();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// </editor-fold>

	private void inicializarComponentesPanelAltaEmpleadoFijo() {

		labelDepartamentoAltaFijo = new JLabel();
		textDepartamentoAltaFijo = new JTextField();
		labelNombreAltaFijo = new javax.swing.JLabel();
		textNombreAltaFijo = new javax.swing.JTextField();
		labelTelefonoAltaFijo = new javax.swing.JLabel();
		textTelefonoAltaFijo = new javax.swing.JTextField();
		labelDNIAltaFijo = new javax.swing.JLabel();
		textDNIAltaFijo = new javax.swing.JTextField();
		labelSueldoMensualAltaFijo = new JLabel();
		textSueldoMensualAltaFijo = new JTextField();
		labelImpuestosAltaFijo = new JLabel();
		textImpuestosAltaFijo = new JTextField();
		botonAceptarAltaFijo = new javax.swing.JButton();
		botonCancelarAltaFijo = new javax.swing.JButton();

		altaFijoPanel.setMaximumSize(new java.awt.Dimension(411, 242));
		altaFijoPanel.setMinimumSize(new java.awt.Dimension(411, 252));
		altaFijoPanel.setLayout(new GridLayout(7, 2));

		labelDNIAltaFijo.setText("DNI");
		altaFijoPanel.add(labelDNIAltaFijo);
		altaFijoPanel.add(textDNIAltaFijo);

		labelDepartamentoAltaFijo.setText("Departamento");
		altaFijoPanel.add(labelDepartamentoAltaFijo);
		altaFijoPanel.add(textDepartamentoAltaFijo);

		labelNombreAltaFijo.setText("Nombre");
		altaFijoPanel.add(labelNombreAltaFijo);
		altaFijoPanel.add(textNombreAltaFijo);

		labelTelefonoAltaFijo.setText("Telefono");
		altaFijoPanel.add(labelTelefonoAltaFijo);
		altaFijoPanel.add(textTelefonoAltaFijo);

		labelSueldoMensualAltaFijo.setText("Sueldo mensual");
		altaFijoPanel.add(labelSueldoMensualAltaFijo);
		altaFijoPanel.add(textSueldoMensualAltaFijo);

		labelImpuestosAltaFijo.setText("Impuestos (%)");
		altaFijoPanel.add(labelImpuestosAltaFijo);
		altaFijoPanel.add(textImpuestosAltaFijo);

		botonAceptarAltaFijo.setText("Aceptar");
		botonAceptarAltaFijo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				aceptarAltaEmpleadoFijoButtonActionPerformed();
			}
		});
		altaFijoPanel.add(botonAceptarAltaFijo);

		botonCancelarAltaFijo.setText("Cancelar");
		botonCancelarAltaFijo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelarAltaEmpleadoFijoButtonActionPerformed();
			}
		});
		altaFijoPanel.add(botonCancelarAltaFijo);
	}

	
	
	private void inicializarComponentesPanelAltaEmpleadoTemporal() {

		labelDepartamentoAltaTemporal = new JLabel();
		textDepartamentoAltaTemporal = new JTextField();
		labelNombreAltaTemporal = new javax.swing.JLabel();
		textNombreAltaTemporal = new javax.swing.JTextField();
		labelTelefonoAltaTemporal = new javax.swing.JLabel();
		textTelefonoAltaTemporal = new javax.swing.JTextField();
		labelDNIAltaTemporal = new javax.swing.JLabel();
		textDNIAltaTemporal = new javax.swing.JTextField();
		labelSalarioPorHoraAltaTemporal = new JLabel();
		textSalarioPorHoraAltaTemporal = new JTextField();
		labelHorasMesAltaTemporal = new JLabel();
		textHorasMesAltaTemporal = new JTextField();
		botonAceptarAltaTemporal = new javax.swing.JButton();
		botonCancelarAltaTemporal = new javax.swing.JButton();

		altaTemporalPanel.setMaximumSize(new java.awt.Dimension(411, 242));
		altaTemporalPanel.setMinimumSize(new java.awt.Dimension(411, 252));
		altaTemporalPanel.setLayout(new GridLayout(7, 2));

		labelDNIAltaTemporal.setText("DNI");
		altaTemporalPanel.add(labelDNIAltaTemporal);
		altaTemporalPanel.add(textDNIAltaTemporal);

		labelDepartamentoAltaTemporal.setText("Departamento");
		altaTemporalPanel.add(labelDepartamentoAltaTemporal);
		altaTemporalPanel.add(textDepartamentoAltaTemporal);

		labelNombreAltaTemporal.setText("Nombre");
		altaTemporalPanel.add(labelNombreAltaTemporal);
		altaTemporalPanel.add(textNombreAltaTemporal);

		labelTelefonoAltaTemporal.setText("Telefono");
		altaTemporalPanel.add(labelTelefonoAltaTemporal);
		altaTemporalPanel.add(textTelefonoAltaTemporal);

		labelSalarioPorHoraAltaTemporal.setText("Salario/hora");
		altaTemporalPanel.add(labelSalarioPorHoraAltaTemporal);
		altaTemporalPanel.add(textSalarioPorHoraAltaTemporal);

		labelHorasMesAltaTemporal.setText("Horas mensuales");
		altaTemporalPanel.add(labelHorasMesAltaTemporal);
		altaTemporalPanel.add(textHorasMesAltaTemporal);

		botonAceptarAltaTemporal.setText("Aceptar");
		botonAceptarAltaTemporal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				aceptarAltaEmpleadoTemporalButtonActionPerformed();
			}
		});
		altaTemporalPanel.add(botonAceptarAltaTemporal);

		botonCancelarAltaTemporal.setText("Cancelar");
		botonCancelarAltaTemporal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelarAltaEmpleadoTemporalButtonActionPerformed();
			}
		});
		altaTemporalPanel.add(botonCancelarAltaTemporal);
	}

	private void inicializarComponentesPanelSeleccionTipoEmpleadoAlta() {

		tipoFijoAltaButton = new JButton("Fijo");
		tipoTemporalAltaButton = new JButton("Temporal");
		cancelarSeleccionTipoAltaButton = new JButton("Cancelar");

		seleccionTipoAltaPanel.setPreferredSize(new java.awt.Dimension(455, 242));
		seleccionTipoAltaPanel.setSize(new java.awt.Dimension(455, 242));

		tipoFijoAltaButton.setText("Fijo");

		tipoTemporalAltaButton.setText("Temporal");

		cancelarSeleccionTipoAltaButton.setText("Cancelar");

		javax.swing.GroupLayout seleccionTipoAltaPanelLayout = new javax.swing.GroupLayout(seleccionTipoAltaPanel);
		seleccionTipoAltaPanel.setLayout(seleccionTipoAltaPanelLayout);
		seleccionTipoAltaPanelLayout.setHorizontalGroup(seleccionTipoAltaPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(seleccionTipoAltaPanelLayout.createSequentialGroup().addContainerGap(33, Short.MAX_VALUE)
						.addComponent(tipoFijoAltaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(tipoTemporalAltaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(cancelarSeleccionTipoAltaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(25, 25, 25)));
		seleccionTipoAltaPanelLayout.setVerticalGroup(seleccionTipoAltaPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(seleccionTipoAltaPanelLayout.createSequentialGroup().addGap(78, 78, 78)
						.addGroup(seleccionTipoAltaPanelLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(tipoFijoAltaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(tipoTemporalAltaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cancelarSeleccionTipoAltaButton, javax.swing.GroupLayout.PREFERRED_SIZE,
										64, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(100, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout
						.createSequentialGroup().addGap(127, 127, 127).addComponent(seleccionTipoAltaPanel,
								javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(202, Short.MAX_VALUE)));

		tipoFijoAltaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				altaFijoButtonActionPerformed();
			}
		});

		tipoTemporalAltaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				altaTemporalButtonActionPerformed();
			}
		});

		cancelarSeleccionTipoAltaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarSeleccionTipoAltaActionPerformed();

			}
		});
	}

	private void inicializarComponentesPanelActualizarEmpleadoFijo() {

		labelIDActualizarFijo = new JLabel();
		textIdActualizarFijo = new JTextField();
		labelDepartamentoActualizarFijo = new JLabel();
		textDepartamentoActualizarFijo = new JTextField();
		labelNombreActualizarFijo = new javax.swing.JLabel();
		textNombreActualizarFijo = new javax.swing.JTextField();
		labelTelefonoActualizarFijo = new javax.swing.JLabel();
		textTelefonoActualizarFijo = new javax.swing.JTextField();
		labelDNIActualizarFijo = new javax.swing.JLabel();
		textDNIActualizarFijo = new javax.swing.JTextField();
		labelSueldoMensualActualizarFijo = new JLabel();
		textSueldoMensualActualizarFijo = new JTextField();
		labelImpuestosActualizarFijo = new JLabel();
		textImpuestosActualizarFijo = new JTextField();
		botonAceptarActualizarFijo = new javax.swing.JButton();
		botonCancelarActualizarFijo = new javax.swing.JButton();

		actualizarFijoPanel.setMaximumSize(new java.awt.Dimension(411, 242));
		actualizarFijoPanel.setMinimumSize(new java.awt.Dimension(411, 252));
		actualizarFijoPanel.setLayout(new GridLayout(8, 2));

		labelIDActualizarFijo.setText("ID");
		actualizarFijoPanel.add(labelIDActualizarFijo);
		actualizarFijoPanel.add(textIdActualizarFijo);

		labelDNIActualizarFijo.setText("DNI");
		actualizarFijoPanel.add(labelDNIActualizarFijo);
		actualizarFijoPanel.add(textDNIActualizarFijo);

		labelDepartamentoActualizarFijo.setText("Departamento");
		actualizarFijoPanel.add(labelDepartamentoActualizarFijo);
		actualizarFijoPanel.add(textDepartamentoActualizarFijo);

		labelNombreActualizarFijo.setText("Nombre");
		actualizarFijoPanel.add(labelNombreActualizarFijo);
		actualizarFijoPanel.add(textNombreActualizarFijo);

		labelTelefonoActualizarFijo.setText("Telefono");
		actualizarFijoPanel.add(labelTelefonoActualizarFijo);
		actualizarFijoPanel.add(textTelefonoActualizarFijo);

		labelSueldoMensualActualizarFijo.setText("Sueldo mensual");
		actualizarFijoPanel.add(labelSueldoMensualActualizarFijo);
		actualizarFijoPanel.add(textSueldoMensualActualizarFijo);

		labelImpuestosActualizarFijo.setText("Impuestos (%)");
		actualizarFijoPanel.add(labelImpuestosActualizarFijo);
		actualizarFijoPanel.add(textImpuestosActualizarFijo);

		botonAceptarActualizarFijo.setText("Aceptar");
		botonAceptarActualizarFijo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				aceptarActualizarEmpleadoFijoButtonActionPerformed();
			}
		});
		actualizarFijoPanel.add(botonAceptarActualizarFijo);

		botonCancelarActualizarFijo.setText("Cancelar");
		botonCancelarActualizarFijo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelarActualizarEmpleadoFijoButtonActionPerformed();
			}
		});
		actualizarFijoPanel.add(botonCancelarActualizarFijo);
	}

	private void inicializarComponentesPanelActualizarEmpleadoTemporal() {

		labelIDActualizarTemporal = new JLabel();
		textIdActualizarTemporal = new JTextField();
		labelDepartamentoActualizarTemporal = new JLabel();
		textDepartamentoActualizarTemporal = new JTextField();
		labelNombreActualizarTemporal = new javax.swing.JLabel();
		textNombreActualizarTemporal = new javax.swing.JTextField();
		labelTelefonoActualizarTemporal = new javax.swing.JLabel();
		textTelefonoActualizarTemporal = new javax.swing.JTextField();
		labelDNIActualizarTemporal = new javax.swing.JLabel();
		textDNIActualizarTemporal = new javax.swing.JTextField();
		labelSalarioPorHoraActualizarTemporal = new JLabel();
		textSalarioPorHoraActualizarTemporal = new JTextField();
		labelHorasMesActualizarTemporal = new JLabel();
		textHorasMesActualizarTemporal = new JTextField();
		botonAceptarActualizarTemporal = new javax.swing.JButton();
		botonCancelarActualizarTemporal = new javax.swing.JButton();

		actualizarTemporalPanel.setMaximumSize(new java.awt.Dimension(411, 242));
		actualizarTemporalPanel.setMinimumSize(new java.awt.Dimension(411, 252));
		actualizarTemporalPanel.setLayout(new GridLayout(8, 2));

		
		labelIDActualizarTemporal.setText("ID");
		actualizarTemporalPanel.add(labelIDActualizarTemporal);
		actualizarTemporalPanel.add(textIdActualizarTemporal);
		
		labelDNIActualizarTemporal.setText("DNI");
		actualizarTemporalPanel.add(labelDNIActualizarTemporal);
		actualizarTemporalPanel.add(textDNIActualizarTemporal);

		labelDepartamentoActualizarTemporal.setText("Departamento");
		actualizarTemporalPanel.add(labelDepartamentoActualizarTemporal);
		actualizarTemporalPanel.add(textDepartamentoActualizarTemporal);

		labelNombreActualizarTemporal.setText("Nombre");
		actualizarTemporalPanel.add(labelNombreActualizarTemporal);
		actualizarTemporalPanel.add(textNombreActualizarTemporal);

		labelTelefonoActualizarTemporal.setText("Telefono");
		actualizarTemporalPanel.add(labelTelefonoActualizarTemporal);
		actualizarTemporalPanel.add(textTelefonoActualizarTemporal);

		labelSalarioPorHoraActualizarTemporal.setText("Salario/hora");
		actualizarTemporalPanel.add(labelSalarioPorHoraActualizarTemporal);
		actualizarTemporalPanel.add(textSalarioPorHoraActualizarTemporal);

		labelHorasMesActualizarTemporal.setText("Horas mensuales");
		actualizarTemporalPanel.add(labelHorasMesActualizarTemporal);
		actualizarTemporalPanel.add(textHorasMesActualizarTemporal);

		botonAceptarActualizarTemporal.setText("Aceptar");
		botonAceptarActualizarTemporal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				aceptarActualizarEmpleadoTemporalButtonActionPerformed();
			}
		});
		actualizarTemporalPanel.add(botonAceptarActualizarTemporal);

		botonCancelarActualizarTemporal.setText("Cancelar");
		botonCancelarActualizarTemporal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cancelarActualizarEmpleadoTemporalButtonActionPerformed();
			}
		});
		actualizarTemporalPanel.add(botonCancelarActualizarTemporal);
	}

	private void inicializarComponentesPanelSeleccionTipoEmpleadoActualizar() {
		GridBagConstraints gridBagConstraints;

		tipoFijoActualizarButton = new JButton("Fijo");
		tipoTemporalActualizarButton = new JButton("Temporal");
		cancelarSeleccionTipoActualizarButton = new JButton("Cancelar");

		seleccionTipoActualizarPanel.setPreferredSize(new java.awt.Dimension(455, 242));
		seleccionTipoActualizarPanel.setSize(new java.awt.Dimension(455, 242));

		javax.swing.GroupLayout seleccionTipoActualizarPanelLayout = new javax.swing.GroupLayout(
				seleccionTipoActualizarPanel);
		seleccionTipoActualizarPanel.setLayout(seleccionTipoActualizarPanelLayout);
		seleccionTipoActualizarPanelLayout.setHorizontalGroup(
				seleccionTipoActualizarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(seleccionTipoActualizarPanelLayout.createSequentialGroup()
								.addContainerGap(33, Short.MAX_VALUE)
								.addComponent(tipoFijoActualizarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(tipoTemporalActualizarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cancelarSeleccionTipoActualizarButton,
										javax.swing.GroupLayout.PREFERRED_SIZE, 112,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(25, 25, 25)));
		seleccionTipoActualizarPanelLayout.setVerticalGroup(seleccionTipoActualizarPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(seleccionTipoActualizarPanelLayout.createSequentialGroup().addGap(78, 78, 78)
						.addGroup(seleccionTipoActualizarPanelLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(tipoFijoActualizarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(tipoTemporalActualizarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cancelarSeleccionTipoActualizarButton,
										javax.swing.GroupLayout.PREFERRED_SIZE, 64,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(100, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout
						.createSequentialGroup().addGap(127, 127, 127).addComponent(seleccionTipoActualizarPanel,
								javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(202, Short.MAX_VALUE)));
		tipoFijoActualizarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				actualizarFijoButtonActionPerformed();
			}
		});

		tipoTemporalActualizarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				actualizarTemporalButtonActionPerformed();
			}
		});

		cancelarSeleccionTipoActualizarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarSeleccionTipoActualizarActionPerformed();

			}
		});

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
		tablaAsignaciones = new JTable();
		cerrarTablaEmpleadosButton = new JButton();

		tablaAsignacionesPanel.setMaximumSize(new Dimension(411, 242));
		tablaAsignacionesPanel.setMinimumSize(new Dimension(411, 242));
		tablaAsignacionesPanel.setLayout(new GridBagLayout());

		tablaAsignaciones.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "DNI", "Nombre", "Telefono", "Activo" }) {

					private static final long serialVersionUID = -5191374889542656332L;

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return false;
					}
				});
		tablaAsignaciones.getTableHeader().setReorderingAllowed(false);
		jScrollPane1Empleados.setViewportView(tablaAsignaciones);
		if (tablaAsignaciones.getColumnModel().getColumnCount() > 0) {
			tablaAsignaciones.getColumnModel().getColumn(0).setResizable(false);
			tablaAsignaciones.getColumnModel().getColumn(1).setResizable(false);
			tablaAsignaciones.getColumnModel().getColumn(2).setResizable(false);
			tablaAsignaciones.getColumnModel().getColumn(3).setResizable(false);
			tablaAsignaciones.getColumnModel().getColumn(4).setResizable(false);
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
	
	private void inicializarComponentesPanelAsignarActividad() {
		labelIDEmpleadoAsignarActividad = new javax.swing.JLabel();
        textIdEmpleadoAsignarActividad = new javax.swing.JTextField();
        labelIDActividadAsignarActividad = new javax.swing.JLabel();
        textIdActividadAsignarActividad = new javax.swing.JTextField();
        textHorasAsignarActividad = new javax.swing.JTextField();
        labelHorasAsignarActividad = new javax.swing.JLabel();
        botonAceptarAsignarActividad = new javax.swing.JButton();
        botonCancelarAsignarActividad = new javax.swing.JButton();
        
        asignarActividadPanel.setPreferredSize(new java.awt.Dimension(455, 242));
        asignarActividadPanel.setSize(new java.awt.Dimension(455, 242));

        labelIDEmpleadoAsignarActividad.setText("Empleado");
        labelIDActividadAsignarActividad.setText("Actividad");
        labelHorasAsignarActividad.setText("Horas");
        
        
        botonAceptarAsignarActividad.setText("Aceptar");
        botonAceptarAsignarActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aceptarAsignaractividadButtonActionPerformed();
				
			}
		});
        botonCancelarAsignarActividad.setText("Cancelar");
        botonCancelarAsignarActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarAsignaractividadButtonActionPerformed();
				
			}
		});	

        javax.swing.GroupLayout asignarActividadPanelLayout = new javax.swing.GroupLayout(asignarActividadPanel);
        asignarActividadPanel.setLayout(asignarActividadPanelLayout);
        asignarActividadPanelLayout.setHorizontalGroup(
            asignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(asignarActividadPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(asignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(asignarActividadPanelLayout.createSequentialGroup()
                        .addGroup(asignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(asignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelIDEmpleadoAsignarActividad)
                                .addComponent(labelIDActividadAsignarActividad))
                            .addComponent(labelHorasAsignarActividad))
                        .addGap(18, 18, 18)
                        .addGroup(asignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textHorasAsignarActividad, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(textIdEmpleadoAsignarActividad)
                            .addComponent(textIdActividadAsignarActividad)))
                    .addGroup(asignarActividadPanelLayout.createSequentialGroup()
                        .addComponent(botonAceptarAsignarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonCancelarAsignarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        asignarActividadPanelLayout.setVerticalGroup(
            asignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(asignarActividadPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(asignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIDEmpleadoAsignarActividad)
                    .addComponent(textIdEmpleadoAsignarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(asignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIDActividadAsignarActividad)
                    .addComponent(textIdActividadAsignarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(asignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textHorasAsignarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHorasAsignarActividad))
                .addGap(18, 18, 18)
                .addGroup(asignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonAceptarAsignarActividad, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(botonCancelarAsignarActividad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
	}
	
	
	private void inicializarComponentesPanelDesasignarActividad() {
		labelIDEmpleadoDesasignarActividad = new javax.swing.JLabel();
        textIdEmpleadoDesasignarActividad = new javax.swing.JTextField();
        labelIDActividadDesasignarActividad = new javax.swing.JLabel();
        textIdActividadDesasignarActividad = new javax.swing.JTextField();
        botonAceptarDesasignarActividad = new javax.swing.JButton();
        botonCancelarDesasignarActividad = new javax.swing.JButton();
        
        desasignarActividadPanel.setPreferredSize(new java.awt.Dimension(455, 242));
        desasignarActividadPanel.setSize(new java.awt.Dimension(455, 242));

        labelIDEmpleadoDesasignarActividad.setText("Empleado");

        labelIDActividadDesasignarActividad.setText("Actividad");

        botonAceptarDesasignarActividad.setText("Aceptar");
        botonAceptarDesasignarActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				aceptarDesasignarActividadButtonActionPerformed();
				
			}
		});
        botonCancelarDesasignarActividad.setText("Cancelar");
        botonCancelarDesasignarActividad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelarDesasignarActividadButtonActionPerformed();
				
			}
		});	

        javax.swing.GroupLayout desasignarActividadPanelLayout = new javax.swing.GroupLayout(desasignarActividadPanel);
        desasignarActividadPanel.setLayout(desasignarActividadPanelLayout);
        desasignarActividadPanelLayout.setHorizontalGroup(
            desasignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desasignarActividadPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(desasignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(desasignarActividadPanelLayout.createSequentialGroup()
                        .addGroup(desasignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelIDEmpleadoDesasignarActividad)
                            .addComponent(labelIDActividadDesasignarActividad))
                        .addGap(18, 18, 18)
                        .addGroup(desasignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textIdEmpleadoDesasignarActividad, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(textIdActividadDesasignarActividad)))
                    .addGroup(desasignarActividadPanelLayout.createSequentialGroup()
                        .addComponent(botonAceptarDesasignarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonCancelarDesasignarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        desasignarActividadPanelLayout.setVerticalGroup(
            desasignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desasignarActividadPanelLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(desasignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIDEmpleadoDesasignarActividad)
                    .addComponent(textIdEmpleadoDesasignarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(desasignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textIdActividadDesasignarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelIDActividadDesasignarActividad))
                .addGap(41, 41, 41)
                .addGroup(desasignarActividadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonAceptarDesasignarActividad, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(botonCancelarDesasignarActividad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
	}

	private JButton botonAltaEmpleado;
	private JButton botonBajaEmpleado;
	private JButton botonActualizarEmpleado;
	private JButton botonMostrarTodosEmpleados;
	private JButton botonMostrarUnEmpleado;
	private JButton botonAsignarActividad;
	private JButton botonDesasignarActividad;
	private JButton botonVolver;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JPanel panelBotones;
	private JPanel panelBotonesCasosDeUso;
	private JTable tablaEmpleados;
	private JTextArea textAreaEmpleado;

	// Alta Empleado Fijo
	private JPanel altaFijoPanel;
	private JButton botonAceptarAltaFijo;
	private JButton botonCancelarAltaFijo;
	private JLabel labelTelefonoAltaFijo;
	private JLabel labelDNIAltaFijo;
	private JLabel labelNombreAltaFijo;
	private JLabel labelDepartamentoAltaFijo;
	private JLabel labelSueldoMensualAltaFijo;
	private JLabel labelImpuestosAltaFijo;
	private JTextField textTelefonoAltaFijo;
	private JTextField textDNIAltaFijo;
	private JTextField textNombreAltaFijo;
	private JTextField textDepartamentoAltaFijo;
	private JTextField textSueldoMensualAltaFijo;
	private JTextField textImpuestosAltaFijo;

	// Alta Empleado Temporal
	private JPanel altaTemporalPanel;
	private JButton botonAceptarAltaTemporal;
	private JButton botonCancelarAltaTemporal;
	private JLabel labelTelefonoAltaTemporal;
	private JLabel labelDNIAltaTemporal;
	private JLabel labelNombreAltaTemporal;
	private JLabel labelHorasMesAltaTemporal;
	private JLabel labelSalarioPorHoraAltaTemporal;
	private JLabel labelDepartamentoAltaTemporal;
	private JTextField textTelefonoAltaTemporal;
	private JTextField textDNIAltaTemporal;
	private JTextField textNombreAltaTemporal;
	private JTextField textDepartamentoAltaTemporal;
	private JTextField textHorasMesAltaTemporal;
	private JTextField textSalarioPorHoraAltaTemporal;

	// Decidir el tipo de empleado para el alta
	private JPanel seleccionTipoAltaPanel;
	private JButton tipoFijoAltaButton;
	private JButton tipoTemporalAltaButton;
	private JButton cancelarSeleccionTipoAltaButton;

	// Actualizar empleado fijo
	private JPanel actualizarFijoPanel;
	private JPanel actualizarBotonesPanel;
	private JButton botonCancelarActualizarFijo;
	private JButton botonAceptarActualizarFijo;
	private JLabel labelTelefonoActualizarFijo;
	private JLabel labelDepartamentoActualizarFijo;
	private JLabel labelIDActualizarFijo;
	private JLabel labelDNIActualizarFijo;
	private JLabel labelNombreActualizarFijo;

	private JLabel labelSueldoMensualActualizarFijo;
	private JLabel labelImpuestosActualizarFijo;

	private JTextField textTelefonoActualizarFijo;
	private JTextField textIdActualizarFijo;
	private JTextField textDNIActualizarFijo;
	private JTextField textNombreActualizarFijo;
	private JTextField textDepartamentoActualizarFijo;
	private JTextField textSueldoMensualActualizarFijo;
	private JTextField textImpuestosActualizarFijo;

	// Actualizar empleado temporal
	private JPanel actualizarTemporalPanel;
	private JButton botonCancelarActualizarTemporal;
	private JButton botonAceptarActualizarTemporal;
	private JLabel labelTelefonoActualizarTemporal;
	private JLabel labelDepartamentoActualizarTemporal;
	private JLabel labelIDActualizarTemporal;
	private JLabel labelDNIActualizarTemporal;
	private JLabel labelNombreActualizarTemporal;

	private JLabel labelHorasMesActualizarTemporal;
	private JLabel labelSalarioPorHoraActualizarTemporal;

	private JTextField textTelefonoActualizarTemporal;
	private JTextField textIdActualizarTemporal;
	private JTextField textDNIActualizarTemporal;
	private JTextField textNombreActualizarTemporal;
	private JTextField textDepartamentoActualizarTemporal;
	private JTextField textHorasMesActualizarTemporal;
	private JTextField textSalarioPorHoraActualizarTemporal;

	// Decidir el tipo de empleado para actualizar
	private JPanel seleccionTipoActualizarPanel;
	private JButton tipoFijoActualizarButton;
	private JButton tipoTemporalActualizarButton;
	private JButton cancelarSeleccionTipoActualizarButton;

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

	// Asignar actividad
	private JPanel asignarActividadPanel;
	private JButton botonCancelarAsignarActividad;
	private JButton botonAceptarAsignarActividad;
	private JLabel labelIDEmpleadoAsignarActividad;
	private JTextField textIdEmpleadoAsignarActividad;
	private JLabel labelIDActividadAsignarActividad;
	private JTextField textIdActividadAsignarActividad;
	private JLabel labelHorasAsignarActividad;
	private JTextField textHorasAsignarActividad;

	// Desasignar actividad
	private JPanel desasignarActividadPanel;
	private JButton botonCancelarDesasignarActividad;
	private JButton botonAceptarDesasignarActividad;
	private JLabel labelIDEmpleadoDesasignarActividad;
	private JTextField textIdEmpleadoDesasignarActividad;
	private JLabel labelIDActividadDesasignarActividad;
	private JTextField textIdActividadDesasignarActividad;

	// Tabla asignaciones
	private JPanel tablaAsignacionesPanel;
	private JScrollPane jScrollPane1Empleados;
	private JTable tablaAsignaciones;
	private JButton cerrarTablaEmpleadosButton;

	private Object[][] datosTabla;
}
