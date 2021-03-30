package com.georgian.movieactordemo.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Director {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long directorId;
  private String firstName;
  private String lastName;
  private String nationality;
  private LocalDate DOB;
}
