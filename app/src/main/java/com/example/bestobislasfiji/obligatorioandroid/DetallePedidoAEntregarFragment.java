package com.example.bestobislasfiji.obligatorioandroid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class DetallePedidoAEntregarFragment extends Fragment {

    public static DetallePedidoAEntregarFragment newInstance() {
        return new DetallePedidoAEntregarFragment();
    }

    private AdminSQLiteHelper BdHelper;
    private SQLiteDatabase BD;

    protected TextView tvIdPed;
    protected TextView tvNombreCliente;
    protected TextView tvNomProd;
    protected TextView tvCantidad;
    protected TextView tvImporteTotal;
    protected Button btnRealizarEntregaPedidoOnClick;
    protected CheckBox chbEntregarPedido;

    protected ScrollView cosoScroll;


    public DetallePedidoAEntregarFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_detalle_pedido_a_entregar, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        BdHelper = new AdminSQLiteHelper(getActivity());
        BD = BdHelper.getWritableDatabase();

        tvIdPed = (TextView)getView().findViewById(R.id.tvIdPed);
        tvNombreCliente = (TextView)getView().findViewById(R.id.tvNombreCliente);
        tvNomProd = (TextView)getView().findViewById(R.id.tvNomProd);
        tvCantidad = (TextView)getView().findViewById(R.id.tvCantidad);
        tvImporteTotal = (TextView)getView().findViewById(R.id.tvImporteTotal);
        cosoScroll=(ScrollView)getView().findViewById(R.id.cosoScroll);

        chbEntregarPedido=(CheckBox)getView().findViewById(R.id.chbEntregarPedido);
        btnRealizarEntregaPedidoOnClick=(Button)getView().findViewById(R.id.btnRealizarEntregaPedido);

        btnRealizarEntregaPedidoOnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues valores = new ContentValues();

                try {
                    Intent volverAPedidosAEntregars = new Intent(getActivity(), ListadoPedidosPendEntAct.class);
                    if (chbEntregarPedido.isChecked()) {
                        if(tvIdPed.getText().toString()!="") {
                            valores.put(BaseDatos.Pedidos.ENTREGADO, 1);
                            BD.update(BaseDatos.PEDIDOS, valores, BaseDatos.Pedidos._ID + " = ?", new String[]{String.valueOf(tvIdPed.getText())});
                            Toast.makeText(getActivity(), "Pedido entregado exitasmente.", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Por favor seleccione un pedido.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Pedido no se ha entregado por no marcar el CheckBox de entregado.", Toast.LENGTH_LONG).show();
                    }

                    startActivity(volverAPedidosAEntregars);
                    getActivity().finish();
                } catch (Exception ex) {

                    Toast.makeText(getActivity(), "No se ha podido entregar el pedido :(", Toast.LENGTH_LONG).show();
                }
            }

        });
    }


    public void mostrarPedido(Integer idPedido,String nombreCliente,String nombreProd,Integer cantidad,Double importe ) {
        cosoScroll.setVisibility(View.VISIBLE);
        tvIdPed.setText(String.valueOf(idPedido));
        tvNombreCliente.setText(nombreCliente);
        tvNomProd.setText(nombreProd);
        tvCantidad.setText(String.valueOf(cantidad));
        tvImporteTotal.setText("$ "+String.valueOf(importe));
    }
}
