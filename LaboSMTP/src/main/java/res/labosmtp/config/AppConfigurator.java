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
    private LinkedList<String> messages;

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
        messageReader.useDelimiter("////");
        while (messageReader.hasNext()) {
            message = messageReader.next();
            messages.add(message);
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
    public LinkedList<String> getMessages() {
        return messages;
    }
    
    
    

}
