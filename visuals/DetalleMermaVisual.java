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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import model.DetalleMerma;
import model.MateriaPrima;
import model.Mes;
import model.TipoMateria;
import services.ServicioDetalleMerma;
import services.ServicioMateria;
import services.ServicioMes;
import Utils.FormatoTabla;
import Utils.Reportes;
import Utils.TableModel;
import Utils.UserInterfaceSuport;
import Utils.Validate;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class DetalleMermaVisual extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JComboBox TipoMateriajComboBox=null;
	private JLabel ProductojLabel = null;
	private JLabel TiempojLabel = null;
	private JTextField ReposojTextField = null;
	private JLabel CantidadTonelesjLabel = null;
	private JTextField CantidadjTextField = null;
	private JLabel TamanojLabel = null;
	private JTextField TamanojTextField = null;
	private JLabel TotaljLabel = null;
	private JTextField TotaljTextField = null;
	private JLabel ExistenciajLabel = null;
	private JTextField ExistenciajTextField = null;
	private JLabel MesjLabel = null;
	private JComboBox MesjComboBox=null;
	private JSpinner DatejSpinner = null;
	private SpinnerDateModel DatespinnerDateModel = null;
	private JLabel FechajLabel = null;
	private JButton InsertarjButton = null;
	private JLabel PerdidajLabel = null;
	private JButton CalcularjButton = null;
	private JTextField PerdidajTextField = null;
	private JButton ModificarjButton = null;
	private JButton EliminarjButton = null;
	private JButton SalirjButton = null;
	private JButton InicialjButton = null;
	private JButton RestaurarjButton = null;
	private JButton ReportejButton = null;
	private JLabel jLabel = null;
	private JPanel jPanel = null;
	private JPanel panel;
	private JLabel lblAo;
	private JTextField AnotextField;
	private JLabel lblTipoMateriaPrima;
	private JComboBox ProductcomboBox;
	
	
	
	public DetalleMermaVisual() {
		super();
		initialize();
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new Dimension(1306, 769));
        this.setTitle("Gestionar Estado de Solera");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/toneles.png")));
        this.setContentPane(getJContentPane());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		ModificarjButton.setEnabled(false);
		EliminarjButton.setEnabled(false);
		InsertarjButton.setEnabled(true);
		TableModel defaultTableModel = new TableModel();
		ArrayList<Object> columnDataTiempo = new ArrayList<Object>();
		ArrayList<Object> columnDataProducto = new ArrayList<Object>();					
		ArrayList<Object> columnDataCantidad = new ArrayList<Object>();
		ArrayList<Object> columnDataTamano = new ArrayList<Object>();
		ArrayList<Object> columnDataTotal= new ArrayList<Object>();
		ArrayList<Object> columnDataExistencia= new ArrayList<Object>();
		ArrayList<Object> columnDataPerdida= new ArrayList<Object>();
		ArrayList<Object> columnDataMes= new ArrayList<Object>();
		ArrayList<Object> columnDataAno= new ArrayList<Object>();
		defaultTableModel.addColumn("Tiempo de Reposo",columnDataTiempo.toArray());
		defaultTableModel.addColumn("Producto",columnDataProducto.toArray());					
		defaultTableModel.addColumn("Cantidad Toneles",columnDataCantidad.toArray());
		defaultTableModel.addColumn("Tamaño Toneles",columnDataTamano.toArray());
		defaultTableModel.addColumn("Total Inicial",columnDataTotal.toArray());		
		defaultTableModel.addColumn("Existencia",columnDataExistencia.toArray());
		defaultTableModel.addColumn("% Perdida/Mes",columnDataPerdida.toArray());
		defaultTableModel.addColumn("Mes",columnDataMes.toArray());
		defaultTableModel.addColumn("Fecha",columnDataAno.toArray());
		getJTable().setModel(defaultTableModel);
		getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
		getJTable().setRowHeight(20);
		jTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
			    simbolo.setDecimalSeparator('.');
				DecimalFormat dosDigitos = new DecimalFormat( "####.#####",simbolo);
				int pos = jTable.getSelectedRow();
				LinkedList<DetalleMerma> toneles = ServicioDetalleMerma.getMermasAño(((Mes)MesjComboBox.getSelectedItem()).getID(),Float.valueOf(AnotextField.getText()),((TipoMateria)ProductcomboBox.getSelectedItem()).getId_tipomateria());
				DetalleMerma t = toneles.get(pos);
				ReposojTextField.setText(String.valueOf(t.getAnejo()));
				CantidadjTextField.setText(String.valueOf(t.getCanttoneles()));
				TamanojTextField.setText(String.valueOf(t.getTamano()));
				TotaljTextField.setText(String.valueOf(t.getTotal()));
				ExistenciajTextField.setText(String.valueOf(t.getExistencia()));
				PerdidajTextField.setText(String.valueOf(dosDigitos.format(t.getPerdida())));
				DatejSpinner.setValue(t.getFecha());
				ModificarjButton.setEnabled(true);
				EliminarjButton.setEnabled(true);
				InsertarjButton.setEnabled(false);
				MesjComboBox.setEditable(false);
				ProductcomboBox.setEditable(false);
				}
		});	
		
		  //Combobox Mes
				LinkedList<Mes> meses = new LinkedList<Mes>();
				try {
					meses = ServicioMes.getRoles();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DefaultComboBoxModel boxModelM = new DefaultComboBoxModel();
				boxModelM.addElement(new String("<Seleccione>"));
				for (int i = 0; i < meses.size(); i++) {
					boxModelM.addElement(meses.get(i));
				}
				getMesjComboBox().setModel(boxModelM);
				
				//Combobox Tipo Materia Prima
				LinkedList<TipoMateria> materias1 = new LinkedList<TipoMateria>();
				try {
					materias1 = ServicioMateria.getMaterias();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DefaultComboBoxModel boxModelF1 = new DefaultComboBoxModel();
				boxModelF1.addElement(new String("<Seleccione>"));
				for (int i = 0; i < materias1.size(); i++) {
					boxModelF1.addElement(materias1.get(i));
				}
				
				getProductcomboBox().setModel(boxModelF1);
				
				
					//ComboBox Materias Primas 				
					LinkedList<MateriaPrima> materiasRM = new LinkedList<MateriaPrima>();
					try {
						materiasRM = ServicioMateria.getMateriasPrimas();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					DefaultComboBoxModel boxModelFRM = new DefaultComboBoxModel();
					boxModelFRM.addElement(new String("<Seleccione>"));
					for (int i = 0; i < materiasRM.size(); i++) {
						boxModelFRM.addElement(materiasRM.get(i));
					}
					getTipoMateriajComboBox().setModel(boxModelFRM);		
				
				
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
			jLabel = new JLabel();
			jLabel.setText("*");
			jLabel.setBounds(new Rectangle(149, 137, 13, 19));
			PerdidajLabel = new JLabel();
			PerdidajLabel.setText("% Pérdida/Mes:");
			PerdidajLabel.setBounds(new Rectangle(697, 119, 124, 27));
			ExistenciajLabel = new JLabel();
			ExistenciajLabel.setText("Existencia:");
			ExistenciajLabel.setBounds(new Rectangle(506, 119, 124, 27));
			TotaljLabel = new JLabel();			
			TotaljLabel.setText("Total Inicial:");
			TotaljLabel.setBounds(new Rectangle(371, 118, 124, 27));
			TamanojLabel = new JLabel();
			TamanojLabel.setText("Tamaño de Toneles:");
			TamanojLabel.setBounds(new Rectangle(173, 119, 124, 27));
			CantidadTonelesjLabel = new JLabel();
			CantidadTonelesjLabel.setText("Cantidad Toneles:");
			CantidadTonelesjLabel.setBounds(new Rectangle(26, 118, 111, 27));
			TiempojLabel = new JLabel();
			TiempojLabel.setText("Reposo (Meses):");
			TiempojLabel.setBounds(new Rectangle(26, 17, 111, 27));
			ProductojLabel = new JLabel();
			ProductojLabel.setText("Producto:");
			ProductojLabel.setBounds(new Rectangle(174, 17, 260, 27));
			jContentPane = new JPanel();
			GridBagLayout gbl_jContentPane = new GridBagLayout();
			gbl_jContentPane.columnWidths = new int[]{327, 828, 0};
			gbl_jContentPane.rowHeights = new int[]{243, 460, 0};
			gbl_jContentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
			gbl_jContentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			jContentPane.setLayout(gbl_jContentPane);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			jContentPane.add(getPanel(), gbc_panel);
			GridBagConstraints gbc_jPanel = new GridBagConstraints();
			gbc_jPanel.fill = GridBagConstraints.BOTH;
			gbc_jPanel.insets = new Insets(0, 0, 5, 0);
			gbc_jPanel.gridx = 1;
			gbc_jPanel.gridy = 0;
			jContentPane.add(getJPanel(), gbc_jPanel);
			GridBagConstraints gbc_jScrollPane = new GridBagConstraints();
			gbc_jScrollPane.fill = GridBagConstraints.BOTH;
			gbc_jScrollPane.gridwidth = 2;
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
	 * This method initializes TipoMateriajComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getTipoMateriajComboBox() {
		if (TipoMateriajComboBox  == null) {
			TipoMateriajComboBox = new JComboBox();
			TipoMateriajComboBox.setBounds(new Rectangle(174, 44, 260, 27));
			}
		return TipoMateriajComboBox;
	}

	
	/**
	 * This method initializes TipoMateriajComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getMesjComboBox() {
		if (MesjComboBox  == null) {
			MesjComboBox = new JComboBox();
			MesjComboBox.setBounds(12, 44, 124, 27);
			}
		return MesjComboBox;
	}
	/**
	 * This method initializes ReposojTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getReposojTextField() {
		if (ReposojTextField == null) {
			ReposojTextField = new JTextField();
			ReposojTextField.setBounds(new Rectangle(26, 44, 111, 27));
			Validate.validateDigit(ReposojTextField);
		}
		return ReposojTextField;
	}

	/**
	 * This method initializes CantidadjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCantidadjTextField() {
		if (CantidadjTextField == null) {
			CantidadjTextField = new JTextField();
			CantidadjTextField.setBounds(new Rectangle(26, 145, 111, 27));
			Validate.validateDigit(CantidadjTextField);
		}
		return CantidadjTextField;
	}

	/**
	 * This method initializes TamanojTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTamanojTextField() {
		if (TamanojTextField == null) {
			TamanojTextField = new JTextField();
			TamanojTextField.setBounds(new Rectangle(173, 146, 124, 27));
			Validate.validateDigit(TamanojTextField);
		}
		return TamanojTextField;
	}

	/**
	 * This method initializes TotaljTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTotaljTextField() {
		if (TotaljTextField == null) {
			TotaljTextField = new JTextField();
			TotaljTextField.setBounds(new Rectangle(371, 145, 124, 27));
			Validate.validateDigit(TotaljTextField);
		}
		return TotaljTextField;
	}

	/**
	 * This method initializes ExistenciajTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getExistenciajTextField() {
		if (ExistenciajTextField == null) {
			ExistenciajTextField = new JTextField();
			ExistenciajTextField.setBounds(new Rectangle(506, 146, 124, 27));
			Validate.validateDigit(ExistenciajTextField);
		}
		return ExistenciajTextField;
	}

	
	/**
	 * This method initializes DatejSpinner	
	 * 	
	 * @return javax.swing.JSpinner	
	 */
	private JSpinner getDatejSpinner() {
		if (DatejSpinner == null) {
			DatejSpinner = new JSpinner(getDatespinnerDateModel() );
			DatejSpinner.setBounds(446, 44, 124, 27);
			DatejSpinner.setEnabled(false);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			DatejSpinner.setEditor(new JSpinner.DateEditor(DatejSpinner,dateFormat.toPattern()));
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
	 * This method initializes InsertarjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getInsertarjButton() {
		if (InsertarjButton == null) {
			InsertarjButton = new JButton();
			InsertarjButton.setText("Insertar");
			InsertarjButton.setBounds(new Rectangle(26, 198, 124, 27));
			InsertarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_SysInfo_Unit1_ilInfoStates1_16x16.png")));
			InsertarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(ReposojTextField.getText().length()>0 && CantidadjTextField.getText().length()!= 0 && TamanojTextField.getText().length()!=0 && TotaljTextField.getText().length()!=0 && ExistenciajTextField.getText().length()!=0 && PerdidajTextField.getText().length()!=0 && (TipoMateriajComboBox.getSelectedIndex()!=0) && (MesjComboBox.getSelectedIndex()!=0)){
						try {
							java.sql.Date sqlDate = new java.sql.Date(getDatespinnerDateModel().getDate().getTime());
							
						ServicioDetalleMerma.insertarDetalleMerma(Integer.valueOf(ReposojTextField.getText()), ((MateriaPrima)getTipoMateriajComboBox().getSelectedItem()).getId_materia(), Integer.valueOf(CantidadjTextField.getText()), Integer.valueOf(TamanojTextField.getText()), Integer.valueOf(TotaljTextField.getText()), Integer.valueOf(ExistenciajTextField.getText()), Float.valueOf(PerdidajTextField.getText()), ((Mes)getMesjComboBox().getSelectedItem()).getID(), sqlDate);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
				        TableModel defaultTableModel = new TableModel();
						LinkedList<DetalleMerma> list = new LinkedList<DetalleMerma>();
						list =ServicioDetalleMerma.getMermasAño(((Mes)MesjComboBox.getSelectedItem()).getID(),Float.valueOf(AnotextField.getText()),((TipoMateria)ProductcomboBox.getSelectedItem()).getId_tipomateria());
						ArrayList<Object> columnDataTiempo = new ArrayList<Object>();
						ArrayList<Object> columnDataProducto = new ArrayList<Object>();					
						ArrayList<Object> columnDataCantidad = new ArrayList<Object>();
						ArrayList<Object> columnDataTamano = new ArrayList<Object>();
						ArrayList<Object> columnDataTotal= new ArrayList<Object>();
						ArrayList<Object> columnDataExistencia= new ArrayList<Object>();
						ArrayList<Object> columnDataPerdida= new ArrayList<Object>();
						ArrayList<Object> columnDataMes= new ArrayList<Object>();
						ArrayList<Object> columnDataAno= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataTiempo.add(list.get(i).getAnejo());
							columnDataProducto.add(list.get(i).getMateria());					
							columnDataCantidad.add(list.get(i).getCanttoneles());
							columnDataTamano.add(list.get(i).getTamano());
							columnDataTotal.add(list.get(i).getTotal());
							columnDataExistencia.add(list.get(i).getExistencia());
							columnDataPerdida.add(dosDigitos.format(list.get(i).getPerdida()));
							columnDataMes.add(list.get(i).getMes());
							columnDataAno.add(list.get(i).getFecha());		
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Tiempo de Reposo",columnDataTiempo.toArray());
						defaultTableModel.addColumn("Producto",columnDataProducto.toArray());					
						defaultTableModel.addColumn("Cantidad Toneles",columnDataCantidad.toArray());
						defaultTableModel.addColumn("Tamaño Toneles",columnDataTamano.toArray());
						defaultTableModel.addColumn("Total Inicial",columnDataTotal.toArray());		
						defaultTableModel.addColumn("Existencia",columnDataExistencia.toArray());
						defaultTableModel.addColumn("% Perdida/Mes",columnDataPerdida.toArray());
						defaultTableModel.addColumn("Mes",columnDataMes.toArray());
						defaultTableModel.addColumn("Fecha",columnDataAno.toArray());
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
						JOptionPane.showMessageDialog(DetalleMermaVisual.this, "Producto Insertado", "Informacion", JOptionPane.INFORMATION_MESSAGE);	
						UserInterfaceSuport.clearComponents(jPanel);
							
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(DetalleMermaVisual.this, "No se puede insertar dos Materias iguales", "Error", JOptionPane.ERROR_MESSAGE);
												// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				     UserInterfaceSuport.clearComponents(getJContentPane());
					InsertarjButton.setEnabled(true);
					ModificarjButton.setEnabled(false);
					EliminarjButton.setEnabled(false);
					}
					else{
						JOptionPane.showMessageDialog(DetalleMermaVisual.this, "Campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);	
						System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
					
				}
			});
		}
		return InsertarjButton;
	}

	/**
	 * This method initializes CalcularjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCalcularjButton() {
		if (CalcularjButton == null) {
			CalcularjButton = new JButton();
			CalcularjButton.setText("=");
			CalcularjButton.setBounds(new Rectangle(644, 119, 41, 53));
			CalcularjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(TotaljTextField.getText().length()!=0 && ExistenciajTextField.getText().length()!=0 && ReposojTextField.getText().length()!=0){
					DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
				    simbolo.setDecimalSeparator('.');
					DecimalFormat dosDigitos = new DecimalFormat( "####.#####",simbolo);
				    float total= Integer.valueOf(TotaljTextField.getText());
					float exist=Integer.valueOf(ExistenciajTextField.getText())*100;
					float perdida= exist/total;
					float porciento=100-perdida;
					int reposo = Integer.valueOf(ReposojTextField.getText());
					PerdidajTextField.setText(String.valueOf(dosDigitos.format(porciento/reposo)));
					}else{
						JOptionPane.showMessageDialog(DetalleMermaVisual.this, "Campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
					}
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return CalcularjButton;
	}

	/**
	 * This method initializes PerdidajTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPerdidajTextField() {
		if (PerdidajTextField == null) {
			PerdidajTextField = new JTextField();
			PerdidajTextField.setBounds(new Rectangle(697, 146, 124, 27));
			Validate.validateDigitAndComma(PerdidajTextField);
		}
		return PerdidajTextField;
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
			ModificarjButton.setBounds(new Rectangle(162, 198, 124, 27));
			ModificarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Refresh_24x24.png")));
			ModificarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(DetalleMermaVisual.this, "Esta seguro que desea modificar el Producto?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(getTipoMateriajComboBox().getSelectedIndex()!=0 && getMesjComboBox().getSelectedIndex()!=0){
					if(result == JOptionPane.YES_OPTION){
					int pos = jTable.getSelectedRow();
					try {
						LinkedList<DetalleMerma> mediciones = ServicioDetalleMerma.getMermasAño(((Mes)MesjComboBox.getSelectedItem()).getID(),Float.valueOf(AnotextField.getText()),((TipoMateria)ProductcomboBox.getSelectedItem()).getId_tipomateria());
						DetalleMerma u = mediciones.get(pos);
						java.sql.Date sqlDate = new java.sql.Date(getDatespinnerDateModel().getDate().getTime());
						ServicioDetalleMerma.ActualizarMedicion(u.getId_detallemerma(), Integer.valueOf(ReposojTextField.getText()),((MateriaPrima)getTipoMateriajComboBox().getSelectedItem()).getId_materia(), Integer.valueOf(CantidadjTextField.getText()), Integer.valueOf(TamanojTextField.getText()), Integer.valueOf(TotaljTextField.getText()), Float.valueOf(PerdidajTextField.getText()), Integer.valueOf(ExistenciajTextField.getText()), sqlDate,((Mes)getMesjComboBox().getSelectedItem()).getID());
						JOptionPane.showMessageDialog(DetalleMermaVisual.this, "Producto Modificado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);  
						TableModel defaultTableModel = new TableModel();
						LinkedList<DetalleMerma> list = new LinkedList<DetalleMerma>();
						list =ServicioDetalleMerma.getMermasAño(((Mes)MesjComboBox.getSelectedItem()).getID(),Float.valueOf(AnotextField.getText()),((TipoMateria)ProductcomboBox.getSelectedItem()).getId_tipomateria());
						ArrayList<Object> columnDataTiempo = new ArrayList<Object>();
						ArrayList<Object> columnDataProducto = new ArrayList<Object>();					
						ArrayList<Object> columnDataCantidad = new ArrayList<Object>();
						ArrayList<Object> columnDataTamano = new ArrayList<Object>();
						ArrayList<Object> columnDataTotal= new ArrayList<Object>();
						ArrayList<Object> columnDataExistencia= new ArrayList<Object>();
						ArrayList<Object> columnDataPerdida= new ArrayList<Object>();
						ArrayList<Object> columnDataMes= new ArrayList<Object>();
						ArrayList<Object> columnDataAno= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataTiempo.add(list.get(i).getAnejo());
							columnDataProducto.add(list.get(i).getMateria());					
							columnDataCantidad.add(list.get(i).getCanttoneles());
							columnDataTamano.add(list.get(i).getTamano());
							columnDataTotal.add(list.get(i).getTotal());
							columnDataExistencia.add(list.get(i).getExistencia());
							columnDataPerdida.add(dosDigitos.format(list.get(i).getPerdida()));
							columnDataMes.add(list.get(i).getMes());
							columnDataAno.add(list.get(i).getFecha());		
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Tiempo de Reposo",columnDataTiempo.toArray());
						defaultTableModel.addColumn("Producto",columnDataProducto.toArray());					
						defaultTableModel.addColumn("Cantidad Toneles",columnDataCantidad.toArray());
						defaultTableModel.addColumn("Tamaño Toneles",columnDataTamano.toArray());
						defaultTableModel.addColumn("Total Inicial",columnDataTotal.toArray());		
						defaultTableModel.addColumn("Existencia",columnDataExistencia.toArray());
						defaultTableModel.addColumn("% Perdida/Mes",columnDataPerdida.toArray());
						defaultTableModel.addColumn("Mes",columnDataMes.toArray());
						defaultTableModel.addColumn("Fecha",columnDataAno.toArray());
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
						UserInterfaceSuport.clearComponents(jPanel);
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
					InsertarjButton.setEnabled(true);
					MesjComboBox.setEditable(true);
					ProductcomboBox.setEditable(true);
					}
					}else{
						JOptionPane.showMessageDialog(DetalleMermaVisual.this, "Debe elegir el Producto y el Mes ", "Informacion", JOptionPane.ERROR_MESSAGE);
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
			EliminarjButton.setBounds(new Rectangle(298, 198, 124, 27));
			EliminarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/edit-cut.png")));
			EliminarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(DetalleMermaVisual.this, "Esta seguro que desea eliminar el Producto?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = jTable.getSelectedRow();
					try {
						LinkedList<DetalleMerma> mediciones = ServicioDetalleMerma.getMermasAño(((Mes)MesjComboBox.getSelectedItem()).getID(),Float.valueOf(AnotextField.getText()),((TipoMateria)ProductcomboBox.getSelectedItem()).getId_tipomateria());
						DetalleMerma u = mediciones.get(pos);
						ServicioDetalleMerma.EliminarDetalleMerma(u.getId_detallemerma());
						JOptionPane.showMessageDialog(DetalleMermaVisual.this, "Producto Eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
						TableModel defaultTableModel = new TableModel();
						LinkedList<DetalleMerma> list = new LinkedList<DetalleMerma>();
						list =ServicioDetalleMerma.getMermasAño(((Mes)MesjComboBox.getSelectedItem()).getID(),Float.valueOf(AnotextField.getText()),((TipoMateria)ProductcomboBox.getSelectedItem()).getId_tipomateria());
						ArrayList<Object> columnDataTiempo = new ArrayList<Object>();
						ArrayList<Object> columnDataProducto = new ArrayList<Object>();					
						ArrayList<Object> columnDataCantidad = new ArrayList<Object>();
						ArrayList<Object> columnDataTamano = new ArrayList<Object>();
						ArrayList<Object> columnDataTotal= new ArrayList<Object>();
						ArrayList<Object> columnDataExistencia= new ArrayList<Object>();
						ArrayList<Object> columnDataPerdida= new ArrayList<Object>();
						ArrayList<Object> columnDataMes= new ArrayList<Object>();
						ArrayList<Object> columnDataAno= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataTiempo.add(list.get(i).getAnejo());
							columnDataProducto.add(list.get(i).getMateria());					
							columnDataCantidad.add(list.get(i).getCanttoneles());
							columnDataTamano.add(list.get(i).getTamano());
							columnDataTotal.add(list.get(i).getTotal());
							columnDataExistencia.add(list.get(i).getExistencia());
							columnDataPerdida.add(dosDigitos.format(list.get(i).getPerdida()));
							columnDataMes.add(list.get(i).getMes());
							columnDataAno.add(list.get(i).getFecha());		
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Tiempo de Reposo",columnDataTiempo.toArray());
						defaultTableModel.addColumn("Producto",columnDataProducto.toArray());					
						defaultTableModel.addColumn("Cantidad Toneles",columnDataCantidad.toArray());
						defaultTableModel.addColumn("Tamaño Toneles",columnDataTamano.toArray());
						defaultTableModel.addColumn("Total Inicial",columnDataTotal.toArray());		
						defaultTableModel.addColumn("Existencia",columnDataExistencia.toArray());
						defaultTableModel.addColumn("% Perdida/Mes",columnDataPerdida.toArray());
						defaultTableModel.addColumn("Mes",columnDataMes.toArray());
						defaultTableModel.addColumn("Fecha",columnDataAno.toArray());
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
						UserInterfaceSuport.clearComponents(jPanel);
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(DetalleMermaVisual.this, "No se puede eliminar el Producto porque Existen relaciones con el mismo", "Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					UserInterfaceSuport.clearComponents(getJContentPane());
					ModificarjButton.setEnabled(false);
					EliminarjButton.setEnabled(false);
					InsertarjButton.setEnabled(true);
					MesjComboBox.setEditable(true);
					ProductcomboBox.setEditable(true);						
				}
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return EliminarjButton;
	}

	/**
	 * This method initializes SalirjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSalirjButton() {
		if (SalirjButton == null) {
			SalirjButton = new JButton();
			SalirjButton.setBounds(319, 198, 124, 27);
			SalirjButton.setText("Cerrar");
			SalirjButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Delete_16x16.png")));
			SalirjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Start i=new Start();
					new Principal(i).setVisible(true);
					dispose();
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return SalirjButton;
	}

	/**
	 * This method initializes InicialjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getInicialjButton() {
		if (InicialjButton == null) {
			InicialjButton = new JButton();
			InicialjButton.setText("=");
			InicialjButton.setBounds(new Rectangle(318, 119, 41, 53));
			InicialjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(TamanojTextField.getText().length()!=0 && CantidadjTextField.getText().length()!=0){
					Integer toneles = Integer.valueOf(TamanojTextField.getText());
					Integer cant = Integer.valueOf(CantidadjTextField.getText());
					TotaljTextField.setText(String.valueOf(toneles*cant));
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					}else {
						JOptionPane.showMessageDialog(DetalleMermaVisual.this, "Existen Bloques en Blanco", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return InicialjButton;
	}

	/**
	 * This method initializes RestaurarjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRestaurarjButton() {
		if (RestaurarjButton == null) {
			RestaurarjButton = new JButton();
			RestaurarjButton.setText("Borrar Bloques");
			RestaurarjButton.setBounds(new Rectangle(434, 198, 142, 27));
			RestaurarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/edit-clear.png")));
			RestaurarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					UserInterfaceSuport.clearComponents(jPanel);
					InsertarjButton.setEnabled(true);
					ModificarjButton.setEnabled(false);
					EliminarjButton.setEnabled(false);					
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return RestaurarjButton;
	}

	/**
	 * This method initializes ReportejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getReportejButton() {
		if (ReportejButton == null) {
			ReportejButton = new JButton();
			ReportejButton.setBounds(121, 198, 189, 27);
			ReportejButton.setText("Imprimir Estado Solera");
			ReportejButton.setEnabled(false);
			ReportejButton.setIcon(new ImageIcon(getClass().getResource("/Img/printer.png")));
			ReportejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Reportes.getR().EstadoSolera(((TipoMateria)ProductcomboBox.getSelectedItem()).getId_tipomateria(), ((Mes)MesjComboBox.getSelectedItem()).getID(), Float.valueOf(AnotextField.getText()));
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return ReportejButton;
	}



	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setBorder(new TitledBorder(null, "Datos Generales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			jPanel.setVisible(false);
			jPanel.setLayout(null);
			jPanel.add(getPerdidajTextField(), null);
			jPanel.add(PerdidajLabel, null);
			jPanel.add(getCalcularjButton(), null);
			jPanel.add(getExistenciajTextField(), null);
			jPanel.add(ExistenciajLabel, null);
			jPanel.add(getRestaurarjButton(), null);
			jPanel.add(getEliminarjButton(), null);
			jPanel.add(getModificarjButton(), null);
			jPanel.add(getInsertarjButton(), null);
			jPanel.add(getCantidadjTextField(), null);
			jPanel.add(CantidadTonelesjLabel, null);
			jPanel.add(jLabel, null);
			jPanel.add(getTamanojTextField(), null);
			jPanel.add(TamanojLabel, null);
			jPanel.add(getInicialjButton(), null);
			jPanel.add(TotaljLabel, null);
			jPanel.add(getTotaljTextField(), null);
			jPanel.add(getTipoMateriajComboBox(), null);
			jPanel.add(ProductojLabel, null);
			jPanel.add(getReposojTextField(), null);
			jPanel.add(TiempojLabel, null);
			FechajLabel = new JLabel();
			FechajLabel.setBounds(446, 17, 124, 27);
			jPanel.add(FechajLabel);
			FechajLabel.setText("Fecha Actualización:");
			jPanel.add(getDatejSpinner());
		}
		return jPanel;
	}
	
	
	
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Panel Introductorio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setLayout(null);
			panel.add(getMesjComboBox());
			MesjLabel = new JLabel();
			MesjLabel.setBounds(12, 17, 124, 27);
			panel.add(MesjLabel);
			MesjLabel.setText("Mes:");
			
			JButton btnAceptar = new JButton("Aceptar");
			btnAceptar.setIcon(new ImageIcon(DetalleMermaVisual.class.getResource("/img/OK.png")));
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(MesjComboBox.getSelectedIndex()!=0 && AnotextField.getText().length()!=0 && ProductcomboBox.getSelectedIndex()!=0 && ProductcomboBox.getSelectedIndex()!=6){
					jPanel.setVisible(true);
					ReportejButton.setEnabled(true);
					DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
				    simbolo.setDecimalSeparator('.');
					DecimalFormat dosDigitos = new DecimalFormat( "####.#####",simbolo);
			       TableModel defaultTableModel = new TableModel();
					LinkedList<DetalleMerma> list = new LinkedList<DetalleMerma>();
					list =ServicioDetalleMerma.getMermasAño(((Mes)MesjComboBox.getSelectedItem()).getID(),Float.valueOf(AnotextField.getText()),((TipoMateria)ProductcomboBox.getSelectedItem()).getId_tipomateria());
					ArrayList<Object> columnDataTiempo = new ArrayList<Object>();
					ArrayList<Object> columnDataProducto = new ArrayList<Object>();					
					ArrayList<Object> columnDataCantidad = new ArrayList<Object>();
					ArrayList<Object> columnDataTamano = new ArrayList<Object>();
					ArrayList<Object> columnDataTotal= new ArrayList<Object>();
					ArrayList<Object> columnDataExistencia= new ArrayList<Object>();
					ArrayList<Object> columnDataPerdida= new ArrayList<Object>();
					ArrayList<Object> columnDataMes= new ArrayList<Object>();
					ArrayList<Object> columnDataAno= new ArrayList<Object>();
					
					for (int i = 0; i < list.size(); i++) {
						columnDataTiempo.add(list.get(i).getAnejo());
						columnDataProducto.add(list.get(i).getMateria());					
						columnDataCantidad.add(list.get(i).getCanttoneles());
						columnDataTamano.add(list.get(i).getTamano());
						columnDataTotal.add(list.get(i).getTotal());
						columnDataExistencia.add(list.get(i).getExistencia());
						columnDataPerdida.add(dosDigitos.format(list.get(i).getPerdida()));
						columnDataMes.add(list.get(i).getMes());
						columnDataAno.add(list.get(i).getFecha());		
						}
					defaultTableModel.setRowCount(list.size());
					defaultTableModel.addColumn("Tiempo de Reposo",columnDataTiempo.toArray());
					defaultTableModel.addColumn("Producto",columnDataProducto.toArray());					
					defaultTableModel.addColumn("Cantidad Toneles",columnDataCantidad.toArray());
					defaultTableModel.addColumn("Tamaño Toneles",columnDataTamano.toArray());
					defaultTableModel.addColumn("Total Inicial",columnDataTotal.toArray());		
					defaultTableModel.addColumn("Existencia",columnDataExistencia.toArray());
					defaultTableModel.addColumn("% Perdida/Mes",columnDataPerdida.toArray());
					defaultTableModel.addColumn("Mes",columnDataMes.toArray());
					defaultTableModel.addColumn("Fecha",columnDataAno.toArray());
					getJTable().setModel(defaultTableModel);
					getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
					getJTable().setRowHeight(20);
					}else{
						JOptionPane.showMessageDialog(DetalleMermaVisual.this, "Debe Elegir el Mes,el Tipo de Producto y Escribir el Año", "Información", JOptionPane.ERROR_MESSAGE);	
						jPanel.setVisible(false);
					}
					
					if(MesjComboBox.getSelectedIndex()!=0 && AnotextField.getText().length()!=0 && ProductcomboBox.getSelectedIndex()==6){
						jPanel.setVisible(false);
						ReportejButton.setEnabled(false);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "####.#####",simbolo);
				       TableModel defaultTableModel = new TableModel();
						LinkedList<DetalleMerma> list = new LinkedList<DetalleMerma>();
						list =ServicioDetalleMerma.getMermasALL(((Mes)MesjComboBox.getSelectedItem()).getID(),Float.valueOf(AnotextField.getText()));
						ArrayList<Object> columnDataTiempo = new ArrayList<Object>();
						ArrayList<Object> columnDataProducto = new ArrayList<Object>();					
						ArrayList<Object> columnDataCantidad = new ArrayList<Object>();
						ArrayList<Object> columnDataTamano = new ArrayList<Object>();
						ArrayList<Object> columnDataTotal= new ArrayList<Object>();
						ArrayList<Object> columnDataExistencia= new ArrayList<Object>();
						ArrayList<Object> columnDataPerdida= new ArrayList<Object>();
						ArrayList<Object> columnDataMes= new ArrayList<Object>();
						ArrayList<Object> columnDataAno= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataTiempo.add(list.get(i).getAnejo());
							columnDataProducto.add(list.get(i).getMateria());					
							columnDataCantidad.add(list.get(i).getCanttoneles());
							columnDataTamano.add(list.get(i).getTamano());
							columnDataTotal.add(list.get(i).getTotal());
							columnDataExistencia.add(list.get(i).getExistencia());
							columnDataPerdida.add(dosDigitos.format(list.get(i).getPerdida()));
							columnDataMes.add(list.get(i).getMes());
							columnDataAno.add(list.get(i).getFecha());		
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Tiempo de Reposo",columnDataTiempo.toArray());
						defaultTableModel.addColumn("Producto",columnDataProducto.toArray());					
						defaultTableModel.addColumn("Cantidad Toneles",columnDataCantidad.toArray());
						defaultTableModel.addColumn("Tamaño Toneles",columnDataTamano.toArray());
						defaultTableModel.addColumn("Total Inicial",columnDataTotal.toArray());		
						defaultTableModel.addColumn("Existencia",columnDataExistencia.toArray());
						defaultTableModel.addColumn("% Perdida/Mes",columnDataPerdida.toArray());
						defaultTableModel.addColumn("Mes",columnDataMes.toArray());
						defaultTableModel.addColumn("Fecha",columnDataAno.toArray());
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
					}
				}
			});
			btnAceptar.setBounds(12, 198, 97, 25);
			panel.add(btnAceptar);
			panel.add(getLblAo());
			panel.add(getAnotextField());
			panel.add(getLblTipoMateriaPrima());
			panel.add(getProductcomboBox());
			panel.add(getReportejButton());
			panel.add(getSalirjButton());
		}
		return panel;
	}
	private JLabel getLblAo() {
		if (lblAo == null) {
			lblAo = new JLabel("A\u00F1o:");
			lblAo.setBounds(168, 17, 124, 27);
		}
		return lblAo;
	}
	private JTextField getAnotextField() {
		if (AnotextField == null) {
			AnotextField = new JTextField();
			AnotextField.setBounds(168, 44, 124, 27);
			AnotextField.setColumns(10);
		}
		return AnotextField;
	}
	private JLabel getLblTipoMateriaPrima() {
		if (lblTipoMateriaPrima == null) {
			lblTipoMateriaPrima = new JLabel("Tipo Materia Prima:");
			lblTipoMateriaPrima.setBounds(12, 118, 124, 27);
		}
		return lblTipoMateriaPrima;
	}
	private JComboBox getProductcomboBox() {
		if (ProductcomboBox == null) {
			ProductcomboBox = new JComboBox();
			ProductcomboBox.setBounds(new Rectangle(601, 42, 158, 27));
			ProductcomboBox.setBounds(12, 145, 158, 27);
		}
		return ProductcomboBox;
	}
}  //  @jve:decl-index=0:visual-constraint="31,-45"
