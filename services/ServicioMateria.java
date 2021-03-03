package services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Utils.ConnectionBD;

import model.MateriaPrima;
import model.TipoMateria;

public class ServicioMateria {
	
	public static LinkedList<TipoMateria> getMaterias() throws SQLException, ClassNotFoundException{

		LinkedList<TipoMateria> listtipo = new LinkedList<TipoMateria>();
		Statement consulta = ConnectionBD.connect.getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sqlSentenc = "SELECT \"public\".\"TipoMateriaPrima\".\"id_tipomateria\",\"public\".\"TipoMateriaPrima\".\"descripcion\" " +
		"FROM  \"public\".\"TipoMateriaPrima\"" +
		"ORDER BY \"public\".\"TipoMateriaPrima\".\"id_tipomateria\" ASC";	
		ResultSet resultado = consulta.executeQuery(sqlSentenc);
		while (resultado.next()) {
			TipoMateria mp = new TipoMateria();
			mp.setId_tipomateria(resultado.getInt(1));
			mp.setDescripcion(resultado.getString(2));
			listtipo.add(mp);
		}
		return listtipo;
	}

	public static void insertarMateria(Date fech,float grad,String ob,Integer materia, String descr) throws SQLException, ClassNotFoundException{
		String sqlSentenc = "SELECT\"public\".\"InsertarMateria\" (?,?,?,?,?)"; /*Los símbolos ? indican los parámetros que se van a pasar */
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setDate(1, fech);
		prepararCons.setFloat(2, grad);
		prepararCons.setString(3, ob);
		prepararCons.setInt(4, materia);
		prepararCons.setString(5, descr);
		prepararCons.execute();
	}
	
	
	public static void  EliminarMateria(Integer cod) throws SQLException, ClassNotFoundException { 		
		String sqlSentenc = "SELECT \"public\".\"EliminarMateriaPrima\"(?)" ; 
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setInt(1,cod ); 
		prepararCons.execute();		
	}
	
	public static LinkedList<MateriaPrima> getMateriasPrimas() throws SQLException, ClassNotFoundException{

		LinkedList<MateriaPrima> listtipo = new LinkedList<MateriaPrima>();
		Statement consulta = ConnectionBD.connect.getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sqlSentenc = "SELECT \"public\".\"MateriaPrima\".\"id_materia\",\"public\".\"MateriaPrima\".\"fechallenado\",\"public\".\"MateriaPrima\".\"grado\",\"public\".\"MateriaPrima\".\"Observaciones\",\"public\".\"TipoMateriaPrima\".\"descripcion\",\"public\".\"MateriaPrima\".\"nombre\" " +
		"FROM  \"public\".\"MateriaPrima\",\"public\".\"TipoMateriaPrima\" WHERE \"public\".\"TipoMateriaPrima\".\"id_tipomateria\"=\"public\".\"MateriaPrima\".\"tipomateria\"" +
		"GROUP BY \"public\".\"MateriaPrima\".\"id_materia\",\"public\".\"TipoMateriaPrima\".\"descripcion\",\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"MateriaPrima\".\"fechallenado\",\"public\".\"MateriaPrima\".\"grado\",\"public\".\"MateriaPrima\".\"Observaciones\"" +
		"ORDER BY \"public\".\"TipoMateriaPrima\".\"descripcion\",\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"MateriaPrima\".\"fechallenado\" DESC";	
		ResultSet resultado = consulta.executeQuery(sqlSentenc);
		while (resultado.next()) {
			MateriaPrima mp = new MateriaPrima();
			mp.setId_materia(resultado.getInt(1));
			mp.setFechallenado(resultado.getDate(2));
			mp.setGrado(resultado.getFloat(3));
			mp.setObservaciones(resultado.getString(4));
			mp.setTipomateria(resultado.getString(5));
			mp.setDescripcion(resultado.getString(6));
			listtipo.add(mp);
		}
		return listtipo;
	}
	
	public static LinkedList<MateriaPrima> getMateriasPrimasAlcoholAñejado() throws SQLException, ClassNotFoundException{

		LinkedList<MateriaPrima> listtipo = new LinkedList<MateriaPrima>();
		Statement consulta = ConnectionBD.connect.getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sqlSentenc = "SELECT \"public\".\"MateriaPrima\".\"id_materia\",\"public\".\"MateriaPrima\".\"fechallenado\",\"public\".\"MateriaPrima\".\"grado\",\"public\".\"MateriaPrima\".\"Observaciones\",\"public\".\"TipoMateriaPrima\".\"descripcion\",\"public\".\"MateriaPrima\".\"nombre\" " +
		"FROM  \"public\".\"MateriaPrima\",\"public\".\"TipoMateriaPrima\" WHERE \"public\".\"TipoMateriaPrima\".\"id_tipomateria\"=\"public\".\"MateriaPrima\".\"tipomateria\""
		+ "and \"public\".\"TipoMateriaPrima\".\"id_tipomateria\"=1" +
		"GROUP BY \"public\".\"MateriaPrima\".\"id_materia\",\"public\".\"TipoMateriaPrima\".\"descripcion\",\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"MateriaPrima\".\"fechallenado\",\"public\".\"MateriaPrima\".\"grado\",\"public\".\"MateriaPrima\".\"Observaciones\"" +
		"ORDER BY \"public\".\"MateriaPrima\".\"nombre\",\"public\".\"MateriaPrima\".\"fechallenado\" DESC";	
		ResultSet resultado = consulta.executeQuery(sqlSentenc);
		while (resultado.next()) {
			MateriaPrima mp = new MateriaPrima();
			mp.setId_materia(resultado.getInt(1));
			mp.setFechallenado(resultado.getDate(2));
			mp.setGrado(resultado.getFloat(3));
			mp.setObservaciones(resultado.getString(4));
			mp.setTipomateria(resultado.getString(5));
			mp.setDescripcion(resultado.getString(6));
			listtipo.add(mp);
		}
		return listtipo;
	}
	
