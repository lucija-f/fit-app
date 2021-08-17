package hr.tvz.android.fitapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context mcontext;
    String db;
    String dbPath;

    private static final String DATABASE_NAME = "FitappDatabase.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context, String name, int version ) {
        super(context, name, null, version);
        db = "FitappDatabase.db";
        this.mcontext = context;
        this.dbPath = "/data/data/" + context.getPackageName() + "/databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void provjeriDB (){
        SQLiteDatabase checkDB = null;
        String filePath = dbPath + DATABASE_NAME;


        try{
            checkDB = SQLiteDatabase.openDatabase(filePath, null, 0);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(checkDB != null){
            Toast.makeText(mcontext, "Baza podataka već postoji", Toast.LENGTH_SHORT).show();
        }else {
            kopirajDB();
        }

    }

    public void kopirajDB(){
        this.getReadableDatabase();

        try {
            InputStream ios = mcontext.getAssets().open(DATABASE_NAME);
            OutputStream os = new FileOutputStream(dbPath + DATABASE_NAME);

            byte[] buffer = new  byte[1024];

            int len;
            while ((len = ios.read(buffer)) > 0){
                os.write(buffer, 0, len);
            }

            os.flush();
            ios.close();
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d("KopirajDB", "Baza je kopirana.");
        Toast.makeText(mcontext, "Baza je kopirana", Toast.LENGTH_SHORT).show();
    }
    public void otvoriDB(){

        String filePath = dbPath + DATABASE_NAME;
        SQLiteDatabase.openDatabase(filePath, null, 0);
    }



}
