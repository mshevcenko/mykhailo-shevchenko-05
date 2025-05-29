package com.example.mykhailoshevchenko05;

import com.example.mykhailoshevchenko05.components.DataSourceRouting;
import com.example.mykhailoshevchenko05.components.DatasourceContextHolder;
import com.example.mykhailoshevchenko05.components.StudentComponent;
import com.example.mykhailoshevchenko05.entity.StudentEntity;
import com.example.mykhailoshevchenko05.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Test1 {

    @Autowired
    private StudentComponent studentComponent;

    @Autowired
    private StudentRepository studentRepository;

    @AfterEach
    public void truncateTables() {
        DatasourceContextHolder.set(CurrentDatasource.DATASOURCE_1);
        studentRepository.truncate();
        DatasourceContextHolder.set(CurrentDatasource.DATASOURCE_2);
        studentRepository.truncate();
    }

    @Test
    public void test1() {
        StudentEntity student = new StudentEntity(1L, "test1@gmail.com", "test1", "test1");
        studentComponent.save(student, CurrentDatasource.DATASOURCE_1);
        List<StudentEntity> students = studentComponent.findAll(CurrentDatasource.DATASOURCE_1);
        Assertions.assertEquals(1, students.size());
        students = studentComponent.findAll(CurrentDatasource.DATASOURCE_2);
        Assertions.assertEquals(0, students.size());
    }

    @Test
    public void test2() {
        StudentEntity student = new StudentEntity(1L, "test1@gmail.com", "test1", "test1");
        studentComponent.save(student, CurrentDatasource.DATASOURCE_2);
        List<StudentEntity> students = studentComponent.findAll(CurrentDatasource.DATASOURCE_2);
        Assertions.assertEquals(1, students.size());
        students = studentComponent.findAll(CurrentDatasource.DATASOURCE_1);
        Assertions.assertEquals(0, students.size());
    }

}
