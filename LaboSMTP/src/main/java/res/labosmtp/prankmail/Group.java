package res.labosmtp.prankmail;

import java.util.LinkedList;
import java.util.Random;


/**
 * This class generates a random group to be pranked from multiple Persons
 *
 * @author Ciani Antony, Hernandez Thomas
 */
public class Group {
    
    private Person sender;
    private LinkedList<Person> recipients;

    public Group(LinkedList<Person> persons){
        
        // Choose a random sender
        Random r = new Random();
        int senderPos = r.nextInt(persons.size());
        sender = persons.get(senderPos);
        
        // Put the other persons into the recipients list
        recipients = new LinkedList<>();
        for(int i = 0; i < persons.size(); i++){
            if(i != senderPos){
                recipients.add(persons.get(i));
                
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
     * @return the recipients emails
     */
    public LinkedList<String> getRecipientsEmails() {
        
        LinkedList<String> emails = new LinkedList<>();
        for(Person p : recipients){
            emails.add(p.getEmailAddress());
        }
        return emails;
    }

    
}
