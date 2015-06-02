package com.example.miguel.proyecto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends Activity {
    EditText editTextUserName,editTextPassword,editTextConfirmPassword;
    Button registrar, backToLogin;

    DataBaseAdapter dataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dataBaseAdapter =new DataBaseAdapter(this);
        dataBaseAdapter = dataBaseAdapter.open();

        editTextUserName=(EditText)findViewById(R.id.name);
        editTextPassword=(EditText)findViewById(R.id.password);
        editTextConfirmPassword=(EditText)findViewById(R.id.RepeatPassword);
        registrar=(Button)findViewById(R.id.btnRegister);
        backToLogin=(Button)findViewById(R.id.btnLinkToLoginScreen);

        //PARA VOLVER A LA PANTALLA DE LOGIN
        backToLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intentToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intentToLogin);

            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();
                if (userName.equals("") || password.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Hay Algun campo vacio", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                    return;
                } else {

                    dataBaseAdapter.insertarEntrada(userName, password);
                    Toast.makeText(getApplicationContext(), "Usuario creado correctamente ", Toast.LENGTH_LONG).show();
                }


            }
        });








    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        dataBaseAdapter.close();
    }
}
