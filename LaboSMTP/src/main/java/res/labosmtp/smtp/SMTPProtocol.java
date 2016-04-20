package res.labosmtp.smtp;

/**
 * This class is used to define some commands to send to an SMTP server to send
 * emails and the response codes that can be sent back by the server.
 * 
 * @author Ciani Antony, Hernandez Thomas
 */
public class SMTPProtocol {
    
    public static final String EHLO = "EHLO ";          // First command to start sending email
    public static final String FROM = "MAIL FROM: ";    // Command to specify sender sddress, used only once
    public static final String RCPT = "RCPT TO: ";      // Command to specify a recipient address, can be used multiple times (one per recipient)
    public static final String DATA = "DATA";           // Command to start sending the mail body
    public static final String ENDMSG = "\r\n.\r\n";    // Command to end sending the mail body
    public static final String QUIT = "QUIT";           // Command to disconnect from the server
    
    public static final String CONNECTION_OK = "220";   // First code sent by server when connection is successful
    public static final String COMMAND_OK = "250";      // Command sent has been executed
    public static final String SEND_DATA_OK = "354";    // Response to the DATA command, allows to send the mail body
    public static final String SERVER_FAILURE = "421";  // Temporary error, server might be overloaded
    public static final String MAX_RCPT_REACHED = "452";// Maximum number of recipients has been reached
    public static final String INVALID_RCPT = "550";    // The destination address is invalid
    public static final String BANNED_FROM_SERVER = "554"; // Permanent error, used instead of 220 for the blacklisted hosts
    
}
