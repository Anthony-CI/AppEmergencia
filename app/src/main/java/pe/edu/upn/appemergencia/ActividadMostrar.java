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
import pe.edu.upn.appemergencia.Access.DAOEmergenciaBD;
import pe.edu.upn.appemergencia.Models.Emergencia;
import pe.edu.upn.appemergencia.Models.EmergenciaBD;

public class ActividadMostrar extends AppCompatActivity {
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.ly_mostrar);
        Toolbar toolbar = findViewById(R.id.tbMostrar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lista = findViewById(R.id.lvListaEmergencia);
        /*lista.setAdapter(new ArrayAdapter<Emergencia>(this,
                android.R.layout.simple_list_item_1,
                DAOEmergencia.getInstancia().mostrarEmergencia()));*/
        DAOEmergenciaBD oBD = new DAOEmergenciaBD(this);
        lista.setAdapter(new ArrayAdapter<EmergenciaBD>(this,
                android.R.layout.simple_list_item_1,
                oBD.getListadoEmergencia()));
    }
}