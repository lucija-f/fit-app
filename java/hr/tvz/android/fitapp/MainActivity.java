package hr.tvz.android.fitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView card1, card2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1 = findViewById(R.id.cardviewWorkout);
        card2 = findViewById(R.id.cardviewBMI);

        card1.setOnClickListener(this::onClick);
        card2.setOnClickListener(this::onClick);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        Toast toast;
        switch (v.getId()){
            case R.id.cardviewWorkout :
                toast = Toast.makeText(getApplicationContext(), "Work hard!", Toast.LENGTH_SHORT);
                toast.show();
                intent = new Intent(MainActivity.this, WorkoutActivity.class);
                startActivity(intent);
                break;

            case R.id.cardviewBMI :
                toast = Toast.makeText(getApplicationContext(), "BMI", Toast.LENGTH_SHORT);
                toast.show();
                intent = new Intent(MainActivity.this, BMIActivity.class);
                startActivity(intent);
                break;

        }

    }


}