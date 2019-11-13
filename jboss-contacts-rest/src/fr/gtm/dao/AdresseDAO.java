package fr.gtm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import fr.gtm.entities.Adresse;
import fr.gtm.entities.Contact;

public class AdresseDAO extends AbstractDAO<fr.gtm.entities.Adresse, Long>{

	public AdresseDAO(EntityManagerFactory emf) {
		super(emf, Adresse.class);
	}
	public List<Adresse> getAllAdresses() {
		EntityManager em = emf.createEntityManager();
		List<Adresse> adresses = em.createNamedQuery("Adresse.getAllAdresses", Adresse.class).getResultList();
		em.close();
		return adresses;
	}
}
