package com.example.furniturestore;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Profile2 extends Fragment {
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
    public Profile2() {
        // Required empty public constructor
    }

    public static Profile2 newInstance(String param1, String param2) {
        Profile2 fragment = new Profile2();
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
        View view= inflater.inflate(R.layout.fragment_profile2, container, false);
        String em= getActivity().getIntent().getStringExtra("email");
        final TextView usernameTextView = (TextView) view.findViewById(R.id.username);
        final TextView passwordTextView = (TextView) view.findViewById(R.id.password);
        final TextView emailTextView = (TextView) view.findViewById(R.id.email);
        final TextView phoneTextView = (TextView) view.findViewById(R.id.phone);
        logout=view.findViewById(R.id.Sigout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(),Login.class));
            }
        });
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
                    Toast.makeText(getContext(), "Something worng happend!", Toast.LENGTH_LONG).show();
                }
            });
    return view;
    }
}