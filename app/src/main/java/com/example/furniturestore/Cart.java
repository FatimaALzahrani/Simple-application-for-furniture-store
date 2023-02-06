package com.example.furniturestore;

import android.content.Intent;
import android.os.Bundle;
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

public class Cart extends AppCompatActivity {
    myAdapterCart adapter;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        summ=(TextView) findViewById(R.id.smmm);
        recqnt=(TextView)findViewById(R.id.recqnt);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
//        int color=R.color.bar;
//        ColorDrawable colorDrawable
//                = new ColorDrawable(getResources().getColor(color));
//        actionBar.setBackgroundDrawable(colorDrawable);
//        actionBar.setTitle(" ");
        actionBar.hide();
//        Toast.makeText(this,ref.child("Cart").orderByChild("email").equalTo(user.getEmail()).,Toast.LENGTH_LONG).show();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.productlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Cart.this));
        ref.child("Cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productList.clear();
                sum=0;
                int co=0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map <Detail,Integer> mp;
                    Detail product = snapshot.getValue(Detail.class);
                    if(product.getEmail().equals(user.getEmail())) {
                        co++;
                        sum+=Integer.parseInt(product.getPrice_of_announcement().substring(0,product.getPrice_of_announcement().length()-4));//*product.getNumber();//.substring(0,product.getPrice_of_announcement().length()-4));
                        product.setPrice_of_announcement(product.getPrice_of_announcement());//*product.getNumber())+" R.S");
                        productList.add(product);
//                            if(np.getValue()==0){
//                                productList.remove(product);
//                            }
                    }
                    adapter.notifyDataSetChanged();
                }
                count=""+co;
                Collections.reverse(productList);
                //Query query = ref.child("Cart").orderByChild("email").equalTo(user.getEmail());
                summ.setText("Total : "+sum+" R.S");
                if(!dataSnapshot.exists() || sum==0){
                    ImageView imgf=(ImageView) findViewById(R.id.imgf);
                    Button b=(Button) findViewById(R.id.btn);
                    b.setText("");
//                    b.setEnabled(false);
                    b.setVisibility(View.INVISIBLE);
                    TextView tv=(TextView) findViewById(R.id.smmm);
                    int imgid=getBaseContext().getResources().getIdentifier("emptycartt","drawable",getBaseContext().getPackageName());
                    imgf.setImageResource(imgid);
                    tv.setText(R.string.ops);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //        Query query = ref.child("Cart").orderByChild("email").equalTo(user.getEmail());
//                    ImageView imgf=(ImageView) findViewById(R.id.imgf);
//                    int imgid=getBaseContext().getResources().getIdentifier("emptycart","drawable",getBaseContext().getPackageName());
//                    imgf.setImageResource(imgid);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new myAdapterCart(Cart.this, productList, new myAdapterCart.ItemClickListener() {
            @Override
            public void onItemClick(Detail p) {
//                Detail d=new Detail(p.name_of_announcement,p.price_of_announcement,p.type_of_announcement,p.color_of_announcement,p.composition_of_announcement,p.durability_of_announcement,p.url_of_the_announcement_dimensions,p.url_of_the_announcement_image);
                Intent intent=new Intent(Cart.this,DetailAnnoucement.class);
                intent.putExtra("name",p.name_of_announcement);
                intent.putExtra("price",p.price_of_announcement);
                intent.putExtra("type",p.type_of_announcement);
                intent.putExtra("color",p.color_of_announcement);
                intent.putExtra("composition",p.composition_of_announcement);
                intent.putExtra("durability",p.durability_of_announcement);
                intent.putExtra("dimensions",p.url_of_the_announcement_dimensions);
                intent.putExtra("image",p.url_of_the_announcement_image);
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

    }
//        private boolean deleteArtist() {
//            DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Cart").child(p.name_of_announcement);
//            dR.removeValue();
//            Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
//            return true;
//        }

    public void announcements(View view) {
        Intent intent = new Intent(Cart.this, Announcements.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
    public void Fav(View view) {
        Intent intent = new Intent(Cart.this, Favorite2.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void Profile(View view) {
        Intent intent = new Intent(Cart.this, Profile.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void addannouncements(View view) {
        Intent intent = new Intent(Cart.this, AddAnnouncements.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void Home(View view) {
        Intent intent = new Intent(Cart.this, Home.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);

    }    }
//
//    public void increment(View view) {
//        recqnt.setText(Integer.parseInt(recqnt.getText().toString())+1);
//        Detail d=new Detail();
//        d.setNumber(Integer.parseInt(recqnt.getText().toString())+1);
//    }
//
//    public void decrement(View view) {
//        recqnt.setText(Integer.parseInt(recqnt.getText().toString())-1);
////        Detail d=new Detail();
//        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//        Map<String, Object> map = new HashMap<>();
//        adapter.setOnItemClicked(new myAdapterCart.OnItemClickListener() {
//            @Override
//            public void onItemClicked(int position) {
//                Query queryRef = ref.child("Cart").orderByChild("name_of_announcement").equalTo(productList.get(position).name_of_announcement);
//                queryRef.addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(DataSnapshot snapshot, String previousChild) {
//                        Detail d=snapshot.getValue(Detail.class);
//                        d.setNumber(Integer.parseInt(recqnt.getText().toString())+1);
//                        snapshot.getRef().setValue(d);
//                    }
//
//                    @Override
//                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//                adapter.notifyItemRemoved(position);
//            }
//        });
//    }
//}
//
////    RecyclerView recview;
////    myAdapterCart adapter;
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_cart);
////        recview = (RecyclerView) findViewById(R.id.productlist);
////        recview.setLayoutManager(new LinearLayoutManager(this));
////        FirebaseRecyclerOptions<Detail> options = new FirebaseRecyclerOptions.Builder<Detail>()
////                .setQuery(FirebaseDatabase.getInstance().getReference().child("Cart"), Detail.class).build();
////        adapter=new myAdapterCart(options, new myAdapterCart.ItemClickListener() {
////            @Override
////            public void onItemClick(Detail p) {
//////                Detail d=new Detail(p.name_of_announcement,p.price_of_announcement,p.type_of_announcement,p.color_of_announcement,p.composition_of_announcement,p.durability_of_announcement,p.url_of_the_announcement_dimensions,p.url_of_the_announcement_image);
////                Intent intent=new Intent(Cart.this,DetailAnnoucement.class);
////                intent.putExtra("name",p.name_of_announcement);
////                intent.putExtra("price",p.price_of_announcement);
////                intent.putExtra("type",p.type_of_announcement);
////                intent.putExtra("color",p.color_of_announcement);
////                intent.putExtra("composition",p.composition_of_announcement);
////                intent.putExtra("durability",p.durability_of_announcement);
////                intent.putExtra("dimensions",p.url_of_the_announcement_dimensions);
////                intent.putExtra("image",p.url_of_the_announcement_image);
////                startActivity(intent);
////            }
////        });
////        recview.setAdapter(adapter);
////    }
////    private void show(){
////
////    }
////    @Override
////    protected void onStart() {
////        super.onStart();
////        adapter.startListening();
////    }
////
////    @Override
////    protected void onStop() {
////        super.onStop();
////        adapter.stopListening();
////    }
////    public void Home(View view) {
////        Intent intent=new Intent(Cart.this,Home.class);
////        startActivity(intent);
////    }
////    public void Profile(View view) {
////        Intent intent=new Intent(Cart.this,Profile.class);
////        startActivity(intent);
////    }
////    public void addannouncements(View view) {
////        Intent intent=new Intent(Cart.this,AddAnnouncements.class);
////        startActivity(intent);
////    }
////    public void announcements(View view) {
////        Intent intent=new Intent(Cart.this,Announcements.class);
////        startActivity(intent);
////    }
////}