package pe.edu.upn.appemergencia.Access;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upn.appemergencia.Model.Emergencia;
import pe.edu.upn.appemergencia.Model.EmergenciaDB;

public class DAOEmergenciaDB {
    private String nameDB;
    private int version;
    private BDOpenHelper oBDcontructor;

    public DAOEmergenciaDB(Activity contexto) {
        this.nameDB = "BDEmergencia";
        this.version = 1;
        this.oBDcontructor = new BDOpenHelper(contexto,nameDB,null,version);
    }

    public String addEmergenciaBD(EmergenciaDB oE){
        String rpta= " ";
        SQLiteDatabase oBD= oBDcontructor.getWritableDatabase();
        ContentValues oColumnas = new ContentValues();
        oColumnas.put("descripcion",oE.getDescripcion());
        oColumnas.put("tipoEmergencia", oE.getTipoEmergencia());
        oColumnas.put("gravedad", oE.getGravedad());
        oColumnas.put("urgente", oE.getUrgente()?0:1);
        oColumnas.put("foto", oE.getFoto());
        long fila=oBD.insert("Emergencia",null,oColumnas);
        if(fila >0){
            rpta ="OK";
        }else {
            rpta="Error : Registro invalido";
        }
        oBD.close();
        return rpta;
    }

    public  List<EmergenciaDB> getListadoEmergencia(){
        List<EmergenciaDB> lista = new ArrayList<EmergenciaDB>();
        SQLiteDatabase oBD = oBDcontructor.getReadableDatabase();
        String sql = "SELECT * FROM Emergencia";
        Cursor oRegistros = oBD.rawQuery(sql, null);
        if(oRegistros.moveToFirst()) {

           do{
               String descripcion = oRegistros.getString(1);
               String tipoEmergencia = oRegistros.getString(2);
               String gravedad = oRegistros.getString(3);
               boolean urgencia = oRegistros.getInt(4) == 0 ? true : false;
               byte[] foto = oRegistros.getBlob(5);
               EmergenciaDB oE = new EmergenciaDB(descripcion, tipoEmergencia, gravedad, urgencia, foto);
               lista.add(oE);
           }while (oRegistros.moveToNext());
           oBD.close();
           oRegistros.close();
        }

        return  lista;
    }
}
