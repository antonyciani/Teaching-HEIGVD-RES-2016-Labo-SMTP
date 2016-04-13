/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package res.labosmtp.prankmail;

import java.util.LinkedList;

/**
 *
 * @author Antony
 */
public class Mail {
    
    private String senderAddress;
    private LinkedList<String> recipientAddresses;
    private LinkedList<String> carboncopyAddresses;
    private LinkedList<String> blindcarboncopyAddresses;
    
    private String message;
    private String subject;
	
    private static final String FROM_HEADER = "From: ";
    private static final String TO_HEADER = "To: ";
	private static final String SUBJECT_HEADER = "Subject: ";

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
     * @return the carboncopyAddresses
     */
    public LinkedList<String> getCarboncopyAddresses() {
        return carboncopyAddresses;
    }

    /**
     * @return the blindcarboncopyAddresses
     */
    public LinkedList<String> getBlindcarboncopyAddresses() {
        return blindcarboncopyAddresses;
    }
    
    public String toString() {
        String recipAddr = "";
        for (String addr : recipientAddresses) {
            recipAddr += addr + ",";
        }
        return FROM_HEADER + senderAddress + "\n" + TO_HEADER + recipAddr + "\n"
                + SUBJECT_HEADER + subject + "\n" + message;
    }

        
    
}
