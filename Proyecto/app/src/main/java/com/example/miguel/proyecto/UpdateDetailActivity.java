package com.example.miguel.proyecto;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class UpdateDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_detail);

        // Read var from Intent
        Intent intent= getIntent();
        final String PteID = intent.getStringExtra("PteID");

        // Show Data
        ShowData(PteID);

        // btnSave (Save)
        final Button actualizar = (Button) findViewById(R.id.btnActualizar);
        actualizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // If Save Complete
                if(UpdateData(PteID))
                {
                    // Open Form ListUpdate
                    Intent newActivity = new Intent(UpdateDetailActivity.this,DeleteActivity.class);//updateactivity
                    startActivity(newActivity);
                }
            }
        });

        // btnCancel (Cancel)
        final Button atras = (Button) findViewById(R.id.btnCancelUpdateDetail);
        atras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form ListUpdate
                Intent newActivity = new Intent(UpdateDetailActivity.this, DeleteActivity.class);
                startActivity(newActivity);
            }
        });

    }

    public void ShowData(String PteID)
    {
        // txtMemberID, txtName, txtTel
        final TextView tParteID = (TextView) findViewById(R.id.txtParteIDupdate);
        final EditText tAula = (EditText) findViewById(R.id.txtAulaUpdate);
        final EditText tDesc = (EditText) findViewById(R.id.txtDescUpdate);

        // new Class DB
        final DataBaseAdapter myDb = new DataBaseAdapter(this);

        // Show Data
        String arrData[] = myDb.SeleccionarDatos(PteID);
        if(arrData != null)
        {
            tParteID.setText(arrData[0]);
            tAula.setText(arrData[1]);
            tDesc.setText(arrData[2]);
        }

    }

    public boolean UpdateData(String PteID)
    {

        // txtName, txtTel
        final EditText tAula = (EditText) findViewById(R.id.txtAulaUpdate);
        final EditText tDesc = (EditText) findViewById(R.id.txtDescUpdate);

        // Dialog
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        AlertDialog ad = adb.create();


        if(tAula.getText().length() == 0)
        {
            ad.setMessage("Por favor introduce un [Aula] ");
            ad.show();
            tAula.requestFocus();
            return false;
        }


        if(tDesc.getText().length() == 0)
        {
            ad.setMessage("Por favor introduce una [Descripcion] ");
            ad.show();
            tDesc.requestFocus();
            return false;
        }


        final DataBaseAdapter myDb = new DataBaseAdapter(this);


        long saveStatus = myDb.actualizarDatos(PteID, tAula.getText().toString(), tDesc.getText().toString());
        if(saveStatus <=  0)
        {
            ad.setMessage("Error!! ");
            ad.show();
            return false;
        }

        Toast.makeText(UpdateDetailActivity.this,"Los datos se han actualizado. ",
                Toast.LENGTH_SHORT).show();

        return true;

    }




    }




