package res.labosmtp.prankmail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Properties;
import res.labosmtp.config.AppConfigurator;
import res.labosmtp.smtp.*;

/**
 *
 * @author Ciani Antony, Hernandez Thomas
 */
public class PrankMailGenerator {

    public static void main(String[] args) {
        try {

            int nbGroups = 1;
            String emailsListPath = "emails.txt";
            String messagesListPath = "messages.txt";
            String serverAddress = "localhost";
            int serverPort = 25;

            Properties prop = new Properties();
            

            try {

                String filename = "appconfig.properties";
                
                BufferedReader br = new BufferedReader(new FileReader(filename));
                //load a properties file from class path, inside static method
                prop.load(br);

                //get the property value and print it out
                nbGroups = Integer.parseInt(prop.getProperty("nbgroups"));
                emailsListPath = prop.getProperty("emailspath");
                messagesListPath = prop.getProperty("messagespath");
                serverAddress = prop.getProperty("smtpserveraddress");
                serverPort = Integer.parseInt(prop.getProperty("serverport"));
                

            } catch (IOException ex) {
                ex.printStackTrace();
            } 

            System.out.println(serverAddress + " " + serverPort);
            
            AppConfigurator ac = new AppConfigurator(emailsListPath, messagesListPath, nbGroups);
            LinkedList<Person> persons = ac.getPersons();
            LinkedList<Message> messages = ac.getMessages();
            LinkedList<Group> groups = ac.getGroups();

            ClientSMTP csmtp = new ClientSMTP();
            csmtp.connect(serverAddress, serverPort);
            System.out.println("=====================================================================================================");

            for (Group g : groups) {
                System.out.println("Group:");
                System.out.println(g.getSender().getEmailAddress());
                for (String email : g.getRecipientsEmails()) {
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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
