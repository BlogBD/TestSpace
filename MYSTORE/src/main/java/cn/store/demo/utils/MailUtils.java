package cn.store.demo.utils;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException, IOException {
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		props.load(MailUtils.class.getClassLoader().getResourceAsStream("mail.properties"));
		//设置发送的协议
		//props.setProperty("mail.transport.protocol", "SMTP");
		
		//设置发送邮件的服务器
		props.setProperty("mail.host", props.getProperty("mailhost"));
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//设置发送人的帐号和密码
				return new PasswordAuthentication(props.getProperty("sendusername"), props.getProperty("sendpassword"));
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容

		Message message = new MimeMessage(session);

		//3.设置发送者
		message.setFrom(new InternetAddress(props.getProperty("sendusername")));

		//4.设置发送方式与接收者
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); 

		//5.设置邮件主题
		message.setSubject("你在耍手机");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		String url="http://localhost:8080/MYSTORE/UserServlet?method=active&code="+emailMsg;
		String content="<h1>来自购物天堂的激活邮件!激活请点击以下链接!</h1><h3><a href='"+url+"'>"+url+"</a></h3>";
		//6.设置邮件内容
		message.setContent(content, "text/html;charset=utf-8");

		// 7.创建 Transport用于将邮件发送
		Transport.send(message);
	}
	/*public static void main(String[] args) throws AddressException, MessagingException, IOException {
		for (int i= 0;i<10;i++){
			//MailUtils.sendMail("787512757@qq.com", "abcdefg");
			new Thread(()->{
				try {
					MailUtils.sendMail("787512757@qq.com", "曾敏你个大美女");
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}*/
}
