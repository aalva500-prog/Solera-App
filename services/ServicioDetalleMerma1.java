package services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import model.DetalleMerma;
import model.DetalleMerma1;
import Utils.ConnectionBD;

public class ServicioDetalleMerma1 {
	
	
	public static LinkedList<DetalleMerma1> getMermas (){
		LinkedList<DetalleMerma1> mediciones = new LinkedList<DetalleMerma1>();
		String sentence = " SELECT DISTINCT \"public\".\"DetalleMerma1\".\"id_detallemerma\",\"public\".\"DetalleMerma1\".\"anejo\"," +
				"\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"DetalleMerma1\".\"canttoneles\"," +
				"\"public\".\"DetalleMerma1\".\"tamano\",\"public\".\"DetalleMerma1\".\"totalinicial\",\"public\".\"DetalleMerma1\".\"existencia\"," +
				"\"public\".\"DetalleMerma1\".\"perdida\",\"public\".\"Mes\".\"descrpcion\",\"public\".\"DetalleMerma1\".\"fecha\"" +
				"FROM  \"public\".\"DetalleMerma1\",\"public\".\"MateriaPrima\",\"public\".\"Mes\" " +
				"WHERE \"public\".\"MateriaPrima\".\"id_materia\"=\"public\".\"DetalleMerma1\".\"id_materia\"" +
				" and \"public\".\"DetalleMerma1\".\"mes\"=\"public\".\"Mes\".\"id_mes\"" +
				"GROUP BY \"public\".\"DetalleMerma1\".\"id_detallemerma\"," +
				"\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"DetalleMerma1\".\"anejo\",\"public\".\"DetalleMerma1\".\"canttoneles\"," +
				"\"public\".\"DetalleMerma1\".\"tamano\",\"public\".\"DetalleMerma1\".\"totalinicial\",\"public\".\"DetalleMerma1\".\"existencia\"," +
				"\"public\".\"DetalleMerma1\".\"perdida\",\"public\".\"Mes\".\"descrpcion\",\"public\".\"DetalleMerma1\".\"fecha\"" +				
				"ORDER BY \"public\".\"DetalleMerma1\".\"fecha\",\"public\".\"Mes\".\"descrpcion\" DESC";
		try {
			PreparedStatement stat = ConnectionBD.connect.getConnection().prepareStatement(sentence);
			stat.execute();
			ResultSet resultado = stat.getResultSet();
			while (resultado.next()) {
				DetalleMerma1 a = new DetalleMerma1();
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

	public static LinkedList<DetalleMerma1> getMermasAño (int mes,float año,int tipo){
		LinkedList<DetalleMerma1> mediciones = new LinkedList<DetalleMerma1>();
		String sentence = " SELECT DISTINCT \"public\".\"DetalleMerma1\".\"id_detallemerma\",\"public\".\"DetalleMerma1\".\"anejo\"," +
				"\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"DetalleMerma1\".\"canttoneles\"," +
				"\"public\".\"DetalleMerma1\".\"tamano\",\"public\".\"DetalleMerma1\".\"totalinicial\",\"public\".\"DetalleMerma1\".\"existencia\"," +
				"\"public\".\"DetalleMerma1\".\"perdida\",\"public\".\"Mes\".\"descrpcion\",\"public\".\"DetalleMerma1\".\"fecha\"" +
				"FROM  \"public\".\"DetalleMerma1\",\"public\".\"MateriaPrima\",\"public\".\"Mes\" " +
				"WHERE \"public\".\"MateriaPrima\".\"id_materia\"=\"public\".\"DetalleMerma1\".\"id_materia\"" +
				" and \"public\".\"DetalleMerma1\".\"mes\"=\"public\".\"Mes\".\"id_mes\" and \"public\".\"DetalleMerma1\".\"mes\"=? and \"date_part\"('year',\"DetalleMerma1\".\"fecha\")=?"
				+ "and \"public\".\"MateriaPrima\".\"tipomateria\" = ? " +
				"GROUP BY \"public\".\"DetalleMerma1\".\"id_detallemerma\"," +
				"\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"DetalleMerma1\".\"anejo\",\"public\".\"DetalleMerma1\".\"canttoneles\"," +
				"\"public\".\"DetalleMerma1\".\"tamano\",\"public\".\"DetalleMerma1\".\"totalinicial\",\"public\".\"DetalleMerma1\".\"existencia\"," +
				"\"public\".\"DetalleMerma1\".\"perdida\",\"public\".\"Mes\".\"descrpcion\",\"public\".\"DetalleMerma1\".\"fecha\"" +				
				"ORDER BY \"public\".\"DetalleMerma1\".\"fecha\",\"public\".\"Mes\".\"descrpcion\" DESC";
		try {
			PreparedStatement stat = ConnectionBD.connect.getConnection().prepareStatement(sentence);
			stat.setInt(1, mes);
			stat.setFloat(2, año);
			stat.setInt(3, tipo);
			stat.execute();
			ResultSet resultado = stat.getResultSet();
			while (resultado.next()) {
				DetalleMerma1 a = new DetalleMerma1();
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
	
	
	public static LinkedList<DetalleMerma1> getMermasALL (int mes,float año){
		LinkedList<DetalleMerma1> mediciones = new LinkedList<DetalleMerma1>();
		String sentence = " SELECT DISTINCT \"public\".\"DetalleMerma1\".\"id_detallemerma\",\"public\".\"DetalleMerma1\".\"anejo\"," +
				"\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"DetalleMerma1\".\"canttoneles\"," +
				"\"public\".\"DetalleMerma1\".\"tamano\",\"public\".\"DetalleMerma1\".\"totalinicial\",\"public\".\"DetalleMerma1\".\"existencia\"," +
				"\"public\".\"DetalleMerma1\".\"perdida\",\"public\".\"Mes\".\"descrpcion\",\"public\".\"DetalleMerma1\".\"fecha\"" +
				"FROM  \"public\".\"DetalleMerma1\",\"public\".\"MateriaPrima\",\"public\".\"Mes\" " +
				"WHERE \"public\".\"MateriaPrima\".\"id_materia\"=\"public\".\"DetalleMerma1\".\"id_materia\"" +
				" and \"public\".\"DetalleMerma1\".\"mes\"=\"public\".\"Mes\".\"id_mes\" and \"public\".\"DetalleMerma1\".\"mes\"=? and \"date_part\"('year',\"DetalleMerma1\".\"fecha\")=?"+
				"GROUP BY \"public\".\"DetalleMerma1\".\"id_detallemerma\"," +
				"\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"DetalleMerma1\".\"anejo\",\"public\".\"DetalleMerma1\".\"canttoneles\"," +
				"\"public\".\"DetalleMerma1\".\"tamano\",\"public\".\"DetalleMerma1\".\"totalinicial\",\"public\".\"DetalleMerma1\".\"existencia\"," +
				"\"public\".\"DetalleMerma1\".\"perdida\",\"public\".\"Mes\".\"descrpcion\",\"public\".\"DetalleMerma1\".\"fecha\"" +				
				"ORDER BY \"public\".\"DetalleMerma1\".\"fecha\",\"public\".\"Mes\".\"descrpcion\" DESC";
		try {
			PreparedStatement stat = ConnectionBD.connect.getConnection().prepareStatement(sentence);
			stat.setInt(1, mes);
			stat.setFloat(2, año);
			stat.execute();
			ResultSet resultado = stat.getResultSet();
			while (resultado.next()) {
				DetalleMerma1 a = new DetalleMerma1();
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
		String sqlSentenc = "SELECT\"public\".\"InsertarDetalleMerma1\" (?,?,?,?,?,?,?,?,?)"; /*Los símbolos ? indican los parámetros que se van a pasar */
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
		String sqlSentenc = "SELECT \"public\".\"EliminarDetalleMerma1\"(?)" ; 
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setInt(1,cod ); 
		prepararCons.execute();		
	}
	
	public static void  ActualizarMedicion(Integer id,Integer anejo,Integer materia,Integer cant,Integer tamano, Integer total,float perdida, Integer exist,Date ano,Integer mes) throws SQLException, ClassNotFoundException { 
		String sqlSentenc = "SELECT \"public\".\"ModificarDetalleMerma1\"(?,?,?,?,?,?,?,?,?,?)"; 
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
