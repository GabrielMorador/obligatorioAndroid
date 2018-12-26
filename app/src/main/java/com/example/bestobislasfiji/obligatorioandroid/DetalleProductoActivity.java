package com.example.bestobislasfiji.obligatorioandroid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DetalleProductoActivity extends AppCompatActivity {

    protected ListView lvProductos;


    protected ListadoCategoriasFragment frgListadoCategorias;

    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precio;

    protected AutoCompleteTextView actCliente;

    protected TextView tvNombreProducto;

    protected EditText etCantidad;
    protected TextView tvPrecio;
    protected ImageView imgProducto;

    protected Button btnRealizarPedido;


    protected DetalleProductoFragment frgDetalleProducto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pedido");
        setContentView(R.layout.activity_detalle_producto);

        frgDetalleProducto = (DetalleProductoFragment)getSupportFragmentManager().findFragmentById(R.id.frgDetalleProducto);


        Bundle extras = getIntent().getExtras();
        id=extras.getInt(ListadoProductosXCategoriaAct.EXTRA_ID);
        nombre=extras.getString(ListadoProductosXCategoriaAct.EXTRA_NOMBRE);
        descripcion=extras.getString(ListadoProductosXCategoriaAct.EXTRA_DESCRIPCION);
        precio=extras.getDouble(ListadoProductosXCategoriaAct.EXTRA_PRECIO);


    }

    @Override
    protected void onStart() {
        super.onStart();

        frgDetalleProducto.mostrarDetalleProducto(id,nombre,descripcion,precio);
    }


}
