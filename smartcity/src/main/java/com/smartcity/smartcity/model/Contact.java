package com.smartcity.smartcity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "CONTACT")

public class Contact {
	
	@Id
	@GeneratedValue
	private int contactId;
	
	@NotBlank(message = " Veuillez renseigner le champ nom")
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotBlank(message = " Veuillez renseigner le champ sujet")
	@Column(name = "subject", nullable = false)
	private String subject;
	
	@NotBlank(message = " Veuillez renseigner le champ email")
	@Column(name = "mail", nullable = false)
	private String mail;
	
	@NotBlank(message = " Veuillez renseigner le champ message")
	@Size(min = 10, max = 300)
	@Column(name = "text")
	private String text;

	public Contact() {
		super();
		
	}

	public Contact(String name, String subject, String mail, String text) {
		super();
		this.name = name;
		this.subject = subject;
		this.mail = mail;
		this.text = text;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + contactId;
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		if (contactId != other.contactId)
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", name=" + name + ", subject=" + subject + ", mail=" + mail
				+ ", text=" + text + "]";
	}
	
	
	


	

	

	
}
