package res.labosmtp.prankmail;

import java.util.LinkedList;
import java.util.Random;


/**
 * This class represents a group of persons to prank (a sender and a list of recipients)
 * 
 * @author Ciani Antony, Hernandez Thomas
 */
public class Group {
    
    private Person sender;
    private LinkedList<Person> recipients;

    /**
     * Picks a random sender and stores the other Persons into the recipients list
     * 
     * @param persons 
     */
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
    public String getSenderEmail() {
        return sender.getEmailAddress();
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
