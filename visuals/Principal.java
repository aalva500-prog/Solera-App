package visuals;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Utils.CurrentUser;
import Utils.Idioma;



public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JMenuBar mainMenuBar = null;

	private JMenu FileMenu = null;

	private JMenuItem jMenuItem1 = null;
	private Start owner = null;

	private JMenuItem jMenuItemAutenticar = null;

	private JMenu GestionjMenu1 = null;

	private JMenuItem UsuariosjMenuItem = null;

	private JLabel jLabel;

	private JMenuItem TonelesjMenuItem=null;

	private JMenuItem jMenuItemBackup = null;

	private JMenuItem ExtraccionesjMenuItem = null;

	private JMenu EstadoSolerajMenu = null;

	private JMenuItem InicialjMenuItem = null;

	private JMenuItem MesesjMenuItem = null;

	/**
	 * This is the default constructor
	 */
	public Principal(Start parent) {
		super();
		this.owner = parent;
		initialize();
		}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(410, 322);		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/toneles.png")));
		this.setResizable(false);
		this.setJMenuBar(getMainMenuBar());
		this.setContentPane(getJContentPane());
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle("Principal");
		this.addWindowListener(new java.awt.event.WindowAdapter() {   
			public void windowClosed(java.awt.event.WindowEvent e) {    
				getOwner().dispose();
			}
			public void windowOpened(java.awt.event.WindowEvent e) {
				if(CurrentUser.getCurrentUser().getSessionUser().getRol().equals("Administrador")){
					getUsuariosjMenuItem().setVisible(true);
				}
				if(CurrentUser.getCurrentUser().getSessionUser().getRol().equals("Invitado")){
					getUsuariosjMenuItem().setVisible(false);
				}
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
			jContentPane = new JPanel();
			jLabel = new JLabel();
			jLabel.setIcon(new ImageIcon(getClass().getResource("/Img/toneles.jpg")));
			jContentPane.add(jLabel, null);
			}
		return jContentPane;
	}

	/**
	 * This method initializes mainMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getMainMenuBar() {
		if (mainMenuBar == null) {
			mainMenuBar = new JMenuBar();
			mainMenuBar.add(getFileMenu());
			mainMenuBar.add(getGestionjMenu1());
			}
		return mainMenuBar;
	}

	/**
	 * This method initializes FileMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileMenu() {
		if (FileMenu == null) {
			FileMenu = new JMenu();
			FileMenu.setText("Archivo");
			FileMenu.add(getJMenuItemAutenticar());
			FileMenu.add(getJMenuItemBackup());
			FileMenu.add(getJMenuItem1());			
		}
		return FileMenu;
	}

	
	/**
	 * This method initializes jMenuItem1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Salir");
			jMenuItem1.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Delete_16x16.png")));
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(DISPOSE_ON_CLOSE);
				}
			});
		}
		return jMenuItem1;
	}

	public void idioma(){
		if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
			FileMenu.setText("File");
			jMenuItem1.setText("Exit");
			TonelesjMenuItem.setText("Barrels List");
			jMenuItemAutenticar.setText("Change User");
			UsuariosjMenuItem.setText("Users");
			GestionjMenu1.setText("Management");
			this.setTitle("Main");
			}
		}

	public Principal returnThis(){
		return this;
	}

	public Start getOwner() {
		return owner;
	}

	/**
	 * This method initializes jMenuItemAutenticar	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemAutenticar() {
		if (jMenuItemAutenticar == null) {
			jMenuItemAutenticar = new JMenuItem();
			jMenuItemAutenticar.setText("Cambiar Usuario");
			jMenuItemAutenticar.setIcon(new ImageIcon(getClass().getResource("/Img/edit_user.png")));
			jMenuItemAutenticar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setVisible(false);
					Start r = new Start();
					r.setVisible(true);
				}
			});
		}
		return jMenuItemAutenticar;
	}

	/**
	 * This method initializes GestionjMenu1	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getGestionjMenu1() {
		if (GestionjMenu1 == null) {
			GestionjMenu1 = new JMenu();
			GestionjMenu1.setText("Gestión");
			GestionjMenu1.add(getUsuariosjMenuItem());
			GestionjMenu1.add(getTOnelesjMenuItem());
			GestionjMenu1.add(getExtraccionesjMenuItem());
			GestionjMenu1.add(getEstadoSolerajMenu());
		}
		return GestionjMenu1;
	}

	/**
	 * This method initializes UsuariosjMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getUsuariosjMenuItem() {
		if (UsuariosjMenuItem == null) {
			UsuariosjMenuItem = new JMenuItem();
			UsuariosjMenuItem.setText("Usuarios");
			UsuariosjMenuItem.setIcon(new ImageIcon(getClass().getResource("/Img/add_user.png")));
			UsuariosjMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					new Usuarios(Principal.this,true).setVisible(true);
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()	
				}
			});
		}
		return UsuariosjMenuItem;
	}

	/**
	 * This method initializes VecindariojMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getTOnelesjMenuItem() {
		if (TonelesjMenuItem == null) {
			TonelesjMenuItem = new JMenuItem();
			TonelesjMenuItem.setText("Gestionar Productos");
			TonelesjMenuItem.setIcon(new ImageIcon(getClass().getResource("/img/toneles.png")));
			TonelesjMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					new MateriaVisual().setVisible(true);
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()	
					}
			});
		}
		return TonelesjMenuItem;
	}

	/**
	 * This method initializes jMenuItemBackup	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemBackup() {
		if (jMenuItemBackup == null) {
			jMenuItemBackup = new JMenuItem();
			jMenuItemBackup.setText("Backup");
			jMenuItemBackup.setIcon(new ImageIcon(getClass().getResource("/Img/save_edit.png")));
			jMenuItemBackup.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					new Backup(Principal.this,true).setVisible(true);
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()					
				}
			});
		}
		return jMenuItemBackup;
	}

	/**
	 * This method initializes ExtraccionesjMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExtraccionesjMenuItem() {
		if (ExtraccionesjMenuItem == null) {
			ExtraccionesjMenuItem = new JMenuItem();
			ExtraccionesjMenuItem.setText("Extracción de Líquidos");
			ExtraccionesjMenuItem.setIcon(new ImageIcon(getClass().getResource("/Img/edit-undo.png")));
			ExtraccionesjMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					new Extracciones().setVisible(true);
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return ExtraccionesjMenuItem;
	}

	/**
	 * This method initializes EstadoSolerajMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getEstadoSolerajMenu() {
		if (EstadoSolerajMenu == null) {
			EstadoSolerajMenu = new JMenu();
			EstadoSolerajMenu.setText("Estado de la Solera");
			EstadoSolerajMenu.setIcon(new ImageIcon(getClass().getResource("/Img/weather-clear.png")));
			EstadoSolerajMenu.add(getInicialjMenuItem());
			EstadoSolerajMenu.add(getMesesjMenuItem());
		}
		return EstadoSolerajMenu;
	}

	/**
	 * This method initializes InicialjMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getInicialjMenuItem() {
		if (InicialjMenuItem == null) {
			InicialjMenuItem = new JMenuItem();
			InicialjMenuItem.setText("Estado Inicial");
			InicialjMenuItem.setIcon(new ImageIcon(getClass().getResource("/Img/edit-redo.png")));
			InicialjMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					new DetalleMermaVisual().setVisible(true);
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return InicialjMenuItem;
	}

	/**
	 * This method initializes MesesjMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMesesjMenuItem() {
		if (MesesjMenuItem == null) {
			MesesjMenuItem = new JMenuItem();
			MesesjMenuItem.setText("Por Meses");
			MesesjMenuItem.setIcon(new ImageIcon(getClass().getResource("/Img/edit-redo.png")));
			MesesjMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					new DetalleMerma1Visual().setVisible(true);
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return MesesjMenuItem;
	}

	
}  //  @jve:decl-index=0:visual-constraint="8,-32"
