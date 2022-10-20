package com.example.employeemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity {

    Button markPresent, addAdvance;
    TextView name, number;

    HashMap<String, String> hashMap;
    SimpleDateFormat sd= new SimpleDateFormat("yyyy-MM-dd");
    String place;

    DAOItem daoItem;
    Item item;
    Transaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        // New tab for editing details like name, etc.
        name = (TextView) findViewById(R.id.name);
        number = (TextView) findViewById(R.id.number);

        addAdvance = (Button) findViewById(R.id.addAdvance);

        Intent intent = getIntent();
        item = (Item) intent.getSerializableExtra("Item");
        name.setText(item.getFirstName() + " " + item.getLastName());
        number.setText(item.getMobile());

        hashMap = new HashMap<>();

        daoItem = new DAOItem();
        daoItem.getDate(item.getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hashMap = (HashMap<String, String>) snapshot.getValue();
                if (hashMap==null) hashMap = new HashMap<>();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Date picker
        OnSelectDateListener listener = new OnSelectDateListener() {
            @Override
            public void onSelect(List<Calendar> calendar) {
                showDialogBox(calendar);
            }

        };
        markPresent = (Button)findViewById(R.id.markPresent);
        markPresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Calendar> list = new ArrayList<>();
                for (String p : hashMap.keySet()) {
                    Calendar c = Calendar.getInstance();
                    try {
                        c.setTime(sd.parse(p));
                    } catch (ParseException e) {
//                        Toast.makeText(getApplicationContext(), "fg", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    list.add(c);
                }
                DatePickerBuilder builder = new DatePickerBuilder(EmployeeActivity.this, listener)
                        .pickerType(CalendarView.MANY_DAYS_PICKER)
                        .highlightedDays(list)
                        .highlightedDaysLabelsColor(R.color.khoon);

                DatePicker datePicker = builder.build();
                datePicker.show();
            }
        });

        addAdvance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddAdvanceDialog();
            }
        });
    }

    private void showAddAdvanceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Advance");

        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_advance, null);
        builder.setView(customLayout);

        builder.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which) {
                EditText editText = customLayout.findViewById(R.id.advance);
                String advance = editText.getText().toString();
                String comment= ((EditText)customLayout.findViewById(R.id.advanceComment)).getText().toString();
                transaction=new Transaction(Calendar.getInstance().getTimeInMillis(),Double.parseDouble(advance),comment);
                daoItem.addTransaction(item.getKey(),transaction);
                Toast.makeText(getApplicationContext(), advance, Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDialogBox(List<Calendar> calendar) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Site");

        final View customLayout = getLayoutInflater().inflate(R.layout.dialog, null);
        builder.setView(customLayout);

        builder.setPositiveButton( "OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick( DialogInterface dialog, int which) {
                                EditText editText = customLayout.findViewById(R.id.siteName);
                                place = editText.getText().toString();
                                Toast.makeText(getApplicationContext(), place, Toast.LENGTH_LONG).show();

                                addToPresentList(calendar, place);
                            }
                        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void addToPresentList(List<Calendar> calendar, String place) {
        for (Calendar c : calendar) {
            hashMap.put(sd.format(c.getTime()), place);
        }
        daoItem.addDate(item.getKey(), hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_LONG).show();
            }
        });
    }


}