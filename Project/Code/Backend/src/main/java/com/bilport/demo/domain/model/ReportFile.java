package com.bilport.demo.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "reportFiles")
public class ReportFile {
    @Id
    public String reportFileId;
    public byte[] reportFile;
    private String type;
}
