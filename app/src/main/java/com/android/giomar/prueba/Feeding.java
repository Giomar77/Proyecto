package com.android.giomar.prueba;

import android.app.Notification;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Feeding extends AppCompatActivity {

    private ZXingScannerView scannerView;
    private TextView textViewQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeding);


        textViewQR = (TextView)findViewById(R.id.textViewQR);
    }

    public void scanCode(View view){

        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ZXingScannerResultHandler());

        setContentView(scannerView);
        scannerView.startCamera();

    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler
    {

        @Override
        public void handleResult(Result result){

            String resultCode = result.getText();

            textViewQR.setText(resultCode);

            Toast.makeText(Feeding.this, resultCode,Toast.LENGTH_LONG).show();

            setContentView(R.layout.activity_feeding);
            scannerView.stopCamera();


        }

    }
}