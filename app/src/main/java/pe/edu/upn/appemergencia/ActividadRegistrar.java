package pe.edu.upn.appemergencia;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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
import androidx.appcompat.app.AlertDialog;
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

import java.io.ByteArrayOutputStream;

import pe.edu.upn.appemergencia.Access.DAOEmergencia;
import pe.edu.upn.appemergencia.Access.DAOEmergenciaBD;
import pe.edu.upn.appemergencia.Models.Emergencia;
import pe.edu.upn.appemergencia.Models.EmergenciaBD;

public class ActividadRegistrar extends AppCompatActivity {
    private TextInputEditText txtDescripcion;
    private AutoCompleteTextView txtTipoEmergencia;
    private MaterialRadioButton rbBaja,rbMedia, rbAlta;
    private MaterialCheckBox cbxUrgente;
    private ImageView imgFoto;
    private Uri uriFoto=null; //almacena la ruta donde està ubicada la imagen
    private String[] vTipoEmergencia = {"Accidente","Incendio","Terremoto","Sunami"};
    private MaterialButton btnSelecionarFoto;
    //Declarar una variable para ir a la actividad del sistema de galeria de fotos
    private ActivityResultLauncher<Intent> irActividadGaleriaFotos;
    private FloatingActionButton btnGrabar;
    private byte[] imagenSeleccionada=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_registrar);
        uriFoto=null;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtTipoEmergencia = findViewById(R.id.spListaTipoEmergencia);
        rbBaja = findViewById(R.id.rbBaja);
        rbMedia = findViewById(R.id.rbMedia);
        rbAlta = findViewById(R.id.rbAlta);
        cbxUrgente = findViewById(R.id.cbxUrgente);
        imgFoto = findViewById(R.id.imgFoto);
        btnSelecionarFoto = findViewById(R.id.btnSeleccionarFoto);
        btnGrabar = findViewById(R.id.FAB);

        txtTipoEmergencia.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                vTipoEmergencia));
        btnSelecionarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Definir un objeto intento de tipo genérico
                Intent oIntento = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //startActivity(oIntento);
                irActividadGaleriaFotos.launch(oIntento);
            }
        });
        irActividadGaleriaFotos = registerForActivityResult( new ActivityResultContracts.StartActivityForResult(),
                result->{
                        if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                            uriFoto = result.getData().getData();
                            imgFoto.setImageURI(uriFoto);
                            //Convertir de URI a byte[]
                            imgFoto.buildDrawingCache();
                            Bitmap oGrafico = imgFoto.getDrawingCache();
                            ByteArrayOutputStream oFlujo = new ByteArrayOutputStream();
                            oGrafico.compress(Bitmap.CompressFormat.PNG,0,oFlujo);
                            imagenSeleccionada = oFlujo.toByteArray();
                        }
                });
        btnGrabar.setOnClickListener(v->{
            grabarObjetoEmergencia();
        });
    }

    private void grabarObjetoEmergencia() {
        //validar
        if(txtDescripcion.getText().toString().isEmpty()){
            txtDescripcion.setError("Campo descripción es obligatorio");
            txtDescripcion.requestFocus();
            return;
        }
        if(txtTipoEmergencia.getText().toString().isEmpty()){
            txtTipoEmergencia.setError("Seleccionar opción del tipo de Emergencia");
            txtTipoEmergencia.requestFocus();
            return;
        }
        if( !(rbBaja.isChecked() || rbMedia.isChecked() || rbAlta.isChecked()) ){ // cuando ningún radioButton este seleccionado
            Toast.makeText(this,"Selecciona un tipo de Gravedad",Toast.LENGTH_LONG).show();
            rbMedia.setChecked(true);
            return;
        }
        if(uriFoto==null){
            Toast.makeText(this,"Cargar una imagen",Toast.LENGTH_LONG).show();
            return;
        }
        //Capturar en variables los datos ingresados por el usuario
        String descripcion = txtDescripcion.getText().toString();
        String tipoEmergencia = txtTipoEmergencia.getText().toString();
        String gravedad = "";
        if(rbBaja.isChecked())
            gravedad="Baja";
        else if (rbMedia.isChecked()) {
            gravedad="Media";
        }else
            gravedad="Alta";
        boolean urgencia = cbxUrgente.isChecked();
        //Crear un objeto Emergencia en memoria RAM
      /*  Emergencia oE = new Emergencia(descripcion,tipoEmergencia,gravedad,urgencia,uriFoto);
        //Utilizar la clase DAOEmergencia para insertar objeto Emergencia a la lista
        DAOEmergencia.getInstancia().insertarEmergencia(oE);*/
        //Crear un objeto Emergencia en BD SQLite
        EmergenciaBD oE = new EmergenciaBD(descripcion,tipoEmergencia,
                gravedad,urgencia,imagenSeleccionada);
        DAOEmergenciaBD oBDEmergencia = new DAOEmergenciaBD(this);
        String rpta = oBDEmergencia.addEmergenciaBD(oE);
        if(rpta=="OK")
            Toast.makeText(this,"Registro aceptado",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,rpta,Toast.LENGTH_LONG).show();
        CuadroDialogo();
    }

    private void CuadroDialogo() {
        AlertDialog.Builder oDialogo = new AlertDialog.Builder(this);
        oDialogo.setTitle("Aviso");
        oDialogo.setMessage("¿Desea seguir registrando emergencias?");
        oDialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                ActividadRegistrar.this.finish();
            }
        });
        oDialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                limpar();
            }
        });
        oDialogo.show();
    }

    private void limpar() {
        txtDescripcion.setText("");
        txtTipoEmergencia.setText("");
        rbBaja.setChecked(false);
        rbMedia.setChecked(false);
        rbAlta.setChecked(false);
        cbxUrgente.setChecked(false);
        uriFoto=null;
        imgFoto.setImageResource(R.drawable.faltafoto);
        txtDescripcion.requestFocus();
    }
}