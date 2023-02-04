package com.example.furniturestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    private EditText password;
    private EditText name;
    private EditText email;
    private TextView register;
    Button btnlogin;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private DBHelper DB;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        register = (TextView) findViewById(R.id.register);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        progressBar = (ProgressBar) findViewById(R.id.prog);
        mAuth=FirebaseAuth.getInstance();
        // Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();
//        int color=R.color.bar;
//        ColorDrawable colorDrawable
//                = new ColorDrawable(getResources().getColor(color));
//        actionBar.setBackgroundDrawable(colorDrawable);
//        actionBar.setTitle(" ");
        actionBar.hide();
    }
    public void sing_in(View v){
        String em=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        String nam=name.getText().toString().trim();
        if(em.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if(em.length()<7 || !em.contains("@") || !em.contains(".")) {//!Patterns.EMAIL_ADDRESS.matcher(em).matches()
            email.setError("Please enter a vaild email!");
            email.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if(pass.length()<6){
            password.setError("Min password length is 6 characters!");
            password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        if(isOnline()==true) {
            mAuth.signInWithEmailAndPassword(em, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user.isEmailVerified()) {
                            Intent intent = new Intent(Login.this, Home.class);
                            String name2 = name.getText().toString();
                            intent.putExtra("name", name2);
                            intent.putExtra("email", em);
                            startActivity(intent);
                            DB = new DBHelper(Login.this);
                            btnlogin.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String user = email.getText().toString();
                                    String pass = password.getText().toString();

                                    if(user.equals("")||pass.equals(""))
                                        Toast.makeText(Login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                                    else{
                                        Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                                        if(checkuserpass==true){
                                            Toast.makeText(Login.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                                            Intent intent  = new Intent(getApplicationContext(), Home.class);
                                            intent.putExtra("email",em);
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                        } else {
                            user.sendEmailVerification();
                            Toast.makeText(Login.this, "Check your Email to verify your account!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Login.this, "Failed to login! pleasse check your credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            imageView = findViewById(R.id.img);
            Glide.with(this).load(R.drawable.load).into(imageView);
        }
    }
    public void Sing_up(View v){
        Intent intent = new Intent(Login.this, Sing_up.class);
        startActivity(intent);
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

}
