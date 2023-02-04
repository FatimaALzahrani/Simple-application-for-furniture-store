package com.example.furniturestore;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.jar.Attributes;

public class DetailAnnoucement extends AppCompatActivity {
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_annoucement);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
//        int color=R.color.bar;
//        ColorDrawable colorDrawable
//                = new ColorDrawable(getResources().getColor(color));
//        actionBar.setBackgroundDrawable(colorDrawable);
//        actionBar.setTitle(" ");
        actionBar.hide();
        ImageView imageView = (ImageView) findViewById(R.id.img);
        ImageView imageView2 =(ImageView) findViewById(R.id.imgD);
        TextView name = (TextView) findViewById(R.id.namep);
        TextView Durability =(TextView) findViewById(R.id.Durability);
        TextView price = (TextView) findViewById(R.id.price);
        //        TextView type = (TextView) findViewById(R.id.Type);
        TextView Composition =(TextView) findViewById(R.id.Composition);
        String Name=getIntent().getStringExtra("name");
        String Price=getIntent().getStringExtra("price");
        String composition=getIntent().getStringExtra("composition");
        String durability=getIntent().getStringExtra("durability");
        String dimensions=getIntent().getStringExtra("dimensions");
        String image=getIntent().getStringExtra("image");
        String type=(getIntent().getStringExtra("type"));
        String color=(getIntent().getStringExtra("color"));
        name.setText(Name);
        price.setText(Price+" R.S");
        Composition.setText(composition);
        Durability.setText(durability);
        Glide.with(imageView2.getContext()).load(getIntent().getStringExtra("dimensions")).into(imageView2);
        Glide.with(imageView.getContext()).load(getIntent().getStringExtra("image")).into(imageView);
    }

    public void Cart(View view) {
        Intent intent=new Intent(DetailAnnoucement.this,Cart.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
    public void Profile(View view) {
        Intent intent=new Intent(DetailAnnoucement.this,Profile.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
    public void addannouncements(View view) {
        Intent intent=new Intent(DetailAnnoucement.this,AddAnnouncements.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
    public void announcements(View view) {
        Intent intent=new Intent(DetailAnnoucement.this,Announcements.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
    public void Home(View view) {
        Intent intent=new Intent(DetailAnnoucement.this,Home.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void addcart(View view) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Cart");
        ImageView imageView = (ImageView) findViewById(R.id.img);
        ImageView imageView2 =(ImageView) findViewById(R.id.imgD);
        TextView name = (TextView) findViewById(R.id.namep);
        TextView Durability =(TextView) findViewById(R.id.Durability);
        TextView price = (TextView) findViewById(R.id.price);
        //        TextView type = (TextView) findViewById(R.id.Type);
        TextView Composition =(TextView) findViewById(R.id.Composition);
        String Name=getIntent().getStringExtra("name");
        String Price=getIntent().getStringExtra("price");
        String composition=getIntent().getStringExtra("composition");
        String durability=getIntent().getStringExtra("durability");
        String dimensions=getIntent().getStringExtra("dimensions");
        String image=getIntent().getStringExtra("image");
        String type=(getIntent().getStringExtra("type"));
        String color=(getIntent().getStringExtra("color"));
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            Detail d=new Detail(Name,image,Price,type,color,composition,durability,dimensions,user.getEmail(), user.getPhoneNumber());
            String id = databaseReference.push().getKey();
            databaseReference.child(id).setValue(d);
            Toast.makeText(DetailAnnoucement.this, "Announcement added to cart", Toast.LENGTH_SHORT).show();
        }
}