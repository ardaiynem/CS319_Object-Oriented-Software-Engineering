package com.bilport.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bilport.demo.domain.dto.StudentResponse;
import com.bilport.demo.domain.dto.SubmissionResponse;
import com.bilport.demo.domain.model.Evaluator;
import com.bilport.demo.domain.model.Report;
import com.bilport.demo.domain.model.Student;
import com.bilport.demo.repository.EvaluatorRepository;
import com.bilport.demo.repository.ReportRepository;
import com.bilport.demo.repository.StudentRepository;

@Service
public class EvaluatorService {
    @Autowired
    EvaluatorRepository evaluatorRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ReportRepository reportRepository;

    public Evaluator findById(String userName) {
        return evaluatorRepository.findById(userName).orElse(null);
    }

    public List<Evaluator> getEvaluators() {
        return evaluatorRepository.findAll();
    }

    public void createEvaluator(Evaluator evaluator) {
        evaluatorRepository.save(evaluator);
    }

    public List<StudentResponse> getAssignedStudents(String id) {
        String[] assignedStudents = evaluatorRepository.findById(id).get().getAssignedStudents();
        ArrayList<StudentResponse> studentResponses = new ArrayList<StudentResponse>();

        for (String assignedStudent : assignedStudents) {
            Student student = studentRepository.findById(assignedStudent).get();
            StudentResponse studentResponse = new StudentResponse();

            studentResponse.setStudentName(student.getStudentName());
            studentResponse.setStudentSurname(student.getStudentSurname());
            studentResponse.setStudentEmail(student.getStudentEmail());
            studentResponse.setAssignedEvaluatorId(student.getAssignedEvaluatorId());
            studentResponse.setAssignedSupervisorId(student.getAssignedSupervisorId());
            studentResponse.setStudentId(student.getUserName());
            studentResponse.setReports299(student.getReports299());
            studentResponse.setReports399(student.getReports399());
            studentResponse.setCoursesTaken(student.getCoursesTaken());

            studentResponses.add(studentResponse);
        }

        return studentResponses;
    }

    public List<Report> getAssignedReports(String id) {
        String[] assignedStudents = evaluatorRepository.findById(id).get().getAssignedStudents();
        ArrayList<Report> reports = new ArrayList<Report>();

        for (String assignedStudent : assignedStudents) {
            reportRepository.findByReportOwner(assignedStudent).get().forEach(reports::add);
        }

        return reports;
    }

    public List<SubmissionResponse> getSubmissions(String id) {
        String[] assignedStudents = evaluatorRepository.findById(id).get().getAssignedStudents();
        ArrayList<SubmissionResponse> submissions = new ArrayList<SubmissionResponse>();

        for (String assignedStudent : assignedStudents) {
            Student student = studentRepository.findById(assignedStudent).get();

            ArrayList<Report> reports = new ArrayList<Report>();
            reportRepository.findByReportOwner(assignedStudent).get().forEach(reports::add);

            for (Report report : reports) {
                SubmissionResponse submissionResponse = new SubmissionResponse();

                submissionResponse.setReportId(report.getReportId());
                submissionResponse.setReportOwner(report.getReportOwner());
                submissionResponse.setReportFileId(report.getReportFileId());
                submissionResponse.setCourse(report.getCourse());
                submissionResponse.setReportDate(report.getReportDate());
                submissionResponse.setReportStatus(report.getReportStatus());
                submissionResponse.setVersion(report.getVersion());
                submissionResponse.setStudentName(student.getStudentName());
                submissionResponse.setStudentSurname(student.getStudentSurname());
                submissionResponse.setStudentEmail(student.getStudentEmail());

                submissions.add(submissionResponse);
            }
        }
        return submissions;
    }

    public void assignEvalToStudent(String evaluatorId, String studentId) {
        Evaluator evaluator = evaluatorRepository.findById(evaluatorId).orElse(null);
        Student student = studentRepository.findById(studentId).get();
        Evaluator oldEvaluator = evaluatorRepository.findById(student.getAssignedEvaluatorId()).orElse(null);

        String[] assignedStudents = evaluator.getAssignedStudents();
        String[] newAssignedStudents = new String[assignedStudents.length + 1];

        for (int i = 0; i < assignedStudents.length; i++) {
            newAssignedStudents[i] = assignedStudents[i];
        }

        newAssignedStudents[assignedStudents.length] = studentId;
        evaluator.setAssignedStudents(newAssignedStudents);
        evaluatorRepository.save(evaluator);

        if (oldEvaluator != null) {
            String[] oldAssignedStudentsEv = oldEvaluator.getAssignedStudents();
            String[] newAssignedStudentsEv = new String[oldAssignedStudentsEv.length - 1];

            for (int i = 0, k = 0; i < oldAssignedStudentsEv.length; i++) {
                if (oldAssignedStudentsEv[i].equals(studentId)) {
                    continue;
                }
                newAssignedStudentsEv[k++] = oldAssignedStudentsEv[i];
            }

            oldEvaluator.setAssignedStudents(newAssignedStudentsEv);
            evaluatorRepository.save(oldEvaluator);

        }


        student.setAssignedEvaluatorId(evaluatorId);
        studentRepository.save(student);
    }

    public void changeStudentLimit(String evaluatorId, int studentLimit) {
        Evaluator evaluator = evaluatorRepository.findById(evaluatorId).orElse(null);

        if (studentLimit < evaluator.getAssignedStudents().length || studentLimit < 0 || evaluator == null) {
            return;
        }

        evaluator.setStudentLimit(studentLimit);
        evaluatorRepository.save(evaluator);
    }

    
}
