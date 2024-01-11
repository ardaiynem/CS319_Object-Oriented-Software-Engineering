// package com.bilport.demo.util;

// import com.bilport.demo.domain.model.Course;
// import com.bilport.demo.domain.model.Evaluator;
// import com.bilport.demo.domain.model.Report;
// import com.bilport.demo.domain.model.Student;
// import com.bilport.demo.domain.model.Supervisor;
// import com.bilport.demo.domain.model.User;
// import com.bilport.demo.repository.EvaluatorRepository;
// import com.bilport.demo.repository.ReportRepository;
// import com.bilport.demo.repository.StudentRepository;
// import com.bilport.demo.repository.SupervisorRepository;
// import com.bilport.demo.repository.TARepository;
// import com.bilport.demo.repository.AdminRepository;
// import com.bilport.demo.repository.CourseRepository;
// import com.bilport.demo.repository.SuperadminRepository;
// import com.bilport.demo.repository.UserRepository;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Date;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.stereotype.Component;

// @Component
// public class DbInit implements CommandLineRunner {

//     @Autowired
//     private final CourseRepository repository;

//     public DbInit(CourseRepository repository) {
//         this.repository = repository;
//     }

//     // @Override
//     // public void run(String... args) throws Exception {
//     // repository.save(new Evaluator("20022002", "pass2", getAuthorities(new
//     // ArrayList<String>( Arrays.asList( new String[]{"ROLE_USER"}))),
//     // "Eray", "Tüzün", "tuzun.eray@gmail.com", new String[]{"22003131"}));
//     // }

//     // @Override
//     // public void run(String... args) throws Exception {
//     // //repository.save(new Report("heee5e", "adminBoy"));
//     // repository.save(new Supervisor("00223131", "pass1", getAuthorities(new
//     // ArrayList<String>( Arrays.asList( new String[]{"ROLE_USER"}))),
//     // "NameOfVisor", "pipi@at.com", "partTimeBitch", "Bilkent", "CS", "22003131"));

//     // }

//     // @Override
//     // public void run(String... args) throws Exception {
//     // repository.save(new User("adminBoy", "pass1", getAuthorities(new
//     // ArrayList<String>( Arrays.asList( new String[]{"ROLE_ADMIN"})))));
//     // repository.save(new User("userBoy", "pass2", getAuthorities(new
//     // ArrayList<String>( Arrays.asList( new String[]{"ROLE_USER"})))));
//     // }

//     // @Override
//     // public void run(String... args) throws Exception {
//     //     repository.save(new Student("22003131", "pass1",
//     //             getAuthorities(new ArrayList<String>(Arrays.asList(new String[] { "ROLE_USER" }))), "Haktan", "Götten",
//     //             "gotten@mail.com", "20022002", "20012001",
//     //             "00223131", 0, 0));
//     // }

//     @Override
//     public void run(String... args) throws Exception {
//         repository.save(new Course("CS299", "Summer Training 1", ""));
//         repository.save(new Course("CS399", "Summer Training 2", ""));
//     }

//     private List<GrantedAuthority> getAuthorities(List<String> roles) {

//         List<GrantedAuthority> authorities = new ArrayList<>();
//         roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
//         return authorities;

//     }
// }
