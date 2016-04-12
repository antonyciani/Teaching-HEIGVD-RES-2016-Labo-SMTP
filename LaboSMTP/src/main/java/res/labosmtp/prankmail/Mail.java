/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package res.labosmtp.prankmail;

/**
 *
 * @author Antony
 */
public class Mail {
    
    private String senderAddress;
    private String[] recipientAddresses;
    private String[] carboncopyAddresses;
    private String[] blindcarboncopyAddresses;
    private String subject;
    private String message;

    /**
     * @return the senderAddress
     */
    public String getSenderAddress() {
        return senderAddress;
    }

    /**
     * @return the recipientAddresses
     */
    public String[] getRecipientAddresses() {
        return recipientAddresses;
    }

    /**
     * @return the carboncopyAddresses
     */
    public String[] getCarboncopyAddresses() {
        return carboncopyAddresses;
    }

    /**
     * @return the blindcarboncopyAddresses
     */
    public String[] getBlindcarboncopyAddresses() {
        return blindcarboncopyAddresses;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    
    
    
}
