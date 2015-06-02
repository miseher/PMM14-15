package com.example.miguel.proyecto;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class show extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        final TextView tParteID = (TextView) findViewById(R.id.ColParteID);
        final TextView tAula = (TextView) findViewById(R.id.ColAula);
        final TextView tDes = (TextView) findViewById(R.id.ColDescripcion);
        final DataBaseAdapter myDb = new DataBaseAdapter(this);
        final ArrayList<HashMap<String, String>> ListaPartes = myDb.seleccionarTodos();


        ListView lisView1 = (ListView)findViewById(R.id.listView1);

        //SimpleAdapter sAdap;
        SpecialAdapter sAdap;

        //Añadimos los datos al Adapter y le indicamos donde dibujar cada dato en la fila del Layout

        sAdap = new SpecialAdapter(show.this, ListaPartes, R.layout.activity_column,
                new String[] {"ParteID", "AULA", "DESCRIPCION"}, new int[] {R.id.ColParteID, R.id.ColAula, R.id.ColDescripcion});



        //sAdap.setViewText(tParteID,"INCIDENCIA");
        //sAdap.setViewText(tAula,"    ");
        //sAdap.setViewText(tDes,"   ");

        lisView1.setAdapter(sAdap);



        lisView1.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {

                // Show on new activity
                Intent newActivity = new Intent(show.this,DetailActivity.class);
                newActivity.putExtra("PteID", ListaPartes.get(position).get("ParteID"));
                startActivity(newActivity);

            }
        });


        // btn (Cancel)
        final Button back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                Intent newActivity = new Intent(show.this,MainActivity.class);
                startActivity(newActivity);
            }
        });

    }



}
