package com.vimla.exp7.service;

import com.vimla.exp7.dto.StudentRequest;
import com.vimla.exp7.dto.StudentResponse;
import com.vimla.exp7.entity.Student;
import com.vimla.exp7.exception.ResourceNotFoundException;
import com.vimla.exp7.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repo;

    public List<Student> getAll() {
        return repo.findAll();
    }

    public StudentResponse getById(Long id) {
        Student s = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return toResponse(s);
    }

    public StudentResponse create(StudentRequest req) {
        Student s = toEntity(req);
        repo.save(s);
        return toResponse(s);
    }

    public StudentResponse update(Long id, StudentRequest req) {
        Student s = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        s.setRollNumber(req.getRollNumber());
        s.setFirstName(req.getFirstName());
        s.setLastName(req.getLastName());
        s.setEmail(req.getEmail());
        s.setDepartment(req.getDepartment());
        s.setYear(req.getYear());
        s.setGpa(req.getGpa());

        repo.save(s);
        return toResponse(s);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Student not found");
        }
        repo.deleteById(id);
    }

    private Student toEntity(StudentRequest r) {
        return Student.builder()
                .rollNumber(r.getRollNumber())
                .firstName(r.getFirstName())
                .lastName(r.getLastName())
                .email(r.getEmail())
                .department(r.getDepartment())
                .year(r.getYear())
                .gpa(r.getGpa())
                .build();
    }

    private StudentResponse toResponse(Student s) {
        return StudentResponse.builder()
                .id(s.getId())
                .rollNumber(s.getRollNumber())
                .firstName(s.getFirstName())
                .lastName(s.getLastName())
                .email(s.getEmail())
                .department(s.getDepartment())
                .year(s.getYear())
                .gpa(s.getGpa())
                .build();
    }
}
