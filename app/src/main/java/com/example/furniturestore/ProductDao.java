//package com.example.furniturestore;
//
//import androidx.room.Dao;
//import androidx.room.Insert;
//import androidx.room.Query;
//
//import java.util.List;
//
//@Dao
//public interface ProductDao
//{
//    @Insert
//    void insertrecord(ProductC product);
//
//    @Query("SELECT EXISTS(SELECT * FROM ProductC WHERE pid = :productid)")
//    Boolean is_exist(int productid);
//
//
//    @Query("SELECT * FROM ProductC")
//    List<ProductC> getallproduct();
//
//    @Query("DELETE FROM ProductC WHERE pid = :id")
//    void deleteById(int id);
//}