package com.guzichenko.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.guzichenko.dao.ContactDAO;
import com.guzichenko.model.Contact;

public class ContactCollectionDAO implements ContactDAO {

	private Map<Integer, Contact> store;
	private int generator = 0;

	public ContactCollectionDAO() {
		store = new HashMap<>();
	}

	@Override
	public void saveContact(Contact contact) {
		contact.setId(generator++);
		store.put(contact.getId(), contact);
	}

	@Override
	public void remove(int id) {

		store.remove(id);

	}

	@Override
	public void updateContact(Contact contact) {

		store.put(contact.getId(), contact);



	}

	@Override
	public void show() {
		for (Contact contact : store.values()) {
			System.out.println(contact);
		}

	}
}
