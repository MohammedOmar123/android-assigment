package com.example.final_project2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class UpdateBook extends AppCompatActivity {
    public static Book b;
    static byte[] imageContent ;
    ImageView img ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);
       img = findViewById(R.id.update_image);
        ImageButton update_image_button = findViewById(R.id.update_image_button) ;
        EditText update_book_name = findViewById(R.id.update_book_name);
        EditText update_author_name = findViewById(R.id.update_author_name);
        EditText update_release_year = findViewById(R.id.update_release_year);
        EditText update_page_number = findViewById(R.id.update_page_number);
        Button update_book = findViewById(R.id.update_book);
        MyDatabase db = new MyDatabase(this);
        update_book_name.setText(b.getName());
        update_author_name.setText(b.getAuthor());
        update_release_year.setText(b.getRelease());
        update_page_number.setText(b.getPages_number() + "");
        update_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });
    update_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String book_name = update_book_name.getText().toString();
                String author_name = update_author_name.getText().toString();
                String release_year = update_release_year.getText().toString();
                int page_number = Integer.parseInt(update_page_number.getText().toString());
                String category = BookDetails.b.getCategory();
                Book book = new Book(book_name, author_name, release_year, page_number, category);
                book.setId(BookDetails.b.getId());
                book.setImage(BookDetails.b.getImage());
                db.updateBook(book);
                Intent intent = new Intent(getApplicationContext(), BookDetails.class);
                BookDetails.b = book;
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == 100 && resultCode ==RESULT_OK) {

            Uri imageUri = intent.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                img.setImageBitmap(bitmap);
                imageContent = getBytes(bitmap) ;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }}
    public byte[] getBytes(Bitmap bitmap){
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
        return stream.toByteArray();
    }
}

