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
    //para juardar los archivos txt
    private SharedPreferences oFlujo=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Configurar nuestro toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //llmamos al txview
        TextView lbMensajePrincipal =findViewById(R.id.lbMensajePrincipal);
        //GUARDAR LOS ARCHIVOS EN XML
        oFlujo = getSharedPreferences("control", Context.MODE_PRIVATE);
        contador = oFlujo.getInt("contador", 1);
        lbMensajePrincipal.setText("N° veces acceso: " + contador);



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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isFinishing()){ //si se cierra la catividad el contador se actualiza
            contador++;
            SharedPreferences.Editor oEditor= oFlujo.edit(); //vavos a cambiar el valor de la variable
            oEditor.putInt("contador",contador); // "contador" no nesesariamente tiene que tener el mismo nombre
            oEditor.commit();//guarda los cambios
            oEditor.clear();//para limpiar
        }
    }
}