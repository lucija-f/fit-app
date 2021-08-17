package hr.tvz.android.fitapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hr.tvz.android.fitapp.databinding.ActivityBmiBinding;

public class BMIActivity extends AppCompatActivity {


    ActivityBmiBinding binding;
    DatePickerDialog.OnDateSetListener dateSetListener;
    Toast toast;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBmiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.btnCalculateId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float height = Integer.parseInt(binding.inputHeightId.getText().toString());
                float weight = Integer.parseInt(binding.inputWeightId.getText().toString());
                float result = ((weight/height)/height)*10000;

                DecimalFormat decformat = new DecimalFormat("#.0");
                String resultString = decformat.format(result);
                binding.resultId.setText(resultString);

                if(result < 18.5){
                    binding.textViewMessageId.setText("UNDERWEIGHT");
                    binding.bmiBackgroundId.setBackgroundColor(Color.parseColor("#55B9FB"));
                }
                if (result >=18.5 && result <= 24.9){
                    binding.textViewMessageId.setText("NORMAL RANGE");
                    binding.bmiBackgroundId.setBackgroundColor(Color.parseColor("#86EF59"));
                }
                if (result >=25 && result <= 29.9){
                    binding.textViewMessageId.setText("OVERWEIGHT");
                    binding.bmiBackgroundId.setBackgroundColor(Color.parseColor("#F6E679"));
                }
                if (result >=30){
                    binding.textViewMessageId.setText("OBESE");
                    binding.bmiBackgroundId.setBackgroundColor(Color.parseColor("#FA0101"));
                }


                binding.btnAddId.setVisibility(View.VISIBLE);

            }
        });


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        binding.dateId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(BMIActivity.this, dateSetListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + "." + month + "." + year;
                binding.dateId.setText(date);
            }
        };

        binding.btnAddId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unesi();
                toast = Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT);
            }
        });

        binding.btnShowId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(BMIActivity.this, ResultListActivity.class));

            }
        });

    }



    private  void unesi(){
        CheckpointModel checkpoint = new CheckpointModel();

        checkpoint.setDate(binding.dateId.getText().toString());
        checkpoint.setResult(binding.resultId.getText().toString());
        checkpoint.setBMIdescription(binding.textViewMessageId.getText().toString());
        FlowManager.getModelAdapter(CheckpointModel.class).save(checkpoint);

        //ukloni tekst iz editTexta
        binding.resultId.setText("");
        binding.dateId.setText("");
        binding.textViewMessageId.setText("");
        binding.inputWeightId.setText("");
        binding.inputHeightId.setText("");
        binding.bmiBackgroundId.setBackgroundResource(R.color.light_fitapp);

    }


}
