package com.guzichenko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.guzichenko.dao.ContactDAO;
import com.guzichenko.dao.impl.ContactArrayDAO;
import com.guzichenko.dao.impl.ContactCollectionDAO;
import com.guzichenko.dao.impl.ContactDBDAO;
import com.guzichenko.dao.impl.ContactDiskDao;
import com.guzichenko.services.ContactService;
import com.guzichenko.ui.CommandLineService;

public class Main {

	public static void main(String[] args) throws IOException {
		ContactDAO dao = new ContactDBDAO();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ContactService contactService = new ContactService(dao);
		CommandLineService service = new CommandLineService(contactService, br);
		service.run();
	}
}
