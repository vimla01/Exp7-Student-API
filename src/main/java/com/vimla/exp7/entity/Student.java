package com.vimla.exp7.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(
        name = "students",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_students_roll_number", columnNames = "roll_number"),
                @UniqueConstraint(name = "uk_students_email", columnNames = "email")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "rollNumber is required")
    @Column(name = "roll_number", nullable = false, unique = true)
    private String rollNumber;

    @NotBlank(message = "firstName is required")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    private String lastName;

    @Email(message = "email must be valid")
    @NotBlank(message = "email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "department is required")
    private String department;

    @Min(1) @Max(4)
    @Column(name = "year_of_study", nullable = false)
    private int year;

    @DecimalMin(value = "0.0") @DecimalMax(value = "10.0")
    private double gpa;
}
