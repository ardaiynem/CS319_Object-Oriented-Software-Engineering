package com.bilport.demo.controller;


import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.bilport.demo.domain.model.Admin;
import com.bilport.demo.domain.model.Evaluator;
import com.bilport.demo.domain.model.Student;
import com.bilport.demo.domain.model.Supervisor;
import com.bilport.demo.domain.model.TA;

@Service
public class MailController {
   
    @Autowired
    private JavaMailSender mailSender;

    private final String term = "2023 Spring Semester";

    /**
     * Constructor in case a MailController is created using the new keyword
     */
    public MailController(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("SATANYAZILIM@gmail.com");
        mailSender.setPassword("lmohzxngbmzmxyqt");
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        
        this.mailSender = mailSender;   
    }

    /**
     * Main simple email sender method, utilized in other methods of the controller
     * @param to the reveiver's email address
     * @param subject the subject of the email
     * @param text the body of the email
     */
    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            mailSender.send(message);
            System.out.println("Account Creation Mail Sent...");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends account creation email to the student object
     * @param s student object the email is to be sent to
     */
    public void sendRegistrationMailStudent(Student s){
        String subject = "Bilport 2023 Spring Semester Account Info";
        String body = "Your account as a Student has been created in the Bilport system for " + term + ". Your username and password can be found below.<p/>";
        String info = "<p/>Username: " + s.getUserName() + "<p/>Password: " + s.getUserPassword() + "<p/>";
        String signature = "<p/>Bilport Team";
        sendEmail(s.getStudentEmail(), subject, body + info + signature);
    }

    /**
     * Sends account creation email to the evaluator object
     * @param e evaluator object the email is to be sent to
     */
    public void sendRegistrationMailEvaluator(Evaluator e){
        String subject = "Bilport 2023 Spring Semester Account Info";
        String body = "Your account as an Evaluator has been created in the Bilport system for " + term + ". Your username and password can be found below.<p/>";
        String info = "<p/>Username: " + e.getUserName() + "<p/>Password: " + e.getUserPassword() + "<p/>";
        String signature = "<p/>Bilport Team";
        sendEmail(e.getEvaluatorEmail(), subject, body + info + signature);
    }

    /**
     * Sends account creation email to the admin object
     * @param a admin object the email is to be sent to
     */
    public void sendRegistrationMailAdmin(Admin a){
        String subject = "Bilport 2023 Spring Semester Account Info";
        String body = "Your account as an Admin has been created in the Bilport system for " + term + ". Your username and password can be found below.<p/>";
        String info = "<p/>Username: " + a.getUserName() + "<p/>Password: " + a.getUserPassword() + "<p/>";
        String signature = "<p/>Bilport Team";
        sendEmail(a.getAdminEmail(), subject, body + info + signature);
    }
     
    /**
     * Sends account creation email to the TA object
     * @param t TA object the email is to be sent to
     */
    public void sendRegistrationMailTA(TA t){
        String subject = "Bilport 2023 Spring Semester Account Info";
        String body = "Your account as a TA has been created in the Bilport system for " + term + ". Your username and password can be found below.<p/>";
        String info = "<p/>Username: " + t.getUserName() + "<p/>Password: " + t.getUserPassword() + "<p/>";
        String signature = "<p/>Bilport Team";
        sendEmail(t.getTaEmail(), subject, body + info + signature);
    }

    /**
     * Sends account creation email to the supervisor object
     * @param s supervisor object the email is to be sent to
     */
    public void sendRegistrationMailSupervisor(Supervisor s){
        String subject = "Bilport 2023 Spring Semester Account Info";
        String body = "Your account as a Supervisor has been created in the Bilport system for " + term + ". Your username and password can be found below.<p/>";
        String info = "<p/>Username: " + s.getUserName() + "<p/>Password: " + s.getUserPassword() + "<p/>";
        String additionalInfo = " You can use your login information to evaluate your student's internship on the Bilport website.";
        String signature = "<p/>Bilport Team";
        sendEmail(s.getSupervisorEmail(), subject, body + info + additionalInfo + signature);
    }

    /*
    public void sendMail(){
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception{
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setFrom("test@mail.com");
                message.setTo("egecenberci@gmail.com");
                message.setSubject("Mail subject");
                message.setText("some text <img src='cid:logo'>", true);
                //message.addInline("logo", new ClassPathResource("img/logo.gif"));
                //message.addAttachment("myDocument.pdf", new ClassPathResource("uploads/document.pdf"));
                
                System.out.println("Mail sent hopefully");
            }
        };
    }
     */
 
}
