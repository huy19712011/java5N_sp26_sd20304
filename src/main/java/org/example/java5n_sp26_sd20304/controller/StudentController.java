package org.example.java5n_sp26_sd20304.controller;

import lombok.RequiredArgsConstructor;
import org.example.java5n_sp26_sd20304.entity.Student;
import org.example.java5n_sp26_sd20304.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    public String listStudents(Model model) {

        // get data from DB
        List<Student> students = studentService.getAllStudents();
        students.forEach(student -> System.out.println(student));

        // send data to view
        model.addAttribute("students", students);

        return "views/students"; // views/students.html
    }
}
