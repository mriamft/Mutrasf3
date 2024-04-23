package com.example.mutrasf;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextClock;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AvailableTimeDateActivity extends AppCompatActivity {
    private Button confirmButton;
    private CalendarView calendarView;
    private TextClock selectedTimeTextClock;

    private long selectedDateMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.availabletimedate);

        confirmButton = findViewById(R.id.confirmtimebtn);
        calendarView = findViewById(R.id.calendarView);
        selectedTimeTextClock = findViewById(R.id.textClock2);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDate = getSelectedDate();
                String selectedTime = getSelectedTime();

                // Create the confirmation message
                String confirmationMessage = "You have chosen the following date and time:\n"
                        + "Date: " + selectedDate + "\n"
                        + "Time: " + selectedTime;

                // Display the confirmation message using a dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(AvailableTimeDateActivity.this);
                builder.setTitle("Confirmation")
                        .setMessage(confirmationMessage)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Save the date and time (you can implement this logic)
                                // Redirect to TruckProfileActivity with the saved date and time
                                Intent intent = new Intent(AvailableTimeDateActivity.this, TruckProfileActivity.class);
                                intent.putExtra("SELECTED_DATE", selectedDate);
                                intent.putExtra("SELECTED_TIME", selectedTime);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                selectedDateMillis = calendar.getTimeInMillis();
            }
        });
    }

    private String getSelectedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date selectedDate = new Date(selectedDateMillis);
        return dateFormat.format(selectedDate);
    }

    private String getSelectedTime() {
        return selectedTimeTextClock.getText().toString();
    }
}