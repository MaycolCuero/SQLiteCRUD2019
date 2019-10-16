package com.example.users;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.users.clases.connectionBD;

public class SignUp extends AppCompatActivity {
    EditText fname, lname, email, passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fname = findViewById(R.id.idFname);
        lname = findViewById(R.id.idLname);
        email = findViewById(R.id.idEmail);
        passwd = findViewById(R.id.idPassword);
    }

    public void Signup(View view){
        //crear el asistente de conexion
        connectionBD manager = new connectionBD( this, "market", null, 1);
        //leer y escribir en la BD
        SQLiteDatabase market = manager.getWritableDatabase();
        //Obtener valores
        String Fname = fname.getText().toString();
        String Lname = lname.getText().toString();
        String Email = email.getText().toString();
        String Passwd = passwd.getText().toString();

        if (!Fname.isEmpty() && !Lname.isEmpty() && !Email.isEmpty()
                && !Passwd.isEmpty()){

            // validation: Don`t repeat email if exists

            Cursor row = market.rawQuery
                    ("SELECT email FROM users " +
                            "WHERE email = '" +  Email + "'", null);

            //if( row.moveToFirst()){ // se ubica en  la primera posision es decir debuelve un true si hay algun elemento
            if(row.getCount() > 0){
                Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
            }else{
                ContentValues DATA = new ContentValues();

                DATA.put("firstname", Fname);
                DATA.put("lastname", Lname);
                DATA.put("email", Email);
                DATA.put("password", Passwd);
                fname.setText("");
                passwd.setText("");
                email.setText("");
                lname.setText("");

                //Guardar valores en BD
                market.insert("users", null, DATA);
                Toast.makeText(this, "El usuario fue creado", Toast.LENGTH_SHORT).show();
                market.close();
            }

        }else{
            Toast.makeText(this, "There are empty fields ", Toast.LENGTH_SHORT).show();
            fname.setError("field can`t be emty");
            passwd.setError("field can`t be emty");
            email.setError("field can`t be emty");
            lname.setError("field can`t be emty");
        }

    }
}
