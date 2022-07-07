package com.example.final_project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView creat_book_cardview ;
    CardView create_category_cardview ;
    CardView library_cardview ;
    CardView fav ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization() ;

        creat_book_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CreateBookAcitvity.class);
                startActivity(i);
            }
        });
        create_category_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CreateCatogoery.class);
                startActivity(i);
            }
        });
        library_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ShowCategories.class);
                startActivity(i);
            }
        });
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),FavBook.class);
                startActivity(i);
            }
        });
    }
    public void initialization(){
        creat_book_cardview = findViewById(R.id.create_book);
        create_category_cardview = findViewById(R.id.create_category);
        library_cardview = findViewById(R.id.library) ;
        fav = findViewById(R.id.sp);
//        book_category_1 =findViewById(R.id.book_category_1);
    }

}