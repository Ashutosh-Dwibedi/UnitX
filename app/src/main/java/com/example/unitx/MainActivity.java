package com.example.unitx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnNoteListener {
    RecyclerView recyclerView;
    SearchView searchView;
    TextView textView,popular_units_text;
    ArrayList<String> unit_categories=new ArrayList<>();
    ArrayList<String> conversion_options=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.dynamicText);
        popular_units_text=findViewById(R.id.textView5);
        searchView=findViewById(R.id.searchView);
        unit_categories=new JSONHandler(this).unitJson();
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CustomAdapter(unit_categories,this));

    }

    void filterList(String newText) {
        ArrayList<String> filteredList = new ArrayList<>();
        for (String item:unit_categories){
            if (item.toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            textView.setText(R.string.vaid_category);
            popular_units_text.setVisibility(View.INVISIBLE);
            recyclerView.setAdapter(new CustomAdapter(new ArrayList<>(0),this));
        }
        else {
            textView.setText("");
            popular_units_text.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new CustomAdapter(filteredList,this));
        }
    }

    @Override
    public void onNoteClick(String clicked_item) {
        conversion_options=new JSONHandler(getApplicationContext()).innerJson(clicked_item);
        Intent intent=new Intent(this,ConversionUnits.class);
        intent.putExtra("Selected_Category",clicked_item);
        intent.putExtra("Conversion List",conversion_options);
        startActivity(intent);
    }
}
