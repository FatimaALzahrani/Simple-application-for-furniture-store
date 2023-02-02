package com.example.furniturestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Favorite2 extends AppCompatActivity {
    private RecyclerView courseRV;
    private ArrayList<RecyclerData> recyclerDataArrayList;
    private RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite2);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
//        int color=R.color.bar;
//        ColorDrawable colorDrawable
//                = new ColorDrawable(getResources().getColor(color));
//        actionBar.setBackgroundDrawable(colorDrawable);
//        actionBar.setTitle(" ");
        actionBar.hide();
        courseRV = findViewById(R.id.idRVCourse);
        recyclerDataArrayList = new ArrayList<>();
        DB_SQLlite DB =new DB_SQLlite(Favorite2.this);
        ListView listView = (ListView)findViewById(R.id.productlist);
        ArrayList<Detail> recyclerDataArrayList = DB.getAllCart();
        if (recyclerDataArrayList.size()==0) {
            ImageView imgf = (ImageView) findViewById(R.id.imgf);
            int imgid = this.getResources().getIdentifier("emptyfav", "drawable", this.getPackageName());
            imgf.setImageResource(imgid);
        } else {
        }
        recyclerViewAdapter = new RecyclerViewAdapter(recyclerDataArrayList, this ,new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Detail p) {
//                Detail d=new Detail(p.name_of_announcement,p.price_of_announcement,p.type_of_announcement,p.color_of_announcement,p.composition_of_announcement,p.durability_of_announcement,p.url_of_the_announcement_dimensions,p.url_of_the_announcement_image);
                Intent intent=new Intent(Favorite2.this,DetailAnnoucement.class);
                intent.putExtra("name",p.name_of_announcement);
                intent.putExtra("price",p.price_of_announcement);
                intent.putExtra("type",p.type_of_announcement);
                intent.putExtra("color",p.color_of_announcement);
                intent.putExtra("composition",p.composition_of_announcement);
                intent.putExtra("durability",p.durability_of_announcement);
                intent.putExtra("dimensions",p.url_of_the_announcement_dimensions);
                intent.putExtra("image",p.url_of_the_announcement_image);
                startActivity(intent);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        courseRV.setLayoutManager(manager);
        courseRV.setAdapter(recyclerViewAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Detail deletedCourse = recyclerDataArrayList.get(viewHolder.getAdapterPosition());
                int position = viewHolder.getAdapterPosition();
                recyclerDataArrayList.remove(viewHolder.getAdapterPosition());
                recyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                DB.delete(deletedCourse.getName_of_announcement());
                Snackbar.make(courseRV, deletedCourse.getName_of_announcement(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerDataArrayList.add(position, deletedCourse);
                        recyclerViewAdapter.notifyItemInserted(position);
                    }
                }).show();
            }
        }).attachToRecyclerView(courseRV);
        if (recyclerDataArrayList.size()==0) {
            ImageView imgf = (ImageView) findViewById(R.id.imgf);
            int imgid = this.getResources().getIdentifier("emptyfav", "drawable", this.getPackageName());
            imgf.setImageResource(imgid);
        }
    }

    public void announcements(View view) {
        Intent intent = new Intent(Favorite2.this, Announcements.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
    public void Cart(View view) {
        Intent intent = new Intent(Favorite2.this, Cart.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void Profile(View view) {
        Intent intent = new Intent(Favorite2.this, Profile.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void addannouncements(View view) {
        Intent intent = new Intent(Favorite2.this, AddAnnouncements.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void Home(View view) {
        Intent intent = new Intent(Favorite2.this, Home.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
}
