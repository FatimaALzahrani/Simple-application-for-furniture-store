//package com.example.furniturestore;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.snackbar.Snackbar;
//
//import java.util.ArrayList;
//
//public class Cart6 extends AppCompatActivity {
//    private RecyclerView courseRV;
//    private ArrayList<RecyclerData> recyclerDataArrayList;
//    private RecyclerViewAdapter recyclerViewAdapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cart);
//        courseRV = findViewById(R.id.idRVCourse);
//        recyclerDataArrayList = new ArrayList<>();
//        DB_SQLlite DB =new DB_SQLlite(Cart6.this);
//        RecyclerView listView = (RecyclerView)findViewById(R.id.productlist);
//        ArrayList<Detail> recyclerDataArrayList = DB.getAllCart();
//        if (recyclerDataArrayList.size()==0) {
//            ImageView imgf = (ImageView) findViewById(R.id.imgf);
//            int imgid = this.getResources().getIdentifier("emptycartt", "drawable", this.getPackageName());
//            imgf.setImageResource(imgid);
//        } else {
//        }
//        recyclerViewAdapter = new RecyclerViewAdapter(recyclerDataArrayList, this ,new RecyclerViewAdapter.ItemClickListener() {
//            @Override
//            public void onItemClick(Detail p) {
////                Detail d=new Detail(p.name_of_announcement,p.price_of_announcement,p.type_of_announcement,p.color_of_announcement,p.composition_of_announcement,p.durability_of_announcement,p.url_of_the_announcement_dimensions,p.url_of_the_announcement_image);
//                Intent intent=new Intent(Cart6.this,DetailAnnoucement.class);
//                intent.putExtra("name",p.name_of_announcement);
//                intent.putExtra("price",p.price_of_announcement);
//                intent.putExtra("type",p.type_of_announcement);
//                intent.putExtra("color",p.color_of_announcement);
//                intent.putExtra("composition",p.composition_of_announcement);
//                intent.putExtra("durability",p.durability_of_announcement);
//                intent.putExtra("dimensions",p.url_of_the_announcement_dimensions);
//                intent.putExtra("image",p.url_of_the_announcement_image);
//                startActivity(intent);
//            }
//        });
//
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        courseRV.setLayoutManager(manager);
//        courseRV.setAdapter(recyclerViewAdapter);
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                Detail deletedCourse = recyclerDataArrayList.get(viewHolder.getAdapterPosition());
//                int position = viewHolder.getAdapterPosition();
//                recyclerDataArrayList.remove(viewHolder.getAdapterPosition());
//                recyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
//                DB.delete(deletedCourse.getName_of_announcement());
//                Snackbar.make(courseRV, deletedCourse.getName_of_announcement(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        recyclerDataArrayList.add(position, deletedCourse);
//                        recyclerViewAdapter.notifyItemInserted(position);
//                    }
//                }).show();
//            }
//        }).attachToRecyclerView(courseRV);
//        if (recyclerDataArrayList.size()==0) {
//            ImageView imgf = (ImageView) findViewById(R.id.imgf);
//            int imgid = this.getResources().getIdentifier("emptycartt", "drawable", this.getPackageName());
//            imgf.setImageResource(imgid);
//        }
//        recyclerViewAdapter.setOnItemClicked(new RecyclerViewAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClicked(int position) {
//                DB.delete(recyclerDataArrayList.get(position).getName_of_announcement());
//                Toast.makeText(Cart6.this,recyclerDataArrayList.get(position).getName_of_announcement()+"Deleted from cart",Toast.LENGTH_LONG).show();
//            }
//        });
//        recyclerViewAdapter.setOnItemClicked2(new RecyclerViewAdapter.OnItemClickListener2() {
//            @Override
//            public void onItemClicked2(int position) {
//                TextView tv=(TextView)findViewById(R.id.num);
//                tv.setText(Integer.parseInt(tv.getText().toString())+1);
//            }
//        });
//        recyclerViewAdapter.setOnItemClicked3(new RecyclerViewAdapter.OnItemClickListener3() {
//            @Override
//            public void onItemClicked3(int position) {
//                TextView tv=(TextView)findViewById(R.id.num);
//                tv.setText(Integer.parseInt(tv.getText().toString())-1);
//            }
//        });
//    }
//
//
//    public void Fav(View view) {
//        Intent intent = new Intent(Cart6.this, Favorite2.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
//    }
//
//    public void Profile(View view) {
//        Intent intent = new Intent(Cart6.this, Profile.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
//    }
//
//    public void addannouncements(View view) {
//        Intent intent = new Intent(Cart6.this, AddAnnouncements.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
//    }
//
//    public void Home(View view) {
//        Intent intent = new Intent(Cart6.this, Home.class);
//        intent.putExtra("email", getIntent().getStringExtra("email"));
//        startActivity(intent);
//    }
//    public void announcements(View view){
//        Intent intent = new Intent(Cart6.this, Announcements.class);
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
