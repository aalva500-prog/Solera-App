package visuals;



import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Rol;
import model.Usuario;
import services.ServicioRol;
import services.ServicioUsuario;
import Utils.Encriptar;
import Utils.FormatoTabla;
import Utils.Idioma;
import Utils.TableModel;
import Utils.UserInterfaceSuport;
import Utils.Validate;



public class Usuarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPaneTableUser = null;
	private JTable jTableUser = null;
	private JTextField jTextFieldUser = null;
	private JTextField jTextFieldNomb = null;
	private JPasswordField jPasswordFieldPass = null;
	private JPasswordField jPasswordFieldConfirm = null;
	private JLabel jLabeluse = null;
	private JLabel jLabelNomb = null;
	private JLabel jLabelRol = null;
	private JLabel jLabelPas = null;
	private JLabel jLabelConfirmar = null;
	private JButton jButtonAgreg = null;
	private JButton jButtonCerrar = null;
	private JComboBox jComboBoxRol = null;	
	private JButton jButtonModificar = null;
	protected DefaultComboBoxModel defaultComboBoxModel = null;
	private JButton jButtonEliminar = null;
	protected String nuevo = "";  //  @jve:decl-index=0:
	private JButton jButtonNuevo = null;
	private JPanel jPanel = null;
	/**
	 * This is the default constructor
	 */
	public Usuarios(Frame owner, boolean modal ) {
		initialize();
		
	}

	/**
	 * This method initializes this
	 * @return 
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(new Dimension(1413, 763));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/ico_alpha_AutoLogon_32x32.png")));
		this.setContentPane(getJContentPane());
		this.setTitle("Gestion de Usuarios");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowOpened(java.awt.event.WindowEvent e) {
				jButtonEliminar.setEnabled(false);
				jButtonAgreg.setEnabled(true);
				jButtonModificar.setEnabled(false);
				jButtonCerrar.setEnabled(true);
				jButtonNuevo.setEnabled(false);
				TableModel defaultTableModel = new TableModel();
				LinkedList<Usuario> list = new LinkedList<Usuario>();
				try {
					list = ServicioUsuario.getUsuarios();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ArrayList<Object> columnDataUser = new ArrayList<Object>();
				ArrayList<Object> columnDataNombre = new ArrayList<Object>();
				ArrayList<Object> columnDataRol = new ArrayList<Object>();
				for (int i = 0; i < list.size(); i++) {
					columnDataUser.add(list.get(i).getUsername());
					columnDataNombre.add(list.get(i).getNombre());
					columnDataRol.add(list.get(i).getRol());
					}
				defaultTableModel.setRowCount(list.size());
				if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
				defaultTableModel.addColumn("User",columnDataUser.toArray());
				defaultTableModel.addColumn("Name",columnDataNombre.toArray());
				defaultTableModel.addColumn("Rol",columnDataRol.toArray());
				}
				else
				{
					defaultTableModel.addColumn("Usuario",columnDataUser.toArray());
					defaultTableModel.addColumn("Nombre",columnDataNombre.toArray());
					defaultTableModel.addColumn("Rol",columnDataRol.toArray());
				}
					
				getJTableUser().setModel(defaultTableModel);
				getJTableUser().setDefaultRenderer (Object.class, new FormatoTabla());	
				getJTableUser().setRowHeight(20);
				jTableUser.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						int pos = jTableUser.getSelectedRow();
						try {
							LinkedList<Usuario> users = ServicioUsuario.getUsuarios();
							Usuario u = users.get(pos);
							jTextFieldUser.setText(u.getUsername());
							jTextFieldNomb.setText(u.getNombre());
							jPasswordFieldPass.setText(u.getPassword());
							jPasswordFieldConfirm.setText(u.getPassword());
							jTextFieldUser.setEditable(false);
						    //Combobox Rol
							LinkedList<Rol> list2 = new LinkedList<Rol>();
							try {
								list2 = ServicioRol.getRoles();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							DefaultComboBoxModel boxModel1 = new DefaultComboBoxModel();
							if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
								boxModel1.addElement(new String("<Select>"));
								}else{
									boxModel1.addElement(new String("<Seleccione>"));
								}
							for (int i = 0; i < list2.size(); i++) {
								boxModel1.addElement(list2.get(i));
							}
							
							getJComboBoxRol().setModel(boxModel1);
													
													
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						jButtonEliminar.setEnabled(true);
						jButtonModificar.setEnabled(true);
						jButtonAgreg.setEnabled(false);
						jButtonNuevo.setEnabled(true);				
					}
				});	
                 //Combobox Rol
				LinkedList<Rol> list2 = new LinkedList<Rol>();
				try {
					list2 = ServicioRol.getRoles();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DefaultComboBoxModel boxModel1 = new DefaultComboBoxModel();
				if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
				boxModel1.addElement(new String("<Select>"));
				}else{
					boxModel1.addElement(new String("<Seleccione>"));
				}
				for (int i = 0; i < list2.size(); i++) {
					boxModel1.addElement(list2.get(i));
				}
				
				getJComboBoxRol().setModel(boxModel1);
				
				}		
		});
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2,((screenSize.height - getHeight()) / 2));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.insets = new Insets(4, 15, 4, 19);
			gridBagConstraints11.gridy = 2;
			gridBagConstraints11.ipadx = 637;
			gridBagConstraints11.ipady = 64;
			gridBagConstraints11.gridx = 2;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints10.gridx = 2;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.ipadx = 71;
			gridBagConstraints10.ipady = 2;
			gridBagConstraints10.weightx = 1.0;
			gridBagConstraints10.insets = new Insets(0, 172, 5, 398);
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.insets = new Insets(21, 97, 33, 14);
			gridBagConstraints9.gridy = 2;
			gridBagConstraints9.ipadx = 148;
			gridBagConstraints9.ipady = 3;
			gridBagConstraints9.gridx = 1;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.insets = new Insets(21, 99, 33, 97);
			gridBagConstraints8.gridy = 2;
			gridBagConstraints8.ipadx = 139;
			gridBagConstraints8.ipady = 3;
			gridBagConstraints8.gridx = 0;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.insets = new Insets(19, 122, 31, 397);
			gridBagConstraints7.gridx = 2;
			gridBagConstraints7.gridy = 0;
			gridBagConstraints7.ipadx = 81;
			gridBagConstraints7.ipady = 3;
			gridBagConstraints7.gridheight = 2;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.insets = new Insets(18, 97, 0, 14);
			gridBagConstraints6.gridy = 0;
			gridBagConstraints6.ipadx = 160;
			gridBagConstraints6.ipady = 3;
			gridBagConstraints6.gridx = 1;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(18, 99, 0, 97);
			gridBagConstraints5.gridy = 0;
			gridBagConstraints5.ipadx = 161;
			gridBagConstraints5.ipady = 3;
			gridBagConstraints5.gridx = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.gridy = 2;
			gridBagConstraints4.ipadx = 204;
			gridBagConstraints4.ipady = 7;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.insets = new Insets(39, 97, 7, 14);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.gridy = 2;
			gridBagConstraints3.ipadx = 204;
			gridBagConstraints3.ipady = 7;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new Insets(39, 99, 7, 97);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.gridy = 1;
			gridBagConstraints2.ipadx = 204;
			gridBagConstraints2.ipady = 9;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.insets = new Insets(0, 97, 3, 14);
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.ipadx = 204;
			gridBagConstraints1.ipady = 9;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new Insets(0, 99, 3, 97);
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridwidth = 3;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 3;
			gridBagConstraints.ipadx = 914;
			gridBagConstraints.ipady = 138;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.insets = new Insets(4, 14, 15, 14);
			jLabelConfirmar = new JLabel();
			jLabelConfirmar.setText("Confirmar:");
			jLabelPas = new JLabel();
			jLabelPas.setText("Contraseña:");
			jLabelRol = new JLabel();
			jLabelRol.setText("Rol:");
			jLabelNomb = new JLabel();
			jLabelNomb.setText("Nombre:");
			jLabeluse = new JLabel();
			jLabeluse.setText("Usuario:");
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJScrollPaneTableUser(), gridBagConstraints);
			jContentPane.add(getJTextFieldUser(), gridBagConstraints1);
			jContentPane.add(getJTextFieldNomb(), gridBagConstraints2);
			jContentPane.add(getJPasswordFieldPass(), gridBagConstraints3);
			jContentPane.add(getJPasswordFieldConfirm(), gridBagConstraints4);
			jContentPane.add(jLabeluse, gridBagConstraints5);
			jContentPane.add(jLabelNomb, gridBagConstraints6);
			jContentPane.add(jLabelRol, gridBagConstraints7);
			jContentPane.add(jLabelPas, gridBagConstraints8);
			jContentPane.add(jLabelConfirmar, gridBagConstraints9);
			jContentPane.add(getJComboBoxRol(), gridBagConstraints10);
			jContentPane.add(getJPanel(), gridBagConstraints11);
			}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPaneTableUser	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneTableUser() {
		if (jScrollPaneTableUser == null) {
			jScrollPaneTableUser = new JScrollPane();
			jScrollPaneTableUser.setViewportView(getJTableUser());
		}
		return jScrollPaneTableUser;
	}

	/**
	 * This method initializes jTableUser	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getJTableUser() {
		if (jTableUser == null) {
			jTableUser = new JTable();
			}
		return jTableUser;
	}

	/**
	 * This method initializes jTextFieldUser	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldUser() {
		if (jTextFieldUser == null) {
			jTextFieldUser = new JTextField();
			Validate.validateLetter(jTextFieldUser);
		}
		return jTextFieldUser;
	}

	/**
	 * This method initializes jTextFieldNomb	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldNomb() {
		if (jTextFieldNomb == null) {
			jTextFieldNomb = new JTextField();
			Validate.validateLetter(jTextFieldNomb);
		}
		return jTextFieldNomb;
	}

	/**
	 * This method initializes jPasswordFieldPass	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordFieldPass() {
		if (jPasswordFieldPass == null) {
			jPasswordFieldPass = new JPasswordField();
		}
		return jPasswordFieldPass;
	}

	/**
	 * This method initializes jPasswordFieldConfirm	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordFieldConfirm() {
		if (jPasswordFieldConfirm == null) {
			jPasswordFieldConfirm = new JPasswordField();
		}
		return jPasswordFieldConfirm;
	}

	/**
	 * This method initializes jButtonAgreg	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonAgreg() {
		if (jButtonAgreg == null) {
			jButtonAgreg = new JButton();
			jButtonAgreg.setText("Insertar");
			jButtonAgreg.setBounds(new Rectangle(35, 22, 99, 26));
			jButtonAgreg.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_SysInfo_Unit1_ilInfoStates1_16x16.png")));
			jButtonAgreg.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(jTextFieldNomb.getText().length() > 0 && jTextFieldUser.getText().length() > 0 && jPasswordFieldConfirm.getPassword().length > 0 
							&& jPasswordFieldPass.getPassword().length > 0 && !(jComboBoxRol.getSelectedIndex()==0)){
						try {
						if( Encriptar.getMd5(new String (getJPasswordFieldPass().getPassword())).equals(Encriptar.getMd5(new String(getJPasswordFieldConfirm().getPassword()))) ){
							ServicioUsuario.insertarUsuario(getJTextFieldUser().getText(), getJPasswordFieldPass().getPassword(), getJTextFieldNomb().getText(), ((Rol)getJComboBoxRol().getSelectedItem()).getRol());

							TableModel defaultTableModel = new TableModel();
							LinkedList<Usuario> list = new LinkedList<Usuario>();
							try {
								list = ServicioUsuario.getUsuarios();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
						        e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Object> columnDataUser = new ArrayList<Object>();
							ArrayList<Object> columnDataNombre = new ArrayList<Object>();
							ArrayList<Object> columnDataRol = new ArrayList<Object>();
							for (int i = 0; i < list.size(); i++) {
								columnDataUser.add(list.get(i).getUsername());
								columnDataNombre.add(list.get(i).getNombre());
								columnDataRol.add(list.get(i).getRol());
							}
							defaultTableModel.setRowCount(list.size());
							if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
								defaultTableModel.addColumn("User",columnDataUser.toArray());
								defaultTableModel.addColumn("Name",columnDataNombre.toArray());
								defaultTableModel.addColumn("Rol",columnDataRol.toArray());
								}
								else
								{
									defaultTableModel.addColumn("Usuario",columnDataUser.toArray());
									defaultTableModel.addColumn("Nombre",columnDataNombre.toArray());
									defaultTableModel.addColumn("Rol",columnDataRol.toArray());
								}
									
							getJTableUser().setModel(defaultTableModel);
							getJTableUser().setDefaultRenderer (Object.class, new FormatoTabla());
							getJTableUser().setRowHeight(20);
							if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
							JOptionPane.showMessageDialog(Usuarios.this, "User Inserted", "Information", JOptionPane.INFORMATION_MESSAGE);
							}else
							{
								JOptionPane.showMessageDialog(Usuarios.this, "Usuario insertado", "Informacion", JOptionPane.INFORMATION_MESSAGE);	
							}
							UserInterfaceSuport.clearComponents(jContentPane);
							}
						else
							if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
							JOptionPane.showMessageDialog(returnThis(), "Passwords must be iqual");
							}
							else{
								JOptionPane.showMessageDialog(returnThis(), "Las contraseñas deben ser iguales");
							}
					
					} catch (SQLException e1) {
						if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
						JOptionPane.showMessageDialog(Usuarios.this, "User Already Exist", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else{
							JOptionPane.showMessageDialog(Usuarios.this, "El Usuario ya Existe", "Error", JOptionPane.ERROR_MESSAGE);	
						}
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					jButtonNuevo.setEnabled(false);
					jTextFieldUser.setText("");
					jTextFieldNomb.setText("");
					jPasswordFieldPass.setText("");
					jPasswordFieldConfirm.setText("");
					jButtonAgreg.setEnabled(true);
					jButtonModificar.setEnabled(false);
					jButtonEliminar.setEnabled(false);
					}
					else
						if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
						JOptionPane.showMessageDialog(Usuarios.this, "Empty Fields", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else{
							JOptionPane.showMessageDialog(Usuarios.this, "Campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
						}
					
					}
				
			});
		}
		return jButtonAgreg;
	}
	/**
	 * This method initializes jButtonCerrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonCerrar() {
		if (jButtonCerrar == null) {
			jButtonCerrar = new JButton();
			jButtonCerrar.setText("Menú Principal");
			jButtonCerrar.setBounds(new Rectangle(489, 22, 138, 26));
			jButtonCerrar.setIcon(new ImageIcon(getClass().getResource("/Img/go-homeeee.png")));
			jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					Start i = new Start();
					new Principal(i).setVisible(true);
				}
			});
		}
		return jButtonCerrar;
	}
	
	 private Usuarios returnThis(){
		 return this;
	 }

	/**
	 * This method initializes jComboBoxRol	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxRol() {
		if (jComboBoxRol == null) {
			jComboBoxRol = new JComboBox();
		}
		return jComboBoxRol;
	}

	


	/**
	 * This method initializes jButtonModificar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	/**
	 * This method initializes jButtonModificar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonModificar() {
		if (jButtonModificar == null) {
			jButtonModificar = new JButton();
			jButtonModificar.setText("Modificar");
			jButtonModificar.setBounds(new Rectangle(147, 22, 115, 26));
			jButtonModificar.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Refresh_24x24.png")));
			jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
					int result = JOptionPane.showConfirmDialog(Usuarios.this, "Are you sure you want to change this User?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = jTableUser.getSelectedRow();
					try {
						LinkedList<Usuario> users = ServicioUsuario.getUsuarios();
						Usuario u = users.get(pos);	
						if(!(jComboBoxRol.getSelectedItem()).toString().equalsIgnoreCase("<Select>")){
						if( Encriptar.getMd5(new String (getJPasswordFieldPass().getPassword())).equals(Encriptar.getMd5(new String(getJPasswordFieldConfirm().getPassword()))) ){
						ServicioUsuario.ModificarUsuario(u.getUsername(), ((Rol)getJComboBoxRol().getSelectedItem()).getRol(),  getJTextFieldNomb().getText(), getJPasswordFieldPass().getPassword());
						JOptionPane.showMessageDialog(Usuarios.this, "User Modified", "Information", JOptionPane.INFORMATION_MESSAGE);
						jButtonAgreg.setEnabled(true);
						TableModel defaultTableModel = new TableModel();
						LinkedList<Usuario> list = new LinkedList<Usuario>();
						try {
							list = ServicioUsuario.getUsuarios();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataUser = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre = new ArrayList<Object>();
						ArrayList<Object> columnDataRol = new ArrayList<Object>();
						for (int i = 0; i < list.size(); i++) {
							columnDataUser.add(list.get(i).getUsername());
							columnDataNombre.add(list.get(i).getNombre());
							columnDataRol.add(list.get(i).getRol());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("User",columnDataUser.toArray());
						defaultTableModel.addColumn("Name",columnDataNombre.toArray());
						defaultTableModel.addColumn("Rol",columnDataRol.toArray());
						getJTableUser().setModel(defaultTableModel);
						getJTableUser().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTableUser().setRowHeight(20);
						UserInterfaceSuport.clearComponents(getJContentPane());
						}else
						{
							JOptionPane.showMessageDialog(Usuarios.this, "Passwords must be iqual", "Information", JOptionPane.INFORMATION_MESSAGE);	
						}
						}else
						{
							JOptionPane.showMessageDialog(Usuarios.this, "You must select a Rol", "Information", JOptionPane.INFORMATION_MESSAGE);	
						}
						} catch (SQLException e1) {
						JOptionPane.showMessageDialog(Usuarios.this, "User Already Exist", "Error", JOptionPane.ERROR_MESSAGE);
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					jButtonAgreg.setEnabled(true);
					jButtonEliminar.setEnabled(false);
					jButtonModificar.setEnabled(false);
					jButtonNuevo.setEnabled(false);
					jTextFieldUser.setEditable(true);
					}
					
					}
				else{
					int result = JOptionPane.showConfirmDialog(Usuarios.this, "¿Está seguro que desea modificar el usuario?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = jTableUser.getSelectedRow();
					try {
						LinkedList<Usuario> users = ServicioUsuario.getUsuarios();
						Usuario u = users.get(pos);	
						if(!(jComboBoxRol.getSelectedItem()).toString().equalsIgnoreCase("<Seleccione>")){
						if( Encriptar.getMd5(new String (getJPasswordFieldPass().getPassword())).equals(Encriptar.getMd5(new String(getJPasswordFieldConfirm().getPassword()))) ){
						ServicioUsuario.ModificarUsuario(u.getUsername(), ((Rol)getJComboBoxRol().getSelectedItem()).getRol(),  getJTextFieldNomb().getText(), getJPasswordFieldPass().getPassword());
						JOptionPane.showMessageDialog(Usuarios.this, "Usuario Modificado", "Información", JOptionPane.INFORMATION_MESSAGE);
						jButtonAgreg.setEnabled(true);
						DefaultTableModel defaultTableModel = new DefaultTableModel();
						LinkedList<Usuario> list = new LinkedList<Usuario>();
						try {
							list = ServicioUsuario.getUsuarios();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataUser = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre = new ArrayList<Object>();
						ArrayList<Object> columnDataRol = new ArrayList<Object>();
						for (int i = 0; i < list.size(); i++) {
							columnDataUser.add(list.get(i).getUsername());
							columnDataNombre.add(list.get(i).getNombre());
							columnDataRol.add(list.get(i).getRol());
							}
						defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("Usuario",columnDataUser.toArray());
							defaultTableModel.addColumn("Nombre",columnDataNombre.toArray());
							defaultTableModel.addColumn("Rol",columnDataRol.toArray());
						getJTableUser().setModel(defaultTableModel);
						getJTableUser().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTableUser().setRowHeight(20);
						
						UserInterfaceSuport.clearComponents(getJContentPane());
						}else
						{
							JOptionPane.showMessageDialog(Usuarios.this, "Las contraseñas deben ser iguales", "Informacion", JOptionPane.INFORMATION_MESSAGE);	
						}
						}else
						{
							JOptionPane.showMessageDialog(Usuarios.this, "Debe seleccionar un Rol", "Informacion", JOptionPane.INFORMATION_MESSAGE);	
						}
						} catch (SQLException e1) {
						JOptionPane.showMessageDialog(Usuarios.this, "No se puede insertar dos Usuarios de igual Nombre de Usuario", "Error", JOptionPane.ERROR_MESSAGE);
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					jButtonAgreg.setEnabled(true);
					jButtonEliminar.setEnabled(false);
					jButtonModificar.setEnabled(false);
					jButtonNuevo.setEnabled(false);
					jTextFieldUser.setEditable(true);
					}
				}
				}});
			}
		return jButtonModificar;
	}


	

	

	/**
	 * This method initializes jButtonEliminar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonEliminar() {
		if (jButtonEliminar == null) {
			jButtonEliminar = new JButton();
			jButtonEliminar.setText("Eliminar");
			jButtonEliminar.setBounds(new Rectangle(377, 22, 100, 26));
			jButtonEliminar.setIcon(new ImageIcon(getClass().getResource("/Img/edit-cut.png")));
			jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
					int result = JOptionPane.showConfirmDialog(Usuarios.this, "Are you sure you want to remove this User?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = jTableUser.getSelectedRow();
					try {
						LinkedList<Usuario> users = ServicioUsuario.getUsuarios();
						Usuario u = users.get(pos);
						ServicioUsuario.EliminarUsuario(u.getUsername());
						JOptionPane.showMessageDialog(Usuarios.this, "User Removed", "Information", JOptionPane.INFORMATION_MESSAGE);
						jButtonAgreg.setEnabled(true);
						TableModel defaultTableModel = new TableModel();
						LinkedList<Usuario> list = new LinkedList<Usuario>();
						try {
							list = ServicioUsuario.getUsuarios();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataUser = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre = new ArrayList<Object>();
						ArrayList<Object> columnDataRol = new ArrayList<Object>();
						for (int i = 0; i < list.size(); i++) {
							columnDataUser.add(list.get(i).getUsername());
							columnDataNombre.add(list.get(i).getNombre());
							columnDataRol.add(list.get(i).getRol());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("User",columnDataUser.toArray());
						defaultTableModel.addColumn("Name",columnDataNombre.toArray());
						defaultTableModel.addColumn("Rol",columnDataRol.toArray());
						getJTableUser().setModel(defaultTableModel);
						getJTableUser().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTableUser().setRowHeight(20);
						UserInterfaceSuport.clearComponents(getJContentPane());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					jButtonAgreg.setEnabled(true);
					jButtonEliminar.setEnabled(false);
					jButtonModificar.setEnabled(false);
					jTextFieldNomb.setEditable(true);
					jButtonNuevo.setEnabled(false);
					jTextFieldUser.setEditable(true);
				}
					}else{
						int result = JOptionPane.showConfirmDialog(Usuarios.this, "¿Está seguro que desea eliminar el usuario?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if(result == JOptionPane.YES_OPTION){
						int pos = jTableUser.getSelectedRow();
						try {
							LinkedList<Usuario> users = ServicioUsuario.getUsuarios();
							Usuario u = users.get(pos);
							ServicioUsuario.EliminarUsuario(u.getUsername());
							JOptionPane.showMessageDialog(Usuarios.this, "Usuario Eliminado", "Información", JOptionPane.INFORMATION_MESSAGE);
							jButtonAgreg.setEnabled(true);
							DefaultTableModel defaultTableModel = new DefaultTableModel();
							LinkedList<Usuario> list = new LinkedList<Usuario>();
							try {
								list = ServicioUsuario.getUsuarios();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Object> columnDataUser = new ArrayList<Object>();
							ArrayList<Object> columnDataNombre = new ArrayList<Object>();
							ArrayList<Object> columnDataRol = new ArrayList<Object>();
							for (int i = 0; i < list.size(); i++) {
								columnDataUser.add(list.get(i).getUsername());
								columnDataNombre.add(list.get(i).getNombre());
								columnDataRol.add(list.get(i).getRol());
								}
							defaultTableModel.setRowCount(list.size());
							if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
								defaultTableModel.addColumn("User",columnDataUser.toArray());
								defaultTableModel.addColumn("Name",columnDataNombre.toArray());
								defaultTableModel.addColumn("Rol",columnDataRol.toArray());
								}
								else
								{
									defaultTableModel.addColumn("Usuario",columnDataUser.toArray());
									defaultTableModel.addColumn("Nombre",columnDataNombre.toArray());
									defaultTableModel.addColumn("Rol",columnDataRol.toArray());
								}
									
							getJTableUser().setModel(defaultTableModel);
							getJTableUser().setDefaultRenderer (Object.class, new FormatoTabla());	
							getJTableUser().setRowHeight(20);
							UserInterfaceSuport.clearComponents(getJContentPane());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						jButtonAgreg.setEnabled(true);
						jButtonEliminar.setEnabled(false);
						jButtonModificar.setEnabled(false);
						jTextFieldNomb.setEditable(true);
						jButtonNuevo.setEnabled(false);
						jTextFieldUser.setEditable(true);
						}
					}
				}});
		}
		return jButtonEliminar;
	}

	/**
	 * This method initializes jButtonNuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonNuevo() {
		if (jButtonNuevo == null) {
			jButtonNuevo = new JButton();
			jButtonNuevo.setText("Borrar");
			jButtonNuevo.setBounds(new Rectangle(274, 22, 91, 26));
			jButtonNuevo.setIcon(new ImageIcon(getClass().getResource("/Img/edit-clear.png")));
			jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					jTextFieldUser.setText("");
					jTextFieldNomb.setText("");
					jPasswordFieldPass.setText("");
					jPasswordFieldConfirm.setText("");
					jButtonAgreg.setEnabled(true);
					jButtonModificar.setEnabled(false);
					jButtonEliminar.setEnabled(false);
					jButtonNuevo.setEnabled(false);
					 //Combobox Rol
					LinkedList<Rol> list2 = new LinkedList<Rol>();
					try {
						list2 = ServicioRol.getRoles();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					DefaultComboBoxModel boxModel1 = new DefaultComboBoxModel();
					boxModel1.addElement(new String("<Seleccione>"));
					for (int i = 0; i < list2.size(); i++) {
						boxModel1.addElement(list2.get(i));
					}
					
					getJComboBoxRol().setModel(boxModel1);					
                   	jTextFieldNomb.setEditable(true);
					jComboBoxRol.setEnabled(true);
					jPasswordFieldConfirm.setEditable(true);
					jPasswordFieldPass.setEditable(true);
					jTextFieldUser.setEditable(true);
					}
			});
		}
		return jButtonNuevo;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(getJButtonModificar(), null);
			jPanel.add(getJButtonNuevo(), null);
			jPanel.add(getJButtonEliminar(), null);
			jPanel.add(getJButtonCerrar(), null);
			jPanel.add(getJButtonAgreg(), null);
		}
		return jPanel;
	}

	

	
}  //  @jve:decl-index=0:visual-constraint="228,33"
 //  @jve:decl-index=0:visual-constraint="10,10"

