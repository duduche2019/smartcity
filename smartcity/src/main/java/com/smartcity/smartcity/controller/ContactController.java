package com.smartcity.smartcity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smartcity.smartcity.DAO.ContactDAO;
import com.smartcity.smartcity.exception.ContactNotFoundException;
import com.smartcity.smartcity.model.Contact;

@RestController
public class ContactController {

	@Autowired
	ContactDAO contactDAO;

	/**
	 * Request a new creation.
	 * 
	 * @param contact Contact informations for the new user creation.
	 * @return URI of the contact newly created
	 */
	@PostMapping(value = "/createContact")
	public Contact addContact(@RequestBody Contact contact) {
		Contact createdContact = contactDAO.save(contact);
		return createdContact;
	}

	/**
	 * Update an existing contact.
	 * 
	 * @param contact Contact informations to put in the actual contact.
	 * @return 200 if correctly updated, 404 if no such user contactId found.
	 */
	@PutMapping(value = "/updateContact")
	public Contact updateContact(@RequestBody Contact contact) {
		Contact updatedContact = contactDAO.save(contact);
		return updatedContact;
	}

	/**
	 * Get a specific contact.
	 * 
	 * @param contactId cId of the user wished.
	 * @return The contact wished, or 404 if not found.
	 */
	@GetMapping(value = "/getContactById/{contactId}")
	public Contact getContactById(@PathVariable int contactId) throws ContactNotFoundException {

		Contact contactFound = contactDAO.findOne(contactId);

		if (contactFound == null) {

			throw new ContactNotFoundException();
		}
		return contactFound;

	}

	/**
	 * Get a specific contact.
	 * 
	 * @param login userId of the user wished.
	 * @return The user wished, or 404 if not found.
	 */
	@GetMapping(value = "/getContactByName/{name}")
	public List<Contact> getContactByName(@PathVariable String name) {
		List<Contact> ContactsFound = contactDAO.getAllContactsByName(name);
		return ContactsFound;
	}

	/**
	 * Delete a specific contact.
	 * 
	 * @param contactId
	 * 
	 * @return void, or 404 if not found.
	 */
	@DeleteMapping(value = "/deleteByContactId/{contactId}")
	public void deleteUserById(@PathVariable int contactId) {
		contactDAO.deleteByContactId(contactId);
	}

	/**
	 * Delete a all users By name.
	 * 
	 * @param name
	 * 
	 * @return void, or 404 if not found.
	 */
	@DeleteMapping(value = "/deleteAllContactsByName/{name}")
	public void deleteAllContacts(@PathVariable String name) {
		contactDAO.deleteByName(name);;
	}
	
	/**
	 * Delete a All contacts.
	 * 
	 * @param
	 * 
	 * @return void, or 404 if not found.
	 */
	@DeleteMapping(value = "/deleteAllContacts")
	public void deleteAllContacts() {
		contactDAO.deleteAll();
	}
}
