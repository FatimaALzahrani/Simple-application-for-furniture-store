package com.example.furniturestore;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Sing_up extends AppCompatActivity {
    EditText edtFullName, edtEmail, edtMobile, edtPassword, edtConfirmPassword;
    ProgressBar progressBar;
    Button btnSignUp;
    String txtFullName, txtEmail, txtMobile, txtPassword, txtConfirmPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private EditText email;
    private EditText password;
    TextView login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        edtFullName = findViewById(R.id.name);
        edtEmail = findViewById(R.id.email);
        edtMobile = findViewById(R.id.phone);
        edtPassword = findViewById(R.id.password);
        edtConfirmPassword = findViewById(R.id.password2);
        progressBar = findViewById(R.id.signUpProgressBar);
        btnSignUp = findViewById(R.id.btnSignUp);
        login= (TextView) findViewById(R.id.login);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
//        int color=R.color.bar;
//        ColorDrawable colorDrawable
//                = new ColorDrawable(getResources().getColor(color));
//        actionBar.setBackgroundDrawable(colorDrawable);
//        actionBar.setTitle(" ");
        actionBar.hide();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtFullName = edtFullName.getText().toString();
                txtEmail = edtEmail.getText().toString().trim();
                txtMobile = edtMobile.getText().toString().trim();
                txtPassword = edtPassword.getText().toString().trim();
                txtConfirmPassword = edtConfirmPassword.getText().toString().trim();

                if (!TextUtils.isEmpty(txtFullName)) {
                    if (!TextUtils.isEmpty(txtEmail)) {
                        if (txtEmail.matches(emailPattern)) {
                            if (!TextUtils.isEmpty(txtMobile)) {
                                if (txtMobile.length() == 10) {
                                    if (!TextUtils.isEmpty(txtPassword)) {
                                        if (!TextUtils.isEmpty(txtConfirmPassword)) {
                                            if (txtConfirmPassword.equals(txtPassword)) {
                                                SignUpUser();
                                            } else {
                                                edtConfirmPassword.setError("Confirm Password and Password should be same.");
                                            }
                                        } else {
                                            edtConfirmPassword.setError("Confirm Password Field can't be empty");
                                        }
                                    } else {
                                        edtPassword.setError("Password Field can't be empty");
                                    }
                                } else {
                                    edtMobile.setError("Enter a valid Mobile");
                                }
                            } else {
                                edtMobile.setError("Mobile Number Field can't be empty");
                            }
                        } else {
                            edtEmail.setError("Enter a valid Email Address");
                        }
                    } else {
                        edtEmail.setError("Email Field can't be empty");
                    }
                } else {
                    edtFullName.setError("Full Name Field can't be empty");
                }
            }
        });

    }

    private void SignUpUser() {
        progressBar.setVisibility(View.VISIBLE);
//        btnSignUp.setVisibility(View.INVISIBLE);
        if(isOnline()==true) {
            mAuth.createUserWithEmailAndPassword(txtEmail, txtPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        User user = new User(txtFullName, txtPassword, txtEmail, txtMobile);
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Sing_up.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                        } else {
                                            Toast.makeText(Sing_up.this, "Faild to register! Tray again!", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(Sing_up.this, "Faild to register! Tray again!", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });
        }else{
            DB = new DBHelper(this);
            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String user = edtEmail.getText().toString();
                    String pass = edtPassword.getText().toString();
                    String repass = edtConfirmPassword.getText().toString();
                    if(user.equals("")||pass.equals("")||repass.equals(""))
                        Toast.makeText(Sing_up.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    else{
                        if(pass.equals(repass)){
                            Boolean checkuser = DB.checkusername(user);
                            if(checkuser==false){
                                Boolean insert = DB.insertData(edtFullName.getText().toString(),pass,user,edtMobile.getText().toString());
                                if(insert==true){
                                    Toast.makeText(Sing_up.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                    DB_SQLlite DB=new DB_SQLlite(Sing_up.this);
                                    DB.insertUser(edtFullName.getText().toString(),user,pass,edtMobile.getText().toString());
                                    Intent intent = new Intent(getApplicationContext(),Home.class);
                                    intent.putExtra("email",user);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(Sing_up.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(Sing_up.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Sing_up.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                        }
                    } }
            });
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                }
            });
        }
    }

    //back to login
    public void login(View v){
        Intent intent=new Intent(Sing_up.this,Login.class);
        startActivity(intent);
    }
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
    public void Home(View view) {
            String em= email.getText().toString();
            String pass= password.getText().toString();
            TextView worngem=(TextView) findViewById(R.id.wrongemail);
            TextView worngpass=(TextView) findViewById(R.id.wrongpassowrd);
            String regexPattern = getString(R.string.pattren);
            int n=0;
            if(em.equals("")){
                worngem.setText(R.string.enter_email_please);
                email.setBackground(this.getResources().getDrawable(R.drawable.wrong_border));
                n++;
                email.setText("");
            }else if(!em.matches(regexPattern)){
                worngem.setText(R.string.enter_email_vaild);
                email.setBackground(this.getResources().getDrawable(R.drawable.wrong_border));
                n++;
                email.setText("");
            }else{
                email.setBackground(this.getResources().getDrawable(R.drawable.yes_border));
            }
            if(pass.equals("")){
                worngpass.setText(R.string.enter_password_please);
                password.setBackground(this.getResources().getDrawable(R.drawable.wrong_border));
                n++;
                password.setText("");
            }else if(!pass.contains("$") && !pass.contains("*")&&!pass.contains("@") && !pass.contains("#")&&!pass.contains("&")){
                worngpass.setText(R.string.symbols_password);
                password.setBackground(this.getResources().getDrawable(R.drawable.wrong_border));
                n++;
                password.setBackground(this.getResources().getDrawable(R.drawable.wrong_border));
                password.setText("");
            }else if(pass.length()<8){
                worngpass.setText(R.string.password_lenght);
                password.setBackground(this.getResources().getDrawable(R.drawable.wrong_border));
                n++;
                password.setText("");
            }else{
                password.setBackground(this.getResources().getDrawable(R.drawable.yes_border));
            }
            if(n==0) {
                Intent intent = new Intent(Sing_up.this, Home.class);
                startActivity(intent);
            }
        }
    }


//                                db.collection("users")
//                                        .add(user)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                Toast.makeText(Sing_up.this, "Data Stored Successfully !", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(Sing_up.this, Home.class);
//                                startActivity(intent);
//                                finish();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(Sing_up.this, "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(Sing_up.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                progressBar.setVisibility(View.INVISIBLE);
//                btnSignUp.setVisibility(View.VISIBLE);
//            }
//        });
