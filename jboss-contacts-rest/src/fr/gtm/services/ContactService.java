package fr.gtm.services;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.gtm.dao.ContactDAO;
import fr.gtm.dto.ContactDTO;
import fr.gtm.entities.Adresse;
import fr.gtm.entities.Contact;


@Path("/contacts")
public class ContactService {
private static final Logger LOG = Logger.getLogger("REST");
@EJB private ContactDAO dao;
	
	
	@POST
	@Path("/save")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String save(Contact c) {
		LOG.info(">>> contact : "+c.getNom()+" "+c.getPrenom());
		return "ok";
	}
	
	
	@GET
	@Path("/allAdresses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Adresse> getAllAdresses() {
		return dao.getAllAdresses();
	}
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addContact(Contact c) {
		dao.create(c);
	}
	
	@DELETE
	@Path("/del/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public void deleteContact(@PathParam("id") long id) {
		dao.deleteById(id);
	}
	
	@POST
	@Path("/edit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateContact(Contact contact) {
		dao.update(contact);
	}
	
	@GET
	@Path("/all")
	@Produces({"application/json;charset=utf-8"})
	public List<ContactDTO> getAllContacts() {
		List<ContactDTO> dtos = new ArrayList<ContactDTO>();
		List<Contact> contacts = dao.getAllContacts();
		for (Contact c : contacts) {
			dtos.add(new ContactDTO(c));
		}
		return dtos;
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ContactDTO getContactById(@PathParam("id") Long id) {
		ContactDTO dto = new ContactDTO(dao.getContactById(id));
		LOG.info(">>> id: "+id);
		return dto;
	}
	
	@GET
	@Path("/adresses/{id}") 
	@Produces({"application/json;charset=utf-8"})
	public List<Adresse> getAdresseByContactId(@PathParam("id") long id) {
		List<Contact> contacts = new ArrayList<Contact>();
		List<Adresse> adresses = new ArrayList<Adresse>();
		return dao.getAdresseByContactId(id);
	}
}
