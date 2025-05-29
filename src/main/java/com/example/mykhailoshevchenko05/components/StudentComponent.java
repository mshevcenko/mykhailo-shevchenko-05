package com.example.mykhailoshevchenko05.components;

import com.example.mykhailoshevchenko05.CurrentDatasource;
import com.example.mykhailoshevchenko05.entity.StudentEntity;
import com.example.mykhailoshevchenko05.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentComponent {

    @Autowired
    private StudentRepository studentRepository;

    public void save(StudentEntity student, CurrentDatasource datasource) {
        DatasourceContextHolder.set(datasource);
        studentRepository.save(student);
    }

    public List<StudentEntity> findAll(CurrentDatasource datasource) {
        DatasourceContextHolder.set(datasource);
        return studentRepository.findAll();
    }

}
