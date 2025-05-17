package pe.edu.upn.appemergencia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private int contador=0;
    private SharedPreferences oflujo=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Configurar nuestro toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView lbMensajePrincipal = findViewById(R.id.lbMensajePrincipal);
        //Guardar los datos en un archivo XML
        oflujo = getSharedPreferences("control", Context.MODE_PRIVATE);
        contador = oflujo.getInt("contador",1);
        lbMensajePrincipal.setText("Nª veces acceso: "+contador);


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
                //oIntento = new Intent(this, ActividadMostrar.class);
                oIntento = new Intent(this, ActividadMostrarTarjeta.class);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isFinishing()){ // si se cierra la actividad
            contador++;
            SharedPreferences.Editor oEditar = oflujo.edit();
            oEditar.putInt("contador",contador);
            oEditar.commit();//Guarda los cambios
            oEditar.clear();
        }
    }
}