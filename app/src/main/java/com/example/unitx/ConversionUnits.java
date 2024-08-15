package com.example.unitx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ConversionUnits extends AppCompatActivity implements CustomAdapter.OnNoteListener{
    RecyclerView recyclerView;
    SearchView searchView;
    TextView textView1,textView;
    ArrayList<String> conversion_unit_list=new ArrayList<>();
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_units);
        searchView=findViewById(R.id.searchView2);
        searchView.clearFocus();
        Intent intent=getIntent();
        message=intent.getStringExtra("Selected_Category");
        conversion_unit_list=intent.getStringArrayListExtra("Conversion List");
        textView=findViewById(R.id.textView3);
        textView1=findViewById(R.id.textView6);
        textView.setText(message);
        recyclerView=findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList2(newText);
                return true;
            }
        });
        recyclerView.setAdapter(new CustomAdapter(conversion_unit_list,this));
    }

    void filterList2(String newText) {
        ArrayList<String> filteredList2= new ArrayList<>();
        for (String item:conversion_unit_list) {
//            for(int i=0;i<capture.length;i++) {
                if (item.toLowerCase().contains(newText.toLowerCase())) {
                    filteredList2.add(item);
                }
        }
        if(filteredList2.isEmpty()) {
            textView1.setText(R.string.valid_unit);
            recyclerView.setAdapter(new CustomAdapter(new ArrayList<>(0),this));
        }
        else {
            textView1.setText("");
            recyclerView.setAdapter(new CustomAdapter(filteredList2,this));
        }
    }

    @Override
    public void onNoteClick(String clicked_item) {
        Intent intent=new Intent(this,CalculationDisplay.class);
        intent.putExtra("Calculation Unit", clicked_item);
        intent.putExtra("Passing Category", message);
        startActivity(intent);
    }
}