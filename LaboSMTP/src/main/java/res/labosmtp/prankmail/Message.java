/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package res.labosmtp.prankmail;

import javax.security.auth.Subject;

/**
 *
 * @author Antony
 */
public class Message {
    
    private String subject;
    private String message;
    
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
