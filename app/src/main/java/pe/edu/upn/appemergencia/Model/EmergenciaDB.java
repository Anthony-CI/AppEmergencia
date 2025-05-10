package pe.edu.upn.appemergencia.Model;

import android.net.Uri;

import androidx.annotation.NonNull;

public class EmergenciaDB {
    private int idEmergencia;
    private String descripcion;
    private String tipoEmergencia;
    private String gravedad;
    private boolean urgente;
    private byte[] foto;

    public EmergenciaDB(String descripcion, String tipoEmergencia, String gravedad, boolean urgente, byte[] foto) {
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

    public boolean getUrgente() {
        return urgente;
    }

    public byte[] getFoto() {
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
