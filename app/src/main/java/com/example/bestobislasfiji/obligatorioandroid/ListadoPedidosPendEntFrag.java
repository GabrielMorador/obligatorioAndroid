package com.example.bestobislasfiji.obligatorioandroid;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListadoPedidosPendEntFrag extends Fragment implements AdapterView.OnItemSelectedListener{
    // implements AdapterView.OnItemSelectedListener
    public static ListadoPedidosPendEntFrag newInstance() {
        return new ListadoPedidosPendEntFrag();
    }

    private AdminSQLiteHelper BdHelper;
    private SQLiteDatabase BD;
    private SimpleCursorAdapter adaptadorPedidosPendEntrega;

    protected ListView lvPedidosPendEntrega;
    protected  Spinner spClientes;
    protected TextView tvImporteTotal;


    protected ListView lvProductos;

    protected OnPedidoSeleccionadoListener onPedidoSeleccionadoListener;

    public ListadoPedidosPendEntFrag() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnPedidoSeleccionadoListener) {
            onPedidoSeleccionadoListener = (OnPedidoSeleccionadoListener)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listado_pedidos_pend, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        lvPedidosPendEntrega = (ListView)getView().findViewById(R.id.lvPedidosPendEntrega);
        spClientes = (Spinner)getView().findViewById(R.id.spCliente);
        tvImporteTotal=(TextView)getView().findViewById(R.id.tvImporteTotal);

        BdHelper = new AdminSQLiteHelper(getActivity());
        BD = BdHelper.getWritableDatabase();


        ArrayAdapter<String> adaptadorClientes = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getClientes());
        adaptadorClientes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spClientes.setAdapter(adaptadorClientes);

        spClientes.setOnItemSelectedListener(this);

        /*lvPedidosPendEntrega.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvPedidosPendEntregaOnItemClick(parent, view, position, id);
            }

        });*/

        //adaptadorPedidosPendEntrega = new SimpleCursorAdapter(getActivity(), R.layout.listado_pedidos_pend, listarPedidosPendEntrega(), BaseDatos.Pedidos.COLUMNAS_PEDIDOS_PEND, new int[] { R.id.tvIdPedido,R.id.tvtvProdPedido,R.id.tvCantidadProd}, 0);
        //lvPedidosPendEntrega.setAdapter(adaptadorPedidosPendEntrega);

      /*  Double importe=0.0;
        Double Imp=0.0;
        Integer cantidad=0;


        adaptadorPedidosPendEntrega = new SimpleCursorAdapter(getActivity(), R.layout.listado_pedidos_pend, listarPedidosPendEntrega(), BaseDatos.Pedidos.COLUMNAS_PEDIDOS_PEND, new int[] { R.id.tvIdPedido,R.id.tvtvProdPedido,R.id.tvCantidadProd}, 0);
        lvPedidosPendEntrega.setAdapter(adaptadorPedidosPendEntrega);

        Cursor cursor = ((SimpleCursorAdapter)adaptadorPedidosPendEntrega).getCursor();
        while(cursor.moveToNext())
        {
            int columnaCantidad = cursor.getColumnIndex(BaseDatos.Pedidos.CANTIDAD);
            int columnaPrecio= cursor.getColumnIndex(BaseDatos.Prodctos.PRECIO);

            cantidad=cursor.getInt(columnaCantidad);
            Imp=cursor.getDouble(columnaPrecio);
            importe=importe+(cantidad*Imp);
        }
        tvImporteTotal.setText("$ "+String.valueOf(importe));*/

        lvPedidosPendEntrega.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvPedidosPendEntregaOnItemClick(parent, view, position, id);
            }

        });
    }

    protected Cursor listarPedidosPendPorCliente(String cliente) {


        return BD.rawQuery("SELECT "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos._ID+","+BaseDatos.Pedidos.NOMBRE_CLIENTE+","+BaseDatos.Prodctos.NOMBRE_PRODUCTO+","+BaseDatos.Pedidos.CANTIDAD+
                ","+BaseDatos.Prodctos.PRECIO+" FROM " +
                BaseDatos.PEDIDOS + " JOIN "+BaseDatos.PRODUCTOS+" on "+BaseDatos.PRODUCTOS+"."+BaseDatos.Prodctos._ID+" = "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos.ID_PRODUCTO+" WHERE "+
                BaseDatos.Pedidos.NOMBRE_CLIENTE + " = ? and "+BaseDatos.Pedidos.ENTREGADO + " = ? ;", new String[] {String.valueOf(cliente),String.valueOf(0)});

    }

    protected Cursor listarPedidosPendEntrega() {

        return BD.rawQuery("SELECT "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos._ID+","+BaseDatos.Pedidos.NOMBRE_CLIENTE+","+BaseDatos.Prodctos.NOMBRE_PRODUCTO+","+BaseDatos.Pedidos.CANTIDAD+
                ","+BaseDatos.Prodctos.PRECIO+" FROM " +
                BaseDatos.PEDIDOS + " JOIN "+BaseDatos.PRODUCTOS+" on "+BaseDatos.PRODUCTOS+"."+BaseDatos.Prodctos._ID+" = "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos.ID_PRODUCTO+
                " WHERE "+BaseDatos.Pedidos.ENTREGADO + " = ? "+" ORDER BY "+BaseDatos.PEDIDOS+"."+BaseDatos.Pedidos._ID+";", new String[] {String.valueOf(0)});

    }

    protected Cursor listarClientes() {

        return BD.query(BaseDatos.PEDIDOS,BaseDatos.Pedidos.COLUMNAS_PEDIDOS, null, null, BaseDatos.Pedidos.NOMBRE_CLIENTE, null, null);

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

    protected void lvPedidosPendEntregaOnItemClick(AdapterView<?> parent, View view , int position, long id)
    {
        //Intent intencionDetallePedido=new Intent(getActivity(),DetallePedidoAEntregarActivity.class);

        if (onPedidoSeleccionadoListener != null) {
            Cursor cursor = ((SimpleCursorAdapter) adaptadorPedidosPendEntrega).getCursor();
            cursor.moveToPosition(position);

            int columnaId = cursor.getColumnIndex(BaseDatos.Pedidos._ID);
            int columnaNombreCliente = cursor.getColumnIndex(BaseDatos.Pedidos.NOMBRE_CLIENTE);
            int columnaNombreProducto = cursor.getColumnIndex(BaseDatos.Prodctos.NOMBRE_PRODUCTO);
            int columnaCantidad = cursor.getColumnIndex(BaseDatos.Pedidos.CANTIDAD);
            int columnaPrecio = cursor.getColumnIndex(BaseDatos.Prodctos.PRECIO);

            Integer idPedido = (cursor.getInt(columnaId));
            String nombreCliente = (cursor.getString(columnaNombreCliente));
            String nombreProducto = (cursor.getString(columnaNombreProducto));
            Integer cantidad = (cursor.getInt(columnaCantidad));
            Double importe = (cursor.getInt(columnaCantidad)) * (cursor.getDouble(columnaPrecio));

onPedidoSeleccionadoListener.onPedidoSeleccionado(idPedido,nombreCliente,nombreProducto,cantidad,importe);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String cliente=parent.getItemAtPosition(position).toString();


        Double importe=0.0;
        Double Imp=0.0;
        Integer cantidad=0;


        if(cliente.equals("Todos"))
        {
            adaptadorPedidosPendEntrega = new SimpleCursorAdapter(getActivity(), R.layout.frag_pedidos_pend_entrega, listarPedidosPendEntrega(), BaseDatos.Pedidos.COLUMNAS_PEDIDOS_PEND, new int[] { R.id.tvIdPedido,R.id.tvtvProdPedido,R.id.tvCantidadProd}, 0);
            lvPedidosPendEntrega.setAdapter(adaptadorPedidosPendEntrega);

            Cursor cursor = ((SimpleCursorAdapter)adaptadorPedidosPendEntrega).getCursor();
            while(cursor.moveToNext())
            {
                int columnaCantidad = cursor.getColumnIndex(BaseDatos.Pedidos.CANTIDAD);
                int columnaPrecio= cursor.getColumnIndex(BaseDatos.Prodctos.PRECIO);

                cantidad=cursor.getInt(columnaCantidad);
                Imp=cursor.getDouble(columnaPrecio);
                importe=importe+(cantidad*Imp);
            }
            tvImporteTotal.setText("$ "+String.valueOf(importe));
        }
        else {
            listarPedidosPendPorCliente(cliente);

            adaptadorPedidosPendEntrega = new SimpleCursorAdapter(getActivity(), R.layout.frag_pedidos_pend_entrega, listarPedidosPendPorCliente(cliente), BaseDatos.Pedidos.COLUMNAS_PEDIDOS_PEND, new int[]{R.id.tvIdPedido, R.id.tvtvProdPedido, R.id.tvCantidadProd}, 0);
            lvPedidosPendEntrega.setAdapter(adaptadorPedidosPendEntrega);

            Cursor cursor = ((SimpleCursorAdapter)adaptadorPedidosPendEntrega).getCursor();

            while(cursor.moveToNext())
            {
                int columnaCantidad = cursor.getColumnIndex(BaseDatos.Pedidos.CANTIDAD);
                int columnaPrecio= cursor.getColumnIndex(BaseDatos.Prodctos.PRECIO);

                cantidad=cursor.getInt(columnaCantidad);
                Imp=cursor.getDouble(columnaPrecio);
                importe=importe+(cantidad*Imp);
            }
            tvImporteTotal.setText("$ "+String.valueOf(importe));
        }
    }
   @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface OnPedidoSeleccionadoListener {

        void onPedidoSeleccionado(Integer idPedido,String nombreCliente,String nombreProd,Integer cantidad,Double importe);
    }
}
