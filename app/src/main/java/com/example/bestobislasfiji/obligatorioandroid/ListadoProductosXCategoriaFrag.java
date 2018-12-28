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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ListadoProductosXCategoriaFrag extends Fragment {

    private AdminSQLiteHelper BdHelper;
    private SQLiteDatabase BD;
    private SimpleCursorAdapter adaptadorProductos;

    private String categoria;

    public static ListadoProductosXCategoriaFrag newInstance() {
        return new ListadoProductosXCategoriaFrag();
    }


    protected ListView lvProductos;

    protected OnProductoSeleccionadoListener onProductoSeleccionadoListener;


    public ListadoProductosXCategoriaFrag() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnProductoSeleccionadoListener) {
            onProductoSeleccionadoListener = (OnProductoSeleccionadoListener)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listado_prod_x_cat, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lvProductos = (ListView)getView().findViewById(R.id.lvProductos);

        BdHelper = new AdminSQLiteHelper(getActivity());
        BD = BdHelper.getReadableDatabase();

        Intent i=getActivity().getIntent();
        categoria=i.getStringExtra("EXTRA_CATEGORIA");
        getActivity().setTitle(categoria);

        adaptadorProductos = new SimpleCursorAdapter(getActivity(), R.layout.frag_listado_prod_x_cat_layout, listarProdXCategoriaCategorias(categoria), BaseDatos.Prodctos.PRODUCTOS_LIST, new int[] { R.id.tvNombreProducto}, 0);
        lvProductos.setAdapter(adaptadorProductos);


        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvProductosOnItemClick(parent, view, position, id);
            }

        });
    }



    @Override
    public void onDetach() {
        super.onDetach();

        onProductoSeleccionadoListener = null;
    }


    protected Cursor listarProdXCategoriaCategorias(String categoria) {

        return BD.query(BaseDatos.PRODUCTOS,BaseDatos.Prodctos.COLUMNAS_PRODUCTOS, BaseDatos.Prodctos.CATEGORIA+"=?", new String [] {categoria}, null, null, null);
    }

    protected void lvProductosOnItemClick(AdapterView<?> parent, View view , int position, long id)
    {
        if (onProductoSeleccionadoListener != null) {

            Cursor listProductos=(Cursor)parent.getItemAtPosition(position);
            if(listProductos.moveToPosition(position)) {


                int columnaId = listProductos.getColumnIndex(BaseDatos.Prodctos._ID);
                int columnaNombre = listProductos.getColumnIndex(BaseDatos.Prodctos.NOMBRE_PRODUCTO);
                int columnaDescripcion = listProductos.getColumnIndex(BaseDatos.Prodctos.DESCRIPCION);
                int columnaPrecio = listProductos.getColumnIndex(BaseDatos.Prodctos.PRECIO);

                Integer idProd=(listProductos.getInt(columnaId));
                String nombre=(listProductos.getString(columnaNombre));
                String descripcion=(listProductos.getString(columnaDescripcion));
                Double precio=(listProductos.getDouble(columnaPrecio));

                onProductoSeleccionadoListener.onProductoSeleccionado(idProd,nombre,descripcion,precio);
            }

        }
    }

    public interface OnProductoSeleccionadoListener {

        void onProductoSeleccionado(Integer id,String nombre,String descripcion,Double precio);
    }


}
