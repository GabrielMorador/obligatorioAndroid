package com.example.bestobislasfiji.obligatorioandroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListadoCategoriasActivity extends AppCompatActivity {

    protected ListView lvCategorias;

    private AdminSQLiteHelper BdHelper;
    private SQLiteDatabase BD;
    private SimpleCursorAdapter adaptadorCategorias;
    protected ListadoCategoriasFragment frgListadoCategorias;

    public static final String EXTRA_CATEGORIA="EXTRA_CATEGORIA";
    private String categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Categorías");

        setContentView(R.layout.listado_categorias);

        lvCategorias = (ListView)findViewById(R.id.lvCategorias);

        BdHelper = new AdminSQLiteHelper(this);
        BD = BdHelper.getReadableDatabase();


        adaptadorCategorias = new SimpleCursorAdapter(this, R.layout.fragment_listado_categorias, listarCategorias(), BaseDatos.Prodctos.CATEGORIAS_PRODUCTO, new int[] { R.id.tvCategoria }, 0);
        lvCategorias.setAdapter(adaptadorCategorias);



        lvCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvCategoriasOnItemClick(parent, view, position, id);
            }

        });

    }

    protected Cursor listarCategorias() {

         return BD.query(BaseDatos.PRODUCTOS,BaseDatos.Prodctos.COLUMNAS_PRODUCTOS, null, null, "categoria", null, null);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        BD.close();

    }

    protected void lvCategoriasOnItemClick(AdapterView<?> parent, View view , int position, long id)
    {
        Intent intencionProductosCategoria=new Intent(this,ListadoProductosXCategoriaAct.class);


        categoria =((TextView)view.findViewById(R.id.tvCategoria)).getText().toString();

            intencionProductosCategoria.putExtra(EXTRA_CATEGORIA, categoria);

            startActivity(intencionProductosCategoria);

    }
}
