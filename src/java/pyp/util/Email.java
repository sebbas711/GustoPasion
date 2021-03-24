/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyp.util;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    private static final String USER = "gustoypasion2020@gmail.com";
    private static final String PASS = "gustoypasion";

    public static void sendModificacion(String para, String Nombres, String nombUsu, String clave) {
        String subject = "Mensaje de recuperacion de clave... Restaurante Gusto y Pasión";
            java.util.Date fecha = new Date();

            String messageContent =
                    "<center><img src='https://www.sri.gob.ec/NoticiasSriPortlet/image/?idCodigo=301&expira=1' title='Restaurante Gusto y Pasión'></center>"
                    + "<h3> Actualizacion de datos en Banco-Pedagogico. "
                    + Nombres
                    + "</h3>"
                    + "Datos de Ingreso: "
                    + "<h4> Nombre Usuario : "
                    + nombUsu
                    + "</h4>"
                    + "<h4> Documento Usuario : "
                    + clave
                    + " </h4>"
                    + "Ultima Modificacion"
                    + fecha.getHours() + ":" + fecha.getMinutes() + ":" + fecha.getSeconds() + " - "
                    + fecha.getDay() + "/" + fecha.getMonth() + "/" + fecha.getYear();

        send(para, subject, messageContent);
    }

    public static void sendClaves(String para, String Nombres, String nombUsu, String clave) {
        String subject = "Recordatorio Claves Restaurante Gusto y Pasión";
        String messageContent
                = "<center><img src='https://lh3.googleusercontent.com/xWcRl0K6wXLrctQR27JVeSAEfPQQe4IHseuNSrANRrHObBQBxwDsub5Th_cL7RyayAplGqRoEleMte2qDrsdMjE4CbM0XSYXqVYaPPqptTFj-thiCGkB1uR7ue_vcwOt8gF9WNOpjjeXHTqLBIlNBcqd1g99lic1AspS-rQZO3USeGQQVAWTbvSbBBbFVYYx05FZP1reL02PHklERWDqAt-ej0r5BNe0De_8tB9-AKxCcnaMdGGl-skhNC9YNsi0yom0ZH7p-Q0WpUhmdBAUu2RSBL3xzP8laZtPfTHvMvR3smzNdRxY9PqOJKSaA-vXRWW7LX4W2XkodjJmZ5O3cwVpSI6aEhE-yGAtuKFa3l4p0tEEPYaJvHZw0e2ESaDlaH3oZ9pTIYQ7uFjLIZvVeJqLGsu0pLIOR16Xtp2GVf1siYruwNRYlZ6jaNMvTomnnIZPnAdyh-pPVWqm-jXf1ZB8eKVoYlqeyW-QbF2bsze0l-JjRR2-hPOvTILZMvQkFtJtA07g2v2nx0nWel0lYWMCXzs2KHrCr0mvCXyGke5pENPv7DNlLMyZyeYMRvvh7_KFM8UGKvgMOw89nTfF1XAzcE0D7-PXuUa9_0iiwJqez_bHrDSRUbb7N72a77dHyQw6kcsIlulVjewMzluC1QM2vQCACZtR_cZpmL5Pv-JaPQmQV6iN0PXhXJkDKQ=w1080-h605-no?authuser=0' title='Restaurante Gusto y Pasión'></center>"
                + "<h3> Recordatorio Claves. "
                + Nombres
                + "</h3>"
                + "Datos de Ingreso: "
                + "<h4> Correo Usuario : "
                + nombUsu
                + "</h4>"
                + "<h4> Clave Usuario : "
                + clave
                + " </h4>";
        
        send(para, subject, messageContent);

    }

    public static void sendBienvenido(String para, String nombres, String asunto, String urlImgPromo) {
        String subject = "Restaurante Gusto y Pasión - ".concat(asunto);
        String messageContent
                = "<center><img src='" + urlImgPromo + "' title='Restaurante Gusto y Pasión'></center>"
                + "<h3> Hola " + nombres + "</h3>"
                + "Reciba un cordial saludo de Restaurante Gusto y Pasión"
                + "<h4> " + asunto + "</h4>"
                + "<h4> Te esperamos pronto </h4>";

        send(para, subject, messageContent);
    }

    public static void send(String to, String subject, String messageContent) {
        try {
            Session session = buildSession();
            //2nd paso)compose message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(messageContent, "text/html;");
            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    private static Properties getPropsConfig() {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
        return props;
    }

    private static Session buildSession() {
        return Session.getInstance(getPropsConfig(), new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER, PASS);
            }
        });
    }

}
