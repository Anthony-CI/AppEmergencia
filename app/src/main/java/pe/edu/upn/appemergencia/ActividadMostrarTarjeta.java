package pe.edu.upn.appemergencia;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pe.edu.upn.appemergencia.Access.BDOpenHelper;
import pe.edu.upn.appemergencia.Access.DAOEmergenciaBD;
import pe.edu.upn.appemergencia.Models.EmergenciaBD;

public class ActividadMostrarTarjeta extends AppCompatActivity {

    private RecyclerView rvListaEmergencia;
    private AdaptadorEmergencia oAdaptador;
    private List<EmergenciaBD> listaEmergencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ly_mostrar_tarjeta);
        Toolbar toolbar = findViewById(R.id.tbMostrar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rvListaEmergencia = findViewById(R.id.rvListaEmergencia);
        DAOEmergenciaBD oBD= new DAOEmergenciaBD(this);
        listaEmergencia = oBD.getListadoEmergencia();
        rvListaEmergencia.setLayoutManager(new LinearLayoutManager(this));
        oAdaptador = new AdaptadorEmergencia(this,listaEmergencia);
        rvListaEmergencia.setAdapter(oAdaptador);
    }
}