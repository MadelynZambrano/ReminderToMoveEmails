/*************************************************************************
 *
 *  Author: Maddy Zambrano 
 *  Date: November 13, 2023
 *  
 *  Description: The goal for this code is to send an email reminder to the recipient
 *  to move their body. I adjusted the code below for a final project for my Yoga Class; 
 *  it sends the recipient a reminder to move and do a yoga sequence upon receiving the email. 
 *  The below code used Java, HTML, and JavaMail API to implement the necessary actions for the 
 *  code to send the message. 
 *   
 *  References:  
 *  https://javaee.github.io/javamail/docs/api/
 *  https://stackoverflow.com/questions/5125107/java-class-cannot-be-resolved-to-a-type
 *  https://help.yahoo.com/kb/SLN4724.html?guccounter=1&guce_referrer=aHR0cHM6Ly93d3cuZ29vZ2xlLmNvbS8&guce_referrer_sig=AQAAAI9Tl9nJjeOtPVUZsBW3yKO-z1x_r5cCDv2tXyiEeaqtTHZ0Luy8q6DiAo0VEEWHRVNVpTP3f6mi9G7-PkLJN5_TkRHQZHsWBgFwSkJJ54o7mFcDbA5pQAv5CbtfK-7v8JwFBETiOgCqnG_m6Hq1S8ALEi-tBaZ-kql3h-TBOvMQ
 *  https://stackoverflow.com/questions/5125107/java-class-cannot-be-resolved-to-a-type#:~:text=This%20means%20that%20you%20have,the%20compilation%20error(s).
 *  https://stackoverflow.com/questions/30005888/java-simple-email-program-in-eclipse 
 *  https://stackoverflow.com/questions/5068827/how-do-i-send-an-html-email
 *  https://www.w3schools.com/tags/tag_body.asp#:~:text=Definition%20and%20Usage,%2C%20tables%2C%20lists%2C%20etc. 
 *  https://www.geeksforgeeks.org/send-email-using-java-program/
 *  
 *  Private String Variables:
 *  private String from;
 *  private String to;
 *  private String subject;
 *  private String messageBody;
 *  private String host;
 *  
 *  Private Properties:
 *  private Properties properties 
 *  
 *  MimeMessage and Related Objects:
 *  private MimeMessage message;
 *   private BodyPart messageBodyPart;
 *   private Multipart multipart;
 *   
 *   Constants in 'SMTPAuthenticator' Class
 *   private static final String SMTP_AUTH_USER 
 *   private static final String SMTP_AUTH_PASSWORD
 *************************************************************************/
package javax.mail;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.*;


public class MoveEmailReminders {
//initiating local variables.
    private String from = "******@yahoo.com";
    private String to = "*****@yahoo.com";
    private String subject;
    private String messageBody;
    private String host;

    private Properties properties;

    private MimeMessage message;
    private BodyPart messageBodyPart;
    private Multipart multipart;

    private Authenticator authenticator;
//MoveEmailReminders class defined the local variables and sends a message to the servers, so that the email may send. 
    public MoveEmailReminders () {
        subject = "Time To Get Up And Move!";
        messageBody = "<html><body>You have been sitting for too long!<br>" 
        		+ "Get up and do your favorite yoga pose for 1 minute!<br>"
        		+"OR run through 1 Sun Salutation A and 1 Sun Salutation B!<br>"
                +"Sun Salutaion A Steps:<br>"
                + "Begin in Mountain Pose<br>" 
                + "Uttanasana to Flat Back<br>" 
                + "Plank Pose<br>"
                +"Knees, Chest, and Chin or Chaturanga Dandasana<br>" 
                + "Cobra or Upward Facing Dog<br>" 
                + "Downward Facing Dog<br>"
                + "Step or Jump to a Forward Bend<br>"
                + "Finish the Sun Salutation<br>"
                + "Sun Salutaion B Steps:<br>"
                + "Utkatasana Chair pose<br>"
                + "Uttanasana Standing forward fold<br>"
                + "Ardha Uttanasana Half standing forward fold<br>"
                + "Chaturanga Dandasana Four-limbed staff pose low press up<br>"
                + "Urdhva Mukha Svanasana Upward facing dog pose<br>"
                + "Adho Mukha Svanasana Downward facing dog<br>"
                + "Virabhadrasana I Warrior 1<br>"
                + "Chaturanga Dandasana<br>"
                + "Urdhva Mukha Svanasana Dpward facing dog<br>"
                + "Adho Mukha Svanasana Downward facing dog<br>"
                + "Virabhadrasana 1 Warrior 1 on the other side<br>"
                + "Ardha Uttanasana Half standing forward fold<br>"
                + "Uttanasana Standing forward fold<br>"
                + "Utkatasana Chair pose</body></html>";
                host = "smtp.mail.yahoo.com";

                

        authenticator = new SMTPAuthenticator ();
        properties = System.getProperties ();
        properties.put ( "mail.smtp.host", host );
        properties.put ( "mail.smtp.starttls.enable", true );
        properties.put ( "mail.smtp.port", 587 );
        properties.put ( "mail.smtp.auth", true );
    }

    private void sendMail ( String from, String to,
                    String subject, String messageBody) {
        try {
            Session session = Session.getDefaultInstance ( properties, authenticator );
            message = new MimeMessage ( session );
            message.setFrom ( new InternetAddress ( from ) );
            message.addRecipient ( Message.RecipientType.TO, new InternetAddress ( to ) );
            message.setSubject ( subject );

            // Set the message body
            message.setContent (messageBody, "text/html");

            Transport.send ( message );
            System.out.println ( "Message sent successfully..." );
        } catch ( Exception me ) {
            me.printStackTrace ();
        }
    } 

    private void performTask () {
        sendMail ( from, to, subject, messageBody);
    }

    public static void main(String[] args) {
 
        new MoveEmailReminders().performTask();
    }
}


//Will perform authentication when the SMTP server requires it.
class SMTPAuthenticator extends Authenticator {

    private static final String SMTP_AUTH_USER = "*******@yahoo.com";
    private static final String SMTP_AUTH_PASSWORD = "*******";

    public PasswordAuthentication getPasswordAuthentication () {
        String username = SMTP_AUTH_USER;
        String password = SMTP_AUTH_PASSWORD;

        return new PasswordAuthentication( username,  password );
    }
}