package com.example.bestobislasfiji.obligatorioandroid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;



public class ListadoPedidosPendEntAct extends AppCompatActivity implements ListadoPedidosPendEntFrag.OnPedidoSeleccionadoListener{


    public static final String EXTRA_ID="EXTRA_ID";
    public static final String EXTRA_NOMBRE_CLIENTE="EXTRA_NOMBRE_CLIENTE";
    public static final String EXTRA_NOMBRE_PRODUCTO="EXTRA_NOMBRE_PRODUCTO";
    public static final String EXTRA_CANTIDAD="EXTRA_CANTIDAD";
    public static final String EXTRA_IMPORTR="EXTRA_IMPORTR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.act_listado_pedidos_pend);

    }


    public void onPedidoSeleccionado(Integer idPedido,String nombreCliente,String nombreProd,Integer cantidad,Double importe ) {
        DetallePedidoAEntregarFragment frgDetallePedido = (DetallePedidoAEntregarFragment)getSupportFragmentManager().findFragmentById(R.id.frgDetallePedidosAEntregar);

        if (frgDetallePedido != null) {
            frgDetallePedido.mostrarPedido(idPedido,nombreCliente,nombreProd,cantidad,importe );

        } else {
            Intent intencionDetallePedido= new Intent(this, DetallePedidoAEntregarActivity.class);
            intencionDetallePedido.putExtra(EXTRA_ID,idPedido);
            intencionDetallePedido.putExtra(EXTRA_NOMBRE_CLIENTE,nombreCliente);
            intencionDetallePedido.putExtra(EXTRA_NOMBRE_PRODUCTO,nombreProd);
            intencionDetallePedido.putExtra(EXTRA_CANTIDAD,cantidad);
            intencionDetallePedido.putExtra(EXTRA_IMPORTR,importe);

            startActivity(intencionDetallePedido);
        }
    }

}
