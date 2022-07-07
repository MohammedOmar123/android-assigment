package com.example.final_project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.final_project2.adapter.BookAdapter;

import java.util.ArrayList;

public class ShowBooks extends AppCompatActivity {
    ArrayList<Book> books;
    public static Category c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_books);
        MyDatabase db = new MyDatabase(this);
//Intent i = getIntent();
        //Category category = (Category) i.getSerializableExtra("cat");
        // Here i will take the value of the category that clicked on and pass it
        // into get_all_books database method to get books that
        // related to this category

        String item = c.getName();
        books = db.get_all_Books(item);
        RecyclerView rv_showBooks = findViewById(R.id.rv_showBooks);
        BookAdapter adapter = new BookAdapter(books, ShowBooks.this);
        rv_showBooks.setAdapter(adapter);
        rv_showBooks.hasFixedSize();
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv_showBooks.setLayoutManager(lm);
    }

}