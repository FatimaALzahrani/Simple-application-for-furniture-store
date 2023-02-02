package com.example.furniturestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.checkerframework.checker.units.qual.A;


public class Home extends AppCompatActivity {

    private TextView home2;
    private TextView tv;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        tv=(TextView) findViewById(R.id.name);
//        String msg = "Hi "+getIntent().getStringExtra("name")+" in Furniture";
//        tv.setText(getIntent().getStringExtra("name"));
//        home2.setText(msg);
// Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        int color=R.color.bar;
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(color));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(" ");
        actionBar.hide();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.bottom_nav_menu,menu);
        return true;
    }
//
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v,
//                                    ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.bottom_nav_menu, menu);
//    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
            if(item.getItemId()== R.id.annou) {
                intent=new Intent(Home.this, Announcements.class);
                startActivity(intent);
                return true;
            }else if(item.getItemId()== R.id.plus) {
                startActivity(new Intent(Home.this, AddAnnouncements.class));
                return true;
            }else if(item.getItemId()==R.id.cart) {
                startActivity(new Intent(Home.this, Cart.class));
                return true;
            }else if (item.getItemId()==R.id.profile) {
                startActivity(new Intent(Home.this, Profile.class));
                return true;
            }else
                return super.onOptionsItemSelected(item);
        }
//        switch (item.getItemId()) {
//            case R.id.annou:
//                startActivity(new Intent(Home.this, Announcements.class));
//                return true;
//            case R.id.plus:
//                startActivity(new Intent(Home.this, AddAnnouncements.class));
//                return true;
//            case R.id.cart:
//                startActivity(new Intent(Home.this, Cart.class));
//                return true;
//            case R.id.profile:
//                startActivity(new Intent(Home.this, Profile.class));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }}


//    public void Cart() {
//        Intent intent=new Intent(Home.this,Cart.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
//    }
//    public void Profile() {
//        Intent intent=new Intent(Home.this,Profile.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
//    }
//    public void addannouncements() {
//        Intent intent=new Intent(Home.this,AddAnnouncements.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
//    }
//    public void announcements() {
//        Intent intent=new Intent(Home.this,Announcements.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
//    }
public void announcements(View view) {
    Intent intent = new Intent(Home.this, Announcements.class);
    intent.putExtra("email", getIntent().getStringExtra("email"));
    startActivity(intent);
}

    public void Fav(View view) {
        Intent intent = new Intent(Home.this, Favorite.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void Profile(View view) {
        Intent intent = new Intent(Home.this, Profile.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void addannouncements(View view) {
        Intent intent = new Intent(Home.this, AddAnnouncements.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void Cart(View view) {
        Intent intent = new Intent(Home.this, Cart.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
}