/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package res.labosmtp.prankmail;

import res.labosmtp.config.AppConfigurator;

/**
 *
 * @author Antony
 */
public class PrankMailGenerator {
    
    
    public static void main(String[] args) {
        try{
            AppConfigurator ac = new AppConfigurator("emails.txt", "messages.txt", 0);
            
            for(Person p : ac.getPersons()){
                System.out.println(p.getEmailAddress());
            }
            
            for(String m : ac.getMessages()){
                System.out.print(m);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        
    }
    
}
