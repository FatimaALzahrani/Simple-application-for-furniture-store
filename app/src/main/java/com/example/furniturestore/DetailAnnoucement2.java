package com.example.furniturestore;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

public class DetailAnnoucement2 extends Fragment {
    public DetailAnnoucement2() {
        // Required empty public constructor
    }
    public static DetailAnnoucement2 newInstance(String param1, String param2) {
        DetailAnnoucement2 fragment = new DetailAnnoucement2();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_detail_annoucement2, container, false);

        final DatabaseReference databaseReference;
        final FirebaseDatabase firebaseDatabase;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Cart");
        ImageView ad;
            ImageView imageView = (ImageView) view.findViewById(R.id.img);
            ImageView imageView2 =(ImageView) view.findViewById(R.id.imgD);
            TextView name = (TextView) view.findViewById(R.id.namep);
            TextView Durability =(TextView) view.findViewById(R.id.Durability);
            TextView price = (TextView) view.findViewById(R.id.price);
            //        TextView type = (TextView) findViewById(R.id.Type);
            TextView Composition =(TextView) view.findViewById(R.id.Composition);
            String Name=this.getArguments().getString("name");
            String Price=this.getArguments().getString("price");
            String composition=this.getArguments().getString("composition");
            String durability=this.getArguments().getString("durability");
            String dimensions=this.getArguments().getString("dimensions");
            String image=this.getArguments().getString("image");
            String type=(this.getArguments().getString("type"));
            String color=(this.getArguments().getString("color"));
            name.setText(Name);
            price.setText(Price);
            Composition.setText(composition);
            Durability.setText(durability);
            Glide.with(imageView2.getContext()).load(this.getArguments().getString("dimensions")).into(imageView2);
            Glide.with(imageView.getContext()).load(this.getArguments().getString("image")).into(imageView);
            ad=view.findViewById(R.id.ad);
            ad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    Detail d = new Detail(Name, image, Price, type, color, composition, durability, dimensions, user.getEmail(), user.getPhoneNumber());
                    String id = databaseReference.push().getKey();
                    databaseReference.child(id).setValue(d);
                    Toast.makeText(getContext(), "Announcement added to cart", Toast.LENGTH_SHORT).show();
                }
            });

        return view;
    }
    public void back(View v){
        Fragment fragment=new Announcements2();
        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag,fragment);
        fragmentTransaction.commit();
    }

}