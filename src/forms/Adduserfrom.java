package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;
import dao.UtilisateurDao;

public class Adduserfrom {
	
	private static final String NOM = "nom";
	private static final String PRENOM = "prenom";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String PASSWORD_CONF = "passwordConf";
	private static final String ECHEC_AJOUT_MESSAGE = "Echec de l'ajout";
	private static final String SUCCESS_AJOUT_MESSAGE = "Ajout effectué avec succès";
	private static final String EMPTY_FIELD_ERROR_MESSSAGE = "Vous devez renseigner ce champ";
	private static final String PASSWORD_NOT_MATCHING = "Les mot de passes ne sont pas conformes";
	
	
	private HttpServletRequest request;
	private Map<String, String> errors;
	private boolean status;
	private String statusMessage;
	private Utilisateur utilisateur;
	
	
	public Adduserfrom(HttpServletRequest request) {
		this.request = request;
		this.status = false;
		this.statusMessage = ECHEC_AJOUT_MESSAGE;
		this.errors = new HashMap<String, String>();
	}

	public boolean ajouter() {
		
		String nom = this.getParameter(NOM);
		String prenom = this.getParameter(PRENOM);
		String login = this.getParameter(LOGIN);
		String password = this.getParameter(PASSWORD);
		this.utilisateur = new Utilisateur(nom,prenom,login,password);
		this.validerChamps(NOM, PRENOM, LOGIN, PASSWORD, PASSWORD_CONF);
		this.validerPassword();
		
		if(this.errors.isEmpty()) {
			 UtilisateurDao.add(utilisateur);
			 this.status = true;
			 this.statusMessage = SUCCESS_AJOUT_MESSAGE;
		}
		
		return status;
		// UtilisateurDao.ajouter(u);
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public boolean isStatus() {
		return status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public String getParameter(String parametre) {
		String valeur = this.request.getParameter(parametre);
		return (valeur == null || valeur.trim().isEmpty()) ? null : valeur.trim();
	}
	
	private void validerChamps(String...champs) {
		for(String champ : champs){
			if(this.getParameter(champ) == null) {
				errors.put(champ, EMPTY_FIELD_ERROR_MESSSAGE);
			}
		}
	}
	
	private void validerPassword() {
		String password = this.getParameter(PASSWORD);
		String passwordconf = this.getParameter(PASSWORD_CONF);
		if(password != null && !password.equals(passwordconf)) {
			errors.put(PASSWORD, PASSWORD_NOT_MATCHING);
			errors.put(PASSWORD_CONF, PASSWORD_NOT_MATCHING);
		}
	}
}