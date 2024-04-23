package com.example.mutrasf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayReservation extends AppCompatActivity {

    private Button editButton;
    private int reservationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayreservation);


        reservationId = getIntent().getIntExtra("reservationId", -1);

        editButton = findViewById(R.id.edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DisplayReservation.this, EditReservation.class);
                intent.putExtra("reservationId", reservationId);
                startActivity(intent);
            }
        });
    }
}
