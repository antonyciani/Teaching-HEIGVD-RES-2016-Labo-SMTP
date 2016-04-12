/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package res.labosmtp.prankmail;

import java.util.LinkedList;


/**
 * This class generates a random group to be pranked from multiple Persons
 * @author Antony
 */
public class Group {
    
    private Person sender;
    private LinkedList<Person> recipients;

    
    public Group(Person[] persons){
        
        // Choose a random sender
        int senderPos = (int) (Math.random() * (persons.length - 1));
        sender = persons[senderPos];
        
        // Put the other person into the recipients
        for(int i = 0; i < persons.length; i++){
            if(i != senderPos){
                recipients.add(persons[i]);
            }
        }
        
    }
    
    /**
     * @return the sender
     */
    public Person getSender() {
        return sender;
    }

    /**
     * @return the recipients
     */
    public LinkedList<Person> getRecipients() {
        return recipients;
    }

    

    
    
    
}
