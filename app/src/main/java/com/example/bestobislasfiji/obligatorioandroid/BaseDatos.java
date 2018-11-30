package com.example.bestobislasfiji.obligatorioandroid;

import android.provider.BaseColumns;

import java.sql.Blob;

public final class BaseDatos {


    public static final String BD_NAME="BioDataBase.sqlite3";
    public static final int BD_VERSION=1;

    public static final String PRODUCTOS="Productos";
    public static final String PEDIDOS="Pedidos";

    private BaseDatos()
    {

    }

    //"create table producto(id int primary key , categoria text ,nombreProducto text ,precio real, descripcion text , foto blob )");
    public static abstract class Prodctos implements BaseColumns
    {
        public static final String CATEGORIA="Categoria";
        public static final String NOMBRE_PRODUCTO="NombreProducto";
        public static final String PRECIO="Precio";
        public static final String FOTO="Foto";

        public static final String[] COLUMNAS_PRODUCTOS={
                _ID,CATEGORIA,NOMBRE_PRODUCTO,PRECIO,FOTO
        } ;

        public static final String SQL_CREAR_TABLA_PRODUCTOS=new StringBuilder("CREATE TABLE ")
                .append(PRODUCTOS).append(" (")
                .append(_ID).append(" int primary key , ")
                .append(CATEGORIA).append(" text not null, ")
                .append(NOMBRE_PRODUCTO).append(" text not null, ")
                .append(FOTO).append(" blob not null")
                .toString();

        public static final String SQL_ELIMINAR_TABLA_PRODUCTOS=new StringBuilder("DROP TABLE IF EXISTS ")
                .append(PRECIO)
                .append(";")
                .toString();
    }

    //   bio.execSQL("create table pedido(idPedido int primary key autoincrement , pagoAdelantado int ,nombreCliente text, cantidad int,idProducto int, FOREIGN KEY(idProducto) REFERENCES producto(id))");
    public static abstract class Pedidos implements BaseColumns
    {
        public static final String PAGO_ADELANTADO="PagoAdelantado";
        public static final String NOMBRE_CLIENTE="NombreCliente";
        public static final String CANTIDAD="Cantidad";
        public static final String ID_PRODUCTO="IdProducto";

        public static final String[] COLUMNAS_PEDIDOS={
                _ID,PAGO_ADELANTADO,NOMBRE_CLIENTE,CANTIDAD,ID_PRODUCTO
    } ;

        public static final String SQL_CREAR_TABLA_PEDIDOS=new StringBuilder("CREATE TABLE ")
                .append(PEDIDOS)
                .append(" (")
                .append(_ID).append(" int primary key autoincrement, ")
                .append(PAGO_ADELANTADO).append(" int not null check PagoAdelantado in(0,1), ")
                .append(NOMBRE_CLIENTE).append(" text not null, ")
                .append(CANTIDAD).append(" real not null , ")
                .append(ID_PRODUCTO).append("int not null, FOREIGN KEY (IdProducto) REFERENCES Productos(_ID)")
                .toString();

        public static final String SQL_ELIMINAR_TABLA_PEDIDOS=new StringBuilder("DROP TABLE ")
        .append(PEDIDOS)
        .append(";")
        .toString();


    }




}
