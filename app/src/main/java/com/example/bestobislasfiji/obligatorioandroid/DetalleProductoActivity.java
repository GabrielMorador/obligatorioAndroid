package com.example.bestobislasfiji.obligatorioandroid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleProductoActivity extends AppCompatActivity {


    protected ListView lvProductos;

    private AdminSQLiteHelper BdHelper;
    private SQLiteDatabase BD;
    private SimpleCursorAdapter adaptadorProductos;
    protected ListadoCategoriasFragment frgListadoCategorias;

    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precio;

    protected AutoCompleteTextView actCliente;

    protected TextView tvNombreProducto;




    protected EditText etCantidad;
    protected TextView tvPrecio;
    protected TextView tvId;
    protected ImageView imgProducto;
    protected CheckBox chbPagaAdelantado;

    public static final String EXTRA_NOMBRE="EXTRA_NOMBRE";
    //private String nombre;

    protected DetalleProductoFragment frgDetalleProducto;

    //private Empleado empleado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pedido");
        setContentView(R.layout.activity_detalle_producto);

        frgDetalleProducto = (DetalleProductoFragment)getSupportFragmentManager().findFragmentById(R.id.frgDetalleEmpleado);

        BdHelper = new AdminSQLiteHelper(this);
        BD = BdHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        id=extras.getInt(ListadoProductosXCategoriaAct.EXTRA_ID);
        nombre=extras.getString(ListadoProductosXCategoriaAct.EXTRA_NOMBRE);
        descripcion=extras.getString(ListadoProductosXCategoriaAct.EXTRA_DESCRIPCION);
        precio=extras.getDouble(ListadoProductosXCategoriaAct.EXTRA_PRECIO);



        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        actCliente = (AutoCompleteTextView)findViewById(R.id.actCliente);
        etCantidad = (EditText) findViewById(R.id.etCantidad);
        tvPrecio = (TextView)findViewById(R.id.tvPrecio);
        imgProducto=(ImageView)findViewById(R.id.imgProducto);
        tvPrecio = (TextView)findViewById(R.id.tvPrecio);
        tvId=(TextView)findViewById(R.id.tvIdProd);
        chbPagaAdelantado=(CheckBox)findViewById(R.id.chbPagoAdelantado);


    }

    @Override
    protected void onStart() {
        super.onStart();

        frgDetalleProducto.mostrarDetalleProducto(id,nombre,descripcion,precio);
    }

    public void btnRealizarPedidoOnClick(View view) {

        ContentValues valores = new ContentValues();

       // BD.beginTransaction();
//_ID,ID_PAGO,PAGO_ADELANTADO,NOMBRE_CLIENTE,CANTIDAD,ENTREGADO,ID_PRODUCTO
        try {

Intent volverAListaCategorias=new Intent(this,ListadoCategoriasActivity.class);
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

          //  BD.setTransactionSuccessful();
            Toast.makeText(this, "Pedido realizado exitasmente.", Toast.LENGTH_LONG).show();
            startActivity(volverAListaCategorias);
            finish();
        } catch (Exception ex) {

            Toast.makeText(this, "No se ha podido realizar el pedido :(", Toast.LENGTH_LONG).show();
        } /*finally {
            BD.endTransaction();
        }*/
    }
}
