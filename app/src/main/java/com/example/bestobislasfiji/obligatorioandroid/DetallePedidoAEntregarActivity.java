package com.example.bestobislasfiji.obligatorioandroid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class DetallePedidoAEntregarActivity extends AppCompatActivity {

    protected DetallePedidoAEntregarFragment frgDetallePedidoAEntregar;

    private AdminSQLiteHelper BdHelper;
    private SQLiteDatabase BD;

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

        BdHelper = new AdminSQLiteHelper(this);
        BD = BdHelper.getWritableDatabase();

        chbEntregarPedido=(CheckBox)findViewById(R.id.chbEntregarPedido);


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

    public void btnRealizarEntregaPedidoOnClick(View view) {

        ContentValues valores = new ContentValues();

        // BD.beginTransaction();
//_ID,ID_PAGO,PAGO_ADELANTADO,NOMBRE_CLIENTE,CANTIDAD,ENTREGADO,ID_PRODUCTO
        try {
            Intent volverAPedidosAEntregars=new Intent(this,ListadoPedidosPendEntAct.class);
            if(chbEntregarPedido.isChecked()) {
                valores.put(BaseDatos.Pedidos.ENTREGADO, 1);
                BD.update(BaseDatos.PEDIDOS,valores,BaseDatos.Pedidos._ID + " = ?",new String[] {String.valueOf(idPedido)});
                Toast.makeText(this, "Pedido entregado exitasmente.", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this, "Pedido no se ha entregado por no marcar el CheckBox de entregado.", Toast.LENGTH_LONG).show();
            }


            //  BD.setTransactionSuccessful();

            startActivity(volverAPedidosAEntregars);
            finish();
        } catch (Exception ex) {

            Toast.makeText(this, "No se ha podido entregar el pedido :(", Toast.LENGTH_LONG).show();
        } /*finally {
            BD.endTransaction();
        }*/
    }
}
