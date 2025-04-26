package pe.edu.upn.appemergencia;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Configurar nuestro toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView botonNavegacion =findViewById(R.id.btnNavegacion);
        botonNavegacion.setOnItemSelectedListener(item -> {
            Intent oIntento = null;
            if(item.getItemId()==R.id.itemInicio){
                return true;
            }
            if(item.getItemId()==R.id.itemRegistrar){
                oIntento = new Intent(this, ActividadRegistrar.class);
                startActivity(oIntento);
                return true;
            }
            if(item.getItemId()==R.id.itemListar){
                oIntento = new Intent(this, ActivityMostrar.class);
                startActivity(oIntento);
                return true;
            }

            if(item.getItemId()==R.id.itemSalir){
                finish();
                return true;
            }
            return false;
        });
    }
}