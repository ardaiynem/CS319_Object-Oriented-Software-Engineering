package com.bilport.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bilport.demo.controller.MailController;


@SpringBootApplication
public class DemoApplication {

	@Autowired
	private  MailController mailer;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
			
	}
	
	/*
	@EventListener(ApplicationReadyEvent.class)
	public void sendMail(){
		mailer.sendEmail("egecenberci@gmail.com","Test subject","Test body <p/> newline character test 3");
		
	}
 	 
	@EventListener(ApplicationReadyEvent.class)
	public static void mailTest(){
		System.out.println("Test objects being created.");
		String[] strList = {};
		TA taTest = new TA("TA name", "TA pasword", null, "ta fullname", "egecenberci@gmail.com");
		Supervisor supervisorTest = new Supervisor("super id", "super password", null, "super full name", "egecenberci@gmail.com", "super working position", "atilim", "CS", "123");
		Student studentTest = new Student("student username", "student password", null, "student name", "student surname", "egecenberci@gmail.com", "123", "123", "123", 0, 0);
		Evaluator evaluatorTest = new Evaluator("evaluator username", "evaluator password", null, "evaluator name" , "evaluator surname", "egecenberci@gmail.com", strList, 0);
		Admin adminTest = new Admin("admin username", "admin password", null, "admin name", "admin surname", "egecenberci@gmail.com");
		System.out.println("All test objects created.");
	}
	*/
}
