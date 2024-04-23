package com.example.mutrasf;

import androidx.appcompat.app.AppCompatActivity;
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
                    return true;
                } else if (item.getItemId() == R.id.logout) {
                    SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.apply();

                    startActivity(new Intent(wishlist.this, MainActivity.class));
                    finish();
                }
                return false;

            }
        });

    }
}