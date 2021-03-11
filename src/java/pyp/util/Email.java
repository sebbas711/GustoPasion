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
import javax.servlet.http.Part;

public class Email {

    public static void sendModificacion(String para, String Nombres, String nombUsu, String clave) {
        final String user = "gustoypasion2020@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "gustoypasion";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Mensaje de recuperacion de clave... Restaurante Gusto y Pasión");
            java.util.Date fecha = new Date();

            message.setContent(
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
                    + fecha.getDay() + "/" + fecha.getMonth() + "/" + fecha.getYear(), "text/html");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void sendClaves(String para, String Nombres, String nombUsu, String clave) {
        final String user = "gustoypasion2020@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "gustoypasion";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Recordatorio Claves Restaurante Gusto y Pasión");

            message.setContent(
                    "<center><img src='https://lh3.googleusercontent.com/xWcRl0K6wXLrctQR27JVeSAEfPQQe4IHseuNSrANRrHObBQBxwDsub5Th_cL7RyayAplGqRoEleMte2qDrsdMjE4CbM0XSYXqVYaPPqptTFj-thiCGkB1uR7ue_vcwOt8gF9WNOpjjeXHTqLBIlNBcqd1g99lic1AspS-rQZO3USeGQQVAWTbvSbBBbFVYYx05FZP1reL02PHklERWDqAt-ej0r5BNe0De_8tB9-AKxCcnaMdGGl-skhNC9YNsi0yom0ZH7p-Q0WpUhmdBAUu2RSBL3xzP8laZtPfTHvMvR3smzNdRxY9PqOJKSaA-vXRWW7LX4W2XkodjJmZ5O3cwVpSI6aEhE-yGAtuKFa3l4p0tEEPYaJvHZw0e2ESaDlaH3oZ9pTIYQ7uFjLIZvVeJqLGsu0pLIOR16Xtp2GVf1siYruwNRYlZ6jaNMvTomnnIZPnAdyh-pPVWqm-jXf1ZB8eKVoYlqeyW-QbF2bsze0l-JjRR2-hPOvTILZMvQkFtJtA07g2v2nx0nWel0lYWMCXzs2KHrCr0mvCXyGke5pENPv7DNlLMyZyeYMRvvh7_KFM8UGKvgMOw89nTfF1XAzcE0D7-PXuUa9_0iiwJqez_bHrDSRUbb7N72a77dHyQw6kcsIlulVjewMzluC1QM2vQCACZtR_cZpmL5Pv-JaPQmQV6iN0PXhXJkDKQ=w1080-h605-no?authuser=0' title='Restaurante Gusto y Pasión'></center>"
                    + "<h3> Recordatorio Claves. "
                    + Nombres
                    + "</h3>"
                    + "Datos de Ingreso: "
                    + "<h4> Correo Usuario : "
                    + nombUsu
                    + "</h4>"
                    + "<h4> Clave Usuario : "
                    + clave
                    + " </h4>", "text/html");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void sendBienvenido(String para, String Nombres, String Asunto, String ImgPromo) {
        final String user = "gustoypasion2020@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "gustoypasion";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Restaurante Gusto y Pasión");

            message.setContent(
                    "<center><img src='https://lh3.googleusercontent.com/UjT-jnUUqhKE4ISNf-vsYFhY3459dFpXwAal2qMQfnnQ_GTVxxhywT_RPPZx0KVfSekyUbEgeVqj9sFEwxH9vwAds4jxcODqIbuoJW4QdnZjzEJLLw7UsPQjdpTZb_hEUu6KqFpCes9GSvRO3iQ7VKD3LaF_is4jVTftG51Mu3mPnZZXcrz41kQucWNcHDnhv-CFeZsyRrNnDd1cxnfG7idSwikTBEQVYVKJUdiWOLLCdlX2GV4M3ZEDooc_S53wkp55aI250nNzFuRFBknDDdR5TBNHpgb7YfN8oJsHfa3bH8F50EZceWC6kAQsmgwdG1sNNLXB6CQdK9RWGeuuQxJORb54uZse-3fpaiKM2w2Hk0_T4pd5j13VRCj1c0fYz5LCNDcCMYUDCHqjhi1i9mRCj2RZnlmc4W2HppGw-tyn4BDQ61PLk5CnZrs_XMQA7h7jIupd2etlrqY3VEqi4rlRXdkM0LzRR6Xr729iJD81abDJvstLCgjJIUB9ZBngTPYvie4nKASGcotsAuFAHGtvsnQ2yoUtBZKaZxtN-7KnKMYbiRifAWmUMCXND1u25xNBeulRK5r-hlq4CJ_Ax2b6RAiPbM5U8cc2KTjZa0N2DTfetayOcjcofgYEgcCfrxAF4OUZ29XgdNJRRhkAPL1lohjT2R51Ag_B5yd57bXfVDnrw5FRnyTvfBb0YA=w763-h610-no?authuser=0' "
                    + "title='Restaurante Gusto y Pasión'></center>"
                    + "<h3> Hola "
                    + Nombres
                    + "</h3>"
                    + "Reciba un cordial saludo de Restaurante Gusto y Pasión"
                    + "<h4> "
                    + Asunto
                    +"</h4>"
                    + "<h4> Te esperamos pronto  "
                    + ImgPromo
                    + " </h4>", "text/html");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void send(String para, String sujeto, String mensaje) {

        final String user = "Restaurante Gusto y Pasión";//cambiará en consecuencia al servidor utilizado
        final String pass = "gustoypasion";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject(sujeto);
            message.setContent(mensaje, "text/html;");
            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
