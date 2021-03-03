package visuals;

import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import model.Usuario;
import services.ServicioUsuario;
import Utils.CurrentUser;
import Utils.Idioma;
import Utils.UserInterfaceSuport;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

public class Start extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JTextField jTextField = null;

	private JLabel jLabel1 = null;

	private JPasswordField jPasswordField = null;

	private JButton jButton = null;

	private ButtonGroup buttonGroup = null;  //  @jve:decl-index=0:visual-constraint="496,146"

	private JButton jButtonSalir = null;

	/**
	 * This is the default constructor
	 */
	public Start() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(430, 195);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/password.png")));
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Conectar");
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowActivated(java.awt.event.WindowEvent e) {    
				jTextField.setText("");
				jPasswordField.setText("");
			}
			public void windowOpened(java.awt.event.WindowEvent e) {
				jTextField.setText("");
				jPasswordField.setText("");
				
			}
		});
		getButtonGroup();
		Idioma.getInstance().setIdioma("espanol");
		UserInterfaceSuport.centerComponent(Start.this);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(64, 57, 76, 25));
			jLabel1.setText("Contrase\u00F1a:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(38, 19, 102, 25));
			jLabel.setText("Nombre usuario:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJTextField(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJPasswordField(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButtonSalir(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(138, 19, 269, 25));
			jTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
						getJButton().doClick();
					}
				}
			});
		}
		return jTextField;
	}

	/**
	 * This method initializes jPasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
			jPasswordField.setBounds(new Rectangle(138, 57, 269, 25));
			jPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
						getJButton().doClick();
					}
				}
			});
		}
		return jPasswordField;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(31, 113, 125, 33));
			jButton.setText("Conectar");
			jButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_SysInfo_Unit1_ilInfoStates1_16x16.png")));
			jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				// Obtener el rol del usuario que se está autenticando
				String rol = ServicioUsuario.getLoginUsuario(jTextField.getText(), jPasswordField.getPassword());
				if(rol.equals("")){
					JOptionPane.showMessageDialog(returnThis(), "Usuario y/o contrasena invalidos");
					//jTextFieldUsuario.setText("");
					jPasswordField.setText("");
				}else{
					//Creando una instancia de tipo usuario para actualizar la clase CurrentUser
					Usuario usuario = new Usuario(jTextField.getText(),rol);
					CurrentUser.getCurrentUser().setSessionUser(usuario);
					Principal frmPpal = new Principal(returnThis());
					returnThis().setVisible(false);
					frmPpal.setVisible(true);
					frmPpal.idioma();
				}
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes buttonGroup	
	 * 	
	 * @return javax.swing.ButtonGroup	
	 */
	private ButtonGroup getButtonGroup() {
		if (buttonGroup == null) {
			buttonGroup = new ButtonGroup();
		}
		return buttonGroup;
	}

	/**
	 * This method initializes jButtonSalir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonSalir() {
		if (jButtonSalir == null) {
			jButtonSalir = new JButton();
			jButtonSalir.setBounds(new Rectangle(172, 113, 125, 33));
			jButtonSalir.setText("Salir");
			jButtonSalir.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Delete_16x16.png")));
			jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return jButtonSalir;
	}

	public static void main(String[] args) {
		try {
			NimbusLookAndFeel NimRODLF = new NimbusLookAndFeel();
			UIManager.setLookAndFeel(NimRODLF);
			java.util.Date utilDate = new java.util.Date();
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    System.out.println("utilDate:" + utilDate);
		    System.out.println("sqlDate:" + sqlDate);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	    Start autenticar = new Start();
		autenticar.setVisible(true);
	}
	
	public Start returnThis(){
		return this;
	}

}  //  @jve:decl-index=0:visual-constraint="62,85"
