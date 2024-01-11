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
@Document(collection = "evaluatorForms")
public class EvaluatorForm {
    @Id
    String studentId;
    String course;
    int partA1;
    boolean partA2, partA3;
    boolean partB1;
    Date partB2;
    int partC1, partC2, partC3;
}
