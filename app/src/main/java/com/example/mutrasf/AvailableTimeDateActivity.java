package com.example.mutrasf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextClock;

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


                Intent intent = new Intent();
                intent.putExtra("selectedDate", selectedDate);
                intent.putExtra("selectedTime", selectedTime);
                setResult(RESULT_OK, intent);
                finish();
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
