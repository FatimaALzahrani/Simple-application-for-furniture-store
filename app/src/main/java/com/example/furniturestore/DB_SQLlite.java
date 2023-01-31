package com.example.furniturestore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB_SQLlite extends SQLiteOpenHelper {
    public static final String DBname="Funiture_Store.db";
    //عشان نقدر نوصل لقاعدة البيانات
    public DB_SQLlite(@Nullable Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Users(Email TEXT PRIMARY KEY,Name TEXT,Phone TEXT,Password TEXT)");
        sqLiteDatabase.execSQL("create table Product(Name TEXT,Image TEXT,Price TEXT,Type TEXT,Color TEXT,Composition TEXT,Durability TEXT,ImageD TEXT)");
        sqLiteDatabase.execSQL("create table Cart(Name TEXT PRIMARY KEY,Image TEXT,Price TEXT,Type TEXT,Color TEXT,Composition TEXT,Durability TEXT,ImageD TEXT,Email TEXT,num TEXT)");//km TEXT,
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Users");//لضمان وجود الجدول بقاعدة البيانات
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Product");//لضمان وجود الجدول بقاعدة البيانات
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Cart");//لضمان وجود الجدول بقاعدة البيانات
        onCreate(sqLiteDatabase);// لو كان موجود بيحذفه ويستدعي اون كرييت ويحط الجديد
    }

    public boolean insertUser(String name,String email,String password,String phone){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",name);
        contentValues.put("Email",email);
        contentValues.put("Password",password);
        contentValues.put("Phone",phone);
        long result=db.insert("Users",null,contentValues);//بيضيف البيانات لجدولنا وترجع رقم
        if(result==-1){//لو سالب واحد القيمه يعني فولس اي ما انضافت القيمه , ومو 0 لان البداية بالجداول عند صفر
            return false;
        }else{
            return true;
        }
    }
    public boolean insertProduct(String Name,String Image,String Price,String Type,String Color,String Composition,String Durability,String ImageD){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",Name);
        contentValues.put("ImageD",ImageD);
        contentValues.put("Image",Image);
        contentValues.put("Price",Price);
        contentValues.put("Type",Type);
        contentValues.put("Composition",Composition);
        contentValues.put("Durability",Durability);
        contentValues.put("Color",Color);
        long result=db.insert("Product",null,contentValues);//بيضيف البيانات لجدولنا وترجع رقم
        if(result==-1){//لو سالب واحد القيمه يعني فولس اي ما انضافت القيمه , ومو 0 لان البداية بالجداول عند صفر
            return false;
        }else{
            return true;
        }
    }
    public boolean insertCart(String Name,String Image,String Price,String Type,String Color,String Composition,String Durability,String ImageD,String Email,String num){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",Name);
        contentValues.put("ImageD",ImageD);
        contentValues.put("Image",Image);
        contentValues.put("Price",Price);
        contentValues.put("Type",Type);
        contentValues.put("Composition",Composition);
        contentValues.put("Durability",Durability);
        contentValues.put("Color",Color);
        contentValues.put("Email",Email);
        contentValues.put("num",num);
        long result=db.insert("Cart",null,contentValues);//بيضيف البيانات لجدولنا وترجع رقم
        if(result==-1){//لو سالب واحد القيمه يعني فولس اي ما انضافت القيمه , ومو 0 لان البداية بالجداول عند صفر
            return false;
        }else{
            return true;
        }
    }

    public boolean checkUser(SQLiteDatabase db,String email,String pass){
        Cursor cursor= db.rawQuery("SELECT * FROM Users Where Email="+email+" and Password="+pass+" ",null);
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    public ArrayList getAllUsers(){
        ArrayList arrayList=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from Users",null);
        res.moveToFirst();//روح لاول صف
        while (res.isAfterLast()==false){
            String t1=res.getString(0);//الرقم يمثل رقم العمود
            String t2=res.getString(1);
            String t3=res.getString(2);
            String t4=res.getString(3);
            arrayList.add(t1+" "+t2+" "+t3+" "+t4);
            res.moveToNext();//يروح الصف الثاني
        }
        return arrayList;
    }
    public ArrayList<Product> getAllProduct(){
        ArrayList arrayList=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from Product",null);
        res.moveToFirst();//روح لاول صف
        while (res.isAfterLast()==false){
            String t0=res.getString(0);//الرقم يمثل رقم العمود
            String t1=res.getString(1);//الرقم يمثل رقم العمود
            String t2=res.getString(2);
            String t3=res.getString(3);
            String t4=res.getString(4);//الرقم يمثل رقم العمود
            String t5=res.getString(5);//الرقم يمثل رقم العمود
            String t6=res.getString(6);
            String t7=res.getString(7);
//            String t4=res.getString(4);//الرقم يمثل رقمم العمود
            Product product=new Product(t0,t1,t2,t3,t4,t5,t6,t7);//,t4);
            arrayList.add(product);
            res.moveToNext();//يروح الصف الثاني
        }
        return arrayList;
    }
    public ArrayList<Detail> getAllCart(){
        ArrayList arrayList=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from Cart",null);
        res.moveToFirst();//روح لاول صف
        while (res.isAfterLast()==false){
            String t0 = res.getString(0);//الرقم يمثل رقم العمود
            String t1 = res.getString(1);//الرقم يمثل رقم العمود
            String t2 = res.getString(2);
            String t3 = res.getString(3);
            String t4 = res.getString(4);//الرقم يمثل رقم العمود
            String t5 = res.getString(5);//الرقم يمثل رقم العمود
            String t6 = res.getString(6);
            String t7 = res.getString(7);
            String t8 = res.getString(8);
//                String t9 = res.getString(9);
//            String t4=res.getString(4);//الرقم يمثل رقمم العمود
            Detail product = new Detail(t0, t1, t2, t3, t4, t5, t6, t7, t8, "1");//,t9);//,t4);
            arrayList.add(product);
            res.moveToNext();//يروح الصف الثاني
        }
        return arrayList;
    }
    public int getSum(){
        int sum=0;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select Price from Cart",null);
        res.moveToFirst();//روح لاول صف
        while (res.isAfterLast()==false){
            sum+=Integer.parseInt(res.getString(0));//*Integer.parseInt(res.getString(4));
            res.moveToNext();//يروح الصف الثاني
        }
        return sum;
    }
    public int delete(String ID){
        SQLiteDatabase db=this.getWritableDatabase();
        String whereArgs[]={ID};
        return db.delete("Cart","Name =?",whereArgs);
    }
    public boolean update(String ID,String name,String Image,String Price){//,String km){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Name",name);
        contentValues.put("Image",Image);
        contentValues.put("Price",Price);
        contentValues.put("ID",ID);
//        contentValues.put("km",km);
        long result=db.update("Carts",contentValues,"ID=?",new String[]{String.valueOf(ID)});//بيضيف البيانات لجدولنا وترجع رقم
        if(result==-1){//لو سالب واحد القيمه يعني فولس اي ما انضافت القيمه , ومو 0 لان البداية بالجداول عند صفر
            return false;
        }else{
            return true;
        }
    }
}
