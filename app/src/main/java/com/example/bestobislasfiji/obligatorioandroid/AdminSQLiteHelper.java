package com.example.bestobislasfiji.obligatorioandroid;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AdminSQLiteHelper extends SQLiteOpenHelper{

    private Context contexto;

    public AdminSQLiteHelper(Context contexto) {
        super(contexto,BaseDatos.BD_NAME,null,BaseDatos.BD_VERSION);

    this.contexto=contexto;
    }

   /* public Bitmap getReportPicture(long reportId) {
        String picturePath = getReportPicturePath(reportId);
        if (picturePath == null || picturePath.length() == 0)
            return (null);

        Bitmap reportPicture = BitmapFactory.decodeFile(picturePath);

        return (reportPicture);
    }*/

    @Override
    public void onCreate(SQLiteDatabase bio) {
    //    bio.execSQL(BaseDatos.Prodctos.SQL_ELIMINAR_TABLA_PRODUCTOS);
        bio.execSQL(BaseDatos.Prodctos.SQL_CREAR_TABLA_PRODUCTOS);
     //   bio.execSQL(BaseDatos.Pedidos.SQL_CREAR_TABLA_PEDIDOS);


        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append(" values(null,'Vehículos','Toyota AE86',25.50,null);")
                .toString());

        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append(" values(null,'Vehículos','Subaru Impreza',55555.50,null);")
                .toString());

        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append(" values(null,'Empanadas','de jamon y queso',10.50,null);")
                .toString());

        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append(" values(null,'Empanadas','De carne',50.00,null);")
                .toString());



        /*bio.execSQL(new StringBuilder("insert into ")
        .append(BaseDatos.PRODUCTOS)
                .append("(")
                .append(BaseDatos.Prodctos.CATEGORIA)
                .append(",")
                .append(BaseDatos.Prodctos.NOMBRE_PRODUCTO)
                .append(",")
                .append(BaseDatos.Prodctos.PRECIO)
                .append(")")
        .append(" values('Vehículos','Toyota AE86',25.50);")
        .toString());

        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append("(")
                .append(BaseDatos.Prodctos.CATEGORIA)
                .append(",")
                .append(BaseDatos.Prodctos.NOMBRE_PRODUCTO)
                .append(",")
                .append(BaseDatos.Prodctos.PRECIO)
                .append(")")
                .append(" values('Vehículos','Subaru Impreza',55555.50);")
                .toString());

        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append("(")
                .append(BaseDatos.Prodctos.CATEGORIA)
                .append(",")
                .append(BaseDatos.Prodctos.NOMBRE_PRODUCTO)
                .append(",")
                .append(BaseDatos.Prodctos.PRECIO)
                .append(")")
                .append(" values('Empanadas','de jamon y queso',10.50);")
                .toString());

        bio.execSQL(new StringBuilder("insert into ")
                .append(BaseDatos.PRODUCTOS)
                .append("(")
                .append(BaseDatos.Prodctos.CATEGORIA)
                .append(",")
                .append(BaseDatos.Prodctos.NOMBRE_PRODUCTO)
                .append(",")
                .append(BaseDatos.Prodctos.PRECIO)
                .append(")")
                .append(" values('Empanadas','De carne',50.00);")
                .toString());*/

      //  bio.execSQL("create table producto(id int primary key , categoria text ,nombreProducto text ,precio real, descripcion text , foto blob )");
        // bio.execSQL("create table pedido(idPedido int primary key autoincrement , pagoAdelantado int ,nombreCliente text, cantidad int,idProducto int, FOREIGN KEY(idProducto) REFERENCES producto(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
