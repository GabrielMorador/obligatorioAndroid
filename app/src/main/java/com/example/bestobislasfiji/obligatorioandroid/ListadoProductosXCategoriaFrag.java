package com.example.bestobislasfiji.obligatorioandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListadoProductosXCategoriaFrag extends Fragment {


  /*  public static ListadoProductosXCategoriaFrag newInstance() {
        return new ListadoProductosXCategoriaFrag();
    }


    protected TextView tvNombreProducto;
    protected TextView tvPrecio;
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_listado_prod_x_cat_layout, container, false);
    }

/*    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvNombreProducto = (TextView)getView().findViewById(R.id.tvNombreProducto);
        tvPrecio = (TextView)getView().findViewById(R.id.tvPrecio);

    }

    public void mostrarProductos(String nombre,Long precio) {
        tvNombreProducto.setText(nombre);
        tvPrecio.setText(precio.toString());

    }*/
}
