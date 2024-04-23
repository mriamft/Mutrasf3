package com.example.mutrasf;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class wishlist extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homemenu) {
                    startActivity(new Intent(wishlist.this, dashboard.class));
                    return true;
                } else if (item.getItemId() == R.id.wishlist) {
                    startActivity(new Intent(wishlist.this, wishlist.class));
                    return true;
                } else if (item.getItemId() == R.id.my_reservations) {
                    startActivity(new Intent(wishlist.this, myreservations.class));
                    return true;}
                else if (item.getItemId() == R.id.logout) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(wishlist.this);
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

                                startActivity(new Intent(wishlist.this, MainActivity.class));
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

    }
}