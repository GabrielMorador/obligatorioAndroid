package com.example.bestobislasfiji.obligatorioandroid;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AdminSQLiteHelper extends SQLiteOpenHelper{

    public AdminSQLiteHelper(@androidx.annotation.Nullable Context context, @androidx.annotation.Nullable String name, @androidx.annotation.Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bio {
        bio.execSQL("create table producto(id int primary key , categoria text ,nombreProducto text ,precio real, descripcion text , foto blob )");
        bio.execSQL("create table pedido(idPedido int primary key autoincrement , pagoAdelantado int ,nombreCliente text, cantidad int,idProducto int, FOREIGN KEY(idProducto) REFERENCES producto(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
