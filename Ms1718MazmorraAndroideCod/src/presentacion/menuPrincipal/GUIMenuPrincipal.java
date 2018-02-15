package presentacion.menuPrincipal;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentacion.GUI;
import presentacion.controlador.Contexto;
import presentacion.controlador.ControladorAplicacion;
import presentacion.controlador.ControladorAplicacionJPA;
import presentacion.eventos.Evento;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIMenuPrincipal extends JFrame implements GUI {

	private static final long serialVersionUID = 1L;
	private ImageIcon logoImgIcon = new ImageIcon("datos/logo.png");
	
	private JPanel contentPane;

	public GUIMenuPrincipal() {
		initGui();
	}
	/**
	 * Create the frame.
	 */
	private void initGui() {
		setTitle("Menu Principal");
		this.setIconImage(logoImgIcon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 274);
		setResizable(false);
		setVisible(true);
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		GridBagLayout gb = new GridBagLayout();
		gb.columnWidths = new int[]{0};
		gb.rowHeights = new int[]{0};
		gb.columnWeights = new double[]{1.0};
		gb.rowWeights = new double[]{1.0};
		contentPane.setLayout(gb);

		
			Imagen logo = new Imagen();
			logo.setIcon(logoImgIcon);
			GridBagConstraints gbcLogo = new GridBagConstraints();
			gbcLogo.fill = GridBagConstraints.BOTH;
			gbcLogo.insets = new Insets(0, 0, 2, 2);
			gbcLogo.gridx = 0;
			gbcLogo.gridy = 0;
			contentPane.add(logo, gbcLogo);
			logo.setMinimumSize(new Dimension(100, 100));
			logo.setMaximumSize(new Dimension(300, 300));
			
			
			JPanel daoJpaFunctionsPanel = new JPanel();
			GridBagConstraints gbcDaoJpaPanel = new GridBagConstraints();
			gbcDaoJpaPanel.fill = GridBagConstraints.HORIZONTAL;
			gbcDaoJpaPanel.insets = new Insets(0, 0, 2, 2);
			gbcDaoJpaPanel.gridx = 1;
			gbcDaoJpaPanel.gridy = 0;
			contentPane.add(daoJpaFunctionsPanel, gbcDaoJpaPanel);
			
			GridBagLayout gbl_daoJpaFunctionsPanel = new GridBagLayout();
			gbl_daoJpaFunctionsPanel.columnWidths = new int[]{0, 0, 0};
			gbl_daoJpaFunctionsPanel.rowHeights = new int[]{0, 0, 0, 0};
			gbl_daoJpaFunctionsPanel.columnWeights = new double[]{0.0, 0.0, 0.0};
			gbl_daoJpaFunctionsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
			daoJpaFunctionsPanel.setLayout(gbl_daoJpaFunctionsPanel);
			
				JLabel lblDao = new JLabel("DAO");
				lblDao.setFont(new Font("Tahoma", Font.BOLD, 36));
				GridBagConstraints gbc_lblDao = new GridBagConstraints();
				gbc_lblDao.insets = new Insets(0, 0, 4, 3);
				gbc_lblDao.gridx = 1;
				gbc_lblDao.gridy = 0;
				daoJpaFunctionsPanel.add(lblDao, gbc_lblDao);
				
					JButton btnClientes = new JButton("Clientes");
					btnClientes.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
							Contexto contexto = new Contexto(Evento.abrirMenuCliente, null);
							ControladorAplicacion.obtenerInstancia().accion(contexto);
						}
					});
					btnClientes.setFont(new Font("Tahoma", Font.PLAIN, 24));
					GridBagConstraints gbc_btnClientes = new GridBagConstraints();
					gbc_btnClientes.fill = GridBagConstraints.HORIZONTAL;
					gbc_btnClientes.insets = new Insets(0, 0, 4, 3);
					gbc_btnClientes.gridx = 0;
					gbc_btnClientes.gridy = 1;
					daoJpaFunctionsPanel.add(btnClientes, gbc_btnClientes);
					
					JButton btnProductos = new JButton("Productos");
					btnProductos.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
							Contexto contexto = new Contexto(Evento.abrirMenuProducto, null);
							ControladorAplicacion.obtenerInstancia().accion(contexto);
						}
					});
					btnProductos.setFont(new Font("Tahoma", Font.PLAIN, 24));
					GridBagConstraints gbc_btnProductos = new GridBagConstraints();
					gbc_btnProductos.fill = GridBagConstraints.HORIZONTAL;
					gbc_btnProductos.insets = new Insets(0, 0, 4, 3);
					gbc_btnProductos.gridx = 1;
					gbc_btnProductos.gridy = 1;
					daoJpaFunctionsPanel.add(btnProductos, gbc_btnProductos);
					
					JButton btnVentas = new JButton("Ventas");
					btnVentas.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
							Contexto contexto = new Contexto(Evento.abrirMenuVenta, null);
							ControladorAplicacion.obtenerInstancia().accion(contexto);
						}
					});
					btnVentas.setFont(new Font("Tahoma", Font.PLAIN, 24));
					GridBagConstraints gbc_btnVentas = new GridBagConstraints();
					gbc_btnVentas.fill = GridBagConstraints.HORIZONTAL;
					gbc_btnVentas.insets = new Insets(0, 0, 4, 3);
					gbc_btnVentas.gridx = 2;
					gbc_btnVentas.gridy = 1;
					daoJpaFunctionsPanel.add(btnVentas, gbc_btnVentas);
				
				JLabel lblJpa = new JLabel("JPA");
				lblJpa.setFont(new Font("Tahoma", Font.BOLD, 36));
				GridBagConstraints gbc_lblJpa = new GridBagConstraints();
				gbc_lblJpa.insets = new Insets(0, 0, 4, 3);
				gbc_lblJpa.gridx = 1;
				gbc_lblJpa.gridy = 2;
				daoJpaFunctionsPanel.add(lblJpa, gbc_lblJpa);
				
					JButton btnEmpleados = new JButton("Empleados");
					btnEmpleados.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
							Contexto contexto = new Contexto(Evento.abrirMenuEmpleado, null);
							ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
						}
					});
					btnEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 24));
					GridBagConstraints gbc_btnEmpleados = new GridBagConstraints();
					gbc_btnEmpleados.insets = new Insets(0, 0, 4, 3);
					gbc_btnEmpleados.gridx = 0;
					gbc_btnEmpleados.gridy = 3;
					daoJpaFunctionsPanel.add(btnEmpleados, gbc_btnEmpleados);
					
					JButton btnDepartamentos = new JButton("Departamentos");
					btnDepartamentos.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
							Contexto contexto = new Contexto(Evento.abrirMenuDepartamento, null);
							ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
						}
					});
					btnDepartamentos.setFont(new Font("Tahoma", Font.PLAIN, 24));
					GridBagConstraints gbc_btnDepartamentos = new GridBagConstraints();
					gbc_btnDepartamentos.insets = new Insets(0, 0, 4, 3);
					gbc_btnDepartamentos.gridx = 1;
					gbc_btnDepartamentos.gridy = 3;
					daoJpaFunctionsPanel.add(btnDepartamentos, gbc_btnDepartamentos);
					
					JButton btnActividades = new JButton("Actividades");
					btnActividades.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							dispose();
							Contexto contexto = new Contexto(Evento.abrirMenuActividad, null);
							ControladorAplicacionJPA.obtenerInstancia().accion(contexto);
						}
					});
					btnActividades.setFont(new Font("Tahoma", Font.PLAIN, 24));
					GridBagConstraints gbc_btnActividades = new GridBagConstraints();
					gbc_btnActividades.insets = new Insets(0, 0, 4, 3);
					gbc_btnActividades.gridx = 2;
					gbc_btnActividades.gridy = 3;
					daoJpaFunctionsPanel.add(btnActividades, gbc_btnActividades);
			
		
	}
	
	@Override
	public void actualizar(Contexto contexto) {
		// TODO Auto-generated method stub

	}

}
