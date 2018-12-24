package com.example.bestobislasfiji.obligatorioandroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListadoPedidosPendEntAct extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    protected ListView lvPedidosPendEntrega;
    protected Spinner spClientes;

    private AdminSQLiteHelper BdHelper;
    private SQLiteDatabase BD;
    private SimpleCursorAdapter adaptadorPedidosPendEntrega;
    private SimpleCursorAdapter adaptadorClientes;
    protected ListadoCategoriasFragment frgListadoCategorias;

    private String cliente;



    protected TextView tvNombreProducto;
    protected TextView tvPrecio;
    protected TextView tvImporteTotal;

    public static final String EXTRA_ID="EXTRA_ID";
    public static final String EXTRA_NOMBRE_CLIENTE="EXTRA_NOMBRE_CLIENTE";
    public static final String EXTRA_NOMBRE_PRODUCTO="EXTRA_NOMBRE_PRODUCTO";
    public static final String EXTRA_CANTIDAD="EXTRA_CANTIDAD";
    public static final String EXTRA_IMPORTR="EXTRA_IMPORTR";

    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.frag_listado_prod_x_cat_layout);
        //setContentView(R.layout.activity_listado_categorias);


        setContentView(R.layout.frag_pedidos_pend_entrega);

        /*frgListadoCategorias = (ListadoCategoriasFragment)getSupportFragmentManager().findFragmentById(R.id.frgListadoCategorias);
        setContentView(R.layout.activity_main);*/

        lvPedidosPendEntrega = (ListView)findViewById(R.id.lvPedidosPendEntrega);
        spClientes = (Spinner)findViewById(R.id.spCliente);
        tvImporteTotal=(TextView)findViewById(R.id.tvImporteTotal);

        BdHelper = new AdminSQLiteHelper(this);
        BD = BdHelper.getWritableDatabase();

        /*eliminarArtistas();
        agregarArtistas();*/

        //Intent i=getIntent();
      // categoria=i.getStringExtra("EXTRA_CATEGORIA");
       // setTitle(categoria);


        //adaptadorCategorias = new SimpleCursorAdapter(this, R.layout.listado_categorias, listarCategorias(), BaseDatos.Prodctos.COLUMNAS_PRODUCTOS, new int[] { R.id.tvId, R.id.tvNombre, R.id.tvAnioNacimiento }, 0);
      //  adaptadorPedidosPendEntrega = new SimpleCursorAdapter(this, R.layout.listado_pedidos_pend, listarPedidosPendEntrega(), BaseDatos.Pedidos.COLUMNAS_PEDIDOS_PEND, new int[] { R.id.tvIdPedido,R.id.tvtvProdPedido,R.id.tvCantidadProd}, 0);
        //lvPedidosPendEntrega.setAdapter(adaptadorPedidosPendEntrega);

       // adaptadorClientes = new SimpleCursorAdapter(this, R.layout.support_simple_spinner_dropdown_item, listarPedidosPendEntrega(), BaseDatos.Pedidos.COLUMNAS_PEDIDOS_PEND, new int[] { R.id.tvIdPedido,R.id.tvtvProdPedido,R.id.tvCantidadProd}, 0);
        //spClientes.setAdapter(adaptadorClientes);

        ArrayAdapter<String> adaptadorClientes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getClientes());
        adaptadorClientes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spClientes.setAdapter(adaptadorClientes);

        spClientes.setOnItemSelectedListener(this);

        //Cursor cursor = ((SimpleCursorAdapter)adaptadorCategorias).getCursor();
        // cursor.moveToPosition(position);

        //int columnaId = cursor.getColumnIndex(BaseDatos.Prodctos._ID);

        //lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           /* @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvCategoriasOnItemClick(parent, view, position, id);
            }

        });*/


        lvPedidosPendEntrega.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvPedidosPendEntregaOnItemClick(parent, view, position, id);
            }

        });

    }

    protected Cursor listarPedidosPendPorCliente(String cliente) {

        //return BD.query(BaseDatos.PRODUCTOS,BaseDatos.Prodctos.COLUMNAS_PRODUCTOS, BaseDatos.Prodctos.CATEGORIA+"=?", new String [] {categoria}, null, null, null);

        /*return BD.rawQuery("SELECT "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos._ID+","+BaseDatos.Prodctos.NOMBRE_PRODUCTO+","+BaseDatos.Pedidos.CANTIDAD+" FROM " +
                BaseDatos.PEDIDOS + " JOIN "+BaseDatos.PRODUCTOS+" on "+BaseDatos.PRODUCTOS+"."+BaseDatos.Prodctos._ID+" = "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos.ID_PRODUCTO+" WHERE "+
                BaseDatos.Pedidos.NOMBRE_CLIENTE + " = ? ;", new String[] {String.valueOf(cliente)});*/

        /*return BD.rawQuery("SELECT "+BaseDatos.PEDIDOS+".* , "+BaseDatos.PRODUCTOS+".*"+" FROM " +
                BaseDatos.PEDIDOS + " JOIN "+BaseDatos.PRODUCTOS+" on "+BaseDatos.PRODUCTOS+"."+BaseDatos.Prodctos._ID+" = "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos.ID_PRODUCTO+" WHERE "+
                BaseDatos.Pedidos.NOMBRE_CLIENTE + " = ? ;", new String[] {String.valueOf(cliente)});*/
        // return BD.rawQuery("SELECT * FROM " + BaseDatos.PRODUCTOS + " ORDER BY " + BaseDatos.Prodctos.CATEGORIA, null);

        return BD.rawQuery("SELECT "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos._ID+","+BaseDatos.Pedidos.NOMBRE_CLIENTE+","+BaseDatos.Prodctos.NOMBRE_PRODUCTO+","+BaseDatos.Pedidos.CANTIDAD+
                ","+BaseDatos.Prodctos.PRECIO+" FROM " +
                BaseDatos.PEDIDOS + " JOIN "+BaseDatos.PRODUCTOS+" on "+BaseDatos.PRODUCTOS+"."+BaseDatos.Prodctos._ID+" = "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos.ID_PRODUCTO+" WHERE "+
                BaseDatos.Pedidos.NOMBRE_CLIENTE + " = ? and "+BaseDatos.Pedidos.ENTREGADO + " = ? ;", new String[] {String.valueOf(cliente),String.valueOf(0)});
        // return BD.rawQuery("SELECT * FROM " + BaseDatos.PRODUCTOS + " ORDER BY " + BaseDatos.Prodctos.CATEGORIA, null);

        /*SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(BD.ARTISTAS); // En caso de dos (o más) tablas: "X INNER JOIN Y ON X.y = Y.y"

        return qb.query(baseDatos, BD.Artistas.COLUMNAS, null, null, null, null, BD.Artistas.NOMBRE + " DESC");*/
    }

    protected Cursor listarPedidosPendEntrega() {


        /*return BD.rawQuery("SELECT "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos._ID+","+BaseDatos.Prodctos.NOMBRE_PRODUCTO+","+BaseDatos.Pedidos.CANTIDAD+" FROM " +
                BaseDatos.PEDIDOS + " JOIN "+BaseDatos.PRODUCTOS+" on "+BaseDatos.PRODUCTOS+"."+BaseDatos.Prodctos._ID+" = "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos.ID_PRODUCTO+
                " ORDER BY "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos._ID+";", null);*/
        /*
        edidos._ID);
BaseDatos.Pedidos.NOMBRE_CLIENTE);
(BaseDatos.Prodctos.NOMBRE_PRODUCTO);
atos.Pedidos.CANTIDAD);
s.Prodctos.PRECIO);*/

        return BD.rawQuery("SELECT "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos._ID+","+BaseDatos.Pedidos.NOMBRE_CLIENTE+","+BaseDatos.Prodctos.NOMBRE_PRODUCTO+","+BaseDatos.Pedidos.CANTIDAD+
                ","+BaseDatos.Prodctos.PRECIO+" FROM " +
                BaseDatos.PEDIDOS + " JOIN "+BaseDatos.PRODUCTOS+" on "+BaseDatos.PRODUCTOS+"."+BaseDatos.Prodctos._ID+" = "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos.ID_PRODUCTO+
                " WHERE "+BaseDatos.Pedidos.ENTREGADO + " = ? "+" ORDER BY "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos._ID+";", new String[] {String.valueOf(0)});

    }

    protected Cursor listarImporteTotal() {


        return BD.rawQuery("SELECT sum("+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos._ID+","+BaseDatos.Prodctos.NOMBRE_PRODUCTO+","+BaseDatos.Pedidos.CANTIDAD+" FROM " + BaseDatos.PEDIDOS + " JOIN "+BaseDatos.PRODUCTOS+" on "+BaseDatos.PRODUCTOS+"."+BaseDatos.Prodctos._ID+" = "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos.ID_PRODUCTO+" ORDER BY "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos._ID+";", null);

    }

    protected Cursor listarClientes() {

        return BD.query(BaseDatos.PEDIDOS,BaseDatos.Pedidos.COLUMNAS_PEDIDOS, null, null, BaseDatos.Pedidos.NOMBRE_CLIENTE, null, null);
        //return BD.rawQuery("SELECT sum("+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos._ID+","+BaseDatos.Prodctos.NOMBRE_PRODUCTO+","+BaseDatos.Pedidos.CANTIDAD+" FROM " + BaseDatos.PEDIDOS + " JOIN "+BaseDatos.PRODUCTOS+" on "+BaseDatos.PRODUCTOS+"."+BaseDatos.Prodctos._ID+" = "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos.ID_PRODUCTO+" ORDER BY "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos._ID+";", null);

    }
    public List<String> getClientes() {
        Cursor cursor = BD.query(BaseDatos.PEDIDOS,BaseDatos.Pedidos.COLUMNAS_PEDIDOS, null, null, BaseDatos.Pedidos.NOMBRE_CLIENTE, null, null);
        List<String> lista = new ArrayList<String>();
        cursor.moveToFirst();
        lista.add("Todos");
        do {
            lista.add(cursor.getString(2));
        } while (cursor.moveToNext());
        return lista;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String cliente=parent.getItemAtPosition(position).toString();


        Double importe=0.0;
        Double Imp=0.0;
        Integer cantidad=0;


        if(cliente.equals("Todos"))
        {
            adaptadorPedidosPendEntrega = new SimpleCursorAdapter(this, R.layout.listado_pedidos_pend, listarPedidosPendEntrega(), BaseDatos.Pedidos.COLUMNAS_PEDIDOS_PEND, new int[] { R.id.tvIdPedido,R.id.tvtvProdPedido,R.id.tvCantidadProd}, 0);
            lvPedidosPendEntrega.setAdapter(adaptadorPedidosPendEntrega);

            Cursor cursor = ((SimpleCursorAdapter)adaptadorPedidosPendEntrega).getCursor();
            while(cursor.moveToNext())
            {
                int columnaCantidad = cursor.getColumnIndex(BaseDatos.Pedidos.CANTIDAD);
                int columnaPrecio= cursor.getColumnIndex(BaseDatos.Prodctos.PRECIO);

                cantidad=cursor.getInt(columnaCantidad);
                Imp=cursor.getDouble(columnaPrecio);
                //importe=importe+((cursor.getInt(columnaCantidad))*(cursor.getDouble(columnaPrecio)));
                importe=importe+(cantidad*Imp);
            }
            tvImporteTotal.setText("$ "+String.valueOf(importe));
        }
        else {
            listarPedidosPendPorCliente(cliente);

            adaptadorPedidosPendEntrega = new SimpleCursorAdapter(this, R.layout.listado_pedidos_pend, listarPedidosPendPorCliente(cliente), BaseDatos.Pedidos.COLUMNAS_PEDIDOS_PEND, new int[]{R.id.tvIdPedido, R.id.tvtvProdPedido, R.id.tvCantidadProd}, 0);
            lvPedidosPendEntrega.setAdapter(adaptadorPedidosPendEntrega);

            Cursor cursor = ((SimpleCursorAdapter)adaptadorPedidosPendEntrega).getCursor();

            while(cursor.moveToNext())
            {
                int columnaCantidad = cursor.getColumnIndex(BaseDatos.Pedidos.CANTIDAD);
                int columnaPrecio= cursor.getColumnIndex(BaseDatos.Prodctos.PRECIO);
                //importe=importe+((cursor.getInt(columnaCantidad))*(cursor.getDouble(columnaPrecio)));

                cantidad=cursor.getInt(columnaCantidad);
                Imp=cursor.getDouble(columnaPrecio);
                //importe=importe+((cursor.getInt(columnaCantidad))*(cursor.getDouble(columnaPrecio)));
                importe=importe+(cantidad*Imp);
            }
            tvImporteTotal.setText("$ "+String.valueOf(importe));
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        BD.close();

        //  BdHelper.eliminarBaseDatos();
    }


    protected void lvPedidosPendEntregaOnItemClick(AdapterView<?> parent, View view , int position, long id)
    {
        Intent intencionDetallePedido=new Intent(this,DetallePedidoAEntregarActivity.class);
        // ListadoProductosXCategoriaFrag frgListadoProductos=(ListadoProductosXCategoriaFrag)getSupportFragmentManager().findFragmentById(R.id.frgListadoProdXCat);

        //  Bundle datosProducto=new Bundle();
        // categoria= (String)parent.getItemAtPosition(position);

//        nombre =((TextView)view.findViewById(R.id.tvNombreProducto)).getText().toString();
        //categoria=(lvCategorias.getItemAtPosition(position).toString());
        //categoria="Vehículos";


        Cursor cursor = ((SimpleCursorAdapter)adaptadorPedidosPendEntrega).getCursor();
        cursor.moveToPosition(position);

        int columnaId = cursor.getColumnIndex(BaseDatos.Pedidos._ID);
        int columnaNombreCliente = cursor.getColumnIndex(BaseDatos.Pedidos.NOMBRE_CLIENTE);
        int columnaNombreProducto = cursor.getColumnIndex(BaseDatos.Prodctos.NOMBRE_PRODUCTO);
        int columnaCantidad = cursor.getColumnIndex(BaseDatos.Pedidos.CANTIDAD);
        int columnaPrecio= cursor.getColumnIndex(BaseDatos.Prodctos.PRECIO);

        Integer idPedido=(cursor.getInt(columnaId));
        String nombreCliente=(cursor.getString(columnaNombreCliente));
        String nombreProducto=(cursor.getString(columnaNombreProducto));
        Integer cantidad=(cursor.getInt(columnaCantidad));
        Double importe=(cursor.getInt(columnaCantidad))*(cursor.getDouble(columnaPrecio));

        intencionDetallePedido.putExtra(EXTRA_ID,idPedido);
        intencionDetallePedido.putExtra(EXTRA_NOMBRE_CLIENTE,nombreCliente);
        intencionDetallePedido.putExtra(EXTRA_NOMBRE_PRODUCTO,nombreProducto);
        intencionDetallePedido.putExtra(EXTRA_CANTIDAD,cantidad);
        intencionDetallePedido.putExtra(EXTRA_IMPORTR,importe);

        /*if (frgListadoProductos != null) {
            frgListadoProductos.mostrarProductos(empleado);
        } else {*/
        //intencionDetalleProducto.putExtra(EXTRA_NOMBRE, nombre);

        startActivity(intencionDetallePedido);
        // }
    }

}
