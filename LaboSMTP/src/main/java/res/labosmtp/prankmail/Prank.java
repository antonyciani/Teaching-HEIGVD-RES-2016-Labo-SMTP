package res.labosmtp.prankmail;

import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Ciani Antony, Hernandez Thomas
 */
public class Prank {
    
    
    private Mail prankMail;
    private Person sender;
    private String message;
    private String subject;
    
    Prank(Group group, LinkedList<String> messages){
        
        sender = group.getSender();
        String senderAddr = sender.getEmailAddress();
        int messagePos = (int) (Math.random() * (messages.size() - 1));
        message = messages.get(messagePos);
//        Scanner sc = new Scanner(System.in);
//        System.out.println("enter a subject :");
//        subject = sc.nextLine();
        
        LinkedList<String> rcptsAdresses = group.getRecipientsEmails();
        
        
        prankMail = new Mail(senderAddr, message, "YOLO", rcptsAdresses);
    }

    /**
     * @return the prankMail
     */
    public Mail getPrankMail() {
        return prankMail;
    }
    

    
    
    
}
