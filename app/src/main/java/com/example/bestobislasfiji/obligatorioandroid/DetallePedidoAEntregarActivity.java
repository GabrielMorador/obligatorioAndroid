package com.example.bestobislasfiji.obligatorioandroid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class DetallePedidoAEntregarActivity extends AppCompatActivity{

    protected DetallePedidoAEntregarFragment frgDetallePedidoAEntregar;



    private Integer idPedido;
    private String nombreCliente;
    private String nombreProducto;
    private Integer cantidad;
    private Double importe;



    private CheckBox chbEntregarPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_detalle_pend_a_entregar);

        frgDetallePedidoAEntregar = (DetallePedidoAEntregarFragment)getSupportFragmentManager().findFragmentById(R.id.frgDetallePedidosAEntregar);




        Bundle extras = getIntent().getExtras();
        idPedido=(Integer)extras.getInt(ListadoPedidosPendEntAct.EXTRA_ID);
        nombreCliente=(String) extras.getString(ListadoPedidosPendEntAct.EXTRA_NOMBRE_CLIENTE);
        nombreProducto=(String)extras.getString(ListadoPedidosPendEntAct.EXTRA_NOMBRE_PRODUCTO);
        cantidad=(Integer)extras.getInt(ListadoPedidosPendEntAct.EXTRA_CANTIDAD);
        importe=(Double)extras.getDouble(ListadoPedidosPendEntAct.EXTRA_IMPORTR);


    }

    @Override
    protected void onStart() {
        super.onStart();

        frgDetallePedidoAEntregar.mostrarPedido(idPedido,nombreCliente,nombreProducto,cantidad,importe);
    }


}
