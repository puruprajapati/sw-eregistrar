package com.example.eregistrar.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long studentId;
  @Column(unique = true)
  private String studentNumber;
  private String firstName;
  private String middleName;
  private String lastName;
  private Double cgpa;
  private String enrollmentDate;
  private String isInternational;
}
