package com.example.mutrasf;

import static com.example.mutrasf.DBHelper.COLUMN_CONTACT_PHONE;
import static com.example.mutrasf.DBHelper.COLUMN_FOODTRUCK_NAME;
import static com.example.mutrasf.DBHelper.COLUMN_FOODTRUCK_PRICE;
import static com.example.mutrasf.DBHelper.COLUMN_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.database.Cursor;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {

    DBHelper db;

    ArrayList<String> name, price, phone, id;

    RecyclerView recycler;
    TruckAdapter adapter;
    TextView userNameTextView, userNameTextView2;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent intent = getIntent();
        String userPhone = intent.getStringExtra("phoneNumber");

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homemenu) {
                    startActivity(new Intent(dashboard.this, dashboard.class));
                    return true;
                } else if (item.getItemId() == R.id.wishlist) {
                    startActivity(new Intent(dashboard.this, wishlist.class));
                    return true;
                } else if (item.getItemId() == R.id.my_reservations) {
                    startActivity(new Intent(dashboard.this, myreservations.class));
                    return true;
                } else if (item.getItemId() == R.id.logout) {
                    SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.apply();

                    startActivity(new Intent(dashboard.this, MainActivity.class));
                    finish();
                    return true;

                }
                return false;

            }
        });

        recycler = findViewById(R.id.recycler);
        db = new DBHelper(this);
        name = new ArrayList<>();
        price = new ArrayList<>();
        phone = new ArrayList<>();
        id = new ArrayList<>();

        if (userPhone != null && !userPhone.isEmpty()) {
            int userName = db.getUserIdFromName(userPhone);
            String userName1 = db.GetUserName(userName);
            userNameTextView = findViewById(R.id.HelloText);
            userNameTextView.setText("Hi " + userName1);
        } else {
            userNameTextView2 = findViewById(R.id.HelloText2);
            userNameTextView2.setText("Hi ");
        }

        adapter = new TruckAdapter(this, name, price, phone, id);
        recycler.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(gridLayoutManager);

        displayData();

        // Set click listener for RecyclerView items
        recycler.addOnItemTouchListener(new RecyclerItemClickListener(this, recycler, new RecyclerItemClickListener.OnItemClickListener() {


            @Override
            public void onItemClick(View view, int position) {
                String truckId = id.get(position);
                String truckName = name.get(position);
                String truckPrice = price.get(position);
                String truckPhone = phone.get(position);

                // Start TruckProfileActivity and pass the truck data as extras
                Intent intent = new Intent(dashboard.this, TruckProfileActivity.class);
                intent.putExtra("TRUCK_ID", truckId);
                intent.putExtra("TRUCK_NAME", truckName);
                intent.putExtra("TRUCK_PRICE", truckPrice);
                intent.putExtra("TRUCK_PHONE", truckPhone);
                startActivity(intent);
            }
        }));
    }

    private void displayData() {

        Cursor cursor = db.getFoodTrucks();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No food truck to reserve", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                try {
                    id.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                    name.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FOODTRUCK_NAME)).concat(" "));
                    price.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FOODTRUCK_PRICE)));
                    phone.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTACT_PHONE)));

                } catch (Exception e) {
                    // Handle the exception
                }
            }
        }
    }
}
