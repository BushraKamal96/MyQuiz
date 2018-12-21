package com.example.acer.myquiz;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.acer.myquiz.Common.Common;
import com.example.acer.myquiz.Interface.ItemClickListener;
import com.example.acer.myquiz.ViewHolder.CategoryViewHolder;
import com.example.acer.myquiz.model.Category;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    View myFragment;
    ImageView logout;


    Toolbar toolbar;

    RecyclerView listCategory;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference categories;

    public static CategoryFragment newInstance() {
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database= FirebaseDatabase.getInstance();
        categories = database.getReference("Category");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      myFragment  = inflater.inflate(R.layout.category_fragment, container, false);

        toolbar = (Toolbar) myFragment.findViewById(R.id.toolbar);
        logout = (ImageView) myFragment.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), MainActivity.class);
                startActivity(intent);


            }
        });



        listCategory = (RecyclerView)myFragment.findViewById(R.id.listCategory);
      listCategory.setHasFixedSize(true);
      layoutManager = new LinearLayoutManager(container.getContext());
      listCategory.setLayoutManager(layoutManager);


      loadCategories();

      return myFragment;
    }

    private void loadCategories() {

        adapter= new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(
                Category.class,
                R.layout.category_layout,
                CategoryViewHolder.class,
                categories
        ) {
            @Override
            protected void populateViewHolder(CategoryViewHolder viewHolder, final Category model, int position) {

                viewHolder.category_name.setText(model.getName());
                Picasso.with(getActivity())
                        .load(model.getImage())
                        .into(viewHolder.category_image);

                viewHolder.setItemClickListner(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Toast.makeText(getActivity(), String.format("%s|%s",adapter.getRef(position).getKey(), model.getName()), Toast.LENGTH_SHORT).show();
                        Intent startGame = new Intent(getActivity(), Start.class);
                        Common.categoryId= adapter.getRef(position).getKey();
                        Common.categoryName= model.getName();
                        startActivity(startGame);

                    }
                });


            }
        };

        adapter.notifyDataSetChanged();
        listCategory.setAdapter(adapter);

    }


}
