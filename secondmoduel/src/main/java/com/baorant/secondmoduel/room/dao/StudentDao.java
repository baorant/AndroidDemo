package com.baorant.secondmoduel.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.baorant.secondmoduel.room.entity.Student;

import java.util.List;

@Dao
public interface StudentDao
{
    @Insert
    void insertStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Update
    void updateStudent(Student student);

    @Query("SELECT * FROM student")
    List<Student> getStudentList();

    @Query("SELECT * FROM student WHERE id = :id")
    Student getStudentById(int id);
}