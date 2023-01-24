package com.example.furniturestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private TextView home2;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home2= (TextView) findViewById(R.id.home2);
        tv=(TextView) findViewById(R.id.name);
        String msg = "Hi "+getIntent().getStringExtra("name")+" in Furniture";
        tv.setText(getIntent().getStringExtra("name"));
        home2.setText(msg);
    }

    public void Cart(View view) {
        Intent intent=new Intent(Home.this,Cart.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
    public void Profile(View view) {
        Intent intent=new Intent(Home.this,Profile.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
    public void addannouncements(View view) {
        Intent intent=new Intent(Home.this,AddAnnouncements.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
    public void announcements(View view) {
        Intent intent=new Intent(Home.this,Announcements.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
}