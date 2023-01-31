package com.example.furniturestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
public class AddAnnouncements extends AppCompatActivity {
    EditText EDname_of_announcement, EDurl_of_the_announcement_dimensions, EDurl_of_the_announcement_image, EDprice_of_announcement, EDtype_of_announcement;
    EditText EDcomposition_of_announcement, EDdurability_of_announcement,EDcolor_of_announcement,EDnum;
    String name_of_announcement, url_of_the_announcement_dimensions, url_of_the_announcement_image, price_of_announcement, type_of_announcement;
    String composition_of_announcement, durability_of_announcement,color_of_announcement,num;
    ProgressBar progressBar;
    Button Add;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Detail product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_add_announcements);
        EDname_of_announcement = (EditText) findViewById(R.id.Name);
        EDurl_of_the_announcement_dimensions = (EditText) findViewById(R.id.UrlD);
        EDurl_of_the_announcement_image = (EditText) findViewById(R.id.Url);
        EDprice_of_announcement = (EditText) findViewById(R.id.Price);
        EDtype_of_announcement = (EditText) findViewById(R.id.Type);
        EDcomposition_of_announcement = (EditText) findViewById(R.id.Composition);
        EDdurability_of_announcement = (EditText) findViewById(R.id.Durability);
        EDcolor_of_announcement = (EditText) findViewById(R.id.Color);
        EDnum = (EditText) findViewById(R.id.num);
        progressBar = findViewById(R.id.ProgressBar);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Announcements");
        product = new Detail();
        Add = findViewById(R.id.add);
        
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name_of_announcement = EDname_of_announcement.getText().toString().trim();
                url_of_the_announcement_dimensions = EDurl_of_the_announcement_dimensions.getText().toString().trim();
                url_of_the_announcement_image = EDurl_of_the_announcement_image.getText().toString().trim();
                price_of_announcement = EDprice_of_announcement.getText().toString().trim();
                type_of_announcement = EDtype_of_announcement.getText().toString().trim();
                composition_of_announcement = EDcomposition_of_announcement.getText().toString().trim();
                durability_of_announcement = EDdurability_of_announcement.getText().toString().trim();
                color_of_announcement = EDcolor_of_announcement.getText().toString().trim();
                num = EDnum.getText().toString().trim();
                if (TextUtils.isEmpty(name_of_announcement) && TextUtils.isEmpty(url_of_the_announcement_dimensions) && TextUtils.isEmpty(url_of_the_announcement_image)
                        && TextUtils.isEmpty(price_of_announcement) && TextUtils.isEmpty(type_of_announcement) && TextUtils.isEmpty(composition_of_announcement)
                        && TextUtils.isEmpty(durability_of_announcement)) {
                    Toast.makeText(AddAnnouncements.this, "some data not found please enter it", Toast.LENGTH_SHORT).show();
                }else {
                    addDatatoFirebase(name_of_announcement, url_of_the_announcement_dimensions, url_of_the_announcement_image, price_of_announcement, type_of_announcement, composition_of_announcement, durability_of_announcement,color_of_announcement,num);
                }
            }
        });
    }
    private void addDatatoFirebase(String name_of_announcement, String url_of_the_announcement_dimensions, String url_of_the_announcement_image, String price_of_announcement, String type_of_announcement, String composition_of_announcement, String durability_of_announcement,String color_of_announcement,String num) {
        product.setName_of_announcement(name_of_announcement);
        product.setUrl_of_the_announcement_image(url_of_the_announcement_image);
        product.setUrl_of_the_announcement_dimensions(url_of_the_announcement_dimensions);
        product.setPrice_of_announcement(price_of_announcement);
        product.setType_of_announcement(type_of_announcement);
        product.setComposition_of_announcement(composition_of_announcement);
        product.setDurability_of_announcement(durability_of_announcement);
        product.setColor_of_announcement(color_of_announcement);
        product.setNumber(num);
        String id = databaseReference.push().getKey();
        databaseReference.child(id).setValue(product);
        Toast.makeText(AddAnnouncements.this, "Announcement added", Toast.LENGTH_SHORT).show();
        EDname_of_announcement.setText("");
        EDurl_of_the_announcement_dimensions.setText("");
        EDurl_of_the_announcement_image.setText("");
        EDprice_of_announcement.setText("");
        EDtype_of_announcement.setText("");
        EDcomposition_of_announcement.setText("");
        EDdurability_of_announcement.setText("");
        EDcolor_of_announcement.setText("");
 //        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                databaseReference.setValue(product);
//                Toast.makeText(AddAnnouncements.this, "Announcement added", Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(AddAnnouncements.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void Cart(View view) {
        Intent intent = new Intent(AddAnnouncements.this, Cart.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void Profile(View view) {
        Intent intent = new Intent(AddAnnouncements.this, Profile.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void Home(View view) {
        Intent intent = new Intent(AddAnnouncements.this, Home.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void announcements(View view) {
        Intent intent = new Intent(AddAnnouncements.this, Announcements.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
}
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_announcements);
//        EDname_of_announcement = (EditText)findViewById(R.id.Name);
//        EDurl_of_the_announcement_dimensions = (EditText) findViewById(R.id.UrlD);
//        EDurl_of_the_announcement_image = (EditText) findViewById(R.id.Url);
//        EDprice_of_announcement = (EditText) findViewById(R.id.Price);
//        EDtype_of_announcement = (EditText) findViewById(R.id.Type);
//        EDcomposition_of_announcement = (EditText) findViewById(R.id.Composition);
//        EDdurability_of_announcement = (EditText) findViewById(R.id.Durability);
//        progressBar = findViewById(R.id.ProgressBar);
//        Add = findViewById(R.id.add);
//        // Initialize Firebase Firestore
//        db = FirebaseFirestore.getInstance();
//    }
//    public void Add_new_Announcement(View view) {
//        name_of_announcement = EDname_of_announcement.getText().toString().trim();
//        url_of_the_announcement_dimensions = EDurl_of_the_announcement_dimensions.getText().toString().trim();
//        url_of_the_announcement_image = EDurl_of_the_announcement_image.getText().toString().trim();
//        price_of_announcement = EDprice_of_announcement.getText().toString().trim();
//        type_of_announcement = EDtype_of_announcement.getText().toString().trim() ;
//        composition_of_announcement = EDcomposition_of_announcement.getText().toString().trim();
//        durability_of_announcement = EDdurability_of_announcement.getText().toString().trim();
//    Add();
//    }
//        private void Add() {
//            progressBar.setVisibility(View.VISIBLE);
//            Add.setVisibility(View.INVISIBLE);
//                    Map<String, Object> Announcement = new HashMap<>();
//                    Announcement.put("name_of_announcement", name_of_announcement);
//                    Announcement.put("url_of_the_announcement_dimensions", url_of_the_announcement_dimensions);
//                    Announcement.put("url_of_the_announcement_image", url_of_the_announcement_image);
//                    Announcement.put("price_of_announcement", price_of_announcement);
//                    Announcement.put("type_of_announcement", type_of_announcement);
//                    Announcement.put("composition_of_announcement", composition_of_announcement);
//                    Announcement.put("durability_of_announcement", durability_of_announcement);
//
//                    db.collection("Announcement")
//                            .add(Announcement)
//                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                @Override
//                                public void onSuccess(DocumentReference documentReference) {
//                                    Toast.makeText(AddAnnouncements.this, "Announcement Added Successfully !", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(AddAnnouncements.this, Announcements.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(AddAnnouncements.this, "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//
//                }
