package com.example.bestobislasfiji.obligatorioandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetalleProductoFragment extends Fragment {

    public static DetalleProductoFragment newInstance() {
        return new DetalleProductoFragment();
    }


    protected TextView tvNombreProd;
    protected TextView tvDescripcion;
    protected TextView tvPrecio;
    protected TextView tvIdProd;
    protected ImageView imgProducto;

    public DetalleProductoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_detalle_producto, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvNombreProd = (TextView)getView().findViewById(R.id.tvNombreProd);
        tvDescripcion = (TextView)getView().findViewById(R.id.tvDescripcion);
        tvPrecio = (TextView)getView().findViewById(R.id.tvPrecio);
        imgProducto=(ImageView)getView().findViewById(R.id.imgProducto);
        tvPrecio = (TextView)getView().findViewById(R.id.tvPrecio);
        tvIdProd=(TextView)getView().findViewById(R.id.tvIdProd);
    }

    public void mostrarDetalleProducto(Integer id,String nombre,String descripcion,Double precio) {
        tvNombreProd.setText(nombre);
        tvDescripcion.setText(descripcion);
        tvPrecio.setText(precio.toString());
        tvIdProd.setText(id.toString());

        if(nombre.equals("Toyota AE86"))
        {
            imgProducto.setImageResource(R.drawable.trueno);
        }
        else if(nombre.equals("Subaru Impreza"))
        {
            imgProducto.setImageResource(R.drawable.impreza);
        }
        else if(nombre.equals("BMW M3"))
        {
            imgProducto.setImageResource(R.drawable.m3);
        }
        else if(nombre.equals("Ford Focus"))
        {
            imgProducto.setImageResource(R.drawable.focus);
        }
        else if(nombre.equals("Canción de hielo y fuego"))
        {
            imgProducto.setImageResource(R.drawable.cancion_de_hielo_y_fuego);
        }
        else if(nombre.equals("Harry potter y la piedra filosofal"))
        {
            imgProducto.setImageResource(R.drawable.harry);
        }else if(nombre.equals("Inferno"))
        {
            imgProducto.setImageResource(R.drawable.inferno);
        }else if(nombre.equals("Huawei Mate 20 Pro"))
        {
            imgProducto.setImageResource(R.drawable.mate20);
        }
        else if(nombre.equals("Samsung Galaxy S9 Plus"))
        {
            imgProducto.setImageResource(R.drawable.galaxy_s9);
        }
        else if(nombre.equals("iPhone Xs"))
        {
            imgProducto.setImageResource(R.drawable.iphone);
        }


    }
}
