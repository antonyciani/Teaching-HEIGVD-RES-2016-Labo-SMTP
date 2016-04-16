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

    // MODIF: PERSONS ET UNE LINKED LIST
    public Group(LinkedList<Person> persons){
        
        // Choose a random sender
        Random r = new Random();
        int senderPos = r.nextInt(persons.size());
        sender = persons.get(senderPos);
        recipients = new LinkedList<>();
        System.out.println(sender.getEmailAddress());
        // Put the other person into the recipients
        for(int i = 0; i < persons.size(); i++){
            if(i != senderPos){
                recipients.add(persons.get(i));
                
            }
        }
        for(Person p : recipients){
            System.out.println(p.getEmailAddress());
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
    public LinkedList<String> getRecipientsEmails() {
        
        LinkedList<String> emails = new LinkedList<>();
        for(Person p : recipients){
            emails.add(p.getEmailAddress());
        }
        return emails;
    }

    
}
