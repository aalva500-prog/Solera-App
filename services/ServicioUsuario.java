package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import model.Usuario;
import Utils.ConnectionBD;
import Utils.Encriptar;

public class ServicioUsuario {

	public static String getLoginUsuario(String usuario, char[] passw){
		String rol = "";
		try {
			String sqlSentenc = "SELECT \"public\".\"Rol\".\"descripcion\" " +
			"FROM  \"public\".\"Usuario\",\"public\".\"Rol\"  " +
			"WHERE  \"public\".\"Usuario\".\"rol\" = \"public\".\"Rol\".\"rol\" and \"public\".\"Usuario\".\"username\" = ? and \"public\".\"Usuario\".\"password\" = ? "; 
			PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
			prepararCons.setString(1, usuario); 
			String passs = new String(passw);
			String pass = Encriptar.getMd5(passs);
			prepararCons.setString(2, pass);
			prepararCons.execute();
			ResultSet resultado = prepararCons.getResultSet();
			
			while (resultado.next()) {
				rol = resultado.getString(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		return rol;
	}
	
	public static LinkedList<Usuario> getUsuarios() throws SQLException, ClassNotFoundException{
		LinkedList<Usuario> listUsuarios = new LinkedList<Usuario>();
		Statement consulta = ConnectionBD.connect.getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sqlSentenc = "SELECT \"public\".\"Usuario\".\"username\",\"public\".\"Usuario\".\"nombre\",\"public\".\"Rol\".\"descripcion\"" +
				"FROM  \"public\".\"Usuario\",\"public\".\"Rol\"" +
				"WHERE  \"public\".\"Usuario\".\"rol\" = \"public\".\"Rol\".\"rol\" ";
		ResultSet resultado = consulta.executeQuery(sqlSentenc);
		while (resultado.next()) {
			Usuario user = new Usuario();
			user.setUsername(resultado.getString(1));
			user.setNombre(resultado.getString(2));
			user.setRol(resultado.getString(3));
			listUsuarios.add(user);
		}
		return listUsuarios;
	}
	
	public static void insertarUsuario(String username, char[] passw, String nombre, String rol) throws SQLException, ClassNotFoundException{
		String pass = new String(passw);
		String sqlSentenc = "SELECT\"public\".\"InsertarUsuario\" (?,?,?,?)"; /*Los símbolos ? indican los parámetros que se van a pasar */
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setString(1, username); /* estamos dándole valor al primer parametro que se pasa, es decir al primer ? que aparezca. */
		prepararCons.setString(2, Encriptar.getMd5(pass));
		prepararCons.setString(3, nombre);
		prepararCons.setString(4, rol);
     	prepararCons.execute();
	}
	

public static void  EliminarUsuario(String userName) throws SQLException, ClassNotFoundException { 
		
		String sqlSentenc = "SELECT \"public\".\"EliminarUsuario\"(?)" ; 
		PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
		prepararCons.setString(1,userName ); 
		prepararCons.execute();
		
	}

public static void  ModificarUsuario(String user,String rol,String nomb,char[] passw) throws SQLException, ClassNotFoundException { 
	String pass = new String(passw);
	String sqlSentenc = "SELECT \"public\".\"ActualizarUsuario\"(?,?,?,?)"; 
	PreparedStatement prepararCons = ConnectionBD.connect.getConnection().prepareStatement(sqlSentenc);
	prepararCons.setString(1,user); 
	prepararCons.setString(2,rol); 
	prepararCons.setString(3,nomb);
	prepararCons.setString(4, Encriptar.getMd5(pass));
	prepararCons.execute();
	
}
}
