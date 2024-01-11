package com.bilport.demo.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "feedbackFiles")
public class FeedbackFile {
    @Id
    public String feedbackFileId;
    public byte[] feedbackFile;
    private String type;
}