	public static LinkedList<MateriaPrima> getMateriasPrimasExtractoRoble() throws SQLException, ClassNotFoundException{

		LinkedList<MateriaPrima> listtipo = new LinkedList<MateriaPrima>();
		Statement consulta = ConnectionBD.connect.getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sqlSentenc = "SELECT \"public\".\"MateriaPrima\".\"id_materia\",\"public\".\"MateriaPrima\".\"fechallenado\",\"public\".\"MateriaPrima\".\"grado\",\"public\".\"MateriaPrima\".\"Observaciones\",\"public\".\"TipoMateriaPrima\".\"descripcion\",\"public\".\"MateriaPrima\".\"nombre\" " +
		"FROM  \"public\".\"MateriaPrima\",\"public\".\"TipoMateriaPrima\" WHERE \"public\".\"TipoMateriaPrima\".\"id_tipomateria\"=\"public\".\"MateriaPrima\".\"tipomateria\""
		+ "and \"public\".\"TipoMateriaPrima\".\"id_tipomateria\"=2" +
		"GROUP BY \"public\".\"MateriaPrima\".\"id_materia\",\"public\".\"TipoMateriaPrima\".\"descripcion\",\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"MateriaPrima\".\"fechallenado\",\"public\".\"MateriaPrima\".\"grado\",\"public\".\"MateriaPrima\".\"Observaciones\"" +
		"ORDER BY \"public\".\"MateriaPrima\".\"nombre\",\"public\".\"MateriaPrima\".\"fechallenado\" DESC";	
		ResultSet resultado = consulta.executeQuery(sqlSentenc);
		while (resultado.next()) {
			MateriaPrima mp = new MateriaPrima();
			mp.setId_materia(resultado.getInt(1));
			mp.setFechallenado(resultado.getDate(2));
			mp.setGrado(resultado.getFloat(3));
			mp.setObservaciones(resultado.getString(4));
			mp.setTipomateria(resultado.getString(5));
			mp.setDescripcion(resultado.getString(6));
			listtipo.add(mp);
		}
		return listtipo;
	}
	
