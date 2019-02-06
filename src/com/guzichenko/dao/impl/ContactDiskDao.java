package com.guzichenko.dao.impl;

import com.guzichenko.dao.ContactDAO;
import com.guzichenko.model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDiskDao implements ContactDAO {

    private int generator = nextId();

    @Override
    public void saveContact(Contact contact) {
        File file = new File("ContactBook.txt");
        try (FileWriter fileWriter = new FileWriter(file, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter) {

        }) {

            contact.setId(generator++);
            printWriter.println(contact);
            printWriter.flush();
        }
        catch (IOException e) {
            System.out.println("Something going wrong!");
        }
    }

    @Override
    public void remove(int idContact){
    File file = new File("ContactBook.txt");
    List<Contact> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file);
    BufferedReader bufferedReader = new BufferedReader(fileReader)){
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            String[] strings = line.split(" ");
            int id = Integer.valueOf(strings[0]);
            String name = strings[1];
            String surname = strings[2];
            int age = Integer.valueOf(strings[3]);
            if (id == idContact ) continue;
                else list.add(new Contact(id,name, surname, age));
            }
        }
        catch (IOException e){
            System.out.println("Something going wrong!");

        }

        file.delete();

        for (Contact cont : list) {
            try (FileWriter fileWriter = new FileWriter(file, true);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                 PrintWriter printWriter = new PrintWriter(bufferedWriter) {

                 }) {

                printWriter.println(cont);
                printWriter.flush();
            }
            catch (IOException e) {
                System.out.println("Something going wrong!");
            }
        }

    }


    @Override
    public void updateContact(Contact contact) {
        File file = new File("ContactBook.txt");
        List<Contact> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] strings = line.split(" ");
                int id = Integer.valueOf(strings[0]);
                String name = strings[1];
                String surname = strings[2];
                int age = Integer.valueOf(strings[3]);
                if (contact.getId() == id) list.add(contact);
                    else list.add(new Contact(id, name, surname, age));
            }
        }
        catch (IOException e){
            System.out.println("Something going wrong!");

        }

        file.delete();

        for (Contact cont : list) {
            try (FileWriter fileWriter = new FileWriter(file, true);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                 PrintWriter printWriter = new PrintWriter(bufferedWriter) {

                 }) {

                printWriter.println(cont);
                printWriter.flush();
            }
            catch (IOException e) {
                System.out.println("Something going wrong!");
            }
        }

    }

    @Override
    public void show() {
        File file = new File("ContactBook.txt");
        if (!file.exists()) {
            System.out.println("File does not exist!!!");
            return;}
        List<Contact> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader)){
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] strings = line.split(" ");
                int id = Integer.valueOf(strings[0]);
                String name = strings[1];
                String surname = strings[2];
                int age = Integer.valueOf(strings[3]);
                list.add(new Contact(id,name, surname, age));
            }
            }
        catch (IOException e){
            System.out.println("Something going wrong!");

        }
        for (Contact contact : list){
            System.out.println(contact);
        }
    }

    private int nextId(){
        int nextId = 0;
        File file = new File("ContactBook.txt");
        if (!file.exists()) return nextId;
        List<Contact> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)){
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] strings = line.split(" ");
                int id = Integer.valueOf(strings[0]);
                nextId = id + 1;
                String name = strings[1];
                String surname = strings[2];
                int age = Integer.valueOf(strings[3]);
                list.add(new Contact(id,name, surname, age));
            }
        }
        catch (IOException e){
            System.out.println("Something going wrong!");

        }

        return nextId;
    }
}
