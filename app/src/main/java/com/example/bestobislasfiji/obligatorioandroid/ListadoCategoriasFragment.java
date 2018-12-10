package com.example.bestobislasfiji.obligatorioandroid;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListadoCategoriasFragment extends Fragment {

    protected ListView lvCategorias;

    private AdminSQLiteHelper BdHelper;
    private SQLiteDatabase BD;
    private SimpleCursorAdapter adaptadorCategorias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado_categorias, container, false);
    }

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        lvCategorias = (ListView)findViewById(R.id.lvCategorias);

        BdHelper = new AdminSQLiteHelper(this);
        BD = BdHelper.getWritableDatabase();*/

        /*eliminarArtistas();
        agregarArtistas();*/

        //adaptadorCategorias = new SimpleCursorAdapter(this, R.layout.listado_categorias, listarCategorias(), BaseDatos.Prodctos.COLUMNAS_PRODUCTOS, new int[] { R.id.tvId, R.id.tvNombre, R.id.tvAnioNacimiento }, 0);
     /*   adaptadorCategorias = new SimpleCursorAdapter(this, R.layout.listado_categorias, listarCategorias(), BaseDatos.Prodctos.COLUMNAS_PRODUCTOS, new int[] { R.id.tvCategoria }, 0);
        lvCategorias.setAdapter(adaptadorCategorias);*/

       /* lvCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvArtistasOnItemClick(parent, view, position, id);
            }

        });
}*/

   //     protected Cursor listarCategorias() {

           // return BD.query(BaseDatos.PRODUCTOS,BaseDatos.Prodctos.COLUMNAS_PRODUCTOS, null, null, null, null, null);

            //return BD.rawQuery("SELECT distinct"+BaseDatos.Prodctos.CATEGORIA +" FROM " + BaseDatos.PRODUCTOS + " ORDER BY " + BaseDatos.Prodctos.CATEGORIA + " DESC;", null);
            //return BD.rawQuery("SELECT * FROM " + BaseDatos.PRODUCTOS + " ORDER BY " + BaseDatos.Prodctos.CATEGORIA, null);

        /*SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(BD.ARTISTAS); // En caso de dos (o más) tablas: "X INNER JOIN Y ON X.y = Y.y"

        return qb.query(baseDatos, BD.Artistas.COLUMNAS, null, null, null, null, BD.Artistas.NOMBRE + " DESC");*/
     //   }
    }


