package pe.edu.upn.appemergencia.Access;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDOpenHelper extends SQLiteOpenHelper {
    String tabla_emergencia="CREATE TABLE Emergencia(IdEmergencia INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
            " descripcion VARCHAR(100) NOT NULL,tipoemergencia VARCHAR(20) NOT NULL,"+
            " gravedad VARCHAR(20) NOT NULL,urgente INTEGER NOT NULL,foto BLOB)";
    //String tabla_ciudad="";

    public BDOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
          db.execSQL(tabla_emergencia);
         // db.execSQL(tabla_ciudad);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Emergencia");
       // db.execSQL("DROP TABLE IF EXISTS Ciudad");
        db.execSQL(tabla_emergencia);
       // db.execSQL(tabla_ciudad);
    }
}
