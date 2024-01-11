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
@Document(collection = "supervisorForms")
public class SupervisorForm {
    @Id
    String studentId;
    String course;
    int q1, q2, q3, q4, q5, q6, q7, q8;
    String comment;
    Date startDate, endDate;
}
