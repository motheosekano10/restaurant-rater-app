package com.example.restaurantrater;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RestaurantAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    RestaurantAdapter adapter;
    ArrayList<Restaurant> list;
    DatabaseHelper db;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Database
        db = new DatabaseHelper(this);

        // Initialize UI components
        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);

        // Load data from database
        list = db.getAllRestaurants();

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RestaurantAdapter(list, this);
        recyclerView.setAdapter(adapter);

        // Floating Action Button to add new restaurant
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddRestaurantActivity.class);
            startActivity(intent);
        });
    }

    // Refresh the list when returning from the "Add" screen
    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        list.addAll(db.getAllRestaurants());
        adapter.notifyDataSetChanged();
    }

    // Handle delete button click from the Adapter
    @Override
    public void onDeleteClick(int id) {
        if (db.deleteRestaurant(id)) {
            // Remove from local list to update UI instantly
            list.removeIf(r -> r.id == id);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Restaurant deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error deleting", Toast.LENGTH_SHORT).show();
        }
    }
}
