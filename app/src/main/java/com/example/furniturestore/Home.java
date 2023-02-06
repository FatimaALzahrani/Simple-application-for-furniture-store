package com.example.furniturestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.A;

import java.util.Collections;
import java.util.Map;


public class Home extends AppCompatActivity {

    private TextView home2;
    private TextView tv;
    Intent intent;
    MeowBottomNavigation bottomNavigation;
    private Handler handler;
    private DatabaseReference ref;
    private DatabaseReference reference;
    private String userID;
    private String count;

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
        int color = R.color.bar;
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(color));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(" ");
        actionBar.hide();
        bottomNavigation=findViewById(R.id.navi);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.aann));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_add));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.cart2));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.profile2));
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
//                Toast.makeText(getApplicationContext(),"Clicked " +item.getId(),Toast.LENGTH_LONG).show();
            }
        });

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment;
                if (item.getId() == 2) {
                    fragment=new Announcements2();
                }else if (item.getId() == 3) {
                    fragment=new AddAnnouncements2();
                }else if (item.getId() == 4) {
                    fragment=new Cart22();
                }else if(item.getId()==5){
                    fragment=new Profile2();
                } else
                fragment = new Home2();
                loadFragment(fragment);
            }
        });
        bottomNavigation.show(1,true);

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
//                Toast.makeText(getApplicationContext(),"Reselect " +item.getId(),Toast.LENGTH_LONG).show();
                Fragment fragment;
                if (item.getId() == 2) {
                    fragment=new Announcements2();
                }else if (item.getId() == 3) {
                    fragment=new AddAnnouncements2();
                }else if (item.getId() == 4) {
                    fragment=new Cart22();
                }else if(item.getId()==5){
                    fragment=new Profile2();
                } else
                    fragment = new Home2();
                loadFragment(fragment);
            }
        });
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        ref = database.getReference();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        reference = FirebaseDatabase.getInstance().getReference("Users");
//        userID = user.getUid();
//        ref.child("Cart").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int co = 0;
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    Detail product = snapshot.getValue(Detail.class);
//                    if (product.getEmail().equals(user.getEmail())) {
//                        co++;
//                    }
//                }
//                count = "" + co;
//                }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
        bottomNavigation.setCount(4,Cart22.count);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_nav_menu, menu);
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
        if (item.getItemId() == R.id.annou) {
            intent = new Intent(Home.this, Announcements.class);
//                startActivity(intent);
//                rep(new Announcements());
            return true;
//            }else if(item.getItemId()== R.id.plus) {
////                startActivity(new Intent(Home.this, AddAnnouncements.class));
//                rep(new AddAnnouncements());
//                return true;
//            }else if(item.getItemId()==R.id.cart) {
////                startActivity(new Intent(Home.this, Cart.class));
//                rep(new Cart());
//                return true;
//            }else if (item.getItemId()==R.id.profile) {
////                startActivity(new Intent(Home.this, Profile.class));
//                rep(new Profile());
//                return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    //define a load method to feed the screen
    private void loadFragment(Fragment fragment) {
        //replace the fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag,fragment, null)
                .commit();
    }

        public void rep(Fragment fragment){
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frag,fragment);
            fragmentTransaction.commit();
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
    public void Favorite(View view) {
        Intent intent = new Intent(Home.this, Favorite2.class);
        startActivity(intent);
    }
    public void map(View v){
        Intent intent=new Intent(Home.this,MapsActivity.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
}