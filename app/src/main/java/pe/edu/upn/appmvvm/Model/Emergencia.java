package pe.edu.upn.appmvvm.Model;

public class Emergencia {
    private String tipoEmergencia;
    private String ubicacion;
    private String Descripcion;

    public Emergencia(String tipoEmergencia, String ubicacion, String descripcion) {
        this.tipoEmergencia = tipoEmergencia;
        this.ubicacion = ubicacion;
        Descripcion = descripcion;
    }

    public String getTipoEmergencia() {
        return tipoEmergencia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getDescripcion() {
        return Descripcion;
    }
}
