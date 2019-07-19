package com.smartcity.smartcity.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartcity.smartcity.model.Contact;


@Transactional
@Repository
public interface ContactDAO extends JpaRepository<Contact, Integer>{
	//Contact getContactByName(String loginname);
	
		
	void deleteByContactId(int contactId);
	
	void deleteByName(String name);
	
	
	List<Contact> getAllContactsByName(String name);
	
		
}
