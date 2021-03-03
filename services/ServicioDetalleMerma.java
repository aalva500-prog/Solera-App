package services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import model.DetalleMerma;
import model.extracciones;
import Utils.ConnectionBD;

public class ServicioDetalleMerma {
	
	
	public static LinkedList<DetalleMerma> getMermas (){
		LinkedList<DetalleMerma> mediciones = new LinkedList<DetalleMerma>();
		String sentence = " SELECT DISTINCT \"public\".\"DetalleMerma\".\"id_detallemerma\",\"public\".\"DetalleMerma\".\"anejo\"," +
				"\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"DetalleMerma\".\"canttoneles\"," +
				"\"public\".\"DetalleMerma\".\"tamano\",\"public\".\"DetalleMerma\".\"totalinicial\",\"public\".\"DetalleMerma\".\"existencia\"," +
				"\"public\".\"DetalleMerma\".\"perdida\",\"public\".\"Mes\".\"descrpcion\",\"public\".\"DetalleMerma\".\"fecha\"" +
				"FROM  \"public\".\"DetalleMerma\",\"public\".\"MateriaPrima\",\"public\".\"Mes\" " +
				"WHERE \"public\".\"MateriaPrima\".\"id_materia\"=\"public\".\"DetalleMerma\".\"id_materia\"" +
				" and \"public\".\"DetalleMerma\".\"mes\"=\"public\".\"Mes\".\"id_mes\"" +
				"GROUP BY \"public\".\"DetalleMerma\".\"id_detallemerma\"," +
				"\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"DetalleMerma\".\"anejo\",\"public\".\"DetalleMerma\".\"canttoneles\"," +
				"\"public\".\"DetalleMerma\".\"tamano\",\"public\".\"DetalleMerma\".\"totalinicial\",\"public\".\"DetalleMerma\".\"existencia\"," +
				"\"public\".\"DetalleMerma\".\"perdida\",\"public\".\"Mes\".\"descrpcion\",\"public\".\"DetalleMerma\".\"fecha\"" +				
				"ORDER BY \"public\".\"DetalleMerma\".\"fecha\",\"public\".\"Mes\".\"descrpcion\" DESC";
		try {
			PreparedStatement stat = ConnectionBD.connect.getConnection().prepareStatement(sentence);
			stat.execute();
			ResultSet resultado = stat.getResultSet();
			while (resultado.next()) {
				DetalleMerma a = new DetalleMerma();
				a.setId_detallemerma(resultado.getInt(1));
				a.setAnejo(resultado.getInt(2));	
				a.setMateria(resultado.getString(3));
				a.setCanttoneles(resultado.getInt(4));
				a.setTamano(resultado.getInt(5));
				a.setTotal(resultado.getInt(6));
				a.setExistencia(resultado.getInt(7));
				a.setPerdida(resultado.getFloat(8));
				a.setMes(resultado.getString(9));
				a.setFecha(resultado.getDate(10));
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
	
	public static LinkedList<DetalleMerma> getMermasAño (int mes,float año,int tipo){
		LinkedList<DetalleMerma> mediciones = new LinkedList<DetalleMerma>();
		String sentence = " SELECT DISTINCT \"public\".\"DetalleMerma\".\"id_detallemerma\",\"public\".\"DetalleMerma\".\"anejo\"," +
				"\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"DetalleMerma\".\"canttoneles\"," +
				"\"public\".\"DetalleMerma\".\"tamano\",\"public\".\"DetalleMerma\".\"totalinicial\",\"public\".\"DetalleMerma\".\"existencia\"," +
				"\"public\".\"DetalleMerma\".\"perdida\",\"public\".\"Mes\".\"descrpcion\",\"public\".\"DetalleMerma\".\"fecha\"" +
				"FROM  \"public\".\"DetalleMerma\",\"public\".\"MateriaPrima\",\"public\".\"Mes\" " +
				"WHERE \"public\".\"MateriaPrima\".\"id_materia\"=\"public\".\"DetalleMerma\".\"id_materia\"" +
				" and \"public\".\"DetalleMerma\".\"mes\"=\"public\".\"Mes\".\"id_mes\" and \"public\".\"DetalleMerma\".\"mes\"=? and \"date_part\"('year',\"DetalleMerma\".\"fecha\")=?"
				+ "and \"public\".\"MateriaPrima\".\"tipomateria\" = ? " +
				"GROUP BY \"public\".\"DetalleMerma\".\"id_detallemerma\"," +
				"\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"DetalleMerma\".\"anejo\",\"public\".\"DetalleMerma\".\"canttoneles\"," +
				"\"public\".\"DetalleMerma\".\"tamano\",\"public\".\"DetalleMerma\".\"totalinicial\",\"public\".\"DetalleMerma\".\"existencia\"," +
				"\"public\".\"DetalleMerma\".\"perdida\",\"public\".\"Mes\".\"descrpcion\",\"public\".\"DetalleMerma\".\"fecha\"" +				
				"ORDER BY \"public\".\"DetalleMerma\".\"fecha\",\"public\".\"Mes\".\"descrpcion\" DESC";
		try {
			PreparedStatement stat = ConnectionBD.connect.getConnection().prepareStatement(sentence);
			stat.setInt(1, mes);
			stat.setFloat(2, año);
			stat.setInt(3, tipo);
			stat.execute();
			ResultSet resultado = stat.getResultSet();
			while (resultado.next()) {
				DetalleMerma a = new DetalleMerma();
				a.setId_detallemerma(resultado.getInt(1));
				a.setAnejo(resultado.getInt(2));	
				a.setMateria(resultado.getString(3));
				a.setCanttoneles(resultado.getInt(4));
				a.setTamano(resultado.getInt(5));
				a.setTotal(resultado.getInt(6));
				a.setExistencia(resultado.getInt(7));
				a.setPerdida(resultado.getFloat(8));
				a.setMes(resultado.getString(9));
				a.setFecha(resultado.getDate(10));
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
	
	public static LinkedList<DetalleMerma> getMermasALL (int mes,float año){
		LinkedList<DetalleMerma> mediciones = new LinkedList<DetalleMerma>();
		String sentence = " SELECT DISTINCT \"public\".\"DetalleMerma\".\"id_detallemerma\",\"public\".\"DetalleMerma\".\"anejo\"," +
				"\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"DetalleMerma\".\"canttoneles\"," +
				"\"public\".\"DetalleMerma\".\"tamano\",\"public\".\"DetalleMerma\".\"totalinicial\",\"public\".\"DetalleMerma\".\"existencia\"," +
				"\"public\".\"DetalleMerma\".\"perdida\",\"public\".\"Mes\".\"descrpcion\",\"public\".\"DetalleMerma\".\"fecha\"" +
				"FROM  \"public\".\"DetalleMerma\",\"public\".\"MateriaPrima\",\"public\".\"Mes\" " +
				"WHERE \"public\".\"MateriaPrima\".\"id_materia\"=\"public\".\"DetalleMerma\".\"id_materia\"" +
				" and \"public\".\"DetalleMerma\".\"mes\"=\"public\".\"Mes\".\"id_mes\" and \"public\".\"DetalleMerma\".\"mes\"=? and \"date_part\"('year',\"DetalleMerma\".\"fecha\")=?"+
				"GROUP BY \"public\".\"DetalleMerma\".\"id_detallemerma\"," +
				"\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"DetalleMerma\".\"anejo\",\"public\".\"DetalleMerma\".\"canttoneles\"," +
				"\"public\".\"DetalleMerma\".\"tamano\",\"public\".\"DetalleMerma\".\"totalinicial\",\"public\".\"DetalleMerma\".\"existencia\"," +
				"\"public\".\"DetalleMerma\".\"perdida\",\"public\".\"Mes\".\"descrpcion\",\"public\".\"DetalleMerma\".\"fecha\"" +				
				"ORDER BY \"public\".\"DetalleMerma\".\"fecha\",\"public\".\"Mes\".\"descrpcion\" DESC";
		try {
			PreparedStatement stat = ConnectionBD.connect.getConnection().prepareStatement(sentence);
			stat.setInt(1, mes);
			stat.setFloat(2, año);
			stat.execute();
			ResultSet resultado = stat.getResultSet();
			while (resultado.next()) {
				DetalleMerma a = new DetalleMerma();
				a.setId_detallemerma(resultado.getInt(1));
				a.setAnejo(resultado.getInt(2));	
				a.setMateria(resultado.getString(3));
				a.setCanttoneles(resultado.getInt(4));
				a.setTamano(resultado.getInt(5));
				a.setTotal(resultado.getInt(6));
				a.setExistencia(resultado.getInt(7));
				a.setPerdida(resultado.getFloat(8));
				a.setMes(resultado.getString(9));
				a.setFecha(resultado.getDate(10));
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

	
	public static void insertarDetalleMerma(Integer anejo,Integer materia,Integer cant,Integer tamano, Integer total,Integer exist, float perdida, Integer mes,Date ano) throws SQLException, ClassNotFoundException{
		String sqlSentenc = "SELECT\"public\".\"InsertarDetalleMerma\" (?,?,?,?,?,?,?,?,?)"; /*Los símbolos ? indican los parámetros que se van a pasar */
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setInt(1, anejo);
		prepararCons.setInt(2, materia);
		prepararCons.setInt(3, cant);
		prepararCons.setInt(4, tamano);
		prepararCons.setInt(5, total);
		prepararCons.setInt(6, exist);
		prepararCons.setFloat(7, perdida);
		prepararCons.setInt(8, mes);
		prepararCons.setDate(9, ano);
		prepararCons.execute();
	}
	
	public static void  EliminarDetalleMerma(Integer cod) throws SQLException, ClassNotFoundException { 		
		String sqlSentenc = "SELECT \"public\".\"EliminarDetalleMerma\"(?)" ; 
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setInt(1,cod ); 
		prepararCons.execute();		
	}
	
	public static void  ActualizarMedicion(Integer id,Integer anejo,Integer materia,Integer cant,Integer tamano, Integer total,float perdida, Integer exist,Date ano,Integer mes) throws SQLException, ClassNotFoundException { 
		String sqlSentenc = "SELECT \"public\".\"ModificarDetalleMerma\"(?,?,?,?,?,?,?,?,?,?)"; 
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setInt(1, id);
		prepararCons.setInt(2, anejo);
		prepararCons.setInt(3, materia);
		prepararCons.setInt(4, cant);
		prepararCons.setInt(5, tamano);
		prepararCons.setInt(6, total);
		prepararCons.setFloat(7, perdida);
		prepararCons.setInt(8, exist);
		prepararCons.setDate(9, ano);
		prepararCons.setInt(10, mes);		
		prepararCons.execute();
		
	}

}
