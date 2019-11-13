package fr.gtm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import fr.gtm.entities.Adresse;
import fr.gtm.entities.Civilite;
import fr.gtm.entities.Contact;

@Singleton
public class ContactDAO {
	@PersistenceContext(name="contacts") private EntityManager em;
	private EntityManagerFactory emf;

	
	public void create(Contact contact) {
		em.persist(contact);
	}
	
	
	public Contact getContactById(long id) {
		Contact contact = em.find(Contact.class, id);
		return contact;
	}
	
	public void delete(Contact contact) {
		Contact c1 = em.find(Contact.class, contact.getId());
		em.remove(c1);

	}
	
	public void deleteById(long id) {
		Contact c1 = em.find(Contact.class, id);
		em.remove(c1);

	}
	
	public void update(Contact contact) {
		em.merge(contact);
	}
	
	
	public List<Contact> getContactsByCivilite(Civilite civilite){
		String sql = "SELECT c FROM Contact c WHERE c.civilite= :foo";
		List<Contact> contacts = em.createQuery(sql, Contact.class)
										.setParameter("foo", civilite)
										.getResultList();

		return contacts;
	}
	
	public List<Contact> getContactsByNom(String nom){
		List<Contact> contacts = em.createNamedQuery("Contact.getByNom", Contact.class)
										.setParameter("nom", nom+"%")
										.getResultList();

		return contacts;
	}
	
	public List<Contact> getAllContacts() {
		List<Contact> contacts = em.createNamedQuery("Contact.getAllContacts", Contact.class).getResultList();
		return contacts;
	}
	
	
	public List<Adresse> getAllAdresses() {
		List<Adresse> adresses = em.createNamedQuery("Adresse.getAllAdresses", Adresse.class).getResultList();
		return adresses;
	}
	public List<Adresse> getContactAdresses() {
		
		List<Adresse> adresses = em.createNamedQuery("Contact.getWithAddress", Adresse.class).getResultList();
		return adresses;
	}
	
	public void create(Adresse adresse) {
		em.persist(adresse);
	}
	public Adresse getAdresseById(long id) {
		Adresse adresse = em.find(Adresse.class, id);
		return adresse;
	}
	
	public void delete(Adresse adresse) {
		Adresse a1 = em.find(Adresse.class, adresse.getId());
		em.remove(a1);
	}
	
	public void update(Adresse adresse) {
		em.merge(adresse);
	}
	
	public List<Adresse> getAdresseByContactId(long id) {
		Contact contact = em.find(Contact.class, id);
		List<Adresse> adresses = new ArrayList<>();
		for (Adresse adresse : contact.getAdresses()) {
			adresses.add(adresse);
		}
		return adresses;
	}
	
	
	    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
