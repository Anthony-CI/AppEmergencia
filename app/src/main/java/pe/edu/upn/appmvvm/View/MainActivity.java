package pe.edu.upn.appmvvm.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import pe.edu.upn.appmvvm.R;
import pe.edu.upn.appmvvm.ViewModel.EmergenciaViewModel;

public class MainActivity extends AppCompatActivity {

    EditText txtUbicacion,txtDescripcion;
    Spinner spTipoEmergencia;
    Button btnEnviar;

    TextView lbRespuesta;
    private EmergenciaViewModel oViewModelE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        oViewModelE = new ViewModelProvider(this).get(EmergenciaViewModel.class);
        lbRespuesta = findViewById(R.id.lbresultado);
        spTipoEmergencia = findViewById(R.id.sptipoemergencia);
        txtUbicacion = findViewById(R.id.txtLocalidad);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        btnEnviar = findViewById(R.id.btnRegistrar);
        //mostrar tipos de emergencia en la lista
        String [] tipos = {"Accidente", "Robo", "Sismo"};
        spTipoEmergencia.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tipos));
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tipoEmergencia = spTipoEmergencia.getSelectedItem().toString();
                String ubicacion = txtUbicacion.getText().toString();
                String descripcion = txtDescripcion.getText().toString();
                oViewModelE.enviarRespuesta(tipoEmergencia,ubicacion,descripcion);

            }
        });

        oViewModelE.getResultado().observe(this,result ->{
            lbRespuesta.setText(result);
        });


    }
}