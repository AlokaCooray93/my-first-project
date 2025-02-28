package com.example.multi_datasource.controller;

import com.example.multi_datasource.Service.StudentService;
import com.example.multi_datasource.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/setStudent")
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }
}
