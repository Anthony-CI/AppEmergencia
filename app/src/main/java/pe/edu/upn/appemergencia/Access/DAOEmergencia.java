package pe.edu.upn.appemergencia.Access;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upn.appemergencia.Models.Emergencia;

public class DAOEmergencia {
    private static DAOEmergencia instancia;
    private List<Emergencia> lista;
    public DAOEmergencia(){
        lista = new ArrayList<>();
    }
    public static DAOEmergencia getInstancia(){
        if(instancia==null){
            instancia = new DAOEmergencia();
        }
        return instancia;
    }
    public void insertarEmergencia(Emergencia oE){
        lista.add(oE);
    }
    public List<Emergencia> mostrarEmergencia(){
        return lista;
    }

}
