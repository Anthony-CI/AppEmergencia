package pe.edu.upn.appemergencia.Model;

import android.net.Uri;

import androidx.annotation.NonNull;

public class Emergencia {
    private String descripcion;
    private String tipoEmergencia;
    private String gravedad;
    private boolean urgente;
    private Uri foto;

    public Emergencia(String descripcion, String tipoEmergencia, String gravedad, boolean urgente, Uri foto) {
        this.descripcion = descripcion;
        this.tipoEmergencia = tipoEmergencia;
        this.gravedad = gravedad;
        this.urgente = urgente;
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipoEmergencia() {
        return tipoEmergencia;
    }

    public String getGravedad() {
        return gravedad;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public Uri getFoto() {
        return foto;
    }
    @NonNull
    @Override

    public  String toString(){
        return tipoEmergencia+" : "+
                descripcion +
                "de gravedad " +" " + gravedad +
                " y " + (urgente? " es " : " no es ") +
                " " +" urgente ";
    }



}
