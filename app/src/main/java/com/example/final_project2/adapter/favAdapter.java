package com.example.final_project2.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project2.Book;
import com.example.final_project2.FavBook;
import com.example.final_project2.R;

import java.util.ArrayList;

public class favAdapter extends RecyclerView.Adapter<favAdapter.favViewHolder> {
    ArrayList<Book>books;
    Activity activity;

    public favAdapter(ArrayList<Book> books, Activity activity) {
        this.books = books ;
        this.activity = activity;
    }

    @NonNull
    @Override
    public favAdapter.favViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(activity).inflate(R.layout.fav_desgin_item, parent, false);
        return new favAdapter.favViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull favAdapter.favViewHolder holder, int position) {
        Book b = books.get(position);
        holder.book_name.setText(b.getName());
        holder.book_author.setText(b.getAuthor());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class favViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView book_name;
        public TextView book_author;
        public favViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            this.book_name = itemView.findViewById(R.id.book_name);
            this.book_author = itemView.findViewById(R.id.book_author);
        }
    }
}
