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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import model.MateriaPrima;
import model.TipoMateria;
import services.ServicioMateria;
import Utils.FormatoTabla;
import Utils.TableModel;
import Utils.UserInterfaceSuport;
import Utils.Validate;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MateriaVisual extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JLabel FechajLabel = null;
	private JSpinner DatejSpinner = null;
	private SpinnerDateModel DatespinnerDateModel = null;
	private JLabel GradojLabel = null;
	private JTextField GradojTextField = null;
	private JLabel ObservacionesjLabel = null;
	private JTextArea ObservacionesjTextArea = null;
	private JLabel TipoMateriajLabel = null;
	private JComboBox TipoMateriajComboBox= null;
	private JLabel descripcionjLabel = null;
	private JTextField NombrejTextField = null;
	private JButton InsertarjButton = null;
	private JButton DetallejButton = null;
	private MateriaPrima mp=null;
	private JButton EliminarjButton = null;
	private JButton ModificarjButton = null;
	private JButton CerrarjButton = null;
	private JButton RestaurarjButton = null;
	private JScrollPane jScrollPane1 = null;
	private JPanel jPanel = null;
	/**
	 * This method initializes jFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	private void initialize() {		
			this.setSize(new Dimension(1131, 650));
			this.setTitle("Gestionar Productos");
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/toneles.png")));
			this.setContentPane(getJContentPane());
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			ModificarjButton.setEnabled(false);
			EliminarjButton.setEnabled(false);
			DetallejButton.setEnabled(false);
			InsertarjButton.setEnabled(false);
			TableModel defaultTableModel = new TableModel();
			ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
			ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
			ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
			ArrayList<Object> columnDataProducto = new ArrayList<Object>();
			ArrayList<Object> columnDataNombre= new ArrayList<Object>();			
			defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
			defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
			defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
			defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
			defaultTableModel.addColumn("Producto",columnDataNombre.toArray());			
			getJTable().setModel(defaultTableModel);
			getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
			getJTable().setRowHeight(20);
			//Combobox Materia Prima
			LinkedList<TipoMateria> materias = new LinkedList<TipoMateria>();
			try {
				materias = ServicioMateria.getMaterias();
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
			
			jTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
				    simbolo.setDecimalSeparator('.');
					DecimalFormat dosDigitos = new DecimalFormat( "####.#####",simbolo);
					int pos = jTable.getSelectedRow();
					if(TipoMateriajComboBox.getSelectedIndex()==6){
					try {
						LinkedList<MateriaPrima> toneles = ServicioMateria.getMateriasPrimas();
						MateriaPrima t = toneles.get(pos);					
						mp=t;				
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ModificarjButton.setEnabled(false);
					EliminarjButton.setEnabled(false);
					InsertarjButton.setEnabled(false);
					DetallejButton.setEnabled(true);
					}
					if(TipoMateriajComboBox.getSelectedIndex()==1){
						try {
							LinkedList<MateriaPrima> toneles = ServicioMateria.getMateriasPrimasAlcoholAñejado();
							MateriaPrima t = toneles.get(pos);					
							TipoMateriajComboBox.setSelectedItem(t.getTipomateria());
							ObservacionesjTextArea.setText(t.getObservaciones());
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							DatejSpinner.setEditor(new JSpinner.DateEditor(DatejSpinner,dateFormat.toPattern()));
							DatejSpinner.setValue(t.getFechallenado());	
							mp=t;
							GradojTextField.setText(String.valueOf(dosDigitos.format(t.getGrado())));
							NombrejTextField.setText(t.getDescripcion());							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ModificarjButton.setEnabled(true);
						EliminarjButton.setEnabled(true);
						InsertarjButton.setEnabled(false);
						DetallejButton.setEnabled(true);
						}
					if(TipoMateriajComboBox.getSelectedIndex()==2){
						try {
							LinkedList<MateriaPrima> toneles = ServicioMateria.getMateriasPrimasExtractoRoble();
							MateriaPrima t = toneles.get(pos);					
							TipoMateriajComboBox.setSelectedItem(t.getTipomateria());
							ObservacionesjTextArea.setText(t.getObservaciones());
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							DatejSpinner.setEditor(new JSpinner.DateEditor(DatejSpinner,dateFormat.toPattern()));
							DatejSpinner.setValue(t.getFechallenado());	
							mp=t;
							GradojTextField.setText(String.valueOf(dosDigitos.format(t.getGrado())));
							NombrejTextField.setText(t.getDescripcion());
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ModificarjButton.setEnabled(true);
						EliminarjButton.setEnabled(true);
						InsertarjButton.setEnabled(false);
						DetallejButton.setEnabled(true);
						}
					
					if(TipoMateriajComboBox.getSelectedIndex()==3){
						try {
							LinkedList<MateriaPrima> toneles = ServicioMateria.getMateriasPrimasMalta();
							MateriaPrima t = toneles.get(pos);					
							TipoMateriajComboBox.setSelectedItem(t.getTipomateria());
							ObservacionesjTextArea.setText(t.getObservaciones());
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							DatejSpinner.setEditor(new JSpinner.DateEditor(DatejSpinner,dateFormat.toPattern()));
							DatejSpinner.setValue(t.getFechallenado());	
							mp=t;
							GradojTextField.setText(String.valueOf(dosDigitos.format(t.getGrado())));
							NombrejTextField.setText(t.getDescripcion());
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ModificarjButton.setEnabled(true);
						EliminarjButton.setEnabled(true);
						InsertarjButton.setEnabled(false);
						DetallejButton.setEnabled(true);
						}
					
					if(TipoMateriajComboBox.getSelectedIndex()==4){
						try {
							LinkedList<MateriaPrima> toneles = ServicioMateria.getMateriasRCS();
							MateriaPrima t = toneles.get(pos);					
							TipoMateriajComboBox.setSelectedItem(t.getTipomateria());
							ObservacionesjTextArea.setText(t.getObservaciones());
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							DatejSpinner.setEditor(new JSpinner.DateEditor(DatejSpinner,dateFormat.toPattern()));
							DatejSpinner.setValue(t.getFechallenado());	
							mp=t;
							GradojTextField.setText(String.valueOf(dosDigitos.format(t.getGrado())));
							NombrejTextField.setText(t.getDescripcion());
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ModificarjButton.setEnabled(true);
						EliminarjButton.setEnabled(true);
						InsertarjButton.setEnabled(false);
						DetallejButton.setEnabled(true);
						}
					if(TipoMateriajComboBox.getSelectedIndex()==5){
						try {
							LinkedList<MateriaPrima> toneles = ServicioMateria.getMateriasRonMadre();
							MateriaPrima t = toneles.get(pos);					
							TipoMateriajComboBox.setSelectedItem(t.getTipomateria());
							ObservacionesjTextArea.setText(t.getObservaciones());
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							DatejSpinner.setEditor(new JSpinner.DateEditor(DatejSpinner,dateFormat.toPattern()));
							DatejSpinner.setValue(t.getFechallenado());	
							mp=t;
							GradojTextField.setText(String.valueOf(dosDigitos.format(t.getGrado())));
							NombrejTextField.setText(t.getDescripcion());
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ModificarjButton.setEnabled(true);
						EliminarjButton.setEnabled(true);
						InsertarjButton.setEnabled(false);
						DetallejButton.setEnabled(true);
						}
					
					
				}
			});	
			
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			setLocation((screenSize.width - getWidth()) / 2,((screenSize.height - getHeight()) / 2));
	
			
	}

	
	public MateriaVisual() {
		super();
		initialize();
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new Insets(7, 16, 0, 70);
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.ipadx = 1038;
			gridBagConstraints1.ipady = 173;
			gridBagConstraints1.gridx = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.ipadx = 637;
			gridBagConstraints.ipady = 7;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.insets = new Insets(1, 14, 9, 21);
			descripcionjLabel = new JLabel();
			descripcionjLabel.setText("Tipo Materia Prima:");
			descripcionjLabel.setBounds(new Rectangle(601, 15, 158, 27));
			TipoMateriajLabel = new JLabel();
			TipoMateriajLabel.setText("Producto:");
			TipoMateriajLabel.setBounds(new Rectangle(785, 15, 239, 27));
			ObservacionesjLabel = new JLabel();
			ObservacionesjLabel.setText("Observaciones:");
			ObservacionesjLabel.setBounds(new Rectangle(309, 15, 269, 27));
			GradojLabel = new JLabel();
			GradojLabel.setText("Grado:");
			GradojLabel.setBounds(new Rectangle(165, 15, 125, 27));
			FechajLabel = new JLabel();
			FechajLabel.setText("Fecha Llenado:");
			FechajLabel.setBounds(new Rectangle(22, 15, 125, 27));
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getJScrollPane(), gridBagConstraints);
			jContentPane.add(getJPanel(), gridBagConstraints1);
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
	 * This method initializes DatejSpinner	
	 * 	
	 * @return javax.swing.JSpinner	
	 */
	private JSpinner getDatejSpinner() {
		if (DatejSpinner == null) {
			DatejSpinner = new JSpinner(getDatespinnerDateModel() );
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			DatejSpinner.setEditor(new JSpinner.DateEditor(DatejSpinner,dateFormat.toPattern()));
			DatejSpinner.setBounds(new Rectangle(22, 41, 125, 27));
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
			GradojTextField.setBounds(new Rectangle(165, 41, 125, 27));
			Validate.validateDigitAndComma(GradojTextField);
		}
		return GradojTextField;
	}

	/**
	 * This method initializes ObservacionesjTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getObservacionesjTextArea() {
		if (ObservacionesjTextArea == null) {
			ObservacionesjTextArea = new JTextArea();
		}
		return ObservacionesjTextArea;
	}


	/**
	 * This method initializes TipoMateriajComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getTipoMateriajComboBox() {
		if (TipoMateriajComboBox  == null) {
			TipoMateriajComboBox = new JComboBox();
			TipoMateriajComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(TipoMateriajComboBox.getSelectedIndex()==1){
						InsertarjButton.setEnabled(true);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
						TableModel defaultTableModel = new TableModel();
						LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
						try {
							list = ServicioMateria.getMateriasPrimasAlcoholAñejado();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
						ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
						ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
						ArrayList<Object> columnDataProducto = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre= new ArrayList<Object>();						
						for (int i = 0; i < list.size(); i++) {
							columnDataFechaLlenado.add(list.get(i).getFechallenado());
							columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));					
							columnDataObservaciones.add(list.get(i).getObservaciones());
							columnDataProducto.add(list.get(i).getTipomateria());
							columnDataNombre.add(list.get(i).getDescripcion());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
						defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
						defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
						defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
						defaultTableModel.addColumn("Producto",columnDataNombre.toArray());					
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
					}
					if(TipoMateriajComboBox.getSelectedIndex()==2){
						InsertarjButton.setEnabled(true);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
						TableModel defaultTableModel = new TableModel();
						LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
						try {
							list = ServicioMateria.getMateriasPrimasExtractoRoble();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
						ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
						ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
						ArrayList<Object> columnDataProducto = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataFechaLlenado.add(list.get(i).getFechallenado());
							columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));					
							columnDataObservaciones.add(list.get(i).getObservaciones());
							columnDataProducto.add(list.get(i).getTipomateria());
							columnDataNombre.add(list.get(i).getDescripcion());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
						defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
						defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
						defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
						defaultTableModel.addColumn("Producto",columnDataNombre.toArray());					
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
					}
					if(TipoMateriajComboBox.getSelectedIndex()==3){
						InsertarjButton.setEnabled(true);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
						TableModel defaultTableModel = new TableModel();
						LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
						try {
							list = ServicioMateria.getMateriasPrimasMalta();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
						ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
						ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
						ArrayList<Object> columnDataProducto = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataFechaLlenado.add(list.get(i).getFechallenado());
							columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));					
							columnDataObservaciones.add(list.get(i).getObservaciones());
							columnDataProducto.add(list.get(i).getTipomateria());
							columnDataNombre.add(list.get(i).getDescripcion());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
						defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
						defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
						defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
						defaultTableModel.addColumn("Producto",columnDataNombre.toArray());				
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
					}
					if(TipoMateriajComboBox.getSelectedIndex()==4){
						InsertarjButton.setEnabled(true);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
						TableModel defaultTableModel = new TableModel();
						LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
						try {
							list = ServicioMateria.getMateriasRCS();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
						ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
						ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
						ArrayList<Object> columnDataProducto = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataFechaLlenado.add(list.get(i).getFechallenado());
							columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));					
							columnDataObservaciones.add(list.get(i).getObservaciones());
							columnDataProducto.add(list.get(i).getTipomateria());
							columnDataNombre.add(list.get(i).getDescripcion());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
						defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
						defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
						defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
						defaultTableModel.addColumn("Producto",columnDataNombre.toArray());			
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
					}
					if(TipoMateriajComboBox.getSelectedIndex()==5){
						InsertarjButton.setEnabled(true);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
						TableModel defaultTableModel = new TableModel();
						LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
						try {
							list = ServicioMateria.getMateriasRonMadre();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
						ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
						ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
						ArrayList<Object> columnDataProducto = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataFechaLlenado.add(list.get(i).getFechallenado());
							columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));					
							columnDataObservaciones.add(list.get(i).getObservaciones());
							columnDataProducto.add(list.get(i).getTipomateria());
							columnDataNombre.add(list.get(i).getDescripcion());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
						defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
						defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
						defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
						defaultTableModel.addColumn("Producto",columnDataNombre.toArray());				
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
					}
					
					if(TipoMateriajComboBox.getSelectedIndex()==6){
						InsertarjButton.setEnabled(false);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
						TableModel defaultTableModel = new TableModel();
						LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
						try {
							list = ServicioMateria.getMateriasPrimas();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
						ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
						ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
						ArrayList<Object> columnDataProducto = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataFechaLlenado.add(list.get(i).getFechallenado());
							columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));					
							columnDataObservaciones.add(list.get(i).getObservaciones());
							columnDataProducto.add(list.get(i).getTipomateria());
							columnDataNombre.add(list.get(i).getDescripcion());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
						defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
						defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
						defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
						defaultTableModel.addColumn("Producto",columnDataNombre.toArray());				
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
					}
					
					if(TipoMateriajComboBox.getSelectedIndex()==0){
						InsertarjButton.setEnabled(false);
						TableModel defaultTableModel = new TableModel();
						ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
						ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
						ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
						ArrayList<Object> columnDataProducto = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre= new ArrayList<Object>();
						defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
						defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
						defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
						defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
						defaultTableModel.addColumn("Producto",columnDataNombre.toArray());			
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
					}
					
				}
			});
			TipoMateriajComboBox.setBounds(new Rectangle(601, 42, 158, 27));
			}
		return TipoMateriajComboBox;
	}

	/**
	 * This method initializes NombrejTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNombrejTextField() {
		if (NombrejTextField == null) {
			NombrejTextField = new JTextField();
			NombrejTextField.setBounds(new Rectangle(785, 42, 239, 27));
		}
		return NombrejTextField;
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
			InsertarjButton.setBounds(new Rectangle(24, 138, 133, 29));
			InsertarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_SysInfo_Unit1_ilInfoStates1_16x16.png")));
			InsertarjButton.setEnabled(false);
			InsertarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if((TipoMateriajComboBox.getSelectedIndex()!=0) &&  NombrejTextField.getText().length()!=0){
						try {
						java.sql.Date sqlDate = new java.sql.Date(getDatespinnerDateModel().getDate().getTime());
						ServicioMateria.insertarMateria(sqlDate, Float.valueOf(GradojTextField.getText()), ObservacionesjTextArea.getText(), ((TipoMateria)getTipoMateriajComboBox().getSelectedItem()).getId_tipomateria(), NombrejTextField.getText());
						JOptionPane.showMessageDialog(MateriaVisual.this, "Producto Insertado", "Informacion", JOptionPane.INFORMATION_MESSAGE);	
						ObservacionesjTextArea.setText("");
						GradojTextField.setText("");
						NombrejTextField.setText("");
						if(TipoMateriajComboBox.getSelectedIndex()==1){
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
						TableModel defaultTableModel = new TableModel();
						LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
						try {
							list = ServicioMateria.getMateriasPrimasAlcoholAñejado();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
						ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
						ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
						ArrayList<Object> columnDataProducto = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataFechaLlenado.add(list.get(i).getFechallenado());
							columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));						
							columnDataObservaciones.add(list.get(i).getObservaciones());
							columnDataProducto.add(list.get(i).getTipomateria());
							columnDataNombre.add(list.get(i).getDescripcion());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
						defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
						defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
						defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
						defaultTableModel.addColumn("Producto",columnDataNombre.toArray());				
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);										
						}
						if(TipoMateriajComboBox.getSelectedIndex()==2){
							DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
						    simbolo.setDecimalSeparator(',');
						    simbolo.setGroupingSeparator('.');
							DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
							TableModel defaultTableModel = new TableModel();
							LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
							try {
								list = ServicioMateria.getMateriasPrimasExtractoRoble();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
							ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
							ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
							ArrayList<Object> columnDataProducto = new ArrayList<Object>();
							ArrayList<Object> columnDataNombre= new ArrayList<Object>();
							
							for (int i = 0; i < list.size(); i++) {
								columnDataFechaLlenado.add(list.get(i).getFechallenado());
								columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));						
								columnDataObservaciones.add(list.get(i).getObservaciones());
								columnDataProducto.add(list.get(i).getTipomateria());
								columnDataNombre.add(list.get(i).getDescripcion());
								}
							defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
							defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
							defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
							defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
							defaultTableModel.addColumn("Producto",columnDataNombre.toArray());				
							getJTable().setModel(defaultTableModel);
							getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
							getJTable().setRowHeight(20);
							}
						if(TipoMateriajComboBox.getSelectedIndex()==3){
							DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
						    simbolo.setDecimalSeparator(',');
						    simbolo.setGroupingSeparator('.');
							DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
							TableModel defaultTableModel = new TableModel();
							LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
							try {
								list = ServicioMateria.getMateriasPrimasMalta();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
							ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
							ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
							ArrayList<Object> columnDataProducto = new ArrayList<Object>();
							ArrayList<Object> columnDataNombre= new ArrayList<Object>();
							
							for (int i = 0; i < list.size(); i++) {
								columnDataFechaLlenado.add(list.get(i).getFechallenado());
								columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));						
								columnDataObservaciones.add(list.get(i).getObservaciones());
								columnDataProducto.add(list.get(i).getTipomateria());
								columnDataNombre.add(list.get(i).getDescripcion());
								}
							defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
							defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
							defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
							defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
							defaultTableModel.addColumn("Producto",columnDataNombre.toArray());					
							getJTable().setModel(defaultTableModel);
							getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
							getJTable().setRowHeight(20);
							}
						if(TipoMateriajComboBox.getSelectedIndex()==4){
							DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
						    simbolo.setDecimalSeparator(',');
						    simbolo.setGroupingSeparator('.');
							DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
							TableModel defaultTableModel = new TableModel();
							LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
							try {
								list = ServicioMateria.getMateriasRCS();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
							ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
							ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
							ArrayList<Object> columnDataProducto = new ArrayList<Object>();
							ArrayList<Object> columnDataNombre= new ArrayList<Object>();
							
							for (int i = 0; i < list.size(); i++) {
								columnDataFechaLlenado.add(list.get(i).getFechallenado());
								columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));						
								columnDataObservaciones.add(list.get(i).getObservaciones());
								columnDataProducto.add(list.get(i).getTipomateria());
								columnDataNombre.add(list.get(i).getDescripcion());
								}
							defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
							defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
							defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
							defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
							defaultTableModel.addColumn("Producto",columnDataNombre.toArray());					
							getJTable().setModel(defaultTableModel);
							getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
							getJTable().setRowHeight(20);
						}
						if(TipoMateriajComboBox.getSelectedIndex()==5){
							DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
						    simbolo.setDecimalSeparator(',');
						    simbolo.setGroupingSeparator('.');
							DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
							TableModel defaultTableModel = new TableModel();
							LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
							try {
								list = ServicioMateria.getMateriasRonMadre();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
							ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
							ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
							ArrayList<Object> columnDataProducto = new ArrayList<Object>();
							ArrayList<Object> columnDataNombre= new ArrayList<Object>();
							
							for (int i = 0; i < list.size(); i++) {
								columnDataFechaLlenado.add(list.get(i).getFechallenado());
								columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));						
								columnDataObservaciones.add(list.get(i).getObservaciones());
								columnDataProducto.add(list.get(i).getTipomateria());
								columnDataNombre.add(list.get(i).getDescripcion());
								}
							defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
							defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
							defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
							defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
							defaultTableModel.addColumn("Producto",columnDataNombre.toArray());				
							getJTable().setModel(defaultTableModel);
							getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
							getJTable().setRowHeight(20);
							}
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(MateriaVisual.this, "No se puede insertar dos Materias iguales", "Error", JOptionPane.ERROR_MESSAGE);
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						ModificarjButton.setEnabled(false);
						EliminarjButton.setEnabled(false);
						InsertarjButton.setEnabled(true);
						DetallejButton.setEnabled(false);
								
					}
					
					else{
						JOptionPane.showMessageDialog(MateriaVisual.this, "Campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);	
						System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
					
				}
			});
		}
		return InsertarjButton;
	}


	/**
	 * This method initializes DetallejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDetallejButton() {
		if (DetallejButton == null) {
			DetallejButton = new JButton();
			DetallejButton.setText("Ver Ubicaciones de Toneles");
			DetallejButton.setBounds(new Rectangle(170, 138, 229, 29));
			DetallejButton.setIcon(new ImageIcon(getClass().getResource("/Img/system-search.png")));
			DetallejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					new DetalleMateriaVisual(mp).setVisible(true);
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return DetallejButton;
	}


	/**
	 * This method initializes EliminarjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEliminarjButton() {
		if (EliminarjButton == null) {
			EliminarjButton = new JButton();
			EliminarjButton.setText("Modificar");
			EliminarjButton.setBounds(new Rectangle(411, 138, 133, 29));
			EliminarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Refresh_24x24.png")));
			EliminarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(MateriaVisual.this, "Esta seguro que desea modificar el Producto?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					
					try {
						java.sql.Date fecha = new java.sql.Date(getDatespinnerDateModel().getDate().getTime());
						ServicioMateria.ActualizarMedicion(fecha, Float.valueOf(GradojTextField.getText()), ObservacionesjTextArea.getText(), ((TipoMateria)getTipoMateriajComboBox().getSelectedItem()).getId_tipomateria(), NombrejTextField.getText(), mp.getId_materia());
						JOptionPane.showMessageDialog(MateriaVisual.this, "Producto Modificado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						ObservacionesjTextArea.setText("");
						GradojTextField.setText("");
						NombrejTextField.setText("");
						if(TipoMateriajComboBox.getSelectedIndex()==1){
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
						TableModel defaultTableModel = new TableModel();
						LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
						try {
							list = ServicioMateria.getMateriasPrimasAlcoholAñejado();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
						ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
						ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
						ArrayList<Object> columnDataProducto = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataFechaLlenado.add(list.get(i).getFechallenado());
							columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));						
							columnDataObservaciones.add(list.get(i).getObservaciones());
							columnDataProducto.add(list.get(i).getTipomateria());
							columnDataNombre.add(list.get(i).getDescripcion());
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
						defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
						defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
						defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
						defaultTableModel.addColumn("Producto",columnDataNombre.toArray());				
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);										
						}
						if(TipoMateriajComboBox.getSelectedIndex()==2){
							DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
						    simbolo.setDecimalSeparator(',');
						    simbolo.setGroupingSeparator('.');
							DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
							TableModel defaultTableModel = new TableModel();
							LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
							try {
								list = ServicioMateria.getMateriasPrimasExtractoRoble();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
							ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
							ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
							ArrayList<Object> columnDataProducto = new ArrayList<Object>();
							ArrayList<Object> columnDataNombre= new ArrayList<Object>();
							
							for (int i = 0; i < list.size(); i++) {
								columnDataFechaLlenado.add(list.get(i).getFechallenado());
								columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));						
								columnDataObservaciones.add(list.get(i).getObservaciones());
								columnDataProducto.add(list.get(i).getTipomateria());
								columnDataNombre.add(list.get(i).getDescripcion());
								}
							defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
							defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
							defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
							defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
							defaultTableModel.addColumn("Producto",columnDataNombre.toArray());				
							getJTable().setModel(defaultTableModel);
							getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
							getJTable().setRowHeight(20);
							}
						if(TipoMateriajComboBox.getSelectedIndex()==3){
							DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
						    simbolo.setDecimalSeparator(',');
						    simbolo.setGroupingSeparator('.');
							DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
							TableModel defaultTableModel = new TableModel();
							LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
							try {
								list = ServicioMateria.getMateriasPrimasMalta();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
							ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
							ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
							ArrayList<Object> columnDataProducto = new ArrayList<Object>();
							ArrayList<Object> columnDataNombre= new ArrayList<Object>();
							
							for (int i = 0; i < list.size(); i++) {
								columnDataFechaLlenado.add(list.get(i).getFechallenado());
								columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));						
								columnDataObservaciones.add(list.get(i).getObservaciones());
								columnDataProducto.add(list.get(i).getTipomateria());
								columnDataNombre.add(list.get(i).getDescripcion());
								}
							defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
							defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
							defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
							defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
							defaultTableModel.addColumn("Producto",columnDataNombre.toArray());					
							getJTable().setModel(defaultTableModel);
							getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
							getJTable().setRowHeight(20);
							}
						if(TipoMateriajComboBox.getSelectedIndex()==4){
							DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
						    simbolo.setDecimalSeparator(',');
						    simbolo.setGroupingSeparator('.');
							DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
							TableModel defaultTableModel = new TableModel();
							LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
							try {
								list = ServicioMateria.getMateriasRCS();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
							ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
							ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
							ArrayList<Object> columnDataProducto = new ArrayList<Object>();
							ArrayList<Object> columnDataNombre= new ArrayList<Object>();
							
							for (int i = 0; i < list.size(); i++) {
								columnDataFechaLlenado.add(list.get(i).getFechallenado());
								columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));						
								columnDataObservaciones.add(list.get(i).getObservaciones());
								columnDataProducto.add(list.get(i).getTipomateria());
								columnDataNombre.add(list.get(i).getDescripcion());
								}
							defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
							defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
							defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
							defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
							defaultTableModel.addColumn("Producto",columnDataNombre.toArray());					
							getJTable().setModel(defaultTableModel);
							getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
							getJTable().setRowHeight(20);
						}
						if(TipoMateriajComboBox.getSelectedIndex()==5){
							DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
						    simbolo.setDecimalSeparator(',');
						    simbolo.setGroupingSeparator('.');
							DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
							TableModel defaultTableModel = new TableModel();
							LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
							try {
								list = ServicioMateria.getMateriasRonMadre();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
							ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
							ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
							ArrayList<Object> columnDataProducto = new ArrayList<Object>();
							ArrayList<Object> columnDataNombre= new ArrayList<Object>();
							
							for (int i = 0; i < list.size(); i++) {
								columnDataFechaLlenado.add(list.get(i).getFechallenado());
								columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));						
								columnDataObservaciones.add(list.get(i).getObservaciones());
								columnDataProducto.add(list.get(i).getTipomateria());
								columnDataNombre.add(list.get(i).getDescripcion());
								}
							defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
							defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
							defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
							defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
							defaultTableModel.addColumn("Producto",columnDataNombre.toArray());				
							getJTable().setModel(defaultTableModel);
							getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
							getJTable().setRowHeight(20);
							}
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ModificarjButton.setEnabled(false);
					DetallejButton.setEnabled(false);
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
	 * This method initializes ModificarjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getModificarjButton() {
		if (ModificarjButton == null) {
			ModificarjButton = new JButton();
			ModificarjButton.setText("Eliminar");
			ModificarjButton.setBounds(new Rectangle(562, 138, 133, 29));
			ModificarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/edit-cut.png")));
			ModificarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
						int result = JOptionPane.showConfirmDialog(MateriaVisual.this, "Esta seguro que desea eliminar el Producto?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						if(result == JOptionPane.YES_OPTION){
						try {
							ServicioMateria.EliminarMateria(mp.getId_materia());
							JOptionPane.showMessageDialog(MateriaVisual.this, "Producto Eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							ObservacionesjTextArea.setText("");
							GradojTextField.setText("");
							NombrejTextField.setText("");
							if(TipoMateriajComboBox.getSelectedIndex()==1){
							DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
						    simbolo.setDecimalSeparator(',');
						    simbolo.setGroupingSeparator('.');
							DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
							TableModel defaultTableModel = new TableModel();
							LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
							try {
								list = ServicioMateria.getMateriasPrimasAlcoholAñejado();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
							ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
							ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
							ArrayList<Object> columnDataProducto = new ArrayList<Object>();
							ArrayList<Object> columnDataNombre= new ArrayList<Object>();
							
							for (int i = 0; i < list.size(); i++) {
								columnDataFechaLlenado.add(list.get(i).getFechallenado());
								columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));						
								columnDataObservaciones.add(list.get(i).getObservaciones());
								columnDataProducto.add(list.get(i).getTipomateria());
								columnDataNombre.add(list.get(i).getDescripcion());
								}
							defaultTableModel.setRowCount(list.size());
							defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
							defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
							defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
							defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
							defaultTableModel.addColumn("Producto",columnDataNombre.toArray());				
							getJTable().setModel(defaultTableModel);
							getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
							getJTable().setRowHeight(20);										
							}
							if(TipoMateriajComboBox.getSelectedIndex()==2){
								DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
							    simbolo.setDecimalSeparator(',');
							    simbolo.setGroupingSeparator('.');
								DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
								TableModel defaultTableModel = new TableModel();
								LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
								try {
									list = ServicioMateria.getMateriasPrimasExtractoRoble();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
								ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
								ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
								ArrayList<Object> columnDataProducto = new ArrayList<Object>();
								ArrayList<Object> columnDataNombre= new ArrayList<Object>();
								
								for (int i = 0; i < list.size(); i++) {
									columnDataFechaLlenado.add(list.get(i).getFechallenado());
									columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));						
									columnDataObservaciones.add(list.get(i).getObservaciones());
									columnDataProducto.add(list.get(i).getTipomateria());
									columnDataNombre.add(list.get(i).getDescripcion());
									}
								defaultTableModel.setRowCount(list.size());
								defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
								defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
								defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
								defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
								defaultTableModel.addColumn("Producto",columnDataNombre.toArray());				
								getJTable().setModel(defaultTableModel);
								getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
								getJTable().setRowHeight(20);
								}
							if(TipoMateriajComboBox.getSelectedIndex()==3){
								DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
							    simbolo.setDecimalSeparator(',');
							    simbolo.setGroupingSeparator('.');
								DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
								TableModel defaultTableModel = new TableModel();
								LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
								try {
									list = ServicioMateria.getMateriasPrimasMalta();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
								ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
								ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
								ArrayList<Object> columnDataProducto = new ArrayList<Object>();
								ArrayList<Object> columnDataNombre= new ArrayList<Object>();
								
								for (int i = 0; i < list.size(); i++) {
									columnDataFechaLlenado.add(list.get(i).getFechallenado());
									columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));						
									columnDataObservaciones.add(list.get(i).getObservaciones());
									columnDataProducto.add(list.get(i).getTipomateria());
									columnDataNombre.add(list.get(i).getDescripcion());
									}
								defaultTableModel.setRowCount(list.size());
								defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
								defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
								defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
								defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
								defaultTableModel.addColumn("Producto",columnDataNombre.toArray());					
								getJTable().setModel(defaultTableModel);
								getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
								getJTable().setRowHeight(20);
								}
							if(TipoMateriajComboBox.getSelectedIndex()==4){
								DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
							    simbolo.setDecimalSeparator(',');
							    simbolo.setGroupingSeparator('.');
								DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
								TableModel defaultTableModel = new TableModel();
								LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
								try {
									list = ServicioMateria.getMateriasRCS();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
								ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
								ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
								ArrayList<Object> columnDataProducto = new ArrayList<Object>();
								ArrayList<Object> columnDataNombre= new ArrayList<Object>();
								
								for (int i = 0; i < list.size(); i++) {
									columnDataFechaLlenado.add(list.get(i).getFechallenado());
									columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));						
									columnDataObservaciones.add(list.get(i).getObservaciones());
									columnDataProducto.add(list.get(i).getTipomateria());
									columnDataNombre.add(list.get(i).getDescripcion());
									}
								defaultTableModel.setRowCount(list.size());
								defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
								defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
								defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
								defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
								defaultTableModel.addColumn("Producto",columnDataNombre.toArray());					
								getJTable().setModel(defaultTableModel);
								getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
								getJTable().setRowHeight(20);
							}
							if(TipoMateriajComboBox.getSelectedIndex()==5){
								DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
							    simbolo.setDecimalSeparator(',');
							    simbolo.setGroupingSeparator('.');
								DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
								TableModel defaultTableModel = new TableModel();
								LinkedList<MateriaPrima> list = new LinkedList<MateriaPrima>();
								try {
									list = ServicioMateria.getMateriasRonMadre();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
								ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
								ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
								ArrayList<Object> columnDataProducto = new ArrayList<Object>();
								ArrayList<Object> columnDataNombre= new ArrayList<Object>();
								
								for (int i = 0; i < list.size(); i++) {
									columnDataFechaLlenado.add(list.get(i).getFechallenado());
									columnDataGrado.add(dosDigitos.format(list.get(i).getGrado()));						
									columnDataObservaciones.add(list.get(i).getObservaciones());
									columnDataProducto.add(list.get(i).getTipomateria());
									columnDataNombre.add(list.get(i).getDescripcion());
									}
								defaultTableModel.setRowCount(list.size());
								defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
								defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
								defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
								defaultTableModel.addColumn("Tipo Materia Prima",columnDataProducto.toArray());
								defaultTableModel.addColumn("Producto",columnDataNombre.toArray());				
								getJTable().setModel(defaultTableModel);
								getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
								getJTable().setRowHeight(20);
								}
							} catch (SQLException e1) {
							// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(MateriaVisual.this, "No se puede eliminar el Producto porque Existen relaciones con el mismo", "Error", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						UserInterfaceSuport.clearComponents(getJContentPane());
						ModificarjButton.setEnabled(false);
						EliminarjButton.setEnabled(false);
						DetallejButton.setEnabled(false);
						InsertarjButton.setEnabled(true);
												
					}
						
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return ModificarjButton;
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
			CerrarjButton.setBounds(new Rectangle(859, 138, 133, 29));
			CerrarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Delete_16x16.png")));
			CerrarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					Start i=new Start();
					new Principal(i).setVisible(true);
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return CerrarjButton;
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
			RestaurarjButton.setBounds(new Rectangle(710, 138, 133, 29));
			RestaurarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/edit-clear.png")));
			RestaurarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					UserInterfaceSuport.clearComponents(getJPanel());
					InsertarjButton.setEnabled(true);
					DetallejButton.setEnabled(false);
					ModificarjButton.setEnabled(false);
					EliminarjButton.setEnabled(false);
					ObservacionesjTextArea.setText("");
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return RestaurarjButton;
	}


	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(309, 42, 269, 84));
			jScrollPane1.setViewportView(getObservacionesjTextArea());
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
			jPanel.add(getRestaurarjButton(), null);
			jPanel.add(getModificarjButton(), null);
			jPanel.add(getEliminarjButton(), null);
			jPanel.add(getDetallejButton(), null);
			jPanel.add(getInsertarjButton(), null);
			jPanel.add(getDatejSpinner(), null);
			jPanel.add(FechajLabel, null);
			jPanel.add(getGradojTextField(), null);
			jPanel.add(GradojLabel, null);
			jPanel.add(ObservacionesjLabel, null);
			jPanel.add(getTipoMateriajComboBox(), null);
			jPanel.add(descripcionjLabel, null);
			jPanel.add(getNombrejTextField(), null);
			jPanel.add(TipoMateriajLabel, null);
			jPanel.add(getJScrollPane1(), null);
		}
		return jPanel;
	}

	

}  //  @jve:decl-index=0:visual-constraint="10,10"
