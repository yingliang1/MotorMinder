package reminder;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import models.Car;
import models.Service;
import models.User;

public class ReminderEmail {

  public void generateAndSendEmail(User user, Car car, Service service) {

    // set mail server properties
    Properties mailServerProperties = System.getProperties();
    mailServerProperties.put("mail.smtp.port", "587");
    mailServerProperties.put("mail.smtp.auth", "true");
    mailServerProperties.put("mail.smtp.starttls.enable", "true");

    // get a session for mail server
    Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
    MimeMessage generateMailMessage = new MimeMessage(getMailSession);

    try {
      // set the fields
      generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
      generateMailMessage.setSubject("MotorMinder - Vehicle service due soon!");
      String emailBody = getEmailBodyHtml(user, car, service);
      generateMailMessage.setContent(emailBody, "text/html");

      // this is an email, so session transport is smtp
      Transport transport = getMailSession.getTransport("smtp");

      // connect and send
      transport.connect("smtp.gmail.com", "motorminder4230", "CS4230JavaMarshWeber");
      transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
      transport.close();
    } catch (MessagingException e) {
      System.out.println("ERROR SENDING REMINDER EMAILS");
      e.printStackTrace();
    }
  }

  private String getEmailBodyHtml(User user, Car car, Service service) {
    return "Hi " + user.getFirstName() + "!<br><br>Your " + car.getYear() + " " + car.getMake() + " " + car.getModel()
        + " has a(n) " + service.getType() + " due within the next week, on " + service.getService_date()
        + "<br><br> Regards, <br>MotorMinder";
  }
}