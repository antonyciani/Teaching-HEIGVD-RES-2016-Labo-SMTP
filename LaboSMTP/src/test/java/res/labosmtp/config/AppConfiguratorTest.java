/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package res.labosmtp.config;

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import res.labosmtp.prankmail.Group;
import res.labosmtp.prankmail.Person;

/**
 *
 * @author Antony
 */
public class AppConfiguratorTest {
    

    @Test
    public void testAll() {
        try {
            System.out.println("getGroups");
            AppConfigurator ac = new AppConfigurator("emails.txt", "messages.txt", 0);
            String e1 = ac.getPersons().get(0).getEmailAddress();
            System.out.println(e1);
            String e2 = ac.getPersons().get(1).getEmailAddress();
            System.out.println(e2);
            String m1 = ac.getMessages().get(0);
            System.out.println(m1);
            String m2 = ac.getMessages().get(1);
            System.out.println(m2);
            assertEquals("antony.ciani@heig-vd.ch",e1);
            assertEquals(e2, "thomas.hernandez@heig-vd.ch");
            assertEquals("Hello Moto!\r\n", m1);
            assertEquals("Domo harigato Mr. Roboto!\r\n", m2);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
