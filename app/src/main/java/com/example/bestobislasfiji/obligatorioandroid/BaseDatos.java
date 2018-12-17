package com.example.bestobislasfiji.obligatorioandroid;

import android.provider.BaseColumns;

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
     //   public static final String ID_PRODUCTO="IdProducto";
        public static final String CATEGORIA="Categoria";
        public static final String NOMBRE_PRODUCTO="NombreProducto";
        public static final String PRECIO="Precio";
        public static final String DESCRIPCION="Descripcion";
        public static final String FOTO="Foto";


        public static final String[] COLUMNAS_PRODUCTOS={
                _ID,CATEGORIA,NOMBRE_PRODUCTO,DESCRIPCION,PRECIO,FOTO
                //_ID,ID_PRODUCTO,CATEGORIA,NOMBRE_PRODUCTO,PRECIO,FOTO
        } ;

        public static final String[] DETALLE_PRODUCTO={
                NOMBRE_PRODUCTO,DESCRIPCION,PRECIO
                //_ID,ID_PRODUCTO,CATEGORIA,NOMBRE_PRODUCTO,PRECIO,FOTO
        } ;

        public static final String[] CATEGORIAS_PRODUCTO={
                CATEGORIA
                //_ID,ID_PRODUCTO,CATEGORIA,NOMBRE_PRODUCTO,PRECIO,FOTO
        } ;

        public static final String[] PRODUCTOS_LIST={
                NOMBRE_PRODUCTO
                //_ID,ID_PRODUCTO,CATEGORIA,NOMBRE_PRODUCTO,PRECIO,FOTO
        } ;

        public static final String SQL_CREAR_TABLA_PRODUCTOS=new StringBuilder("CREATE TABLE ")
                .append(PRODUCTOS).append(" (")
                .append(_ID).append(" integer primary key  autoincrement, ")
                //.append(ID_PRODUCTO).append(" integer primary key autoincrement, ")
                .append(CATEGORIA).append(" text not null, ")
                .append(NOMBRE_PRODUCTO).append(" text not null, ")
                .append(DESCRIPCION).append(" text not null, ")
                .append(PRECIO).append(" real not null, ")
                .append(FOTO).append(" blob );")
                .toString();

        public static final String SQL_ELIMINAR_TABLA_PRODUCTOS=new StringBuilder("DROP TABLE IF EXISTS ")
                .append(PRODUCTOS)
                .append(";")
                .toString();
    }

    //   bio.execSQL("create table pedido(idPedido int primary key autoincrement , pagoAdelantado int ,nombreCliente text, cantidad int,idProducto int, FOREIGN KEY(idProducto) REFERENCES producto(id))");
    public static abstract class Pedidos implements BaseColumns
    {
        public static final String ID_PAGO="IdPago";
        public static final String PAGO_ADELANTADO="PagoAdelantado";
        public static final String NOMBRE_CLIENTE="NombreCliente";
        public static final String CANTIDAD="Cantidad";
        public static final String ENTREGADO="Entregado";
        public static final String ID_PRODUCTO="IdProducto";

        public static final String[] COLUMNAS_PEDIDOS={
                _ID,PAGO_ADELANTADO,NOMBRE_CLIENTE,CANTIDAD,ENTREGADO,ID_PRODUCTO
    } ;

        public static final String SQL_CREAR_TABLA_PEDIDOS=new StringBuilder("CREATE TABLE ")
                .append(PEDIDOS)
                .append(" (")
                .append(_ID).append(" integer primary key autoincrement, ")
                .append(PAGO_ADELANTADO).append(" integer not null , ")
                .append(NOMBRE_CLIENTE).append(" text not null, ")
                .append(CANTIDAD).append(" real not null , ")
                .append(ENTREGADO).append("integer not null default 0, ")
                .append(ID_PRODUCTO).append("integer not null, FOREIGN KEY (IdProducto) REFERENCES Productos(IdProducto));")
                .toString();

        public static final String SQL_ELIMINAR_TABLA_PEDIDOS=new StringBuilder("DROP TABLE ")
        .append(PEDIDOS)
        .append(";")
        .toString();


    }




}
