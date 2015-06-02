package com.example.miguel.proyecto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {
    DataBaseAdapter dataBaseAdapter;

    Button login,register;
    EditText user, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // creamos instancia SQLite Database
        dataBaseAdapter =new DataBaseAdapter(this);
        dataBaseAdapter = dataBaseAdapter.open();

        //Referencia de los botones
        login=(Button)findViewById(R.id.btnLogin);
        register=(Button)findViewById(R.id.btnLinkToRegisterScreen);

        //referencia a los editText
        user=(EditText)findViewById(R.id.user);
        password=(EditText)findViewById(R.id.user);

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /*Intent i = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(i);*/


                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
        }
        });

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String userName = user.getText().toString();
                String passwd = password.getText().toString();

                // le pasamos el nombre de usuario y lo recoge de la base de datos
                String storedPassword = dataBaseAdapter.obtenerEntrada(userName);

                // comprueba si la contraseña almacenada concuerda con la introducida por el usuario
                if (passwd.equals(storedPassword)) {
                    Toast.makeText(LoginActivity.this, "Credenciales correctas, bienvenido/a", Toast.LENGTH_LONG).show();

                    Intent intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intentMainActivity);

                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Error: Usuario o Contraseña no validos", Toast.LENGTH_LONG).show();
                }


            }
        });




    }




    }


