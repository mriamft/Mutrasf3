package com.example.mutrasf;
import static com.example.mutrasf.DBHelper.COLUMN_RESERVEDID;
import static com.example.mutrasf.DBHelper.COLUMN_USERID;
import static com.example.mutrasf.DBHelper.COLUMN_TIME;
import static com.example.mutrasf.DBHelper.COLUMN_DATE;
//import static com.example.mutrasf.DBHelper.COLUMN_FOODTRUCK_PHOTO;
import static com.example.mutrasf.DBHelper.COLUMN_TRUCK_ID;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextWatcher;
import android.text.Editable;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class myreservations extends AppCompatActivity {

    DBHelper db;

    ArrayList<String> name, date, time, id;
    ArrayList<byte[]> photo;

    RecyclerView recycler;
    TruckAdapter adapter;
    private BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreservations);
        bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homemenu) {
                    startActivity(new Intent(myreservations.this, dashboard.class));
                    return true;
                } else if (item.getItemId() == R.id.wishlist) {
                    startActivity(new Intent(myreservations.this, wishlist.class));
                    return true;
                } else if (item.getItemId() == R.id.my_reservations) {
                    startActivity(new Intent(myreservations.this, myreservations.class));
                    return true;
                }
                else if (item.getItemId() == R.id.logout) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(myreservations.this);
                    builder.setTitle("Logout Confirmation");
                    builder.setMessage("Are you sure you want to logout?");

                    //logout
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();

                            startActivity(new Intent(myreservations.this, MainActivity.class));
                            finish();
                        }
                    });

                    //no logout
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });


                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

                return false;


            }
        });

        EditText searchText = findViewById(R.id.searchtext);

        recycler = findViewById(R.id.recycler);
        db = new DBHelper(this);
        name = new ArrayList<>();
        date = new ArrayList<>();
        time = new ArrayList<>();
        id = new ArrayList<>();
        //photo = new ArrayList<>();

        //adapter = new TruckAdapter(this, name, price, photo, phone, id);
        adapter = new TruckAdapter(this, name, date, time, id);
        recycler.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(gridLayoutManager);

        displayData();
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String query = s.toString().trim();
                searchMyReservations(query);
            }
        });

    }

    private void displayData() {
        //Intent intent = getIntent();
        //String userEmail = intent.getStringExtra("userEmail");
        int userId = db.getUserIdFromName("phoneNumber");

        Cursor cursor = db.getMYreservation(userId);

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "You Do not have any reservation", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                try {
                    id.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESERVEDID)));
                    name.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERID)).concat(" "));
                    date.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
                    time.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME)));
                    //photo.add(cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_FOODTRUCK_PHOTO)));

                } catch (Exception e) {
                    // Handle the exception
                }
            }
        }
    }
    // search my reservations:


    private void searchMyReservations(String query) {
        Cursor cursor = db.searchReservationByName(query);

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No reservations found", Toast.LENGTH_SHORT).show();
            return;
        }

        // Clear the lists to avoid duplicates
        id.clear();
        name.clear();
        date.clear();
        time.clear();

        // Process the search results
        while (cursor.moveToNext()) {
            try {
                id.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RESERVEDID)));
                name.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERID)).concat(" "));
                date.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
                time.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME)));
            } catch (Exception e) {
                // Handle the exception
            }
        }

        // Notify the adapter about the changes in the data
        adapter.notifyDataSetChanged();
    }
}