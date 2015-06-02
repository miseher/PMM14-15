package com.example.miguel.proyecto;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {


    Button salir,nuevoParte, verPartes, borrareditarPartes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salir=(Button)findViewById(R.id.btnExit);
        nuevoParte=(Button)findViewById(R.id.btnNuevoParte);
        verPartes=(Button)findViewById(R.id.btnRevisarParte);
        borrareditarPartes=(Button)findViewById(R.id.btnVerPartes);

        //BOTON AÑADIR PARTE
        nuevoParte.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentAdd = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intentAdd);
            }
        });


        //BOTON VISUALIZAR PARTES Y LOS DETALLES DEL PARTE
        verPartes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentShow = new Intent(MainActivity.this, show.class);
                startActivity(intentShow);
            }
        });

        borrareditarPartes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentShow = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(intentShow);
            }
        });





        //BOTON SALIR
        salir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //System.exit(0);
                //finish();
                Intent intentExit = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentExit);


            }
        });


    }



}
