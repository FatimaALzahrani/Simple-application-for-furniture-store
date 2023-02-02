//package com.example.furniturestore;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.SearchView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.auth.FirebaseUser;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Cart4 extends AppCompatActivity {
//    RecyclerView listView;
//    myadapterC adapter;
//    private SearchView searchView;
//    ArrayList<Detail> List;
//    List<Product> productList = new ArrayList<>();
//    List<Detail> productListD = new ArrayList<>();
//    private FirebaseUser user;
//    DB_SQLlite DB;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_announcements);
//        listView = (RecyclerView) findViewById(R.id.productlist);
//        DB_SQLlite DB = new DB_SQLlite(Cart4.this);
////        ListView listView = (ListView) findViewById(R.id.productlist);
//         List = DB.getAllCart();
//        if (List.size() == 0) {
//            ImageView img = (ImageView) findViewById(R.id.imgf);
////            int imgid = this.getResources().getIdentifier("emptycart", "drawable", this.getPackageName());
////            img.setImageResource(imgid);
//        } else {
//            listView.setLayoutManager(new LinearLayoutManager(this));
//            adapter = new myadapterC(Cart4.this, List, new myadapterC.ItemClickListener() {
//                @Override
//                public void onItemClick(Detail p) {
////                Detail d=new Detail(p.name_of_announcement,p.price_of_announcement,p.type_of_announcement,p.color_of_announcement,p.composition_of_announcement,p.durability_of_announcement,p.url_of_the_announcement_dimensions,p.url_of_the_announcement_image);
//                    Intent intent=new Intent(Cart4.this,DetailAnnoucement.class);
//                    intent.putExtra("name",p.name_of_announcement);
//                    intent.putExtra("price",p.price_of_announcement);
//                    intent.putExtra("type",p.type_of_announcement);
//                    intent.putExtra("color",p.color_of_announcement);
//                    intent.putExtra("composition",p.composition_of_announcement);
//                    intent.putExtra("durability",p.durability_of_announcement);
//                    intent.putExtra("dimensions",p.url_of_the_announcement_dimensions);
//                    intent.putExtra("image",p.url_of_the_announcement_image);
//                    startActivity(intent);
//                }
//            });
//            listView.setAdapter(adapter);
//        }
//        adapter.setOnItemClicked(new myadapterC.OnItemClickListener() {
//            @Override
//            public void onItemClicked(int position) {
//                DB.delete(List.get(position).name_of_announcement);
////                setContentView(R.layout.activity_cart);
//                listView.setAdapter(adapter);
//            }
//        });
//
//        adapter.setOnItemClicked2(new myadapterC.OnItemClickListener2() {
//            @Override
//            public void onItemClicked2(int position) {
//                Detail product = new Detail();
//                int newv=Integer.parseInt((List.get(position).getKm()))-1;
//                String Name= List.get(position).name_of_announcement;
//                String Image=List.get(position).url_of_the_announcement_image;
//                String Price=List.get(position).price_of_announcement;
//                String Type=List.get(position).type_of_announcement;
//                String Color=List.get(position).color_of_announcement;
//                String Composition=List.get(position).composition_of_announcement;
//                String Durability=List.get(position).durability_of_announcement;
//                String ImageD=List.get(position).url_of_the_announcement_dimensions;
//                String Email=List.get(position).Email;
//                String num=List.get(position).number;
//                String km=""+newv;
//                DB.update( Name, Image, Price, Type, Color, Composition, Durability, ImageD, Email, num, km);
//                setContentView(R.layout.activity_cart);
//                listView.setAdapter(adapter);
//            }
//        });
//
//        adapter.setOnItemClicked3(new myadapterC.OnItemClickListener3() {
//            @Override
//            public void onItemClicked3(int position) {
//                Detail product = new Detail();
//                int newv=Integer.parseInt((List.get(position).getKm()))+1;
//                String Name= List.get(position).name_of_announcement;
//                String Image=List.get(position).url_of_the_announcement_image;
//                String Price=List.get(position).price_of_announcement;
//                String Type=List.get(position).type_of_announcement;
//                String Color=List.get(position).color_of_announcement;
//                String Composition=List.get(position).composition_of_announcement;
//                String Durability=List.get(position).durability_of_announcement;
//                String ImageD=List.get(position).url_of_the_announcement_dimensions;
//                String Email=List.get(position).Email;
//                String num=List.get(position).number;
//                String km=""+newv;
//                DB.update( Name, Image, Price, Type, Color, Composition, Durability, ImageD, Email, num, km);
////                setContentView(R.layout.activity_cart);
//                listView.setAdapter(adapter);
//            }
//        });
//    }
//
//    public void Fav(View view) {
//        Intent intent = new Intent(Cart4.this, Favorite.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
//    }
//
//    public void Profile(View view) {
//        Intent intent = new Intent(Cart4.this, Profile.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
//    }
//
//    public void addannouncements(View view) {
//        Intent intent = new Intent(Cart4.this, AddAnnouncements.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
//    }
//
//    public void Home(View view) {
//        Intent intent = new Intent(Cart4.this, Home.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
//    }
//    public void announcements(View view){
//        Intent intent = new Intent(Cart4.this, Announcements.class);
//    }
//
//}
////----------------------------------
////            database= FirebaseDatabase.getInstance().getReference().child("Announcements");```````````````````````
////        ArrayList<Product> list;
////        DatabaseReference databaseReference;
////        ProgressDialog progressDialog;
////        RecyclerView.Adapter adapter ;
////        List<Product> products;
////        ListView listViewProduct;
////            list=new ArrayList<>();
////            myAdapter=new MyAddapter(this,list);
////            recyclerView.setAdapter(myAdapter);
////            database.addValueEventListener(new ValueEventListener() {
////                @Override
////                public void onDataChange(@NonNull DataSnapshot snapshot) {
////                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
////                        Product product=dataSnapshot.getValue(Product.class);
////                        list.add(product);
////                    }
////                    myAdapter.notifyDataSetChanged();
////                }
////
////                @Override
////                public void onCancelled(@NonNull DatabaseError error) {
////
////                }
////            });
//
////_______________________________________________________________________
////            progressDialog = new ProgressDialog(Announcements.this);
////            progressDialog.setMessage("Loading Data from Firebase Database");
////            progressDialog.show();
////            databaseReference = FirebaseDatabase.getInstance().getReference().child("Announcements");
////            databaseReference.addValueEventListener(new ValueEventListener() {
////                @Override
////                public void onDataChange(DataSnapshot snapshot) {
////                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
////                        Product product = dataSnapshot.getValue(Product.class);
////                        list.add(product);
////                    }
////                    adapter = new RecyclerViewAdapter(Announcements.this, list);
////                    recyclerView.setAdapter(adapter);
////                    progressDialog.dismiss();
////                }
////                @Override
////                public void onCancelled(DatabaseError databaseError) {
////                    progressDialog.dismiss();
////                }
////            });
////        products = new ArrayList<>();
////        databaseReference =FirebaseDatabase.getInstance().getReference("Announcements");
//
////    @Override
////    protected void onStart() {
////        super.onStart();
////        databaseReference.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                products.clear();
////                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
////                    Product product = postSnapshot.getValue(Product.class);
////                    products.add(product);
////                }
////                ProductList productAdapter = new ProductList(Announcements.this, products);
////                listViewProduct.setAdapter(productAdapter);
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////
////            }
////        });
////    }
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_announcements);
////
////        recyclerView = findViewById(R.id.productlist);
////        database = FirebaseDatabase.getInstance().getReference("Announcement");
////        recyclerView.setHasFixedSize(true);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////
////        list = new ArrayList<>();
////        myAdapter = new MyAddapter(this,list);
////        recyclerView.setAdapter(myAdapter);
////
////        database.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot snapshot) {
////
////                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
////
////                    Product product = dataSnapshot.getValue(Product.class);
////                    list.add(product);
////
////
////                }
////                myAdapter.notifyDataSetChanged();
////
////            }
////
////            @Override
////            public void onCancelled(DatabaseError error) {
////
////            }
////        });}
