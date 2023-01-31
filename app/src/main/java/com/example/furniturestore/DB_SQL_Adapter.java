package com.example.furniturestore;
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Build;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.widget.VideoView;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.annotation.RequiresApi;
        import androidx.constraintlayout.helper.widget.Layer;
        import androidx.room.Room;

        import com.bumptech.glide.Glide;

        import java.util.ArrayList;

//coustom adapter class extends from arrayadapter class
public class DB_SQL_Adapter extends ArrayAdapter {
    Context AdapterContext;
    int AdapterResource;
    ArrayList<Detail> AdapterBook;
    public DB_SQL_Adapter(@NonNull Context context, int resource, ArrayList<Detail> book) {
        super(context, resource, book);
        AdapterContext=context;
        AdapterResource=resource;
        AdapterBook=book;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //اجمع بياناتي بفيو واحد لان ليست فيو ما تقبل الا واحد بكل عنصر
        View row;
        LayoutInflater rowInflator=LayoutInflater.from(AdapterContext);//لضغط جميع العناصر بالصف الواحد كعنصر فيو واحد
        row=rowInflator.inflate(AdapterResource,parent,false);
        TextView Name=(TextView) row.findViewById(R.id.name);
        TextView Price=(TextView) row.findViewById(R.id.price);
        ImageView Image=(ImageView) row.findViewById(R.id.img);
        Detail product=AdapterBook.get(position);
        Name.setText(product.getName_of_announcement());
//        EditText ed=(EditText) row.findViewById(R.id.ed);
//        int p=Integer.parseInt(book.price)*Integer.parseInt(ed.getText().toStrjing());
        Price.setText(product.getPrice_of_announcement()+" R.S" );
        Glide.with(Image.getContext()).load(product.url_of_the_announcement_image).into(Image);
        ImageView del=(ImageView) row.findViewById(R.id.del);
        ImageView Button1= (ImageView)  row.findViewById(R.id.decbtn);
        ImageView Button2= (ImageView)  row.findViewById(R.id.incbtn);
    //    del.setOnClickListener(new View.OnClickListener(){
          //  @Override
    //        public void onClick(View v){
        //        product.setNumber("0");
  //          }
    //    });
//        Button1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                product.setNumber(product.getNumber()-1);
//            }
//        });
//        Button2.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                product.setNumber(product.getNumber()+1);
//            }
//        });
//        TextView hi=(TextView) row.findViewById(R.id.hin);
//        TextView KM=(TextView) row.findViewById(R.id.km);
//        ed.setText(book.km);
//        hi.setHint(book.ID);
        return row;//super.getView(position, convertView, parent);
    }
}
