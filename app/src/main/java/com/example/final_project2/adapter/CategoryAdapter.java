package com.example.final_project2.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project2.BookDetails;
import com.example.final_project2.Category;
import com.example.final_project2.R;
import com.example.final_project2.ShowBooks;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private ArrayList<Category> categories;
    private Activity activity;
    //  private RecyclerViewClickListener listener;

    public CategoryAdapter(ArrayList<Category> categories, Activity activity/* RecyclerViewClickListener listener*/) {
        this.categories = categories;
        this.activity = activity;
        //   this.listener = listener;
    }

    @NonNull

    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(activity).inflate(R.layout.category_item_desgin, parent, false);
        return new CategoryViewHolder(root);
    }// end onCreate method

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.nameTv.setText(categories.get(position).getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(activity, ShowBooks.class);
                ShowBooks.c = category;
                activity.startActivity(i);
            }
        });
    }// end onBindViewHolder method

    @Override
    public int getItemCount() {
        return categories.size();
    }
    // end getItemCount method

    public class CategoryViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener */ {
        public View view;
        public TextView nameTv;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            this.nameTv = view.findViewById(R.id.nameTv);
            //   itemView.setOnClickListener(this);
        }
        //end of CategoryViewHolder Class



//        @Override
//        public void onClick(View view) {
//            listener.onClick(view, getAdapterPosition());
//        }
//    }
//
//    public interface RecyclerViewClickListener {
//        void onClick(View v, int position);
//    }


    }//end of CategoryAdapter Class
}
