package com.example.final_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateCatogoery extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_catogoery);
        EditText category_name_editText = findViewById(R.id.category_name_editText);
        Button add_category_btn = findViewById(R.id.add_category_btn);

        add_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabase db = new MyDatabase(CreateCatogoery.this);
                String name = category_name_editText.getText().toString();
                Category category_object = new Category(name);
                db.insertCategory(category_object);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }//end onCreate method


}