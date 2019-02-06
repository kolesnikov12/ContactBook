package com.guzichenko.dao.impl;

import com.guzichenko.dao.ContactDAO;
import com.guzichenko.model.Contact;

public class ContactArrayDAO implements ContactDAO {

	private static int generator = 0;

	private Contact[] store = new Contact[10];

	@Override
	public void saveContact(Contact contact) {
		for (int i = 0; i < store.length; i++) {
			if (store[i] == null) {
				contact.setId(generator++);
				store[i] = contact;
				break;
			}

		}

		System.out.println(contact);
		System.out.println("Contact stored!!!");
	}

	@Override
	public void remove(int id) {
		for (int i = 0; i < store.length; i++) {

			if (store[i] != null && store[i].getId() == id) store[i] = null;;
		}
	}

	@Override
	public void updateContact(Contact contact) {
		store[contact.getId()] = contact;


	}

	@Override
	public void show() {
		for (Contact contact : store) {
			if (contact == null) continue;
			System.out.println(contact);
		}
	}

}