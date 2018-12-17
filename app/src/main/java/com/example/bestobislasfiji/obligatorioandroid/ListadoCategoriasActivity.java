package com.example.bestobislasfiji.obligatorioandroid;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ListadoCategoriasActivity extends AppCompatActivity {

    protected ListView lvCategorias;

    private AdminSQLiteHelper BdHelper;
    private SQLiteDatabase BD;
    private SimpleCursorAdapter adaptadorCategorias;
    protected ListadoCategoriasFragment frgListadoCategorias;

    //EXTRAS
    //public static final String EXTRA_FOTO="EXTRA_FOTO";
    //public static final String EXTRA_NOMBRE_PROD="EXTRA_NOMBRE_PROD";
    //public static final String EXTRA_PRECIO="EXTRA_PRECIO";
    public static final String EXTRA_CATEGORIA="EXTRA_CATEGORIA";
    private String categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Categorías");
        //setContentView(R.layout.activity_listado_categorias);


        setContentView(R.layout.listado_categorias);

        /*frgListadoCategorias = (ListadoCategoriasFragment)getSupportFragmentManager().findFragmentById(R.id.frgListadoCategorias);
        setContentView(R.layout.activity_main);*/

        lvCategorias = (ListView)findViewById(R.id.lvCategorias);

        BdHelper = new AdminSQLiteHelper(this);
        BD = BdHelper.getWritableDatabase();

        /*eliminarArtistas();
        agregarArtistas();*/

        //adaptadorCategorias = new SimpleCursorAdapter(this, R.layout.listado_categorias, listarCategorias(), BaseDatos.Prodctos.COLUMNAS_PRODUCTOS, new int[] { R.id.tvId, R.id.tvNombre, R.id.tvAnioNacimiento }, 0);
        adaptadorCategorias = new SimpleCursorAdapter(this, R.layout.fragment_listado_categorias, listarCategorias(), BaseDatos.Prodctos.CATEGORIAS_PRODUCTO, new int[] { R.id.tvCategoria }, 0);
        lvCategorias.setAdapter(adaptadorCategorias);

        //Cursor cursor = ((SimpleCursorAdapter)adaptadorCategorias).getCursor();
       // cursor.moveToPosition(position);

        //int columnaId = cursor.getColumnIndex(BaseDatos.Prodctos._ID);

        lvCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvCategoriasOnItemClick(parent, view, position, id);
            }

        });

    }

    protected Cursor listarCategorias() {

         return BD.query(BaseDatos.PRODUCTOS,BaseDatos.Prodctos.COLUMNAS_PRODUCTOS, null, null, "categoria", null, null);

        //return BD.rawQuery("SELECT distinct"+BaseDatos.Prodctos.CATEGORIA +" FROM " + BaseDatos.PRODUCTOS + " ORDER BY " + BaseDatos.Prodctos.CATEGORIA + " DESC;", null);
       // return BD.rawQuery("SELECT * FROM " + BaseDatos.PRODUCTOS + " ORDER BY " + BaseDatos.Prodctos.CATEGORIA, null);

        /*SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(BD.ARTISTAS); // En caso de dos (o más) tablas: "X INNER JOIN Y ON X.y = Y.y"

        return qb.query(baseDatos, BD.Artistas.COLUMNAS, null, null, null, null, BD.Artistas.NOMBRE + " DESC");*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        BD.close();

      //  BdHelper.eliminarBaseDatos();
    }

    protected void lvCategoriasOnItemClick(AdapterView<?> parent, View view , int position, long id)
    {
        Intent intencionProductosCategoria=new Intent(this,ListadoProductosXCategoriaAct.class);
       // ListadoProductosXCategoriaFrag frgListadoProductos=(ListadoProductosXCategoriaFrag)getSupportFragmentManager().findFragmentById(R.id.frgListadoProdXCat);

        //Bundle datosProducto=new Bundle();
       // categoria= (String)parent.getItemAtPosition(position);

        categoria =((TextView)view.findViewById(R.id.tvCategoria)).getText().toString();
        //categoria=(lvCategorias.getItemAtPosition(position).toString());
        //categoria="Vehículos";

        /*if (frgListadoProductos != null) {
            frgListadoProductos.mostrarProductos(empleado);
        } else {*/
            intencionProductosCategoria.putExtra(EXTRA_CATEGORIA, categoria);

            startActivity(intencionProductosCategoria);
       // }
    }
}
