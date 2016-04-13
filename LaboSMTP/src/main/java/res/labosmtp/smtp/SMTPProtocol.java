/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package res.labosmtp.smtp;

/**
 *
 * @author Antony
 */
public class SMTPProtocol {
    
    public static final String EHLO = "EHLO ";
    public static final String FROM = "MAIL FROM: ";
    public static final String RCPT = "RCPT TO: ";
    public static final String DATA = "DATA";
    public static final String ENDMSG = "\n.\n";
    
    public static final String CONNECTION_OK = "220";
    public static final String COMMAND_OK = "250";
    public static final String SEND_DATA_OK = "354";
    public static final String SERVER_FAILURE = "421";
    public static final String MAX_RCPT_REACHED = "452";
    public static final String INVALID_RCPT = "550";
    public static final String BANNED_FROM_SERVER = "554";
    
//    220	Premier code envoyé par le serveur lorsque la connexion s'est effectuée avec succès.
//    250	Confirmation de commande acceptée.
//    354	Réponse à la commande DATA. Le serveur attend les données du corps du message. Le client indique la fin du message par un point seul sur une ligne : <CR><LF>.<CR><LF>
//    421	Échec temporaire au niveau de la connexion. Il se peut que le serveur soit surchargé, qu'il limite le nombre de connexions en provenance d'une même adresse IP ou que le service soit indisponible.
//    452	Échec temporaire : nombre de destinataires maximum atteint.
//    550	Échec permanent. La boîte aux lettres n'existe pas ou l'adresse du destinataire est invalide.
//    554	Échec permanent au niveau de la connexion : utilisé à la place du code 220 pour les hôtes sur liste noire.
    
}
