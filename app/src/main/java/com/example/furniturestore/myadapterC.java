//package com.example.furniturestore;
//
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.room.Room;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.List;
//
//public class myadapterC  extends RecyclerView.Adapter<myadapterC.myviewholder>
//{
//    List<ProductC> products;
//    TextView rateview;
//    public myadapterC(List<ProductC> products, TextView rateview) {
//        this.products = products;
//        this.rateview= rateview;
//    }
//
//    @NonNull
//    @NotNull
//    @Override
//    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
//        return new myviewholder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull @NotNull myadapterC.myviewholder holder, int position) {
//        holder.recid.setText(String.valueOf(products.get(position).getPid()));
//        holder.recpname.setText(products.get(position).getPname());
//        holder.recpprice.setText(String.valueOf(products.get(position).getPrice()));
//        holder.recqnt.setText(String.valueOf(products.get(position).getQnt()));
//
//        holder.delbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AppDatabase db = Room.databaseBuilder(holder.recid.getContext(),
//                        AppDatabase.class, "cart_db").allowMainThreadQueries().build();
//                ProductDao productDao = db.ProductDao();
//
//                productDao.deleteById(products.get(position).getPid());
//                products.remove(position);
//                notifyItemRemoved(position);
//                updateprice();
//            }
//        });
//
//        holder.incr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int qnt=products.get(position).getQnt();
//                qnt++;
//                products.get(position).setQnt(qnt);
//                notifyDataSetChanged();
//                updateprice();
//            }
//        });
//
//        holder.decr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int qnt=products.get(position).getQnt();
//                qnt--;
//                products.get(position).setQnt(qnt);
//                notifyDataSetChanged();
//                updateprice();
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return products.size();
//    }
//
//    class myviewholder extends RecyclerView.ViewHolder
//    {
//        TextView recid,recpname,recqnt, recpprice;
//        ImageButton delbtn;
//        ImageView incr,decr;
//
//        public myviewholder(@NonNull @NotNull View itemView) {
//            super(itemView);
//
//            recid=itemView.findViewById(R.id.recid);
//            recpname=itemView.findViewById(R.id.recpname);
//            recpprice=itemView.findViewById(R.id.recpprice);
//            recqnt=itemView.findViewById(R.id.recqnt);
//            delbtn=itemView.findViewById(R.id.delbtn);
//
//            incr=itemView.findViewById(R.id.incbtn);
//            decr=itemView.findViewById(R.id.decbtn);
//        }
//    }
//
//    public void updateprice()
//    {
//        int sum=0,i;
//        for(i=0;i< products.size();i++)
//            sum=sum+(products.get(i).getPrice()*products.get(i).getQnt());
//
//        rateview.setText("Total Amount : INR "+sum);
//    }
//
//}
