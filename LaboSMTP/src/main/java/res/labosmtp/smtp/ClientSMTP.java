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
 * @author Ciani Antony, Hernandez Thomas
 */
public class ClientSMTP {

    private static final Logger LOG = Logger.getLogger(ClientSMTP.class.getName());

    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;
    boolean connected = false;
    

    public void connect(String serverAddress, int serverPort) {
        try {
            clientSocket = new Socket(serverAddress, serverPort);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream());
            String serverMsg = "";

            while ((serverMsg = in.readLine()) != null) {
                LOG.info(serverMsg);

                if (!serverMsg.substring(3, 4).equals("-") && serverMsg.substring(0, 3).equals(SMTPProtocol.CONNECTION_OK)) {
                    LOG.info("Server replied 220");

                    boolean ehloSent = false;
                    LOG.info("saying ehlo");
                    out.println(SMTPProtocol.EHLO + "localhost");
                    out.flush();
                    while (!ehloSent && (serverMsg = in.readLine()) != null) {

                        if (!serverMsg.substring(3, 4).equals("-") && serverMsg.substring(0, 3).equals(SMTPProtocol.COMMAND_OK)) {
                            ehloSent = true;
                        } 

                    }

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

    public void disconnect() throws IOException{
        LOG.log(Level.INFO, "You requested to be disconnected.");
        out.println(SMTPProtocol.QUIT);
        out.flush();
        LOG.info(in.readLine());
        connected = false;
        out.println();
        cleanup();
    }

    public void sendMail(Mail mail) throws IOException {

        if (connected) {
            boolean ehloSent = false;
            boolean fromtoSent = false;
            boolean rcptsSent = false;
            boolean dataSent = false;
            boolean mailDataSent = false;
            boolean endmsgSent = false;
            String serverMsg = "";
            LOG.info("sending email");

            LinkedList<String> rcpts = new LinkedList<>(mail.getRecipientAddresses());
            out.println(SMTPProtocol.FROM + mail.getSenderAddress());
            out.flush();
            fromtoSent = true;
            while (connected && (serverMsg = in.readLine()) != null) {
                LOG.info(serverMsg);
                if (/*!serverMsg.substring(3, 4).equals("-") &&*/serverMsg.substring(0, 3).equals(SMTPProtocol.COMMAND_OK)) {
                    LOG.info("Server replied 250");

                    //ehloSent = true;
                    if (!rcptsSent) {
                        if (!rcpts.isEmpty()) {
                            String rcpt = rcpts.pop();
                            LOG.info(SMTPProtocol.RCPT + rcpt);
                            out.println(SMTPProtocol.RCPT + rcpt);
                            out.flush();
                        } else {
                            LOG.info("DATA");
                            out.println(SMTPProtocol.DATA);
                            out.flush();
                            rcptsSent = true;
                        }
                        //send RCPT TO

                    } else if (!endmsgSent) {

                        LOG.info("Mail sent successfully");
                        endmsgSent = true;
                        return;

                    } else {
                        LOG.info("CHELOU");
                    }

                } else if (serverMsg.substring(0, 3).equals(SMTPProtocol.SEND_DATA_OK)) {
                    // Send mail
                    LOG.info("Server replied 354");
                    if (!mailDataSent) {
                        LOG.info(mail.toString());
                        out.print(mail);
                        out.flush();
                        LOG.info("END");
                        out.print(SMTPProtocol.ENDMSG);
                        out.flush();

                        mailDataSent = true;
                    }
                } else {
                    LOG.info("Error from Server");
                    LOG.info(serverMsg);
                }
            }

        } else {
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
