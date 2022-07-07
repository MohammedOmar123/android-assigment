package com.example.final_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BookDetails extends AppCompatActivity {
    public static Book b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        ImageView view_image_Book2 = findViewById(R.id.view_image_Book2);
        TextView view_book_name = findViewById(R.id.view_book_name);
        TextView view_book_author = findViewById(R.id.view_book_author);
        TextView view_book_release = findViewById(R.id.view_book_release);
        TextView view_book_pages_number = findViewById(R.id.view_book_pages_number);
        TextView view_book_category = findViewById(R.id.view_book_category);

        view_book_name.setText(b.getName());
        view_book_author.setText(b.getAuthor());
        view_book_release.setText(b.getRelease() + "");
        view_book_pages_number.setText(b.getPages_number() + "");
        view_book_category.setText(b.getCategory());
        // imageContent = BitmapFactory.decodeByteArray(b.getImage(),0,b.getImage().length);
     //   view_image_Book2.setImageBitmap(imageContent);
        view_image_Book2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });

        Button edit_book = findViewById(R.id.edit_book);
        Button delete_book = findViewById(R.id.delete_book);

        MyDatabase db = new MyDatabase(this);
        edit_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UpdateBook.class);
                UpdateBook.b =b ;
                startActivity(intent);

            }
        });

      delete_book.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
               db.deleteBook(b.getId());
Intent intent = new Intent(getApplicationContext(),ShowBooks.class) ;
startActivity(intent);
finish();            }});
    }
    }
