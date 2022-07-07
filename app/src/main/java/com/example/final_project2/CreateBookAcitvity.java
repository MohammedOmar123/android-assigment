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

public class CreateBookAcitvity extends AppCompatActivity {
    ImageView   img ;
static byte[] imageContent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book_acitvity);

        MyDatabase db = new MyDatabase(this);

        img =findViewById(R.id.resultImage);

        Spinner spinner = findViewById(R.id.spinner);
        EditText book_name = findViewById(R.id.book_name);
        EditText author_name = findViewById(R.id.author_name);
        EditText release_year = findViewById(R.id.release_year);
        EditText page_number = findViewById(R.id.page_number);
        Button creat_book = findViewById(R.id.create_book);
        ImageButton add_image=findViewById(R.id.imageButton);

        ArrayList<Category> categories = db.get_all_categories();

        ArrayList<String> content = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            String value = categories.get(i).getName();
            content.add(i, value);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, content);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);


        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });


        creat_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabase db = new MyDatabase(CreateBookAcitvity.this) ;

                String category = spinner.getSelectedItem().toString();
                String name = book_name.getText().toString();
                String author = author_name.getText().toString();
                String release = release_year.getText().toString();
                int page = Integer.parseInt(page_number.getText().toString());

                Book b = new Book(name,author,release,page,category);
                b.setImage(imageContent);
                db.insertBook(b);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 100 && resultCode ==RESULT_OK){
            Uri imageUri = intent.getData();
            try {
                InputStream inputStream =getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                img.setImageBitmap(bitmap);
                imageContent = getBytes(bitmap) ;

            }catch (FileNotFoundException e){
                e.printStackTrace();

            }
        }
    }

    public byte[]getBytes(Bitmap bitmap){
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
        return stream.toByteArray();
    }
}