package pe.edu.upn.appemergencia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputEditText;

import pe.edu.upn.appemergencia.Access.DAOEmergencia;
import pe.edu.upn.appemergencia.Model.Emergencia;

public class ActividadRegistrar extends AppCompatActivity {

    private TextInputEditText txtDescripcion;
    private AutoCompleteTextView txtTipoEmergencia;
    private MaterialRadioButton rBaja,rbmedia,rbAlta;
    private MaterialCheckBox cbxUrgente;
    private ImageView imgFoto;
    private Uri uriFoto=null; //almacena la ruta de la imagen
    private String[] vTipoEmergencia = {"Accidente","Terremoto","Sunami"};
    private MaterialButton btnSeleccionarFoto;
    //actividad sistema de galeria
    private ActivityResultLauncher<Intent> irActividaGaleriaFoto;
    private FloatingActionButton btnGrabar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ly_registrar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //relacionar con interfce
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtTipoEmergencia = findViewById(R.id.spListaTipoEmergencia);
        rBaja = findViewById(R.id.rbBaja);
        rbmedia = findViewById(R.id.rbMedia);
        rbAlta= findViewById(R.id.rbAlta);
        cbxUrgente = findViewById(R.id.cbxUrgente);
        imgFoto = findViewById(R.id.imgFoto);
        btnSeleccionarFoto= findViewById(R.id.btnSeleccionarFoto);
        btnGrabar = findViewById(R.id.FAB);


        txtTipoEmergencia.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                vTipoEmergencia));

        //selecionar foto al precionar boton
        btnSeleccionarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //generar objeto de tipo generico
                Intent oIntento = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //startActivity(oIntento);
                irActividaGaleriaFoto.launch(oIntento);
            }
        });
        irActividaGaleriaFoto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result->{
                          if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                              uriFoto = result.getData().getData();
                              imgFoto.setImageURI(uriFoto);
                          }
                });
        //crear un disparador
        btnGrabar.setOnClickListener(v-> {
            grabarObjetoEmergencia();
        });
    }

    private void grabarObjetoEmergencia() {
        //validar
        if(txtDescripcion.getText().toString().isEmpty()){
            txtDescripcion.setError("Campo Descripción es obligatorio ");
            txtDescripcion.requestFocus();
            return;
        }
        if(txtTipoEmergencia.getText().toString().isEmpty()){
            txtTipoEmergencia.setError("Campo  es obligatorio ");
            txtTipoEmergencia.requestFocus();
            return;
        }
        if ( ! (rBaja.isChecked() || rbmedia.isChecked() || rbAlta.isChecked()) ) { //cuando ningun radiobuton este seleccionado
            Toast.makeText(this,"Seleccione Gravedad de Emergencia",Toast.LENGTH_LONG).show();
            rbmedia.setChecked(true);
            return;
        }
        if (uriFoto==null){
            Toast.makeText(this,"Cargar Imagen",Toast.LENGTH_LONG).show();
            return;
        }
        //capturar en varibles los datos ingresados por el usuario
        String descripcion = txtDescripcion.getText().toString();
        String tipoEmergencia = txtTipoEmergencia.getText().toString();
        String gravedad= "";
        if (rBaja.isChecked()){
            gravedad="Baja";
        } else if (rbmedia.isChecked()) {
            gravedad="Media";
        }else{
            gravedad="Alta";
        }

        boolean urgencia = cbxUrgente.isChecked();
        //crear objeto emergencia
        Emergencia oE = new Emergencia(descripcion,tipoEmergencia,gravedad,urgencia,uriFoto);
        //Utilizar la clase DAOEmergencia para registrar objetoEmergencia a la lista
        DAOEmergencia.getInstacia().insertarEmergencia(oE);
        Toast.makeText(this,"Registro Aceptado",Toast.LENGTH_LONG).show();
        finish();
    }
}