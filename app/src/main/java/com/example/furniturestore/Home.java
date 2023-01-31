package com.example.furniturestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Home extends AppCompatActivity {

    private TextView home2;
    private TextView tv;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home2= (TextView) findViewById(R.id.home2);
        tv=(TextView) findViewById(R.id.name);
        String msg = "Hi "+getIntent().getStringExtra("name")+" in Furniture";
        tv.setText(getIntent().getStringExtra("name"));
//        home2.setText(msg);

    }
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v,
//                                    ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.bottom_nav_menu, menu);
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.annou:
//                announcements();
//                return true;
//            case R.id.plus:
//                addannouncements();
//                return true;
//            case R.id.cart:
//                Cart();
//                return true;
//            case R.id.profile:
//                Profile();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

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