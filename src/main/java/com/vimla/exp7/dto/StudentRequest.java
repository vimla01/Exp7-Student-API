package com.vimla.exp7.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    @NotBlank
    private String rollNumber;

    @NotBlank
    private String firstName;

    private String lastName;

    @Email @NotBlank
    private String email;

    @NotBlank
    private String department;

    @Min(1) @Max(4)
    private int year;

    @DecimalMin("0.0") @DecimalMax("10.0")
    private double gpa;
}
