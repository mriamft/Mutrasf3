package com.example.mutrasf;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.view.View.OnClickListener;
public class TruckProfileActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private long selectedDate;
    private int selectedHour;
    private int selectedMinute;
    private String truckId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truckprofile);

        ImageButton backButton = findViewById(R.id.imageView8);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TruckProfileActivity.this, AvailableTimeDateActivity.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement the action for the real back button
                // For example, you can call onBackPressed() to go back to the previous activity
                onBackPressed();
            }
        });

        selectedDate = getIntent().getLongExtra("SELECTED_DATE", 0);
        selectedHour = getIntent().getIntExtra("SELECTED_HOUR", 0);
        selectedMinute = getIntent().getIntExtra("SELECTED_MINUTE", 0);

        TextView selectedDateTimeTextView = findViewById(R.id.selectedDateTimeTextView);
        String selectedDateTime = "Selected Date and Time: "
                + new SimpleDateFormat("dd/MM/yyyy").format(selectedDate) + " "
                + String.format("%02d:%02d", selectedHour, selectedMinute);
        selectedDateTimeTextView.setText(selectedDateTime);

        dbHelper = new DBHelper(this);

        truckId = getIntent().getStringExtra("TRUCK_ID");
        String truckName = getIntent().getStringExtra("TRUCK_NAME");
        String truckPrice = getIntent().getStringExtra("TRUCK_PRICE");
        String truckPhone = getIntent().getStringExtra("TRUCK_PHONE");

        TextView truckNameTextView = findViewById(R.id.textView8);
        truckNameTextView.setText(truckName);

        Cursor cursor = dbHelper.getTruckDetailsById(truckId);
        if (cursor != null && cursor.moveToFirst()) {
            String category = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_FOODTRUCK_CATEGORY));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_FOODTRUCK_DESCRIPTION));
            float price = cursor.getFloat(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_FOODTRUCK_PRICE));
            int phoneNumber = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_CONTACT_PHONE));

            // Truck info
            TextView categoryTextView = findViewById(R.id.cat);
            TextView descriptionTextView = findViewById(R.id.textView22);
            TextView priceTextView = findViewById(R.id.textView11);
            TextView phoneNumberTextView = findViewById(R.id.textView21);

            categoryTextView.setText(category);
            descriptionTextView.setText(description);
            priceTextView.setText(String.valueOf(price));
            phoneNumberTextView.setText(String.valueOf(phoneNumber));

            // Reserve button
            Button reserveButton = findViewById(R.id.reservebtn);
            reserveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the user ID for the reservation (replace "userId" with the actual user ID)
                    String userId = "userId";

                    // Insert the reservation details into the Reservation table
                    long reservationId = dbHelper.insertReservation(selectedDate, selectedHour, selectedMinute, truckId, userId);

                    if (reservationId != -1) {
                        // Reservation successful
                        Toast.makeText(TruckProfileActivity.this, "Reservation Successful!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Reservation failed
                        Toast.makeText(TruckProfileActivity.this, "Reservation Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            Toast.makeText(this, "Truck not found", Toast.LENGTH_SHORT).show();
        }

        if (cursor != null) {
            cursor.close();
        }
    }
}