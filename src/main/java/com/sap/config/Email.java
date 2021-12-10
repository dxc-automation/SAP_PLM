package com.sap.config;

import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import static com.sap.config.GeneralTestConfig.commons;
import static com.sap.config.TestNGListener.failedTests;
import static com.sap.config.TestNGListener.passedTests;
import static com.sap.properties.FilePaths.*;
import static com.sap.utilities.FileUtility.getDate;


public class Email {

	public static void sendEmail(String zipName) throws MessagingException, GeneralSecurityException {
		final String date 		= getDate();
		final String attachment = zipName;
		final String username   = "vsuddala@worldbankgroup.org";
		final String password   = "<<Password>>";
		final String host = "lmail.worldbank.org";
		final String port = "25";
		final String from = "PLM_QA_TEAM@worldbankgroup.org";
		final String to	  = "ppopov@worldbankgroup.org";
		final String cc   = "vsuddala@worldbankgroup.org";


		// Create configuration preset for email send
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");

		// Get the session object
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// Create empty message object where to store information
		MimeMessage message = new MimeMessage(session);
		Address fromAddress =  new InternetAddress(from);
		message.setFrom(fromAddress);
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
		message.setSubject("SAP IVA Automation");

		Multipart multipart = new MimeMultipart("related");

		int allTests = passedTests + failedTests;

		// Add text message
		BodyPart textBodyPart = new MimeBodyPart();
		String body = "<html><head><style>p {font-family: cursive; padding: 10px; } .tab{tab-size: 2;}</style></head>" +
				"<body><br><p>Hello Team,<br>Please find attached SAP automation tests report.</p><br>" +
				"<p>Number of Executed Tests: &nbsp; &nbsp;" + allTests + "<br>" +
				"Passed Tests: &nbsp; &nbsp;" + passedTests + "<br>" +
				"Failed Tests: &ensp; &nbsp;" + failedTests + "</p>" +
				"<br><br></body></html><img src=\"cid:image\">";
		textBodyPart.setContent(body, "text/html");
		multipart.addBodyPart(textBodyPart);

		// Add image inline
		BodyPart imageBodyPart = new MimeBodyPart();
		DataSource fds = new FileDataSource(path + "/src/main/resources/logo.png");
		imageBodyPart.setDataHandler(new DataHandler(fds));
		imageBodyPart.setHeader("Content-ID", "<image>");
		imageBodyPart.setDisposition(MimeBodyPart.INLINE);
		multipart.addBodyPart(imageBodyPart);

		// Add attachment
		BodyPart attachmentPart = new MimeBodyPart();
		String file = attachment;
		DataSource source = new FileDataSource(file);
		attachmentPart.setDataHandler(new DataHandler(source));
		attachmentPart.setFileName(attachment);
		multipart.addBodyPart(attachmentPart);

		// Send email
		message.setContent(multipart);
		System.out.println("Sending message");
		Transport.send(message);
		System.out.println("Done");
	}
}
