package com.example.furniturestore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import org.checkerframework.checker.units.qual.C;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Announcements2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Announcements2 extends Fragment {
    RecyclerView recview;
    ProductAdapter adapter;
    private SearchView searchView;
    MeowBottomNavigation bottomNavigation;
    DatabaseReference ref;
    List<Product> productList = new ArrayList<>();
    List<Detail> productListD = new ArrayList<>();
    private DatabaseReference reference;
    private String userID;
    DatabaseReference databaseReference;
    static int PERMISSION_CODE= 100;
    private FirebaseDatabase firebaseDatabase;
    public Announcements2() {
        // Required empty public constructor
    }
    public static Announcements2 newInstance(String param1, String param2) {
        Announcements2 fragment = new Announcements2();
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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_announcements2, container, false);
        searchView=(SearchView) view.findViewById(R.id.searchview);
        searchView.clearFocus();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        ref.child("Announcements").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Product product = snapshot.getValue(Product.class);
                    Detail product2 = snapshot.getValue(Detail.class);
                    product.setPrice_of_announcement(product.getPrice_of_announcement()+" R.S");
                    productList.add(product);
                    productListD.add(product2);
                    adapter.notifyDataSetChanged();
                }
                Collections.reverse(productList);
                Collections.reverse(productListD);
            }
            @Override
            public void onCancelled(DatabaseError databaseError){

            }
        });
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.productlist);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter = new ProductAdapter(getContext(), productList, new ProductAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Product p){
                Fragment fragment = new DetailAnnoucement2();
                Bundle bundle = new Bundle();
                bundle.putString("name",p.name_of_announcement);
                bundle.putString("price",p.price_of_announcement);
                bundle.putString("type",p.type_of_announcement);
                bundle.putString("color",p.color_of_announcement);
                bundle.putString("composition",p.composition_of_announcement);
                bundle.putString("durability",p.durability_of_announcement);
                bundle.putString("dimensions",p.url_of_the_announcement_dimensions);
                bundle.putString("image",p.url_of_the_announcement_image);
                fragment.setArguments(bundle);
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frag,fragment);
                fragmentTransaction.commit();
            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Cart");
        userID=user.getUid();
        ref = database.getReference();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClicked(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Detail product=new Detail();
                Map<Detail,Integer> FMA_Map2=new HashMap<Detail, Integer>();;
                Query query = reference
                        .child("Cart")
                        .orderByChild("name_of_announcement")
                        .equalTo(productList.get(position).name_of_announcement);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (FMA_Map2.containsKey(product)) {
                            for(Map.Entry s:FMA_Map2.entrySet()){
                                if(s.getKey()==product){
                                    Query queryRef = ref.child("Cart").orderByChild("name_of_announcement").equalTo(product.name_of_announcement);
                                    queryRef.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(DataSnapshot snapshot, String previousChild){
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
                                    Integer count2=((Number)s.getValue()).intValue()+1;
                                    product.setName_of_announcement(productList.get(position).name_of_announcement);
                                    product.setUrl_of_the_announcement_image(productList.get(position).url_of_the_announcement_image);
                                    product.setUrl_of_the_announcement_dimensions(productList.get(position).url_of_the_announcement_dimensions);
                                    product.setPrice_of_announcement(productList.get(position).price_of_announcement);
                                    product.setType_of_announcement(productList.get(position).type_of_announcement);
                                    product.setComposition_of_announcement(productList.get(position).composition_of_announcement);
                                    product.setDurability_of_announcement(productList.get(position).durability_of_announcement);
                                    product.setColor_of_announcement(productList.get(position).color_of_announcement);
                                    product.setEmail(user.getEmail());
                                    FMA_Map2.put((Detail)(s.getKey()),count2);
                                    product.setNumber(user.getPhoneNumber());
                                }
                            }
                        } else {
                            product.setName_of_announcement(productList.get(position).name_of_announcement);
                            product.setUrl_of_the_announcement_image(productList.get(position).url_of_the_announcement_image);
                            product.setUrl_of_the_announcement_dimensions(productList.get(position).url_of_the_announcement_dimensions);
                            product.setPrice_of_announcement(productList.get(position).price_of_announcement);
                            product.setType_of_announcement(productList.get(position).type_of_announcement);
                            product.setComposition_of_announcement(productList.get(position).composition_of_announcement);
                            product.setDurability_of_announcement(productList.get(position).durability_of_announcement);
                            product.setColor_of_announcement(productList.get(position).color_of_announcement);
                            product.setEmail(user.getEmail());
                            product.setNumber(user.getPhoneNumber());
                            FMA_Map2.put(product,1);
                            firebaseDatabase = FirebaseDatabase.getInstance();
                            String id = firebaseDatabase.getReference("Cart").push().getKey();
                            firebaseDatabase.getReference("Cart").child(id).setValue(product);
                        }
                        Toast.makeText(getContext(),"Announcements Added to Cart",Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
//                productList.remove(position);
//                adapter.notifyItemRemoved(position);
//                recyclerView.setAdapter(adapter);
            }
        });
        adapter.setOnItemClicked2(new ProductAdapter.OnItemClickListener2() {
            @Override
            public void onItemClicked2(int position) {
                Detail product=new Detail();
                product.setName_of_announcement(productList.get(position).name_of_announcement);
                product.setUrl_of_the_announcement_image(productList.get(position).url_of_the_announcement_image);
                product.setUrl_of_the_announcement_dimensions(productList.get(position).url_of_the_announcement_dimensions);
                product.setPrice_of_announcement(productList.get(position).price_of_announcement);
                product.setType_of_announcement(productList.get(position).type_of_announcement);
                product.setComposition_of_announcement(productList.get(position).composition_of_announcement);
                product.setDurability_of_announcement(productList.get(position).durability_of_announcement);
                product.setColor_of_announcement(productList.get(position).color_of_announcement);
                product.setEmail(user.getEmail());
//                firebaseDatabase = FirebaseDatabase.getInstance();
//                String id =firebaseDatabase.getReference("Favorite").push().getKey();
//                firebaseDatabase.getReference("Favorite").child(id).setValue(product);
                String Name=productList.get(position).name_of_announcement;
                String Price=productList.get(position).price_of_announcement;
                String composition=productList.get(position).composition_of_announcement;
                String durability=productList.get(position).durability_of_announcement;
                String dimensions=productList.get(position).durability_of_announcement;
                String image=productList.get(position).url_of_the_announcement_image;
                String type=productList.get(position).type_of_announcement;
                String color=productList.get(position).color_of_announcement;
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DB_SQLlite DB=new DB_SQLlite(getContext());
                DB.insertCart(Name,image,Price,type,color,composition,durability,dimensions,user.getEmail(),user.getPhoneNumber());
                Toast.makeText(getContext(),"Announcements Added to Favorite",Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemClicked3(new ProductAdapter.OnItemClickListener3() {
            @Override
            public void onItemClicked3(int position) {
                String phoneno=productListD.get(position).getNumber();
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);
                }
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+phoneno));
                startActivity(i);
            }
        });
        SearchView searchView = (SearchView) view.findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Query fireQuery = ref.child("Announcements");
                fireQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) {
                            Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                        } else {
                            List<Product> searchList = new ArrayList<Product>();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Product product = snapshot.getValue(Product.class);
                                if(product.getName_of_announcement().toLowerCase().contains(query.toLowerCase()) || product.getType_of_announcement().toLowerCase().contains(query.toLowerCase())
                                        ||product.getColor_of_announcement().toLowerCase().contains(query.toLowerCase()))
                                    searchList.add(product);
                                adapter = new ProductAdapter(getContext(), searchList, new ProductAdapter.ItemClickListener() {
                                    @Override
                                    public void onItemClick(Product p) {
                                        Fragment fragment = new DetailAnnoucement2();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("name",p.name_of_announcement);
                                        bundle.putString("price",p.price_of_announcement);
                                        bundle.putString("type",p.type_of_announcement);
                                        bundle.putString("color",p.color_of_announcement);
                                        bundle.putString("composition",p.composition_of_announcement);
                                        bundle.putString("durability",p.durability_of_announcement);
                                        bundle.putString("dimensions",p.url_of_the_announcement_dimensions);
                                        bundle.putString("image",p.url_of_the_announcement_image);
                                        fragment.setArguments(bundle);
                                        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                                        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                                        fragmentTransaction.replace(R.id.frag,fragment);
                                        fragmentTransaction.commit();
                                    }
                                });
                                recyclerView.setAdapter(adapter);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                adapter = new ProductAdapter(getContext(), productList,new ProductAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(Product p) {
//                Detail d=new Detail(p.name_of_announcement,p.price_of_announcement,p.type_of_announcement,p.color_of_announcement,p.composition_of_announcement,p.durability_of_announcement,p.url_of_the_announcement_dimensions,p.url_of_the_announcement_image);
                        Fragment fragment = new DetailAnnoucement2();
                        Bundle bundle = new Bundle();
                        bundle.putString("name",p.name_of_announcement);
                        bundle.putString("price",p.price_of_announcement);
                        bundle.putString("type",p.type_of_announcement);
                        bundle.putString("color",p.color_of_announcement);
                        bundle.putString("composition",p.composition_of_announcement);
                        bundle.putString("durability",p.durability_of_announcement);
                        bundle.putString("dimensions",p.url_of_the_announcement_dimensions);
                        bundle.putString("image",p.url_of_the_announcement_image);
                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frag,fragment);
                        fragmentTransaction.commit();
                    }
                });
                recyclerView.setAdapter(adapter);
                return false;
            }
        });
//        bottomNavigation=view.findViewById(R.id.navi);
//        bottomNavigation.setCount(4,Cart22.count);
        return view;
    }
}