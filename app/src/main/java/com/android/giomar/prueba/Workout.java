package com.android.giomar.prueba;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Workout extends AppCompatActivity implements View.OnClickListener {

    private String ever;
    private String info[]={

            "Date","Exercise","Repet"
    };
    private int rep;
    private int cont=1;
    Boolean n = true;
    private long timeCountInMilliSeconds = 1 * 60000;

    private enum TimerStatus {
        STARTED,
        STOPPED
    }

    private TimerStatus timerStatus = TimerStatus.STOPPED;

    private ProgressBar progressBarCircle;
    private EditText editTextMinute;
    private EditText editTextRep;
    private TextView textViewTime;
    private TextView textViewRep;
    private ImageView imageViewReset;
    private ImageView imageViewStartStop;
    private ImageView imageViewSave;
    private CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Intent intname = getIntent();
        Bundle extras = intname.getExtras();

        info[1] = extras.getString("INFO");

        // method call to initialize the views
        initViews();
        // method call to initialize the listeners
        initListeners();


    }

    /**
     * method to initialize the views
     */
    private void initViews() {
        progressBarCircle = (ProgressBar) findViewById(R.id.progressBarCircle);
        editTextMinute = (EditText) findViewById(R.id.editTextMinute);
        editTextRep = (EditText) findViewById(R.id.editTextRep);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewRep = (TextView) findViewById(R.id.textViewRep);
        imageViewReset = (ImageView) findViewById(R.id.imageViewReset);
        imageViewStartStop = (ImageView) findViewById(R.id.imageViewStartStop);
        imageViewSave = (ImageView) findViewById(R.id.imageViewSave);


        //
    }

    /**
     * method to initialize the click listeners
     */
    private void initListeners() {
        imageViewReset.setOnClickListener(this);
        imageViewStartStop.setOnClickListener(this);
        imageViewSave.setOnClickListener(this);
    }

    /**
     * implemented method to listen clicks
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewReset:
                reset();
                break;
            case R.id.imageViewStartStop:
                startStop();
                break;
            case R.id.imageViewSave:
                Salva();
                break;
        }
    }

    private void Salva() {
        //Toast.makeText(getApplicationContext(),"Info: "+info[0], Toast.LENGTH_SHORT).show();
        Intent intTrack = new Intent(Workout.this,Tracking.class);
        String fecha = new Date().toString();
        Toast.makeText(getApplicationContext(),"Info: "+fecha, Toast.LENGTH_SHORT).show();
        info[0] = fecha;
        intTrack.putExtra("DATE",info[0]);
        intTrack.putExtra("EXE",info[1]);
        intTrack.putExtra("REP",info[2]);
        startActivity(intTrack);


    }
    /**
     * method to reset count down timer
     */
    private void reset() {
        stopCountDownTimer();
        startCountDownTimer();
    }


    /**
     * method to start and stop count down timer
     */
    private void startStop() {

        if (timerStatus == TimerStatus.STOPPED) {

            // call to initialize the timer values
            setTimerValues();
            setRepValues();
            // call to initialize the progress bar values
            setProgressBarValues();
            // showing the reset icon
            imageViewReset.setVisibility(View.VISIBLE);
            //Salva invisible
            imageViewSave.setVisibility(View.INVISIBLE);
            // changing play icon to stop icon
            imageViewStartStop.setImageResource(R.drawable.icon_stop);
            // making edit text not editable
            editTextMinute.setEnabled(false);
            editTextRep.setEnabled(false);
            textViewRep.setEnabled(false);
            // changing the timer status to started
            timerStatus = TimerStatus.STARTED;
            // call to start the count down timer
            startCountDownTimer();

        } else {

            // hiding the reset icon
            imageViewReset.setVisibility(View.GONE);
            imageViewReset.setVisibility(View.GONE);
            // changing stop icon to start icon
            imageViewStartStop.setImageResource(R.drawable.icon_start);
            // making edit text editable
            editTextMinute.setEnabled(true);
            // changing the timer status to stopped
            timerStatus = TimerStatus.STOPPED;
            stopCountDownTimer();

        }

    }

    private void setRepValues() {
        rep = Integer.parseInt(editTextRep.getText().toString().trim());
        info[2]= String.valueOf(rep);
        ever = editTextRep.getText().toString();
        textViewRep.setText(+(cont)+"/"+ever);

    }

    /**
     * method to initialize the values for count down timer
     */
    private void setTimerValues() {
        int time = 0;
        if (!editTextMinute.getText().toString().isEmpty()) {
            // fetching value from edit text and type cast to integer

               time = Integer.parseInt(editTextMinute.getText().toString().trim());

        } else {
            // toast message to fill edit text
            Toast.makeText(getApplicationContext(), getString(R.string.message_minutes), Toast.LENGTH_LONG).show();
        }
        // assigning values after converting to milliseconds


        timeCountInMilliSeconds = time * 60 * 1000;
    }

    /**
     * method to start count down timer
     */
    private void startCountDownTimer() {

        countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                textViewTime.setText(hmsTimeFormatter(millisUntilFinished));

                progressBarCircle.setProgress((int) (millisUntilFinished / 1000));


            }

            @Override
            public void onFinish() {

                //if (cont==3){

                textViewTime.setText(hmsTimeFormatter(timeCountInMilliSeconds));
                //String Q = textViewTime.getText().toString();



                if (rep==1){ n=false; }
                if(n){


                    reset();
                    rep--;
                    cont++;
                    textViewRep.setText(+cont+"/"+ever);

                    imageViewReset.setVisibility(View.VISIBLE);
                    // changing play icon to stop icon
                    imageViewStartStop.setImageResource(R.drawable.icon_stop);
                    // making edit text not editable
                    editTextMinute.setEnabled(false);

                    // changing the timer status to started
                    timerStatus = TimerStatus.STARTED;
                }else{

                    // call to initialize the progress bar values
                    setProgressBarValues();
                    // hiding the reset icon
                    imageViewReset.setVisibility(View.GONE);

                    // changing stop icon to start icon
                    imageViewStartStop.setImageResource(R.drawable.icon_start);
                    // making edit text editable
                    editTextMinute.setEnabled(true);
                    editTextRep.setEnabled(true);
                    textViewRep.setEnabled(true);
                    // changing the timer status to stopped



                    imageViewSave.setVisibility(View.VISIBLE);
                    timerStatus = TimerStatus.STOPPED;

                }


            }

        }.start();

        countDownTimer.start();
    }

    /**
     * method to stop count down timer
     */
    private void stopCountDownTimer() {
        countDownTimer.cancel();
    }

    /**
     * method to set circular progress bar values
     */
    private void setProgressBarValues() {

        progressBarCircle.setMax((int) timeCountInMilliSeconds / 1000);
        progressBarCircle.setProgress((int) timeCountInMilliSeconds / 1000);
    }


    /**
     * method to convert millisecond to time format
     *
     * @param milliSeconds
     * @return HH:mm:ss time formatted string
     */
    private String hmsTimeFormatter(long milliSeconds) {

            String hms = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(milliSeconds),
                    TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                    TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));

            return hms;

        }
}
