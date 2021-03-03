package services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import model.extracciones;
import Utils.ConnectionBD;

public class ServicioExtraccion {
	
	public static LinkedList<extracciones> getxtracciones (Integer materia){
		LinkedList<extracciones> mediciones = new LinkedList<extracciones>();
		String sentence = " SELECT DISTINCT \"public\".\"Extracciones\".\"id_extraccion\",\"public\".\"Extracciones\".\"inventario\",\"public\".\"Extracciones\".\"liqsacado\"," +
				"\"public\".\"Extracciones\".\"anejo\",\"public\".\"Extracciones\".\"perdida\",\"public\".\"Extracciones\".\"perdidamensual\"," +
				"\"public\".\"Extracciones\".\"perdidaanual\",\"public\".\"Extracciones\".\"perdidatotal\",\"public\".\"Extracciones\".\"observaciones\"," +
				"\"public\".\"Extracciones\".\"fecha\",\"public\".\"Extracciones\".\"grado\"" +
				"FROM  \"public\".\"Extracciones\" WHERE \"public\".\"Extracciones\".\"id_materia\"=?" +
				"ORDER BY \"public\".\"Extracciones\".\"fecha\" ASC";
		try {
			PreparedStatement stat = ConnectionBD.connect.getConnection().prepareStatement(sentence);
			stat.setInt(1, materia);
			stat.execute();
			ResultSet resultado = stat.getResultSet();
			while (resultado.next()) {
				extracciones a = new extracciones();
				a.setId_extracciones(resultado.getInt(1));
				a.setInventario(resultado.getInt(2));	
				a.setLiqsacado(resultado.getInt(3));
				a.setAnejo(resultado.getInt(4));
				a.setPerdida(resultado.getFloat(5));
				a.setPerdidamensual(resultado.getFloat(6));
				a.setPerdidaanual(resultado.getFloat(7));
				a.setPerdidatotal(resultado.getFloat(8));
				a.setObservaciones(resultado.getString(9));
				a.setFecha(resultado.getDate(10));
				a.setGrado(resultado.getFloat(11));
				mediciones.add(a);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mediciones;
	}
	
	public static void insertarExtracciones(int materia,int inv,int sac,int anejo, float perd,float perdmen,float perdan,float perdtotal,String ob,Date fech,float grad) throws SQLException, ClassNotFoundException{
		String sqlSentenc = "SELECT\"public\".\"InsertarExtracciones\" (?,?,?,?,?,?,?,?,?,?,?)"; /*Los símbolos ? indican los parámetros que se van a pasar */
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setInt(1, materia);
		prepararCons.setInt(2, inv);
		prepararCons.setInt(3, sac);
		prepararCons.setInt(4, anejo);
		prepararCons.setFloat(5, perd);
		prepararCons.setFloat(6, perdmen);
		prepararCons.setFloat(7, perdan);
		prepararCons.setFloat(8, perdtotal);
		prepararCons.setString(9, ob);
		prepararCons.setDate(10, fech);
		prepararCons.setFloat(11, grad);	
		prepararCons.execute();
	}
	
	public static void  EliminarExtraccion(Integer cod) throws SQLException, ClassNotFoundException { 		
		String sqlSentenc = "SELECT \"public\".\"EliminarExtracciones\"(?)" ; 
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setInt(1,cod ); 
		prepararCons.execute();		
	}

	public static void  ActualizarExtraccion(int materia,int inv,int sac,int anejo, float perd,float perdmen,float perdan,float perdtotal,String ob,Date fech,float grad) throws SQLException, ClassNotFoundException { 
		String sqlSentenc = "SELECT \"public\".\"ModificarExtracciones\"(?,?,?,?,?,?,?,?,?,?,?)"; 
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setInt(1, materia);
		prepararCons.setInt(2, inv);
		prepararCons.setInt(3, sac);
		prepararCons.setInt(4, anejo);
		prepararCons.setFloat(5, perd);
		prepararCons.setFloat(6, perdmen);
		prepararCons.setFloat(7, perdan);
		prepararCons.setFloat(8, perdtotal);
		prepararCons.setString(9, ob);
		prepararCons.setDate(10, fech);
		prepararCons.setFloat(11, grad);	
		prepararCons.execute();
		
	}
	
	
}
