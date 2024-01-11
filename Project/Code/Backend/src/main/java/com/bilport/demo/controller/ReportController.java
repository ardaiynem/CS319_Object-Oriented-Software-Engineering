package com.bilport.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bilport.demo.domain.model.Report;
import com.bilport.demo.service.ReportService;

@RestController
@RequestMapping(value = "/reports")
public class ReportController {

    @Autowired
    ReportService reportService;

    @ResponseBody
    @GetMapping(value = "/{name}")
    public List<Report> getReportsOfStudent(@PathVariable("name") String name) {
        return reportService.findByReportOwner(name);
    }

    @ResponseBody
    @GetMapping(value = "/report/{reportId}")
    public Report getReportById(@PathVariable("reportId") String reportId) {
        return reportService.findByReportId(reportId);
    }

    @ResponseBody
    @GetMapping(value = "/{name}/{course}")
    public List<Report> getReportsOfStudentAndCourse(@PathVariable("name") String name, @PathVariable("course") String course) {
        return reportService.findByReportOwnerAndCourse(name, course);
    }

    @PostMapping(value = "/file/{reportId}")
    public ResponseEntity<String> uploadStudentReportFile(@RequestParam("file") MultipartFile reportFile,
            @PathVariable("reportId") String reportId) {
        try {
            reportService.uploadFileToReport(reportFile, reportId);

            return new ResponseEntity<String>("Success Upload", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>("Fail Upload", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/{name}")
    public ResponseEntity<String> uploadStudentReport(@PathVariable("name") String name, @RequestBody Report report) {
        try {

            reportService.uploadStudentReport(report);

            return new ResponseEntity<String>("Success Upload", HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<String>("Fail Upload", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/file/{reportId}")
    public ResponseEntity<byte[]> downloadStudentReport(@PathVariable("reportId") String reportId) {
        try {
            /*
             * @PathVariable("id") String reportId
             */
            byte[] file = reportService.downloadReportFile(reportId);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition", "attachment; filename=\"" + reportId + "\"");
            httpHeaders.setContentType(MediaType.APPLICATION_PDF);
            return new ResponseEntity<byte[]>(file, httpHeaders, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
