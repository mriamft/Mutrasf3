package com.example.mutrasf;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TruckProfileActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truckprofile);


        dbHelper = new DBHelper(this);


        String truckId = getIntent().getStringExtra("TRUCK_ID");
        String truckName = getIntent().getStringExtra("TRUCK_NAME");
        String truckPrice = getIntent().getStringExtra("TRUCK_PRICE");
        String truckPhone = getIntent().getStringExtra("TRUCK_PHONE");

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
