package res.labosmtp.prankmail;

import java.util.LinkedList;
import res.labosmtp.config.AppConfigurator;
import res.labosmtp.smtp.*;

/**
 *
 * @author Ciani Antony, Hernandez Thomas
 */
public class PrankMailGenerator {
    
    
    public static void main(String[] args) {
        try{
            AppConfigurator ac = new AppConfigurator("emails.txt", "messages.txt", 0);
            
            //
            for(Person p : ac.getPersons()){
                System.out.println(p.getEmailAddress());
            }
            
            for(String m : ac.getMessages()){
                System.out.print(m);
            }
            
            //
            ClientSMTP csmtp = new ClientSMTP();
            csmtp.connect("localhost", 2525);
            System.out.println("=====================================================================================================");
            //
            LinkedList<Person> persons = ac.getPersons();
            LinkedList<String> messages = ac.getMessages();
            
            Group group = new Group(persons);
            
            Prank prank = new Prank(group, messages);
            
            Mail prankMail = prank.getPrankMail();
            csmtp.sendMail(prankMail);
            System.out.println("=====================================================================================================");

            System.out.println("ADIEU");
            prankMail = prank.getPrankMail();
            csmtp.sendMail(prankMail);
            
            csmtp.disconnect();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        
    }
    
}
