/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package res.labosmtp.smtp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import res.labosmtp.prankmail.Mail;

/**
 *
 * @author Antony
 */
public class ClientSMTP {
    
    private static final Logger LOG = Logger.getLogger(ClientSMTP.class.getName());

    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;
    boolean connected = false;
    int clientId;
    boolean keyReceived = false;
    int secretKey = -1;
    Scanner keyboard = new Scanner(System.in);


    public void connect(String serverAddress, int serverPort) {
        try {
            clientSocket = new Socket(serverAddress, serverPort);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream());
            String serverMsg = "";
            
            while((serverMsg = in.readLine()) != null){
                LOG.info(serverMsg);
                
                if(!serverMsg.substring(3, 4).equals("-") && serverMsg.substring(0,3).equals(SMTPProtocol.CONNECTION_OK)){
                    LOG.info("Server replied 220");
                    connected = true;
                    return;
                }
                
            }
            
            
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Unable to connect to server: {0}", e.getMessage());
            cleanup();
            return;
        }
        
    }
    
    public void disconnect() {
        LOG.log(Level.INFO, "You requested to be disconnected.");
        connected = false;
        out.println();
        cleanup();
    }
    
    public void sendMail(Mail mail) throws IOException{
        
        if(connected){
            String serverMsg = "";
            LOG.info("sending email");
            LOG.info(SMTPProtocol.EHLO + " localhost");
            out.println(SMTPProtocol.EHLO + " localhost");
            out.flush();
            while((serverMsg = in.readLine()) != null){
                LOG.info(serverMsg);
                if(!serverMsg.substring(3, 4).equals("-") && serverMsg.substring(0,3).equals(SMTPProtocol.COMMAND_OK)){
                    LOG.info("Server replied 250");
                    
                }
                
            }
            
            
        }
        else{
            throw new RuntimeException("Connect before sending mails!");
        }
        
        
    }

    private void cleanup() {

        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }

        if (out != null) {
            out.close();
        }

        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    
    
    
}
