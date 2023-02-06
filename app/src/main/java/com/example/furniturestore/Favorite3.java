package com.example.furniturestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class Favorite3 extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        summ = (TextView) findViewById(R.id.smmm);
        recqnt = (TextView) findViewById(R.id.recqnt);
        DB_SQLlite DB =new DB_SQLlite(Favorite3.this);
        ListView listView = (ListView)findViewById(R.id.productlist);
        ArrayList<Detail> List = DB.getAllCart();
        if (List.size()==0) {
            ImageView imgf = (ImageView) findViewById(R.id.imgf);
            int imgid = this.getResources().getIdentifier("emptyfav", "drawable", this.getPackageName());
            imgf.setImageResource(imgid);
        } else {
            DB_SQL_Adapter adapterr = new DB_SQL_Adapter(Favorite3.this, R.layout.fav, List);
            listView.setAdapter(adapterr);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String selectedItem=adapterView.getItemAtPosition(i).toString();
                Intent intent=new Intent(Favorite3.this,DetailAnnoucement.class);
                intent.putExtra("name",List.get(i).name_of_announcement);
                intent.putExtra("price",List.get(i).price_of_announcement);
                intent.putExtra("type",List.get(i).type_of_announcement);
                intent.putExtra("color",List.get(i).color_of_announcement);
                intent.putExtra("composition",List.get(i).composition_of_announcement);
                intent.putExtra("durability",List.get(i).durability_of_announcement);
                intent.putExtra("dimensions",List.get(i).url_of_the_announcement_dimensions);
                intent.putExtra("image",List.get(i).url_of_the_announcement_image);
                startActivity(intent);
            }
        });

//        adapter.setOnItemClicked(new myAdapterCart.OnItemClickListener() {
//            @Override
//            public void onItemClicked(int position) {
//                SQLiteDatabase db=DB_SQLlite.getReadableDatabase();
//                Cursor res=db.rawQuery("select * from Cart",null);
//                res.moveToFirst();//روح لاول صف
//                while (res.isAfterLast()==false){
//                    if(res.getString(0).equals(List.get(position).getName_of_announcement())){
////                        if(List.get(position).getNumber()<=1) {
//                            List.remove(position);
//                            String query = "DELETE FROM Cart WHERE Name = "+List.get(position).getName_of_announcement();
//                            db.execSQL(query);
//                            db.close();
//                            break;
////                        }
////                        else{
////                            List.get(position).setNumber(List.get(position).getNumber()-1);
////                        }
//                    }
//
//                    res.moveToNext();//يروح الصف الثاني
//                }
//            }
//        });
    }


    public void announcements(View view) {
        Intent intent = new Intent(Favorite3.this, Announcements.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }
    public void Fav(View view) {
        Intent intent = new Intent(Favorite3.this, Favorite3.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void Profile(View view) {
        Intent intent = new Intent(Favorite3.this, Profile.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void addannouncements(View view) {
        Intent intent = new Intent(Favorite3.this, AddAnnouncements.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

    public void Home(View view) {
        Intent intent = new Intent(Favorite3.this, Home.class);
        intent.putExtra("email", getIntent().getStringExtra("email"));
        startActivity(intent);
    }

}
//    myAdapterCart adapter;
//    private FirebaseUser user;
//    private DatabaseReference reference;
//    private String userID;
//    DatabaseReference ref;
//    List<Detail> productList = new ArrayList<>();
//    List<Product> productList2 = new ArrayList<>();
//    int sum=0;
//    private TextView summ;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_favorite);
////___________________________________________________________________________________
////        summ=(TextView) findViewById(R.id.smmm);
//////        Toast.makeText(this,ref.child("Cart").orderByChild("email").equalTo(user.getEmail()).,Toast.LENGTH_LONG).show();
////        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////        reference= FirebaseDatabase.getInstance().getReference("Users");
////        userID=user.getUid();
////        final FirebaseDatabase database = FirebaseDatabase.getInstance();
////        ref = database.getReference();
////        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.productlist);
////        recyclerView.setAdapter(adapter);
////        recyclerView.setLayoutManager(new LinearLayoutManager(Favorite.this));
////        ref.child("Favorite").addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                productList.clear();
////                sum=0;
////                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
////                    Detail product = snapshot.getValue(Detail.class);
////                        if(product.getEmail().equals(user.getEmail())) {
////                            product.setPrice_of_announcement(product.getPrice_of_announcement());
////                            productList.add(product);
////                        }
////                        adapter.notifyDataSetChanged();
////                }
////                Collections.reverse(productList);
////                //Query query = ref.child("Cart").orderByChild("email").equalTo(user.getEmail());
////                if(!dataSnapshot.exists()){
////                    ImageView imgf=(ImageView) findViewById(R.id.imgf);
////                    int imgid=getBaseContext().getResources().getIdentifier("emptyfav","drawable",getBaseContext().getPackageName());
////                    imgf.setImageResource(imgid);
////                }
////            }
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////                //        Query query = ref.child("Cart").orderByChild("email").equalTo(user.getEmail());
//////                    ImageView imgf=(ImageView) findViewById(R.id.imgf);
//////                    int imgid=getBaseContext().getResources().getIdentifier("emptycart","drawable",getBaseContext().getPackageName());
//////                    imgf.setImageResource(imgid);
////            }
////        });
////
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        adapter = new myAdapterCart(Favorite.this, productList, new myAdapterCart.ItemClickListener() {
////            @Override
////            public void onItemClick(Detail p) {
//////                Detail d=new Detail(p.name_of_announcement,p.price_of_announcement,p.type_of_announcement,p.color_of_announcement,p.composition_of_announcement,p.durability_of_announcement,p.url_of_the_announcement_dimensions,p.url_of_the_announcement_image);
////                Intent intent=new Intent(Favorite.this,DetailAnnoucement.class);
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
////        recyclerView.setAdapter(adapter);
////        adapter.setOnItemClicked(new myAdapterCart.OnItemClickListener() {
////            @Override
////            public void onItemClicked(int position) {
////                Query queryRef = ref.child("Favorite").orderByChild("name_of_announcement").equalTo(productList.get(position).name_of_announcement);
////                queryRef.addChildEventListener(new ChildEventListener() {
////                    @Override
////                    public void onChildAdded(DataSnapshot snapshot, String previousChild) {
////                        snapshot.getRef().setValue(null);
////                    }
////
////                    @Override
////                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
////
////                    }
////
////                    @Override
////                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
////
////                    }
////
////                    @Override
////                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
////
////                    }
////
////                    @Override
////                    public void onCancelled(@NonNull DatabaseError error) {
////
////                    }
////                });
////                productList.remove(position);
////                adapter.notifyItemRemoved(position);
////                recyclerView.setAdapter(adapter);
////            }
////        });
//    }
//
//    public void announcements(View view) {
//        Intent intent = new Intent(Favorite.this, Announcements.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
//    }
//
//    public void Profile(View view) {
//        Intent intent = new Intent(Favorite.this, Profile.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
//    }
//
//    public void addannouncements(View view) {
//        Intent intent = new Intent(Favorite.this, AddAnnouncements.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
//    }
//
//    public void Home(View view) {
//        Intent intent = new Intent(Favorite.this, Home.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
//    }
//    public void Cart(View view) {
//        Intent intent = new Intent(Favorite.this, Cart.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
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
//}