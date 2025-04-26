package pe.edu.upn.appemergencia;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import pe.edu.upn.appemergencia.Access.DAOEmergencia;
import pe.edu.upn.appemergencia.Model.Emergencia;

public class ActivityMostrar extends AppCompatActivity {
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.lymostrar);
        Toolbar toolbar = findViewById(R.id.tbMostrar);
        setSupportActionBar(toolbar);
        //se modifica el archivo manifieto para mostrar la fecha de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lista = findViewById(R.id.lvListaEmergencia);
        lista.setAdapter(new ArrayAdapter<Emergencia>(this,
                android.R.layout.simple_list_item_1,
                DAOEmergencia.getInstacia().mostrarEmergencia()));
    }
}