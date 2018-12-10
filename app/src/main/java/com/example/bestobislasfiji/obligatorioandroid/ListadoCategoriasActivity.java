package com.example.bestobislasfiji.obligatorioandroid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListadoCategoriasActivity extends AppCompatActivity {

    protected ListView lvCategorias;

    private AdminSQLiteHelper BdHelper;
    private SQLiteDatabase BD;
    private SimpleCursorAdapter adaptadorCategorias;
    protected ListadoCategoriasFragment frgListadoCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listado_categorias);

        frgListadoCategorias = (ListadoCategoriasFragment)getSupportFragmentManager().findFragmentById(R.id.frgListadoCategorias);
        setContentView(R.layout.activity_main);

        lvCategorias = (ListView)findViewById(R.id.lvCategorias);

        BdHelper = new AdminSQLiteHelper(this);
        BD = BdHelper.getWritableDatabase();

        /*eliminarArtistas();
        agregarArtistas();*/

        //adaptadorCategorias = new SimpleCursorAdapter(this, R.layout.listado_categorias, listarCategorias(), BaseDatos.Prodctos.COLUMNAS_PRODUCTOS, new int[] { R.id.tvId, R.id.tvNombre, R.id.tvAnioNacimiento }, 0);
        adaptadorCategorias = new SimpleCursorAdapter(this, R.layout.listado_categorias, listarCategorias(), BaseDatos.Prodctos.COLUMNAS_PRODUCTOS, new int[] { R.id.tvCategoria }, 0);
        lvCategorias.setAdapter(adaptadorCategorias);

    }
    protected Cursor listarCategorias() {

        // return BD.query(BaseDatos.PRODUCTOS,BaseDatos.Prodctos.COLUMNAS_PRODUCTOS, null, null, null, null, null);

        //return BD.rawQuery("SELECT distinct"+BaseDatos.Prodctos.CATEGORIA +" FROM " + BaseDatos.PRODUCTOS + " ORDER BY " + BaseDatos.Prodctos.CATEGORIA + " DESC;", null);
        return BD.rawQuery("SELECT * FROM " + BaseDatos.PRODUCTOS + " ORDER BY " + BaseDatos.Prodctos.CATEGORIA, null);

        /*SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(BD.ARTISTAS); // En caso de dos (o más) tablas: "X INNER JOIN Y ON X.y = Y.y"

        return qb.query(baseDatos, BD.Artistas.COLUMNAS, null, null, null, null, BD.Artistas.NOMBRE + " DESC");*/
    }
}
