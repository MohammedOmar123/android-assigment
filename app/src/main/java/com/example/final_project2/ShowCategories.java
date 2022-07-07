package com.example.final_project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.final_project2.adapter.CategoryAdapter;

import java.util.ArrayList;

public class ShowCategories extends AppCompatActivity {
    ArrayList<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_categories);
        MyDatabase db = new MyDatabase(this);
        categories = db.get_all_categories();
        RecyclerView rv = findViewById(R.id.rv);
        CategoryAdapter adapter = new CategoryAdapter(categories, ShowCategories.this /*,listener*/);
        rv.setAdapter(adapter);
        rv.hasFixedSize();
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

    }

    }
