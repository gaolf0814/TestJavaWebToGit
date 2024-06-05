package com.example.demotest1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public void saveStudentWithRollback(Student1 student) {
        studentRepository.save(student);

//        // 模拟异常以触发回滚
//        if (true) {
//            throw new RuntimeException("Simulated exception to trigger rollback");
//        }
    }
}
