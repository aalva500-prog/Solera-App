package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import model.DetalleMateria;
import Utils.ConnectionBD;

public class ServicioDetalleMateria {
	

	
	public static void insertarMateria(String secc,String ubi,Integer catn1,Integer cant2, float vol,Integer mat) throws SQLException, ClassNotFoundException{
		String sqlSentenc = "SELECT\"public\".\"InsertarDetalleMateria\" (?,?,?,?,?,?)"; /*Los símbolos ? indican los parámetros que se van a pasar */
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setString(1, secc);
		prepararCons.setString(2, ubi);
		prepararCons.setInt(3, catn1);
		prepararCons.setInt(4, cant2);
		prepararCons.setFloat(5, vol);
		prepararCons.setInt(6, mat);
		prepararCons.execute();
	}
	
	public static LinkedList<DetalleMateria> getMediciones (Integer tonel){
		LinkedList<DetalleMateria> mediciones = new LinkedList<DetalleMateria>();
		String sentence = " SELECT DISTINCT \"public\".\"DetalleMateria\".\"id_detallemateria\",\"public\".\"DetalleMateria\".\"seccion\",\"public\".\"DetalleMateria\".\"ubicacion\",\"public\".\"DetalleMateria\".\"tamano\",\"public\".\"DetalleMateria\".\"tonel\",\"public\".\"DetalleMateria\".\"volumen\"" +
				"FROM  \"public\".\"DetalleMateria\" WHERE \"public\".\"DetalleMateria\".\"id_materiaprima\"=?" +
				"ORDER BY \"public\".\"DetalleMateria\".\"seccion\" ASC";
		try {
			PreparedStatement stat = ConnectionBD.connect.getConnection().prepareStatement(sentence);
			stat.setInt(1, tonel);
			stat.execute();
			ResultSet resultado = stat.getResultSet();
			while (resultado.next()) {
				DetalleMateria a = new DetalleMateria();
				a.setId_detalle(resultado.getInt(1));
				a.setSeccion(resultado.getString(2));	
				a.setUbicacion(resultado.getString(3));
				a.setTamano(resultado.getInt(4));
				a.setTonel(resultado.getInt(5));
				a.setVolumen(resultado.getFloat(6));
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
	
	
	public static void  EliminarDetalleMateria(Integer cod) throws SQLException, ClassNotFoundException { 		
		String sqlSentenc = "SELECT \"public\".\"EliminarDetalleMateria\"(?)" ; 
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setInt(1,cod ); 
		prepararCons.execute();		
	}
	
	public static void  ActualizarMedicion(String seccion,String ubicacion,Integer tam,Integer cant, float vol,Integer id) throws SQLException, ClassNotFoundException { 
		String sqlSentenc = "SELECT \"public\".\"ModificarDetalle\"(?,?,?,?,?,?)"; 
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setString(1,seccion); 
		prepararCons.setString(2, ubicacion);
		prepararCons.setInt(3, tam);
		prepararCons.setInt(4, cant);
		prepararCons.setFloat(5, vol);
		prepararCons.setInt(6, id);
		prepararCons.execute();
		
	}
	
}
