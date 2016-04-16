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
            
            int nbGroups = 1;
            String emailsListPath = "emails.txt";
            String messagesListPath = "messages.txt";
            
            if(args.length > 0){
                
                if(args.length == 1){
                    nbGroups = Integer.parseInt(args[0]);
                }
                else if(args.length == 3){

                    emailsListPath = args[0];
                    messagesListPath = args[1];
                    nbGroups = Integer.parseInt(args[2]);
                }
            }
            
            AppConfigurator ac = new AppConfigurator(emailsListPath, messagesListPath, nbGroups);                      
            LinkedList<Person> persons = ac.getPersons();
            LinkedList<Message> messages = ac.getMessages();
            LinkedList<Group> groups = ac.getGroups();
            
            ClientSMTP csmtp = new ClientSMTP();
            csmtp.connect("localhost", 2525);
            System.out.println("=====================================================================================================");
            
            for(Group g : groups){
                System.out.println("Group:");
                System.out.println(g.getSender().getEmailAddress());
                for(String email : g.getRecipientsEmails()){
                    System.out.println(email);
                }
                System.out.println("=========================================");
                Prank prank = new Prank(g, messages);
                Mail prankMail = prank.getPrankMail();
                csmtp.sendMail(prankMail);
                System.out.println("=========================================");
                
            }
            
            System.out.println("=====================================================================================================");
            
            csmtp.disconnect();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        
    }
    
}
