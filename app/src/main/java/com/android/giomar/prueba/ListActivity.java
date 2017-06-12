package com.android.giomar.prueba;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ListActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btnEmpezar;
    private Bitmap loadedImage;
    private String real;
    private String exe[] = {

            "Flexiones",
            "Sentadillas",
            "Zancadas",
            "Abdominales",
            "Saltos",
            "Mancuernas pecho",
            "Mancuernas brazos",
            "Barras",
            "Dorsales"};

    private String imgUrl[] = {

            "http://www.gifmania.com/Gif-Animados-Deportes/Imagenes-Fitness/Flexiones/Flexiones-Pierna-51230.gif",
            "http://1.bp.blogspot.com/-lgLfcUp6ko0/VkPBuDZyH4I/AAAAAAAAMq8/xiN4p646ocU/s1600/sentadillas.gif",
            "http://www.retomh.es/wp-content/uploads/2014/01/zancuerna-cabeza-2.png",
            "http://www.canalgif.net/Gifs-animados/Deportes/Abdominales/Imagen-animada-Abdominales-03.gif",
            "http://cto-am.com/images/rhb/rhbmi/rodilla/pliom_cuclillas.gif",
            "http://www.rutinaejercicios.com/ejercicios-mancuernas_clip_image002.gif",
            "http://www.rutinaejercicios.com/ejercicios-mancuernas_clip_image009.gif",
            "https://s-media-cache-ak0.pinimg.com/736x/3b/e2/ca/3be2ca8a24945e1320ed2ad98675ba8f.jpg",
            "http://3.bp.blogspot.com/-5Hn7WDElVLM/URT2GIaV93I/AAAAAAAAAQo/6Cwyr_KlYqM/s1600/pelvis+curl+fot.jpg"};



    //String img = "http://3.bp.blogspot.com/-5Hn7WDElVLM/URT2GIaV93I/AAAAAAAAAQo/6Cwyr_KlYqM/s1600/pelvis+curl+fot.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Bundle bundle = getIntent().getExtras();
        real = bundle.getString("exercises");

        Toast.makeText(getApplicationContext(), "Real es: "+real, Toast.LENGTH_SHORT).show();

        imageView = (ImageView) findViewById(R.id.imageView);
        btnEmpezar = (Button) findViewById(R.id.btnStart);

       setImg();




       //Picasso.with(this).load().error(R.mipmap.ic_launcher).fit().centerInside().into(imageView);

        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intWorkout = new Intent(ListActivity.this,Workout.class);
                intWorkout.putExtra("INFO",real);
                startActivity(intWorkout);
            }
        });



    }

    //Función encuentra la imagen según la elección.

    public void setImg(){
        for (int i=0;i<=8;i++){
            if(real.equals(exe[i])){
                Picasso.with(this).load(imgUrl[i]).error(R.mipmap.ic_launcher).fit().centerInside().into(imageView);
            }
        }
    }

}