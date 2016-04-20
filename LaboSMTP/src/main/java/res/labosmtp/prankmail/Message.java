package res.labosmtp.prankmail;

import javax.security.auth.Subject;

/**
 * This class represents an email message (Subject + actual message)
 * 
 * @author Ciani Antony, Hernandez Thomas
 */
public class Message {
    
    private String subject;
    private String message;
    
    /**
     * 
     * Simply encapsulates the fields for a mor comfortable use
     * 
     * @param subject
     * @param message 
     */
    public Message(String subject, String message){
        
        this.subject = subject;
        this.message = message;
        
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
