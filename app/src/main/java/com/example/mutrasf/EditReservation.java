package com.example.mutrasf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextClock;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditReservation extends AppCompatActivity {

    private TextClock textClock;
    private CalendarView calendarView;
    private Button confirmButton;
    private DBHelper dbHelper;
    private int reservationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reservation);


        dbHelper = new DBHelper(this);

        textClock = findViewById(R.id.textClock22);
        calendarView = findViewById(R.id.calendarView1);
        confirmButton = findViewById(R.id.confirmtimebtnn);


        reservationId = getIntent().getIntExtra("reservationId", -1);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newTime = textClock.getText().toString();
                long newDateMillis = calendarView.getDate();

                String newDate = convertMillisToDate(newDateMillis);


                //dbHelper.editReservation(reservationId, newTime,newDate);


                Toast.makeText(EditReservation.this, "Reservation updated successfully", Toast.LENGTH_SHORT).show();


                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        dbHelper.close();
    }

    private String convertMillisToDate(long millis) {

        Date date = new Date(millis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }
}