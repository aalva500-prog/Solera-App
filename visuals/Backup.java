package visuals;

    import java.awt.BorderLayout;
	import java.awt.Dimension;
import java.awt.Frame;
	import java.awt.GridLayout;
import java.awt.Toolkit;
	import java.awt.event.*;
	import java.io.*;
	import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Utils.Idioma;
import Utils.UserInterfaceSuport;


	public class Backup extends JDialog {
	 /**
		 * 
		 */
		
    private static final long serialVersionUID = 1L;
	Process p;  //  @jve:decl-index=0:
	ProcessBuilder pb;  //  @jve:decl-index=0:
	JFileChooser seleccion = new JFileChooser();  //  @jve:decl-index=0:visual-constraint="626,266"
	JButton btn; 
	JComboBox cBFormato; 
	static JTextArea progreso;  //  @jve:decl-index=0:visual-constraint="604,52"
	JTextField rutaArchivo, usuario, clave, bDatos, host, puerto;
	
	
	
	/**
	 * @param owner
	 */
	public Backup(Frame owner, boolean modal ) {
		super(owner,modal);
		initialize();
		idioma();
	}

	 
	 /**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
      this.setSize(new Dimension(511, 501));
      this.setTitle("Realizar Backup de la Base de Datos");  
      this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/toneles.png")));
      this.setResizable(false);
      this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      setLayout(new BorderLayout());	  
  	  JPanel principal = new JPanel();
  	  principal.setLayout(new GridLayout(8,2));
  	  if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
  	  principal.add(new JLabel("File Name ")); 
  	  }else{
  		 principal.add(new JLabel("Nombre Archivo "));   
  	  }
  	  JPanel aux = new JPanel();
  	  aux.add(rutaArchivo = new JTextField(15));
  	  rutaArchivo.setText("");  	  
  	  btn = new JButton(". . .");
  	  btn.addActionListener(new ActionListener(){
  	   @Override
  	   public void actionPerformed(ActionEvent e) {
  	    if(seleccion.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
  	     rutaArchivo.setText(seleccion.getSelectedFile().getAbsolutePath());
  	    }
  	   }  
  	  }); 
  	  
  	  aux.add(btn); 
  	  principal.add(aux);
  	if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
  		principal.add(new JLabel("Format "));
    	  }else{
    		  principal.add(new JLabel("Formato ")); 
    		  }  	  
  	  cBFormato = new JComboBox(new String[]{"","Custom","Tar","Plain", "Directoy"}); 
  	  cBFormato.addActionListener(new ActionListener() {
  	   @Override
  	   public void actionPerformed(ActionEvent e) {
  	    if(cBFormato.getSelectedIndex() == 0) {
  	     seleccion.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
  	     seleccion.setFileFilter(new FileNameExtensionFilter("Text files (*.txt)","txt"));
  	    } else { 
  	    if(cBFormato.getSelectedIndex() == 1) {
  	     seleccion.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
  	     seleccion.setFileFilter(new FileNameExtensionFilter("Backup files (*.backup)","backup"));
  	    } else {
  	    if(cBFormato.getSelectedIndex() == 2) {
  	     seleccion.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
  	     seleccion.setFileFilter(new FileNameExtensionFilter("Backup files (*.backup)","backup"));
  	    } else {
  	    if(cBFormato.getSelectedIndex() == 3) {
  	     seleccion.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
  	     seleccion.setFileFilter(new FileNameExtensionFilter("Query files (*.sql)","sql"));
  	    } else {
  	    if(cBFormato.getSelectedIndex() == 4) {
  	     seleccion.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
  	     seleccion.setFileFilter(new FileNameExtensionFilter("Directorys ", "txt", "backup", "sql"));
  	    }
  	    }
  	    }
  	    }
  	    }
  	   }}); 
  	  
  	  principal.add(cBFormato);
  	  
  	 	  
  	if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
 		 principal.add(new JLabel("Data Base ")); 
  	  }else{
  		 principal.add(new JLabel("Base de Datos ")).setVisible(false); 
  		  }  
  	  principal.add(bDatos = new JTextField(15)).setVisible(false);
  	  bDatos.setText("soleranew");
  	  bDatos.setEditable(false);
  	  
  	   	  
  	if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
  		 btn = new JButton("Save");
 	  }else{
 		 btn = new JButton("Salvar");
 		btn.setIcon(new ImageIcon(getClass().getResource("/Img/media-floppy.png")));
 		  }   	 
  	  btn.addActionListener(new ActionListener() {
  	   @Override
  	   public void actionPerformed(ActionEvent e) {
  	    pgBackUp(host.getText(), puerto.getText(), usuario.getText(), clave.getText(),
  	      bDatos.getText(), cBFormato.getSelectedItem().toString(), rutaArchivo.getText());
  	   }  
  	  }); 
  	  principal.add(btn);

  	  
  	if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
 		 btn = new JButton("Exit");
	  }else{
		 btn = new JButton("Menú Principal");
		 btn.setIcon(new ImageIcon(getClass().getResource("/Img/go-homeeee.png")));
		  }   
  	  btn.addActionListener(new ActionListener() {
 	   @Override
 	   public void actionPerformed(ActionEvent e) {
 		   dispose();
 		   Start i = new Start();
 		   new Principal(i).setVisible(true); 	   
 	   }  
 	  }); 
 	  principal.add(btn);

  	  progreso = new JTextArea(20,40);
  	  progreso.setSize(new Dimension(462, 184));
  	  JScrollPane scroll = new JScrollPane(progreso);
  	  scroll.setPreferredSize(new Dimension(400,200));
  	  add(scroll, java.awt.BorderLayout.SOUTH);
  	  add(principal);
  	  principal.add(new JLabel("Host")).setVisible(false); 
  	  principal.add(host = new JTextField(15));
  	  host.setText("localhost");
  	  host.setEditable(false);
  	  host.setVisible(false);
  	  
  	if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
  		 principal.add(new JLabel("Port ")).setVisible(false); 
    	  }else{
    		  principal.add(new JLabel("Puerto ")).setVisible(false); 
    		  }   	 
  	  principal.add(puerto = new JTextField(15)).setVisible(false);
  	  puerto.setText("5432");
  	  puerto.setEditable(false);
  	  puerto.setVisible(false);
  	  
  	if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
  		 principal.add(new JLabel("User ")).setVisible(false); 
   	  }else{
   		 principal.add(new JLabel("Usuario ")).setVisible(false); 
   		  }    	 
  	  principal.add(usuario = new JTextField(15)).setVisible(false);
  	  usuario.setText("postgres");
  	  usuario.setEditable(false);
  	  usuario.setVisible(false);
  	  
  	if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
 		 principal.add(new JLabel("Password ")).setVisible(false); 
  	  }else{
  		 principal.add(new JLabel("Contraseña ")).setVisible(false); 
  		  }  
  	  principal.add(clave = new JPasswordField(15)).setVisible(false);
  	  clave.setText("postgres");
  	  clave.setEditable(false);
  	  clave.setVisible(false);
  	  
  	  
  	  UserInterfaceSuport.centerComponent(Backup.this);
			
	}



	public void pgBackUp(String host, String puerto, String usuario, String clave, String bDatos, String format, String path) {
	  try { /*
	          C:/Program Files/PostgreSQL/9.1/bin\pg_dump.exe 
	          --host localhost --port 5432 --username "postgres" --role "postgres"  
	          --format custom --blobs --oids --inserts --column-inserts --no-privileges --no-tablespaces 
	          --use-set-session-authorization --disable-dollar-quoting --verbose --quote-all-identifiers 
	          --no-unlogged-table-data --file "path" "postgres" */
	   if(!format.equalsIgnoreCase("")) {
	    pb = new ProcessBuilder("C:/Program Files (x86)/PostgreSQL/9.0/bin\\pg_dump.exe", "--verbose", "--format", format, "-f", path);
	   } else {
	    pb = new ProcessBuilder("C:/Program Files (x86)/PostgreSQL/9.0/bin\\pg_dump.exe", "--verbose", "--inserts", "--column-inserts", "-f", path);
	   }
	         pb.environment().put("PGHOST", host);
	         pb.environment().put("PGPORT", puerto);
	         pb.environment().put("PGUSER", usuario);
	         pb.environment().put("PGPASSWORD", clave);
	         pb.environment().put("PGDATABASE", bDatos);
	         pb.redirectErrorStream(true);
	         p = pb.start();
	         
	         escribirProcess(p);
	         System.out.print("terminado backup "+path+"\n");
	     } catch (Exception e) {
	      System.out.print("backup \n"+e.getMessage()+"\n");
	     }
	 }
	 
	
	 static void escribirProcess(Process process) throws Exception{
	  BufferedInputStream bufferIs = new BufferedInputStream(process.getInputStream());
	        InputStreamReader isReader = new InputStreamReader( bufferIs );
	        BufferedReader reader = new BufferedReader(isReader);
	        String line = ""; progreso.setText(line);
	        while (true){
	         line = reader.readLine();
	            if (line == null) break;
	            progreso.setText(progreso.getText()+"\n"+line);  
	        }  
	    }
	 
	 
	 public void idioma(){
		 if(Idioma.getInstance().getIdioma().equalsIgnoreCase("ingles")){
			 this.setTitle("Make Backup of the Database");
			 
		 }
		 
	 }
	 
	}  //  @jve:decl-index=0:visual-constraint="57,-4"




