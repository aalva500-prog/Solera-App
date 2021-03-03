package services;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import model.Rol;
import Utils.ConnectionBD;




public class ServicioRol {

	public static LinkedList<Rol> getRoles() throws SQLException, ClassNotFoundException{

		LinkedList<Rol> listRol = new LinkedList<Rol>();
		Statement consulta = ConnectionBD.connect.getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sqlSentenc = "SELECT \"public\".\"Rol\".\"rol\",\"public\".\"Rol\".\"descripcion\" " +
		"FROM  \"public\".\"Rol\"";
		ResultSet resultado = consulta.executeQuery(sqlSentenc);
		while (resultado.next()) {
			Rol rol = new Rol();
			rol.setRol(resultado.getString(1));
			rol.setDescripcion(resultado.getString(2));
			listRol.add(rol);
		}
		return listRol;
	}
}
