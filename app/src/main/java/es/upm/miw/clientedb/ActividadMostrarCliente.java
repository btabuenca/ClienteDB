package es.upm.miw.clientedb;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import es.upm.miw.clientedb.databinding.ActivityDetalleItemBinding;
import es.upm.miw.clientedb.models.Cliente;

public class ActividadMostrarCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_detalle_item);

        // Data binding
        ActivityDetalleItemBinding binding =
                DataBindingUtil.setContentView(
                        this,
                        R.layout.activity_detalle_item
                );

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Recuperar entidad
        Cliente cliente = getIntent().getParcelableExtra(MainActivity.KEY_CLIENTE);
        Log.i(MainActivity.LOG_TAG, cliente.toString());

        binding.setMainCliente(cliente);
    }
}
