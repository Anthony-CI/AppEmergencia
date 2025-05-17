package pe.edu.upn.appemergencia.Access;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upn.appemergencia.Models.Emergencia;
import pe.edu.upn.appemergencia.Models.EmergenciaBD;

public class DAOEmergenciaBD {
    private String nombreBD;
    private int version;
    private BDOpenHelper oBDConstructor;

    public DAOEmergenciaBD(Activity contexto) {
        this.nombreBD = "BDEmergencias";
        this.version = 1;
        this.oBDConstructor = new BDOpenHelper(contexto,nombreBD,null,version);
    }

    public String addEmergenciaBD(EmergenciaBD oE){
        String rpta="";
        SQLiteDatabase oBD = oBDConstructor.getWritableDatabase();
        ContentValues oColumnas = new ContentValues();
        oColumnas.put("descripcion",oE.getDescripcion());
        oColumnas.put("tipoemergencia",oE.getTipoEmergencia());
        oColumnas.put("gravedad",oE.getGravedad());
        // 0 = Verdadero, 1 = Falso
        oColumnas.put("urgente",oE.isUrgente()?0:1);
        oColumnas.put("foto",oE.getFoto());
        long fila=oBD.insert("Emergencia",null,oColumnas);
        if(fila>0)
            rpta="OK";
        else
            rpta="Error: Registro inválido";
        oBD.close();
        return rpta;
    }
    public List<EmergenciaBD> getListadoEmergencia(){
        List<EmergenciaBD> lista = new ArrayList<EmergenciaBD>();
        SQLiteDatabase oBD = oBDConstructor.getReadableDatabase();
        String sql = "SELECT * FROM Emergencia";
        Cursor oRegistros = oBD.rawQuery(sql,null);
        if(oRegistros.moveToFirst()){
              do {
                  String descripcion = oRegistros.getString(1);
                  String tipoemergencia = oRegistros.getString(2);
                  String gravedad = oRegistros.getString(3);
                  boolean urgencia = oRegistros.getInt(4) == 0 ? true : false;
                  byte[] foto = oRegistros.getBlob(5);
                  EmergenciaBD oE = new EmergenciaBD(descripcion, tipoemergencia,
                          gravedad, urgencia, foto);
                  lista.add(oE);
              }while (oRegistros.moveToNext());
              oBD.close();
              oRegistros.close();
        }
        return lista;
    }

}
