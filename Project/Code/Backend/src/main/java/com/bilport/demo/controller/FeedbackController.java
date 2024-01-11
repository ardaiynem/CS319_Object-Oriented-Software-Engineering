package com.bilport.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bilport.demo.domain.model.FeedbackFile;
import com.bilport.demo.service.FeedbackService;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;
    
    @PostMapping(value = "/file/{reportId}")
    public ResponseEntity<String> uploadStudentReportFile(@RequestParam("file") MultipartFile reportFile,
            @PathVariable("reportId") String reportId) {
        try {
            feedbackService.uploadFileToReport(reportFile, reportId);

            System.out.println("File uploaded successfully! -> filename = " + reportFile.getOriginalFilename());

            return new ResponseEntity<String>("Success Upload", HttpStatus.OK);

        } catch (Exception e) {

            System.out.println("Fail to upload Profile Picture!");
            
            return new ResponseEntity<String>("Fail Upload", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/file/{reportId}")
    public ResponseEntity<byte[]> downloadStudentReport(@PathVariable("reportId") String reportId) {
        try {
            /*
             * @PathVariable("id") String reportId
             */
            byte[] file = feedbackService.downloadReportFile(reportId);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition", "attachment; filename=\"" + reportId + "\"");
            httpHeaders.setContentType(MediaType.APPLICATION_PDF);
            return new ResponseEntity<byte[]>(file, httpHeaders, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
