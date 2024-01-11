package com.bilport.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bilport.demo.domain.model.Superadmin;
import com.bilport.demo.domain.model.Admin;
import com.bilport.demo.domain.model.Evaluator;
import com.bilport.demo.domain.model.PasswordGenerator;
import com.bilport.demo.domain.model.Student;
import com.bilport.demo.domain.model.TA;
import com.bilport.demo.service.SuperadminService;
import com.bilport.demo.service.AdminService;
import com.bilport.demo.service.StudentService;
import com.bilport.demo.service.TAService;
import com.bilport.demo.service.EvaluatorService;

@RestController
@RequestMapping(value = "/superadmins")
public class SuperadminController {
    @Autowired
    SuperadminService superadminService;
    @Autowired
    AdminService adminService;
    @Autowired
    StudentService studentService;
    @Autowired
    TAService taService;
    @Autowired
    EvaluatorService evaluatorService;
    //Declaration for mail sending capabilities
    @Autowired
    MailController mailer;

    @ResponseBody
    @GetMapping(value = "/{id}")
    public Superadmin getSuperadmin(@PathVariable("id") String id) {
        return superadminService.findById(id);
    }

    // @GetMapping(value = "/{id}")

    // public List<Student> getAllStudents(@PathVariable("id") String id) {
    public List<Admin> getAllAdmins() {
        return adminService.getAdmins();
    }

    // public List<Student> getAllStudents(@PathVariable("id") String id) {
    public List<Student> getAllStudents() {
        return studentService.getStudents();
    }

    // public List<TA> getAllTAs(@PathVariable("id") String id) {
    public List<TA> getAllTAs() {
        return taService.getTAs();
    }

    // public List<Evaluator> getAllTAs(@PathVariable("id") String id) {
    public List<Evaluator> getAllEvaluators() {
        return evaluatorService.getEvaluators();
    }

    @PostMapping(value = "/studentImport")
    public ResponseEntity<String> uploadExcelFileStudent(@RequestParam("file") MultipartFile file) {
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                for (Row row : sheet) {
                    // Assuming data starts from the second row
                    if (row.getRowNum() > 0) {
                        if (row.getCell(0).getStringCellValue().isEmpty() 
                        || row.getCell(1).getStringCellValue().isEmpty()
                        || row.getCell(2).getStringCellValue().isEmpty()
                        || row.getCell(3).getStringCellValue().isEmpty()
                        || row.getCell(4).getStringCellValue().isEmpty()) {
                            return new ResponseEntity<>("An empty cell is found, check the file", HttpStatus.OK);
                        }
                        Student student = new Student();
                        student.setUserName(row.getCell(0).getStringCellValue());
                        PasswordGenerator passwordGenerator = new PasswordGenerator();
                        student.setUserPassword(passwordGenerator.generatePassword());
                        student.setUserAuthorities(new ArrayList<GrantedAuthority>() {{
                            add(new SimpleGrantedAuthority("ROLE_STUDENT"));
                        }});
                        student.setStudentName(row.getCell(1).getStringCellValue());
                        student.setStudentSurname(row.getCell(2).getStringCellValue());
                        student.setStudentEmail(row.getCell(3).getStringCellValue());
                        student.setAssignedEvaluatorId("");
                        student.setAssignedSupervisorId("");
                        student.setReports299(null);
                        student.setReports399(null);

            
                        if (row.getCell(4).getStringCellValue().length() == 11) {
                            String[] takenCourses = new String[2];
                            takenCourses[0] = row.getCell(4).getStringCellValue().substring(0, 5);
                            takenCourses[1] = row.getCell(4).getStringCellValue().substring(6);
                            student.setCoursesTaken(takenCourses);
                        }
                        else if (row.getCell(4).getStringCellValue().length() == 5) {
                            String[] takenCourses = new String[1];
                            takenCourses[0] = row.getCell(4).getStringCellValue().substring(0);
                            student.setCoursesTaken(takenCourses);
                        }
                        
                        studentService.createStudent(student);
                        mailer.sendRegistrationMailStudent(student);
                    }
                }
            }
            return new ResponseEntity<>("Successful Import", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: Failed to read the file", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: Encrypted document found", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: Failed to import data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/evaluatorImport")
    public ResponseEntity<String> uploadExcelFileEvaluator(@RequestParam("file") MultipartFile file) {
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                for (Row row : sheet) {
                    // Assuming data starts from the second row
                    if (row.getRowNum() > 0) {
                        if (row.getCell(0).getStringCellValue().isEmpty() 
                        || row.getCell(1).getStringCellValue().isEmpty()
                        || row.getCell(2).getStringCellValue().isEmpty()
                        || row.getCell(3).getStringCellValue().isEmpty()) {
                            return new ResponseEntity<>("An empty cell is found, check the file", HttpStatus.OK);
                        }
                        Evaluator evaluator = new Evaluator();
                        evaluator.setUserName(row.getCell(0).getStringCellValue());
                        PasswordGenerator passwordGenerator = new PasswordGenerator();
                        evaluator.setUserPassword(passwordGenerator.generatePassword());
                        evaluator.setUserAuthorities(new ArrayList<GrantedAuthority>() {{
                            add(new SimpleGrantedAuthority("ROLE_EVALUATOR"));
                        }});
                        evaluator.setAssignedStudents(null);
                        evaluator.setEvaluatorName(row.getCell(1).getStringCellValue());
                        evaluator.setEvaluatorSurname(row.getCell(2).getStringCellValue());
                        evaluator.setEvaluatorEmail(row.getCell(3).getStringCellValue());
                        evaluator.setAssignedStudents(null);
                        evaluator.setStudentLimit((int) (row.getCell(4).getNumericCellValue()));

                        evaluatorService.createEvaluator(evaluator);
                        mailer.sendRegistrationMailEvaluator(evaluator);
                    }
                }
            }

            return new ResponseEntity<>("Successful Import", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: Failed to read the file", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (EncryptedDocumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: Encrypted document found", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: Failed to import data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
