package com.guzichenko.services;

import com.guzichenko.dao.ContactDAO;
import com.guzichenko.model.Contact;

public class ContactService {

	private ContactDAO dao;

	public ContactService(ContactDAO contactDAO) {
		this.dao = contactDAO;
	}

	public void createContact(String name, String surname, int age) {
		Contact contact = new Contact(name, surname, age);

		System.out.println("CONTACT CREATED");
		dao.saveContact(contact);
	}

	public void modifyContact(int id, String name, String surname, int age){
		Contact contact = new Contact(id, name, surname, age);

		System.out.println("CONTACT MODIFIED");
			dao.updateContact(contact);
	}

	public void deleteContact(int id){


		System.out.println("CONTACT DELETED");
		dao.remove(id);
	}


	public void show() {
		dao.show();
	}
}
