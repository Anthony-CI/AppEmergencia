package pe.edu.upn.appmvvm.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import pe.edu.upn.appmvvm.Model.Emergencia;

public class EmergenciaViewModel extends ViewModel {

    //equivalente a un array list
    private final MutableLiveData<String> oLiveData = new MutableLiveData<>();

    public void enviarRespuesta(String tipoEmergencia, String ubicacion, String descripcion){
        Emergencia oE = new Emergencia(tipoEmergencia,ubicacion,descripcion);
        oLiveData.setValue("Reporte Enviado :" + " : " + tipoEmergencia + " : " +  "ubicado: "+ ubicacion + ", y suceso" + descripcion);
        // invocar a metodos implementados en esta misma clase toda la logica del negocio
        //verificarUbicacion();

        /*
        public boolean verificarUbicacion(){
        }
         */

    }

    public LiveData<String> getResultado(){
        return oLiveData;
    }

}
