/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package res.labosmtp.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import res.labosmtp.prankmail.*;

/**
 *
 * @author Antony
 */
public class AppConfigurator {

    private LinkedList<Group> groups;
    private LinkedList<Person> persons;
    private LinkedList<Message> messages;
    private static final int MIN_NB_GROUP_SIZE = 3; // Should be at least 2

    public AppConfigurator(String emailListFilename, String messageListFilename,
            int nbGroups) throws FileNotFoundException, IOException {

        persons = new LinkedList<>();
        messages = new LinkedList<>();
        groups = new LinkedList<>();

        // Extracting email list from config file
        BufferedReader br = new BufferedReader(new FileReader(emailListFilename));
        String email = "";
        while ((email = br.readLine()) != null) {
            Person p = new Person(email);
            persons.add(p);

        }

        // Extracting messages list
        String message = "";
        Scanner messageReader = new Scanner(new BufferedReader(new FileReader(messageListFilename)));
        messageReader.useDelimiter(System.getProperty("line.separator") + "////" + System.getProperty("line.separator"));
        while (messageReader.hasNext()) {
            message = messageReader.next();
            String[] lines = message.split(System.getProperty("line.separator"));
            String subject = lines[0];
            message = message.substring(message.indexOf(System.getProperty("line.separator")));
            messages.add(new Message(subject, message));
        }

        // Creating groups
        if((persons.size() / nbGroups) < MIN_NB_GROUP_SIZE) {
            throw new RuntimeException("Email list is too short, or number of groups is too big, minimum 3 emails per group!");
        }
        
        for (int i = 0; i < nbGroups; i++) {
            LinkedList<Person> newGroup = new LinkedList<>();
            for (int j = i*(persons.size() / nbGroups); j < i*(persons.size() / nbGroups) + (persons.size() / nbGroups); j++) {
                newGroup.add(persons.get(j));
            }
            groups.add(new Group(newGroup));
        }

    }

    /**
     * @return the groups
     */
    public LinkedList<Group> getGroups() {
        return groups;
    }

    /**
     * @return the persons
     */
    public LinkedList<Person> getPersons() {
        return persons;
    }

    /**
     * @return the messages
     */
    public LinkedList<Message> getMessages() {
        return messages;
    }

}
