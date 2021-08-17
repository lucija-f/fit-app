package hr.tvz.android.fitapp;

import android.app.Application;
import android.view.View;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class DBFlowApp extends Application {

    public static ArrayList<Exercise> listExercises = new ArrayList<>();

    DatabaseHelper databaseHelper;


    @Override
    public void onCreate() {
        super.onCreate();
        // initiate DBFlow
        FlowManager.init(new FlowConfig.Builder(this).build());

        checkDB();
        exportExerciseListFromDB();

    }

    public void checkDB(){
        databaseHelper = new DatabaseHelper(this, "FitappDatabase.db", 1 );
        try {
            databaseHelper.provjeriDB();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            databaseHelper.otvoriDB();
        }catch (Exception e){e.printStackTrace();}
    }


    public void exportExerciseListFromDB(){

        List<ExerciseModel> listaDB = SQLite.select().from(ExerciseModel.class).queryList();

        for(ExerciseModel m : listaDB){
            listExercises.add(new Exercise(m.getId(), m.getName(), m.getImage(), m.getDescription() ));
        }
    }



}
