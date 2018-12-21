package com.example.acer.myquiz.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.myquiz.Interface.ItemClickListener;
import com.example.acer.myquiz.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

   public TextView category_name;
   public ImageView category_image;

   private ItemClickListener itemClickListner;




    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        category_image = (ImageView)itemView.findViewById(R.id.category_image);
        category_name = (TextView)itemView.findViewById(R.id.category_name);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListner(ItemClickListener itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    @Override
    public void onClick(View view) {
        itemClickListner.onClick(view,getAdapterPosition(), false);

    }
}
