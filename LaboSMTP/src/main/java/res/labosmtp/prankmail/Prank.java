package res.labosmtp.prankmail;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Ciani Antony, Hernandez Thomas
 */
public class Prank {
    
    
    private Mail prankMail;
    private Person sender;
    private Message message;
    
    
    Prank(Group group, LinkedList<Message> messages){
        
        sender = group.getSender();
        String senderAddr = sender.getEmailAddress();
        System.out.println("MSIZE:" + messages.size());
        Random r = new Random();
        int messagePos = r.nextInt(messages.size());
        System.out.println("MPOS:" + messagePos);
        message = messages.get(messagePos);
        
//        Scanner sc = new Scanner(System.in);
//        System.out.println("enter a subject :");
//        subject = sc.nextLine();
        
        LinkedList<String> rcptsAdresses = group.getRecipientsEmails();
        
        prankMail = new Mail(senderAddr, message, rcptsAdresses);
    }

    /**
     * @return the prankMail
     */
    public Mail getPrankMail() {
        return prankMail;
    }
    

    
    
    
}
