package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddEmployeeActivity extends AppCompatActivity {

    EditText firstName, lastName, fatherName, mobile, joiningDate, salary, previousOccupation;
    EditText previousOccupationAddress, currentAddress, villageAddress, referenceName, referenceMobile;
    Button save;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        // initializing
        firstName = (EditText) findViewById(R.id.firstName);
        fatherName = (EditText) findViewById(R.id.fatherName);
        lastName = (EditText) findViewById(R.id.lastName);
        mobile = (EditText) findViewById(R.id.mobile);
        joiningDate = (EditText) findViewById(R.id.joiningDate);
        salary = (EditText) findViewById(R.id.salary);
        previousOccupation = (EditText) findViewById(R.id.previousOccupation);
        previousOccupationAddress = (EditText)findViewById(R.id.previousOccupationAddress) ;
        currentAddress = (EditText)findViewById(R.id.currentAddress) ;
        villageAddress = (EditText)findViewById(R.id.villageAddress) ;
        referenceName = (EditText)findViewById(R.id.referenceName) ;
        referenceMobile = (EditText)findViewById(R.id.referenceMobile) ;
        save = (Button) findViewById(R.id.saveEmployee);

        // Date picker for the joining date
        joiningDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) return;
                showJoiningDate();
            }
        });
        joiningDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJoiningDate();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Item item = new Item(firstName.getText().toString(), lastName.getText().toString(),
                            fatherName.getText().toString(), mobile.getText().toString(),
                            simpleDateFormat.parse(joiningDate.getText().toString()).getTime(),
                            Double.parseDouble(salary.getText().toString()), previousOccupation.getText().toString(),
                            previousOccupationAddress.getText().toString(), currentAddress.getText().toString(),
                            villageAddress.getText().toString(), referenceName.getText().toString(),
                            referenceMobile.getText().toString());

                    DAOItem dao = new DAOItem();
                    dao.add(item).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_LONG).show();
                            onBackPressed();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showJoiningDate() {
        final Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddEmployeeActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // on below line we are setting date to our text view.
                        joiningDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, year, month, day);
        datePickerDialog.show();
    }
}