package es.upm.btb.clientedb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import es.upm.btb.clientedb.models.Cliente;
import es.upm.btb.clientedb.models.RepoClienteSQLiteOpenHelper;
import es.upm.btb.clientedb.views.ClienteAdapter;

public class MainActivity extends AppCompatActivity {

    static final String LOG_TAG = "btb";
    static final String KEY_CLIENTE = "Clave_Cliente";

    RepoClienteSQLiteOpenHelper db;
    List<Cliente> clientes;

    TextView tvCabecera;
    ListView lvClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvCabecera = findViewById(R.id.tvCabecera);
        lvClientes = findViewById(R.id.lvListadoClientes);

        db = new RepoClienteSQLiteOpenHelper(getApplicationContext());
        long numElementos = db.count();
        Log.i(LOG_TAG, "Número elementos = " + numElementos);

        clientes = db.getAll();
        Log.i(LOG_TAG, "Clientes = " + clientes);

        final ClienteAdapter clienteAdapter = new ClienteAdapter(
                getApplicationContext(),
                R.layout.layout_listado_clientes,
                clientes
        );
        lvClientes.setAdapter(clienteAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id = db.add("C1", "1234567X", 11111, "c1@xyz.com", true);
                Log.i(LOG_TAG, "Nuevo Número cliente = " + id);
                Log.i(LOG_TAG, "Número elementos = " + db.count());
                clienteAdapter.add(db.get((int) id));

                Snackbar.make(view, "Añadido cliente " + id, Snackbar.LENGTH_SHORT)
                        .show();
            }
        });

        // Listener del listado de clientes
        lvClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tvId = (TextView) ((LinearLayout) view).getChildAt(0); // obtiene tv con id del cliente
                long numCliente = Integer.parseInt(tvId.getText().toString());
                Log.d(LOG_TAG, "PRINCIPAL pos= " + position + ", numCliente=" + numCliente);

                Intent intent = new Intent(MainActivity.this, MostrarClienteActivity.class);
                intent.putExtra(KEY_CLIENTE, clientes.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
