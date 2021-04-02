package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}

			rs.close();
			st.close();
			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public List<Corso> getElencoCorsi(){
		final String sql = "SELECT * FROM corso";
		List<Corso> corsi = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();
			
			corsi.add(new Corso("", 0, "", 0));
			while(rs.next()) {
				corsi.add(new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd")));
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return corsi;
		}catch (SQLException e){
			throw new RuntimeException("Errore Db", e);
		}
		
			
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		try {
			List<Studente> ris = new ArrayList<>();
			final String sql;
			if(corso.getNome().equals("")) {
				sql ="SELECT s.matricola, s.nome, s.cognome, s.CDS "
						+ "FROM studente s, corso c, iscrizione i "
						+ "WHERE s.matricola=i.matricola AND i.codins=c.codins ";
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				
				ResultSet rs = st.executeQuery();

				while(rs.next()) {
					ris.add(new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS")));
				}
				
				rs.close();
				st.close();
				conn.close();
				
				return ris;
			}else {
				sql ="SELECT s.matricola, s.nome, s.cognome, s.CDS "
						+ "FROM studente s, corso c, iscrizione i "
						+ "WHERE s.matricola=i.matricola AND i.codins=c.codins AND c.nome = ? ";
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				
				st.setString(1, corso.getNome());
				ResultSet rs = st.executeQuery();

				while(rs.next()) {
					ris.add(new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS")));
				}
				
				rs.close();
				st.close();
				conn.close();
				
				return ris;
			}
			
			
			
		}catch(SQLException e){
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public List<Corso> getCorsiDelloStudente(int matricola){
		try {
			final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "
					+ "FROM studente s, corso c, iscrizione i "
					+ "WHERE s.matricola=i.matricola AND i.codins=c.codins AND s.matricola = ?";
			List<Corso> corsi = new ArrayList<>();
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, matricola); 
			
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				corsi.add(new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd")));
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return corsi;
			
		}catch (SQLException e){
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
