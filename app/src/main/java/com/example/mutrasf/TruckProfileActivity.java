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

public class TruckProfileActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truckprofile);
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);

        //back Button
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TruckProfileActivity.this, dashboard.class);
                startActivity(intent);
            }
        });
        long selectedDate = getIntent().getLongExtra("SELECTED_DATE", 0);
        int selectedHour = getIntent().getIntExtra("SELECTED_HOUR", 0);
        int selectedMinute = getIntent().getIntExtra("SELECTED_MINUTE", 0);

        TextView selectedDateTimeTextView = findViewById(R.id.selectedDateTimeTextView);
        String selectedDateTime = "Selected Date and Time: "
                + new SimpleDateFormat("dd/MM/yyyy").format(selectedDate) + " "
                + String.format("%02d:%02d", selectedHour, selectedMinute);
        selectedDateTimeTextView.setText(selectedDateTime);


        dbHelper = new DBHelper(this);

        String truckId = getIntent().getStringExtra("TRUCK_ID");
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

            //truck info
            TextView categoryTextView = findViewById(R.id.cat);
            TextView descriptionTextView = findViewById(R.id.textView22);
            TextView priceTextView = findViewById(R.id.textView11);
            TextView phoneNumberTextView = findViewById(R.id.textView21);

            categoryTextView.setText(category);
            descriptionTextView.setText(description);
            priceTextView.setText(String.valueOf(price));
            phoneNumberTextView.setText(String.valueOf(phoneNumber));

        } else {

            Toast.makeText(this, "Truck not found", Toast.LENGTH_SHORT).show();
        }


        if (cursor != null) {
            cursor.close();
        }
    }
}
