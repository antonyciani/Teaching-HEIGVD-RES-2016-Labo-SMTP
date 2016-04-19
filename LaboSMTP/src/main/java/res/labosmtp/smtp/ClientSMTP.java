package res.labosmtp.smtp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;
import res.labosmtp.prankmail.*;

/**
 * This class is used to connect to an SMTP server to send them emails. In our case,
 * an email is represented by a Mail object which contains all the information
 * required to send an email.
 *
 * @author Ciani Antony, Hernandez Thomas
 */
public class ClientSMTP {

    private static final Logger LOG = Logger.getLogger(ClientSMTP.class.getName());

    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;
    boolean connected = false;


    
    /**
     * This method enables the connection to an SMTP server designated by its IP or
     * DNS name and the port on which it is listening In addition to creating a
     * socket, the method performs the EHLO phase which is necessary to begin sending
     * emails to the server
     * 
     * @param serverAddress
     * @param serverPort 
     */
    public void connect(String serverAddress, int serverPort) {
        try {

            clientSocket = new Socket(serverAddress, serverPort);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream());
            String serverMsg = "";

            while ((serverMsg = in.readLine()) != null) {
                LOG.info(serverMsg);

                if (!serverMsg.substring(3, 4).equals("-") && serverMsg.substring(0, 3).equals(SMTPProtocol.CONNECTION_OK)) {
                    boolean ehloSent = false;
                    LOG.info("EHLO");
                    out.println(SMTPProtocol.EHLO + "localhost");
                    out.flush();
                    while (!ehloSent && (serverMsg = in.readLine()) != null) {
                        LOG.info(serverMsg);
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
    
    /**
     * 
     * This method performs the disconnection from the SMTP server, it first send the
     * QUIT command to the server and then closes its Streams and Socket
     * 
     * @throws IOException 
     */
    public void disconnect() throws IOException {
        LOG.log(Level.INFO, "You requested to be disconnected.");
        out.println(SMTPProtocol.QUIT);
        out.flush();
        LOG.info(in.readLine());
        connected = false;
        out.println();
        cleanup();
    }
    
    /**
     * 
     * This method alllows to send an email to the SMTP server. The Mail object
     * contains the various information needed to send the mail. The client must
     * first have connected to the server before, otherwise a RuntimeException is
     * thrown. In the case the server responds with a code other than the codes
     * needed (see SMTPProtocol), it throws a RuntimeException
     * 
     * @param mail
     * @throws IOException 
     */
    public void sendMail(Mail mail) throws IOException {

        if (connected) {

            boolean rcptsSent = false;
            boolean mailDataSent = false;
            boolean endmsgSent = false;
            String serverMsg = "";
            LOG.info("Sending email");

            LinkedList<String> rcpts = new LinkedList<>(mail.getRecipientAddresses());
            out.println(SMTPProtocol.FROM + mail.getSenderAddress());
            out.flush();

            while (connected && (serverMsg = in.readLine()) != null) {
                LOG.info(serverMsg);
                if (serverMsg.substring(0, 3).equals(SMTPProtocol.COMMAND_OK)) {

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

                    } else if (!endmsgSent) {

                        LOG.info("Mail sent successfully");
                        endmsgSent = true;
                        return;

                    }

                } else if (serverMsg.substring(0, 3).equals(SMTPProtocol.SEND_DATA_OK)) {
                    // Send mail data
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
                    throw new RuntimeException("Server replied with an error!");
                }
            }

        } else {
            throw new RuntimeException("Connect before sending mails!");
        }

    }

    /**
     *
     * This method closes the streams and the socket
     * 
     */
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
