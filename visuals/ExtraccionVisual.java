package visuals;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import model.MateriaPrima;
import model.extracciones;
import services.ServicioExtraccion;
import Utils.FormatoTabla;
import Utils.Reportes;
import Utils.TableModel;
import Utils.UserInterfaceSuport;
import Utils.Validate;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ExtraccionVisual extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="102,71"
	private MateriaPrima mp= null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JLabel InventariojLabel = null;
	private JTextField InventariojTextField = null;
	private JLabel LiquidoSacadojLabel = null;
	private JTextField LuiqSacadojTextField = null;
	private JLabel TiempoReposojLabel = null;
	private JTextField TiempoReposojTextField = null;
	private JLabel PerdidajLabel = null;
	private JTextField PerdidajTextField = null;
	private JLabel PerdidaMensualjLabel = null;
	private JTextField PerdidaMensualjTextField = null;
	private JLabel PerdidaAnualjLabel = null;
	private JTextField PerdidaAnualjTextField = null;
	private JLabel PerdidaTotaljLabel = null;
	private JTextField PerdidaTotaljTextField = null;
	private JSpinner DatejSpinner = null;
	private SpinnerDateModel DatespinnerDateModel = null;
	private JLabel FechajLabel = null;
	private JLabel GradojLabel = null;
	private JTextField GradojTextField = null;
	private JButton perdidajButton = null;
	private JButton perdidamensualjButton = null;
	private JButton PerididaAnualjButton = null;
	private JButton PerdidaTotaljButton = null;
	private JLabel ObservacionesjLabel = null;
	private JTextArea ObserrvacionesjTextArea = null;
	private JButton InsetarjButton = null;
	private JButton ModificarjButton = null;
	private JButton EliminarjButton = null;
	private JButton RestaurarjButton = null;
	private JButton ImprimirjButton = null;
	private JButton CerrarjButton = null;
	private JLabel ProductojLabel = null;
	private JLabel GradoMjLabel = null;
	private extracciones extrac=null;
	private JScrollPane jScrollPane1 = null;
	private JPanel jPanel = null;

	
	public ExtraccionVisual(MateriaPrima mp) {
		super();
		this.mp=mp;
		initialize();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private void initialize() {	
			this.setSize(new Dimension(1152, 592));
			this.setTitle("Registrar Extracciones");
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/toneles.png")));
			this.setContentPane(getJContentPane());
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			ModificarjButton.setEnabled(false);
			EliminarjButton.setEnabled(false);
			ImprimirjButton.setEnabled(false);
			InsetarjButton.setEnabled(true);
			DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
		    simbolo.setDecimalSeparator(',');
		    simbolo.setGroupingSeparator('.');
			DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
			TableModel defaultTableModel = new TableModel();
			LinkedList<extracciones> list = new LinkedList<extracciones>();
			list = ServicioExtraccion.getxtracciones(mp.getId_materia());
			ArrayList<Object> columnDataInventario = new ArrayList<Object>();
			ArrayList<Object> columnDataLiqSacado = new ArrayList<Object>();					
			ArrayList<Object> columnDataAnejo = new ArrayList<Object>();
			ArrayList<Object> columnDataPerdida = new ArrayList<Object>();
			ArrayList<Object> columnDataPerdidaMensual= new ArrayList<Object>();
			ArrayList<Object> columnDataPerdidaAnual= new ArrayList<Object>();
			ArrayList<Object> columnDataPerdidaTotal= new ArrayList<Object>();
			ArrayList<Object> columnDataObservaciones= new ArrayList<Object>();
			ArrayList<Object> columnDataFecha= new ArrayList<Object>();
			ArrayList<Object> columnDataGrado= new ArrayList<Object>();
			
			for (int i = 0; i < list.size(); i++) {
				columnDataInventario.add(list.get(i).getInventario());
				columnDataLiqSacado.add(list.get(i).getLiqsacado());					
				columnDataAnejo.add(list.get(i).getAnejo());
				columnDataPerdida.add(dosDigitos.format(list.get(i).getPerdida()));
				columnDataPerdidaMensual.add(dosDigitos.format(list.get(i).getPerdidamensual()));
				columnDataPerdidaAnual.add(dosDigitos.format(list.get(i).getPerdidaanual()));
				columnDataPerdidaTotal.add(dosDigitos.format(list.get(i).getPerdidatotal()));
				columnDataObservaciones.add(list.get(i).getObservaciones());
				columnDataFecha.add(list.get(i).getFecha());
				columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));
				}
			defaultTableModel.setRowCount(list.size());
			defaultTableModel.addColumn("Inventario",columnDataInventario.toArray());
			defaultTableModel.addColumn("Líquido Sacado",columnDataLiqSacado.toArray());					
			defaultTableModel.addColumn("Tiempo Reposo",columnDataAnejo.toArray());
			defaultTableModel.addColumn("Perdida",columnDataPerdida.toArray());
			defaultTableModel.addColumn("Pérdida Mensual",columnDataPerdidaMensual.toArray());	
			defaultTableModel.addColumn("Pérdida Anual",columnDataPerdidaAnual.toArray());	
			defaultTableModel.addColumn("Pérdida Total",columnDataPerdidaTotal.toArray());
			defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
			defaultTableModel.addColumn("Fecha",columnDataFecha.toArray());	
			defaultTableModel.addColumn("Grado",columnDataGrado.toArray());	
			getJTable().setModel(defaultTableModel);
			getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
			getJTable().setRowHeight(20);
			jTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
				    simbolo.setDecimalSeparator('.');
					DecimalFormat dosDigitos1 = new DecimalFormat( "####.#####",simbolo);
					int pos = jTable.getSelectedRow();
					LinkedList<extracciones> toneles = ServicioExtraccion.getxtracciones(mp.getId_materia());
					extracciones t = toneles.get(pos);					
					ObserrvacionesjTextArea.setText(t.getObservaciones());
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					DatejSpinner.setEditor(new JSpinner.DateEditor(DatejSpinner,dateFormat.toPattern()));
					DatejSpinner.setValue(t.getFecha());	
					GradojTextField.setText(String.valueOf(t.getGrado()));
					InventariojTextField.setText(String.valueOf(t.getInventario()));
					LuiqSacadojTextField.setText(String.valueOf(t.getLiqsacado()));
					TiempoReposojTextField.setText(String.valueOf(t.getAnejo()));
					PerdidajTextField.setText(String.valueOf(dosDigitos1.format(t.getPerdida())));
					PerdidaAnualjTextField.setText(String.valueOf(dosDigitos1.format(t.getPerdidaanual())));
					PerdidaMensualjTextField.setText(String.valueOf(dosDigitos1.format(t.getPerdidamensual())));
					PerdidaTotaljTextField.setText(String.valueOf(dosDigitos1.format(t.getPerdidatotal())));
					ModificarjButton.setEnabled(true);
					EliminarjButton.setEnabled(true);
					ImprimirjButton.setEnabled(true);
					InsetarjButton.setEnabled(false);
					extrac=t;
					
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
			GradoMjLabel = new JLabel();
			DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
		    simbolo.setDecimalSeparator(',');
		    simbolo.setGroupingSeparator('.');
			DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
			GradoMjLabel.setText("Grado: "  + String.valueOf(dosDigitos.format(mp.getGrado())));
			GradoMjLabel.setBounds(new Rectangle(882, 46, 241, 20));
			ProductojLabel = new JLabel();
			ProductojLabel.setText("Producto: " +  mp.getDescripcion());
			ProductojLabel.setBounds(new Rectangle(882, 8, 241, 20));
			ObservacionesjLabel = new JLabel();
			ObservacionesjLabel.setText("Observaciones:");
			ObservacionesjLabel.setBounds(new Rectangle(622, 8, 252, 20));
			GradojLabel = new JLabel();
			GradojLabel.setText("Grado:");
			GradojLabel.setBounds(new Rectangle(369, 7, 107, 20));
			FechajLabel = new JLabel();
			FechajLabel.setText("Fecha:");
			FechajLabel.setBounds(new Rectangle(494, 8, 107, 20));
			PerdidaTotaljLabel = new JLabel();
			PerdidaTotaljLabel.setText("Pérdida Total:");
			PerdidaTotaljLabel.setBounds(new Rectangle(57, 103, 107, 20));
			PerdidaAnualjLabel = new JLabel();
			PerdidaAnualjLabel.setText("Pérdida Anual:");
			PerdidaAnualjLabel.setBounds(new Rectangle(56, 203, 107, 20));
			PerdidaMensualjLabel = new JLabel();
			PerdidaMensualjLabel.setText("Pérdida Mensual:");
			PerdidaMensualjLabel.setBounds(new Rectangle(57, 153, 107, 20));
			PerdidajLabel = new JLabel();
			PerdidajLabel.setText("Pérdida:");
			PerdidajLabel.setBounds(new Rectangle(56, 55, 107, 20));
			TiempoReposojLabel = new JLabel();
			TiempoReposojLabel.setText("Tiempo Reposo:");
			TiempoReposojLabel.setBounds(new Rectangle(247, 6, 107, 20));
			LiquidoSacadojLabel = new JLabel();
			LiquidoSacadojLabel.setText("Líquido Sacado:");
			LiquidoSacadojLabel.setBounds(new Rectangle(126, 5, 107, 20));
			InventariojLabel = new JLabel();
			InventariojLabel.setText("Inventario:");
			InventariojLabel.setBounds(new Rectangle(5, 5, 107, 20));
			jContentPane = new JPanel();
			jContentPane.setSize(new Dimension(487, 332));
			GridBagLayout gbl_jContentPane = new GridBagLayout();
			gbl_jContentPane.columnWidths = new int[]{1120, 0};
			gbl_jContentPane.rowHeights = new int[]{259, 275, 0};
			gbl_jContentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_jContentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			jContentPane.setLayout(gbl_jContentPane);
			GridBagConstraints gbc_jPanel = new GridBagConstraints();
			gbc_jPanel.fill = GridBagConstraints.BOTH;
			gbc_jPanel.insets = new Insets(0, 0, 5, 0);
			gbc_jPanel.gridx = 0;
			gbc_jPanel.gridy = 0;
			jContentPane.add(getJPanel(), gbc_jPanel);
			GridBagConstraints gbc_jScrollPane = new GridBagConstraints();
			gbc_jScrollPane.fill = GridBagConstraints.BOTH;
			gbc_jScrollPane.gridx = 0;
			gbc_jScrollPane.gridy = 1;
			jContentPane.add(getJScrollPane(), gbc_jScrollPane);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
		}
		return jTable;
	}

	/**
	 * This method initializes InventariojTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getInventariojTextField() {
		if (InventariojTextField == null) {
			InventariojTextField = new JTextField();
			InventariojTextField.setBounds(new Rectangle(5, 25, 107, 26));
			Validate.validateDigit(InventariojTextField);
		}
		return InventariojTextField;
	}

	/**
	 * This method initializes LuiqSacadojTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getLuiqSacadojTextField() {
		if (LuiqSacadojTextField == null) {
			LuiqSacadojTextField = new JTextField();
			LuiqSacadojTextField.setBounds(new Rectangle(126, 25, 107, 26));
			Validate.validateDigit(LuiqSacadojTextField);
		}
		return LuiqSacadojTextField;
	}

	/**
	 * This method initializes TiempoReposojTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTiempoReposojTextField() {
		if (TiempoReposojTextField == null) {
			TiempoReposojTextField = new JTextField();
			TiempoReposojTextField.setBounds(new Rectangle(247, 26, 107, 25));
			Validate.validateDigit(TiempoReposojTextField);
		}
		return TiempoReposojTextField;
	}

	/**
	 * This method initializes PerdidajTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPerdidajTextField() {
		if (PerdidajTextField == null) {
			PerdidajTextField = new JTextField();
			PerdidajTextField.setBounds(new Rectangle(56, 75, 107, 26));
			Validate.validateDigit(PerdidajTextField);
		}
		return PerdidajTextField;
	}

	/**
	 * This method initializes PerdidaMensualjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPerdidaMensualjTextField() {
		if (PerdidaMensualjTextField == null) {
			PerdidaMensualjTextField = new JTextField();
			PerdidaMensualjTextField.setBounds(new Rectangle(57, 173, 107, 26));
			Validate.validateDigitAndComma(PerdidaMensualjTextField);
		}
		return PerdidaMensualjTextField;
	}

	/**
	 * This method initializes PerdidaAnualjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPerdidaAnualjTextField() {
		if (PerdidaAnualjTextField == null) {
			PerdidaAnualjTextField = new JTextField();
			PerdidaAnualjTextField.setBounds(new Rectangle(56, 222, 107, 28));
			Validate.validateDigitAndComma(PerdidaAnualjTextField);
		}
		return PerdidaAnualjTextField;
	}

	/**
	 * This method initializes PerdidaTotaljTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPerdidaTotaljTextField() {
		if (PerdidaTotaljTextField == null) {
			PerdidaTotaljTextField = new JTextField();
			PerdidaTotaljTextField.setBounds(new Rectangle(57, 123, 107, 26));
			Validate.validateDigitAndComma(PerdidaTotaljTextField);
		}
		return PerdidaTotaljTextField;
	}

	
	
	/**
	 * This method initializes DatejSpinner	
	 * 	
	 * @return javax.swing.JSpinner	
	 */
	private JSpinner getDatejSpinner() {
		if (DatejSpinner == null) {
			DatejSpinner = new JSpinner(getDatespinnerDateModel() );
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			DatejSpinner.setEditor(new JSpinner.DateEditor(DatejSpinner,dateFormat.toPattern()));
			DatejSpinner.setBounds(new Rectangle(494, 28, 107, 24));
		}
		return DatejSpinner;
	}
	/**
	 * This method initializes DatespinnerDateModel	
	 * 	
	 * @return javax.swing.SpinnerDateModel	
	 */
	private SpinnerDateModel getDatespinnerDateModel() {
		if (DatespinnerDateModel  == null) {
			DatespinnerDateModel = new SpinnerDateModel();
		}
		return DatespinnerDateModel;
	}

	/**
	 * This method initializes GradojTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getGradojTextField() {
		if (GradojTextField == null) {
			GradojTextField = new JTextField();
			GradojTextField.setBounds(new Rectangle(369, 27, 107, 24));
			Validate.validateDigitAndComma(GradojTextField);
		}
		return GradojTextField;
	}

	/**
	 * This method initializes perdidajButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPerdidajButton() {
		if (perdidajButton == null) {
			perdidajButton = new JButton();
			perdidajButton.setText("=");
			perdidajButton.setBounds(new Rectangle(4, 54, 41, 39));
			perdidajButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(InventariojTextField.getText().length()!=0 && LuiqSacadojTextField.getText().length()!=0){
					int total= Integer.valueOf(InventariojTextField.getText());
					int sacar=Integer.valueOf(LuiqSacadojTextField.getText());
					PerdidajTextField.setText(String.valueOf(total-sacar));
					}else{
						JOptionPane.showMessageDialog(ExtraccionVisual.this, "Existen Bloques en Blanco", "Error", JOptionPane.ERROR_MESSAGE);
					}
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return perdidajButton;
	}

	/**
	 * This method initializes perdidamensualjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPerdidamensualjButton() {
		if (perdidamensualjButton == null) {
			perdidamensualjButton = new JButton();
			perdidamensualjButton.setText("=");
			perdidamensualjButton.setBounds(new Rectangle(5, 153, 41, 39));
			perdidamensualjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(PerdidaTotaljTextField.getText().length()!=0 && TiempoReposojTextField.getText().length()!=0){
					DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
				    simbolo.setDecimalSeparator('.');
					DecimalFormat dosDigitos1 = new DecimalFormat( "####.#####",simbolo);
					float perdtotal= Float.valueOf(PerdidaTotaljTextField.getText());
					int meses= Integer.valueOf(TiempoReposojTextField.getText());
					PerdidaMensualjTextField.setText(String.valueOf(dosDigitos1.format(perdtotal/meses)));
					}else{
						JOptionPane.showMessageDialog(ExtraccionVisual.this, "Existen Bloques en Blanco", "Error", JOptionPane.ERROR_MESSAGE);
					}
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return perdidamensualjButton;
	}

	/**
	 * This method initializes PerididaAnualjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPerididaAnualjButton() {
		if (PerididaAnualjButton == null) {
			PerididaAnualjButton = new JButton();
			PerididaAnualjButton.setText("=");
			PerididaAnualjButton.setBounds(new Rectangle(4, 203, 41, 39));
			PerididaAnualjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(PerdidaMensualjTextField.getText().length()!=0){
					DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
				    simbolo.setDecimalSeparator('.');
					DecimalFormat dosDigitos1 = new DecimalFormat( "####.#####",simbolo);
					float perdmens = Float.valueOf(PerdidaMensualjTextField.getText());
					PerdidaAnualjTextField.setText(String.valueOf(dosDigitos1.format(perdmens*12)));
					}else{
						JOptionPane.showMessageDialog(ExtraccionVisual.this, "Existen Bloques en Blanco", "Error", JOptionPane.ERROR_MESSAGE);
					}
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return PerididaAnualjButton;
	}

	/**
	 * This method initializes PerdidaTotaljButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPerdidaTotaljButton() {
		if (PerdidaTotaljButton == null) {
			PerdidaTotaljButton = new JButton();
			PerdidaTotaljButton.setText("=");
			PerdidaTotaljButton.setBounds(new Rectangle(5, 103, 41, 39));
			PerdidaTotaljButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(InventariojTextField.getText().length()!=0 && PerdidajTextField.getText().length()!=0){
					DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
				    simbolo.setDecimalSeparator('.');
					DecimalFormat dosDigitos1 = new DecimalFormat( "####.#####",simbolo);
					int inventario = Integer.valueOf(InventariojTextField.getText());
					float perdida= Float.valueOf(PerdidajTextField.getText());
					float cal = perdida*100;
					PerdidaTotaljTextField.setText(String.valueOf(dosDigitos1.format(cal/inventario)));
					}else{
						JOptionPane.showMessageDialog(ExtraccionVisual.this, "Existen Bloques en Blanco", "Error", JOptionPane.ERROR_MESSAGE);
					}
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return PerdidaTotaljButton;
	}

	/**
	 * This method initializes ObserrvacionesjTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getObserrvacionesjTextArea() {
		if (ObserrvacionesjTextArea == null) {
			ObserrvacionesjTextArea = new JTextArea();
		}
		return ObserrvacionesjTextArea;
	}

	/**
	 * This method initializes InsetarjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getInsetarjButton() {
		if (InsetarjButton == null) {
			InsetarjButton = new JButton();
			InsetarjButton.setText("Insertar");
			InsetarjButton.setBounds(new Rectangle(187, 211, 99, 39));
			InsetarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_SysInfo_Unit1_ilInfoStates1_16x16.png")));
			InsetarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {				
						try {
						java.sql.Date sqlDate = new java.sql.Date(getDatespinnerDateModel().getDate().getTime());
						ServicioExtraccion.insertarExtracciones(mp.getId_materia(), Integer.valueOf(InventariojTextField.getText()), Integer.valueOf(LuiqSacadojTextField.getText()), Integer.valueOf(TiempoReposojTextField.getText()), Float.valueOf(PerdidajTextField.getText()), Float.valueOf(PerdidaMensualjTextField.getText()), Float.valueOf(PerdidaAnualjTextField.getText()), Float.valueOf(PerdidaTotaljTextField.getText()),ObserrvacionesjTextArea.getText(), sqlDate, Float.valueOf(GradojTextField.getText()));
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
						TableModel defaultTableModel = new TableModel();
						LinkedList<extracciones> list = new LinkedList<extracciones>();
						list = ServicioExtraccion.getxtracciones(mp.getId_materia());
						ArrayList<Object> columnDataInventario = new ArrayList<Object>();
						ArrayList<Object> columnDataLiqSacado = new ArrayList<Object>();					
						ArrayList<Object> columnDataAnejo = new ArrayList<Object>();
						ArrayList<Object> columnDataPerdida = new ArrayList<Object>();
						ArrayList<Object> columnDataPerdidaMensual= new ArrayList<Object>();
						ArrayList<Object> columnDataPerdidaAnual= new ArrayList<Object>();
						ArrayList<Object> columnDataPerdidaTotal= new ArrayList<Object>();
						ArrayList<Object> columnDataObservaciones= new ArrayList<Object>();
						ArrayList<Object> columnDataFecha= new ArrayList<Object>();
						ArrayList<Object> columnDataGrado= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataInventario.add(list.get(i).getInventario());
							columnDataLiqSacado.add(list.get(i).getLiqsacado());					
							columnDataAnejo.add(list.get(i).getAnejo());
							columnDataPerdida.add(dosDigitos.format(list.get(i).getPerdida()));
							columnDataPerdidaMensual.add(dosDigitos.format(list.get(i).getPerdidamensual()));
							columnDataPerdidaAnual.add(dosDigitos.format(list.get(i).getPerdidaanual()));
							columnDataPerdidaTotal.add(dosDigitos.format(list.get(i).getPerdidatotal()));
							columnDataObservaciones.add(list.get(i).getObservaciones());
							columnDataFecha.add(list.get(i).getFecha());
							columnDataGrado.add(list.get(i).getGrado());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Inventario",columnDataInventario.toArray());
						defaultTableModel.addColumn("Líquido Sacado",columnDataLiqSacado.toArray());					
						defaultTableModel.addColumn("Tiempo Reposo",columnDataAnejo.toArray());
						defaultTableModel.addColumn("Perdida",columnDataPerdida.toArray());
						defaultTableModel.addColumn("Pérdida Mensual",columnDataPerdidaMensual.toArray());	
						defaultTableModel.addColumn("Pérdida Anual",columnDataPerdidaAnual.toArray());	
						defaultTableModel.addColumn("Pérdida Total",columnDataPerdidaTotal.toArray());
						defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
						defaultTableModel.addColumn("Fecha",columnDataFecha.toArray());	
						defaultTableModel.addColumn("Grado",columnDataGrado.toArray());	
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
						JOptionPane.showMessageDialog(ExtraccionVisual.this, "Producto Insertado", "Informacion", JOptionPane.INFORMATION_MESSAGE);	
						UserInterfaceSuport.clearComponents(jPanel);
							ObserrvacionesjTextArea.setText("");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(ExtraccionVisual.this, "No se puede insertar dos Materias iguales", "Error", JOptionPane.ERROR_MESSAGE);
												// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						UserInterfaceSuport.clearComponents(getJContentPane());
						ModificarjButton.setEnabled(false);
						ImprimirjButton.setEnabled(false);
						EliminarjButton.setEnabled(false);
						InsetarjButton.setEnabled(true);
								
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return InsetarjButton;
	}

	/**
	 * This method initializes ModificarjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getModificarjButton() {
		if (ModificarjButton == null) {
			ModificarjButton = new JButton();
			ModificarjButton.setText("Modificar");
			ModificarjButton.setBounds(new Rectangle(296, 211, 118, 39));
			ModificarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Refresh_24x24.png")));
			ModificarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(ExtraccionVisual.this, "Esta seguro que desea modificar el Producto?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = jTable.getSelectedRow();
					try {
						LinkedList<extracciones> mediciones = ServicioExtraccion.getxtracciones(mp.getId_materia());
						extracciones u = mediciones.get(pos);
						java.sql.Date fecha = new java.sql.Date(getDatespinnerDateModel().getDate().getTime());
						ServicioExtraccion.ActualizarExtraccion(u.getId_extracciones(), Integer.valueOf(InventariojTextField.getText()), Integer.valueOf(LuiqSacadojTextField.getText()), Integer.valueOf(TiempoReposojTextField.getText()), Float.valueOf(PerdidajTextField.getText()), Float.valueOf(PerdidaMensualjTextField.getText()), Float.valueOf(PerdidaAnualjTextField.getText()), Float.valueOf(PerdidaTotaljTextField.getText()),ObserrvacionesjTextArea.getText(), fecha, Float.valueOf(GradojTextField.getText()));
						JOptionPane.showMessageDialog(ExtraccionVisual.this, "Producto Modificado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
						TableModel defaultTableModel = new TableModel();
						LinkedList<extracciones> list = new LinkedList<extracciones>();
						list = ServicioExtraccion.getxtracciones(mp.getId_materia());
						ArrayList<Object> columnDataInventario = new ArrayList<Object>();
						ArrayList<Object> columnDataLiqSacado = new ArrayList<Object>();					
						ArrayList<Object> columnDataAnejo = new ArrayList<Object>();
						ArrayList<Object> columnDataPerdida = new ArrayList<Object>();
						ArrayList<Object> columnDataPerdidaMensual= new ArrayList<Object>();
						ArrayList<Object> columnDataPerdidaAnual= new ArrayList<Object>();
						ArrayList<Object> columnDataPerdidaTotal= new ArrayList<Object>();
						ArrayList<Object> columnDataObservaciones= new ArrayList<Object>();
						ArrayList<Object> columnDataFecha= new ArrayList<Object>();
						ArrayList<Object> columnDataGrado= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataInventario.add(list.get(i).getInventario());
							columnDataLiqSacado.add(list.get(i).getLiqsacado());					
							columnDataAnejo.add(list.get(i).getAnejo());
							columnDataPerdida.add(dosDigitos.format(list.get(i).getPerdida()));
							columnDataPerdidaMensual.add(dosDigitos.format(list.get(i).getPerdidamensual()));
							columnDataPerdidaAnual.add(dosDigitos.format(list.get(i).getPerdidaanual()));
							columnDataPerdidaTotal.add(dosDigitos.format(list.get(i).getPerdidatotal()));
							columnDataObservaciones.add(list.get(i).getObservaciones());
							columnDataFecha.add(list.get(i).getFecha());
							columnDataGrado.add(list.get(i).getGrado());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Inventario",columnDataInventario.toArray());
						defaultTableModel.addColumn("Líquido Sacado",columnDataLiqSacado.toArray());					
						defaultTableModel.addColumn("Tiempo Reposo",columnDataAnejo.toArray());
						defaultTableModel.addColumn("Perdida",columnDataPerdida.toArray());
						defaultTableModel.addColumn("Pérdida Mensual",columnDataPerdidaMensual.toArray());	
						defaultTableModel.addColumn("Pérdida Anual",columnDataPerdidaAnual.toArray());	
						defaultTableModel.addColumn("Pérdida Total",columnDataPerdidaTotal.toArray());
						defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
						defaultTableModel.addColumn("Fecha",columnDataFecha.toArray());	
						defaultTableModel.addColumn("Grado",columnDataGrado.toArray());	
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
						UserInterfaceSuport.clearComponents(jPanel);
						ObserrvacionesjTextArea.setText("");
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					UserInterfaceSuport.clearComponents(getJContentPane());
					ModificarjButton.setEnabled(false);
					EliminarjButton.setEnabled(false);
					ImprimirjButton.setEnabled(false);
					InsetarjButton.setEnabled(true);	
					}
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return ModificarjButton;
	}

	
	
	
	/**
	 * This method initializes EliminarjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEliminarjButton() {
		if (EliminarjButton == null) {
			EliminarjButton = new JButton();
			EliminarjButton.setText("Eliminar");
			EliminarjButton.setBounds(new Rectangle(424, 211, 115, 39));
			EliminarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/edit-cut.png")));
			EliminarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(ExtraccionVisual.this, "Esta seguro que desea eliminar el Producto?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = jTable.getSelectedRow();
					try {
						LinkedList<extracciones> mediciones = ServicioExtraccion.getxtracciones(mp.getId_materia());
						extracciones u = mediciones.get(pos);
						ServicioExtraccion.EliminarExtraccion(u.getId_extracciones());
						JOptionPane.showMessageDialog(ExtraccionVisual.this, "Producto Eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
						TableModel defaultTableModel = new TableModel();
						LinkedList<extracciones> list = new LinkedList<extracciones>();
						list = ServicioExtraccion.getxtracciones(mp.getId_materia());
						ArrayList<Object> columnDataInventario = new ArrayList<Object>();
						ArrayList<Object> columnDataLiqSacado = new ArrayList<Object>();					
						ArrayList<Object> columnDataAnejo = new ArrayList<Object>();
						ArrayList<Object> columnDataPerdida = new ArrayList<Object>();
						ArrayList<Object> columnDataPerdidaMensual= new ArrayList<Object>();
						ArrayList<Object> columnDataPerdidaAnual= new ArrayList<Object>();
						ArrayList<Object> columnDataPerdidaTotal= new ArrayList<Object>();
						ArrayList<Object> columnDataObservaciones= new ArrayList<Object>();
						ArrayList<Object> columnDataFecha= new ArrayList<Object>();
						ArrayList<Object> columnDataGrado= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataInventario.add(list.get(i).getInventario());
							columnDataLiqSacado.add(list.get(i).getLiqsacado());					
							columnDataAnejo.add(list.get(i).getAnejo());
							columnDataPerdida.add(dosDigitos.format(list.get(i).getPerdida()));
							columnDataPerdidaMensual.add(dosDigitos.format(list.get(i).getPerdidamensual()));
							columnDataPerdidaAnual.add(dosDigitos.format(list.get(i).getPerdidaanual()));
							columnDataPerdidaTotal.add(dosDigitos.format(list.get(i).getPerdidatotal()));
							columnDataObservaciones.add(list.get(i).getObservaciones());
							columnDataFecha.add(list.get(i).getFecha());
							columnDataGrado.add(list.get(i).getGrado());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Inventario",columnDataInventario.toArray());
						defaultTableModel.addColumn("Líquido Sacado",columnDataLiqSacado.toArray());					
						defaultTableModel.addColumn("Tiempo Reposo",columnDataAnejo.toArray());
						defaultTableModel.addColumn("Perdida",columnDataPerdida.toArray());
						defaultTableModel.addColumn("Pérdida Mensual",columnDataPerdidaMensual.toArray());	
						defaultTableModel.addColumn("Pérdida Anual",columnDataPerdidaAnual.toArray());	
						defaultTableModel.addColumn("Pérdida Total",columnDataPerdidaTotal.toArray());
						defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
						defaultTableModel.addColumn("Fecha",columnDataFecha.toArray());	
						defaultTableModel.addColumn("Grado",columnDataGrado.toArray());	
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
						UserInterfaceSuport.clearComponents(jPanel);
						ObserrvacionesjTextArea.setText("");
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(ExtraccionVisual.this, "No se puede eliminar el Producto porque Existen relaciones con el mismo", "Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					UserInterfaceSuport.clearComponents(getJContentPane());
					ModificarjButton.setEnabled(false);
					EliminarjButton.setEnabled(false);
					ImprimirjButton.setEnabled(false);
					InsetarjButton.setEnabled(true);
											
				}
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return EliminarjButton;
	}

	/**
	 * This method initializes RestaurarjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRestaurarjButton() {
		if (RestaurarjButton == null) {
			RestaurarjButton = new JButton();
			RestaurarjButton.setText("Borrar");
			RestaurarjButton.setBounds(new Rectangle(553, 211, 99, 39));
			RestaurarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/edit-clear.png")));
			RestaurarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					UserInterfaceSuport.clearComponents(jPanel);
					ModificarjButton.setEnabled(false);
					EliminarjButton.setEnabled(false);
					ImprimirjButton.setEnabled(false);
					InsetarjButton.setEnabled(true);
					ObserrvacionesjTextArea.setText("");
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return RestaurarjButton;
	}

	/**
	 * This method initializes ImprimirjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getImprimirjButton() {
		if (ImprimirjButton == null) {
			ImprimirjButton = new JButton();
			ImprimirjButton.setText("Imprimir Extracci\u00F3n");
			ImprimirjButton.setBounds(new Rectangle(663, 211, 163, 39));
			ImprimirjButton.setIcon(new ImageIcon(getClass().getResource("/Img/printer.png")));
			ImprimirjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Reportes.getR().ReporteExtraccion(mp.getId_materia(), extrac.getId_extracciones());
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return ImprimirjButton;
	}

	/**
	 * This method initializes CerrarjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCerrarjButton() {
		if (CerrarjButton == null) {
			CerrarjButton = new JButton();
			CerrarjButton.setText("Cerrar");
			CerrarjButton.setBounds(new Rectangle(838, 211, 99, 39));
			CerrarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Delete_16x16.png")));
			CerrarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new Extracciones().setVisible(true);
					dispose();
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return CerrarjButton;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(620, 26, 252, 135));
			jScrollPane1.setViewportView(getObserrvacionesjTextArea());
		}
		return jScrollPane1;
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
			jPanel.add(getCerrarjButton(), null);
			jPanel.add(getImprimirjButton(), null);
			jPanel.add(getRestaurarjButton(), null);
			jPanel.add(getEliminarjButton(), null);
			jPanel.add(getModificarjButton(), null);
			jPanel.add(getInsetarjButton(), null);
			jPanel.add(PerdidaAnualjLabel, null);
			jPanel.add(getPerdidaAnualjTextField(), null);
			jPanel.add(getPerididaAnualjButton(), null);
			jPanel.add(getPerdidamensualjButton(), null);
			jPanel.add(getPerdidaMensualjTextField(), null);
			jPanel.add(PerdidaMensualjLabel, null);
			jPanel.add(getPerdidaTotaljButton(), null);
			jPanel.add(getPerdidaTotaljTextField(), null);
			jPanel.add(PerdidaTotaljLabel, null);
			jPanel.add(getPerdidajButton(), null);
			jPanel.add(getPerdidajTextField(), null);
			jPanel.add(PerdidajLabel, null);
			jPanel.add(getInventariojTextField(), null);
			jPanel.add(InventariojLabel, null);
			jPanel.add(getLuiqSacadojTextField(), null);
			jPanel.add(LiquidoSacadojLabel, null);
			jPanel.add(getTiempoReposojTextField(), null);
			jPanel.add(TiempoReposojLabel, null);
			jPanel.add(getGradojTextField(), null);
			jPanel.add(GradojLabel, null);
			jPanel.add(getDatejSpinner(), null);
			jPanel.add(FechajLabel, null);
			jPanel.add(ObservacionesjLabel, null);
			jPanel.add(GradoMjLabel, null);
			jPanel.add(ProductojLabel, null);
			jPanel.add(getJScrollPane1(), null);
			
			JLabel lblTipoMateriaPrima = new JLabel("Tipo Materia Prima:  " + mp.getTipomateria());
			lblTipoMateriaPrima.setBounds(882, 77, 241, 20);
			jPanel.add(lblTipoMateriaPrima);
		}
		return jPanel;
	}
}  //  @jve:decl-index=0:visual-constraint="11,-8"
