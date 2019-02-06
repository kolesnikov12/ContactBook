package com.guzichenko.dao;

import com.guzichenko.model.Contact;

public interface ContactDAO {

	/**
	 *
	 * @param contact
	 */

	void saveContact(Contact contact);

	void remove(int id);

	void updateContact(Contact contact);

	void show();
}