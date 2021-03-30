package com.georgian.movieactordemo.demo.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ActorRequest {
  private String firstName;
  private String lastName;
  private String nationality;
  private LocalDate DOB;

}
