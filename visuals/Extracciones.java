package visuals;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.MateriaPrima;
import model.TipoMateria;
import services.ServicioMateria;
import Utils.FormatoTabla;
import Utils.TableModel;

import javax.swing.JComboBox;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.UIManager;

public class Extracciones extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JButton SALIRjButton = null;
	private JComboBox<TipoMateria> TipoMateriajComboBox = null;
	private MateriaPrima mp=null;
	private JButton btnVerExtracionesDel=null;
	

	public Extracciones() {
		super();
		initialize();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new Dimension(1026, 552));
        this.setTitle("Gestionar Extracciones de Líquidos");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/toneles.png")));
        this.setContentPane(getJContentPane());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);		
    	TableModel defaultTableModel = new TableModel();		
		ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
		ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
		ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
		ArrayList<Object> columnDataProducto = new ArrayList<Object>();
		ArrayList<Object> columnDataNombre= new ArrayList<Object>();
		defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
		defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
		defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
		defaultTableModel.addColumn("producto",columnDataProducto.toArray());
		defaultTableModel.addColumn("Descripcion",columnDataNombre.toArray());			
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
				int pos = jTable.getSelectedRow();
				if(TipoMateriajComboBox.getSelectedIndex()==6){
				try {
					LinkedList<MateriaPrima> toneles = ServicioMateria.getMateriasPrimas();
					MateriaPrima t = toneles.get(pos);	
					mp=t;
					TipoMateriajComboBox.setSelectedItem(mp.getTipomateria());		
					btnVerExtracionesDel.setEnabled(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
				if(TipoMateriajComboBox.getSelectedIndex()==1){
					try {
						LinkedList<MateriaPrima> toneles = ServicioMateria.getMateriasPrimasAlcoholAñejado();
						MateriaPrima t = toneles.get(pos);	
						mp=t;
						TipoMateriajComboBox.setSelectedItem(mp.getTipomateria());		
						btnVerExtracionesDel.setEnabled(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}
				if(TipoMateriajComboBox.getSelectedIndex()==2){
					try {
						LinkedList<MateriaPrima> toneles = ServicioMateria.getMateriasPrimasExtractoRoble();
						MateriaPrima t = toneles.get(pos);					
						mp=t;
						TipoMateriajComboBox.setSelectedItem(mp.getTipomateria());		
						btnVerExtracionesDel.setEnabled(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}
				
				if(TipoMateriajComboBox.getSelectedIndex()==3){
					try {
						LinkedList<MateriaPrima> toneles = ServicioMateria.getMateriasPrimasMalta();
						MateriaPrima t = toneles.get(pos);					
						mp=t;
						TipoMateriajComboBox.setSelectedItem(mp.getTipomateria());		
						btnVerExtracionesDel.setEnabled(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}
				
				if(TipoMateriajComboBox.getSelectedIndex()==4){
					try {
						LinkedList<MateriaPrima> toneles = ServicioMateria.getMateriasRCS();
						MateriaPrima t = toneles.get(pos);					
						mp=t;
						TipoMateriajComboBox.setSelectedItem(mp.getTipomateria());			
						btnVerExtracionesDel.setEnabled(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}
				if(TipoMateriajComboBox.getSelectedIndex()==5){
					try {
						LinkedList<MateriaPrima> toneles = ServicioMateria.getMateriasRonMadre();
						MateriaPrima t = toneles.get(pos);					
						mp=t;
						TipoMateriajComboBox.setSelectedItem(mp.getTipomateria());		
						btnVerExtracionesDel.setEnabled(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
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
			jContentPane.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
			GridBagLayout gbl_jContentPane = new GridBagLayout();
			gbl_jContentPane.columnWidths = new int[]{982, 0};
			gbl_jContentPane.rowHeights = new int[]{51, 444, 0};
			gbl_jContentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_jContentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			jContentPane.setLayout(gbl_jContentPane);
			
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			jContentPane.add(panel, gbc_panel);
			panel.setLayout(null);
			panel.add(getTipoMateriajComboBox());
			
			JLabel lblTipoMateriaPrima = new JLabel();
			lblTipoMateriaPrima.setBounds(12, 0, 157, 16);
			panel.add(lblTipoMateriaPrima);
			lblTipoMateriaPrima.setText("Tipo Materia Prima:");
			panel.add(getSALIRjButton());	
			panel.add(getbtnVerExtracionesDel());		
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

	private  JButton getbtnVerExtracionesDel(){
		btnVerExtracionesDel = new JButton("Ver Extraciones del Producto");
		btnVerExtracionesDel.setIcon(new ImageIcon(Extracciones.class.getResource("/img/lens_in.png")));
		btnVerExtracionesDel.setBounds(255, 15, 230, 25);
		btnVerExtracionesDel.setEnabled(false);
		btnVerExtracionesDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ExtraccionVisual(mp).setVisible(true);		
			}
		});
		
		return btnVerExtracionesDel;
	}

	/**
	 * This method initializes SALIRjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSALIRjButton() {
		if (SALIRjButton == null) {
			SALIRjButton = new JButton();
			SALIRjButton.setBounds(497, 15, 157, 25);
			SALIRjButton.setText("Cerrar");
			SALIRjButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Delete_16x16.png")));
			SALIRjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Start i = new Start();
					new Principal(i).setVisible(true);
					dispose();
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return SALIRjButton;
	}
	

	/**
	 * This method initializes TipoMateriajComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getTipoMateriajComboBox() {
		if (TipoMateriajComboBox   == null) {
			TipoMateriajComboBox = new JComboBox();
			TipoMateriajComboBox.setBounds(12, 15, 157, 25);
			TipoMateriajComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
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
						defaultTableModel.addColumn("Producto",columnDataProducto.toArray());
						defaultTableModel.addColumn("Materia Prima",columnDataNombre.toArray());			
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
						defaultTableModel.addColumn("Producto",columnDataProducto.toArray());
						defaultTableModel.addColumn("Materia Prima",columnDataNombre.toArray());			
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
						defaultTableModel.addColumn("Producto",columnDataProducto.toArray());
						defaultTableModel.addColumn("Materia Prima",columnDataNombre.toArray());			
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
						defaultTableModel.addColumn("Producto",columnDataProducto.toArray());
						defaultTableModel.addColumn("Materia Prima",columnDataNombre.toArray());			
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
						defaultTableModel.addColumn("Producto",columnDataProducto.toArray());
						defaultTableModel.addColumn("Materia Prima",columnDataNombre.toArray());			
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
					}
					
					if(TipoMateriajComboBox.getSelectedIndex()==6){
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
						defaultTableModel.addColumn("Producto",columnDataProducto.toArray());
						defaultTableModel.addColumn("Materia Prima",columnDataNombre.toArray());			
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
					}
					if(TipoMateriajComboBox.getSelectedIndex()==0){
						TableModel defaultTableModel = new TableModel();
						ArrayList<Object> columnDataFechaLlenado = new ArrayList<Object>();
						ArrayList<Object> columnDataGrado = new ArrayList<Object>();					
						ArrayList<Object> columnDataObservaciones = new ArrayList<Object>();
						ArrayList<Object> columnDataProducto = new ArrayList<Object>();
						ArrayList<Object> columnDataNombre= new ArrayList<Object>();					
						defaultTableModel.addColumn("Fecha Llenado",columnDataFechaLlenado.toArray());
						defaultTableModel.addColumn("Grado",columnDataGrado.toArray());					
						defaultTableModel.addColumn("Observaciones",columnDataObservaciones.toArray());
						defaultTableModel.addColumn("Producto",columnDataProducto.toArray());
						defaultTableModel.addColumn("Materia Prima",columnDataNombre.toArray());			
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
					}
				}
			});
			}
		return TipoMateriajComboBox;
	}
}  //  @jve:decl-index=0:visual-constraint="83,6"
