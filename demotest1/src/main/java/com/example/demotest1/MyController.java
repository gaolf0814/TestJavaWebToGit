package com.example.demotest1;

import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);


    @GetMapping("/hello")
    public String sayHello() {
        logger.info("这是第一个打印的日志测试");
        System.out.println("hello,中国人");
        logger.info("这是第二个打印的日志测试");

        return("hello,中国人");
    }

    @PostMapping("/post")
    public Student testPost(@RequestBody Student student1){
        Student student = new Student();
        student.name="gaolingfeng";
        student.id="212050330";
        student.year=23;
        System.out.println(student.toString());
        return student;
    }


    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/returnStu")
    public Student1 returnStu(@RequestBody Student1 student1){
        Student1 stu = null;
        try{
            Logger l = LoggerFactory.getLogger("l");
            l.info("测试returnStu");
            stu=studentRepository.getById(1L);
            String email = stu.getEmail();
            Long id = stu.getId();
            Student1 s = new Student1();
            s.setEmail(stu.getEmail());
            s.setName(stu.getName());
            s.setId(stu.getId());
            return s;
//            stu=studentRepository.findById(1L).get();
//            stu=studentRepository.findAll().get(0);
        }catch (Exception e){
            if(stu==null){
                stu = new Student1();
                stu.setId(123L);
                stu.setName("gao");
                stu.setEmail("23163@qq.com");
                return stu;
            }
        }
        if(stu==null){
            stu = new Student1();
            stu.setId(123L);
            stu.setName("gao");
            stu.setEmail("23163@qq.com");
            return stu;
        }

        return stu;
    }

    @GetMapping("/get")
    public List<Student1> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("/ret")
    public Student1 ret(@RequestBody Student1 student1){
        Student1 stu = null;
        try{
            Logger l = LoggerFactory.getLogger("l");
            l.info("测试stu");
            stu=studentRepository.getById(1L);
            String email = stu.getEmail();
            Long id = stu.getId();
            Student1 s = new Student1();
            s.setEmail(stu.getEmail());
            s.setName(stu.getName());
            s.setId(stu.getId());
            return stu;
//            stu=studentRepository.findById(1L).get();
//            stu=studentRepository.findAll().get(0);
        }catch (Exception e){

        }
        if(stu==null){
            stu = new Student1();
            stu.setId(123L);
            stu.setName("gao");
            stu.setEmail("23163@qq.com");
            return stu;
        }

        return stu;
    }
}
