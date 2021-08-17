package hr.tvz.android.fitapp;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class WorkoutActivity extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        Adapter myAdapter = new Adapter(DBFlowApp.listExercises, this);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        else
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(myAdapter);
    }






}
