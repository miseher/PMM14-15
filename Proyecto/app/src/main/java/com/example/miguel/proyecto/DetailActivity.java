package com.example.miguel.proyecto;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailActivity extends ActionBarActivity {

    ArrayList<HashMap<String, String>> ListaParte;
    CheckBox cb;
    OnClickListener checkBoxListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        cb=(CheckBox) findViewById(R.id.checkBox1);

        Intent intent= getIntent();
        final String PteID = intent.getStringExtra("PteID");


        final DataBaseAdapter myDb = new DataBaseAdapter(this);

        // Show Data
        mostrarDatosDetalle(PteID);

        // btnCancel (Cancel)
        final Button backDetail = (Button) findViewById(R.id.btnbackDtail);
        backDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent newActivity = new Intent(DetailActivity.this, show.class);
                startActivity(newActivity);
            }
        });

        checkBoxListener =new OnClickListener() {


           @Override
            public void onClick(View v) {


               if(cb.isChecked()){

                   myDb.borrarIncidencia(PteID);
                   Toast.makeText(DetailActivity.this, "Eliminado de la lista, Incidencia resuelta ",
                           Toast.LENGTH_SHORT).show();
               }

            }
        };
        cb.setOnClickListener(checkBoxListener);



    }

    public void mostrarDatosDetalle(String ParteID)
    {

        final TextView tParteID = (TextView) findViewById(R.id.txtParteID);
        final TextView tAula = (TextView) findViewById(R.id.txtAula);
        final TextView tDes = (TextView) findViewById(R.id.txtDescripcion);


        final DataBaseAdapter myDb = new DataBaseAdapter(this);


        String arrData[] = myDb.SeleccionarDatos(ParteID);
        if(arrData != null)
        {
            tParteID.setText(arrData[0]);
            tAula.setText(arrData[1]);
            tDes.setText(arrData[2]);
        }

    }



    }





