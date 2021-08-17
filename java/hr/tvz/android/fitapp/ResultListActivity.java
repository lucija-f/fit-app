package hr.tvz.android.fitapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import hr.tvz.android.fitapp.databinding.ActivityResultListBinding;


public class ResultListActivity extends AppCompatActivity {

    ArrayList<Checkpoint> checkpointList = new ArrayList<>();
    ActivityResultListBinding binding;
    int id;
    String date;
    String result;
    String BMIdescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityResultListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        exportCheckpointListFromDB();
        printCheckpoints();

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteCheckpoints();
            }
        });


    }

    public void exportCheckpointListFromDB(){
        List<CheckpointModel> checkpoint = SQLite.select().from(CheckpointModel.class).queryList();

        for (CheckpointModel cp : checkpoint){
            checkpointList.add(new Checkpoint(cp.getId(), cp.getDate(), cp.getResult(), cp.getBMIdescription()));
        }

    }

    private void printCheckpoints(){

        if (checkpointList.isEmpty())
            binding.checkpointsViewId.setText("");
        else {
            int i = 0;
            for (Checkpoint cp : checkpointList){
                id = cp.getId();
                date = cp.getDate();
                result = cp.getResult();
                BMIdescription = cp.getBMIdescription();
                i++;

                binding.checkpointsViewId.append(i + ". " + "\t\t" + date + "\t\t" + result + "\t\t" + BMIdescription + "\n\n");
            }
        }
    }

    public void deleteCheckpoints(){
        List<CheckpointModel> checkpoint = SQLite.select().from(CheckpointModel.class).queryList();
        for(CheckpointModel cp:checkpoint) {
            FlowManager.getModelAdapter(CheckpointModel.class).delete(cp);
        }
        checkpointList.clear();
        printCheckpoints();
    }
}
