package com.baorant.secondmoduel.room;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.baorant.secondmoduel.R;
import com.baorant.secondmoduel.room.dao.StudentDao;
import com.baorant.secondmoduel.room.database.MyDatabase;
import com.baorant.secondmoduel.room.entity.Student;

import java.util.List;

public class RoomActivity extends AppCompatActivity {
    private EditText addName;
    private EditText addAge;
    private Button addStudent;
    private Button queryStudents;
    private StudentDao studentDao;
    private TextView showStudents;

    private Handler studentInfoHandler;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init() {
        addAge = findViewById(R.id.addAge);
        addName = findViewById(R.id.addName);
        addStudent = findViewById(R.id.addBtn);
        queryStudents = findViewById(R.id.queryBtn);
        showStudents = findViewById(R.id.studentInfo);

        studentDao = MyDatabase.getInstance(RoomActivity.this).studentDao();

        studentInfoHandler = new Handler();

        addStudent.setOnClickListener(view -> {
            String age = addAge.getText().toString();
            String name = addName.getText().toString();
            new Thread(() -> studentDao.insertStudent(new Student(name, age))).start();
        });

        final String[] info = {""};
        queryStudents.setOnClickListener(view -> {
            new Thread(() -> {
                List<Student> studentList = studentDao.getStudentList();
                info[0] = "";
                studentList.forEach(student -> {
                    info[0] += student.name;
                    info[0] += " ";
                    info[0] += student.age;
                    info[0] += "    ";
                });
                studentInfoHandler.post(() -> showStudents.setText(info[0]));
            }).start();
        });
    }
}