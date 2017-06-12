package com.android.giomar.prueba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Tracking extends AppCompatActivity {
    String datos[]={"","",""};
    TextView textViewDate;
    TextView textViewExe;
    //TextView textViewRepet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);


        textViewDate = (TextView)findViewById(R.id.textViewDate);
        textViewExe = (TextView)findViewById(R.id.textViewExe);
        //textViewRepet = (TextView)findViewById(R.id.textViewRepet);

        Intent intInf = getIntent();
        Bundle extraInf = intInf.getExtras();

        datos[0] = extraInf.getString("DATE");
        textViewDate.setText("Fecha:\n"+datos[0]);

        datos[1] = extraInf.getString("EXE");
        datos[2] = extraInf.getString("REP");

        textViewExe.setText("Ejercicio: "+datos[1]+"\nRepeticiones: "+datos[2]);

    }
}
