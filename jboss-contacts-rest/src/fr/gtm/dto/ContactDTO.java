package fr.gtm.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import fr.gtm.entities.Civilite;
import fr.gtm.entities.Contact;

public class ContactDTO implements Serializable {
	
	private long id;
	private Civilite civilite;
	private String nom;
	private String prenom;
	
	public ContactDTO() {
		
	}

	public ContactDTO(Contact contact) {
		this.id = contact.getId();
		this.civilite = contact.getCivilite();
		this.nom = contact.getNom();
		this.prenom = contact.getPrenom();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	

}
