package pe.edu.upn.appemergencia.Access;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upn.appemergencia.Model.Emergencia;

public class DAOEmergencia {
    public static DAOEmergencia instacia;
    private List<Emergencia> lista;

    public DAOEmergencia(){
        lista = new ArrayList<>();
    }

    public static  DAOEmergencia getInstacia(){
        if(instacia == null){
            instacia = new DAOEmergencia();
        }
        return  instacia;
    }

    public void insertarEmergencia(Emergencia oE){
        lista.add(oE);
    }

    public List<Emergencia> mostrarEmergencia(){
        return lista;
    }
}
