package com.bilport.demo.domain.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "reports")
public class Report {
    @Id
    public String reportId;
    public String reportOwner;
    public String reportFileId;
    public String course;
    public Date reportDate;
    public String reportStatus;
    public int version;
}
