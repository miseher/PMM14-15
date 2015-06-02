package com.example.miguel.proyecto;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class UpdateActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final DataBaseAdapter myDb = new DataBaseAdapter(this);
        final ArrayList<HashMap<String, String>> ListaParte = myDb.seleccionarTodos();

        // listView1
        ListView lisView1 = (ListView)findViewById(R.id.listView1);

        SimpleAdapter sAdap;
        sAdap = new SimpleAdapter(UpdateActivity.this, ListaParte, R.layout.activity_column,
                new String[] {"ParteID", "AULA", "DESCRIPCION"}, new int[] {R.id.ColParteID, R.id.ColAula, R.id.ColDescripcion}); //Member ID, Name , Tel
        lisView1.setAdapter(sAdap);

        lisView1.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {


                Intent newActivity = new Intent(UpdateActivity.this,UpdateDetailActivity.class);
                newActivity.putExtra("PteID", ListaParte.get(position).get("ParteID").toString());
                startActivity(newActivity);

            }
        });


        // btnCancel (Cancel)
        final Button atras = (Button) findViewById(R.id.btnBackUpdate);
        atras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent newActivity = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(newActivity);
            }
        });

    }




    }





