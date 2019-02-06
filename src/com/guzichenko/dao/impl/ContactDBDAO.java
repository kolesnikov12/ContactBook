package com.guzichenko.dao.impl;

import com.guzichenko.dao.ContactDAO;
import com.guzichenko.model.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDBDAO implements ContactDAO {


    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/ContactBook";
    private static final String USER = "test";
    private static final String PASSWORD = "test";

    public ContactDBDAO() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS CONTACT(ID BIGINT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255), SURNAME VARCHAR(255), AGE INT);");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveContact(Contact contact) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO CONTACT(NAME, SURNAME, AGE) VALUES(?, ?, ?)")) {

            statement.setString(1, contact.getName());
            statement.setString(2, contact.getSurname());
            statement.setInt(3, contact.getAge());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM CONTACT WHERE ID=?")) {

            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContact(Contact contact) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("UPDATE CONTACT SET (NAME, SURNAME, AGE) = (?, ?, ?) WHERE ID=?")) {


            statement.setString(1, contact.getName());
            statement.setString(2, contact.getSurname());
            statement.setInt(3, contact.getAge());
            statement.setInt(4, contact.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }

    @Override
    public void show() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM CONTACT;");
            List<Contact> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Contact(rs.getInt(1), rs.getString("NAME"), rs.getString(3), rs.getInt(4)));
            }
            for (Contact contact : list) {
                System.out.println(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
