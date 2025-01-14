package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	
	private List<Corso> corsi =null;
	
	public List<Corso> getElencoCorsi2(){
		if(this.corsi==null) {
			CorsoDAO dao = new CorsoDAO();
			this.corsi=dao.getElencoCorsi();
		}
		return corsi;
	}
	
	
	public Studente getDatiCompletamento2(int matricola) {
		StudenteDAO dao = new StudenteDAO();
		return dao.getDatiCompletamento(matricola);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso2(Corso corso) {
		CorsoDAO dao = new CorsoDAO();
		return dao.getStudentiIscrittiAlCorso(corso);
	}
	
	public boolean esisteStudente2(int matricola) {
		StudenteDAO dao = new StudenteDAO();
		return dao.esisteStudente(matricola);
	}
	
	public List<Corso> getCorsiDelloStudente2(int matricola){
		CorsoDAO dao = new CorsoDAO();
		return dao.getCorsiDelloStudente(matricola);
	}
	
	public boolean controllaIscrizioneStudenteCorso2(int matricola, Corso corso) {
		CorsoDAO dao = new CorsoDAO();
		return dao.controllaIscrizioneStudenteCorso(matricola, corso);
	}
	
	public boolean inscriviStudenteACorso2(int matricola, Corso corso) {
		CorsoDAO dao = new CorsoDAO();
		return dao.inscriviStudenteACorso(matricola, corso);
	}

}
