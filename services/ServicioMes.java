package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import model.Mes;
import Utils.ConnectionBD;

public class ServicioMes {
	
	public static LinkedList<Mes> getRoles() throws SQLException, ClassNotFoundException{

		LinkedList<Mes> listRol = new LinkedList<Mes>();
		Statement consulta = ConnectionBD.connect.getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sqlSentenc = "SELECT \"public\".\"Mes\".\"id_mes\",\"public\".\"Mes\".\"descrpcion\" " +
		"FROM  \"public\".\"Mes\"";
		ResultSet resultado = consulta.executeQuery(sqlSentenc);
		while (resultado.next()) {
			Mes rol = new Mes();
			rol.setID(resultado.getInt(1));
			rol.setDescripcion(resultado.getString(2));
			listRol.add(rol);
		}
		return listRol;
	}

}
