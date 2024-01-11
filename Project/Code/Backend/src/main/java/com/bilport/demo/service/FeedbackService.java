package com.bilport.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bilport.demo.domain.model.FeedbackFile;
import com.bilport.demo.repository.FeedbackRepository;

@Service
public class FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    public FeedbackFile getFeedbackFile(String feedbackId) {
        return feedbackRepository.findById(feedbackId).orElse(null);
    }

    public void uploadFileToReport(MultipartFile reportFile, String feedbackFileId) {
        try {
            feedbackRepository.save(new FeedbackFile(feedbackFileId, reportFile.getBytes(), reportFile.getContentType()));
        } catch (Exception e) {

        }
    }

    public byte[] downloadReportFile(String reportId) {
        try {
            return feedbackRepository.findById(reportId).get().getFeedbackFile();
        } catch (Exception e) {
            return null;
        }
    }
}
