/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model ;
	private List<Corso> el;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxCorsi"
    private ComboBox<Corso> boxCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void handleCercaCorsi(ActionEvent event) {
    	try {
    		int matr = Integer.parseInt(this.txtMatricola.getText()) ;
        	if(!model.esisteStudente2(matr)) {
        		this.txtRisultato.setText("ERRORE: studente inesistente \n");
        		return;
        	}
        	
        	List<Corso> corsi = model.getCorsiDelloStudente2(matr);
        	if(corsi.size()==0) {
        		this.txtRisultato.appendText("Studente non iscritto a nessun corso \n");
        		return;
        	}else {
        		for(Corso c: corsi) {
        			this.txtRisultato.appendText("Corso "+c.getNome()+" con codice "+c.getCodins()+", crediti "+c.getCrediti()+" e del pd "+c.getPd()+"\n");
        		}
        	}
        	
    	}catch (NumberFormatException nfe) {
    		this.txtRisultato.setText("ERRORE: la matricola è composta da soli numeri \n");
    		return;
    	}
    	

    }

    @FXML
    void handleCercaIscrittiCorso(ActionEvent event) {
    	try {
	    	Corso c = this.boxCorsi.getValue();
	    	List<Studente> ris = model.getStudentiIscrittiAlCorso2(c);
	    	if(c.getNome().equals("")) {
	    		this.txtRisultato.appendText("ELENCO DI TUTTE LE ISCRIZIONI A TUTTI I CORSI \n");
	    	}
		    for(Studente s: ris) {
		    	this.txtRisultato.appendText(s.toString()+"\n");;
		    }
    	}catch (NullPointerException npe){
    		this.txtRisultato.setText("ERRORE: devi selezionare un corso \n");
    		return;
    	}
    }

    @FXML
    void handleCercaSeIscritto(ActionEvent event) {
    	
    	try {
    		Corso c = this.boxCorsi.getValue();
        	int matr = Integer.parseInt(this.txtMatricola.getText()) ;
    		
    		if(c.getNome().equals("")) {
        		this.txtRisultato.setText("ERRORE:Devi selezionare un corso specifico \n");          
        		return;
        	}
    		
    		if(!model.esisteStudente2(matr)) {
        		this.txtRisultato.setText("ERRORE: studente inesistente \n");
        		return;
        	}
    		
    		if(model.controllaIscrizioneStudenteCorso2(matr, c)) {
    			this.txtRisultato.appendText("Lo studente con matricola "+matr+ " è iscritto al corso "+c.getNome()+"("+c.getCodins()+")"+"\n");
    		}else {
    			this.txtRisultato.appendText("Lo studente con matricola "+matr+ " non è iscritto al corso "+c.getNome()+"("+c.getCodins()+")"+"\n");
    		}
    		
    	}catch (NullPointerException npe){
    		this.txtRisultato.setText("ERRORE: devi selezionare un corso \n");
    		return;
    	}catch (NumberFormatException nfe) {
    		this.txtRisultato.setText("ERRORE: la matricola è composta da soli numeri \n");
    		return;
    	}
    	
    	

    }

    @FXML
    void handleCompleta(ActionEvent event){
    	try {
    		int matr = Integer.parseInt(this.txtMatricola.getText()) ;
        	Studente st = model.getDatiCompletamento2(matr);
        	
        	if(st==null) {
        		this.txtRisultato.appendText("Studente inesistente \n");
        	}else {
        		this.txtNome.setText(st.getNome());
            	this.txtCognome.setText(st.getCognome());	
        	}
        	
    	}catch (NumberFormatException nfe) {
    		this.txtRisultato.setText("ERRORE: la matricola è composta da soli numeri \n");
    		return;
    	}
    	
    }

    @FXML
    void handleIscrivi(ActionEvent event) {

    }

    @FXML
    void handleReset(ActionEvent event) {
    	this.txtCognome.clear();
    	this.txtNome.clear();
    	this.txtMatricola.clear();
    	this.txtRisultato.clear();
    }
    
    public void setModel(Model m) {
    	this.model = m ;
    	 el = this.model.getElencoCorsi2();
    	
    	this.boxCorsi.getItems().addAll(el);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
