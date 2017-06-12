package com.android.giomar.prueba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static com.android.giomar.prueba.R.layout.activity_training;

public class Training extends AppCompatActivity {


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_training);

        listView = (ListView)findViewById(R.id.lvLista);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(Training.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.exercises));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Training.this, ListActivity.class);
                intent.putExtra("exercises", listView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });
        listView.setAdapter(mAdapter);
    }
}