	public static LinkedList<MateriaPrima> getMateriasPrimasMalta() throws SQLException, ClassNotFoundException{

		LinkedList<MateriaPrima> listtipo = new LinkedList<MateriaPrima>();
		Statement consulta = ConnectionBD.connect.getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sqlSentenc = "SELECT \"public\".\"MateriaPrima\".\"id_materia\",\"public\".\"MateriaPrima\".\"fechallenado\",\"public\".\"MateriaPrima\".\"grado\",\"public\".\"MateriaPrima\".\"Observaciones\",\"public\".\"TipoMateriaPrima\".\"descripcion\",\"public\".\"MateriaPrima\".\"nombre\" " +
		"FROM  \"public\".\"MateriaPrima\",\"public\".\"TipoMateriaPrima\" WHERE \"public\".\"TipoMateriaPrima\".\"id_tipomateria\"=\"public\".\"MateriaPrima\".\"tipomateria\""
		+ "and \"public\".\"TipoMateriaPrima\".\"id_tipomateria\"=3" +
		"GROUP BY \"public\".\"MateriaPrima\".\"id_materia\",\"public\".\"TipoMateriaPrima\".\"descripcion\",\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"MateriaPrima\".\"fechallenado\",\"public\".\"MateriaPrima\".\"grado\",\"public\".\"MateriaPrima\".\"Observaciones\"" +
		"ORDER BY \"public\".\"MateriaPrima\".\"nombre\",\"public\".\"MateriaPrima\".\"fechallenado\" DESC";	
		ResultSet resultado = consulta.executeQuery(sqlSentenc);
		while (resultado.next()) {
			MateriaPrima mp = new MateriaPrima();
			mp.setId_materia(resultado.getInt(1));
			mp.setFechallenado(resultado.getDate(2));
			mp.setGrado(resultado.getFloat(3));
			mp.setObservaciones(resultado.getString(4));
			mp.setTipomateria(resultado.getString(5));
			mp.setDescripcion(resultado.getString(6));
			listtipo.add(mp);
		}
		return listtipo;
	}
	
	
	public static LinkedList<MateriaPrima> getMateriasRCS() throws SQLException, ClassNotFoundException{

		LinkedList<MateriaPrima> listtipo = new LinkedList<MateriaPrima>();
		Statement consulta = ConnectionBD.connect.getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sqlSentenc = "SELECT \"public\".\"MateriaPrima\".\"id_materia\",\"public\".\"MateriaPrima\".\"fechallenado\",\"public\".\"MateriaPrima\".\"grado\",\"public\".\"MateriaPrima\".\"Observaciones\",\"public\".\"TipoMateriaPrima\".\"descripcion\",\"public\".\"MateriaPrima\".\"nombre\" " +
		"FROM  \"public\".\"MateriaPrima\",\"public\".\"TipoMateriaPrima\" WHERE \"public\".\"TipoMateriaPrima\".\"id_tipomateria\"=\"public\".\"MateriaPrima\".\"tipomateria\""
		+ "and \"public\".\"TipoMateriaPrima\".\"id_tipomateria\"=4" +
		"GROUP BY \"public\".\"MateriaPrima\".\"id_materia\",\"public\".\"TipoMateriaPrima\".\"descripcion\",\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"MateriaPrima\".\"fechallenado\",\"public\".\"MateriaPrima\".\"grado\",\"public\".\"MateriaPrima\".\"Observaciones\"" +
		"ORDER BY \"public\".\"MateriaPrima\".\"nombre\",\"public\".\"MateriaPrima\".\"fechallenado\" DESC";	
		ResultSet resultado = consulta.executeQuery(sqlSentenc);
		while (resultado.next()) {
			MateriaPrima mp = new MateriaPrima();
			mp.setId_materia(resultado.getInt(1));
			mp.setFechallenado(resultado.getDate(2));
			mp.setGrado(resultado.getFloat(3));
			mp.setObservaciones(resultado.getString(4));
			mp.setTipomateria(resultado.getString(5));
			mp.setDescripcion(resultado.getString(6));
			listtipo.add(mp);
		}
		return listtipo;
	}
	
	public static LinkedList<MateriaPrima> getMateriasRonMadre() throws SQLException, ClassNotFoundException{

		LinkedList<MateriaPrima> listtipo = new LinkedList<MateriaPrima>();
		Statement consulta = ConnectionBD.connect.getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sqlSentenc = "SELECT \"public\".\"MateriaPrima\".\"id_materia\",\"public\".\"MateriaPrima\".\"fechallenado\",\"public\".\"MateriaPrima\".\"grado\",\"public\".\"MateriaPrima\".\"Observaciones\",\"public\".\"TipoMateriaPrima\".\"descripcion\",\"public\".\"MateriaPrima\".\"nombre\" " +
		"FROM  \"public\".\"MateriaPrima\",\"public\".\"TipoMateriaPrima\" WHERE \"public\".\"TipoMateriaPrima\".\"id_tipomateria\"=\"public\".\"MateriaPrima\".\"tipomateria\""
		+ "and \"public\".\"TipoMateriaPrima\".\"id_tipomateria\"=5" +
		"GROUP BY \"public\".\"MateriaPrima\".\"id_materia\",\"public\".\"TipoMateriaPrima\".\"descripcion\",\"public\".\"MateriaPrima\".\"nombre\",\"public\".\"MateriaPrima\".\"fechallenado\",\"public\".\"MateriaPrima\".\"grado\",\"public\".\"MateriaPrima\".\"Observaciones\"" +
		"ORDER BY \"public\".\"MateriaPrima\".\"nombre\",\"public\".\"MateriaPrima\".\"fechallenado\" DESC";	
		ResultSet resultado = consulta.executeQuery(sqlSentenc);
		while (resultado.next()) {
			MateriaPrima mp = new MateriaPrima();
			mp.setId_materia(resultado.getInt(1));
			mp.setFechallenado(resultado.getDate(2));
			mp.setGrado(resultado.getFloat(3));
			mp.setObservaciones(resultado.getString(4));
			mp.setTipomateria(resultado.getString(5));
			mp.setDescripcion(resultado.getString(6));
			listtipo.add(mp);
		}
		return listtipo;
	}
	
	public static void  ActualizarMedicion(Date fecha,float grad,String ob,Integer materia, String descr,Integer id) throws SQLException, ClassNotFoundException { 
		String sqlSentenc = "SELECT \"public\".\"ModificarMateriaPrima\"(?,?,?,?,?,?)"; 
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setDate(1,fecha); 
		prepararCons.setFloat(2, grad);
		prepararCons.setString(3, ob);
		prepararCons.setInt(4, materia);
		prepararCons.setString(5, descr);
		prepararCons.setInt(6, id);
		prepararCons.execute();
		
	}

	
}
