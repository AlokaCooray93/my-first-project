package com.example.multi_datasource.Service;


import com.example.multi_datasource.teacher.Teacher;
import com.example.multi_datasource.teacherRepo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    //new comment
    @Autowired
    private TeacherRepo teacherRepo;
    public Teacher addTeacher(Teacher teacher){
        return teacherRepo.save(teacher);
    }
}
