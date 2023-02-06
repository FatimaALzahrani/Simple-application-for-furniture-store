package com.example.furniturestore;

import android.content.Intent;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cart22#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cart22 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    myAdapterCart adapter;
    MeowBottomNavigation bottomNavigation;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    DatabaseReference ref;
    List<Detail> productList = new ArrayList<>();
    List<Product> productList2 = new ArrayList<>();
    int sum=0;
    private TextView summ,recqnt;
    private NumberPicker np;
    public static String count ="0";
    private Handler handler;

    public Cart22() {
        // Required empty public constructor
    }

    public static Cart22 newInstance(String param1, String param2) {
        Cart22 fragment = new Cart22();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart22, container, false);
        summ = (TextView) view.findViewById(R.id.smmm);
        recqnt = (TextView) view.findViewById(R.id.recqnt);
        ActionBar actionBar;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.productlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ref.child("Cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productList.clear();
                sum = 0;
                int co = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<Detail, Integer> mp;
                    Detail product = snapshot.getValue(Detail.class);
                    if (product.getEmail().equals(user.getEmail())) {
                        co++;
                        sum += Integer.parseInt(product.getPrice_of_announcement().substring(0, product.getPrice_of_announcement().length() - 4));//*product.getNumber();//.substring(0,product.getPrice_of_announcement().length()-4));
                        product.setPrice_of_announcement(product.getPrice_of_announcement());//*product.getNumber())+" R.S");
                        productList.add(product);
//                            if(np.getValue()==0){
//                                productList.remove(product);
//                            }
                    }
                    adapter.notifyDataSetChanged();
                }
                count = "" + co;
                Collections.reverse(productList);
                //Query query = ref.child("Cart").orderByChild("email").equalTo(user.getEmail());
                summ.setText("Total : " + sum + " R.S");
                if (!dataSnapshot.exists() || sum == 0) {
                    ImageView imgf = (ImageView) view.findViewById(R.id.imgf);
                    Button b = (Button) view.findViewById(R.id.btn);
                    b.setText("");
//                    b.setEnabled(false);
                    b.setVisibility(View.INVISIBLE);
                    TextView tv = (TextView) view.findViewById(R.id.smmm);
                    int imgid = getContext().getResources().getIdentifier("emptycartt", "drawable", getContext().getPackageName());
                    imgf.setImageResource(imgid);
                    tv.setText(R.string.ops);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new myAdapterCart(getContext(), productList, new myAdapterCart.ItemClickListener() {
            @Override
            public void onItemClick(Detail p) {
                Intent intent = new Intent(getContext(), DetailAnnoucement.class);
                intent.putExtra("name", p.name_of_announcement);
                intent.putExtra("price", p.price_of_announcement);
                intent.putExtra("type", p.type_of_announcement);
                intent.putExtra("color", p.color_of_announcement);
                intent.putExtra("composition", p.composition_of_announcement);
                intent.putExtra("durability", p.durability_of_announcement);
                intent.putExtra("dimensions", p.url_of_the_announcement_dimensions);
                intent.putExtra("image", p.url_of_the_announcement_image);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClicked(new myAdapterCart.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Query queryRef = ref.child("Cart").orderByChild("name_of_announcement").equalTo(productList.get(position).name_of_announcement);
                queryRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                        snapshot.getRef().setValue(null);
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                productList.remove(position);
                adapter.notifyItemRemoved(position);
                recyclerView.setAdapter(adapter);
            }
        });
        return view;
    }
    public void Favorite(View view) {
        Intent intent = new Intent(getContext(), Favorite2.class);
        startActivity(intent);
    }
}