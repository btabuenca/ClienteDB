package es.upm.miw.clientedb.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import es.upm.miw.clientedb.R;
import es.upm.miw.clientedb.models.Cliente;

public class ClienteAdapter extends ArrayAdapter {

    private Context _contexto;
    private int _idLayout;
    private List<Cliente> _clientes;

    /**
     * Constructor
     * @param contexto   Contexto
     * @param clientes  Datos a representar
     */
    public ClienteAdapter(Context contexto, int idLayout, List<Cliente> clientes) {
        super(contexto, idLayout, clientes);
        this._contexto = contexto;
        this._clientes = clientes;
        this._idLayout = idLayout;
        setNotifyOnChange(true);
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView
     * @param parent
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) _contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this._idLayout, null);
        }

        Cliente cliente = _clientes.get(position);
        if (cliente != null) {
            TextView  tvId     = convertView.findViewById(R.id.tvListadoClienteId);
            TextView  tvNombre = convertView.findViewById(R.id.tvListadoClienteNombre);
            TextView  tvDorsal = convertView.findViewById(R.id.tvListadoClienteDNI);
            ImageView ivActivo = convertView.findViewById(R.id.ivListadoClienteVerificado);

            tvId.setText(Long.toString(cliente.getId()));
            tvNombre.setText(cliente.getNombre());
            tvDorsal.setText(cliente.getDni());

            ivActivo.setImageResource(
                    cliente.isVerificado()
                            ? android.R.drawable.checkbox_on_background
                            : android.R.drawable.checkbox_off_background
            );
        }

        return convertView;
    }
}
