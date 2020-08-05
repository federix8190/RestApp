package py.com.konecta.services;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.konecta.model.csj.AccesoSistema;
import py.com.konecta.model.csj.Departamento;
import py.com.konecta.model.nucleo.Consumo;

@Stateless
public class TestServices {

	@PersistenceContext(unitName = "CSJ_POI_DS")
    protected EntityManager em;
	
	@PersistenceContext(unitName = "dwhDS")
    protected EntityManager emDWH;
	
	Logger log = Logger.getLogger(this.getClass().getCanonicalName());
	
	public List<Departamento> getDepartamento() {
		
		String sql = "SELECT a from Departamento a ";
        Query query = em.createQuery(sql);
        List<Departamento> res = query.getResultList();
        return res;        
	}
	
	public List<Consumo> getConsumo() {
		
		String sql = "SELECT a from Consumo a ";
        Query query = emDWH.createQuery(sql);
        List<Consumo> res = query.getResultList();
        return res;        
	}
	
	@Schedule(hour = "15", minute = "*/15")
	public void limpiarDatos() throws InterruptedException, UnknownHostException {
		
		InetAddress ip = InetAddress.getLocalHost();
		log.info("limpiar captchas : " + ip.getHostName());
	}
	
	@Asynchronous
	public void enviarCorreo(String correoDestinatario, String asunto, String mensaje) {
		
		
//		final String host = config.get("MAIL_SMTPS_HOST");
//        final String port = config.get("MAIL_SMTPS_PORT");
//        final String username = config.get("MAIL_SMTPS_USER");
//        final String password = config.get("MAIL_SMTPS_PASS");
        
		final String host = "mail.konecta.com.py";
        final String port = "25";
        final String username = "no-reply-poi@konecta.com.py";
        final String password = "R3Pl1P01#2020.";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", port);
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestinatario));
            message.setSubject(asunto);
            message.setContent(mensaje, "text/html; charset=utf-8");

            Transport.send(message);

            System.err.println("Correo enviado a : " + correoDestinatario);

        } catch (MessagingException e) {
        	System.err.println("Error al enviar correo a " + correoDestinatario + " : " + e.getMessage());
        }
	}
}
