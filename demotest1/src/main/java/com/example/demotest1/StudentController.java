package com.example.demotest1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import static com.example.demotest1.MyController.logger;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public String createStudent(@RequestBody Student1 student) {
        try {
            studentService.saveStudentWithRollback(student);
        } catch (RuntimeException e) {
            return "Transaction rolled back: " + e.getMessage();
        }
        Logger l = LoggerFactory.getLogger("l");
        l.info("保存一个学生记录");
        return "Student created successfully";
    }
}