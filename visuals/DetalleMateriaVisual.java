package visuals;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.DetalleMateria;
import model.MateriaPrima;
import services.ServicioDetalleMateria;
import Utils.FormatoTabla;
import Utils.Reportes;
import Utils.TableModel;
import Utils.UserInterfaceSuport;
import Utils.Validate;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class DetalleMateriaVisual extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JLabel SeccionjLabel = null;
	private JTextField SecciojTextField = null;
	private JLabel UbicacionjLabel = null;
	private JTextField UbicacionjTextField = null;
	private JLabel TamnojLabel = null;
	private JTextField tamanojTextField = null;
	private JLabel CantidadjLabel = null;
	private JTextField cantijTextField = null;
	private JLabel VolumenjLabel = null;
	private JTextField volumenjTextField = null;
	private JButton InsertarjButton = null;
	private MateriaPrima mp=null;
	private JLabel ProductojLabel = null;
	private JButton ModificarjButton = null;
	private JButton ELiminarjButton = null;
	private JButton CerrarjButton = null;
	private JButton CalcularjButton = null;
	private JButton ImprimirjButton = null;
	private JButton RestaurarjButton = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JLabel lblGrado;
	public DetalleMateriaVisual(MateriaPrima mp) {
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
			this.setSize(new Dimension(941, 572));
			this.setTitle("Gestionar Detalles de Productos");
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/toneles.png")));
			this.setContentPane(getJContentPane());
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			ModificarjButton.setEnabled(false);
			ELiminarjButton.setEnabled(false);
			InsertarjButton.setEnabled(true);
			DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
		    simbolo.setDecimalSeparator(',');
		    simbolo.setGroupingSeparator('.');
			DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
			TableModel defaultTableModel = new TableModel();
			LinkedList<DetalleMateria> list = new LinkedList<DetalleMateria>();
			list =ServicioDetalleMateria.getMediciones(mp.getId_materia() );
			ArrayList<Object> columnDataSeccion = new ArrayList<Object>();
			ArrayList<Object> columnDataUbicacion = new ArrayList<Object>();					
			ArrayList<Object> columnDataTamano = new ArrayList<Object>();
			ArrayList<Object> columnDataCantidad = new ArrayList<Object>();
			ArrayList<Object> columnDataVolumen= new ArrayList<Object>();
			
			for (int i = 0; i < list.size(); i++) {
				columnDataSeccion.add(list.get(i).getSeccion());
				columnDataUbicacion.add(list.get(i).getUbicacion());					
				columnDataTamano.add(list.get(i).getTamano());
				columnDataCantidad.add(list.get(i).getTonel());
				columnDataVolumen.add(dosDigitos.format(list.get(i).getVolumen()));
				
				}
			defaultTableModel.setRowCount(list.size());
			defaultTableModel.addColumn("Seccion",columnDataSeccion.toArray());
			defaultTableModel.addColumn("Ubicacion",columnDataUbicacion.toArray());					
			defaultTableModel.addColumn("Cant. Toneles de 180",columnDataTamano.toArray());
			defaultTableModel.addColumn("Cant. Toneles de 225",columnDataCantidad.toArray());
			defaultTableModel.addColumn("Volumen",columnDataVolumen.toArray());				
			getJTable().setModel(defaultTableModel);
			getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
			getJTable().setRowHeight(20);
			

			jTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
				    simbolo.setDecimalSeparator('.');
					DecimalFormat dosDigitos = new DecimalFormat( "####.#####",simbolo);
					int pos = jTable.getSelectedRow();
					LinkedList<DetalleMateria> toneles = ServicioDetalleMateria.getMediciones(mp.getId_materia());
					DetalleMateria t = toneles.get(pos);
					SecciojTextField.setText(t.getSeccion());
					UbicacionjTextField.setText(t.getUbicacion());
					tamanojTextField.setText(String.valueOf(t.getTamano()));
					cantijTextField.setText(String.valueOf(t.getTonel()));
					volumenjTextField.setText(String.valueOf(dosDigitos.format(t.getVolumen())));
					ModificarjButton.setEnabled(true);
					ELiminarjButton.setEnabled(true);
					InsertarjButton.setEnabled(false);
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
			ProductojLabel = new JLabel();
			ProductojLabel.setText( "Producto:  "+mp.getDescripcion());
			ProductojLabel.setBounds(new Rectangle(714, 13, 176, 22));
			VolumenjLabel = new JLabel();
			VolumenjLabel.setText("Volumen:");
			VolumenjLabel.setBounds(new Rectangle(606, 13, 106, 22));
			CantidadjLabel = new JLabel();
			CantidadjLabel.setText("Cant. Toneles de 225:");
			CantidadjLabel.setBounds(new Rectangle(399, 13, 142, 22));
			TamnojLabel = new JLabel();
			TamnojLabel.setText("Cant. Toneles de 180:");
			TamnojLabel.setBounds(new Rectangle(248, 13, 142, 22));
			UbicacionjLabel = new JLabel();
			UbicacionjLabel.setText("Ubicacion:");
			UbicacionjLabel.setBounds(new Rectangle(130, 13, 106, 22));
			SeccionjLabel = new JLabel();
			SeccionjLabel.setText("Seccion:");
			SeccionjLabel.setBounds(new Rectangle(12, 13, 106, 22));
			jContentPane = new JPanel();
			GridBagLayout gbl_jContentPane = new GridBagLayout();
			gbl_jContentPane.columnWidths = new int[]{923, 0};
			gbl_jContentPane.rowHeights = new int[]{95, 50, 370, 0};
			gbl_jContentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_jContentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			jContentPane.setLayout(gbl_jContentPane);
			GridBagConstraints gbc_jPanel1 = new GridBagConstraints();
			gbc_jPanel1.fill = GridBagConstraints.BOTH;
			gbc_jPanel1.insets = new Insets(0, 0, 5, 0);
			gbc_jPanel1.gridx = 0;
			gbc_jPanel1.gridy = 0;
			jContentPane.add(getJPanel1(), gbc_jPanel1);
			GridBagConstraints gbc_jPanel = new GridBagConstraints();
			gbc_jPanel.fill = GridBagConstraints.BOTH;
			gbc_jPanel.insets = new Insets(0, 0, 5, 0);
			gbc_jPanel.gridx = 0;
			gbc_jPanel.gridy = 1;
			jContentPane.add(getJPanel(), gbc_jPanel);
			GridBagConstraints gbc_jScrollPane = new GridBagConstraints();
			gbc_jScrollPane.fill = GridBagConstraints.BOTH;
			gbc_jScrollPane.gridx = 0;
			gbc_jScrollPane.gridy = 2;
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
	 * This method initializes SecciojTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSecciojTextField() {
		if (SecciojTextField == null) {
			SecciojTextField = new JTextField();
			SecciojTextField.setBounds(new Rectangle(12, 34, 106, 27));
			}
		return SecciojTextField;
	}



	/**
	 * This method initializes UbicacionjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getUbicacionjTextField() {
		if (UbicacionjTextField == null) {
			UbicacionjTextField = new JTextField();
			UbicacionjTextField.setBounds(new Rectangle(130, 34, 106, 27));
		}
		return UbicacionjTextField;
	}



	/**
	 * This method initializes tamanojTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTamanojTextField() {
		if (tamanojTextField == null) {
			tamanojTextField = new JTextField();
			tamanojTextField.setBounds(new Rectangle(248, 34, 142, 27));
			Validate.validateDigit(tamanojTextField);
		}
		return tamanojTextField;
	}



	/**
	 * This method initializes cantijTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCantijTextField() {
		if (cantijTextField == null) {
			cantijTextField = new JTextField();
			cantijTextField.setBounds(new Rectangle(399, 34, 142, 27));
			Validate.validateDigit(cantijTextField);
		}
		return cantijTextField;
	}

	

	/**
	 * This method initializes volumenjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getVolumenjTextField() {
		if (volumenjTextField == null) {
			volumenjTextField = new JTextField();
			volumenjTextField.setBounds(new Rectangle(606, 34, 82, 27));
			Validate.validateDigitAndComma(volumenjTextField);
		}
		return volumenjTextField;
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
			InsertarjButton.setBounds(new Rectangle(12, 13, 131, 27));
			InsertarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_SysInfo_Unit1_ilInfoStates1_16x16.png")));
			InsertarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(SecciojTextField.getText().length()>0 && UbicacionjTextField.getText().length()!= 0 && tamanojTextField.getText().length()!=0 && cantijTextField.getText().length()!=0 && volumenjTextField.getText().length()!=0){
						try {
						ServicioDetalleMateria.insertarMateria(SecciojTextField.getText(), UbicacionjTextField.getText(), Integer.valueOf(tamanojTextField.getText()), Integer.valueOf(cantijTextField.getText()), Float.valueOf(volumenjTextField.getText()), mp.getId_materia());
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
						TableModel defaultTableModel = new TableModel();
						LinkedList<DetalleMateria> list = new LinkedList<DetalleMateria>();
						list =ServicioDetalleMateria.getMediciones(mp.getId_materia()) ;
						ArrayList<Object> columnDataSeccion = new ArrayList<Object>();
						ArrayList<Object> columnDataUbicacion = new ArrayList<Object>();					
						ArrayList<Object> columnDataTamano = new ArrayList<Object>();
						ArrayList<Object> columnDataCantidad = new ArrayList<Object>();
						ArrayList<Object> columnDataVolumen= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataSeccion.add(list.get(i).getSeccion());
							columnDataUbicacion.add(list.get(i).getUbicacion());					
							columnDataTamano.add(list.get(i).getTamano());
							columnDataCantidad.add(list.get(i).getTonel());
							columnDataVolumen.add(dosDigitos.format(list.get(i).getVolumen()));
							
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Seccion",columnDataSeccion.toArray());
						defaultTableModel.addColumn("Ubicacion",columnDataUbicacion.toArray());					
						defaultTableModel.addColumn("Cant. Toneles de 180",columnDataTamano.toArray());
						defaultTableModel.addColumn("Cant. Toneles de 225",columnDataCantidad.toArray());
						defaultTableModel.addColumn("Volumen",columnDataVolumen.toArray());		
						
						getJTable().setModel(defaultTableModel);
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
						JOptionPane.showMessageDialog(DetalleMateriaVisual.this, "Producto Insertado", "Informacion", JOptionPane.INFORMATION_MESSAGE);	
						UserInterfaceSuport.clearComponents(jPanel1);
							
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(DetalleMateriaVisual.this, "No se puede insertar dos Materias iguales", "Error", JOptionPane.ERROR_MESSAGE);
												// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					InsertarjButton.setEnabled(true);
					SecciojTextField.setText("");
					UbicacionjTextField.setText("");
					tamanojTextField.setText("");
					cantijTextField.setText("");
					volumenjTextField.setText("");
					ModificarjButton.setEnabled(false);
					ELiminarjButton.setEnabled(false);
					InsertarjButton.setEnabled(true);
					}
					else{
						JOptionPane.showMessageDialog(DetalleMateriaVisual.this, "Campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);	
						System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
				}
			});
		}
		return InsertarjButton;
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
			ModificarjButton.setBounds(new Rectangle(159, 13, 131, 27));
			ModificarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Refresh_24x24.png")));
			ModificarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(DetalleMateriaVisual.this, "Esta seguro que desea Modificar el Producto?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = jTable.getSelectedRow();
					try {
						LinkedList<DetalleMateria> mediciones = ServicioDetalleMateria.getMediciones(mp.getId_materia());
						DetalleMateria u = mediciones.get(pos);
						ServicioDetalleMateria.ActualizarMedicion(SecciojTextField.getText(), UbicacionjTextField.getText(), Integer.valueOf(tamanojTextField.getText()),Integer.valueOf(cantijTextField.getText()), Float.valueOf(volumenjTextField.getText()), u.getId_detalle());
						JOptionPane.showMessageDialog(DetalleMateriaVisual.this, "Producto Modificado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
						TableModel defaultTableModel = new TableModel();
						LinkedList<DetalleMateria> list = new LinkedList<DetalleMateria>();
						list =ServicioDetalleMateria.getMediciones(mp.getId_materia()) ;
						ArrayList<Object> columnDataSeccion = new ArrayList<Object>();
						ArrayList<Object> columnDataUbicacion = new ArrayList<Object>();					
						ArrayList<Object> columnDataTamano = new ArrayList<Object>();
						ArrayList<Object> columnDataCantidad = new ArrayList<Object>();
						ArrayList<Object> columnDataVolumen= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataSeccion.add(list.get(i).getSeccion());
							columnDataUbicacion.add(list.get(i).getUbicacion());					
							columnDataTamano.add(list.get(i).getTamano());
							columnDataCantidad.add(list.get(i).getTonel());
							columnDataVolumen.add(dosDigitos.format(list.get(i).getVolumen()));
							
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Seccion",columnDataSeccion.toArray());
						defaultTableModel.addColumn("Ubicacion",columnDataUbicacion.toArray());					
						defaultTableModel.addColumn("Cant. Toneles de 180",columnDataTamano.toArray());
						defaultTableModel.addColumn("Cant. Toneles de 225",columnDataCantidad.toArray());
						defaultTableModel.addColumn("Volumen",columnDataVolumen.toArray());		
						
						getJTable().setModel(defaultTableModel);	
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
						UserInterfaceSuport.clearComponents(getJPanel1());
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					UserInterfaceSuport.clearComponents(getJContentPane());
					SecciojTextField.setText("");
					UbicacionjTextField.setText("");
					tamanojTextField.setText("");
					cantijTextField.setText("");
					volumenjTextField.setText("");
					ModificarjButton.setEnabled(false);
					ELiminarjButton.setEnabled(false);
					InsertarjButton.setEnabled(true);						
				}
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return ModificarjButton;
	}

	/**
	 * This method initializes ELiminarjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getELiminarjButton() {
		if (ELiminarjButton == null) {
			ELiminarjButton = new JButton();
			ELiminarjButton.setText("Eliminar");
			ELiminarjButton.setBounds(new Rectangle(308, 13, 131, 27));
			ELiminarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/edit-cut.png")));
			ELiminarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(DetalleMateriaVisual.this, "Esta seguro que desea eliminar el Producto?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(result == JOptionPane.YES_OPTION){
					int pos = jTable.getSelectedRow();
					try {
						LinkedList<DetalleMateria> mediciones = ServicioDetalleMateria.getMediciones(mp.getId_materia());
						DetalleMateria u = mediciones.get(pos);
						ServicioDetalleMateria.EliminarDetalleMateria(u.getId_detalle());
						JOptionPane.showMessageDialog(DetalleMateriaVisual.this, "Producto Eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
						DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
					    simbolo.setDecimalSeparator(',');
					    simbolo.setGroupingSeparator('.');
						DecimalFormat dosDigitos = new DecimalFormat( "###,###.#####",simbolo);
						TableModel defaultTableModel = new TableModel();
						LinkedList<DetalleMateria> list = new LinkedList<DetalleMateria>();
						list =ServicioDetalleMateria.getMediciones(mp.getId_materia()) ;
						ArrayList<Object> columnDataSeccion = new ArrayList<Object>();
						ArrayList<Object> columnDataUbicacion = new ArrayList<Object>();					
						ArrayList<Object> columnDataTamano = new ArrayList<Object>();
						ArrayList<Object> columnDataCantidad = new ArrayList<Object>();
						ArrayList<Object> columnDataVolumen= new ArrayList<Object>();
						
						for (int i = 0; i < list.size(); i++) {
							columnDataSeccion.add(list.get(i).getSeccion());
							columnDataUbicacion.add(list.get(i).getUbicacion());					
							columnDataTamano.add(list.get(i).getTamano());
							columnDataCantidad.add(list.get(i).getTonel());
							columnDataVolumen.add(dosDigitos.format(list.get(i).getVolumen()));
							
							}
						defaultTableModel.setRowCount(list.size());
						defaultTableModel.addColumn("Seccion",columnDataSeccion.toArray());
						defaultTableModel.addColumn("Ubicacion",columnDataUbicacion.toArray());					
						defaultTableModel.addColumn("Cant. Toneles de 180",columnDataTamano.toArray());
						defaultTableModel.addColumn("Cant. Toneles de 225",columnDataCantidad.toArray());
						defaultTableModel.addColumn("Volumen",columnDataVolumen.toArray());		
						
						getJTable().setModel(defaultTableModel);	
						getJTable().setDefaultRenderer (Object.class, new FormatoTabla());	
						getJTable().setRowHeight(20);
						UserInterfaceSuport.clearComponents(getJPanel1());
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(DetalleMateriaVisual.this, "No se puede eliminar el Producto porque Existen relaciones con el mismo", "Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					UserInterfaceSuport.clearComponents(getJContentPane());
					SecciojTextField.setText("");
					UbicacionjTextField.setText("");
					tamanojTextField.setText("");
					cantijTextField.setText("");
					volumenjTextField.setText("");
					ModificarjButton.setEnabled(false);
					ELiminarjButton.setEnabled(false);
					InsertarjButton.setEnabled(true);
											
				}
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return ELiminarjButton;
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
			CerrarjButton.setBounds(new Rectangle(737, 13, 131, 27));
			CerrarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Delete_16x16.png")));
			CerrarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new MateriaVisual().setVisible(true);
					dispose();
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return CerrarjButton;
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
			CalcularjButton.setBounds(new Rectangle(551, 13, 43, 45));
			CalcularjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(tamanojTextField.getText().length()!=0 && cantijTextField.getText().length()!=0){
					Integer tam = Integer.valueOf(tamanojTextField.getText())*180;
					Integer cant = Integer.valueOf(cantijTextField.getText())*225;					
					volumenjTextField.setText(String.valueOf(tam + cant) );
					}else{
						JOptionPane.showMessageDialog(DetalleMateriaVisual.this, "Existen Campos Vacíos", "Error", JOptionPane.ERROR_MESSAGE);
					}
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return CalcularjButton;
	}

	/**
	 * This method initializes ImprimirjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getImprimirjButton() {
		if (ImprimirjButton == null) {
			ImprimirjButton = new JButton();
			ImprimirjButton.setText("Imprimir");
			ImprimirjButton.setBounds(new Rectangle(597, 13, 131, 27));
			ImprimirjButton.setIcon(new ImageIcon(getClass().getResource("/Img/printer.png")));
			ImprimirjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Reportes.getR().ReporteProducto(mp.getId_materia());
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return ImprimirjButton;
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
			RestaurarjButton.setBounds(new Rectangle(452, 13, 131, 27));
			RestaurarjButton.setIcon(new ImageIcon(getClass().getResource("/Img/edit-clear.png")));
			RestaurarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					SecciojTextField.setText("");
					UbicacionjTextField.setText("");
					tamanojTextField.setText("");
					cantijTextField.setText("");
					volumenjTextField.setText("");
					ModificarjButton.setEnabled(false);
					ELiminarjButton.setEnabled(false);
					InsertarjButton.setEnabled(true);						
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return RestaurarjButton;
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
			jPanel.add(getELiminarjButton(), null);
			jPanel.add(getModificarjButton(), null);
			jPanel.add(getInsertarjButton(), null);
			jPanel.add(getImprimirjButton(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(VolumenjLabel, null);
			jPanel1.add(getVolumenjTextField(), null);
			jPanel1.add(getCalcularjButton(), null);
			jPanel1.add(CantidadjLabel, null);
			jPanel1.add(getCantijTextField(), null);
			jPanel1.add(TamnojLabel, null);
			jPanel1.add(getTamanojTextField(), null);
			jPanel1.add(UbicacionjLabel, null);
			jPanel1.add(getUbicacionjTextField(), null);
			jPanel1.add(SeccionjLabel, null);
			jPanel1.add(getSecciojTextField(), null);
			jPanel1.add(ProductojLabel, null);
			
			JLabel lblNewLabel = new JLabel("Tipo Materia Prima:  " + mp.getTipomateria());
			lblNewLabel.setBounds(714, 34, 212, 27);
			jPanel1.add(lblNewLabel);
			jPanel1.add(getLblGrado());
		}
		return jPanel1;
	}
	private JLabel getLblGrado() {
		if (lblGrado == null) {
			lblGrado = new JLabel("Grado: " + mp.getGrado());
			lblGrado.setBounds(714, 65, 212, 22);
		}
		return lblGrado;
	}
}  //  @jve:decl-index=0:visual-constraint="-92,10"
