package com.example.bestobislasfiji.obligatorioandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListadoProductosXCategoriaAct extends AppCompatActivity implements ListadoProductosXCategoriaFrag.OnProductoSeleccionadoListener{


    protected ListView lvProductos;

    protected ListadoCategoriasFragment frgListadoCategorias;


    protected ImageView imgProducto;
    protected TextView tvNombreProducto;
    protected TextView tvPrecio;

    public static final String EXTRA_ID="EXTRA_ID";
    public static final String EXTRA_NOMBRE="EXTRA_NOMBRE";
    public static final String EXTRA_DESCRIPCION="EXTRA_DESCRIPCION";
    public static final String EXTRA_PRECIO="EXTRA_PRECIO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acti_listado_prod_x_cat_layout);
        }


    @Override
    public void onProductoSeleccionado(Integer id,String nombre,String descripcion,Double precio) {
        DetalleProductoFragment frgDetalleProducto = (DetalleProductoFragment)getSupportFragmentManager().findFragmentById(R.id.frgDetalleProducto);

        if (frgDetalleProducto != null) {
            frgDetalleProducto.mostrarDetalleProducto(id,nombre,descripcion,precio);
        } else {
            Intent intencionDetalleProducto= new Intent(this, DetalleProductoActivity.class);
            intencionDetalleProducto.putExtra(EXTRA_ID,id);
            intencionDetalleProducto.putExtra(EXTRA_NOMBRE,nombre);
            intencionDetalleProducto.putExtra(EXTRA_DESCRIPCION,descripcion);
            intencionDetalleProducto.putExtra(EXTRA_PRECIO,precio);

            startActivity(intencionDetalleProducto);
        }
    }




}
