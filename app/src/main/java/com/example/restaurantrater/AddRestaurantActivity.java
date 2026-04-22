package com.example.restaurantrater;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddRestaurantActivity extends AppCompatActivity {

    EditText etName, etCuisine;
    RatingBar rbRating;
    Button btnSave;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName = findViewById(R.id.etName);
        etCuisine = findViewById(R.id.etCuisine);
        rbRating = findViewById(R.id.rbRating);
        btnSave = findViewById(R.id.btnSave);
        db = new DatabaseHelper(this);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String cuisine = etCuisine.getText().toString().trim();
            float rating = rbRating.getRating();

            if (name.isEmpty() || cuisine.isEmpty()) {
                Toast.makeText(this, "Please enter name and cuisine", Toast.LENGTH_SHORT).show();
            } else {
                // Save to database
                boolean success = db.addRestaurant(name, cuisine, rating);
                if (success) {
                    Toast.makeText(this, "Restaurant added!", Toast.LENGTH_SHORT).show();
                    finish(); // Close activity and go back to list
                } else {
                    Toast.makeText(this, "Error saving data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
