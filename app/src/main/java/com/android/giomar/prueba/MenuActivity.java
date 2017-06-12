package com.android.giomar.prueba;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class MenuActivity extends AppCompatActivity {


    String arrayMenu[] = {

            "Entrenaminto",
            "Seguimiento",
            "Alimentación"
    };

    public static int s = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);


        CircleMenu circleMenu = (CircleMenu) findViewById(R.id.menu);

        circleMenu.setMainMenu(Color.parseColor(("#CDCDCD")),R.drawable.ic_ico,R.drawable.ic_ico)
                .addSubMenu(Color.parseColor("#258CFF"),R.drawable.ic_tra)
                .addSubMenu(Color.parseColor("#6d4641"),R.drawable.ic_seg)
                .addSubMenu(Color.parseColor("#03a9f4"),R.drawable.ic_ali).setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(int index) {

               Toast.makeText(MenuActivity.this, ""+arrayMenu[index], Toast.LENGTH_SHORT).show();
               // waittime(s);
                if(index == 0){startActivity(new Intent(getApplicationContext(),Training.class));};
                if(index == 1){startActivity(new Intent(getApplicationContext(),Tracking.class));};
                if(index == 2){startActivity(new Intent(getApplicationContext(),Feeding.class));};

                };
        });

    }

   /* public void waittime(int s) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, s);
    }*/

}
