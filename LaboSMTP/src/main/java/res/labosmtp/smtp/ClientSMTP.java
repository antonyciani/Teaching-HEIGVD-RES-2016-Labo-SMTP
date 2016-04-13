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
import java.util.LinkedList;
import res.labosmtp.prankmail.*;


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
            LOG.info(SMTPProtocol.EHLO + "localhost");
            out.println(SMTPProtocol.EHLO + "localhost");
            out.flush();
            boolean ehloSent = false;
            boolean fromtoSent = false;
            boolean rcptsSent = false;
            boolean dataSent = false;
            boolean mailDataSent = false;
            boolean endmsgSent = false;
            
            LinkedList<String> rcpts = mail.getRecipientAddresses();
                    
            while(connected && (serverMsg = in.readLine()) != null){
                LOG.info(serverMsg);
                if(!serverMsg.substring(3, 4).equals("-") && serverMsg.substring(0,3).equals(SMTPProtocol.COMMAND_OK)){
                    LOG.info("Server replied 250");
                    if(!ehloSent){
                        ehloSent = true;
                        if(!fromtoSent){
                            out.println(SMTPProtocol.FROM + mail.getSenderAddress());
                            fromtoSent = true;
                        }
                        else if(!rcptsSent){
                            if(!rcpts.isEmpty()){
                                out.println(SMTPProtocol.RCPT + rcpts.pop());
                            }
                            else{
                                rcptsSent = true;
                            }
                            //send RCPT TO
                        }
                        else if(!dataSent){
                            out.println(SMTPProtocol.DATA);
                        }
                        else if(endmsgSent){
                            LOG.info("Mail sent successfully");
                        }
                        else{
                            LOG.info("CHELOU");
                        }
                    }
                    
                }
                else if(serverMsg.substring(0,3).equals(SMTPProtocol.SEND_DATA_OK)){
                    // Send mail
                    if(!mailDataSent){
                        out.print(mail);
                        mailDataSent = true;
                    }
                    else if(!endmsgSent){
                        out.print(SMTPProtocol.ENDMSG);
                        endmsgSent = true;
                    }
                    
                }
                else{
                    LOG.info("Error from Server");
                    LOG.info(serverMsg);
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
