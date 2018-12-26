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
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DetalleProductoFragment extends Fragment {

    public static DetalleProductoFragment newInstance() {
        return new DetalleProductoFragment();
    }

    private AdminSQLiteHelper BdHelper;
    private SQLiteDatabase BD;

    protected TextView tvNombreProd;
    protected TextView tvDescripcion;
    protected TextView tvPrecio;
    protected TextView tvIdProd;
    protected ImageView imgProducto;

    /*labels*/
    protected RelativeLayout rlDetalleProducto;
    protected ScrollView cosoScroll;


    protected AutoCompleteTextView actCliente;
    protected EditText etCantidad;
    protected TextView tvId;
    protected CheckBox chbPagaAdelantado;
    protected Button btnRealizarPedido;

    public DetalleProductoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_detalle_producto, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        BdHelper = new AdminSQLiteHelper(getActivity());
        BD = BdHelper.getWritableDatabase();

        tvNombreProd = (TextView)getView().findViewById(R.id.tvNombreProd);
        tvDescripcion = (TextView)getView().findViewById(R.id.tvDescripcion);
        tvPrecio = (TextView)getView().findViewById(R.id.tvPrecio);
        imgProducto=(ImageView)getView().findViewById(R.id.imgProducto);
        tvPrecio = (TextView)getView().findViewById(R.id.tvPrecio);
        tvIdProd=(TextView)getView().findViewById(R.id.tvIdProd);
        cosoScroll=(ScrollView)getView().findViewById(R.id.cosoScroll);

        rlDetalleProducto=(RelativeLayout)getView().findViewById(R.id.rlDetalleProducto);

        btnRealizarPedido=(Button)getView().findViewById(R.id.btnRealizarPedido);
        //btnRealizarPedido.setOnClickListener(new View.OnClickListener());

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        actCliente = (AutoCompleteTextView)getView().findViewById(R.id.actCliente);
        etCantidad = (EditText)getView().findViewById(R.id.etCantidad);
        tvId=(TextView)getView().findViewById(R.id.tvIdProd);
        chbPagaAdelantado=(CheckBox)getView().findViewById(R.id.chbPagoAdelantado);

       // btnRealizarPedido=(Button)findViewById(R.id.btnRealizarPedido);

        btnRealizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues valores = new ContentValues();


                try {

                    Intent volverAListaCategorias=new Intent(getActivity(),ListadoCategoriasActivity.class);
                    valores.put(BaseDatos.Pedidos.ID_PRODUCTO, Integer.parseInt(tvId.getText().toString()));
                    valores.put(BaseDatos.Pedidos.CANTIDAD, Integer.parseInt(etCantidad.getText().toString()));
                    valores.put(BaseDatos.Pedidos.NOMBRE_CLIENTE,actCliente.getText().toString());
                    if(chbPagaAdelantado.isChecked()) {
                        valores.put(BaseDatos.Pedidos.PAGO_ADELANTADO, 1);
                    }
                    else
                    {
                        valores.put(BaseDatos.Pedidos.PAGO_ADELANTADO, 0);
                    }
                    BD.insert(BaseDatos.PEDIDOS, null, valores);


                    Toast.makeText(getActivity(), "Pedido realizado exitasmente.", Toast.LENGTH_LONG).show();
                    startActivity(volverAListaCategorias);
                    getActivity().finish();
                } catch (Exception ex) {

                    Toast.makeText(getActivity(), "No se ha podido realizar el pedido :(", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void mostrarDetalleProducto(Integer id,String nombre,String descripcion,Double precio) {
        cosoScroll.setVisibility(View.VISIBLE);
        tvNombreProd.setText(nombre);
        tvDescripcion.setText(descripcion);
        tvPrecio.setText("$ "+precio.toString());
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
