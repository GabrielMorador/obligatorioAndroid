package com.example.bestobislasfiji.obligatorioandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetallePedidoAEntregarFragment extends Fragment {

    public static DetallePedidoAEntregarFragment newInstance() {
        return new DetallePedidoAEntregarFragment();
    }


    protected TextView tvIdPed;
    protected TextView tvNombreCliente;
    protected TextView tvNomProd;
    protected TextView tvCantidad;
    protected TextView tvImporteTotal;


    public DetallePedidoAEntregarFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_detalle_pedido_a_entregar, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvIdPed = (TextView)getView().findViewById(R.id.tvIdPed);
        tvNombreCliente = (TextView)getView().findViewById(R.id.tvNombreCliente);
        tvNomProd = (TextView)getView().findViewById(R.id.tvNomProd);
        tvCantidad = (TextView)getView().findViewById(R.id.tvCantidad);
        tvImporteTotal = (TextView)getView().findViewById(R.id.tvImporteTotal);
    }

    public void mostrarPedido(Integer idPedido,String nombreCliente,String nombreProd,Integer cantidad,Double importe ) {
        tvIdPed.setText(String.valueOf(idPedido));
        tvNombreCliente.setText(nombreCliente);
        tvNomProd.setText(nombreProd);
        tvCantidad.setText(String.valueOf(cantidad));
        tvImporteTotal.setText("$ "+String.valueOf(importe));
    }
}
