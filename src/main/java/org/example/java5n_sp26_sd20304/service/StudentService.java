package org.example.java5n_sp26_sd20304.service;

import lombok.RequiredArgsConstructor;
import org.example.java5n_sp26_sd20304.entity.Student;
import org.example.java5n_sp26_sd20304.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {

        return studentRepository.getAllStudents();
    }

    public void saveStudent(Student student) {

        studentRepository.saveStudent(student);
    }
}
