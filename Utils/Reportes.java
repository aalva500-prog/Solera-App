package Utils;



import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.view.JasperViewer;

public class Reportes {

	
	
	private java.sql.Connection myConnection = null;
       static  Reportes report;
	
	public Reportes() {
		super();
		try {
			this.myConnection = ConnectionBD.connect.getConnection();
			Class.forName("org.postgresql.Driver");
			 myConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/soleranew", "postgres", "postgres");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public static Reportes getR()
	{
		if(report == null)
		{
			report = new Reportes();
		}
		return report;
			
	}
	
	//Reportes Mermas
	
	public void MermasAlcohol(){
		try {
			JasperFillManager.fillReportToFile("reportes/perdidasalcohol.jasper", null, myConnection);
			JasperViewer.viewReport("reportes/perdidasalcohol.jrprint", false,false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void MermasMalta(){
		try {
			JasperFillManager.fillReportToFile("reportes/perdidasmalta.jasper", null, myConnection);
			JasperViewer.viewReport("reportes/perdidasmalta.jrprint", false,false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void MermasRonMadre(){
		try {
			JasperFillManager.fillReportToFile("reportes/perdidaronmadre.jasper", null, myConnection);
			JasperViewer.viewReport("reportes/perdidaronmadre.jrprint", false,false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void MermasRonCarta(){
		try {
			JasperFillManager.fillReportToFile("reportes/perdidaroncarta.jasper", null, myConnection);
			JasperViewer.viewReport("reportes/perdidaroncarta.jrprint", false,false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Reportes Mermas por Año
	public void PerdidasAlcoholAno(float year){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("fech",year);
			JasperFillManager.fillReportToFile("reportes/perdidaalcoholano.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/perdidaalcoholano.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	
	public void PerdidasMaltaAno(float year){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("fecha",year);
			JasperFillManager.fillReportToFile("reportes/perdidamalta.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/perdidamalta.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	
	public void PerdidasRonMadreAno(float year){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("fech",year);
			JasperFillManager.fillReportToFile("reportes/perdidaronmadreano.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/perdidaronmadreano.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	public void PerdidasRonCartaAno(float year){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("fech",year);
			JasperFillManager.fillReportToFile("reportes/perdidaroncartaano.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/perdidaroncartaano.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	//Pérdidas de un Tonel
	public void PerdidasTOnel(Integer tonel){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("id_tonel", tonel);
			JasperFillManager.fillReportToFile("reportes/perdidasportoneles.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/perdidasportoneles.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	public void PerdidasTonelRonMadre(Integer tonel){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("id_tonel", tonel);
			JasperFillManager.fillReportToFile("reportes/mermasdeltonelMadre.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/mermasdeltonelMadre.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
		
	public void PerdidasTonelRonCarta(Integer tonel){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("id_tonel", tonel);
			JasperFillManager.fillReportToFile("reportes/peridastonelroncarta.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/peridastonelroncarta.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	

	public void PerdidasTonelMalta(Integer tonel){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("id_tonel", tonel);
			JasperFillManager.fillReportToFile("reportes/perdidastonelmalta.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/perdidastonelmalta.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	//Reportes Bajas de Toneles
	public void TonelesBaja(){
		try {
			JasperFillManager.fillReportToFile("reportes/tonelesbaja.jasper", null, myConnection);
			JasperViewer.viewReport("reportes/tonelesbaja.jrprint", false,false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void TonelesBajaPeriodo(float fecha){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("fechaF", fecha);
			JasperFillManager.fillReportToFile("reportes/tonelesbajaperiodo.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/tonelesbajaperiodo.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	public void TonelesBajaDia(Date dia){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("day", dia);
			JasperFillManager.fillReportToFile("reportes/tonelesbajadia.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/tonelesbajadia.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	//Reportes Mediciones 
	public void Mediciones(Integer tonel){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("id_tonel", tonel);
			JasperFillManager.fillReportToFile("reportes/mediciones.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/mediciones.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	//Reportes Toneles
	public void TonelesMateria(Integer mat){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("materia", mat);
			JasperFillManager.fillReportToFile("reportes/ListadoToneles.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/ListadoToneles.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	public void CantidadToneles(Integer mat){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("materia", mat);
			//JasperFillManager.fillReportToFile("reportes/cantidaddetoneles.jasper", mypara, myConnection);
			JasperRunManager.runReportToPdfFile("reportes/cantidaddetoneles.jasper", null, mypara, myConnection);
			JasperViewer.viewReport("reportes/cantidaddetoneles.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	public void TonelesPorAno(float ano){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("ano", ano);
			JasperFillManager.fillReportToFile("reportes/tonelesporano.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/tonelesporano.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	
	public void TonelesDia(Date day){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("fech", day);
			JasperFillManager.fillReportToFile("reportes/ltonelesdia.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/ltonelesdia.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}

	//Reportes de Movimientos en Toneles
	
	public void LlenadosTonel(Integer ton){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("tonel", ton);
			JasperFillManager.fillReportToFile("reportes/LlenadosTonel.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/LlenadosTonel.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	
	public void ExtraccionesTonel(Integer ton){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("tonel", ton);
			JasperFillManager.fillReportToFile("reportes/ExtraccionesTonel.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/ExtraccionesTonel.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	//Reporte Producto
	public void ReporteProducto(Integer ton){
		try {
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("id_materia", ton);
			JasperFillManager.fillReportToFile("reportes/ReporteProducto.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/ReporteProducto.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	//Reporte Estado Solera
	public void EstadoSolera(Integer materia, Integer mes,float ano){
		try {
			
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("materia", materia);
			mypara.put("mess", mes);	
			mypara.put("ano", ano);
			JasperFillManager.fillReportToFile("reportes/otroestado.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/otroestado.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	public void EstadoSolera1(Integer materia, Integer mes,float ano,int anejo){
		try {
			
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("materia", materia);
			mypara.put("mess", mes);	
			mypara.put("ano", ano);
			mypara.put("an", anejo);
			JasperFillManager.fillReportToFile("reportes/otroestado1.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/otroestado1.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	//reporte Extraccion
	public void ReporteExtraccion(Integer materia, Integer extraccion){
		try {
			
			HashMap<String, Object> mypara = new HashMap <String, Object> ();
			mypara.put("id_materia", materia);
			mypara.put("id_extraccion", extraccion);	
			JasperFillManager.fillReportToFile("reportes/Extraccion.jasper", mypara, myConnection);
			JasperViewer.viewReport("reportes/Extraccion.jrprint", false,false);
		} catch (JRException e2) {
						e2.printStackTrace();
		}
	}
	
	}
