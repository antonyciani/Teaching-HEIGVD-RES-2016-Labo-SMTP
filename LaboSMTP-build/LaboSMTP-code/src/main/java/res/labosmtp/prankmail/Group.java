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
public class Group {
    
    private Person sender;
    private Person[] recipients;

    
    Group(Person[] persons){
        
        
        
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
    public Person[] getRecipients() {
        return recipients;
    }

    
    
    
}
