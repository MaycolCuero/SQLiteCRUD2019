package com.example.users;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.users.clases.connectionBD;

public class SignIn extends AppCompatActivity {
    EditText username, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = findViewById(R.id.idEmail);
        pass = findViewById(R.id.idPassword);

    }

    public void Signin(View view){
        //crear el asistente de conexion
        connectionBD manager = new connectionBD( this, "market", null, 1);
        //leer y escribir en la BD
        SQLiteDatabase market = manager.getWritableDatabase();
        //Obtener valores
        String Uname = username.getText().toString();
        String Passwd = pass.getText().toString();

        if (!Uname.isEmpty() && !Passwd.isEmpty()){

            Cursor row = market.rawQuery(
                "SELECT *  FROM users WHERE email = '" + Uname + "'" +
                        "AND password = '" + Passwd + "'", null
            );

            if(row.getCount() > 0){


            }else{

            }

        }
    }
}
