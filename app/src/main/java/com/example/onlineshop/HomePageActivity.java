package com.example.onlineshop;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ShoeAdapter adapter;
    private List<Shoe> allShoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.shoeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        allShoes = Arrays.asList(
                new Shoe("Classic Shoe", R.drawable.shoe1, new String[]{"36", "37", "38", "39", "40"}, 30.50),
                new Shoe("Modern Shoe", R.drawable.shoe2, new String[]{"36", "37", "38", "39", "40"}, 65.10),
                new Shoe("Heels Shoe", R.drawable.shoe3, new String[]{"36", "37", "38", "39", "40"}, 20.00),
                new Shoe("Flat Shoe", R.drawable.shoe4, new String[]{"36", "37", "38", "39", "40"}, 30.50)
        );
        adapter = new ShoeAdapter(allShoes);
        recyclerView.setAdapter(adapter);

        Button sizeGuideButton = findViewById(R.id.btnSizeGuide);
        sizeGuideButton.setOnClickListener(v -> showSizeGuidePopup());
    }

    private void showSizeGuidePopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.shoesize); // Ensure this drawable exists
        builder.setView(imageView);
        builder.setPositiveButton("Close", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.updateData(filterForSearch(newText));
                return true;
            }
        });
        return true;
    }

    private List<Shoe> filterForSearch(String query) {
        List<Shoe> filteredShoes = new ArrayList<>();
        if (query == null || query.isEmpty()) {
            return allShoes;
        }
        for (Shoe shoe : allShoes) {
            if (shoe.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredShoes.add(shoe);
            }
        }
        return filteredShoes;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_basket) {
            startActivity(new Intent(this, BasketActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
