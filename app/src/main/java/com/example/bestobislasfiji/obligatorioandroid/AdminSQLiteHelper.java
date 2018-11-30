package com.example.bestobislasfiji.obligatorioandroid;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;


public class AdminSQLiteHelper extends SQLiteOpenHelper{

    private Context contexto;

    public AdminSQLiteHelper(Context contexto) {
        super(contexto,BaseDatos.BD_NAME,null,BaseDatos.BD_VERSION);

    this.contexto=contexto;
    }

    public Bitmap getReportPicture(long reportId) {
        String picturePath = getReportPicturePath(reportId);
        if (picturePath == null || picturePath.length() == 0)
            return (null);

        Bitmap reportPicture = BitmapFactory.decodeFile(picturePath);

        return (reportPicture);
    }

    @Override
    public void onCreate(SQLiteDatabase bio) {

        bio.execSQL(BaseDatos.Prodctos.SQL_CREAR_TABLA_PRODUCTOS);
        bio.execSQL(BaseDatos.Pedidos.SQL_CREAR_TABLA_PEDIDOS);

        bio.execSQL(new StringBuilder("insert into ")
        .append(BaseDatos.PRODUCTOS)
        .append(" values(1,'Vehículos,'Toyota AE86,25.50,'Papa con sistema de pelación automática',);")
        .toString());

        bio.execSQL("create table producto(id int primary key , categoria text ,nombreProducto text ,precio real, descripcion text , foto blob )");
        bio.execSQL("create table pedido(idPedido int primary key autoincrement , pagoAdelantado int ,nombreCliente text, cantidad int,idProducto int, FOREIGN KEY(idProducto) REFERENCES producto(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
