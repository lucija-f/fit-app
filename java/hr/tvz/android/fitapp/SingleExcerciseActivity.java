package hr.tvz.android.fitapp;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SingleExcerciseActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView timerTextView;
    private ImageView imageView;
    private CountDownTimer countDownTimerReady;
    private long readyTimeLeft = 5000;
   private static final long EXERCISE_TIME = 10000;
    private long exerciseTimeLeft = EXERCISE_TIME;
    private Button btnAgain;
    private CountDownTimer countDownTimerStart;
    private TextView getReadyText;
    MediaPlayer mediaPlayer;

    private static final String TAG = "SingleExcerciseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_exercise);

        getAndSetIncomingIntent();
        startReadyTime();

    }



    private void getAndSetIncomingIntent(){

        nameTextView = findViewById(R.id.singleExercise_name_id);
        imageView = findViewById(R.id.singleExercise_image_id);

        Log.d(TAG, "checking for incoming intents");
        if (getIntent().hasExtra("name") && getIntent().hasExtra("img_url")){
            Log.d(TAG, "incoming intents are found");

            nameTextView.setText(getIntent().getStringExtra("name"));

            Glide.with(this)
                    .load(getIntent().getStringExtra("img_url"))
                    .into(imageView);
        }
    }


    private void startReadyTime(){

        getReadyText = findViewById(R.id.getReady_id);
        getReadyText.setVisibility(View.VISIBLE);
        countDownTimerReady = new CountDownTimer(readyTimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                readyTimeLeft = millisUntilFinished;
                playSoundTick();
                updateTimerTextView(readyTimeLeft);

            }

            @Override
            public void onFinish() {
                getReadyText.setVisibility(View.INVISIBLE);
                startExcerciseTime();
            }
        }.start();
    }


    private void playSoundTick(){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this, R.raw.bip);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        mediaPlayer.start();
    }

    private void playSoundFinish(){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this, R.raw.triplebip);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        mediaPlayer.start();
    }

    private void stopPlayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    @Override
    protected void onStop(){
        super.onStop();
        stopPlayer();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
            if (countDownTimerStart != null || countDownTimerReady != null) {
                countDownTimerStart.cancel();
                countDownTimerStart = null;
                countDownTimerReady.cancel();
                countDownTimerReady = null;
                stopPlayer();
            }
            finish();
    }


    private void startExcerciseTime() {

        timerTextView = findViewById(R.id.timer_id);
        timerTextView.setTextColor(Color.parseColor("#FF000000"));

        getReadyText = findViewById(R.id.getReady_id);
        getReadyText.setBackgroundColor(Color.parseColor("#FF26ACD9"));
        getReadyText.setText("GO!");
        getReadyText.setVisibility(View.VISIBLE);

        btnAgain = findViewById(R.id.btn_id);
        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
                startExcerciseTime();
            }
        });

        countDownTimerStart = new CountDownTimer(exerciseTimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                exerciseTimeLeft = millisUntilFinished;
                updateTimerTextView(exerciseTimeLeft);

            }

            @Override
            public void onFinish() {
                getReadyText.setText("YOU ARE DONE !!!");
                btnAgain.setVisibility(View.VISIBLE);
                playSoundFinish();
            }
        }.start();
    }



    private void resetTimer() {
        exerciseTimeLeft = EXERCISE_TIME;
        updateTimerTextView(exerciseTimeLeft);
        btnAgain.setVisibility(View.INVISIBLE);
    }


    private void updateTimerTextView(long timeLeft) {
        int minutes = (int) timeLeft / 1000 / 60;
        int sec = (int) timeLeft / 1000 % 60;

        String timeFormat = String.format("%02d:%02d", minutes, sec);
        timerTextView = findViewById(R.id.timer_id);
        timerTextView.setText(timeFormat);
    }





}