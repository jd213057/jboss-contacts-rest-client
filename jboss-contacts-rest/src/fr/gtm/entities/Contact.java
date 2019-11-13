package fr.gtm.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "personnes")
@NamedQueries({
	@NamedQuery(name = "Contact.getByNom",query = "SELECT c FROM Contact c WHERE c.nom LIKE :nom"),
	@NamedQuery(name = "Contact.getWithAddress",query = "SELECT c.adresses From Contact c WHERE c LIKE :c"),
	@NamedQuery(name = "Contact.getAllContacts", query = "SELECT c FROM Contact c"), 
})
public class Contact implements Serializable {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk")
	private long id;
	@Column(length =5)
	@Enumerated(EnumType.STRING)
	private Civilite civilite;
	private String nom;
	private String prenom;
//	@OneToOne(cascade = CascadeType.ALL)	//si adresse pas partagée par plusieurs contacts, je peux supprimer l'addresse si je supprime le contact
//	private Adresse adresse;
	
	
	@OneToMany(fetch=FetchType.LAZY)
	@ElementCollection
	@JoinTable(name = "contacts_adresses",
			joinColumns=@JoinColumn(name= "fk_personne"),
			inverseJoinColumns=@JoinColumn(name= "fk_adresse"))
	public List<Adresse> adresses = new ArrayList<>();	//création d'une liste d'adresses, car on part du principe  que comme on est en @OneToMany, 1 contact peut avoir plusieurs adresses.
//	private Contact contact;
	
//	@ElementCollection(fetch = FetchType.LAZY)
//	@CollectionTable(name = "emails",joinColumns = @JoinColumn(name="fk_contact"))
//	@Column(name="email")
////	private List<String> emails = new ArrayList<>();
	
	public Contact() {}
	
	public Contact(Civilite civilite, String nom, String prenom) {
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	
public Contact(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

public Contact(Civilite civilite, String nom, String prenom, List<Adresse> adresses) {
		super();
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.adresses = adresses;
	}



public Contact(long id, Civilite civilite, String nom, String prenom, List<Adresse> adresses) {
	super();
	this.id = id;
	this.civilite = civilite;
	this.nom = nom;
	this.prenom = prenom;
	this.adresses = adresses;
}

//	public void addEmail(String email) {
//		emails.add(email);
//	}
	
//	public void addEmails(String... emails) {
//		for(String email: emails) {
//			this.emails.add(email);
//		}
	
	
	public void addAdresse(Adresse adresse) {
		adresses.add(adresse);
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

	@Override
	public String toString() {
		return "Contact [id=" + id + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

//	public List<String> getEmails() {
//		return emails;
//	}

//	public void setEmails(List<String> emails) {
//		this.emails = emails;
//	}
	
	public List<Adresse> getAdresses() {
		return adresses;
	}
	
	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

//	public Adresse getAdresse() {
//		return adresse;
//	}
//
//	public void setAdresse(Adresse adresse) {
//		this.adresse = adresse;
//	}

	
//	public Contact getContact() {
//		return contact;
//	}
//
//	public void setContact(Contact contact) {
//		this.contact = contact;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((civilite == null) ? 0 : civilite.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (civilite != other.civilite)
			return false;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}
	
	
	
}
