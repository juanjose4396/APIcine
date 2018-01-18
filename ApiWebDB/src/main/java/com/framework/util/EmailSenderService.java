package com.framework.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

public class EmailSenderService {

	private final Properties properties = new Properties();

	/**
	 * determina si hace autorizacion al servidor de correo-
	 */
	public static final String AUTH_MAIL = "mail.smtp.auth";

	// Propiedades correo Masivo
	/**
	 * Cadena de texto que representa la etiqueta del protocolo de transporte
	 * utilizado.
	 */
	public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";

	/**
	 * Cadena de texto que representa la etiqueta del puerto del socket factory
	 */
	public static final String MAIL_SMTP_SOCKETFACTORY_PORT = "mail.smtp.socketFactory.port";

	/**
	 * Cadena de texto que representa la etiqueta de la clase del socket factory
	 */
	public static final String MAIL_SMTP_SOCKETFACTORY_CLASS = "mail.smtp.socketFactory.class";

	/**
	 * Puerto smtp.
	 */
	public static final String MAIL_SMTP_PORT_MASIVO = "mail.port.masivo";

	public static final String MAIL_SMTP_HOST = "mail.host";

	private String password;

	private Session session;

	private void init() {
		//
		// properties.put("mail.smtp.host", "mail.gmail.com");
		// properties.put("mail.smtp.starttls.enable", "true");
		// properties.put("mail.smtp.port", 25);
		properties.put("mail.smtp.mail.sender", "monedero@orussystem.com");
		properties.put("mail.smtp.user", "monedero@orussystem.com");
		// properties.put("mail.smtp.auth", "true");
		Properties props = new Properties();
		properties.put(MAIL_TRANSPORT_PROTOCOL, "smtp");
		properties.put(MAIL_SMTP_HOST, "smtp.zoho.com");
		properties.put(MAIL_SMTP_SOCKETFACTORY_PORT, "465");
		properties.put(MAIL_SMTP_SOCKETFACTORY_CLASS, "javax.net.ssl.SSLSocketFactory");
		properties.put(AUTH_MAIL, true);
		properties.put(MAIL_SMTP_PORT_MASIVO, "465");
		// session = Session.getDefaultInstance(properties);
		session = Session.getDefaultInstance(properties, null);
	}

	public String sendEmail(String EmailSend) {

		init();
		try {
			String code = generateOTP();
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(EmailSend));
			message.setSubject("Codigo Monedero");
			message.setText("Su codigo temporal es: " + code);
			Transport t = session.getTransport();
			t.connect((String) properties.get("mail.smtp.user"), "1q2w3e4r5t*");
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			return code;
		} catch (Exception me) {
			me.printStackTrace();
			return "0001";
		}

	}

	public String generateOTP() {
		Random rand = new Random();
		String n = String.valueOf(rand.nextInt(9999) + 1);
		return UtilString.paddindRea(n, 4);
	}

}