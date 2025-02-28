package com.example.multi_datasource.Service;

import com.example.multi_datasource.student.Student;
import com.example.multi_datasource.studentRepository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    public Student addStudent(Student student){
       return studentRepo.save(student);

    }
}
