package com.example.miguel.proyecto;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class DeleteActivity extends ActionBarActivity {

    ArrayList<HashMap<String, String>> ListaParte;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        // Call Show List All Data
        mostrarListaDatos();

        // btnCancel (Cancel)
        final Button atras = (Button) findViewById(R.id.btnBackDelete);
        atras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                Intent newActivity = new Intent(DeleteActivity.this, MainActivity.class);
                startActivity(newActivity);
            }
        });

    }

    public void mostrarListaDatos()
    {
        final DataBaseAdapter myDb = new DataBaseAdapter(this);
        ListaParte = myDb.seleccionarTodos();

        // listView1
        ListView lisView1 = (ListView)findViewById(R.id.listView1);

       // SimpleAdapter sAdap;
        SpecialAdapter sAdap;
        sAdap = new SpecialAdapter(DeleteActivity.this, ListaParte, R.layout.activity_column,
                new String[] {"ParteID", "AULA", "DESCRIPCION"}, new int[] {R.id.ColParteID, R.id.ColAula, R.id.ColDescripcion});
        lisView1.setAdapter(sAdap);
        registerForContextMenu(lisView1);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle("Accion: ");
        String[] menuItems = getResources().getStringArray(R.array.Menu);
        for (int i = 0; i<menuItems.length; i++) {
            menu.add(Menu.NONE, i, i, menuItems[i]);
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.Menu);
        String NombreMenu = menuItems[menuItemIndex];
        String partID = ListaParte.get(info.position).get("ParteID").toString();


        // Check Event Command
        if ("Editar".equals(NombreMenu)) {


            Intent newActivity = new Intent(DeleteActivity.this,UpdateDetailActivity.class);
            newActivity.putExtra("PteID", ListaParte.get(info.position).get("ParteID").toString());
            startActivity(newActivity);


        } else if ("Borrar".equals(NombreMenu)) {

            DataBaseAdapter myDb = new DataBaseAdapter(this);

            long flag = myDb.borrarIncidencia(partID);
            if(flag > 0)
            {
                Toast.makeText(DeleteActivity.this, "Parte Borrado",
                        Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(DeleteActivity.this,"Fallo al borrar el Parte.",
                        Toast.LENGTH_LONG).show();
            }

            // Call Show Data again
            mostrarListaDatos();
        }

        return true;
    }




}
