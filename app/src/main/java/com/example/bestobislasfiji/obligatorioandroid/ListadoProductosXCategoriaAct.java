package com.example.bestobislasfiji.obligatorioandroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListadoProductosXCategoriaAct extends AppCompatActivity {


    protected ListView lvProductos;

    private AdminSQLiteHelper BdHelper;
    private SQLiteDatabase BD;
    private SimpleCursorAdapter adaptadorProductos;
    protected ListadoCategoriasFragment frgListadoCategorias;

    private String categoria;


    protected ImageView imgProducto;
    protected TextView tvNombreProducto;
    protected TextView tvPrecio;

    public static final String EXTRA_ID="EXTRA_ID";
    public static final String EXTRA_NOMBRE="EXTRA_NOMBRE";
    public static final String EXTRA_DESCRIPCION="EXTRA_DESCRIPCION";
    public static final String EXTRA_PRECIO="EXTRA_PRECIO";

    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.frag_listado_prod_x_cat_layout);
        //setContentView(R.layout.activity_listado_categorias);


        setContentView(R.layout.listado_prod_x_cat);

        /*frgListadoCategorias = (ListadoCategoriasFragment)getSupportFragmentManager().findFragmentById(R.id.frgListadoCategorias);
        setContentView(R.layout.activity_main);*/

        lvProductos = (ListView)findViewById(R.id.lvProductos);

        BdHelper = new AdminSQLiteHelper(this);
        BD = BdHelper.getWritableDatabase();

        /*eliminarArtistas();
        agregarArtistas();*/

        Intent i=getIntent();
        categoria=i.getStringExtra("EXTRA_CATEGORIA");
        setTitle(categoria);


        //adaptadorCategorias = new SimpleCursorAdapter(this, R.layout.listado_categorias, listarCategorias(), BaseDatos.Prodctos.COLUMNAS_PRODUCTOS, new int[] { R.id.tvId, R.id.tvNombre, R.id.tvAnioNacimiento }, 0);
        adaptadorProductos = new SimpleCursorAdapter(this, R.layout.frag_listado_prod_x_cat_layout, listarCategorias(categoria), BaseDatos.Prodctos.PRODUCTOS_LIST, new int[] { R.id.tvNombreProducto}, 0);
        lvProductos.setAdapter(adaptadorProductos);

        //Cursor cursor = ((SimpleCursorAdapter)adaptadorCategorias).getCursor();
        // cursor.moveToPosition(position);

        //int columnaId = cursor.getColumnIndex(BaseDatos.Prodctos._ID);

        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvCategoriasOnItemClick(parent, view, position, id);
            }

        });

    }

    protected Cursor listarCategorias(String categoria) {

      return BD.query(BaseDatos.PRODUCTOS,BaseDatos.Prodctos.COLUMNAS_PRODUCTOS, BaseDatos.Prodctos.CATEGORIA+"=?", new String [] {categoria}, null, null, null);

       //return BD.rawQuery("SELECT * FROM " + BaseDatos.PRODUCTOS + " WHERE  " + BaseDatos.Prodctos.CATEGORIA + " = ? ;", new String[] {String.valueOf(categoria)});
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
        Intent intencionDetalleProducto=new Intent(this,DetalleProductoActivity.class);
        // ListadoProductosXCategoriaFrag frgListadoProductos=(ListadoProductosXCategoriaFrag)getSupportFragmentManager().findFragmentById(R.id.frgListadoProdXCat);

      //  Bundle datosProducto=new Bundle();
        // categoria= (String)parent.getItemAtPosition(position);

//        nombre =((TextView)view.findViewById(R.id.tvNombreProducto)).getText().toString();
        //categoria=(lvCategorias.getItemAtPosition(position).toString());
        //categoria="Vehículos";


        Cursor cursor = ((SimpleCursorAdapter)adaptadorProductos).getCursor();
        cursor.moveToPosition(position);

        int columnaId = cursor.getColumnIndex(BaseDatos.Prodctos._ID);
        int columnaNombre = cursor.getColumnIndex(BaseDatos.Prodctos.NOMBRE_PRODUCTO);
        int columnaDescripcion = cursor.getColumnIndex(BaseDatos.Prodctos.DESCRIPCION);
        int columnaPrecio = cursor.getColumnIndex(BaseDatos.Prodctos.PRECIO);
      //  int columnaAnioNacimiento = cursor.getColumnIndex(BD.Artistas.ANIO_NACIMIENTO);

        Integer idProd=(cursor.getInt(columnaId));
        String nombre=(cursor.getString(columnaNombre));
        String descripcion=(cursor.getString(columnaDescripcion));
        Double precio=(cursor.getDouble(columnaPrecio));

        intencionDetalleProducto.putExtra(EXTRA_ID,idProd);
        intencionDetalleProducto.putExtra(EXTRA_NOMBRE,nombre);
        intencionDetalleProducto.putExtra(EXTRA_DESCRIPCION,descripcion);
        intencionDetalleProducto.putExtra(EXTRA_PRECIO,precio);

        /*if (frgListadoProductos != null) {
            frgListadoProductos.mostrarProductos(empleado);
        } else {*/
        //intencionDetalleProducto.putExtra(EXTRA_NOMBRE, nombre);

        startActivity(intencionDetalleProducto);
        // }
    }


}
