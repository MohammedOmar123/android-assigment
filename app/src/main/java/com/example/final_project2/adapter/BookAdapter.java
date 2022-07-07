package com.example.final_project2.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.final_project2.Book;
import com.example.final_project2.BookDetails;
import com.example.final_project2.R;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private ArrayList<Book> Books;
    private Activity activity;

    public BookAdapter(ArrayList<Book> books, Activity activity)/*,RecyclerViewClickListener listener)*/ {
        this.Books = books;
        this.activity = activity;

    }

    @NonNull
    @Override
    public BookAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(activity).inflate(R.layout.book_desgin_item, parent, false);
        return new BookAdapter.BookViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.BookViewHolder holder, int position) {
        Book b = Books.get(position);
        holder.book_name.setText(b.getName());
        holder.book_author.setText(b.getAuthor());

        if (b.isFav()) {
            holder.favBtn.setBackground(activity.getDrawable(R.drawable.favtwo));

        } else {
            holder.favBtn.setBackground(activity.getDrawable(R.drawable.favone));

        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookDetails.b = b;
                Intent i = new Intent(activity, BookDetails.class);
                //  i.putExtra("book",b) ;
                activity.startActivity(i);
            }
        });
        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.setFav(!b.isFav());//makes it true if its false and vice versa
                changeStatus(holder, b.isFav());
            }
        });
    }

    @Override
    public int getItemCount() {
        return Books.size();
    }

    public void changeStatus(BookViewHolder holder, boolean Status) {
        if (Status) {
            holder.favBtn.setBackground(activity.getDrawable(R.drawable.favtwo));
            //    MyDatabase.fav_var = true ;
        } else {
            holder.favBtn.setBackground(activity.getDrawable(R.drawable.favone));
        }
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView book_name;
        public TextView book_author;
        public Button favBtn;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            this.book_name = itemView.findViewById(R.id.book_name);
            this.book_author = itemView.findViewById(R.id.book_author);
            favBtn = itemView.findViewById(R.id.fv_btn);
        }
    }
}
