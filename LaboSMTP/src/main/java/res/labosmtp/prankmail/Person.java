package res.labosmtp.prankmail;

/**
 * This class simply encapsulate an email address
 * 
 * @author Ciani Antony, Hernandez Thomas
 */
public class Person {
    
    private String emailAddress;
    
    /**
     * 
     * Simply encapsulate the field
     * 
     * @param emailAddress 
     */
    public Person(String emailAddress){
        this.emailAddress = emailAddress;
        
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    
    
    
}
