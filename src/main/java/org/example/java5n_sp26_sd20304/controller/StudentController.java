package org.example.java5n_sp26_sd20304.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java5n_sp26_sd20304.entity.Student;
import org.example.java5n_sp26_sd20304.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/students/showNewStudentForm")
    public String showNewStudentForm(Model model) {

        Student student = new Student();
        model.addAttribute("student", student);

        return "views/new_student"; // new_student.html
    }

    @PostMapping("/students/saveStudent")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return "views/new_student";
        }

        // save to DB
        studentService.saveStudent(student);

        // return view
        return "redirect:/students";
    }

    @GetMapping("/students/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable("id") long id, Model model) {

        // get student
        Student student = studentService.getStudentById(id);

        // send data to view
        model.addAttribute("student", student);

        return "views/update_student";

    }

    @PostMapping("/students/updateStudent")
    public String updateStudent(@ModelAttribute("student") Student student) {

        // update to DB
        studentService.updateStudent(student);

        // return view
        return "redirect:/students";
    }

    @GetMapping("/students/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") long id) {

        studentService.deleteStudentById(id);

        return "redirect:/students";
    }

}
