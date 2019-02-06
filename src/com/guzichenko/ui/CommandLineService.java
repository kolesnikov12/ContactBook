package com.guzichenko.ui;

import java.io.BufferedReader;
import java.io.IOException;

import com.guzichenko.services.ContactService;

public class CommandLineService {

	private ContactService contactService;
	private BufferedReader br;

	public CommandLineService(
			ContactService contactService,
			BufferedReader br) {
		this.contactService = contactService;
		this.br = br;
	}

	public void run() throws IOException {
		String input;
		do {
			showMenu();
			input = br.readLine();
			if (input.equals("1")) {
				showCreateMenu();
			}
			if (input.equals("2")){
                modifyMenu();
            }
            if (input.equals("3")){
                deleteMenu();
            }
			if (input.equals("4")) {
				contactService.show();
			}
		}
		while (!input.equals("0"));
	}

	private void showMenu() {
		System.out.println("Menu");
		System.out.println("1. Create contact");
		System.out.println("2. Modify contact");
		System.out.println("3. Delete contact");
		System.out.println("4. Show contacts");
		System.out.println("0. Exit");
	}

	private void showCreateMenu() throws IOException {
		System.out.println("Input name:");
		String name = br.readLine();
		System.out.println("Input surname:");
		String surname = br.readLine();
		System.out.println("Input age:");
		int age = readNumber();
		//System.out.println("Input phone:");

		contactService.createContact(name, surname, age);
	}

	private void modifyMenu() throws IOException{

        contactService.show();
        System.out.println("Input ID of the contact that you want to modify:");
        int id = readNumber();
        System.out.println("Input new name:");
        String name = br.readLine();
        System.out.println("Input new surname:");
        String surname = br.readLine();
        System.out.println("Input new age:");
        int age = readNumber();

        contactService.modifyContact(id, name, surname, age);


    }
    private void deleteMenu() throws IOException{
        contactService.show();
        System.out.println("Input ID of the contact that you want to delete:");
        int id = readNumber();

        contactService.deleteContact(id);
    }

	private int readNumber() {
		try {

			Integer integer = new Integer(br.readLine());
			return integer;
		}
		catch (IOException | NumberFormatException e) {
			System.out.println("Wrong format!!!");
			return readNumber();
		}
		catch (Exception e) {
			System.out.println("Wrong age!!! Too Old.");
			return readNumber();
		}
	}
}
