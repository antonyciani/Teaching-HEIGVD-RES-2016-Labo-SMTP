package res.labosmtp.prankmail;

import java.util.LinkedList;

/**
 * This class represents all the information needed to send an email
 * The sender address, the various recipients addresses, the subject of the message
 * and the message itself.
 * 
 * 
 * @author Ciani Antony, Hernandez Thomas
 */
public class Mail {
    
    private String senderAddress;
    private LinkedList<String> recipientAddresses;
    
    private String message;
    private String subject;
	
    private static final String FROM_HEADER = "From: ";
    private static final String TO_HEADER = "To: ";
    private static final String SUBJECT_HEADER = "Subject: ";
    
    
    public Mail(String senderAddress, 
            Message message, 
            LinkedList<String> recipientAddresses) {
        
        this.senderAddress = senderAddress;
        this.recipientAddresses = recipientAddresses;
        this.subject = message.getSubject();
        this.message = message.getMessage();
        
    }

    /**
     * @return the senderAddress
     */
    public String getSenderAddress() {
        return senderAddress;
    }

    /**
     * @return the recipientAddresses
     */
    public LinkedList<String> getRecipientAddresses() {
        return recipientAddresses;
    }
    
    
    /**
     * This method generates the "body(what we actually send after the DATA command)
     * of the email to be sent
     * 
     * @return the email data to be sent
     */
    public String toString() {
        String recipAddr = "";
        for (String addr : recipientAddresses) {
            recipAddr += addr + ",";
        }
        return FROM_HEADER + senderAddress + "\n" + TO_HEADER + recipAddr + "\n"
                + SUBJECT_HEADER + subject + "\n" + message;
    }

        
    
}
