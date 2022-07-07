package com.example.final_project2;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    private static int db_version = 15;
    private static String db_name = "Library.db";
    private static String table_name = "CATEGORY";
    private static String table_book = "BOOK";
    private static String ID_table_book = "ID";
    private static String name_table_book = "NAME";
    private static String author_table_book = "AUTHOR";
    private static String releasey_table_book = "RELEASEY";
    private static String pages_table_book = "PAGES";
    private static String image_table_book = "IMAGE";
   private static String fav_table_book = "fav";

    private static String catogry_table_book = "CATOGRY";
    private static String catogry_table_catogry = " ID";

    Context context;
//public  static boolean fav_var =false ;
    public MyDatabase(@Nullable Context context) {
        super(context, db_name, null, db_version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + table_name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT UNIQUE)";
        //  String query2 = "CREATE TABLE " + table_book + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,AUTHOR TEXT,RELEASEY TEXT,PAGES INTEGER,IMAGE BLOB, CATOGRY TEXT  ,FOREIGN KEY(CATOGRY) REFERENCES "+table_name+"(NAME));";

        String query2 = "CREATE TABLE "
                + table_book + "("
                + ID_table_book + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + name_table_book + " TEXT not null, "
                + author_table_book + " TEXT not null, "
                + releasey_table_book + " TEXT not null,"
                + pages_table_book + " INTEGER,"
                + image_table_book + " BLOB,"
                + catogry_table_book + " TEXT,"
                + fav_table_book + " INTEGER DEFAULT 0 ,"

                + " FOREIGN KEY (" + catogry_table_book + ") REFERENCES " + table_name + "(" + catogry_table_catogry + "));";

        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query2);
    } //end onCreate method


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + table_name;
        String query2 = "DROP TABLE IF EXISTS " + table_book;
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query2);
        onCreate(sqLiteDatabase);
    }//end onUpgrade method

    public void insertCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", category.getName());
        long result = db.insert(table_name, null, cv);
        if (result != -1) {
            Toast.makeText(context, "category Added Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to Added category", Toast.LENGTH_SHORT).show();

        }
    }
    public ArrayList<Category> get_all_categories() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + table_name;

        Cursor cursor = null;
        if (db != null) { // this mean that is there data in the database ?
            cursor = db.rawQuery(query, null);
        }
        if (cursor == null) {
            return null;
        } else {
            ArrayList<Category> categories = new ArrayList<>();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                Category category_object = new Category(name);
                category_object.setId(id);
                categories.add(category_object);
            }
            return categories;
        }
    }

    public void insertBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(catogry_table_book, book.getCategory());
        cv.put(name_table_book, book.getName());
        cv.put(author_table_book, book.getAuthor());
        cv.put(releasey_table_book, book.getRelease());
        cv.put(pages_table_book, book.getPages_number());
       cv.put(image_table_book, book.getImage());
        long result = db.insert(table_book, null, cv);
        if (result != -1) {
            Toast.makeText(context,"Book Added Successfully",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Failed to Added Book",Toast.LENGTH_SHORT).show();

        }
    }
    public ArrayList<Book> get_all_Books (String item) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + table_book + " WHERE "
                + catogry_table_book + " = " + "?";
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, new String[]{item});
        }
        if (cursor == null) {
            return null;
        } else {
            ArrayList<Book> books = new ArrayList<>();// to store all the  data in the database in Arraylist
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String author = cursor.getString(2);
                String release = cursor.getString(3);
                int pages_number = cursor.getInt(4);
                  byte[] imageContent =cursor.getBlob(5) ;
                String catogery = cursor.getString(6);
                Book book_object = new Book(name, author, release, pages_number, catogery);
                book_object.setId(id);
                book_object.setImage(imageContent);
                books.add(book_object);
            }
            return books;
        }
    }

    public void updateBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(catogry_table_book, book.getCategory());
        cv.put(name_table_book, book.getName());
        cv.put(author_table_book, book.getAuthor());
        cv.put(releasey_table_book, book.getRelease());
        cv.put(pages_table_book, book.getPages_number());
        long result = db.update(table_book, cv, "id=?", new String[]{"" + book.getId()});

        if (result != -1) {
            Toast.makeText(context.getApplicationContext(), "Book Updated Successfully ", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(context.getApplicationContext(), "Book Updated Failed ", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteBook(int book_id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(table_book , "id=?" , new String[]{"" + book_id});
    }
}