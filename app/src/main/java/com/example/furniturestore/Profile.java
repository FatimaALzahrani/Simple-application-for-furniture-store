package com.example.furniturestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


public class Profile extends AppCompatActivity {
    private Button logout;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private TextView emailTxtView, nameTxtView, passwordTxtView,phoneTxtView;
    private final String TAG = this.getClass().getName().toUpperCase();
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private Map<String, String> userMap;
    private String email;
    private String userid;
    private static final String USERS = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout=(Button) findViewById(R.id.Sigout);
        String em= getIntent().getStringExtra("email");
        final TextView usernameTextView = (TextView) findViewById(R.id.username);
        final TextView passwordTextView = (TextView) findViewById(R.id.password);
        final TextView emailTextView = (TextView) findViewById(R.id.email);
        final TextView phoneTextView = (TextView) findViewById(R.id.phone);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile.this,Login.class));
            }
        });
        if(isOnline()) {
            user = FirebaseAuth.getInstance().getCurrentUser();
            reference = FirebaseDatabase.getInstance().getReference("Users");
            userID = user.getUid();
            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile = snapshot.getValue(User.class);
                    if (userProfile != null) {
                        String username = userProfile.username;
                        String password = userProfile.password;
                        String email = userProfile.email;
                        String phone = userProfile.mobile;
                        usernameTextView.setText(username);
                        passwordTextView.setText(password);
                        emailTextView.setText(email);
                        phoneTextView.setText(phone);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Profile.this, "Something worng happend!", Toast.LENGTH_LONG).show();
                }
            });
        }else{
            User userProfile = new DBHelper(this).ret(em);
            String username = userProfile.username;
            String password = userProfile.password;
            String email = userProfile.email;
            String phone = userProfile.mobile;
            usernameTextView.setText(username);
            passwordTextView.setText(password);
            emailTextView.setText(email);
            phoneTextView.setText(phone);
        }
    }
    //check the internet of user
    public boolean isOnline() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        else{
            return false;
        }
    }
    public void map(View v){
        Intent intent=new Intent(Profile.this,MapsActivity.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
    public void Cart(View view) {
        Intent intent=new Intent(Profile.this,Cart.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
    public void announcements(View view) {
        Intent intent=new Intent(Profile.this,Announcements.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
    public void addannouncements(View view) {
        Intent intent=new Intent(Profile.this,AddAnnouncements.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
    public void Home(View view) {
        Intent intent=new Intent(Profile.this,Home.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
}