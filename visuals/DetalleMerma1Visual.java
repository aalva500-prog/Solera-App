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

import model.DetalleMerma1;
import model.MateriaPrima;
import model.Mes;
import model.TipoMateria;
import services.ServicioDetalleMerma1;
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

import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetalleMerma1Visual extends JFrame{

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
	private JButton RestaurarjButton = null;
	private JButton ReportejButton = null;
	private JLabel jLabel = null;
	private JPanel jPanel = null;
	private JPanel panel;
	private JTextField textField;
	private JLabel label;
	private JLabel label_1;
	private JComboBox comboBox;
	private JButton button;
	
	public DetalleMerma1Visual() {
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
		
      //Combobox Materia Prima
		LinkedList<MateriaPrima> materias = new LinkedList<MateriaPrima>();
		try {
			materias = ServicioMateria.getMateriasPrimas();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultComboBoxModel boxModelF = new DefaultComboBoxModel();
		boxModelF.addElement(new String("<Seleccione>"));
		for (int i = 0; i < materias.size(); i++) {
			boxModelF.addElement(materias.get(i));
		}
		getTipoMateriajComboBox().setModel(boxModelF);
		
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
				LinkedList<DetalleMerma1> toneles = ServicioDetalleMerma1.getMermasAño(((Mes)getMesjComboBox().getSelectedItem()).getID(), Float.valueOf(textField.getText()), ((TipoMateria)getComboBox().getSelectedItem()).getId_tipomateria());
				DetalleMerma1 t = toneles.get(pos);
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
				getComboBox().setModel(boxModelF1);							
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
			jLabel.setBounds(new Rectangle(124, 138, 13, 19));
			PerdidajLabel = new JLabel();
			PerdidajLabel.setText("% Pérdida/Mes:");
			PerdidajLabel.setBounds(new Rectangle(617, 119, 124, 27));
			FechajLabel = new JLabel();
			FechajLabel.setText("Fecha Actualización:");
			FechajLabel.setBounds(new Rectangle(302, 18, 124, 27));
			ExistenciajLabel = new JLabel();
			ExistenciajLabel.setText("Existencia:");
			ExistenciajLabel.setBounds(new Rectangle(426, 119, 124, 27));
			TotaljLabel = new JLabel();			
			TotaljLabel.setText("Total Inicial:");
			TotaljLabel.setBounds(new Rectangle(291, 119, 124, 27));
			TamanojLabel = new JLabel();
			TamanojLabel.setText("Tamaño de Toneles:");
			TamanojLabel.setBounds(new Rectangle(148, 119, 124, 27));
			CantidadTonelesjLabel = new JLabel();
			CantidadTonelesjLabel.setText("Cantidad Toneles:");
			CantidadTonelesjLabel.setBounds(new Rectangle(10, 119, 112, 27));
			ProductojLabel = new JLabel();
			ProductojLabel.setText("Producto:");
			ProductojLabel.setBounds(new Rectangle(10, 18, 260, 27));
			jContentPane = new JPanel();
			GridBagLayout gbl_jContentPane = new GridBagLayout();
			gbl_jContentPane.columnWidths = new int[]{470, 789, 0};
			gbl_jContentPane.rowHeights = new int[]{242, 467, 0};
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
			TipoMateriajComboBox.setBounds(new Rectangle(10, 45, 260, 27));
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
			MesjComboBox.setBounds(12, 56, 124, 27);
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
			ReposojTextField.setBounds(194, 139, 124, 27);
			ReposojTextField.setEnabled(false);
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
			CantidadjTextField.setBounds(new Rectangle(10, 146, 102, 27));
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
			TamanojTextField.setBounds(new Rectangle(148, 146, 124, 27));
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
			TotaljTextField.setBounds(new Rectangle(291, 146, 124, 27));
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
			ExistenciajTextField.setBounds(new Rectangle(426, 146, 124, 27));
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
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			DatejSpinner.setEditor(new JSpinner.DateEditor(DatejSpinner,dateFormat.toPattern()));
			DatejSpinner.setBounds(new Rectangle(302, 45, 124, 27));
			DatejSpinner.setEnabled(false);
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
			InsertarjButton.setBounds(new Rectangle(10, 202, 124, 27));
			InsertarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_SysInfo_Unit1_ilInfoStates1_16x16.png")));
			InsertarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(ReposojTextField.getText().length()>0 && CantidadjTextField.getText().length()!= 0 && TamanojTextField.getText().length()!=0 && TotaljTextField.getText().length()!=0 && ExistenciajTextField.getText().length()!=0 && PerdidajTextField.getText().length()!=0 && (TipoMateriajComboBox.getSelectedIndex()!=0) && (MesjComboBox.getSelectedIndex()!=0)){
						try {
							java.sql.Date sqlDate = new java.sql.Date(getDatespinnerDateModel().getDate().getTime());
						ServicioDetalleMerma1.insertarDetalleMerma(Integer.valueOf(ReposojTextField.getText()), ((MateriaPrima)getTipoMateriajComboBox().getSelectedItem()).getId_materia(), Integer.valueOf(CantidadjTextField.getText()), Integer.valueOf(TamanojTextField.getText()), Integer.valueOf(TotaljTextField.getText()), Integer.valueOf(ExistenciajTextField.getText()), Float.valueOf(PerdidajTextField.getText()), ((Mes)getMesjComboBox().getSelectedItem()).getID(), sqlDate);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
				        TableModel defaultTableModel = new TableModel();
						LinkedList<DetalleMerma1> list = new LinkedList<DetalleMerma1>();
						list =ServicioDetalleMerma1.getMermasAño(((Mes)getMesjComboBox().getSelectedItem()).getID(), Float.valueOf(textField.getText()), ((TipoMateria)getComboBox().getSelectedItem()).getId_tipomateria());
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
						JOptionPane.showMessageDialog(DetalleMerma1Visual.this, "Producto Insertado", "Informacion", JOptionPane.INFORMATION_MESSAGE);	
						UserInterfaceSuport.clearComponents(jPanel);
							
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(DetalleMerma1Visual.this, "No se puede insertar dos Materias iguales", "Error", JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(DetalleMerma1Visual.this, "Campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);	
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
			CalcularjButton.setBounds(new Rectangle(564, 119, 41, 53));
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
						JOptionPane.showMessageDialog(DetalleMerma1Visual.this, "Existen Bloques en Blanco", "Error", JOptionPane.ERROR_MESSAGE);
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
			PerdidajTextField.setBounds(new Rectangle(617, 146, 124, 27));
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
			ModificarjButton.setBounds(new Rectangle(157, 202, 124, 27));
			ModificarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Refresh_24x24.png")));
			ModificarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(DetalleMerma1Visual.this, "Esta seguro que desea modificar el Producto?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(getTipoMateriajComboBox().getSelectedIndex()!=0 && getMesjComboBox().getSelectedIndex()!=0){
					if(result == JOptionPane.YES_OPTION){
					int pos = jTable.getSelectedRow();
					try {
						LinkedList<DetalleMerma1> mediciones = ServicioDetalleMerma1.getMermasAño(((Mes)getMesjComboBox().getSelectedItem()).getID(), Float.valueOf(textField.getText()), ((TipoMateria)getComboBox().getSelectedItem()).getId_tipomateria());
						DetalleMerma1 u = mediciones.get(pos);
						java.sql.Date sqlDate = new java.sql.Date(getDatespinnerDateModel().getDate().getTime());
						ServicioDetalleMerma1.ActualizarMedicion(u.getId_detallemerma(), Integer.valueOf(ReposojTextField.getText()),((MateriaPrima)getTipoMateriajComboBox().getSelectedItem()).getId_materia(), Integer.valueOf(CantidadjTextField.getText()), Integer.valueOf(TamanojTextField.getText()), Integer.valueOf(TotaljTextField.getText()), Float.valueOf(PerdidajTextField.getText()), Integer.valueOf(ExistenciajTextField.getText()), sqlDate,((Mes)getMesjComboBox().getSelectedItem()).getID());
						JOptionPane.showMessageDialog(DetalleMerma1Visual.this, "Producto Modificado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "####.#####",simbolo); 
						TableModel defaultTableModel = new TableModel();
						LinkedList<DetalleMerma1> list = new LinkedList<DetalleMerma1>();
						list =ServicioDetalleMerma1.getMermasAño(((Mes)getMesjComboBox().getSelectedItem()).getID(), Float.valueOf(textField.getText()), ((TipoMateria)getComboBox().getSelectedItem()).getId_tipomateria());
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
						UserInterfaceSuport.clearComponents(getJPanel());
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
					}
					}else{
						JOptionPane.showMessageDialog(DetalleMerma1Visual.this, "Debe elegir el Producto y el Mes ", "Informacion", JOptionPane.ERROR_MESSAGE);
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
			EliminarjButton.setBounds(new Rectangle(302, 202, 124, 27));
			EliminarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/edit-cut.png")));
			EliminarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(DetalleMerma1Visual.this, "Esta seguro que desea eliminar el Producto?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = jTable.getSelectedRow();
					try {
						LinkedList<DetalleMerma1> mediciones = ServicioDetalleMerma1.getMermasAño(((Mes)getMesjComboBox().getSelectedItem()).getID(), Float.valueOf(textField.getText()), ((TipoMateria)getComboBox().getSelectedItem()).getId_tipomateria());
						DetalleMerma1 u = mediciones.get(pos);
						ServicioDetalleMerma1.EliminarDetalleMerma(u.getId_detallemerma());
						JOptionPane.showMessageDialog(DetalleMerma1Visual.this, "Producto Eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "####.#####",simbolo); 
						TableModel defaultTableModel = new TableModel();
						LinkedList<DetalleMerma1> list = new LinkedList<DetalleMerma1>();
						list =ServicioDetalleMerma1.getMermasAño(((Mes)getMesjComboBox().getSelectedItem()).getID(), Float.valueOf(textField.getText()), ((TipoMateria)getComboBox().getSelectedItem()).getId_tipomateria());
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
						UserInterfaceSuport.clearComponents(getJPanel());
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(DetalleMerma1Visual.this, "No se puede eliminar el Producto porque Existen relaciones con el mismo", "Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					UserInterfaceSuport.clearComponents(getJContentPane());
					ModificarjButton.setEnabled(false);
					EliminarjButton.setEnabled(false);
					InsertarjButton.setEnabled(true);
											
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
			SalirjButton.setBounds(324, 202, 124, 27);
			SalirjButton.setText("Cerrar");
			SalirjButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Delete_16x16.png")));
			SalirjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Start i = new Start();
					new Principal(i).setVisible(true);
					dispose();
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return SalirjButton;
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
			RestaurarjButton.setBounds(new Rectangle(447, 202, 124, 27));
			RestaurarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/edit-clear.png")));
			RestaurarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					UserInterfaceSuport.clearComponents(getJPanel());
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
			ReportejButton.setBounds(121, 202, 187, 27);
			ReportejButton.setText("Imprimir Estado Solera");
			ReportejButton.setEnabled(false);
			ReportejButton.setIcon(new ImageIcon(getClass().getResource("/Img/printer.png")));
			ReportejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Reportes.getR().EstadoSolera1(((TipoMateria)comboBox.getSelectedItem()).getId_tipomateria(), ((Mes)getMesjComboBox().getSelectedItem()).getID(), Float.valueOf(textField.getText()), Integer.valueOf(ReposojTextField.getText()));
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
			jPanel.setVisible(false);
			jPanel.setBorder(new TitledBorder(null, "Datos Generales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			jPanel.setLayout(null);
			jPanel.add(getPerdidajTextField(), null);
			jPanel.add(PerdidajLabel, null);
			jPanel.add(getCalcularjButton(), null);
			jPanel.add(getRestaurarjButton(), null);
			jPanel.add(getEliminarjButton(), null);
			jPanel.add(getModificarjButton(), null);
			jPanel.add(getInsertarjButton(), null);
			jPanel.add(getCantidadjTextField(), null);
			jPanel.add(CantidadTonelesjLabel, null);
			jPanel.add(jLabel, null);
			jPanel.add(getTamanojTextField(), null);
			jPanel.add(TamanojLabel, null);
			jPanel.add(getTotaljTextField(), null);
			jPanel.add(TotaljLabel, null);
			jPanel.add(getExistenciajTextField(), null);
			jPanel.add(ExistenciajLabel, null);
			jPanel.add(getDatejSpinner(), null);
			jPanel.add(FechajLabel, null);
			jPanel.add(getTipoMateriajComboBox(), null);
			jPanel.add(ProductojLabel, null);
		}
		return jPanel;
	}
	
	
	
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Panel Introductorio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setLayout(null);
			MesjLabel = new JLabel();
			MesjLabel.setBounds(12, 29, 124, 27);
			panel.add(MesjLabel);
			MesjLabel.setText("Mes:");
			panel.add(getMesjComboBox());
			panel.add(getTextField());
			panel.add(getLabel());
			TiempojLabel = new JLabel();
			TiempojLabel.setBounds(194, 112, 124, 27);
			panel.add(TiempojLabel);
			TiempojLabel.setText("Reposo (Meses):");
			panel.add(getReposojTextField());
			panel.add(getLabel_1());
			panel.add(getComboBox());
			panel.add(getSalirjButton());
			panel.add(getReportejButton());
			panel.add(getButton());
		}
		return panel;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(194, 56, 124, 27);
		}
		return textField;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("A\u00F1o:");
			label.setBounds(194, 29, 124, 27);
		}
		return label;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("Tipo Materia Prima:");
			label_1.setBounds(12, 112, 158, 27);
		}
		return label_1;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setBounds(new Rectangle(601, 42, 158, 27));
			comboBox.setBounds(12, 139, 158, 27);
		}
		return comboBox;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("Aceptar");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(MesjComboBox.getSelectedIndex()!=0 && textField.getText().length()!=0 && comboBox.getSelectedIndex()!=0){
					jPanel.setVisible(true);
					ReposojTextField.setEnabled(true);
					ReportejButton.setEnabled(true);
					DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
				    simbolo.setDecimalSeparator('.');
					DecimalFormat dosDigitos = new DecimalFormat( "####.#####",simbolo);
			       TableModel defaultTableModel = new TableModel();
					LinkedList<DetalleMerma1> list = new LinkedList<DetalleMerma1>();
					list =ServicioDetalleMerma1.getMermasAño(((Mes)getMesjComboBox().getSelectedItem()).getID(), Float.valueOf(textField.getText()), ((TipoMateria)getComboBox().getSelectedItem()).getId_tipomateria());
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
					JOptionPane.showMessageDialog(DetalleMerma1Visual.this, "Debe Llenar los Datos del Panel Introductorio", "Información", JOptionPane.ERROR_MESSAGE);
				}
				}
			});
			button.setIcon(new ImageIcon(DetalleMerma1Visual.class.getResource("/img/OK.png")));
			button.setBounds(12, 202, 97, 27);
		}
		return button;
	}
}  //  @jve:decl-index=0:visual-constraint="31,-45"
