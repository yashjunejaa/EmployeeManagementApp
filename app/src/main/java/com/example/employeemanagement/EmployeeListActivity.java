package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EmployeeListActivity extends AppCompatActivity {

    FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        // next screen to add employee
        add = (FloatingActionButton) findViewById(R.id.addEmployee);
        Intent intent= new Intent(this, AddEmployeeActivity.class);
        startActivity(intent);
    }
}