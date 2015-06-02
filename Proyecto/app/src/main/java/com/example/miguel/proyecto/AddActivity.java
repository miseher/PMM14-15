package com.example.miguel.proyecto;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        final Button save = (Button) findViewById(R.id.btnGuardar);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if(guardarDatosFormulario())
                {

                    Intent newActivity = new Intent(AddActivity.this,MainActivity.class);
                    startActivity(newActivity);
                }
            }
        });


        // btnCancel (Cancel)
        final Button cancel = (Button) findViewById(R.id.btnCancelar);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent newActivity = new Intent(AddActivity.this,MainActivity.class);
                startActivity(newActivity);
            }
        });

    }

    public boolean guardarDatosFormulario()
    {

        final EditText numParte = (EditText) findViewById(R.id.editTextParte);
        final EditText Aula = (EditText) findViewById(R.id.editTextaula);
        final EditText descripcion = (EditText) findViewById(R.id.editTextDes);

        // Dialog
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();


        if(numParte.getText().length() == 0)
        {
            ad.setMessage("Por favor pon el [Numero de parte] ");
            ad.show();
            numParte.requestFocus();
            return false;
        }


        if(Aula.getText().length() == 0)
        {
            ad.setMessage("Por favor introduce el [Aula] ");
            ad.show();
            Aula.requestFocus();
            return false;
        }


        if(descripcion.getText().length() == 0)
        {
            ad.setMessage("Por favor introduce la [Descripcion] ");
            ad.show();
            descripcion.requestFocus();
            return false;
        }


        final DataBaseAdapter myDb = new DataBaseAdapter(this);

        // comprueba si existe parte ID
        String arrData[] = myDb.SeleccionarDatos(numParte.getText().toString());
        if(arrData != null)
        {
            ad.setMessage("el ID del parte ya existe!  ");
            ad.show();
            numParte.requestFocus();
            return false;
        }

        // Save Data
        long saveStatus = myDb.InsertarDatos(numParte.getText().toString(),
                Aula.getText().toString(),
                descripcion.getText().toString());
        if(saveStatus <=  0)
        {
            ad.setMessage("Error, solo se admiten numeros en [ID Parte]!! ");
            ad.show();
            return false;
        }

        Toast.makeText(AddActivity.this, "Su parte ha sido añadido ",
                Toast.LENGTH_SHORT).show();

        return true;
    }

}













