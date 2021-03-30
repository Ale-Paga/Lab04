package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	
	
	public Studente getDatiCompletamento(int matricola) {
		try {
		
		final String sql = "SELECT * "
				+ "FROM studente "
				+ "WHERE matricola = ?";
		
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);

		st.setInt(1, matricola);
		
		ResultSet rs = st.executeQuery();
		rs.first();
		Studente ris = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS") );
		st.close();
		conn.close();
		return ris;
			
		}catch (SQLException e){
			throw new RuntimeException("Errore Db", e);
		}
		
		
	}
}
